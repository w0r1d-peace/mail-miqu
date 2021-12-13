package com.islet.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.common.util.RedisKeyUtil;
import com.islet.common.web.ResultCode;
import com.islet.domain.dto.base.UserLoginDTO;
import com.islet.domain.dto.base.UserPageDTO;
import com.islet.domain.dto.base.UserSaveOrUpdateDTO;
import com.islet.domain.vo.PageVO;
import com.islet.domain.vo.bese.UserPageVO;
import com.islet.exception.BusinessException;
import com.islet.mapper.base.UserMapper;
import com.islet.model.base.User;
import com.islet.service.base.IUserService;
import com.islet.util.MD5Util;
import com.islet.util.PageUtil;
import com.islet.util.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangJM.
 * @since 2021-09-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private RedisService redisService;

    @Value("${server.servlet.session.timeout:1800}")
    private Long timeout;

    @Override
    public User detail(Long id) {
        return super.getById(id);
    }


    @Override
    public void login(UserLoginDTO dto, String token) {
        // 获取表单验证码
        checkCaptcha(dto.getCaptcha());
        // 验证用户名和密码
        checkUserInfo(dto.getUsername(), dto.getPassword(), token);
    }

    @Override
    public PageVO<UserPageVO> userPage(UserPageDTO dto) {
        // 组装条件
        LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .lambda()
                .like(StringUtils.isNotEmpty(dto.getUsername()), User::getUsername, dto.getUsername())
                .like(StringUtils.isNotEmpty(dto.getName()), User::getName, dto.getName())
                .like(StringUtils.isNotEmpty(dto.getPhone()), User::getPhone, dto.getPhone())
                .eq(User::getUserId, dto.getUserId());

        return PageUtil.getPageVOByIPage(page -> (IPage<User>) super.page(page
                , queryWrapper)
                , dto.getCurrentPage()
                , dto.getPageSize()
                , UserPageVO.class);
    }

    @Override
    public Long saveUser(UserSaveOrUpdateDTO dto) {
        //查询用户名是否已经存在
        boolean exist = existByUsername(dto.getUsername(), null, dto.getUserId());
        if (exist) {
            throw new BusinessException(ResultCode.PARAMETER_FAIL, "用户名已经存在");
        }

        User user = new User();
        BeanUtils.copyProperties(dto, user);
        // 保存数据库再次加密
        user.setPassword(MD5Util.formPassToDBPass(dto.getPassword()));
        user.setCreateTime(new Date());
        user.setModified(new Date());
        user.setRemoved(false);
        user.setUserId(dto.getUserId());
        user.setCreator(dto.getCreator());
        //保存用户记录
        this.save(user);
        return user.getId();
    }

    @Override
    public Boolean editUser(UserSaveOrUpdateDTO dto) {
        //查询用户名是否已经存在
        boolean exist = existByUsername(dto.getUsername(), dto.getId(), dto.getUserId());
        if (exist) {
            throw new BusinessException(ResultCode.PARAMETER_FAIL, "用户名已经存在");
        }

        User user = this.getById(dto.getId());
        if (user == null) {
            throw new BusinessException(String.format("获取不到ID为{%s}的记录", dto.getId()));
        }
        user.setName(dto.getName());
        user.setPhone(dto.getPhone());
        user.setDescription(dto.getDescription());
        user.setModified(new Date());
        //保存用户记录
        return this.updateById(user);
    }

    @Override
    public Boolean deleteUser(List<Long> ids, Long userId, String createName) {
        return this.removeByIds(ids);
    }

    /**
     * 用户名是否已经存在
     * @param username
     * @param id
     * @param userId
     * @return
     */
    private boolean existByUsername(String username, Long id, Long userId) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username)
                .eq(User::getUserId, userId);

        if (id != null) {
            queryWrapper.ne(User::getId, id);
        }

        int count = this.count(queryWrapper);
        return count > 0 ? true : false;
    }

    /**
     * 验证表单验证码
     * @param captcha
     * @throws Exception
     */
    private void checkCaptcha(String captcha) {
        boolean exists = redisService.exists(RedisKeyUtil.CAPTCHA + captcha.toLowerCase());
        if (!exists) {
            throw new BusinessException("验证码不匹配");
        }
    }

    /**
     * 验证用户名和密码
     * @param username
     * @param password
     */
    private Long checkUserInfo(String username, String password, String token) {
        User user = this.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user == null) {
            throw new BusinessException(ResultCode.NOT_LOGIN, "账号不存在");
        }
        // 加密
        String dbPassword = MD5Util.formPassToDBPass(password);
        if (!StringUtils.equals(user.getPassword(), dbPassword)) {
            throw new BusinessException(ResultCode.NOT_LOGIN, "密码错误");
        }
        // 这里代码主要就是防止有两个一样的用户同时登陆
        Object o = redisService.get(RedisKeyUtil.USER_ID + user.getId());
        if (o != null) {
            String t = o.toString();
            redisService.remove(RedisKeyUtil.TOKEN + t);
        }

        boolean b1 = redisService.set(RedisKeyUtil.TOKEN + token, user, timeout);
        if (!b1) {
            log.error("TOKEN保存redis异常");
            throw new RuntimeException();
        }
        return user.getId();
    }
}

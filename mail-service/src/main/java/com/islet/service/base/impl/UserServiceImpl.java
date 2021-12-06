package com.islet.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.common.util.RedisKeyUtil;
import com.islet.common.web.ResultCode;
import com.islet.domain.dto.base.UserLoginDTO;
import com.islet.domain.dto.base.UserPageDTO;
import com.islet.domain.vo.PageVO;
import com.islet.domain.vo.bese.UserPageVO;
import com.islet.exception.BusinessException;
import com.islet.mapper.base.PermissionMapper;
import com.islet.mapper.base.UserMapper;
import com.islet.model.base.RoleUser;
import com.islet.model.base.User;
import com.islet.service.base.IRoleUserService;
import com.islet.service.base.IUserService;
import com.islet.util.MD5Util;
import com.islet.util.PageUtil;
import com.islet.util.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private IRoleUserService roleUserService;
    @Resource
    private PermissionMapper permissionMapper;
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
        Long userId = checkUserInfo(dto.getUsername(), dto.getPassword(), token);
        // 获取用户的权限,将用户权限保存在缓存中
        saveUserPermission(userId);
    }

    @Override
    public PageVO<UserPageVO> userPage(UserPageDTO dto) {
        Set<Long> userIds = new HashSet<>();
        if (dto.getRoleId() != null) {
            List<RoleUser> roleUsersList = roleUserService.list(
                    new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getRoleId, dto.getRoleId()));
            roleUsersList.forEach(roleUser -> {
                userIds.add(roleUser.getUserId());
            });
        }

        // 组装条件
        LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .lambda()
                .in(dto.getRoleId() != null, User::getId, userIds)
                .like(StringUtils.isNotEmpty(dto.getUsername()), User::getUsername, dto.getUsername())
                .like(StringUtils.isNotEmpty(dto.getName()), User::getName, dto.getName())
                .like(StringUtils.isNotEmpty(dto.getPhone()), User::getPhone, dto.getPhone())
                .ne(User::getUserId, 0L);

        return PageUtil.getPageVOByIPage(page -> (IPage<User>) super.page(page
                , queryWrapper)
                , dto.getCurrentPage()
                , dto.getPageSize()
                , UserPageVO.class);
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

    /**
     * 查询用户权限，并将用户权限中url保存到redis
     * @param userId
     * @throws Exception
     */
    private void saveUserPermission(Long userId) {
        // 获取到权限的url
        Set<String> permissionUrls = permissionMapper.getPermissionUrls(userId);
        StringBuffer urls = new StringBuffer();
        permissionUrls.forEach(url -> {
            urls.append(url).append(",");
        });

        // 保存用户权限到redis
        boolean isSuccess = redisService.set(RedisKeyUtil.USER_PERMISSION_URLS + userId, StringUtils.isNotBlank(urls) ? urls.substring(0, urls.length() - 1) : "");
        if (!isSuccess) {
            log.error("保存用户到redis异常");
            throw new RuntimeException();
        }
    }
}

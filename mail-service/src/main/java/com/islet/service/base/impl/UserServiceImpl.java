package com.islet.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.common.web.ResultCode;
import com.islet.domain.dto.base.UserLoginDTO;
import com.islet.exception.BusinessException;
import com.islet.mapper.base.PermissionMapper;
import com.islet.mapper.base.UserMapper;
import com.islet.model.base.User;
import com.islet.service.base.IPermissionService;
import com.islet.service.base.IUserService;
import com.islet.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private IPermissionService permissionService;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public User detail(Long id) {
        return super.getById(id);
    }


    @Override
    public void login(UserLoginDTO dto, String token) {
        // 验证用户名和密码
        Long userId = checkUserInfo(dto.getUsername(), dto.getPassword(), token);
        // 获取用户的权限,将用户权限保存在
        saveUserPermission(userId);
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
       /* // 这里代码主要就是防止有两个一样的用户同时登陆
        Object o = redisService.get(RedisKeyUtil.USER_ID + user.getId());
        if (o != null) {
            String t = o.toString();
            redisService.remove(RedisKeyUtil.TOKEN + t);
        }

        boolean b1 = redisService.set(RedisKeyUtil.TOKEN + token, user, timeout);
        if (!b1) {
            log.error("TOKEN保存REDIS异常");
            throw new ApiException(ResultEnum.UNKNOWN_ERR);
        }*/
        return user.getId();
    }

    /**
     * 查询用户权限，并将用户权限中url保存到redis
     * @param userId
     * @throws Exception
     */
    private void saveUserPermission(Long userId) {
       /* // 获取到权限的url
        Set<String> permissionUrls = permissionMapper.getPermissionUrls(userId);
        StringBuffer urls = new StringBuffer();
        permissionUrls.forEach(url -> {
            urls.append(url);
            urls.append(",");
        });
        // 保存用户权限到redis
        boolean isSuccess = redisService.set(RedisKeyUtil.USER_PERMISSION_URLS + userId, StringUtils.isNotBlank(urls) ? urls.substring(0, urls.length() - 1) : "");
        if (!isSuccess) {
            log.error("保存用户到REDIS异常");
            throw new ApiException(ResultEnum.UNKNOWN_ERR);
        }*/
    }
}

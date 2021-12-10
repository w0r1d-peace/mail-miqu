package com.islet.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.common.util.RedisKeyUtil;
import com.islet.common.web.ResultCode;
import com.islet.domain.dto.base.RoleIdNameDTO;
import com.islet.domain.dto.base.UserLoginDTO;
import com.islet.domain.dto.base.UserPageDTO;
import com.islet.domain.dto.base.UserSaveOrUpdateDTO;
import com.islet.domain.vo.PageVO;
import com.islet.domain.vo.bese.UserPageVO;
import com.islet.exception.BusinessException;
import com.islet.mapper.base.PermissionMapper;
import com.islet.mapper.base.UserMapper;
import com.islet.model.base.Role;
import com.islet.model.base.RoleUser;
import com.islet.model.base.User;
import com.islet.service.base.IRoleService;
import com.islet.service.base.IRoleUserService;
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

import static java.util.stream.Collectors.toSet;

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
    private IRoleService roleService;
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
        String rolenameString = "";
        Set<RoleIdNameDTO> roleList = dto.getRoleList();
        for (RoleIdNameDTO roleIdName : roleList) {
            rolenameString += roleIdName.getName() + ",";
        }
        user.setRolename(rolenameString.substring(0, rolenameString.length()-1));
        user.setUserId(dto.getUserId());
        user.setCreator(dto.getCreator());
        //保存用户记录
        this.save(user);

        Set<Long> roleIds = roleList.stream().map(RoleIdNameDTO::getId).collect(toSet());
        //批量更新ROLE
        batchUpdateRole(null, roleIds);
        //批量保存ROLEUSER
        batchSaveRoleUser(user.getId(), roleIds);
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
        Set<RoleIdNameDTO> roleList = dto.getRoleList();
        String rolenameString = "";
        for(RoleIdNameDTO roleIdName : roleList) {
            rolenameString += roleIdName.getName() + ",";
        }
        user.setRolename(rolenameString.substring(0, rolenameString.length()-1));
        BeanUtils.copyProperties(dto, user);

        Set<Long> roleIds = roleList.stream().map(RoleIdNameDTO::getId).collect(toSet());
        //批量更新ROLE
        batchUpdateRole(dto.getId(), roleIds);

        //保存用户记录
        this.updateById(user);
        //把所属角色删除，重新添加记录
        roleUserService.remove(new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getUserId, dto.getId()));

        //批量保存ROLEUSER
        batchSaveRoleUser(user.getId(), roleIds);
        return true;
    }

    @Override
    public Boolean deleteUser(List<Long> ids, Long userId, String createName) {
        //批量更新角色中用户数量
        batchUpdateRole(ids);
        //删除
        this.removeByIds(ids);
        //删除关联数据
        roleUserService.remove(new LambdaQueryWrapper<RoleUser>().in(RoleUser::getUserId, ids));
        return true;
    }

    /**
     * 批量更新ROLE
     * @param userId
     * @param roleIds
     * @throws Exception
     */
    public void batchUpdateRole(Long userId, Set<Long> roleIds) {
        List<Role> roleList = new ArrayList<>();
        if (userId == null) {
            roleIds.forEach(roleId -> {
                Role role = roleService.getById(roleId);
                role.setUserNumber(role.getUserNumber() + 1);
                roleList.add(role);
            });
        } else {
            Set<Long> plusSet;
            Set<Long> minusSet;
            Set<Long> userRoleIds = new HashSet<>();
            List<RoleUser> roleUserList = roleUserService.list(
                    new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getUserId, userId));
            roleUserList.forEach(roleUser -> {
                userRoleIds.add(roleUser.getRoleId());
            });
            //取差集 roleIds - userRoleIds
            plusSet = roleIds.stream().filter(item -> !userRoleIds.contains(item)).collect(toSet());
            //用户数量加1
            plusSet.forEach(roleId -> {
                Role role = roleService.getById(roleId);
                role.setUserNumber(role.getUserNumber() + 1);
                roleList.add(role);
            });
            //取差集 userRoleIds - roleIds
            minusSet = userRoleIds.stream().filter(item -> !roleIds.contains(item)).collect(toSet());
            //用户数量减1
            minusSet.forEach(roleId -> {
                Role role = roleService.getById(roleId);
                role.setUserNumber(role.getUserNumber() - 1);
                roleList.add(role);
            });
        }
        if (!roleList.isEmpty()) {
            //批量更新ROLE
            roleService.updateBatchById(roleList);
        }
    }

    /**
     * 批量保存ROLEUSER
     * @param userId
     * @param roleIds
     * @return
     * @throws Exception
     */
    private void batchSaveRoleUser(Long userId, Set<Long> roleIds) {
        List<RoleUser> roleUserList = new ArrayList<>();
        roleIds.forEach(roleId -> {
            //封装对象
            RoleUser roleUser = new RoleUser();
            roleUser.setUserId(userId);
            roleUser.setRoleId(roleId);
            roleUserList.add(roleUser);
        });
        roleUserService.saveBatch(roleUserList);
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

    /**
     * 批量更新ROLE
     * @param userIds
     * @throws Exception
     */
    public void batchUpdateRole(List<Long> userIds) {
        List<Role> roleList = new ArrayList<>();
        List<RoleUser> roleUserList = roleUserService.list(
                new LambdaQueryWrapper<RoleUser>().in(RoleUser::getUserId, userIds));
        List<Long> roleIdList = new ArrayList<>();

        roleUserList.forEach(roleUser -> {
            roleIdList.add(roleUser.getRoleId());
        });
        if (!roleIdList.isEmpty()) {
            //获取list中元素出现的个数
            Map<Long, Integer> map = frequencyOfListElements(roleIdList);
            map.forEach((k, v) -> {
                Role role = roleService.getById(k);
                role.setUserNumber(role.getUserNumber() - v);
                roleList.add(role);
            });
            //批量更新ROLE
            roleService.updateBatchById(roleList);
        }
    }

    /**
     * 统计list中每个元素出现的次数
     */
    public Map<Long,Integer> frequencyOfListElements(List<Long> items) {
        if (items == null || items.size() == 0) return null;
        Map<Long, Integer> map = new HashMap<>();
        for (Long temp : items) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }
        return map;
    }

}

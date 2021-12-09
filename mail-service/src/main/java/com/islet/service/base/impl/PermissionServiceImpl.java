package com.islet.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.common.web.ResultCode;
import com.islet.domain.dto.base.PermissionSaveOrUpdateDTO;
import com.islet.exception.BusinessException;
import com.islet.mapper.base.PermissionMapper;
import com.islet.model.base.Permission;
import com.islet.model.base.PermissionRole;
import com.islet.service.base.IPermissionRoleService;
import com.islet.service.base.IPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangJM.
 * @since 2021-09-18
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Resource
    private IPermissionRoleService permissionRoleService;

    @Override
    public Long savePermission(PermissionSaveOrUpdateDTO dto) {
        //查询数据库是否存在此权限名称
        boolean nameExist = existByName(dto.getName(), null);
        if(nameExist) {
            throw new BusinessException(ResultCode.PARAMETER_FAIL, "权限名称已存在");
        }
        //查询数据库是否存在此权限唯一标识
        boolean keyExist = existByKey(dto.getKey(), null);
        if(keyExist) {
            throw new BusinessException(ResultCode.PARAMETER_FAIL, "权限唯一标识已存在");
        }
        //查询数据库是否存在此权限URL
        boolean existCount = existByUrl(dto.getUrl(), null);
        if(existCount) {
            throw new BusinessException(ResultCode.PARAMETER_FAIL, "权限URL已存在");
        }

        Permission permission = new Permission();
        BeanUtils.copyProperties(dto, permission);
        permission.setCreateTime(new Date());
        permission.setModified(new Date());
        permission.setRemoved(false);
        this.save(permission);
        return permission.getId();
    }

    @Override
    public Boolean editPermission(PermissionSaveOrUpdateDTO dto) {
        //查询数据库是否存在此权限名称
        boolean nameExist = existByName(dto.getName(), dto.getId());
        if(nameExist) {
            throw new BusinessException(ResultCode.PARAMETER_FAIL, "权限名称已存在");
        }
        //查询数据库是否存在此权限唯一标识
        boolean keyExist = existByKey(dto.getKey(), dto.getId());
        if(keyExist) {
            throw new BusinessException(ResultCode.PARAMETER_FAIL, "权限唯一标识已存在");
        }
        //查询数据库是否存在此权限URL
        boolean existCount = existByUrl(dto.getUrl(), dto.getId());
        if(existCount) {
            throw new BusinessException(ResultCode.PARAMETER_FAIL, "权限URL已存在");
        }

        Permission permission = this.getById(dto.getId());
        BeanUtils.copyProperties(dto, permission);
        return this.updateById(permission);
    }

    @Override
    public Boolean deletePermission(List<Long> ids, Long userId, String createName) {
        int count = permissionRoleService.count(
                new LambdaQueryWrapper<PermissionRole>()
                        .in(PermissionRole::getPermissionId, ids));
        if (count > 0) {
            throw new BusinessException(ResultCode.FAIL_MSG, "存在角色关联权限，不可删除");
        }

        return super.removeByIds(ids);
    }

    /**
     * 判断权限url是否存在
     * @param url
     * @param id
     * @return
     */
    private boolean existByUrl(String url, Long id) {
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<Permission>()
                .eq(Permission::getUrl, url);

        if (id != null) {
            queryWrapper.ne(Permission::getId, id);
        }

        int count = this.count(queryWrapper);
        return count > 0 ? true : false;
    }

    /**
     * 判断权限唯一标识是否存在
     * @param key
     * @param id
     * @return
     */
    private boolean existByKey(String key, Long id) {
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<Permission>()
                .eq(Permission::getKey, key);

        if (id != null) {
            queryWrapper.ne(Permission::getId, id);
        }

        int count = this.count(queryWrapper);
        return count > 0 ? true : false;
    }

    /**
     * 判断权限名称是否存在
     * @param name
     * @param id
     * @return
     */
    private boolean existByName(String name, Long id) {
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<Permission>()
                .eq(Permission::getName, name);

        if (id != null) {
            queryWrapper.ne(Permission::getId, id);
        }

        int count = this.count(queryWrapper);
        return count > 0 ? true : false;
    }
}

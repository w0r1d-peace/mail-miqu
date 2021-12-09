package com.islet.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.islet.common.web.ResultCode;
import com.islet.domain.dto.base.RolePageDTO;
import com.islet.domain.dto.base.RoleSavaOrUpdateDTO;
import com.islet.domain.vo.PageVO;
import com.islet.domain.vo.bese.RoleFindAllVO;
import com.islet.domain.vo.bese.RolePageVO;
import com.islet.exception.BusinessException;
import com.islet.mapper.base.RoleMapper;
import com.islet.model.base.PermissionRole;
import com.islet.model.base.Role;
import com.islet.model.base.RoleUser;
import com.islet.service.base.IPermissionRoleService;
import com.islet.service.base.IRoleService;
import com.islet.service.base.IRoleUserService;
import com.islet.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private IPermissionRoleService permissionRoleService;
    @Resource
    private IRoleUserService roleUserService;

    @Override
    public PageVO<RolePageVO> rolePage(RolePageDTO dto) {
        LambdaQueryWrapper<Role> queryWrapper = new QueryWrapper<Role>()
                .lambda()
                .like(StringUtils.isNotEmpty(dto.getName()), Role::getName, dto.getName());

        return PageUtil.getPageVOByIPage(page -> (IPage<Role>) super.page(page
                , queryWrapper)
                , dto.getCurrentPage()
                , dto.getPageSize()
                , RolePageVO.class);
    }

    @Override
    public Long saveRole(RoleSavaOrUpdateDTO dto) {
        //判断角色名称是否已经存在
        boolean exist = existByName(dto.getName(), null, dto.getUserId());
        if (exist) {
            throw new BusinessException(ResultCode.PARAMETER_FAIL, "角色名称已经存在");
        }

        Role role = new Role();
        BeanUtils.copyProperties(dto, role);
        role.setCreateTime(new Date());
        role.setModified(new Date());
        role.setRemoved(false);
        role.setUserNumber(0);
        //保存用户记录
        this.save(role);
        //批量保存
        List<PermissionRole> permissionRoleList = getPermissionRoleList(role.getId(), dto.getPermissionIds());
        permissionRoleService.saveBatch(permissionRoleList);
        return role.getId();
    }

    @Override
    public Boolean updateRole(RoleSavaOrUpdateDTO dto) {
        //判断角色名称是否已经存在
        boolean exist = existByName(dto.getName(), dto.getId(), dto.getUserId());
        if (exist) {
            throw new BusinessException(ResultCode.PARAMETER_FAIL, "角色名称已经存在");
        }

        Role role = super.getById(dto.getId());
        BeanUtils.copyProperties(dto, role);

        //保存用户记录
        this.updateById(role);
        //把所属权限删除，重新添加记录
        permissionRoleService.remove(new LambdaQueryWrapper<PermissionRole>().eq(PermissionRole::getRoleId, dto.getId()));
        //批量保存
        List<PermissionRole> permissionRoleList = getPermissionRoleList(role.getId(), dto.getPermissionIds());
        permissionRoleService.saveBatch(permissionRoleList);

        return null;
    }

    @Override
    public Boolean deleteRole(List<Long> ids, Long userId, String createName) {
        int count = roleUserService.count(
                new LambdaQueryWrapper<RoleUser>()
                        .in(RoleUser::getRoleId, ids));
        if (count > 0) {
            throw new BusinessException(ResultCode.FAIL_MSG, "存在用户关联角色，不可删除");
        }

        //删除角色
        this.removeByIds(ids);
        //删除关联的权限
        permissionRoleService.remove(new LambdaQueryWrapper<PermissionRole>().in(PermissionRole::getRoleId, ids));
        return true;
    }

    @Override
    public List<RoleFindAllVO> findAll() {
        List<Role> roleList = super.list();
        List<RoleFindAllVO> roleFindAllVOList =  new ArrayList<>();
        roleList.forEach(role -> {
            roleFindAllVOList.add(RoleFindAllVO.builder().id(role.getId()).name(role.getName()).build());
        });

        return roleFindAllVOList;
    }

    /**
     * 判断角色名称是否已经存在
     * @param name
     * @param id
     * @param userId
     * @return
     */
    private boolean existByName(String name, Long id, Long userId) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<Role>()
                .eq(Role::getName, name)
                .eq(Role::getUserId, userId);

        if (id != null) {
            queryWrapper.ne(Role::getId, id);
        }

        int count = this.count(queryWrapper);
        return count > 0 ? true : false;
    }

    /**
     * 获取权限集合
     * @param roleId
     * @param permissionIds
     * @return
     */
    private List<PermissionRole> getPermissionRoleList(Long roleId, Set<Long> permissionIds) {
        List<PermissionRole> permissionRoleList = new ArrayList<>();
        permissionIds.forEach(permissionId -> {
            PermissionRole permissionRole = new PermissionRole();
            permissionRole.setRoleId(roleId);
            permissionRole.setPermissionId(permissionId);
            permissionRoleList.add(permissionRole);
        });
        return permissionRoleList;
    }
}

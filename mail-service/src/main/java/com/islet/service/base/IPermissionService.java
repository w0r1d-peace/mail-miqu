package com.islet.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.islet.domain.dto.base.PermissionSaveOrUpdateDTO;
import com.islet.model.base.Permission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangJM.
 * @since 2021-09-18
 */
public interface IPermissionService extends IService<Permission> {

    /**
     * 新增
     * @param dto
     * @return
     */
    Long savePermission(PermissionSaveOrUpdateDTO dto);

    /**
     *
     * @param dto
     * @return
     */
    Boolean editPermission(PermissionSaveOrUpdateDTO dto);

    /**
     * 删除
     * @param ids
     * @param userId
     * @param createName
     * @return
     */
    Boolean deletePermission(List<Long> ids, Long userId, String createName);
}

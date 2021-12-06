package com.islet.mapper.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.islet.model.base.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tangJM.
 * @since 2021-09-18
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 获取用户权限地址
     * @param userId
     * @return
     * @throws Exception
     */
    Set<String> getPermissionUrls(@Param("userId") Long userId);
}

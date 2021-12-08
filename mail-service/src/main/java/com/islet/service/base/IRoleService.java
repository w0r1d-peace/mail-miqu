package com.islet.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.islet.domain.dto.base.RolePageDTO;
import com.islet.domain.dto.base.RoleSavaOrUpdateDTO;
import com.islet.domain.vo.PageVO;
import com.islet.domain.vo.bese.RoleFindAllVO;
import com.islet.domain.vo.bese.RolePageVO;
import com.islet.model.base.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangJM.
 * @since 2021-09-18
 */
public interface IRoleService extends IService<Role> {

    /**
     * 角色列表（分页）
     * @param dto
     * @return
     */
    PageVO<RolePageVO> rolePage(RolePageDTO dto);

    /**
     * 新增
     * @param dto
     * @return
     */
    Long saveRole(RoleSavaOrUpdateDTO dto);

    /**
     * 编辑
     * @param dto
     * @return
     */
    Boolean updateRole(RoleSavaOrUpdateDTO dto);

    /**
     * 删除
     * @param ids
     * @param userId
     * @param createName
     * @return
     */
    Boolean deleteRole(List<Long> ids, Long userId, String createName);

    /**
     * 查询所有角色id和名称
     * @return
     */
    List<RoleFindAllVO> findAll();
}

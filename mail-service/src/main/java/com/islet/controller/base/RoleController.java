package com.islet.controller.base;

import com.islet.common.web.Result;
import com.islet.controller.AbstractController;
import com.islet.domain.dto.base.RolePageDTO;
import com.islet.domain.dto.base.RoleSavaOrUpdateDTO;
import com.islet.domain.vo.PageVO;
import com.islet.domain.vo.bese.RoleFindAllVO;
import com.islet.domain.vo.bese.RolePageVO;
import com.islet.model.base.Role;
import com.islet.service.base.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tangJM.
 * @since 2021-09-18
 */
@Controller
@RequestMapping("role")
public class RoleController extends AbstractController {

    @Resource
    private IRoleService roleService;

    /**
     * 列表
     * @return
     */
    @PostMapping("page")
    @ResponseBody
    public Result<PageVO<RolePageVO>> page(@Valid RolePageDTO dto) {
        PageVO<RolePageVO> page = roleService.rolePage(dto);
        return Result.success(page);
    }

    /**
     * 新增
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    public Result<Long> save(@RequestBody @Valid RoleSavaOrUpdateDTO dto) {
        return Result.success(roleService.saveRole(dto));
    }

    /**
     * 编辑
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public Result<Boolean> edit(@RequestBody @Valid RoleSavaOrUpdateDTO dto) {
        return Result.success(roleService.updateRole(dto));
    }

    /**
     * 删除
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public Result<Boolean> delete(@RequestBody @Valid List<Long> ids) {
        return Result.success(roleService.deleteRole(ids, super.getUserId(), super.getCreateName()));
    }

    /**
     * 查询所有的角色
     * @return
     */
    @PostMapping("findAll")
    @ResponseBody
    public Result<List<RoleFindAllVO>> findAll() {
        return Result.success(roleService.findAll());
    }
}

package com.islet.controller.base;
import com.islet.common.web.Result;
import com.islet.controller.AbstractController;
import com.islet.domain.dto.base.PermissionSaveOrUpdateDTO;
import com.islet.service.base.IPermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
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
@RequestMapping("permission")
public class PermissionController extends AbstractController {

    @Resource
    private IPermissionService permissionService;

    /**
     * 新增
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    public Result<Long> save(@RequestBody @Valid PermissionSaveOrUpdateDTO dto) {
        return Result.success(permissionService.savePermission(dto));
    }

    /**
     * 修改
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public Result<Boolean> edit(@Valid PermissionSaveOrUpdateDTO dto) {
        return Result.success(permissionService.editPermission(dto));
    }

    /**
     * 删除
     * @return
     */
    @PostMapping("/permission/delete")
    @ResponseBody
    public Result<Boolean> delete(@RequestBody @Valid List<Long> ids) {
        return Result.success(permissionService.deletePermission(ids, super.getUserId(), super.getCreateName()));
    }
}

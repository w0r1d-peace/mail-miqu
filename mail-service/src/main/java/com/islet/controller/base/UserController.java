package com.islet.controller.base;
import com.islet.common.web.Result;
import com.islet.controller.AbstractController;
import com.islet.domain.dto.base.UserPageDTO;
import com.islet.domain.dto.base.UserSaveOrUpdateDTO;
import com.islet.domain.vo.PageVO;
import com.islet.domain.vo.bese.UserPageVO;
import com.islet.service.base.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/user")
public class UserController extends AbstractController {

    @Resource
    private IUserService userService;

    /**
     * 列表
     * @return
     */
    @GetMapping("/page")
    @ResponseBody
    public Result<PageVO<UserPageVO>> page(@Valid UserPageDTO dto) {
        PageVO<UserPageVO> page = userService.userPage(dto);
        return Result.success(page);
    }

    /**
     * 新增
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public Result<Long> save(@RequestBody @Valid UserSaveOrUpdateDTO dto) {
        return Result.success(userService.saveUser(dto));
    }

    /**
     * 修改
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Result<Boolean> edit(@RequestBody @Valid UserSaveOrUpdateDTO dto) {
        return Result.success(userService.editUser(dto));
    }

    /**
     * 删除
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result<Boolean> delete(@Valid List<Long> ids) {
        return Result.success(userService.deleteUser(ids, super.getUserId(), super.getCreateName()));
    }
}

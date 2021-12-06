package com.islet.controller.base;
import com.islet.common.web.Result;
import com.islet.domain.dto.base.UserPageDTO;
import com.islet.domain.vo.PageVO;
import com.islet.domain.vo.bese.UserPageVO;
import com.islet.model.base.User;
import com.islet.service.base.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tangJM.
 * @since 2021-09-18
 */
@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 列表
     * @return
     */
    @PostMapping("/page")
    @ResponseBody
    public Result<PageVO<UserPageVO>> page(@Valid UserPageDTO dto) {
        PageVO<UserPageVO> page = userService.userPage(dto);
        return Result.success(page);
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public void detail(@RequestParam Long id) {
        User user = userService.detail(id);
        log.info("{}", user);
    }
}

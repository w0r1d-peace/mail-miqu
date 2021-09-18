package com.islet.controller.base;
import com.islet.model.base.User;
import com.islet.service.base.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

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

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public void detail(@RequestParam Long id) {
        User user = userService.detail(id);
        log.info("{}", user);
    }
}

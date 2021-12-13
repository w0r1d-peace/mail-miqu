package com.islet.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tangJM.
 * @date 2021/12/6
 * @description
 */
@Controller
@RequestMapping("page")
public class PageController {

    /**
     * 登录
     * @return
     */
    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("user")
    public String user() { return "base/user/list"; }

    @GetMapping("user/add")
    public String userAdd() { return "base/user/add"; }

    @GetMapping("user/edit")
    public String userEdit() { return "base/user/edit"; }
}

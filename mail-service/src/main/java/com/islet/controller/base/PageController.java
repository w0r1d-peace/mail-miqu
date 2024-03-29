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

    @GetMapping("role")
    public String role() { return "base/role/list"; }

    @GetMapping("role/add")
    public String roleAdd() { return "base/role/add"; }

    @GetMapping("role/edit")
    public String roleEdit() { return "base/role/edit"; }

    @GetMapping("permission")
    public String permission() { return "base/permission/list"; }

    @GetMapping("permission/add")
    public String permissionAdd() { return "base/permission/add"; }

    @GetMapping("permission/edit")
    public String permissionEdit() { return "base/permission/edit"; }

    @GetMapping("task")
    public String task() { return "mail/task/list"; }

    @GetMapping("task/add")
    public String taskAdd() { return "mail/task/add"; }
}

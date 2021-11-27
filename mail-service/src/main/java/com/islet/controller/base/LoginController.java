package com.islet.controller.base;

import com.islet.common.web.Result;
import com.islet.domain.dto.base.UserLoginDTO;
import com.islet.service.base.IUserService;
import com.islet.util.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author tangJM.
 * @date 2021/10/8
 * @description
 */
@Controller
@Validated
public class LoginController {

    @Resource
    private IUserService userService;

    @PostMapping("login")
    @ResponseBody
    public Result<Boolean> login(@RequestBody @Valid UserLoginDTO dto, HttpServletResponse response) {
        String token = UUIDUtil.uuid();
        userService.login(dto, token);
        response.setHeader("Authorization", token);
        response.setContentType("application/json;charset=UTF-8");
        return Result.success(true);
    }

    @GetMapping("login/page")
    public String loginPage() {
        return "pages-login";
    }

    @GetMapping("register/pages")
    public String pagesRegister() {
        return "pages-register";
    }
}

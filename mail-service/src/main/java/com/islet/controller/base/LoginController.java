package com.islet.controller.base;

import com.islet.common.web.Result;
import com.islet.domain.dto.base.UserLoginDTO;
import com.islet.service.base.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
        /*String token = UUIDUtil.uuid();
        userService.login(dto, token);
        response.setHeader("Authorization", token);
        response.setContentType("application/json;charset=UTF-8");*/
        return Result.success(true);
    }

    @GetMapping("verifycode/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response){
        /*response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        // 1.根据随机数生成图片
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        String captcha = vc.getText().toLowerCase();
        // 2.将图片存入redis中
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, vc);
        redisTemplate.opsForValue().set(RedisKeyUtil.CAPTCHA + captcha, null, 60, TimeUnit.SECONDS);

        // 3.将生成的图片写入到接口响应中
        ImageIO.write(image, "JPEG", response.getOutputStream());*/
    }

    @GetMapping("login/page")
    public String loginPage() {
        return "login";
    }
}

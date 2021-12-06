package com.islet.controller.base;

import com.islet.common.util.RedisKeyUtil;
import com.islet.common.web.Result;
import com.islet.domain.dto.base.UserLoginDTO;
import com.islet.service.base.IUserService;
import com.islet.util.UUIDUtil;
import com.islet.util.VerifyCode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 登录
     * @param dto
     * @param response
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public Result<Boolean> login(@RequestBody @Valid UserLoginDTO dto, HttpServletResponse response) {
        String token = UUIDUtil.uuid();
        userService.login(dto, token);
        response.setHeader("Authorization", token);
        response.setContentType("application/json;charset=UTF-8");
        return Result.success(true);
    }

    /**
     * 验证码图片
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("captcha/image")
    public void captchaImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        // 1.根据随机数生成图片
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        String captcha = vc.getText().toLowerCase();
        // 2.将图片存入redis中
        /*        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, vc);*/
        redisTemplate.opsForValue().set(RedisKeyUtil.CAPTCHA + captcha, null, 60, TimeUnit.SECONDS);

        // 3.将生成的图片写入到接口响应中
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }
}

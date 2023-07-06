package com.islet.aspect;

import com.islet.common.util.RedisKeyUtil;
import com.islet.common.web.ResultCode;
import com.islet.domain.dto.BaseDTO;
import com.islet.exception.BusinessException;
import com.islet.model.base.User;
import com.islet.util.ThreadUtil;
import com.islet.util.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;


/**
 * @author tangJM.
 * Token和权限校验
 */
@Aspect
@Slf4j
@Component
public class PermissionAspect {

    @Value("${server.servlet.session.timeout}")
    private Integer timeout;

    @Resource
    private RedisTemplate redisTemplate;


    @Pointcut(value = "execution(* com.islet.controller.*.*Controller.*(..))" +
            "&&!execution(* com.islet.controller.base.LoginController.*(..))" +
            "&&!execution(* com.islet.controller.base.PageController.*(..))"
    )
    public void pointCut() {
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        //校验身份
        HttpServletRequest request = UserContext.getRequest();
        //获取到token
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authorization)) {
            // 获取用户信息
            Object o = redisTemplate.opsForValue().get(RedisKeyUtil.TOKEN + authorization);
            if (o == null) {
                log.error("获取不到REDIS中Authorization{}的KEY", authorization);
                throw new BusinessException(ResultCode.CAN_NOT_LOGIN);
            }

            User user = null;
            // 避免在强转的时候报错
            try {
                user = (User) o;
                ThreadLocal<User> currentUser = ThreadUtil.currentUser;
                currentUser.set(user);
            } catch (Exception e) {
                throw new BusinessException(ResultCode.FAIL);
            }

            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                //是否需要携带身份信息
                if (args[i] instanceof BaseDTO) {
                    BaseDTO dto = (BaseDTO) args[i];
                    dto.setUserId(user.getId());
                    dto.setCreator(user.getName());
                }
            }

            redisTemplate.expire(RedisKeyUtil.TOKEN + authorization, timeout, TimeUnit.SECONDS);

        } else {
            log.error("获取不到header中Authorization参数");
            throw new BusinessException(ResultCode.FAIL);
        }
    }
}

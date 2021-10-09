package com.islet.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author liqigui
 * @date 2020-09-15 9:53
 */
@Slf4j
@RestControllerAdvice()
@Component("globalExceptionHandler")
public class GlobalExceptionHandler {

/*    @ExceptionHandler
    @Override
    public Result<?> handle(ConstraintViolationException e) {
        log.error("错误：" + e.getMessage(), e);
        return Result.of(ResultCode.FAIL_MSG, e.getMessage().split(": ")[1], null);
    }


    @ExceptionHandler(BusinessException.class)
    @Override
    public Result<?> handle(BusinessException e) {
        log.error("错误：" + e.getMessage(), e);
        BusinessException exception = (BusinessException) e;
        return Result.of(exception.getResultCode(), Optional.ofNullable(exception.getErrorData()).orElse(Arrays.toString(Optional.ofNullable(e.getArgs()).orElse(new String[]{
                "系統异常"
        }))).toString(), null);
    }*/

}

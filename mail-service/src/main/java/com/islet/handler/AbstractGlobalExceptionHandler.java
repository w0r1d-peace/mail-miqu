package com.islet.handler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import java.util.concurrent.CompletionException;
import javax.validation.ConstraintViolationException;

import com.islet.common.web.Result;
import com.islet.common.web.ResultCode;
import com.islet.config.BaseResultConfig;
import com.islet.config.ResultConfig;
import com.islet.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author tangJM.
 * @date 2021/10/14
 * @description
 */
public abstract class AbstractGlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(AbstractGlobalExceptionHandler.class);

    public AbstractGlobalExceptionHandler() {
        log.info("AbstractGlobalExceptionHandler");
    }

    @ExceptionHandler
    public Result<?> handle(MissingServletRequestParameterException e) {
        log.error("==> MissingServletRequestParameterException", e);
        return this.build(ResultCode.PARAMETER_FAIL, this.getMessage(String.valueOf(ResultCode.PARAMETER_FAIL), e.getParameterName()));
    }

    @ExceptionHandler
    public Result<?> handle(MethodArgumentTypeMismatchException e) {
        log.error("==> MethodArgumentTypeMismatchException", e);
        return this.build(ResultCode.PARAMETER_FAIL, this.getMessage(String.valueOf(ResultCode.PARAMETER_FAIL), e.getName()));
    }

    @ExceptionHandler
    public Result<?> handle(BindException e) {
        log.error("==> BindException", e);
        return this.handle(e.getBindingResult());
    }

    @ExceptionHandler
    public Result<?> handle(ConstraintViolationException e) {
        log.error("错误：" + e.getMessage().split(": ")[1], e);
        return this.build(ResultCode.FAIL_MSG, "操作失败，请联系客服");
    }

    @ExceptionHandler
    public Result<?> handle(MethodArgumentNotValidException e) {
        log.error("==> MethodArgumentNotValidException", e);
        return this.handle(e.getBindingResult());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<?> handle(HttpMessageNotReadableException e) {
        log.error("==> HttpMessageNotReadableException", e);
        Throwable cause = e.getCause();
        if (cause instanceof JsonMappingException) {
            String fieldName = ((Reference)((JsonMappingException)cause).getPath().get(0)).getFieldName();
            return this.build(ResultCode.PARAMETER_FAIL, this.getMessage(String.valueOf(ResultCode.PARAMETER_FAIL), fieldName));
        } else if (cause instanceof JsonParseException) {
            return this.build(ResultCode.PARAMETER_FAIL, "JSON格式错误");
        } else {
            log.error("错误：" + e.getMessage(), e);
            return this.build(ResultCode.FAIL, "操作失败，请联系客服");
        }
    }

    @ExceptionHandler
    public Result<?> handle(BusinessException e) {
        log.error("==> BusinessException", e);
        return this.build(e.getResultCode(), this.getMessage(String.valueOf(e.getResultCode()), e.getArgs()), e.getErrorData());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<?> handle(NoHandlerFoundException e) {
        log.error("错误：请求 URL 不存在", e);
        return this.build(ResultCode.FAIL_MSG, "错误的操作请求");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result<?> handle(HttpRequestMethodNotSupportedException e) {
        log.error("错误：请求方法错误", e);
        return this.build(ResultCode.FAIL_MSG, "错误的操作请求");
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public Result<?> handle(IllegalArgumentException e) {
        log.error("==> 参数非法异常", e);
        return this.build(ResultCode.FAIL_MSG, e.getMessage());
    }

    @ExceptionHandler({CompletionException.class})
    public Result<?> handle(CompletionException e) {
        Throwable cause = e.getCause();
        String message = "操作失败，请联系客服";
        if (cause instanceof BusinessException) {
            int resultCode = ((BusinessException)cause).getResultCode();
            Object[] args = ((BusinessException)cause).getArgs();
            message = this.getMessage(String.valueOf(resultCode), args);
        }

        log.error("并发CompletionException：", e);
        return this.build(ResultCode.FAIL_MSG, message);
    }

    @ExceptionHandler
    public Result<?> handle(Exception e) {
        log.error("错误：系统BUG", e);
        return this.build(ResultCode.FAIL, "操作失败，请联系客服");
    }

    private Result<?> handle(BindingResult bindingResult) {
        FieldError fieldError = (FieldError)bindingResult.getFieldErrors().get(0);
        return "typeMismatch".equals(fieldError.getCode()) ? this.build(ResultCode.PARAMETER_FAIL, fieldError.getField()) : this.build(ResultCode.FAIL_MSG, fieldError.getDefaultMessage());
    }

    private Result<?> build(int resultCode, String resultMsg) {
        return this.build(resultCode, resultMsg, (Object)null);
    }

    private Result<?> build(int resultCode, String resultMsg, Object errorData) {
        return Result.of(resultCode, resultMsg, errorData);
    }

    private String getMessage(String key, Object... args) {
        String message = BaseResultConfig.getInstance().getMessage(key, args);
        return !StringUtils.isEmpty(message) ? message : ResultConfig.getInstance().getMessage(key, args);
    }
}

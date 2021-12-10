package com.islet.common.web;

public class Result<T> {
    public static final Result SUCCESS;
    private int code;
    private String msg;
    private T data;

    /** @deprecated */
    @Deprecated
    public Result() {
    }

    /** @deprecated */
    @Deprecated
    public Result(T data) {
        this(SUCCESS.code, SUCCESS.msg, data);
    }

    /** @deprecated */
    @Deprecated
    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "Result{code=" + this.code + ", msg='" + this.msg + '\'' + ", data=" + this.data + '}';
    }

    public static <T> Result<T> of(int code, String msg) {
        return of(code, msg, null);
    }

    public static <T> Result<T> of(int resultCode, String msg, T data) {
        return new Result(resultCode, msg, data);
    }

    public static <T> Result<T> success(T data) {
        return of(SUCCESS.code, SUCCESS.msg, data);
    }

    public static <T> Result<T> failed(String msg) {
        return of(ResultCode.FAIL_MSG, msg);
    }

    static {
        SUCCESS = of(ResultCode.SUCCESS, "操作成功");
    }
}

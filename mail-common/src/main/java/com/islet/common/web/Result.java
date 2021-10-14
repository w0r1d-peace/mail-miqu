package com.islet.common.web;

public class Result<T> {
    public static final Result SUCCESS;
    private int resultCode;
    private String resultMsg;
    private T resultData;

    /** @deprecated */
    @Deprecated
    public Result() {
    }

    /** @deprecated */
    @Deprecated
    public Result(T resultData) {
        this(SUCCESS.resultCode, SUCCESS.resultMsg, resultData);
    }

    /** @deprecated */
    @Deprecated
    public Result(int resultCode, String resultMsg, T resultData) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getResultData() {
        return this.resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }

    public String toString() {
        return "Result{resultCode=" + this.resultCode + ", resultMsg='" + this.resultMsg + '\'' + ", resultData=" + this.resultData + '}';
    }

    public static <T> Result<T> of(int resultCode, String resultMsg) {
        return of(resultCode, resultMsg, null);
    }

    public static <T> Result<T> of(int resultCode, String resultMsg, T resultData) {
        return new Result(resultCode, resultMsg, resultData);
    }

    public static <T> Result<T> success(T resultData) {
        return of(SUCCESS.resultCode, SUCCESS.resultMsg, resultData);
    }

    public static <T> Result<T> failed(String resultMsg) {
        return of(ResultCode.FAIL_MSG, resultMsg);
    }

    static {
        SUCCESS = of(ResultCode.SUCCESS, "操作成功");
    }
}

package com.islet.common.web;

/**
 * @author tangJM.
 * @date 2021/10/9
 * @description
 */
public class ResultCode {
    public static final Integer SUCCESS = 1000;
    public static final Integer FAIL = 1001;
    public static final Integer PARAMETER_FAIL = 1002;
    public static final Integer NOT_LOGIN = 1003;
    public static final Integer NO_ACCESS = 1004;
    public static final Integer GET_ACCESS_FAIL = 1005;
    public static final Integer FAIL_REASON = 1006;
    public static final Integer FAIL_MSG = 1007;
    public static final Integer CAN_NOT_LOGIN = 1008;
    public static final Integer SUPPLIER_DISABLE_CUSTOMER = 1009;
    public static final Integer NOT_REPEAT_REQUEST = 1010;
    public static final Integer ACTION_PROCESSING = 1101;

    public ResultCode() {
    }
}

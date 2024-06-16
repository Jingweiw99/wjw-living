package com.wjw.constant;

/**
 * 统一状态码
 */
public enum WjwlivingCodeEnum {
    UNKNOWN_EXCEPTION(40000, "系统未知异常!"),
    INVALID_EXCEPTION(40001, "参数校验异常!");
    private int code;
    private String msg;

    WjwlivingCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
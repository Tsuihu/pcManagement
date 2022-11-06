package com.higher.pcmanagement.util;

/**
 * 封装返回数据状态码
 */
public enum ResultCodeEnum {
    SUCCESS(200),ERROR(500),LOGIN_ERROR(101);

    private int code;
    ResultCodeEnum(int i) {
        this.code = i;
    }
    public int getCode() {
        return code;
    }
}

/*
 * Copyright (c) 2018-2999 广州市蓝海创新科技有限公司 All rights reserved.
 *
 * https://www.mall4j.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */
package com.hz.blog.response;

public enum ResponseEnum {

    /**
     * 用于直接显示提示系统的成功，内容由输入内容决定
     */
    SHOW_SUCCESS(100000, "success"),

    SHOW_FAIL(100001, ""),

    /**
     * 用于直接显示提示用户的错误，内容由输入内容决定
     */


//    SHOW_SUCCESS("100000", ""),

    /**
     * 未授权
     */
    UNAUTHORIZED(100003, "Unauthorized"),

    /**
     * 服务器出了点小差
     */
    EXCEPTION(100005, "服务器出了点小差"),
    /**
     * 方法参数没有校验，内容由输入内容决定
     */
    METHOD_ARGUMENT_NOT_VALID(100014, "方法参数没有校验"),

    SERVER_ERROR(999999, "服务器内部错误");

    private final Integer code;

    private final String msg;

    public Integer value() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseEnum{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + "} " + super.toString();
    }

}

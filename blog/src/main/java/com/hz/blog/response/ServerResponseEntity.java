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

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Objects;

@Slf4j
public class ServerResponseEntity<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 版本
     */
    private String version;

    /**
     * 时间
     */
    private Long timestamp;

    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public ServerResponseEntity setData(T data) {
        this.data = data;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return Objects.equals(ResponseEnum.SHOW_SUCCESS.value(), this.code);
    }
    public boolean isFail() {
        return !Objects.equals(ResponseEnum.SHOW_SUCCESS.value(), this.code);
    }

    public ServerResponseEntity() {
        // 版本号
        this.version = "v230424";
    }

    public static <T> ServerResponseEntity<T> success(T data) {
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setData(data);
        serverResponseEntity.setCode(ResponseEnum.SHOW_SUCCESS.value());
        serverResponseEntity.setMessage(ResponseEnum.SHOW_SUCCESS.getMsg());
        return serverResponseEntity;
    }

    public static <T> ServerResponseEntity<T> success() {
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setCode(ResponseEnum.SHOW_SUCCESS.value());
        serverResponseEntity.setMessage(ResponseEnum.SHOW_SUCCESS.getMsg());
        return serverResponseEntity;
    }

    public static <T> ServerResponseEntity<T> success(Integer code, T data) {
        return success(code,ResponseEnum.SHOW_SUCCESS.getMsg(), data);
    }

    public static <T> ServerResponseEntity<T> success(String message, T data) {
        return success(ResponseEnum.SHOW_SUCCESS.value(),message, data);
    }

    public static <T> ServerResponseEntity<T> success(String message) {
        return success(ResponseEnum.SHOW_SUCCESS.value(),message, null);
    }

    public static <T> ServerResponseEntity<T> success(Integer code,String message, T data) {
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setCode(code);
        serverResponseEntity.setData(data);
        serverResponseEntity.setMessage(message/*ResponseEnum.SHOW_SUCCESS.getMsg()*/);
        return serverResponseEntity;
    }

    /**
     * 前端显示失败消息
     * @param msg 失败消息
     * @return
     */
    public static <T> ServerResponseEntity<T> showFailMsg(String msg) {
        log.error(msg);
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setMessage(msg);
        serverResponseEntity.setCode(ResponseEnum.SHOW_FAIL.value());
        return serverResponseEntity;
    }

    public static <T> ServerResponseEntity<T> fail(ResponseEnum responseEnum) {
        log.error(responseEnum.toString());
//        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
//        serverResponseEntity.setMessage(responseEnum.getMsg());
//        serverResponseEntity.setCode(responseEnum.value());
//        return serverResponseEntity;
        return fail(responseEnum.value(), responseEnum.getMsg());

    }

    public static <T> ServerResponseEntity<T> fail(T data) {
//        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
//        serverResponseEntity.setMessage(responseEnum.getMsg());
//        serverResponseEntity.setCode(responseEnum.value());
//        return serverResponseEntity;
        return fail(ResponseEnum.SERVER_ERROR.value(), ResponseEnum.SERVER_ERROR.getMsg(),data);

    }

    public static <T> ServerResponseEntity<T> fail(ResponseEnum responseEnum, T data) {
        log.error(responseEnum.toString());
//        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
//        serverResponseEntity.setMessage(responseEnum.getMsg());
//        serverResponseEntity.setCode(responseEnum.value());
//        serverResponseEntity.setData(data);
//        return serverResponseEntity;
        return fail(responseEnum.value(), responseEnum.getMsg(),data);
    }

    public static <T> ServerResponseEntity<T> fail(Integer code, String msg, T data) {
        log.error(msg);
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setMessage(msg);
        serverResponseEntity.setCode(code);
        serverResponseEntity.setData(data);
        return serverResponseEntity;
    }

    public static <T> ServerResponseEntity<T> fail(String msg, T data) {
        log.error(msg);
//        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
//        serverResponseEntity.setMessage(msg);
//        serverResponseEntity.setCode(ResponseEnum.SERVER_ERROR.value());
//        serverResponseEntity.setData(data);
//        return serverResponseEntity;
        return fail(ResponseEnum.SERVER_ERROR.value(), msg,data);

    }

    public static <T> ServerResponseEntity<T> fail(Integer code, String msg) {
        return fail(code, msg, null);
    }

    public static <T> ServerResponseEntity<T> fail(String msg) {
        return fail(ResponseEnum.SERVER_ERROR.value(), msg, null);
    }

    public static <T> ServerResponseEntity<T> fail(Integer code, T data) {
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setCode(code);
        serverResponseEntity.setData(data);
        return serverResponseEntity;
    }

    @Override
    public String toString() {
        return "ServerResponseEntity{" +
                "code='" + code + '\'' +
                ", msg='" + message + '\'' +
                ", data=" + data +
                ", version='" + version + '\'' +
                ", timestamp=" + timestamp +
                ", sign='" + sign + '\'' +
                '}';
    }
}

/*
 * Copyright (c) 2018-2999 广州市蓝海创新科技有限公司 All rights reserved.
 *
 * https://www.mall4j.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.hz.blog.exception;

import com.hz.blog.response.ResponseEnum;
import com.hz.blog.response.ServerResponseEntity;
import lombok.Getter;

@Getter
public class BlogBindException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = -4137688758944857209L;

	/**
	 * http状态码
	 */
	private Integer code;

	private Object object;

	private ServerResponseEntity<?> serverResponseEntity;

	public BlogBindException(ResponseEnum responseEnum) {
		super(responseEnum.getMsg());
		this.code = responseEnum.value();
	}
	/**
	 * @param responseEnum
	 */
	public BlogBindException(ResponseEnum responseEnum, String msg) {
		super(msg);
		this.code = responseEnum.value();
	}

	public BlogBindException(ServerResponseEntity<?> serverResponseEntity) {
		this.serverResponseEntity = serverResponseEntity;
	}


	public BlogBindException(String msg) {
		super(msg);
		this.code = ResponseEnum.SHOW_FAIL.value();
	}

	public BlogBindException(Integer code,String msg) {
		super(msg);
		this.code = code;
	}

	public BlogBindException(String msg, Object object) {
		super(msg);
		this.code = ResponseEnum.SHOW_FAIL.value();
		this.object = object;
	}

}

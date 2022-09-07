package com.hz.blog.exception;


import com.alibaba.fastjson.JSONObject;
import com.hz.blog.entity.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 异常处理逻辑
 */
@RestControllerAdvice
@Slf4j
public class ExceptionController {


    //@ExceptionHandler
//    public JSONObject handleException(Exception e){
//        JSONObject jsonObject = new JSONObject(true);
//        jsonObject.put("code","500");
//        jsonObject.put("msg","发生了未知的错误");
//        jsonObject.put("message",e.getMessage());
//        return jsonObject;
//    }

    @ExceptionHandler
    public JSONObject MissingServletRequestParameterException(MissingServletRequestParameterException e){
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("code","999998");
        jsonObject.put("msg","miss parameter");
        jsonObject.put("message","参数缺失");
        //e.printStackTrace();
        return jsonObject;
    }

    @ExceptionHandler(AuthenticationException.class)
    public JSONObject authenticationExceptionException(AuthenticationException e){
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("code","999999");
        jsonObject.put("msg","参数缺失,类型错误");
        jsonObject.put("data",e.getMessage());
        return jsonObject;
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public JSONObject httpMediaTypeNotSupportedException(Exception e){
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("code","999999");
        jsonObject.put("msg","参数缺失,类型错误");
        jsonObject.put("data","[]");
        return jsonObject;
    }


    @ExceptionHandler(ShiroException.class)
    public JSONObject shiroHandleException(Exception e){
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("code","999999");
        jsonObject.put("msg","认证失败");
        jsonObject.put("message",e.getMessage());
        return jsonObject;
    }

    @ExceptionHandler(UnauthorizedException.class)
    public JSONObject shiroHandleException(UnauthorizedException e){
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("code","999999");
        jsonObject.put("msg","认证失败");
        jsonObject.put("message","您没有权限访问当前接口");
        return jsonObject;
    }

    @ExceptionHandler(MultipartException.class)
    public JSONObject handleException(MultipartException e){
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("code","999999");
        jsonObject.put("msg","参数缺失,类型错误");
        jsonObject.put("data","[]");
        return jsonObject;
    }

    //@ExceptionHandler(HttpMessageNotReadableException.class)
    public JSONObject handleException(HttpMessageNotReadableException e){
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("code","999999");
        jsonObject.put("msg","格式错误,请检查格式是否完整或参数是否填写完成并重新输入");
        jsonObject.put("data","[]");
        return jsonObject;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JSONObject handleException(MethodArgumentNotValidException e){
        log.info("异常: {}",e);
        log.info("异常详情: {}", Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        List<String> list = e.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        log.info("异常集合: {}",list);
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("code","999999");
        jsonObject.put("msg","参数缺失,类型错误");
        jsonObject.put("data",list);
        return jsonObject;
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RRException.class)
    public R handleRRException(RRException e){
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());

        return r;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(), e);
        return R.error("数据库中已存在该记录");
    }

	/*@ExceptionHandler(AuthorizationException.class)
	public R handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return R.error("没有权限，请联系管理员授权");
	}*/



    @ExceptionHandler(ParamRRException.class)
    public JSONObject handleException(ParamRRException e){
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("code","999999");
        jsonObject.put("msg","格式错误,参数不能为空");
        jsonObject.put("data",e.getMsg());
        return jsonObject;
    }

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e){
        log.error(e.getMessage(), e);
        return R.error();
    }

}

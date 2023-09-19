package com.hz.blog.exception;


import com.alibaba.fastjson.JSONObject;
import com.hz.blog.entity.R;
import com.hz.blog.response.ServerResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler
    public ResponseEntity<ServerResponseEntity<?>> MissingServletRequestParameterException(MissingServletRequestParameterException e){
//        JSONObject jsonObject = new JSONObject(true);
//        jsonObject.put("code","999998");
//        jsonObject.put("msg","miss parameter");
//        jsonObject.put("message","参数缺失");
//        return jsonObject;
        return ResponseEntity.status(HttpStatus.OK).body(ServerResponseEntity.fail(999999,"miss parameter","参数缺失"));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ServerResponseEntity<?>> authenticationExceptionException(AuthenticationException e){
//        JSONObject jsonObject = new JSONObject(true);
//        jsonObject.put("code","999999");
//        jsonObject.put("msg","参数缺失,类型错误");
//        jsonObject.put("data",e.getMessage());
//        return jsonObject;
        return ResponseEntity.status(HttpStatus.OK).body(ServerResponseEntity.fail(999999,"参数缺失,类型错误",e.getMessage()));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ServerResponseEntity<?>> httpMediaTypeNotSupportedException(Exception e){
//        JSONObject jsonObject = new JSONObject(true);
//        jsonObject.put("code","999999");
//        jsonObject.put("msg","参数缺失,类型错误");
//        jsonObject.put("data","[]");
//        return jsonObject;
        return ResponseEntity.status(HttpStatus.OK).body(ServerResponseEntity.fail(999999,"参数缺失,类型错误"));

    }


    @ExceptionHandler(ShiroException.class)
    public ResponseEntity<ServerResponseEntity<?>> shiroHandleException(Exception e){
//        JSONObject jsonObject = new JSONObject(true);
//        jsonObject.put("code","999999");
//        jsonObject.put("msg","认证失败");
//        jsonObject.put("message",e.getMessage());
//        return jsonObject;
        return ResponseEntity.status(HttpStatus.OK).body(ServerResponseEntity.fail(999999,"认证失败",e.getMessage()));

    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ServerResponseEntity<?>> shiroHandleException(UnauthorizedException e){
//        JSONObject jsonObject = new JSONObject(true);
//        jsonObject.put("code","999999");
//        jsonObject.put("msg","认证失败");
//        jsonObject.put("message","您没有权限访问当前接口");
//        return jsonObject;
        return ResponseEntity.status(HttpStatus.OK).body(ServerResponseEntity.fail(999999,"认证失败","您没有权限访问当前接口"));

    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ServerResponseEntity<?>> handleException(MultipartException e){
//        JSONObject jsonObject = new JSONObject(true);
//        jsonObject.put("code","999999");
//        jsonObject.put("msg","参数缺失,类型错误");
//        jsonObject.put("data","[]");
//        return jsonObject;
        return ResponseEntity.status(HttpStatus.OK).body(ServerResponseEntity.fail(999999,"参数缺失,类型错误"));

    }

    //@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ServerResponseEntity<?>> handleException(HttpMessageNotReadableException e){
//        JSONObject jsonObject = new JSONObject(true);
//        jsonObject.put("code","999999");
//        jsonObject.put("msg","格式错误,请检查格式是否完整或参数是否填写完成并重新输入");
//        jsonObject.put("data","[]");
//        return jsonObject;
        return ResponseEntity.status(HttpStatus.OK).body(ServerResponseEntity.fail(999999,"格式错误,请检查格式是否完整或参数是否填写完成并重新输入"));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServerResponseEntity<?>> handleException(MethodArgumentNotValidException e){
        List<String> list = e.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
//        JSONObject jsonObject = new JSONObject(true);
//        jsonObject.put("code","999999");
//        jsonObject.put("msg","参数缺失,类型错误");
//        jsonObject.put("data",list);
//        return jsonObject;
        return ResponseEntity.status(HttpStatus.OK).body(ServerResponseEntity.fail(999999,"参数缺失,类型错误",list));

    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RRException.class)
    public ResponseEntity<ServerResponseEntity<?>> handleRRException(RRException e){
//        R r = new R();
//        r.put("code", e.getCode());
//        r.put("msg", e.getMessage());
//        return r;
        return ResponseEntity.status(HttpStatus.OK).body(ServerResponseEntity.fail(999999,"",e.getMessage()));

    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ServerResponseEntity<?>> handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.OK).body(ServerResponseEntity.fail(999999,"数据库中已存在该记录",e.getMessage()));
//        return R.error();
    }

	/*@ExceptionHandler(AuthorizationException.class)
	public R handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return R.error("没有权限，请联系管理员授权");
	}*/



    @ExceptionHandler(ParamRRException.class)
    public ResponseEntity<ServerResponseEntity<?>> handleException(ParamRRException e){
//        JSONObject jsonObject = new JSONObject(true);
//        jsonObject.put("code","999999");
//        jsonObject.put("msg","格式错误,参数不能为空");
//        jsonObject.put("data",e.getMsg());
//        return jsonObject;
        return ResponseEntity.status(HttpStatus.OK).body(ServerResponseEntity.fail(999999,"格式错误,参数不能为空",e.getMessage()));
    }

    @ExceptionHandler(BlogBindException.class)
    public ResponseEntity<ServerResponseEntity<?>> handleException(BlogBindException e){
        log.error("mall4jExceptionHandler", e);

        ServerResponseEntity<?> serverResponseEntity = e.getServerResponseEntity();
        if (serverResponseEntity!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(serverResponseEntity);
        }
        // 失败返回消息 状态码固定为直接显示消息的状态码
        return ResponseEntity.status(HttpStatus.OK).body(ServerResponseEntity.fail(e.getCode(),e.getMessage()));
//        return ResponseEntity.status(HttpStatus.OK).body(ServerResponseEntity.fail("999999","格式错误,参数不能为空",e.getMessage()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServerResponseEntity<?>> handleException(Exception e){
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.OK).body(ServerResponseEntity.fail(999999,"未知错误",e.getMessage()));

//        return R.error();
    }

}

package com.hz.blog.aspects;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hz.blog.annotation.ParamCheck;
import com.hz.blog.entity.PageResult;
import com.hz.blog.exception.ParamRRException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.ServletRequestBindingException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

//声明当前类是配置类
@Configuration
//标注当前类是Aop切面类
@Aspect
@Slf4j
//开启Aop增强
//@EnableAspectJAutoProxy
public class ParamCheckAspect {

    /**
     * 定义切入点
     */
    @Pointcut("@annotation(com.hz.blog.annotation.ParamCheck)")
    public void annotation() {
    }

/*
    @Pointcut("execution( * * (..,@com.hz.blog.annotation.ParamCheck (*),..))")
     public void pointcut() {
         // do nothing
     }
*/

    /**
     * 环绕增强
     */
    @Around("annotation()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        MethodSignature signature = ((MethodSignature) point.getSignature());
        //得到拦截的方法
        Method method = signature.getMethod();
        //获取方法参数注解，返回二维数组是因为某些参数可能存在多个注解
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (parameterAnnotations.length == 0) {
            return point.proceed();
        }
        //获取方法参数名
        String[] paramNames = signature.getParameterNames();
        //获取参数值
        Object[] paranValues = point.getArgs();
        //获取方法参数类型
    //       Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (int j = 0; j < parameterAnnotations[i].length; j++) {
                //如果该参数前面的注解是CheckParamNull的实例，并且notNull()=true,则进行非空校验
                if (parameterAnnotations[i][j] != null && parameterAnnotations[i][j] instanceof ParamCheck) {
                    paramIsNull(paramNames[i], paranValues[i], ((ParamCheck) parameterAnnotations[i][j]));
                    break;
                }
            }
        }
        return point.proceed();
    }

     /**
       * 参数非空校验，如果参数为空，则抛出ServletRequestBindingException异常
       * @param paramName
       * @param value
       * @param checkParamNull
       */
     private void paramIsNull(String paramName, Object value, ParamCheck checkParamNull) {
         if (checkParamNull.notNull() && value == null) {
             System.out.println("============present");
             throw new ParamRRException("Required parameter '"+paramName+"' is not present");
         }else if(checkParamNull.notBlank() && StringUtils.isBlank(value.toString().trim())){
             System.out.println("============blank");
             throw new ParamRRException("Required String parameter '"+paramName+"' is must not blank");
         }

     }
}

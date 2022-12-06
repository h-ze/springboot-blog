package com.hz.blog.aspects;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.hz.blog.annotation.LogOperator;
import com.hz.blog.entity.Config;
import com.hz.blog.entity.LogEntity;
import com.hz.blog.entity.SysLogEntity;
import com.hz.blog.service.LogService;
import com.hz.blog.utils.HttpContextUtils;
import com.hz.blog.utils.IPUtils;
import com.hz.blog.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private LogService logService;

    @Autowired
    private Config config;

    @Pointcut("@annotation(com.hz.blog.annotation.LogOperator)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }


    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //SysLogEntity sysLog = new SysLogEntity();

        LogOperator syslog = method.getAnnotation(LogOperator.class);
        if(syslog != null){
            //注解上的描述



            //logEntity.setOperator(syslog.value());

            //请求的方法名
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = signature.getName();
            //logEntity.setOperator(className + "." + methodName + "()");

            //请求的参数
//            Object[] args = joinPoint.getArgs();
//            try{
//                String params = new Gson().toJson(args[0]);
//                //logEntity.setParams(params);
//                System.out.println("======"+params);
//
//            }catch (Exception e){
//
//            }

            //获取request
            HttpServletRequest request = HttpContextUtils.getHttpServletRequest();

            String userId="";
            String fullName="";
            String principal = (String) SecurityUtils.getSubject().getPrincipal();
            if (StringUtils.isNoneEmpty(principal)) {
                Claims claims = jwtUtil.parseJWT(principal);
                userId = (String)claims.get("userId");
                fullName = (String)claims.get("fullName");
            }else {
                JSONObject loginInfo = config.getJsonObject().getJSONObject("loginInfo");
                userId =loginInfo.getString("userId");
                fullName =loginInfo.getString("fullName");
            }
            //设置IP地址
            //logEntity.setIp(IPUtils.getIpAddr(request));

            //用户名


            //JSONObject requestBody = (JSONObject)config.getJsonObject().get("requestBody");
            //String remoteAddr = requestBody.getString("remoteAddr");

            //String userId = "userId";
            //String fullName = "fullName";
            String username ="test";
            //logEntity.setUsername(username);

            //logEntity.setTime(time);
            //logEntity.setCreateDate(new Date());

            log.info("保存日志。。。。。。");
            String logId = UUID.randomUUID().toString().replace("-", "");
            String ip ="";
            Integer code =100000;
            LogEntity logEntity = new LogEntity(logId,userId,fullName,"","",IPUtils.getIpAddr(request),new Date(),syslog.value(),code);
            //保存系统日志
            logService.addLog(logEntity);
            //sysLogService.save(sysLog);
        }


    }
}

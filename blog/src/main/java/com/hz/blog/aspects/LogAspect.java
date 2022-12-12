package com.hz.blog.aspects;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hz.blog.annotation.LogOperator;
import com.hz.blog.entity.Config;
import com.hz.blog.entity.LogEntity;
import com.hz.blog.entity.LoginUserInfo;
import com.hz.blog.entity.ResponseResult;
import com.hz.blog.service.LogService;
import com.hz.blog.task.LogListener;
import com.hz.blog.task.TaskManager;
import com.hz.blog.task.TaskParam;
import com.hz.blog.utils.HttpContextUtils;
import com.hz.blog.utils.IPUtils;
import com.hz.blog.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

import static com.hz.blog.constant.Constant.LOG_LOGIN;

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

    @Autowired
    private TaskManager taskManager;

    @Pointcut("@annotation(com.hz.blog.annotation.LogOperator)")
    public void logPointCut() {

    }

//    @Around("logPointCut()")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        long beginTime = System.currentTimeMillis();
//        //执行方法
//        Object result = point.proceed();
//        //执行时长(毫秒)
//        long time = System.currentTimeMillis() - beginTime;
//
//        //保存日志
//        saveSysLog(point, time);
//
//        return result;
//    }

    /**
     * 处理完请求后执行
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        System.out.println("1232"+jsonResult);
        saveSysLog(joinPoint,jsonResult,null);
    }

    /**
     * 拦截异常操作
     * 抛出异常后执行
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        saveSysLog(joinPoint,null,e);
    }


    private void saveSysLog(JoinPoint joinPoint ,Object jsonResult, final Exception e) {
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
                log.info("fullName:{}",fullName);
            }else {
                if (jsonResult!=null && StringUtils.equals(syslog.type(),LOG_LOGIN)){
                    if (jsonResult instanceof ResponseResult) {
                        ResponseResult responseResult = (ResponseResult) jsonResult;
                        //String token = String.valueOf(responseResult.getData());
                        LoginUserInfo loginUserInfo = (LoginUserInfo) responseResult.getData();
                        Claims claims = jwtUtil.parseJWT(loginUserInfo.getToken());
                        userId = (String)claims.get("userId");
                        fullName = (String)claims.get("fullName");
                    }
                }else {
                    String token = request.getHeader("token");
                    log.info("token{}",token);
                    Claims claims = jwtUtil.parseJWT(token);
                    userId = (String)claims.get("userId");
                    fullName = (String)claims.get("fullName");
                }

//                JSONObject loginInfo = config.getJsonObject().getJSONObject("loginInfo");
//                userId =loginInfo.getString("userId");
//                fullName =loginInfo.getString("fullName");
//                log.info("fullName:{}",fullName);

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

            Integer code = e==null ? 200:500;

            LogEntity logEntity = new LogEntity(logId,userId,fullName,"","",Integer.valueOf(syslog.type()),IPUtils.getIpAddr(request),new Date(),syslog.value(),code);

            //保存系统日志 异步任务执行
            TaskParam taskParam = new TaskParam(LogListener.class);
            taskParam.put("log", logEntity);
            taskManager.pushTask(taskParam);

            //logService.addLog(logEntity);
            //sysLogService.save(sysLog);
        }



    }
}

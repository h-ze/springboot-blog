package com.hz.blog.interceptors;

import com.alibaba.fastjson.JSONObject;
import com.hz.blog.annotation.RepeatSubmit;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class RepeatSubmitInterceptorConfig implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation!=null){
                if (isRepeatSubmit(request,annotation)){


                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("code",700001);
                    jsonObject.put("msg",annotation.message());
                    String json=jsonObject.toString();
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().println(json);
                    return false;

                }
            }
        }
        return true;
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     *
     * @param request 请求对象
     * @param annotation 防复注解
     * @return 结果
     */
    public  boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation) throws Exception{
        return true;
    }
}

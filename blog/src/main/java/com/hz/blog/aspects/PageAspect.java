package com.hz.blog.aspects;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hz.blog.entity.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.ObjectUtils;

//声明当前类是配置类
@Configuration
//标注当前类是Aop切面类
@Aspect
@Slf4j
//开启Aop增强
@EnableAspectJAutoProxy
public class PageAspect {

    /**
     * 定义切入点
     */
    @Pointcut("@annotation(com.hz.blog.annotation.StartPage)")
    public void annotation() {
    }

    /**
     * 环绕增强
     */
    @Around("annotation()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 当前页码
        int pageNum=1;
        //每页记录数
        int pageSize=10;
//        PageFilter pageFilter=null;
        PageResult pageResult =null;
//        //获取被增强方法的参数
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if(arg instanceof PageResult) {
                pageResult=(PageResult) arg;
                pageNum= ObjectUtils.isEmpty(pageResult.getPageNum())? pageNum:pageResult.getPageNum();
                pageSize=ObjectUtils.isEmpty(pageResult.getPageSize())? pageSize:pageResult.getPageSize();
            }
        }
        Object result = null;
        try {
            //调用分页插件传入开始页码和页面容量
            Page<Object> page = PageHelper.startPage(pageNum, pageSize);
            //执行被增强的方法，不写，则被增强方法不执行
            result = proceedingJoinPoint.proceed(args);
            log.info("result:{}",result);

            //获取并封装分页后的参数
            pageResult.setPageNum(page.getPageNum());
            pageResult.setPageSize(page.getPageSize());
            pageResult.setTotalSize(page.getTotal());
            pageResult.setTotalPages(page.getPages());
            //pageResult.setData(page.getList());
            //pageResult.setCurrentSize(page.getList().size());

        } catch (Exception e) {
            log.info("查询数据库异常",e);
        }
        return result;
    }
}

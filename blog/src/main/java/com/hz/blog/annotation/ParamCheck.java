package com.hz.blog.annotation;


import java.lang.annotation.*;

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamCheck {

    String message() default "参数为空";

    /**
     * 是否为null，默认不能为null
     * @return
     */
    boolean notNull() default true;

    /**
     * 是否为空，默认不能为空
     * @return
     */
    boolean notBlank() default true;
}

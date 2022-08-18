package com.hz.blog.annotation;

import java.lang.annotation.*;

/**
 * @author zengjintao
 * @create_at 2021年10月16日 0016 11:40
 * @since version 1.6.5
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventQueue {

    String name() default "";
}


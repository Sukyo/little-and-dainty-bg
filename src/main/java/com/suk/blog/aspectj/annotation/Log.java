package com.suk.blog.aspectj.annotation;


import com.suk.blog.enums.LogType;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 标题或模块
     */
    String title() default "";

    /**
     * 功能
     */
    LogType logType() default LogType.OTHER;

    /**
     * 请求参数
     */
    boolean requestParam() default true;

}

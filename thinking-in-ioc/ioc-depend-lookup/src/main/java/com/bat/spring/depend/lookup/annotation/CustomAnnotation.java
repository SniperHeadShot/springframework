package com.bat.spring.depend.lookup.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 *
 * @author ZhengYu
 * @version 1.0 2020/1/8 3:22
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CustomAnnotation {

    String value() default "";
}

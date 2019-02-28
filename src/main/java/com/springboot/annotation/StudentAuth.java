package com.springboot.annotation;

import java.lang.annotation.*;


/**
 * 自定义的学生权限注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StudentAuth {
}

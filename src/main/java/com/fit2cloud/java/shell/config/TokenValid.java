package com.fit2cloud.java.shell.config;

/**
 * @author : zhm
 * @description :
 * @date : 2019/4/1 10:58
 */

import java.lang.annotation.*;

/**
 * token校验注解
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenValid {
}

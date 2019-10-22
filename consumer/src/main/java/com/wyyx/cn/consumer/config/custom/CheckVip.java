package com.wyyx.cn.consumer.config.custom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: 91917
 * Date: 2019/10/18
 * Time: 13:43
 * Description: No Description
 */
@Target(ElementType.METHOD)//代表了这个自定义注解必须要加载方法上
@Retention(RetentionPolicy.RUNTIME)//运行时候有效
public @interface CheckVip {
}

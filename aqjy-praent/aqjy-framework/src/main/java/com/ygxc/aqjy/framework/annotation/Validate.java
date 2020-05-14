package com.ygxc.aqjy.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启动数据验证得注解
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月8日
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {

}

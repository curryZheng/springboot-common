package com.ygxc.aqjy.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

import com.ygxc.aqjy.framework.core.db.MybatisPlusConfig;


/**
 * @ClassName EnableHvacData
 * @Description
 * @Author zc
 * @Date 2019/6/13 15:51
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Import({MybatisPlusConfig.class})
public @interface EnableYgxcData {
}

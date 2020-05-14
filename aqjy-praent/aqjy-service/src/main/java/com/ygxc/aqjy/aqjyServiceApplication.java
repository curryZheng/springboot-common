package com.ygxc.aqjy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.ygxc.aqjy.framework.handler.GlobalExceptionHandler;


/**
 * 安全教育启动类
 * @author leiZheng
 *
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@Import(value = GlobalExceptionHandler.class)
public class aqjyServiceApplication {

	public static void main(String[] args) {
		 SpringApplication.run(aqjyServiceApplication.class, args);
	}
}

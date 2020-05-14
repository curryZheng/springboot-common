package com.ygxc.aqjy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * 安全教育启动类
 * @author leiZheng
 *
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class aqjyServiceApplication {

	public static void main(String[] args) {
		 SpringApplication.run(aqjyServiceApplication.class, args);
	}
}

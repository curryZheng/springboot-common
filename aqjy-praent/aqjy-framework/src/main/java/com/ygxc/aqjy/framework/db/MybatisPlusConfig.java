package com.ygxc.aqjy.framework.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * mybatis plus 配置
 *
 * @author LeiZheng
 * @date 2018/7/20 0020 上午 11:36
 */
@Configuration
//@ConditionalOnBean(DataSource.class)
@MapperScan("com.ygxc.aqjy.**.dao")
@EnableTransactionManagement
public class MybatisPlusConfig {

	/**
	 * 分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	@Bean
	public ISqlInjector sqlInjector() {
		return new LogicSqlInjector();
	}

}

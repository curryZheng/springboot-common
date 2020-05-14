package com.ygxc.aqjy.filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤配置类
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月14日
 */
@Configuration
public class FilterConfig {

	/**
	 * 操作日志过滤器
	 * @return
	 */
	@ConditionalOnExpression("${oplogSwitch}")
	@Bean("opLogFilterRegistration")
	public FilterRegistrationBean<OpLogFilter> createOpLogFilterRegistration(OpLogFilter filter) {
		FilterRegistrationBean<OpLogFilter> bean = new FilterRegistrationBean<OpLogFilter>();
		bean.setFilter(filter);
		bean.setOrder(100);
		return bean;
	}
}

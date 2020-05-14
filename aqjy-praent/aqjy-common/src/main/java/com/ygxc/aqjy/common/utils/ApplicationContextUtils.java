package com.ygxc.aqjy.common.utils;

import org.springframework.context.ApplicationContext;

/**
 * 获取ApplicationContext的工具类，由spring boot启动后将ApplicationContext设置到此窗口中，便于之后的使用，
 * 注：无法保证先后顺序
 * @author leiZheng  
 * @date 2019年8月13日
 */
public class ApplicationContextUtils {

	private static ApplicationContext context;
	
	/**
	 * 设置ApplicationContext
	 * @param context
	 */
	public static void setContext(ApplicationContext context) {
		ApplicationContextUtils.context = context;
	}
	
	/**
	 * 取得ApplicationContext
	 * @return
	 */
	public static ApplicationContext getContext() {
		return context;
	}
	
	/**
	 * 取得bean
	 * @param <T>
	 * @param beanClass
	 * @return
	 */
	public static <T> T getBean(Class<T> beanClass) {
		return getContext().getBean(beanClass);
	}
}

package com.ygxc.aqjy.common.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 排除的url信息
 * 
 * 
 *
 */
public class AqjyExcludeUrlOptions {

	/** 不登录可访问的url列表 */
	public final static List<String> NOT_AUTHENTICATION_LIST = Arrays.asList("/login", "/logout");
	
	/** 不鉴权可访问的url列表 */
	public final static List<String> NOT_AUTHORIZATION_LIST = Arrays.asList();
}

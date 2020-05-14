package com.ygxc.aqjy.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
import lombok.Data;
/**
 * 封装请求对象用户区分用户登录类型
 * @author leiZheng
 *
 */
@Data
public class CustomToken extends UsernamePasswordToken {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String loginType;

	public CustomToken() {
		
	}
	
	
	public CustomToken(String userName, String password, String loginType) {
		super(userName,password);
		
		this.loginType = loginType;
	}
	
}

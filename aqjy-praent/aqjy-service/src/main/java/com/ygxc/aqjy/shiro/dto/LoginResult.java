package com.ygxc.aqjy.shiro.dto;

/**
 * 登录结果集
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月8日
 */
public class LoginResult<T> {

	/** 登录的用户信息 */
	private T user;
	
	/** sessionId */
	private String sessionId;
	
	public void setUser(T user) {
		this.user = user;
	}
	
	public T getUser() {
		return user;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
package com.ygxc.aqjy.rsp.user;

import java.io.Serializable;

/**
 * 登录授权信息
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月8日
 */
public class PrincipalDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** id */
	protected String id;

	/** 用户名 */
    protected String username;
    
    /** 姓名 */
    protected String name;
    
    /** 员工编号 */
    protected String userNo;
    
    /** 角色ID */
    protected String roleId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleId() {
		return roleId;
	}
}

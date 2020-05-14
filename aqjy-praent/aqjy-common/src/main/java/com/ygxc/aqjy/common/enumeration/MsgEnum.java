package com.ygxc.aqjy.common.enumeration;

/**
 * 异常状态枚举
 * @author Lei
 *
 */
public enum MsgEnum {
	
	/**
	 * "受影響行數為0，操作失敗
	 */
	DB_NOT_FIND_DATA(1001, "受影響行數為0，操作失敗"),
	
	/**
	 * 操作异常
	 */
	ABNORMAL_OPERATION(1002, "操作异常"),
	
	/**
	 * 用户不存在
	 */
	THE_USER_DOESN_T_EXIST(1004,"用户不存在"),
	
	/**
	 * 账号不存在或者密码错误
	 */
	NO_ACCOUNT_OR_PASSWORD_ERROR(1003,"账号不存在或者密码错误");
	// ################################社區功能#####################################//
	
	private Integer val;
	private String description;

	public static MsgEnum findEnum(Integer val) {
		if (val == null) {
			return null;
		}
		for (MsgEnum typeMsg : values()) {
			if (val.equals(typeMsg.getVal())) {
				return typeMsg;
			}
		}
		return null;
	}

	/**
	 * @param key
	 * @param msg
	 */
	private MsgEnum(Integer val, String description) {
		this.val = val;
		this.description = description;
	}

	public Integer getVal() {
		return val;
	}

	public void setVal(Integer val) {
		this.val = val;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

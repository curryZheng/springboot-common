package com.ygxc.aqjy.common.structure;

import java.sql.Timestamp;



public interface IBusinessEntity    {
	
	/**
	 * 设置主键编号
	 */
	void setId(String id);
	
	/**
	 * 取得主键编号
	 * @return
	 */
	String getId();

	/**
	 * 设置创建时间
	 */
	void setCreateTime(Timestamp createTime);

	/**
	 * 设置修改时间
	 */
	void setUpdateTime(Timestamp updateTime);

	/**
	 * 设置软删除，0：可用；1：删除；
	 */
	void setIsDelete(Integer isDelete);

	/**
	 * 设置操作用户编号
	 */
	void setOpUserId(String opUserId);
	
	/**
	 * 设置操作用户姓名
	 */
	void setOpUserName(String opUserName);
}

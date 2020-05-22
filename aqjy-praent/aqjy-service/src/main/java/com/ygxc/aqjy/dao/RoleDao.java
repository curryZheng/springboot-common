package com.ygxc.aqjy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ygxc.aqjy.entity.user.RoleEntity;
import com.ygxc.aqjy.req.user.RoleQueryReq;

/**
 * 角色dao
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月8日
 */
public interface RoleDao extends BaseMapper<RoleEntity> {

	/**
	 * 查询角色列表
	 * @param req
	 * @param page 
	 * @return
	 */
	List<RoleEntity> queryRoleList(@Param("req") RoleQueryReq req, Page<?> page);
}

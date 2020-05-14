package com.ygxc.aqjy.dao;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
	 * @return
	 */
	List<RoleEntity> queryRoleList(RoleQueryReq req);
}

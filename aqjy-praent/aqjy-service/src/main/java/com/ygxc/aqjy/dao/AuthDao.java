package com.ygxc.aqjy.dao;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ygxc.aqjy.entity.user.AuthEntity;
import com.ygxc.aqjy.rsp.user.AuthTreeForRoleDto;

/**
 * 权限dao
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月8日
 */
public interface AuthDao extends BaseMapper<AuthEntity> {

	/**
	 * 查询角色的权限编号列表
	 * @param roleId
	 * @return
	 */
	List<String> queryRoleAuthCodeList(String roleId);
	
	/**
	 * 查询角色的权限url列表
	 * @param roleId
	 * @return
	 */
	List<String> queryRoleAuthUrlList(String roleId);
	
	/**
	 * 查询角色的权限树列表
	 * @param roleId
	 * @return
	 */
	List<AuthEntity> queryRoleAuthTreeList(String roleId);
	
	/**
	 * 查询权限树列表，角色相关信息
	 * @param req
	 * @return
	 */
	List<AuthTreeForRoleDto> queryAuthTreeForRoleList(String roleId);

	/**
	 * 根据URL查询权限信息
	 * @param url
	 * @return
	 */
	AuthEntity selectOneByUrl(String url);

}

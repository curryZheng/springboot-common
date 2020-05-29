package com.ygxc.aqjy.service;

import java.util.List;

import com.ygxc.aqjy.common.structure.PageR;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.req.common.OperationByIdReq;
import com.ygxc.aqjy.req.user.RoleCreateReq;
import com.ygxc.aqjy.req.user.RoleModifyReq;
import com.ygxc.aqjy.req.user.RoleQueryReq;
import com.ygxc.aqjy.rsp.user.RoleDto;

/**
 * 校色service
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月8日
 */
public interface RoleService {

	/**
	 * 创建角色
	 * @param req
	 * @return
	 */
	R<String> createRole(RoleCreateReq req);
	
	/**
	 * 修改角色
	 * @param req
	 * @return
	 */
	R<Void> modifyRole(RoleModifyReq req);
	
	/**
	 * 删除角色
	 * @param req
	 * @return
	 */
	R<Void> deleteRole(OperationByIdReq req);
	
	/**
	 * 查询角色列表
	 * @param req
	 * @return
	 */
	PageR<List<RoleDto>> queryRoleList(RoleQueryReq req);

	/**
	 * 查询全部角色
	 * @return
	 */
	R<List<RoleDto>> queryRoleAll();
}

package com.ygxc.aqjy.service;

import java.util.List;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.common.structure.RequestHead;
import com.ygxc.aqjy.req.common.OperationByIdReq;
import com.ygxc.aqjy.req.user.AuthByUrlFindReq;
import com.ygxc.aqjy.req.user.AuthCreateReq;
import com.ygxc.aqjy.req.user.AuthInfoByRoleIdQueryReq;
import com.ygxc.aqjy.req.user.AuthModifyReq;
import com.ygxc.aqjy.req.user.RoleAuthModifyReq;
import com.ygxc.aqjy.rsp.user.AuthDto;
import com.ygxc.aqjy.rsp.user.AuthTreeDto;
import com.ygxc.aqjy.rsp.user.AuthTreeForRoleDto;

/**
 * 权限service
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月8日
 */
public interface AuthService {

	/**
	 * 创建权限
	 * @param req
	 * @return
	 */
	R<String> createAuth(AuthCreateReq req);
	
	/**
	 * 修改权限
	 * @param req
	 * @return
	 */
	R<Void> modifyAuth(AuthModifyReq req);
	
	/**
	 * 删除权限
	 * @param req
	 * @return
	 */
	R<Void> deleteAuth(OperationByIdReq req);
	
	/**
	 * 查询角色的权限对象列表
	 * @param req
	 * @return
	 */
	R<List<String>> queryRoleAuthCodeList(AuthInfoByRoleIdQueryReq req);
	
	/**
	 * 查询角色的权限url列表
	 * @param req
	 * @return
	 */
	R<List<String>> queryRoleAuthUrlList(AuthInfoByRoleIdQueryReq req);
	
	/**
	 * 查询角色的权限树列表
	 * @param req
	 * @return
	 */
	R<List<AuthTreeDto>> queryRoleAuthTreeList(AuthInfoByRoleIdQueryReq req);
	
	/**
	 * 查询权限树列表
	 * @param req
	 * @return
	 */
	R<List<AuthTreeDto>> queryAuthTreeList(RequestHead req);
	
	/**
	 * 查询权限树列表，角色相关信息
	 * @param req
	 * @return
	 */
	R<List<AuthTreeForRoleDto>> queryAuthTreeForRoleList(AuthInfoByRoleIdQueryReq req);
	
	/**
	 * 修改角色权限
	 * @param req
	 * @return
	 */
	R<Void> modifyRoleAuth(RoleAuthModifyReq req);
	
	/**
	 * 根据URL查询权限
	 * @param req
	 * @return
	 */
	R<AuthDto> findAuthByUrl(AuthByUrlFindReq req);
}

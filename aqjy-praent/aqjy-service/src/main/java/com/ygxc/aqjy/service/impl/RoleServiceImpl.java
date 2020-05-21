package com.ygxc.aqjy.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ygxc.aqjy.common.structure.PageR;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.dao.RoleDao;
import com.ygxc.aqjy.dao.UserDao;
import com.ygxc.aqjy.framework.base.BaseService;
import com.ygxc.aqjy.req.common.OperationByIdReq;
import com.ygxc.aqjy.req.user.RoleCreateReq;
import com.ygxc.aqjy.req.user.RoleModifyReq;
import com.ygxc.aqjy.req.user.RoleQueryReq;
import com.ygxc.aqjy.rsp.user.RoleDto;
import com.ygxc.aqjy.service.RoleService;

/**
 * 角色service
 * 
 * @author Qiaoxin.Hong
 *
 */
@Service
public class RoleServiceImpl extends BaseService implements RoleService {
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	UserDao userDao;

	@Override
	public R<String> createRole(RoleCreateReq req) {
		
		return null;
	}

	@Override
	public R<Void> modifyRole(RoleModifyReq req) {
	
		return null;
	}

	@Override
	public R<Void> deleteRole(OperationByIdReq req) {
		
		return null;
	}

	@Override
	public PageR<List<RoleDto>> queryRoleList(RoleQueryReq req) {
		
		return null;
	}

	
}

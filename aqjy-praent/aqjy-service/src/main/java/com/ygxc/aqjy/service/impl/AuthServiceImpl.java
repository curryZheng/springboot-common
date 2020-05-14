package com.ygxc.aqjy.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.common.structure.RequestHead;
import com.ygxc.aqjy.dao.AuthDao;
import com.ygxc.aqjy.dao.RoleAuthDao;
import com.ygxc.aqjy.framework.base.BaseService;
import com.ygxc.aqjy.req.common.OperationByIdReq;
import com.ygxc.aqjy.req.user.AuthByUrlFindReq;
import com.ygxc.aqjy.req.user.AuthCreateReq;
import com.ygxc.aqjy.req.user.AuthInfoByRoleIdQueryReq;
import com.ygxc.aqjy.req.user.AuthModifyReq;
import com.ygxc.aqjy.req.user.RoleAuthModifyReq;
import com.ygxc.aqjy.rsp.user.AuthDto;
import com.ygxc.aqjy.rsp.user.AuthTreeDto;
import com.ygxc.aqjy.rsp.user.AuthTreeForRoleDto;
import com.ygxc.aqjy.service.AuthService;

/**
 * 权限service
 * 
 * @author Qiaoxin.Hong
 *
 */
@Service
public class AuthServiceImpl extends BaseService implements AuthService {
	
	@Autowired
	private  AuthDao authDao;
	
	@Autowired
	private  RoleAuthDao roleAuthDao;

	@Override
	public R<String> createAuth(AuthCreateReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<Void> modifyAuth(AuthModifyReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<Void> deleteAuth(OperationByIdReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<List<String>> queryRoleAuthCodeList(AuthInfoByRoleIdQueryReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<List<String>> queryRoleAuthUrlList(AuthInfoByRoleIdQueryReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<List<AuthTreeDto>> queryRoleAuthTreeList(AuthInfoByRoleIdQueryReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<List<AuthTreeDto>> queryAuthTreeList(RequestHead req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<List<AuthTreeForRoleDto>> queryAuthTreeForRoleList(AuthInfoByRoleIdQueryReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<Void> modifyRoleAuth(RoleAuthModifyReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<AuthDto> findAuthByUrl(AuthByUrlFindReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

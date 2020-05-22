package com.ygxc.aqjy.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.common.structure.RequestHead;
import com.ygxc.aqjy.dao.AuthDao;
import com.ygxc.aqjy.framework.annotation.AqjyValidate;
import com.ygxc.aqjy.framework.base.BaseService;
import com.ygxc.aqjy.req.sys.LoginReq;
import com.ygxc.aqjy.rsp.sys.LoginDto;
import com.ygxc.aqjy.rsp.user.PrincipalDto;
import com.ygxc.aqjy.service.SystemService;
import com.ygxc.aqjy.shiro.dto.LoginResult;
import com.ygxc.aqjy.shiro.utils.ShiroUtils;

/**
 * 系统service
 * 
 *
 *
 */
@Service
public class SystemServiceImpl extends BaseService implements SystemService {
	
	@Autowired
	AuthDao authDao;

	/**
	 * 登录
	 */
	@AqjyValidate
	@Override
	public R<LoginDto> login(LoginReq req) {
		//登录
		LoginResult<PrincipalDto> loginResult = ShiroUtils.login(req.getUsername(), req.getPassword());	
		//封装登录结果信息
		LoginDto dto = convertBean(loginResult.getUser(), LoginDto.class);
    	dto.setToken(loginResult.getSessionId());	
		//权限代号列表
		List<String> authCodeList = authDao.queryRoleAuthCodeList(dto.getRoleId());
		dto.setAuthCodeList(authCodeList);
		return packResult(dto);

	}

	/**
	 * 登出
	 */
	@Override
	public R<Void> logout(RequestHead req) {
		ShiroUtils.logout();
		return packResult();
	}

	
}

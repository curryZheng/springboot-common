package com.ygxc.aqjy.service;

import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.common.structure.RequestHead;
import com.ygxc.aqjy.req.sys.LoginReq;
import com.ygxc.aqjy.rsp.sys.LoginDto;

/**
 * 系统service
 * 
 * @author curry
 *
 */
public interface SystemService {

	/**
	 * 登录
	 * @param req
	 * @return
	 */
	R<LoginDto> login(LoginReq req);
	
	/**
	 * 登出
	 * @param req
	 * @return
	 */
	R<Void> logout(RequestHead req);
}

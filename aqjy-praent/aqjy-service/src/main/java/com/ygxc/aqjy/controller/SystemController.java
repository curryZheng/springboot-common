package com.ygxc.aqjy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.common.structure.RequestHead;
import com.ygxc.aqjy.req.sys.LoginReq;
import com.ygxc.aqjy.rsp.sys.LoginDto;
import com.ygxc.aqjy.service.SystemService;

/**
 * 系统设定控制器
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月8日
 */
@Api(tags = "系统controller")
@RestController
@RequestMapping("/")
public class SystemController {

	@Autowired
	private  SystemService systemService;
	
	@ApiOperation("登录")
	@PostMapping("login")
	public R<LoginDto> login(@RequestBody LoginReq req) {
		return systemService.login(req);
	}
	
	@ApiOperation("登出")
	@PostMapping("logout")
	public R<Void> logout(@RequestBody RequestHead req) {
		return systemService.logout(req);
	}
}

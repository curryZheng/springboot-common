package com.ygxc.aqjy.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.ygxc.aqjy.service.AuthService;
@Api(tags = "权限controller")
@RestController
@RequestMapping("/auth/")
public class AuthController {
	
	@Autowired
	private  AuthService authService;

	@ApiOperation("创建权限")
	@PostMapping("createAuth")
	public R<String> createAuth(@RequestBody AuthCreateReq req) {
		return authService.createAuth(req);
	}
	
	@ApiOperation("修改权限")
	@PostMapping("modifyAuth")
	public R<Void> modifyAuth(@RequestBody AuthModifyReq req) {
		return authService.modifyAuth(req);
	}
	
	@ApiOperation("删除权限")
	@PostMapping("deleteAuth")
	public R<Void> deleteAuth(@RequestBody OperationByIdReq req) {
		return authService.deleteAuth(req);
	}
	
	@ApiOperation("查询角色的权限对象列表")
	@PostMapping("queryRoleAuthCodeList")
	public R<List<String>> queryRoleAuthCodeList(@RequestBody AuthInfoByRoleIdQueryReq req) {
		return authService.queryRoleAuthCodeList(req);
	}
	
	@ApiOperation("查询角色的权限url列表")
	@PostMapping("queryRoleAuthUrlList")
	public R<List<String>> queryRoleAuthUrlList(@RequestBody AuthInfoByRoleIdQueryReq req) {
		return authService.queryRoleAuthUrlList(req);
	}
	
	@ApiOperation("查询角色的权限树列表")
	@PostMapping("queryRoleAuthTreeList")
	public R<List<AuthTreeDto>> queryRoleAuthTreeList(@RequestBody AuthInfoByRoleIdQueryReq req) {
		return authService.queryRoleAuthTreeList(req);
	}
	
	@ApiOperation("查询权限树列表")
	@PostMapping("queryAuthTreeList")
	public R<List<AuthTreeDto>> queryAuthTreeList(@RequestBody RequestHead req) {
		return authService.queryAuthTreeList(req);
	}
	
	@ApiOperation("查询权限树列表，角色相关信息")
	@PostMapping("queryAuthTreeForRoleList")
	public R<List<AuthTreeForRoleDto>> queryAuthTreeForRoleList(@RequestBody AuthInfoByRoleIdQueryReq req) {
		return authService.queryAuthTreeForRoleList(req);
	}
	
	@ApiOperation("修改角色权限")
	@PostMapping("modifyRoleAuth")
	public R<Void> modifyRoleAuth(@RequestBody RoleAuthModifyReq req) {
		return authService.modifyRoleAuth(req);
	}
	
	@ApiOperation("根据URL查询权限")
	@PostMapping("findAuthByUrl")
	public R<AuthDto> findAuthByUrl(@RequestBody AuthByUrlFindReq req) {
		return authService.findAuthByUrl(req);
	}
}

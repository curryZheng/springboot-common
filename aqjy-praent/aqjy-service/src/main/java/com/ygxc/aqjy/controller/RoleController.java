package com.ygxc.aqjy.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.ygxc.aqjy.common.structure.PageR;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.req.common.OperationByIdReq;
import com.ygxc.aqjy.req.user.RoleCreateReq;
import com.ygxc.aqjy.req.user.RoleModifyReq;
import com.ygxc.aqjy.req.user.RoleQueryReq;
import com.ygxc.aqjy.rsp.user.RoleDto;
import com.ygxc.aqjy.service.RoleService;

@Api(tags = "角色controller")
@RestController
@RequestMapping("/role/")
public class RoleController {

	@Autowired
	private  RoleService roleService;
	
	@ApiOperation("创建角色")
	@PostMapping("createRole")
	public R<String> createRole(@RequestBody RoleCreateReq req) {
		return roleService.createRole(req);
	}
	
	@ApiOperation("修改角色")
	@PostMapping("modifyRole")
	public R<Void> modifyRole(@RequestBody RoleModifyReq req) {
		return roleService.modifyRole(req);
	}
	
	@ApiOperation("删除角色")
	@PostMapping("deleteRole")
	public R<Void> deleteRole(@RequestBody OperationByIdReq req) {
		return roleService.deleteRole(req);
	}
	
	@ApiOperation("查询角色列表")
	@PostMapping("queryRoleList")
	public PageR<List<RoleDto>> queryRoleList(@RequestBody RoleQueryReq req) {
		return roleService.queryRoleList(req);
	}
}

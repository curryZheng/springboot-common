package com.ygxc.aqjy.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.ygxc.aqjy.common.structure.PageR;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.req.common.OperationByIdReq;
import com.ygxc.aqjy.req.user.UserCreateReq;
import com.ygxc.aqjy.req.user.UserLoginReq;
import com.ygxc.aqjy.req.user.UserModifyReq;
import com.ygxc.aqjy.req.user.UserQueryReq;
import com.ygxc.aqjy.rsp.user.UserDto;
import com.ygxc.aqjy.rsp.user.UserLoginDto;
import com.ygxc.aqjy.service.SystemService;
import com.ygxc.aqjy.service.UserService;

@Api(tags = "用户controller")
@RestController
@RequestMapping("/user/")
public class UserController {
	
	@Autowired
	private  UserService userService;
	
	@Autowired
	private SystemService systemService;
	
	@ApiOperation("创建用户")
	@PostMapping("createUser")
	public R<String> createUser(@RequestBody UserCreateReq req) {
		return userService.createUser(req);
	}
	
	@ApiOperation("修改用户")
	@PostMapping("modifyUser")
	public R<Void> modifyUser(@RequestBody UserModifyReq req) {
		return userService.modifyUser(req);
	}
	
	@ApiOperation("登录查询用户")
	@PostMapping("findUserByLogin")
	public R<UserLoginDto> findUserByLogin(@RequestBody UserLoginReq req) {
		return userService.findUserByLogin(req);
	}
	
	@ApiOperation("根据ID查询用户")
	@PostMapping("findUserById")
	public R<UserDto> findUserById(@RequestBody OperationByIdReq req) {
		return userService.findUserById(req);
	}
	
	@ApiOperation("删除用户")
	@PostMapping("deleteUser")
	public R<Void> deleteUser(@RequestBody OperationByIdReq req) {
		return userService.deleteUser(req);
	}
	
	@ApiOperation("查询用户列表")
	@GetMapping
	public PageR<List<UserDto>> queryUserList(UserQueryReq req) {
		return userService.queryUserList(req);
	}
	

}

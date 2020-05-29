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
import com.ygxc.aqjy.req.oplog.OpLogCreateReq;
import com.ygxc.aqjy.req.oplog.OpLogModifyReq;
import com.ygxc.aqjy.req.oplog.OpLogQueryReq;
import com.ygxc.aqjy.rsp.oplog.OpLogDto;
import com.ygxc.aqjy.service.OpLogService;

@Api(tags = "操作日志controller")
@RestController
@RequestMapping("/oplog/")
public class OpLogController {
	
	@Autowired
	private  OpLogService opLogService;
	
	@ApiOperation("创建操作日志")
	@PostMapping("createOpLog")
	public R<String> createOpLog(@RequestBody OpLogCreateReq req) {
		return opLogService.createOpLog(req);
	}
	
	@ApiOperation("修改操作日志")
	@PostMapping("modifyOpLog")
	public R<String> modifyOpLog(@RequestBody OpLogModifyReq req) {
		return opLogService.modifyOpLog(req);
	}
	
	@ApiOperation("查询操作日志列表")
	@GetMapping("queryOpLogList")
	public PageR<List<OpLogDto>> queryOpLogList( OpLogQueryReq req) {
		return opLogService.queryOpLogList(req);
	}
	
	@ApiOperation("根据ID查询操作日志")
	@PostMapping("findOpLogById")
	public R<OpLogDto> findOpLogById(@RequestBody OperationByIdReq req) {
		return opLogService.findOpLogById(req);
	}
	
	@ApiOperation("导出操作日志")
	@GetMapping("exportOpLog")
	public void exportOpLog( OpLogQueryReq req) {
		opLogService.exportOpLog(req);
	}
}

package com.ygxc.aqjy.service;

import java.util.List;

import com.ygxc.aqjy.common.structure.PageR;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.req.common.OperationByIdReq;
import com.ygxc.aqjy.req.oplog.OpLogCreateReq;
import com.ygxc.aqjy.req.oplog.OpLogModifyReq;
import com.ygxc.aqjy.req.oplog.OpLogQueryReq;
import com.ygxc.aqjy.rsp.oplog.OpLogDto;

/**
 * 操作日志service
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月8日
 */
public interface OpLogService {
	
	/**
	 * 创建操作日志
	 * @param req
	 * @return
	 */
	R<String> createOpLog(OpLogCreateReq req);
	
	/**
	 * 修改操作日志
	 * @param req
	 * @return
	 */
	R<String> modifyOpLog(OpLogModifyReq req);
	
	/**
	 * 查询操作日志列表
	 * @param req
	 * @return
	 */
	PageR<List<OpLogDto>> queryOpLogList(OpLogQueryReq req);
	
	/**
	 * 根据ID查询操作日志
	 * @param req
	 * @return
	 */
	R<OpLogDto> findOpLogById(OperationByIdReq req);
	
	/**
	 * 导出操作日志
	 * @param req
	 */
	void exportOpLog(OpLogQueryReq req);
}

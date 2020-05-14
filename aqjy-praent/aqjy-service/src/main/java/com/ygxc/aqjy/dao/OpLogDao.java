package com.ygxc.aqjy.dao;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ygxc.aqjy.entity.oplog.OpLogEntity;
import com.ygxc.aqjy.req.oplog.OpLogQueryReq;
import com.ygxc.aqjy.rsp.oplog.OpLogExcelDto;

/**
 * 操作日志dao
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月8日
 */
public interface OpLogDao extends BaseMapper<OpLogEntity> {

	/**
	 * 查询操作日志列表
	 * @param req
	 * @return
	 */
	List<OpLogEntity> queryOpLogList(OpLogQueryReq req);
	
	/**
	 * 查询导出的操作日志列表
	 * @param req
	 * @return
	 */
	List<OpLogExcelDto> queryExportOpLogList(OpLogQueryReq req);
}

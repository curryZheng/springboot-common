package com.ygxc.aqjy.dao;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ygxc.aqjy.entity.oplog.OpLogEntity;
import com.ygxc.aqjy.req.oplog.OpLogQueryReq;
import com.ygxc.aqjy.rsp.oplog.OpLogExcelDto;

import io.lettuce.core.dynamic.annotation.Param;

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
	 * @param page 
	 * @param object 
	 * @return
	 */
	List<OpLogEntity> queryOpLogList(@Param("req") OpLogQueryReq req, Page<?> page);
	
	/**
	 * 查询导出的操作日志列表
	 * @param req
	 * @param page 
	 * @return
	 */
	List<OpLogExcelDto> queryExportOpLogList(@Param("req") OpLogQueryReq req, Page<?> page);
}

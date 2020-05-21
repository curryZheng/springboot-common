package com.ygxc.aqjy.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ygxc.aqjy.common.constant.BConst;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.common.utils.Assist;
import com.ygxc.aqjy.common.utils.DateUtil;
import com.ygxc.aqjy.common.utils.IdUtil;
import com.ygxc.aqjy.common.utils.ServeletUtils;
import com.ygxc.aqjy.dao.OpLogDao;
import com.ygxc.aqjy.entity.oplog.OpLogEntity;
import com.ygxc.aqjy.framework.base.BaseService;
import com.ygxc.aqjy.framework.core.poi.interfaces.IWriteDataFetch;
import com.ygxc.aqjy.framework.core.poi.utils.ExcelExportUtils;
import com.ygxc.aqjy.req.common.OperationByIdReq;
import com.ygxc.aqjy.req.oplog.OpLogCreateReq;
import com.ygxc.aqjy.req.oplog.OpLogModifyReq;
import com.ygxc.aqjy.req.oplog.OpLogQueryReq;
import com.ygxc.aqjy.rsp.oplog.OpLogDto;
import com.ygxc.aqjy.rsp.oplog.OpLogExcelDto;
import com.ygxc.aqjy.service.OpLogService;

/**
 * 操作日志service
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月14日
 */
@Service
public class OpLogServiceImpl extends BaseService implements OpLogService {
	
	@Autowired
	OpLogDao opLogDao;

	/**
	 * 创建操作日志
	 */
	@Override
	public R<String> createOpLog(OpLogCreateReq req) {
		OpLogEntity entity=convertBean(req,OpLogEntity.class);
		entity.setCreateTime(DateUtil.getCurTimestamp());
		entity.setId(IdUtil.generateUUID());
		entity.setUpdateTime(DateUtil.getCurTimestamp());
		opLogDao.insert(entity);
		return packResult();
	}

	@Override
	public R<String> modifyOpLog(OpLogModifyReq req) {
		
		return null;
	}

	@Override
	public R<List<OpLogDto>> queryOpLogList(OpLogQueryReq req) {		
	    startPage(req.getPageNum(), req.getPageSize());
		List<OpLogEntity> list = opLogDao.queryOpLogList(req,getPage());
		List<OpLogDto> dtoList = convertBeanList(list, OpLogDto.class);
		return packPageResult(dtoList);
	}

	@Override
	public R<OpLogDto> findOpLogById(OperationByIdReq req) {
		
		return null;
	}

	/**
	 * 导出日志
	 */
	@Override
	public void exportOpLog(OpLogQueryReq req) {
		String fileNmae = "opLog_" + DateUtil.format(new Date(), "yyyyMMdd")+".xls";
		String colParams = "操作时间, opTimeStr, 操作人, opUserName,功能名称, fnName,结果, resultStr,员工编号, opUserNo";
		
		
		
		ExcelExportUtils.write(ServeletUtils.getOutputStream(fileNmae)
				, new IWriteDataFetch<OpLogExcelDto>() {
					@Override
					public List<OpLogExcelDto> fetch(int fetchIndex, int excelRowIndex) {
						startPage(fetchIndex + 1, req.getPageSize());
						List<OpLogExcelDto> list = opLogDao.queryExportOpLogList(req,getPage());
						Assist.forEach(list, dto -> fill(dto));
						return list;
					}
				}
				, colParams);
		
	}
	
	private void fill(OpLogExcelDto dto) {
		if (dto.getOpTime() != null) {
			dto.setOpTimeStr(DateUtil.format(dto.getOpTime(), DateUtil.PATTERN_DATETIME));
		}
		if (BConst.YES.equals(dto.getResult())) {
			dto.setResultStr("成功");
		} else if (BConst.NO.equals(dto.getResult())) {
			dto.setResultStr("失败");
		}
	}
	
}

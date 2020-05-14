package com.ygxc.aqjy.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.dao.OpLogDao;
import com.ygxc.aqjy.framework.base.BaseService;
import com.ygxc.aqjy.req.common.OperationByIdReq;
import com.ygxc.aqjy.req.oplog.OpLogCreateReq;
import com.ygxc.aqjy.req.oplog.OpLogModifyReq;
import com.ygxc.aqjy.req.oplog.OpLogQueryReq;
import com.ygxc.aqjy.rsp.oplog.OpLogDto;
import com.ygxc.aqjy.service.OpLogService;

/**
 * 操作日志service
 * 
 * @author Qiaoxin.Hong
 *
 */
@Service
public class OpLogServiceImpl extends BaseService implements OpLogService {
	
	@Autowired
	OpLogDao opLogDao;

	@Override
	public R<String> createOpLog(OpLogCreateReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<String> modifyOpLog(OpLogModifyReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<List<OpLogDto>> queryOpLogList(OpLogQueryReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<OpLogDto> findOpLogById(OperationByIdReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exportOpLog(OpLogQueryReq req) {
		// TODO Auto-generated method stub
		
	}

	
}

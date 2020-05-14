package com.ygxc.aqjy.framework.base;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ygxc.aqjy.common.constant.RConst;
import com.ygxc.aqjy.common.enumeration.MsgEnum;
import com.ygxc.aqjy.common.exception.YgxcBusinessException;
import com.ygxc.aqjy.common.structure.PageR;
import com.ygxc.aqjy.common.structure.PageRequest;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.common.utils.BeanConvertUtils;






public class BaseService  {

	/**
	 * 日志记录器
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 分页page对象
	 */
	private Page<?> page;
	
	/**
	 * 将类转换为另一个类
	 * @param from
	 * @param toClass
	 * @return
	 */
	protected <T> T convertBean(Object from, Class<T> toClass) {
		return BeanConvertUtils.convertBean(from, toClass);
	}
	
	protected void startPage(int  current, int size) {
		this.page=new Page<Integer>(current, size);
	}
	
	protected Page<?> getPage() {
		return this.page;
	}
	
	/**
	 * 将一个类的list转换为另一个类的list
	 * @param fromList
	 * @param toClass
	 * @return
	 */
	protected <T> List<T> convertBeanList(List<?> fromList, Class<T> toClass) {
		return BeanConvertUtils.convertBeanList(fromList, toClass);
	}
	
	/**
	   *     默认返回成功结果集
	 * @param <T>
	 * @return
	 */
	protected  <T> R<T>   packResult(){
		R<T> r=new R<T>();
		r.setCode(RConst.SUCCESS_CODE);
		r.setInfo(RConst.SUCCESS_INFO);
		return r;
	}
	
	/**
	   *     默认返回成功结果集
	 * @param <T>
	 * @return
	 */
	protected  <T> R<T>   packResult(T data){
		R<T> r=new R<T>();
		r.setCode(RConst.SUCCESS_CODE);
		r.setData(data);
		r.setInfo(RConst.SUCCESS_INFO);
		return r;
	}

	/**
	 * 封装分页结果集
	 * @param list
	 * @return
	 */
	protected <T> PageR<List<T>> packPageResult(Page<?> page, List<T> list) {
		PageR<List<T>> pageR = new PageR<List<T>>();
		if(page!=null) {
			pageR.setCurrent(page.getCurrent());
			pageR.setPageSize(page.getSize());
			pageR.setTotal(page.getTotal());
			pageR.setData(list);
		}else {
			pageR.setCurrent(this.page.getCurrent());
			pageR.setPageSize(this.page.getSize());
			pageR.setTotal(this.page.getTotal());
			pageR.setData(list);
		}
		
		return pageR;
	}
	
	/**
	 * 封装分页结果集
	 * @param list
	 * @return
	 */
	protected <T> PageR<List<T>> packPageResult( List<T> list) {
		PageR<List<T>> pageR = new PageR<List<T>>();
		pageR.setCurrent(this.page.getCurrent());
		pageR.setPageSize(this.page.getSize());
		pageR.setTotal(this.page.getTotal());
		pageR.setData(list);
	
		
		return pageR;
	}

	
	protected <T> PageR<List<T>> packPageResult(Integer total, List<T> list,PageRequest req) {
		PageR<List<T>> pageR = new PageR<List<T>>();
		pageR.setCurrent(req.getCurrent());
		pageR.setPageSize(req.getSize());
		pageR.setTotal(total.longValue());
		pageR.setData(list);
		return pageR;
	}
	
	protected void packPageInfo(PageR<?> PageR, List<?> list) {
		
		Page<?> page=(Page<?>)list;
		PageR.setCurrent(page.getCurrent());
		PageR.setPageSize(page.getSize());
		PageR.setTotal(page.getTotal());
		
	}

	/**
	 * 断定数据库操作的受影响行数，为0时抛出异常
	 * @param count
	 */
	protected void judgeDbHdRow(int count) {
		if (count <= 0)
			throw new YgxcBusinessException(MsgEnum.DB_NOT_FIND_DATA.getDescription());
	}

   /**
    * 结果集成功验证
    * @param <T>
    * @param rest
    */
   protected <T> Boolean resultValidation(R<T> rest) {
	if(rest!=null&&RConst.SUCCESS_CODE==rest.getCode()) {
		return true;
	   }
	else {
		return false;
	}
    }

   /**
    * 结果集成功验证
    * @param <T>
    * @param rest
    */
   protected <T> Boolean resultValidation(PageR<T> rest) {
	if(rest!=null&&RConst.SUCCESS_CODE==rest.getCode()) {
		return true;
	   }
	else {
		return false;
	}
    }
}

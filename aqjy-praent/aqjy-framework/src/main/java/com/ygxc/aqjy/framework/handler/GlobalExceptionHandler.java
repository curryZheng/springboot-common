package com.ygxc.aqjy.framework.handler;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.ygxc.aqjy.common.enumeration.MsgEnum;
import com.ygxc.aqjy.common.exception.YgxcAqjyServiceException;
import com.ygxc.aqjy.common.exception.YgxcBusinessException;
import com.ygxc.aqjy.common.structure.R;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常捕获
 * @author leiZheng
 *
 */
@RestController
@ControllerAdvice
@Order(3)
@Slf4j
public class GlobalExceptionHandler {
	
    /**
     * 安全教育service异常
     * @param ygxcAqjyServiceException
     * @return
     */
	@ExceptionHandler(YgxcAqjyServiceException.class)
    public R<Void> handleAqjxException(YgxcAqjyServiceException ygxcAqjyServiceException) {
	    log.info("error",ygxcAqjyServiceException);
		
		return  R.error(ygxcAqjyServiceException.getMsgEnum());
    }

	/**
	 * 统一基础异常
	 * @param ygxcAqjyServiceException
	 * @return
	 */
	@ExceptionHandler(YgxcBusinessException.class)
    public R<Void> handleAqjxExceptions(YgxcBusinessException ygxcBusinessException) {
	    log.info("error",ygxcBusinessException);
		
		return  R.error(ygxcBusinessException.getMessage());
    }
	
	 /**
     * 其他全局异常在此捕获
     * @param e
     * @return
     */
	@ExceptionHandler(Throwable.class)
    public R<Void> handleException(Throwable e) {
    	 log.info("error",e);
        return R.error(MsgEnum.ABNORMAL_OPERATION);
    
    }

}

package com.ygxc.aqjy.common.exception;

import com.ygxc.aqjy.common.enumeration.MsgEnum;
import com.ygxc.aqjy.common.exception.YgxcBusinessException;

/**
 * 供应链异常
 * @author Lei
 *
 */
public class YgxcAqjyServiceException extends YgxcBusinessException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private MsgEnum msgEnum;
	
	public YgxcAqjyServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}
   
	public YgxcAqjyServiceException(MsgEnum msg ) {
		super(msg.getDescription());
		this.msgEnum=msg;
	}

	public YgxcAqjyServiceException(String message, Throwable cause,
                                 boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public YgxcAqjyServiceException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public YgxcAqjyServiceException(String message,String...msgArgs) {
		super(message);
		
	}

	public YgxcAqjyServiceException(String message) {
		super(message);
		
	}
	
	public YgxcAqjyServiceException(Throwable cause) {
		super(cause);
		
	}

	public MsgEnum getMsgEnum() {
		return msgEnum;
	}

	public void setMsgEnum(MsgEnum msgEnum) {
		this.msgEnum = msgEnum;
	}
	
	
}
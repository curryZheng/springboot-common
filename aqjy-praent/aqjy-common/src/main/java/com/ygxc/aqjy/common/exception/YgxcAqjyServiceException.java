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
		// TODO Auto-generated constructor stub
	}

	public YgxcAqjyServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public YgxcAqjyServiceException(String message,String...msgArgs) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public YgxcAqjyServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public YgxcAqjyServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public MsgEnum getMsgEnum() {
		return msgEnum;
	}

	public void setMsgEnum(MsgEnum msgEnum) {
		this.msgEnum = msgEnum;
	}
	
	
}
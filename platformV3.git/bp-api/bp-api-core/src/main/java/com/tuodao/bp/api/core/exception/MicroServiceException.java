package com.tuodao.bp.api.core.exception;

/**
 * 微服务异常
 * 
 * @author hechuan
 *
 * @created 2017年5月31日
 *
 * @version 1.0.0
 */
public class MicroServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 代码 */
	private int code;

	/** 响应消息 */
	private String msg;

	public MicroServiceException() {
		super();
	}

	public MicroServiceException(int code) {
		this();
		this.code = code;
	}

	public MicroServiceException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public MicroServiceException(int code, String msg, Throwable e) {
		super(msg, e);
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}

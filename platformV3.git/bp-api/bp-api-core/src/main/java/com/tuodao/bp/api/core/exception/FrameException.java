package com.tuodao.bp.api.core.exception;

/**
 * 框架异常
 * 
 * @author hechuan
 *
 * @created 2017年5月25日
 *
 * @version 1.0.0
 */
public class FrameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 代码 */
	private int code;

	/** 响应消息 */
	private String msg;

	public FrameException() {
		super();
	}

	public FrameException(int code) {
		this();
		this.code = code;
	}

	public FrameException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public FrameException(int code, String msg, Throwable e) {
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

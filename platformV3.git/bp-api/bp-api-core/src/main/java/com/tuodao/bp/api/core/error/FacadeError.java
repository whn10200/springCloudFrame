package com.tuodao.bp.api.core.error;

/**
 * 聚合错误
 * 
 * @author hechuan
 *
 * @created 2017年8月4日
 *
 * @version 1.0.0
 */
public class FacadeError {

	/** 错误编号 */
	private String code;

	/** 错误信息 */
	private String msg;

	public static FacadeError builder() {
		return new FacadeError();
	}

	public String getCode() {
		return code;
	}

	public FacadeError setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public FacadeError setMsg(String msg) {
		this.msg = msg;
		return this;
	}

}

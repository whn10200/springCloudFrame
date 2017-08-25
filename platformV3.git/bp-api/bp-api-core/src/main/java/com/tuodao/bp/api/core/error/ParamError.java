package com.tuodao.bp.api.core.error;

import com.google.common.base.MoreObjects;

/**
 * 参数错误检验
 * 
 * @author hechuan
 *
 * @created 2017年6月7日
 *
 * @version 1.0.0
 */
public class ParamError {

	/* 字段名 */
	private String field;

	/** 错误编号 */
	private String code;

	/** 错误信息 */
	private String msg;

	/**
	 * 构造错误信息
	 *
	 * @param code
	 *            错误编号
	 */
	public ParamError(String code) {
		this.code = code;
	}

	/**
	 * 构造错误信息
	 *
	 * @param field
	 *            字段名
	 * @param errorCode
	 *            错误编号
	 */
	public ParamError(String field, String code) {
		this.field = field;
		this.code = code;
	}

	/**
	 * 构造错误信息
	 *
	 * @param field
	 *            字段名
	 * @param code
	 *            错误编号
	 * @param msg
	 *            错误信息
	 */
	public ParamError(String field, String code, String msg) {
		this.field = field;
		this.code = code;
		this.msg = msg;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper("ParamError").add("field", field).add("errorCode", code).add("errorMsg", msg)
				.toString();
	}
}

package com.tuodao.bp.api.core.exception;

import com.tuodao.bp.api.core.constant.FrameConstant;
import com.tuodao.bp.api.core.error.ParamErrors;

/**
 * 参数检验异常
 * 
 * @author hechuan
 *
 * @created 2017年6月8日
 *
 * @version 1.0.0
 */
public class ParamException extends FrameException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 错误代码 */
	private int code = FrameConstant.PARAMETER_CHECK_FAILED;

	/** 错误列表 */
	private ParamErrors errors;

	public ParamException(ParamErrors errors) {
		this.errors = errors;
	}

	public int getCode() {
		return code;
	}

	public ParamErrors getErrors() {
		return errors;
	}
}

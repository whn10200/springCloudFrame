package com.tuodao.bp.api.core.exception;

import java.util.List;

/**
 * 聚合exception
 * @author hechuan
 *
 * @created 2017年8月4日
 *
 * @version 1.0.0
 */
public class FacadeException extends FrameException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 错误代码 */
	private List<Integer> codes;
	
	public FacadeException(List<Integer> codes) {
		this.codes = codes;
	}

	public List<Integer> getCodes() {
		return codes;
	}

	public void setCodes(List<Integer> codes) {
		this.codes = codes;
	}

}

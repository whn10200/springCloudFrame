package com.tuodao.bp.api.core.error;

import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

/**
 * 错误集合
 * 
 * @author hechuan
 *
 * @created 2017年6月7日
 *
 * @version 1.0.0
 */
public class ParamErrors {

	/** 错误信息列表 */
	private List<ParamError> errors = Lists.newArrayList();

	/**
	 * 添加错误信息
	 *
	 * @param field
	 *            字段名
	 * @param errorCode
	 *            错误编号
	 * @param errorMsg
	 *            错误信息
	 * @return 错误对象
	 */
	public ParamErrors addError(String field, String errorCode, String errorMsg) {
		this.errors.add(new ParamError(field, errorCode, errorMsg));
		return this;
	}

	/**
	 * 添加一组错误信息
	 *
	 * @param errors
	 *            错误信息列表
	 * @return 错误对象
	 */
	public ParamErrors addErrors(List<ParamError> errors) {
		this.errors.addAll(errors);
		return this;
	}

	/**
	 * 取得错误信息列表
	 *
	 * @return 错误信息列表
	 */
	public List<ParamError> getErrors() {
		return errors;
	}

	/**
	 * 是否有错误
	 *
	 * @return true:有/false:没有
	 */
	public boolean hasErrors() {
		return this.errors.size() > 0;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper("ParamErrors").add("errors", errors).toString();
	}
}

package com.tuodao.bp.api.core.response;

import com.google.common.base.MoreObjects;
import com.tuodao.bp.api.core.constant.FrameConstant;

/**
 * 返回对象
 * 
 * @author hechuan
 *
 * @created 2017年5月26日
 *
 * @version 1.0.0
 */
public class RespResult<T> {

	/** 代码 */
	private int code = FrameConstant.SUCCESS_CODE;;

	/** 消息 */
	private String msg = FrameConstant.SUCCESS_MESSAGE;

	/** 内容 */
	private T content;
	
	/** 成功true/失败false */
	private boolean success = true;

	public static <T> RespResult<T> create() {
		return new RespResult<T>();
	}

	public RespResult() {
		this.code = FrameConstant.SUCCESS_CODE;
		this.msg = FrameConstant.SUCCESS_MESSAGE;
		this.success = true;
	}

	public RespResult(T content) {
		this();
		this.content = content;
	}

	public int getCode() {
		return code;
	}

	public RespResult<T> setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public RespResult<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public T getContent() {
		return content;
	}

	public RespResult<T> setContent(T content) {
		this.content = content;
		return this;
	}
	

	public boolean isSuccess() {
		return success;
	}

	public RespResult<T> setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues().add("code", code).add("msg", msg)
				.add("content", content).toString();
	}

}

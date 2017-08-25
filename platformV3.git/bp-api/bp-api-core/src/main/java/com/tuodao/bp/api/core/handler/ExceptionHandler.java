package com.tuodao.bp.api.core.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.tuodao.bp.api.core.constant.FrameConstant;
import com.tuodao.bp.api.core.error.FacadeError;
import com.tuodao.bp.api.core.error.ParamError;
import com.tuodao.bp.api.core.exception.FacadeException;
import com.tuodao.bp.api.core.exception.FrameException;
import com.tuodao.bp.api.core.exception.MicroServiceException;
import com.tuodao.bp.api.core.exception.ParamException;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.cache.basic.BasicDataDao;
import com.tuodao.bp.db.model.basic.SystemBasicData;

/**
 * 异常收集
 * 
 * @author hechuan
 *
 * @created 2017年5月31日
 *
 * @version 1.0.0
 * 
 */
@ControllerAdvice
public class ExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	@Autowired
	private BasicDataDao basicDataDao;

	
	/**
	 * 入口参数异常
	 *
	 * @param request
	 *            请求体
	 * @param response
	 *            回复体
	 * @param handler
	 *            发生异常的类
	 * @param ex
	 *            具体异常
	 * @return 处理结果
	 */
	@ResponseBody
	@org.springframework.web.bind.annotation.ExceptionHandler(value = ParamException.class)
	public RespResult<List<ParamError>> resolveParamException(HttpServletRequest request, HttpServletResponse response,
			Object handler, ParamException ex) {
		
		RespResult<List<ParamError>> result = new RespResult<List<ParamError>>();
		
		
		List<ParamError> list = FluentIterable.from(ex.getErrors().getErrors()).transform(new Function<ParamError, ParamError>() {
			@Override
			public ParamError apply(ParamError input) {
				input.setMsg(getRespMessage(input.getMsg(),Integer.parseInt(input.getCode())));
				return input;
			}
		}).toList();
		
		int code = ex.getCode();
		String msg = getRespMessage(ex.getMsg(),code);
		logger.info("参数异常..code = {},msg = {},list = {}", code, msg,list);
		return result.setCode(code).setMsg(msg).setContent(list).setSuccess(false);
	}
	
	/**
	 * 微服务异常
	 *
	 * @param request
	 *            请求体
	 * @param response
	 *            回复体
	 * @param handler
	 *            发生异常的类
	 * @param ex
	 *            具体异常
	 * @return 处理结果
	 */
	@ResponseBody
	@org.springframework.web.bind.annotation.ExceptionHandler(value = MicroServiceException.class)
	public RespResult<String> resolveMicroException(HttpServletRequest request, HttpServletResponse response,
			Object handler, MicroServiceException ex) {
		int code = ex.getCode();
		String msg = getRespMessage(ex.getMsg(),code);
		logger.info("微服务异常..code = {},msg = {}", code, msg);
		return RespResult.<String> create().setCode(code).setMsg(msg).setSuccess(false);
	}


	/**
	 * 框架异常
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@org.springframework.web.bind.annotation.ExceptionHandler(value = FrameException.class)
	public RespResult<String> resolveFrameException(HttpServletRequest request, HttpServletResponse response,
			Object handler, FrameException ex) {
		int code = ex.getCode();
		String msg = getRespMessage(ex.getMsg(),code);
		logger.info("框架异常..code = {},msg = {}", code, msg);
		return RespResult.<String> create().setCode(code).setMsg(msg).setSuccess(false);
	}

	@ResponseBody
	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
	public RespResult<String> resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		int code = FrameConstant.SYSTEM_EXCEPTION_CODE;
		String msg = getRespMessage(FrameConstant.SYSTEM_EXCEPTION_MSG,code);
		logger.info("系统异常..code = {},msg = {},exception.msg = {}\n", code, msg, ex.getMessage(), ex);
		return RespResult.<String> create().setCode(code).setMsg(msg).setSuccess(false);
	}
	
	/**
	 * 聚合异常
	 *
	 * @param request
	 *            请求体
	 * @param response
	 *            回复体
	 * @param handler
	 *            发生异常的类
	 * @param ex
	 *            具体异常
	 * @return 处理结果
	 */
	@ResponseBody
	@org.springframework.web.bind.annotation.ExceptionHandler(value = FacadeException.class)
	public RespResult<List<FacadeError>> resolveParamException(HttpServletRequest request, HttpServletResponse response,
			Object handler, FacadeException ex) {
		
		RespResult<List<FacadeError>> result = new RespResult<List<FacadeError>>();
		
		
		List<FacadeError> list = FluentIterable.from(ex.getCodes()).transform(new Function<Integer, FacadeError>() {
			@Override
			public FacadeError apply(Integer input) {
				return FacadeError.builder().setCode(String.valueOf(input)).setMsg(getRespMessage(null, input.intValue()));
			}
		}).toList();
		
		int code = FrameConstant.FACADE_REQUEST_ERROR_CODE;
		String msg = FrameConstant.FACADE_REQUEST_EXCEPTION_MSG;
		logger.info("参数异常..code = {},msg = {},list = {}", code, msg,list);
		return result.setCode(code).setMsg(msg).setContent(list).setSuccess(false);
	}
	
	/**
	 * 获取异常返回信息
	 * @param msg exception 中返回的消息
	 * @param code exception 中返回的code
	 * @return
	 */
	private String getRespMessage(String msg, int code) {
		SystemBasicData business = basicDataDao.getBusiness(String.valueOf(code));
		String value = business.getConfValue();
		if (StringUtils.isNotBlank(value)) {
			return value;
		}
		return msg;
	}
}

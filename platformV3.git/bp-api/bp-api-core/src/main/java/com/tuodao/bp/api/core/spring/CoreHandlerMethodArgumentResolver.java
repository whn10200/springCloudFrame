package com.tuodao.bp.api.core.spring;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.databind.ObjectReader;
import com.tuodao.bp.api.core.constant.RequestConstant;
import com.tuodao.bp.api.core.error.ParamError;
import com.tuodao.bp.api.core.error.ParamErrors;
import com.tuodao.bp.api.core.exception.ParamException;

/**
 * 方法拦截(参数检查，判断)
 * 
 * @author hechuan
 *
 * @created 2017年5月24日
 *
 * @version 1.0.0
 */
public class CoreHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	private static final Logger logger = LoggerFactory.getLogger(CoreHandlerMethodArgumentResolver.class);

	/**
	 * 错误列表
	 */
	private ParamErrors errors;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		return true;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

		Class<?> clazz = parameter.getParameterType();
		logger.info("clazz : {}", clazz);

		String requestJson = (String) request.getAttribute(RequestConstant.REQUEST_CONTENT);
		String format = (String) request.getAttribute(RequestConstant.FORMAT);
		Object input = convertToInput(requestJson, clazz, format);

		// 校验参数
		final WebDataBinder binder = binderFactory.createBinder(webRequest, input, clazz.getName());
		binder.validate(input);
		BindingResult result = binder.getBindingResult();
		// 将错误信息转换成error
		List<ParamError> errorList = result.getAllErrors().stream().map(paramError -> {
			logger.info("inputCode:{},defaultMessage:{},objectName:{}", paramError.getCode(),
					paramError.getDefaultMessage(), paramError.getObjectName());
			if (paramError instanceof FieldError) {
				FieldError fe = (FieldError) paramError;
				return new ParamError(fe.getField(), fe.getDefaultMessage());
			} else {
				return new ParamError(paramError.getDefaultMessage());
			}
		}).collect(Collectors.toList());
		// 如果有错误信息
		if (errorList != null && errorList.size() > 0) {
			this.errors = new ParamErrors().addErrors(errorList);
			logger.debug("校验结果: {}", errors);
			throw new ParamException(this.errors);
		}
		// 返回序列化后参数
		return input;
	}

	/**
	 * 将接收的请求体，解析成controller的对象
	 *
	 * @param requestJson
	 *            请求体内容
	 * @param clazz
	 *            具体controller的参数类型
	 * @param format
	 *            解析类型
	 * @param <T>
	 *            具体controller的参数类型泛型
	 * @return 反序列化后实体
	 */
	private <T> T convertToInput(String requestJson, Class<T> clazz, String format) {

		ObjectReader objectReader = getReader(format);

		try {
			return objectReader.forType(clazz).readValue(requestJson);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return null;
		}

	}

	/**
	 * 通过format，获取解析对象是json还是xml
	 * 
	 * @param format
	 *            json|xml
	 * @return
	 */
	private ObjectReader getReader(String format) {
		ObjectReader objectReader = null;
		if (RequestConstant.FORMATE_JSON.equalsIgnoreCase(format)) {
			objectReader = Jackson2ObjectMapperBuilder.json().build().reader();
		} else if (RequestConstant.FORMATE_XML.equalsIgnoreCase(format)) {
			objectReader = Jackson2ObjectMapperBuilder.xml().build().reader();
		}
		return objectReader;
	}

}

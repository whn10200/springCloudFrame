package com.tuodao.bp.api.core.spring;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.UrlPathHelper;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.tuodao.bp.api.core.constant.RequestConstant;
import com.tuodao.bp.api.core.constant.ResponseConstant;
import com.tuodao.bp.api.core.exception.FrameException;

/**
 * 拦截器
 * 
 * @author hechuan
 *
 * @created 2017年5月25日
 *
 * @version 1.0.0
 */
public class CoreHandlerInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(CoreHandlerInterceptor.class);

	private static final String ENCODING = "utf-8";
	
	private static final String POST = "POST";
	
	private static final String SUCCESS = "/api/router";

	private static final ThreadLocal<Boolean> validate = new ThreadLocal<Boolean>() {
		protected Boolean initialValue() {
			return Boolean.TRUE;
		};
	};

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.info("请求开始...");
		
		// 必须从zuul api gateway 过来的请求才是正常请求
		String header = request.getHeader("x-forwarded-prefix");

		if (StringUtils.isBlank(header)) {
			logger.warn("不是从zuul请求过来，非法请求，不予响应..,header : {}", header);
			throw new FrameException(ResponseConstant.HEADER_ERROR);
		}

		if (!checkHeader(header)) {
			logger.info("请求头检查不合法..,header : {},it's must equals : {}", header, SUCCESS);
			throw new FrameException(ResponseConstant.HEADER_ERROR);
		}

		String methodName = request.getMethod();
		logger.info("请求方式：{}", methodName);
		
		if(!POST.equals(methodName)) {
			throw new FrameException(ResponseConstant.POST_SUPPORT);
		}
		
		String format = request.getHeader(RequestConstant.FORMAT);
		if(StringUtils.isBlank(format)) {
			UrlPathHelper urlPathHelper = new UrlPathHelper();
            String path = urlPathHelper.getLookupPathForRequest(request);
            String extension = UriUtils.extractFileExtension(path);
            if (StringUtils.isEmpty(extension)) {
            	format = RequestConstant.DEFAULT_FORMAT;
            }
            logger.info("request path : {}",path);
            request.setAttribute(RequestConstant.FORMAT, format);
        }
		logger.info("format:{}", format);

		String content = IOUtils.toString(request.getInputStream(), ENCODING);
		if (StringUtils.isBlank(content)) {
			Map<String, Object> map = Maps.newHashMap();
			Enumeration<String> pns = request.getParameterNames();
			while (pns.hasMoreElements()) {
				String name = pns.nextElement();
				String value = request.getParameter(name);
				map.put(name, value);
			}
			validate.set(Boolean.FALSE);
			content = JSON.toJSONString(map);
			// 正则表达式
			content = content.replaceAll("\"\\[", "[").replaceAll("]\"", "]").replaceAll("\\\\", "");
		}
		logger.info("请求体内容：{}", content);

		request.setAttribute(RequestConstant.REQUEST_CONTENT, content);

		return true;
	}
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("handler:{}",handler);
		super.afterCompletion(request, response, handler, ex);
	}
	
	public static boolean getContext() {
		return validate.get().booleanValue();
	}
	
	/**
	 * 请求头部检查
	 * 
	 * @param header
	 *            请求头
	 * @return true|false
	 */
	private boolean checkHeader(String header) {

		return StringUtils.equals(SUCCESS, header);
	}

}

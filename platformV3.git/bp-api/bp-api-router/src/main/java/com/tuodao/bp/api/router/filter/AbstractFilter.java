package com.tuodao.bp.api.router.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 过滤器
 * 
 * @author hechuan
 *
 * @created 2017年5月24日
 *
 * @version 1.0.0
 */
public abstract class AbstractFilter extends ZuulFilter {

	protected static final Logger logger = LoggerFactory.getLogger(AbstractFilter.class);

	protected static final String SUCCESS = "SUCCESS";
	
	protected static final String FAILED = "FAILED";
	
	protected static final int HTTP_SUCCESS_CODE = 200;

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = getCurCxt();
		Object object = ctx.get(SUCCESS);
		if(null == object || object.equals(SUCCESS)) {
			return true;
		}
		return false;
	}

	/**
	 * 当前上下文
	 * 
	 * @return
	 */
	protected RequestContext getCurCxt() {
		RequestContext currentContext = RequestContext.getCurrentContext();
		return currentContext;
	}

	/**
	 * request
	 * 
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return getCurCxt().getRequest();
	}

	/**
	 * response
	 * 
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		return getCurCxt().getResponse();
	}

}

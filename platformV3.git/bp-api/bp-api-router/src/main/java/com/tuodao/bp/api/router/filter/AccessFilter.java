package com.tuodao.bp.api.router.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.context.RequestContext;

/**
 * 访问过滤
 * 
 * @author hechuan
 *
 * @created 2017年5月24日
 *
 * @version 1.0.0
 */
public class AccessFilter extends AbstractFilter {
	
	@Override
	public Object run() {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		String method = request.getMethod();
		logger.info("{} request to {}", method, request.getRequestURL().toString());
		
		String acId = request.getHeader("accessId");
		String acKey = request.getHeader("accessKey");
		if(StringUtils.isBlank(acId) || StringUtils.isBlank(acKey)) {
			logger.info("accessid {},accessKey {} not right ",acId,acKey);
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(FilterOrder.ACCESS_NOT_ALLOWED);
			ctx.set(SUCCESS, FAILED);
		}else {
			logger.info("accessid {},accessKey {} validate success ",acId,acKey);
			ctx.setSendZuulResponse(true);
			ctx.setResponseStatusCode(HTTP_SUCCESS_CODE);
			ctx.set(SUCCESS, SUCCESS);
		}
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return FilterOrder.ACCESS_FILTER;
	}

}

package com.tuodao.bp.api.router.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.FluentIterable;
import com.netflix.zuul.context.RequestContext;
import com.tuodao.bp.api.core.exception.FrameException;
import com.tuodao.bp.cache.basic.BasicDataDao;
import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.db.model.basic.SystemBasicData;

/**
 * ip 白名单 filter
 * 
 * @author hechuan
 *
 * @created 2017年5月24日
 *
 * @version 1.0.0
 */
public class IpFilter extends AbstractFilter {

	@Autowired
	private BasicDataDao basicDataDao;
	
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		logger.info("{} request to {}", request.getMethod(), request.getRequestURL().toString());
		
		String clientIP = getClientIP(request);
		logger.info("clientIp {}",clientIP);
				
		SystemBasicData iplimit = basicDataDao.getIplimit(RedisConstans.BASIC_DATA_MODULE_IPLIMIT);
		if(null==iplimit) {
			logger.info("no ip limit");
			ctx.setSendZuulResponse(true);
			ctx.setResponseStatusCode(HTTP_SUCCESS_CODE);
			ctx.set(SUCCESS, SUCCESS);
			return null;
		}
		
		List<String> ipCollection = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(iplimit.getConfValue());
		
		boolean match = FluentIterable.from(ipCollection).anyMatch(new Predicate<String>() {
			@Override
			public boolean apply(String input) {
					return StringUtils.equals(clientIP, input);
			}
		});

		if(match) {
			logger.info("ip：{} validate success ",clientIP);
			ctx.setSendZuulResponse(true);
			ctx.setResponseStatusCode(HTTP_SUCCESS_CODE);
			ctx.set(SUCCESS, SUCCESS);
		}else {
			logger.info("ip：{} not allowed to visit this server",clientIP);
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(FilterOrder.IP_NOT_ALLOWED);
			ctx.set(SUCCESS, FAILED);
			throw new FrameException(200010);
		}
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return FilterOrder.IP_FILTER;
	}
	
	/**
	 * 获取客户端真实ip地址。
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return ip地址
	 */
	public String getClientIP(HttpServletRequest request) {
		String UNKNOWN = "unknown";
		String ip = request.getHeader("x-forwarded-for");
		if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
					ip = request.getRemoteAddr();
				}
			}
			return ip;
		}

		String[] ips = ip.split(",");
		if (ips.length > 1) {
			String tempIP = StringUtils.EMPTY;
			for (int i = 0; i < ips.length; i++) {
				tempIP = Optional.fromNullable(ips[i].trim()).or(StringUtils.EMPTY);
				if (!Strings.isNullOrEmpty(tempIP) && UNKNOWN.equalsIgnoreCase(tempIP)) {
					ip = tempIP;
					break;
				}
			}
		}

		return ip;
	}

}

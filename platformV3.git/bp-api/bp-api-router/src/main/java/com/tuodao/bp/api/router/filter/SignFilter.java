package com.tuodao.bp.api.router.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.context.RequestContext;
import com.tuodao.bp.api.router.encrypt.SignEncrypt;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

/**
 * 签名过滤
 *
 * @author hechuan
 * @version 1.0.0
 * @created 2017年5月26日
 */
public class SignFilter extends AbstractFilter {

    private static final Logger logger = LoggerFactory.getLogger(SignFilter.class);

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String sign = request.getHeader("sign");
        String timestamp = request.getHeader("timestamp");
        String accessId = request.getHeader("accessId");
        String accessKey = request.getHeader("accessKey");
        logger.info("sign = {},timestamp = {},accessId = {},accessKey = {}", sign, timestamp, accessId,accessKey);

        String calcSign = SignEncrypt.getDefaultSign(accessId + timestamp + accessKey);
        logger.info("calcSign = {}", calcSign);
        if ("NO".equals(sign) || sign.equals(calcSign)) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(HTTP_SUCCESS_CODE);
            ctx.set(SUCCESS, SUCCESS);
            logger.info("sign equals calcSign,sign = {},calcSign = {}", sign, calcSign);
        } else {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(FilterOrder.SIGN_NOT_ALLOWED);
            ctx.set(SUCCESS, FAILED);
            logger.info("sign not the same,sign = {},calcSign = {}", sign, calcSign);
        }

        return null;
    }

    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterOrder.SIGN_FILTER;
    }


}

package com.tuodao.bp.api.core.spring;

import java.util.List;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.tuodao.bp.api.core.constant.RequestConstant;
import com.tuodao.bp.api.core.handler.ExceptionHandler;
import com.tuodao.bp.cache.basic.BasicDataDao;

@Configuration
public class CoreWebConfiguration extends WebMvcConfigurerAdapter {

	// 用于处理编码问题
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding(RequestConstant.ENCODING);
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	@Bean
	public CoreHandlerInterceptor coreHandlerInterceptor() {
		return new CoreHandlerInterceptor();
	}

	@Bean
	public CoreHandlerMethodArgumentResolver coreHandlerMethodArgumentResolver() {
		return new CoreHandlerMethodArgumentResolver();
	}
	
	@Bean
	public BasicDataDao baseDataDao() {
		return new BasicDataDao();
	}
	
	@Bean
	public ExceptionHandler exceptionHandler() {
		return new ExceptionHandler();
	}

	/**
	 * 拦截器添加
	 *
	 * @param registry
	 *            拦截器注册
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(coreHandlerInterceptor());
	}

	/**
	 * controller 参数解析
	 *
	 * @param argumentResolvers
	 *            参数解析列表
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(coreHandlerMethodArgumentResolver());
	}

}

package com.tuodao.bp.api.router.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tuodao.bp.api.router.filter.AccessFilter;
import com.tuodao.bp.api.router.filter.IpFilter;
import com.tuodao.bp.api.router.filter.SignFilter;
import com.tuodao.bp.cache.basic.BasicDataDao;

/**
 * 过滤器集中配置
 * 
 * @author hechuan
 *
 * @created 2017年5月24日
 *
 * @version 1.0.0
 */
@Configuration
public class FilterConfig {

	@Bean
	public BasicDataDao basicDataDao() {
		return new BasicDataDao();
	}
	
	@Bean
	public IpFilter ipFilter() {
		return new IpFilter();
	}
	
	@Bean
	public AccessFilter acFilter() {
		return new AccessFilter();
	}
	
	@Bean
	public SignFilter signFilter() {
		return new SignFilter();
	}
}
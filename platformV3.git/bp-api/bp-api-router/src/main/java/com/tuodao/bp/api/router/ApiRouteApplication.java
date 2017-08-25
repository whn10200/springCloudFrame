package com.tuodao.bp.api.router;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * API ROUTER GATE WAY SERVICE
 * 
 * @author HECHUAN
 * 
 * @created 2017年5月22日
 *
 * @version 1.0.0
 *
 * @since 1.0.0
 * 
 * @see http://projects.spring.io/spring-cloud/spring-cloud.html#_router_and_filter_zuul
 *      中文翻译：http://blog.csdn.net/luozhonghua2014/article/details/53846034
 */
@SpringCloudApplication
@EnableZuulProxy
public class ApiRouteApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiRouteApplication.class, args);
	}
}

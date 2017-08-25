package com.tuodao.bp.eureka;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 
 * @author HECHUAN
 * 
 * @created 2017年5月17日
 *
 * @version 1.0.0
 *
 * @since 1.0.0
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaServer.class).bannerMode(Mode.OFF).web(true).run(args);
	}
	
	
}

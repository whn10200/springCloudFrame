package com.tuodao.bp.control;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.config.EnableAdminServer;

/**
 * ADMIN SERVER APPLICATION
 * 
 * @author hechuan
 *
 * @created 2017年5月22日
 *
 * @version 1.0.0
 */
@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class AdminServerApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AdminServerApplication.class).bannerMode(Mode.OFF).web(true).run(args);
	}
}

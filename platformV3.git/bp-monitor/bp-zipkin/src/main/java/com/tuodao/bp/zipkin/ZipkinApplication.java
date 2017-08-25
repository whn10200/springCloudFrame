package com.tuodao.bp.zipkin;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import zipkin.server.EnableZipkinServer;

/**
 * 链路监控
 * 
 * @author HECHUAN
 * 
 * @created 2017年5月18日
 *
 * @version 1.0.0
 *
 * @since 1.0.0
 */
@EnableZipkinServer
@SpringBootApplication
public class ZipkinApplication {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(ZipkinApplication.class).bannerMode(Mode.OFF).web(true).run(args);
	}
}

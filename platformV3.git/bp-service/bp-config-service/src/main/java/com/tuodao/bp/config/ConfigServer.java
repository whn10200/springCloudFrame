package com.tuodao.bp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 配置服务中心
 * 
 * @author HECHUAN
 * 
 * @created 2017年5月17日
 *
 * @version 1.0.0
 *
 * @since 1.0.0
 */
@EnableConfigServer
@SpringBootApplication
@RefreshScope
@MapperScan(value= {"com.tuodao.bp.db"})
public class ConfigServer {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigServer.class).bannerMode(Mode.OFF).web(true).run(args);
	}
	
}

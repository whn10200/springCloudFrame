package com.tuodao.bp.db.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @description: 数据源配置
 * @author: mif
 * @date: 2017/7/14
 * @time: 09:31
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.tuodao.bp.db.inteceptor")
public class DataSourceConfiguration {

    @Value("${hikari.type}")
    private Class<? extends DataSource> dataSourceType;

    /**
     * Master
     *
     * @return
     */
    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "hikari.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * Slave1
     *
     * @return
     */
    @Bean(name = "slave1DataSource")
    @ConfigurationProperties(prefix = "hikari.slave1")
    public DataSource slave1DataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * slave2
     *
     * @return
     */
    @Bean(name = "slave2DataSource")
    @ConfigurationProperties(prefix = "hikari.slave2")
    public DataSource slave2DataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

}

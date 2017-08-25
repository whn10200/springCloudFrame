package com.tuodao.bp.db.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @description: 读写分离路由
 * @author: mif
 * @date: 2017/7/14
 * @time: 09:38
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class ReadWriteSplitRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDbType();
    }
}

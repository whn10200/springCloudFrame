package com.tuodao.bp.db.datasource;

import java.util.Random;

/**
 * @description: 数据源预处理
 * @author: mif
 * @date: 2017/7/14
 * @time: 09:39
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class DataSourceContextHolder {

    public enum DbType {
        MASTER, SLAVE1, SLAVE2
    }

    private static final ThreadLocal<DbType> contextHolder = new ThreadLocal<>();

    public static void setDbType(DbType dbType) {
        if (dbType == null) throw new NullPointerException();
        contextHolder.set(dbType);
    }

    public static DbType getDbType() {
        return contextHolder.get() == null ? DbType.MASTER : contextHolder.get();
    }

    public static void clearDbType() {
        contextHolder.remove();
    }

    private static final Random random = new Random();

    public static DbType getSlaveRandom() {
        DbType[] dbTypes = new DbType[]{DbType.SLAVE1, DbType.SLAVE2};
        return dbTypes[random.nextInt(dbTypes.length)];
    }

}

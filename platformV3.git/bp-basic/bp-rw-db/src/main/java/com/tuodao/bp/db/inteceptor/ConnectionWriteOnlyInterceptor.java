package com.tuodao.bp.db.inteceptor;

import com.tuodao.bp.db.datasource.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @description: 只写操作拦截器
 * @author: mif
 * @date: 2017/7/14
 * @time: 09:52
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Aspect
@Component
@Order(0)
public class ConnectionWriteOnlyInterceptor {

    static final Logger logger = LoggerFactory.getLogger(ConnectionWriteOnlyInterceptor.class);

    @Pointcut("execution(* com.tuodao.bp..*Mapper.update*(..))")
    private void updateOpt() {
    }

    @Pointcut("execution(* com.tuodao.bp..*Mapper.insert*(..))")
    private void insertOpt() {
    }

    @Pointcut("execution(* com.tuodao.bp..*Mapper.delete*(..))")
    private void deleteOpt() {
    }

    /**
     * 只写操作
     *
     *
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("updateOpt() or insertOpt() or deleteOpt()")
    public Object writeOnlyProceed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            logger.info("set database connection to write only");
            DataSourceContextHolder.setDbType(DataSourceContextHolder.DbType.MASTER);
            return proceedingJoinPoint.proceed();
        } finally {
            DataSourceContextHolder.clearDbType();
            logger.info("restore database connection");
        }
    }
}

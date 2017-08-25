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
 * @description: 只读连接拦截器
 * @author: mif
 * @date: 2017/7/14
 * @time: 09:49
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Aspect
@Component
@Order(0)
public class ConnetionReadOnlyInterceptor {

    static final Logger logger = LoggerFactory.getLogger(ConnetionReadOnlyInterceptor.class);

    @Pointcut("execution(* com.tuodao.bp..*Mapper.count*(..))")
    private void countOpt() {
    }

    @Pointcut("execution(* com.tuodao.bp..*Mapper.select*(..))")
    private void selectOpt() {
    }

    /**
     * 只读操作
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("countOpt() || selectOpt()")
    public Object readOnlyProceed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            logger.info("set database connection to read only");
            DataSourceContextHolder.DbType dbType = DataSourceContextHolder.getSlaveRandom();
            logger.info(dbType.name());
            DataSourceContextHolder.setDbType(dbType);
            return proceedingJoinPoint.proceed();
        } finally {
            DataSourceContextHolder.clearDbType();
            logger.info("restore database connection");
        }
    }
}

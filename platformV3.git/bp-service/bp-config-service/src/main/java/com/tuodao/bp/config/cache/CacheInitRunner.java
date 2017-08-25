package com.tuodao.bp.config.cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.tuodao.bp.cache.basic.BasicDataDao;
import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.db.mapper.basic.SystemBasicDataMapper;
import com.tuodao.bp.db.model.basic.SystemBasicData;
import com.tuodao.bp.db.model.basic.SystemBasicDataExample;



/**
 * 缓存初始化
 * 
 * @author hechuan
 *
 * @created 2017年8月4日
 *
 * @version 1.0.0
 */
@Component
public class CacheInitRunner implements CommandLineRunner {

	/** 日志 */
	private static Logger logger = LoggerFactory.getLogger(CacheInitRunner.class);

	@Autowired
	private BasicDataDao basicDataDao;

	@Autowired
	private SystemBasicDataMapper systemBasicDataMapper;

	@Override
	public void run(String... args) throws Exception {
		logger.info("basic data of query begin............");

		SystemBasicDataExample example = new SystemBasicDataExample();
		List<SystemBasicData> basicDataList = systemBasicDataMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(basicDataList)) {
			logger.warn("basic data init over beacuase the basicDataList is Empty.....");
			return;
		}

		logger.info("basic data of cache init begin...");
		for (SystemBasicData data : basicDataList) {
			
			if (RedisConstans.BASIC_DATA_MODULE_BUSINESS.equals(data.getModule())) {
				basicDataDao.putBusiness(data);
			}
			if (RedisConstans.BASIC_DATA_MODULE_IPLIMIT.equals(data.getModule())) {
				basicDataDao.putIplimit(data);
			}

		}

		logger.info("basic data of cache init end...");

	}

}

package com.tuodao.bp.cache.basic;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.db.model.basic.SystemBasicData;

/**
 * 基础数据缓存信息
 * 
 * @author hechuan
 *
 * @created 2017年5月31日
 *
 * @version 1.0.0
 */
@Component
public class BasicDataDao {

	/**
	 * 业务异常数据加入到redis中
	 *
	 * @param systemBasicData
	 */
	@CachePut(cacheNames = RedisConstans.CACHE_NAME_BASIC_DATA_BUSINESS, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_BASIC_DATA_BUSINESS +#p0.confKey")
	public SystemBasicData putBusiness(SystemBasicData systemBasicData) {
		return systemBasicData;
	}

	/**
	 * IP白名单加入到redis中
	 *
	 * @param systemBasicData
	 */
	@CachePut(cacheNames = RedisConstans.CACHE_NAME_BASIC_DATA_IPLIMIT, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_BASIC_DATA_IPLIMIT +#p0.confKey")
	public SystemBasicData putIplimit(SystemBasicData systemBasicData) {
		return systemBasicData;
	}

	/**
	 * 获取redis中业务异常数据
	 *
	 * @param confKey
	 * @return
	 */
	@Cacheable(cacheNames = RedisConstans.CACHE_NAME_BASIC_DATA_BUSINESS, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_BASIC_DATA_BUSINESS +#p0")
	public SystemBasicData getBusiness(String confKey) {
		return new SystemBasicData();
	}

	/**
	 * 获取redis中IP白名单
	 *
	 * @param confKey
	 * @return
	 */
	@Cacheable(cacheNames = RedisConstans.CACHE_NAME_BASIC_DATA_IPLIMIT, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_BASIC_DATA_IPLIMIT +#p0")
	public SystemBasicData getIplimit(String confKey) {
		return new SystemBasicData();
	}
}

package com.tuodao.bp.api.router.filter;

/**
 * filter 顺序
 * 
 * 从小到大，越小越先执行
 * 
 * @author hechuan
 *
 * @created 2017年5月24日
 *
 * @version 1.0.0
 */
public class FilterOrder {

	/** ip 白名单过滤 */
	public static final int IP_FILTER = 1;
	
	public static final int IP_NOT_ALLOWED= 100100;
	
	/** accessId,key过滤 */
	public static final int ACCESS_FILTER = 2;
	
	public static final int ACCESS_NOT_ALLOWED= 100101;
	
	/** sign过滤 */
	public static final int SIGN_FILTER = 3;
	
	public static final int SIGN_NOT_ALLOWED= 100102;
}

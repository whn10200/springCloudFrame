package com.tuodao.bp.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 替换工具类
 * 
 * @author hc
 *
 * @created on 2017年6月12日
 * 
 * @version 1.0.0
 */
public final class ReplaceUtils {

	/**
	 * 得到显示的手机号码
	 * 
	 * @param mobile
	 *            手机号
	 * @return
	 */
	public static String getViewMobile(String mobile) {
		if(StringUtils.isBlank(mobile) || mobile.length() !=11) {
			return mobile;
		}
		return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
	}

	/**
	 * 得到显示的用户名
	 * 
	 * @param userName
	 *            真实姓名
	 * @return
	 */
	public static String getViewName(String userName) {
		String viewName = "";
		if (StringUtils.isBlank(userName)) {
			return viewName;
		}

		int length = userName.length();
		switch (length) {
		case 2:
			viewName = userName.replaceAll("(\\D{1})\\D{1}", "$1*");
			break;
		default:
			int len = length - 2;
			String rex = "D{" + len + "}";
			String fix = "";
			for (int i = 0; i < len; i++) {
				fix += "*";
			}
			viewName = userName.replaceAll("(\\D{1})\\" + rex + "(\\D{1})", "$1" + fix + "$2");
			break;
		}
		return viewName;
	}

	public static void main(String[] args) {
		System.out.println(getViewMobile("13965232589"));
		System.out.println(getViewName("张三"));
		System.out.println(getViewName("张三丰"));
		System.out.println(getViewName("张三丰厉害了"));
		System.out.println(getViewName("奥巴马"));
	}
}

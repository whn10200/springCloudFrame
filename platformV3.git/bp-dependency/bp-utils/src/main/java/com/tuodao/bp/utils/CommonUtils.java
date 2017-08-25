package com.tuodao.bp.utils;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

public class CommonUtils {
	private static SecureRandom secureRand = new SecureRandom();
	private static Random rand = new Random(secureRand.nextLong());
	private static String localhost = getLocalHost();

	public static String getLocalHost() {
		try {
			return InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String byte2hex(byte[] b) {

		String str = "";
		String stmp = "";

		int length = b.length;

		for (int n = 0; n < length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				str += "0";
			}
			str += stmp;
		}

		return str.toLowerCase();
	}

	public static String convertCtrlNAndBlankToHtml(String text) {
		if (StringUtils.isNotBlank(text)) {
			text = text.replace("\n", "<br/>");
		}
		return text;
	}

	public static String getRandom(String string) {

		String str = MD5Utils.md5(
				getUUID() + System.currentTimeMillis() + getRandom(999999999) + rand.nextLong() + localhost + string);
		str = str.toLowerCase();

		return str;
	}

	public static int getRandom(int accuracy) {

		return (int) (Math.random() * accuracy);
	}

	public static long getRandom(long accuracy) {

		return (long) (Math.random() * accuracy);
	}

	public static int getWordLength(String str) {

		str = StringUtils.defaultString(str, "");
		return str.replaceAll("[^\\x00-\\xff]", "**").length();
	}

	public static String nullToStrTrim(String str) {

		if (str == null) {
			str = "";
		}

		return str.trim();
	}

	public static boolean isHalfAngle(String str) {

		str = nullToStrTrim(str);
		return str.length() == getWordLength(str);
	}

	public static int nullToIntZero(String str) {
		if (nullToStrTrim(str).equals("")) {
			str = "0";
		}
		return Integer.valueOf(str, 10);
	}

	public static Integer nullToIntegerZero(String str) {
		if (nullToStrTrim(str).equals("")) {
			str = "0";
		}
		return Integer.valueOf(str, 10);
	}

	public static int getRealLength(String str, String charsetName) {
		if (StringUtils.isBlank(str)) {
			return 0;
		}
		try {
			return str.getBytes(charsetName).length;
		} catch (UnsupportedEncodingException e) {
			return 0;
		}
	}

	/**
	 * 简单检查一下是否是MD5值
	 *
	 * @param md5
	 * @return
	 */
	public static boolean checkMd5(String md5) {
		if (md5.length() != 32) {
			return false;
		}
		return md5.matches("[0-9A-Fa-f]+");
	}

	public static long nullToLongZero(String str) {

		if (str == null || str.trim().length() == 0) {
			str = "0";
		}

		return Long.valueOf(str.trim(), 10);
	}

	/**
	 * 生成随机验证码
	 *
	 * @param randstring
	 * @param length
	 * @return
	 */
	public static String getRandom(String randstring, int length) {

		String rand = "";

		Random random = new Random();
		String c = "";
		for (int i = 1; i <= length; i++) {
			c = String.valueOf(randstring.charAt(random.nextInt(randstring.length())));
			rand += c;
		}

		return rand;
	}

	public static double nullToDoubleZero(String str) {
		if (StringUtils.isBlank(str)) {
			str = "0";
		}
		return Double.valueOf(str.trim()).doubleValue();
	}

	/**
	 * 计算百分比
	 *
	 * @param numerator
	 *            分子
	 * @param denominator
	 *            分母
	 * @return
	 */
	public static String percentage(String numerator, String denominator) {
		if (StringUtils.isEmpty(numerator) || StringUtils.isEmpty(denominator)) {
			return "";
		}

		double percent = nullToDoubleZero(numerator) / nullToDoubleZero(denominator);
		// 获取格式化对象
		NumberFormat nt = NumberFormat.getPercentInstance();
		// 设置百分数精确度2即保留两位小数
		nt.setMinimumFractionDigits(2);

		return nt.format(percent);
	}

	/**
	 * 求平均值
	 *
	 * @param numerator
	 *            分子
	 * @param denominator
	 *            分母
	 * @return
	 */
	public static String getAvg(String numerator, String denominator) {
		if (StringUtils.isEmpty(numerator) || StringUtils.isEmpty(denominator)) {
			return "";
		}

		double avg = nullToDoubleZero(numerator) / nullToDoubleZero(denominator);
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(avg);
	}

	/**
	 * 检验是是否存在字符串中，分号分隔
	 *
	 * @param config
	 * @param checkData
	 * @return
	 */
	public static boolean checkConfig(String config, String checkData) {

		return checkData.indexOf(";") == -1 && (";" + config + ";").indexOf(";" + checkData + ";") >= 0;
	}

	public static String encode(String str, String enc) {

		String strEncode = "";

		try {
			if (str != null)
				strEncode = URLEncoder.encode(str, enc);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		return strEncode;
	}

	public static String encodeBase64String(String data, String charsetName) {
		try {
			return Base64.encodeBase64String(data.getBytes(charsetName)).trim();
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getUUID() {

		return UUID.randomUUID().toString();
	}
}

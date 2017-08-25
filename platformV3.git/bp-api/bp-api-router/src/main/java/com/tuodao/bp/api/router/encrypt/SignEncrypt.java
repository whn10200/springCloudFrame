package com.tuodao.bp.api.router.encrypt;

import java.security.MessageDigest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 签名加密
 * 
 * @author hechuan
 *
 * @created 2017年5月26日
 *
 * @version 1.0.0
 */
public class SignEncrypt {

	private static final Logger logger = LoggerFactory.getLogger(SignEncrypt.class);

	public static final String SHA256 = "SHA-256";

	public static final String SHA512 = "SHA-512";

	public static final String MD5 = "MD5";

	private static final String ENCODING = "UTF-8";

	/**
	 * md5签名
	 * 
	 * @param content
	 *            内容体
	 * @return md5签名后的内容
	 */
	public static String md5(String content) {
		return SHA(content, MD5);
	}

	/**
	 * SHA-256 签名
	 * 
	 * @param content
	 *            内容体
	 * @return
	 */
	public static String SHA256(String content) {
		return SHA(content, SHA256);
	}

	/**
	 * SHA-512 签名
	 * 
	 * @param content
	 *            内容体
	 * @return
	 */
	public static String SHA512(String content) {
		return SHA(content, SHA512);
	}

	/**
	 * 默认 签名--SHA-256
	 * 
	 * @param content
	 *            内容体
	 * @return
	 */
	public static String getDefaultSign(String content) {
		return SHA256(content);
	}

	private static String SHA(String content, String key) {
		logger.info("sign with key = {}", key);
		// 返回值
		String strResult = null;

		// 是否是有效字符串
		if (StringUtils.isNotBlank(content)) {
			try {
				// SHA 加密开始
				// 创建加密对象 并傳入加密類型
				MessageDigest messageDigest = MessageDigest.getInstance(key);
				// 传入要加密的字符串
				messageDigest.update(content.getBytes(ENCODING));
				// 得到 byte 類型结果
				byte byteBuffer[] = messageDigest.digest();
				strResult = byte2hex(byteBuffer);
			} catch (Exception e) {
				logger.error("content = {},key = {},加密错误", content, key, e);
			}
		}

		return strResult;
	}

	private static String byte2hex(byte[] byteBuffer) {

		// 將 byte 轉換爲 string
		StringBuffer strHexString = new StringBuffer();
		// 遍歷 byte buffer
		for (int i = 0; i < byteBuffer.length; i++) {
			String hex = Integer.toHexString(0xff & byteBuffer[i]);
			if (hex.length() == 1) {
				strHexString.append('0');
			}
			strHexString.append(hex);
		}
		// 得到返回結果
		return strHexString.toString().toLowerCase();
	}
}

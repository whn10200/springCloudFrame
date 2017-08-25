package com.tuodao.bp.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.google.common.io.BaseEncoding;

/**
 * HmacSHA1签名工具类。
 *
 * @Created on 2015-02-11
 * @author xieyushi
 * @version 1.0
 */
public class HmacSha1Utils {

	private static final String ALGORITHM = "HmacSHA1";

	private static final Logger log = LoggerFactory.getLogger(HmacSha1Utils.class);

	/**
	 * 根据key和字符集算出data的hash值
	 *
	 * @param data
	 *            需要hash的字节
	 * @param key
	 *            使用HmacSHA1算法hash用key
	 * @param charsetName
	 *            字符集
	 * @return hash后得到的字节数组
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws IllegalStateException
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] sign(String data, String key, String charsetName)
			throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {

		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(charsetName), ALGORITHM);
		Mac mac = Mac.getInstance(ALGORITHM);
		mac.init(signingKey);

		return mac.doFinal(data.getBytes(charsetName));
	}

	/**
	 * 根据key和字符集算出data的hash值
	 *
	 * @param data
	 *            需要hash的字节
	 * @param key
	 *            使用HmacSHA1算法hash用key
	 * @param charsetName
	 *            字符集
	 * @return hash后得到的字符串
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalStateException
	 * @throws UnsupportedEncodingException
	 */
	public static String signToString(String data, String key, String charsetName)
			throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, UnsupportedEncodingException {

		// 取得签名字节数组
		byte[] sign = sign(data, key, charsetName);

		String signStr = BaseEncoding.base64().encode(sign);

		return Strings.nullToEmpty(signStr).trim();
	}

	/**
	 * 生成accessId
	 * 
	 * @param phone
	 * @return add zkai
	 */
	public static String creatAccessId(String phone) {
		return CommonUtils.getRandom(phone);
	}

	/**
	 * 生成accessKey
	 * 
	 * @param accessid
	 * @return add zkai
	 */
	public static String creatAccessKey(String accessid) {
		return CommonUtils.encodeBase64String(CommonUtils.getRandom(accessid), "UTF-8");
	}

	public static boolean signCheck(String data, String key, String sign, String charsetName) {
		try {
			String actual = signToString(data, key, charsetName);
			log.debug("signCheck/actual:[{}][{}]", actual, sign);
			return actual.equals(sign);
		} catch (Exception e) {
			log.error("签名错误", e);
			return false;
		}

	}

	public static void main(String[] args) {
		String accessId = creatAccessId("18868807380");
		System.out.println("accessId:" + accessId);
		String accessKey = creatAccessKey(accessId);
		System.out.println("accessKey:" + accessKey);
	}

}

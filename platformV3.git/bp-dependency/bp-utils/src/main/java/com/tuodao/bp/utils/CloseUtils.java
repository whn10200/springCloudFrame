package com.tuodao.bp.utils;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 关闭流
 * 
 * @author hechuan
 *
 * @created 2017年3月25日
 *
 * @since 1.0.0
 */
public class CloseUtils {

	private static Logger log = LoggerFactory.getLogger(CloseUtils.class);

	public static void closeStream(Closeable stream) {
		if (null != stream) {
			try {
				stream.close();
				stream = null;
			} catch (IOException e) {
				log.error("close stream exception e - >" + e);
			}
		}
	}

	public static void closeStream(Closeable... streams) {
		for (Closeable stream : streams) {
			closeStream(stream);
		}
	}
}

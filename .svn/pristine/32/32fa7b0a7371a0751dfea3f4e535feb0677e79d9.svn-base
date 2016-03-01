package com.tianque.core.redis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.tianque.core.util.StringUtil;

/**
 * @ClassName: RedisCacheJsonUtils
 * @Description: JSON化工具类
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2015年1月16日 下午4:46:39
 */
public class RedisCacheJsonUtils {

	private static Logger logger = LoggerFactory
			.getLogger(RedisCacheJsonUtils.class);
	private static Gson gson = new Gson();

	public static String toJson(Object object) throws Exception {
		if (object == null) {
			return null;
		}
		return gson.toJson(object);
	}

	public static Object fromJson(String json, Class classzs) {
		classzs = classzs == null ? String.class : classzs;
		if (StringUtil.isStringAvaliable(json)) {
			try {
				return gson.fromJson(json, classzs);
			} catch (Exception e) {
				logger.error("json转对象失败！", e);
			}
		}
		return null;
	}
}

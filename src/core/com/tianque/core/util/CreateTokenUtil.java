package com.tianque.core.util;

import com.tianque.core.cache.service.CacheService;

public class CreateTokenUtil {
	
	public static String getToken() {
		String token = FormTokenHelper.generateGUID();
		CacheService cacheService = getCacheService();
		cacheService.set(token, token);
		return token;
	}

	private static CacheService getCacheService() {
		CacheService cacheService = (CacheService) SpringBeanUtil.
				getBeanFromSpringByBeanName("cacheService");
		return cacheService;
	}
}

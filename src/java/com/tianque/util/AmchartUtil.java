package com.tianque.util;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.core.cache.service.CacheService;
import com.tianque.domain.AmchartBean;

@Scope("prototype")
@Component("amchartUtil")
public class AmchartUtil {

	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	@Autowired
	private CacheService cacheService;

	public String setTtile(String title, String classname) {
		String cacheKey = UUID.randomUUID().toString();

		cacheService.set(cacheKey, title);

		return (cacheKey);
	}

	public String setChartData(List<AmchartBean> datalist, String classname) {
		String cacheKey = UUID.randomUUID().toString();

		cacheService.set(cacheKey, datalist);

		return (cacheKey);
	}

	public String getTile(String key) {
		if (key == null)
			return null;
		return (String) cacheService.get(key);
	}

	public List<AmchartBean> getChartData(String key) {
		if (key == null)
			return null;
		return (List<AmchartBean>) cacheService.get(key);
	}

	public void remove(String key) {
		if (key != null)
			cacheService.remove(key);

	}
}
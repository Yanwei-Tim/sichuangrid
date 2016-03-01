package com.tianque.baseInfo.enamePermissionCache.domain;

import java.util.List;

import net.sf.json.JSONArray;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.CalendarUtil;

public class EnamePermissionCache<T> extends BaseDomain {

	/****
	 * @author weiminglong
	 * @date 2015-11-2 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long invalidateTime;
	private String cacheKey;
	private String cacheValue;
	
	public EnamePermissionCache() {
	}
	
	public EnamePermissionCache(String cacheKey, List<String> addPermissionName) {
		invalidateTime = CalendarUtil.transferStringDateToLong(
				"yyyy-MM-dd HH:mm:ss", 10);
		this.cacheKey = cacheKey;
		this.setDatas(addPermissionName);
	}

	public Long getInvalidateTime() {
		return invalidateTime;
	}

	public void setInvalidateTime(Long invalidateTime) {
		this.invalidateTime = invalidateTime;
	}

	public String getCacheKey() {
		return cacheKey;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}

	public String getCacheValue() {
		return cacheValue;
	}

	public void setCacheValue(String cacheValue) {
		this.cacheValue = cacheValue;
	}
	
	public List<T> getDatas(Class<T> clazz) {
		return (List<T>) JSONArray.toCollection(
				JSONArray.fromObject(cacheValue), clazz);
	}

	public void setDatas(List<String> addPermissionName) {
		invalidateTime = CalendarUtil.transferStringDateToLong(
				"yyyy-MM-dd HH:mm:ss", 10);
		cacheValue = JSONArray.fromObject(addPermissionName).toString();
	}


}

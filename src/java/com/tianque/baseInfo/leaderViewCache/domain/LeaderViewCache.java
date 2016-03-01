package com.tianque.baseInfo.leaderViewCache.domain;

import java.util.List;

import net.sf.json.JSONArray;

import com.tianque.baseInfo.leaderViewCache.constants.LeaderViewCacheType;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.CalendarUtil;

public class LeaderViewCache<T> extends BaseDomain {

	private Long invalidateTime;
	private String cacheKey;
	private String cacheValue;
	private Integer cacheType;

	public LeaderViewCache() {
	}

	public LeaderViewCache(String cacheKey, List<T> datas) {
		invalidateTime = CalendarUtil.transferStringDateToLong(
				"yyyy-MM-dd HH:mm:ss", 10);
		this.cacheKey = cacheKey;
		this.setDatas(datas);
		this.cacheType = LeaderViewCacheType.OTHER_TYPE;
	}

	public LeaderViewCache(String cacheKey, List<T> datas, Integer cacheType) {
		invalidateTime = CalendarUtil.transferStringDateToLong(
				"yyyy-MM-dd HH:mm:ss", 10);
		this.cacheKey = cacheKey;
		this.setDatas(datas);
		this.cacheType = cacheType;
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

	public void setDatas(List<T> datas) {
		invalidateTime = CalendarUtil.transferStringDateToLong(
				"yyyy-MM-dd HH:mm:ss", 10);
		cacheValue = JSONArray.fromObject(datas).toString();
	}

	public Integer getCacheType() {
		return cacheType;
	}

	public void setCacheType(Integer cacheType) {
		this.cacheType = cacheType;
	}
}

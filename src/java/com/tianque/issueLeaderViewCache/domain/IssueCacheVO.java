package com.tianque.issueLeaderViewCache.domain;

import java.io.Serializable;

public class IssueCacheVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String keyCache;
	private Integer dataCount;
	private String dataObject;

	public IssueCacheVO() {
	}

	public IssueCacheVO(String keyCache, Integer dataCount, String dataObject) {
		this.keyCache = keyCache;
		this.dataCount = dataCount;
		this.dataObject = dataObject;
	}

	public String getKeyCache() {
		return keyCache;
	}

	public void setKeyCache(String keyCache) {
		this.keyCache = keyCache;
	}

	public Integer getDataCount() {
		return dataCount;
	}

	public void setDataCount(Integer dataCount) {
		this.dataCount = dataCount;
	}

	public String getDataObject() {
		return dataObject;
	}

	public void setDataObject(String dataObject) {
		this.dataObject = dataObject;
	}

}

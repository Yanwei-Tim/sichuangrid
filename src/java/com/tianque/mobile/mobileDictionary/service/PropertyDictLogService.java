package com.tianque.mobile.mobileDictionary.service;

import com.tianque.mobile.mobileDictionary.domain.PropertyDictLog;

public interface PropertyDictLogService {
	public Integer countPropertyDictLogsByParams(PropertyDictLog log);

	public void addPropertyDictLog(PropertyDictLog log);
}

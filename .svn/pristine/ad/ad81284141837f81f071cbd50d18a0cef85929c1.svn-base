package com.tianque.mobile.mobileDictionary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.mobile.mobileDictionary.dao.MobileDictionaryDao;
import com.tianque.mobile.mobileDictionary.dao.PropertyDictLogDao;
import com.tianque.mobile.mobileDictionary.domain.PropertyDictLog;
import com.tianque.mobile.mobileDictionary.service.PropertyDictLogService;

@Service("propertyDictLogService")
public class PropertyDictLogServiceImpl implements PropertyDictLogService {

	@Autowired
	private PropertyDictLogDao propertyDictLogDao;

	@Override
	public Integer countPropertyDictLogsByParams(PropertyDictLog log) {
		return propertyDictLogDao.countPropertyDictLogsByParams(log);
	}

	@Override
	public void addPropertyDictLog(PropertyDictLog log) {
		propertyDictLogDao.addPropertyDictLog(log);
	}
	
}

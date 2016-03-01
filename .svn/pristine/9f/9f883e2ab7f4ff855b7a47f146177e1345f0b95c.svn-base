package com.tianque.mobile.mobileDictionary.dao.impl;

import org.springframework.stereotype.Repository;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.mobile.mobileDictionary.dao.PropertyDictLogDao;
import com.tianque.mobile.mobileDictionary.domain.PropertyDictLog;

@Repository("propertyDictLogDao")
public class PropertyDictLogDaoImpl extends AbstractBaseDao implements
		PropertyDictLogDao {

	@Override
	public Integer countPropertyDictLogsByParams(PropertyDictLog log){
		return (Integer)getSqlMapClientTemplate().queryForObject(
				"propertyDictLog.countPropertyDictLogsByParams",log);
	}

	@Override
	public void addPropertyDictLog(PropertyDictLog log){
		getSqlMapClientTemplate().insert("propertyDictLog.addPropertyDictLog", log);
	}

}

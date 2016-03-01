package com.tianque.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.SmsMessageFunDao;
import com.tianque.domain.SmsMessageFun;

@Repository("smsMessageFunDao")
public class SmsMessageFunDaoImpl extends AbstractBaseDao implements
		SmsMessageFunDao {
	@Override
	public SmsMessageFun getSimpleSmsMessageFunByOrgCode(String orgcode) {
		return (SmsMessageFun) getSqlMapClientTemplate().queryForObject(
				"smsMessageFun.getSimpleSmsMessageFunByOrgCode", orgcode);
	}
}

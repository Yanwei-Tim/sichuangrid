package com.tianque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.dao.SmsMessageFunDao;
import com.tianque.domain.SmsMessageFun;
import com.tianque.service.SmsMessageFunService;

@Transactional
@Service("smsMessageFunService")
public class SmsMessageFunServiceImpl implements SmsMessageFunService {
	@Autowired
	private SmsMessageFunDao smsMessageFunDao;

	@Override
	public SmsMessageFun getSimpleSmsMessageFunByOrgCode(String orgcode) {
		return smsMessageFunDao.getSimpleSmsMessageFunByOrgCode(orgcode);
	}
}

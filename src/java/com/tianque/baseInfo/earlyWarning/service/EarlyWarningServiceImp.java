package com.tianque.baseInfo.earlyWarning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.earlyWarning.dao.EarlyWarningDao;
import com.tianque.baseInfo.earlyWarning.domain.EarlyWarning;

@Service("earlyWarningService")
@Transactional
public class EarlyWarningServiceImp implements EarlyWarningService {
	@Autowired
	private EarlyWarningDao earlyWarningDao;

	@Override
	public EarlyWarning getEarlyWarningByName(String name) {
		return earlyWarningDao.getEarlyWarningByName(name);
	}

}

package com.tianque.plugin.dataManage.population.idleYouthTemp.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.plugin.dataManage.base.dao.AbstractDataManageDao;
import com.tianque.plugin.dataManage.base.service.BusinessPopulationDataManageServiceImpl;

@Service("idleYouthTempService")
@Transactional
public class IdleYouthTempServiceImpl extends BusinessPopulationDataManageServiceImpl {

	@Autowired
	@Qualifier("idleYouthTempDao")
	private AbstractDataManageDao abstractDataManageDao;

	@Override
	@Resource(name = "idleYouthTempDao")
	public void setAbstractDataManageDao(AbstractDataManageDao abstractDataManageDao) {
		super.setAbstractDataManageDao(abstractDataManageDao);
	}

}

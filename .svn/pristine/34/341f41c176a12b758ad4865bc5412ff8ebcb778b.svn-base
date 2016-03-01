package com.tianque.plugin.dataManage.population.superiorVisitTemp.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.plugin.dataManage.base.dao.AbstractDataManageDao;
import com.tianque.plugin.dataManage.base.service.BusinessPopulationDataManageServiceImpl;

@Service("superiorVisitTempService")
@Transactional
public class SuperiorVisitTempServiceImpl extends BusinessPopulationDataManageServiceImpl {

	@Autowired
	@Qualifier("superiorVisitTempDao")
	private AbstractDataManageDao abstractDataManageDao;

	@Override
	@Resource(name = "superiorVisitTempDao")
	public void setAbstractDataManageDao(AbstractDataManageDao abstractDataManageDao) {
		super.setAbstractDataManageDao(abstractDataManageDao);
	}

}

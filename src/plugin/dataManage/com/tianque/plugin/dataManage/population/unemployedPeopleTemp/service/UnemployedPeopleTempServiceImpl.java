package com.tianque.plugin.dataManage.population.unemployedPeopleTemp.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.plugin.dataManage.base.dao.AbstractDataManageDao;
import com.tianque.plugin.dataManage.base.service.BusinessPopulationDataManageServiceImpl;

@Service("unemployedPeopleTempService")
@Transactional
public class UnemployedPeopleTempServiceImpl extends BusinessPopulationDataManageServiceImpl {

	@Autowired
	@Qualifier("unemployedPeopleTempDao")
	private AbstractDataManageDao abstractDataManageDao;

	@Resource(name = "unemployedPeopleTempDao")
	public void setAbstractDataManageDao(AbstractDataManageDao abstractDataManageDao) {
		super.setAbstractDataManageDao(abstractDataManageDao);
	}
}

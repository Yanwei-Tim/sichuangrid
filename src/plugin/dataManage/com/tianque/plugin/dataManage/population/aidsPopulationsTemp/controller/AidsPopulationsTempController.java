package com.tianque.plugin.dataManage.population.aidsPopulationsTemp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.aidsPopulations.domain.Aidspopulations;
import com.tianque.aidsPopulations.service.AidspopulationsService;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.aidsPopulationsTemp.domain.AidspopulationsTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;

/**
 * 数据管理 -艾滋病人员控制类
 */
@Namespace("/plugin/dataManage/aidspopulationsTempManage")
@Transactional
@Scope("request")
@Controller("aidspopulationsTempController")
public class AidsPopulationsTempController extends
		AbstractDataManageController<AidspopulationsTemp, Aidspopulations> {

	@Autowired
	private AidspopulationsService aidspopulationService;
	@Autowired
	@Qualifier("businessPopulationDataManageService")
	private AbstractDataManageService businessPopulationDataManageService;

	@Resource(name = "businessPopulationDataManageService")
	public void setDataManageService(
			AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(businessPopulationDataManageService);
	}

	@Override
	public Aidspopulations getTargetById(Long id) {
		return aidspopulationService.getAidspopulationsById(id);
	}

	@Override
	public List getCompareList(AidspopulationsTemp aidsPopulationTemp,
			Aidspopulations aidspopulations) {
		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(
				aidsPopulationTemp, aidspopulations);

		return compareList;
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.POPULATION;
	}

	public AidspopulationsTemp getPopulation() {
		return population;
	}

	public void setPopulation(AidspopulationsTemp population) {
		this.population = population;
	}

	public AidspopulationsTemp getTemp() {
		return temp;
	}

	public void setTemp(AidspopulationsTemp temp) {
		this.temp = temp;
	}

}

package com.tianque.plugin.dataManage.population.optimalObjectTemp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.optimalObject.domain.OptimalObject;
import com.tianque.baseInfo.optimalObject.service.OptimalObjectService;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.optimalObjectTemp.domain.OptimalObjectTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;

/**
 * 数据管理 - 优抚对象控制类。
 */
@Namespace("/plugin/dataManage/optimalObjectTempManage")
@Transactional
@Scope("request")
@Controller("optimalObjectTempController")
public class OptimalObjectTempController extends
		AbstractDataManageController<OptimalObjectTemp, OptimalObject> {
	@Autowired
	private OptimalObjectService optimalObjectService;
	@Autowired
	@Qualifier("businessPopulationDataManageService")
	private AbstractDataManageService businessPopulationDataManageService;

	@Resource(name = "businessPopulationDataManageService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(businessPopulationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.POPULATION;
	}

	public OptimalObjectTemp getPopulation() {
		return population;
	}

	public void setPopulation(OptimalObjectTemp population) {
		this.population = population;
	}

	public OptimalObjectTemp getTemp() {
		return temp;
	}

	public void setTemp(OptimalObjectTemp temp) {
		this.temp = temp;
	}

	@Override
	public OptimalObject getTargetById(Long id) {
		return optimalObjectService.getOptimalObjectById(id);
	}

	@Override
	public List getCompareList(OptimalObjectTemp optimalObjectTemp, OptimalObject optimalObject) {
		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(optimalObjectTemp,
				optimalObject);
		Map map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "优抚类型");
		map.put("tempValue", optimalObjectTemp.getOptimalCardType() == null ? "-1" : ""
				+ optimalObjectTemp.getOptimalCardType().getId());
		map.put("realValue", optimalObject.getOptimalCardType() == null ? "-1" : ""
				+ optimalObject.getOptimalCardType().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@SPECIAL_CARE_TYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "optimalCardType.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "生活能力");
		map.put("tempValue", optimalObjectTemp.getAbilityLiving() == null ? "-1" : ""
				+ optimalObjectTemp.getAbilityLiving().getId());
		map.put("realValue", optimalObject.getAbilityLiving() == null ? "-1" : ""
				+ optimalObject.getAbilityLiving().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@VIABILITY");
		map.put("argType", "PropertyDict");
		map.put("submitName", "abilityLiving.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "劳动能力");
		map.put("tempValue", optimalObjectTemp.getLaborAbility() == null ? "-1" : ""
				+ optimalObjectTemp.getLaborAbility().getId());
		map.put("realValue", optimalObject.getLaborAbility() == null ? "-1" : ""
				+ optimalObject.getLaborAbility().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@LABOUR_CAPACITY");
		map.put("argType", "PropertyDict");
		map.put("submitName", "laborAbility.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "优待证号");
		map.put("tempValue", "" + optimalObjectTemp.getOptimalCardNo());
		map.put("realValue", "" + optimalObject.getOptimalCardNo());
		map.put("argType", "str");
		map.put("submitName", "optimalCardNo");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "供养情况");
		map.put("tempValue", optimalObjectTemp.getSupportSituation() == null ? "-1" : ""
				+ optimalObjectTemp.getSupportSituation().getId());
		map.put("realValue", optimalObject.getSupportSituation() == null ? "-1" : ""
				+ optimalObject.getSupportSituation().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@SUPPORT_STATUS");
		map.put("argType", "PropertyDict");
		map.put("submitName", "supportSituation.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "就业情况");
		map.put("tempValue", optimalObjectTemp.getEmploymentSituation() == null ? "-1" : ""
				+ optimalObjectTemp.getEmploymentSituation().getId());
		map.put("realValue", optimalObject.getEmploymentSituation() == null ? "-1" : ""
				+ optimalObject.getEmploymentSituation().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@EMPLOYMENT_STATUS");
		map.put("argType", "PropertyDict");
		map.put("submitName", "employmentSituation.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "关注程度");
		map.put("tempValue", optimalObjectTemp.getAttentionExtent() == null ? "-1" : ""
				+ optimalObjectTemp.getAttentionExtent().getId());
		map.put("realValue", optimalObject.getAttentionExtent() == null ? "-1" : ""
				+ optimalObject.getAttentionExtent().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT");
		map.put("argType", "PropertyDict");
		map.put("submitName", "attentionExtent.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "月生活费");
		map.put("tempValue", "" + optimalObjectTemp.getMonthLivingExpenses());
		map.put("realValue", "" + optimalObject.getMonthLivingExpenses());
		map.put("argType", "str");
		map.put("submitName", "monthLivingExpenses");
		compareList.add(map);

		return compareList;
	}
}

package com.tianque.plugin.dataManage.population.elderlyPeopleTemp.controller;

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

import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.baseInfo.elderlyPeople.service.ElderlyPeopleService;
import com.tianque.core.util.DateUtil;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.elderlyPeopleTemp.domain.ElderlyPeopleTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;

/**
 * 数据管理 - 老年人控制类。
 */
@Namespace("/plugin/dataManage/elderlyPeopleTempManage")
@Transactional
@Scope("request")
@Controller("elderlyPeopleTempController")
public class ElderlyPeopleTempController extends
		AbstractDataManageController<ElderlyPeopleTemp, ElderlyPeople> {
	@Autowired
	private ElderlyPeopleService elderlyPeopleService;
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

	public ElderlyPeopleTemp getPopulation() {
		return population;
	}

	public void setPopulation(ElderlyPeopleTemp population) {
		this.population = population;
	}

	public ElderlyPeopleTemp getTemp() {
		return temp;
	}

	public void setTemp(ElderlyPeopleTemp temp) {
		this.temp = temp;
	}

	@Override
	public ElderlyPeople getTargetById(Long id) {

		return elderlyPeopleService.getElderlyPeopleById(id);
	}

	@Override
	public List getCompareList(ElderlyPeopleTemp elderlyPeopleTemp, ElderlyPeople elderlyPeople) {
		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(elderlyPeopleTemp,
				elderlyPeople);
		Map map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "老年人证号");
		map.put("tempValue", "" + elderlyPeopleTemp.getElderPeopleId());
		map.put("realValue", "" + elderlyPeople.getElderPeopleId());
		map.put("argType", "str");
		map.put("submitName", "elderPeopleId");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "人员类型");
		map.put("tempValue", elderlyPeopleTemp.getPeopleType() == null ? "-1" : ""
				+ elderlyPeopleTemp.getPeopleType().getId());
		map.put("realValue", elderlyPeople.getPeopleType() == null ? "-1" : ""
				+ elderlyPeople.getPeopleType().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@SPECIAL_PERSON");
		map.put("argType", "PropertyDict");
		map.put("submitName", "peopleType.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "目前状况");
		map.put("tempValue", elderlyPeopleTemp.getCurrentStatus() == null ? "-1" : ""
				+ elderlyPeopleTemp.getCurrentStatus().getId());
		map.put("realValue", elderlyPeople.getCurrentStatus() == null ? "-1" : ""
				+ elderlyPeople.getCurrentStatus().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@CURRENT_STATUS");
		map.put("argType", "PropertyDict");
		map.put("submitName", "currentStatus.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "配偶情况");
		map.put("tempValue", elderlyPeopleTemp.getSpouseStatus() == null ? "-1" : ""
				+ elderlyPeopleTemp.getSpouseStatus().getId());
		map.put("realValue", elderlyPeople.getSpouseStatus() == null ? "-1" : ""
				+ elderlyPeople.getSpouseStatus().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@SPOUSE_STATUS");
		map.put("argType", "PropertyDict");
		map.put("submitName", "spouseStatus.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "经济来源");
		map.put("tempValue", elderlyPeopleTemp.getIncomeSource() == null ? "-1" : ""
				+ elderlyPeopleTemp.getIncomeSource().getId());
		map.put("realValue", elderlyPeople.getIncomeSource() == null ? "-1" : ""
				+ elderlyPeople.getIncomeSource().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@INCOME_SOURCE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "incomeSource.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "参加工作日期");
		map.put("tempValue", "" + DateUtil.formateYMD(elderlyPeopleTemp.getEnterWorkDate()));
		map.put("realValue", "" + DateUtil.formateYMD(elderlyPeople.getEnterWorkDate()));
		map.put("argType", "strDate");
		map.put("submitName", "enterWorkDate");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "离退休单位");
		map.put("tempValue", "" + "" + elderlyPeopleTemp.getRetireUnitAndDuty());
		map.put("realValue", "" + "" + elderlyPeople.getRetireUnitAndDuty());
		map.put("argType", "str");
		map.put("submitName", "retireUnitAndDuty");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "离退休日期");
		map.put("tempValue", "" + DateUtil.formateYMD(elderlyPeopleTemp.getRetireDate()));
		map.put("realValue", "" + DateUtil.formateYMD(elderlyPeople.getRetireDate()));
		map.put("argType", "strDate");
		map.put("submitName", "retireDate");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "退休金(元)");
		map.put("tempValue", "" + "" + elderlyPeopleTemp.getPension());
		map.put("realValue", "" + "" + elderlyPeople.getPension());
		map.put("argType", "str");
		map.put("submitName", "pension");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "关注程度");
		map.put("tempValue", elderlyPeopleTemp.getAttentionExtent() == null ? "-1" : ""
				+ elderlyPeopleTemp.getAttentionExtent().getId());
		map.put("realValue", elderlyPeople.getAttentionExtent() == null ? "-1" : ""
				+ elderlyPeople.getAttentionExtent().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT");
		map.put("argType", "PropertyDict");
		map.put("submitName", "attentionExtent.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "居住情况");
		map.put("tempValue", elderlyPeopleTemp.getLiveStatus() == null ? "-1" : ""
				+ elderlyPeopleTemp.getLiveStatus().getId());
		map.put("realValue", elderlyPeople.getLiveStatus() == null ? "-1" : ""
				+ elderlyPeople.getLiveStatus().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@LIVE_STATUS");
		map.put("argType", "PropertyDict");
		map.put("submitName", "liveStatus.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "离退休单位");
		map.put("tempValue", "" + "" + elderlyPeopleTemp.getZhiwu());
		map.put("realValue", "" + "" + elderlyPeople.getZhiwu());
		map.put("argType", "str");
		map.put("submitName", "zhiwu");
		compareList.add(map);

		return compareList;
	}

}

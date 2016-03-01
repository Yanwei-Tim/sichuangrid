package com.tianque.plugin.dataManage.population.handicappedTemp.controller;

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

import com.tianque.baseInfo.handicapped.domain.Handicapped;
import com.tianque.baseInfo.handicapped.service.HandicappedService;
import com.tianque.core.util.DateUtil;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.handicappedTemp.domain.HandicappedTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;

/**
 * 数据管理 - 残疾人员控制类。
 */
@Namespace("/plugin/dataManage/handicappedTempManage")
@Transactional
@Scope("request")
@Controller("handicappedTempController")
public class HandicappedTempController extends
		AbstractDataManageController<HandicappedTemp, Handicapped> {
	@Autowired
	private HandicappedService handicappedService;
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

	public HandicappedTemp getPopulation() {
		return population;
	}

	public void setPopulation(HandicappedTemp population) {
		this.population = population;
	}

	public HandicappedTemp getTemp() {
		return temp;
	}

	public void setTemp(HandicappedTemp temp) {
		this.temp = temp;
	}

	@Override
	public Handicapped getTargetById(Long id) {
		return handicappedService.getHandicappedById(id);
	}

	@Override
	public List getCompareList(HandicappedTemp handicappedTemp, Handicapped handicapped) {
		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(handicappedTemp, handicapped);
		Map map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "监护人");
		map.put("tempValue", "" + handicappedTemp.getGuardianName());
		map.put("realValue", "" + handicapped.getGuardianName());
		map.put("argType", "str");
		map.put("submitName", "guardianName");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "残疾证号");
		map.put("tempValue", "" + handicappedTemp.getDisabilityCardNo());
		map.put("realValue", "" + handicapped.getDisabilityCardNo());
		map.put("argType", "str");
		map.put("submitName", "disabilityCardNo");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "残疾类别");
		map.put("tempValue", handicappedTemp.getDisabilityType() == null ? "-1" : ""
				+ handicappedTemp.getDisabilityType().getId());
		map.put("realValue", handicapped.getDisabilityType() == null ? "-1" : ""
				+ handicapped.getDisabilityType().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@DISABILITY_TYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "disabilityType.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "残疾原因");
		map.put("tempValue", "" + handicappedTemp.getDisabilityReason());
		map.put("realValue", "" + handicapped.getDisabilityReason());
		map.put("argType", "str");
		map.put("submitName", "disabilityReason");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "残疾时间");
		map.put("tempValue", "" + DateUtil.formateYMD(handicappedTemp.getDisabilityDate()));
		map.put("realValue", "" + DateUtil.formateYMD(handicapped.getDisabilityDate()));
		map.put("argType", "strDate");
		map.put("submitName", "disabilityDate");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "残疾等级");
		map.put("tempValue", handicappedTemp.getDisability() == null ? "-1" : ""
				+ handicappedTemp.getDisability().getId());
		map.put("realValue", handicapped.getDisability() == null ? "-1" : ""
				+ handicapped.getDisability().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@DISABILITY_STATUS");
		map.put("argType", "PropertyDict");
		map.put("submitName", "disability.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "技能特长");
		map.put("tempValue", handicappedTemp.getSkillProfile() == null ? "-1" : ""
				+ handicappedTemp.getSkillProfile().getId());
		map.put("realValue", handicapped.getSkillProfile() == null ? "-1" : ""
				+ handicapped.getSkillProfile().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@SKILLS_AND_SPECIALITIES");
		map.put("argType", "PropertyDict");
		map.put("submitName", "skillProfile.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "劳动能力");
		map.put("tempValue", handicappedTemp.getWorkProfile() == null ? "-1" : ""
				+ handicappedTemp.getWorkProfile().getId());
		map.put("realValue", handicapped.getWorkProfile() == null ? "-1" : ""
				+ handicapped.getWorkProfile().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@LABOUR_CAPACITY");
		map.put("argType", "PropertyDict");
		map.put("submitName", "workProfile.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "关注程度");
		map.put("tempValue", handicappedTemp.getAttentionExtent() == null ? "-1" : ""
				+ handicappedTemp.getAttentionExtent().getId());
		map.put("realValue", handicapped.getAttentionExtent() == null ? "-1" : ""
				+ handicapped.getAttentionExtent().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT");
		map.put("argType", "PropertyDict");
		map.put("submitName", "attentionExtent.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "是否有残疾证");
		map.put("tempValue", "" + handicappedTemp.getHadDisabilityCard());
		map.put("realValue", "" + handicapped.getHadDisabilityCard());
		map.put("argType", "boolean");
		map.put("submitName", "hadDisabilityCard");
		compareList.add(map);

		return compareList;
	}

}

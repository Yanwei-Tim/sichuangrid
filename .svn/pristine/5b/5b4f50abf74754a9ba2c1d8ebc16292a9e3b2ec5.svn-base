package com.tianque.plugin.dataManage.population.druggyTemp.controller;

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

import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.baseInfo.druggy.service.DruggyService;
import com.tianque.core.util.DateUtil;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.druggyTemp.domain.DruggyTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;

/**
 * 数据管理 - 吸毒人员控制类。
 */
@Namespace("/plugin/dataManage/druggyTempManage")
@Transactional
@Controller("druggyTempController")
@Scope("request")
public class DruggyTempController extends AbstractDataManageController<DruggyTemp, Druggy> {
	@Autowired
	private DruggyService druggyService;
	@Autowired
	@Qualifier("businessPopulationDataManageService")
	private AbstractDataManageService businessPopulationDataManageService;

	@Override
	public List getCompareList(DruggyTemp druggytemp, Druggy druggy) {
		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(druggytemp, druggy);
		Map map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "关注程度");
		map.put("tempValue", druggytemp.getAttentionExtent() == null ? "-1" : ""
				+ druggytemp.getAttentionExtent().getId());
		map.put("realValue", druggy.getAttentionExtent() == null ? "-1" : ""
				+ druggy.getAttentionExtent().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT");
		map.put("argType", "PropertyDict");
		map.put("submitName", "attentionExtent.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "吸毒状态");
		map.put("tempValue", druggytemp.getDetoxicateCondition() == null ? "-1" : ""
				+ druggytemp.getDetoxicateCondition().getId());
		map.put("realValue", druggy.getDetoxicateCondition() == null ? "-1" : ""
				+ druggy.getDetoxicateCondition().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@DETOXICATE_CONDITION");
		map.put("argType", "PropertyDict");
		map.put("submitName", "detoxicateCondition.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "首吸时间");
		map.put("tempValue", "" + DateUtil.formateYMD(druggytemp.getDrugFristDate()));
		map.put("realValue", "" + DateUtil.formateYMD(druggy.getDrugFristDate()));
		map.put("argType", "strDate");
		map.put("submitName", "drugFristDate");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "查获日期");
		map.put("tempValue", "" + DateUtil.formateYMD(druggytemp.getFerretOutDate()));
		map.put("realValue", "" + DateUtil.formateYMD(druggy.getFerretOutDate()));
		map.put("argType", "strDate");
		map.put("submitName", "ferretOutDate");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "管控现状");
		map.put("tempValue", "" + druggytemp.getControlActuality());
		map.put("realValue", "" + druggy.getControlActuality());
		map.put("argType", "str");
		map.put("submitName", "controlActuality");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "吸毒原因");
		map.put("tempValue", druggytemp.getDrugReason() == null ? "-1" : ""
				+ druggytemp.getDrugReason().getId());
		map.put("realValue", druggy.getDrugReason() == null ? "-1" : ""
				+ druggy.getDrugReason().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@DRUG_REASON");
		map.put("argType", "PropertyDict");
		map.put("submitName", "drugReason.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "毒品来源");
		map.put("tempValue", druggytemp.getDrugSource() == null ? "-1" : ""
				+ druggytemp.getDrugSource().getId());
		map.put("realValue", druggy.getDrugSource() == null ? "-1" : ""
				+ druggy.getDrugSource().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@DRUG_SOURCE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "drugSource.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "戒毒情况");
		map.put("tempValue", druggytemp.getDetoxicateCase() == null ? "-1" : ""
				+ druggytemp.getDetoxicateCase().getId());
		map.put("realValue", druggy.getDetoxicateCase() == null ? "-1" : ""
				+ druggy.getDetoxicateCase().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@DETOXICATE_CASE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "detoxicateCase.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "滥用毒品种类");
		map.put("tempValue", "" + druggytemp.getDrugType());
		map.put("realValue", "" + druggy.getDrugType());
		map.put("argType", "str");
		map.put("submitName", "drugType");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "是否服美沙酮戒毒");
		map.put("tempValue", "" + druggytemp.isAdanon());
		map.put("realValue", "" + druggy.isAdanon());
		map.put("argType", "boolean");
		map.put("submitName", "isAdanon");
		compareList.add(map);
		return compareList;
	}

	@Override
	public Druggy getTargetById(Long id) {
		return druggyService.getDruggyById(id);
	}

	@Resource(name = "businessPopulationDataManageService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(businessPopulationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.POPULATION;
	}

	public DruggyTemp getTemp() {
		return temp;
	}

	public void setTemp(DruggyTemp temp) {
		this.temp = temp;
	}

	public DruggyTemp getPopulation() {
		return population;
	}

	public void setPopulation(DruggyTemp population) {
		this.population = population;
	}

}

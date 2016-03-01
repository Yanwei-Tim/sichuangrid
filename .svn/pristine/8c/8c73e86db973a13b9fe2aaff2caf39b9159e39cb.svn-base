package com.tianque.plugin.dataManage.population.aidNeedPopulationTemp.controller;

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

import com.tianque.baseInfo.aidNeedPopulation.domain.AidNeedPopulation;
import com.tianque.baseInfo.aidNeedPopulation.service.AidNeedPopulationService;
import com.tianque.core.util.DateUtil;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.aidNeedPopulationTemp.domain.AidNeedPopulationTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;

/**
 * 数据管理 -需救助人员控制类
 */
@Namespace("/plugin/dataManage/aidNeedPopulationTempManage")
@Transactional
@Scope("request")
@Controller("aidNeedPopulationTempController")
public class AidNeedPopulationTempController extends
		AbstractDataManageController<AidNeedPopulationTemp, AidNeedPopulation> {
	@Autowired
	private AidNeedPopulationService aidNeedPopulationService;
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

	public AidNeedPopulationTemp getPopulation() {
		return population;
	}

	public void setPopulation(AidNeedPopulationTemp population) {
		this.population = population;
	}

	public AidNeedPopulationTemp getTemp() {
		return temp;
	}

	public void setTemp(AidNeedPopulationTemp temp) {
		this.temp = temp;
	}

	@Override
	public AidNeedPopulation getTargetById(Long id) {
		return aidNeedPopulationService.getAidNeedPopulationById(id);
	}

	@Override
	public List getCompareList(AidNeedPopulationTemp aidNeedPopulationTemp,
			AidNeedPopulation aidNeedPopulation) {
		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(aidNeedPopulationTemp,
				aidNeedPopulation);
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "救助原因");
		map.put("tempValue", aidNeedPopulationTemp.getAidReason() == null ? "-1" : ""
				+ aidNeedPopulationTemp.getAidReason().getId());
		map.put("realValue", aidNeedPopulation.getAidReason() == null ? "-1" : ""
				+ aidNeedPopulation.getAidReason().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@AIDRREASON");
		map.put("argType", "PropertyDict");
		map.put("submitName", "aidReason.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "是否低保户");
		map.put("tempValue", "" + aidNeedPopulationTemp.getIsLowHouseholds());
		map.put("realValue", "" + aidNeedPopulation.getIsLowHouseholds());
		map.put("argType", "boolean");
		map.put("submitName", "isLowHouseholds");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "困难证号");
		map.put("tempValue", "" + aidNeedPopulationTemp.getDifficultCardNo());
		map.put("realValue", "" + aidNeedPopulation.getDifficultCardNo());
		map.put("argType", "str");
		map.put("submitName", "difficultCardNo");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "困难类型");
		map.put("tempValue", aidNeedPopulationTemp.getDifficultType() == null ? "-1" : ""
				+ aidNeedPopulationTemp.getDifficultType().getId());
		map.put("realValue", aidNeedPopulation.getDifficultType() == null ? "-1" : ""
				+ aidNeedPopulation.getDifficultType().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@DIFFICULT_TYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "difficultType.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "领取金额(元)");
		map.put("tempValue", "" + aidNeedPopulationTemp.getSubsidiesAmount());
		map.put("realValue", "" + aidNeedPopulation.getSubsidiesAmount());
		map.put("argType", "str");
		map.put("submitName", "subsidiesAmount");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "领取时间");
		map.put("tempValue", "" + DateUtil.formateYMD(aidNeedPopulationTemp.getSubsidiesGetTime()));
		map.put("realValue", "" + DateUtil.formateYMD(aidNeedPopulation.getSubsidiesGetTime()));
		map.put("argType", "strDate");
		map.put("submitName", "subsidiesGetTime");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "享受起始时间");
		map.put("tempValue",
				"" + DateUtil.formateYMD(aidNeedPopulationTemp.getSubsidiesStartTime()));
		map.put("realValue", "" + DateUtil.formateYMD(aidNeedPopulation.getSubsidiesStartTime()));
		map.put("argType", "strDate");
		map.put("submitName", "subsidiesStartTime");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "享受人数(人)");
		map.put("tempValue", "" + aidNeedPopulationTemp.getSubsidiesPopulation());
		map.put("realValue", "" + aidNeedPopulation.getSubsidiesPopulation());
		map.put("argType", "str");
		map.put("submitName", "subsidiesPopulation");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "享受地区");
		map.put("tempValue", "" + aidNeedPopulationTemp.getSubsidiesArea());
		map.put("realValue", "" + aidNeedPopulation.getSubsidiesArea());
		map.put("argType", "str");
		map.put("submitName", "subsidiesArea");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "关注程度");
		map.put("tempValue", aidNeedPopulationTemp.getAttentionExtent() == null ? "-1" : ""
				+ aidNeedPopulationTemp.getAttentionExtent().getId());
		map.put("realValue", aidNeedPopulation.getAttentionExtent() == null ? "-1" : ""
				+ aidNeedPopulation.getAttentionExtent().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT");
		map.put("argType", "PropertyDict");
		map.put("submitName", "attentionExtent.id");
		compareList.add(map);

		return compareList;
	}
}

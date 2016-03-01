package com.tianque.plugin.dataManage.population.rectificativePersonTemp.controller;

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

import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.baseInfo.rectificativePerson.service.RectificativePersonService;
import com.tianque.core.util.DateUtil;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.rectificativePersonTemp.domain.RectificativePersonTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;

/*
 * 数据管理-社区矫正人员控制类*
 */
@Namespace("/plugin/dataManage/rectificativePersonTempManage")
@Transactional
@Scope("request")
@Controller("rectificativePersonTempController")
public class RectificativePersonTempController extends
		AbstractDataManageController<RectificativePersonTemp, RectificativePerson> {
	@Autowired
	private RectificativePersonService rectificativePersonService;
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

	public RectificativePersonTemp getPopulation() {
		return population;
	}

	public void setPopulation(RectificativePersonTemp population) {
		this.population = population;
	}

	public RectificativePersonTemp getTemp() {
		return temp;
	}

	public void setTemp(RectificativePersonTemp temp) {
		this.temp = temp;
	}

	@Override
	public RectificativePerson getTargetById(Long id) {
		return rectificativePersonService.getRectificativePersonById(id);
	}

	@Override
	public List getCompareList(RectificativePersonTemp rectificativePersonTemp,
			RectificativePerson rectificativePerson) {
		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(rectificativePersonTemp,
				rectificativePerson);
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "刑法执行类别");
		map.put("tempValue", rectificativePersonTemp.getExecuteType() == null ? "-1" : ""
				+ rectificativePersonTemp.getExecuteType().getId());
		map.put("realValue", rectificativePerson.getExecuteType() == null ? "-1" : ""
				+ rectificativePerson.getExecuteType().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@EXECUTE_TYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "executeType.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "社区矫正开始时间");
		map.put("tempValue",
				"" + DateUtil.formateYMD(rectificativePersonTemp.getRectifyStartDate()));
		map.put("realValue", "" + DateUtil.formateYMD(rectificativePerson.getRectifyStartDate()));
		map.put("argType", "strDate");
		map.put("submitName", "rectifyStartDate");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "社区矫正结束时间");
		map.put("tempValue", "" + DateUtil.formateYMD(rectificativePersonTemp.getRectifyEndDate()));
		map.put("realValue", "" + DateUtil.formateYMD(rectificativePerson.getRectifyEndDate()));
		map.put("argType", "strDate");
		map.put("submitName", "rectifyEndDate");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "原判刑期");
		map.put("tempValue", "" + rectificativePersonTemp.getPenaltyTerm());
		map.put("realValue", "" + rectificativePerson.getPenaltyTerm());
		map.put("argType", "str");
		map.put("submitName", "penaltyTerm");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "近况描述");
		map.put("tempValue", "" + rectificativePersonTemp.getRecentSituation());
		map.put("realValue", "" + rectificativePerson.getRecentSituation());
		map.put("argType", "str");
		map.put("submitName", "recentSituation");
		compareList.add(map);
		return compareList;
	}
}

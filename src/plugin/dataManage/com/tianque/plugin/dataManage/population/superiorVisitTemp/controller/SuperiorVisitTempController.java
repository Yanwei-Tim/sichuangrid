package com.tianque.plugin.dataManage.population.superiorVisitTemp.controller;

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

import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
import com.tianque.baseInfo.superiorVisit.service.SuperiorVisitService;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.superiorVisitTemp.domain.SuperiorVisitTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;
import com.tianque.sysadmin.service.PropertyDictService;

/*
 * 数据管理-重点信访人员控制类*
 */

@Namespace("/plugin/dataManage/superiorVisitTempManage")
@Transactional
@Scope("request")
@Controller("superiorVisitTempController")
public class SuperiorVisitTempController extends
		AbstractDataManageController<SuperiorVisitTemp, SuperiorVisit> {
	@Autowired
	private SuperiorVisitService superiorVisitService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	@Qualifier("superiorVisitTempService")
	private AbstractDataManageService businessPopulationDataManageService;

	@Resource(name = "superiorVisitTempService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(businessPopulationDataManageService);
	}

	private List<Long> visitTypes;

	private List<PropertyDict> LongToPropertyDict(List<Long> ids) {
		List<PropertyDict> list = new ArrayList<PropertyDict>();
		for (Long id : ids) {
			list.add(propertyDictService.getPropertyDictById(id));
		}
		return list;
	}

	private void initPopulation() {
		if (population != null) {
			if (visitTypes != null && visitTypes.size() > 0) {
				population.setVisitTypes(this.LongToPropertyDict(visitTypes));
			}
		}
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.POPULATION;
	}

	public void setPopulation(SuperiorVisitTemp population) {

		this.population = population;
		initPopulation();
	}

	public SuperiorVisitTemp getPopulation() {
		return population;
	}

	public SuperiorVisitTemp getTemp() {
		return temp;
	}

	public void setTemp(SuperiorVisitTemp temp) {
		this.temp = temp;
	}

	public List<Long> getVisitTypes() {
		return visitTypes;
	}

	public void setVisitTypes(List<Long> visitTypes) {
		this.visitTypes = visitTypes;
		initPopulation();
	}

	@Override
	public SuperiorVisit getTargetById(Long id) {
		return superiorVisitService.getSuperiorVisitById(id);
	}

	@Override
	public List getCompareList(SuperiorVisitTemp superiorVisitTemp, SuperiorVisit superiorVisit) {
		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(superiorVisitTemp,
				superiorVisit);
		Map map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "上访状态");
		map.put("tempValue", superiorVisitTemp.getVisitState() == null ? "-1" : ""
				+ superiorVisitTemp.getVisitState().getId());
		map.put("realValue", superiorVisit.getVisitState() == null ? "-1" : ""
				+ superiorVisit.getVisitState().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@SUPERIOR_VISIT_STATUS");
		map.put("argType", "PropertyDict");
		map.put("submitName", "visitState.id");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "上访原因");
		map.put("tempValue", "" + superiorVisitTemp.getVisitReason());
		map.put("realValue", "" + superiorVisit.getVisitReason());
		map.put("argType", "str");
		map.put("submitName", "visitReason");
		compareList.add(map);

		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "关注程度");
		map.put("tempValue", superiorVisitTemp.getAttentionExtent() == null ? "-1" : ""
				+ superiorVisitTemp.getAttentionExtent().getId());
		map.put("realValue", superiorVisit.getAttentionExtent() == null ? "-1" : ""
				+ superiorVisit.getAttentionExtent().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT");
		map.put("argType", "PropertyDict");
		map.put("submitName", "attentionExtent.id");
		compareList.add(map);

		// map = new HashMap();
		// map.put("notNull", "false");
		// map.put("label", "上访类型");
		// map.put("tempValue", superiorVisitTemp.getVisitType() == null ? "-1"
		// : superiorVisitTemp.getVisitType().toString());
		// map.put("realValue", superiorVisit.getVisitType() == null ? "-1"
		// : superiorVisit.getVisitType().toString());
		// map.put("argType", "long");
		// map.put("submitName", "visitType");
		// map.put("options", new String[] { "-1", "1", "0" });
		// map.put("-1", "<空>");
		// map.put("1", "是");
		// map.put("0", "否");
		// compareList.add(map);

		return compareList;
	}

}

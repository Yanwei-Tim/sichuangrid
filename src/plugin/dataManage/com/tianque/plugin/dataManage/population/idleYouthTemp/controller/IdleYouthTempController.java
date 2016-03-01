package com.tianque.plugin.dataManage.population.idleYouthTemp.controller;

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

import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.baseInfo.idleYouth.service.IdleYouthService;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.idleYouthTemp.domain.IdleYouthTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 数据管理 - 重点青少年控制类。
 */
@Namespace("/plugin/dataManage/idleYouthTempManage")
@Controller("idleYouthTempController")
@Scope("request")
@Transactional
public class IdleYouthTempController extends AbstractDataManageController<IdleYouthTemp, IdleYouth> {
	@Autowired
	private IdleYouthService idleYouthService;
	@Autowired
	@Qualifier("idleYouthTempService")
	private AbstractDataManageService businessPopulationDataManageService;

	@Resource(name = "idleYouthTempService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(businessPopulationDataManageService);
	}

	private Long[] staffTypeIds;
	@Autowired
	private PropertyDictService propertyDictService;

	public IdleYouthTemp getPopulation() {
		return population;
	}

	private void initPopulation() {
		if (population != null) {
			if (staffTypeIds != null && staffTypeIds.length > 0) {
				population.setStaffType(this.LongToPropertyDict(staffTypeIds));
			}
		}
	}

	private List<PropertyDict> LongToPropertyDict(Long[] ids) {
		List<PropertyDict> staffTypeList = new ArrayList<PropertyDict>();
		for (Long id : ids) {
			staffTypeList.add(propertyDictService.getPropertyDictById(id));
		}
		return staffTypeList;
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.POPULATION;
	}

	public void setPopulation(IdleYouthTemp population) {

		this.population = population;
		initPopulation();
	}

	public IdleYouthTemp getTemp() {
		return temp;
	}

	public void setTemp(IdleYouthTemp temp) {
		this.temp = temp;
	}

	public Long[] getStaffTypeIds() {
		return staffTypeIds;
	}

	public void setStaffTypeIds(Long[] staffTypeIds) {
		this.staffTypeIds = staffTypeIds;
		initPopulation();
	}

	@Override
	public IdleYouth getTargetById(Long id) {
		return idleYouthService.getFullIdleYouthById(id);
	}

	@Override
	public List getCompareList(IdleYouthTemp temp, IdleYouth target) {
		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(temp, target);
		Map map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "关注程度");
		map.put("tempValue", temp.getAttentionExtent() == null ? "-1" : ""
				+ temp.getAttentionExtent().getId());
		map.put("realValue", target.getAttentionExtent() == null ? "-1" : ""
				+ target.getAttentionExtent().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT");
		map.put("argType", "PropertyDict");
		map.put("submitName", "attentionExtent.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "人员类型");
		String tempValue = DataManageBaseInfoUtil.getPropertyDictListIds(temp.getStaffType());
		String realValue = DataManageBaseInfoUtil.getPropertyDictListIds(target.getStaffType());
		map.put("tempValue", "" + tempValue);
		map.put("realValue", "" + realValue);
		map.put("tempArray", tempValue.split(","));
		map.put("realArray", realValue.split(","));
		map.put("argType", "checkbox");
		map.put("submitName", "compareStaffTypeIds");
		map.put("tempPropertyDicts", temp.getStaffType());
		map.put("targetPropertyDicts", target.getStaffType());
		map.put("domainName", "@com.tianque.domain.property.PropertyTypes@IDLEYOUTH_STAFF_TYPE");
		DataManageBaseInfoUtil.getPropertyDictMapValue(temp.getStaffType(), map);
		DataManageBaseInfoUtil.getPropertyDictMapValue(target.getStaffType(), map);
		compareList.add(map);
		return compareList;
	}
}

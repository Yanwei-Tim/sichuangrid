package com.tianque.plugin.dataManage.location.dangerousChemicalsUnitTemp.controller;

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

import com.tianque.baseInfo.dangerousChemicalsUnit.domain.DangerousChemicalsUnit;
import com.tianque.baseInfo.dangerousChemicalsUnit.service.DangerousChemicalsUnitService;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.dangerousChemicalsUnitTemp.domain.DangerousChemicalsUnitTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;

/**
 * 数据管理 - 吸毒人员控制类。
 */
@Namespace("/plugin/dataManage/dangerousChemicalsUnitTempManage")
@Transactional
@Controller("dangerousChemicalsUnitTempController")
@Scope("request")
public class DangerousChemicalsUnitTempController extends
		AbstractDataManageController<DangerousChemicalsUnitTemp, DangerousChemicalsUnit> {
	@Autowired
	private DangerousChemicalsUnitService dangerousChemicalsUnitService;
	@Autowired
	@Qualifier("dangerousChemicalsUnitTempService")
	private AbstractDataManageService locationDataManageService;

	@Resource(name = "dangerousChemicalsUnitTempService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(locationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.LOCATION;
	}

	public DangerousChemicalsUnitTemp getTemp() {
		return temp;
	}

	public void setTemp(DangerousChemicalsUnitTemp temp) {
		this.temp = temp;
	}

	public DangerousChemicalsUnitTemp getPopulation() {
		return population;
	}

	public DangerousChemicalsUnitTemp getLocation() {
		return population;
	}

	public void setLocation(DangerousChemicalsUnitTemp location) {
		this.population = location;
	}

	public void setPopulation(DangerousChemicalsUnitTemp population) {
		this.population = population;
	}

	@Override
	public DangerousChemicalsUnit getTargetById(Long id) {
		return dangerousChemicalsUnitService.getDangerousChemicalsUnitById(id);
	}

	@Override
	public List getCompareList(DangerousChemicalsUnitTemp temp, DangerousChemicalsUnit target) {
		List compareList = new ArrayList();
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "单位名称");
		map.put("tempValue", "" + temp.getName());
		map.put("realValue", "" + target.getUnitName());
		map.put("submitName", "name");
		map.put("argType", "str");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "单位地址");
		map.put("tempValue", "" + temp.getAddress());
		map.put("realValue", "" + target.getUnitAddress());
		map.put("argType", "str");
		map.put("submitName", "address");
		compareList.add(map);
		map = new HashMap();
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
		map.put("notNull", "false");
		map.put("label", "负责人");
		map.put("tempValue", "" + temp.getManager());
		map.put("realValue", "" + target.getSuperintendent());
		map.put("argType", "str");
		map.put("submitName", "manager");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "联系电话");
		map.put("tempValue", "" + temp.getTelephone());
		map.put("realValue", "" + target.getTelephone());
		map.put("argType", "str");
		map.put("submitName", "telephone");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "单位类别");
		map.put("tempValue", "" + temp.getUnitType());
		map.put("realValue", "" + target.getUnitType());
		map.put("argType", "str");
		map.put("submitName", "unitType");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "货物类别");
		map.put("tempValue", "" + temp.getCommodityType());
		map.put("realValue", "" + target.getCommodityType());
		map.put("argType", "str");
		map.put("submitName", "commodityType");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "副本许可范围");
		map.put("tempValue", "" + temp.getCopyScope());
		map.put("realValue", "" + target.getCopyScope());
		map.put("argType", "str");
		map.put("submitName", "copyScope");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "存储设备");
		map.put("tempValue", "" + temp.getStorageDevice());
		map.put("realValue", "" + target.getStorageDevice());
		map.put("argType", "str");
		map.put("submitName", "storageDevice");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "备注");
		map.put("tempValue", "" + temp.getRemark());
		map.put("realValue", "" + target.getRemark());
		map.put("argType", "str");
		map.put("submitName", "remark");
		compareList.add(map);
		return compareList;
	}

}

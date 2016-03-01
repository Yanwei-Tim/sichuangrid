package com.tianque.plugin.dataManage.location.fireSafetyEnterpriseTemp.controller;

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

import com.tianque.domain.FireSafetyEnterprise;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.fireSafetyEnterpriseTemp.domain.FireSafetyEnterpriseTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.service.FireSafetyEnterpriseService;

/**
 * 数据管理 - 消防安全重点控制类。
 */
@Namespace("/plugin/dataManage/fireSafetyEnterpriseTempManage")
@Transactional
@Controller("fireSafetyEnterpriseTempController")
@Scope("request")
public class FireSafetyEnterpriseTempController extends
		AbstractDataManageController<FireSafetyEnterpriseTemp, FireSafetyEnterprise> {
	@Autowired
	private FireSafetyEnterpriseService fireSafetyEnterpriseService;
	@Autowired
	@Qualifier("fireSafetyEnterpriseTempService")
	private AbstractDataManageService locationDataManageService;

	@Resource(name = "fireSafetyEnterpriseTempService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(locationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.LOCATION;
	}

	public FireSafetyEnterpriseTemp getTemp() {
		return temp;
	}

	public void setTemp(FireSafetyEnterpriseTemp temp) {
		this.temp = temp;
	}

	public FireSafetyEnterpriseTemp getPopulation() {
		return population;
	}

	public void setPopulation(FireSafetyEnterpriseTemp population) {
		this.population = population;
	}

	public FireSafetyEnterpriseTemp getLocation() {
		return population;
	}

	public void setLocation(FireSafetyEnterpriseTemp Location) {
		this.population = Location;
	}

	@Override
	public FireSafetyEnterprise getTargetById(Long id) {
		return fireSafetyEnterpriseService.getFireSafetyEnterpriseById(id);
	}

	@Override
	public List getCompareList(FireSafetyEnterpriseTemp temp, FireSafetyEnterprise target) {
		List compareList = new ArrayList();
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", " 场所名称");
		map.put("tempValue", "" + temp.getName());
		map.put("realValue", "" + target.getName());
		map.put("argType", "str");
		map.put("submitName", "name");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "场所地址");
		map.put("tempValue", "" + temp.getAddress());
		map.put("realValue", "" + target.getAddress());
		map.put("argType", "str");
		map.put("submitName", "address");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "场所类型");
		map.put("tempValue", temp.getType() == null ? "-1" : "" + temp.getType().getId());
		map.put("realValue", target.getType() == null ? "-1" : "" + target.getType().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@FIRESAFETY_TYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "type.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "负责人");
		map.put("tempValue", "" + temp.getManager());
		map.put("realValue", "" + target.getLegalPerson());
		map.put("argType", "str");
		map.put("submitName", "manager");
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
		map.put("label", "手机号码");
		map.put("tempValue", "" + temp.getMobileNumber());
		map.put("realValue", "" + target.getMobileNumber());
		map.put("argType", "str");
		map.put("submitName", "mobileNumber");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "是否存在隐患");
		map.put("tempValue", "" + temp.isRiskEnterprise());
		map.put("realValue", "" + target.isRiskEnterprise());
		map.put("argType", "boolean");
		map.put("submitName", "isRiskEnterprise");
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
		map.put("label", "隐患情况");
		map.put("tempValue", "" + temp.getHiddenCases());
		map.put("realValue", "" + target.getHiddenCases());
		map.put("argType", "str");
		map.put("submitName", "hiddenCases");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "隐患整改情况");
		map.put("tempValue", "" + temp.getHiddenRectification());
		map.put("realValue", "" + target.getHiddenRectification());
		map.put("argType", "str");
		map.put("submitName", "hiddenRectification");
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

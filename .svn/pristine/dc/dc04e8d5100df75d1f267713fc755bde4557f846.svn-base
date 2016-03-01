package com.tianque.plugin.dataManage.location.otherLocaleTemp.controller;

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

import com.tianque.domain.OtherLocale;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.otherLocaleTemp.domain.OtherLocaleTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.service.OtherLocaleService;

/**
 * 数据管理 - 其他场所控制类。
 */
@Namespace("/plugin/dataManage/otherLocaleTempManage")
@Transactional
@Controller("otherLocaleTempController")
@Scope("request")
public class OtherLocaleTempController extends
		AbstractDataManageController<OtherLocaleTemp, OtherLocale> {
	@Autowired
	private OtherLocaleService otherLocaleService;
	@Autowired
	@Qualifier("otherLocaleTempService")
	private AbstractDataManageService locationDataManageService;

	@Resource(name = "otherLocaleTempService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(locationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.LOCATION;
	}

	public OtherLocaleTemp getTemp() {
		return temp;
	}

	public void setTemp(OtherLocaleTemp temp) {
		this.temp = temp;
	}

	public OtherLocaleTemp getLocation() {
		return population;
	}

	public OtherLocaleTemp getPopulation() {
		return population;
	}

	public void setLocation(OtherLocaleTemp Location) {
		this.population = Location;
	}

	@Override
	public List getCompareList(OtherLocaleTemp temp, OtherLocale target) {
		List compareList = new ArrayList();
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "场所名称");
		map.put("tempValue", "" + temp.getName());
		map.put("realValue", "" + target.getName());
		map.put("submitName", "name");
		map.put("argType", "str");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "场所地址");
		map.put("tempValue", "" + temp.getAddress());
		map.put("realValue", "" + target.getAddress());
		map.put("argType", "str");
		map.put("submitName", "address");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", " 场所类型");
		map.put("tempValue", temp.getType() == null ? "-1" : "" + temp.getType().getId());
		map.put("realValue", target.getType() == null ? "-1" : "" + target.getType().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@OTHER_LOCALE_TYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "type.id");
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
		map.put("label", "联系人");
		map.put("tempValue", "" + temp.getManager());
		map.put("realValue", "" + target.getContactPerson());
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
		map.put("label", "联系手机");
		map.put("tempValue", "" + temp.getMobileNumber());
		map.put("realValue", "" + target.getMobileNumber());
		map.put("argType", "str");
		map.put("submitName", "mobileNumber");
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

	@Override
	public OtherLocale getTargetById(Long id) {
		return otherLocaleService.getOtherLocaleById(id);
	}

}

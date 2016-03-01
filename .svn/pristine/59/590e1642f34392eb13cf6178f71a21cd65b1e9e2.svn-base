package com.tianque.plugin.dataManage.location.hospitalTemp.controller;

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

import com.tianque.domain.Hospital;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.hospitalTemp.domain.HospitalTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.service.HospitalService;

@Namespace("/plugin/dataManage/hospitalTempManage")
@Transactional
@Controller("hospitalTempController")
@Scope("request")
public class HospitalTempController extends AbstractDataManageController<HospitalTemp,Hospital>{
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	@Qualifier("hospitalTempService")
	private AbstractDataManageService locationDataManageService;

	@Resource(name = "hospitalTempService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(locationDataManageService);
	}

	// public String getAjaxUrl() {
	// return "hasDuplicateHospitalTempLocation";
	// }

	public String getBigType() {
		return DataManageBaseInfoTypes.LOCATION;
	}

	public HospitalTemp getTemp() {
		return temp;
	}

	public void setTemp(HospitalTemp temp) {
		this.temp = temp;
	}

	public HospitalTemp getLocation() {
		return population;
	}

	// public void setPopulation(HospitalTemp population) {
	// this.population = population;
	// }
	public HospitalTemp getPopulation() {
		return population;
	}

	public void setLocation(HospitalTemp Location) {
		this.population = Location;
	}

	@Override
	public Hospital getTargetById(Long id) {
		return hospitalService.getHospitalById(id);
	}

	@Override
	public List getCompareList(HospitalTemp temp, Hospital target) {
		List compareList = new ArrayList();
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "医院名称");
		map.put("tempValue", "" + temp.getName());
		map.put("realValue", "" + target.getHospitalName());
		map.put("submitName", "name");
		map.put("argType", "str");
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
		map.put("label", "医院院长");
		map.put("tempValue", "" + temp.getDirector());
		map.put("realValue", "" + target.getDirector());
		map.put("argType", "str");
		map.put("submitName", "director");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "医院地址");
		map.put("tempValue", "" + temp.getAddress());
		map.put("realValue", "" + target.getAddress());
		map.put("argType", "str");
		map.put("submitName", "address");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "所属单位");
		map.put("tempValue", "" + temp.getAffiliatedUnit());
		map.put("realValue", "" + target.getAffiliatedUnit());
		map.put("argType", "str");
		map.put("submitName", "affiliatedUnit");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "医院性质");
		map.put("tempValue", temp.getKind() == null ? "-1" : ""
				+ temp.getKind().getId());
		map.put("realValue", target.getKind() == null ? "-1" : ""
				+ target.getKind().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@HOSPITALS_KIND");
		map.put("argType", "PropertyDict");
		map.put("submitName", "kind.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "医院类型");
		map.put("tempValue", temp.getType() == null ? "-1" : ""
				+ temp.getType().getId());
		map.put("realValue", target.getType() == null ? "-1" : ""
				+ target.getType().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@HOSPITALS_TYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "type.id");
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
		map.put("label", "电子邮箱");
		map.put("tempValue", "" + temp.getEmail());
		map.put("realValue", "" + target.getEmail());
		map.put("argType", "str");
		map.put("submitName", "email");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "传真");
		map.put("tempValue", "" + temp.getFax());
		map.put("realValue", "" + target.getFax());
		map.put("argType", "str");
		map.put("submitName", "fax");
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

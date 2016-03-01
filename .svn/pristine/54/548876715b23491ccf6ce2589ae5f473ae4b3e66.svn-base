package com.tianque.plugin.dataManage.location.newSocietyOrganizationsTemp.controller;

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

import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.baseInfo.newSocietyOrganizations.service.NewSocietyOrganitionsService;
import com.tianque.core.util.DateUtil;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.newSocietyOrganizationsTemp.domain.NewSocietyOrganizationsTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;

/**
 * 数据管理 - 新社会组织控制类。
 */
@Namespace("/plugin/dataManage/newSocietyOrganizationsTempManage")
@Transactional
@Controller("NewSocietyOrganizationsTempController")
@Scope("request")
public class NewSocietyOrganizationsTempController extends
		AbstractDataManageController<NewSocietyOrganizationsTemp, NewSocietyOrganizations> {
	@Autowired
	private NewSocietyOrganitionsService newSocietyOrganitionsService;
	@Autowired
	@Qualifier("newSocietyOrganizationsTempService")
	private AbstractDataManageService locationDataManageService;

	@Resource(name = "newSocietyOrganizationsTempService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(locationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.LOCATION;
	}

	public NewSocietyOrganizationsTemp getTemp() {
		return temp;
	}

	public void setTemp(NewSocietyOrganizationsTemp temp) {
		this.temp = temp;
	}

	public NewSocietyOrganizationsTemp getPopulation() {
		return population;
	}

	public void setPopulation(NewSocietyOrganizationsTemp population) {
		this.population = population;
	}

	public NewSocietyOrganizationsTemp getLocation() {
		return population;
	}

	public void setLocation(NewSocietyOrganizationsTemp Location) {
		this.population = Location;
	}

	@Override
	public NewSocietyOrganizations getTargetById(Long id) {
		return newSocietyOrganitionsService.getSimpleNewSocietyOrganizations(id);
	}

	@Override
	public List getCompareList(NewSocietyOrganizationsTemp temp, NewSocietyOrganizations target) {
		List compareList = new ArrayList();
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "组织名称");
		map.put("tempValue", "" + temp.getName());
		map.put("realValue", "" + target.getName());
		map.put("argType", "str");
		map.put("submitName", "name");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", " 住所");
		map.put("tempValue", "" + temp.getAddress());
		map.put("realValue", "" + target.getAddress());
		map.put("argType", "str");
		map.put("submitName", "address");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "组织类别");
		map.put("tempValue", temp.getType() == null ? "-1" : "" + temp.getType().getId());
		map.put("realValue", target.getType() == null ? "-1" : "" + target.getType().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@SOCIETY_GROUP");
		map.put("argType", "PropertyDict");
		map.put("submitName", "type.id");
		compareList.add(map);
		// 组织子类别(新增)
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "组织子类别");
		map.put("tempValue", temp.getSubType() == null ? "-1" : "" + temp.getSubType().getId());
		map.put("realValue", target.getSubType() == null ? "-1" : "" + target.getSubType().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@NEW_SOCIETY_TYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "subType.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", " 法定代表人");
		map.put("tempValue", "" + temp.getManager());
		map.put("realValue", "" + target.getLegalPerson());
		map.put("argType", "str");
		map.put("submitName", "manager");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", " 固定电话");
		map.put("tempValue", "" + temp.getTelephone());
		map.put("realValue", "" + target.getLegalPersonTelephone());
		map.put("argType", "str");
		map.put("submitName", "telephone");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", " 联系手机");
		map.put("tempValue", "" + temp.getMobileNumber());
		map.put("realValue", "" + target.getLegalPersonMobileNumber());
		map.put("argType", "str");
		map.put("submitName", "mobileNumber");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", " 业务主管单位");
		map.put("tempValue", "" + temp.getChargeUnit());
		map.put("realValue", "" + target.getChargeUnit());
		map.put("argType", "str");
		map.put("submitName", "chargeUnit");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", " 登记证号码");
		map.put("tempValue", "" + temp.getRegistrationNumber());
		map.put("realValue", "" + target.getRegistrationNumber());
		map.put("argType", "str");
		map.put("submitName", "registrationNumber");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "有效期开始");
		map.put("tempValue", "" + DateUtil.formateYMD(temp.getValidityStartDate()));
		map.put("realValue", "" + DateUtil.formateYMD(target.getValidityStartDate()));
		map.put("argType", "strDate");
		map.put("submitName", "validityStartDate");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "有效期结束");
		map.put("tempValue", "" + DateUtil.formateYMD(temp.getValidityEndDate()));
		map.put("realValue", "" + DateUtil.formateYMD(target.getValidityEndDate()));
		map.put("argType", "strDate");
		map.put("submitName", "validityEndDate");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "成员数");
		map.put("tempValue", "" + temp.getPersonNum());
		map.put("realValue", "" + target.getPersonNum());
		map.put("argType", "str");
		map.put("submitName", "personNum");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "党员人数");
		map.put("tempValue", "" + temp.getPartyNum());
		map.put("realValue", "" + target.getPartyNum());
		map.put("argType", "str");
		map.put("submitName", "personNum");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "简介");
		map.put("tempValue", "" + temp.getIntroduction());
		map.put("realValue", "" + target.getIntroduction());
		map.put("argType", "str");
		map.put("submitName", "introduction");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "荣誉");
		map.put("tempValue", "" + temp.getHonor());
		map.put("realValue", "" + target.getHonor());
		map.put("argType", "str");
		map.put("submitName", "honor");
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

package com.tianque.plugin.dataManage.location.neweconomicOrganizationsTemp.controller;

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

import com.tianque.core.util.DateUtil;
import com.tianque.domain.NewEconomicOrganizations;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.neweconomicOrganizationsTemp.domain.NewEconomicOrganizationsTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.service.NewEconomicOrganizationsService;

/**
 * 数据管理 - 新经济组织控制类。
 */
@Namespace("/plugin/dataManage/newEconomicOrganizationsTempManage")
@Transactional
@Controller("newEconomicOrganizationsTempController")
@Scope("request")
public class NewEconomicOrganizationsTempController extends
		AbstractDataManageController<NewEconomicOrganizationsTemp, NewEconomicOrganizations> {
	@Autowired
	private NewEconomicOrganizationsService newEconomicOrganizationsService;
	@Autowired
	@Qualifier("newEconomicOrganizationsTempService")
	private AbstractDataManageService locationDataManageService;

	@Resource(name = "newEconomicOrganizationsTempService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(locationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.LOCATION;
	}

	public NewEconomicOrganizationsTemp getTemp() {
		return temp;
	}

	public void setTemp(NewEconomicOrganizationsTemp temp) {
		this.temp = temp;
	}

	public NewEconomicOrganizationsTemp getPopulation() {
		return population;
	}

	public void setPopulation(NewEconomicOrganizationsTemp population) {
		this.population = population;
	}

	public NewEconomicOrganizationsTemp getLocation() {
		return population;
	}

	public void setLocation(NewEconomicOrganizationsTemp Location) {
		this.population = Location;
	}

	@Override
	public NewEconomicOrganizations getTargetById(Long id) {
		return newEconomicOrganizationsService.getNewEconomicOrganizationsById(id);
	}

	@Override
	public List getCompareList(NewEconomicOrganizationsTemp temp, NewEconomicOrganizations target) {
		List compareList = new ArrayList();
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "名称");
		map.put("tempValue", "" + temp.getName());
		map.put("realValue", "" + target.getName());
		map.put("argType", "str");
		map.put("submitName", "name");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "住所");
		map.put("tempValue", "" + temp.getAddress());
		map.put("realValue", "" + target.getResidence());
		map.put("argType", "str");
		map.put("submitName", "address");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "营业执照号码");
		map.put("tempValue", "" + temp.getLicenseNumber());
		map.put("realValue", "" + target.getLicenseNumber());
		map.put("argType", "str");
		map.put("submitName", "licenseNumber");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "有效期开始");
		map.put("tempValue", "" + DateUtil.formateYMD(temp.getValidityStartDate()));
		map.put("realValue", "" + DateUtil.formateYMD(target.getValidityStartDate()));
		map.put("argType", "strDate");
		map.put("submitName", "validityStartDate");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "有效期结束");
		map.put("tempValue", "" + DateUtil.formateYMD(temp.getValidityEndDate()));
		map.put("realValue", "" + DateUtil.formateYMD(target.getValidityEndDate()));
		map.put("argType", "strDate");
		map.put("submitName", "validityEndDate");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "类别");
		map.put("tempValue", temp.getType() == null ? "-1" : "" + temp.getType().getId());
		map.put("realValue", target.getStyle() == null ? "-1" : "" + target.getStyle().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@NEWECONOMICORGANIZATIONS_STYLE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "type.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "负责人");
		map.put("tempValue", "" + temp.getManager());
		map.put("realValue", "" + target.getChief());
		map.put("argType", "str");
		map.put("submitName", "manager");
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
		map.put("label", "固定电话");
		map.put("tempValue", "" + temp.getTelephone());
		map.put("realValue", "" + target.getTelephone());
		map.put("argType", "str");
		map.put("submitName", "telephone");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "传真号码");
		map.put("tempValue", "" + temp.getFoxNumber());
		map.put("realValue", "" + target.getFoxNumber());
		map.put("argType", "str");
		map.put("submitName", "foxNumber");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "面积");
		map.put("tempValue", "" + temp.getArea());
		map.put("realValue", "" + target.getArea());
		map.put("argType", "str");
		map.put("submitName", "area");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "从业人数");
		map.put("tempValue", "" + temp.getEmployeeNumber());
		map.put("realValue", "" + target.getEmployeeNumber());
		map.put("argType", "str");
		map.put("submitName", "employeeNumber");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "党员人数");
		map.put("tempValue", "" + temp.getPartyNum());
		map.put("realValue", "" + target.getPartyMemberNumber());
		map.put("argType", "str");
		map.put("submitName", "partyNum");
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

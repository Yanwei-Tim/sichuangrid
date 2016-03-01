package com.tianque.plugin.dataManage.location.enterpriseKeyTemp.controller;

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

import com.tianque.domain.Enterprise;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.enterpriseKeyTemp.domain.EnterpriseKeyTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.service.EnterpriseService;

/**
 * 数据管理 - 规上企业控制类。
 */
@Namespace("/plugin/dataManage/enterpriseKeyTempManage")
@Transactional
@Controller("enterpriseKeyTempController")
@Scope("request")
public class EnterpriseKeyTempController extends
		AbstractDataManageController<EnterpriseKeyTemp, Enterprise> {
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	@Qualifier("enterpriseKeyTempService")
	private AbstractDataManageService locationDataManageService;

	@Resource(name = "enterpriseKeyTempService")
	public void setDataManageService(AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(locationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.LOCATION;
	}

	public EnterpriseKeyTemp getTemp() {
		return temp;
	}

	public void setTemp(EnterpriseKeyTemp temp) {
		this.temp = temp;
	}

	public EnterpriseKeyTemp getLocation() {
		return population;
	}

	public EnterpriseKeyTemp getPopulation() {
		return population;
	}

	public void setLocation(EnterpriseKeyTemp Location) {
		this.population = Location;
	}

	@Override
	public Enterprise getTargetById(Long id) {
		return enterpriseService.getEnterpriseById(id);
	}

	@Override
	public List getCompareList(EnterpriseKeyTemp temp, Enterprise target) {
		List compareList = new ArrayList();
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "企业名称");
		map.put("tempValue", "" + temp.getName());
		map.put("realValue", "" + target.getName());
		map.put("argType", "str");
		map.put("submitName", "name");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "企业地址");
		map.put("tempValue", "" + temp.getAddress());
		map.put("realValue", "" + target.getAddress());
		map.put("argType", "str");
		map.put("submitName", "address");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "法人代表");
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
		map.put("label", "注册资金");
		map.put("tempValue", "" + temp.getRegisteredCapital());
		map.put("realValue", "" + target.getRegisteredCapital());
		map.put("argType", "str");
		map.put("submitName", "registeredCapital");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "所属行业");
		map.put("tempValue", temp.getIndustry() == null ? "-1" : "" + temp.getIndustry().getId());
		map.put("realValue", target.getIndustry() == null ? "-1" : ""
				+ target.getIndustry().getId());
		map.put("PropertyDict", "@com.tianque.domain.property.PropertyTypes@CAREER");
		map.put("argType", "PropertyDict");
		map.put("submitName", "industry.id");
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
		map.put("label", "员工数");
		map.put("tempValue", "" + temp.getEmployeeAmount());
		map.put("realValue", "" + target.getEmployeeAmount());
		map.put("argType", "str");
		map.put("submitName", "employeeAmount");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "党员数");
		map.put("tempValue", "" + temp.getPartyMemberAmount());
		map.put("realValue", "" + target.getPartyMemberAmount());
		map.put("argType", "str");
		map.put("submitName", "partyMemberAmount");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "企业联系电话");
		map.put("tempValue", "" + temp.getEnterpriseTelephone());
		map.put("realValue", "" + target.getEnterpriseTelephone());
		map.put("argType", "str");
		map.put("submitName", "enterpriseTelephone");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "企业传真号码");
		map.put("tempValue", "" + temp.getFax());
		map.put("realValue", "" + target.getFax());
		map.put("argType", "str");
		map.put("submitName", "fax");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "法人联系电话");
		map.put("tempValue", "" + temp.getTelephone());
		map.put("realValue", "" + target.getTelephone());
		map.put("argType", "str");
		map.put("submitName", "telephone");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "法人手机号码");
		map.put("tempValue", "" + temp.getMobileNumber());
		map.put("realValue", "" + target.getMobileNumber());
		map.put("argType", "str");
		map.put("submitName", "mobileNumber");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "是否为危化企业");
		map.put("tempValue", "" + temp.isRiskEnterprise());
		map.put("realValue", "" + target.isRiskEnterprise());
		map.put("argType", "boolean");
		map.put("submitName", "isRiskEnterprise");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "工商执照号码");
		map.put("tempValue", "" + temp.getBusinessLicense());
		map.put("realValue", "" + target.getBusinessLicense());
		map.put("argType", "str");
		map.put("submitName", "businessLicense");
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

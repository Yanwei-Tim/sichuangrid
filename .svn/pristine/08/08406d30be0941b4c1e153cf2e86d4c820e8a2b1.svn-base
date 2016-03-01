package com.tianque.plugin.dataManage.location.actualCompanyTemp.controller;

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

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.baseInfo.actualCompany.service.ActualCompanyService;
import com.tianque.core.util.DateUtil;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.actualCompanyTemp.domain.ActualCompanyTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;

/**
 * 数据管理 - 实有单位控制类。
 */
@Namespace("/plugin/dataManage/actualCompanyTempManage")
@Transactional
@Controller("actualCompanyTempController")
@Scope("request")
public class ActualCompanyTempController extends
		AbstractDataManageController<ActualCompanyTemp, ActualCompany> {
	@Autowired
	private ActualCompanyService actualCompanyService;
	@Autowired
	@Qualifier("actualCompanyTempService")
	private AbstractDataManageService locationDataManageService;

	@Resource(name = "actualCompanyTempService")
	public void setDataManageService(
			AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(locationDataManageService);
	}

	public String getBigType() {
		return DataManageBaseInfoTypes.LOCATION;
	}

	public ActualCompanyTemp getTemp() {
		return temp;
	}

	public void setTemp(ActualCompanyTemp temp) {
		this.temp = temp;
	}

	public ActualCompanyTemp getPopulation() {
		return population;
	}

	public void setPopulation(ActualCompanyTemp population) {
		this.population = population;
	}

	public ActualCompanyTemp getLocation() {
		return population;
	}

	public void setLocation(ActualCompanyTemp location) {
		this.population = location;
	}

	@Override
	public ActualCompany getTargetById(Long id) {
		return actualCompanyService.getActualCompanyById(id);
	}

	@Override
	public List getCompareList(ActualCompanyTemp temp, ActualCompany target) {
		List compareList = new ArrayList();
		Map map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "单位名称");
		map.put("tempValue", "" + temp.getName());
		map.put("realValue", "" + target.getCompanyName());
		map.put("submitName", "name");
		map.put("argType", "str");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "单位地址");
		map.put("tempValue", "" + temp.getAddress());
		map.put("realValue", "" + target.getCompanyAddress());
		map.put("argType", "str");
		map.put("submitName", "address");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "营业执照号码");
		map.put("tempValue", "" + temp.getBusinessLicenseNo());
		map.put("realValue", "" + target.getBusinessLicenseNo());
		map.put("argType", "str");
		map.put("submitName", "businessLicenseNo");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "组织机构代码");
		map.put("tempValue", "" + temp.getOrgCode());
		map.put("realValue", "" + target.getOrgCode());
		map.put("argType", "str");
		map.put("submitName", "orgCode");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "单位类别");
		map.put("tempValue", temp.getCompanyType() == null ? "-1" : ""
				+ temp.getCompanyType().getId());
		map.put("realValue", target.getCompanyType() == null ? "-1" : ""
				+ target.getCompanyType().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_COMPANYTYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "companyType.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "true");
		map.put("label", "是否重点单位");
		map.put("tempValue", "" + temp.getIsKey());
		map.put("realValue", "" + target.getIsKey());
		map.put("argType", "boolean");
		map.put("submitName", "isKey");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "法人代表");
		map.put("tempValue", "" + temp.getManager());
		map.put("realValue", "" + target.getCorporateRepresentative());
		map.put("argType", "str");
		map.put("submitName", "manager");
		compareList.add(map);
		// 原来表中有，数据管理中没有(现在已经增加)
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "身份证号码");
		map.put("tempValue", "" + temp.getIdCardNo());
		map.put("realValue", "" + target.getIdCardNo());
		map.put("argType", "str");
		map.put("submitName", "idCardNo");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "单位电话");
		map.put("tempValue", "" + temp.getTelephone());
		map.put("realValue", "" + target.getTelephone());
		map.put("argType", "str");
		map.put("submitName", "telephone");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "单位传真");
		map.put("tempValue", "" + temp.getFax());
		map.put("realValue", "" + target.getFacsimile());
		map.put("argType", "str");
		map.put("submitName", "fax");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "注册资本");
		map.put("tempValue", "" + temp.getRegisteredCapital());
		map.put("realValue", "" + target.getRegisteredCapital());
		map.put("argType", "str");
		map.put("submitName", "registeredCapital");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "经济性质");
		map.put("tempValue", temp.getEconomicNature() == null ? "-1" : ""
				+ temp.getEconomicNature().getId());
		map.put("realValue", target.getEconomicNature() == null ? "-1" : ""
				+ target.getEconomicNature().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_ECONOMICNATURE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "economicNature.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "有效期至");
		map.put("tempValue", "" + DateUtil.formateYMD(temp.getExpiryDate()));
		map.put("realValue", "" + DateUtil.formateYMD(target.getExpiryDate()));
		map.put("argType", "strDate");
		map.put("submitName", "expiryDate");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "注册日期");
		map.put("tempValue",
				"" + DateUtil.formateYMD(temp.getRegistrationDate()));
		map.put("realValue",
				"" + DateUtil.formateYMD(target.getRegistrationDate()));
		map.put("argType", "strDate");
		map.put("submitName", "registrationDate");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "经营范围");
		map.put("tempValue", "" + temp.getBusinessScope());
		map.put("realValue", "" + target.getBusinessScope());
		map.put("argType", "str");
		map.put("submitName", "businessScope");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "注册地址");
		map.put("tempValue", "" + temp.getRegistrationAddress());
		map.put("realValue", "" + target.getRegistrationAddress());
		map.put("argType", "str");
		map.put("submitName", "registrationAddress");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "从业人数");
		map.put("tempValue", "" + temp.getEmployeesNum());
		map.put("realValue", "" + target.getEmployeesNum());
		map.put("argType", "str");
		map.put("submitName", "employeesNum");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "主管部门");
		map.put("tempValue", "" + temp.getCompetentDepartment());
		map.put("realValue", "" + target.getCompetentDepartment());
		map.put("argType", "str");
		map.put("submitName", "competentDepartment");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "管理级别");
		map.put("tempValue", temp.getSupervisoryLevel() == null ? "-1" : ""
				+ temp.getSupervisoryLevel().getId());
		map.put("realValue", target.getSupervisoryLevel() == null ? "-1" : ""
				+ target.getSupervisoryLevel().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_SUPERVISORYLEVEL");
		map.put("argType", "PropertyDict");
		map.put("submitName", "supervisoryLevel.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "管理部门");
		map.put("tempValue", "" + temp.getSupervisoryDepartment());
		map.put("realValue", "" + target.getSupervisoryDepartment());
		map.put("argType", "str");
		map.put("submitName", "supervisoryDepartment");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "消防等级");
		map.put("tempValue", temp.getFireFightingLevel() == null ? "-1" : ""
				+ temp.getFireFightingLevel().getId());
		map.put("realValue", target.getFireFightingLevel() == null ? "-1" : ""
				+ target.getFireFightingLevel().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FIREFIGHTINGLEVEL");
		map.put("argType", "PropertyDict");
		map.put("submitName", "fireFightingLevel.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "治安负责人");
		map.put("tempValue", "" + temp.getSecurityChief());
		map.put("realValue", "" + target.getSecurityChief());
		map.put("argType", "str");
		map.put("submitName", "securityChief");
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

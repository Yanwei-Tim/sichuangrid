package com.tianque.plugin.dataManage.population.unsettledPopulationTemp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.baseInfo.unsettledPopulation.service.UnsettledPopulationService;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.util.DateUtil;
import com.tianque.domain.Organization;
import com.tianque.plugin.dataManage.base.AbstractDataManageController;
import com.tianque.plugin.dataManage.base.ReflectionUtil;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.unsettledPopulationTemp.domain.UnsettledPopulationTemp;
import com.tianque.plugin.dataManage.population.unsettledPopulationTemp.service.UnsettledPopulationTempService;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;

/*
 * 数据管理-未落户人员控制类*
 */
@Namespace("/plugin/dataManage/unsettledPopulationTempManage")
@Controller("unsettledPopulationTempController")
@Scope("request")
@Transactional
public class UnsettledPopulationTempController
		extends
		AbstractDataManageController<UnsettledPopulationTemp, UnsettledPopulation> {
	private Logger logger = Logger
			.getLogger(UnsettledPopulationTempController.class);
	@Autowired
	@Qualifier("actualPopulationDataManageService")
	private AbstractDataManageService actualPopulationDataManageService;
	@Autowired
	private UnsettledPopulationTempService unsettledPopulationTempService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private UnsettledPopulationService unsettledPopulationService;
	private UnsettledPopulationTemp unsettledPopulation;
	private String ajaxUrl;

	@Resource(name = "actualPopulationDataManageService")
	public void setDataManageService(
			AbstractDataManageService abstractDataManageServiceImpl) {
		replaceDataManageService(actualPopulationDataManageService);
	}

	private Long targetOrgId;// 要认领的目标网格的id
	private String viewBusinessPage;

	public String getBigType() {
		return DataManageBaseInfoTypes.POPULATION;
	}

	/**
	 * 测试认领处修改上一步下一步页面跳转
	 */
	@Action(value = "specialDispatch", results = {
			@Result(name = "update", location = "/template/dataManage/population/common/SpecialSubmitForUnsettledAndOversea.ftl"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String specialDispatch() throws Exception {
		ajaxUrl = "hasDuplicate";
		if (null != id) {
			// population = unsettledPopulationTempService.getTempById(id);
			// Organization org = ReflectionUtil
			// .getTargetOrganization(population);
			unsettledPopulation = unsettledPopulationTempService
					.getTempById(id);
			Organization org = ReflectionUtil
					.getTargetOrganization(unsettledPopulation);
			if (null != targetOrgId) {
				org.setId(targetOrgId);
			}
			org.setOrgName(ControllerHelper.getOrganizationRelativeName(
					org.getId(), organizationDubboService));

		}

		return "update";

	}

	/**
	 * 修改数据
	 * 
	 * @return
	 */
	@Action(value = "maintainUnsettledPopulationInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"unsettledPopulation", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainUnsettledPopulationInfo() throws Exception {
		unsettledPopulation = unsettledPopulationTempService
				.updateTemp(unsettledPopulation);
		return SUCCESS;
	}

	public UnsettledPopulationTemp getTemp() {
		return temp;
	}

	public void setTemp(UnsettledPopulationTemp temp) {
		this.temp = temp;
	}

	// public UnsettledPopulationTemp getPopulation() {
	// return population;
	// }
	public UnsettledPopulationTemp getPopulation() {
		return population;
	}

	public void setPopulation(UnsettledPopulationTemp population) {
		this.population = population;
	}

	@Override
	public UnsettledPopulation getTargetById(Long id) {
		return unsettledPopulationService.getUnsettledPopulationById(id);
	}

	@Override
	public List getCompareList(UnsettledPopulationTemp temp,
			UnsettledPopulation target) {
		List compareList = new ArrayList();
		compareList = DataManageBaseInfoUtil.getPopulationCommonList(temp,
				target);
		Map map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "持证种类");
		map.put("tempValue", temp.getCertificateType() == null ? "-1" : ""
				+ temp.getCertificateType().getId());
		map.put("realValue", target.getCertificateType() == null ? "-1" : ""
				+ target.getCertificateType().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@CERTIFICATEHOLD_TYPE");
		map.put("argType", "PropertyDict");
		map.put("submitName", "certificateType.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "持证编号");
		map.put("tempValue",
				temp.getCertificateNo() == null ? "-1" : ""
						+ temp.getCertificateNo());
		map.put("realValue", target.getCertificateNo() == null ? "-1" : ""
				+ target.getCertificateNo());
		map.put("argType", "str");
		map.put("submitName", "detoxicateCondition.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "未落户原因");
		map.put("tempValue", temp.getUnSettedReason() == null ? "-1" : ""
				+ temp.getUnSettedReason().getId());
		map.put("realValue", target.getUnSettedReason() == null ? "-1" : ""
				+ target.getUnSettedReason().getId());
		map.put("PropertyDict",
				"@com.tianque.domain.property.PropertyTypes@UNSETTEDREASON");
		map.put("argType", "PropertyDict");
		map.put("submitName", "unSettedReason.id");
		compareList.add(map);
		map = new HashMap();
		map.put("notNull", "false");
		map.put("label", "未落户时间");
		map.put("tempValue", "" + DateUtil.formateYMD(temp.getUnSettedTime()));
		map.put("realValue", "" + DateUtil.formateYMD(target.getUnSettedTime()));
		map.put("argType", "strDate");
		map.put("submitName", "unSettedTime");
		compareList.add(map);
		return compareList;
	}

	public Long getTargetOrgIdUn() {
		return targetOrgId;
	}

	public void setTargetOrgIdUn(Long targetOrgId) {
		this.targetOrgId = targetOrgId;
	}

	public String getViewBusinessPage() {
		return viewBusinessPage;
	}

	public void setViewBusinessPage(String viewBusinessPage) {
		this.viewBusinessPage = viewBusinessPage;
	}

	public UnsettledPopulationTemp getUnsettledPopulation() {
		return unsettledPopulation;
	}

	public void setUnsettledPopulation(
			UnsettledPopulationTemp unsettledPopulation) {
		this.unsettledPopulation = unsettledPopulation;
	}

	public String getAjaxUrl() {
		return ajaxUrl;
	}

	public void setAjaxUrl(String ajaxUrl) {
		this.ajaxUrl = ajaxUrl;
	}

	public UnsettledPopulationService getUnsettledPopulationService() {
		return unsettledPopulationService;
	}

	public void setUnsettledPopulationService(
			UnsettledPopulationService unsettledPopulationService) {
		this.unsettledPopulationService = unsettledPopulationService;
	}

}

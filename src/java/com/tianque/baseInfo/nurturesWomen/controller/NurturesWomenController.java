package com.tianque.baseInfo.nurturesWomen.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.controller.PopulationControllerAdapter;
import com.tianque.baseInfo.nurturesWomen.domain.NurturesWomen;
import com.tianque.baseInfo.nurturesWomen.service.NurturesWomenService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.LicenseSituation;
import com.tianque.domain.property.MaritalState;
import com.tianque.domain.vo.SearchNurturesWomenVo;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Namespace("/baseinfo/nurturesWomenManage")
@Controller("nurturesWomenController")
@Scope("prototype")
@Transactional
public class NurturesWomenController extends
		PopulationControllerAdapter<NurturesWomen> {
	private NurturesWomen population;

	@Autowired
	protected OrganizationDubboService organizationDubboService;

	@Autowired
	protected NurturesWomenService nurturesWomenService;

	@Autowired
	private PropertyDictService propertyDictService;

	private SearchNurturesWomenVo searchNurturesWomenVo;

	private String supervisorName;// 监管人员名称
	private Long orgId;

	private List<Long> initTargetId(String[] targetIds, int size) {
		List<Long> idLongs = new ArrayList<Long>();
		for (int i = size; i < targetIds.length; i++) {
			String tempId = targetIds[i];
			if (size == 0) {
				idLongs.add(Long.parseLong(targetIds[i]));
			} else {
				idLongs.add(Long.parseLong(tempId));
			}
		}
		return idLongs;
	}

	public NurturesWomen getPopulation() {
		return population;
	}

	public void setPopulation(NurturesWomen population) {
		this.population = population;
	}

	private PageInfo<NurturesWomen> emptyPage(int pageSize) {
		PageInfo<NurturesWomen> pageInfo = new PageInfo<NurturesWomen>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<NurturesWomen>());
		return pageInfo;
	}

	@Action(value = "nurturesWomenList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					nurturesWomenService.findNurturesWomenForPageByOrgId(
							organizationId, page, rows, sidx, sord,
							population.getIsEmphasis()), organizationDubboService,
					new String[] { "organization" }, organizationId));
		}
		return SUCCESS;
	}

	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/nurturesWomen/searchNurturesWomenDlg.jsp"),
					// @Result(name = "print", location =
					// "/baseinfo/nurturesWomen/searchNurturesWomenDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/nurturesWomen/viewNurturesWomenDlg.jsp"),
					@Result(name = "statistic", location = "/baseinfo/nurturesWomen/nurturesWomenStatistics.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/nurturesWomen/viewNurturesWomenDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		actionName = "maintainNurturesWomenBaseInfo";
		ajaxUrl = "hasDuplicateNurturesWomen";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/nurturesWomen/viewNurturesWomenDlg.jsp"),
			@Result(name = "statistic", location = "/baseinfo/nurturesWomen/nurturesWomenStatistics.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperateByEncrypt() throws Exception {
		actionName = "maintainNurturesWomenBaseInfo";
		ajaxUrl = "hasDuplicateNurturesWomen";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@Override
	protected NurturesWomen getPopulationFetchOrgById(Long id) {
		NurturesWomen population = nurturesWomenService
				.getNurturesWomenById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	@Action(value = "dispatchNurturesWomenBusiness", results = {
			@Result(name = "success", location = "/baseinfo/nurturesWomen/nurturesWomenDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchBusiness() throws Exception {
		if (null != id) {
			population = getPopulationFetchOrgById(id);
		}
		return SUCCESS;
	}

	@Action(value = "hasDuplicateNurturesWomen", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicatePopulation() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		hasDuplicatePopulation = nurturesWomenService
				.hasDuplicateNurturesWomen(organizationId,
						population.getIdCardNo(), population.getId());
		return SUCCESS;
	}

	@Action(value = "addNurturesWomen", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBusinessInfo() throws Exception {
		population = nurturesWomenService
				.updateNurturesWomenBusiness(population);
		return SUCCESS;
	}

	@Action(value = "addNurturesWomenForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "population", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String addNurturesWomenForMobile() throws Exception {
		population = nurturesWomenService.addNurturesWomen(population);
		return SUCCESS;
	}

	@Action(value = "updateNurturesWomen", results = { @Result(name = "success", type = "json", params = {
			"root", "population", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String updateNurturesWomen() throws Exception {
		population = nurturesWomenService.updateNurturesWomen(population);
		return SUCCESS;
	}

	@Action(value = "updateNurturesWomenForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "population", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String updateNurturesWomenForMobile() throws Exception {
		population = nurturesWomenService
				.updateNurturesWomeForMobile(population);
		return SUCCESS;
	}

	@Action(value = "maintainNurturesWomenBaseInfo", results = {
			@Result(name = "addcache", type = "json", params = { "root",
					"cacheId", "ignoreHierarchy", "false" }),
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = nurturesWomenService
					.updateNurturesWomenBaseInfo(population);
		} else {
			population = nurturesWomenService
					.addNurturesWomenBaseInfo(population);
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	@Actions({
			@Action(value = "viewCommonPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewCommonPopulation.jsp") }),
			@Action(value = "viewNurturesWomen", results = {
					@Result(name = "success", location = "/baseinfo/nurturesWomen/viewProfessionInfo.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "getCommonPopulationByIdForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"population", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	@Override
	public String viewInfo() throws Exception {
		if (population != null && population.getId() != null) {
			population = nurturesWomenService.getNurturesWomenById(population
					.getId());
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(), organizationDubboService));
		}
		// 拆分图片路径字符串
		if (null != population.getImgUrl()) {
			String[] value = population.getImgUrl().split(",");
			population.setImgUrl(value[0]);
		}
		return SUCCESS;
	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String updateEmphasiseById() throws Exception {
		populationIdList = nurturesWomenService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.NURTURESWOMEN_KEY,
						analyzePopulationIds());
		return SUCCESS;
	}

	/**
	 * 注销 ID加密
	 */
	@EncryptAnnotation
	@Action(value = "updateEmphasiseByEncryptId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseByEncryptId() throws Exception {
		populationIdList = nurturesWomenService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.NURTURESWOMEN_KEY,
						analyzePopulationIds());
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "deleteNurturesWomenByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String deleteByIds() throws Exception {
		nurturesWomenService.deleteNurturesWomenByIdList(Arrays
				.asList(analyzePopulationIds()));
		return SUCCESS;
	}

	private Long[] analyzePopulationIds() {
		String[] deleteId = populationIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		Long[] ids = new Long[idList.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idList.get(i);
		}
		return ids;
	}

	@Action(value = "updateDeathByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByIds() throws Exception {
		populations = nurturesWomenService.updateDeathOfNurturesWomenByIdList(
				Arrays.asList(analyzePopulationIds()), population.isDeath());
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "updateDeathByEncryptIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByEncryptIds() throws Exception {
		populations = nurturesWomenService.updateDeathOfNurturesWomenByIdList(
				Arrays.asList(analyzePopulationIds()), population.isDeath());
		return SUCCESS;
	}

	@Action(value = "validateType", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String validateType() throws Exception {
		PropertyDict propertyDict = null;
		if (searchNurturesWomenVo.getLicenseSituation() != null) {
			propertyDict = propertyDictService
					.getPropertyDictById(searchNurturesWomenVo
							.getLicenseSituation());
			if (propertyDict.getInternalId() == LicenseSituation.ALREADYLICENSE) {
				return SUCCESS;
			}
		}
		if (searchNurturesWomenVo.getMaritalState() != null) {
			propertyDict = propertyDictService
					.getPropertyDictById(searchNurturesWomenVo
							.getMaritalState());
			if (propertyDict.getInternalId() == MaritalState.ALREADYMARRIED) {
				return SUCCESS;
			}
		}
		return "error";
	}

	@Action(value = "findNurturesWomenByIdCardNoAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findNurturesWomenByIdCardNoAndOrgId() throws Exception {
		population = nurturesWomenService
				.getNurturesWomenByIdCardNoAndOrganizationId(population
						.getIdCardNo(), population.getOrganization().getId());

		return SUCCESS;
	}

	public SearchNurturesWomenVo getSearchNurturesWomenVo() {
		return searchNurturesWomenVo;
	}

	public void setSearchNurturesWomenVo(
			SearchNurturesWomenVo searchNurturesWomenVo) {
		this.searchNurturesWomenVo = searchNurturesWomenVo;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}

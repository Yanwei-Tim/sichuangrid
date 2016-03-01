package com.tianque.baseInfo.optimalObject.controller;

import java.util.ArrayList;
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
import com.tianque.baseInfo.optimalObject.domain.OptimalObject;
import com.tianque.baseInfo.optimalObject.service.OptimalObjectService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchOptimalObjectVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@SuppressWarnings("serial")
@Controller("optimalObjectController")
@Scope("prototype")
@Namespace("/baseinfo/optimalObjectManage")
@Transactional
public class OptimalObjectController extends
		PopulationControllerAdapter<OptimalObject> {

	@Autowired
	private OptimalObjectService optimalObjectService;

	@Autowired
	protected OrganizationDubboService organizationDubboService;

	private Long orgId;

	private OptimalObject optimalObject;

	private OptimalObject population;

	private SearchOptimalObjectVo searchOptimalObjectVo;

	private Boolean hasDuplicateOptimalObjectPopulation;
	private String supervisorName;// 监管人员名称

	private PageInfo<OptimalObject> emptyPage(int pageSize) {
		PageInfo<OptimalObject> pageInfo = new PageInfo<OptimalObject>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<OptimalObject>());
		return pageInfo;
	}

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

	@Action(value = "optimalObjectList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					optimalObjectService
							.findOptimalObjectForPageByOrgInternalCode(
									organizationId, page, rows, sidx, sord,
									population.getIsEmphasis()),
					organizationDubboService, new String[] { "organization" },
					organizationId));
		}
		return SUCCESS;
	}

	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/civilAdministration/optimalObject/searchOptimalObjectDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/civilAdministration/optimalObject/optimalObjectViewDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "statistic", location = "/baseinfo/civilAdministration/optimalObject/optimalObjectStatistics.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/civilAdministration/optimalObject/optimalObjectViewDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		actionName = "maintainOptimalObjectBaseInfo";
		ajaxUrl = "hasDuplicateOptimalObjectPopulation";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/civilAdministration/optimalObject/optimalObjectViewDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "statistic", location = "/baseinfo/civilAdministration/optimalObject/optimalObjectStatistics.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperateByEncrypt() throws Exception {
		actionName = "maintainOptimalObjectBaseInfo";
		ajaxUrl = "hasDuplicateOptimalObjectPopulation";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@Override
	protected OptimalObject getPopulationFetchOrgById(Long id) {
		OptimalObject population = optimalObjectService
				.getOptimalObjectById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	@Action(value = "dispatchOptimalObjectBusiness", results = {
			@Result(name = "success", location = "/baseinfo/civilAdministration/optimalObject/optimalObjectBusiness.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchBusiness() throws Exception {
		if (null != id) {
			population = getPopulationFetchOrgById(id);
		}
		return SUCCESS;
	}

	@Action(value = "hasDuplicateOptimalObjectPopulation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicatePopulation() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		hasDuplicatePopulation = optimalObjectService
				.hasDuplicateOptimalObject(organizationId,
						population.getIdCardNo(), population.getId());
		return SUCCESS;
	}

	@PermissionFilter(ename = "addOptimalObject")
	@Action(value = "addOptimalObject", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBusinessInfo() throws Exception {
		population = optimalObjectService
				.updateOptimalObjectBusiness(population);
		return SUCCESS;
	}

	@Action(value = "maintainOptimalObjectBaseInfo", results = {
			@Result(name = "addcache", type = "json", params = { "root",
					"cacheId", "ignoreHierarchy", "false" }),
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = optimalObjectService
					.updateOptimalObjectBaseInfo(population);
		} else {
			population = optimalObjectService
					.addOptimalObjectBaseInfo(population);
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	@Actions({
			@Action(value = "viewCommonPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewCommonPopulation.jsp") }),
			@Action(value = "viewOptimalObject", results = {
					@Result(name = "success", location = "/baseinfo/civilAdministration/optimalObject/optimalObjectView.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "getOptimalObjectByIdForMobile", results = {
					@Result(type = "json", name = "success", params = { "root",
							"population", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String viewInfo() throws Exception {
		if (population != null && population.getId() != null) {
			population = optimalObjectService.getOptimalObjectById(population
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
	public String updateEmphasiseById() throws Exception {
		populationIdList = optimalObjectService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.OPTIMALOBJECT_KEY,
						analyzePopulationIds());
		return SUCCESS;
	}

	/**
	 * ID加密 关注
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "updateEmphasiseByEncryptId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseByEncryptId() throws Exception {
		populationIdList = optimalObjectService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.OPTIMALOBJECT_KEY,
						analyzePopulationIds());
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

	@EncryptAnnotation
	@PermissionFilter(ename = "deleteOptimalObject")
	@Action(value = "deleteOptimalObjectByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		optimalObjectService.deleteOptimalObjectByIds(analyzePopulationIds());
		return SUCCESS;
	}

	@Action(value = "updateDeathByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByIds() throws Exception {
		populations = optimalObjectService.updateDeathByIds(
				analyzePopulationIds(), population.isDeath());
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "updateDeathByEncryptIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByEncryptIds() throws Exception {
		populations = optimalObjectService.updateDeathByIds(
				analyzePopulationIds(), population.isDeath());
		return SUCCESS;
	}

	@Action(value = "addOptimalObjectForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addOptimalObjectForMobile() throws Exception {
		population = optimalObjectService.addOptimalObject(population);
		return SUCCESS;
	}

	@Action(value = "updateOptimalObjectForMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateOptimalObjectForMobile() throws Exception {
		optimalObjectService.updateOptimalObjectBaseInfo(population);
		optimalObjectService.updateOptimalObjectBusiness(population);
		return SUCCESS;
	}

	@Action(value = "findOptimalObjectByIdCardNoAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findOptimalObjectByIdCardNoAndOrgId() throws Exception {
		population = optimalObjectService.getOptimalObjectIdCardNo(
				population.getIdCardNo(), orgId);

		return SUCCESS;
	}

	public OptimalObject getPopulation() {
		return population;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public OptimalObject getOptimalObject() {
		return optimalObject;
	}

	public void setOptimalObject(OptimalObject optimalObject) {
		this.optimalObject = optimalObject;
	}

	public void setPopulation(OptimalObject population) {
		this.population = population;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Boolean getHasDuplicateOptimalObjectPopulation() {
		return hasDuplicateOptimalObjectPopulation;
	}

	public SearchOptimalObjectVo getSearchOptimalObjectVo() {
		return searchOptimalObjectVo;
	}

	public void setSearchOptimalObjectVo(
			SearchOptimalObjectVo searchOptimalObjectVo) {
		this.searchOptimalObjectVo = searchOptimalObjectVo;
	}

	public void setHasDuplicateOptimalObjectPopulation(
			Boolean hasDuplicateOptimalObjectPopulation) {
		this.hasDuplicateOptimalObjectPopulation = hasDuplicateOptimalObjectPopulation;
	}
}
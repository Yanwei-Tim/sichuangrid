package com.tianque.baseInfo.elderlyPeople.controller;

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
import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.baseInfo.elderlyPeople.service.ElderlyPeopleService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/baseinfo/elderlyPeopleManage")
@Controller("elderlyPeopleController")
@Scope("prototype")
@Transactional
public class ElderlyPeopleController extends
		PopulationControllerAdapter<ElderlyPeople> {
	@Autowired
	protected OrganizationDubboService organizationDubboService;
	@Autowired
	protected ElderlyPeopleService elderlyPeopleService;

	private ElderlyPeople population;
	private String supervisorName;// 监管人员名称
	private String populationType;// 人员类型名称
	private Long orgId;

	@Action(value = "updateDeathByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathOfElderlyPeoples() throws Exception {
		String[] updateId = populationIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		elderlyPeopleService.updateDeathOfElderlyPeoplesByIdList(idList,
				population.isDeath());
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "updateDeathByEncryptIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByEncryptIds() throws Exception {
		String[] updateId = populationIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		elderlyPeopleService.updateDeathOfElderlyPeoplesByIdList(idList,
				population.isDeath());
		return SUCCESS;
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

	public ElderlyPeople getPopulation() {
		return population;
	}

	public void setPopulation(ElderlyPeople population) {
		this.population = population;
	}

	@Action(value = "dispatchElderlyPeopleBusiness", results = {
			@Result(name = "success", location = "/baseinfo/elderlyPeople/elderlyPeopleDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchBusiness() throws Exception {
		if (null != id) {
			population = getPopulationFetchOrgById(id);
		}
		return SUCCESS;
	}

	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/elderlyPeople/searchElderlyPeopleCondition.jsp"),
					// @Result(name = "print", location =
					// "/baseinfo/elderlyPeople/searchElderlyPeopleCondition.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/elderlyPeople/viewElderlyPeopleDlg.jsp"),
					@Result(name = "statistic", location = "/baseinfo/elderlyPeople/elderlyPeopleStatistics.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/elderlyPeople/viewElderlyPeopleDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		actionName = "mainElderPeopleBaseInfo";
		ajaxUrl = "hasDuplicateElderlyPeople";
		population = dispathBaseInfo(population);
		// 拆分图片路径字符串
		if (null != population.getImgUrl()) {
			String[] value = population.getImgUrl().split(",");
			population.setImgUrl(value[0]);
		}
		return getRetunString();
	}

	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/elderlyPeople/viewElderlyPeopleDlg.jsp"),
			@Result(name = "statistic", location = "/baseinfo/elderlyPeople/elderlyPeopleStatistics.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperateByEncrypt() throws Exception {
		actionName = "mainElderPeopleBaseInfo";
		ajaxUrl = "hasDuplicateElderlyPeople";
		population = dispathBaseInfo(population);
		// 拆分图片路径字符串
		if (null != population.getImgUrl()) {
			String[] value = population.getImgUrl().split(",");
			population.setImgUrl(value[0]);
		}
		return getRetunString();
	}

	@Override
	protected ElderlyPeople getPopulationFetchOrgById(Long id) {
		ElderlyPeople population = elderlyPeopleService
				.getElderlyPeopleById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	@Action(value = "elderlyPeopleList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					elderlyPeopleService
							.findElderlyPeopleForPageByOrgInternalCode(
									organizationId, page, rows, sidx, sord,
									population.getIsEmphasis()),
					organizationDubboService, new String[] { "organization" },
					organizationId));
		}
		return SUCCESS;
	}

	private PageInfo<ElderlyPeople> emptyPage(int pageSize) {
		PageInfo<ElderlyPeople> pageInfo = new PageInfo<ElderlyPeople>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<ElderlyPeople>());
		return pageInfo;
	}

	@EncryptAnnotation
	@PermissionFilter(ename = "deleteElderlyPeople")
	@Actions({
			@Action(value = "deleteElderlyPeopleByIds", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "deleteElderlyPeopleByIdsForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String deleteByIds() throws Exception {
		elderlyPeopleService.deleteElderlyPeoplesByIdList(Arrays
				.asList(analyzePopulationIds()));
		return SUCCESS;
	}

	@Action(value = "hasDuplicateElderlyPeople", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicatePopulation() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		} else {
			hasDuplicatePopulation = elderlyPeopleService
					.hasDuplicateElderlyPeople(organizationId,
							population.getIdCardNo(), population.getId());
			return SUCCESS;
		}
	}

	@Action(value = "mainElderPeopleBaseInfo", results = {
			@Result(name = "addcache", type = "json", params = { "root",
					"cacheId", "ignoreHierarchy", "false" }),
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = elderlyPeopleService
					.updateElderlyPeopleBaseInfo(population);
		} else {
			population = elderlyPeopleService
					.addElderlyPeopleBaseInfo(population);
		}
		return SUCCESS;
	}

	@Action(value = "addElderlyPeople", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBusinessInfo() throws Exception {
		population = elderlyPeopleService
				.updateElderlyPeopleBusiness(population);
		return SUCCESS;
	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		populationIdList = elderlyPeopleService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.ELDERLYPEOPLE_KEY,
						analyzePopulationIds());
		return SUCCESS;
	}

	/**
	 * ID加密 关注
	 * 
	 */
	@EncryptAnnotation
	@Action(value = "updateEmphasiseByEncryptId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseByEncryptId() throws Exception {
		populationIdList = elderlyPeopleService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.ELDERLYPEOPLE_KEY,
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
	@Actions({
			@Action(value = "viewCommonPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewCommonPopulation.jsp") }),
			@Action(value = "viewElderlyPeople", results = {
					@Result(name = "success", location = "/baseinfo/elderlyPeople/viewInElderlyPeople.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "getElderlyPeopleByIdForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"population", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String viewInfo() throws Exception {
		if (population != null && population.getId() != null) {
			population = elderlyPeopleService.getElderlyPeopleById(population
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

	@Action(value = "addElderlyPeopleForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addElderlyPeopleForMobile() throws Exception {
		population = elderlyPeopleService.addElderlyPeople(population);
		return SUCCESS;
	}

	@Action(value = "updateElderlyPeopleForMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateElderlyPeopleForMobile() throws Exception {
		elderlyPeopleService.updateElderlyPeopleBaseInfo(population);

		elderlyPeopleService.updateElderlyPeopleBusiness(population);

		return SUCCESS;
	}

	@Action(value = "findElderlyPeopleByIdCardNoAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findElderlyPeopleByIdCardNoAndOrgId() throws Exception {
		population = elderlyPeopleService
				.getElderlyPeopleByIdCardNoAndOrganizationId(
						population.getIdCardNo(), organizationId);

		return SUCCESS;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
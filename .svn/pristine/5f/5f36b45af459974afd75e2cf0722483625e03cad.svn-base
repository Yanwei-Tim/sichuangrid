package com.tianque.baseInfo.mPersonnel.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.base.controller.PopulationControllerAdapter;
import com.tianque.baseInfo.mPersonnel.domain.MPersonnel;
import com.tianque.baseInfo.mPersonnel.service.MPersonnelService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/baseinfo/mPersonnelManage")
@Controller("mPersonnelController")
@Scope("prototype")
public class MPersonnelController extends
		PopulationControllerAdapter<MPersonnel> {

	@Autowired
	MPersonnelService mPersonnelService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private MPersonnel population;

	private String supervisorName;// 监管人员名称
	private String populationType;// 人员类型名称
	private Long orgId;

	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/mPersonnel/searchMPersonnelsDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/mPersonnel/viewMPersonnelDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "statistic", location = "/baseinfo/mPersonnel/mPersonnelForStatistics.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/positiveInfo/viewPositiveinfoDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		actionName = "maintainMPersonnelBaseInfo";
		ajaxUrl = "hasDuplicateMPersonnel";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/mPersonnel/viewMPersonnelDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "statistic", location = "/baseinfo/mPersonnel/mPersonnelForStatistics.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperateByEncrypt() throws Exception {
		actionName = "maintainMPersonnelBaseInfo";
		ajaxUrl = "hasDuplicateMPersonnel";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@Action(value = "dispatchMPersonnelBusiness", results = {
			@Result(name = "success", location = "/baseinfo/mPersonnel/mPersonnelDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchBusiness() throws Exception {
		if (null != id) {
			population = getPopulationFetchOrgById(id);
		}
		return SUCCESS;
	}

	@Override
	protected MPersonnel getPopulationFetchOrgById(Long id) {
		MPersonnel population = mPersonnelService.getSimpleMPersonnelById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	private PageInfo<MPersonnel> emptyPage(int pageSize) {
		PageInfo<MPersonnel> pageInfo = new PageInfo<MPersonnel>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<MPersonnel>());
		return pageInfo;
	}

	@Action(value = "mPersonnelList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					mPersonnelService.findMPersonnelsForPageByOrgId(
							organizationId, page, rows, sidx, sord,
							population.getIsEmphasis()), organizationDubboService,
					new String[] { "organization" }, organizationId));
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	@Actions({
			@Action(value = "deleteMPersonnelByIdsForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "deleteMPersonnelByIds", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true", "excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	@Override
	public String deleteByIds() throws Exception {
		mPersonnelService.deleteMPersonnelByIds(Arrays
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

	@Action(value = "hasDuplicateMPersonnel", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String hasDuplicatePopulation() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		hasDuplicatePopulation = mPersonnelService.existMPersonnel(
				organizationId, population.getIdCardNo(), population.getId());
		return SUCCESS;
	}

	@Actions({
			@Action(value = "maintainMPersonnelBaseInfo", results = {
					@Result(name = "addcache", type = "json", params = {
							"root", "cacheId", "ignoreHierarchy", "false" }),
					@Result(name = "success", type = "json", params = { "root",
							"population", "ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "maintainMPersonnelBaseInfoForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	@Override
	public String maintainBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = mPersonnelService.updateMPersonnelBaseInfo(population);
		} else {
			population = mPersonnelService.addMPersonnelBaseInfo(population);
		}
		return SUCCESS;
	}

	@Action(value = "addMPersonnel", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String maintainBusinessInfo() throws Exception {
		population = mPersonnelService.updateMPersonnelBusiness(population);
		return SUCCESS;
	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String updateEmphasiseById() throws Exception {
		if (StringUtils.isEmpty(populationIds)) {
			errorMessage = "populationIds参数不正确";
			return ERROR;
		}
		Long[] ids = analyzePopulationIds();
		populationIdList = mPersonnelService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.MPERSONNEL_KEY, ids);
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
		if (StringUtils.isEmpty(populationIds)) {
			errorMessage = "populationIds参数不正确";
			return ERROR;
		}
		Long[] ids = analyzePopulationIds();
		populationIdList = mPersonnelService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.MPERSONNEL_KEY, ids);
		return SUCCESS;
	}

	@EncryptAnnotation
	@Actions({
			@Action(value = "viewCommonPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewCommonPopulation.jsp") }),
			@Action(value = "viewMPersonnel", results = {
					@Result(name = "success", location = "/baseinfo/mPersonnel/mPersonnelView.jsp"),
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
			population = mPersonnelService.getSimpleMPersonnelById(population
					.getId());
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(), organizationDubboService));
		}
		// 拆分图片路径字符串
		if (null != population && null != population.getImgUrl()) {
			String[] value = population.getImgUrl().split(",");
			population.setImgUrl(value[0]);
		}
		return SUCCESS;
	}

	@Action(value = "updateDeathByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByIds() throws Exception {
		populations = mPersonnelService.updateDeathByIds(
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
		populations = mPersonnelService.updateDeathByIds(
				Arrays.asList(analyzePopulationIds()), population.isDeath());
		return SUCCESS;
	}

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public MPersonnel getPopulation() {
		return population;
	}

	public void setPopulation(MPersonnel population) {
		this.population = population;
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

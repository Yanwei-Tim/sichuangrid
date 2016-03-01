package com.tianque.baseInfo.qPersonnel.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.base.controller.PopulationControllerAdapter;
import com.tianque.baseInfo.qPersonnel.domain.QPersonnel;
import com.tianque.baseInfo.qPersonnel.service.QPersonnelService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/baseinfo/qPersonnelManage")
@Controller("qPersonnelController")
@Scope("prototype")
public class QPersonnelController extends
		PopulationControllerAdapter<QPersonnel> {

	private static Logger logger = LoggerFactory
			.getLogger(QPersonnelController.class);

	@Autowired
	QPersonnelService qPersonnelService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private QPersonnel population;
	private Long orgId;
	private String supervisorName;// 监管人员名称
	private String populationType;// 人员类型名称

	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/qPersonnel/searchQPersonnelsDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/qPersonnel/viewQPersonnelDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "statistic", location = "/baseinfo/qPersonnel/qPersonnelForStatistics.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/positiveInfo/viewPositiveinfoDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		actionName = "maintainQPersonnelBaseInfo";
		ajaxUrl = "hasDuplicateQPersonnel";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/qPersonnel/viewQPersonnelDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "statistic", location = "/baseinfo/qPersonnel/qPersonnelForStatistics.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperateByEncrypt() throws Exception {
		actionName = "maintainQPersonnelBaseInfo";
		ajaxUrl = "hasDuplicateQPersonnel";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@Action(value = "dispatchQPersonnelBusiness", results = {
			@Result(name = "success", location = "/baseinfo/qPersonnel/qPersonnelDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchBusiness() throws Exception {
		if (null != id) {
			population = getPopulationFetchOrgById(id);
		}
		return SUCCESS;
	}

	@Override
	protected QPersonnel getPopulationFetchOrgById(Long id) {
		QPersonnel population = qPersonnelService.getSimpleQPersonnelById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	private PageInfo<QPersonnel> emptyPage(int pageSize) {
		PageInfo<QPersonnel> pageInfo = new PageInfo<QPersonnel>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<QPersonnel>());
		return pageInfo;
	}

	@Action(value = "qPersonnelList", results = {
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
					qPersonnelService.findQPersonnelsForPageByOrgId(
							organizationId, page, rows, sidx, sord,
							population.getIsEmphasis()), organizationDubboService,
					new String[] { "organization" }, organizationId));
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	@Actions({
			@Action(value = "deleteQPersonnelByIdsForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "deleteQPersonnelByIds", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true", "excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	@Override
	public String deleteByIds() throws Exception {
		qPersonnelService.deleteQPersonnelByIds(Arrays
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

	@Action(value = "hasDuplicateQPersonnel", results = {
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
		hasDuplicatePopulation = qPersonnelService.existQPersonnel(
				organizationId, population.getIdCardNo(), population.getId());
		return SUCCESS;
	}

	@Actions({
			@Action(value = "maintainQPersonnelBaseInfo", results = {
					@Result(name = "addcache", type = "json", params = {
							"root", "cacheId", "ignoreHierarchy", "false" }),
					@Result(name = "success", type = "json", params = { "root",
							"population", "ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "maintainQPersonnelBaseInfoForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	@Override
	public String maintainBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = qPersonnelService.updateQPersonnelBaseInfo(population);
		} else {
			population = qPersonnelService.addQPersonnelBaseInfo(population);
		}
		return SUCCESS;
	}

	@Action(value = "addQPersonnel", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String maintainBusinessInfo() throws Exception {
		population = qPersonnelService.updateQPersonnelBusiness(population);
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
		populationIdList = qPersonnelService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.QPERSONNEL_KEY, ids);
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
		populationIdList = qPersonnelService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.QPERSONNEL_KEY, ids);
		return SUCCESS;
	}

	@EncryptAnnotation
	@Actions({
			@Action(value = "viewCommonPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewCommonPopulation.jsp") }),
			@Action(value = "viewQPersonnel", results = {
					@Result(name = "success", location = "/baseinfo/qPersonnel/qPersonnelView.jsp"),
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
			population = qPersonnelService.getSimpleQPersonnelById(population
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
		populations = qPersonnelService.updateDeathByIds(
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
		populations = qPersonnelService.updateDeathByIds(
				Arrays.asList(analyzePopulationIds()), population.isDeath());
		return SUCCESS;
	}

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public QPersonnel getPopulation() {
		return population;
	}

	public void setPopulation(QPersonnel population) {
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

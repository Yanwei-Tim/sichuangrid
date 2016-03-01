package com.tianque.baseInfo.aidNeedPopulation.controller;

import java.util.ArrayList;
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
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.aidNeedPopulation.domain.AidNeedPopulation;
import com.tianque.baseInfo.aidNeedPopulation.service.AidNeedPopulationService;
import com.tianque.baseInfo.base.controller.PopulationControllerAdapter;
import com.tianque.baseInfo.druggy.controller.DruggyController;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.service.TemporaryPopulationService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("aidNeedPopulationController")
@Scope("prototype")
@Transactional
@Namespace("/baseinfo/aidNeedPopulationManage")
public class AidNeedPopulationController extends
		PopulationControllerAdapter<AidNeedPopulation> {
	private static Logger logger = LoggerFactory
			.getLogger(DruggyController.class);

	@Autowired
	private AidNeedPopulationService aidNeedPopulationService;
	@Autowired
	protected OrganizationDubboService organizationDubboService;
	@Autowired
	private TemporaryPopulationService temporaryPopulationService;

	private AidNeedPopulation population;

	private Long orgId;

	private Organization ownerOrg;
	private String supervisorName;// 监管人员名称

	public String getAidNeedPopulationBussiness() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			if (null != cacheId && null != cacheId.get("id")) {
				population = (AidNeedPopulation) temporaryPopulationService
						.getPopulationById(cacheId.get("id"));
			}
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			if (null != population && null != population.getId()) {
				population = aidNeedPopulationService
						.getAidNeedPopulationById(population.getId());
			}
			return SUCCESS;
		}
		return ERROR;
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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public AidNeedPopulation getPopulation() {
		return population;
	}

	public void setPopulation(AidNeedPopulation population) {
		this.population = population;
	}

	@Override
	public Long getOrganizationId() {
		return organizationId;
	}

	@Override
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Organization getOwnerOrg() {
		return ownerOrg;
	}

	public void setOwnerOrg(Organization ownerOrg) {
		this.ownerOrg = ownerOrg;
	}

	@Action(value = "aidNeedPopulationList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(new PageInfo<AidNeedPopulation>());
		} else {
			gridPage = new GridPage(
					ControllerHelper.processAllOrgRelativeName(
							aidNeedPopulationService
									.findAidNeedPopulationServiceForPageByOrganizationId(
											organizationId, page, rows, sidx,
											sord, population.getIsEmphasis()),
							organizationDubboService,
							new String[] { "organization" }, organizationId));
		}
		return SUCCESS;
	}

	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/civilAdministration/aidNeedPopulation/searchAidNeedPopulation.jsp"),
					@Result(name = "view", location = "/baseinfo/civilAdministration/aidNeedPopulation/aidNeedPopulationViewDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "statistic", location = "/baseinfo/civilAdministration/aidNeedPopulation/aidNeedPopulationListForStatistics.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/civilAdministration/aidNeedPopulation/aidNeedPopulationViewDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		actionName = "saveAidNeedPopulationInfo";
		ajaxUrl = "hasDuplicateAidNeedPopulation";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/civilAdministration/aidNeedPopulation/aidNeedPopulationViewDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "statistic", location = "/baseinfo/civilAdministration/aidNeedPopulation/aidNeedPopulationListForStatistics.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperateByEncrypt() throws Exception {
		actionName = "saveAidNeedPopulationInfo";
		ajaxUrl = "hasDuplicateAidNeedPopulation";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@Override
	protected AidNeedPopulation getPopulationFetchOrgById(Long id) {
		AidNeedPopulation population = aidNeedPopulationService
				.getAidNeedPopulationById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	@Action(value = "dispatchAidNeedPopulationBusiness", results = {
			@Result(name = "success", location = "/baseinfo/civilAdministration/aidNeedPopulation/aidNeedPopulationBusiness.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchBusiness() throws Exception {
		if (null != id) {
			population = getPopulationFetchOrgById(id);
		}
		return SUCCESS;
	}

	@Action(value = "hasDuplicateAidNeedPopulation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicatePopulation() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		hasDuplicatePopulation = aidNeedPopulationService
				.hasDuplicateAidNeedPopulation(organizationId,
						population.getIdCardNo(), population.getId());
		return SUCCESS;
	}

	@PermissionFilter(ename = "addAidNeedPopulation")
	@Action(value = "addAidNeedPopulation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBusinessInfo() throws Exception {
		population = aidNeedPopulationService
				.updateAidNeedPopulationBusiness(population);
		return SUCCESS;
	}

	@Action(value = "saveAidNeedPopulationInfo", results = {
			@Result(name = "addcache", type = "json", params = { "root",
					"cacheId", "ignoreHierarchy", "false" }),
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = aidNeedPopulationService
					.updateAidNeedPopulationBaseInfo(population);
		} else {
			population = aidNeedPopulationService
					.addAidNeedPopulationBaseInfo(population);
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	@Actions({
			@Action(value = "viewCommonPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewCommonPopulation.jsp") }),
			@Action(value = "viewAidNeedPopulation", results = {
					@Result(name = "success", location = "/baseinfo/civilAdministration/aidNeedPopulation/aidNeedPopulationView.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "getAidNeedPopulationByIdForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"population", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String viewInfo() throws Exception {
		if (population != null && population.getId() != null) {
			population = aidNeedPopulationService
					.getAidNeedPopulationById(population.getId());
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

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		populationIdList = aidNeedPopulationService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.AIDNEEDPOPULATION_KEY,
						analyzePopulationIds());
		return SUCCESS;
	}

	/**
	 * 取消关注 ID加密
	 */
	@EncryptAnnotation
	@Action(value = "updateEmphasiseByEncryptId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseByEncryptId() throws Exception {
		populationIdList = aidNeedPopulationService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.AIDNEEDPOPULATION_KEY,
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
	@PermissionFilter(ename = "deleteAidNeedPopulation")
	@Action(value = "deleteAidNeedPopulationByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		aidNeedPopulationService
				.deleteAidNeedPopulationByIds(analyzePopulationIds());
		return SUCCESS;
	}

	@Action(value = "updateDeathByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByIds() throws Exception {
		aidNeedPopulationService.updateDeathByIds(analyzePopulationIds(),
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
		aidNeedPopulationService.updateDeathByIds(analyzePopulationIds(),
				population.isDeath());
		return SUCCESS;
	}

	@Action(value = "addAidNeedPopulationForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addAidNeedPopulationForMobile() throws Exception {
		population = aidNeedPopulationService.addAidNeedPopulation(population);
		return SUCCESS;
	}

	@Action(value = "updateAidNeedPopulationForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateAidNeedPopulationForMobile() throws Exception {
		population = aidNeedPopulationService
				.updateAidNeedPopulation(population);
		return SUCCESS;
	}

	@Action(value = "findAidNeedPopulationByIdCardNoAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findAidNeedPopulationByIdCardNoAndOrgId() throws Exception {
		population = aidNeedPopulationService
				.getAidNeedPopulationByIdCardNoAndOrganizationId(
						population.getIdCardNo(), organizationId);

		return SUCCESS;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	// fateson add 数据转移操作
	Long targetOrgId;
	private String currentClassName = "";
	private Organization organization = null;
	// need move peoples id
	String ids = "";

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getCurrentClassName() {
		return currentClassName;
	}

	public void setCurrentClassName(String currentClassName) {
		this.currentClassName = currentClassName;
	}

	public Long getTargetOrgId() {
		return targetOrgId;
	}

	public void setTargetOrgId(Long targetOrgId) {
		this.targetOrgId = targetOrgId;
	}

	@Action(value = "shiftDispatch", results = {
			@Result(name = "success", location = "/common/shiftTree.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String shiftDispatch() throws Exception {

		if (orgId == null) {
			errorMessage = "组织机构不能为空";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(orgId);
		if (StringUtils.isBlank(currentClassName)) {
			errorMessage = "系统忙";
			logger.error("deal shiftDispatch occur Error，currentClassName is empty");
			return ERROR;
		}
		return SUCCESS;
	}

	String SEPARATOR = ".";

	/**
	 * 数据转移操作
	 * 
	 * @return
	 */

	@Action(value = "shiftPerson", results = {
			@Result(name = "success", type = "json", params = {}),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String shiftPerson() throws Exception {
		if (ids.equals("")) {
			errorMessage = "请选择要转移的数据";
			return ERROR;
		}

		if (orgId == 0 || targetOrgId == 0) {
			errorMessage = "请选择组织机构";
			return ERROR;
		}

		if (targetOrgId.equals(orgId)) {
			errorMessage = "请选择其他组织机构";
			return ERROR;
		}

		String[] moveids = ids.split(",");
		aidNeedPopulationService.moveTempByIds(moveids, targetOrgId);
		return SUCCESS;
	}

}

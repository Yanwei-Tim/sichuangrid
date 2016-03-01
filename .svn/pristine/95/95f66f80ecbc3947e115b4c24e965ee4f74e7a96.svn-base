package com.tianque.baseInfo.superiorVisit.controller;

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

import com.tianque.baseInfo.base.controller.PopulationControllerAdapter;
import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
import com.tianque.baseInfo.superiorVisit.service.SuperiorVisitService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/baseinfo/superiorVisitManage")
@Transactional
@Scope("request")
@SuppressWarnings("serial")
@Controller("superiorVisitController")
public class SuperiorVisitController extends
		PopulationControllerAdapter<SuperiorVisit> {
	private static Logger logger = LoggerFactory
			.getLogger(SuperiorVisitController.class);

	private SuperiorVisit population;

	private List<Long> visitTypes;

	private String visitType;

	private String conditions;

	@Autowired
	private SuperiorVisitService superiorVisitService;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	private String supervisorName;// 监管人员名称
	private String populationType;// 人员类型名称

	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/superiorVisit/searchSuperiorVisitDlg.jsp"),
					// @Result(name = "print", location =
					// "/baseinfo/superiorVisit/maintainSuperiorVisitPrintDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/superiorVisit/superiorVisitViewDlg.jsp"),
					@Result(name = "statistic", location = "/baseinfo/superiorVisit/superiorVisitListForStatistics.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/superiorVisit/superiorVisitViewDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		actionName = "maintainSuperiorVisitBaseInfo";
		ajaxUrl = "hasDuplicateSuperiorVisitPopulation";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/superiorVisit/superiorVisitViewDlg.jsp"),
			@Result(name = "statistic", location = "/baseinfo/superiorVisit/superiorVisitListForStatistics.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperateByEncrypt() {
		actionName = "maintainSuperiorVisitBaseInfo";
		ajaxUrl = "hasDuplicateSuperiorVisitPopulation";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@Override
	protected SuperiorVisit getPopulationFetchOrgById(Long id) {
		SuperiorVisit population = superiorVisitService
				.getSuperiorVisitById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	@Action(value = "hasDuplicateSuperiorVisitPopulation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicatePopulation() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		hasDuplicatePopulation = superiorVisitService
				.hasDuplicateSuperiorVisit(organizationId,
						population.getIdCardNo(), population.getId());
		return SUCCESS;
	}

	@Action(value = "hasDuplicateResultSuperiorVisitPopulation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicateResultSuperiorVisitPopulation() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		population = superiorVisitService.hasDuplicateSuperiorVisit(
				organizationId, population.getIdCardNo());
		if (population != null) {
			if (population.getSourcesState() != 1) {
				hasDuplicatePopulation = true;
			}
		}
		return SUCCESS;
	}

	/**
	 * 根据网格内置编码分页查询重点上访人员
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "superiorVisitList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					superiorVisitService
							.findSuperiorVisitForPageByOrgInternalCode(
									organizationId, page, rows, sidx, sord,
									population.getIsEmphasis()),
					organizationDubboService, new String[] { "organization" },
					organizationId));
		}
		return SUCCESS;
	}

	/**
	 * 新增重点上访人员
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "addSuperiorVisit")
	@Action(value = "addSuperiorVisit", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBusinessInfo() throws Exception {
		population = superiorVisitService.updateSuperiorVisitBusiness(
				population, visitTypes);
		return SUCCESS;
	}

	@Action(value = "addSuperiorVisitForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "population", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String addSuperiorVisitForMobile() throws Exception {
		if (null != visitType && !"".equals(visitType)) {
			String[] visitArr = visitType.split(",");
			visitTypes = new ArrayList();
			for (String visitTypeId : visitArr) {
				visitTypes.add(Long.parseLong(visitTypeId));

			}
		}
		population = superiorVisitService.addSuperiorVisitForMobile(population,
				visitTypes);
		return SUCCESS;
	}

	@Action(value = "updateSuperiorVisit", results = { @Result(name = "success", type = "json", params = {
			"root", "population", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String updateSuperiorVisit() throws Exception {
		if (null != visitType && !"".equals(visitType)) {
			String[] visitArr = visitType.split(",");
			visitTypes = new ArrayList();
			for (String visitTypeId : visitArr) {
				visitTypes.add(Long.parseLong(visitTypeId));
			}
		}
		SuperiorVisit temp = new SuperiorVisit();
		temp = population;
		population = superiorVisitService
				.updateSuperiorVisitBaseInfo(population);
		population = superiorVisitService.updateSuperiorVisitBusiness(temp,
				visitTypes);
		return SUCCESS;
	}

	@PermissionFilter(ename = "addSuperiorVisit")
	@Action(value = "maintainSuperiorVisitBaseInfo", results = {
			@Result(name = "addcache", type = "json", params = { "root",
					"cacheId", "ignoreHierarchy", "false" }),
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = superiorVisitService
					.updateSuperiorVisitBaseInfo(population);
		} else {
			population = superiorVisitService
					.addSuperiorVisitBaseInfo(population);
		}
		return SUCCESS;
	}

	@Action(value = "dispatchSuperiorVisitBusiness", results = {
			@Result(name = "success", location = "/baseinfo/superiorVisit/superiorVisitBusiness.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchBusiness() throws Exception {
		if (null != id) {
			population = getPopulationFetchOrgById(id);
		}
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

	/**
	 * 删除重点上访人员
	 * 
	 * @return SUCCESS
	 */
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteSuperiorVisit")
	@Action(value = "deleteSuperiorVisitByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		superiorVisitService.deleteSuperiorVisitByIds(analyzePopulationIds());
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

	/**
	 * 根据ID获取重点上访人员
	 * 
	 * @return SUCCESS
	 */
	@EncryptAnnotation
	@Actions({
			@Action(value = "viewCommonPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewCommonPopulation.jsp") }),
			@Action(value = "viewSuperiorVisit", results = {
					@Result(name = "success", location = "/baseinfo/superiorVisit/superiorVisitView.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "getCommonPopulationByIdForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"population", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String viewInfo() throws Exception {
		if (population != null && population.getId() != null) {
			population = superiorVisitService.getSuperiorVisitById(population
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

	private PageInfo<SuperiorVisit> emptyPage(int pageSize) {
		PageInfo<SuperiorVisit> pageInfo = new PageInfo<SuperiorVisit>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SuperiorVisit>());
		return pageInfo;
	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		populationIdList = superiorVisitService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.SUPERIORVISIT_KEY,
						analyzePopulationIds());
		return SUCCESS;
	}

	/**
	 * id加密 关注
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
		populationIdList = superiorVisitService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.SUPERIORVISIT_KEY,
						analyzePopulationIds());
		return SUCCESS;
	}

	@Action(value = "updateDeathByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByIds() throws Exception {
		populations = superiorVisitService.updateDeathByIds(
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
		populations = superiorVisitService.updateDeathByIds(
				analyzePopulationIds(), population.isDeath());
		return SUCCESS;
	}

	@Action(value = "findSuperiorVisitByIdCardNoAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSuperiorVisitByIdCardNoAndOrgId() throws Exception {
		population = superiorVisitService.getSuperiorVistByIdCardNoAndOrgId(
				population.getIdCardNo(), population.getOrganization().getId());

		return SUCCESS;
	}

	public SuperiorVisit getPopulation() {
		return population;
	}

	public void setPopulation(SuperiorVisit population) {
		this.population = population;
	}

	public List<Long> getVisitTypes() {
		return visitTypes;
	}

	public void setVisitTypes(List<Long> visitTypes) {
		this.visitTypes = visitTypes;
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

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	// fateson add 数据转移操作
	// current org id
	private Long orgId;
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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
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
		superiorVisitService.moveTempByIds(moveids, targetOrgId);
		return SUCCESS;
	}

}

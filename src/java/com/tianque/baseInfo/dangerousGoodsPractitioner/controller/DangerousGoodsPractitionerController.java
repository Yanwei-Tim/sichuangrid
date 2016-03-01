package com.tianque.baseInfo.dangerousGoodsPractitioner.controller;

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
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.controller.PopulationControllerAdapter;
import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
import com.tianque.baseInfo.dangerousGoodsPractitioner.service.DangerousGoodsPractitionerService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 危险品从业人员控制类
 * 
 * @author pursuer
 */

@Namespace("/baseinfo/dangerousGoodsPractitionerManage")
@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("dangerousGoodsPractitionerController")
public class DangerousGoodsPractitionerController extends
		PopulationControllerAdapter<DangerousGoodsPractitioner> {

	private static Logger logger = LoggerFactory
			.getLogger(DangerousGoodsPractitionerController.class);

	private DangerousGoodsPractitioner population;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private DangerousGoodsPractitionerService dangerousGoodsPractitionerService;
	private String supervisorName;// 监管人员名称
	private String populationType;// 人员类型名称

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

	private PageInfo<DangerousGoodsPractitioner> emptyPage(int pageSize) {
		PageInfo<DangerousGoodsPractitioner> pageInfo = new PageInfo<DangerousGoodsPractitioner>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<DangerousGoodsPractitioner>());
		return pageInfo;
	}

	public DangerousGoodsPractitioner getPopulation() {
		return population;
	}

	public void setPopulation(DangerousGoodsPractitioner population) {
		this.population = population;
	}

	@Action(value = "dispatchDangerousGoodsPractitionerBusiness", results = {
			@Result(name = "success", location = "/baseinfo/dangerousGoodsPractitioner/dangerousGoodsPractitionerNiz.jsp"),
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
					@Result(name = "search", location = "/baseinfo/dangerousGoodsPractitioner/dangerousGoodsPractitionerConditionDlg.jsp"),
					// @Result(name = "print", location =
					// "/baseinfo/dangerousGoodsPractitioner/maintainDangerousGoodsPractitioerPrintDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/dangerousGoodsPractitioner/dangerousGoodsPractitionerViewDlg.jsp"),
					@Result(name = "statistic", location = "/baseinfo/dangerousGoodsPractitioner/dangerousGoodsPractitionerListForStatistisc.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/dangerousGoodsPractitioner/dangerousGoodsPractitionerViewDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) })

	})
	public String dispatchOperate() throws Exception {
		actionName = "maintainDangerousGoodsPractitionerBaseInfo";
		ajaxUrl = "hasDuplicateDangerousGoodsPractitioner";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/dangerousGoodsPractitioner/dangerousGoodsPractitionerViewDlg.jsp"),
			@Result(name = "statistic", location = "/baseinfo/dangerousGoodsPractitioner/dangerousGoodsPractitionerListForStatistisc.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperateByEncrypt() throws Exception {
		actionName = "maintainDangerousGoodsPractitionerBaseInfo";
		ajaxUrl = "hasDuplicateDangerousGoodsPractitioner";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@Override
	protected DangerousGoodsPractitioner getPopulationFetchOrgById(Long id) {
		DangerousGoodsPractitioner population = dangerousGoodsPractitionerService
				.getSimpleDangerousGoodsPractitionerById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	@Action(value = "dangerousGoodsPractitionerList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					dangerousGoodsPractitionerService
							.findDangerousGoodsPractitionersForPageByOrgId(
									organizationId, page, rows, sidx, sord,
									population.getIsEmphasis()),
					organizationDubboService, new String[] { "organization" },
					organizationId));
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "deleteDangerousGoodsPractitionerByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		dangerousGoodsPractitionerService
				.deleteDangerousGoodsPractitionerByIds(Arrays
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

	@Action(value = "hasDuplicateDangerousGoodsPractitioner", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicatePopulation() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		hasDuplicatePopulation = dangerousGoodsPractitionerService
				.existDangerousGoodsPractitioner(organizationId,
						population.getIdCardNo(), population.getId());
		return SUCCESS;
	}

	@Action(value = "hasDuplicateResultDangerousGoodsPractitioner", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicateResultDangerousGoodsPractitioner()
			throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		population = dangerousGoodsPractitionerService
				.hasDuplicateDangerousGoodsPractitioner(organizationId,
						population.getIdCardNo());
		if (population != null) {
			if (population.getSourcesState() != 1) {
				hasDuplicatePopulation = true;
			}
		}
		return SUCCESS;
	}

	@Action(value = "maintainDangerousGoodsPractitionerBaseInfo", results = {
			@Result(name = "addcache", type = "json", params = { "root",
					"cacheId", "ignoreHierarchy", "false" }),
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = dangerousGoodsPractitionerService
					.updateDangerousGoodsPractitionerBaseInfo(population);
		} else {
			population = dangerousGoodsPractitionerService
					.addDangerousGoodsPractitionerBaseInfo(population);
		}
		return SUCCESS;
	}

	@Action(value = "addDangerousGoodsPractitioner", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBusinessInfo() throws Exception {
		population = dangerousGoodsPractitionerService
				.updateDangerousGoodsPractitionerBusiness(population);
		return SUCCESS;
	}

	@Action(value = "addDangerousGoodsPractitionerForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "population", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String addDangerousGoodsPractitionerForMobile() throws Exception {
		population = dangerousGoodsPractitionerService
				.addDangerousGoodsPractitionerForMobile(population);
		return SUCCESS;
	}

	@Action(value = "updateDangerousGoodsPractitioner", results = { @Result(name = "success", type = "json", params = {
			"root", "population", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String updateDangerousGoodsPractitioner() throws Exception {
		population = dangerousGoodsPractitionerService
				.updateDangerousGoodsPractitioner(population);
		return SUCCESS;
	}

	@Action(value = "updateDangerousGoodsPractitionerForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "population", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String updateDangerousGoodsPractitionerForMobile() throws Exception {
		population = dangerousGoodsPractitionerService
				.updateDangerousGoodsPractitionerForMobile(population);
		return SUCCESS;
	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		populationIdList = dangerousGoodsPractitionerService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY,
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
		populationIdList = dangerousGoodsPractitionerService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY,
						analyzePopulationIds());
		return SUCCESS;
	}

	@EncryptAnnotation
	@Actions({
			@Action(value = "viewCommonPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewCommonPopulation.jsp") }),
			@Action(value = "viewDangerousGoodsPractitioner", results = {
					@Result(name = "success", location = "/baseinfo/dangerousGoodsPractitioner/dangerousGoodsPractitionerView.jsp"),
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
			population = dangerousGoodsPractitionerService
					.getSimpleDangerousGoodsPractitionerById(population.getId());
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
		populations = dangerousGoodsPractitionerService.updateDeathByIds(
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
		populations = dangerousGoodsPractitionerService.updateDeathByIds(
				Arrays.asList(analyzePopulationIds()), population.isDeath());
		return SUCCESS;
	}

	@Action(value = "findDangerousGoodsPractitionerByIdCardNoAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSuperiorVisitByIdCardNoAndOrgId() throws Exception {
		population = dangerousGoodsPractitionerService
				.getDangerousGoodsPractitionerByIdCardNo(population
						.getIdCardNo(), population.getOrganization().getId());

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
		dangerousGoodsPractitionerService.moveTempByIds(moveids, targetOrgId);
		return SUCCESS;
	}

}

package com.tianque.baseInfo.idleYouth.controller;

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
import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.baseInfo.idleYouth.service.IdleYouthService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.service.impl.SchoolServiceImpl;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/baseinfo/idleYouthManage")
@Controller("idleYouthController")
@Scope("prototype")
@Transactional
public class IdleYouthController extends PopulationControllerAdapter<IdleYouth> {
	private static Logger logger = LoggerFactory
			.getLogger(IdleYouthController.class);
	@Autowired
	private IdleYouthService idleYouthService;

	private IdleYouth population;

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SchoolServiceImpl schoolService;
	// 自动完成填充学校名称
	private String schoolName;
	private List<String> schoolNamelist;

	private List<Long> staffTypeIds;
	private String staffTypeUpdateIds;

	private String staffType;
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

	@Action(value = "dispatchIdleYouthBusiness", results = {
			@Result(name = "success", location = "/baseinfo/idleYouth/idleYouthBusiness.jsp"),
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
					@Result(name = "search", location = "/baseinfo/idleYouth/searchIdleYouthCondition.jsp"),
					// @Result(name = "print", location =
					// "/baseinfo/idleYouth/maintainIdleYouthPrintDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/idleYouth/idleYouthViewDlg.jsp"),
					@Result(name = "statistic", location = "/baseinfo/idleYouth/idleYouthListForStatistics.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/idleYouth/idleYouthViewDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		actionName = "maintainIdleYouthBaseInfo";
		ajaxUrl = "hasDuplicateIdleYouth";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/idleYouth/idleYouthViewDlg.jsp"),
			@Result(name = "statistic", location = "/baseinfo/idleYouth/idleYouthListForStatistics.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperateByEncrypt() throws Exception {
		actionName = "maintainIdleYouthBaseInfo";
		ajaxUrl = "hasDuplicateIdleYouth";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@Override
	protected IdleYouth getPopulationFetchOrgById(Long id) {
		IdleYouth population = idleYouthService.getIdleYouthById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	@PermissionFilter(ename = "idleYouthManagement")
	@Action(value = "idleYouthList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					idleYouthService.findIdleYouthsForPageByOrganizationId(
							organizationId, page, rows, sidx, sord,
							population.getIsEmphasis()), organizationDubboService,
					new String[] { "organization" }, organizationId));
		}
		return SUCCESS;
	}

	private PageInfo<IdleYouth> emptyPage(int pageSize) {
		PageInfo<IdleYouth> pageInfo = new PageInfo<IdleYouth>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<IdleYouth>());
		return pageInfo;
	}

	@EncryptAnnotation
	@PermissionFilter(ename = "deleteIdleYouth")
	@Action(value = "deleteIdleYouthByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		idleYouthService.deleteIdleYouthByIds(analyzePopulationIds());
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

	@Action(value = "hasDuplicateIdleYouth", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicatePopulation() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		hasDuplicatePopulation = idleYouthService.hasDuplicateIdleYouth(
				organizationId, population.getIdCardNo(), population.getId());
		return SUCCESS;
	}

	@Action(value = "hasDuplicateResultIdleYouth", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicateResultIdleYouth() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		population = idleYouthService.hasDuplicateIdleYouth(organizationId,
				population.getIdCardNo());
		if (population != null) {
			if (population.getSourcesState() != 1) {
				hasDuplicatePopulation = true;
			}
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "addIdleYouth")
	@Action(value = "maintainIdleYouthBaseInfo", results = {
			@Result(name = "addcache", type = "json", params = { "root",
					"cacheId", "ignoreHierarchy", "false" }),
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = idleYouthService.updateIdleYouthBaseInfo(population);
		} else {
			population = idleYouthService.addIdleYouthBaseInfo(population);
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "addIdleYouth")
	@Action(value = "addIdleYouth", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBusinessInfo() throws Exception {
		population = idleYouthService.updateIdleYouthBusinessInfo(population,
				staffTypeIds);
		return SUCCESS;
	}

	@Action(value = "addIdleYouthForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "population", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String addIdleYouthForMobile() throws Exception {
		if (null != staffType && !"".equals(staffType)) {
			String[] staffTypes = staffType.split(",");
			for (String staffType : staffTypes) {
				if (staffTypeIds == null) {
					staffTypeIds = new ArrayList<Long>();
				}
				staffTypeIds.add(Long.parseLong(staffType));
			}
		}
		setIdleYouthStaffTypes();
		population = idleYouthService.addIdleYouth(population);
		return SUCCESS;
	}

	private void setIdleYouthStaffTypes() {
		List<PropertyDict> staffTypeList = new ArrayList<PropertyDict>();
		PropertyDict propertyDict;
		if (null != staffTypeIds && staffTypeIds.size() > 0) {
			for (Long id : staffTypeIds) {
				propertyDict = new PropertyDict();
				propertyDict.setId(id);
				staffTypeList.add(propertyDict);
			}
		}
		population.setStaffType(staffTypeList);
	}

	private void setIdleYouthStaffTypesForMobile() {
		List<PropertyDict> staffTypeList = new ArrayList<PropertyDict>();
		PropertyDict propertyDict;

		if (!StringUtils.isEmpty(staffTypeUpdateIds)) {
			if (staffTypeIds == null) {
				staffTypeIds = new ArrayList<Long>();
			}
			String[] arr = staffTypeUpdateIds.split(",");
			for (String s : arr) {
				staffTypeIds.add(Long.parseLong(s));
			}
		}
		if (null != staffTypeIds && staffTypeIds.size() > 0) {
			for (Long id : staffTypeIds) {
				propertyDict = new PropertyDict();
				propertyDict.setId(id);
				staffTypeList.add(propertyDict);
			}
		}
		population.setStaffType(staffTypeList);
	}

	@Action(value = "updateIdleYouth", results = { @Result(name = "success", type = "json", params = {
			"root", "population", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String updateIdleYouth() throws Exception {
		setIdleYouthStaffTypes();
		population = idleYouthService.updateIdleYouth(population);
		// staffTypeIds = new ArrayList();
		idleYouthService.regrantStaffTypeIds(population.getId(), staffTypeIds);
		return SUCCESS;
	}

	@Action(value = "updateIdleYouthForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String updateIdleYouthForMobile() throws Exception {
		setIdleYouthStaffTypesForMobile();
		population = idleYouthService.updateIdleYouth(population);
		idleYouthService.regrantStaffTypeIds(population.getId(), staffTypeIds);
		return SUCCESS;
	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		populationIdList = idleYouthService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.IDLEYOUTH_KEY, analyzePopulationIds());
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
		populationIdList = idleYouthService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.IDLEYOUTH_KEY, analyzePopulationIds());
		return SUCCESS;
	}

	@EncryptAnnotation
	@Actions({
			@Action(value = "viewCommonPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewCommonPopulation.jsp") }),
			@Action(value = "viewIdleYouth", results = {
					@Result(name = "success", location = "/baseinfo/idleYouth/idleYouthView.jsp"),
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
			population = idleYouthService.getFullIdleYouthById(population
					.getId());
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(), organizationDubboService));
			if (population.getStaffType() != null) {
				staffType = new String();
				for (int i = 0; i < population.getStaffType().size(); i++) {
					PropertyDict propertyDict = population.getStaffType()
							.get(i);
					if (i != population.getStaffType().size() - 1) {
						staffType += propertyDict.getDisplayName() + "，";
					} else {
						staffType += propertyDict.getDisplayName();
					}

				}
			}
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
		populations = idleYouthService.updateDeathByIds(
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
		populations = idleYouthService.updateDeathByIds(
				Arrays.asList(analyzePopulationIds()), population.isDeath());
		return SUCCESS;
	}

	@Action(value = "findIdleYouthByIdCardNoAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findIdleYouthByIdCardNoAndOrgId() throws Exception {
		population = idleYouthService.getIdleYouthByIdCardNoAndOrganizationId(
				population.getIdCardNo(), organizationId);
		return SUCCESS;
	}

	@Action(value = "autoCompleteSchoolName", results = { @Result(name = "success", type = "json", params = {
			"root", "schoolNamelist", "ignoreHierarchy", "false" }) })
	public String autoCompleteSchoolName() throws Exception {
		if (null != schoolName && !"".equals(schoolName))
			schoolNamelist = schoolService.autoCompleteSchoolName(schoolName);
		return "success";
	}

	public List<Long> getStaffTypeIds() {
		return staffTypeIds;
	}

	public void setStaffTypeIds(List<Long> staffTypeIds) {
		this.staffTypeIds = staffTypeIds;
	}

	public IdleYouth getPopulation() {
		return population;
	}

	public void setPopulation(IdleYouth population) {
		this.population = population;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
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
		idleYouthService.moveTempByIds(moveids, targetOrgId);
		return SUCCESS;
	}

	public String getStaffTypeUpdateIds() {
		return staffTypeUpdateIds;
	}

	public void setStaffTypeUpdateIds(String staffTypeUpdateIds) {
		this.staffTypeUpdateIds = staffTypeUpdateIds;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public List<String> getSchoolNamelist() {
		return schoolNamelist;
	}

	public void setSchoolNamelist(List<String> schoolNamelist) {
		this.schoolNamelist = schoolNamelist;
	}

}

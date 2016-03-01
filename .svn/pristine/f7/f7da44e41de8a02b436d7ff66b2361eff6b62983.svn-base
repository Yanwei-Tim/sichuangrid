package com.tianque.working.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.WorkCalendar;
import com.tianque.domain.property.DailyDirectoryTypes;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.StatementsReportedType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyYear;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyYearService;
import com.tianque.working.vo.DailyDirectoryVo;

@Controller("dailyYearController")
@Namespaces({ @Namespace("/daily/dailyYearManage"),
		@Namespace("/hotModuel/daily/dailyYearManage") })
@Scope("prototype")
@Transactional
public class DailyYearController extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private DailyYearService dailyYearService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;

	private DailyYear dailyYear;

	private List<DailyYear> dailyYears;

	private List<PropertyDict> propertyDicts;

	private List<Long> dialyDirectoryIds;

	private Long templateId;
	private List<String> set;
	private WorkCalendar workCalendar;
	private Organization currentOrganization;
	private int orgLevel;
	private int userOrgLevel;
	private boolean bol;
	private Organization childFirstOrg;
	private Organization organization;

	private String dailyDirectoryName;

	/**
	 * id加密业务跳转
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "nextStep", location = "/daily/dailyDirectory/dailyDirectoryList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperateByEncrypt() throws Exception {
		if ("nextStep".equals(mode) || "update".equals(mode)
				|| "view".equals(mode)) {
			prepareNextStep();
			return "nextStep";
		} else if ("add".equals(mode)) {
			prepareAddDailyYear();
			return SUCCESS;
		}
		this.errorMessage = "操作失败";
		return ERROR;
	}

	@Action(value = "dispatchOperate", results = {
			@Result(name = "nextStep", location = "/daily/dailyDirectory/dailyDirectoryList.jsp"),
			@Result(name = "success", location = "/daily/dailyDirectory/maintainDailyYear.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperate() throws Exception {
		if ("nextStep".equals(mode) || "update".equals(mode)
				|| "view".equals(mode)) {
			prepareNextStep();
			return "nextStep";
		} else if ("add".equals(mode)) {
			prepareAddDailyYear();
			return SUCCESS;
		}
		this.errorMessage = "操作失败";
		return ERROR;
	}

	@Action(value = "findDailyYearForPageByOrgId", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findDailyYearForPageByOrgId() throws Exception {
		PageInfo pageInfo = dailyYearService.findDailyYearForPageByOrgId(
				ThreadVariable.getUser().getOrganization().getId(), page, rows,
				sidx, sord);
		if (pageInfo == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(pageInfo);
		}
		return SUCCESS;
	}

	private PageInfo<DailyYear> emptyPage(int pageSize) {
		PageInfo<DailyYear> pageInfo = new PageInfo<DailyYear>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<DailyYear>());
		return pageInfo;
	}

	private void prepareAddDailyYear() {
		Calendar can = Calendar.getInstance();
		currentOrganization = organizationDubboService
				.getSimpleOrgById(ThreadVariable.getUser().getOrganization()
						.getId());
		createSetMap(can);
		createDailyYearName(can);
		getTemplateYear();
	}

	private String prepareNextStep() {
		if (dailyYear == null || dailyYear.getId() == null
				|| dailyYear.getId().longValue() <= 0) {
			this.errorMessage = "操作失败!";
			return ERROR;
		}
		dailyYear = dailyYearService.getSimpleDailyYearById(dailyYear.getId());
		return "nextStep";
	}

	private void createSetMap(Calendar can) {
		if (workCalendar == null) {
			workCalendar = new WorkCalendar();
			workCalendar.setYear((long) can.get(Calendar.YEAR));
		}
		set = new ArrayList<String>();
		set.add(String.valueOf(can.get(Calendar.YEAR) + 1));
		set.add(String.valueOf(can.get(Calendar.YEAR)));
		set.add(String.valueOf(can.get(Calendar.YEAR) - 1));
	}

	private void createDailyYearName(Calendar can) {
		dailyYear = new DailyYear();
		StringBuffer sb = new StringBuffer();
		sb.append(can.get(Calendar.YEAR) + 1);
		sb.append("年");
		sb.append(currentOrganization.getOrgName());
		sb.append("直属下辖台账目录");
		dailyYear.setName(sb.toString());
	}

	private void getTemplateYear() {
		dailyYears = new ArrayList<DailyYear>();
		dailyYears.add(dailyYearService.getInitYear());
		List<DailyYear> myMakedYears = dailyYearService
				.findDailyYearsByOrgId(ThreadVariable.getUser()
						.getOrganization().getId());
		if (myMakedYears != null && myMakedYears.size() > 0) {
			dailyYears.addAll(myMakedYears);
		}
	}

	// 目录列表
	@Action(value = "prepareDailyDirectoryList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String prepareDailyDirectoryList() throws Exception {
		gridPage = new GridPage();
		if (null != dailyYear && dailyYear.getId() > 0L) {
			List<DailyDirectoryVo> result = new ArrayList<DailyDirectoryVo>();
			dailyYear = dailyYearService.getSimpleDailyYearById(dailyYear
					.getId());
			if (null != dailyYear) {
				DailyDirectoryVo dailyDirectoryOne = new DailyDirectoryVo();

				dailyDirectoryOne.setDailyYear(dailyYear);
				dailyDirectoryOne.setFullName(dailyYear.getName());
				dailyDirectoryOne.setId(0L);
				dailyDirectoryOne.setLeaf(false);
				dailyDirectoryOne.setLevel(1);
				result.add(dailyDirectoryOne);
			}

			List<DailyDirectoryVo> dailyVoList = dailyYearService
					.findDailyDirectory(dailyYear.getId());

			List<DailyDirectoryVo> list = new ArrayList();
			for (int i = 0; i < dailyVoList.size(); i++) {
				DailyDirectoryVo dailyDirectoryVo = dailyVoList.get(i);
				List<DailyDirectory> subDailyDirectorys = dailyDirectoryService
						.findDailySubDirectoryByParentId(dailyDirectoryVo
								.getId());
				if (null == subDailyDirectorys
						|| subDailyDirectorys.size() <= 0) {
					dailyDirectoryVo.setShowClock(true);
					list.add(dailyDirectoryVo);
				} else {
					dailyDirectoryVo.setEffectiveDays(null);
					dailyDirectoryVo.setShowClock(false);
					list.add(dailyDirectoryVo);
				}
			}

			if (null != list && !list.isEmpty()) {
				result.addAll(list);
				gridPage.setRows(result);
			} else if (null != result && !result.isEmpty()) {
				List<DailyDirectoryVo> directoryList = new ArrayList();
				for (DailyDirectoryVo dailyDirectoryVo : result) {
					dailyDirectoryVo.setExpanded(false);
					directoryList.add(dailyDirectoryVo);
				}
				gridPage.setRows(directoryList);
			}

		}
		return SUCCESS;

	}

	@Action(value = "checkUnique", results = { @Result(name = "success", type = "json", params = {
			"root", "bol" }) })
	public String checkUnique() throws Exception {
		if (dailyYear == null || dailyYear.getName() == null
				|| "".equals(dailyYear.getName().trim())) {
			bol = false;
		}
		dailyYear.setMakedOrganization(ThreadVariable.getUser()
				.getOrganization());
		bol = !dailyYearService.checkUniqueDailyYearByOrgIdAndYear(dailyYear);
		return SUCCESS;
	}

	@PermissionFilter(ename = "addDailyDirectory")
	@Action(value = "addDailyYear", results = {
			@Result(type = "json", name = "success", params = { "root",
					"dailyYear", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addDailyYear() throws Exception {
		if (!validateByAddDailyYar()) {
			return ERROR;
		}
		if (templateId != null && dialyDirectoryIds != null
				&& 0 < dialyDirectoryIds.size()) {

			List<DailyDirectory> list = dailyDirectoryService
					.findRangeDailyDirectoryByDailyYearId(templateId,
							dialyDirectoryIds);
			for (DailyDirectory dailyDirectory : list) {
				if (null != dailyDirectory.getType()) {
					PropertyDict propertyDict = propertyDictService
							.getPropertyDictById(dailyDirectory.getType()
									.getId());
					if (propertyDict.getDisplayName().equals(
							DailyDirectoryTypes.SERVICE_MANAGEMENT_NAME)) {
						List<DailyDirectory> subDailyDirectorys = dailyDirectoryService
								.findDailySubDirectoryByParentId(dailyDirectory
										.getId());
						for (DailyDirectory directory : subDailyDirectorys) {
							if (dialyDirectoryIds.contains(directory.getId())) {
								dailyYear.setManagementSubmit("true");
							}
						}
						if (null == dailyYear.getManagementSubmit()) {
							dailyYear.setManagementSubmit("false");
						}
						dailyYear.setServiceManagement(dailyDirectory
								.getFullName());
					}
					if (propertyDict.getDisplayName().equals(
							DailyDirectoryTypes.STATEMENTS_REPORTED_NAME)) {
						List<DailyDirectory> subDailyDirectorys = dailyDirectoryService
								.findDailySubDirectoryByParentId(dailyDirectory
										.getId());
						for (DailyDirectory directory : subDailyDirectorys) {
							if (null != directory.getWhetherAdd()
									&& directory.getWhetherAdd() == 1
									&& null != directory.getType()) {
								PropertyDict dict = propertyDictService
										.getPropertyDictById(directory
												.getType().getId());
								if (dict.getDisplayName().equals(
										StatementsReportedType.CHECK_NAME)) {
									List<DailyDirectory> dailyDirectories = dailyDirectoryService
											.findDailySubDirectoryByParentId(directory
													.getId());
									for (DailyDirectory directory2 : dailyDirectories) {
										if (dialyDirectoryIds
												.contains(directory2.getId())) {
											dailyYear
													.setCheckNameSubmit("true");
										}
									}
									if (null == dailyYear.getCheckNameSubmit()) {
										dailyYear.setCheckNameSubmit("false");
									}
									dailyYear.setCheckName(directory
											.getFullName());
								}
								if (dict.getDisplayName().equals(
										StatementsReportedType.ISSUEDEAL_NAME)) {
									List<DailyDirectory> dailyDirectories = dailyDirectoryService
											.findDailySubDirectoryByParentId(directory
													.getId());

									for (DailyDirectory directory2 : dailyDirectories) {
										if (dialyDirectoryIds
												.contains(directory2.getId())) {
											dailyYear
													.setIssueDealNameSubmit("true");
										}
									}
									if (null == dailyYear
											.getIssueDealNameSubmit()) {
										dailyYear
												.setIssueDealNameSubmit("false");
									}
									dailyYear.setIssueDealName(directory
											.getFullName());
								}
							}
						}
					}
				}
			}
		}

		if ((dailyYear.getManagementSubmit() != null && dailyYear
				.getManagementSubmit().equals("false"))
				|| (dailyYear.getCheckNameSubmit() != null && dailyYear
						.getCheckNameSubmit().equals("false"))
				|| (dailyYear.getIssueDealNameSubmit() != null && dailyYear
						.getIssueDealNameSubmit().equals("false"))) {
			return SUCCESS;
		} else {
			dailyYear.setMakedOrganization(ThreadVariable.getUser()
					.getOrganization());
			dailyYear.setMakedOrgInternalCode(ThreadVariable.getUser()
					.getOrgInternalCode());
			dailyYear = dailyYearService.addDailyYear(dailyYear);
			dailyYear.setManagementSubmit("true");
			dailyYear.setCheckNameSubmit("true");
			dailyYear.setIssueDealNameSubmit("true");
			batchCopyDailyDirectory(dailyYear);
		}
		return SUCCESS;
	}

	private void batchCopyDailyDirectory(DailyYear dailyYear) {
		if (templateId == null || dialyDirectoryIds == null
				|| 0 == dialyDirectoryIds.size())
			return;
		List<DailyDirectory> list = dailyDirectoryService
				.findRangeDailyDirectoryByDailyYearId(templateId,
						dialyDirectoryIds);
		for (DailyDirectory dailyDirectory : list) {
			if (dailyDirectory.getParentDailyDirectory() == null) {
				dailyDirectory.setDailyYear(dailyYear);
				List<DailyDirectory> subDailyDirectorys = dailyDirectoryService
						.findDailySubDirectoryByParentId(dailyDirectory.getId());
				dailyDirectory = dailyDirectoryService
						.addDailyDirectory(dailyDirectory);
				for (DailyDirectory subDailyDirectory : subDailyDirectorys) {
					if (list.contains(subDailyDirectory)) {
						addCopyDirectory(dailyYear, subDailyDirectory,
								dailyDirectory, list);
					}
				}
			}
		}
	}

	private void addCopyDirectory(DailyYear dailyYear,
			DailyDirectory directory, DailyDirectory parentDirectory,
			List<DailyDirectory> list) {
		directory.setDailyYear(dailyYear);
		directory.setParentDailyDirectory(parentDirectory);
		List<DailyDirectory> subDailyDirectorys = dailyDirectoryService
				.findDailySubDirectoryByParentId(directory.getId());
		parentDirectory = dailyDirectoryService.addDailyDirectory(directory);
		for (DailyDirectory subDailyDirectory : subDailyDirectorys) {
			if (list.contains(subDailyDirectory)) {
				addCopyDirectory(dailyYear, subDailyDirectory, parentDirectory,
						list);
			}
		}
	}

	private boolean validateByAddDailyYar() {
		if (dailyYear == null) {
			this.errorMessage = "保存失败！";
			return false;
		}
		if (!StringUtil.isStringAvaliable(dailyYear.getName())) {
			this.errorMessage = "年度台帐名称不能为空";
			return false;
		}
		if (dailyYear.getYearDate() == null) {
			this.errorMessage = "台帐年度不能为空";
			return false;
		}
		Organization org = ThreadVariable.getUser().getOrganization();
		dailyYear.setMakedOrganization(org);
		dailyYear.setMakedOrgInternalCode(org.getOrgInternalCode());
		if (dailyYearService.checkUniqueDailyYearByOrgIdAndYear(dailyYear)) {
			this.errorMessage = dailyYear.getYearDate().toString()
					+ "年度已经有台帐目录，请选择修改";
			return false;
		}
		return true;
	}

	@PermissionFilter(ename = "updateDailyDirectory")
	@Action(value = "updateDailyYear", results = {
			@Result(type = "json", name = "success", params = { "root",
					"dailyYear", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDailyYear() {
		if (!validateByUpdateDailyYar())
			return ERROR;
		dailyYear = dailyYearService.updateDailyYear(dailyYear);
		return SUCCESS;
	}

	private boolean validateByUpdateDailyYar() {
		if (dailyYear == null || dailyYear.getId() == null) {
			this.errorMessage = "保存失败！";
			return false;
		}
		DailyYear oldDailyYear = dailyYearService
				.getSimpleDailyYearById(dailyYear.getId());
		if (oldDailyYear == null) {
			this.errorMessage = "保存失败！";
			return false;
		}
		if (oldDailyYear.getStatus() != null
				&& oldDailyYear.getStatus().equals(1L)) {
			this.errorMessage = "该台帐目录已经启用,请停用后再修改!";
			return false;
		}
		if (!StringUtil.isStringAvaliable(dailyYear.getName())) {
			this.errorMessage = "年度台帐名称不能为空";
			return false;
		}
		return true;
	}

	/**
	 * id加密启用
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "startDailyYearByEncrypt", results = {
			@Result(type = "json", name = "success", params = { "root",
					"dailyYear", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String startDailyYearByEncrypt() throws Exception {
		if (dailyYear == null || dailyYear.getId() == null) {
			this.errorMessage = "操作失败！";
			return ERROR;
		}
		dailyYear = dailyYearService.startDailyYearById(dailyYear.getId());
		return SUCCESS;
	}

	@Action(value = "startDailyYear", results = {
			@Result(type = "json", name = "success", params = { "root",
					"dailyYear", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String startDailyYear() throws Exception {
		if (dailyYear == null || dailyYear.getId() == null) {
			this.errorMessage = "操作失败！";
			return ERROR;
		}
		dailyYear = dailyYearService.startDailyYearById(dailyYear.getId());
		return SUCCESS;
	}

	/**
	 * id加密停用
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "stopDailyYearByEncrypt", results = {
			@Result(type = "json", name = "success", params = { "root",
					"dailyYear", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String stopDailyYearByEncrypt() throws Exception {
		if (dailyYear == null || dailyYear.getId() == null) {
			this.errorMessage = "操作失败！";
			return ERROR;
		}
		dailyYear = dailyYearService.stopDailyYearById(dailyYear.getId());
		return SUCCESS;
	}

	@Action(value = "stopDailyYear", results = {
			@Result(type = "json", name = "success", params = { "root",
					"dailyYear", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String stopDailyYear() throws Exception {
		if (dailyYear == null || dailyYear.getId() == null) {
			this.errorMessage = "操作失败！";
			return ERROR;
		}
		dailyYear = dailyYearService.stopDailyYearById(dailyYear.getId());
		return SUCCESS;
	}

	public String dailyYearList() {
		dailyYears = dailyYearService.findDailyYearList();
		return SUCCESS;
	}

	@Action(value = "findDailyDirectoryListByMakedOrgId", results = {
			@Result(name = "success", location = "/daily/dailyDirectory/dailyYearList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findDailyDirectoryListByMakedOrgId() throws Exception {
		dailyYears = dailyYearService.findDailyYearsByOrgId(ThreadVariable
				.getUser().getOrganization().getId());
		return SUCCESS;
	}

	@Actions({
			@Action(value = "findDailyYearsByParentOrgId", results = {
					@Result(name = "success", location = "/daily/dailyYear/dailyYearTree.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "findDailyYearsForCompletedIssue", results = { @Result(name = "success", type = "json", params = {
					"root", "dailyYears" }) }) })
	public String findDailyYearsByParentOrgId() throws Exception {
		if (currentOrganization == null) {
			currentOrganization = ThreadVariable.getUser().getOrganization();
			currentOrganization = organizationDubboService
					.getSimpleOrgById(currentOrganization.getId());
			PropertyDict orgLevelDict = propertyDictService
					.getPropertyDictById(currentOrganization.getOrgLevel()
							.getId());
			orgLevel = orgLevelDict.getInternalId();
			userOrgLevel = orgLevel;
		} else {
			currentOrganization = organizationDubboService
					.getSimpleOrgById(currentOrganization.getId());

			Organization userOrganization = ThreadVariable.getUser()
					.getOrganization();
			userOrganization = organizationDubboService
					.getSimpleOrgById(userOrganization.getId());

			orgLevel = propertyDictService.getPropertyDictById(
					currentOrganization.getOrgLevel().getId()).getInternalId();
			userOrgLevel = propertyDictService.getPropertyDictById(
					userOrganization.getOrgLevel().getId()).getInternalId();
		}
		if (currentOrganization.getParentOrg() != null) {
			dailyYears = dailyYearService.findDailyYearsByOrgIdAndStatus(
					currentOrganization.getParentOrg().getId(), 1L);
		} else {
			dailyYears = dailyYearService.findDailyYearsByOrgIdAndStatus(
					currentOrganization.getId(), 1L);
		}
		return SUCCESS;
	}

	@Action(value = "findReportWorkingRecordSubmitInfoByParentOrgId", results = {
			@Result(name = "success", location = "/daily/dailyYear/areaDailyYearTree.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findIssueSubmitInfoByParentOrgId() throws Exception {
		if (null == currentOrganization || null == currentOrganization.getId()) {
			currentOrganization = organizationDubboService
					.getSimpleOrgById(ThreadVariable.getUser()
							.getOrganization().getId());

			// fateson add 这里嘉兴市（1.1.3.） 下面的海宁市是（1.1.3.5.） 不是（1.1.3.1.）
			// 使用下面的查询有问题
			/*
			 * childFirstOrg = organizationDubboService
			 * .getOrganizationByOrgInternalCode(currentOrganization
			 * .getOrgInternalCode() + "1.");
			 */
			// 和杨帅讨论
			List<Organization> childOrgs = organizationDubboService.findOrganizationsByParentId(
					currentOrganization.getId());
			
			//  针对没有下辖的行政区域 或 职能部门的处理 
			if(childOrgs == null || childOrgs.isEmpty())
			{
				dailyYears = Collections.emptyList();
				childFirstOrg = organization  =  currentOrganization ;
				return SUCCESS;
			}
			
		   childFirstOrg = childOrgs.get(0) ;
			organization = childFirstOrg;
			PropertyDict orgLevelDict = propertyDictService
					.getPropertyDictById(currentOrganization.getOrgLevel()
							.getId());
			orgLevel = orgLevelDict.getInternalId();
		} else {
			childFirstOrg = organizationDubboService
					.getSimpleOrgById(currentOrganization.getId());
			organization = childFirstOrg;

			orgLevel = propertyDictService.getPropertyDictById(
					childFirstOrg.getOrgLevel().getId()).getInternalId();
		}
		if (null == childFirstOrg) {
			return SUCCESS;
		}
		propertyDicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.DAILYDIRECTORY_TYPE);
		dailyYears = dailyYearService.findDailyYearsByOrgIdAndStatus(
				childFirstOrg.getParentOrg().getId(), 1L);

		return SUCCESS;
	}

	@Action(value = "findDailyYearByParentOrgIdAndYear", results = {
			@Result(name = "success", type = "json", params = { "root",
					"dailyYear", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findDailyYearByParentOrgIdAndYear() throws Exception {
		dailyYear = dailyYearService.findDailyYearByParentOrgIdAndYear(
				currentOrganization.getId(), dailyYear.getName());
		return SUCCESS;
	}

	/**
	 * id加密删除
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteDailyDirectory")
	@Action(value = "deleteDailyYearByIdByEncrypt", results = {
			@Result(type = "json", name = "success", params = { "root", "bol" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteDailyYearByIdByEncrypt() throws Exception {
		bol = false;
		if (dailyYear == null || dailyYear.getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		this.errorMessage = dailyYearService.deleteDailyYearById(dailyYear
				.getId());
		if ("true".equals(this.errorMessage)) {
			bol = true;
			return SUCCESS;
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "deleteDailyDirectory")
	@Action(value = "deleteDailyYearById", results = {
			@Result(type = "json", name = "success", params = { "root", "bol" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteDailyYearById() throws Exception {
		bol = false;
		if (dailyYear == null || dailyYear.getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		this.errorMessage = dailyYearService.deleteDailyYearById(dailyYear
				.getId());
		if ("true".equals(this.errorMessage)) {
			bol = true;
			return SUCCESS;
		}
		return SUCCESS;
	}

	/**
	 * id加密验证
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "validateDeleteByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root", "bol" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String validateDeleteByEncrypt() throws Exception {
		if (dailyYear == null || dailyYear.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		this.errorMessage = dailyYearService
				.validateDeleteDailyYearById(dailyYear.getId());
		if ("true".equals(this.errorMessage)) {
			bol = true;
			return SUCCESS;
		}
		return ERROR;
	}

	@Action(value = "validateDelete", results = {
			@Result(name = "success", type = "json", params = { "root", "bol" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String validateDelete() throws Exception {
		if (dailyYear == null || dailyYear.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		this.errorMessage = dailyYearService
				.validateDeleteDailyYearById(dailyYear.getId());
		if ("true".equals(this.errorMessage)) {
			bol = true;
			return SUCCESS;
		}
		return ERROR;
	}

	@PermissionFilter(ename = "updateDailyDirectory")
	@Action(value = "whetherAutoStartDailyYear", results = {
			@Result(type = "json", name = "success", params = { "root",
					"dailyYear", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String whetherAutoStartDailyYear() throws Exception {
		dailyYear.getReminderDate();
		dailyYear = dailyYearService.setWhetherAutoStartDailyYear(dailyYear);
		return SUCCESS;
	}

	public DailyYear getDailyYear() {
		return dailyYear;
	}

	public void setDailyYear(DailyYear dailyYear) {
		this.dailyYear = dailyYear;
	}

	public List<DailyYear> getDailyYears() {
		return dailyYears;
	}

	public void setDailyYears(List<DailyYear> dailyYears) {
		this.dailyYears = dailyYears;
	}

	public List<Long> getDialyDirectoryIds() {
		return dialyDirectoryIds;
	}

	public void setDialyDirectoryIds(List<Long> dialyDirectoryIds) {
		this.dialyDirectoryIds = dialyDirectoryIds;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public List<String> getSet() {
		return set;
	}

	public void setSet(List<String> set) {
		this.set = set;
	}

	public WorkCalendar getWorkCalendar() {
		return workCalendar;
	}

	public void setWorkCalendar(WorkCalendar workCalendar) {
		this.workCalendar = workCalendar;
	}

	public Organization getCurrentOrganization() {
		return currentOrganization;
	}

	public void setCurrentOrganization(Organization currentOrganization) {
		this.currentOrganization = currentOrganization;
	}

	public PropertyDictService getPropertyDictService() {
		return propertyDictService;
	}

	public void setPropertyDictService(PropertyDictService propertyDictService) {
		this.propertyDictService = propertyDictService;
	}

	public List<PropertyDict> getPropertyDicts() {
		return propertyDicts;
	}

	public void setPropertyDicts(List<PropertyDict> propertyDicts) {
		this.propertyDicts = propertyDicts;
	}

	public int getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(int orgLevel) {
		this.orgLevel = orgLevel;
	}

	public int getUserOrgLevel() {
		return userOrgLevel;
	}

	public void setUserOrgLevel(int userOrgLevel) {
		this.userOrgLevel = userOrgLevel;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public Organization getChildFirstOrg() {
		return childFirstOrg;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getDailyDirectoryName() {
		return dailyDirectoryName;
	}

	public void setDailyDirectoryName(String dailyDirectoryName) {
		this.dailyDirectoryName = dailyDirectoryName;
	}
}

package com.tianque.working.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.DirectoryReportType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.StatementsReportedType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.constants.WorkingReportResultTypes;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyYear;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyYearService;

@Controller("statementsReportedController")
@Namespaces({ @Namespace("/daily/statementsReportedManage"),
		@Namespace("/hotModuel/daily/statementsReportedManage") })
@Scope("prototype")
@Transactional
public class StatementsReportedController extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger(StatementsReportedController.class);
	@Autowired
	private DailyYearService dailyYearService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private List<PropertyDict> propertyDicts;
	private List<DailyYear> dailyYears;
	private Organization currentOrganization;
	private Organization organization;
	private int typeInternalId;
	private int reportTypeInternalId;
	private Long dailyLogId;
	private Long orgId;
	private String msgtitle;
	private String reportedType;
	private String modeType;
	private DailyDirectory dailyDirectory;
	private Long dailyDirectoryId;

	@Action(value = "findStatementsReported", results = {
			@Result(name = "success", location = "/daily/statementsReported/dailyYearTreeForReport.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findStatementsReported() throws Exception {
		if (currentOrganization == null) {
			currentOrganization = ThreadVariable.getUser().getOrganization();
		}
		propertyDicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.DAILYDIRECTORY_TYPE);
		dailyYears = dailyYearService.findDailyYearsByOrgIdAndStatus(
				currentOrganization.getId(), 1L);
		return SUCCESS;
	}

	@Action(value = "findStatementsReportedList", results = {
			@Result(name = "publicSecurity", location = "/daily/statementsReported/publicSecurityRenovate/publicSecurityRenovateList.jsp"),
			@Result(name = "socialConflict", location = "/daily/statementsReported/socialconflictmediate/socialConflictRecordList.jsp"),
			@Result(name = "keyAreas", location = "/daily/statementsReported/keyAreasOfInvestigationInfo/keyAreasOfInvestigationInfoList.jsp"),
			@Result(name = "singificant", location = "/daily/statementsReported/singificantIssueseal/singificantIssuesealList.jsp"),
			@Result(name = "investigationRemediation", location = "/daily/statementsReported/investigationRemediation/investigationRemediationList.jsp"),
			@Result(name = "societySecurity", location = "/daily/societySecurity/societySecurityList.jsp"),
			@Result(name = "seriesSecurity", location = "/daily/seriesSecurity/seriesSecurityList.jsp"),
			@Result(name = "securityPropaganda", location = "/daily/securityPropaganda/securityPropagandaList.jsp"),
			@Result(name = "gridManagementNormal", location = "/daily/gridManagementNormal/gridManagementNormalList.jsp"),
			@Result(name = "serviceManagementCity", location = "/daily/serviceManagement/cityServiceManagementList.jsp"),
			@Result(name = "serviceManagementTown", location = "/daily/serviceManagement/townServiceManagementList.jsp"),
			@Result(name = "serviceManagementVillage", location = "/daily/serviceManagement/villageServiceManagementList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findStatementsReportedList() throws Exception {
		if (validateDailyDirectoryIsNull()) {
			return ERROR;
		}
		organization = organizationDubboService.getFullOrgById(ThreadVariable
				.getUser().getOrganization().getId());

		return dispathResult();
	}

	private String dispathResult() {
		dailyDirectoryId = dailyDirectory.getId();
		int internalId = dailyDirectory.getType().getInternalId();

		reportedType = WorkingReportResultTypes.getResultType(internalId);

		// 如果一级目录是报表类型，则选中后的效果跟选中其第一个子目录的效果一致
		if (WorkingReportResultTypes.isStatementsReportedType(internalId)) {
			reportedType = genarateResultType();
		}

		if (!WorkingReportResultTypes.isWorkingReportType(internalId)) {
			this.errorMessage = "操作失败，请联系管理员";
			reportedType = ERROR;
		}

		return reportedType;
	}

	private String genarateResultType() {
		List<DailyDirectory> dailyDirectories = dailyDirectoryService
				.findDailySubDirectoryByParentId(dailyDirectory.getId());
		DailyDirectory dailyDirectory = dailyDirectoryService
				.getFullDailyDirectoryById(dailyDirectories.get(0).getId());
		dailyDirectoryId = dailyDirectory.getId();
		return WorkingReportResultTypes.getResultType(dailyDirectory.getType()
				.getInternalId());
	}

	@Action(value = "backDailyLog", results = {
			@Result(name = "back", location = "/daily/statementsReported/backDailyLogDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String backDailyLog() throws Exception {
		if (null == dailyLogId || !StringUtil.isStringAvaliable(msgtitle)) {
			this.errorMessage = "操作失败，请联系管理员";
			return ERROR;
		}
		orgId = ThreadVariable.getUser().getOrganization().getId();
		return "back";
	}

	private boolean validateDailyDirectoryIsNull() {
		if (dailyDirectory == null || dailyDirectory.getId() == null) {
			this.errorMessage = "操作失败，请联系管理员";
			return true;
		}
		dailyDirectory = dailyDirectoryService
				.getFullDailyDirectoryById(dailyDirectory.getId());
		if (dailyDirectory == null || dailyDirectory.getId() == null) {
			this.errorMessage = "操作失败，请联系管理员";
			return true;
		}
		if (dailyDirectory.getType() == null
				|| dailyDirectory.getType().getId() == null) {
			this.errorMessage = "操作失败，请联系管理员";
			return true;
		}
		return false;
	}

	@Action(value = "findStatementsReportedDetailList", results = {
			@Result(name = "success", location = "/daily/statementsReported/statementsReportedList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findStatementsReportedDetailList() throws Exception {
		if (typeInternalId == StatementsReportedType.CHECK) {
			modeType = "security";
		} else if (typeInternalId == StatementsReportedType.INVESTIGATION_REMEDIATION) {
			modeType = "social";
		} else if (typeInternalId == StatementsReportedType.ISSUEDEAL) {
			modeType = "issueDeal";
		}
		if (reportTypeInternalId == DirectoryReportType.QUARTER_REPORT) {
			reportedType = "季报";
			return SUCCESS;
		}
		if (reportTypeInternalId == DirectoryReportType.SEMIYEARLY_REPORT) {
			reportedType = "半年报";
			return SUCCESS;
		}
		if (reportTypeInternalId == DirectoryReportType.YEAR_REPORT) {
			reportedType = "年报";
			return SUCCESS;
		}

		if (typeInternalId == StatementsReportedType.CHECK
				|| typeInternalId == StatementsReportedType.ISSUEDEAL
				|| reportTypeInternalId == DirectoryReportType.MONTH_REPORT) {
			reportedType = "月报";
			return SUCCESS;
		}
		if (typeInternalId == StatementsReportedType.INVESTIGATION
				|| typeInternalId == StatementsReportedType.SIGNIFICANT_ISSUEDEAL) {
			reportedType = "月报";
			return SUCCESS;
		}
		reportedType = "月报";
		return SUCCESS;
	}

	public List<DailyYear> getDailyYears() {
		return dailyYears;
	}

	public void setDailyYears(List<DailyYear> dailyYears) {
		this.dailyYears = dailyYears;
	}

	public Organization getCurrentOrganization() {
		return currentOrganization;
	}

	public void setCurrentOrganization(Organization currentOrganization) {
		this.currentOrganization = currentOrganization;
	}

	public List<PropertyDict> getPropertyDicts() {
		return propertyDicts;
	}

	public void setPropertyDicts(List<PropertyDict> propertyDicts) {
		this.propertyDicts = propertyDicts;
	}

	public int getTypeInternalId() {
		return typeInternalId;
	}

	public void setTypeInternalId(int typeInternalId) {
		this.typeInternalId = typeInternalId;
	}

	public int getReportTypeInternalId() {
		return reportTypeInternalId;
	}

	public void setReportTypeInternalId(int reportTypeInternalId) {
		this.reportTypeInternalId = reportTypeInternalId;
	}

	public String getReportedType() {
		return reportedType;
	}

	public void setReportedType(String reportedType) {
		this.reportedType = reportedType;
	}

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	public Long getDailyLogId() {
		return dailyLogId;
	}

	public void setDailyLogId(Long dailyLogId) {
		this.dailyLogId = dailyLogId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getMsgtitle() {
		return msgtitle;
	}

	public void setMsgtitle(String msgtitle) {
		this.msgtitle = msgtitle;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getDailyDirectoryId() {
		return dailyDirectoryId;
	}

	public void setDailyDirectoryId(Long dailyDirectoryId) {
		this.dailyDirectoryId = dailyDirectoryId;
	}

}

package com.tianque.plugin.account.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.account.api.LedgerPoorPeopleDubboService;
import com.tianque.account.api.LedgerSteadyWorkDubboService;
import com.tianque.account.api.ThreeRecordsIssueDubboService;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Controller("threeRecordsPageHomeController")
@Scope("request")
@Namespace("/threeRecordsIssue/pageHomeManage")
public class ThreeRecordsPageHomeController extends BaseAction {

	private Long keyId;
	private String seachValue;
	private Integer page;
	private Integer rows;
	private String sidx;
	private String sord;
	private Long issueType;
	private String leaderView;
	private Integer viewProcess;
	private Long sourceType;
	private Long orgLevel;
	private Integer orgLevelInternalId;
	private Integer sourceTypeInternalId;
	private Long functionalOrgType;// 职能部门类型
	private Integer year;
	private Integer month;

	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private LedgerPoorPeopleDubboService ledgerPoorPeopleDubboService;
	@Autowired
	private LedgerSteadyWorkDubboService ledgerSteadyWorkDubboService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ThreeRecordsIssueDubboService threeRecordsIssueDubboService;

	@Action(value = "findIssueForView", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findIssueForView() throws Exception {
		if (orgLevel == null && orgLevelInternalId != null) {
			List<PropertyDict> orgLevels = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.ORGANIZATION_LEVEL,
							orgLevelInternalId);
			if (orgLevels != null && orgLevels.size() > 0
					&& orgLevels.get(0) != null) {
				orgLevel = orgLevels.get(0).getId();
			}
		}
		if (sourceType == null && sourceTypeInternalId != null) {
			List<PropertyDict> sourceTypes = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.SOURCE_KIND, sourceTypeInternalId);
			if (sourceTypes != null && sourceTypes.size() > 0
					&& sourceTypes.get(0) != null) {
				sourceType = sourceTypes.get(0).getId();
			}
		}
		return findJurisdictionsNeedDo(issueType);
	}

	private String findJurisdictionsNeedDo(Long issueType) {
		PageInfo issues = null;
		if (!legalKeyIdParam()) {
			errorMessage = "参数错误";
			return ERROR;
		}
		if (issueType == LedgerConstants.POORPEOPLE) {
			issues = ledgerPoorPeopleDubboService.findJurisdictionsNeedDo(
					seachValue, keyId, page, rows, sidx, sord, issueType,
					orgLevel, leaderView, functionalOrgType, viewProcess,
					sourceType, year, month);
		} else if (issueType == LedgerConstants.STEADYWORK) {
			issues = ledgerSteadyWorkDubboService.findJurisdictionsNeedDo(
					seachValue, keyId, page, rows, sidx, sord, issueType,
					orgLevel, leaderView, functionalOrgType, viewProcess,
					sourceType, year, month);
		} else {
			issues = threeRecordsIssueDubboService.findJurisdictionsNeedDo(
					seachValue, keyId, page, rows, sidx, sord, issueType, year,
					month);
		}
		String[] params = ThreeRecordsIssueOperationUtil
				.getViewprocessParams(viewProcess);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, params, null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	private boolean legalKeyIdParam() {
		return null != keyId;
	}

	public Long getKeyId() {
		return keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public String getSeachValue() {
		return seachValue;
	}

	public void setSeachValue(String seachValue) {
		this.seachValue = seachValue;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public Long getIssueType() {
		return issueType;
	}

	public void setIssueType(Long issueType) {
		this.issueType = issueType;
	}

	public String getLeaderView() {
		return leaderView;
	}

	public void setLeaderView(String leaderView) {
		this.leaderView = leaderView;
	}

	public Integer getViewProcess() {
		return viewProcess;
	}

	public void setViewProcess(Integer viewProcess) {
		this.viewProcess = viewProcess;
	}

	public Long getSourceType() {
		return sourceType;
	}

	public void setSourceType(Long sourceType) {
		this.sourceType = sourceType;
	}

	public Long getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Long orgLevel) {
		this.orgLevel = orgLevel;
	}

	public Integer getOrgLevelInternalId() {
		return orgLevelInternalId;
	}

	public void setOrgLevelInternalId(Integer orgLevelInternalId) {
		this.orgLevelInternalId = orgLevelInternalId;
	}

	public Integer getSourceTypeInternalId() {
		return sourceTypeInternalId;
	}

	public void setSourceTypeInternalId(Integer sourceTypeInternalId) {
		this.sourceTypeInternalId = sourceTypeInternalId;
	}

	public Long getFunctionalOrgType() {
		return functionalOrgType;
	}

	public void setFunctionalOrgType(Long functionalOrgType) {
		this.functionalOrgType = functionalOrgType;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}

package com.tianque.issue.controller;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.constants.IssueViewType;
import com.tianque.issue.domain.IssueApplyDelay;
import com.tianque.issue.service.IssueApplyDelayService;
import com.tianque.issueLeaderViewCache.service.IssueLeaderViewCacheService;
import com.tianque.service.WorkCalendarService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * @ClassName: IssueApplyDelayController
 * @Description: 延期申请控制器
 * @author wangxiaohu wsmalltiger@163.com
 * @date Nov 25, 2013 3:29:52 PM
 */
@Scope("prototype")
@Namespace("/issues/issueApplyDelay")
@Controller("issueApplyDelayController")
public class IssueApplyDelayController extends BaseAction {

	private Integer workDay = 0;

	@Autowired
	private WorkCalendarService workCalendarService;
	@Autowired
	private IssueApplyDelayService issueApplyDelayService;
	@Autowired
	private PropertyDictService propertyDictService;

	private IssueApplyDelay issueApplyDelay;// 申请延期实体
	private Long issueStepId;
	private Long orgId;
	private Integer orgLevelInternalId;
	private Long functionalOrgType;
	private int auditDelayCount;
	@Autowired
	private IssueLeaderViewCacheService issueLeaderViewCacheService;

	@Action(value = "getWorkDayByDate", results = {
			@Result(name = "success", type = "json", params = { "root",
					"workDay" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getWorkDayByDate() throws Exception {
		if (issueApplyDelay != null && issueApplyDelay.getDelayDate() != null) {
			workDay = workCalendarService.getWorkDaysFromStartTimeToEndTime(
					new Date(), issueApplyDelay.getDelayDate());
		}
		return SUCCESS;
	}

	@Actions({
			@Action(value = "dispatch", results = {
					@Result(name = "success", location = "/issue/issueManage/auditDelayDlg.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOfBench", results = {
					@Result(name = "success", location = "/workBench/issueManage/auditDelayDlg.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatch() throws Exception {
		if (issueStepId == null) {
			errorMessage = "操作失败，请联系管理员！";
			return ERROR;
		}
		issueApplyDelay = issueApplyDelayService
				.getIssueApplyDelayByIssueStepIdAndDelayState(issueStepId);
		return SUCCESS;
	}

	@Actions({
			@Action(value = "applyDelay", results = {
					@Result(name = "success", type = "json", params = { "root",
							"issueApplyDelay", "ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "applyDelayForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String applyDelay() throws Exception {
		if (issueApplyDelay != null && issueApplyDelay.getIssueStepId() != null) {
			issueApplyDelay = issueApplyDelayService
					.applyDelay(issueApplyDelay);
		} else {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "auditDelay", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueApplyDelay", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String auditDelay() throws Exception {
		if (issueApplyDelay != null && issueApplyDelay.getId() != null) {
			issueApplyDelay = issueApplyDelayService
					.auditDelay(issueApplyDelay);
		} else {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "getJurisdictionsAuditDelayCount", results = {
			@Result(type = "json", name = "success", params = { "root",
					"auditDelayCount", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getJurisdictionsAuditDelayCount() throws Exception {
		Long orgLevel = null;
		if (orgLevelInternalId != null) {
			List<PropertyDict> orgLevels = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.ORGANIZATION_LEVEL,
							orgLevelInternalId);
			if (orgLevels != null && orgLevels.size() > 0
					&& orgLevels.get(0) != null) {
				orgLevel = orgLevels.get(0).getId();
			}
		}
		if (orgId == null || orgLevel == null) {
			throw new BusinessValidationException("参数错误");
		}
		if (ThreadVariable.getOrganization().getOrgLevel().getInternalId() == OrganizationLevel.PROVINCE
				&& functionalOrgType == null) {
			String key = IssueViewType.CHECKPENDING + "_"
					+ ThreadVariable.getOrganization().getId() + "_" + orgLevel
					+ "_count";
			setAuditDelayCount(issueLeaderViewCacheService
					.getCountByCacheKey(key));
		} else {
			setAuditDelayCount(issueApplyDelayService
					.getJurisdictionsAuditDelayCount(orgLevel, orgId,
							functionalOrgType));
		}
		return SUCCESS;
	}

	/**
	 * 获取待办已经督办事件的个数
	 */
	public String getOverseerIssueCountForMobile() throws Exception {
		Long orgLevel = null;
		if (orgLevelInternalId != null) {
			List<PropertyDict> orgLevels = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.ORGANIZATION_LEVEL,
							orgLevelInternalId);
			if (orgLevels != null && orgLevels.size() > 0
					&& orgLevels.get(0) != null) {
				orgLevel = orgLevels.get(0).getId();
			}
		}
		if (orgId == null || orgLevel == null) {
			throw new BusinessValidationException("参数错误");
		}
		setAuditDelayCount(issueApplyDelayService
				.getOverseerIssueCountForMobile(orgLevel, orgId,
						functionalOrgType));
		return SUCCESS;

	}

	/**
	 * 获取待办事件的个数
	 */
	public String getJurisdictionsNeedDoCountForMobile() throws Exception {
		Long orgLevel = null;
		if (orgLevelInternalId != null) {
			List<PropertyDict> orgLevels = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.ORGANIZATION_LEVEL,
							orgLevelInternalId);
			if (orgLevels != null && orgLevels.size() > 0
					&& orgLevels.get(0) != null) {
				orgLevel = orgLevels.get(0).getId();
			}
		}
		if (orgId == null || orgLevel == null) {
			throw new BusinessValidationException("参数错误");
		}
		setAuditDelayCount(issueApplyDelayService
				.getJurisdictionsNeedDoCountForMobile(orgLevel, orgId,
						functionalOrgType));
		return SUCCESS;

	}

	@Action(value = "findIssueDelayList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findIssueDelayList() throws Exception {
		if (issueStepId == null) {
			errorMessage = "操作失败，请联系管理员！";
			return ERROR;
		}
		gridPage = new GridPage(issueApplyDelayService.findIssueDelayList(
				issueStepId, page, rows, sidx, sord));
		return SUCCESS;
	}

	public Integer getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Integer workDay) {
		this.workDay = workDay;
	}

	public IssueApplyDelay getIssueApplyDelay() {
		return issueApplyDelay;
	}

	public void setIssueApplyDelay(IssueApplyDelay issueApplyDelay) {
		this.issueApplyDelay = issueApplyDelay;
	}

	public Long getIssueStepId() {
		return issueStepId;
	}

	public void setIssueStepId(Long issueStepId) {
		this.issueStepId = issueStepId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setAuditDelayCount(int auditDelayCount) {
		this.auditDelayCount = auditDelayCount;
	}

	public int getAuditDelayCount() {
		return auditDelayCount;
	}

	public Integer getOrgLevelInternalId() {
		return orgLevelInternalId;
	}

	public void setOrgLevelInternalId(Integer orgLevelInternalId) {
		this.orgLevelInternalId = orgLevelInternalId;
	}

	public Long getFunctionalOrgType() {
		return functionalOrgType;
	}

	public void setFunctionalOrgType(Long functionalOrgType) {
		this.functionalOrgType = functionalOrgType;
	}

}
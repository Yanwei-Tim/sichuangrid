package com.tianque.issue.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.constants.IssueViewType;
import com.tianque.issue.domain.CompletedIssue;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.service.SearchIssueByLevelService;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Controller("searchIssueBylevelController")
@Scope("prototype")
@Namespace("/issues/searchIssueByLevelManage")
public class SearchIssueByLevelController extends BaseAction {
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private SearchIssueByLevelService searchIssueByLevelService;
	private SearchIssueVoNew searchIssueVo;

	private String viewType;// 视图类型
	private String statusType;// 是否历史事件
	private Long keyId;
	private Long issueType;
	private Integer sourceTypeInternalId;
	private Integer superviseType;// 事件的督办类型（超时事件的筛选条件）
	@Autowired
	private GlobalSettingService globalSettingService;

	/***
	 * 为武胜县演示服务的视图类
	 * 
	 * @return
	 */
	@Action(value = "findIssueForView", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findIssueForView() throws Exception {
		if (searchIssueVo == null) {
			createEmptyIssueList();
			return ERROR;
		}
		if (searchIssueVo.getTargeOrgId() != null
				&& searchIssueVo.getTargeOrgId() > 0L) {
			keyId = searchIssueVo.getTargeOrgId();
		}
		if (searchIssueVo.getOrgLevel() == null
				&& searchIssueVo.getOrgLevelInternalId() != null) {
			List<PropertyDict> orgLevels = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.ORGANIZATION_LEVEL,
							searchIssueVo.getOrgLevelInternalId());
			if (orgLevels != null && orgLevels.size() > 0
					&& orgLevels.get(0) != null) {
				searchIssueVo.setOrgLevel(orgLevels.get(0).getId());
			}
			if (searchIssueVo.getSearchOrgId() != null) {
				Organization searchOrg = organizationDubboService
						.getFullOrgById(searchIssueVo.getSearchOrgId());
				int orgCodeFindLevel = searchOrg.getOrgLevel().getInternalId()
						/ 10 - orgLevels.get(0).getInternalId() / 10;
				if (searchIssueVo.getFunctionalOrgType() != null
						&& searchOrg.getOrgType().getInternalId() != OrganizationType.FUNCTIONAL_ORG) {
					orgCodeFindLevel = orgCodeFindLevel + 1;
				}
				searchIssueVo.setSearchOrgCode(searchOrg.getOrgInternalCode());
				searchIssueVo.setOrgCodeFindLevel(orgCodeFindLevel);
			}
		}
		if (searchIssueVo.getSourceType() == null
				&& sourceTypeInternalId != null) {
			List<PropertyDict> sourceTypes = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.SOURCE_KIND, sourceTypeInternalId);
			if (sourceTypes != null && sourceTypes.size() > 0
					&& sourceTypes.get(0) != null) {
				searchIssueVo.setSourceType(sourceTypes.get(0).getId());
			}
		}
		// 根据不同类型调用不同的service
		if (IssueViewType.NEED.equalsIgnoreCase(viewType)) {
			return findJurisdictionsNeedDo();
		} else if (IssueViewType.DONE.equalsIgnoreCase(viewType)) {
			return findJurisdictionsDone();
		} else if (IssueViewType.DOING.equalsIgnoreCase(viewType)) {
			return findJurisdictionsDoing();
		} else if (IssueViewType.COMPLETED.equalsIgnoreCase(viewType)) {
			if (Boolean.valueOf(globalSettingService
					.getGlobalValue(GlobalSetting.IS_TQSEARCH_OPEN))) {
				return findJurisdictionsCompletedFromSolr();
			} else {
				return findJurisdictionsCompleted();
			}
		} else if (IssueViewType.VERIFICATION.equalsIgnoreCase(viewType)) {
			return findJurisdictionsVerification();
		} else if (IssueViewType.ASSIGN.equalsIgnoreCase(viewType)) {
			return findJurisdictionsAssgin();
		} else if (IssueViewType.SUBMIT.equalsIgnoreCase(viewType)) {
			return findJurisdictionsSubmit();
		} else if (IssueViewType.CHECKPENDING.equalsIgnoreCase(viewType)) {
			return findJurisdictionAuditDelay();
		} else if (IssueViewType.FOLLOW.equalsIgnoreCase(viewType)) {
			return findJurisdictionsFollow();
		} else if (IssueViewType.SKIP.equalsIgnoreCase(viewType)) {
			return findJurisdictionsSkipIssue();
		} else if (IssueViewType.CHECKGRADE.equalsIgnoreCase(viewType)) {
			if (Boolean.valueOf(globalSettingService
					.getGlobalValue(GlobalSetting.IS_TQSEARCH_OPEN))) {
				return findJurisdictionsGradeDelayFromSolr();
			} else {
				return findJurisdictionsGradeDelay();
			}
		} else if (IssueViewType.PUBLICLTYCASSDONE.equalsIgnoreCase(viewType)) {
			return findJurisdictionsPublicltyCassDone();
		} else if (IssueViewType.TIMEOUT.equalsIgnoreCase(viewType)) {
			return findTimeOutIssue();
		} else {
			createEmptyIssueList();
			return ERROR;
		}

	}

	public String findJurisdictionsNeedDo() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			if (searchIssueVo == null) {
				createEmptyIssueList();
				return ERROR;
			}
			PageInfo<IssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionsNeedDo(searchIssueVo, page, rows, sidx,
							sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg", "createOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	public String findJurisdictionsDoing() throws Exception {
		createEmptyIssueList();
		return SUCCESS;
	}

	public String findJurisdictionsAssgin() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionsAssgin(statusType, searchIssueVo, page,
							rows, sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg", "createOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	public String findJurisdictionsCompletedFromSolr() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionsCompletedFromSolr(keyId, issueType,
							searchIssueVo, page, rows, sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg", "createOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	public String findJurisdictionsGradeDelayFromSolr() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionsGradeDelayFromSolr(keyId, issueType,
							searchIssueVo, page, rows, sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg", "createOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	public String findJurisdictionsCompleted() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<CompletedIssue> issues = searchIssueByLevelService
					.findJurisdictionsCompleted(keyId, issueType,
							searchIssueVo, page, rows, sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg", "createOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	public String findJurisdictionsPublicltyCassDone() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionsPublicltyCassDone(issueType,
							searchIssueVo, page, rows, sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg", "createOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	public String findTimeOutIssue() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = searchIssueByLevelService
					.findTimeOutIssue(searchIssueVo, superviseType, page, rows,
							sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg", "createOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	public String findJurisdictionsGradeDelay() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<CompletedIssue> issues = searchIssueByLevelService
					.findJurisdictionsGradeDelay(keyId, issueType,
							searchIssueVo, page, rows, sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg", "createOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	public String findJurisdictionsVerification() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionsVerification(searchIssueVo, page, rows,
							sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg", "createOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	public String findJurisdictionsSkipIssue() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionsSkipIssue(statusType, searchIssueVo,
							page, rows, sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg", "createOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	public String findJurisdictionsFollow() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionsFollow(searchIssueVo, page, rows, sidx,
							sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg", "createOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	public String findJurisdictionsDone() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionsDone(statusType, searchIssueVo, page,
							rows, sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg", "createOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	public String findJurisdictionsSubmit() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionsSubmit(statusType, searchIssueVo, page,
							rows, sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg", "createOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	private String findJurisdictionAuditDelay() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionAuditDelay(searchIssueVo, page, rows,
							sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg", "createOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	private void resolveIssueType() {
		// 网格根据事件的八个大类型进行筛选
		if (!StringUtil.isStringAvaliable(type) || issueType != null) {
			// 高级查询，根据事件大类和小类进行筛选
			IssueTypeDomain domain = searchIssueVo.getIssueTypeDomain();
			if (domain == null || domain.getId() == null) {
				setIssueType(null);
			} else {
				List<IssueType> issueTypes = new ArrayList<IssueType>();
				IssueType selectIssueType = new IssueType();
				selectIssueType.setIssueTypeDomain(domain);
				selectIssueType.setId(issueType);
				issueTypes.add(selectIssueType);
				searchIssueVo.setIssueTypes(issueTypes);
			}
		} else {
			IssueTypeDomain domain = issueTypeService
					.getIssueTypeDoaminByDomainName(IssueTypes
							.getChineseName(type));
			if (domain == null) {
				issueType = null;
			} else {
				searchIssueVo.setIssueType(domain.getId());
			}
		}

	}

	private boolean legalKeyIdParam() {
		return null != keyId;
	}

	private void createEmptyIssueList() {
		PageInfo<IssueNew> pageInfo = new PageInfo<IssueNew>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(rows);
		pageInfo.setResult(new ArrayList<IssueNew>());
		gridPage = new GridPage(pageInfo);
	}

	public SearchIssueVoNew getSearchIssueVo() {
		return searchIssueVo;
	}

	public void setSearchIssueVo(SearchIssueVoNew searchIssueVo) {
		this.searchIssueVo = searchIssueVo;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public void setIssueType(Long issueType) {
		this.issueType = issueType;
	}

	public Long getIssueType() {
		return issueType;
	}

	public Integer getSourceTypeInternalId() {
		return sourceTypeInternalId;
	}

	public void setSourceTypeInternalId(Integer sourceTypeInternalId) {
		this.sourceTypeInternalId = sourceTypeInternalId;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public Integer getSuperviseType() {
		return superviseType;
	}

	public void setSuperviseType(Integer superviseType) {
		this.superviseType = superviseType;
	}

}

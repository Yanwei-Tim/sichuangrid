package com.tianque.fourTeams.fourTeamsIssue.controller;

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
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueViewType;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.service.SearchFourTeamsIssueByLevelService;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Controller("SearchFourTeamsIssueBylevelController")
@Scope("prototype")
@Namespace("/searchFourTeamsIssueByLevelManage")
public class SearchFourTeamsIssueByLevelController extends BaseAction {
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private SearchFourTeamsIssueByLevelService searchIssueByLevelService;
	private SearchIssueVoNew searchIssueVo;

	private String viewType;// 视图类型
	private String statusType;

	private Long keyId;
	private Long issueType;

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
		// 根据不同类型调用不同的service
		if (FourTeamsIssueViewType.NEED.equalsIgnoreCase(viewType)) {
			return findJurisdictionsNeedDo();
		} else if (FourTeamsIssueViewType.DONE.equalsIgnoreCase(viewType)) {
			return findJurisdictionsDone();
		} else if (FourTeamsIssueViewType.DOING.equalsIgnoreCase(viewType)) {
			return findJurisdictionsDoing();
		} else if (FourTeamsIssueViewType.COMPLETED.equalsIgnoreCase(viewType)) {
			return findJurisdictionsCompleted();
		} else if (FourTeamsIssueViewType.ASSIGN.equalsIgnoreCase(viewType)) {
			return findJurisdictionsAssgin();
		} else if (FourTeamsIssueViewType.SUBMIT.equalsIgnoreCase(viewType)) {
			return findJurisdictionsSubmit();
		} else if (FourTeamsIssueViewType.CHECKPENDING
				.equalsIgnoreCase(viewType)) {
			return findJurisdictionAuditDelay();
		} else if (FourTeamsIssueViewType.SKIP.equalsIgnoreCase(viewType)) {
			return findJurisdictionsSkipIssue();
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
			PageInfo<FourTeamsIssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionsNeedDo(searchIssueVo, page, rows, sidx,
							sord);

			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg", "lastOrg",
							"targeOrg", "currentOrg", "createOrg" }, null);
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
			PageInfo<FourTeamsIssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionsAssgin(searchIssueVo, page, rows, sidx,
							sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg", "lastOrg",
							"targeOrg", "currentOrg" }, null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	public String findJurisdictionsCompleted() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<FourTeamsIssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionsCompleted(statusType, searchIssueVo,
							page, rows, sidx, sord);

			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg", "lastOrg",
							"targeOrg", "currentOrg", "createOrg" }, null);
			gridPage = new GridPage(issues);

		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	public String findJurisdictionsSkipIssue() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<FourTeamsIssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionsSkipIssue(searchIssueVo, page, rows,
							sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg", "lastOrg",
							"targeOrg", "currentOrg" }, null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	public String findJurisdictionsDone() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<FourTeamsIssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionsDone(statusType, searchIssueVo, page,
							rows, sidx, sord);

			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg", "lastOrg",
							"targeOrg", "currentOrg", "createOrg" }, null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	public String findJurisdictionsSubmit() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<FourTeamsIssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionsSubmit(searchIssueVo, page, rows, sidx,
							sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg", "lastOrg",
							"targeOrg", "currentOrg" }, null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	private String findJurisdictionAuditDelay() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<FourTeamsIssueViewObject> issues = searchIssueByLevelService
					.findJurisdictionAuditDelay(searchIssueVo, page, rows,
							sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg", "lastOrg",
							"targeOrg", "currentOrg" }, null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	private void resolveIssueType() {
		if (null == issueType) {
			setIssueType(null);
		} else {
			IssueTypeDomain domain = searchIssueVo.getIssueTypeDomain();
			if (domain == null) {
				setIssueType(null);
			} else {
				List<IssueType> issueTypes = new ArrayList<IssueType>();
				IssueType selectIssueType = new IssueType();
				selectIssueType.setIssueTypeDomain(domain);
				selectIssueType.setId(issueType);
				selectIssueType.setId(issueType);
				issueTypes.add(selectIssueType);
				searchIssueVo.setIssueTypes(issueTypes);
			}
		}
	}

	private boolean legalKeyIdParam() {
		return null != keyId;
	}

	private void createEmptyIssueList() {
		PageInfo<FourTeamsIssueNew> pageInfo = new PageInfo<FourTeamsIssueNew>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(rows);
		pageInfo.setResult(new ArrayList<FourTeamsIssueNew>());
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

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

}

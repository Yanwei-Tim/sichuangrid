package com.tianque.eventSource.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.IssueType;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.eventSource.domain.EventSource;
import com.tianque.eventSource.service.CommandCenterService;
import com.tianque.eventSource.service.EventSourceService;
import com.tianque.issue.controller.strategy.IssueManageStrategy;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.factory.IssueManageStrategyFactory;
import com.tianque.issue.factory.IssueServiceFactory;
import com.tianque.issue.service.IssueService;
import com.tianque.issue.vo.IssueTypeViewDefine;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/commandCenter/commandManage")
@Transactional
@Scope("request")
@Controller("commandCenterController")
public class CommandCenterController extends BaseAction {
	private final static String SYS_SERIES = IssueServiceFactory.DEFAULT_SERIES;
	private Long keyId;
	private IssueNew issue = null;
	private Organization organization;
	private SearchIssueVoNew searchIssueVo;
	private PropertyDict propertyDict;
	private String moduleName;
	private IssueManageStrategy strategy;
	private EventSource eventSource;

	private Map<String, List<IssueType>> issueTypes;

	private List<IssueTypeViewDefine> issueTypeNames;

	private List<IssueType> contradiction;
	private List<IssueType> specialisation;
	private List<IssueType> peopleliveService;
	private List<IssueAttachFile> issueAttachFiles = new ArrayList<IssueAttachFile>();
	private Map<Long, List<IssueAttachFile>> issueLogAttachFiles = new HashMap<Long, List<IssueAttachFile>>();

	@Autowired
	private EventSourceService eventSourceService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private IssueServiceFactory issueServiceFactory;
	@Autowired
	private IssueManageStrategyFactory factory;
	@Autowired
	private CommandCenterService commandCenterService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	protected String getSeries() {
		return SYS_SERIES;
	}

	@Actions({ @Action(value = "dispatch", results = { @Result(name = "success", location = "/commandCenter/updateCommandCenterIssueDlg.jsp") }) })
	public String dispatch() throws Exception {
		if (keyId != null) {
			IssueManageStrategy strategy = getActualIssueManageStrategy();
			issue = getActualIssueServiceInstance().getFullIssueByStepId(keyId);
			loadFullIssueTypes(issue);
			loadAttachFiles(issue);
			issueTypeNames = strategy.getIssueTypeNames();
			issueTypes = strategy.loadEnabledIssueTypes(issueTypeNames);
			eventSource = eventSourceService.getEventSourceBySerialNumber(issue
					.getSerialNumber());
			return SUCCESS;
		} else {
			return "error";
		}
	}

	@Action(value = "findMyDone", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findMyDone() throws Exception {
		if (null != keyId) {
			PageInfo<IssueViewObject> issues = commandCenterService.findMyDone(
					keyId, issue, page, rows, sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg" }, keyId);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	@Action(value = "findCommandCenterIssue", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findCommandCenterIssue() throws Exception {
		if (null == organization || null == organization.getId()) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		PageInfo<IssueViewObject> pageInfo = proccessRelativeOrgNameByPageInfo(
				commandCenterService.findCommandCenterIssueForPageByTargeOrgIdAndDealState(
						organization.getId(), null, page, rows, sidx, sord),
				organizationDubboService);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	private void createEmptyIssueList() {
		PageInfo<IssueNew> pageInfo = new PageInfo<IssueNew>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(rows);
		pageInfo.setResult(new ArrayList<IssueNew>());
		gridPage = new GridPage(pageInfo);
	}

	private IssueManageStrategy getActualIssueManageStrategy() {
		if (strategy == null) {
			strategy = factory.getIssueManageStrategyByModule(moduleName);
		}
		return strategy;
	}

	private IssueService getActualIssueServiceInstance() {
		return issueServiceFactory.getIssueServiceBySeries(getSeries());
	}

	private void loadFullIssueTypes(IssueNew selectIssue) {
		selectIssue.getIssueType().setIssueTypeDomain(
				issueTypeService.getIssueTypeDomainById(selectIssue
						.getIssueType().getIssueTypeDomain().getId()));
	}

	private void loadAttachFiles(IssueNew selectIssue) {
		issueAttachFiles = getActualIssueServiceInstance()
				.loadIssueAttachFilesByIssueId(selectIssue.getId());
		for (int index = issueAttachFiles.size(); index > 0; index--) {
			IssueAttachFile file = issueAttachFiles.get(index - 1);
			if (isLogAttachFile(file)) {
				List<IssueAttachFile> logFiles = lookupLogFilesFromAllLogFile(file
						.getIssueLog().getId());
				logFiles.add(file);
				issueAttachFiles.remove(index - 1);
			}
		}
	}

	private boolean isLogAttachFile(IssueAttachFile file) {
		return file.getIssueLog() != null && file.getIssueLog().getId() != null;
	}

	private List<IssueAttachFile> lookupLogFilesFromAllLogFile(Long id) {
		if (issueLogAttachFiles.containsKey(id)) {
			return issueLogAttachFiles.get(id);
		} else {
			List<IssueAttachFile> files = new ArrayList<IssueAttachFile>();
			issueLogAttachFiles.put(id, files);
			return files;
		}
	}

	private PageInfo<IssueNew> emptyPage(int pageSize) {
		PageInfo<IssueNew> pageInfo = new PageInfo<IssueNew>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<IssueNew>());
		return pageInfo;
	}

	private PageInfo<IssueViewObject> proccessRelativeOrgNameByPageInfo(
			PageInfo<IssueViewObject> pageInfo,
			OrganizationDubboService organizationDubboService) {
		pageInfo = ControllerHelper.processAllOrgRelativeName(pageInfo,
				organizationDubboService, new String[] { "occurOrg", "lastOrg",
						"targeOrg", "currentOrg" }, keyId);
		return pageInfo;
	}

	public Long getKeyId() {
		return keyId;
	}

	public IssueNew getIssue() {
		return issue;
	}

	public void setIssue(IssueNew issue) {
		this.issue = issue;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public SearchIssueVoNew getSearchIssueVo() {
		return searchIssueVo;
	}

	public void setSearchIssueVo(SearchIssueVoNew searchIssueVo) {
		this.searchIssueVo = searchIssueVo;
	}

	public void setPropertyDict(PropertyDict propertyDict) {
		this.propertyDict = propertyDict;
	}

	public PropertyDict getPropertyDict() {
		return propertyDict;
	}

	public void setContradiction(List<IssueType> contradiction) {
		this.contradiction = contradiction;
	}

	public List<IssueType> getContradiction() {
		return contradiction;
	}

	public void setSpecialisation(List<IssueType> specialisation) {
		this.specialisation = specialisation;
	}

	public List<IssueType> getSpecialisation() {
		return specialisation;
	}

	public void setPeopleliveService(List<IssueType> peopleliveService) {
		this.peopleliveService = peopleliveService;
	}

	public List<IssueType> getPeopleliveService() {
		return peopleliveService;
	}

	public IssueManageStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(IssueManageStrategy strategy) {
		this.strategy = strategy;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public List<IssueAttachFile> getIssueAttachFiles() {
		return issueAttachFiles;
	}

	public void setIssueAttachFiles(List<IssueAttachFile> issueAttachFiles) {
		this.issueAttachFiles = issueAttachFiles;
	}

	public Map<Long, List<IssueAttachFile>> getIssueLogAttachFiles() {
		return issueLogAttachFiles;
	}

	public void setIssueLogAttachFiles(
			Map<Long, List<IssueAttachFile>> issueLogAttachFiles) {
		this.issueLogAttachFiles = issueLogAttachFiles;
	}

	public Map<String, List<IssueType>> getIssueTypes() {
		return issueTypes;
	}

	public void setIssueTypes(Map<String, List<IssueType>> issueTypes) {
		this.issueTypes = issueTypes;
	}

	public List<IssueTypeViewDefine> getIssueTypeNames() {
		return issueTypeNames;
	}

	public void setIssueTypeNames(List<IssueTypeViewDefine> issueTypeNames) {
		this.issueTypeNames = issueTypeNames;
	}

	public void setEventSource(EventSource eventSource) {
		this.eventSource = eventSource;
	}

	public EventSource getEventSource() {
		return eventSource;
	}
}

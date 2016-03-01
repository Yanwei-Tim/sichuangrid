package com.tianque.plugin.taskList.controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.plugin.taskList.domain.TaskListTimeStandard;
import com.tianque.plugin.taskList.service.TaskListTimeStandardService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务清单考核标准设置
 * Created by hesimin on 2015/12/17.
 */
@Scope("request")
@Namespace("/taskListTimeStandardManage")
@Controller("taskListTimeStandardController")
public class TaskListTimeStandardController extends BaseAction {
	@Autowired
	private TaskListTimeStandardService taskListTimeStandardService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private TaskListTimeStandard taskListTimeStandard;
	private Organization org;
	private List<Organization> orgList;
	private String ids;

	private Long orgId;
	private Integer itemNameDictInternalId;
	private List<TaskListTimeStandard> taskListTimeStandardList;

	@Action(value = "getTaskListTimeStandardByItemName", results = {
			@Result(name = "success", type = "json", params = {"root", "taskListTimeStandardList", "ignoreHierarchy", "false"}),
			@Result(name = "error", type = "json", params = {"root", "errorMessage", "ignoreHierarchy", "false"})})
	public String getTaskListTimeStandardByItemName() {
		taskListTimeStandardList= taskListTimeStandardService.getTaskListTimeStandardByFunOrgIdAndItemName(orgId,itemNameDictInternalId);
		return SUCCESS;
	}
	@Action(value = "dispatch", results = {
			@Result(name = "add", location = "/task/taskListTimeStandard/taskListTimeStandardDlg.jsp"),
			@Result(name = "edit", location = "/task/taskListTimeStandard/taskListTimeStandardDlg.jsp"),
			@Result(name = "error", type = "json", params = {"root", "errorMessage"})})
	public String dispatch() {
		orgList = this.findFunOrgsByParentOrgId();
		if ("edit".equals(mode)) {
			if (taskListTimeStandard.getId() == null) {
				errorMessage = "参数错误";
				return ERROR;
			}
			taskListTimeStandard = taskListTimeStandardService.getTaskListTimeStandardById(taskListTimeStandard.getId());
		}
		return mode;
	}

	@Action(value = "addTaskListTimeStandard", results = {
			@Result(name = "success", type = "json", params = {"root", "true", "ignoreHierarchy", "false"}),
			@Result(name = "error", type = "json", params = {"root", "errorMessage", "ignoreHierarchy", "false"})})
	public String addTaskListTimeStandard() {
		taskListTimeStandardService.addTaskListTimeStandard(taskListTimeStandard);
		return SUCCESS;
	}

	@Action(value = "updateTaskListTimeStandard", results = {
			@Result(name = "success", type = "json", params = {"root", "true", "ignoreHierarchy", "false"}),
			@Result(name = "error", type = "json", params = {"root", "errorMessage", "ignoreHierarchy", "false"})})
	public String updateTaskListTimeStandard() {
		taskListTimeStandardService.updateTaskListTimeStandard(taskListTimeStandard);
		return SUCCESS;
	}

	@Action(value = "deleteTaskListTimeStandard", results = {
			@Result(name = "success", type = "json", params = {"root", "true", "ignoreHierarchy", "false"}),
			@Result(name = "error", type = "json", params = {"root", "errorMessage", "ignoreHierarchy", "false"})})
	public String deleteTaskListTimeStandard() {
		if(StringUtils.isBlank(ids)){
			errorMessage = "参数错误";
			return ERROR;
		}
		taskListTimeStandardService.deleteTaskListTimeStandardByIds(super.analyzeToList(ids));
		return SUCCESS;
	}

	@Action(value = "findTaskListTimeStandard", results = {
			@Result(name = "success", type = "json", params = {"root", "gridPage", "ignoreHierarchy", "false"}),
			@Result(name = "error", type = "json", params = {"root", "errorMessage", "ignoreHierarchy", "false"})})
	public String findTaskListTimeStandard() {
		gridPage = new GridPage(taskListTimeStandardService.findTaskListTimeStandard(ThreadVariable.getOrganization().getOrgInternalCode(), page, rows, sidx, sord));
		ControllerHelper.processAllOrgName(gridPage.getRows(), organizationDubboService, new String[]{"org"});
		return SUCCESS;
	}

	private List<Organization> findFunOrgsByParentOrgId() {
		Organization organization = organizationDubboService
				.getSimpleOrgById(ThreadVariable.getOrganization().getId());
		if (organization == null) {
			return new ArrayList<Organization>();
		}
		return organizationDubboService.findFunOrgsByParentOrgId(organization
				.getId());
	}

	public TaskListTimeStandardService getTaskListTimeStandardService() {
		return taskListTimeStandardService;
	}

	public void setTaskListTimeStandardService(TaskListTimeStandardService taskListTimeStandardService) {
		this.taskListTimeStandardService = taskListTimeStandardService;
	}

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public TaskListTimeStandard getTaskListTimeStandard() {
		return taskListTimeStandard;
	}

	public void setTaskListTimeStandard(TaskListTimeStandard taskListTimeStandard) {
		this.taskListTimeStandard = taskListTimeStandard;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public List<Organization> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<Organization> orgList) {
		this.orgList = orgList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getItemNameDictInternalId() {
		return itemNameDictInternalId;
	}

	public void setItemNameDictInternalId(Integer itemNameDictInternalId) {
		this.itemNameDictInternalId = itemNameDictInternalId;
	}

	public List<TaskListTimeStandard> getTaskListTimeStandardList() {
		return taskListTimeStandardList;
	}

	public void setTaskListTimeStandardList(List<TaskListTimeStandard> taskListTimeStandardList) {
		this.taskListTimeStandardList = taskListTimeStandardList;
	}
}

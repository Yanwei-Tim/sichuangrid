package com.tianque.fourTeams.fourTeamsIssue.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStandardForFunOrg;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueStandardForFunOrgService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("fourTeamsIssueStandardForFunOrgController")
@Scope("request")
@Namespace("/fourTeams/issues/issueStandardForFunOrgManage")
public class FourTeamsIssueStandardForFunOrgController extends BaseAction {
	private FourTeamsIssueStandardForFunOrg issueStandardForFunOrg;
	private Map<Long, String> funOrg;
	private String ids;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private FourTeamsIssueStandardForFunOrgService issueStandardForFunOrgService;

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/issueAccessConfig/maintainFunctionOrgTimeLimitStandardDlg.jsp"),
			@Result(name = "default_edit", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/issueAccessConfig/maintainFunctionOrgTimeLimitStandardDlg1.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		getFunOrgMap();
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {

		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)
				|| DialogMode.VIEW1_MODE.equalsIgnoreCase(mode)) {
			if (issueStandardForFunOrg == null
					|| issueStandardForFunOrg.getId() == null
					|| issueStandardForFunOrg.getId() == 0) {
				return ERROR;
			}
			issueStandardForFunOrg = issueStandardForFunOrgService
					.getIssueStandardForFunOrgById(issueStandardForFunOrg
							.getId());
			if (DialogMode.VIEW1_MODE.equalsIgnoreCase(mode)) {
				return "default_edit";
			}
		}
		return SUCCESS;
	}

	@Action(value = "findIssueStandardForFunOrgList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findIssueStandardForFunOrgList() throws Exception {
		gridPage = new GridPage(
				ControllerHelper.proccessRelativeOrgNameByPageInfo(
						issueStandardForFunOrgService
								.findIssueStandardForFunOrgsForList(page, rows,
										sidx, sord), organizationDubboService));
		return SUCCESS;
	}

	@Action(value = "addIssueStandardForFunOrg", results = {
			@Result(type = "json", name = "success", params = { "root",
					"issueStandardForFunOrg", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addIssueStandardForFunOrg() throws Exception {
		if (issueStandardForFunOrg == null) {
			return ERROR;
		}
		issueStandardForFunOrg = issueStandardForFunOrgService
				.addIssueStandardForFunOrg(issueStandardForFunOrg);
		return SUCCESS;
	}

	@Action(value = "updateIssueStandardForFunOrg", results = {
			@Result(type = "json", name = "success", params = { "root",
					"issueStandardForFunOrg", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateIssueStandardForFunOrg() throws Exception {
		if (issueStandardForFunOrg == null) {
			return ERROR;
		}
		issueStandardForFunOrg = issueStandardForFunOrgService
				.updateIssueStandardForFunOrg(issueStandardForFunOrg);
		return SUCCESS;
	}

	@Action(value = "deleteIssueStandardForFunOrgs", results = {
			@Result(type = "json", name = "success", params = { "root",
					"result", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteIssueStandardForFunOrgs() throws Exception {
		if (!StringUtil.isStringAvaliable(ids)) {
			return ERROR;
		}
		result = issueStandardForFunOrgService
				.deleteIssueStandardForFunOrgByIds(analyze(ids));
		return SUCCESS;
	}

	@Action(value = "validateRepeatByOrgIdAndItemName", results = {
			@Result(type = "json", name = "success", params = { "root",
					"result", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String validateRepeatByOrgIdAndItemName() throws Exception {
		if (issueStandardForFunOrg == null) {
			errorMessage = "操作失败，请联系管理员！";
			return ERROR;
		}
		result = issueStandardForFunOrgService
				.validateRepeatByOrgIdAndItemName(issueStandardForFunOrg);
		return SUCCESS;
	}

	private void getFunOrgMap() {
		funOrg = new HashMap<Long, String>();
		for (Organization org : findFunOrgsByParentOrgId()) {
			funOrg.put(org.getId(), org.getOrgName());
		}
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

	public FourTeamsIssueStandardForFunOrg getIssueStandardForFunOrg() {
		return issueStandardForFunOrg;
	}

	public void setIssueStandardForFunOrg(
			FourTeamsIssueStandardForFunOrg issueStandardForFunOrg) {
		this.issueStandardForFunOrg = issueStandardForFunOrg;
	}

	public Map<Long, String> getFunOrg() {
		return funOrg;
	}

	public void setFunOrg(Map<Long, String> funOrg) {
		this.funOrg = funOrg;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}

package com.tianque.xichang.working.flow.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.PageInfoUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.User;
import com.tianque.xichang.working.flow.constant.AccountType;
import com.tianque.xichang.working.flow.domain.AccountLogs;
import com.tianque.xichang.working.flow.service.AccountLogsService;

/**
 * @ClassName: AccountLogsController
 * @Description: 三本台账-情况记录
 */

@Scope("request")
@Namespace("/account/accountLogsManage")
@Controller("accountLogsController")
public class AccountLogsController extends BaseAction {
	@Autowired
	private AccountLogsService accountLogsService;

	private AccountLogs accountLogs;
	private Long accountLogsId;
	private String accountType;
	private Integer finishType;
	private List<AutoCompleteData> autoCompleteData;
	/** 目标处理部门的orgId(可以看作是当前登录用户的orgId) */
	private Long targetOrgId;
	/** 是查询行政部门还是职能部门 */
	private boolean adminTarget;

	/** 选择单位联想输入时输入的关键字 */
	private String tag;

	@Action(value = "findAccountLogsForPageByAccountId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findAccountLogsForPageByAccountId() throws Exception {
		if (accountLogs == null || accountLogs.getAccountId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		gridPage = new GridPage(
				accountLogsService.findAccountLogsForPageByAccountId(
						accountLogs.getAccountId(), accountType,
						PageInfoUtil.genaratePager(page, rows, sidx, sord)));
		return SUCCESS;
	}

	@Action(value = "addAccountLogs", results = {
			@Result(name = "success", type = "json", params = { "root",
					"opreationResult", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String addAccountLogs() throws Exception {
		if (accountLogs == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		accountLogs.setEssenceAndProgram(finishType);
		opreationResult = accountLogsService.addAccountLogs(accountLogs);
		return SUCCESS;
	}

	@Action(value = "updateAccountLogs", results = {
			@Result(name = "success", type = "json", params = { "root",
					"accountLogs", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String updateAccountLogs() throws Exception {
		if (accountLogs == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		accountLogs.setEssenceAndProgram(finishType);
		accountLogs = accountLogsService.updateAccountLogs(accountLogs);
		return SUCCESS;
	}

	@Action(value = "deleteAccountLogsById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"opreationResult", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String deleteAccountLogsById() throws Exception {
		if (accountLogsId == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		opreationResult = accountLogsService
				.deleteAccountLogsById(accountLogsId);
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "peopleaspiration", location = "/xichang/working/flow/maintainAccountLogsForPeopleAspirationDlg.jsp"),
			@Result(name = "poorpeople", location = "/xichang/working/flow/maintainAccountLogsForPoorPeopleDlg.jsp"),
			@Result(name = "steadywork", location = "/xichang/working/flow/maintainAccountLogsForSteadyWorkDlg.jsp"),
			@Result(name = "searchTarget", location = "/xichang/working/flow/searchOrgDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String dispatch() throws Exception {
		if (accountLogs == null || accountLogs.getId() == null
				|| !StringUtil.isStringAvaliable(accountType)) {
			errorMessage = "参数错误";
			return ERROR;
		}
		accountLogs = accountLogsService
				.getAccountLogsById(accountLogs.getId());
		if (AccountType.PEOPLEASPIRATION.equalsIgnoreCase(accountType)) {
			return AccountType.PEOPLEASPIRATION.toLowerCase();
		} else if (AccountType.POORPEOPLE.equalsIgnoreCase(accountType)) {
			return AccountType.POORPEOPLE.toLowerCase();
		} else if (AccountType.STEADYWORK.equalsIgnoreCase(accountType)) {
			return AccountType.STEADYWORK.toLowerCase();
		} else {
			errorMessage = "参数错误";
			return ERROR;
		}
	}

	/**
	 * 控制跳转，匹配选择组织机构
	 */
	@Action(value = "dispatchOperate", results = {
			@Result(name = "searchTarget", location = "/xichang/working/flow/searchOrgDlg.jsp"),
			@Result(name = "fourTeamType", location = "/fourTeamsManage/searchOrgDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String dispatchOperate() throws Exception {
		if (AccountType.SEARCH_TARGET.equals(mode)) {
			return AccountType.SEARCH_TARGET;
		} else if (AccountType.FOURTEAM_TYPE.equals(mode)) {
			return AccountType.FOURTEAM_TYPE;
		} else {
			errorMessage = "参数错误";
			return ERROR;
		}
	}

	/**
	 * 台账情况记录选择组织机构联想输入
	 * 
	 * @return
	 */
	@Action(value = "searchTransferTarget", results = {
			@Result(name = "auto", type = "json", params = { "root",
					"gridPage.rows", "ignoreHierarchy", "false" }),
			@Result(name = "list", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }) })
	public String searchTransferTarget() throws Exception {
		if (adminTarget) {
			gridPage = new GridPage(accountLogsService.findAdminTargetsByName(
					targetOrgId, tag, page, rows));
		} else {
			gridPage = new GridPage(
					accountLogsService.findFunctionTargetsByName(targetOrgId,
							tag, page, rows));
		}
		return mode;
	}

	/**
	 * 根据当前ORGID查询本级及下辖所有只能部门
	 * 
	 * @return
	 */
	@Action(value = "searchFunctionTarget", results = {
			@Result(name = "auto", type = "json", params = { "root",
					"gridPage.rows", "ignoreHierarchy", "false" }),
			@Result(name = "list", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }) })
	public String searchFunctionTarget() throws Exception {
		gridPage = new GridPage(accountLogsService.findFunctionTargetsByOrgId(
				targetOrgId, tag, page, rows));
		return mode;
	}

	/**
	 * 根据部门名称查询当前用户层级下辖所有符合条件的职能部门
	 */
	@Action(value = "searchFunctionTargetByName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"autoCompleteData", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String searchFunctionTargetByName() throws Exception {
		User user = ThreadVariable.getUser();
		if (user == null || user.getOrganization() == null
				|| user.getOrganization().getId() == null) {
			errorMessage = "查询部门列表出错，请重试";
			return ERROR;
		}

		PageInfo pageInfo = accountLogsService.findFunctionTargetsByName(user
				.getOrganization().getId(), tag, page, rows);
		if (pageInfo == null) {
			pageInfo = new PageInfo<AutoCompleteData>();
		}
		autoCompleteData = pageInfo.getResult();
		return SUCCESS;
	}

	/**
	 * 根据当前ORGID查询本级及下辖所有只能部门,返回list
	 * 
	 * @return
	 */
	@Action(value = "searchFunctionTargetForList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"autoCompleteData", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String searchFunctionTargetForList() throws Exception {
		User user = ThreadVariable.getUser();
		if (user == null || user.getOrganization() == null
				|| user.getOrganization().getId() == null) {
			errorMessage = "查询部门列表出错，请重试";
			return ERROR;
		}
		PageInfo pageInfo = accountLogsService.findFunctionTargetsByOrgId(user
				.getOrganization().getId(), tag, page, rows);
		autoCompleteData = pageInfo.getResult();
		return SUCCESS;
	}

	/**
	 * 保存实体对象
	 * 
	 * @return
	 */

	public AccountLogs getAccountLogs() {
		return accountLogs;
	}

	public void setAccountLogs(AccountLogs accountLogs) {
		this.accountLogs = accountLogs;
	}

	public Long getAccountLogsId() {
		return accountLogsId;
	}

	public void setAccountLogsId(Long accountLogsId) {
		this.accountLogsId = accountLogsId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void setFinishType(Integer finishType) {
		this.finishType = finishType;
	}

	public Integer getFinishType() {
		return finishType;
	}

	public Long getTargetOrgId() {
		return targetOrgId;
	}

	public void setTargetOrgId(Long targetOrgId) {
		this.targetOrgId = targetOrgId;
	}

	public boolean isAdminTarget() {
		return adminTarget;
	}

	public void setAdminTarget(boolean adminTarget) {
		this.adminTarget = adminTarget;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<AutoCompleteData> getAutoCompleteData() {
		return autoCompleteData;
	}

	public void setAutoCompleteData(List<AutoCompleteData> autoCompleteData) {
		this.autoCompleteData = autoCompleteData;
	}

}

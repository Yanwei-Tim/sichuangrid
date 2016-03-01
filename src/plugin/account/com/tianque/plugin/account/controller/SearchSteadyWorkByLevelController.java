package com.tianque.plugin.account.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.account.api.SearchSteadyWorkByLevelDubboService;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.constants.ThreeRecordsIssueViewType;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.plugin.account.vo.LedgerSteadyWorkVo;
import com.tianque.plugin.account.vo.SearchLedgerSteadyWorkVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("SearchSteadyWorkByLevellevelController")
@Scope("request")
@Namespace("/ledger/searchSteadyWork")
public class SearchSteadyWorkByLevelController extends BaseAction {

	private SearchLedgerSteadyWorkVo searchVo;

	private String viewType;// 视图类型

	private Long ledgerType;

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SearchSteadyWorkByLevelDubboService searchSteadyWorkByLevelDubboService;

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
		if (searchVo == null) {
			errorMessage = "参数错误！";
			return ERROR;
		}
		// 根据不同类型调用不同的service
		if (ThreeRecordsIssueViewType.NEED.equalsIgnoreCase(viewType)) {
			return findJurisdictionsNeedDo();
		} else if (ThreeRecordsIssueViewType.DONE.equalsIgnoreCase(viewType)) {
			return findJurisdictionsDone();
		} else if (ThreeRecordsIssueViewType.PERIOD.equalsIgnoreCase(viewType)) {
			return findPeriodCompletedList();
		} else if (ThreeRecordsIssueViewType.COMPLETED
				.equalsIgnoreCase(viewType)) {
			return findCompletedIssueList();
		} else if (ThreeRecordsIssueViewType.FEEDBACK
				.equalsIgnoreCase(viewType)) {
			return findJurisdictionsFeedBack();
		} else if (ThreeRecordsIssueViewType.ASSIGN.equalsIgnoreCase(viewType)) {
			return findJurisdictionsAssgin();
		} else if (ThreeRecordsIssueViewType.SUBMIT.equalsIgnoreCase(viewType)) {
			return findJurisdictionsSubmit();
		} else if (ThreeRecordsIssueViewType.FOLLOW.equalsIgnoreCase(viewType)) {
			return findJurisdictionsFollow();
		} else if (ThreeRecordsIssueViewType.SUPPORT.equalsIgnoreCase(viewType)) {
			return findJurisdictionsNeedDo();
		} else if (ThreeRecordsIssueViewType.NOTICE.equalsIgnoreCase(viewType)) {
			return findJurisdictionsNeedDo();
		} else if (ThreeRecordsIssueViewType.CREATE_DONE
				.equalsIgnoreCase(viewType)) {
			return findJurisdictionsCreateAndDoneList();
		}

		return SUCCESS;
	}

	private String findJurisdictionsNeedDo() {
		if (searchVo == null) {
			errorMessage = "参数错误！";
			return ERROR;
		}
		PageInfo<LedgerSteadyWorkVo> issues = searchSteadyWorkByLevelDubboService
				.findJurisdictionsNeedDo(searchVo, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	private String findJurisdictionsAssgin() {
		PageInfo<LedgerSteadyWorkVo> issues = searchSteadyWorkByLevelDubboService
				.findJurisdictionsAssgin(searchVo, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	private String findJurisdictionsDone() {
		PageInfo<LedgerSteadyWorkVo> issues = searchSteadyWorkByLevelDubboService
				.findJurisdictionsDone(searchVo, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	private String findJurisdictionsSubmit() {
		PageInfo<LedgerSteadyWorkVo> issues = searchSteadyWorkByLevelDubboService
				.findJurisdictionsSubmit(searchVo, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	private String findPeriodCompletedList() {
		PageInfo<LedgerSteadyWorkVo> issues = searchSteadyWorkByLevelDubboService
				.findPeriodCompletedList(searchVo, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	private String findJurisdictionsCreateAndDoneList() {
		PageInfo<LedgerSteadyWorkVo> issues = searchSteadyWorkByLevelDubboService
				.findJurisdictionsCreateAndDoneList(searchVo, page, rows, sidx,
						sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	private String findCompletedIssueList() {
		PageInfo<LedgerSteadyWorkVo> issues = searchSteadyWorkByLevelDubboService
				.findCompletedIssueList(searchVo, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	private String findJurisdictionsFeedBack() {
		PageInfo<LedgerSteadyWorkVo> issues = searchSteadyWorkByLevelDubboService
				.findJurisdictionsFeedBack(searchVo, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	private String findJurisdictionsFollow() {
		PageInfo<LedgerSteadyWorkVo> issues = searchSteadyWorkByLevelDubboService
				.findJurisdictionsFollow(searchVo, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public SearchLedgerSteadyWorkVo getSearchVo() {
		return searchVo;
	}

	public void setSearchVo(SearchLedgerSteadyWorkVo searchVo) {
		this.searchVo = searchVo;
	}

	public Long getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(Long ledgerType) {
		this.ledgerType = ledgerType;
	}

}

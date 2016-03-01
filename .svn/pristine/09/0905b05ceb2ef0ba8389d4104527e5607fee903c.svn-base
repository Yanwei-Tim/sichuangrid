package com.tianque.plugin.account.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.account.api.SearchPoorPeopleByLevelDubboService;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.constants.CommonalityParameter;
import com.tianque.plugin.account.constants.ThreeRecordsIssueViewType;
import com.tianque.plugin.account.vo.LedgerPoorPeopleVo;
import com.tianque.plugin.account.vo.SearchLedgerPoorPeopleVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("SearchPoorPeopleByLevelController")
@Scope("request")
@Namespace("/ledger/searchPoorPeople")
public class SearchPoorPeopleByLevelController extends BaseAction {

	private SearchLedgerPoorPeopleVo poorPeople;
	private String viewType;// 视图类型
	private Long ledgerType;

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SearchPoorPeopleByLevelDubboService searchPoorPeopleByLevelDubboService;

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
		if (poorPeople == null) {
			errorMessage = "参数错误！";
			return ERROR;
		}

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

	public String findJurisdictionsNeedDo() {
		if (poorPeople == null) {
			errorMessage = "参数错误！";
			return ERROR;
		}
		PageInfo<LedgerPoorPeopleVo> issues = searchPoorPeopleByLevelDubboService
				.findJurisdictionsNeedDo(poorPeople, page, rows, sidx, sord);

		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, new String[] {
						CommonalityParameter.OCCUR_ORG,
						CommonalityParameter.LAST_ORG,
						CommonalityParameter.TARGE_ORG,
						CommonalityParameter.CURRENT_ORG,
						CommonalityParameter.CREATE_ORG }, null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	public String findJurisdictionsDoing() {
		return SUCCESS;
	}

	public String findJurisdictionsAssgin() {
		PageInfo<LedgerPoorPeopleVo> issues = searchPoorPeopleByLevelDubboService
				.findJurisdictionsAssgin(poorPeople, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, new String[] {
						CommonalityParameter.OCCUR_ORG,
						CommonalityParameter.LAST_ORG,
						CommonalityParameter.TARGE_ORG,
						CommonalityParameter.CURRENT_ORG }, null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	public String findJurisdictionsCompleted() {
		PageInfo<LedgerPoorPeopleVo> issues = searchPoorPeopleByLevelDubboService
				.findJurisdictionsCompleted(poorPeople, page, rows, sidx, sord);

		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, new String[] {
						CommonalityParameter.OCCUR_ORG,
						CommonalityParameter.LAST_ORG,
						CommonalityParameter.TARGE_ORG,
						CommonalityParameter.CURRENT_ORG,
						CommonalityParameter.CREATE_ORG }, null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	public String findJurisdictionsDone() {
		PageInfo<LedgerPoorPeopleVo> issues = searchPoorPeopleByLevelDubboService
				.findJurisdictionsDone(poorPeople, page, rows, sidx, sord);

		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, new String[] {
						CommonalityParameter.OCCUR_ORG,
						CommonalityParameter.LAST_ORG,
						CommonalityParameter.TARGE_ORG,
						CommonalityParameter.CURRENT_ORG,
						CommonalityParameter.CREATE_ORG }, null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	public String findJurisdictionsSubmit() {
		PageInfo<LedgerPoorPeopleVo> issues = searchPoorPeopleByLevelDubboService
				.findJurisdictionsSubmit(poorPeople, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, new String[] {
						CommonalityParameter.OCCUR_ORG,
						CommonalityParameter.LAST_ORG,
						CommonalityParameter.TARGE_ORG,
						CommonalityParameter.CURRENT_ORG }, null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	private String findPeriodCompletedList() {
		PageInfo<LedgerPoorPeopleVo> issues = searchPoorPeopleByLevelDubboService
				.findPeriodCompletedList(poorPeople, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, new String[] {
						CommonalityParameter.OCCUR_ORG,
						CommonalityParameter.LAST_ORG,
						CommonalityParameter.TARGE_ORG,
						CommonalityParameter.CURRENT_ORG }, null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	private String findJurisdictionsCreateAndDoneList() {
		PageInfo<LedgerPoorPeopleVo> issues = searchPoorPeopleByLevelDubboService
				.findJurisdictionsCreateAndDoneList(poorPeople, page, rows,
						sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, new String[] {
						CommonalityParameter.OCCUR_ORG,
						CommonalityParameter.LAST_ORG,
						CommonalityParameter.TARGE_ORG,
						CommonalityParameter.CURRENT_ORG }, null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	private String findCompletedIssueList() {
		PageInfo<LedgerPoorPeopleVo> issues = searchPoorPeopleByLevelDubboService
				.findCompletedIssueList(poorPeople, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, new String[] {
						CommonalityParameter.OCCUR_ORG,
						CommonalityParameter.LAST_ORG,
						CommonalityParameter.TARGE_ORG,
						CommonalityParameter.CURRENT_ORG }, null);
		gridPage = new GridPage(issues);

		return SUCCESS;
	}

	private String findJurisdictionsFeedBack() {
		PageInfo<LedgerPoorPeopleVo> issues = searchPoorPeopleByLevelDubboService
				.findJurisdictionsFeedBack(poorPeople, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, new String[] {
						CommonalityParameter.OCCUR_ORG,
						CommonalityParameter.LAST_ORG,
						CommonalityParameter.TARGE_ORG,
						CommonalityParameter.CURRENT_ORG }, null);
		gridPage = new GridPage(issues);

		return SUCCESS;
	}

	private String findJurisdictionsFollow() {
		PageInfo<LedgerPoorPeopleVo> issues = searchPoorPeopleByLevelDubboService
				.findJurisdictionsFollow(poorPeople, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, new String[] {
						CommonalityParameter.OCCUR_ORG,
						CommonalityParameter.LAST_ORG,
						CommonalityParameter.TARGE_ORG,
						CommonalityParameter.CURRENT_ORG }, null);
		gridPage = new GridPage(issues);

		return SUCCESS;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public SearchLedgerPoorPeopleVo getPoorPeople() {
		return poorPeople;
	}

	public void setPoorPeople(SearchLedgerPoorPeopleVo poorPeople) {
		this.poorPeople = poorPeople;
	}

	public Long getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(Long ledgerType) {
		this.ledgerType = ledgerType;
	}

}

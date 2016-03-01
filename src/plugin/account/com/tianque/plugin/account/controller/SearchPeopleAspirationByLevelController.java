package com.tianque.plugin.account.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.account.api.SearchPeopleAspirationByLevelDubboService;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.plugin.account.constants.ThreeRecordsIssueViewType;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.plugin.account.vo.SearchPeopleAspirationVo;
import com.tianque.plugin.account.vo.ThreeRecordsIssueViewObject;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Controller("SearchPeopleAspirationByLevellevelController")
@Scope("request")
@Namespace("/ledger/searchPeopleAspiration")
public class SearchPeopleAspirationByLevelController extends BaseAction {

	private SearchPeopleAspirationVo searchVo;
	private String viewType;// 视图类型
	private Long ledgerType;

	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SearchPeopleAspirationByLevelDubboService searchIssueByLevelDubboService;

	@Action(value = "findIssueForView", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findIssueForView() throws Exception {
		if (searchVo == null) {
			errorMessage = "参数无效";
			return ERROR;
		}
		if (searchVo.getOrgLevel() == null
				&& searchVo.getOrgLevelInternalId() != null) {
			List<PropertyDict> orgLevels = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.ORGANIZATION_LEVEL, searchVo
									.getOrgLevelInternalId());
			if (orgLevels != null && orgLevels.size() > 0
					&& orgLevels.get(0) != null) {
				searchVo.setOrgLevel(orgLevels.get(0).getId());
			}
			if (searchVo.getSearchOrgId() != null) {
				Organization searchOrg = organizationDubboService
						.getFullOrgById(searchVo.getSearchOrgId());
				searchVo.setSearchOrgCode(searchOrg.getOrgInternalCode());
			}
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

	private String findJurisdictionsNeedDo() throws Exception {
		PageInfo<ThreeRecordsIssueViewObject> issues = searchIssueByLevelDubboService
				.findJurisdictionsNeedDo(searchVo, page, rows, sidx, sord);

		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	private String findJurisdictionsAssgin() throws Exception {
		PageInfo<ThreeRecordsIssueViewObject> issues = searchIssueByLevelDubboService
				.findJurisdictionsAssgin(searchVo, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	private String findJurisdictionsDone() throws Exception {
		PageInfo<ThreeRecordsIssueViewObject> issues = searchIssueByLevelDubboService
				.findJurisdictionsDone(searchVo, page, rows, sidx, sord);

		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	public String findJurisdictionsSubmit() throws Exception {
		PageInfo<ThreeRecordsIssueViewObject> issues = searchIssueByLevelDubboService
				.findJurisdictionsSubmit(searchVo, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	private String findPeriodCompletedList() throws Exception {
		PageInfo<ThreeRecordsIssueViewObject> issues = searchIssueByLevelDubboService
				.findPeriodCompletedList(searchVo, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);

		return SUCCESS;
	}

	private String findJurisdictionsCreateAndDoneList() throws Exception {
		PageInfo<ThreeRecordsIssueViewObject> issues = searchIssueByLevelDubboService
				.findJurisdictionsCreateAndDoneList(searchVo, page, rows, sidx,
						sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);

		return SUCCESS;
	}

	private String findCompletedIssueList() throws Exception {
		PageInfo<ThreeRecordsIssueViewObject> issues = searchIssueByLevelDubboService
				.findCompletedIssueList(searchVo, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);

		return SUCCESS;
	}

	private String findJurisdictionsFeedBack() throws Exception {
		PageInfo<ThreeRecordsIssueViewObject> issues = searchIssueByLevelDubboService
				.findJurisdictionsFeedBack(searchVo, page, rows, sidx, sord);
		issues = ControllerHelper.processAllOrgRelativeName(issues,
				organizationDubboService, ThreeRecordsIssueOperationUtil
						.getViewprocessParams(null), null);
		gridPage = new GridPage(issues);
		return SUCCESS;
	}

	private String findJurisdictionsFollow() throws Exception {
		PageInfo<ThreeRecordsIssueViewObject> issues = searchIssueByLevelDubboService
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

	public SearchPeopleAspirationVo getSearchVo() {
		return searchVo;
	}

	public void setSearchVo(SearchPeopleAspirationVo searchVo) {
		this.searchVo = searchVo;
	}

	public Long getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(Long ledgerType) {
		this.ledgerType = ledgerType;
	}

}

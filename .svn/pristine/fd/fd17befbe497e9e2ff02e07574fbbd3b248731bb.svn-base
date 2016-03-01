package com.tianque.baseInfo.superiorVisit.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisitHistory;
import com.tianque.baseInfo.superiorVisit.service.SuperiorVisitHistoryService;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;

@Namespace("/baseinfo/superiorVisitHistoryManage")
@Scope("request")
@SuppressWarnings("serial")
@Controller("superiorVisitHistoryController")
public class SuperiorVisitHistoryController extends BaseAction {
	private SuperiorVisitHistory visitHistory;

	private List<Long> visitTypes;

	private String visitType;

	protected String locationIds;

	@Autowired
	private SuperiorVisitHistoryService superiorVisitHistoryService;

	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/supervisorManage/supervisitHistory/maintainSupervisitHistory.jsp"),
			@Result(name = "search", location = "/baseinfo/commonPopulation/supervisorManage/supervisitHistory/supervisistHistoryInfoDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/commonPopulation/supervisorManage/supervisitHistory/maintainSupervisitHistory.jsp") })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(getMode())) {

		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())) {
			visitHistory = superiorVisitHistoryService
					.getSuperiorVisitHistoryById(visitHistory.getId());
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(getMode())) {
			visitHistory = superiorVisitHistoryService
					.getSuperiorVisitHistoryById(visitHistory.getId());
			return "view";
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(getMode())
				|| "viewList".equalsIgnoreCase(getMode())) {
			if (id != null) {
				if (visitHistory == null) {
					visitHistory = new SuperiorVisitHistory();
				}
				visitHistory.setVisitId(id);
			}
			return "search";
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/supervisorManage/supervisitHistory/maintainSupervisitHistory.jsp"),
			@Result(name = "search", location = "/baseinfo/commonPopulation/supervisorManage/supervisitHistory/supervisistHistoryInfoDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/commonPopulation/supervisorManage/supervisitHistory/maintainSupervisitHistory.jsp") })
	public String dispatchOperateByEncrypt() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(getMode())) {

		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())) {
			visitHistory = superiorVisitHistoryService
					.getSuperiorVisitHistoryById(visitHistory.getId());
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(getMode())) {
			visitHistory = superiorVisitHistoryService
					.getSuperiorVisitHistoryById(visitHistory.getId());
			return "view";
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(getMode())
				|| "viewList".equalsIgnoreCase(getMode())) {
			if (id != null) {
				if (visitHistory == null) {
					visitHistory = new SuperiorVisitHistory();
				}
				visitHistory.setVisitId(id);
			}
			return "search";
		}
		return SUCCESS;
	}

	@Action(value = "findSuperiorVisitHistorys", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSuperiorVisitHistorys() throws Exception {
		if (visitHistory == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(
					superiorVisitHistoryService.findSuperiorVisitHistorys(
							visitHistory, page, rows, sidx, sord));
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "addSuperiorVisitHistory")
	@Action(value = "addSuperiorVisitHistory", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addSuperiorVisitHistory() throws Exception {
		if (null != visitType && !"".equals(visitType)) {
			String[] visitArr = visitType.split(",");
			visitTypes = new ArrayList();
			for (String visitTypeId : visitArr) {
				visitTypes.add(Long.parseLong(visitTypeId));
			}
		}
		visitHistory = superiorVisitHistoryService.addSuperiorVisitHistory(
				visitHistory, visitTypes);
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateSuperiorVisitHistory")
	@Action(value = "updateSuperiorVisitHistory", results = { @Result(name = "success", type = "json", params = {
			"root", "population", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String updateSuperiorVisitHistory() throws Exception {
		if (null != visitType && !"".equals(visitType)) {
			String[] visitArr = visitType.split(",");
			visitTypes = new ArrayList();
			for (String visitTypeId : visitArr) {
				visitTypes.add(Long.parseLong(visitTypeId));
			}
		}
		visitHistory = superiorVisitHistoryService.updateSuperiorVisitHistory(
				visitHistory, visitTypes);
		return SUCCESS;
	}

	@PermissionFilter(ename = "deleteSuperiorVisitHistory")
	@Action(value = "deleteSuperiorVisitByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		superiorVisitHistoryService
				.deleteSuperiorVisitHistoryByIds(analyze(locationIds));
		return SUCCESS;
	}

	private PageInfo<SuperiorVisitHistory> emptyPage(int pageSize) {
		PageInfo<SuperiorVisitHistory> pageInfo = new PageInfo<SuperiorVisitHistory>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SuperiorVisitHistory>());
		return pageInfo;
	}

	public List<Long> getVisitTypes() {
		return visitTypes;
	}

	public void setVisitTypes(List<Long> visitTypes) {
		this.visitTypes = visitTypes;
	}

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public SuperiorVisitHistory getVisitHistory() {
		return visitHistory;
	}

	public void setVisitHistory(SuperiorVisitHistory visitHistory) {
		this.visitHistory = visitHistory;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}

}

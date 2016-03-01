package com.tianque.xichang.working.poorPeople.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.base.BaseAction;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.keyGenerator.WorkingSerialKeyGenerator;
import com.tianque.xichang.working.domain.WorkingPrefix;
import com.tianque.xichang.working.poorPeople.domain.PoorPeople;
import com.tianque.xichang.working.poorPeople.service.PoorPeopleService;

/**
 * @ClassName: PoorPeopleController
 * @Description: 三本台账-困难群众台账-控制器
 * @author wangxiaohu wsmalltiger@163.com
 * @date Dec 23, 2013 5:28:08 PM
 */
@Scope("prototype")
@Namespace("/account/poorPeopleManage")
public class PoorPeopleController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger(PoorPeopleController.class);

	@Autowired
	private PoorPeopleService poorPeopleService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private WorkingSerialKeyGenerator workingSerialKeyGenerator;

	private PoorPeople poorPeople;

	private String ids;

	private String returnStr;

	private String module;// 模块

	private String listType;// 列表类型

	private Long orgId;

	@PermissionFilters({
			@PermissionFilter(ename = "unDoPoorPeopleListManagement", actionName = "findUndoPoorPeopleForList"),
			@PermissionFilter(ename = "donePoorPeopleListManagement", actionName = "findUndoPoorPeopleForList"),
			@PermissionFilter(ename = "unDoChildPoorPeopleListManagement", actionName = "findUndoPoorPeopleForList"),
			@PermissionFilter(ename = "doneChildPoorPeopleListManagement", actionName = "findUndoPoorPeopleForList") })
	@Action(value = "findUndoPoorPeopleForList", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "unDoPoorPeopleListManagement")
	public String findUndoPoorPeopleForList() throws Exception {
		poorPeople = poorPeople == null ? new PoorPeople() : poorPeople;
		setPages(poorPeople);
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				poorPeopleService.findUndoPoorPeopleForList(poorPeople, orgId, module, listType),
				organizationDubboService, new String[] { "organization", "occurOrg" }, null));
		return SUCCESS;
	}

	@Action(value = "addPoorPeople", results = {
			@Result(name = "success", type = "json", params = { "root", "poorPeople",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "addPoorPeople")
	public String addPoorPeople() throws Exception {
		if (poorPeople == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		poorPeople = poorPeopleService.addPoorPeople(poorPeople);
		poorPeople.getOccurOrg().setOrgName(
				organizationDubboService.getOrganizationRelativeNameByRootOrgIdAndOrgId(poorPeople
						.getOccurOrg().getId(),
						OrganizationServiceHelper.getRootOrg(organizationDubboService).getId()));
		return SUCCESS;
	}

	@Action(value = "updatePoorPeople", results = {
			@Result(name = "success", type = "json", params = { "root", "poorPeople",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "updatePoorPeople")
	public String updatePoorPeople() throws Exception {
		if (poorPeople == null || poorPeople.getId() == null
				|| poorPeople.getId().longValue() == 0L) {
			errorMessage = "参数错误";
			return ERROR;
		}
		poorPeople = poorPeopleService.updatePoorPeople(poorPeople);
		poorPeople.getOccurOrg().setOrgName(
				organizationDubboService.getOrganizationRelativeNameByRootOrgIdAndOrgId(poorPeople
						.getOccurOrg().getId(),
						OrganizationServiceHelper.getRootOrg(organizationDubboService).getId()));
		return SUCCESS;
	}

	@Action(value = "deletePoorPeople", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "deletePoorPeople")
	public String deletePoorPeople() throws Exception {
		if (ids == null || "".equals(ids)) {
			errorMessage = "参数错误";
			return ERROR;
		}
		poorPeopleService.deletePoorPeople(ids);
		return SUCCESS;
	}

	@Actions({
			@Action(value = "getPoorPeopleById", results = {
					@Result(name = "success", type = "json", params = { "root", "poorPeople",
							"ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root", "errorMessage",
							"ignoreHierarchy", "false" }) }),
			@Action(value = "dispatch", results = {
					@Result(name = "success", location = "/xichang/working/poorPeople/poorPeopleDlg.jsp"),
					@Result(name = "view", location = "/xichang/working/poorPeople/poorPeopleView.jsp"),
					@Result(name = "error", type = "json", params = { "root", "errorMessage",
							"ignoreHierarchy", "false" }) }) })
	@PermissionFilter(ename = "normalPoorPeople")
	public String getPoorPeopleById() throws Exception {
		if (poorPeople == null || poorPeople.getId() == null
				|| poorPeople.getId().longValue() == 0L) {
			errorMessage = "参数错误";
			return ERROR;
		}
		poorPeople = poorPeopleService.getPoorPeopleById(poorPeople);
		poorPeople.getOccurOrg().setOrgName(
				organizationDubboService.getOrganizationRelativeNameByRootOrgIdAndOrgId(poorPeople
						.getOccurOrg().getId(),
						OrganizationServiceHelper.getRootOrg(organizationDubboService).getId()));
		poorPeople
				.setBookingUnit(organizationDubboService.getOrganizationRelativeNameByRootOrgIdAndOrgId(
						poorPeople.getOrganization().getId(),
						OrganizationServiceHelper.getRootOrg(organizationDubboService).getId()));
		if (DialogMode.VIEW_MODE.equals(mode)) {
			return DialogMode.VIEW_MODE;
		}
		return SUCCESS;
	}

	@PermissionFilters({
			@PermissionFilter(ename = "unDoPoorPeopleListManagement", actionName = "unDoPoorPeopleList"),
			@PermissionFilter(ename = "donePoorPeopleListManagement", actionName = "donePoorPeopleList"),
			@PermissionFilter(ename = "unDoChildPoorPeopleListManagement", actionName = "unDoChildPoorPeopleList"),
			@PermissionFilter(ename = "doneChildPoorPeopleListManagement", actionName = "doneChildPoorPeopleList") })
	@Actions({
			@Action(value = "unDoPoorPeopleList", results = {
					@Result(name = "success", location = "/xichang/working/poorPeople/unDoPoorPeopleList.jsp"),
					@Result(name = "error", type = "json", params = { "root", "errorMessage",
							"ignoreHierarchy", "false" }) }),
			@Action(value = "donePoorPeopleList", results = {
					@Result(name = "success", location = "/xichang/working/poorPeople/unDoPoorPeopleList.jsp"),
					@Result(name = "error", type = "json", params = { "root", "errorMessage",
							"ignoreHierarchy", "false" }) }),
			@Action(value = "unDoChildPoorPeopleList", results = {
					@Result(name = "success", location = "/xichang/working/poorPeople/unDoPoorPeopleList.jsp"),
					@Result(name = "error", type = "json", params = { "root", "errorMessage",
							"ignoreHierarchy", "false" }) }),
			@Action(value = "doneChildPoorPeopleList", results = {
					@Result(name = "success", location = "/xichang/working/poorPeople/unDoPoorPeopleList.jsp"),
					@Result(name = "error", type = "json", params = { "root", "errorMessage",
							"ignoreHierarchy", "false" }) }) })
	public String unDoPoorPeopleList() throws Exception {
		return SUCCESS;
	}

	@Action(value = "getPoorPeopleByIdForViewTab", results = {
			@Result(name = "success", location = "/xichang/working/poorPeople/poorPeoplesViewTab.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String getPoorPeopleByIdForViewTab() throws Exception {
		if (poorPeople == null || poorPeople.getId() == null
				|| poorPeople.getId().longValue() == 0L) {
			errorMessage = "参数错误";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "toAddPoorPeople", results = {
			@Result(name = "success", location = "/xichang/working/poorPeople/poorPeopleDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "unDoPoorPeopleListManagement")
	public String toAddPoorPeople() throws Exception {
		String serialNumber = workingSerialKeyGenerator.getNextKey(WorkingPrefix.POORPEOPLE);
		poorPeople = new PoorPeople();
		poorPeople.setSerialNumber(serialNumber);
		poorPeople.setOrganization(ThreadVariable.getOrganization());
		poorPeople.setRegistrant(ThreadVariable.getUser().getName());
		return SUCCESS;
	}

	/**
	 * 验证是否存在相同的身份证
	 */
	@Action(value = "exsistedIdCard", results = {
			@Result(name = "success", type = "json", params = { "root", "returnStr",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String exsistedIdCard() throws Exception {
		if (poorPeople == null || poorPeople.getIdCardNo() == null) {
			errorMessage = "参数错误";
			return ERROR;
		} else {
			returnStr = poorPeopleService.exsistedIdCardByIdAndIdCardNo(poorPeople);
		}
		return SUCCESS;
	}

	/**
	 * 验证是否存在相同的编号
	 * 
	 * @return
	 */
	@Action(value = "hasDuplicateSerialNumber", results = {
			@Result(name = "success", type = "json", params = { "root", "returnStr",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String hasDuplicateSerialNumber() throws Exception {
		if (poorPeople == null || poorPeople.getSerialNumber() == null) {
			errorMessage = "参数错误";
			return ERROR;
		} else {
			returnStr = poorPeopleService.hasDuplicateSerialNumber(poorPeople);
		}
		return SUCCESS;
	}

	private void setPages(BaseDomain baseDomain) {
		baseDomain.setSortField(sidx);
		baseDomain.setOrder(sord);
		((PoorPeople) baseDomain).setPage(page);
		((PoorPeople) baseDomain).setRows(rows);
	}

	public PoorPeople getPoorPeople() {
		return poorPeople;
	}

	public void setPoorPeople(PoorPeople poorPeople) {
		this.poorPeople = poorPeople;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getReturnStr() {
		return returnStr;
	}

	public void setReturnStr(String returnStr) {
		this.returnStr = returnStr;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getModule() {
		return module;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getListType() {
		return listType;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getOrgId() {
		return orgId;
	}

}

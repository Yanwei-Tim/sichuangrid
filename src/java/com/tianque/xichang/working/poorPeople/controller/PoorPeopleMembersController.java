package com.tianque.xichang.working.poorPeople.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.vo.GridPage;
import com.tianque.xichang.working.poorPeople.domain.PoorPeopleMembers;
import com.tianque.xichang.working.poorPeople.service.PoorPeopleMembersService;

/**
 * @ClassName: PoorPeopleController
 * @Description: 三本台账-困难群众台账-家庭成员控制器
 * @author wangxiaohu wsmalltiger@163.com
 * @date Dec 23, 2013 5:28:08 PM
 */
@Scope("prototype")
@Namespace("/account/poorPeopleMemberManage")
public class PoorPeopleMembersController extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger(PoorPeopleMembersController.class);

	@Autowired
	private PoorPeopleMembersService poorPeopleMembersService;

	private PoorPeopleMembers poorPeopleMembers;

	private String ids;

	@Action(value = "getPoorPeopleMemberForList", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "maintainMember")
	public String getPoorPeopleMemberForList() throws Exception {
		poorPeopleMembers = poorPeopleMembers == null ? new PoorPeopleMembers() : poorPeopleMembers;
		setPages(poorPeopleMembers);
		if (poorPeopleMembers.getPoorPeople() == null
				|| poorPeopleMembers.getPoorPeople().getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		gridPage = new GridPage(
				poorPeopleMembersService.findPoorPeopleMembersForList(poorPeopleMembers));
		return SUCCESS;
	}

	@Action(value = "poorPeopleMemberList", results = {
			@Result(name = "success", location = "/xichang/working/poorPeople/poorPeopleMemberList.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "maintainMember")
	public String poorPeopleMemberList() throws Exception {
		if (poorPeopleMembers == null || poorPeopleMembers.getPoorPeople() == null
				|| poorPeopleMembers.getPoorPeople().getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "addPoorPeopleMembers", results = {
			@Result(name = "success", type = "json", params = { "root", "poorPeopleMembers",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String addPoorPeopleMembers() throws Exception {
		if (poorPeopleMembers == null || poorPeopleMembers.getPoorPeople() == null
				|| poorPeopleMembers.getPoorPeople().getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		poorPeopleMembers = poorPeopleMembersService.addPoorPeopleMembers(poorPeopleMembers);
		return SUCCESS;
	}

	@Action(value = "updatePoorPeopleMembers", results = {
			@Result(name = "success", type = "json", params = { "root", "poorPeopleMembers",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String updatePoorPeopleMembers() throws Exception {
		if (poorPeopleMembers == null || poorPeopleMembers.getId() == null
				|| poorPeopleMembers.getId().longValue() == 0L
				|| poorPeopleMembers.getPoorPeople() == null
				|| poorPeopleMembers.getPoorPeople().getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		poorPeopleMembers = poorPeopleMembersService.updatePoorPeopleMembers(poorPeopleMembers);
		return SUCCESS;
	}

	@Action(value = "deletePoorPeopleMembers", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String deletePoorPeopleMembers() throws Exception {
		if (ids == null || "".equals(ids)) {
			errorMessage = "参数错误";
			return ERROR;
		}
		poorPeopleMembersService.deletePoorPeopleMembers(ids);
		return SUCCESS;
	}

	@Actions({
			@Action(value = "getPoorPeopleMembersById", results = {
					@Result(name = "success", type = "json", params = { "root",
							"poorPeopleMembers", "ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root", "errorMessage",
							"ignoreHierarchy", "false" }) }),
			@Action(value = "getPoorPeopleMembersByIdForEdit", results = {
					@Result(name = "success", location = "/xichang/working/poorPeople/poorPeopleMembersDlg.jsp"),
					@Result(name = "error", type = "json", params = { "root", "errorMessage",
							"ignoreHierarchy", "false" }) }),
			@Action(value = "getPoorPeopleMembersByIdForView", results = {
					@Result(name = "success", location = "/xichang/working/poorPeople/poorPeopleMembersView.jsp"),
					@Result(name = "error", type = "json", params = { "root", "errorMessage",
							"ignoreHierarchy", "false" }) }) })
	public String getPoorPeopleMembersById() throws Exception {
		if (poorPeopleMembers == null || poorPeopleMembers.getId() == null
				|| poorPeopleMembers.getId().longValue() == 0L) {
			errorMessage = "参数错误";
			return ERROR;
		}
		poorPeopleMembers = poorPeopleMembersService.getPoorPeopleMembersById(poorPeopleMembers);
		return SUCCESS;
	}

	@Action(value = "toAddPoorPeopleMembers", results = {
			@Result(name = "success", location = "/xichang/working/poorPeople/poorPeopleMembersDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String toAddPoorPeopleMembers() throws Exception {
		if (poorPeopleMembers == null || poorPeopleMembers.getPoorPeople() == null
				|| poorPeopleMembers.getPoorPeople().getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		mode = "add";
		return SUCCESS;
	}

	private void setPages(BaseDomain baseDomain) {
		baseDomain.setSortField(sidx);
		baseDomain.setOrder(sord);
		((PoorPeopleMembers) baseDomain).setPage(page);
		((PoorPeopleMembers) baseDomain).setRows(rows);
	}

	public PoorPeopleMembers getPoorPeopleMembers() {
		return poorPeopleMembers;
	}

	public void setPoorPeopleMembers(PoorPeopleMembers poorPeopleMembers) {
		this.poorPeopleMembers = poorPeopleMembers;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}

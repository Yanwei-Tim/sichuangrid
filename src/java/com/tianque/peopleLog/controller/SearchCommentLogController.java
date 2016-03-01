package com.tianque.peopleLog.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.domain.CommentLog;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.SearchPeopleLogVo;
import com.tianque.peopleLog.service.SearchCommentLogService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Scope("prototype")
@Controller("searchCommentLogController")
@Namespace("/peopleLog/searchCommentLog")
public class SearchCommentLogController extends BaseAction {

	private SearchPeopleLogVo searchPeopleLogVo;

	private CommentLog commentLog;

	private PeopleLog peopleLog;

	private Long orgId;

	@Autowired
	private SearchCommentLogService searchCommentLogService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	/**
	 * 根据查询条件分页查询我的评论列表
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "findCommentLogByQueryCondition", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findCommentLogByQueryCondition() throws Exception {
		if (searchPeopleLogVo == null) {
			searchPeopleLogVo = new SearchPeopleLogVo();
		}
		searchPeopleLogVo.setUserId(ThreadVariable.getUser().getId());

		PageInfo<CommentLog> pageInfo = ControllerHelper
				.processAllOrgRelativeName(searchCommentLogService
						.findCommentLogByCondition(searchPeopleLogVo, page,
								rows, sidx, sord), organizationDubboService,
						new String[] { "organization" }, orgId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 根据条件查询下辖日志列表
	 * 
	 * @return
	 */
	@Action(value = "findSubLogByQueryCondition", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSubLogByQueryCondition() throws Exception {

		// if (searchPeopleLogVo == null) {
		// this.errorMessage = "参数错误";
		// }
		if (null == searchPeopleLogVo) {
			searchPeopleLogVo = new SearchPeopleLogVo();
		}

		PageInfo<PeopleLog> pageInfo = ControllerHelper
				.processAllOrgRelativeName(searchCommentLogService
						.findSubLogByQueryCondition(orgId, searchPeopleLogVo,
								page, rows, sidx, sord,
								searchPeopleLogVo.getIsPeer()),
						organizationDubboService, new String[] { "organization" },
						orgId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String fastSearch() throws Exception {
		return findCommentLogByQueryCondition();
	}

	@Action(value = "quickSearch", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String quickSearch() throws Exception {
		return findSubLogByQueryCondition();
	}

	public CommentLog getCommentLog() {
		return commentLog;
	}

	public void setCommentLog(CommentLog commentLog) {
		this.commentLog = commentLog;
	}

	public SearchPeopleLogVo getSearchPeopleLogVo() {
		return searchPeopleLogVo;
	}

	public void setSearchPeopleLogVo(SearchPeopleLogVo searchPeopleLogVo) {
		this.searchPeopleLogVo = searchPeopleLogVo;
	}

	public PeopleLog getPeopleLog() {
		return peopleLog;
	}

	public void setPeopleLog(PeopleLog peopleLog) {
		this.peopleLog = peopleLog;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}

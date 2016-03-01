package com.tianque.peopleLog.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.SearchPeopleLogVo;
import com.tianque.peopleLog.service.SearchPeopleLogService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Scope("prototype")
@Controller("searchPeopleLogController")
@Namespace("/peopleLog/searchPeopleLog")
public class SearchPeopleLogController extends BaseAction {

	@Autowired
	private SearchPeopleLogService searchPeopleLogService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private static Logger logger = LoggerFactory
			.getLogger(SearchPeopleLogController.class);
	private Long orgId;
	private Boolean isComment;
	private SearchPeopleLogVo searchPeopleLogVo;
	private PeopleLog peopleLog;

	@Action(value = "findPeopleLogByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findPeopleLogByQueryCondition() throws Exception {
		if (searchPeopleLogVo == null && isComment == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if (null == isComment) {
			isComment = false;
		}
		if (isComment) {
			if (searchPeopleLogVo.getSumCommentMin() == null
					|| searchPeopleLogVo.getSumCommentMin() < 1) {
				searchPeopleLogVo.setSumCommentMin(1L);
			}
		}

		if (null == searchPeopleLogVo) {
			searchPeopleLogVo = new SearchPeopleLogVo();
			searchPeopleLogVo.setUserId(ThreadVariable.getUser().getId());
		} else {
			searchPeopleLogVo.setUserId(ThreadVariable.getUser().getId());
		}

		PageInfo<PeopleLog> pageInfo = ControllerHelper
				.processAllOrgRelativeName(searchPeopleLogService
						.findPeopleLogByQueryCondition(searchPeopleLogVo, page,
								rows, sidx, sord), organizationDubboService,
						new String[] { "organization" }, orgId);

		gridPage = new GridPage(pageInfo);

		return SUCCESS;

	}

	public void setSearchPeopleLogVo(SearchPeopleLogVo searchPeopleLogVo) {
		this.searchPeopleLogVo = searchPeopleLogVo;
	}

	public SearchPeopleLogVo getSearchPeopleLogVo() {
		return searchPeopleLogVo;
	}

	public void setPeopleLog(PeopleLog peopleLog) {
		this.peopleLog = peopleLog;
	}

	public PeopleLog getPeopleLog() {
		return peopleLog;
	}

	public void setIsComment(Boolean isComment) {
		this.isComment = isComment;
	}

	public Boolean getIsComment() {
		return isComment;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
}

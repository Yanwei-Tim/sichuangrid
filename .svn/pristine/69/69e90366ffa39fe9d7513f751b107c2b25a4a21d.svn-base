package com.tianque.platformMessage.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Role;
import com.tianque.domain.User;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.domain.SearchPlatformMessageVo;
import com.tianque.platformMessage.service.SearchPlatformMessageService;
import com.tianque.sysadmin.service.PermissionService;

@Namespace("/interactive/searchPlatformMessage")
@Controller("searchPlatformMessageController")
@Scope("prototype")
@Transactional
public class SearchPlatformMessageController extends BaseAction {
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private SearchPlatformMessageService searchPlatformMessageService;

	private SearchPlatformMessageVo searchPlatformMessageVo;

	private Integer isAdmin;

	/***
	 * 发件箱查询
	 * 
	 * @return
	 */
	@Action(value = "searchOutboxPlatformMessage", results = { @Result(name = "success", location = "/interaction/platformMessage/outbox/simpleOutboxPlatformMessageList.jsp") })
	@PermissionFilter(ename = "searchPmOutbox")
	public String searchOutboxPlatformMessage() {
		if (searchPlatformMessageVo == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		PageInfo<PlatformMessage> pageInfo = searchPlatformMessageService
				.searchOutboxPlatformMessage(setPlatformMessageVoSendUserId(),
						page, rows, sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/***
	 * 收件箱查询 系统消息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "searchPmInbox")
	@Action(value = "searchInboxPlatformMessage", results = {
			@Result(name = "searchInboxSystemMessage", location = "/interaction/platformMessage/inbox/simpleInboxPlatformMessageList.jsp"),
			@Result(name = "searchInboxSelfMessage", location = "/interaction/platformMessage/inbox/simpleInboxPlatformSelfMessageList.jsp") })
	public String searchInboxPlatformMessage() {
		if (searchPlatformMessageVo == null) {
			searchPlatformMessageVo = new SearchPlatformMessageVo();
		}
		PageInfo<PlatformMessage> pageInfo = searchPlatformMessageService
				.searchInboxPlatformMessage(setPlatformMessageVoUserId(), page,
						rows, sidx, sord);
		gridPage = new GridPage(pageInfo);
		return mode;
	}

	// /***
	// * 收件箱查询 个人消息
	// *
	// * @return
	// */
	// @PermissionFilter(ename = "searchPmInbox")
	// @Action(value = "searchSelfInboxPlatformMessage", results = {
	// @Result(name = "success", location =
	// "/interaction/platformMessage/inbox/simpleInboxPlatformSelfMessageList.jsp")
	// })
	// public String searchSelfInboxPlatformMessage() {
	// if (searchPlatformMessageVo == null) {
	// searchPlatformMessageVo = new SearchPlatformMessageVo();
	// }
	// PageInfo<PlatformMessage> pageInfo = searchPlatformMessageService
	// .searchInboxPlatformMessage(setPlatformMessageVoUserId(), page, rows,
	// sidx, sord);
	// gridPage = new GridPage(pageInfo);
	// return SUCCESS;
	// }

	private SearchPlatformMessageVo setPlatformMessageVoSendUserId() {
		User user = ThreadVariable.getUser();
		if (searchPlatformMessageVo == null) {
			searchPlatformMessageVo = new SearchPlatformMessageVo();
		}
		searchPlatformMessageVo.setSendUserId(user.getId());
		return searchPlatformMessageVo;
	}

	private SearchPlatformMessageVo setPlatformMessageVoUserId() {
		User user = ThreadVariable.getUser();
		if (searchPlatformMessageVo == null) {
			searchPlatformMessageVo = new SearchPlatformMessageVo();
		}
		searchPlatformMessageVo.setUserId(user.getId());
		searchPlatformMessageVo.setOrgId(user.getOrganization().getId());
		List<Role> roleList = permissionService.findRolesByUserId(user.getId());
		Long[] roleIds = new Long[roleList.size()];
		for (int i = 0; i < roleList.size(); i++) {

			roleIds[i] = roleList.get(i).getId();
		}
		searchPlatformMessageVo.setRoleIds(roleIds);
		searchPlatformMessageVo.setIsAdmin(isAdmin);
		return searchPlatformMessageVo;
	}

	public SearchPlatformMessageVo getSearchPlatformMessageVo() {
		return searchPlatformMessageVo;
	}

	public void setSearchPlatformMessageVo(
			SearchPlatformMessageVo searchPlatformMessageVo) {
		this.searchPlatformMessageVo = searchPlatformMessageVo;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

}

package com.tianque.issue.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.IssueMessageRemind;
import com.tianque.issue.domain.vo.SearchIssueMessageRemindVo;
import com.tianque.issue.service.IssueMessageRemindService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 事件类型越级短信提醒:服务前端控制类
 * 
 * @author
 * @date 2013-11-22 14:51:42
 */
@Namespace("/issueMessageRemindManage")
@Scope("request")
@Controller("issueMessageRemindController")
public class IssueMessageRemindController extends BaseAction {

	@Autowired
	private IssueMessageRemindService issueMessageRemindService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private IssueMessageRemind issueMessageRemind;
	private SearchIssueMessageRemindVo searchissueMessageRemindVo;
	private String ids;
	private Long organizationId;

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	@Action(value = "getIssueMessageRemindById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueMessageRemind", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "issueMessageRemindManagement")
	public String getIssueMessageRemindById() throws Exception {
		if (id == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		issueMessageRemind = issueMessageRemindService.get(id);
		return SUCCESS;
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	@Action(value = "addIssueMessageRemind", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueMessageRemind", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addIssueMessageRemind")
	public String addIssueMessageRemind() throws Exception {
		if (issueMessageRemind == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		issueMessageRemind = issueMessageRemindService.add(issueMessageRemind);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updateIssueMessageRemind", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueMessageRemind", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updateIssueMessageRemind")
	public String updateIssueMessageRemind() throws Exception {
		if (issueMessageRemind == null || issueMessageRemind.getId() == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		issueMessageRemind = issueMessageRemindService
				.update(issueMessageRemind);
		return SUCCESS;
	}

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	@Action(value = "deleteIssueMessageRemindById", results = {
			@Result(name = "success", type = "json", params = { "root", "id" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteIssueMessageRemind")
	public String deleteIssueMessageRemindById() throws Exception {
		if (id == null) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		issueMessageRemindService.delete(id);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteIssueMessageRemindByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteIssueMessageRemind")
	public String deleteIssueMessageRemindByIds() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		String[] idsStr = ids.split(",");
		Long[] idsLong = new Long[idsStr.length];
		String id = null;
		for (int i = 0; i < idsStr.length; i++) {
			id = idsStr[i];
			if (id != null && !"".equals(id.trim())) {
				idsLong[i] = Long.parseLong(idsStr[i]);
			}
		}
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		issueMessageRemindService.delete(idsLong);
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @return Pager对象
	 */
	@Action(value = "findIssueMessageRemindPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "issueMessageRemindManagement")
	public String findIssueMessageRemindPagerBySearchVo() throws Exception {
		if (searchissueMessageRemindVo == null || organizationId == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		if (organization == null) {
			this.errorMessage = "网格不存在";
			return ERROR;
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				issueMessageRemindService.findPagerBySearchVo(
						searchissueMessageRemindVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" },
				organizationId));
		return SUCCESS;
	}

	public IssueMessageRemind getIssueMessageRemind() {
		return issueMessageRemind;
	}

	public void setIssueMessageRemind(IssueMessageRemind issueMessageRemind) {
		this.issueMessageRemind = issueMessageRemind;
	}

	public SearchIssueMessageRemindVo getSearchIssueMessageRemindVo() {
		return searchissueMessageRemindVo;
	}

	public void setSearchIssueMessageRemindVo(
			SearchIssueMessageRemindVo searchissueMessageRemindVo) {
		this.searchissueMessageRemindVo = searchissueMessageRemindVo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}

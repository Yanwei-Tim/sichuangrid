package com.tianque.xichang.working.parameterEvaluation.timeLimit.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.domain.Parametertimelimit;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.domain.vo.SearchParametertimelimitVo;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.service.ParametertimelimitService;

/**
 * 三本台账时限标准表:服务前端控制类
 * 
 * @author
 * @date 2014-03-04 10:34:33
 */
@Namespace("/parametertimelimitManage")
@Scope("request")
@Controller("parametertimelimitController")
public class ParametertimelimitController extends BaseAction {

	@Autowired
	private ParametertimelimitService parametertimelimitService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private Parametertimelimit parametertimelimit;
	private String ids;
	private Long id;
	private Long organizationId;

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *        对象
	 * @return ID
	 */
	@Action(value = "addParametertimelimit", results = {
			@Result(name = "success", type = "json", params = { "root", "parametertimelimit",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	@PermissionFilter(ename = "addTimeLimit")
	public String addParametertimelimit() throws Exception {
		if (parametertimelimit == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		parametertimelimit = parametertimelimitService.add(parametertimelimit);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *        对象
	 */
	@Action(value = "updateParametertimelimit", results = {
			@Result(name = "success", type = "json", params = { "root", "parametertimelimit",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	@PermissionFilter(ename = "updateTimeLimit")
	public String updateParametertimelimit() throws Exception {
		/*
		 * if (parametertimelimit == null || parametertimelimit.getId() ==
		 * null || !checkOrganization(parametertimelimit.getOrganization()))
		 * { this.errorMessage = "参数无效!"; return ERROR; }
		 */
		parametertimelimit = parametertimelimitService.update(parametertimelimit);
		return SUCCESS;
	}

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *        记录ID
	 */
	@Action(value = "deleteParametertimelimitById", results = {
			@Result(name = "success", type = "json", params = { "root", "id" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	@PermissionFilter(ename = "deleteTimeLimit")
	public String deleteParametertimelimitById() throws Exception {
		if (id == null) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		// parametertimelimitService.delete(Long.parseLong(id));
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *        ID数组
	 */
	@Action(value = "deleteParametertimelimitByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	@PermissionFilter(ename = "deleteTimeLimit")
	public String deleteParametertimelimitByIds() throws Exception {
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
		parametertimelimitService.delete(idsLong);
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *        Pager对象
	 * @return Pager对象
	 */
	@Action(value = "findParametertimelimitPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	@PermissionFilter(ename = "timeLimit")
	public String findParametertimelimitPagerBySearchVo() throws Exception {

		gridPage = new GridPage(parametertimelimitService.findPagerBySearchVo(
				new SearchParametertimelimitVo(), page, rows, sidx, sord));

		return SUCCESS;
	}

	@Actions({ @Action(value = "dispatch", results = {
			@Result(name = "edit", location = "/xichang/working/parameterEvaluation/timeLimit/parametertimelimitDlg.jsp"),
			@Result(name = "view", location = "/xichang/working/parameterEvaluation/timeLimit/parametertimelimitView.jsp"),
			@Result(name = "add", location = "/xichang/working/parameterEvaluation/timeLimit/parametertimelimitDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) }) })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			return DialogMode.ADD_MODE;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			parametertimelimit = parametertimelimitService.get(parametertimelimit.getId());
			return DialogMode.EDIT_MODE;
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			parametertimelimit = parametertimelimitService.get(parametertimelimit.getId());
			return DialogMode.VIEW_MODE;
		}
		return ERROR;
	}

	public Parametertimelimit getParametertimelimit() {
		return parametertimelimit;
	}

	public void setParametertimelimit(Parametertimelimit parametertimelimit) {
		this.parametertimelimit = parametertimelimit;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

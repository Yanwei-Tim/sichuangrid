package com.tianque.plugin.judgmentAnalysis.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.analysis.api.DimensionDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.Dimension;

@Scope("prototype")
@Namespace("/judgmentAnalysis/dimension")
@Controller("dimensionController")
public class DimensionController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(DimensionController.class);
	@Autowired
	private DimensionDubboService dimensionDubboService;
	private Dimension dimension;

	@Action(value = "findDimensionForPage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "excludeNullProperties", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findDimensionForPage() {
		try {
			PageInfo<Dimension> pageInfo = dimensionDubboService
					.findDimensionForPage(page, rows, sidx, sord, dimension);
			gridPage = new GridPage(pageInfo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * @return 跳转页面
	 */
	/**
	 * @PermissionFilters({
	 * @PermissionFilter( ename = "addDimension" , actionName =
	 *                    "addDimensionView"),
	 * @PermissionFilter( ename = "updateDimension" , actionName =
	 *                    "updateDimensionView"),
	 * @PermissionFilter( ename = "viewDimension" , actionName =
	 *                    "viewDimension"), }) *
	 */
	@Actions(value = {
			@Action(value = "updateDimensionView", results = {
					@Result(name = "success", location = "/template/judgmentAnalysis/dimension/maintainDimension.ftl"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "addDimensionView", results = {
					@Result(name = "success", location = "/template/judgmentAnalysis/dimension/maintainDimension.ftl"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "viewDimension", results = {
					@Result(name = "success", location = "/template/judgmentAnalysis/dimension/maintainDimension.ftl"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatch() {
		try {
			if (DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())
					|| DialogMode.VIEW_MODE.equalsIgnoreCase(getMode())) {
				if (dimension == null || dimension.getId() == null) {
					errorMessage = "请填写数据！";
					return ERROR;
				}
				dimension = this.dimensionDubboService
						.getDimensionById(dimension.getId());
				return SUCCESS;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	/**
	 * @PermissionFilters({
	 * @PermissionFilter( ename = "addDimension" , actionName = "addDimension"),
	 * @PermissionFilter( ename = "updateDimension" , actionName =
	 *                    "updateDimension"), })
	 */
	@Actions(value = {
			@Action(value = "addDimension", results = {
					@Result(name = "success", type = "json", params = { "root",
							"dimension", "excludeNullProperties", "true",
							"ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "updateDimension", results = {
					@Result(name = "success", type = "json", params = { "root",
							"dimension", "excludeNullProperties", "true",
							"ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String operatorDimension() {
		try {
			if (dimension == null) {
				errorMessage = "请填写数据！";
				return ERROR;
			}
			if (DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())) {
				if (dimension.getId() == null) {
					errorMessage = "请填写数据！";
					return ERROR;
				}
				dimension = dimensionDubboService.updateDimension(dimension);
			} else if (DialogMode.ADD_MODE.equalsIgnoreCase(getMode())) {
				dimension = dimensionDubboService.addDimension(dimension);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	// @PermissionFilter(ename = "deleteDimensionById")
	@Action(value = "deleteDimensionById", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteDimensionById() {
		try {
			if (id == null) {
				errorMessage = "请选择要删除的数据！";
				return null;
			}
			this.dimensionDubboService.deleteDimensionById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 校验
	 * 
	 * @return
	 */
	@Action(value = "validateDimension", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String validateDimension() {
		try {
			if (dimension == null) {
				return ERROR;
			}
			Dimension ch = dimensionDubboService
					.getDimensionByConditions(dimension);
			if (ch == null) {
				return SUCCESS;
			}
			if (DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())) {
				if (ch.getId().equals(dimension.getId())) {
					return SUCCESS;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
			return ERROR;
		}
		return ERROR;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

}

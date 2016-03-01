package com.tianque.plugin.judgmentAnalysis.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.analysis.api.DimensionCombinationDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.DimensionCombination;
import com.tianque.plugin.judgmentAnalysis.util.DialogMode;

@Scope("prototype")
@Namespace("/judgmentAnalysis/dimensionCombinationManage")
@Controller("dimensionCombinationController")
public class DimensionCombinationController extends BaseAction {

	@Autowired
	private DimensionCombinationDubboService dimensionCombinationDubboService;

	private DimensionCombination dimensionCombination;

	private Long id;

	private String ids;

	@Action(value = "dispatch", results = { @Result(name = "success", location = "/template/judgmentAnalysis/dimensionCombination/dimensionCombinationMaintainDlg.ftl") })
	public String dispatch() throws Exception {

		if (DialogMode.ADD_MODE.equals(mode)) {
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			return getDimensionCombinationById();
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return getDimensionCombinationById();
		}
		return ERROR;
	}

	// @PermissionFilter(ename = "viewDimensionCombination")
	@Action(value = "getDimensionCombinationById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"dimensionCombination", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getDimensionCombinationById() {
		if (id == null) {
			errorMessage = "请选择要查看的数据";
			return ERROR;
		}
		try {
			dimensionCombination = dimensionCombinationDubboService
					.getDimensionCombinationById(id);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "查看数据失败";
			return ERROR;
		}
		return SUCCESS;
	}

	// @PermissionFilter(ename = "addDimensionCombination")
	@Action(value = "addDimensionCombination", results = {
			@Result(name = "success", type = "json", params = { "root",
					"dimensionCombination", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addDimensionCombination() {
		if (dimensionCombination == null) {
			errorMessage = "请填入数据再修改";
			return ERROR;
		}
		try {
			dimensionCombination = dimensionCombinationDubboService
					.addDimensionCombination(dimensionCombination);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "添加数据失败";
			return ERROR;
		}
		return SUCCESS;
	}

	// @PermissionFilter(ename = "updateDimensionCombination")
	@Action(value = "updateDimensionCombination", results = {
			@Result(name = "success", type = "json", params = { "root",
					"dimensionCombination", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDimensionCombination() {
		if (dimensionCombination == null
				|| dimensionCombination.getId() == null) {
			errorMessage = "请填入数据再修改";
			return ERROR;
		}
		try {
			dimensionCombination = dimensionCombinationDubboService
					.updateDimensionCombination(dimensionCombination);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "添加数据失败";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "findDimensionCombinationForPage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findDimensionCombinationForPage() {
		try {
			PageInfo<DimensionCombination> pager = dimensionCombinationDubboService
					.findDimensionCombinationForPage(page, rows, sidx, sord,
							dimensionCombination);
			gridPage = new GridPage(pager);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "查询失败!";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "deleteDimensionCombinationByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteDimensionCombinationByIds() {

		if (ids == null || ids.equals("")) {
			errorMessage = "请选择一条或者多条记录进行操作！";
			return ERROR;
		}
		try {
			dimensionCombinationDubboService
					.deleteDimensionCombinationByIds(StringConvertArray(ids));
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "删除失败!";
			return ERROR;
		}
		return SUCCESS;
	}

	private List<Long> StringConvertArray(String str) {
		List<Long> listLong = new ArrayList<Long>();
		if (str != null && str.length() > 0) {
			String[] strArray = str.split(",");
			for (int i = 0; i < strArray.length; i++) {
				if (!strArray[i].trim().equals("")) {
					listLong.add(Long.valueOf(strArray[i]));
				}
			}
		}
		return listLong;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public DimensionCombination getDimensionCombination() {
		return dimensionCombination;
	}

	public void setDimensionCombination(
			DimensionCombination dimensionCombination) {
		this.dimensionCombination = dimensionCombination;
	}

}

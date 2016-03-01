package com.tianque.plugin.judgmentAnalysis.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.analysis.api.BusinessModelDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.BusinessModel;
import com.tianque.plugin.judgmentAnalysis.util.DialogMode;

@Scope("prototype")
@Namespace("/judgmentAnalysis/businessModelManage")
@Controller("businessModelController")
public class BusinessModelController extends BaseAction {

	@Autowired
	private BusinessModelDubboService businessModelDubboService;

	private BusinessModel businessModel;
	private Long id;
	private String ids;
	private String mode;

	@Action(value = "dispatch", results = { @Result(name = "maintain", location = "/template/judgmentAnalysis/businessModel/businessModelMaintainDlg.ftl") })
	public String dispatch() throws Exception {

		if (DialogMode.ADD_MODE.equals(mode)) {
			return "maintain";
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			return getBusinessModelById();
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return getBusinessModelById();
		}
		return ERROR;
	}

	@Action(value = "addBusinessModel", results = {
			@Result(name = "success", type = "json", params = { "root",
					"businessModel", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addBusinessModel() {
		if (businessModel == null) {
			errorMessage = "请填入数据再修改";
			return ERROR;
		}
		try {
			businessModel = businessModelDubboService
					.addBusinessModel(businessModel);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "添加数据失败";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "editBusinessModel", results = {
			@Result(name = "success", type = "json", params = { "root",
					"businessModel", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String editBusinessModel() {
		if (businessModel == null) {
			errorMessage = "请填入数据再修改";
			return ERROR;
		}
		try {
			businessModel = businessModelDubboService
					.updateBusinessModel(businessModel);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "添加数据失败";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "findBusinessModelForPage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findBusinessModelForPage() {
		try {
			PageInfo<BusinessModel> pager = businessModelDubboService
					.findBusinessModelForPage(page, rows, sidx, sord,
							businessModel);
			gridPage = new GridPage(pager);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "查询失败!";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "deleteBusinessModelByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteBusinessModelByIds() {

		if (ids == null || ids.equals("")) {
			errorMessage = "请选择一条或者多条记录进行操作！";
			return ERROR;
		}
		try {
			businessModelDubboService
					.deleteBusinessModelByIds(StringConvertArray(ids));
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "删除失败!";
			return ERROR;
		}
		return SUCCESS;
	}

	private String getBusinessModelById() {
		if (id == null) {
			errorMessage = "请选择要查询的数据";
			return ERROR;
		}
		try {
			businessModel = businessModelDubboService.getBusinessModelById(id);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "查询数据失败";
			return ERROR;
		}
		return "maintain";
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

	public BusinessModel getBusinessModel() {
		return businessModel;
	}

	public void setBusinessModel(BusinessModel businessModel) {
		this.businessModel = businessModel;
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

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}

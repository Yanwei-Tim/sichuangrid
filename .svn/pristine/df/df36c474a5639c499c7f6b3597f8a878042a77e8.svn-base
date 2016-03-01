package com.tianque.plugin.judgmentAnalysis.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.analysis.api.BusinessDescriptionDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.BusinessDescription;
import com.tianque.plugin.judgmentAnalysis.util.CalendarUtil;
import com.tianque.plugin.judgmentAnalysis.util.DialogMode;

@Scope("prototype")
@Namespace("/judgmentAnalysis/businessDescriptionManage")
@Controller("businessDescriptionController")
public class BusinessDescriptionController extends BaseAction {

	@Autowired
	private BusinessDescriptionDubboService businessDescriptionService;

	private BusinessDescription businessDescription;

	private Long id;

	private String ids;

	private String content;

	private String keyName;

	private Integer year;

	private Integer month;

	private Long orgId;

	@Action(value = "dispatch", results = { @Result(name = "success", location = "/template/judgmentAnalysis/businessDescription/businessDescriptionMaintainDlg.ftl") })
	public String dispatch() throws Exception {

		if (DialogMode.ADD_MODE.equals(mode)) {
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			return getBusinessDescriptionById();
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return getBusinessDescriptionById();
		}
		return ERROR;
	}

	// @PermissionFilter(ename = "viewBusinessDescription")
	@Action(value = "getBusinessDescriptionById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"businessDescription", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getBusinessDescriptionById() {
		if (id == null) {
			errorMessage = "请选择要查看的数据";
			return ERROR;
		}
		try {
			businessDescription = businessDescriptionService
					.getBusinessDescriptionById(id);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "查看数据失败";
			return ERROR;
		}
		return SUCCESS;
	}

	// @PermissionFilter(ename = "addBusinessDescription")
	@Action(value = "addBusinessDescription", results = {
			@Result(name = "success", type = "json", params = { "root",
					"businessDescription", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addBusinessDescription() {
		if (businessDescription == null) {
			errorMessage = "请填入数据再修改";
			return ERROR;
		}
		try {
			businessDescription = businessDescriptionService
					.addBusinessDescription(businessDescription);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "添加数据失败";
			return ERROR;
		}
		return SUCCESS;
	}

	// @PermissionFilter(ename = "updateBusinessDescription")
	@Action(value = "updateBusinessDescription", results = {
			@Result(name = "success", type = "json", params = { "root",
					"businessDescription", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateBusinessDescription() {
		if (businessDescription == null || businessDescription.getId() == null) {
			errorMessage = "请填入数据再修改";
			return ERROR;
		}
		try {
			businessDescription = businessDescriptionService
					.updateBusinessDescription(businessDescription);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "添加数据失败";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "findBusinessDescriptionForPage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findBusinessDescriptionForPage() {
		try {
			PageInfo<BusinessDescription> pager = businessDescriptionService
					.findBusinessDescriptionForPage(page, rows, sidx, sord,
							businessDescription);
			gridPage = new GridPage(pager);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "查询失败!";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "deleteBusinessDescriptionByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteBusinessDescriptionByIds() {

		if (ids == null || ids.equals("")) {
			errorMessage = "请选择一条或者多条记录进行操作！";
			return ERROR;
		}
		try {
			businessDescriptionService
					.deleteBusinessDescriptionByIds(StringConvertArray(ids));
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "删除失败!";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "getDescriptionForStatistics", results = {
			@Result(name = "success", type = "json", params = { "root",
					"content", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getDescriptionForStatistics() {
		try {

			if (orgId == null) {
				orgId = ThreadVariable.getOrganization().getId();
			}

			if (year == null) {
				year = CalendarUtil.getYear(new Date());
			}

			if (month == null) {
				month = CalendarUtil.getMonth(new Date());
			}

			content = businessDescriptionService.getDescriptionForStatistics(
					keyName, year, month, orgId);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "获取失败";
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

	public BusinessDescription getBusinessDescription() {
		return businessDescription;
	}

	public void setBusinessDescription(BusinessDescription businessDescription) {
		this.businessDescription = businessDescription;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}

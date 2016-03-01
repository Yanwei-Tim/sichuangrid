package com.tianque.baseInfo.excelimportlog.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.excelimportlog.domain.ExcelImportLog;
import com.tianque.baseInfo.excelimportlog.domain.ExcelImportLogVO;
import com.tianque.baseInfo.excelimportlog.service.ExcelImportLogService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;

@Namespace("/baseinfo/excelImportLogManage")
@Scope("request")
@Controller("excelImportLogController")
public class ExcelImportLogController extends BaseAction {

	private Integer status;
	private ExcelImportLogVO excelImportLogVO;
	private ExcelImportLog excelImportLog;

	@Autowired
	private ExcelImportLogService excelImportLogService;

	@Action(value = "dispatchOperate", results = {
			@Result(name = "search", location = "/baseinfo/excelimportlog/searchExcelimportlog.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (DialogMode.SEARCH_MODE.equals(mode)) {
			return getMode();
		}
		return ERROR;
	}

	/**
	 * 信息查询
	 * 
	 * @return
	 */
	@Action(value = "searchImportLog", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchImportLog() throws Exception {
		PageInfo<ExcelImportLog> pageInfo = excelImportLogService
				.searchImportLog(page, rows, sidx, sord, status);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 高级查询
	 * 
	 * @return
	 */

	@Action(value = "selectExcelimportlog", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String selectExcelimportlog() throws Exception {
		PageInfo<ExcelImportLog> pageInfo = excelImportLogService
				.selectExcelimportlog(excelImportLogVO, page, rows, sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}
	
	@Action(value = "getExcelimportlogById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"excelImportLog", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getExcelimportlogById() throws Exception {
		excelImportLog = excelImportLogService.getExcelimportlogById(id);
		return SUCCESS;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ExcelImportLogVO getExcelImportLogVO() {
		return excelImportLogVO;
	}

	public void setExcelImportLogVO(ExcelImportLogVO excelImportLogVO) {
		this.excelImportLogVO = excelImportLogVO;
	}

	public ExcelImportLog getExcelImportLog() {
		return excelImportLog;
	}

	public void setExcelImportLog(ExcelImportLog excelImportLog) {
		this.excelImportLog = excelImportLog;
	}

}

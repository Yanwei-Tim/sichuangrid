package com.tianque.plugin.tbSchedule.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.base.BaseAction;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.plugin.tbSchedule.domain.IdCardNoExpLog;
import com.tianque.plugin.tbSchedule.domain.TBScheduleLog;
import com.tianque.plugin.tbSchedule.service.ScheduleTaskDealService;

/**
 * @ClassName: TBScheduleLogController
 * @Description: 任务调度job控制层
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年11月17日 下午4:05:26
 */
@Namespace("/tbScheduleLog")
public class TBScheduleLogController extends BaseAction {

	@Autowired
	private ScheduleTaskDealService scheduleTaskDealService;

	private TBScheduleLog tbSchedule;
	private IdCardNoExpLog idCardNoExpLog;

	@Action(value = "list", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String list() throws Exception {
		gridPage = scheduleTaskDealService.queryTBScheduleLogForPage(
				tbSchedule, page, rows, sidx, sord);
		return SUCCESS;
	}

	@Action(value = "view", results = { @Result(name = "success", location = "/template/tbSchedule/viewTBScheduleLog.ftl") })
	public String view() throws Exception {
		tbSchedule = scheduleTaskDealService
				.getFullTBScheduleLogForView(tbSchedule);
		return SUCCESS;
	}

	@Action(value = "exceptionList", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String exceptionList() {
		gridPage = scheduleTaskDealService.queryExceptionForPage(
				tbSchedule.getId(), page, rows, sidx, sord);
		return SUCCESS;
	}

	@Action(value = "illegalIdCardNoList", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String illegalIdCardNoList() {
		gridPage = scheduleTaskDealService.queryIdCardNoExpLogForPage(
				idCardNoExpLog, page, rows, sidx, sord);
		return SUCCESS;
	}

	@Action("downloadIllegalIdCardNo")
	public String downloadHouseholdStaff() throws Exception {

		List<IdCardNoExpLog> IdCardNoExpLogList = scheduleTaskDealService
				.queryIdCardNoExpLog(idCardNoExpLog, sidx, sord);
		inputStream = ExcelExportHelper.exportDateToExcel(
				SpecialGroupsContext.getIllIdCardNoArray(), IdCardNoExpLogList);
		downloadFileName = new String("异常身份证清单".getBytes("gbk"), "ISO8859-1")
				+ ".xls";

		return STREAM_SUCCESS;
	}

	public TBScheduleLog getTbSchedule() {
		return tbSchedule;
	}

	public void setTbSchedule(TBScheduleLog tbSchedule) {
		this.tbSchedule = tbSchedule;
	}

	public IdCardNoExpLog getIdCardNoExpLog() {
		return idCardNoExpLog;
	}

	public void setIdCardNoExpLog(IdCardNoExpLog idCardNoExpLog) {
		this.idCardNoExpLog = idCardNoExpLog;
	}
}

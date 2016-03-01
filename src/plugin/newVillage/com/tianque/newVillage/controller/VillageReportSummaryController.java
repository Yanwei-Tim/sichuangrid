package com.tianque.newVillage.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.newVillage.constant.ComprehensiveInfoConstant;
import com.tianque.newVillage.domain.NewVillageReport;
import com.tianque.newVillage.domain.ReportDataSummary;
import com.tianque.newVillage.service.VillageReportSummaryService;

@Scope("prototype")
@Namespace("/baseinfo/villageReportSummaryManage")
@Controller("villageReportSummaryController")
public class VillageReportSummaryController extends BaseAction {

	private String ids;
	private Integer dataType;
	private ReportDataSummary reportDataSummary;
	private Integer isPlaning;
	private List<ReportDataSummary> list;
	private List<NewVillageReport> reportList;
	private Integer checkStatus;

	@Autowired
	private VillageReportSummaryService villageReportSummaryService;

	// @Autowired
	// private OrganizationDubboService organizationDubboService;

	/**
	 * 默认查询新村建设信息
	 */
	@Action(value = "findVillageReportSummaryForlist", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findVillageReportSummaryForlist() throws Exception {
		if (isPlaning == null) {
			errorMessage = "查询出错，未获得标识信息";
			return ERROR;
		}
		PageInfo<ReportDataSummary> pageInfo = villageReportSummaryService
				.findReportSummaryForList(reportDataSummary.getOrganization()
						.getId(), reportDataSummary.getYear(),
						reportDataSummary.getQuarter(), isPlaning, page, rows,
						sidx, sord);
		gridPage = new GridPage<ReportDataSummary>(pageInfo);
		return SUCCESS;
	}

	/**
	 * 报表统计
	 */
	@Action(value = "countVillageReportSummaryForlist", results = {
			@Result(name = "success", type = "json", params = { "root",
					"reportList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String countVillageReportSummaryForlist() throws Exception {
		if (reportDataSummary == null
				|| reportDataSummary.getOrganization() == null
				|| reportDataSummary.getOrganization().getId() == null
				|| reportDataSummary.getYear() == null
		//|| reportDataSummary.getQuarter() == null
		) {
			errorMessage = "查询出错，未获得参数信息";
			return ERROR;
		}
		reportList = villageReportSummaryService.countReportSummaryForList(
				reportDataSummary.getOrganization().getId(),
				reportDataSummary.getYear(), reportDataSummary.getQuarter());
		return SUCCESS;
	}

	@Actions(value = { @Action(value = "dispatchOpearte", results = {
			@Result(name = "success", location = "/newCountryside/newCountrySideInfoSummary/reportSummaryTabList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String dispatchOpearte() throws Exception {
		if (!StringUtil.isStringAvaliable(ids)) {
			errorMessage = "未获得需要审核的数据信息";
			return ERROR;
		}
		list = villageReportSummaryService.findReportSummaryByIds(dealIds(ids));
		return SUCCESS;
	}

	@Actions(value = { @Action(value = "dispatchOpearteForMaintain", results = {
			@Result(name = "basicYearInfo", location = "/newCountryside/newCountrySideInfoSummary/addBasicYearInfo.jsp"),
			@Result(name = "newVillage", location = "/newCountryside/newCountrySideInfoSummary/addNewVillage.jsp"),
			@Result(name = "industryDevelopment", location = "/newCountryside/newCountrySideInfoSummary/addIndustryDevelopment.jsp"),
			@Result(name = "infrastructure", location = "/newCountryside/newCountrySideInfoSummary/addInfrastructure.jsp"),
			@Result(name = "commonServiceInfo", location = "/newCountryside/newCountrySideInfoSummary/addCommonServiceInfo.jsp"),
			@Result(name = "environmentalReform", location = "/newCountryside/newCountrySideInfoSummary/addEnvironmentalReform.jsp"),
			@Result(name = "organizationConstruction", location = "/newCountryside/newCountrySideInfoSummary/addOrgConstruction.jsp"),
			@Result(name = "capitalInvested", location = "/newCountryside/newCountrySideInfoSummary/addCapitalInvested.jsp"),
			@Result(name = "farmerPerIncomeInfo", location = "/newCountryside/newCountrySideInfoSummary/addFarmerPerIncomeInfo.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String dispatchOpearteForMaintain() throws Exception {
		if (!StringUtil.isStringAvaliable(ids) || dataType == null) {
			errorMessage = "未获得需要审核的数据信息";
			return ERROR;
		}
		String operateType = ComprehensiveInfoConstant.map.get(dataType);
		if (!StringUtil.isStringAvaliable(operateType)) {
			errorMessage = "跳转出现错误，请重试";
			return ERROR;
		}
		list = villageReportSummaryService.findReportSummaryByIds(dealIds(ids));
		return operateType;
	}

	@Action(value = "reportAllById", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String reportAllById() throws Exception {
		if (!StringUtil.isStringAvaliable(ids)) {
			errorMessage = "未获得需要审核的数据信息";
			return ERROR;
		}
		villageReportSummaryService.updateReportSummaryReportStatus(
				dealIds(ids), ComprehensiveInfoConstant.REPORT_YES, ids,
				isPlaning);
		return SUCCESS;
	}

	/***
	 * 数据审核状态
	 * 
	 * @param ids
	 * @return
	 */
	@Action(value = "checkReportStatus", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String checkReportStatus() throws Exception {
		if (!StringUtil.isStringAvaliable(ids) || checkStatus == null
				|| checkStatus == 0) {
			errorMessage = "数据审核失败";
			return ERROR;
		}
		villageReportSummaryService.updateReportSummaryCheckStatus(
				ids.split(","), checkStatus);
		return SUCCESS;
	}

	public Integer getIsPlaning() {
		return isPlaning;
	}

	public void setIsPlaning(Integer isPlaning) {
		this.isPlaning = isPlaning;
	}

	private List<Long> dealIds(String ids) {
		List<Long> idList = new ArrayList<Long>();
		for (String id : ids.split(",")) {
			idList.add(Long.parseLong(id));
		}
		return idList;
	}

	public List<NewVillageReport> getReportList() {
		return reportList;
	}

	public void setReportList(List<NewVillageReport> reportList) {
		this.reportList = reportList;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public List<ReportDataSummary> getList() {
		return list;
	}

	public void setList(List<ReportDataSummary> list) {
		this.list = list;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public ReportDataSummary getReportDataSummary() {
		return reportDataSummary;
	}

	public void setReportDataSummary(ReportDataSummary reportDataSummary) {
		this.reportDataSummary = reportDataSummary;
	}

}

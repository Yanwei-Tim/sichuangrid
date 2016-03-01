/**
 * 
 */
package com.tianque.serviceList.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.tianque.core.base.BaseAction;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.serviceList.domain.ServiceListReport;
import com.tianque.serviceList.service.ServiceListReportService;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
@Scope("request")
@Namespace("/serviceList/serviceListReportManage")
@Controller("serviceListReportController")
public class ServiceListReportController extends BaseAction{
	private static Logger logger = LoggerFactory
			.getLogger(ServiceListReportController.class);
	
	private Long orgId;
	private List<ServiceListReport> allList;
	private Integer searchType;//按周、按月查询
	private Integer year;
	private Integer month;
	private Integer week;
	@Autowired
	private ServiceListReportService reportService;
	
	/**
	 * 服务清单报表数据
	 * 
	 */
	@Action(value = "getReportList", results = {
			@Result(name = "success", type = "json", params = { "root", "allList" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getReportList() throws Exception {
		if (orgId == null || searchType==null) {
			this.errorMessage = "查询失败，参数获取失败!";
			return ERROR;
		}
		allList = reportService.getAllKindServiceList(orgId, searchType, year, month, week);
		return SUCCESS;
	}
	/**
	 * 服务清单报表数据导出
	 * @return
	 * @throws Exception
	 */
	@Action(value = "downloadServiceListReport", results = {
			@Result(name = "success", type = "stream", params = { "contentType", "application/vnd.ms-excel;charset=ISO8859-1",
					"inputName","inputStream","contentDisposition","attachment;filename='${downloadFileName}'","bufferSize","4096" }),
			@Result(name = "error", type = "json", params = {"root","errorMessage"}) })
	public String downloadServiceListReport() throws Exception {
		if (orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			List<ServiceListReport> records = new ArrayList<ServiceListReport>();
			records = reportService.getAllKindServiceList(orgId, searchType, year, month, week);
			inputStream = exportTaskList(records);
			downloadFileName = new String("任务清单统计报表".getBytes("gbk"),
					"ISO8859-1") + ".xls";
		}
		return SUCCESS;
	}
	
	private InputStream exportTaskList(List<ServiceListReport> records)
			throws Exception {
		ExcelWriter writer = constructExcelWriter();
		writer.addWorkSheet("服务清单统计报表");
		appendTableHead(writer, "服务清单统计报表");
		int index=0;
		for ( ;index < records.size(); index++) {
			ServiceListReport serviceListReport = records.get(index);
			appendRow(writer, index + 4,serviceListReport);
		}
		return new java.io.FileInputStream(writer.getExcelFile(UUID
				.randomUUID() + ".xls"));
	}
	
	private ExcelWriter constructExcelWriter() { 	 	
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		return writer;
	}
	
	private void appendTableHead(ExcelWriter writer, String string) {
		writer=mergeCell(0, 0, "服务清单报表",0, 17,writer);
		
		writer=mergeCell(1, 0, "区域",3, 0,writer);
		writer=mergeCell(1, 1, "发送量",3, 1,writer);
		writer=mergeCell(1, 2, "签收量",3, 2,writer);
		writer=mergeCell(1, 3, "签收量百分比",3, 3,writer);
		writer=mergeCell(1, 4, "食品药品工商管理",1, 24,writer);
		
		writer=mergeCell(2, 4, "政策法规宣传",2, 6,writer);
		writer=mergeCell(2, 7, "食品安全协管",2, 9,writer);
		writer=mergeCell(2, 10, "药品安全协管",2, 12,writer);
		writer=mergeCell(2, 13, "工商行政管理协管",2, 15,writer);
		writer=mergeCell(2, 16, "打击非法传销协管",2, 18,writer);
		writer=mergeCell(2, 19, "查处取缔无证无照经营协管",2, 21,writer);
		writer=mergeCell(2, 22, "其它情况",2, 24,writer);
		
		//政策法规宣传
		writer=mergeCell(3,4,"网格员发送",3,4,writer);
		writer=mergeCell(3,5,"已签收",3,5,writer);
		writer=mergeCell(3,6,"百分比",3,6,writer);
		//食品安全协管
		writer=mergeCell(3,7,"网格员发送",3,7,writer);
		writer=mergeCell(3,8,"已签收",3,8,writer);
		writer=mergeCell(3,9,"百分比",3,9,writer);
		//药品安全协管
		writer=mergeCell(3,10,"网格员发送",3,10,writer);
		writer=mergeCell(3,11,"已签收",3,11,writer);
		writer=mergeCell(3,12,"百分比",3,12,writer);
		//工商行政管理协管
		writer=mergeCell(3,13,"网格员发送",3,13,writer);
		writer=mergeCell(3,14,"已签收",3,14,writer);
		writer=mergeCell(3,15,"百分比",3,15,writer);
		//打击非法传销协管
		writer=mergeCell(3,16,"网格员发送",3,16,writer);
		writer=mergeCell(3,17,"已签收",3,17,writer);
		writer=mergeCell(3,18,"百分比",3,18,writer);
		//查处取缔无证无照经营协管
		writer=mergeCell(3,19,"网格员发送",3,19,writer);
		writer=mergeCell(3,20,"已签收",3,20,writer);
		writer=mergeCell(3,21,"百分比",3,21,writer);
		//其它情况
		writer=mergeCell(3,22,"网格员发送",3,22,writer);
		writer=mergeCell(3,23,"已签收",3,23,writer);
		writer=mergeCell(3,24,"百分比",3,24,writer);
	}
	
	private void appendRow(ExcelWriter writer,int index,ServiceListReport report){
		writer.addCell(index, 0, report.getOrgname().toString());
		writer.addCell(index, 1, report.getTotalSum().toString());
		writer.addCell(index, 2, report.getSignSum().toString());
		writer.addCell(index, 3, report.getSignPercent().toString());
		//政策法规宣传
		writer.addCell(index, 4, report.getPolicyPropagandaSum().toString());
		writer.addCell(index, 5, report.getPolicyPropagandaVisit().toString());
		writer.addCell(index, 6, report.getPolicyPropagandaSignPercent().toString());
		//食品安全协管
		writer.addCell(index, 7, report.getFoodSaftySum().toString());
		writer.addCell(index, 8, report.getFoodSaftyVisit().toString());
		writer.addCell(index, 9, report.getFoodSaftySignPercent().toString());
		//药品安全协管
		writer.addCell(index, 10, report.getDrugsSaftySum().toString());
		writer.addCell(index, 11, report.getDrugsSaftyVisit().toString());
		writer.addCell(index, 12, report.getDrugsSaftySignPercent().toString());
		//工商行政管理协管
		writer.addCell(index, 13, report.getBusinessManageSum().toString());
		writer.addCell(index, 14, report.getBusinessManageVisit().toString());
		writer.addCell(index, 15, report.getBusinessManageSignPercent().toString());
		//打击非法传销协管
		writer.addCell(index, 16, report.getPyramidSalesManageSum().toString());
		writer.addCell(index, 17, report.getPyramidSalesManageVisit().toString());
		writer.addCell(index, 18, report.getPyramidSalesManageSignPercent().toString());
		//查处取缔无证无照经营协管
		writer.addCell(index, 19, report.getUnlicensedManageSum().toString());
		writer.addCell(index, 20, report.getUnlicensedManageVisit().toString());
		writer.addCell(index, 21, report.getUnlicensedManageSignPercent().toString());
		//其它情况
		writer.addCell(index, 22, report.getOtherSituationManageSum().toString());
		writer.addCell(index, 23, report.getOtherSituationManageVisit().toString());
		writer.addCell(index, 24, report.getOtherSituationManageSignPercent().toString());
		
	}
	
	private ExcelWriter mergeCell(int m,int n,String str,int x,int y,ExcelWriter writer){
		writer.addCell(m, n, str).setFont("宋体", 12, true, false, false, false).mergeTo(x, y)
		.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		return writer;
	}
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public List<ServiceListReport> getAllList() {
		return allList;
	}

	public void setAllList(List<ServiceListReport> allList) {
		this.allList = allList;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
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

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public ServiceListReportService getReportService() {
		return reportService;
	}

	public void setReportService(ServiceListReportService reportService) {
		this.reportService = reportService;
	}
	
	
}

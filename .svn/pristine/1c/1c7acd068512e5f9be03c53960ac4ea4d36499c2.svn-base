package com.tianque.plugin.weChat.controller;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.plugin.weChat.service.StatisticAnalysisService;
import com.tianque.plugin.weChat.vo.StatisticAnalysisDetailVo;
import com.tianque.plugin.weChat.vo.StatisticAnalysisVo;

/**微信统计分析控制类*/
@Namespace("/statisticAnalysis")
@Scope("prototype")
@Controller("StatisticAnalysisController")
public class StatisticAnalysisController extends BaseAction {
	private static Logger logger = LoggerFactory.getLogger(StatisticAnalysisController.class);
	@Autowired
	private StatisticAnalysisService statisticAnalysisService;
	private StatisticAnalysisVo statisticAnalysisVo;
	private List<StatisticAnalysisVo> statisticAnalysisList;
	
	private boolean pageOnly;  //是否分页

	/**统计列表*/
	@Action(value = "findStatisticAnalysis", results = {
			@Result(type = "json", name = "success", params = { "root", "statisticAnalysisList",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findStatisticAnalysis() {
		statisticAnalysisList = statisticAnalysisService.findStatisticAnalysis(statisticAnalysisVo);
		return SUCCESS;
	}
	/**
	 * 导出统计数量
	 * 
	 */
	@PermissionFilter(ename = "exportWechatGroupStatistic")
	@Actions( { @Action(value = "downStatisticAnalysis", results = {
			@Result(name = "success", type = "stream", params = { "contentType",
					"application/vnd.ms-excel;charset=ISO8859-1", "inputName", "inputStream",
					"contentDisposition", "attachment;filename=${downloadFileName}" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) }) })
	public String downStatisticAnalysis() {
		if (statisticAnalysisVo == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} 
		try {
			statisticAnalysisList = statisticAnalysisService.findStatisticAnalysis(statisticAnalysisVo);
			inputStream = exportDownStatisticAnalysis(statisticAnalysisList);
			downloadFileName = new String("微信公众账号统计分析表".getBytes("gbk"), "ISO8859-1") + ".xls";
		} catch (Exception e) {
			logger.error("附件下载错误：", e);
			errorMessage = "操作失败，请联系管理员";
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**单位人员统计列表*/
	@Action(value = "findFansStatisticAnalysis", results = {
			@Result(type = "json", name = "success", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findFansStatisticAnalysis() {
		if (statisticAnalysisVo == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} 
		try {
			gridPage = new GridPage(statisticAnalysisService.findStatisticAnalysisToFans(statisticAnalysisVo,page, rows, sidx, sord));
		} catch (Exception e) {
			logger.error("附件下载错误：", e);
			errorMessage = "操作失败，请联系管理员";
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**导出单元人员统计*/
	@PermissionFilter(ename = "exportWeChatFansStatistic")
	@Action("downFansStatisticAnalysis")
	public String downFansStatisticAnalysis() {
		if (null == statisticAnalysisVo) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		PageInfo<StatisticAnalysisDetailVo> pageInfo;
		if(pageOnly){
			pageInfo = statisticAnalysisService.findStatisticAnalysisToFans(statisticAnalysisVo,page, rows, sidx, sord);
		}else{
			pageInfo = statisticAnalysisService.findStatisticAnalysisToFans(statisticAnalysisVo,1, 100000, sidx, sord);
		}
		
	 	try {
			inputStream = ExcelExportHelper.exportDateToExcel(
					SpecialGroupsContext.getFansStatisticAnalysisArray(), pageInfo.getResult());
			downloadFileName = new String("微信单位人员统计".getBytes("gbk"), "ISO8859-1") + ".xls";
		} catch (Exception e) {
			errorMessage = e.getMessage();
			e.printStackTrace();
			return ERROR;
		}

		return STREAM_SUCCESS;
	}
	
	/**事件大类统计列表*/
	@Action(value = "findStatisticAnalysisToIssueTypeDomain", results = {
			@Result(type = "json", name = "success", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findStatisticAnalysisToIssueTypeDomain() {
		statisticAnalysisList = statisticAnalysisService.findStatisticAnalysisToIssueTypeDomain(statisticAnalysisVo);
		gridPage = new GridPage<StatisticAnalysisVo>(statisticAnalysisList);
		return SUCCESS;
	}
	
	/**事件小类统计列表*/
	@Action(value = "findStatisticAnalysisToIssueType", results = {
			@Result(type = "json", name = "success", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findStatisticAnalysisToIssueType() {
		statisticAnalysisList = statisticAnalysisService.findStatisticAnalysisToIssueType(statisticAnalysisVo);
		gridPage = new GridPage<StatisticAnalysisVo>(statisticAnalysisList);
		return SUCCESS;
	}
	
	@Action(value = "findStatisticAnalysisToIssueTypePage", results = {
			@Result(name = "success", location = "/template/statisticAnalysis/childIssueTypeStatisticAnalysisList.ftl"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findStatisticAnalysisToIssueTypePage() {
		return SUCCESS;
	}
	
	@PermissionFilter(ename = "exportWeChatIssueTypeStatistic")
	@Actions( { @Action(value = "downIssueTypeStatisticAnalysis", results = {
			@Result(name = "success", type = "stream", params = { "contentType",
					"application/vnd.ms-excel;charset=ISO8859-1", "inputName", "inputStream",
					"contentDisposition", "attachment;filename=${downloadFileName}" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) }) })
	public String downIssueTypeStatisticAnalysis() {
		if (statisticAnalysisVo == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} 
		try {
			//statisticAnalysisList = statisticAnalysisService.findStatisticAnalysisToIssueTypeDomain(statisticAnalysisVo);
			inputStream = exportDownIssueTypeStatisticAnalysis(statisticAnalysisVo);
			downloadFileName = new String("微信事件类型统计分析报表".getBytes("gbk"), "ISO8859-1") + ".xls";
		} catch (Exception e) {
			logger.error("附件下载错误：", e);
			errorMessage = "操作失败，请联系管理员";
			return ERROR;
		}
		return SUCCESS;
	}
	
	private InputStream exportDownIssueTypeStatisticAnalysis(StatisticAnalysisVo statisticAnalysisVo) throws  Exception {
		ExcelWriter writer = constructExcelWriter();
		String displayTitle = "微信事件类型统计分析报表";
		writer.addWorkSheet("微信事件类型统计报表");
		appendIssueTypeTableHead(writer, displayTitle);
		statisticAnalysisList = statisticAnalysisService.findStatisticAnalysisToIssueTypeDomain(statisticAnalysisVo);
		int index = 4;
		StatisticAnalysisVo newStatisticAnalysisVo = new StatisticAnalysisVo();
		for (int i = 0; i < statisticAnalysisList.size(); i++) {
			newStatisticAnalysisVo = statisticAnalysisList.get(i);
			appendIssueTypeDomainRow(writer, index, newStatisticAnalysisVo);
			statisticAnalysisVo.setIssueTypeDomainName(newStatisticAnalysisVo.getStatisticAnalysisDetailVo().getIssueTypeDomainName());
			List<StatisticAnalysisVo> issueTypeStatisticAnalysisList = statisticAnalysisService.findStatisticAnalysisToIssueType(statisticAnalysisVo);
			for(int j = 0; j < issueTypeStatisticAnalysisList.size(); j++) {
				newStatisticAnalysisVo = issueTypeStatisticAnalysisList.get(j);
				appendIssueTypeRow(writer, index+j+1, newStatisticAnalysisVo);
			}
			index = index+issueTypeStatisticAnalysisList.size()+1;
		}
		return new java.io.FileInputStream(writer.getExcelFile(UUID.randomUUID() + ".xls"));
	}
	
	private InputStream exportDownStatisticAnalysis(List<StatisticAnalysisVo> statisticAnalysisList) throws  Exception {
		ExcelWriter writer = constructExcelWriter();
		String displayTitle = "微信公众账号使用情况统计分析报表";
		writer.addWorkSheet("微信公众账号报表");
		appendTableHead(writer, displayTitle);
		StatisticAnalysisVo statisticAnalysisVo = new StatisticAnalysisVo();
		int index = 4;
		for (int i = 0; i < statisticAnalysisList.size(); i++) {
			statisticAnalysisVo = statisticAnalysisList.get(i);
			appendRow(writer, index, statisticAnalysisVo);
			index = index+statisticAnalysisVo.getStatisticList().size();
		}
		return new java.io.FileInputStream(writer.getExcelFile(UUID.randomUUID() + ".xls"));
	}
	
	private void appendRow(ExcelWriter writer, int index, StatisticAnalysisVo statisticAnalysisVo) {
		writer.addCell(index, 0, statisticAnalysisVo.getWeChatUserName()).mergeTo(statisticAnalysisVo.getStatisticList().size()+index-1, 0)
			.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		for (int i = 0; i < statisticAnalysisVo.getStatisticList().size(); i++) {
			writer.addCell(index+i, 1, statisticAnalysisVo.getStatisticList().get(i).getGroupName())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
			writer.addCell(index+i, 2, statisticAnalysisVo.getStatisticList().get(i).getReportNum())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
			writer.addCell(index+i, 3, statisticAnalysisVo.getStatisticList().get(i).getAcceptNum())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
//			writer.addCell(index+i, 4, statisticAnalysisVo.getStatisticList().get(i).getAvailabilityNum())
//				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
			writer.addCell(index+i, 4, statisticAnalysisVo.getStatisticList().get(i).getForwardingNum())
			.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		}
		
	}
	
	private void appendTableHead(ExcelWriter writer, String bigTitle) {
		writer.addCell(0, 0, bigTitle).mergeTo(1, 4).setFont("宋体", 18, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(2, 0, "公众号名称(所属层级)").mergeTo(3, 0).setFont("宋体", 12, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(2, 1, "群组").mergeTo(3, 1).setFont("宋体", 12, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(2, 2, "事件上报数量").mergeTo(3, 2).setFont("宋体", 12, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(2, 3, "受理事件数量").mergeTo(3, 3).setFont("宋体", 12, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
//		writer.addCell(2, 4, "有效事件数").mergeTo(3, 4).setFont("宋体", 12, true, false, false, false)
//				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(2, 4, "转发事件数量").mergeTo(3, 4).setFont("宋体", 12, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
				
	}
	private ExcelWriter constructExcelWriter() {
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		return writer;
	}
	
	private void appendIssueTypeTableHead(ExcelWriter writer, String bigTitle) {
		writer.addCell(0, 0, bigTitle).mergeTo(1, 3).setFont("宋体", 18, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(2, 0, "事件类型").mergeTo(3, 0).setFont("宋体", 15, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(2, 1, "受理事件数").mergeTo(3, 1).setFont("宋体", 15, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(2, 2, "已办结数").mergeTo(3, 2).setFont("宋体", 15, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(2, 3, "未办结数").mergeTo(3, 3).setFont("宋体", 15, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
	}

	private void appendIssueTypeDomainRow(ExcelWriter writer, int index, StatisticAnalysisVo statisticAnalysisVo) {
		writer.addCell(index, 0, statisticAnalysisVo.getStatisticAnalysisDetailVo().getIssueTypeDomainName())
			.setFont("宋体", 12, true, false, false, false)
			.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(index, 1, statisticAnalysisVo.getStatisticAnalysisDetailVo().getAcceptNum())
			.setFont("宋体", 12, true, false, false, false)
			.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(index, 2, statisticAnalysisVo.getStatisticAnalysisDetailVo().getCompletedNum())
			.setFont("宋体", 12, true, false, false, false)
			.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(index, 3, statisticAnalysisVo.getStatisticAnalysisDetailVo().getUnCompletedNum())
			.setFont("宋体", 12, true, false, false, false)
			.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
	}
	
	private void appendIssueTypeRow(ExcelWriter writer, int index, StatisticAnalysisVo statisticAnalysisVo) {
		writer.addCell(index, 0, statisticAnalysisVo.getStatisticAnalysisDetailVo().getIssueTypeName())
			.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(index, 1, statisticAnalysisVo.getStatisticAnalysisDetailVo().getAcceptNum())
			.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(index, 2, statisticAnalysisVo.getStatisticAnalysisDetailVo().getCompletedNum())
			.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(index, 3, statisticAnalysisVo.getStatisticAnalysisDetailVo().getUnCompletedNum())
		.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
	}
	
	public StatisticAnalysisVo getWeChatStatisticVo() {
		return statisticAnalysisVo;
	}

	public void setWeChatStatisticVo(StatisticAnalysisVo statisticAnalysisVo) {
		this.statisticAnalysisVo = statisticAnalysisVo;
	}

	public List<StatisticAnalysisVo> getStatisticAnalysisList() {
		return statisticAnalysisList;
	}

	public void setStatisticAnalysisList(List<StatisticAnalysisVo> statisticAnalysisList) {
		this.statisticAnalysisList = statisticAnalysisList;
	}

	public StatisticAnalysisVo getStatisticAnalysisVo() {
		return statisticAnalysisVo;
	}

	public void setStatisticAnalysisVo(StatisticAnalysisVo statisticAnalysisVo) {
		this.statisticAnalysisVo = statisticAnalysisVo;
	}
	public boolean isPageOnly() {
		return pageOnly;
	}
	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}
	
}

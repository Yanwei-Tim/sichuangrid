package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CalendarUtil;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.report.vo.IssueInvestigationMediateItemVo;
import com.tianque.report.vo.IssueInvestigationMediateVo;
import com.tianque.service.CommonService;
import com.tianque.service.IssueInvestiagtionMediateService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("issueInvestigationMediateController")
public class IssueInvestigationMediateController extends BaseAction {
	@Autowired
	private IssueInvestiagtionMediateService issueInvestiagtionMediateService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private List<Integer> years;

	private int selectedYear;
	private int selectedMonth;
	private Long orgId;

	private List<IssueInvestigationMediateVo> issueInvestigationMediateVos = new ArrayList<IssueInvestigationMediateVo>();

	public int getSelectedYear() {
		return selectedYear;
	}

	public void setSelectedYear(int selectedYear) {
		this.selectedYear = selectedYear;
	}

	public int getSelectedMonth() {
		return selectedMonth;
	}

	public void setSelectedMonth(int selectedMonth) {
		this.selectedMonth = selectedMonth;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	@PermissionFilter(ename = "generatedDataInvestigation")
	public String replaceIssueInvestigationMediate() throws Exception {
		issueInvestiagtionMediateService.rebuildMonthIssueInvestigationMediate(
				getSelectedYear(), getSelectedMonth(), orgId);
		return SUCCESS;
	}

	public String dispatch() throws Exception {
		years = commonService.getUsableYear();
		selectedYear = CalendarUtil.getLastMonthYearValue();
		selectedMonth = CalendarUtil.getLastMonth();
		return SUCCESS;
	}

	/**
	 * 列表显示
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "issueInvestigationMediate")
	public String findIssueInvestigationMediateList() throws Exception {
		issueInvestigationMediateVos = issueInvestiagtionMediateService
				.findIssueInvestigationMediates(selectedYear, selectedMonth,
						orgId);
		return SUCCESS;
	}

	@PermissionFilter(ename = "downIssueInvestigationMediate")
	public String downloadIssueInvestigationMediate() throws Exception {
		if (orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		String title = organizationDubboService.getSimpleOrgById(orgId).getOrgName()
				+ "矛盾纠纷排查调处情况报表(" + selectedYear + "年" + selectedMonth + "月)";
		List<IssueInvestigationMediateVo> records = issueInvestiagtionMediateService
				.findIssueInvestigationMediates(selectedYear, selectedMonth,
						orgId);
		ExcelWriter writer = constructExcelWriter();
		writer.addWorkSheet("矛盾纠纷排查调处情况");
		appendTitle(writer, title);
		appendTableHead(writer);
		int rowIndex = 4;
		for (int index = 0; index < records.size(); index++) {
			IssueInvestigationMediateVo record = records.get(index);
			rowIndex = rowIndex + appendRow(writer, rowIndex, record);
		}
		inputStream = new java.io.FileInputStream(writer.getExcelFile(UUID
				.randomUUID() + ".xls"));
		downloadFileName = new String(title.getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		return SUCCESS;
	}

	private int appendRow(ExcelWriter writer, int startRow,
			IssueInvestigationMediateVo record) {
		int itemCount = record.getItems().size();
		writer.addCell(startRow, 1, record.getTypeDisplayName()).mergeTo(
				startRow + itemCount - 1, 3);
		for (int index = 0; index < itemCount; index++) {
			IssueInvestigationMediateItemVo item = record.getItems().get(index);
			writer.addCell(startRow + index, 0, startRow + index - 4 + 1);
			writer.addCell(startRow + index, 4, item.getName());
			writer.addCell(startRow + index, 5, item.getCity());
			writer.addCell(startRow + index, 6, item.getDistrict());
			writer.addCell(startRow + index, 7, item.getTown());
			writer.addCell(startRow + index, 8, item.getGrid());
			writer.addCell(startRow + index, 9, item.getSubtotal());
			writer.addCell(startRow + index, 10, item.getAnnualTotal());
		}
		return itemCount;
	}

	private ExcelWriter constructExcelWriter() {
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		return writer;
	}

	private void appendTitle(ExcelWriter write, String title) {
		write.addCell(0, 0, title).mergeTo(0, 10)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
	}

	private void appendTableHead(ExcelWriter writer) {
		writer.addCell(1, 0, "填报单位（盖章）：____________________")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 4)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(1, 5, "填报时间：______年___月___日")
				.setFont("宋体", 12, true, false, false, false).mergeTo(1, 10)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);

		writer.addCell(2, 0, "内容").setFont("宋体", 12, true, false, false, false)
				.mergeTo(3, 4)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(2, 5, "层级").setFont("宋体", 12, true, false, false, false)
				.mergeTo(2, 10)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(3, 5, "市").setFont("宋体", 12, true, false, false, false);
		writer.addCell(3, 6, "县市区")
				.setFont("宋体", 12, true, false, false, false);
		writer.addCell(3, 7, "乡镇街道").setFont("宋体", 12, true, false, false,
				false);
		writer.addCell(3, 8, "村居").setFont("宋体", 12, true, false, false, false);
		writer.addCell(3, 9, "小计").setFont("宋体", 12, true, false, false, false);
		writer.addCell(3, 10, "年度累计").setFont("宋体", 12, true, false, false,
				false);

	}

	public List<Integer> getYears() {
		return years;
	}

	public void setYears(List<Integer> years) {
		this.years = years;
	}

	public List<IssueInvestigationMediateVo> getIssueInvestigationMediateVos() {
		return issueInvestigationMediateVos;
	}

	public void setIssueInvestigationMediateVos(
			List<IssueInvestigationMediateVo> issueInvestigationMediateVos) {
		this.issueInvestigationMediateVos = issueInvestigationMediateVos;
	}
}

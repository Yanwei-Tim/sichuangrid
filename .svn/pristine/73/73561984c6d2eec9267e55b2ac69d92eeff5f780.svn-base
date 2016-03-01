package com.tianque.working.controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.User;
import com.tianque.domain.property.DirectoryReportType;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkingRecordSubmitstate;
import com.tianque.domain.workingRecord.DisputEsexamine;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.factory.PlatformMessageFactory;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.DetailedRuleDailyLogRelaService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyYear;
import com.tianque.working.domain.ExpirationDate;
import com.tianque.working.domain.ReportWorkingRecord;
import com.tianque.working.domain.TimeLimitHelper;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyYearService;
import com.tianque.working.service.DisputEsexamineService;
import com.tianque.working.service.ReportWorkingRecordService;

/***
 * 矛盾纠纷排查
 */
@SuppressWarnings("serial")
@Controller("socialConflictReordController")
@Namespace("/daily/socialConflictReordManage")
@Scope("prototype")
@Transactional
public class SocialConflictReordController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(SocialConflictReordController.class);
	@Autowired
	private ReportWorkingRecordService reportWorkingRecordService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private DisputEsexamineService disputEsexamineService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private DailyYearService dailyYearService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private PlatformMessageService platformaMessageService;
	@Autowired
	private DetailedRuleDailyLogRelaService detailedRuleDailyLogRelaService;
	@Autowired
	private PlatformMessageFactory platformMessageFactory;

	private DisputEsexamine disputEsexamine;
	private Organization organization;
	private DailyDirectory dailyDirectory;
	private DailyYear dailyYear;
	private Long reportTypeInternalId;
	private Long reportTime;
	private Long dailyLogId;
	private PlatformMessage platformMessage;
	private boolean bol = false;
	private Long dailyDirectoryId;
	private String reportedType;
	private String modeType;
	private int sysTime;
	private Map dateMap;

	private void prepareForUpdateAndView() {
		disputEsexamine = disputEsexamineService
				.getdDisputEsexamineId(disputEsexamine.getId());
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(disputEsexamine
						.getDailyDirectory().getId());
		dailyYear = dailyYearService.getSimpleDailyYearById(dailyDirectory
				.getDailyYear().getId());
		reportTime = disputEsexamine.getReportTime();
		if (reportTypeInternalId == 0L) {
			reportTypeInternalId = Long.valueOf(dailyDirectory.getIndexId());
		}
		getTime(reportTime);
		PropertyDict propertyDict = propertyDictService
				.getPropertyDictByDomainNameAndDictId(
						PropertyTypes.DIRECTORY_REPORT_TYPE, dailyDirectory
								.getDirectoryReportType().getId());
		reportTypeInternalId = Integer.valueOf(propertyDict.getInternalId())
				.longValue();
	}

	/**
	 * 导出数据
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "downloadSocialConflict", results = {
			@Result(name = "success", type = "stream", params = {
					"contentType",
					"application/vnd.ms-excel;charset=ISO8859-1", "inputName",
					"inputStream", "contentDisposition",
					"attachment;filename=${downloadFileName}" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String downloadSocialConflict() throws Exception {
		disputEsexamine = disputEsexamineService
				.getdDisputEsexamineId(disputEsexamine.getId());
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(disputEsexamine
						.getDailyDirectory().getId());
		dailyYear = dailyYearService.getSimpleDailyYearById(dailyDirectory
				.getDailyYear().getId());
		disputEsexamineService.getRealTimeYearData(
				disputEsexamine.getReportTime(), disputEsexamine,
				dailyDirectory);
		reportTime = disputEsexamine.getReportTime();
		inputStream = exportSocialConflict(disputEsexamine);
		downloadFileName = new String("矛盾纠纷排查情况表".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		return SUCCESS;
	}

	private InputStream exportSocialConflict(DisputEsexamine disputEsexamine)
			throws Exception {
		ExcelWriter writer = constructExcelWriter();
		writer.addWorkSheet("矛盾纠纷排查情况表");
		organization = organizationDubboService.getFullOrgById(disputEsexamine
				.getOrganization().getId());
		if (organization.getOrgLevel().getInternalId() == OrganizationLevel.VILLAGE) {
			appendTableColVillage(writer, disputEsexamine);// 制表格 村
		} else if (organization.getOrgLevel().getInternalId() == OrganizationLevel.TOWN) {
			appendTableColTown(writer, disputEsexamine);// 制表格 镇
		} else if (organization.getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT) {
			appendTableColDistrict(writer, disputEsexamine);// 制表格 县
		} else if (organization.getOrgLevel().getInternalId() == OrganizationLevel.CITY
				|| organization.getOrgLevel().getInternalId() == OrganizationLevel.PROVINCE) {
			appendTableColCity(writer, disputEsexamine);// 制表格 市
		}
		return new java.io.FileInputStream(writer.getExcelFile(UUID
				.randomUUID() + ".xls"));
	}

	private void appendTableColVillage(ExcelWriter writer,
			DisputEsexamine disputEsexamine) {
		appendTableCol(writer, disputEsexamine, 4);

	}

	private void appendTableColTown(ExcelWriter writer,
			DisputEsexamine disputEsexamine) {
		// 第4列开始
		writer.addCell(3, 4, "乡镇街道").setFont("宋体", 16, true, false, false,
				false);
		writer.addCell(4, 4, disputEsexamine.getTotalTwonIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(5, 4, disputEsexamine.getTotalTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(6, 4, disputEsexamine.getTotalTwonDealrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, 4, disputEsexamine.getTotalTwonFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 4, disputEsexamine.getTotalTwonFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, 4, disputEsexamine.getReligionTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 4, disputEsexamine.getReligionTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, 4, disputEsexamine.getSoldierTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 4, disputEsexamine.getSoldierTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(13, 4, disputEsexamine.getRemoveTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(14, 4, disputEsexamine.getRemoveTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 4, disputEsexamine.getAssetsTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, 4, disputEsexamine.getAssetsTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(17, 4, disputEsexamine.getLandBoundariesTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(18, 4, disputEsexamine.getLandBoundariesTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(19, 4, disputEsexamine.getEconomyTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(20, 4, disputEsexamine.getEconomyTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(21, 4, disputEsexamine.getLabourTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(22, 4, disputEsexamine.getLabourTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(23, 4, disputEsexamine.getLtdTwonIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(24, 4, disputEsexamine.getLtdTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(25, 4, disputEsexamine.getEnvironTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(26, 4, disputEsexamine.getEnvironTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(27, 4, disputEsexamine.getJudicialTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(28, 4, disputEsexamine.getJudicialTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(29, 4, disputEsexamine.getAdminTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(30, 4, disputEsexamine.getAdminTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(31, 4, disputEsexamine.getSchoolTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(32, 4, disputEsexamine.getSchoolTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(33, 4, disputEsexamine.getSeaTwonIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(34, 4, disputEsexamine.getSeaTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(35, 4, disputEsexamine.getCadreTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(36, 4, disputEsexamine.getCadreTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(37, 4, disputEsexamine.getVilthTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(38, 4, disputEsexamine.getVilthTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(39, 4, disputEsexamine.getJobTwonIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(40, 4, disputEsexamine.getJobTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(41, 4, disputEsexamine.getFamilyTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(42, 4, disputEsexamine.getFamilyTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(43, 4, disputEsexamine.getPatientTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(44, 4, disputEsexamine.getPatientTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(45, 4, disputEsexamine.getOtherTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(46, 4, disputEsexamine.getOtherTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(47, 4, disputEsexamine.getIssuelowTwonStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(48, 4, disputEsexamine.getIssuemidTwonStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(49, 4, disputEsexamine.getIssuehighTwonStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(50, 4, disputEsexamine.getIssuehigherTwonStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(51, 4, disputEsexamine.getImptTwonImptIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(52, 4, disputEsexamine.getImptTwonImptDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(53, 4, disputEsexamine.getImptTwonImptFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(54, 4, disputEsexamine.getImptTwonImptFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(55, 4, disputEsexamine.getConcentrateTwonFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		// 第4列结束
		appendTableCol(writer, disputEsexamine, 5);
	}

	private void appendTableColDistrict(ExcelWriter writer,
			DisputEsexamine disputEsexamine) {
		// 第4列开始
		writer.addCell(3, 4, "县市区")
				.setFont("宋体", 16, true, false, false, false);
		writer.addCell(4, 4, disputEsexamine.getTotalCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, 4, disputEsexamine.getTotalCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 4, disputEsexamine.getTotalCountyDealrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, 4, disputEsexamine.getTotalCountyFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 4, disputEsexamine.getTotalCountyFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, 4, disputEsexamine.getReligionCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 4, disputEsexamine.getReligionCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, 4, disputEsexamine.getSoldierCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 4, disputEsexamine.getSoldierCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(13, 4, disputEsexamine.getRemoveCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(14, 4, disputEsexamine.getRemoveCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 4, disputEsexamine.getAssetsCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, 4, disputEsexamine.getAssetsCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(17, 4,
				disputEsexamine.getLandBoundariesCountyIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(18, 4,
				disputEsexamine.getLandBoundariesCountyDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(19, 4, disputEsexamine.getEconomyCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(20, 4, disputEsexamine.getEconomyCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(21, 4, disputEsexamine.getLabourCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(22, 4, disputEsexamine.getLabourCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(23, 4, disputEsexamine.getLtdCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(24, 4, disputEsexamine.getLtdCountyDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(25, 4, disputEsexamine.getEnvironCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(26, 4, disputEsexamine.getEnvironCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(27, 4, disputEsexamine.getJudicialCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(28, 4, disputEsexamine.getJudicialCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(29, 4, disputEsexamine.getAdminCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(30, 4, disputEsexamine.getAdminCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(31, 4, disputEsexamine.getSchoolCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(32, 4, disputEsexamine.getSchoolCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(33, 4, disputEsexamine.getSeaCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(34, 4, disputEsexamine.getSeaCountyDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(35, 4, disputEsexamine.getCadreCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(36, 4, disputEsexamine.getCadreCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(37, 4, disputEsexamine.getVilthCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(38, 4, disputEsexamine.getVilthCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(39, 4, disputEsexamine.getJobCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(40, 4, disputEsexamine.getJobCountyDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(41, 4, disputEsexamine.getFamilyCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(42, 4, disputEsexamine.getFamilyCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(43, 4, disputEsexamine.getPatientCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(44, 4, disputEsexamine.getPatientCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(45, 4, disputEsexamine.getOtherCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(46, 4, disputEsexamine.getOtherCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(47, 4, disputEsexamine.getIssuelowCountyStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(48, 4, disputEsexamine.getIssuemidCountyStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(49, 4, disputEsexamine.getIssuehighCountyStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(50, 4, disputEsexamine.getIssuehigherCountyStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(51, 4, disputEsexamine.getImptCountyImptIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(52, 4, disputEsexamine.getImptCountyImptDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(53, 4, disputEsexamine.getImptCountyImptFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(54, 4, disputEsexamine.getImptCountyImptFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(55, 4, disputEsexamine.getConcentrateCountyFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		// 第4列结束
		// 第5列开始
		writer.addCell(3, 5, "乡镇街道").setFont("宋体", 16, true, false, false,
				false);
		writer.addCell(4, 5, disputEsexamine.getTotalTwonIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(5, 5, disputEsexamine.getTotalTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(6, 5, disputEsexamine.getTotalTwonDealrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, 5, disputEsexamine.getTotalTwonFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 5, disputEsexamine.getTotalTwonFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, 5, disputEsexamine.getReligionTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 5, disputEsexamine.getReligionTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, 5, disputEsexamine.getSoldierTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 5, disputEsexamine.getSoldierTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(13, 5, disputEsexamine.getRemoveTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(14, 5, disputEsexamine.getRemoveTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 5, disputEsexamine.getAssetsTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, 5, disputEsexamine.getAssetsTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(17, 5, disputEsexamine.getLandBoundariesTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(18, 5, disputEsexamine.getLandBoundariesTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(19, 5, disputEsexamine.getEconomyTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(20, 5, disputEsexamine.getEconomyTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(21, 5, disputEsexamine.getLabourTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(22, 5, disputEsexamine.getLabourTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(23, 5, disputEsexamine.getLtdTwonIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(24, 5, disputEsexamine.getLtdTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(25, 5, disputEsexamine.getEnvironTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(26, 5, disputEsexamine.getEnvironTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(27, 5, disputEsexamine.getJudicialTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(28, 5, disputEsexamine.getJudicialTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(29, 5, disputEsexamine.getAdminTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(30, 5, disputEsexamine.getAdminTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(31, 5, disputEsexamine.getSchoolTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(32, 5, disputEsexamine.getSchoolTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(33, 5, disputEsexamine.getSeaTwonIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(34, 5, disputEsexamine.getSeaTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(35, 5, disputEsexamine.getCadreTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(36, 5, disputEsexamine.getCadreTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(37, 5, disputEsexamine.getVilthTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(38, 5, disputEsexamine.getVilthTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(39, 5, disputEsexamine.getJobTwonIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(40, 5, disputEsexamine.getJobTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(41, 5, disputEsexamine.getFamilyTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(42, 5, disputEsexamine.getFamilyTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(43, 5, disputEsexamine.getPatientTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(44, 5, disputEsexamine.getPatientTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(45, 5, disputEsexamine.getOtherTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(46, 5, disputEsexamine.getOtherTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(47, 5, disputEsexamine.getIssuelowTwonStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(48, 5, disputEsexamine.getIssuemidTwonStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(49, 5, disputEsexamine.getIssuehighTwonStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(50, 5, disputEsexamine.getIssuehigherTwonStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(51, 5, disputEsexamine.getImptTwonImptIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(52, 5, disputEsexamine.getImptTwonImptDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(53, 5, disputEsexamine.getImptTwonImptFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(54, 5, disputEsexamine.getImptTwonImptFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(55, 5, disputEsexamine.getConcentrateTwonFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		// 第5列结束
		appendTableCol(writer, disputEsexamine, 6);

	}

	private void appendTableColCity(ExcelWriter writer,
			DisputEsexamine disputEsexamine) {
		// 第4列开始
		writer.addCell(3, 4, "市").setFont("宋体", 16, true, false, false, false);
		writer.addCell(4, 4, disputEsexamine.getTotalCityIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(5, 4, disputEsexamine.getTotalCityDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(6, 4, disputEsexamine.getTotalCityDealrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, 4, disputEsexamine.getTotalCityFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 4, disputEsexamine.getTotalCityFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, 4, disputEsexamine.getReligionCityIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 4, disputEsexamine.getReligionCityDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, 4, disputEsexamine.getSoldierCityIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 4, disputEsexamine.getSoldierCityDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(13, 4, disputEsexamine.getRemoveCityIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(14, 4, disputEsexamine.getRemoveCityDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 4, disputEsexamine.getAssetsCityIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, 4, disputEsexamine.getAssetsCityDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(17, 4, disputEsexamine.getLandBoundariesCityIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(18, 4, disputEsexamine.getLandBoundariesCityDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(19, 4, disputEsexamine.getEconomyCityIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(20, 4, disputEsexamine.getEconomyCityDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(21, 4, disputEsexamine.getLabourCityIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(22, 4, disputEsexamine.getLabourCityDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(23, 4, disputEsexamine.getLtdCityIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(24, 4, disputEsexamine.getLtdCityDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(25, 4, disputEsexamine.getEnvironCityIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(26, 4, disputEsexamine.getEnvironCityDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(27, 4, disputEsexamine.getJudicialCityIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(28, 4, disputEsexamine.getJudicialCityDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(29, 4, disputEsexamine.getAdminCityIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(30, 4, disputEsexamine.getAdminCityDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(31, 4, disputEsexamine.getSchoolCityIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(32, 4, disputEsexamine.getSchoolCityDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(33, 4, disputEsexamine.getSeaCityIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(34, 4, disputEsexamine.getSeaCityDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(35, 4, disputEsexamine.getCadreCityIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(36, 4, disputEsexamine.getCadreCityDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(37, 4, disputEsexamine.getVilthCityIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(38, 4, disputEsexamine.getVilthCityDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(39, 4, disputEsexamine.getJobCityIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(40, 4, disputEsexamine.getJobCityDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(41, 4, disputEsexamine.getFamilyCityIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(42, 4, disputEsexamine.getFamilyCityDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(43, 4, disputEsexamine.getPatientCityIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(44, 4, disputEsexamine.getPatientCityDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(45, 4, disputEsexamine.getOtherCityIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(46, 4, disputEsexamine.getOtherCityDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(47, 4, disputEsexamine.getIssuelowCityStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(48, 4, disputEsexamine.getIssuemidCityStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(49, 4, disputEsexamine.getIssuehighCityStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(50, 4, disputEsexamine.getIssuehigherCityStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(51, 4, disputEsexamine.getImptCityImptIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(52, 4, disputEsexamine.getImptCityImptDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(53, 4, disputEsexamine.getImptCityImptFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(54, 4, disputEsexamine.getImptCityImptFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(55, 4, disputEsexamine.getConcentrateCityFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		// 第4列结束
		// 第5列开始
		writer.addCell(3, 5, "县市区")
				.setFont("宋体", 16, true, false, false, false);
		writer.addCell(4, 5, disputEsexamine.getTotalCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, 5, disputEsexamine.getTotalCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 5, disputEsexamine.getTotalCountyDealrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, 5, disputEsexamine.getTotalCountyFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 5, disputEsexamine.getTotalCountyFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, 5, disputEsexamine.getReligionCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 5, disputEsexamine.getReligionCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, 5, disputEsexamine.getSoldierCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 5, disputEsexamine.getSoldierCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(13, 5, disputEsexamine.getRemoveCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(14, 5, disputEsexamine.getRemoveCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 5, disputEsexamine.getAssetsCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, 5, disputEsexamine.getAssetsCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(17, 5,
				disputEsexamine.getLandBoundariesCountyIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(18, 5,
				disputEsexamine.getLandBoundariesCountyDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(19, 5, disputEsexamine.getEconomyCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(20, 5, disputEsexamine.getEconomyCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(21, 5, disputEsexamine.getLabourCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(22, 5, disputEsexamine.getLabourCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(23, 5, disputEsexamine.getLtdCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(24, 5, disputEsexamine.getLtdCountyDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(25, 5, disputEsexamine.getEnvironCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(26, 5, disputEsexamine.getEnvironCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(27, 5, disputEsexamine.getJudicialCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(28, 5, disputEsexamine.getJudicialCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(29, 5, disputEsexamine.getAdminCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(30, 5, disputEsexamine.getAdminCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(31, 5, disputEsexamine.getSchoolCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(32, 5, disputEsexamine.getSchoolCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(33, 5, disputEsexamine.getSeaCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(34, 5, disputEsexamine.getSeaCountyDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(35, 5, disputEsexamine.getCadreCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(36, 5, disputEsexamine.getCadreCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(37, 5, disputEsexamine.getVilthCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(38, 5, disputEsexamine.getVilthCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(39, 5, disputEsexamine.getJobCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(40, 5, disputEsexamine.getJobCountyDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(41, 5, disputEsexamine.getFamilyCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(42, 5, disputEsexamine.getFamilyCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(43, 5, disputEsexamine.getPatientCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(44, 5, disputEsexamine.getPatientCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(45, 5, disputEsexamine.getOtherCountyIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(46, 5, disputEsexamine.getOtherCountyDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(47, 5, disputEsexamine.getIssuelowCountyStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(48, 5, disputEsexamine.getIssuemidCountyStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(49, 5, disputEsexamine.getIssuehighCountyStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(50, 5, disputEsexamine.getIssuehigherCountyStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(51, 5, disputEsexamine.getImptCountyImptIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(52, 5, disputEsexamine.getImptCountyImptDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(53, 5, disputEsexamine.getImptCountyImptFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(54, 5, disputEsexamine.getImptCountyImptFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(55, 5, disputEsexamine.getConcentrateCountyFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		// 第5列结束
		// 第6列开始
		writer.addCell(3, 6, "乡镇街道").setFont("宋体", 16, true, false, false,
				false);
		writer.addCell(4, 6, disputEsexamine.getTotalTwonIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(5, 6, disputEsexamine.getTotalTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(6, 6, disputEsexamine.getTotalTwonDealrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, 6, disputEsexamine.getTotalTwonFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 6, disputEsexamine.getTotalTwonFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, 6, disputEsexamine.getReligionTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 6, disputEsexamine.getReligionTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, 6, disputEsexamine.getSoldierTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 6, disputEsexamine.getSoldierTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(13, 6, disputEsexamine.getRemoveTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(14, 6, disputEsexamine.getRemoveTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 6, disputEsexamine.getAssetsTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, 6, disputEsexamine.getAssetsTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(17, 6, disputEsexamine.getLandBoundariesTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(18, 6, disputEsexamine.getLandBoundariesTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(19, 6, disputEsexamine.getEconomyTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(20, 6, disputEsexamine.getEconomyTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(21, 6, disputEsexamine.getLabourTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(22, 6, disputEsexamine.getLabourTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(23, 6, disputEsexamine.getLtdTwonIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(24, 6, disputEsexamine.getLtdTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(25, 6, disputEsexamine.getEnvironTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(26, 6, disputEsexamine.getEnvironTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(27, 6, disputEsexamine.getJudicialTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(28, 6, disputEsexamine.getJudicialTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(29, 6, disputEsexamine.getAdminTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(30, 6, disputEsexamine.getAdminTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(31, 6, disputEsexamine.getSchoolTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(32, 6, disputEsexamine.getSchoolTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(33, 6, disputEsexamine.getSeaTwonIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(34, 6, disputEsexamine.getSeaTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(35, 6, disputEsexamine.getCadreTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(36, 6, disputEsexamine.getCadreTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(37, 6, disputEsexamine.getVilthTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(38, 6, disputEsexamine.getVilthTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(39, 6, disputEsexamine.getJobTwonIssuecount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(40, 6, disputEsexamine.getJobTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(41, 6, disputEsexamine.getFamilyTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(42, 6, disputEsexamine.getFamilyTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(43, 6, disputEsexamine.getPatientTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(44, 6, disputEsexamine.getPatientTwonDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(45, 6, disputEsexamine.getOtherTwonIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(46, 6, disputEsexamine.getOtherTwonDealcount()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(47, 6, disputEsexamine.getIssuelowTwonStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(48, 6, disputEsexamine.getIssuemidTwonStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(49, 6, disputEsexamine.getIssuehighTwonStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(50, 6, disputEsexamine.getIssuehigherTwonStagescount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(51, 6, disputEsexamine.getImptTwonImptIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(52, 6, disputEsexamine.getImptTwonImptDealcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(53, 6, disputEsexamine.getImptTwonImptFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(54, 6, disputEsexamine.getImptTwonImptFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(55, 6, disputEsexamine.getConcentrateTwonFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		// 第6列结束
		appendTableCol(writer, disputEsexamine, 7);
	}

	private void appendTableCol(ExcelWriter writer,
			DisputEsexamine disputEsexamine, int x) {
		// 零行
		writer.addCell(0, 0, disputEsexamine.getName()).mergeTo(0, x + 2)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		PropertyDict dict = propertyDictService
				.getPropertyDictByDomainNameAndDictId(
						PropertyTypes.DIRECTORY_REPORT_TYPE, dailyDirectory
								.getDirectoryReportType().getId());
		if (dict.getInternalId() == DirectoryReportType.MONTH_REPORT) {
			writer.addCell(
					1,
					0,
					"填报单位：" + organization.getOrgName()
							+ "                        填报月份："
							+ disputEsexamine.getReportTime() + "月份")
					.mergeTo(1, x + 2)
					.setFont("宋体", 16, true, false, false, false)
					.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		} else if (dict.getInternalId() == DirectoryReportType.QUARTER_REPORT) {
			writer.addCell(
					1,
					0,
					"填报单位：" + organization.getOrgName()
							+ "                        填报季度：第"
							+ disputEsexamine.getReportTime() + "季度")
					.mergeTo(1, x + 2)
					.setFont("宋体", 16, true, false, false, false)
					.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		} else if (dict.getInternalId() == DirectoryReportType.SEMIYEARLY_REPORT
				&& disputEsexamine.getReportTime() == 1) {
			writer.addCell(
					1,
					0,
					"填报单位：" + organization.getOrgName()
							+ "                        填报半年度：上半年度")
					.mergeTo(1, x + 2)
					.setFont("宋体", 16, true, false, false, false)
					.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		} else if (dict.getInternalId() == DirectoryReportType.SEMIYEARLY_REPORT
				&& disputEsexamine.getReportTime() == 2) {
			writer.addCell(
					1,
					0,
					"填报单位：" + organization.getOrgName()
							+ "                        填报半年度：下半年度")
					.mergeTo(1, x + 2)
					.setFont("宋体", 16, true, false, false, false)
					.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		} else if (dict.getInternalId() == DirectoryReportType.YEAR_REPORT) {
			writer.addCell(
					1,
					0,
					"填报单位：" + organization.getOrgName()
							+ "                        填报年度："
							+ disputEsexamine.getReportTime() + "年度")
					.mergeTo(1, x + 2)
					.setFont("宋体", 16, true, false, false, false)
					.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		}
		// 一二行开始
		writer.addCell(2, 0, "内容").mergeTo(3, 3)
				.setFont("宋体", 16, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER)
				.setVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		writer.addCell(2, 4, "层       级").mergeTo(2, x + 2)
				.setFont("宋体", 16, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(3, x, "村居    ").setFont("宋体", 16, true, false, false,
				false);
		writer.addCell(3, x + 1, "小计   ").setFont("宋体", 16, true, false, false,
				false);
		writer.addCell(3, x + 2, "年度累计").setFont("宋体", 16, true, false, false,
				false);
		// 一二行结束
		// 第0列开始
		writer.addCell(4, 0, "1").setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, 0, "2").setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 0, "3").setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, 0, "4").setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 0, "5").setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, 0, "6").setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 0, "7").setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, 0, "8").setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 0, "9").setFont("宋体", 10, true, false, false, false);
		writer.addCell(13, 0, "10")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(14, 0, "11")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 0, "12")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, 0, "13")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(17, 0, "14")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(18, 0, "15")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(19, 0, "16")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(20, 0, "17")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(21, 0, "18")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(22, 0, "19")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(23, 0, "20")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(24, 0, "21")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(25, 0, "22")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(26, 0, "23")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(27, 0, "24")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(28, 0, "25")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(29, 0, "26")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(30, 0, "27")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(31, 0, "28")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(32, 0, "29")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(33, 0, "30")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(34, 0, "31")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(35, 0, "32")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(36, 0, "33")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(37, 0, "34")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(38, 0, "35")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(39, 0, "36")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(40, 0, "37")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(41, 0, "38")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(42, 0, "39")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(43, 0, "40")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(44, 0, "41")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(45, 0, "42")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(46, 0, "43")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(47, 0, "44")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(48, 0, "45")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(49, 0, "46")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(50, 0, "47")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(51, 0, "48")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(52, 0, "49")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(53, 0, "50")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(54, 0, "51")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(55, 0, "52")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(56, 0, "备注")
				.setFont("宋体", 10, true, false, false, false);
		// 第0列结束
		// 第1-2列开始
		writer.addCell(4, 1, "总     况").mergeTo(8, 2)
				.setFont("宋体", 10, false, false, false, false);
		writer.addCell(9, 1, "民族宗教矛盾纠纷").mergeTo(10, 2)
				.setFont("宋体", 10, false, false, false, false);
		writer.addCell(11, 1, "军转干部、复原退伍军人安置引发的矛盾纠纷").mergeTo(12, 2)
				.setFont("宋体", 10, false, false, false, false).setBorder(false);
		writer.addCell(13, 1, "征地拆迁安置引发的矛盾纠纷").mergeTo(14, 2)
				.setFont("宋体", 10, false, false, false, false);
		writer.addCell(15, 1, "建筑工程质量、物业管理等问题引发的矛盾纠纷").mergeTo(16, 2)
				.setFont("宋体", 10, false, false, false, false);
		writer.addCell(17, 1, "土地矿产山林水利界限权属纠纷").mergeTo(18, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(19, 1, "经济活动引发的矛盾纠纷").mergeTo(20, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(21, 1, "劳资纠纷").mergeTo(22, 2)
				.setFont("宋体", 10, true, false, false, false).setBorder(false);
		writer.addCell(23, 1, "企业改制引发的矛盾纠纷").mergeTo(24, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(25, 1, "环境污染、生态破坏引发的矛盾纠纷").mergeTo(26, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(27, 1, "司法活动引发的矛盾纠纷").mergeTo(28, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(29, 1, "行政执法活动引发的矛盾纠纷").mergeTo(30, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(31, 1, "大中专院校、中小学校引发的矛盾纠纷").mergeTo(32, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(33, 1, "海事渔事纠纷").mergeTo(34, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(35, 1, "干部作风等问题引发的矛盾纠纷").mergeTo(36, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(37, 1, "村务管理引发的矛盾纠纷").mergeTo(38, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(39, 1, "农村土地承包、农村集体“三资”及农民负担等问题引发的矛盾纠纷").mergeTo(40, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(41, 1, "婚姻家庭邻里等各类民间纠纷").mergeTo(42, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(43, 1, "医患纠纷").mergeTo(44, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(45, 1, "其他").mergeTo(46, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(47, 1, "涉及矛盾纠纷人数").mergeTo(50, 1)
				.setFont("宋体", 10, true, false, false, false);
		// 第2列
		writer.addCell(47, 2, "50人以下").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(48, 2, "50至100人").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(49, 2, "100人至500人").setFont("宋体", 10, true, false,
				false, false);
		writer.addCell(50, 2, "500人以上").setFont("宋体", 10, true, false, false,
				false);
		// 第2列结束
		writer.addCell(51, 1, "重大矛盾纠纷").mergeTo(54, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(55, 1, "开展集中排查").mergeTo(55, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(56, 1, "各市于每季度第一个月的10日前将此报表上报省综治办；县（市、区）于次月5日前上报市综治办。")
				.mergeTo(56, x + 2)
				.setFont("宋体", 10, true, false, false, false);// 最后一列
		// 第1-2列结束

		// 第3列开始
		writer.addCell(4, 3, "排查数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, 3, "调处数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 3, "调处率")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, 3, "成功数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 3, "成功率")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, 3, "排查数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(11, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(12, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(13, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(14, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(15, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(16, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(17, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(18, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(19, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(20, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(21, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(22, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(23, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(24, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(25, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(26, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(27, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(28, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(29, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(30, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(31, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(32, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(33, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(34, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(35, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(36, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(37, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(38, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(39, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(40, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(41, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(42, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(43, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(44, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(45, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(46, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(47, 3, "起数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(48, 3, "起数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(49, 3, "起数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(50, 3, "起数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(51, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(52, 3, "调处数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(53, 3, "成功数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(54, 3, "成功率").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(55, 3, "次数")
				.setFont("宋体", 10, true, false, false, false);
		// 第3列结束
		// 第4列开始
		writer.addCell(4, x, disputEsexamine.getTotalVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, x, disputEsexamine.getTotalVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, x, disputEsexamine.getTotalVilDealrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, x, disputEsexamine.getTotalVilFinishcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, x, disputEsexamine.getTotalVilFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, x, disputEsexamine.getReligionVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, x, disputEsexamine.getReligionVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, x, disputEsexamine.getSoldierVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, x, disputEsexamine.getSoldierVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(13, x, disputEsexamine.getRemoveVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(14, x, disputEsexamine.getRemoveVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, x, disputEsexamine.getAssetsVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, x, disputEsexamine.getAssetsVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(17, x, disputEsexamine.getLandBoundariesVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(18, x, disputEsexamine.getLandBoundariesVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(19, x, disputEsexamine.getEconomyVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(20, x, disputEsexamine.getEconomyVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(21, x, disputEsexamine.getLabourVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(22, x, disputEsexamine.getLabourVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(23, x, disputEsexamine.getLtdVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(24, x, disputEsexamine.getLtdVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(25, x, disputEsexamine.getEnvironVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(26, x, disputEsexamine.getEnvironVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(27, x, disputEsexamine.getJudicialVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(28, x, disputEsexamine.getJudicialVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(29, x, disputEsexamine.getAdminVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(30, x, disputEsexamine.getAdminVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(31, x, disputEsexamine.getSchoolVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(32, x, disputEsexamine.getSchoolVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(33, x, disputEsexamine.getSeaVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(34, x, disputEsexamine.getSeaVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(35, x, disputEsexamine.getCadreVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(36, x, disputEsexamine.getCadreVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(37, x, disputEsexamine.getVilthVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(38, x, disputEsexamine.getVilthVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(39, x, disputEsexamine.getJobVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(40, x, disputEsexamine.getJobVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(41, x, disputEsexamine.getFamilyVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(42, x, disputEsexamine.getFamilyVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(43, x, disputEsexamine.getPatientVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(44, x, disputEsexamine.getPatientVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(45, x, disputEsexamine.getOtherVilIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(46, x, disputEsexamine.getOtherVilDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(47, x, disputEsexamine.getIssuelowVilStagescount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(48, x, disputEsexamine.getIssuemidVilStagescount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(49, x, disputEsexamine.getIssuehighVilStagescount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(50, x, disputEsexamine.getIssuehigherVilStagescount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(51, x, disputEsexamine.getImptVilImptIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(52, x, disputEsexamine.getImptVilImptDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(53, x, disputEsexamine.getImptVilImptFinishcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(54, x, disputEsexamine.getImptVilImptFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(55, x, disputEsexamine.getConcentrateVilFinishcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		// 第4列结束
		// 第5列开始
		writer.addCell(4, x + 1, disputEsexamine.getTotalUnIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, x + 1, disputEsexamine.getTotalUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, x + 1, disputEsexamine.getTotalUnDealrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, x + 1, disputEsexamine.getTotalUnFinishcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, x + 1, disputEsexamine.getTotalUnFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, x + 1, disputEsexamine.getReligionUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, x + 1, disputEsexamine.getReligionUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, x + 1, disputEsexamine.getSoldierUnIssuecount())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, x + 1, disputEsexamine.getSoldierUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(13, x + 1, disputEsexamine.getRemoveUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(14, x + 1, disputEsexamine.getRemoveUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, x + 1, disputEsexamine.getAssetsUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, x + 1, disputEsexamine.getAssetsUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(17, x + 1,
				disputEsexamine.getLandBoundariesUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(18, x + 1,
				disputEsexamine.getLandBoundariesUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(19, x + 1, disputEsexamine.getEconomyUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(20, x + 1, disputEsexamine.getEconomyUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(21, x + 1, disputEsexamine.getLabourUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(22, x + 1, disputEsexamine.getLabourUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(23, x + 1, disputEsexamine.getLtdUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(24, x + 1, disputEsexamine.getLtdUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(25, x + 1, disputEsexamine.getEnvironUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(26, x + 1, disputEsexamine.getEnvironUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(27, x + 1, disputEsexamine.getJudicialUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(28, x + 1, disputEsexamine.getJudicialUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(29, x + 1, disputEsexamine.getAdminUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(30, x + 1, disputEsexamine.getAdminUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(31, x + 1, disputEsexamine.getSchoolUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(32, x + 1, disputEsexamine.getSchoolUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(33, x + 1, disputEsexamine.getSeaUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(34, x + 1, disputEsexamine.getSeaUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(35, x + 1, disputEsexamine.getCadreUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(36, x + 1, disputEsexamine.getCadreUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(37, x + 1, disputEsexamine.getVilthUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(38, x + 1, disputEsexamine.getVilthUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(39, x + 1, disputEsexamine.getJobUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(40, x + 1, disputEsexamine.getJobUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(41, x + 1, disputEsexamine.getFamilyUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(42, x + 1, disputEsexamine.getFamilyUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(43, x + 1, disputEsexamine.getPatientUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(44, x + 1, disputEsexamine.getPatientUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(45, x + 1, disputEsexamine.getOtherUnIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(46, x + 1, disputEsexamine.getOtherUnDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(47, x + 1, disputEsexamine.getIssuelowUnStagescount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(48, x + 1, disputEsexamine.getIssuemidUnStagescount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(49, x + 1, disputEsexamine.getIssuehighUnStagescount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(50, x + 1, disputEsexamine.getIssuehigherUnStagescount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(51, x + 1, disputEsexamine.getImptUnImptIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(52, x + 1, disputEsexamine.getImptUnImptDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(53, x + 1, disputEsexamine.getImptUnImptFinishcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(54, x + 1, disputEsexamine.getImptUnImptFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(55, x + 1, disputEsexamine.getConcentrateUnFinishcount())
				.setFont("宋体", 10, true, false, false, false);
		// 第5列结束
		// 第6列开始
		writer.addCell(4, x + 2, disputEsexamine.getTotalYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, x + 2, disputEsexamine.getTotalYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, x + 2, disputEsexamine.getTotalYearDealrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, x + 2, disputEsexamine.getTotalYearFinishcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, x + 2, disputEsexamine.getTotalYearFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, x + 2, disputEsexamine.getReligionYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, x + 2, disputEsexamine.getReligionYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, x + 2, disputEsexamine.getSoldierYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, x + 2, disputEsexamine.getSoldierYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(13, x + 2, disputEsexamine.getRemoveYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(14, x + 2, disputEsexamine.getRemoveYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, x + 2, disputEsexamine.getAssetsYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, x + 2, disputEsexamine.getAssetsYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(17, x + 2,
				disputEsexamine.getLandBoundariesYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(18, x + 2,
				disputEsexamine.getLandBoundariesYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(19, x + 2, disputEsexamine.getEconomyYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(20, x + 2, disputEsexamine.getEconomyYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(21, x + 2, disputEsexamine.getLabourYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(22, x + 2, disputEsexamine.getLabourYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(23, x + 2, disputEsexamine.getLtdYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(24, x + 2, disputEsexamine.getLtdYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(25, x + 2, disputEsexamine.getEnvironYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(26, x + 2, disputEsexamine.getEnvironYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(27, x + 2, disputEsexamine.getJudicialYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(28, x + 2, disputEsexamine.getJudicialYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(29, x + 2, disputEsexamine.getAdminYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(30, x + 2, disputEsexamine.getAdminYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(31, x + 2, disputEsexamine.getSchoolYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(32, x + 2, disputEsexamine.getSchoolYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(33, x + 2, disputEsexamine.getSeaYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(34, x + 2, disputEsexamine.getSeaYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(35, x + 2, disputEsexamine.getCadreYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(36, x + 2, disputEsexamine.getCadreYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(37, x + 2, disputEsexamine.getVilthYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(38, x + 2, disputEsexamine.getVilthYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(39, x + 2, disputEsexamine.getJobYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(40, x + 2, disputEsexamine.getJobYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(41, x + 2, disputEsexamine.getFamilyYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(42, x + 2, disputEsexamine.getFamilyYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(43, x + 2, disputEsexamine.getPatientYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(44, x + 2, disputEsexamine.getPatientYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(45, x + 2, disputEsexamine.getOtherYearIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(46, x + 2, disputEsexamine.getOtherYearDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(47, x + 2, disputEsexamine.getIssuelowYearStagescount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(48, x + 2, disputEsexamine.getIssuemidYearStagescount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(49, x + 2, disputEsexamine.getIssuehighYearStagescount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(50, x + 2,
				disputEsexamine.getIssuehigherYearStagescount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(51, x + 2, disputEsexamine.getImptYearImptIssuecount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(52, x + 2, disputEsexamine.getImptYearImptDealcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(53, x + 2, disputEsexamine.getImptYearImptFinishcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(54, x + 2, disputEsexamine.getImptYearImptFinishrate())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(55, x + 2,
				disputEsexamine.getConcentrateYearFinishcount())
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_RIGHT)
				.setFont("宋体", 10, true, false, false, false);
		// 第6列结束
		if (disputEsexamine.getDealPerson() == null) {
			writer.addCell(
					57,
					0,
					"签发人 :                           制表人 : "
							+ disputEsexamine.getLister()
							+ "             四川省社会管理综合治理委员会办公室")
					.mergeTo(57, x + 2)
					.setFont("宋体", 10, true, false, false, false);
		} else {
			writer.addCell(
					57,
					0,
					"签发人 : "
							+ (disputEsexamine.getDealPerson() != null ? disputEsexamine
									.getDealPerson() : "")
							+ "                           制表人 : "
							+ (disputEsexamine.getLister() != null ? disputEsexamine
									.getLister() : "")
							+ "             四川省社会管理综合治理委员会办公室")
					.mergeTo(57, x + 2)
					.setFont("宋体", 10, true, false, false, false);
		}
	}

	private ExcelWriter constructExcelWriter() {
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		return writer;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/daily/statementsReported/socialconflictmediate/citysocialconflictmediateDetails.jsp"),
			@Result(name = "successPrint", location = "/daily/statementsReported/socialconflictmediate/citysocialconflictmediateDetailsPrint.jsp"),
			@Result(name = "district", location = "/daily/statementsReported/socialconflictmediate/districtsocialconflictmediateDetails.jsp"),
			@Result(name = "town", location = "/daily/statementsReported/socialconflictmediate/townsocialconflictmediateDetails.jsp"),
			@Result(name = "village", location = "/daily/statementsReported/socialconflictmediate/villagesocialconflictmediateDetails.jsp"),
			@Result(name = "townPrint", location = "/daily/statementsReported/socialconflictmediate/townsocialconflictmediateDetailsPrint.jsp"),
			@Result(name = "districtPrint", location = "/daily/statementsReported/socialconflictmediate/districtsocialconflictmediateDetailsPrint.jsp"),
			@Result(name = "villagePrint", location = "/daily/statementsReported/socialconflictmediate/villagesocialconflictmediateDetailsPrint.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (null == organization || null == organization.getId()) {
			organization = organizationDubboService.getFullOrgById(ThreadVariable
					.getUser().getOrganization().getId());
		} else {
			organization = organizationDubboService.getFullOrgById(organization
					.getId());
		}
		sysTime = Calendar.getInstance().get(Calendar.YEAR);
		PropertyDict OrgLevelDict = propertyDictService
				.getPropertyDictByDomainNameAndDictId(
						PropertyTypes.ORGANIZATION_LEVEL, organization
								.getOrgLevel().getId());
		String returnStr = addFoward(OrgLevelDict.getInternalId());
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			getDailyYearByDirectoryId();
			List<ReportWorkingRecord> recordsList = reportWorkingRecordService
					.findReportWorkingRecord(ThreadVariable.getUser()
							.getOrganization().getId(), dailyYear.getId(),
							dailyDirectory.getId());
			Long reportTime = null;
			if (null != recordsList && !recordsList.isEmpty()) {
				reportTime = recordsList.get(0).getReportTime();
			}
			getTime(reportTime);
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			prepareForUpdateAndView();
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			prepareForUpdateAndView();
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			prepareForUpdateAndView();
		}
		return returnStr;
	}

	/**
	 * 新增的时候，根据系统时间给reportTime赋值
	 */
	private void getTime(Long time) {
		Date date = new Date();
		Long month = Long.valueOf(new SimpleDateFormat("MM").format(date));
		dateMap = new HashMap();
		if (DirectoryReportType.MONTH_REPORT == reportTypeInternalId) {
			if (null == time) {
				reportTime = 1L;
			} else if (time.equals(month)) {
				reportTime = month;
			} else if (time < month) {
				reportTime = time + 1L;
			}
			for (int i = 1; i < month + 1; i++) {
				dateMap.put(i, i + "月");
			}
		} else if (DirectoryReportType.QUARTER_REPORT == reportTypeInternalId) {
			if (month % 3 == 0) {
				reportTime = month / 3;
			} else {
				reportTime = month / 3 + 1;
			}
			for (int i = 1; i < reportTime + 1; i++) {
				if (i == 1) {
					dateMap.put(i, "第一季度");
				} else if (i == 2) {
					dateMap.put(i, "第二季度");
				} else if (i == 3) {
					dateMap.put(i, "第三季度");
				} else {
					dateMap.put(i, "第四季度");
				}
			}
			if (null == time) {
				reportTime = 1L;
			} else if (time.equals(reportTime)) {
				reportTime = time;
			} else if (time < reportTime) {
				reportTime = time + 1L;
			}
		} else if (DirectoryReportType.SEMIYEARLY_REPORT == reportTypeInternalId) {
			if (month > 6 && null != time) {
				reportTime = 2L;
			} else {
				reportTime = 1L;
			}
		}
	}

	private void getDailyYearByDirectoryId() {
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory.getId());
		dailyYear = dailyYearService.getSimpleDailyYearById(dailyDirectory
				.getDailyYear().getId());
	}

	@Action(value = "getDisputEsexamineByReportTime", results = { @Result(type = "json", name = "success", params = {
			"root", "disputEsexamine", "ignoreHierarchy", "false" }) })
	public String getDisputEsexamineByReportTime() throws Exception {
		disputEsexamine = disputEsexamineService.reportSummarizing(reportTime,
				dailyDirectory.getId(), ThreadVariable.getUser()
						.getOrganization().getId());
		return SUCCESS;
	}

	private String addFoward(int orglevel) {
		String fow;
		switch (orglevel) {
		case OrganizationLevel.COUNTRY:
			if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
				fow = "successPrint";
				break;
			}
			fow = "success";
			break;
		case OrganizationLevel.PROVINCE:
			if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
				fow = "successPrint";
				break;
			}
			fow = "success";
			break;
		case OrganizationLevel.CITY:
			if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
				fow = "successPrint";
				break;
			}
			fow = "success";
			break;
		case OrganizationLevel.DISTRICT:
			if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
				fow = "districtPrint";
				break;
			}
			fow = "district";
			break;
		case OrganizationLevel.TOWN:
			if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
				fow = "townPrint";
				break;
			}
			fow = "town";
			break;
		case OrganizationLevel.VILLAGE:
			if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
				fow = "villagePrint";
				break;
			}
			fow = "village";
			break;
		default:
			errorMessage = "请在社区新增矛盾纠纷排查";
			fow = "error";
		}
		return fow;
	}

	@PermissionFilter(ename = "deleteWorkingRecord")
	@Action(value = "deleteDisputEsexamine", results = {
			@Result(type = "json", name = "success", params = { "root", "bol" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteDisputEsexamine() throws Exception {
		if (null != detailedRuleDailyLogRelaService
				.getDetailedRuleDailyLogRelaByWorkingRecordId(disputEsexamine
						.getId())) {
			return ERROR;
		}
		disputEsexamineService.deleteDisputEsexamine(disputEsexamine.getId());
		bol = true;
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateWorkingRecord")
	@Action(value = "updateSocialConflictReord", results = {
			@Result(type = "json", name = "success", params = { "root",
					"disputEsexamine", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDisputEsexamine() throws Exception {
		encodeUTF();
		disputEsexamine.setOrganization(ThreadVariable.getUser()
				.getOrganization());
		disputEsexamine = disputEsexamineService
				.updateDisputEsexamine(disputEsexamine);
		return SUCCESS;
	}

	private void encodeUTF() {
		try {
			disputEsexamine.setName(URLDecoder.decode(
					disputEsexamine.getName(), "UTF-8"));
			disputEsexamine
					.setLister(disputEsexamine.getLister() != null ? URLDecoder
							.decode(disputEsexamine.getLister(), "UTF-8")
							: null);
			disputEsexamine
					.setDealPerson(disputEsexamine.getDealPerson() != null ? URLDecoder
							.decode(disputEsexamine.getDealPerson(), "UTF-8")
							: null);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@PermissionFilter(ename = "addWorkingRecord")
	@Action(value = "addSocialConflictReord", results = {
			@Result(type = "json", name = "success", params = { "root",
					"disputEsexamine", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addSocialConflictReord() throws Exception {
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory.getId());
		if (!validateParams()) {
			this.errorMessage = "操作失败，请联系管理员";
			return ERROR;
		}
		if (!validateYear()) {
			this.errorMessage = "" + dailyYear.getYearDate() + "年度不能补录台账信息";
			return ERROR;
		}

		disputEsexamine.setOrganization(ThreadVariable.getUser()
				.getOrganization());
		encodeUTF();
		disputEsexamine.setDailyDirectory(dailyDirectory);
		disputEsexamine.setDailyYear(dailyYear);
		ReportWorkingRecord record = reportWorkingRecordService
				.summarizingJudge(disputEsexamine.getReportTime(),
						dailyDirectory.getId(), disputEsexamine
								.getOrganization().getId());
		if (null == record) {
			disputEsexamine = disputEsexamineService
					.addDisputEsexamine(disputEsexamine);
			disputEsexamine
					.setOrganization(organizationDubboService
							.getSimpleOrgById(disputEsexamine.getOrganization()
									.getId()));
		} else {
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "findSocialConflictRecords", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSocialConflictRecords() throws Exception {
		if (null == dailyDirectoryId || null == organization
				|| null == organization.getId()) {
			gridPage = new GridPage();
		}
		gridPage = new GridPage(
				reportWorkingRecordService
						.findReportWorkingRecordForPageByOrgInternalCode(
								organization.getId(), page, rows, sidx, sord,
								dailyDirectory.getId()));
		return SUCCESS;
	}

	@Action(value = "judgeReportCondition", results = {
			@Result(type = "json", name = "success", params = { "root", "bol",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "bol",
					"ignoreHierarchy", "false" }) })
	public String judgeReportCondition() throws Exception {
		disputEsexamine = disputEsexamineService
				.getdDisputEsexamineId(disputEsexamine.getId());
		if (disputEsexamine != null
				&& (disputEsexamine.getDealPerson() != null || !disputEsexamine
						.getDealPerson().equals(""))) {
			bol = true;
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Action(value = "sunReportJudge", results = {
			@Result(type = "json", name = "success", params = { "root", "bol",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "bol",
					"ignoreHierarchy", "false" }) })
	public String sunReportJudge() throws Exception {
		if (disputEsexamineService.sunReportJudge(reportTime,
				dailyDirectory.getId(), ThreadVariable.getUser()
						.getOrganization().getId())) {
			bol = true;
			return SUCCESS;
		}
		return ERROR;
	}

	/***
	 * 上报
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "upWorkingRecord")
	@Action(value = "reportDisputEsexamine", results = {
			@Result(type = "json", name = "success", params = { "root",
					"disputEsexamine", "ignoreHierarchy", "false" }),
			@Result(type = "json", name = "error", params = { "root",
					"errorMessage" }) })
	public String reportDisputEsexamine() throws Exception {
		PropertyDict dict = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.WORKING_RECORD_SUBMITSTATE,
						WorkingRecordSubmitstate.HAS_SUBMIT).get(0);
		disputEsexamine = disputEsexamineService.updateSubmitState(
				disputEsexamine.getId(), dict.getId(),
				disputEsexamine.getExpiredEntering());
		disputEsexamine = disputEsexamineService
				.updateDisputEsexamine(validateWhenAdd(disputEsexamine));
		return SUCCESS;
	}

	private DisputEsexamine validateWhenAdd(DisputEsexamine disputEsexamine) {
		if (disputEsexamine != null
				&& disputEsexamine.getDailyDirectory() != null
				&& disputEsexamine.getDailyDirectory().getId() != null) {
			DailyDirectory dailyDirectory = dailyDirectoryService
					.getFullDailyDirectoryById(disputEsexamine
							.getDailyDirectory().getId());

			Long reportTime = disputEsexamine.getReportTime();
			Date endDate = new Date();
			if (null != reportTime && null != dailyDirectory.getTimeLimit()
					&& null != dailyDirectory.getDeadlineType()
					&& null != dailyDirectory.getDeadlineStart()
					&& null != dailyDirectory.getDeadlineEnd()) {
				if (TimeLimitHelper.REPORTED_LEDGER_MONTHLY == dailyDirectory
						.getDeadlineType()) {// 按月上报
					endDate = ExpirationDate.getMonth(reportTime,
							dailyDirectory.getDeadlineEnd(),
							dailyDirectory.getDeadlineStart());
				} else if (TimeLimitHelper.REPORTED_LEDGER_QUARTERLY == dailyDirectory
						.getDeadlineType()) {// 按季度上报
					endDate = ExpirationDate.getMonthOfQuarter(reportTime,
							dailyDirectory.getDeadlineEnd(),
							dailyDirectory.getDeadlineStart());
				} else if (TimeLimitHelper.REPORTED_LEDGER_YEAR == dailyDirectory
						.getDeadlineType()) {// 按半年上报台账
					endDate = ExpirationDate.getMonthOfHalfAYear(reportTime,
							dailyDirectory.getDeadlineEnd(),
							dailyDirectory.getDeadlineStart());
				}
			}
			if (null != dailyDirectory.getDeadlineType()
					&& TimeLimitHelper.ORDINARY_LEDGER == dailyDirectory
							.getDeadlineType()) {// 普通台账
				endDate = TimeLimitHelper.getEndDate(dailyDirectory);
			}

			Date dealDate = disputEsexamine.getSubmitTime();// ThreadVariable.getSession().getAccessTime();
			if (dealDate != null && endDate != null) {
				if (dealDate.after(endDate)) {
					disputEsexamine.setExpiredEntering(1L);// 1超期录入
				} else {
					disputEsexamine.setExpiredEntering(0L);
				}
			}
		}
		return disputEsexamine;
	}

	/**
	 * 报表回退
	 * 
	 * @return
	 */
	@PermissionFilters(value = {
			@PermissionFilter(ename = "backAreaWorkingRecord", actionName = "reportBack"),
			@PermissionFilter(ename = "backWorkingRecord", actionName = "reportBack") })
	@Action(value = "reportBack", results = {
			@Result(type = "json", name = "success", params = { "root",
					"disputEsexamine", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String reportBack() throws Exception {
		if (platformMessage == null
				|| !StringUtil.isStringAvaliable(platformMessage.getTitle())
				|| !StringUtil.isStringAvaliable(platformMessage.getContent())) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}

		List<PropertyDict> propertyDict = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.WORKING_RECORD_SUBMITSTATE,
						WorkingRecordSubmitstate.BACK_STATE);

		disputEsexamine = disputEsexamineService.backWorkingRecord(dailyLogId,
				propertyDict.get(0).getId());
		PropertyDict reportTypeDict = propertyDictService
				.getPropertyDictByDomainNameAndDictId(
						PropertyTypes.WORKING_RECORD_SUBMITSTATE,
						disputEsexamine.getSubmitState().getId());

		disputEsexamine.setSubmitState(reportTypeDict);

		List<User> users = permissionService.findChildUsersByEnameAndOrgCode(
				"myWorkingRecordManagement", disputEsexamine.getOrganization()
						.getId().toString());

		if (null != users && users.size() > 0) {

			platformMessage = platformMessageFactory
					.createStatedReportsBackPlatformMessage(platformMessage);

			for (User user : users) {
				platformMessage.setReceiverId(user.getId());
				platformaMessageService
						.sendPlatformMessageToUser(platformMessage);
			}

		}
		return "success";
	}

	private boolean validateParams() {
		if (null == dailyDirectory || null == dailyDirectory.getId()) {
			return false;
		}
		if (null == dailyYear || null == dailyYear.getId()) {
			return false;
		}
		return true;
	}

	private boolean validateYear() {
		dailyYear = dailyYearService.getSimpleDailyYearById(dailyYear.getId());
		Calendar can = Calendar.getInstance();
		int year = can.get(Calendar.YEAR);
		if ((year - 1) > dailyYear.getYearDate().intValue()) {
			return false;
		}
		return true;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	public DailyYear getDailyYear() {
		return dailyYear;
	}

	public void setDailyYear(DailyYear dailyYear) {
		this.dailyYear = dailyYear;
	}

	public Long getReportTypeInternalId() {
		return reportTypeInternalId;
	}

	public void setReportTypeInternalId(Long reportTypeInternalId) {
		this.reportTypeInternalId = reportTypeInternalId;
	}

	public DisputEsexamine getDisputEsexamine() {
		return disputEsexamine;
	}

	public void setDisputEsexamine(DisputEsexamine disputEsexamine) {
		this.disputEsexamine = disputEsexamine;
	}

	public Long getReportTime() {
		return reportTime;
	}

	public void setReportTime(Long reportTime) {
		this.reportTime = reportTime;
	}

	public Long getDailyLogId() {
		return dailyLogId;
	}

	public void setDailyLogId(Long dailyLogId) {
		this.dailyLogId = dailyLogId;
	}

	public PlatformMessage getPlatformMessage() {
		return platformMessage;
	}

	public void setPlatformMessage(PlatformMessage platformMessage) {
		this.platformMessage = platformMessage;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public Long getDailyDirectoryId() {
		return dailyDirectoryId;
	}

	public void setDailyDirectoryId(Long dailyDirectoryId) {
		this.dailyDirectoryId = dailyDirectoryId;
	}

	public String getReportedType() {
		return reportedType;
	}

	public void setReportedType(String reportedType) {
		this.reportedType = reportedType;
	}

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	public int getSysTime() {
		return sysTime;
	}

	public Map getDateMap() {
		return dateMap;
	}

	public void setDateMap(Map dateMap) {
		this.dateMap = dateMap;
	}
}

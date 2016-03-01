package com.tianque.working.controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.vo.PopedomEmphasisSafetyReportSituation;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.domain.Organization;
import com.tianque.domain.PlantFormMessageConfig;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkingRecordSubmitstate;
import com.tianque.service.DetailedRuleDailyLogRelaService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyYear;
import com.tianque.working.domain.ExpirationDate;
import com.tianque.working.domain.InvestigationRemediation;
import com.tianque.working.domain.ReportWorkingRecord;
import com.tianque.working.domain.TimeLimitHelper;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyYearService;
import com.tianque.working.service.InvestigationRemediationService;
import com.tianque.working.service.ReportWorkingRecordService;

@SuppressWarnings("serial")
@Controller("investigationRemediationController")
@Scope("prototype")
@Transactional
public class InvestigationRemediationController extends BaseAction {

	@Autowired
	private InvestigationRemediationService investigationRemediationService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private ReportWorkingRecordService reportWorkingRecordService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private DailyYearService dailyYearService;
	@Autowired
	private DetailedRuleDailyLogRelaService detailedRuleDailyLogRelaService;

	private InvestigationRemediation investigationRemediation;
	private Long dailyDirectoryId;
	private DailyYear dailyYear;
	private DailyDirectory dailyDirectory;
	private Date dealDate;
	private Long reportTime;
	private String ids;
	private String modeType;
	private Organization organization;
	private Integer orgLevle;
	private String[][] popedomReports;
	private String objectType;
	private PlantFormMessageConfig plantFormMessageConfig;
	private Long dailyLogId;
	private List<PopedomEmphasisSafetyReportSituation> popedomEmphasisSafetyReportSituation = new ArrayList<PopedomEmphasisSafetyReportSituation>();
	private Map dateMap;

	public String dispatch() throws Exception {
		prepareExcute();
		if (DialogMode.ADD_MODE.equalsIgnoreCase(getMode())) {
			dailyDirectory = dailyDirectoryService
					.getSimpleDailyDirectoryById(dailyDirectoryId);
			dailyYear = dailyYearService.getSimpleDailyYearById(dailyDirectory
					.getDailyYear().getId());
			List<ReportWorkingRecord> recordsList = reportWorkingRecordService
					.findReportWorkingRecord(ThreadVariable.getUser()
							.getOrganization().getId(), dailyYear.getId(),
							dailyDirectoryId);
			Long reportTime = null;
			if (null != recordsList && !recordsList.isEmpty()) {
				reportTime = recordsList.get(0).getReportTime();
			}
			getTime(reportTime);
			return "PREPARE_ADD_REPORT";
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())) {
			prepareUpdateInvestigationRemediation();
			return "PREPARE_UPDATE_MONTH_REPORT";
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(getMode())) {
			viewInvestigationRemediation();
			return "PREPARE_VIEW_MONTH_REPORT";
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(getMode())) {
			viewDailyLog();
			return "print";
		}
		return SUCCESS;
	}

	public String investigationRemediationList() throws Exception {
		gridPage = new GridPage(
				reportWorkingRecordService
						.findReportWorkingRecordForPageByOrgInternalCode(
								organization.getId(), page, rows, sidx, sord,
								dailyDirectoryId));
		return SUCCESS;
	}

	public String getInvestigationRemediationByReportTime() throws Exception {
		investigationRemediation = investigationRemediationService
				.reportSummarizing(reportTime, dailyDirectory.getId(),
						ThreadVariable.getUser().getOrganization().getId());

		return SUCCESS;
	}

	public String addInvestigationRemediation() throws Exception {
		encodeUTF();
		investigationRemediation.setOrganization(ThreadVariable.getUser()
				.getOrganization());
		investigationRemediation.setDailyYear(dailyYear);
		investigationRemediation.setDailyDirectory(dailyDirectory);
		ReportWorkingRecord record = reportWorkingRecordService
				.summarizingJudge(investigationRemediation.getReportTime(),
						dailyDirectory.getId(), investigationRemediation
								.getOrganization().getId());
		if (null == record) {
			investigationRemediation = investigationRemediationService
					.addInvestigationRemediation(investigationRemediation);
			investigationRemediation.setOrganization(organizationDubboService
					.getSimpleOrgById(investigationRemediation
							.getOrganization().getId()));
		} else {
			return ERROR;
		}
		return SUCCESS;
	}

	public String updateInvestigationRemediation() throws Exception {
		encodeUTF();
		investigationRemediation.setOrganization(ThreadVariable.getUser()
				.getOrganization());
		investigationRemediation = investigationRemediationService
				.updateInvestigationRemediation(investigationRemediation);
		return SUCCESS;
	}

	public String deleteInvestigationRemediation() throws Exception {
		if (null != detailedRuleDailyLogRelaService
				.getDetailedRuleDailyLogRelaByWorkingRecordId(investigationRemediation
						.getId())) {
			return ERROR;
		}
		investigationRemediationService
				.deleteInvestigationRemediation(investigationRemediation
						.getId());
		return SUCCESS;
	}

	public String downloadInvestigationRemediation() throws Exception {
		investigationRemediation = investigationRemediationService
				.getInvestigationRemediationById(investigationRemediation
						.getId());
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(investigationRemediation
						.getDailyDirectory().getId());
		dailyYear = dailyYearService.getSimpleDailyYearById(dailyDirectory
				.getDailyYear().getId());
		inputStream = exportInvestigationRemediation(investigationRemediation);
		downloadFileName = new String("“排查整治、强基促稳”专项活动月报表".getBytes("gbk"),
				"ISO8859-1") + ".xls";
		return SUCCESS;
	}

	// 上报
	public String reportInvestigationRemediation() throws Exception {
		List<PropertyDict> reportTypeDict = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.WORKING_RECORD_SUBMITSTATE,
						WorkingRecordSubmitstate.HAS_SUBMIT);

		investigationRemediation = investigationRemediationService
				.updateSubmitState(investigationRemediation.getId(),
						reportTypeDict.get(0).getId(),
						investigationRemediation.getExpiredEntering());

		investigationRemediation = investigationRemediationService
				.updateInvestigationRemediation(validateWhenAdd(investigationRemediation));// 上报时是否超期
		if (investigationRemediation == null) {
			return ERROR;
		}
		return SUCCESS;
	}

	private InvestigationRemediation validateWhenAdd(
			InvestigationRemediation investigationRemediation) {
		if (investigationRemediation != null
				&& investigationRemediation.getDailyDirectory() != null
				&& investigationRemediation.getDailyDirectory().getId() != null) {
			DailyDirectory dailyDirectory = dailyDirectoryService
					.getFullDailyDirectoryById(investigationRemediation
							.getDailyDirectory().getId());

			Long reportTime = investigationRemediation.getReportTime();
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

			Date dealDate = investigationRemediation.getSubmitTime();// ThreadVariable.getSession().getAccessTime();
			if (dealDate != null && endDate != null) {
				if (dealDate.after(endDate)) {
					investigationRemediation.setExpiredEntering(1L);// 1超期录入
				} else {
					investigationRemediation.setExpiredEntering(0L);
				}
			}
		}
		return investigationRemediation;
	}

	public String judgeReportCondition() {
		investigationRemediation = investigationRemediationService
				.getInvestigationRemediationById(investigationRemediation
						.getId());
		if (investigationRemediation != null
				&& (investigationRemediation.getDealPerson() != null || !investigationRemediation
						.getDealPerson().equals(""))) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String reportBack() {
		try {
			if (plantFormMessageConfig == null) {
				this.errorMessage = "参数错误!";
				return ERROR;
			}
			if (StringUtils.isBlank(plantFormMessageConfig.getMsgTitle())) {
				this.errorMessage = "参数错误!";
				return ERROR;
			}
			if (StringUtils.isBlank(plantFormMessageConfig.getMsgContent())) {
				this.errorMessage = "参数错误!";
				return ERROR;
			}
			List<PropertyDict> propertyDict = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.WORKING_RECORD_SUBMITSTATE,
							WorkingRecordSubmitstate.BACK_STATE);
			investigationRemediation = investigationRemediationService
					.backWorkingRecord(dailyLogId, propertyDict.get(0).getId());
			PropertyDict reportTypeDict = propertyDictService
					.getPropertyDictByDomainNameAndDictId(
							PropertyTypes.WORKING_RECORD_SUBMITSTATE,
							investigationRemediation.getSubmitState().getId());
			investigationRemediation.setSubmitState(reportTypeDict);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	private InputStream exportInvestigationRemediation(
			InvestigationRemediation investigationRemediation) throws Exception {
		organization = organizationDubboService
				.getFullOrgById(investigationRemediation.getOrganization()
						.getId());
		ExcelWriter writer = constructExcelWriter();
		writer.addWorkSheet("“排查整治、强基促稳”专项活动月报表");
		appendTableCol(writer, investigationRemediation);
		return new java.io.FileInputStream(writer.getExcelFile(UUID
				.randomUUID() + ".xls"));
	}

	private void appendTableCol(ExcelWriter writer,
			InvestigationRemediation investigationRemediation) {
		// 零行
		writer.addCell(0, 0, investigationRemediation.getName()).mergeTo(0, 15)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(
				1,
				0,
				"填报单位：" + organization.getOrgName()
						+ "                        填报月份："
						+ investigationRemediation.getReportTime() + "月份")
				.mergeTo(1, 15).setFont("宋体", 16, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		// 第0-1列
		writer.addCell(2, 0, "排查数").mergeTo(3, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(4, 0, "矛盾纠纷").mergeTo(4, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, 0, "排查数").mergeTo(6, 0)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, 1, "化解成功数").mergeTo(6, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, 0, investigationRemediation.getDisputeSum()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(7, 1, investigationRemediation.getDisputeSucces())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 0, "安全隐患").mergeTo(9, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 0, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(10, 1, "消除数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(11, 0, investigationRemediation.getSecuritySum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, 1, investigationRemediation.getSecurityEliminate())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 0, "基层基础").mergeTo(12, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(13, 0, "排查数").mergeTo(14, 0)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(13, 1, "解决数").mergeTo(14, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 0, investigationRemediation.getBasicSum()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(15, 1, investigationRemediation.getBasicSolving())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, 0, "流动人口和特殊人群管理").mergeTo(17, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(18, 0, "备注：").mergeTo(18, 1)
				.setFont("宋体", 10, true, false, false, false);
		// 第2列
		writer.addCell(2, 2, "单位总数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(3, 2, "参与人数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(4, 2, "土地山林矿山水利海事渔事等权属纠纷").mergeTo(5, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 2, "环境污染生态破坏纠纷").mergeTo(7, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 2, "治安安全隐患").mergeTo(9, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 2, "食药安全隐患").mergeTo(11, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 2, "未按要求配备乡镇街道综治办专职副主任数").mergeTo(15, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, 2, "流动人口数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(17, 2, "严重精神障碍患者人数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(18, 2, "各栏均为开展专项活动以来的累计数").mergeTo(18, 15)
				.setFont("宋体", 10, true, false, false, false);
		// 第3-4列
		writer.addCell(2, 3, investigationRemediation.getInvestigationSum())
				.mergeTo(2, 4).setFont("宋体", 10, true, false, false, false);
		writer.addCell(3, 3,
				investigationRemediation.getInvestigationParticipant())
				.mergeTo(3, 4).setFont("宋体", 10, true, false, false, false);
		writer.addCell(4, 3, "排查数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(4, 4, "化解数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, 3, investigationRemediation.getDisputeBelongSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, 4, investigationRemediation.getDisputeBelongSuccess())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 3, "排查数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 4, "化解数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, 3,
				investigationRemediation.getDisputeEnvironmentSum()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(7, 4,
				investigationRemediation.getDisputeEnvironmentSuccess())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 3, "排查数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 4, "消除数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, 3, investigationRemediation.getSecurityPoliceSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, 4,
				investigationRemediation.getSecurityPoliceEliminate()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(10, 3, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(10, 4, "消除数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(11, 3, investigationRemediation.getSecurityGrocerySum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, 4,
				investigationRemediation.getSecurityGroceryEliminate())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 3, "排查数").mergeTo(14, 3)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 4, "解决数").mergeTo(14, 4)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 3, investigationRemediation.getBasicDirectorSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 4,
				investigationRemediation.getBasicDirectorSolving()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(16, 3,
				investigationRemediation.getSpecialtrampResident())
				.mergeTo(16, 4).setFont("宋体", 10, true, false, false, false);
		writer.addCell(17, 3,
				investigationRemediation.getSpecialMentalPatient())
				.mergeTo(17, 4).setFont("宋体", 10, true, false, false, false);
		// 第5列
		writer.addCell(2, 5, "其中村（社区）").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(3, 5, "其中机关干部").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(4, 5, "“三改一拆”纠纷（征地、拆迁、安置）").mergeTo(5, 5)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 5, "村（社）务管理纠纷").mergeTo(7, 5)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 5, "交通安全隐患").mergeTo(9, 5)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 5, "消防安全隐患").mergeTo(11, 5)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 5, "乡镇街道社会服务管理中心运行机制未整合数").mergeTo(15, 5)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, 5, "刑释解教人数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(17, 5, "社区矫正人数").setFont("宋体", 10, true, false, false,
				false);
		// 第6-7列
		writer.addCell(2, 6, investigationRemediation.getInvestigationVillage())
				.mergeTo(2, 7).setFont("宋体", 10, true, false, false, false);
		writer.addCell(3, 6, investigationRemediation.getInvestigationLeader())
				.mergeTo(3, 7).setFont("宋体", 10, true, false, false, false);
		writer.addCell(4, 6, "排查数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(4, 7, "化解数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, 6, investigationRemediation.getDisputeLandSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, 7, investigationRemediation.getDisputeLandSuccess())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 6, "排查数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 7, "化解数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, 6, investigationRemediation.getDisputeDissensionSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, 7,
				investigationRemediation.getDisputeDissensionSuccess())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 6, "排查数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 7, "消除数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, 6, investigationRemediation.getSecurityTrafficSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, 7,
				investigationRemediation.getSecurityTrafficEliminate())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 6, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(10, 7, "消除数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(11, 6, investigationRemediation.getSecurityFireSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, 7,
				investigationRemediation.getSecurityFireEliminate()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(12, 6, "排查数").mergeTo(14, 6)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 7, "解决数").mergeTo(14, 7)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 6, investigationRemediation.getBasicIntegrateSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 7,
				investigationRemediation.getBasicIntegrateSolving()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(16, 6, investigationRemediation.getSpecialPositiveInfo())
				.mergeTo(16, 7).setFont("宋体", 10, true, false, false, false);
		writer.addCell(17, 6, investigationRemediation.getSpecialRectify())
				.mergeTo(17, 7).setFont("宋体", 10, true, false, false, false);
		// 第8列
		writer.addCell(2, 8, "企业").setFont("宋体", 10, true, false, false, false);
		writer.addCell(3, 8, "单位职工").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(4, 8, "执法司法纠纷").mergeTo(5, 8)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 8, "教育卫生纠纷").mergeTo(7, 8)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 8, "安全生产隐患").mergeTo(9, 8)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 8, "经济安全隐患").mergeTo(11, 8)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 8, "未建立或未正常运行村（企业）服务管理站（室）数").mergeTo(15, 8)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, 8, "重点上访人数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(17, 8, "吸毒人数").setFont("宋体", 10, true, false, false,
				false);
		// 第9-10列
		writer.addCell(2, 9,
				investigationRemediation.getInvestigationEnterprise())
				.mergeTo(2, 10).setFont("宋体", 10, true, false, false, false);
		writer.addCell(3, 9, investigationRemediation.getInvestigationStaff())
				.mergeTo(3, 10).setFont("宋体", 10, true, false, false, false);
		writer.addCell(4, 9, "排查数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(4, 10, "化解数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(5, 9, investigationRemediation.getDisputeLawSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, 10, investigationRemediation.getDisputeLawSuccess())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 9, "排查数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 10, "化解数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(7, 9, investigationRemediation.getDisputeEducationSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, 10,
				investigationRemediation.getDisputeEducationSuccess()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(8, 9, "排查数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 10, "消除数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(9, 9, investigationRemediation.getSecurityProduceSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, 10,
				investigationRemediation.getSecurityProduceEliminate())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 9, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(10, 10, "消除数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(11, 9, investigationRemediation.getSecurityEconomySum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, 10,
				investigationRemediation.getSecurityEconomyEliminate())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 9, "排查数").mergeTo(14, 9)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 10, "解决数").mergeTo(14, 10)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 9, investigationRemediation.getBasicCreationSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 10,
				investigationRemediation.getBasicCreationSolving()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(16, 9,
				investigationRemediation.getSpecialSuperiorVisit())
				.mergeTo(16, 10).setFont("宋体", 10, true, false, false, false);
		writer.addCell(17, 9, investigationRemediation.getSpecialDruggy())
				.mergeTo(17, 10).setFont("宋体", 10, true, false, false, false);
		// 第11列
		writer.addCell(2, 11, "单位")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(3, 11, "普通群众").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(4, 11, "劳资经济活动纠纷").mergeTo(5, 11)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 11, "婚姻家庭邻里纠纷").mergeTo(7, 11)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 11, "线路管理隐患").mergeTo(9, 11)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 11, "其他安全隐患").mergeTo(11, 11)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 11, "网格划分、管理人员配备不符合要求数").mergeTo(15, 11)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, 11, "闲散青少年数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(17, 11, "其他特殊人员").setFont("宋体", 10, true, false, false,
				false);
		// 第12-15列
		writer.addCell(2, 12, investigationRemediation.getInvestigationUnit())
				.mergeTo(2, 15).setFont("宋体", 10, true, false, false, false);
		writer.addCell(3, 12, investigationRemediation.getInvestigationMasses())
				.mergeTo(3, 15).setFont("宋体", 10, true, false, false, false);
		writer.addCell(4, 12, "排查数").mergeTo(4, 13)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(4, 14, "化解数").mergeTo(4, 15)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, 12, investigationRemediation.getDisputeEconomySum())
				.mergeTo(5, 13).setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, 14,
				investigationRemediation.getDisputeEconomySuccess())
				.mergeTo(5, 15).setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 12, "排查数").mergeTo(6, 13)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 14, "化解数").mergeTo(6, 15)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, 12, investigationRemediation.getDisputeMarriageSum())
				.mergeTo(7, 13).setFont("宋体", 10, true, false, false, false);
		writer.addCell(7, 14,
				investigationRemediation.getDisputeMarriageSuccess())
				.mergeTo(7, 15).setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 12, "排查数").mergeTo(8, 13)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 14, "消除数").mergeTo(8, 15)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, 12, investigationRemediation.getSecurityRouteSum())
				.mergeTo(9, 13).setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, 14,
				investigationRemediation.getSecurityRouteEliminate())
				.mergeTo(9, 15).setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 12, "排查数").mergeTo(10, 13)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 14, "消除数").mergeTo(10, 15)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, 12, investigationRemediation.getSecurityOtherSum())
				.mergeTo(11, 13).setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, 14,
				investigationRemediation.getSecurityOtherEliminate())
				.mergeTo(11, 15).setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 12, "排查数").mergeTo(14, 12)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 13, "解决数").mergeTo(14, 13)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 12, investigationRemediation.getBasicAllocatingSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 13,
				investigationRemediation.getBasicAllocatingSolving()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(12, 14, "村（社区）未正常使用综治信息系统数").mergeTo(13, 15)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(14, 14, "排查数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(14, 15, "解决数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(15, 14,
				investigationRemediation.getBasicComprehensiveSum()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(15, 15,
				investigationRemediation.getBasicComprehensiveSolving())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, 12, investigationRemediation.getSpecialIdleYouth())
				.mergeTo(16, 15).setFont("宋体", 10, true, false, false, false);
		writer.addCell(17, 12, investigationRemediation.getSpecialOther())
				.mergeTo(17, 15).setFont("宋体", 10, true, false, false, false);

		writer.addCell(
				19,
				0,
				"填报人：" + investigationRemediation.getDealPerson()
						+ "(注意:该项上报时填写)").mergeTo(19, 12)
				.setFont("宋体", 10, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.addCell(19, 13, "审核人:" + investigationRemediation.getLister())
				.mergeTo(19, 15).setFont("宋体", 10, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);

	}

	private ExcelWriter constructExcelWriter() {
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);

		return writer;
	}

	private void prepareUpdateInvestigationRemediation() {
		getActivityWorkingRecodeDetail();
	}

	private void getActivityWorkingRecodeDetail() {
		try {
			investigationRemediation = new InvestigationRemediation(
					reportWorkingRecordService
							.getReportWorkingRecordById(investigationRemediation
									.getId()));
			investigationRemediation.setOrganization(organizationDubboService
					.getFullOrgById(investigationRemediation.getOrganization()
							.getId()));
			dailyDirectory = dailyDirectoryService
					.getSimpleDailyDirectoryById(investigationRemediation
							.getDailyDirectory().getId());
			dailyYear = dailyYearService.getSimpleDailyYearById(dailyDirectory
					.getDailyYear().getId());
			reportTime = investigationRemediation.getReportTime();

			getTime(reportTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void encodeUTF() {
		try {
			investigationRemediation.setName(URLDecoder.decode(
					investigationRemediation.getName(), "UTF-8"));
			investigationRemediation.setLister(investigationRemediation
					.getLister() != null ? URLDecoder.decode(
					investigationRemediation.getLister(), "UTF-8") : null);
			investigationRemediation.setDealPerson(investigationRemediation
					.getDealPerson() != null ? URLDecoder.decode(
					investigationRemediation.getDealPerson(), "UTF-8") : null);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private void getTime(Long time) {
		Date date = new Date();
		Long month = Long.valueOf(new SimpleDateFormat("MM").format(date));
		dateMap = new HashMap();
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
	}

	private void prepareExcute() {
		if (null == organization || null == organization.getId()) {
			organization = organizationDubboService.getSimpleOrgById(ThreadVariable
					.getSession().getOrganization().getId());
		} else {
			organization = organizationDubboService.getSimpleOrgById(organization
					.getId());
		}
		PropertyDict propertyDict = propertyDictService
				.getPropertyDictByDomainNameAndDictId(
						PropertyTypes.ORGANIZATION_LEVEL, organization
								.getOrgLevel().getId());
		if (propertyDict == null) {
			this.errorMessage = "请配置数据库字典!";
			orgLevle = 20;
			return;
		}
		if (propertyDict.getInternalId() == OrganizationLevel.COUNTRY) {
			orgLevle = 6;
		} else if (propertyDict.getInternalId() == OrganizationLevel.PROVINCE) {
			orgLevle = 5;
		} else if (propertyDict.getInternalId() == OrganizationLevel.CITY) {
			orgLevle = 4;
		} else if (propertyDict.getInternalId() == OrganizationLevel.DISTRICT) {
			orgLevle = 3;
		} else if (propertyDict.getInternalId() == OrganizationLevel.TOWN) {
			orgLevle = 2;
		} else if (propertyDict.getInternalId() == OrganizationLevel.VILLAGE) {
			orgLevle = 1;
		} else if (propertyDict.getInternalId() == OrganizationLevel.GRID) {
			orgLevle = 0;
		} else {
			orgLevle = 10;
		}
	}

	private void viewDailyLog() {
		getActivityWorkingRecodeDetail();
	}

	private void viewInvestigationRemediation() {
		getActivityWorkingRecodeDetail();
	}

	public Long getDailyDirectoryId() {
		return dailyDirectoryId;
	}

	public void setDailyDirectoryId(Long dailyDirectoryId) {
		this.dailyDirectoryId = dailyDirectoryId;
	}

	public DailyYear getDailyYear() {
		return dailyYear;
	}

	public void setDailyYear(DailyYear dailyYear) {
		this.dailyYear = dailyYear;
	}

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	public Long getReportTime() {
		return reportTime;
	}

	public void setReportTime(Long reportTime) {
		this.reportTime = reportTime;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	public InvestigationRemediation getInvestigationRemediation() {
		return investigationRemediation;
	}

	public void setInvestigationRemediation(
			InvestigationRemediation investigationRemediation) {
		this.investigationRemediation = investigationRemediation;
	}

	public Integer getOrgLevle() {
		return orgLevle;
	}

	public void setOrgLevle(Integer orgLevle) {
		this.orgLevle = orgLevle;
	}

	public String[][] getPopedomReports() {
		return popedomReports;
	}

	public void setPopedomReports(String[][] popedomReports) {
		this.popedomReports = popedomReports;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public PlantFormMessageConfig getPlantFormMessageConfig() {
		return plantFormMessageConfig;
	}

	public void setPlantFormMessageConfig(
			PlantFormMessageConfig plantFormMessageConfig) {
		this.plantFormMessageConfig = plantFormMessageConfig;
	}

	public Long getDailyLogId() {
		return dailyLogId;
	}

	public void setDailyLogId(Long dailyLogId) {
		this.dailyLogId = dailyLogId;
	}

	public List<PopedomEmphasisSafetyReportSituation> getPopedomEmphasisSafetyReportSituation() {
		return popedomEmphasisSafetyReportSituation;
	}

	public void setPopedomEmphasisSafetyReportSituation(
			List<PopedomEmphasisSafetyReportSituation> popedomEmphasisSafetyReportSituation) {
		this.popedomEmphasisSafetyReportSituation = popedomEmphasisSafetyReportSituation;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Map getDateMap() {
		return dateMap;
	}

	public void setDateMap(Map dateMap) {
		this.dateMap = dateMap;
	}
}
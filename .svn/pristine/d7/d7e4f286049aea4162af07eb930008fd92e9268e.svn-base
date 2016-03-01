package com.tianque.working.controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.tianque.controller.vo.PopedomEmphasisSafetyReportSituation;
import com.tianque.controller.vo.PopedomEmphasisSafetyforQuarter;
import com.tianque.controller.vo.PopedomEmphasisSafetyforSixMonths;
import com.tianque.controller.vo.PopedomEmphasisSafetyforYesr;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DateUtil;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.User;
import com.tianque.domain.property.DirectoryReportType;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkingRecordSubmitstate;
import com.tianque.domain.workingRecord.EmphasisSafetyDetail;
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
import com.tianque.working.service.EmphasisSafetyDetailService;
import com.tianque.working.service.ReportWorkingRecordService;

/***
 * 治安重点整治
 */
@SuppressWarnings("serial")
@Controller("emphasisSafetyDetailController")
@Namespace("/daily/publicSecurityRenovateManage")
@Scope("prototype")
@Transactional
public class EmphasisSafetyDetailController extends BaseAction {
	@Autowired
	private EmphasisSafetyDetailService emphasisSafetyDetailService;
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
	private PermissionService permissionService;
	@Autowired
	private DetailedRuleDailyLogRelaService detailedRuleDailyLogRelaService;
	@Autowired
	private PlatformMessageFactory platformMessageFactory;
	@Autowired
	private PlatformMessageService platformaMessageService;

	private static Logger logger = LoggerFactory
			.getLogger(EmphasisSafetyDetailController.class);

	private EmphasisSafetyDetail emphasisSafetyDetail;
	private Organization organization;
	private DailyDirectory dailyDirectory;
	private Long dailyDirectoryId;
	private DailyYear dailyYear;
	private Integer orgLevle;
	private String[][] popedomReports;
	private List<PopedomEmphasisSafetyReportSituation> popedomEmphasisSafetyReportSituation = new ArrayList<PopedomEmphasisSafetyReportSituation>();
	private int reportTypeInternalId;
	private Date dealDate;
	private Long reportTime;
	private String ids;
	private String modeType;
	private String objectType;
	private PlatformMessage platformMessage;
	private Long dailyLogId;
	private boolean bol;
	private String reportedType;
	private int sysTime;

	private Map dateMap;

	/**
	 * 导出数据
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "downloadEmphasisSafetyDetail", results = {
			@Result(name = "success", type = "stream", params = {
					"contentType",
					"application/vnd.ms-excel;charset=ISO8859-1", "inputName",
					"inputStream", "contentDisposition",
					"attachment;filename=${downloadFileName}" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String downloadEmphasisSafetyDetail() throws Exception {
		emphasisSafetyDetail = emphasisSafetyDetailService
				.getEmphasisSafetyDetailById(emphasisSafetyDetail.getId());
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(emphasisSafetyDetail
						.getDailyDirectory().getId());
		dailyYear = dailyYearService.getSimpleDailyYearById(dailyDirectory
				.getDailyYear().getId());
		inputStream = exportEmphasisSafetyDetail(emphasisSafetyDetail);
		downloadFileName = new String("治安重点整治".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		return SUCCESS;
	}

	private InputStream exportEmphasisSafetyDetail(
			EmphasisSafetyDetail emphasisSafetyDetail) throws Exception {
		organization = organizationDubboService.getFullOrgById(emphasisSafetyDetail
				.getOrganization().getId());
		ExcelWriter writer = constructExcelWriter();
		writer.addWorkSheet("治安重点整治况表");
		appendTableCol(writer, emphasisSafetyDetail);
		return new java.io.FileInputStream(writer.getExcelFile(UUID
				.randomUUID() + ".xls"));
	}

	private void appendTableCol(ExcelWriter writer,
			EmphasisSafetyDetail emphasisSafetyDetail) {
		// 零行
		writer.addCell(0, 0, emphasisSafetyDetail.getName()).mergeTo(0, 3)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		PropertyDict dict = propertyDictService
				.getPropertyDictByDomainNameAndDictId(
						PropertyTypes.DIRECTORY_REPORT_TYPE,
						dailyDirectoryService
								.getSimpleDailyDirectoryById(
										emphasisSafetyDetail
												.getDailyDirectory().getId())
								.getDirectoryReportType().getId());
		if (dict.getInternalId() == DirectoryReportType.MONTH_REPORT) {
			writer.addCell(
					1,
					0,
					"填报单位：" + organization.getOrgName()
							+ "                        填报月份："
							+ emphasisSafetyDetail.getReportTime() + "月份")
					.mergeTo(1, 3)
					.setFont("宋体", 16, true, false, false, false)
					.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		} else if (dict.getInternalId() == DirectoryReportType.QUARTER_REPORT) {
			writer.addCell(
					1,
					0,
					"填报单位：" + organization.getOrgName()
							+ "                        填报季度：第"
							+ emphasisSafetyDetail.getReportTime() + "季度")
					.mergeTo(1, 3)
					.setFont("宋体", 16, true, false, false, false)
					.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		} else if (dict.getInternalId() == DirectoryReportType.SEMIYEARLY_REPORT
				&& emphasisSafetyDetail.getReportTime() == 1) {
			writer.addCell(
					1,
					0,
					"填报单位：" + organization.getOrgName()
							+ "                        填报半年度：上半年度")
					.mergeTo(1, 3)
					.setFont("宋体", 16, true, false, false, false)
					.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		} else if (dict.getInternalId() == DirectoryReportType.SEMIYEARLY_REPORT
				&& emphasisSafetyDetail.getReportTime() == 2) {
			writer.addCell(
					1,
					0,
					"填报单位：" + organization.getOrgName()
							+ "                        填报半年度：下半年度")
					.mergeTo(1, 3)
					.setFont("宋体", 16, true, false, false, false)
					.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		} else if (dict.getInternalId() == DirectoryReportType.YEAR_REPORT) {
			writer.addCell(
					1,
					0,
					"填报单位：" + organization.getOrgName()
							+ "                        填报年度："
							+ emphasisSafetyDetail.getReportTime() + "年底")
					.mergeTo(1, 3)
					.setFont("宋体", 16, true, false, false, false)
					.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		}
		// 第0列
		writer.addCell(2, 0, "排查次数").mergeTo(4, 0)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, 0, "宣传发动数").mergeTo(12, 0)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(13, 0, "排查发现治安重点地区数").mergeTo(23, 0)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(24, 0, "已整治重点地区数").mergeTo(28, 0)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(29, 0, "正在整治重点地区数").mergeTo(33, 0)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(34, 0, "打击整治数").mergeTo(46, 0)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(47, 0, "警示数").mergeTo(50, 0)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(51, 0, "挂牌整治数").mergeTo(54, 0)
				.setFont("宋体", 10, true, false, false, false);
		// 第1列
		writer.addCell(2, 1, "总次数").mergeTo(2, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(3, 1, "其中").mergeTo(4, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(5, 1, "总人数").mergeTo(5, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 1, "其中").mergeTo(12, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(13, 1, "总数").mergeTo(13, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(14, 1, "其中").mergeTo(17, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(18, 1, "其中").mergeTo(23, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(24, 1, "总数").mergeTo(24, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(25, 1, "其中").mergeTo(28, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(29, 1, "总数").mergeTo(29, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(30, 1, "其中").mergeTo(33, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(34, 1, "破获刑事案件数").mergeTo(37, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(38, 1, "抓获犯罪嫌疑人数").mergeTo(43, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(44, 1, "打掉黑恶势力数").mergeTo(46, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(47, 1, "总数").mergeTo(47, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(48, 1, "其中").mergeTo(50, 1)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(51, 1, "总数").mergeTo(51, 2)
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(52, 1, "其中").mergeTo(54, 1)
				.setFont("宋体", 10, true, false, false, false);
		// 第2列
		writer.addCell(3, 2, "组织工作组数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(4, 2, "其他").setFont("宋体", 10, true, false, false, false);
		writer.addCell(6, 2, "发动干部数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(7, 2, "发动群众数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(8, 2, "发布通告数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(9, 2, "召开座谈会数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(10, 2, "群众举报数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(11, 2, "从中破获刑事案件数").setFont("宋体", 10, true, false,
				false, false);
		writer.addCell(12, 2, "从中抓获犯罪嫌疑人数").setFont("宋体", 10, true, false,
				false, false);
		writer.addCell(14, 2, "县（市,区）数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(15, 2, "乡镇(街道)数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(16, 2, "村(社区)数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(17, 2, "其他")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(18, 2, "黑恶势力地区数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(19, 2, "杀人爆炸等严重暴力犯罪地区数").setFont("宋体", 10, true, false,
				false, false);
		writer.addCell(20, 2, "两抢一盗犯罪地区数").setFont("宋体", 10, true, false,
				false, false);
		writer.addCell(21, 2, "黄赌毒违法犯罪地区数").setFont("宋体", 10, true, false,
				false, false);
		writer.addCell(22, 2, "邪教违法犯罪地区数").setFont("宋体", 10, true, false,
				false, false);
		writer.addCell(23, 2, "其他")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(25, 2, "县（市,区）数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(26, 2, "乡镇(街道)数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(27, 2, "村(社区)数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(28, 2, "其他")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(30, 2, "县（市,区）数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(31, 2, "乡镇(街道)数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(32, 2, "村(社区)数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(33, 2, "其他")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(34, 2, "总数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(35, 2, "杀人爆炸等严重暴力犯罪案件数").setFont("宋体", 10, true, false,
				false, false);
		writer.addCell(36, 2, "两抢一盗案件数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(37, 2, "黄赌毒案件数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(38, 2, "总数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(39, 2, "流串犯罪嫌疑人数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(40, 2, "外来人员犯罪嫌疑人数").setFont("宋体", 10, true, false,
				false, false);
		writer.addCell(41, 2, "无业人员犯罪嫌疑人数").setFont("宋体", 10, true, false,
				false, false);
		writer.addCell(42, 2, "刑释人员犯罪嫌疑人数").setFont("宋体", 10, true, false,
				false, false);
		writer.addCell(43, 2, "14-25周岁青少年犯罪嫌疑人数").setFont("宋体", 10, true,
				false, false, false);
		writer.addCell(44, 2, "总数")
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(45, 2, "黑社会性质组织数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(46, 2, "恶势力").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(48, 2, "省级警示数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(49, 2, "市级警示数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(50, 2, "县（市,区）警示数").setFont("宋体", 10, true, false,
				false, false);
		writer.addCell(52, 2, "省级挂牌数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(53, 2, "市级挂牌数").setFont("宋体", 10, true, false, false,
				false);
		writer.addCell(54, 2, "县（市,区）挂牌数").setFont("宋体", 10, true, false,
				false, false);
		// 第3列
		writer.addCell(2, 3, emphasisSafetyDetail.getInspectSum()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(3, 3, emphasisSafetyDetail.getInspectworkingTeam())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(4, 3, emphasisSafetyDetail.getInspectOther()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(5, 3, emphasisSafetyDetail.getPublicitySum()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(6, 3, emphasisSafetyDetail.getPublicityCadre()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(7, 3, emphasisSafetyDetail.getPublicityPeople())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(8, 3, emphasisSafetyDetail.getPublicityPublicNotice())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(9, 3, emphasisSafetyDetail.getPublicitySymposium())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(10, 3, emphasisSafetyDetail.getPublicityPeopleInform())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(11, 3, emphasisSafetyDetail.getPublicityCrackedCase())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(12, 3,
				emphasisSafetyDetail.getPublicityCatchingCriminal()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(13, 3, emphasisSafetyDetail.getFindAreaSum()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(14, 3, emphasisSafetyDetail.getFindAreaCounty())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(15, 3, emphasisSafetyDetail.getFindAreaStreet())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(16, 3, emphasisSafetyDetail.getFindAreaVillage())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(17, 3, emphasisSafetyDetail.getFindAreaOther()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(18, 3, emphasisSafetyDetail.getFindAreaVicious())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(19, 3, emphasisSafetyDetail.getFindAreaViolence())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(20, 3, emphasisSafetyDetail.getFindAreaRob()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(21, 3, emphasisSafetyDetail.getFindAreaPoison())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(22, 3, emphasisSafetyDetail.getFindAreaHeresy())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(23, 3, emphasisSafetyDetail.getFindAreaOtherType())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(24, 3, emphasisSafetyDetail.getAlreadyRenovateSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(25, 3, emphasisSafetyDetail.getAlreadyRenovateCounty())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(26, 3, emphasisSafetyDetail.getAlreadyRenovateStreet())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(27, 3, emphasisSafetyDetail.getAlreadyRenovateVillage())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(28, 3, emphasisSafetyDetail.getAlreadyRenovateOther())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(29, 3, emphasisSafetyDetail.getNowRenovateSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(30, 3, emphasisSafetyDetail.getNowRenovateCounty())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(31, 3, emphasisSafetyDetail.getNowRenovateStreet())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(32, 3, emphasisSafetyDetail.getNowRenovateVillage())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(33, 3, emphasisSafetyDetail.getNowRenovateOther())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(34, 3, emphasisSafetyDetail.getStrikeCrackedSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(35, 3, emphasisSafetyDetail.getStrikeViolence())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(36, 3, emphasisSafetyDetail.getStrikeRob()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(37, 3, emphasisSafetyDetail.getStrikePoison()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(38, 3, emphasisSafetyDetail.getStrikeArrestSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(39, 3, emphasisSafetyDetail.getStrikeFlow()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(40, 3, emphasisSafetyDetail.getStrikeOutside()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(41, 3, emphasisSafetyDetail.getStrikeUnemployed())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(42, 3, emphasisSafetyDetail.getStrikePenal()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(43, 3, emphasisSafetyDetail.getStrikeJuvenile())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(44, 3, emphasisSafetyDetail.getStrikeAttackSum())
				.setFont("宋体", 10, true, false, false, false);
		writer.addCell(45, 3, emphasisSafetyDetail.getStrikeGangdom()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(46, 3, emphasisSafetyDetail.getStrikeDadness()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(47, 3, emphasisSafetyDetail.getCautionSum()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(48, 3, emphasisSafetyDetail.getCautionCity()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(49, 3, emphasisSafetyDetail.getCautionLand()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(50, 3, emphasisSafetyDetail.getCautionCounty()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(51, 3, emphasisSafetyDetail.getBrandSum()).setFont("宋体",
				10, true, false, false, false);
		writer.addCell(52, 3, emphasisSafetyDetail.getBrandCity()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(53, 3, emphasisSafetyDetail.getBrandLand()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(54, 3, emphasisSafetyDetail.getBrandCounty()).setFont(
				"宋体", 10, true, false, false, false);
		writer.addCell(
				55,
				0,
				"签发人 : "
						+ (emphasisSafetyDetail.getDealPerson() != null ? emphasisSafetyDetail
								.getDealPerson() : "")
						+ "                         制表人 : "
						+ (emphasisSafetyDetail.getLister() != null ? emphasisSafetyDetail
								.getLister() : "")).mergeTo(55, 3)
				.setFont("宋体", 10, true, false, false, false);

	}

	private ExcelWriter constructExcelWriter() throws Exception {
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		return writer;
	}

	@Action(value = "popedomReports", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String popedomReports() throws Exception {
		try {
			dailyDirectory = dailyDirectoryService
					.getSimpleDailyDirectoryById(dailyDirectory.getId());
			dailyYear = dailyYearService.getSimpleDailyYearById(dailyDirectory
					.getDailyYear().getId());
			Date dealDate = DateUtil.parseDate(dailyYear.getYearDate() + "-"
					+ "01-01", "yyyy-MM-dd");
			popedomReports = reportWorkingRecordService
					.findAllReportWorkingRecordByOrgIdAndYear(ThreadVariable
							.getUser().getOrganization().getId(), dealDate,
							dailyDirectory.getId(), dailyYear.getId());
			for (int i = 0; i < popedomReports.length; i++) {
				PopedomEmphasisSafetyReportSituation emphasisSafetyReportSituation = new PopedomEmphasisSafetyReportSituation();
				emphasisSafetyReportSituation.setOrgId(popedomReports[i][0]);
				emphasisSafetyReportSituation.setOrgName(popedomReports[i][1]);
				emphasisSafetyReportSituation.setJanuary(popedomReports[i][2]);
				emphasisSafetyReportSituation.setFebruary(popedomReports[i][3]);
				emphasisSafetyReportSituation.setMarch(popedomReports[i][4]);
				emphasisSafetyReportSituation.setApril(popedomReports[i][5]);
				emphasisSafetyReportSituation.setMay(popedomReports[i][6]);
				emphasisSafetyReportSituation.setJune(popedomReports[i][7]);
				emphasisSafetyReportSituation.setJuly(popedomReports[i][8]);
				emphasisSafetyReportSituation.setAugust(popedomReports[i][9]);
				emphasisSafetyReportSituation
						.setSeptember(popedomReports[i][10]);
				emphasisSafetyReportSituation.setOctober(popedomReports[i][11]);
				emphasisSafetyReportSituation
						.setNovember(popedomReports[i][12]);
				emphasisSafetyReportSituation
						.setDecember(popedomReports[i][13]);
				popedomEmphasisSafetyReportSituation
						.add(emphasisSafetyReportSituation);
			}
			PageInfo<PopedomEmphasisSafetyReportSituation> pageInfo = new PageInfo<PopedomEmphasisSafetyReportSituation>();
			pageInfo.setResult(popedomEmphasisSafetyReportSituation);
			pageInfo.setTotalRowSize(popedomReports.length);
			pageInfo.setPerPageSize(100);
			gridPage = new GridPage(pageInfo);

		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	/*** 上报治安重点整治 */
	@PermissionFilter(ename = "upWorkingRecord")
	@Action(value = "reportEmphasisSafetyDetail", results = {
			@Result(type = "json", name = "success", params = { "root",
					"emphasisSafetyDetail", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String reportEmphasisSafetyDetail() throws Exception {
		List<PropertyDict> reportTypeDict = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.WORKING_RECORD_SUBMITSTATE,
						WorkingRecordSubmitstate.HAS_SUBMIT);

		if (validateObjectIsNull(emphasisSafetyDetail)) {
			return ERROR;
		}

		emphasisSafetyDetail = emphasisSafetyDetailService.updateSubmitState(
				emphasisSafetyDetail.getId(), reportTypeDict.get(0).getId(),
				emphasisSafetyDetail.getExpiredEntering());

		emphasisSafetyDetail = emphasisSafetyDetailService
				.updateEmphasisSafetyDetail(validateWhenAdd(emphasisSafetyDetail));
		if (validateObjectIsNull(emphasisSafetyDetail)) {
			return ERROR;
		}
		return SUCCESS;
	}

	private EmphasisSafetyDetail validateWhenAdd(
			EmphasisSafetyDetail emphasisSafetyDetail) {
		if (emphasisSafetyDetail != null
				&& emphasisSafetyDetail.getDailyDirectory() != null
				&& emphasisSafetyDetail.getDailyDirectory().getId() != null) {
			DailyDirectory dailyDirectory = dailyDirectoryService
					.getFullDailyDirectoryById(emphasisSafetyDetail
							.getDailyDirectory().getId());
			ThreadVariable.getUser().getOrganization().getId();

			Long reportTime = emphasisSafetyDetail.getReportTime();
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

			Date dealDate = emphasisSafetyDetail.getSubmitTime();// ThreadVariable.getSession().getAccessTime();
			if (dealDate != null && endDate != null) {
				if (dealDate.after(endDate)) {
					emphasisSafetyDetail.setExpiredEntering(1L);// 1超期录入
				} else {
					emphasisSafetyDetail.setExpiredEntering(0L);
				}
			}
		}
		return emphasisSafetyDetail;
	}

	/** 治安重点整治催报 矛盾纠纷排查催报用的也是这个方法 */
	@Action(value = "addDailyLogWarmMessage", results = { @Result(type = "json", name = "success", params = {
			"root", "bol", "ignoreHierarchy", "false" }) })
	public String addDailyLogWarmMessage() throws Exception {

		String[] subInfos = ids.split(",");

		for (String subInfo : subInfos) {

			if ("".equals(subInfo)) {
				continue;
			}

			String[] orgAndTime = subInfo.split("_");

			sendPlatformMessageToOrg(orgAndTime[1], Long.valueOf(orgAndTime[0]));
		}

		bol = true;

		return SUCCESS;
	}

	private void sendPlatformMessageToOrg(String time, Long orgId) {

		PlatformMessage pm = platformMessageFactory
				.createStatedReportsRushToPlatformMessage(time, objectType,
						modeType, orgId);

		platformaMessageService.sendPlatformMessageToOrg(orgId, pm);
	}

	/**
	 * 报表回退
	 */
	@PermissionFilter(ename = "backWorkingRecord")
	@Action(value = "reportBack", results = {
			@Result(type = "json", name = "success", params = { "root",
					"emphasisSafetyDetail", "ignoreHierarchy", "false" }),
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
		emphasisSafetyDetail = emphasisSafetyDetailService.backWorkingRecord(
				dailyLogId, propertyDict.get(0).getId());

		PropertyDict reportTypeDict = propertyDictService
				.getPropertyDictByDomainNameAndDictId(
						PropertyTypes.WORKING_RECORD_SUBMITSTATE,
						emphasisSafetyDetail.getSubmitState().getId());

		emphasisSafetyDetail.setSubmitState(reportTypeDict);

		List<User> users = permissionService.findChildUsersByEnameAndOrgCode(
				"myWorkingRecordManagement", emphasisSafetyDetail
						.getOrganization().getId().toString());

		if (null != users && users.size() > 0) {
			platformMessage = platformMessageFactory
					.createStatedReportsBackPlatformMessage(platformMessage);
			for (User user : users) {
				platformMessage.setReceiverId(user.getId());
				platformaMessageService
						.sendPlatformMessageToUser(platformMessage);
			}

		}

		return SUCCESS;
	}

	private boolean validateObjectIsNull(EmphasisSafetyDetail obj) {
		if (null == obj || null == obj.getId()) {
			this.errorMessage = "操作失败，请联系管理员";
			return true;
		}
		return false;
	}

	@Action(value = "popedomReportQuarter", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String popedomReportQuarter() throws Exception {
		List<PopedomEmphasisSafetyforQuarter> peoEmphasisSafetyforQuarterList = new ArrayList<PopedomEmphasisSafetyforQuarter>();
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory.getId());
		dailyYear = dailyYearService.getSimpleDailyYearById(dailyDirectory
				.getDailyYear().getId());
		Date dealDate = DateUtil.parseDate(dailyYear.getYearDate() + "-"
				+ "01-01", "yyyy-MM-dd");
		popedomReports = reportWorkingRecordService
				.findAllReportWorkingRecordByOrgIdAndYearForQuarter(
						ThreadVariable.getUser().getOrganization().getId(),
						dealDate, dailyDirectory.getId(), dailyYear.getId());
		for (int i = 0; i < popedomReports.length; i++) {
			PopedomEmphasisSafetyforQuarter peoEmphasisSafetyforQuarter = new PopedomEmphasisSafetyforQuarter();
			peoEmphasisSafetyforQuarter.setOrgId(popedomReports[i][0]);
			peoEmphasisSafetyforQuarter.setOrgName(popedomReports[i][1]);
			peoEmphasisSafetyforQuarter.setFirstQuarter(popedomReports[i][2]);
			peoEmphasisSafetyforQuarter.setSecondQuarter(popedomReports[i][3]);
			peoEmphasisSafetyforQuarter.setThirdQuarter(popedomReports[i][4]);
			peoEmphasisSafetyforQuarter.setFourthQuarter(popedomReports[i][5]);
			peoEmphasisSafetyforQuarterList.add(peoEmphasisSafetyforQuarter);
		}
		PageInfo<PopedomEmphasisSafetyforQuarter> pageInfo = new PageInfo<PopedomEmphasisSafetyforQuarter>();
		pageInfo.setResult(peoEmphasisSafetyforQuarterList);
		pageInfo.setTotalRowSize(popedomReports.length);
		pageInfo.setPerPageSize(100);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Action(value = "popedomReportforHalf", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String popedomReportforHalf() throws Exception {
		List<PopedomEmphasisSafetyforSixMonths> popedomEmphasisSafetyforSixMonthList = new ArrayList<PopedomEmphasisSafetyforSixMonths>();
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory.getId());
		dailyYear = dailyYearService.getSimpleDailyYearById(dailyDirectory
				.getDailyYear().getId());
		Date dealDate = DateUtil.parseDate(dailyYear.getYearDate() + "-"
				+ "01-01", "yyyy-MM-dd");
		popedomReports = reportWorkingRecordService
				.findAllReportWorkingRecordByOrgIdAndYearForHalf(ThreadVariable
						.getUser().getOrganization().getId(), dealDate,
						dailyDirectory.getId(), dailyYear.getId());
		for (int i = 0; i < popedomReports.length; i++) {
			PopedomEmphasisSafetyforSixMonths emphasisSafetyReportSituation = new PopedomEmphasisSafetyforSixMonths();
			emphasisSafetyReportSituation.setOrgId(popedomReports[i][0]);
			emphasisSafetyReportSituation.setOrgName(popedomReports[i][1]);
			emphasisSafetyReportSituation.setFirstHalf(popedomReports[i][2]);
			emphasisSafetyReportSituation.setSecondHalf(popedomReports[i][3]);
			popedomEmphasisSafetyforSixMonthList
					.add(emphasisSafetyReportSituation);
		}
		PageInfo<PopedomEmphasisSafetyforSixMonths> pageInfo = new PageInfo<PopedomEmphasisSafetyforSixMonths>();
		pageInfo.setResult(popedomEmphasisSafetyforSixMonthList);
		pageInfo.setTotalRowSize(popedomReports.length);
		pageInfo.setPerPageSize(100);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	private void encodeUTF() {
		try {
			emphasisSafetyDetail.setName(URLDecoder.decode(
					emphasisSafetyDetail.getName(), "UTF-8"));
			emphasisSafetyDetail
					.setLister(emphasisSafetyDetail.getLister() != null ? URLDecoder
							.decode(emphasisSafetyDetail.getLister(), "UTF-8")
							: null);
			emphasisSafetyDetail.setDealPerson(emphasisSafetyDetail
					.getDealPerson() != null ? URLDecoder.decode(
					emphasisSafetyDetail.getDealPerson(), "UTF-8") : null);
		} catch (UnsupportedEncodingException e) {
			logger.error("组装数据错误：", e);
		}
	}

	@Action(value = "popedomReportforYear", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String popedomReportforYear() throws Exception {
		List<PopedomEmphasisSafetyforYesr> popedomEmphasisSafetyforSixMonthList = new ArrayList<PopedomEmphasisSafetyforYesr>();
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory.getId());
		dailyYear = dailyYearService.getSimpleDailyYearById(dailyDirectory
				.getDailyYear().getId());
		Date dealDate = DateUtil.parseDate(dailyYear.getYearDate() + "-"
				+ "01-01", "yyyy-MM-dd");
		popedomReports = reportWorkingRecordService
				.findAllReportWorkingRecordByOrgIdAndYearForYear(ThreadVariable
						.getUser().getOrganization().getId(), dealDate,
						dailyDirectory.getId(), dailyYear.getId());
		for (int i = 0; i < popedomReports.length; i++) {
			PopedomEmphasisSafetyforYesr peEmphasisSafetyforYesr = new PopedomEmphasisSafetyforYesr();
			peEmphasisSafetyforYesr.setOrgId(popedomReports[i][0]);
			peEmphasisSafetyforYesr.setOrgName(popedomReports[i][1]);
			peEmphasisSafetyforYesr.setYears(popedomReports[i][2]);
			popedomEmphasisSafetyforSixMonthList.add(peEmphasisSafetyforYesr);
		}
		PageInfo<PopedomEmphasisSafetyforYesr> pageInfo = new PageInfo<PopedomEmphasisSafetyforYesr>();
		pageInfo.setResult(popedomEmphasisSafetyforSixMonthList);
		pageInfo.setTotalRowSize(popedomReports.length);
		pageInfo.setPerPageSize(100);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@PermissionFilter(ename = "addWorkingRecord")
	@Action(value = "addEmphasisSafetyDetail", results = {
			@Result(type = "json", name = "success", params = { "root",
					"emphasisSafetyDetail", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addEmphasisSafetyDetail() throws Exception {
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

		encodeUTF();
		emphasisSafetyDetail.setOrganization(ThreadVariable.getUser()
				.getOrganization());
		emphasisSafetyDetail.setDailyYear(dailyYear);
		emphasisSafetyDetail.setDailyDirectory(dailyDirectory);
		ReportWorkingRecord record = reportWorkingRecordService
				.summarizingJudge(emphasisSafetyDetail.getReportTime(),
						dailyDirectory.getId(), emphasisSafetyDetail
								.getOrganization().getId());
		if (null == record) {
			emphasisSafetyDetail = emphasisSafetyDetailService
					.addEmphasisSafetyDetail(emphasisSafetyDetail);
			emphasisSafetyDetail.setOrganization(organizationDubboService
					.getSimpleOrgById(emphasisSafetyDetail.getOrganization()
							.getId()));
		} else {
			return ERROR;
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateWorkingRecord")
	@Action(value = "updateEmphasisSafetyDetail", results = {
			@Result(type = "json", name = "success", params = { "root",
					"emphasisSafetyDetail", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasisSafetyDetail() throws Exception {
		encodeUTF();
		emphasisSafetyDetail.setOrganization(ThreadVariable.getUser()
				.getOrganization());
		emphasisSafetyDetail = emphasisSafetyDetailService
				.updateEmphasisSafetyDetail(emphasisSafetyDetail);
		return SUCCESS;
	}

	@PermissionFilter(ename = "deleteWorkingRecord")
	@Action(value = "deleteEmphasisSafetyDetail", results = {
			@Result(type = "json", name = "success", params = { "root", "bol",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteEmphasisSafetyDetail() throws Exception {
		if (null != detailedRuleDailyLogRelaService
				.getDetailedRuleDailyLogRelaByWorkingRecordId(emphasisSafetyDetail
						.getId())) {
			bol = false;
			return ERROR;
		}
		emphasisSafetyDetailService
				.deleteEmphasisSafetyDetail(emphasisSafetyDetail.getId());
		bol = true;
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/daily/statementsReported/publicSecurityRenovate/maintainEmphasisSafetyDlg.jsp"),
			@Result(name = "print", location = "/daily/statementsReported/publicSecurityRenovate/maintainEmphasisSafetyPrintDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		sysTime = Calendar.getInstance().get(Calendar.YEAR);
		this.prepareExcute();
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
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
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			prepareUpdateEmphasisSafetyDetail();
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			viewEmphasisSafetyDetail();
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			viewDailyLog();
			return "print";
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			if (emphasisSafetyDetail == null
					|| emphasisSafetyDetail.getId() == null) {
				errorMessage = "参数错误";
				return ERROR;
			}
			emphasisSafetyDetail = emphasisSafetyDetailService
					.getEmphasisSafetyDetailById(emphasisSafetyDetail.getId());
			return "print";
		}
		return SUCCESS;
	}

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

	private void viewDailyLog() {
		getActivityWorkingRecodeDetail();
	}

	private void viewEmphasisSafetyDetail() {
		getActivityWorkingRecodeDetail();
	}

	private void getActivityWorkingRecodeDetail() {
		try {
			emphasisSafetyDetail = new EmphasisSafetyDetail(
					reportWorkingRecordService
							.getReportWorkingRecordById(emphasisSafetyDetail
									.getId()));
			emphasisSafetyDetail.setOrganization(organizationDubboService
					.getFullOrgById(emphasisSafetyDetail.getOrganization()
							.getId()));
			dailyDirectory = dailyDirectoryService
					.getSimpleDailyDirectoryById(emphasisSafetyDetail
							.getDailyDirectory().getId());
			dailyYear = dailyYearService.getSimpleDailyYearById(dailyDirectory
					.getDailyYear().getId());
			reportTime = emphasisSafetyDetail.getReportTime();

			if (reportTypeInternalId == 0) {
				reportTypeInternalId = dailyDirectory.getIndexId();
			}
			getTime(reportTime);
			if (reportTypeInternalId == 0) {
				PropertyDict propertyDict = propertyDictService
						.getPropertyDictByDomainNameAndDictId(
								PropertyTypes.DIRECTORY_REPORT_TYPE,
								dailyDirectory.getDirectoryReportType().getId());
				reportTypeInternalId = propertyDict.getInternalId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prepareExcute() {
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
			return;
		}
		if (propertyDict.getInternalId() == OrganizationLevel.COUNTRY) {
			orgLevle = OrganizationLevel.COUNTRY;
		} else if (propertyDict.getInternalId() == OrganizationLevel.PROVINCE) {
			orgLevle = OrganizationLevel.PROVINCE;
		} else if (propertyDict.getInternalId() == OrganizationLevel.CITY) {
			orgLevle = OrganizationLevel.CITY;
		} else if (propertyDict.getInternalId() == OrganizationLevel.DISTRICT) {
			orgLevle = OrganizationLevel.DISTRICT;
		} else if (propertyDict.getInternalId() == OrganizationLevel.TOWN) {
			orgLevle = OrganizationLevel.TOWN;
		} else if (propertyDict.getInternalId() == OrganizationLevel.VILLAGE) {
			orgLevle = OrganizationLevel.VILLAGE;
		} else if (propertyDict.getInternalId() == OrganizationLevel.GRID) {
			orgLevle = OrganizationLevel.GRID;
		} else
			orgLevle = OrganizationLevel.VILLAGE;
	}

	private void prepareUpdateEmphasisSafetyDetail() {
		getActivityWorkingRecodeDetail();
	}

	@Action(value = "emphasisSafetyDetailList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String emphasisSafetyDetailList() throws Exception {
		if (null == dailyDirectoryId || null == organization
				|| null == organization.getId()) {
			gridPage = new GridPage();
		}
		gridPage = new GridPage(
				reportWorkingRecordService
						.findReportWorkingRecordForPageByOrgInternalCode(
								organization.getId(), page, rows, sidx, sord,
								dailyDirectoryId));
		return SUCCESS;
	}

	@Action(value = "judgeReportCondition", results = {
			@Result(type = "json", name = "success", params = { "root", "bol",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "bol",
					"ignoreHierarchy", "false" }) })
	public String judgeReportCondition() throws Exception {
		emphasisSafetyDetail = emphasisSafetyDetailService
				.getEmphasisSafetyDetailById(emphasisSafetyDetail.getId());
		if (emphasisSafetyDetail != null
				&& (emphasisSafetyDetail.getDealPerson() != null || !emphasisSafetyDetail
						.getDealPerson().equals(""))) {
			bol = true;
			return SUCCESS;
		} else {
			bol = false;
			return ERROR;
		}
	}

	@Action(value = "getEmphasisSafetyDetailByReportTime", results = {
			@Result(type = "json", name = "success", params = { "root",
					"emphasisSafetyDetail", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getEmphasisSafetyDetailByReportTime() throws Exception {
		emphasisSafetyDetail = emphasisSafetyDetailService.reportSummarizing(
				reportTime, dailyDirectory.getId(), ThreadVariable.getUser()
						.getOrganization().getId());

		return SUCCESS;
	}

	@Action(value = "sunReportJudge", results = {
			@Result(type = "json", name = "success", params = { "root", "bol",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "bol",
					"ignoreHierarchy", "false" }) })
	public String sunReportJudge() throws Exception {
		if (emphasisSafetyDetailService.sunReportJudge(reportTime,
				dailyDirectory.getId(), ThreadVariable.getUser()
						.getOrganization().getId())) {
			bol = true;
			return SUCCESS;
		}
		return ERROR;
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

	public Long getDailyDirectoryId() {
		return dailyDirectoryId;
	}

	public void setDailyDirectoryId(Long dailyDirectoryId) {
		this.dailyDirectoryId = dailyDirectoryId;
	}

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	public EmphasisSafetyDetail getEmphasisSafetyDetail() {
		return emphasisSafetyDetail;
	}

	public void setEmphasisSafetyDetail(
			EmphasisSafetyDetail emphasisSafetyDetail) {
		this.emphasisSafetyDetail = emphasisSafetyDetail;
	}

	public DailyYear getDailyYear() {
		return dailyYear;
	}

	public void setDailyYear(DailyYear dailyYear) {
		this.dailyYear = dailyYear;
	}

	public Integer getOrgLevle() {
		return orgLevle;
	}

	public void setOrgLevle(Integer orgLevle) {
		this.orgLevle = orgLevle;
	}

	public int getReportTypeInternalId() {
		return reportTypeInternalId;
	}

	public void setReportTypeInternalId(int reportTypeInternalId) {
		this.reportTypeInternalId = reportTypeInternalId;
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

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
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

	public String getReportedType() {
		return reportedType;
	}

	public void setReportedType(String reportedType) {
		this.reportedType = reportedType;
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
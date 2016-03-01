package com.tianque.fourTeams.platformMessage.factory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.DateUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Evaluate;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.domain.property.StatementsReportedType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;
import com.tianque.platformMessage.constants.PlatformMessageSendType;
import com.tianque.platformMessage.constants.PlatformMessageType;
import com.tianque.platformMessage.constants.ReceiverType;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.resourcePool.domain.MyProfile;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.util.TemplateConfiguration;
import com.tianque.working.domain.DailyYear;
import com.tianque.working.domain.Document;
import com.tianque.working.domain.ReportWorkingRecord;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component("fourTeamsPlatformMessageFactory")
public class FourTeamsPlatformMessageFactory {

	private static Logger logger = LoggerFactory
			.getLogger(FourTeamsPlatformMessageFactory.class);
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private TemplateConfiguration templateConfiguration;

	/***
	 * 待办事项提醒消息
	 * 
	 * @return pm
	 */
	public PlatformMessage createNeedDoIssuePlatformMessage(
			FourTeamsIssueNew issue) {

		String title = getGlobalValue(GlobalSetting.NEED_DO_ISSUEPM_TITLE)
				.replace("【事件名称】", issue.getSubject()).replace("【服务单号】",
						issue.getSerialNumber());

		String content = getGlobalValue(GlobalSetting.NEED_DO_ISSUEPM_CONTENT)
				.replace("【事件名称】", issue.getSubject()).replace("【服务单号】",
						issue.getSerialNumber());

		String url = getGlobalValue(GlobalSetting.NEED_DO_MYISSUELIST_URL);

		PlatformMessage pm = new PlatformMessage(title, content, url,
				ReceiverType.ORG, PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.NEED_DO_ISSUE_REMIND);
		return pm;
	}

	/***
	 * 重大事项提醒消息
	 * 
	 * @return pm
	 */
	public PlatformMessage createSeriousIssuePlatformMessage(
			FourTeamsIssueNew issue) {

		String title = getGlobalValue(GlobalSetting.SERIOUS_ISSUE_TITLE)
				.replace("【事件名称】", issue.getSubject()).replace("【服务单号】",
						issue.getSerialNumber());

		String content = getGlobalValue(GlobalSetting.SERIOUS_ISSUE_CONTENT)
				.replace("【事件发生时间】", getTimeStr(issue.getOccurDate()))
				.replace("【发生地点】", issue.getOccurOrg().getOrgName())
				.replace("【事件名称】", issue.getSubject())
				.replace("【服务单号】", issue.getSerialNumber())
				.replace("【涉及人数】", issue.getRelatePeopleCount() + "");

		// url = getGlobalValue(GlobalSetting.SERIOUS_ISSUE_URL);

		PlatformMessage pm = new PlatformMessage(title, content,
				ReceiverType.ORG, PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.SERIOUS_ISSUE_REMIND);
		return pm;
	}

	/***
	 * 事件信息反馈提醒消息
	 * 
	 * @param issue
	 * @param issueOper
	 * @param tatgetOrg
	 * @return pm
	 */
	public PlatformMessage createIssueTipPlatformMessage(
			FourTeamsIssueNew issue, FourTeamsIssueOperate issueOper,
			FourTeamsIssueStepGroup steps) {

		String currentOrgName = getCurrentOrg().getOrgName();
		Organization tatgetOrg = null;
		if (steps != null) {
			tatgetOrg = organizationDubboService.getSimpleOrgById(steps
					.getKeyStep().getTarget().getId());
		}

		String title = getGlobalValue(GlobalSetting.ISSUEPM_TITLE).replace(
				"【服务单号】", issue.getSerialNumber()).replace("【事件名称】",
				issue.getSubject());

		String content = "";

		if (FourTeamsIssueOperate.COMPLETE.equals(issueOper)) {

			content = getGlobalValue(GlobalSetting.ISSUEPM_CONTENT)
					.replace("【服务单号】", issue.getSerialNumber())
					.replace("【事件名称】", issue.getSubject())
					.replace("【处理部门名称】", currentOrgName)
					.replace("【处理时间】", getTimeStr())
					.replace("【操作类型】", issueOper.toString());
			content = content.substring(0, content.lastIndexOf("于"))
					+ "，请进行打分评价。";
		} else {
			content = getGlobalValue(GlobalSetting.ISSUEPM_CONTENT)
					.replace("【服务单号】", issue.getSerialNumber())
					.replace("【事件名称】", issue.getSubject())
					.replace("【处理部门名称】", currentOrgName)
					.replace("【处理时间】", getTimeStr())
					.replace("【操作类型】", issueOper.toString())
					.replace("【目标部门名称】", tatgetOrg.getOrgName());
			if (steps != null && steps.hasIncidentalStep()) {
				StringBuilder sb = new StringBuilder();
				for (FourTeamsIssueStep step : steps.getIncidentalSteps()) {
					sb.append("、").append(
							organizationDubboService.getSimpleOrgById(
									step.getTarget().getId()).getOrgName());
				}
				content = content + "并抄告给" + sb.toString().substring(1);
			}
		}

		PlatformMessage pm = new PlatformMessage(
				title,
				content,
				FourTeamsIssueOperate.COMPLETE.getDesc().equals(
						issueOper.getDesc()) ? getGlobalValue(GlobalSetting.MYISSUELIST_URL)
						+ "Complete"
						: null, ReceiverType.ORG,
				PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.ISSUE_FEEDBACK_REMIND);
		return pm;
	}

	/**
	 * 工作台帐启用提醒消息
	 * 
	 * @param dailyYear
	 * @return pm
	 */
	public PlatformMessage createStartDailyYearPlatformMessage(
			DailyYear dailyYear) {

		Organization org = organizationDubboService.getSimpleOrgById(dailyYear
				.getMakedOrganization().getId());

		String title = getGlobalValue(GlobalSetting.START_DAILY_YEAR_PM_TITLE)
				.replace("【台帐年份】", dailyYear.getYearDate().toString()).replace(
						"【台帐创建部门名称】", org.getOrgName());

		String content = getGlobalValue(
				GlobalSetting.START_DAILY_YEAR_PM_CONTENT).replace("【台帐年份】",
				dailyYear.getYearDate().toString()).replace("【台帐创建部门名称】",
				org.getOrgName());

		String url = getGlobalValue(GlobalSetting.START_DAILY_YEAR_URL);

		PlatformMessage pm = new PlatformMessage(title, content, url,
				ReceiverType.ORG, PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.DAILYDIRECTORY_START_REMIND);

		return pm;
	}

	// 自动启动台帐目录
	public PlatformMessage createAutoStartDailyYearPlatformMessage(
			DailyYear dailyYear) {

		Organization org = organizationDubboService.getSimpleOrgById(dailyYear
				.getMakedOrganization().getId());

		String title = getGlobalValue(GlobalSetting.START_DAILY_YEAR_PM_TITLE)
				.replace("【台帐年份】", dailyYear.getYearDate().toString()).replace(
						"【台帐创建部门名称】", org.getOrgName());

		String content = getGlobalValue(
				GlobalSetting.START_DAILY_YEAR_PM_CONTENT).replace("【台帐年份】",
				dailyYear.getYearDate().toString()).replace("【台帐创建部门名称】",
				org.getOrgName());

		String url = getGlobalValue(GlobalSetting.START_DAILY_DIRECTORY_URL);

		PlatformMessage pm = new PlatformMessage(title, content, url,
				ReceiverType.ORG, PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.DAILYDIRECTORY_START_REMIND);

		return pm;
	}

	// 手动启动台帐目录
	public PlatformMessage createManuallyStartDailyYearPlatformMessage(
			DailyYear dailyYear) {

		Organization org = organizationDubboService.getSimpleOrgById(dailyYear
				.getMakedOrganization().getId());

		String title = getGlobalValue(
				GlobalSetting.WILL_START_DAILY_YEAR_PM_CONTENT).replace(
				"【台帐年份】", dailyYear.getYearDate().toString()).replace(
				"【台帐创建部门名称】", org.getOrgName());

		String content = getGlobalValue(
				GlobalSetting.WILL_START_DAILY_YEAR_PM_CONTENT).replace(
				"【台帐年份】", dailyYear.getYearDate().toString()).replace(
				"【台帐创建部门名称】", org.getOrgName());

		String url = getGlobalValue(GlobalSetting.START_DAILY_DIRECTORY_URL);

		PlatformMessage pm = new PlatformMessage(title, content, url,
				ReceiverType.ORG, PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.DAILYDIRECTORY_START_REMIND);

		return pm;
	}

	/***
	 * 治安重点整治和矛盾纠纷排查的月报、季报、年报上报提醒消息
	 */
	public PlatformMessage createStatedReportSubmitPlatformMessage(
			ReportWorkingRecord record) {

		String title = getGlobalValue(
				GlobalSetting.STATED_REPORTS_REPORT_PM_TITLE).replace("【报表名称】",
				record.getName());

		String content = getGlobalValue(
				GlobalSetting.STATED_REPORTS_REPORT_PM_TITLE).replace("【报表名称】",
				record.getName()).replace("【上报时间】",
				getTimeStr(record.getSubmitTime()));

		String url = getGlobalValue(GlobalSetting.STATED_REPORTS_REPORT_URL);

		PlatformMessage pm = new PlatformMessage(title, content, url,
				ReceiverType.ORG, PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.STATED_REPORT_REPORT_REMIND);
		return pm;
	}

	/**
	 * 治安重点整治和矛盾纠纷排查的月报、季报、年报催报提醒消息
	 * 
	 * @param month
	 * @param objectType
	 * @param modeType
	 * @param orgId
	 * @return
	 */
	public PlatformMessage createStatedReportsRushToPlatformMessage(
			String time, String reportType, String modeType, Long orgId) {

		if (reportType.equals("security")) {
			reportType = "治安重点整治工作表";
		} else if (reportType.equals("social")) {
			reportType = "排查整治强基促稳情况";
		} else if (reportType.equals("issueDeal")) {
			reportType = "矛盾纠纷排查工作表";
		}

		String nowYear = CalendarUtil.getNowYear() + "年";

		if (modeType.equals("month")) {
			time = nowYear + time + "月";
		} else if (modeType.equals("quarter")) {
			time = nowYear + createQuarter(time);
		} else if (modeType.equals("half")) {
			time = nowYear + createHalf(time);
		} else if (modeType.equals("year")) {
			time = nowYear;
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);

		String reportName = time + org.getOrgName() + reportType;

		String title = getGlobalValue(
				GlobalSetting.STATED_REPORTS_RUSHTO_PM_TITLE).replace("【报表名称】",
				reportName);

		String content = getGlobalValue(
				GlobalSetting.STATED_REPORTS_RUSHTO_PM_CONTENT).replace(
				"【报表名称】", reportName);

		String url = getGlobalValue(GlobalSetting.STATED_REPORTS_RUSHTO_URL);

		PlatformMessage pm = new PlatformMessage(title, content, url,
				ReceiverType.ORG, PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.STATED_RUSHTO_REPORT_REMIND);
		return pm;
	}

	/***
	 * 治安重点整治和矛盾纠纷排查的月报、季报、年报回退提醒消息
	 * 
	 * @param pm
	 * @return
	 */
	public PlatformMessage createStatedReportsBackPlatformMessage(
			PlatformMessage pm) {

		String url = getGlobalValue(GlobalSetting.STATED_REPORTS_BACK_URL);

		PlatformMessage platformMessage = new PlatformMessage(pm.getTitle(),
				pm.getContent(), url, ReceiverType.USER,
				PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.STATED_REPORT_BACK_REMIND);
		return platformMessage;
	}

	/***
	 * 治安重点排查情况、重大矛盾纠纷排查调处情况上报消息
	 * 
	 * @param size
	 * @param reportype
	 * @return
	 */
	public PlatformMessage createUnStatedReportsSubmitPlatformMessage(int size,
			int reportype) {

		Organization org = getCurrentOrg();

		String reportName = reportype == StatementsReportedType.INVESTIGATION ? StatementsReportedType.INVESTIGATION_NAME
				: StatementsReportedType.SIGNIFICANT_ISSUEDEAL_NAME;

		String title = getGlobalValue(
				GlobalSetting.UN_STATED_REPORTS_REPORT_PM_TITLE)
				.replace("【报表所属部门】", org.getOrgName())
				.replace("【数量】", size + "").replace("【报表类型】", reportName);

		String content = getGlobalValue(
				GlobalSetting.UN_STATED_REPORTS_REPORT_PM_CONTENT)
				.replace("【报表所属部门】", org.getOrgName())
				.replace("【上报时间】", getTimeStr()).replace("【数量】", size + "")
				.replace("【报表类型】", reportName);

		String url = getGlobalValue(GlobalSetting.UN_STATED_REPORTS_REPORT_URL);

		PlatformMessage pm = new PlatformMessage(title, content, url,
				ReceiverType.ORG, PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.UN_STATED_REPORT_REPORT_REMIND);
		return pm;

	}

	/***
	 * 治安重点排查情况、重大矛盾纠纷排查调处情况回退消息
	 * 
	 * @param evaluate
	 * @param user
	 * @return
	 */
	public PlatformMessage createUnStatedReportsBackPlatformMessage(
			PlatformMessage pm) {

		String url = getGlobalValue(GlobalSetting.UN_STATED_REPORTS_BACK_URL);

		PlatformMessage platformMessage = new PlatformMessage(pm.getTitle(),
				pm.getContent(), url, ReceiverType.USER,
				PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.UN_STATED_REPORT_BACK_REMIND);
		return platformMessage;
	}

	/**
	 * 考核评估启用提醒消息
	 * 
	 * @param dailyYear
	 * @return pm
	 */
	public PlatformMessage createActiveStandardEvaluatePlatformMessage(
			Evaluate evaluate) {

		String title = getGlobalValue(
				GlobalSetting.EVALUATE_ACTIVESTANDARD_PM_TITLE).replace("【年份】",
				evaluate.getYear()).replace("【考核目录名称】", evaluate.getTitle());

		String content = getGlobalValue(
				GlobalSetting.EVALUATE_ACTIVESTANDARD_PM_CONTENT).replace(
				"【年份】", evaluate.getYear()).replace("【考核目录名称】",
				evaluate.getTitle());

		String url = getGlobalValue(GlobalSetting.EVALUATE_ACTIVESTANDARD_URL);

		PlatformMessage pm = new PlatformMessage(title, content, url,
				ReceiverType.USER, PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.EVALUATE_ACTIVESTANDARD_REMIND);

		return pm;
	}

	/***
	 * 考核评估自评上报消息
	 * 
	 * @param evaluate
	 * @return
	 */
	public PlatformMessage createEvaluateReportPlatformMessage(Evaluate evaluate) {

		Organization org = organizationDubboService.getSimpleOrgById(evaluate
				.getOrganization().getId());

		String title = getGlobalValue(GlobalSetting.EVALUATE_REPORT_PM_TITLE)
				.replace("【自评部门】", org.getOrgName())
				.replace("【考核标准时间】", evaluate.getYear())
				.replace("【考核标准名称】", evaluate.getTitle());

		String content = getGlobalValue(
				GlobalSetting.EVALUATE_REPORT_PM_CONTENT)
				.replace("【自评部门】", org.getOrgName())
				.replace("【考核标准时间】", evaluate.getYear())
				.replace("【考核标准名称】", evaluate.getTitle())
				.replace("【上报时间】", getTimeStr());

		String url = getGlobalValue(GlobalSetting.EVALUATE_REPORT_URL);

		PlatformMessage pm = new PlatformMessage(title, content, url,
				ReceiverType.USER, PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.EVALUATE_REPORT_REMIND);

		return pm;
	}

	/***
	 * 考核评估催报消息
	 * 
	 * @param evaluate
	 * @param user
	 * @return
	 */
	public PlatformMessage createEvaluateUrgePlatformMessage(Evaluate evaluate,
			User user) {

		Organization org = organizationDubboService.getSimpleOrgById(user
				.getOrganization().getId());

		String title = getGlobalValue(GlobalSetting.EVALUATE_RUSHTO_PM_TITLE)
				.replace("【自评部门】", org.getOrgName())
				.replace("【考核标准时间】", evaluate.getYear())
				.replace("【考核标准名称】", evaluate.getTitle());

		String content = getGlobalValue(GlobalSetting.EVALUATE_RUSHTO_PM_CONTENT
				.replace("【自评部门】", org.getOrgName())
				.replace("【考核标准时间】", evaluate.getYear())
				.replace("【考核标准名称】", evaluate.getTitle()));

		String url = getGlobalValue(GlobalSetting.EVALUATE_RUSHTO_URL);

		PlatformMessage pm = new PlatformMessage(title, content, url,
				ReceiverType.USER, PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.EVALUATE_RUSHTO_REMIND);

		pm.setReceiverId(user.getId());

		return pm;
	}

	/***
	 * 考核评估自评回退消息
	 * 
	 * @param evaluate
	 * @return
	 */
	public PlatformMessage createEvaluateBackPlatformMessage(Evaluate evaluate) {

		Organization org = organizationDubboService.getSimpleOrgById(evaluate
				.getOrganization().getId());

		Organization parentOrg = organizationDubboService.getSimpleOrgById(org
				.getParentOrg().getId());

		String title = getGlobalValue(GlobalSetting.EVALUATE_BACK_PM_TITLE)
				.replace("【考核标准时间】", evaluate.getYear())
				.replace("【考核标准名称】", evaluate.getTitle())
				.replace("【执行回退操作的部门】", parentOrg.getOrgName());

		String content = getGlobalValue(GlobalSetting.EVALUATE_BACK_PM_CONTENT)
				.replace("【自评部门】", org.getOrgName())
				.replace("【考核标准时间】", evaluate.getYear())
				.replace("【考核标准名称】", evaluate.getTitle())
				.replace("【执行回退操作的部门】", parentOrg.getOrgName())
				.replace("【回退时间】", getTimeStr());

		String url = getGlobalValue(GlobalSetting.EVALUATE_BACK_URL);

		PlatformMessage pm = new PlatformMessage(title, content, url,
				ReceiverType.USER, PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.EVALUATE_BACK_REMIND);

		return pm;
	}

	/**
	 * 文件签收提醒消息
	 * 
	 * @param document
	 * @return
	 */
	public PlatformMessage createUrgeDocumentPlatformMessage(Document document) {

		String title = getGlobalValue(GlobalSetting.URGE_DOCUMENT_PM_TITLE)
				.replace("【文件名称】", document.getTitle());

		String content = getGlobalValue(GlobalSetting.URGE_DOCUMENT_PM_CONTENT)
				.replace("【文件名称】", document.getTitle())
				.replace("【发文单位】", getCurrentOrg().getOrgName())
				.replace("【发文时间】", getTimeStr());

		String url = getGlobalValue(GlobalSetting.RECEIVE_DOCUMENT_URL);

		PlatformMessage pm = new PlatformMessage(title, content, url,
				ReceiverType.ORG, PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.SIGN_FILE_REMIND);

		return pm;
	}

	/**
	 * 共享资料提醒
	 * 
	 * @return
	 */
	public PlatformMessage createSharInfomationPlatformMessage(
			List<MyProfile> files) {

		if (files == null || files.size() == 0) {
			throw new BusinessValidationException("参数错误：没有要共享的资料");
		}

		String title = getGlobalValue(
				GlobalSetting.SHARING_INFORMATION_PM_TITLE).replace("【共享文件数量】",
				files.size() + "");

		String fileName = getFileNames(files);

		String content = getGlobalValue(
				GlobalSetting.SHARING_INFORMATION_PM_CONTENT)
				.replace("【共享文件部门名称】", getCurrentOrg().getOrgName())
				.replace("【共享文件用户姓名】", getCurrentUser().getName())
				.replace("【共享文件名称】", fileName)
				.replace("【共享目录名称】", files.get(0).getShareDirectory().getName())
				.replace("【共享时间】", DateUtil.toString(new Date(), "yyyy-MM-dd"));

		String url = getGlobalValue(GlobalSetting.SHARING_INFORMATION_URL);

		PlatformMessage pm = new PlatformMessage(title, content, url,
				ReceiverType.ORG, PlatformMessageSendType.SYSTERM_SNED,
				PlatformMessageType.SHARED_FILE_REMIND);

		return pm;
	}

	private String getFileNames(List<MyProfile> files) {
		StringBuilder stb = new StringBuilder();
		Boolean flag = true;
		for (MyProfile domain : files) {
			if (null != domain.getName() && !domain.getName().isEmpty()) {
				if (flag) {
					stb.append("《" + domain.getName() + "》");
					flag = Boolean.FALSE;
				} else {
					stb.append("、").append("《" + domain.getName() + "》");
				}
			}
		}
		return stb.toString();
	}

	/***
	 * 社区矫正人员到期提醒
	 * 
	 * @param list
	 * @return
	 */
	public PlatformMessage createRectificativePersonPlatformMessage(
			List<RectificativePerson> list) {
		try {
			String title = getGlobalValue(
					GlobalSetting.RECTIFICATIVE_PERSON_PM_TITLE).replace(
					"【时间】", getDateStr()).replace("【人员到期数量】", list.size() + "");

			String content = getGlobalValue(
					GlobalSetting.RECTIFICATIVE_PERSON_PM_CONTENT).replace(
					"【时间】", getDateStr()).replace("【人员到期数量】", list.size() + "");

			content += getTemplateContent(list,
					GridProperties.RECTIFICATIVE_PERSON_MESSAGE_TEMPLATE);

			String url = getGlobalValue(GlobalSetting.RECTIFICATIVE_PERSON_URL);

			PlatformMessage pm = new PlatformMessage(title, content, url,
					ReceiverType.ORG, PlatformMessageSendType.SYSTERM_SNED,
					PlatformMessageType.RECTIFICATIVE_PERSON_REMIND);

			return pm;

		} catch (Exception e) {

			return null;
		}
	}

	/***
	 * 刑释解教人员到期提醒
	 * 
	 * @param list
	 * @return
	 */
	public PlatformMessage createPositiveInfoPlatformMessage(
			List<PositiveInfo> list) {
		try {
			String title = getGlobalValue(GlobalSetting.POSITIVEINFO_PM_TITLE)
					.replace("【时间】", getDateStr()).replace("【人员到期数量】",
							list.size() + "");

			String content = getGlobalValue(
					GlobalSetting.POSITIVEINFO_PM_CONTENT).replace("【时间】",
					getDateStr()).replace("【人员到期数量】", list.size() + "");

			content += getTemplateContent(list,
					GridProperties.POSITIVEINFO_MESSAGE_TEMPLATE);

			String url = getGlobalValue(GlobalSetting.POSITIVEINFO_URL);

			PlatformMessage pm = new PlatformMessage(title, content, url,
					ReceiverType.ORG, PlatformMessageSendType.SYSTERM_SNED,
					PlatformMessageType.POSITIVEINFO_REMIND);

			return pm;

		} catch (Exception e) {

			logger.error("", e);

			return null;
		}
	}

	/***
	 * 重点青少年到期提醒消息
	 * 
	 * @param list
	 * @return
	 */
	public PlatformMessage createIdleYouthPlatformMessage(List<IdleYouth> list) {

		try {
			String title = getGlobalValue(GlobalSetting.IDLEYOUTH_PM_TITLE)
					.replace("【时间】", getDateStr()).replace("【人员到期数量】",
							list.size() + "");

			String content = getGlobalValue(GlobalSetting.IDLEYOUTH_PM_CONTENT)
					.replace("【时间】", getDateStr()).replace("【人员到期数量】",
							list.size() + "");

			content += getTemplateContent(list,
					GridProperties.IDLEYOUTHM_ESSAGE_TEMPLATE);

			String url = getGlobalValue(GlobalSetting.IDLEYOUTH_URL);

			PlatformMessage pm = new PlatformMessage(title, content, url,
					ReceiverType.ORG, PlatformMessageSendType.SYSTERM_SNED,
					PlatformMessageType.IDLEYOUTH_REMIND);

			return pm;

		} catch (Exception e) {

			logger.error("", e);

			return null;
		}

	}

	/***
	 * 实口标记为老年人提醒消息
	 * 
	 * @param list
	 * @return
	 */
	public PlatformMessage createElderlyPeoplePlatformMessage(
			List<ActualPopulation> list, String actualtype) {

		try {
			String title = getGlobalValue(GlobalSetting.ELDERLY_PEOPLE_PM_TITLE)
					.replace("【时间】", getDateStr())
					.replace("【人员到期数量】", list.size() + "")
					.replace("【实口类型】", actualtype);

			String content = getGlobalValue(
					GlobalSetting.ELDERLY_PEOPLE_PM_CONTENT)
					.replace("【时间】", getDateStr())
					.replace("【人员到期数量】", list.size() + "")
					.replace("【实口类型】", actualtype);

			content += getTemplateContent(list,
					GridProperties.ELDERLY_PEOPLE_MESSAGE_TEMPLATE);

			String url = getGlobalValue(GlobalSetting.ELDERLY_PEOPLE_URL);

			PlatformMessage pm = new PlatformMessage(title, content, url,
					ReceiverType.ORG, PlatformMessageSendType.SYSTERM_SNED,
					PlatformMessageType.ELDERLY_PEOPLE_REMIND);

			return pm;

		} catch (Exception e) {

			logger.error("", e);

			return null;
		}

	}

	/**
	 * 流动人口到期后消息提醒
	 * 
	 * @param list
	 * @param actualtype
	 * @return
	 */
	public PlatformMessage createEndFloatingPopulationPlatformMessage(
			List<ActualPopulation> list, String actualtype) {
		try {
			String title = getGlobalValue(
					GlobalSetting.FLOATINGPOPULATION_PEOPLE_PM_TITLE)
					.replace("【时间】", getDateStr())
					.replace("【人员到期数量】", list.size() + "")
					.replace("【实口类型】", actualtype);

			String content = getGlobalValue(
					GlobalSetting.FLOATINGPOPULATION_PEOPLE_PM_COUNT)
					.replace("【时间】", getDateStr())
					.replace("【人员到期数量】", list.size() + "")
					.replace("【实口类型】", actualtype);

			content += getTemplateContent(list,
					GridProperties.FLOATINGPOPULATION_PEOPLE_MESSAGE_TEMPLATE);

			String url = getGlobalValue(GlobalSetting.FLOATINGPOPULATION_PEOPLE_URL);

			PlatformMessage pm = new PlatformMessage(title, content, url,
					ReceiverType.ORG, PlatformMessageSendType.SYSTERM_SNED,
					PlatformMessageType.FLOATINGPOPULATION_PEOPLE_REMIND);
			return pm;

		} catch (Exception e) {

			logger.error("", e);

			return null;
		}

	}

	/***
	 * 实口标记为育龄妇女提醒消息
	 * 
	 * @param list
	 * @return
	 */
	public PlatformMessage createNurturesWomenformMessage(
			List<ActualPopulation> list, String actualtype) {

		try {
			String title = getGlobalValue(GlobalSetting.NURTURES_WOMEN_PM_TITLE)
					.replace("【时间】", getDateStr())
					.replace("【人员到期数量】", list.size() + "")
					.replace("【实口类型】", actualtype);

			String content = getGlobalValue(
					GlobalSetting.NURTURES_WOMEN_PM_CONTENT)
					.replace("【时间】", getDateStr())
					.replace("【人员到期数量】", list.size() + "")
					.replace("【实口类型】", actualtype);

			content += getTemplateContent(list,
					GridProperties.NURTURES_WOMEN_MESSAGE_TEMPLATE);

			String url = getGlobalValue(GlobalSetting.NURTURES_WOMEN_URL);

			PlatformMessage pm = new PlatformMessage(title, content, url,
					ReceiverType.ORG, PlatformMessageSendType.SYSTERM_SNED,
					PlatformMessageType.NURTURES_WOMEN_REMIND);

			return pm;

		} catch (Exception e) {

			logger.error("", e);

			return null;
		}

	}

	private String getTemplateContent(List list, String templateName)
			throws IOException, TemplateException {

		Template template = templateConfiguration.getTemplate(templateName);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("list", list);

		StringWriter out = new StringWriter();

		template.process(map, out);

		return out.getBuffer().toString();
	}

	private User getCurrentUser() {

		return permissionService.getSimpleUserById(ThreadVariable.getSession()
				.getUserId());
	}

	private Organization getCurrentOrg() {

		return organizationDubboService.getFullOrgById(getCurrentUser()
				.getOrganization().getId());
	}

	private String getGlobalValue(String key) {
		return globalSettingService.getGlobalValue(key).trim();
	}

	private String getDateStr() {
		return CalendarUtil.format("yyyy年MM月dd日", CalendarUtil.now());
	}

	private String getTimeStr() {
		return CalendarUtil.format("yyyy-MM-dd HH:mm", CalendarUtil.now())
				+ "(" + CalendarUtil.getWeekDay() + ")";
	}

	private String getTimeStr(Date date) {
		return CalendarUtil.format("yyyy-MM-dd HH:mm", date) + "("
				+ CalendarUtil.getWeekDay(date) + ")";
	}

	private String createQuarter(String quarter) {
		String quartersString = "";
		if (quarter.equals("1")) {
			quartersString = "第一季度";
		}
		if (quarter.equals("2")) {
			quartersString = "第二季度";
		}
		if (quarter.equals("3")) {
			quartersString = "第三季度";
		}
		if (quarter.equals("4")) {
			quartersString = "第四季度";
		}
		return quartersString;
	}

	private String createHalf(String half) {
		String quartersString = "";
		if (half.equals("1")) {
			quartersString = "上半年";
		}
		if (half.equals("2")) {
			quartersString = "下半年";
		}
		return quartersString;
	}

}

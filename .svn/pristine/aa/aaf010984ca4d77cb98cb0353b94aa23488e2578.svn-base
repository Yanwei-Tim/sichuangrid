package com.tianque.mobile.sysadmin.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.vo.UserMessageVo;
import com.tianque.core.util.CookieUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.issue.service.IssueService;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.sysadmin.RemindMobileAdapter;
import com.tianque.mobile.vo.Remind;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.IssueNewService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.userAuth.api.SessionManagerDubboService;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.ReceiveDocumentsService;

/**
 * 手机端获取未读信息数量和代办事项数量
 */
@Scope("request")
@Controller("remindMobileAdapter")
public class RemindMobileAdapterImpl extends BaseMobileAction implements
		RemindMobileAdapter {

	// @Autowired
	// private SmsReceivedBoxService smsReceivedBoxService;//该模块在系统中已经注释，没有再使用
	@Autowired
	private IssueNewService issueNewService;
	@Autowired
	private PlatformMessageService platformaMessageService;
	@Autowired
	private SessionManagerDubboService sessionManagerDubboService;
	@Autowired
	private PermissionService permissionService;

	@Autowired
	private ReceiveDocumentsService receiveDocumentsService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;

	private UserMessageVo msgNum;
	/** 已逾期报表 */
	private Integer overdueReports;
	/** 即将到期报表 */
	private Integer willOverdueReports;
	/** 待签收文件 */
	private Integer notsignDocs;
	/** 待阅读文件 */
	private Integer notReadDocs;

	private Remind remind;

	private Map<String, Object> remindMap;

	@Autowired
	private IssueService issueService;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	public String findRemind() throws Exception {
		remind = new Remind();
		remindMap = new HashMap<String, Object>();
		msgNum = getMessages();

		Map<String, Integer> report = dailyDirectoryService
				.statisticsReport(ThreadVariable.getSession().getOrganization()
						.getId());
		overdueReports = report.get("already");
		willOverdueReports = report.get("goingTo");
		Map<String, Object> jsonMap = receiveDocumentsService
				.caculateDocuments();
		notsignDocs = (Integer) jsonMap.get("notsignDocs");
		notReadDocs = (Integer) jsonMap.get("notReadDocs");
		remind.putCount(Remind.UNREAD_MESSAGES, msgNum.getPersonnelMessageNum());
		remind.putCount(Remind.TO_DO_TASKS, msgNum.getMyNeedDoNum());
		remind.putCount(Remind.WORKING_REPORTS, overdueReports
				+ willOverdueReports);
		remind.putCount(Remind.RECEIVE_DOCUMENTS, notsignDocs + notReadDocs);
		remind.putCount(Remind.VERIFICATION_NUM, msgNum.getMyVerification());
		remind.putCount(Remind.CHECKGRADE_NUM, msgNum.getMyCheckGrade());
		remindMap.put("remindCount", remind.getDataCount());
		return SUCCESS;
	}

	private UserMessageVo getMessages() throws Exception {
		UserMessageVo msgNum = null;
		String sessionId = CookieUtil
				.getSesssionIdFromCookies(ServletActionContext.getRequest());
		com.tianque.domain.Session session = sessionManagerDubboService
				.findSessionBySessionId(sessionId);
		if (null == session || null == session.getUserId()) {
			return null;
		}
		// int smsReceivedBoxNum =
		// smsReceivedBoxService.countUnprocessSmsReceivedBoxByOwnerId(session
		// .getUserId());
		int myNeedDoNum = issueNewService.getNeedDoCount(permissionService
				.getSimpleUserById(session.getUserId()).getOrganization()
				.getId());
		int personnelMessageNum = platformaMessageService
				.getUserUnreadPlatformMessageFromInbox(session.getUserId());

		int myVerification = 0;
		int myCheckGrade = 0;
		User user = permissionService.getSimpleUserById(session
				.getUserId());
		if (user != null && user.getOrganization() != null
				&& user.getOrganization().getId() != null) {
			Organization organization = organizationDubboService
					.getSimpleOrgById(user.getOrganization().getId());
			Long functionalOrgType = null;
			if (organization != null
					&& organization.getFunctionalOrgType() != null
					&& organization.getFunctionalOrgType().getId() != null) {
				functionalOrgType = organization.getFunctionalOrgType().getId();
			}
			if (organization != null) {
				// 统计待验证的数量
				myVerification = issueService
						.getJurisdictionsVerificationCountForViewTab(
								organization.getOrgLevel().getId(),
								organization.getId(), functionalOrgType);
				// 统计待评分的数量
				myCheckGrade = issueService
						.getJurisdictionsGradeDelayCountForViewTab(organization
								.getOrgLevel().getId(), organization.getId(),
								functionalOrgType);
			}

		}

		msgNum = new UserMessageVo();
		// msgNum.setSmsReceivedBoxNum(smsReceivedBoxNum);
		msgNum.setMyNeedDoNum(myNeedDoNum);
		msgNum.setPersonnelMessageNum(personnelMessageNum);
		msgNum.setMyVerification(myVerification);
		msgNum.setMyCheckGrade(myCheckGrade);
		// msgNum.setSmsReceivedBoxNum(smsReceivedBoxNum);
		msgNum.setMessageNum(myNeedDoNum + personnelMessageNum + myVerification
				+ myCheckGrade);
		return msgNum;
	}

	public UserMessageVo getMsgNum() {
		return msgNum;
	}

	public void setMsgNum(UserMessageVo msgNum) {
		this.msgNum = msgNum;
	}

	public Integer getOverdueReports() {
		return overdueReports;
	}

	public void setOverdueReports(Integer overdueReports) {
		this.overdueReports = overdueReports;
	}

	public Integer getWillOverdueReports() {
		return willOverdueReports;
	}

	public void setWillOverdueReports(Integer willOverdueReports) {
		this.willOverdueReports = willOverdueReports;
	}

	public Integer getNotsignDocs() {
		return notsignDocs;
	}

	public void setNotsignDocs(Integer notsignDocs) {
		this.notsignDocs = notsignDocs;
	}

	public Integer getNotReadDocs() {
		return notReadDocs;
	}

	public void setNotReadDocs(Integer notReadDocs) {
		this.notReadDocs = notReadDocs;
	}

	public Remind getRemind() {
		return remind;
	}

	public void setRemind(Remind remind) {
		this.remind = remind;
	}

	public Map<String, Object> getRemindMap() {
		return remindMap;
	}

	public void setRemindMap(Map<String, Object> remindMap) {
		this.remindMap = remindMap;
	}

}

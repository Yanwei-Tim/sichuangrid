package com.tianque.controller;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.vo.MsgNumAndProclamation;
import com.tianque.controller.vo.UserMessageVo;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CookieUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.Proclamation;
import com.tianque.domain.User;
import com.tianque.issue.service.IssueApplyDelayService;
import com.tianque.issue.service.IssueService;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.IssueNewService;
import com.tianque.service.ProclamationService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.userAuth.api.SessionManagerDubboService;

@Scope("prototype")
@Transactional
@SuppressWarnings("serial")
@Controller("userMessageController")
public class UserMessageController extends BaseAction {

	// @Autowired
	// private SmsReceivedBoxService smsReceivedBoxService;//该模块在系统中已经注释掉
	@Autowired
	private IssueNewService issueNewService;
	@Autowired
	private PlatformMessageService platformaMessageService;
	@Autowired
	private SessionManagerDubboService sessionManagerDubboService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private ProclamationService proclamationService;
	@Autowired
	private IssueApplyDelayService issueApplyDelayService;

	private Proclamation proclamation;
	private UserMessageVo msgNum;
	private MsgNumAndProclamation msgNumAndProclamation;
	@Autowired
	private IssueService issueService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public String findUserMessages() {
		msgNum = getMessagesNum();
		return SUCCESS;
	}

	public String findUserMessagesAndProclamation() {
		msgNum = getMessagesNum();
		proclamation = proclamationService.getDisplayProclamation();

		msgNumAndProclamation = new MsgNumAndProclamation();
		msgNumAndProclamation.setMsgNum(msgNum);
		msgNumAndProclamation.setProclamation(proclamation);
		return SUCCESS;
	}

	/**
	 * 用户工作台中消息提醒的统计
	 * 
	 * @return
	 */
	private UserMessageVo getMessagesNum() {
		String sessionId = CookieUtil
				.getSesssionIdFromCookies(ServletActionContext.getRequest());
		com.tianque.domain.Session session = sessionManagerDubboService
				.findSessionBySessionId(sessionId);
		if (null == session || null == session.getUserId()) {
			return null;
		}
		User user = permissionService.getSimpleUserById(session.getUserId());
		Organization organization = organizationDubboService
				.getFullOrgById(user.getOrganization().getId());
		int myNeedDoNum = 0;
		int personnelMessageNum = 0;
		if (getUserIssuePermission(session.getUserId())) {
			if (organization.getFunctionalOrgType() != null
					&& organization.getFunctionalOrgType().getId() != null) {
				myNeedDoNum = issueService.getIssueNumWorkBench(null,
						organization.getId(), page, rows, "issueId", sord,
						null, organization.getOrgLevel().getId(), "1",
						organization.getFunctionalOrgType().getId(), null,
						null, session.getUserId());
			} else {
				myNeedDoNum = issueService.getIssueNumWorkBench(null,
						organization.getId(), page, rows, "issueId", sord,
						null, organization.getOrgLevel().getId(), "1", null,
						null, null, session.getUserId());
			}
		}
		if (user.isAdmin()
				|| permissionService
						.findUserAllPermissionByUserIdAndPermissionEname(
								session.getUserId(), "pmInboxManagement")) {
			personnelMessageNum = platformaMessageService
					.getUserUnreadPlatformMessageFromInbox(session.getUserId());
		}
		UserMessageVo msgNum = new UserMessageVo();
		msgNum.setMyNeedDoNum(myNeedDoNum);
		msgNum.setPersonnelMessageNum(personnelMessageNum);
		msgNum.setMessageNum(myNeedDoNum + personnelMessageNum);
		return msgNum;
	}

	private boolean getUserIssuePermission(Long userId) {
		return permissionService
				.findUserAllPermissionByUserIdAndPermissionEnames(userId,
						"needIssueListManagement"// 待办的权限
						, "checkPendingIssueListManagement"// 待审核的权限
						, "verificationIssueListManagement"// 待验证的权限
						, "checkGradeIssueListManagement");// 待评分的权限
	}

	private UserMessageVo getMessages() {
		String sessionId = CookieUtil
				.getSesssionIdFromCookies(ServletActionContext.getRequest());
		com.tianque.domain.Session session = sessionManagerDubboService
				.findSessionBySessionId(sessionId);
		if (null == session || null == session.getUserId()) {
			return null;
		}
		// int smsReceivedBoxNum = smsReceivedBoxService
		// .countUnprocessSmsReceivedBoxByOwnerId(session.getUserId());
		int myNeedDoNum = issueNewService.getNeedDoCount(permissionService
				.getSimpleUserById(session.getUserId()).getOrganization()
				.getId());
		int personnelMessageNum = platformaMessageService
				.getUserUnreadPlatformMessageFromInbox(session.getUserId());
		int myAuditDelayNum = issueApplyDelayService
				.getJurisdictionsAuditDelayCount(permissionService
						.getSimpleUserById(session.getUserId())
						.getOrganization().getId());
		UserMessageVo msgNum = new UserMessageVo();
		// msgNum.setSmsReceivedBoxNum(smsReceivedBoxNum);
		msgNum.setMyNeedDoNum(myNeedDoNum);
		msgNum.setPersonnelMessageNum(personnelMessageNum);
		// msgNum.setSmsReceivedBoxNum(smsReceivedBoxNum);
		msgNum.setMyAuditDelayNum(myAuditDelayNum);
		msgNum.setMessageNum(myNeedDoNum + personnelMessageNum
				+ myAuditDelayNum);
		return msgNum;
	}

	public UserMessageVo getMsgNum() {
		return msgNum;
	}

	public void setMsgNum(UserMessageVo msgNum) {
		this.msgNum = msgNum;
	}

	public Proclamation getProclamation() {
		return proclamation;
	}

	public void setProclamation(Proclamation proclamation) {
		this.proclamation = proclamation;
	}

	public MsgNumAndProclamation getMsgNumAndProclamation() {
		return msgNumAndProclamation;
	}

	public void setMsgNumAndProclamation(
			MsgNumAndProclamation msgNumAndProclamation) {
		this.msgNumAndProclamation = msgNumAndProclamation;
	}

}

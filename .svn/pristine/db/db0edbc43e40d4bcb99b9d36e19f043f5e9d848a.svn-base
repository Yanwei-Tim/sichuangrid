package com.tianque.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CookieUtil;
import com.tianque.domain.Proclamation;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.IssueNewService;
import com.tianque.service.ProclamationService;
import com.tianque.state.IssueQueryState;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.userAuth.api.SessionManagerDubboService;

@Scope("prototype")
@Transactional
@SuppressWarnings("serial")
@Controller("messagePollingController")
public class MessagePollingController extends BaseAction {
	// @Autowired
	// private SmsReceivedBoxService smsReceivedBoxService;//该模块在系统中没有使用
	@Autowired
	private IssueNewService issueNewService;
	@Autowired
	private PlatformMessageService platformaMessageService;
	@Autowired
	private SessionManagerDubboService sessionManagerDubboService;
	@Autowired
	private PermissionService permissionService;

	private Map<String, Object> msgNum = new HashMap<String, Object>();

	@Autowired
	private ProclamationService proclamationService;

	private Proclamation proclamation;

	public String findUserMessages() {
		String sessionId = CookieUtil
				.getSesssionIdFromCookies(ServletActionContext.getRequest());
		com.tianque.domain.Session session = sessionManagerDubboService
				.findSessionBySessionId(sessionId);
		if (null == session || null == session.getUserId()) {
			return SUCCESS;
		}
		// int smsReceivedBoxNum =
		// smsReceivedBoxService.countUnprocessSmsReceivedBoxByOwnerId(session
		// .getUserId());
		int myNeedDoNum = issueNewService
				.getMyNeedDoForByTargeOrgIdAndDealState(permissionService
						.getSimpleUserById(session.getUserId())
						.getOrganization().getId(), IssueQueryState.MY_NEED_DO);
		int personnelMessageNum = platformaMessageService
				.getUserUnreadPlatformMessageFromInbox(session.getUserId());
		// msgNum.put("smsReceivedBoxNum", smsReceivedBoxNum);
		msgNum.put("myNeedDoNum", myNeedDoNum);
		msgNum.put("personnelMessageNum", personnelMessageNum);
		msgNum.put("messageNum", myNeedDoNum + personnelMessageNum);
		return SUCCESS;
	}

	public String getDisplayProclamation() {
		proclamation = proclamationService.getDisplayProclamation();
		return SUCCESS;
	}

	public Map<String, Object> getMsgNum() {
		return msgNum;
	}

	public void setMsgNum(Map<String, Object> msgNum) {
		this.msgNum = msgNum;
	}

	public Proclamation getProclamation() {
		return proclamation;
	}

	public void setProclamation(Proclamation proclamation) {
		this.proclamation = proclamation;
	}
}

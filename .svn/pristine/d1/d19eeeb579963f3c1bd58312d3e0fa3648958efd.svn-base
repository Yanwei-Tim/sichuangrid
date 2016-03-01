package com.tianque.issue.event.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.util.DateUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Contacter;
import com.tianque.domain.IssueType;
import com.tianque.domain.Organization;
import com.tianque.domain.SmsSendBox;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.SmsMessageMarkType;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.event.IssueChangeEvent;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueStepGroup;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.factory.PlatformMessageFactory;
import com.tianque.service.IssueTypeService;
import com.tianque.service.SmsMessageFunService;
import com.tianque.service.SmsMessageMarkService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.userAuth.api.ContacterDubboService;

/**
 * 发消息监听器
 */
public abstract class SendMessageListener extends NothingDoIssueChangeListener {
	@Autowired
	protected OrganizationDubboService organizationService;
	@Autowired
	protected IssueTypeService issueTypeService;
	@Autowired
	private PlatformMessageFactory platformMessageFactory;
	@Autowired
	private SmsMessageMarkService smsMessageMarkService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private ContacterDubboService contacterDubboService;
	@Autowired
	private SmsMessageFunService smsMessageFunService;
	/** 短信功能是否开启，为1的时候开启 */
	public final static int IS_OPENSMS = 1;
	public final static int DEFAULE_STATUS = 1;

	protected List<IssueType> getImportantTypes() {
		List<IssueType> importantTypes = issueTypeService
				.findIssueTypesByParentName(IssueTypes.CONTRADICTION);
		importantTypes.addAll(issueTypeService
				.findIssueTypesByParentName(IssueTypes.SECURITYTROUBLE));
		return importantTypes;
	}

	protected PlatformMessage getSeriousIssueMessage(IssueNew issue) {

		return platformMessageFactory.createSeriousIssuePlatformMessage(issue);
	}

	protected abstract void sendMessageToOrg(Organization target,
			PlatformMessage pm);

	protected abstract void sendSMSingToOrg(SmsSendBox smsSendBox,
			List<Contacter> contacters);

	protected boolean isSeriousIssue(IssueNew issue) {
		List<IssueType> types = getImportantTypes();
		List<IssueType> issueTypes = new ArrayList<IssueType>();
		issueTypes.add(issue.getIssueType());
		types.retainAll(issueTypes);
		return types.size() > 0;
	}

	@Override
	public void onEntry(IssueNew issue, IssueStep step) {
		if (issue.getIsEmergency() == null || !issue.getIsEmergency()
				|| issue.getImportant() == null || !issue.getImportant()
				|| issue.getRelatePeopleCount() == null) {
			return;
		}
		if (isSeriousIssue(issue) && issue.getRelatePeopleCount() >= 50) {

			PlatformMessage message = getSeriousIssueMessage(issue);

			Organization messageTarget = null;
			if (issue.getOccurOrg().getOrgLevel().getInternalId() >= OrganizationLevel.TOWN) {
				messageTarget = issue.getOccurOrg();
			} else {
				messageTarget = organizationService.findSomeLevelParentOrg(
						issue.getOccurOrg().getId(), OrganizationLevel.TOWN);
			}
			if (messageTarget != null) {
				sendMessageToOrg(messageTarget, message);
			}
			if (issue.getRelatePeopleCount() >= 100 && messageTarget != null
					&& messageTarget.getParentOrg() != null) {
				messageTarget = organizationService
						.getSimpleOrgById(messageTarget.getParentOrg().getId());
				sendMessageToOrg(messageTarget, message);
			}
			if (issue.getRelatePeopleCount() >= 200 && messageTarget != null
					&& messageTarget.getParentOrg() != null) {
				messageTarget = organizationService
						.getSimpleOrgById(messageTarget.getParentOrg().getId());
				sendMessageToOrg(messageTarget, message);
			}
		}
	}

	@Override
	protected boolean currentOrgChanged(IssueChangeEvent event) {
		return IssueOperate.SUBMIT.equals(event.getOperate())
				|| IssueOperate.ASSIGN.equals(event.getOperate())
				|| IssueOperate.REPORT_TO.equals(event.getOperate())
				|| IssueOperate.GIVETO.equals(event.getOperate())
				|| IssueOperate.BACK.equals(event.getOperate())
				|| IssueOperate.NORMAL_SUPERVISE.equals(event.getOperate())
				|| IssueOperate.YELLOW_SUPERVISE.equals(event.getOperate())
				|| IssueOperate.RED_SUPERVISE.equals(event.getOperate())
				|| IssueOperate.CANCEL_SUPERVISE.equals(event.getOperate())
				|| IssueOperate.URGENT.equals(event.getOperate())
				|| IssueOperate.COMPLETE.equals(event.getOperate())
				|| IssueOperate.CANCEL_URGENT.equals(event.getOperate())
				|| IssueOperate.INSTRUCT.equals(event.getOperate());
	}

	@Override
	public void onChanged(IssueNew issue, IssueStepGroup steps,
			IssueChangeEvent event) {
		if (!currentOrgChanged(event)) {
			return;
		}
		if (IssueOperate.NORMAL_SUPERVISE.equals(event.getOperate())
				|| IssueOperate.YELLOW_SUPERVISE.equals(event.getOperate())
				|| IssueOperate.RED_SUPERVISE.equals(event.getOperate())
				|| IssueOperate.CANCEL_SUPERVISE.equals(event.getOperate())
				|| IssueOperate.URGENT.equals(event.getOperate())
				|| IssueOperate.CANCEL_URGENT.equals(event.getOperate())
				|| IssueOperate.INSTRUCT.equals(event.getOperate())) {
			// 给要处理该事件的目标部门发送平台消息（督办）
			sendMessageToOrg(steps.getKeyStep().getTarget(),
					getPm(issue, event.getOperate(), steps));
		} else {
			// 给新增该事件的部门发送平台消息
			sendMessageToOrg(issue.getCreateOrg(),
					getPm(issue, event.getOperate(), steps));

			PlatformMessage pm = platformMessageFactory
					.createNeedDoIssuePlatformMessage(issue);
			// 给要处理该事件的目标部门发送平台消息
			sendMessageToOrg(steps.getKeyStep().getTarget(), pm);
			// 给要处理该事件的目标部门发送短信消息
			sendSMSingToOrg(event.getOperate(), steps);
		}
	}

	@Override
	public void onVerification(IssueNew issue, IssueStep step,
			IssueChangeEvent event) {
		// 验证时给创建该事件的部门发送平台消息
		sendMessageToOrg(issue.getCreateOrg(),
				getPm(issue, event.getOperate(), null));
		// 给要处理该事件的目标部门发送短信消息
		sendSMSingToOrg(event.getOperate(), step);
	}

	private PlatformMessage getPm(IssueNew issue, IssueOperate issueOper,
			IssueStepGroup steps) {

		return platformMessageFactory.createIssueTipPlatformMessage(issue,
				issueOper, steps);

	}

	/**
	 * 判断发短信功能是否开启，并获取短信模板的格式
	 * 
	 * @param operate
	 * @param steps
	 */
	private void sendSMSingToOrg(IssueOperate operate, IssueStep step) {
		// 当前用户所在市级的组织机构信息，用于判断是否开启了短信功能
		List<Organization> org = organizationService
				.findProvinceOrganizationsByOrgIdAndCityLevel(ThreadVariable
						.getOrganization().getId(), OrganizationType.CITY_LEVEL);
		for (Organization organization : org) {
			if (GridProperties.IS_SMSMESSAGE
					// 广元市和测试市事件是发短信
					// && (organization.getId() == 14155l ||
					// organization.getId() == 62528l)
					&& smsMessageFunService
							.getSimpleSmsMessageFunByOrgCode(organization
									.getOrgInternalCode()) != null
					&& IS_OPENSMS == smsMessageFunService
							.getSimpleSmsMessageFunByOrgCode(
									organization.getOrgInternalCode())
							.getIsOpenSms()) {
				String content = "";
				// 验证
				if ((IssueOperate.VERIFICATION.getCode()) == (operate.getCode())
						&& smsMessageMarkService
								.getSimpleSmsMessageMarkByDealType(SmsMessageMarkType.VERIFICATION
										.getCode()) != null) {
					content = smsMessageMarkService
							.getSimpleSmsMessageMarkByDealType(
									SmsMessageMarkType.VERIFICATION.getCode())
							.getModel();
				}
				if (!"".equals(content) && content != null) {
					assembleObject(step.getTarget(), content);
				}
			}
		}
	}

	/**
	 * 判断发短信功能是否开启，并获取短信模板的格式
	 * 
	 * @param operate
	 * @param steps
	 */
	private void sendSMSingToOrg(IssueOperate operate, IssueStepGroup steps) {
		// 当前用户所在市级的组织机构信息，用于判断是否开启了短信功能
		List<Organization> org = organizationService
				.findProvinceOrganizationsByOrgIdAndCityLevel(ThreadVariable
						.getOrganization().getId(), OrganizationType.CITY_LEVEL);
		for (Organization organization : org) {
			if (GridProperties.IS_SMSMESSAGE
					// 广元市和测试市事件是发短信
					// && (organization.getId() == 14155l ||
					// organization.getId() == 62528l)
					&& smsMessageFunService
							.getSimpleSmsMessageFunByOrgCode(organization
									.getOrgInternalCode()) != null
					&& IS_OPENSMS == smsMessageFunService
							.getSimpleSmsMessageFunByOrgCode(
									organization.getOrgInternalCode())
							.getIsOpenSms()) {
				String content = "";
				// 交办
				if ((IssueOperate.ASSIGN.getCode()) == (operate.getCode())
						&& smsMessageMarkService
								.getSimpleSmsMessageMarkByDealType(SmsMessageMarkType.ASSIGN
										.getCode()) != null) {
					content = smsMessageMarkService
							.getSimpleSmsMessageMarkByDealType(
									SmsMessageMarkType.ASSIGN.getCode())
							.getModel();
				} else if ((IssueOperate.SUBMIT.getCode()) == (operate
						.getCode())
						&& smsMessageMarkService
								.getSimpleSmsMessageMarkByDealType(SmsMessageMarkType.SUBMIT
										.getCode()) != null) {// 上报

					content = smsMessageMarkService
							.getSimpleSmsMessageMarkByDealType(
									SmsMessageMarkType.SUBMIT.getCode())
							.getModel();
				} else if ((IssueOperate.COMPLETE.getCode()) == (operate
						.getCode())
						&& smsMessageMarkService
								.getSimpleSmsMessageMarkByDealType(SmsMessageMarkType.COMPLETE
										.getCode()) != null) { // 结案
					content = smsMessageMarkService
							.getSimpleSmsMessageMarkByDealType(
									SmsMessageMarkType.COMPLETE.getCode())
							.getModel();
				} else if ((IssueOperate.BACK.getCode()) == (operate.getCode())
						&& smsMessageMarkService
								.getSimpleSmsMessageMarkByDealType(SmsMessageMarkType.BACK
										.getCode()) != null) {// 回退
					content = smsMessageMarkService
							.getSimpleSmsMessageMarkByDealType(
									SmsMessageMarkType.BACK.getCode())
							.getModel();
				}
				if (!"".equals(content) && content != null) {
					assembleObject(steps.getKeyStep().getTarget(), content);
				}
			}
		}
	}

	/**
	 * 组装短信的对象
	 * 
	 * @param target
	 * @param content
	 */
	private void assembleObject(Organization target, String content) {
		SmsSendBox smsSendBox = new SmsSendBox();
		List<Contacter> contacters = new ArrayList<Contacter>();
		List<Long> contacterIds = new ArrayList<Long>();
		List<User> userList = permissionService
				.findUsersByOrgId(target.getId());
		User currentUser = ThreadVariable.getUser();
		for (User user : userList) {
			Contacter contacter = contacterDubboService.findUserContacters(user
					.getId());
			if (null != contacter) {
				contacters.add(contacter);
				contacterIds.add(contacter.getId());
			}
		}
		String contentReplace = content.replace("#time#",
				DateUtil.formateYMDHMS(new Date()));
		smsSendBox.setSmsContent(contentReplace);
		smsSendBox.setSendUser(currentUser);
		smsSendBox.setSendMobile(currentUser.getMobile());
		smsSendBox.setSender(currentUser.getUserName());
		smsSendBox.setCreateDate(new Date());
		smsSendBox.setReceiver(StringUtil.toString(contacterIds, ","));
		smsSendBox.setContacts(contacters);
		smsSendBox.setSendStatus(DEFAULE_STATUS);
		sendSMSingToOrg(smsSendBox, contacters);
	}
}

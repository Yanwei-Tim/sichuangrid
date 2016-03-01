package com.tianque.issue.service.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.DateUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issue.constants.IssueApplyDelayState;
import com.tianque.issue.dao.IssueApplyDelayDao;
import com.tianque.issue.dao.IssueDao;
import com.tianque.issue.dao.IssueProcessDao;
import com.tianque.issue.domain.IssueApplyDelay;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueSkiprule;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.factory.IssueServiceFactory;
import com.tianque.issue.service.IssueApplyDelayService;
import com.tianque.issue.service.IssueService;
import com.tianque.issue.service.IssueSkipruleService;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.uitl.IssueToCMSUtil;
import com.tianque.platformMessage.constants.PlatformMessageSendType;
import com.tianque.platformMessage.constants.PlatformMessageType;
import com.tianque.platformMessage.constants.ReceiverType;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.tqSearch.common.TqSolrSearchCommonOperate;
import com.tianque.tqSearch.constant.TqSolrSearchOperateType;
import com.tianque.tqSearch.convert.IssueSolrDocumentConvert;

/**
 * @ClassName: IssueApplyDelayServiceImpl
 * @Description: 申请延期服务接口实现类
 * @author wangxiaohu wsmalltiger@163.com
 * @date Nov 25, 2013 3:59:42 PM
 */
@Transactional
@Service("issueApplyDelayService")
public class IssueApplyDelayServiceImpl implements IssueApplyDelayService {
	@Autowired
	private IssueApplyDelayDao issueApplyDelayDao;
	@Autowired
	private IssueProcessDao issueProcessDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private IssueServiceFactory issueServiceFactory;
	@Autowired
	private IssueSkipruleService issueSkipruleService;
	@Autowired
	private PlatformMessageService platformMessageService;
	@Autowired
	private IssueService issueService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private IssueDao issueDao;
	@Autowired
	private TqSolrSearchCommonOperate tqSolrSearchCommonOperate;

	private final static Long NO_SEARCH_ORG = null;// 事件快速检索时候，未选择组织机构
	private final static int ORG_CODE_FIND_LEVEL = 0;// 事件快速检索时候，未选择组织机构
	private final static String NO_SEARCH_ORGCODE = "";// 事件快速检索时候，未选择组织机构

	@Override
	public IssueApplyDelay applyDelay(IssueApplyDelay issueApplyDelay) {
		try {
			IssueNew issue = issueService.getFullIssueByStepId(issueApplyDelay
					.getIssueStepId());
			if (StringUtil.isStringAvaliable(issue.getFromSerialNumber())) {
				MultipartEntity data = new MultipartEntity();
				data.addPart("demandsCode",
						getParam(issue.getFromSerialNumber()));
				data.addPart("transactionDate", getParam(new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(new Date())));
				Organization organization = ThreadVariable.getOrganization();
				data.addPart("orgName", getParam(organization.getOrgName()));
				data.addPart("ordId", getParam(organization.getId() + ""));
				data.addPart("dealType", getParam("1401"));
				User user = ThreadVariable.getUser();
				data.addPart("dealUserName", getParam(user.getName()));
				data.addPart("mobile", getParam(user.getMobile()));
				data.addPart("dealDescription", getParam("申请延期"));
				data.addPart("content",
						getParam("申请延期（" + issueApplyDelay.getDelayWorkday()
								+ "天）"));
				IssueToCMSUtil.post(
						GridProperties.CMS_CALLCENTER_ISSUE_TRANSACTION_LOG,
						data);
				IssueToCMSUtil
						.post(GridProperties.CMS_CALLCENTER_ISSUE_PONE_APPLY
								+ "?demandsCode=" + issue.getFromSerialNumber()
								+ "&applyDate="
								+ issueApplyDelay.getDelayWorkday());
				issueProcessDao.updateDelayStateOrDelayDateByIssueStepId(
						issueApplyDelay.getIssueStepId(),
						IssueApplyDelayState.APPIYING, null);
				IssueStep step = issueProcessDao
						.getSimpleIssueStepById(issueApplyDelay
								.getIssueStepId());
				tqSolrSearchCommonOperate.commonOperate(
						IssueSolrDocumentConvert.createIssueSolrDocument(issue,
								step), TqSolrSearchOperateType.ADD_OR_UPDATE);
				issueApplyDelay.setId(1l);
			} else {
				checkIssueApplyDelay(issueApplyDelay);
				issueApplyDelay.setApplyUser(ThreadVariable.getUser());
				autoFillAuditOrg(issueApplyDelay);
				issueApplyDelay.setAuditState(IssueApplyDelayState.APPIYING);
				issueApplyDelay = issueApplyDelayDao
						.saveApplyDelay(issueApplyDelay);
				issueProcessDao.updateDelayStateOrDelayDateByIssueStepId(
						issueApplyDelay.getIssueStepId(),
						IssueApplyDelayState.APPIYING, null);
				// 延期发消息给审核部门
				sendPlatformMessageToOrgForApply(issueApplyDelay, issue);
				IssueStep step = issueProcessDao
						.getSimpleIssueStepById(issueApplyDelay
								.getIssueStepId());
				tqSolrSearchCommonOperate.commonOperate(
						IssueSolrDocumentConvert.createIssueSolrDocument(issue,
								step), TqSolrSearchOperateType.ADD_OR_UPDATE);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("延期申请失败", e);
		}
		return issueApplyDelay;
	}

	private StringBody getParam(String arg) throws UnsupportedEncodingException {
		return new StringBody(arg, Charset.forName("UTF-8"));
	}

	private void autoFillAuditOrg(IssueApplyDelay issueApplyDelay) {
		IssueNew issueNew = issueServiceFactory.getIssueServiceBySeries(
				IssueServiceFactory.DEFAULT_SERIES).getFullIssueByStepId(
				issueApplyDelay.getIssueStepId());
		IssueStep step = issueServiceFactory.getIssueServiceBySeries(
				IssueServiceFactory.DEFAULT_SERIES).getIssueStepById(
				issueApplyDelay.getIssueStepId());
		if (step.getEmergencyLevel() == null) {
			issueApplyDelay.setAuditOrg(ThreadVariable.getOrganization()
					.getParentOrg());
		} else {
			IssueSkiprule issueSkiprule = issueSkipruleService
					.getIssueSkipruleByIssue(issueNew, step);
			if (issueSkiprule == null) {
				issueApplyDelay.setAuditOrg(ThreadVariable.getOrganization()
						.getParentOrg());
			} else {
				Organization org = new Organization();
				org.setId(issueSkiprule.getSubmitOrgId());
				issueApplyDelay.setAuditOrg(org);
			}
		}
	}

	private void checkIssueApplyDelay(IssueApplyDelay issueApplyDelay) {
		if (issueApplyDelay == null || issueApplyDelay.getIssueStepId() == null) {
			throw new BusinessValidationException("参数错误");
		}
	}

	@Override
	public PageInfo<IssueApplyDelay> findIssueDelayList(Long issueStepId,
			Integer page, Integer rows, String sidx, String sord) {
		PageInfo<IssueApplyDelay> pageInfo = issueApplyDelayDao
				.findIssueDelayList(issueStepId, page, rows, sidx, sord);
		if (pageInfo == null || pageInfo.getResult() == null
				|| pageInfo.getResult().size() == 0) {
			return pageInfo;
		}
		IssueStep istep = issueService.getIssueStepById(issueStepId);
		int SuperviseLevel = IssueState.NORMAL_SUPERVISE;
		if (istep != null) {
			SuperviseLevel = istep.getSuperviseLevel();
		}
		for (IssueApplyDelay issueApplyDelay : pageInfo.getResult()) {
			if (issueApplyDelay.getApplyUser() != null) {
				issueApplyDelay.setApplyUser(permissionService
						.getSimpleUserById(issueApplyDelay.getApplyUser()
								.getId()));
			}
			if (issueApplyDelay.getAuditUser() != null) {
				issueApplyDelay.setAuditUser(permissionService
						.getSimpleUserById(issueApplyDelay.getAuditUser()
								.getId()));
			}
			if (IssueState.RED_CARD_SUPERVISE == SuperviseLevel) {
				fillFailureDelay(issueApplyDelay);
			}

		}
		return pageInfo;
	}

	// 对于已被红牌督办步骤的，设置审核状态为已失效
	private void fillFailureDelay(IssueApplyDelay issueApplyDelay) {
		if (((Integer) IssueApplyDelayState.APPIYING).equals(issueApplyDelay
				.getAuditState())) {
			issueApplyDelay.setAuditState(IssueApplyDelayState.APPLY_FAILURE);
		}
	}

	@Override
	public IssueApplyDelay getIssueApplyDelayByIssueStepIdAndDelayState(
			Long issueStepId) {
		return issueApplyDelayDao
				.getIssueApplyDelayByIssueStepIdAndDelayState(issueStepId);
	}

	@Override
	public IssueApplyDelay auditDelay(IssueApplyDelay issueApplyDelay) {
		try {
			resetPermitDelayWorkday(issueApplyDelay);
			issueApplyDelay.setAuditUser(ThreadVariable.getUser());
			issueProcessDao.updateDelayStateOrDelayDateByIssueStepId(
					issueApplyDelay.getIssueStepId(),
					issueApplyDelay.getAuditState(),
					issueApplyDelay.getPermitDelayWorkday());
			IssueNew issueNew = sendPlatformMessageToOrgForAudit(issueApplyDelay);
			if (issueNew != null
					&& new Integer(IssueApplyDelayState.APPLY_NOT_PASSING)
							.equals(issueApplyDelay.getAuditState())
					&& StringUtil.isStringAvaliable(issueNew
							.getFromSerialNumber())) {
				IssueToCMSUtil
						.post(GridProperties.CMS_CALLCENTER_ISSUE_PONE_APPLY
								+ "?demandsCode="
								+ issueNew.getFromSerialNumber()
								+ "&applyDate="
								+ issueApplyDelay.getPermitDelayWorkday());
			}
			IssueNew issue = issueService.getFullIssueByStepId(issueApplyDelay
					.getIssueStepId());
			IssueStep step = issueProcessDao
					.getSimpleIssueStepById(issueApplyDelay.getIssueStepId());
			tqSolrSearchCommonOperate.commonOperate(IssueSolrDocumentConvert
					.createIssueSolrDocument(issue, step),
					TqSolrSearchOperateType.ADD_OR_UPDATE);
		} catch (Exception e) {
			throw new ServiceValidationException("延期审核出错", e);
		}
		return issueApplyDelayDao.auditDelay(issueApplyDelay);
	}

	private void resetPermitDelayWorkday(IssueApplyDelay issueApplyDelay) {
		if (issueApplyDelay.getAuditState() == null
				|| issueApplyDelay.getAuditState() == IssueApplyDelayState.APPLY_PASSING) {
			issueApplyDelay.setPermitDelayWorkday(0);
		}
	}

	@Override
	public int getJurisdictionsAuditDelayCount(Long orgLevel, Long orgId,
			Long functionalOrgType) {
		if (null == orgId || orgLevel == null) {
			throw new BusinessValidationException("参数错误");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		if (org == null) {
			throw new BusinessValidationException("参数错误");
		}
		return issueDao.getJurisdictionsAuditDelayCount(orgLevel, org,
				functionalOrgType, ORG_CODE_FIND_LEVEL, NO_SEARCH_ORG,
				NO_SEARCH_ORGCODE, null);
	}

	@Override
	public int getOverseerIssueCountForMobile(Long orgLevel, Long orgId,
			Long functionalOrgType) {
		if (null == orgId || orgLevel == null) {
			throw new BusinessValidationException("参数错误");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		if (org == null) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (orgLevel != null) {
			map.put("functionalOrgType", functionalOrgType);
		}
		map.put("orgCode", org.getOrgInternalCode());
		map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
		map.put("seachValue", "present");
		return issueDao.getJurisdictionsNeedDoCountForOverseerForMobile(map);
	}

	@Override
	public int getJurisdictionsNeedDoCountForMobile(Long orgLevel, Long orgId,
			Long functionalOrgType) {
		if (null == orgId || orgLevel == null) {
			throw new BusinessValidationException("参数错误");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		if (org == null) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (orgLevel != null) {
			map.put("functionalOrgType", functionalOrgType);
		}
		map.put("orgCode", org.getOrgInternalCode());
		map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
		map.put("seachValue", "present");
		return issueDao.getJurisdictionsNeedDoCount(map);
	}

	@Override
	public int getJurisdictionsAuditDelayCount(Long orgId) {
		return issueDao.getJurisdictionsAuditDelayCount(orgId);
	}

	// 延期申请平台消息
	private void sendPlatformMessageToOrgForApply(
			IssueApplyDelay issueApplyDelay, IssueNew issue) {
		try {
			PlatformMessage pm = new PlatformMessage();
			User applyUser = permissionService.getFullUserById(issueApplyDelay
					.getApplyUser().getId());
			Organization applyOrg = organizationDubboService
					.getFullOrgById(applyUser.getOrganization().getId());
			Organization auditOrg = organizationDubboService
					.getFullOrgById(issueApplyDelay.getAuditOrg().getId());
			String title = issue.getSubject() + "事件(" + issue.getSerialNumber()
					+ ")延期申请提醒!";
			String content = issue.getSubject() + "事件("
					+ issue.getSerialNumber() + ")于"
					+ DateUtil.formateYMDHMS(issueApplyDelay.getApplyDate())
					+ "由" + applyOrg.getOrgName() + "用户" + applyUser.getName()
					+ "(" + applyUser.getUserName() + ")向"
					+ auditOrg.getOrgName() + "提出为期"
					+ issueApplyDelay.getDelayWorkday() + "天的延期申请。";
			pm.setTitle(title);
			pm.setContent(content);
			pm.setReceiverType(ReceiverType.ORG);
			pm.setSendType(PlatformMessageSendType.SYSTERM_SNED);
			pm.setMessageType(PlatformMessageType.ISSUE_FEEDBACK_REMIND);
			platformMessageService.sendPlatformMessageToOrg(issueApplyDelay
					.getAuditOrg().getId(), pm);
		} catch (Exception e) {
			throw new ServiceValidationException("发送平台消息出错", e);
		}
	}

	// 延期审核平台消息
	private IssueNew sendPlatformMessageToOrgForAudit(
			IssueApplyDelay issueApplyDelay) {
		try {
			IssueApplyDelay oldIssueApplyDelay = issueApplyDelayDao
					.getApplyDelayById(issueApplyDelay.getId());
			if (oldIssueApplyDelay == null) {
				throw new BusinessValidationException("延期申请记录不存在");
			}
			PlatformMessage pm = new PlatformMessage();
			IssueNew issue = issueService.getFullIssueByStepId(issueApplyDelay
					.getIssueStepId());
			User auditUser = permissionService.getFullUserById(issueApplyDelay
					.getAuditUser().getId());
			Organization auditOrg = organizationDubboService
					.getFullOrgById(auditUser.getOrganization().getId());
			User applyUser = permissionService
					.getFullUserById(oldIssueApplyDelay.getApplyUser().getId());
			String title = issue.getSubject() + "事件(" + issue.getSerialNumber()
					+ ")延期审核提醒!";
			String content = issue.getSubject() + "事件("
					+ issue.getSerialNumber() + ")的延期申请于"
					+ DateUtil.formateYMDHMS(issueApplyDelay.getAuditDate())
					+ "由" + auditOrg.getOrgName() + "("
					+ auditUser.getUserName() + ")审核("
					+ getApplyState(issueApplyDelay) + ")。";
			pm.setTitle(title);
			pm.setContent(content);
			pm.setReceiverType(ReceiverType.ORG);
			pm.setSendType(PlatformMessageSendType.SYSTERM_SNED);
			pm.setMessageType(PlatformMessageType.ISSUE_FEEDBACK_REMIND);
			platformMessageService.sendPlatformMessageToOrg(applyUser
					.getOrganization().getId(), pm);
			return issue;
		} catch (Exception e) {
			throw new ServiceValidationException("发送平台消息出错", e);
		}
	}

	private String getApplyState(IssueApplyDelay issueApplyDelay) {
		if (IssueApplyDelayState.APPLY_NOT_PASSING == issueApplyDelay
				.getAuditState()) {
			return IssueApplyDelayState.APPLY_NOT_PASSING_NAME + "  延期天数："
					+ issueApplyDelay.getPermitDelayWorkday();
		} else {
			return IssueApplyDelayState.APPLY_PASSING_NAME;
		}
	}
}

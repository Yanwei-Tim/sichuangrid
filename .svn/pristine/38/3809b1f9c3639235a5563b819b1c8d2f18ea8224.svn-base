package com.tianque.issue.event.listener;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.IssueRegisteRegradedReason;
import com.tianque.domain.vo.RegradedReason;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issue.domain.IssueAccessConfig;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.event.IssueChangeEvent;
import com.tianque.issue.service.IssueAccessConfigService;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueStepGroup;
import com.tianque.issue.uitl.IssueToCMSUtil;
import com.tianque.service.IssueLogService;
import com.tianque.service.RegradedPointService;
import com.tianque.state.RegradedType;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 给部门打分
 */
@Repository("scoreForOrganization")
public class ScoreForOrganization extends NothingDoIssueChangeListener {
	@Autowired
	private RegradedPointService regradedPointService;
	@Autowired
	private IssueAccessConfigService issueAccessConfigService;
	@Autowired
	private IssueLogService issueLogService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private RegradedReason createRegistIssueRegradedReason(IssueNew issue) {
		IssueRegisteRegradedReason result = new IssueRegisteRegradedReason();
		result.setIssueSerialNumber(issue.getSerialNumber());
		result.setContent(organizationDubboService.getSimpleOrgById(
				getCurrentLoginedOrganization().getId()).getOrgName()
				+ "的"
				+ getCurrentLoginedUserName()
				+ "于"
				+ CalendarUtil.format(CalendarUtil.today())
				+ "排查到一项事件，事件单号为："
				+ issue.getSerialNumber());
		return result;
	}

	/**
	 * 验证事件的时候，添加评分记录的“打分原因"信息
	 * 
	 * @param issue
	 * @return
	 */
	private RegradedReason createVerificationIssueRegradedReason(IssueNew issue) {
		IssueRegisteRegradedReason result = new IssueRegisteRegradedReason();
		result.setIssueSerialNumber(issue.getSerialNumber());
		result.setContent(organizationDubboService.getSimpleOrgById(
				getCurrentLoginedOrganization().getId()).getOrgName()
				+ "的"
				+ getCurrentLoginedUserName()
				+ "于"
				+ CalendarUtil.format(CalendarUtil.today())
				+ "验证一项事件，事件单号为："
				+ issue.getSerialNumber());
		return result;
	}

	/**
	 * 结案事件的时候，添加评分记录的“打分原因"信息
	 * 
	 * @param issue
	 * @return
	 */
	private RegradedReason createCompleteIssueRegradedReason(IssueNew issue) {
		IssueRegisteRegradedReason result = new IssueRegisteRegradedReason();
		result.setIssueSerialNumber(issue.getSerialNumber());
		result.setContent(organizationDubboService.getSimpleOrgById(
				getCurrentLoginedOrganization().getId()).getOrgName()
				+ "的"
				+ getCurrentLoginedUserName()
				+ "于"
				+ CalendarUtil.format(CalendarUtil.today())
				+ "办结一项事件，事件单号为："
				+ issue.getSerialNumber());
		return result;
	}

	private RegradedReason createTransferIssueRegradedReason(IssueNew issue,
			IssueOperate operate) {
		IssueRegisteRegradedReason result = new IssueRegisteRegradedReason();
		result.setIssueSerialNumber(issue.getSerialNumber());
		Long orgId = getCurrentLoginedOrganization().getId();
		String orgName = "";
		if (orgId < 0) {
			orgName = IssueToCMSUtil.getLocationOrgNameByLocationId(orgId)
					.getOrgName();
		} else {
			orgName = organizationDubboService.getSimpleOrgById(orgId).getOrgName();
		}
		result.setContent(orgName + "的" + getCurrentLoginedUserName() + "于"
				+ CalendarUtil.format(CalendarUtil.today())
				+ operate.toString() + "一项事件，事件单号为：" + issue.getSerialNumber());
		return result;
	}

	private RegradedReason createTellIssueRegradedReason(IssueNew issue) {
		IssueRegisteRegradedReason result = new IssueRegisteRegradedReason();
		result.setIssueSerialNumber(issue.getSerialNumber());
		result.setContent(organizationDubboService.getSimpleOrgById(
				getCurrentLoginedOrganization().getId()).getOrgName()
				+ "的"
				+ getCurrentLoginedUserName()
				+ "于"
				+ CalendarUtil.format(CalendarUtil.today())
				+ "抄告一项事件给该部门，事件单号为：" + issue.getSerialNumber());
		return result;
	}

	private void addRatingRecord(Organization org, RegradedReason reason,
			double point, Date applyDate, Long logId, boolean isBonusPoints) {
		try {
			if (isBonusPoints) {
				// 加分
				regradedPointService.bonusPoints(org,
						RegradedType.ASSESSMENTUSER, reason, point, applyDate,
						logId);
			} else {
				// 扣分
				regradedPointService.deductPoints(org,
						RegradedType.ASSESSMENTUSER, reason, point, applyDate,
						logId);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(e.getMessage(), e);
		}
	}

	@Override
	protected boolean currentOrgChanged(IssueChangeEvent event) {
		return IssueOperate.SUBMIT.equals(event.getOperate())
				|| IssueOperate.ASSIGN.equals(event.getOperate())
				|| IssueOperate.REPORT_TO.equals(event.getOperate())
				|| IssueOperate.GIVETO.equals(event.getOperate())
				|| IssueOperate.COMPLETE.equals(event.getOperate())
				|| IssueOperate.BACK.equals(event.getOperate());
	}

	@Override
	public void onEntry(IssueNew issue, IssueStep step) {
		try {
			IssueAccessConfig config = issueAccessConfigService
					.getIssueAccessConfig();
			if (config == null) {
				config = new IssueAccessConfig();
			}
			addRatingRecord(issue.getCreateOrg(),
					createRegistIssueRegradedReason(issue), config
							.getNormalScores().doubleValue(),
					issue.getCreateDate(), getLastIssueLogId(issue.getId()),
					Boolean.TRUE);
		} catch (Exception e) {
			throw new ServiceValidationException("添加打分记录时发生错误", e);
		}
	}

	private Long getLastIssueLogId(Long id) {
		List<IssueLogNew> issueLogs = issueLogService
				.findIssueLogsByIssueId(id);
		IssueLogNew issueLog = issueLogs.get(issueLogs.size() - 1);
		return issueLog == null ? null : issueLog.getId();
	}

	@Override
	public void onVerification(IssueNew issue, IssueStep step,
			IssueChangeEvent event) {
		try {
			if (step.getTarget() != null && step.getTarget().getId() > 0) {
				double cent = issueAccessConfigService
						.getIssueScoresConfig(IssueOperate.VERIFICATION);
				addRatingRecord(step.getTarget(),
						createVerificationIssueRegradedReason(issue), cent,
						issue.getCreateDate(),
						getLastIssueLogId(issue.getId()), Boolean.TRUE);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("添加打分记录时发生错误", e);
		}
	}

	@Override
	public void onChanged(IssueNew issue, IssueStepGroup steps,
			IssueChangeEvent event) {
		if (currentOrgChanged(event)) {
			try {
				double cent = issueAccessConfigService
						.getIssueScoresConfig(event.getOperate());
				Long logId = getLastIssueLogId(issue.getId());
				// 上报,交办的考核部门是最后一个操作的部门
				Organization TargetOrg = null;
				if (IssueOperate.SUBMIT.equals(event.getOperate())
						|| IssueOperate.ASSIGN.equals(event.getOperate())) {
					TargetOrg = organizationDubboService.getSimpleOrgById(issue
							.getLastOrg().getId());
				} else {
					TargetOrg = steps.getKeyStep().getTarget();
				}
				boolean isBonusPoints = IssueOperate.BACK.equals(event
						.getOperate()) ? Boolean.FALSE : Boolean.TRUE;
				addRatingRecord(
						TargetOrg,
						createTransferIssueRegradedReason(issue,
								event.getOperate()), cent,
						issue.getCreateDate(), logId, isBonusPoints);

				if (steps.hasIncidentalStep()) {
					for (IssueStep step : steps.getIncidentalSteps()) {
						addRatingRecord(step.getTarget(),
								createTellIssueRegradedReason(issue), event
										.getOperate().getCent(),
								issue.getCreateDate(), logId, Boolean.TRUE);
					}
				}
			} catch (Exception e) {
				throw new ServiceValidationException("添加打分记录时发生错误", e);
			}
		}
	}
}

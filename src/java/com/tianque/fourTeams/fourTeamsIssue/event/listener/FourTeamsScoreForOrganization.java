package com.tianque.fourTeams.fourTeamsIssue.event.listener;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.IssueRegisteRegradedReason;
import com.tianque.domain.vo.RegradedReason;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAccessConfig;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.event.FourTeamsIssueChangeEvent;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueAccessConfigService;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;
import com.tianque.fourTeams.service.FourTeamsIssueLogService;
import com.tianque.service.RegradedPointService;
import com.tianque.state.RegradedType;

/**
 * 给部门打分
 */
@Repository("fourTeamsScoreForOrganization")
public class FourTeamsScoreForOrganization extends
		FourTeamsNothingDoIssueChangeListener {
	@Autowired
	private RegradedPointService regradedPointService;
	@Autowired
	private FourTeamsIssueAccessConfigService issueAccessConfigService;
	@Autowired
	private FourTeamsIssueLogService issueLogService;

	private RegradedReason createRegistIssueRegradedReason(
			FourTeamsIssueNew issue) {
		IssueRegisteRegradedReason result = new IssueRegisteRegradedReason();
		result.setIssueSerialNumber(issue.getSerialNumber());
		result.setContent(getCurrentLoginedOrganization().getOrgName() + "的"
				+ getCurrentLoginedUserName() + "于"
				+ CalendarUtil.format(CalendarUtil.today()) + "排查到一项事件，事件单号为："
				+ issue.getSerialNumber());
		return result;
	}

	private RegradedReason createCompleteIssueRegradedReason(
			FourTeamsIssueNew issue) {
		IssueRegisteRegradedReason result = new IssueRegisteRegradedReason();
		result.setIssueSerialNumber(issue.getSerialNumber());
		result.setContent(getCurrentLoginedOrganization().getOrgName() + "的"
				+ getCurrentLoginedUserName() + "于"
				+ CalendarUtil.format(CalendarUtil.today()) + "办结一项事件，事件单号为："
				+ issue.getSerialNumber());
		return result;
	}

	private RegradedReason createTransferIssueRegradedReason(
			FourTeamsIssueNew issue, FourTeamsIssueOperate operate) {
		IssueRegisteRegradedReason result = new IssueRegisteRegradedReason();
		result.setIssueSerialNumber(issue.getSerialNumber());
		result.setContent(getCurrentLoginedOrganization().getOrgName() + "的"
				+ getCurrentLoginedUserName() + "于"
				+ CalendarUtil.format(CalendarUtil.today())
				+ operate.toString() + "一项事件，事件单号为：" + issue.getSerialNumber());
		return result;
	}

	private RegradedReason createTellIssueRegradedReason(FourTeamsIssueNew issue) {
		IssueRegisteRegradedReason result = new IssueRegisteRegradedReason();
		result.setIssueSerialNumber(issue.getSerialNumber());
		result.setContent(getCurrentLoginedOrganization().getOrgName() + "的"
				+ getCurrentLoginedUserName() + "于"
				+ CalendarUtil.format(CalendarUtil.today())
				+ "抄告一项事件给该部门，事件单号为：" + issue.getSerialNumber());
		return result;
	}

	private void addRatingRecord(Organization org, RegradedReason reason,
			double point, Date applyDate, Long logId) throws JSONException {
		regradedPointService.bonusPoints(org, RegradedType.ASSESSMENTUSER,
				reason, point, applyDate, logId);
	}

	@Override
	public void onEntry(FourTeamsIssueNew issue, FourTeamsIssueStep step) {
		try {
			FourTeamsIssueAccessConfig config = issueAccessConfigService
					.getIssueAccessConfig();
			if (config == null) {
				config = new FourTeamsIssueAccessConfig();
			}
			addRatingRecord(issue.getCreateOrg(),
					createRegistIssueRegradedReason(issue), config
							.getNormalScores().doubleValue(),
					issue.getCreateDate(), getLastIssueLogId(issue.getId()));
		} catch (JSONException e) {
			throw new ServiceValidationException("添加打分记录时发生错误", e);
		}
	}

	private Long getLastIssueLogId(Long id) {
		List<FourTeamsIssueLogNew> issueLogs = issueLogService
				.findIssueLogsByIssueId(id);
		FourTeamsIssueLogNew issueLog = issueLogs.get(issueLogs.size() - 1);
		return issueLog == null ? null : issueLog.getId();
	}

	@Override
	public void onComplete(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueChangeEvent event) {
		try {
			double cent = issueAccessConfigService
					.getIssueScoresConfig(FourTeamsIssueOperate.COMPLETE);
			addRatingRecord(step.getTarget(),
					createCompleteIssueRegradedReason(issue), cent,
					issue.getCreateDate(), getLastIssueLogId(issue.getId()));
		} catch (JSONException e) {
			throw new ServiceValidationException("添加打分记录时发生错误", e);
		}
	}

	@Override
	public void onChanged(FourTeamsIssueNew issue,
			FourTeamsIssueStepGroup steps, FourTeamsIssueChangeEvent event) {
		if (currentOrgChanged(event)) {
			try {
				double cent = issueAccessConfigService
						.getIssueScoresConfig(event.getOperate());
				Long logId = getLastIssueLogId(issue.getId());
				addRatingRecord(
						steps.getKeyStep().getTarget(),
						createTransferIssueRegradedReason(issue,
								event.getOperate()), cent,
						issue.getCreateDate(), logId);
				if (steps.hasIncidentalStep()) {
					for (FourTeamsIssueStep step : steps.getIncidentalSteps()) {
						addRatingRecord(step.getTarget(),
								createTellIssueRegradedReason(issue), event
										.getOperate().getCent(),
								issue.getCreateDate(), logId);
					}
				}
			} catch (JSONException e) {
				throw new ServiceValidationException("添加打分记录时发生错误", e);
			}
		}
	}

}

package com.tianque.fourTeams.fourTeamsIssue.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.CalendarUtil;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperationContext;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepInfo;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsOrganizationInfo;
import com.tianque.fourTeams.fourTeamsIssue.state.exception.FourTeamsIssueOperationException;
import com.tianque.fourTeams.fourTeamsIssue.state.exception.FourTeamsUnsupportOperationException;

public class FourTeamsUnConceptedState extends FourTeamsCanSuperviseState {

	@Override
	protected String getStateLabel() {
		return "待受理";
	}

	@Override
	public void concept(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		if (!isSameOrg(currentStep.getTarget(), context.getTargetOrg())) {
			throw new FourTeamsIssueOperationException("不能受理其他部门的事件");
		}
		currentStep.setLastDealDate(CalendarUtil.now());
		currentStep.setState(FourTeamsDealingState.class.getName());
		currentStep.setStateCode(DEALING_CODE);
		// currentStep.setSuperviseLevel(NO_SUPERVISE);
		issue.setStatus(DEALING);
	}

	@Override
	public List<FourTeamsIssueOperate> getCando(FourTeamsIssueStepInfo step,
			FourTeamsOrganizationInfo orgInfo) {
		List<FourTeamsIssueOperate> result = new ArrayList<FourTeamsIssueOperate>();
		if (step.getOperationOrg().equals(orgInfo.getOrganization())) {
			result.add(FourTeamsIssueOperate.CONCEPT);
		}
		if (isHighLevelOrg(orgInfo.getOrganization().getOrgLevel()
				.getInternalId(), step.getOperationOrg().getOrgLevel()
				.getInternalId())) {
			result.add(FourTeamsIssueOperate.INSTRUCT);
		}
		if (isHighLevelOrg(orgInfo.getOrganization().getOrgLevel()
				.getInternalId(), step.getOperationOrg().getOrgLevel()
				.getInternalId())
				|| isSameLevelOrg(orgInfo.getOrganization().getOrgLevel()
						.getInternalId(), step.getOperationOrg().getOrgLevel()
						.getInternalId())) {
			if (step.getSuperviseLevel() < RED_CARD_SUPERVISE) {
				result.add(FourTeamsIssueOperate.RED_SUPERVISE);
			}
			if (step.getSuperviseLevel() < YELLOW_CARD_SUPERVISE) {
				result.add(FourTeamsIssueOperate.YELLOW_SUPERVISE);
			}
			if (step.getSuperviseLevel() < NORMAL_SUPERVISE) {
				result.add(FourTeamsIssueOperate.NORMAL_SUPERVISE);
			}
			if (step.getSuperviseLevel() > NO_SUPERVISE) {
				result.add(FourTeamsIssueOperate.CANCEL_SUPERVISE);
			}
		}
		return result;
	}
}

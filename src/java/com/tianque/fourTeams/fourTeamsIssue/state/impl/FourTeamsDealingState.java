package com.tianque.fourTeams.fourTeamsIssue.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueSkiprule;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperationContext;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueSourceState;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepInfo;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsOrganizationInfo;
import com.tianque.fourTeams.fourTeamsIssue.state.exception.FourTeamsIssueOperationException;
import com.tianque.fourTeams.fourTeamsIssue.state.exception.FourTeamsUnsupportOperationException;

public class FourTeamsDealingState extends FourTeamsCanSuperviseState {

	@Override
	protected String getStateLabel() {
		return "办理中";
	}

	@Override
	public FourTeamsIssueStepGroup assign(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new FourTeamsIssueOperationException("不能交办其他部门的事件");
		}
		if (issue.getEmergencyLevel() == null
				|| issue.getEmergencyLevel().getId() == null) {
			if (getLoginedOrg(context).getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
				if (!context.getTargetOrg().getParentFunOrg().equals(
						getLoginedOrg(context))) {
					throw new FourTeamsIssueOperationException(
							"不能将事件交办给非直属下级部门");
				}
			} else if (!context.getTargetOrg().getParentOrg().equals(
					getLoginedOrg(context))) {
				throw new FourTeamsIssueOperationException("不能将事件交办给非直属下级部门");
			}
		}
		return transferIssueToOtherOrg(issue, currentStep,
				getLoginedOrg(context), context.getTargetOrg(), context
						.getTellOrgs(), false);
	}

	@Override
	public void comment(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new FourTeamsIssueOperationException("不能办理其他部门的事件");
		}
		// currentStep.setLastDealDate(CalendarUtil.now());
		// 办理中不取消督办状态
		// currentStep.setSuperviseLevel(NO_SUPERVISE);
	}

	@Override
	public void complete(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new FourTeamsIssueOperationException("不能办结其他部门的事件");
		}
		issue.setStatus(COMPLETE);
		issue.setUrgent(0L);
		currentStep.setLastDealDate(CalendarUtil.now());
		currentStep.setEndDate(currentStep.getLastDealDate());
		currentStep.setState(FourTeamsIssueCompleteState.class.getName());
		currentStep.setStateCode(ISSUECOMPLETE_CODE);
		currentStep.setSuperviseLevel(NO_SUPERVISE);
		currentStep.setIsExtended(ISSUE_EXTENDED);
	}

	@Override
	public void backUps(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new FourTeamsIssueOperationException("不能办结其他部门的事件");
		}
		issue.setStatus(COMPLETE);
		currentStep.setLastDealDate(CalendarUtil.now());
		currentStep.setEndDate(currentStep.getLastDealDate());
		currentStep.setState(FourTeamsIssueCompleteState.class.getName());
		currentStep.setStateCode(BACKUPS_CODE);
		currentStep.setSuperviseLevel(NO_SUPERVISE);
	}

	@Override
	public FourTeamsIssueStepGroup submit(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context,
			FourTeamsIssueSkiprule issueSkiprule)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new FourTeamsIssueOperationException("不能上报其他部门的事件");
		}
		if (issueSkiprule == null) {
			if (getLoginedOrg(context).getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
				if (context.getTargetOrg().getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG ? !getLoginedOrg(
						context).getParentFunOrg().equals(
						context.getTargetOrg())
						: !getLoginedOrg(context).getParentOrg().equals(
								context.getTargetOrg())) {
					throw new FourTeamsIssueOperationException("不能越级上报");
				}
			} else {
				if (context.getTargetOrg().getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG ? !getLoginedOrg(
						context).getParentOrg().equals(
						context.getTargetOrg().getParentOrg())
						: !getLoginedOrg(context).getParentOrg().equals(
								context.getTargetOrg())) {
					throw new FourTeamsIssueOperationException("不能越级上报");
				}
			}
		}
		return transferIssueToOtherOrg(issue, currentStep,
				getLoginedOrg(context), context.getTargetOrg(), context
						.getTellOrgs(), true);
	}

	@Override
	public FourTeamsIssueStepGroup back(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new FourTeamsIssueOperationException("不能回退其他部门的事件");
		}
		if (currentStep.getBackTo() == null) {
			throw new FourTeamsIssueOperationException("事件处于第一个处理环节，不能回退");
		}
		return backIssueToSourceStep(issue, currentStep, context);
	}

	private FourTeamsIssueStepGroup backIssueToSourceStep(
			FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) {
		FourTeamsIssueStepGroup result = new FourTeamsIssueStepGroup();
		FourTeamsIssueStep keyStep = constructIssueStepAndFillDefaultProperty(
				issue, getLoginedOrg(context), currentStep.getBackTo()
						.getTarget());
		keyStep.setBackTo(currentStep.getBackTo().getBackTo());
		if ("true".equals(GridProperties.IS_NEED_UNCONCEPTEDSTATE)) {
			keyStep.setState(FourTeamsUnConceptedState.class.getName());
			keyStep.setStateCode(UNCONCEPTED_CODE);
		} else {
			keyStep.setState(FourTeamsDealingState.class.getName());
			keyStep.setStateCode(DEALING_CODE);
		}
		stepCompleteCurrentStepAndFillDealTime(currentStep, keyStep);
		result.setKeyStep(keyStep);
		issue.setCurrentStep(keyStep);
		issue.setCurrentOrg(keyStep.getTarget());
		issue.setStatus(DEALING);
		// currentStep.setSuperviseLevel(NO_SUPERVISE);
		return result;
	}

	private FourTeamsIssueStepGroup transferIssueToOtherOrg(
			FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			Organization loginedOrg, Organization target,
			List<Organization> tellOrgs, boolean tellHighOrg) {
		FourTeamsIssueStepGroup result = new FourTeamsIssueStepGroup();
		FourTeamsIssueStep keyStep = constructIssueStepAndFillDefaultProperty(
				issue, loginedOrg, target);
		keyStep.setBackTo(currentStep);
		keyStep.setItemTypeId(currentStep.getItemTypeId());
		if ("true".equals(GridProperties.IS_NEED_UNCONCEPTEDSTATE)) {
			keyStep.setState(FourTeamsUnConceptedState.class.getName());
			keyStep.setStateCode(UNCONCEPTED_CODE);
		} else {
			keyStep.setState(FourTeamsDealingState.class.getName());
			keyStep.setStateCode(DEALING_CODE);
		}
		if (!tellHighOrg)
			keyStep.setAssign(FourTeamsIssueSourceState.assign);
		stepCompleteCurrentStepAndFillDealTime(currentStep, keyStep);
		if (tellOrgs != null) {
			for (Organization org : tellOrgs) {
				if (!org.equals(target)) {
					FourTeamsIssueStep tellStep = constructIssueStepAndFillDefaultProperty(
							issue, loginedOrg, org);
					tellStep.setState(FourTeamsUnReadState.class.getName());
					result.addIncidentalStep(constructTellStep(issue,
							loginedOrg, org, tellHighOrg));
				}
			}
		}
		result.setKeyStep(keyStep);
		// currentStep.setSuperviseLevel(NO_SUPERVISE);
		issue.setCurrentStep(keyStep);
		issue.setCurrentOrg(keyStep.getTarget());
		issue.setStatus(DEALING);
		return result;
	}

	private FourTeamsIssueStep constructTellStep(FourTeamsIssueNew issue,
			Organization loginedOrg, Organization tellorg, boolean tellHighOrg) {
		if (issue.getEmergencyLevel() == null
				|| issue.getEmergencyLevel().getId() == null) {
			if (loginedOrg.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
				if (tellHighOrg
						&& (tellorg.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG ? !loginedOrg
								.getParentFunOrg().equals(tellorg)
								: !loginedOrg.getParentOrg().equals(tellorg))) {
					throw new FourTeamsIssueOperationException("只能抄告上级部门");
				} else if (!tellHighOrg
						&& tellorg.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG ? !tellorg
						.getParentFunOrg().equals(loginedOrg)
						: !loginedOrg.getParentOrg().equals(tellorg)) {
					throw new FourTeamsIssueOperationException("只能抄告下级或主管部门");
				}
			} else {
				if (tellHighOrg
						&& (tellorg.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG ? !loginedOrg
								.getParentOrg().equals(tellorg.getParentOrg())
								: !loginedOrg.getParentOrg().equals(tellorg))) {
					throw new FourTeamsIssueOperationException("只能抄告上级部门");
				} else if (!tellHighOrg
						&& !tellorg.getParentOrg().equals(loginedOrg)) {
					throw new FourTeamsIssueOperationException("只能抄告下级部门");
				}
			}
		}
		FourTeamsIssueStep tellStep = constructIssueStepAndFillDefaultProperty(
				issue, loginedOrg, tellorg);
		tellStep.setState(FourTeamsUnReadState.class.getName());
		tellStep.setStateCode(UNREAD_CODE);
		if (!tellHighOrg)
			tellStep.setAssign(FourTeamsIssueSourceState.assign);
		return tellStep;
	}

	@Override
	public List<FourTeamsIssueOperate> getCando(FourTeamsIssueStepInfo step,
			FourTeamsOrganizationInfo orgInfo) {
		List<FourTeamsIssueOperate> result = new ArrayList<FourTeamsIssueOperate>();
		if (step.getOperationOrg().equals(orgInfo.getOrganization())) {
			result.add(FourTeamsIssueOperate.COMMENT);
			result.add(FourTeamsIssueOperate.COMPLETE);
			if (!orgInfo.isTopOrg()
					&& orgInfo.getOrganization().getOrgLevel().getInternalId() != OrganizationLevel.PROVINCE) {
				result.add(FourTeamsIssueOperate.SUBMIT);
			}
			if (!orgInfo.isLeafOrg()) {
				result.add(FourTeamsIssueOperate.ASSIGN);
			}
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
		result.add(FourTeamsIssueOperate.BACK);
		return result;
	}

}

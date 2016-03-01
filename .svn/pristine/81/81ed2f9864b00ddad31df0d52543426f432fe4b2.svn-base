package com.tianque.issue.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueSkiprule;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueOperationContext;
import com.tianque.issue.state.IssueSourceState;
import com.tianque.issue.state.IssueStepGroup;
import com.tianque.issue.state.IssueStepInfo;
import com.tianque.issue.state.OrganizationInfo;
import com.tianque.issue.state.exception.IssueOperationException;
import com.tianque.issue.state.exception.UnsupportOperationException;
import com.tianque.issue.uitl.IssueToCMSUtil;

public class DealingState extends CanSuperviseState {

	@Override
	protected String getStateLabel() {
		return "办理中";
	}

	@Override
	public IssueStepGroup assign(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new IssueOperationException("不能交办其他部门的事件");
		}
		verificationCurrentStep(currentStep);
		if (getLoginedOrg(context).getId() > 0
				&& (currentStep.getEmergencyLevel() == null || currentStep
						.getEmergencyLevel().getId() == null)) {
			if (getLoginedOrg(context).getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
				if (!context.getTargetOrg().getParentFunOrg()
						.equals(getLoginedOrg(context))) {
					throw new IssueOperationException("不能将事件交办给非直属下级部门");
				}
			} else if (!context.getTargetOrg().getParentOrg()
					.equals(getLoginedOrg(context))) {
				throw new IssueOperationException("不能将事件交办给非直属下级部门");
			}
		}
		return transferIssueToOtherOrg(issue, currentStep,
				getLoginedOrg(context), context.getTargetOrg(),
				context.getTellOrgs(), false);
	}

	@Override
	public void comment(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new IssueOperationException("不能办理其他部门的事件");
		}
		// currentStep.setLastDealDate(CalendarUtil.now());
		// 办理中不取消督办状态
		// currentStep.setSuperviseLevel(NO_SUPERVISE);
	}

	@Override
	public IssueStepGroup complete(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new IssueOperationException("不能办结其他部门的事件");
		}
		verificationCurrentStep(currentStep);
		return transferIssueForComplete(issue, currentStep,
				getLoginedOrg(context), context.getTargetOrg());

	}

	private IssueStepGroup transferIssueForComplete(IssueNew issue,
			IssueStep currentStep, Organization loginedOrg, Organization target) {
		IssueStepGroup result = new IssueStepGroup();
		IssueStep keyStep = constructIssueStepAndFillDefaultProperty(issue,
				loginedOrg, target);
		keyStep.setBackTo(currentStep);
		keyStep.setItemTypeId(currentStep.getItemTypeId());
		keyStep.setState(IssueCompleteState.class.getName());
		keyStep.setStateCode(ISSUEVERIFICATION_CODE);// 结案
		if (!StringUtil.isStringAvaliable(issue.getFromSerialNumber())) {
			keyStep.setTarget(issue.getCreateOrg());
		} else {
			keyStep.setTarget(IssueToCMSUtil
					.getLocationOrgNameByLocationId(keyStep.getTarget().getId()));
		}
		keyStep.setFourTeams(currentStep.getFourTeams());
		keyStep.setFourTeamsTypeID(currentStep.getFourTeamsTypeID());
		stepCompleteCurrentStepAndFillDealTime(currentStep, keyStep);
		//如果超时保存超时状态
		keyStep.setIsExtended(currentStep.getIsExtended());
		result.setKeyStep(keyStep);
		// currentStep.setSuperviseLevel(NO_SUPERVISE);
		issue.setCurrentStep(keyStep);
		issue.setCurrentOrg(keyStep.getTarget());
		issue.setStatus(COMPLETE);// 结案待验证
		return result;
	}

	@Override
	public void backUps(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new IssueOperationException("不能办结其他部门的事件");
		}
		issue.setStatus(COMPLETE);
		currentStep.setLastDealDate(CalendarUtil.now());
		currentStep.setEndDate(currentStep.getLastDealDate());
		currentStep.setState(IssueCompleteState.class.getName());
		currentStep.setStateCode(BACKUPS_CODE);
		currentStep.setSuperviseLevel(NO_SUPERVISE);
	}

	@Override
	public IssueStepGroup submit(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context, IssueSkiprule issueSkiprule)
			throws UnsupportOperationException, IssueOperationException {
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new IssueOperationException("不能上报其他部门的事件");
		}
		verificationCurrentStep(currentStep);
		if (issueSkiprule == null && context.getTargetOrg().getId() > 0) {
			if (getLoginedOrg(context).getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
				if (context.getTargetOrg().getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG ? !getLoginedOrg(
						context).getParentFunOrg().equals(
						context.getTargetOrg())
						: !getLoginedOrg(context).getParentOrg().equals(
								context.getTargetOrg())) {
					throw new IssueOperationException("不能越级上报");
				}
			} else {
				if (context.getTargetOrg().getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG ? !getLoginedOrg(
						context).getParentOrg().equals(
						context.getTargetOrg().getParentOrg())
						: !getLoginedOrg(context).getParentOrg().equals(
								context.getTargetOrg())) {
					throw new IssueOperationException("不能越级上报");
				}
			}
		}
		return transferIssueToOtherOrg(issue, currentStep,
				getLoginedOrg(context), context.getTargetOrg(),
				context.getTellOrgs(), true);
	}

	@Override
	public IssueStepGroup back(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		if (!getLoginedOrg(context).equals(currentStep.getTarget())) {
			throw new IssueOperationException("不能回退其他部门的事件");
		}
		if (currentStep.getBackTo() == null) {
			throw new IssueOperationException("事件处于第一个处理环节，不能回退");
		}
		verificationCurrentStep(currentStep);
		return backIssueToSourceStep(issue, currentStep, context);
	}

	private IssueStepGroup backIssueToSourceStep(IssueNew issue,
			IssueStep currentStep, IssueOperationContext context) {
		IssueStepGroup result = new IssueStepGroup();
		IssueStep keyStep = constructIssueStepAndFillDefaultProperty(issue,
				getLoginedOrg(context), currentStep.getBackTo().getTarget());
		keyStep.setBackTo(currentStep.getBackTo().getBackTo());
		if ("true".equals(GridProperties.IS_NEED_UNCONCEPTEDSTATE)) {
			keyStep.setState(UnConceptedState.class.getName());
			keyStep.setStateCode(UNCONCEPTED_CODE);
		} else {
			keyStep.setState(DealingState.class.getName());
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

	private IssueStepGroup transferIssueToOtherOrg(IssueNew issue,
			IssueStep currentStep, Organization loginedOrg,
			Organization target, List<Organization> tellOrgs,
			boolean tellHighOrg) {
		IssueStepGroup result = new IssueStepGroup();
		IssueStep keyStep = constructIssueStepAndFillDefaultProperty(issue,
				loginedOrg, target);
		keyStep.setBackTo(currentStep);
		keyStep.setItemTypeId(currentStep.getItemTypeId());
		if ("true".equals(GridProperties.IS_NEED_UNCONCEPTEDSTATE)) {
			keyStep.setState(UnConceptedState.class.getName());
			keyStep.setStateCode(UNCONCEPTED_CODE);
		} else {
			keyStep.setState(DealingState.class.getName());
			keyStep.setStateCode(DEALING_CODE);
		}
		if (!tellHighOrg)
			keyStep.setAssign(IssueSourceState.assign);
		stepCompleteCurrentStepAndFillDealTime(currentStep, keyStep);
		if (tellOrgs != null) {
			for (Organization org : tellOrgs) {
				if (!org.equals(target)) {
					IssueStep tellStep = constructIssueStepAndFillDefaultProperty(
							issue, loginedOrg, org);
					tellStep.setState(UnReadState.class.getName());
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

	private IssueStep constructTellStep(IssueNew issue,
			Organization loginedOrg, Organization tellorg, boolean tellHighOrg) {
		if (issue.getCurrentStep().getEmergencyLevel() == null
				|| issue.getCurrentStep().getEmergencyLevel().getId() == null) {
			if (loginedOrg.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
				if (tellHighOrg
						&& (tellorg.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG ? !loginedOrg
								.getParentFunOrg().equals(tellorg)
								: !loginedOrg.getParentOrg().equals(tellorg))) {
					throw new IssueOperationException("只能抄告上级部门");
				} else if (!tellHighOrg
						&& tellorg.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG ? !tellorg
						.getParentFunOrg().equals(loginedOrg) : !loginedOrg
						.getParentOrg().equals(tellorg)) {
					throw new IssueOperationException("只能抄告下级或主管部门");
				}
			} else {
				if (tellHighOrg
						&& (tellorg.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG ? !loginedOrg
								.getParentOrg().equals(tellorg.getParentOrg())
								: !loginedOrg.getParentOrg().equals(tellorg))) {
					throw new IssueOperationException("只能抄告上级部门");
				} else if (!tellHighOrg
						&& !tellorg.getParentOrg().equals(loginedOrg)) {
					throw new IssueOperationException("只能抄告下级部门");
				}
			}
		}
		IssueStep tellStep = constructIssueStepAndFillDefaultProperty(issue,
				loginedOrg, tellorg);
		tellStep.setState(UnReadState.class.getName());
		tellStep.setStateCode(UNREAD_CODE);
		// if (!tellHighOrg)
		// // tellStep.setAssign(IssueSourceState.assign);
		return tellStep;
	}

	@Override
	public List<IssueOperate> getCando(IssueStepInfo step,
			OrganizationInfo orgInfo) {
		List<IssueOperate> result = new ArrayList<IssueOperate>();
		if (isHighLevelOrg(orgInfo.getOrganization().getOrgLevel()
				.getInternalId(), step.getOperationOrg().getOrgLevel()
				.getInternalId())) {
			result.add(IssueOperate.INSTRUCT);
		}
		if (isHighLevelOrg(orgInfo.getOrganization().getOrgLevel()
				.getInternalId(), step.getOperationOrg().getOrgLevel()
				.getInternalId())
				|| isSameLevelOrg(orgInfo.getOrganization().getOrgLevel()
						.getInternalId(), step.getOperationOrg().getOrgLevel()
						.getInternalId())) {
			if (step.getSuperviseLevel() < RED_CARD_SUPERVISE) {
				result.add(IssueOperate.RED_SUPERVISE);
			}
			if (step.getSuperviseLevel() < YELLOW_CARD_SUPERVISE) {
				result.add(IssueOperate.YELLOW_SUPERVISE);
			}
			if (step.getSuperviseLevel() < NORMAL_SUPERVISE) {
				result.add(IssueOperate.NORMAL_SUPERVISE);
			}
			if (step.getSuperviseLevel() > NO_SUPERVISE) {
				result.add(IssueOperate.CANCEL_SUPERVISE);
			}
		}
		result.add(IssueOperate.BACK);
		if (step.getOperationOrg().equals(orgInfo.getOrganization())) {
			result.add(IssueOperate.COMMENT);
			if (!orgInfo.isLeafOrg()) {
				result.add(IssueOperate.ASSIGN);
			}
			result.add(IssueOperate.COMPLETE);
			if (!orgInfo.isTopOrg()
					&& orgInfo.getOrganization().getOrgLevel().getInternalId() != OrganizationLevel.PROVINCE) {
				result.add(IssueOperate.SUBMIT);
			}
		}
		return result;
	}

}

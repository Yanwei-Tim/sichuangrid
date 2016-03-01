package com.tianque.issue.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.issue.constants.IssueConstants;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueOperationContext;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.state.IssueStepGroup;
import com.tianque.issue.state.IssueStepInfo;
import com.tianque.issue.state.OrganizationInfo;
import com.tianque.issue.state.exception.IssueOperationException;
import com.tianque.issue.state.exception.UnsupportOperationException;

public class IssueCompleteState extends CanSuperviseState {

	@Override
	protected String getStateLabel() {
		return "已完成待验证";
	}

	@Override
	public List<IssueOperate> getCando(IssueStepInfo step,
			OrganizationInfo orgInfo) {
		List<IssueOperate> result = new ArrayList<IssueOperate>();
		// if (step.getOperationOrg().equals(orgInfo.getOrganization())) {

		// }
		result.add(IssueOperate.BACK);
		result.add(IssueOperate.VERIFICATION);
		int stepInternalId = orgInfo.getOrganization().getOrgLevel()
				.getInternalId();
		int orgInternalId = step.getOperationOrg().getOrgLevel()
				.getInternalId();
		if (isHighLevelOrg(stepInternalId, orgInternalId)
				|| isSameLevelOrg(stepInternalId, orgInternalId)) {
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
		return result;
	}

	@Override
	public IssueStepGroup back(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		if (!getLoginedOrg(context).equals(issue.getCreateOrg())) {
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
			// 如果不是在本级那么设置为待受理
			keyStep.setState(UnConceptedState.class.getName());
			keyStep.setStateCode(UNCONCEPTED_CODE);
		} else {
			// 如果是在本级则设置为待 办
			keyStep.setState(IssueCompleteState.class.getName());
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

	@Override
	public void verification(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		if (issue.getIsChangeIntoThreeRecords() == IssueConstants.NOT_CHANGEINTOTHREERECORDS) {
			if (!getLoginedOrg(context).equals(issue.getCreateOrg())) {
				throw new IssueOperationException("不能验证其他部门的事件");
			}
		}
		verificationCurrentStep(currentStep);
		issue.setStatus(VERIFICATION);
		issue.setUrgent(0L);
		currentStep.setLastDealDate(CalendarUtil.now());
		currentStep.setEndDate(currentStep.getLastDealDate());
		currentStep.setState(VerificationState.class.getName());
		currentStep.setStateCode(ISSUECOMPLETE_CODE);// 更改成验证完成,事件结束
		currentStep.setSuperviseLevel(IssueState.NO_SUPERVISE);// 验证通过的事件，督办情况都改为未督办状态
		currentStep.setIsExtended(currentStep.getIsExtended());
	}

}
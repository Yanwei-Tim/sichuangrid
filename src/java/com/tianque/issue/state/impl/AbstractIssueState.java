package com.tianque.issue.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueSkiprule;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueOperationContext;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.state.IssueStepGroup;
import com.tianque.issue.state.IssueStepInfo;
import com.tianque.issue.state.OrganizationInfo;
import com.tianque.issue.state.exception.IssueOperationException;
import com.tianque.issue.state.exception.UnsupportOperationException;

public abstract class AbstractIssueState implements IssueState {

	protected abstract String getStateLabel();

	@Override
	public IssueStepGroup assign(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行"
				+ IssueOperate.ASSIGN);
	}

	@Override
	public void comment(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行"
				+ IssueOperate.COMMENT);
	}

	@Override
	public IssueStepGroup complete(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行"
				+ IssueOperate.COMPLETE);
	}

	@Override
	public void verification(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行"
				+ IssueOperate.VERIFICATION);
	}

	@Override
	public void backUps(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行"
				+ IssueOperate.BACKUPS);
	}

	@Override
	public void concept(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行"
				+ IssueOperate.CONCEPT);
	}

	@Override
	public void instruct(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行"
				+ IssueOperate.INSTRUCT);
	}

	@Override
	public void read(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行"
				+ IssueOperate.READ);
	}

	@Override
	public IssueStep reportTo(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行"
				+ IssueOperate.REPORT_TO);
	}

	@Override
	public IssueStepGroup submit(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context, IssueSkiprule issueSkiprule)
			throws UnsupportOperationException, IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行"
				+ IssueOperate.SUBMIT);
	}

	@Override
	public void supervise(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行督办");
	}

	@Override
	public void cancelSupervise(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行"
				+ IssueOperate.CANCEL_SUPERVISE);
	}

	@Override
	public IssueStep close(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行"
				+ IssueOperate.CLOSE);
	}

	@Override
	public IssueStep dealOver(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行"
				+ IssueOperate.DEALOVER);
	}

	@Override
	public IssueStep fedBack(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行"
				+ IssueOperate.FED_BACK);
	}

	@Override
	public IssueStepGroup giveTo(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行"
				+ IssueOperate.GIVETO);
	}

	@Override
	public IssueStepGroup back(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		throw new UnsupportOperationException(getStateLabel() + "下不能进行"
				+ IssueOperate.BACK);
	}

	@Override
	public List<IssueOperate> getCando(IssueStepInfo step,
			OrganizationInfo orgInfo) {
		return new ArrayList<IssueOperate>();
	}

	public String getStateTag() {
		return getClass().getName();
	}

	protected boolean isSameOrg(Organization source, Organization target) {
		if (source == null && target == null) {
			return true;
		}
		if (source == null || target == null) {
			return false;
		}
		return source.equals(target);
	}

	protected boolean isCommandCenter(Organization org) {
		return org.getParentOrg() == null;
	}

	protected IssueStep constructIssueStepAndFillDefaultProperty(
			IssueNew issue, Organization loginedOrg, Organization target) {
		IssueStep result = new IssueStep();
		result.setEntryDate(CalendarUtil.now());
		result.setLastDealDate(result.getEntryDate());
		result.setSource(loginedOrg);
		result.setTarget(target);
		result.setIssue(issue);
		return result;
	}

	protected void stepCompleteCurrentStepAndFillDealTime(
			IssueStep currentStep, IssueStep newstep) {
		if (currentStep.getStateCode() == ISSUEVERIFICATION_CODE) {
			// 如果是验证回退那么状态改为验证未通过
			currentStep.setState(VerificationState.class.getName());
			currentStep.setStateCode(ISSUEUNVERIFICATION_CODE);
		} else {
			currentStep.setState(StepCompleteState.class.getName());
			currentStep.setStateCode(STEPCOMPLETE_CODE);
		}
		currentStep.setLastDealDate(newstep.getEntryDate());
		currentStep.setEndDate(currentStep.getLastDealDate());
	}

	protected void stepCompleteCurrentStep(IssueStep currentStep) {
		currentStep.setState(StepCompleteState.class.getName());
		currentStep.setStateCode(STEPCOMPLETE_CODE);
		currentStep.setLastDealDate(CalendarUtil.now());
		currentStep.setEndDate(currentStep.getLastDealDate());
	}

	protected boolean notParentOrg(Organization parent, Organization child) {
		return child.getOrgInternalCode().startsWith(
				parent.getOrgInternalCode());
	}

	protected Organization getLoginedOrg(IssueOperationContext context) {
		return context.getCurrentLoginedOrg();
	}

	protected boolean isHighLevelOrg(int orgLevel, int sourceLevel) {
		return orgLevel > sourceLevel;
	}

	protected boolean isSameLevelOrg(int orgLevel, int sourceLevel) {
		return orgLevel == sourceLevel;
	}

	protected void verificationCurrentStep(IssueStep currentStep) {
		if (currentStep.getStateCode() == IssueState.STEPCOMPLETE_CODE
				|| currentStep.getStateCode() == IssueState.ISSUEUNVERIFICATION_CODE
				|| currentStep.getStateCode() == IssueState.ISSUECOMPLETE_CODE) {
			throw new IssueOperationException("该事件已经处理,不能重复处理");
		}
	}

}

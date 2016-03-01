package com.tianque.fourTeams.fourTeamsIssue.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueSkiprule;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperationContext;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueState;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepInfo;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsOrganizationInfo;
import com.tianque.fourTeams.fourTeamsIssue.state.exception.FourTeamsIssueOperationException;
import com.tianque.fourTeams.fourTeamsIssue.state.exception.FourTeamsUnsupportOperationException;

public abstract class AbstractFourTeamsIssueState implements FourTeamsIssueState {

	protected abstract String getStateLabel();

	@Override
	public FourTeamsIssueStepGroup assign(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		throw new FourTeamsUnsupportOperationException(getStateLabel() + "下不能进行"
				+ FourTeamsIssueOperate.ASSIGN);
	}

	@Override
	public void comment(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		throw new FourTeamsUnsupportOperationException(getStateLabel() + "下不能进行"
				+ FourTeamsIssueOperate.COMMENT);
	}

	@Override
	public void complete(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		throw new FourTeamsUnsupportOperationException(getStateLabel() + "下不能进行"
				+ FourTeamsIssueOperate.COMPLETE);
	}

	@Override
	public void backUps(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		throw new FourTeamsUnsupportOperationException(getStateLabel() + "下不能进行"
				+ FourTeamsIssueOperate.BACKUPS);
	}

	@Override
	public void concept(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		throw new FourTeamsUnsupportOperationException(getStateLabel() + "下不能进行"
				+ FourTeamsIssueOperate.CONCEPT);
	}

	@Override
	public void instruct(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		throw new FourTeamsUnsupportOperationException(getStateLabel() + "下不能进行"
				+ FourTeamsIssueOperate.INSTRUCT);
	}

	@Override
	public void read(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		throw new FourTeamsUnsupportOperationException(getStateLabel() + "下不能进行"
				+ FourTeamsIssueOperate.READ);
	}

	@Override
	public FourTeamsIssueStep reportTo(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		throw new FourTeamsUnsupportOperationException(getStateLabel() + "下不能进行"
				+ FourTeamsIssueOperate.REPORT_TO);
	}

	@Override
	public FourTeamsIssueStepGroup submit(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context, FourTeamsIssueSkiprule issueSkiprule)
			throws FourTeamsUnsupportOperationException, FourTeamsIssueOperationException {
		throw new FourTeamsUnsupportOperationException(getStateLabel() + "下不能进行"
				+ FourTeamsIssueOperate.SUBMIT);
	}

	@Override
	public void supervise(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		throw new FourTeamsUnsupportOperationException(getStateLabel() + "下不能进行督办");
	}

	@Override
	public void cancelSupervise(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		throw new FourTeamsUnsupportOperationException(getStateLabel() + "下不能进行"
				+ FourTeamsIssueOperate.CANCEL_SUPERVISE);
	}

	@Override
	public FourTeamsIssueStep close(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		throw new FourTeamsUnsupportOperationException(getStateLabel() + "下不能进行"
				+ FourTeamsIssueOperate.CLOSE);
	}

	@Override
	public FourTeamsIssueStep dealOver(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		throw new FourTeamsUnsupportOperationException(getStateLabel() + "下不能进行"
				+ FourTeamsIssueOperate.DEALOVER);
	}

	@Override
	public FourTeamsIssueStep fedBack(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		throw new FourTeamsUnsupportOperationException(getStateLabel() + "下不能进行"
				+ FourTeamsIssueOperate.FED_BACK);
	}

	@Override
	public FourTeamsIssueStepGroup giveTo(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		throw new FourTeamsUnsupportOperationException(getStateLabel() + "下不能进行"
				+ FourTeamsIssueOperate.GIVETO);
	}

	@Override
	public FourTeamsIssueStepGroup back(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		throw new FourTeamsUnsupportOperationException(getStateLabel() + "下不能进行"
				+ FourTeamsIssueOperate.BACK);
	}

	@Override
	public List<FourTeamsIssueOperate> getCando(FourTeamsIssueStepInfo step,
			FourTeamsOrganizationInfo orgInfo) {
		return new ArrayList<FourTeamsIssueOperate>();
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

	protected FourTeamsIssueStep constructIssueStepAndFillDefaultProperty(
			FourTeamsIssueNew issue, Organization loginedOrg, Organization target) {
		FourTeamsIssueStep result = new FourTeamsIssueStep();
		result.setEntryDate(CalendarUtil.now());
		result.setLastDealDate(result.getEntryDate());
		result.setSource(loginedOrg);
		result.setTarget(target);
		result.setIssue(issue);
		return result;
	}

	protected void stepCompleteCurrentStepAndFillDealTime(
			FourTeamsIssueStep currentStep, FourTeamsIssueStep newstep) {
		currentStep.setState(FourTeamsStepCompleteState.class.getName());
		currentStep.setStateCode(STEPCOMPLETE_CODE);
		currentStep.setLastDealDate(newstep.getEntryDate());
		currentStep.setEndDate(currentStep.getLastDealDate());
	}

	protected void stepCompleteCurrentStep(FourTeamsIssueStep currentStep) {
		currentStep.setState(FourTeamsStepCompleteState.class.getName());
		currentStep.setStateCode(STEPCOMPLETE_CODE);
		currentStep.setLastDealDate(CalendarUtil.now());
		currentStep.setEndDate(currentStep.getLastDealDate());
	}

	protected boolean notParentOrg(Organization parent, Organization child) {
		return child.getOrgInternalCode().startsWith(
				parent.getOrgInternalCode());
	}

	protected Organization getLoginedOrg(FourTeamsIssueOperationContext context) {
		return context.getCurrentLoginedOrg();
	}

	protected boolean isHighLevelOrg(int orgLevel, int sourceLevel) {
		return orgLevel > sourceLevel;
	}

	protected boolean isSameLevelOrg(int orgLevel, int sourceLevel) {
		return orgLevel == sourceLevel;
	}

}

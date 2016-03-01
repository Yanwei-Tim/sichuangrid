package com.tianque.fourTeams.fourTeamsIssue.state;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.impl.FourTeamsDealingState;

public class FourTeamsIssueOperateHelper {
	public static FourTeamsIssueStep register(FourTeamsIssueNew issue) {
		FourTeamsIssueStep step = new FourTeamsIssueStep();
		step.setState(FourTeamsDealingState.class.getName());
		step.setStateCode(FourTeamsIssueState.DEALING_CODE);
		step.setEntryDate(CalendarUtil.now());
		step.setLastDealDate(step.getEntryDate());
		step.setSource(issue.getCreateOrg());
		// 如果不是新增部门直接受理的话，可以用发生部门
		step.setTarget(issue.getCreateOrg());
		step.setIssue(issue);
		issue.setCurrentStep(step);
		issue.setCurrentOrg(step.getTarget());
		issue.setStatus(FourTeamsIssueState.DEALING);
		return step;
	}

	public static List<FourTeamsIssueOperate> getCanDoOperationByOrg(
			FourTeamsIssueStep step, Organization org) {
		try {
			if (step != null) {
				FourTeamsIssueStepInfo si = new FourTeamsIssueStepInfo();
				si.setOperationOrg(step.getTarget());
				si.setSuperviseLevel(step.getSuperviseLevel());
				FourTeamsOrganizationInfo oi = new FourTeamsOrganizationInfo();
				oi.setLeafOrg((org.getSubCount() + org.getSubCountFun()) == 0);
				oi.setTopOrg(org.getParentOrg() == null);
				oi.setOrganization(org);
				return constructIssueStateFromStep(step).getCando(si, oi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<FourTeamsIssueOperate>();
	}

	// public static List<IssueOperate> getCanTransferToOrg(IssueStep
	// step,IssueOperate operation){
	// try {
	// IssueStepInfo si=new IssueStepInfo();
	// si.setOperationOrg(step.getTarget());
	// si.setSuperviseLevel(step.getSuperviseLevel());
	// OrganizationInfo oi=new OrganizationInfo();
	// oi.setLeafOrg((org.getSubCount()+org.getSubCountFun())==0);
	// oi.setTopOrg(org.getParentOrg()==null);
	// oi.setOrganization(org);
	// return constructIssueStateFromStep(step).getCando(si,oi);
	// } catch (Exception e) {
	// e.printStackTrace();
	// return new ArrayList<IssueOperate>();
	// }
	// }

	public static FourTeamsIssueState constructIssueStateFromStep(
			FourTeamsIssueStep step) throws Exception {
		return (FourTeamsIssueState) Class.forName(step.getState())
				.newInstance();
	}
}

package com.tianque.issue.state;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.impl.DealingState;

public class IssueOperateHelper {
	public static IssueStep register(IssueNew issue) {
		IssueStep step = new IssueStep();
		step.setState(DealingState.class.getName());
		step.setStateCode(IssueState.DEALING_CODE);
		step.setEntryDate(CalendarUtil.now());
		step.setLastDealDate(step.getEntryDate());
		step.setSource(issue.getCreateOrg());
		// 如果不是新增部门直接受理的话，可以用发生部门
		step.setTarget(issue.getCreateOrg());
		step.setIssue(issue);
		issue.setCurrentStep(step);
		issue.setCurrentOrg(step.getTarget());
		issue.setStatus(IssueState.DEALING);
		return step;
	}

	public static List<IssueOperate> getCanDoOperationByOrg(IssueStep step, Organization org) {
		try {
			if (step != null) {
				IssueStepInfo si = new IssueStepInfo();
				si.setOperationOrg(step.getTarget());
				si.setSuperviseLevel(step.getSuperviseLevel());
				OrganizationInfo oi = new OrganizationInfo();
				oi.setLeafOrg((org.getSubCount() + org.getSubCountFun()) == 0);
				oi.setTopOrg(org.getParentOrg() == null);
				oi.setOrganization(org);
				return constructIssueStateFromStep(step).getCando(si, oi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<IssueOperate>();
	}

	// public static List<IssueOperate> getCanTransferToOrg(IssueStep step,IssueOperate operation){
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

	public static IssueState constructIssueStateFromStep(IssueStep step) throws Exception {
		return (IssueState) Class.forName(step.getState()).newInstance();
	}
}

package com.tianque.issue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.event.listener.IssueChangeListener;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.state.impl.DealingState;
import com.tianque.issue.state.impl.UnConceptedState;

@Service("defaultIssueWorkFlowEngine")
@Transactional
public class DefaultIssueWorkFlowEngineImpl extends
		AbstractIssueWorkFlowEngineImpl {

	@Autowired
	@Qualifier("defaultIssueListeners")
	private ArrayList<IssueChangeListener> listeners;

	@Override
	protected IssueStep createEntryIssueStep(IssueNew issue, Long sourceKindId) {
		if (issue == null) {
			throw new BusinessValidationException("参数issue为空");
		}
		IssueStep step = new IssueStep();
		step.setState(DealingState.class.getName());
		step.setStateCode(IssueState.DEALING_CODE);
		step.setSource(issue.getCreateOrg());
		if (issue != null && issue.getSourceKind() != null
				&& sourceKindId.equals(issue.getSourceKind().getId())
				&& StringUtil.isStringAvaliable(issue.getFromSerialNumber())) {
			step.setState(UnConceptedState.class.getName());
			step.setStateCode(IssueState.UNCONCEPTED_CODE);
			step.setSource(ThreadVariable.getOrganization());
		}
		step.setEntryDate(issue.getCreateDate());
		step.setLastDealDate(step.getEntryDate());
		// 服务审批转入的事件用发生部门
		// if (issue.isIssueType() == true) {
		// step.setTarget(issue.getOccurOrg());
		// } else {
		step.setTarget(issue.getCreateOrg());
		// }
		step.setIssue(issue);
		return step;
	}

	@Override
	protected List<IssueChangeListener> getIssueChangeListeners() {
		return listeners;
	}

}

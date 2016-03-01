package com.tianque.issue.state;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;

/****
 * 事件处理步骤组 ：
 */
public class IssueStepGroup extends BaseDomain {
	/** 当前处理步骤 */
	private IssueStep keyStep;
	/** 抄告 */
	private List<IssueStep> incidentalSteps;
	/** 事件处理步骤组所属事件 */
	private IssueNew issue;

	private IssueLogNew entyLog;

	private IssueLogNew outLog;

	public IssueStep getKeyStep() {
		return keyStep;
	}

	public void setKeyStep(IssueStep keyStep) {
		this.keyStep = keyStep;
	}

	public List<IssueStep> getIncidentalSteps() {
		return incidentalSteps;
	}

	public void setIncidentalSteps(List<IssueStep> incidentalSteps) {
		this.incidentalSteps = incidentalSteps;
	}

	public boolean hasIncidentalStep() {
		return incidentalSteps != null && incidentalSteps.size() > 0;
	}

	public void addIncidentalStep(IssueStep step) {
		if (incidentalSteps == null) {
			incidentalSteps = new ArrayList<IssueStep>();
		}
		incidentalSteps.add(step);
	}

	public IssueNew getIssue() {
		return issue;
	}

	public void setIssue(IssueNew issue) {
		this.issue = issue;
	}

	public IssueLogNew getEntyLog() {
		return entyLog;
	}

	public void setEntyLog(IssueLogNew entyLog) {
		this.entyLog = entyLog;
	}

	public IssueLogNew getOutLog() {
		return outLog;
	}

	public void setOutLog(IssueLogNew outLog) {
		this.outLog = outLog;
	}
}

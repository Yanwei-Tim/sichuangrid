package com.tianque.fourTeams.fourTeamsIssue.state;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;

/****
 * 事件处理步骤组 ：
 */
public class FourTeamsIssueStepGroup extends BaseDomain {
	/** 当前处理步骤 */
	private FourTeamsIssueStep keyStep;
	/** 抄告 */
	private List<FourTeamsIssueStep> incidentalSteps;
	/** 事件处理步骤组所属事件 */
	private FourTeamsIssueNew issue;

	private FourTeamsIssueLogNew entyLog;

	private FourTeamsIssueLogNew outLog;

	public FourTeamsIssueStep getKeyStep() {
		return keyStep;
	}

	public void setKeyStep(FourTeamsIssueStep keyStep) {
		this.keyStep = keyStep;
	}

	public List<FourTeamsIssueStep> getIncidentalSteps() {
		return incidentalSteps;
	}

	public void setIncidentalSteps(List<FourTeamsIssueStep> incidentalSteps) {
		this.incidentalSteps = incidentalSteps;
	}

	public boolean hasIncidentalStep() {
		return incidentalSteps != null && incidentalSteps.size() > 0;
	}

	public void addIncidentalStep(FourTeamsIssueStep step) {
		if (incidentalSteps == null) {
			incidentalSteps = new ArrayList<FourTeamsIssueStep>();
		}
		incidentalSteps.add(step);
	}

	public FourTeamsIssueNew getIssue() {
		return issue;
	}

	public void setIssue(FourTeamsIssueNew issue) {
		this.issue = issue;
	}

	public FourTeamsIssueLogNew getEntyLog() {
		return entyLog;
	}

	public void setEntyLog(FourTeamsIssueLogNew entyLog) {
		this.entyLog = entyLog;
	}

	public FourTeamsIssueLogNew getOutLog() {
		return outLog;
	}

	public void setOutLog(FourTeamsIssueLogNew outLog) {
		this.outLog = outLog;
	}
}

package com.tianque.domain;

import java.util.Date;

import com.tianque.core.base.BaseDomain;

public class AssessmentLog extends BaseDomain {

	private Date assessmentDate;

	public boolean issuccessful;

	public Date getAssessmentDate() {
		return assessmentDate;
	}

	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	public boolean isIssuccessful() {
		return issuccessful;
	}

	public void setIssuccessful(boolean issuccessful) {
		this.issuccessful = issuccessful;
	}

}

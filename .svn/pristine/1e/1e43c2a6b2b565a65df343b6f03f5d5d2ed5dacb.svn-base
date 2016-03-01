package com.tianque.incident.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

public class IncidentStepFeedbacks extends BaseDomain {

	private IncidentSteps incidentSteps;

	private String feedBackUser;

	private Date feedBackDate;

	private String content;

	public IncidentSteps getIncidentSteps() {
		return incidentSteps;
	}

	public void setIncidentSteps(IncidentSteps incidentSteps) {
		this.incidentSteps = incidentSteps;
	}

	public String getFeedBackUser() {
		return feedBackUser;
	}

	public void setFeedBackUser(String feedBackUser) {
		this.feedBackUser = feedBackUser;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:dd")
	public Date getFeedBackDate() {
		return feedBackDate;
	}

	public void setFeedBackDate(Date feedBackDate) {
		this.feedBackDate = feedBackDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

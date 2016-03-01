package com.tianque.incident.domain;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

/**
 * 应急事件处理步骤对象
 * 
 * @author lizhining
 */
public class IncidentSteps extends BaseDomain {

	/**
	 * 应急事件对象
	 */
	private Incidents incidents;
	/**
	 * 执行时间
	 */
	private Date executeDate;
	/**
	 * 是否执行
	 */
	private Boolean executed = false;
	/**
	 * 执行人
	 */
	private String executeUser;
	/**
	 * 步骤类型
	 */
	private Integer stepType;
	/**
	 * 顺序
	 */
	private Integer seqIndex;
	/**
	 * 预案处置内容
	 */
	private String planContent;
	/**
	 * 实际处置内容
	 */
	private String actualContent;

	/**
	 * 步骤名称
	 * 
	 * @return
	 */
	private String stepName;

	private List<IncidentStepFeedbacks> incidentStepFeedBackList;

	public Incidents getIncidents() {
		return incidents;
	}

	public void setIncidents(Incidents incidents) {
		this.incidents = incidents;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getExecuteDate() {
		return executeDate;
	}

	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}

	public Boolean getExecuted() {
		return executed;
	}

	public void setExecuted(Boolean executed) {
		this.executed = executed;
	}

	public String getExecuteUser() {
		return executeUser;
	}

	public void setExecuteUser(String executeUser) {
		this.executeUser = executeUser;
	}

	public Integer getStepType() {
		return stepType;
	}

	public void setStepType(Integer stepType) {
		this.stepType = stepType;
	}

	public Integer getSeqIndex() {
		return seqIndex;
	}

	public void setSeqIndex(Integer seqIndex) {
		this.seqIndex = seqIndex;
	}

	public String getPlanContent() {
		return planContent;
	}

	public void setPlanContent(String planContent) {
		this.planContent = planContent;
	}

	public String getActualContent() {
		return actualContent;
	}

	public void setActualContent(String actualContent) {
		this.actualContent = actualContent;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public List<IncidentStepFeedbacks> getIncidentStepFeedBackList() {
		return incidentStepFeedBackList;
	}

	public void setIncidentStepFeedBackList(List<IncidentStepFeedbacks> incidentStepFeedBackList) {
		this.incidentStepFeedBackList = incidentStepFeedBackList;
	}

}

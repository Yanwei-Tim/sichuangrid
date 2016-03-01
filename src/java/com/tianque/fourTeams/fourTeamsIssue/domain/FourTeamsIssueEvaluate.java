package com.tianque.fourTeams.fourTeamsIssue.domain;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

public class FourTeamsIssueEvaluate extends BaseDomain {

	/** 要评价的事件 **/
	private FourTeamsIssueNew issue;
	/** 评价的星级 **/
	private Integer evaluateType;
	/** 评价内容 **/
	private String evaluateContent;
	/** 评价时间 **/
	private Date evaluateTime;

	private List<FourTeamsIssueAttachFile> issueAttachFiles;

	public Integer getEvaluateType() {
		return evaluateType;
	}

	public void setEvaluateType(Integer evaluateType) {
		this.evaluateType = evaluateType;
	}

	public String getEvaluateContent() {
		return evaluateContent;
	}

	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getEvaluateTime() {
		return evaluateTime;
	}

	public void setEvaluateTime(Date evaluateTime) {
		this.evaluateTime = evaluateTime;
	}

	public void setIssue(FourTeamsIssueNew issue) {
		this.issue = issue;
	}

	public FourTeamsIssueNew getIssue() {
		return issue;
	}

	public void setIssueAttachFiles(List<FourTeamsIssueAttachFile> issueAttachFiles) {
		this.issueAttachFiles = issueAttachFiles;
	}

	public List<FourTeamsIssueAttachFile> getIssueAttachFiles() {
		return issueAttachFiles;
	}

}

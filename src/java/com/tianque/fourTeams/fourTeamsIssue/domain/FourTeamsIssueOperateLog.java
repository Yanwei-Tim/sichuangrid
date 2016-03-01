package com.tianque.fourTeams.fourTeamsIssue.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

@SuppressWarnings("serial")
public class FourTeamsIssueOperateLog extends BaseDomain {
	/** 服务办事 **/
	private FourTeamsIssue issue;
	/** 处理目标id **/
	private Organization dealOrg;
	/** 处理部门内部编码 **/
	private String dealOrgInternalCode;
	/** 处理用户名称 **/
	private String dealUserName;
	/** 处理时间 **/
	private Date dealTime;
	/** 处理人 电话 **/
	private String mobile;
	/** 处理类型 **/
	private Long dealType;
	/** 处理描述 **/
	private String dealDescription;
	/** 处理意见 **/
	private String content;
	/** 目标部门id **/
	private Organization targeOrg;
	/** 目标部门内部编码 **/
	private String targeOrgInternalCode;
	/** 督办状态 **/
	private Long supervisionState;
	/** 处理状态(是否完成) **/
	private Long dealState;
	/** 针对的日志编号 **/
	private FourTeamsIssueOperateLog forIssueLog;
	/** 回退的日志 **/
	private FourTeamsIssueOperateLog backIssueLog;
	/** 处理步骤编号 **/
	private Long dealStepIndex;
	/** 状态类名 **/
	private String stateClass;
	/** Log日志处理时间 **/
	private Date logCompleteTime;
	/** 针对哪条日志的完成时间 **/
	private Date forLogEntryTime;
	/** 是否可以反馈 */
	/** 事件等级 */
	private PropertyDict reportedGrade;

	public PropertyDict getReportedGrade() {
		return reportedGrade;
	}

	public void setReportedGrade(PropertyDict reportedGrade) {
		this.reportedGrade = reportedGrade;
	}

	public Date getForLogEntryTime() {
		return forLogEntryTime;
	}

	public void setForLogEntryTime(Date forLogEntryTime) {
		this.forLogEntryTime = forLogEntryTime;
	}

	public Date getLogCompleteTime() {
		return logCompleteTime;
	}

	public void setLogCompleteTime(Date logCompleteTime) {
		this.logCompleteTime = logCompleteTime;
	}

	public FourTeamsIssue getIssue() {
		return issue;
	}

	public void setIssue(FourTeamsIssue issue) {
		this.issue = issue;
	}

	public Organization getDealOrg() {
		return dealOrg;
	}

	public void setDealOrg(Organization dealOrg) {
		this.dealOrg = dealOrg;
	}

	public String getDealOrgInternalCode() {
		return dealOrgInternalCode;
	}

	public void setDealOrgInternalCode(String dealOrgInternalCode) {
		this.dealOrgInternalCode = dealOrgInternalCode;
	}

	public String getDealUserName() {
		return dealUserName;
	}

	public void setDealUserName(String dealUserName) {
		this.dealUserName = dealUserName;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getDealType() {
		return dealType;
	}

	public void setDealType(Long dealType) {
		this.dealType = dealType;
	}

	public String getDealDescription() {
		return dealDescription;
	}

	public void setDealDescription(String dealDescription) {
		this.dealDescription = dealDescription;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Organization getTargeOrg() {
		return targeOrg;
	}

	public void setTargeOrg(Organization targeOrg) {
		this.targeOrg = targeOrg;
	}

	public String getTargeOrgInternalCode() {
		return targeOrgInternalCode;
	}

	public void setTargeOrgInternalCode(String targeOrgInternalCode) {
		this.targeOrgInternalCode = targeOrgInternalCode;
	}

	public Long getSupervisionState() {
		return supervisionState;
	}

	public void setSupervisionState(Long supervisionState) {
		this.supervisionState = supervisionState;
	}

	public Long getDealState() {
		return dealState;
	}

	public void setDealState(Long dealState) {
		this.dealState = dealState;
	}

	public FourTeamsIssueOperateLog getForIssueLog() {
		return forIssueLog;
	}

	public void setForIssueLog(FourTeamsIssueOperateLog forIssueLog) {
		this.forIssueLog = forIssueLog;
	}

	public void setDealStepIndex(Long dealStepIndex) {
		this.dealStepIndex = dealStepIndex;
	}

	public String getStateClass() {
		return stateClass;
	}

	public void setStateClass(String stateClass) {
		this.stateClass = stateClass;
	}

	public Long getDealStepIndex() {
		return dealStepIndex;
	}

	public FourTeamsIssueOperateLog getBackIssueLog() {
		return backIssueLog;
	}

	public void setBackIssueLog(FourTeamsIssueOperateLog backIssueLog) {
		this.backIssueLog = backIssueLog;
	}
}

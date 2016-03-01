package com.tianque.fourTeams.fourTeamsIssue.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.User;

/**
 * @ClassName: IssueApplyDelay
 * @Description: 延期申请实体类
 * @author wangxiaohu wsmalltiger@163.com
 * @date Nov 25, 2013 3:03:56 PM
 */
public class FourTeamsIssueApplyDelay extends BaseDomain {
	private Long issueStepId;// 流程节点
	private String delayInfo;// 延期原因
	private Date delayDate;// 延期时间
	private Integer delayWorkday;// 延期工作日
	private Organization auditOrg;// 审核组织
	private Integer auditState = 0;// 审核状态 0 同意 1 不同意
	private Date permitDelayDate;// 允许延期时间
	private Integer permitDelayWorkday;// 允许延期工作日
	private String auditInfo;// 审核意见（备注）
	private Date applyDate;// 申请时间
	private User applyUser;// 申请人
	private Date auditDate;// 审核时间
	private User auditUser;// 审核人

	public Long getIssueStepId() {
		return issueStepId;
	}

	public void setIssueStepId(Long issueStepId) {
		this.issueStepId = issueStepId;
	}

	public String getDelayInfo() {
		return delayInfo;
	}

	public void setDelayInfo(String delayInfo) {
		this.delayInfo = delayInfo;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getDelayDate() {
		return delayDate;
	}

	public void setDelayDate(Date delayDate) {
		this.delayDate = delayDate;
	}

	public Integer getDelayWorkday() {
		return delayWorkday;
	}

	public void setDelayWorkday(Integer delayWorkday) {
		this.delayWorkday = delayWorkday;
	}

	public Organization getAuditOrg() {
		return auditOrg;
	}

	public void setAuditOrg(Organization auditOrg) {
		this.auditOrg = auditOrg;
	}

	public Integer getAuditState() {
		return auditState;
	}

	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getPermitDelayDate() {
		return permitDelayDate;
	}

	public void setPermitDelayDate(Date permitDelayDate) {
		this.permitDelayDate = permitDelayDate;
	}

	public Integer getPermitDelayWorkday() {
		return permitDelayWorkday;
	}

	public void setPermitDelayWorkday(Integer permitDelayWorkday) {
		this.permitDelayWorkday = permitDelayWorkday;
	}

	public String getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(String auditInfo) {
		this.auditInfo = auditInfo;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public User getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(User auditUser) {
		this.auditUser = auditUser;
	}

	public User getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(User applyUser) {
		this.applyUser = applyUser;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
}

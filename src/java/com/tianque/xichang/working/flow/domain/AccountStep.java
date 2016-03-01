package com.tianque.xichang.working.flow.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class AccountStep extends BaseDomain {

	/** 台账id(ACCOUNTID) */
	private Long accountId;
	/** 台账类型(ACCOUNTTYPE) */
	private String accountType;
	/** 步骤是否完成 0 否 1 是(ISFINISH) */
	private Boolean isFinish;
	/** 目标处理部门 */
	private Organization targetOrg;
	/** 办结类型 */
	private Integer finishType;
	/** 呈报乡镇 */
	private Integer reportToTownEnd;
	/** 呈报乡镇 */
	private Integer reportToCityEnd;
	/** 流转职能部门 */
	private Integer reportToFunctionEnd;
	/** 区分办结和流转办结 */
	private Integer realOrCirculation;

	private Long orgType;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Boolean getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(Boolean isFinish) {
		this.isFinish = isFinish;
	}

	public Organization getTargetOrg() {
		return targetOrg;
	}

	public void setTargetOrg(Organization targetOrg) {
		this.targetOrg = targetOrg;
	}

	public Integer getFinishType() {
		return finishType;
	}

	public void setFinishType(Integer finishType) {
		this.finishType = finishType;
	}

	public Integer getReportToTownEnd() {
		return reportToTownEnd;
	}

	public void setReportToTownEnd(Integer reportToTownEnd) {
		this.reportToTownEnd = reportToTownEnd;
	}

	public Integer getReportToCityEnd() {
		return reportToCityEnd;
	}

	public void setReportToCityEnd(Integer reportToCityEnd) {
		this.reportToCityEnd = reportToCityEnd;
	}

	public void setReportToFunctionEnd(Integer reportToFunctionEnd) {
		this.reportToFunctionEnd = reportToFunctionEnd;
	}

	public Integer getReportToFunctionEnd() {
		return reportToFunctionEnd;
	}

	public void setRealOrCirculation(Integer realOrCirculation) {
		this.realOrCirculation = realOrCirculation;
	}

	public Integer getRealOrCirculation() {
		return realOrCirculation;
	}

	public Long getOrgType() {
		return orgType;
	}

	public void setOrgType(Long orgType) {
		this.orgType = orgType;
	}

}

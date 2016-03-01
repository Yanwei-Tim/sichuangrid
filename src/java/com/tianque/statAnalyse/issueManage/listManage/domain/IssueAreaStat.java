package com.tianque.statAnalyse.issueManage.listManage.domain;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class IssueAreaStat extends BaseDomain {

	private int year;
	private int month;
	private Long parentOrgId;
	private Organization organization;
	/**
	 * 新增事件数
	 */
	private Integer addIssueSum;
	/**
	 * 上报事件数
	 */
	private Integer submitIssueSum;
	/**
	 * 上级交办事件数
	 */
	private Integer assignIssueSum;
	/**
	 * 在办事件数
	 */
	private Integer doingIssueSum;
	/**
	 * 办结事件数
	 */
	private Integer doneIssueSum;
	/**
	 * 待验证事件数
	 */
	private Integer verificationSum;
	/**
	 * 已验证事件数
	 */
	private Integer verificationedSum;
	/**
	 * 事件总数
	 */
	private Integer issueSum;
	/**
	 * 事件办结率
	 */
	private String completionRate;
	/**
	 * 超期在办数
	 */
	private Integer extendedDoingSum;
	/**
	 * 超期办结数
	 */
	private Integer extendedDoneSum;
	/**
	 * 超期办结率
	 */
	private String extendedRate;

	/**
	 * 民生服务
	 */
	private Integer contradictionSum;
	/**
	 * 矛盾劝解
	 */
	private Integer resolveTheContradictionSum;
	/**
	 * 参与治安防控
	 */
	private Integer securityPrecautionSum;
	/**
	 * 参与特殊人群服务管理
	 */
	private Integer specialPopulationSum;
	/**
	 * 社情民意收集
	 */
	private Integer socialConditionSum;
	/**
	 * 政策法律宣传
	 */
	private Integer policiesAndLawSum;
	/**
	 * 突发事件报告
	 */
	private Integer emergencieSum;
	/**
	 * 其它
	 */
	private Integer otherManageSum;

	/**
	 * 矛盾劝解-普通流程
	 */
	private Integer resolveGeneralSum;
	/**
	 * 矛盾劝解-越级流程
	 */
	private Integer resolveSkipSum;
	/**
	 * 参与治安防控-普通流程
	 */
	private Integer securityGeneralSum;
	/**
	 * 参与治安防控-越级流程
	 */
	private Integer securitySkipSum;
	/**
	 * 突发事件报告-普通流程
	 */
	private Integer emergencieGeneralSum;
	/**
	 * 突发事件报告-越级流程
	 */
	private Integer emergencieSkipSum;
	/**
	 * 其它-普通流程
	 */
	private Integer otherManageGeneralSum;
	/**
	 * 其它-越级流程
	 */
	private Integer otherManageSkipSum;

	private Integer historySum;
	private Integer nowSum;
	/**
	 * 增长率
	 */
	private String growthRate;
	/**
	 * 增长速度
	 */
	private String growthSpeeding;

	private Date findBackDate;// 用于找回之前的老数据,没实际意义

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Integer getDoingIssueSum() {
		return doingIssueSum;
	}

	public void setDoingIssueSum(Integer doingIssueSum) {
		this.doingIssueSum = doingIssueSum;
	}

	public Integer getDoneIssueSum() {
		return doneIssueSum;
	}

	public void setDoneIssueSum(Integer doneIssueSum) {
		this.doneIssueSum = doneIssueSum;
	}

	public Integer getIssueSum() {
		return issueSum;
	}

	public void setIssueSum(Integer issueSum) {
		this.issueSum = issueSum;
	}

	public String getCompletionRate() {
		return completionRate;
	}

	public void setCompletionRate(String completionRate) {
		this.completionRate = completionRate;
	}

	public Integer getExtendedDoingSum() {
		return extendedDoingSum;
	}

	public void setExtendedDoingSum(Integer extendedDoingSum) {
		this.extendedDoingSum = extendedDoingSum;
	}

	public Integer getExtendedDoneSum() {
		return extendedDoneSum;
	}

	public void setExtendedDoneSum(Integer extendedDoneSum) {
		this.extendedDoneSum = extendedDoneSum;
	}

	public String getExtendedRate() {
		return extendedRate;
	}

	public void setExtendedRate(String extendedRate) {
		this.extendedRate = extendedRate;
	}

	public Integer getAddIssueSum() {
		return addIssueSum;
	}

	public void setAddIssueSum(Integer addIssueSum) {
		this.addIssueSum = addIssueSum;
	}

	public Integer getSubmitIssueSum() {
		return submitIssueSum;
	}

	public void setSubmitIssueSum(Integer submitIssueSum) {
		this.submitIssueSum = submitIssueSum;
	}

	public Integer getAssignIssueSum() {
		return assignIssueSum;
	}

	public void setAssignIssueSum(Integer assignIssueSum) {
		this.assignIssueSum = assignIssueSum;
	}

	public Integer getContradictionSum() {
		return contradictionSum;
	}

	public void setContradictionSum(Integer contradictionSum) {
		this.contradictionSum = contradictionSum;
	}

	public Integer getResolveTheContradictionSum() {
		return resolveTheContradictionSum;
	}

	public void setResolveTheContradictionSum(Integer resolveTheContradictionSum) {
		this.resolveTheContradictionSum = resolveTheContradictionSum;
	}

	public Integer getSecurityPrecautionSum() {
		return securityPrecautionSum;
	}

	public void setSecurityPrecautionSum(Integer securityPrecautionSum) {
		this.securityPrecautionSum = securityPrecautionSum;
	}

	public Integer getSpecialPopulationSum() {
		return specialPopulationSum;
	}

	public void setSpecialPopulationSum(Integer specialPopulationSum) {
		this.specialPopulationSum = specialPopulationSum;
	}

	public Integer getSocialConditionSum() {
		return socialConditionSum;
	}

	public void setSocialConditionSum(Integer socialConditionSum) {
		this.socialConditionSum = socialConditionSum;
	}

	public Integer getPoliciesAndLawSum() {
		return policiesAndLawSum;
	}

	public void setPoliciesAndLawSum(Integer policiesAndLawSum) {
		this.policiesAndLawSum = policiesAndLawSum;
	}

	public Integer getEmergencieSum() {
		return emergencieSum;
	}

	public void setEmergencieSum(Integer emergencieSum) {
		this.emergencieSum = emergencieSum;
	}

	public Integer getOtherManageSum() {
		return otherManageSum;
	}

	public void setOtherManageSum(Integer otherManageSum) {
		this.otherManageSum = otherManageSum;
	}

	public Integer getResolveGeneralSum() {
		return resolveGeneralSum;
	}

	public void setResolveGeneralSum(Integer resolveGeneralSum) {
		this.resolveGeneralSum = resolveGeneralSum;
	}

	public Integer getResolveSkipSum() {
		return resolveSkipSum;
	}

	public void setResolveSkipSum(Integer resolveSkipSum) {
		this.resolveSkipSum = resolveSkipSum;
	}

	public Integer getSecurityGeneralSum() {
		return securityGeneralSum;
	}

	public void setSecurityGeneralSum(Integer securityGeneralSum) {
		this.securityGeneralSum = securityGeneralSum;
	}

	public Integer getSecuritySkipSum() {
		return securitySkipSum;
	}

	public void setSecuritySkipSum(Integer securitySkipSum) {
		this.securitySkipSum = securitySkipSum;
	}

	public Integer getEmergencieGeneralSum() {
		return emergencieGeneralSum;
	}

	public void setEmergencieGeneralSum(Integer emergencieGeneralSum) {
		this.emergencieGeneralSum = emergencieGeneralSum;
	}

	public Integer getEmergencieSkipSum() {
		return emergencieSkipSum;
	}

	public void setEmergencieSkipSum(Integer emergencieSkipSum) {
		this.emergencieSkipSum = emergencieSkipSum;
	}

	public Integer getOtherManageGeneralSum() {
		return otherManageGeneralSum;
	}

	public void setOtherManageGeneralSum(Integer otherManageGeneralSum) {
		this.otherManageGeneralSum = otherManageGeneralSum;
	}

	public Integer getOtherManageSkipSum() {
		return otherManageSkipSum;
	}

	public void setOtherManageSkipSum(Integer otherManageSkipSum) {
		this.otherManageSkipSum = otherManageSkipSum;
	}

	public Integer getHistorySum() {
		return historySum;
	}

	public void setHistorySum(Integer historySum) {
		this.historySum = historySum;
	}

	public Integer getNowSum() {
		return nowSum;
	}

	public void setNowSum(Integer nowSum) {
		this.nowSum = nowSum;
	}

	public String getGrowthRate() {
		return growthRate;
	}

	public void setGrowthRate(String growthRate) {
		this.growthRate = growthRate;
	}

	public String getGrowthSpeeding() {
		return growthSpeeding;
	}

	public void setGrowthSpeeding(String growthSpeeding) {
		this.growthSpeeding = growthSpeeding;
	}

	public void setFindBackDate(Date findBackDate) {
		this.findBackDate = findBackDate;
	}

	public Date getFindBackDate() {
		return findBackDate;
	}

	public Integer getVerificationSum() {
		return verificationSum;
	}

	public void setVerificationSum(Integer verificationSum) {
		this.verificationSum = verificationSum;
	}

	public Integer getVerificationedSum() {
		return verificationedSum;
	}

	public void setVerificationedSum(Integer verificationedSum) {
		this.verificationedSum = verificationedSum;
	}

}

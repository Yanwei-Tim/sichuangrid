package com.tianque.usedInfoOptmize.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * @Description:信息系统使用情况报表类（用于周和月的表（临时）用）
 * @author zhangyouwei@hztianque.com
 * @date: 2015-7-1 下午04:23:58
 */
public class UsedInfoData extends BaseDomain {
	private Organization org;
	private Long parentOrgId;
	private Integer activeSum;
	private Integer accountSum;
	private Integer specialCrowdCount;

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public Integer getActiveSum() {
		return activeSum;
	}

	public void setActiveSum(Integer activeSum) {
		this.activeSum = activeSum;
	}

	public Integer getAccountSum() {
		return accountSum;
	}

	public void setAccountSum(Integer accountSum) {
		this.accountSum = accountSum;
	}

	public Integer getSpecialCrowdCount() {
		return specialCrowdCount;
	}

	public void setSpecialCrowdCount(Integer specialCrowdCount) {
		this.specialCrowdCount = specialCrowdCount;
	}
}

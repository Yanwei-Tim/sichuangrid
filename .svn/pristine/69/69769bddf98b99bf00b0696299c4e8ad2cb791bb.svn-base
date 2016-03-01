package com.tianque.partyBuilding.organizations.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

/**
 * 区域联建情况认领
 * */
public class ClaimRegionalBuildInfo extends BaseDomain {

	/** 区域联建情况id */
	private Long regionalBuildInfoId;
	/** 区域联建情况认领单位 */
	private String claimDepartment;
	/** 区域联建情况认领时间 */
	private Date claimDate;

	public Long getRegionalBuildInfoId() {
		return regionalBuildInfoId;
	}

	public void setRegionalBuildInfoId(Long regionalBuildInfoId) {
		this.regionalBuildInfoId = regionalBuildInfoId;
	}

	public String getClaimDepartment() {
		return claimDepartment;
	}

	public void setClaimDepartment(String claimDepartment) {
		this.claimDepartment = claimDepartment;
	}

	public Date getClaimDate() {
		return claimDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}

}

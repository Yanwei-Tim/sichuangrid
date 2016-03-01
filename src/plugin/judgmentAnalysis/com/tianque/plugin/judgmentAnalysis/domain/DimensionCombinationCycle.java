package com.tianque.plugin.judgmentAnalysis.domain;

import java.io.Serializable;

public class DimensionCombinationCycle implements Serializable{
	private Long orgId;
	private String orgCode;
	private String orgName;
	private Long parentOrgId;
	private Long amount;
	private String dimensionCode1;
	private String dimensionName1;
	private String dimensionCode2;
	private String dimensionName2;
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Long getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getDimensionCode1() {
		return dimensionCode1;
	}
	public void setDimensionCode1(String dimensionCode1) {
		this.dimensionCode1 = dimensionCode1;
	}
	public String getDimensionName1() {
		return dimensionName1;
	}
	public void setDimensionName1(String dimensionName1) {
		this.dimensionName1 = dimensionName1;
	}
	public String getDimensionCode2() {
		return dimensionCode2;
	}
	public void setDimensionCode2(String dimensionCode2) {
		this.dimensionCode2 = dimensionCode2;
	}
	public String getDimensionName2() {
		return dimensionName2;
	}
	public void setDimensionName2(String dimensionName2) {
		this.dimensionName2 = dimensionName2;
	}
	
	
}

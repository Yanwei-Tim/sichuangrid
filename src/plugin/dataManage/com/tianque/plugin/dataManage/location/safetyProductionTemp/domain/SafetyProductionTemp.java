package com.tianque.plugin.dataManage.location.safetyProductionTemp.domain;

import java.math.BigDecimal;

import com.tianque.domain.PropertyDict;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;
import com.tianque.plugin.dataManage.location.enterpriseTemp.domain.EnterpriseTemp;

/**
 * 安全生产重点
 */
public class SafetyProductionTemp extends EnterpriseTemp {
	private ClaimDetail claimDetail;
	/** 所属行业 */
	private PropertyDict industry;
	/** 工商执照号码 */
	private String businessLicense;
	/** 面积 */
	private BigDecimal area;
	/**
	 * 注册资金
	 */
	private BigDecimal registeredCapital;
	/**
	 * 员工数量
	 */
	private Long employeeAmount;
	/**
	 * 党员数量
	 */
	private Long partyMemberAmount;
	/**
	 * 企业联系电话
	 */
	private String enterpriseTelephone;
	/**
	 * 企业传真电话
	 */
	private String fax;

	public PropertyDict getIndustry() {
		return industry;
	}

	public void setIndustry(PropertyDict industry) {
		this.industry = industry;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public BigDecimal getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public Long getEmployeeAmount() {
		return employeeAmount;
	}

	public void setEmployeeAmount(Long employeeAmount) {
		this.employeeAmount = employeeAmount;
	}

	public Long getPartyMemberAmount() {
		return partyMemberAmount;
	}

	public void setPartyMemberAmount(Long partyMemberAmount) {
		this.partyMemberAmount = partyMemberAmount;
	}

	public String getEnterpriseTelephone() {
		return enterpriseTelephone;
	}

	public void setEnterpriseTelephone(String enterpriseTelephone) {
		this.enterpriseTelephone = enterpriseTelephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public ClaimDetail getClaimDetail() {
		if (null == claimDetail) {
			claimDetail = new ClaimDetail();
		}
		return claimDetail;
	}

	public void setClaimDetail(ClaimDetail claimDetail) {
		this.claimDetail = claimDetail;
	}

}

package com.tianque.plugin.dataManage.location.neweconomicOrganizationsTemp.domain;

import com.tianque.plugin.dataManage.base.vo.ClaimDetail;
import com.tianque.plugin.dataManage.location.newOrganizationsTemp.domain.NewOrganizationsTempBaseDomain;

/**
 * 新经济组织
 */
public class NewEconomicOrganizationsTemp extends NewOrganizationsTempBaseDomain {
	private ClaimDetail claimDetail;
	/** 营业执照号码 */
	private String licenseNumber;

	/** 传真号码 */
	private String foxNumber;
	/** 面积 */
	private Double area;
	/** 从业人数 */
	private Integer employeeNumber;

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getFoxNumber() {
		return foxNumber;
	}

	public void setFoxNumber(String foxNumber) {
		this.foxNumber = foxNumber;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Integer getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
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

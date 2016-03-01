package com.tianque.plugin.dataManage.location.newSocietyOrganizationsTemp.domain;

import com.tianque.domain.PropertyDict;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;
import com.tianque.plugin.dataManage.location.newOrganizationsTemp.domain.NewOrganizationsTempBaseDomain;

/**
 * 新社会组织
 */
public class NewSocietyOrganizationsTemp extends NewOrganizationsTempBaseDomain {
	private ClaimDetail claimDetail;
	/** 业务主管单位 */
	private String chargeUnit;
	/** 登记证号码 */
	private String registrationNumber;
	/** 成员数 */
	private Long personNum;
	/** 主要职责 */
	private String mainResponsibilities;
	/** 组织子类别 */
	private PropertyDict subType;

	public String getChargeUnit() {
		return chargeUnit;
	}

	public void setChargeUnit(String chargeUnit) {
		this.chargeUnit = chargeUnit;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Long getPersonNum() {
		return personNum;
	}

	public void setPersonNum(Long personNum) {
		this.personNum = personNum;
	}

	public String getMainResponsibilities() {
		return mainResponsibilities;
	}

	public void setMainResponsibilities(String mainResponsibilities) {
		this.mainResponsibilities = mainResponsibilities;
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

	public PropertyDict getSubType() {
		return subType;
	}

	public void setSubType(PropertyDict subType) {
		this.subType = subType;
	}

}

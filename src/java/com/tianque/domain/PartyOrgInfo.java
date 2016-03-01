package com.tianque.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

/**
 * 党组织信息
 */
@SuppressWarnings("serial")
public class PartyOrgInfo extends BaseDomain implements Serializable {

	/** 所属网格 */
	private Organization organization;

	/** 党支部名称 */
	private String partyBranchName;

	/** 党支部书记 */
	private String partyBranchSecretary;

	/** 身份证号 */
	private String idCardNo;

	/** 联系手机 */
	private String mobileNumber;

	/** 联系电话 */
	private String telephone;

	/** 党员人数 */
	private Long partyNumbers;

	/** 党组织地址 */
	private String partyBranchAddr;

	/** 党组织概况 */
	private String remark;

	/** 党支部成立时间 */
	private Date establishedDate;

	public PartyOrgInfo() {
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getPartyBranchName() {
		return partyBranchName;
	}

	public void setPartyBranchName(String partyBranchName) {
		this.partyBranchName = partyBranchName;
	}

	public String getPartyBranchSecretary() {
		return partyBranchSecretary;
	}

	public void setPartyBranchSecretary(String partyBranchSecretary) {
		this.partyBranchSecretary = partyBranchSecretary;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPartyBranchAddr() {
		return partyBranchAddr;
	}

	public Long getPartyNumbers() {
		return partyNumbers;
	}

	public void setPartyNumbers(Long partyNumbers) {
		this.partyNumbers = partyNumbers;
	}

	public void setPartyBranchAddr(String partyBranchAddr) {
		this.partyBranchAddr = partyBranchAddr;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getEstablishedDate() {
		return establishedDate;
	}

	public void setEstablishedDate(Date establishedDate) {
		this.establishedDate = establishedDate;
	}

}

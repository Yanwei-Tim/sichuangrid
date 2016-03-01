package com.tianque.domain.vo;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

@SuppressWarnings("serial")
public class SearchPartyOrgInfoVo extends BaseDomain {

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

	/** 党员人数 从 */
	private Long partyNumbers;

	/** 党员人数到 */
	private Long endPartyNumbers;

	/** 党组织地址 */
	private String partyBranchAddr;

	/** 党支部成立时间 从 */
	private Date establishedDate;

	/** 党支部成立时间 到 */
	private Date endEstablishedDate;

	/** 快速检索关键字 */
	private String fastSearchKeyWords;

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

	public Long getPartyNumbers() {
		return partyNumbers;
	}

	public void setPartyNumbers(Long partyNumbers) {
		this.partyNumbers = partyNumbers;
	}

	public Long getEndPartyNumbers() {
		return endPartyNumbers;
	}

	public void setEndPartyNumbers(Long endPartyNumbers) {
		this.endPartyNumbers = endPartyNumbers;
	}

	public Date getEndEstablishedDate() {
		return endEstablishedDate;
	}

	public void setEndEstablishedDate(Date endEstablishedDate) {
		this.endEstablishedDate = endEstablishedDate;
	}

	public String getPartyBranchAddr() {
		return partyBranchAddr;
	}

	public void setPartyBranchAddr(String partyBranchAddr) {
		this.partyBranchAddr = partyBranchAddr;
	}

	public Date getEstablishedDate() {
		return establishedDate;
	}

	public void setEstablishedDate(Date establishedDate) {
		this.establishedDate = establishedDate;
	}

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
	}
}

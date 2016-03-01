package com.tianque.baseInfo.familyInfo.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/***
 * 家庭信息
 * 
 * @author guwenbiao
 */
public class GroupFamily extends BaseDomain {

	private String familyAccount; // 家庭编号

	private PropertyDict familyRelation; // 与家长关系

	private String familyMaster;// 家长姓名

	private String masterCardNo;// 家长证件号

	private String masterMobileNumber;

	private String masterTelephone;

	private String orgInternalCode;

	private Organization organization;

	private PropertyDict gender;

	private String currentAddress;

	/** 家庭成员个数 */
	private int membersCount;
	/** 全拼 */
	private String fullPinyin;
	/** 简拼 */
	private String simplePinyin;
	/** 家长所属类别 */
	private String populationtype;
	/** 人员ID */
	private Long populationid;

	public Long getPopulationid() {
		return populationid;
	}

	public void setPopulationid(Long populationid) {
		this.populationid = populationid;
	}

	public String getPopulationtype() {
		return populationtype;
	}

	public void setPopulationtype(String populationtype) {
		this.populationtype = populationtype;
	}

	public String getFamilyAccount() {
		return familyAccount;
	}

	public PropertyDict getFamilyRelation() {
		return familyRelation;
	}

	public String getFamilyMaster() {
		return familyMaster;
	}

	public String getMasterCardNo() {
		return masterCardNo;
	}

	public String getMasterMobileNumber() {
		return masterMobileNumber;
	}

	public String getMasterTelephone() {
		return masterTelephone;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public Organization getOrganization() {
		return organization;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setFamilyAccount(String familyAccount) {
		this.familyAccount = familyAccount;
	}

	public void setFamilyRelation(PropertyDict familyRelation) {
		this.familyRelation = familyRelation;
	}

	public void setFamilyMaster(String familyMaster) {
		this.familyMaster = familyMaster;
	}

	public void setMasterCardNo(String masterCardNo) {
		this.masterCardNo = masterCardNo;
	}

	public void setMasterMobileNumber(String masterMobileNumber) {
		this.masterMobileNumber = masterMobileNumber;
	}

	public void setMasterTelephone(String masterTelephone) {
		this.masterTelephone = masterTelephone;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public int getMembersCount() {
		return membersCount;
	}

	public void setMembersCount(int membersCount) {
		this.membersCount = membersCount;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}
	
	public String getEncryptId() {
		String orgCode = this.orgInternalCode;
		if(!StringUtil.isStringAvaliable(orgCode) && organization != null){
			orgCode = this.organization.getOrgInternalCode();
		}
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), orgCode,null);
	}
	
	public String getEncryptPopulationId() {
		String orgCode = this.orgInternalCode;
		if(!StringUtil.isStringAvaliable(orgCode) && organization != null){
			orgCode = this.organization.getOrgInternalCode();
		}
		return BaseDomainIdEncryptUtil.encryptDomainId(getPopulationid(), orgCode,null);
	}
}

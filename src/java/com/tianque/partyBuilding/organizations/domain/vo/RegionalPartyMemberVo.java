package com.tianque.partyBuilding.organizations.domain.vo;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 区域党委成员搜索类
 * */
public class RegionalPartyMemberVo extends BaseDomain {

	/** 组织机构 */
	private Organization organization;
	/** 姓名 */
	private String name;
	/** 性别 */
	private PropertyDict gender;
	/** 区域党委职务 */
	private PropertyDict partyOrgDuty;
	/** 所属单位 */
	private String unit;
	/** 所属单位职务 */
	private String unitDuty;
	/** 联系手机 */
	private String mobileNumber;
	/** 固定电话 */
	private String telephone;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public PropertyDict getPartyOrgDuty() {
		return partyOrgDuty;
	}

	public void setPartyOrgDuty(PropertyDict partyOrgDuty) {
		this.partyOrgDuty = partyOrgDuty;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnitDuty() {
		return unitDuty;
	}

	public void setUnitDuty(String unitDuty) {
		this.unitDuty = unitDuty;
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

}

package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

public class HelpPersonnel extends BaseDomain {

	// 负责人姓名
	private String name;

	// 负责人手机号码
	private String mobile;

	// 负责人联系电话
	private String telephone;

	// 人员Id
	private Long personId;

	// 人员类型
	private String personType;

	private Long status;

	private String fullPinyin;

	private String simplePinyin;

	private String helpPersonnelUuid;

	private String trId;

	private String identity;

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		if (fullPinyin != null && fullPinyin.length() > 30) {
			fullPinyin = fullPinyin.substring(0, 30);
		}
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (simplePinyin != null && simplePinyin.length() > 10) {
			simplePinyin = simplePinyin.substring(0, 10);
		}
		this.simplePinyin = simplePinyin;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHelpPersonnelUuid() {
		return helpPersonnelUuid;
	}

	public void setHelpPersonnelUuid(String helpPersonnelUuid) {
		this.helpPersonnelUuid = helpPersonnelUuid;
	}

	public String getTrId() {
		trId = getId() + "_" + getId();
		return trId;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
}

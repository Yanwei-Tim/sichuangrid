package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

public class PersonInCharges extends BaseDomain {

	// 负责人姓名
	private String name;

	// 负责人手机号码
	private String moblie;

	// 负责人联系电话
	private String telephone;

	// 场所Id
	private Long placeId;

	// 场所类型
	private String placeType;

	private Long status;

	private String fullPinyin;

	private String simplePinyin;

	private String personInChargesUuid;

	private String trId;

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

	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public String getPlaceType() {
		return placeType;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
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

	public String getPersonInChargesUuid() {
		return personInChargesUuid;
	}

	public void setPersonInChargesUuid(String personInChargesUuid) {
		this.personInChargesUuid = personInChargesUuid;
	}

	public String getTrId() {
		trId = getId() + "_" + getId();
		return trId;
	}

}

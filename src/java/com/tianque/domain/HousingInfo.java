package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

public class HousingInfo extends BaseDomain {

	private String housingCode;

	private PropertyDict housingType;

	private Boolean isOwner;

	private String ownerCard;

	private String ownerName;

	private Organization townOrg;

	private Organization org;

	private Boolean state;

	private String personType;

	private Long personId;

	public String getHousingCode() {
		return housingCode;
	}

	public void setHousingCode(String housingCode) {
		this.housingCode = housingCode;
	}

	public PropertyDict getHousingType() {
		return housingType;
	}

	public void setHousingType(PropertyDict housingType) {
		this.housingType = housingType;
	}

	public Boolean getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(Boolean isOwner) {
		this.isOwner = isOwner;
	}

	public String getOwnerCard() {
		return ownerCard;
	}

	public void setOwnerCard(String ownerCard) {
		this.ownerCard = ownerCard;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Organization getTownOrg() {
		return townOrg;
	}

	public void setTownOrg(Organization townOrg) {
		this.townOrg = townOrg;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Boolean getState() {
		return state;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public HousingInfo() {
		super();
	}

	public HousingInfo(String housingCode, PropertyDict housingType, Boolean isOwner,
			String ownerCard, String ownerName, Organization townOrg, Organization org,
			Boolean state, String personType, Long personId) {
		super();
		this.housingCode = housingCode;
		this.housingType = housingType;
		this.isOwner = isOwner;
		this.ownerCard = ownerCard;
		this.ownerName = ownerName;
		this.townOrg = townOrg;
		this.org = org;
		this.state = state;
		this.personType = personType;
		this.personId = personId;
	}

}

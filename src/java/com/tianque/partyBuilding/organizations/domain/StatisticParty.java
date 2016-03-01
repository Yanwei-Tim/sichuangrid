package com.tianque.partyBuilding.organizations.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 区域内主要党组织资源:实体类(PARTYRESOURCE)
 * 
 * @author
 * @date 2014-01-15 09:22:20
 */
public class StatisticParty extends BaseDomain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Organization organization;
	private int townOrgNum;
	private int townMemberNum;
	private int townRecordNum;
	private int townResourceNum;

	private int villageOrgNum;
	private int villageMemberNum;
	private int villageRecordNum;
	private int villageResourceNum;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public int getTownOrgNum() {
		return townOrgNum;
	}

	public void setTownOrgNum(int townOrgNum) {
		this.townOrgNum = townOrgNum;
	}

	public int getTownMemberNum() {
		return townMemberNum;
	}

	public void setTownMemberNum(int townMemberNum) {
		this.townMemberNum = townMemberNum;
	}

	public int getTownRecordNum() {
		return townRecordNum;
	}

	public void setTownRecordNum(int townRecordNum) {
		this.townRecordNum = townRecordNum;
	}

	public int getTownResourceNum() {
		return townResourceNum;
	}

	public void setTownResourceNum(int townResourceNum) {
		this.townResourceNum = townResourceNum;
	}

	public int getVillageOrgNum() {
		return villageOrgNum;
	}

	public void setVillageOrgNum(int villageOrgNum) {
		this.villageOrgNum = villageOrgNum;
	}

	public int getVillageMemberNum() {
		return villageMemberNum;
	}

	public void setVillageMemberNum(int villageMemberNum) {
		this.villageMemberNum = villageMemberNum;
	}

	public int getVillageRecordNum() {
		return villageRecordNum;
	}

	public void setVillageRecordNum(int villageRecordNum) {
		this.villageRecordNum = villageRecordNum;
	}

	public int getVillageResourceNum() {
		return villageResourceNum;
	}

	public void setVillageResourceNum(int villageResourceNum) {
		this.villageResourceNum = villageResourceNum;
	}
}

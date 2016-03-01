package com.tianque.partyBuilding.organizations.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 区域内主要党组织资源:实体类(PARTYRESOURCE)
 * 
 * @author
 * @date 2014-01-15 09:22:20
 */
public class DistrictPartyStatistics extends BaseDomain implements
		java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Organization organization;
	/** 乡镇区域党委成员数 */
	private int townMemberNum;
	/** 乡镇工作动态记录数 */
	private int townActivityRecordNum;
	/** 乡镇主要党组织资源数 */
	private int townPartyResourceNum;
	/** 乡镇区域联建情况数 */
	private int townRegionalBuildInfoNum;
	/** 村社区区域党委成员数 */
	private int villageMemberNum;
	/** 村社区工作动态记录数 */
	private int villageActivityRecordNum;
	/** 村社区主要党组织资源数 */
	private int villagePartyResourceNum;
	/** 村社区区域联建情况数 */
	private int villageRegionalBuildInfoNum;

	public int getTownMemberNum() {
		return townMemberNum;
	}

	public void setTownMemberNum(int townMemberNum) {
		this.townMemberNum = townMemberNum;
	}

	public int getTownActivityRecordNum() {
		return townActivityRecordNum;
	}

	public void setTownActivityRecordNum(int townActivityRecordNum) {
		this.townActivityRecordNum = townActivityRecordNum;
	}

	public int getTownPartyResourceNum() {
		return townPartyResourceNum;
	}

	public void setTownPartyResourceNum(int townPartyResourceNum) {
		this.townPartyResourceNum = townPartyResourceNum;
	}

	public int getVillageMemberNum() {
		return villageMemberNum;
	}

	public void setVillageMemberNum(int villageMemberNum) {
		this.villageMemberNum = villageMemberNum;
	}

	public int getVillageActivityRecordNum() {
		return villageActivityRecordNum;
	}

	public void setVillageActivityRecordNum(int villageActivityRecordNum) {
		this.villageActivityRecordNum = villageActivityRecordNum;
	}

	public int getVillagePartyResourceNum() {
		return villagePartyResourceNum;
	}

	public void setVillagePartyResourceNum(int villagePartyResourceNum) {
		this.villagePartyResourceNum = villagePartyResourceNum;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Organization getOrganization() {
		return organization;
	}

	public int getTownRegionalBuildInfoNum() {
		return townRegionalBuildInfoNum;
	}

	public void setTownRegionalBuildInfoNum(int townRegionalBuildInfoNum) {
		this.townRegionalBuildInfoNum = townRegionalBuildInfoNum;
	}

	public int getVillageRegionalBuildInfoNum() {
		return villageRegionalBuildInfoNum;
	}

	public void setVillageRegionalBuildInfoNum(int villageRegionalBuildInfoNum) {
		this.villageRegionalBuildInfoNum = villageRegionalBuildInfoNum;
	}

}

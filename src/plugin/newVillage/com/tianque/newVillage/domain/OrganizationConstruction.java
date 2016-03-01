package com.tianque.newVillage.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * @ClassName: OrganizationConstruction
 * @Description: 基层组织建设
 */
public class OrganizationConstruction extends BaseDomain {
	private Organization organization;// 组织机构
	private NewVillageBasic newVillageBasic;// 基本信息

	private Integer partyMembers;//党员人数
	
	private Integer isPositionBuilding;// 是否阵地建设(0无 1有)
	private Double threeSatisfaction;// 三务公开群众满意度
	private Double surveySatisfaction;// 调查满意度

	
	public Integer getPartyMembers() {
		return partyMembers==null?0:partyMembers;
	}

	public void setPartyMembers(Integer partyMembers) {
		this.partyMembers = partyMembers;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public NewVillageBasic getNewVillageBasic() {
		return newVillageBasic;
	}

	public void setNewVillageBasic(NewVillageBasic newVillageBasic) {
		this.newVillageBasic = newVillageBasic;
	}

	public Integer getIsPositionBuilding() {
		return isPositionBuilding == null ? 0 : isPositionBuilding;
	}

	public void setIsPositionBuilding(Integer isPositionBuilding) {
		this.isPositionBuilding = isPositionBuilding;
	}

	public Double getThreeSatisfaction() {
		return threeSatisfaction == null ? 0 : threeSatisfaction;
	}

	public void setThreeSatisfaction(Double threeSatisfaction) {
		this.threeSatisfaction = threeSatisfaction;
	}

	public Double getSurveySatisfaction() {
		return surveySatisfaction == null ? 0 : surveySatisfaction;
	}

	public void setSurveySatisfaction(Double surveySatisfaction) {
		this.surveySatisfaction = surveySatisfaction;
	}

}

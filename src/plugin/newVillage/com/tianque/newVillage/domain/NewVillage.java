package com.tianque.newVillage.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * @ClassName: NewVillage
 * @Description: 新农村建设
 */
public class NewVillage extends BaseDomain {
	private Organization organization;// 组织机构
	private NewVillageBasic newVillageBasic;// 基本信息

	private Integer settlementsNumber;// 聚居点数量
	private Integer settlementsBuild;// 新建数

	private Integer settlementsReform;// 改造数据

	private Integer settlementsProtect;// 保护数

	private Integer involvingFarmers;// 涉及农户
	

	private Integer houseCount;// 房屋总数
	private Integer noHouseCount;// 无房户数
	private Integer dangerousHouseCount;// 危房户数
	private Integer housingDifficultCount;// 住房困难户数


	
	private Double  capitaHousingArea;//农村人均安全住房面积
	private Integer lowRentHousing;// 廉租房数
	private Integer inLowRentHousing;//入住廉租房数
	private Integer numberInvolved;//廉租涉及人数
	private Integer isAllStanding;//是否通过新建、改造、保护等形式推进新村建设全覆盖
	
	public Integer getHouseCount() {
		return houseCount==null?0:houseCount;
	}

	public void setHouseCount(Integer houseCount) {
		this.houseCount = houseCount;
	}

	public Integer getNoHouseCount() {
		return noHouseCount==null?0:noHouseCount;
	}

	public void setNoHouseCount(Integer noHouseCount) {
		this.noHouseCount = noHouseCount;
	}

	public Integer getDangerousHouseCount() {
		return dangerousHouseCount==null?0:dangerousHouseCount;
	}

	public void setDangerousHouseCount(Integer dangerousHouseCount) {
		this.dangerousHouseCount = dangerousHouseCount;
	}

	public Integer getHousingDifficultCount() {
		return housingDifficultCount==null?0:housingDifficultCount;
	}

	public void setHousingDifficultCount(Integer housingDifficultCount) {
		this.housingDifficultCount = housingDifficultCount;
	}

	public Integer getIsAllStanding() {
		return isAllStanding==null?0:isAllStanding;
	}

	public void setIsAllStanding(Integer isAllStanding) {
		this.isAllStanding = isAllStanding;
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

	public Integer getSettlementsNumber() {
		return settlementsNumber == null ? 0 : settlementsNumber;
	}

	public void setSettlementsNumber(Integer settlementsNumber) {
		this.settlementsNumber = settlementsNumber;
	}

	public Integer getSettlementsBuild() {
		return settlementsBuild == null ? 0 : settlementsBuild;
	}

	public void setSettlementsBuild(Integer settlementsBuild) {
		this.settlementsBuild = settlementsBuild;
	}

	public Integer getSettlementsReform() {
		return settlementsReform == null ? 0 : settlementsReform;
	}

	public void setSettlementsReform(Integer settlementsReform) {
		this.settlementsReform = settlementsReform;
	}

	public Integer getSettlementsProtect() {
		return settlementsProtect == null ? 0 : settlementsProtect;
	}

	public void setSettlementsProtect(Integer settlementsProtect) {
		this.settlementsProtect = settlementsProtect;
	}

	public Integer getInvolvingFarmers() {
		return involvingFarmers == null ? 0 : involvingFarmers;
	}

	public void setInvolvingFarmers(Integer involvingFarmers) {
		this.involvingFarmers = involvingFarmers;
	}

	public Integer getLowRentHousing() {
		return lowRentHousing == null ? 0 : lowRentHousing;
	}

	public void setLowRentHousing(Integer lowRentHousing) {
		this.lowRentHousing = lowRentHousing;
	}

	public Double getCapitaHousingArea() {
		return capitaHousingArea==null?0:capitaHousingArea;
	}

	public void setCapitaHousingArea(Double capitaHousingArea) {
		this.capitaHousingArea = capitaHousingArea;
	}

	public Integer getInLowRentHousing() {
		return inLowRentHousing==null?0:inLowRentHousing;
	}

	public void setInLowRentHousing(Integer inLowRentHousing) {
		this.inLowRentHousing = inLowRentHousing;
	}

	public Integer getNumberInvolved() {
		return numberInvolved==null?0:numberInvolved;
	}

	public void setNumberInvolved(Integer numberInvolved) {
		this.numberInvolved = numberInvolved;
	}
	
	

}

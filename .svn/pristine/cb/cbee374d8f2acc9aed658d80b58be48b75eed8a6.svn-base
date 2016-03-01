package com.tianque.newVillage.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * @Description: 农民人均可支配收入 DOMAIN
 * @author yangfan
 * @date 2015年10月10日
 */
public class FarmerPerIncomeInfo extends BaseDomain {
	private Organization organization;// 组织机构.
	private NewVillageBasic newVillageBasic;// 基本信息
	
	private Double farmerPerIncome;//农村居民人均可支配收入

	
	private Double agriculturalIncome;//农业主导产业收入
	private Double householdIncome;//农村家庭经营性收入
	private Double villageCollectiveIncome;//村集体经济收入
	
	
	public Double getAgriculturalIncome() {
		return agriculturalIncome==null?0:agriculturalIncome;
	}

	public void setAgriculturalIncome(Double agriculturalIncome) {
		this.agriculturalIncome = agriculturalIncome;
	}

	public Double getHouseholdIncome() {
		return householdIncome==null?0:householdIncome;
	}

	public void setHouseholdIncome(Double householdIncome) {
		this.householdIncome = householdIncome;
	}

	public Double getVillageCollectiveIncome() {
		return villageCollectiveIncome==null?0:villageCollectiveIncome;
	}

	public void setVillageCollectiveIncome(Double villageCollectiveIncome) {
		this.villageCollectiveIncome = villageCollectiveIncome;
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

	public Double getFarmerPerIncome() {
		return farmerPerIncome == null ? 0 : farmerPerIncome;
	}

	public void setFarmerPerIncome(Double farmerPerIncome) {
		this.farmerPerIncome = farmerPerIncome;
	}


	@Override
	public String toString() {
		return "FarmerPerIncomeInfo [organization=" + organization
				+ ", newVillageBasic=" + newVillageBasic + ", farmerPerIncome="
				+ farmerPerIncome + "]";
	}

}

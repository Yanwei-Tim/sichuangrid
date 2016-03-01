package com.tianque.newVillage.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * @Description: 资金投入 实体类
 * @author yangfan
 * @date 2015年10月10日
 */
public class CapitalInvested extends BaseDomain {
	private Organization organization;// 组织机构.
	private NewVillageBasic newVillageBasic;// 基本信息
	
	private Double allInvestmentCount;//概算总投资
	private Double capitalInvestmentCount;//财政资金投入总额
	private Double centralProvinceInvested;// 中央/省份投入资金
	private Double municipalityInvested;// 市投入资金//市/州投入
	private Double countyInvested;// 县资金投入//县级投入
	private Double sociologyInvested;//社会资金投入总额
	private Double financialInvested;// 金融投入资金//金融投入
	private Double industryAndCommerceInvested;// 工商投入资金//工商资本投入
	private Double otherInvested;//其他
	private Double farmerInvested;// 农民自筹资金//农民自筹资金
	

	public Double getAllInvestmentCount() {
		return allInvestmentCount==null?0:allInvestmentCount;
	}

	public void setAllInvestmentCount(Double allInvestmentCount) {
		this.allInvestmentCount = allInvestmentCount;
	}

	public Double getCapitalInvestmentCount() {
		return capitalInvestmentCount==null?0:capitalInvestmentCount;
	}

	public void setCapitalInvestmentCount(Double capitalInvestmentCount) {
		this.capitalInvestmentCount = capitalInvestmentCount;
	}

	public Double getSociologyInvested() {
		return sociologyInvested==null?0:sociologyInvested;
	}

	public void setSociologyInvested(Double sociologyInvested) {
		this.sociologyInvested = sociologyInvested;
	}

	public Double getOtherInvested() {
		return otherInvested==null?0:otherInvested;
	}

	public void setOtherInvested(Double otherInvested) {
		this.otherInvested = otherInvested;
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

	public Double getCentralProvinceInvested() {
		return centralProvinceInvested == null ? 0 : centralProvinceInvested;
	}

	public void setCentralProvinceInvested(Double centralProvinceInvested) {
		this.centralProvinceInvested = centralProvinceInvested;
	}

	public Double getMunicipalityInvested() {
		return municipalityInvested == null ? 0 : municipalityInvested;
	}

	public void setMunicipalityInvested(Double municipalityInvested) {
		this.municipalityInvested = municipalityInvested;
	}

	public Double getCountyInvested() {
		return countyInvested == null ? 0 : countyInvested;
	}

	public void setCountyInvested(Double countyInvested) {
		this.countyInvested = countyInvested;
	}

	public Double getFinancialInvested() {
		return financialInvested == null ? 0 : financialInvested;
	}

	public void setFinancialInvested(Double financialInvested) {
		this.financialInvested = financialInvested;
	}

	public Double getIndustryAndCommerceInvested() {
		return industryAndCommerceInvested == null ? 0
				: industryAndCommerceInvested;
	}

	public void setIndustryAndCommerceInvested(
			Double industryAndCommerceInvested) {
		this.industryAndCommerceInvested = industryAndCommerceInvested;
	}

	public Double getFarmerInvested() {
		return farmerInvested == null ? 0 : farmerInvested;
	}

	public void setFarmerInvested(Double farmerInvested) {
		this.farmerInvested = farmerInvested;
	}

}

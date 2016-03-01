package com.tianque.newVillage.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * @Description: 每年需要审核的新农村基本信息
 * @date 2015年10月12日
 */
public class BasicYearInfo extends BaseDomain implements Cloneable {
	private Organization organization;// 组织机构.
	private NewVillageBasic newVillageBasic;// 基本信息
	private Double totalArea;// 总面积/幅员面积
	private Double cultivatedLandArea;// 耕地面积
	private Double woodlandArea;// 林地面积
	private Double wastelandArea;// 荒地面积
	private Double landTransfer;// 土地流转面积
	private Integer totalHouseholdsNum;// 总户数
	private Integer totalPeopleNum;// 总人数
	private Integer outWorkNum;// 外出务工人数
	
	private Double collectiveFunds;//集体资金
	private Double collectiveAssets;//集体资产折资
	private String assetsDescribe;//村资产描述
	

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

	public Double getTotalArea() {
		return totalArea == null ? 0 : totalArea;
	}

	public void setTotalArea(Double totalArea) {
		this.totalArea = totalArea;
	}

	public Double getCultivatedLandArea() {
		return cultivatedLandArea == null ? 0 : cultivatedLandArea;
	}

	public void setCultivatedLandArea(Double cultivatedLandArea) {
		this.cultivatedLandArea = cultivatedLandArea;
	}

	public Double getWoodlandArea() {
		return woodlandArea == null ? 0 : woodlandArea;
	}

	public void setWoodlandArea(Double woodlandArea) {
		this.woodlandArea = woodlandArea;
	}

	public Double getWastelandArea() {
		return wastelandArea == null ? 0 : wastelandArea;
	}

	public void setWastelandArea(Double wastelandArea) {
		this.wastelandArea = wastelandArea;
	}

	public Double getLandTransfer() {
		return landTransfer == null ? 0 : landTransfer;
	}

	public void setLandTransfer(Double landTransfer) {
		this.landTransfer = landTransfer;
	}

	public Integer getTotalHouseholdsNum() {
		return totalHouseholdsNum == null ? 0 : totalHouseholdsNum;
	}

	public void setTotalHouseholdsNum(Integer totalHouseholdsNum) {
		this.totalHouseholdsNum = totalHouseholdsNum;
	}

	public Integer getTotalPeopleNum() {
		return totalPeopleNum == null ? 0 : totalPeopleNum;
	}

	public void setTotalPeopleNum(Integer totalPeopleNum) {
		this.totalPeopleNum = totalPeopleNum;
	}

	public Integer getOutWorkNum() {
		return outWorkNum == null ? 0 : outWorkNum;
	}

	public void setOutWorkNum(Integer outWorkNum) {
		this.outWorkNum = outWorkNum;
	}

	public Double getCollectiveFunds() {
		return collectiveFunds==null?0:collectiveFunds;
	}

	public void setCollectiveFunds(Double collectiveFunds) {
		this.collectiveFunds = collectiveFunds;
	}

	public Double getCollectiveAssets() {
		return collectiveAssets==null?0:collectiveAssets;
	}

	public void setCollectiveAssets(Double collectiveAssets) {
		this.collectiveAssets = collectiveAssets;
	}


	public String getAssetsDescribe() {
		return assetsDescribe;
	}

	public void setAssetsDescribe(String assetsDescribe) {
		this.assetsDescribe = assetsDescribe;
	}


}

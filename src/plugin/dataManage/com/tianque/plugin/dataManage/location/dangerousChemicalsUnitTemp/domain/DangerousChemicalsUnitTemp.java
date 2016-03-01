package com.tianque.plugin.dataManage.location.dangerousChemicalsUnitTemp.domain;

import com.tianque.core.util.BaseInfoTables;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;
import com.tianque.plugin.dataManage.location.placeCommon.domain.PlaceCommonTemp;

/**
 * 危险化学品单位
 */
public class DangerousChemicalsUnitTemp extends PlaceCommonTemp {

	public DangerousChemicalsUnitTemp() {
		setLocationType(BaseInfoTables.DANGEROUSCHEMICALSUNIT_KEY);
	}

	private ClaimDetail claimDetail;
	/** 联系电话 */
	private String telephone;
	/** 单位类别 */
	private String unitType;
	/** 货物类别 */
	private String commodityType;
	/** 副本许可范围 */
	private String copyScope;
	/** 存储设备 */
	private String storageDevice;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getCommodityType() {
		return commodityType;
	}

	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}

	public String getCopyScope() {
		return copyScope;
	}

	public void setCopyScope(String copyScope) {
		this.copyScope = copyScope;
	}

	public String getStorageDevice() {
		return storageDevice;
	}

	public void setStorageDevice(String storageDevice) {
		this.storageDevice = storageDevice;
	}

	public ClaimDetail getClaimDetail() {
		if (null == claimDetail) {
			claimDetail = new ClaimDetail();
		}
		return claimDetail;
	}

	public void setClaimDetail(ClaimDetail claimDetail) {
		this.claimDetail = claimDetail;
	}

}

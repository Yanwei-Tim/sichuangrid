package com.tianque.baseInfo.actualHouse.domain;

public class PackageHouseInfo {

	private String cName;// 字段中文名称
	private String mainValue;// 住房屋字段信息信息
	private String megerValue;// 需要覆盖房屋字段信息
	private String enName;// 属性字段
	private Long houseInfoId;// 房屋ID
	private Long mainPropertyDictId;// 有字典项属性的ID
	private String mainPropertyName;// 属性字段

	private String isRentalVlaue;

	public String getIsRentalVlaue() {
		return isRentalVlaue;
	}

	public void setIsRentalVlaue(String isRentalVlaue) {
		this.isRentalVlaue = isRentalVlaue;
	}

	private Long megerPropertyDictId;// 有字典项属性的ID

	public Long getMainPropertyDictId() {
		return mainPropertyDictId;
	}

	public void setMainPropertyDictId(Long mainPropertyDictId) {
		this.mainPropertyDictId = mainPropertyDictId;
	}

	public String getMainPropertyName() {
		return mainPropertyName;
	}

	public void setMainPropertyName(String mainPropertyName) {
		this.mainPropertyName = mainPropertyName;
	}

	public Long getMegerPropertyDictId() {
		return megerPropertyDictId;
	}

	public void setMegerPropertyDictId(Long megerPropertyDictId) {
		this.megerPropertyDictId = megerPropertyDictId;
	}

	public Long getHouseInfoId() {
		return houseInfoId;
	}

	public void setHouseInfoId(Long houseInfoId) {
		this.houseInfoId = houseInfoId;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getMainValue() {
		return mainValue;
	}

	public void setMainValue(String mainValue) {
		this.mainValue = mainValue;
	}

	public String getMegerValue() {
		return megerValue;
	}

	public void setMegerValue(String megerValue) {
		this.megerValue = megerValue;
	}
}

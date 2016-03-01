package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

/**
 * 地图楼宇Feature扩展属性
 */
@SuppressWarnings("serial")
public class BuildingData extends BaseDomain {
	/** 楼宇featureId */
	private Long buildingId;
	/** 楼宇feature名称 */
	private String buildingName;
	/** 楼宇feature地址 */
	private String buildingAddress;
	/** 所属责任区 */
	private Organization organization;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 中心坐标 */
	private Long centerX;
	private Long centerY;
	/** 全拼 */
	private String fullPinyin;
	/** 间拼 */
	private String simplePinyin;

	private Integer houseSum;// 住房数量

	public Integer getHouseSum() {
		return houseSum;
	}

	public void setHouseSum(Integer houseSum) {
		this.houseSum = houseSum;
	}

	public Long getCenterX() {
		return centerX;
	}

	public void setCenterX(Long centerX) {
		this.centerX = centerX;
	}

	public Long getCenterY() {
		return centerY;
	}

	public void setCenterY(Long centerY) {
		this.centerY = centerY;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingAddress() {
		return buildingAddress;
	}

	public void setBuildingAddress(String buildingAddress) {
		this.buildingAddress = buildingAddress;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getFullPinyin() {
		if (fullPinyin != null && fullPinyin.length() > 30) {
			return fullPinyin.substring(0, 30);
		}
		return fullPinyin;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		if (simplePinyin != null && simplePinyin.length() > 10) {
			return simplePinyin.substring(0, 10);
		}
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

}
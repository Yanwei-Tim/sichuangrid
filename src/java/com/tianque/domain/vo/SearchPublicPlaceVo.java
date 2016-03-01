package com.tianque.domain.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.Organization;

@SuppressWarnings("serial")
public class SearchPublicPlaceVo extends BaseDomain {

	/** 形式参数,用来接收公共场所名称 **/
	private String unitName;
	private Organization organization;
	/** 所属网格内置码 */
	private String orgInternalCode;
	/** 场所地址 */
	private String placeAddress;
	private String placeName;
	/** 领取执照时间 */
	private Date licenseDate;
	/** 领取执照结束时间 */
	private Date endLicenseDate;
	/** 负责人 */
	private String manager;
	/** 开业时间 */
	private Date openingDate;
	/** 开业结束时间 */
	private Date endOpeningDate;
	/** 公共产所类别 */
	private String category;
	/** 备案登记号码 */
	private String registrationNumber;
	/** 场所等级 */
	private String placeLevel;
	/** 建筑物结构 */
	private String buildingStructure;
	/** 总面积 */
	private Double totalArea;
	/** 最大总面积 */
	private Double endTotalArea;
	/** 营业面积 */
	private Double operationArea;
	/** 最大营业面积 */
	private Double endOperationArea;
	/** 是否有 小件寄存处 */
	private Long cloakroom;
	/** 核定人数 */
	private Long vouchedPeopleCount;
	/** 最大核定人数 */
	private Long endVouchedPeopleCount;
	/** 包间数 */
	private Long privateRoomCount;
	/** 最大包间数 */
	private Long endPrivateRoomCount;
	/** 周围环境 */
	private String surrounding;
	/** 通道口 */
	private String passageway;
	/** 内部结构 */
	private String innerStructure;

	private Boolean isEmphasis;
	private String remark;
	/**
	 * 关注程度
	 */
	private Long attentionExtentId;
	// 是否有治安负责人
	private Long hasServiceTeamMember;
	// 是否有巡场记录
	private Long hasServiceRecord;

	private String importantLocationType;

	public SearchPublicPlaceVo() {
		setImportantLocationType(BaseInfoTables.PUBLICPLACE_KEY);
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

	public String getPlaceAddress() {
		return placeAddress;
	}

	public void setPlaceAddress(String placeAddress) {
		this.placeAddress = placeAddress;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getLicenseDate() {
		return licenseDate;
	}

	public void setLicenseDate(Date licenseDate) {
		this.licenseDate = licenseDate;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getPlaceLevel() {
		return placeLevel;
	}

	public void setPlaceLevel(String placeLevel) {
		this.placeLevel = placeLevel;
	}

	public String getBuildingStructure() {
		return buildingStructure;
	}

	public void setBuildingStructure(String buildingStructure) {
		this.buildingStructure = buildingStructure;
	}

	public Double getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(Double totalArea) {
		this.totalArea = totalArea;
	}

	public Double getOperationArea() {
		return operationArea;
	}

	public void setOperationArea(Double operationArea) {
		this.operationArea = operationArea;
	}

	public Long getCloakroom() {
		return cloakroom;
	}

	public void setCloakroom(Long cloakroom) {
		this.cloakroom = cloakroom;
	}

	public Long getVouchedPeopleCount() {
		return vouchedPeopleCount;
	}

	public void setVouchedPeopleCount(Long vouchedPeopleCount) {
		this.vouchedPeopleCount = vouchedPeopleCount;
	}

	public Long getPrivateRoomCount() {
		return privateRoomCount;
	}

	public void setPrivateRoomCount(Long privateRoomCount) {
		this.privateRoomCount = privateRoomCount;
	}

	public String getSurrounding() {
		return surrounding;
	}

	public void setSurrounding(String surrounding) {
		this.surrounding = surrounding;
	}

	public String getPassageway() {
		return passageway;
	}

	public void setPassageway(String passageway) {
		this.passageway = passageway;
	}

	public String getInnerStructure() {
		return innerStructure;
	}

	public void setInnerStructure(String innerStructure) {
		this.innerStructure = innerStructure;
	}

	public Boolean getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Boolean isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public Date getEndLicenseDate() {
		return endLicenseDate;
	}

	public void setEndLicenseDate(Date endLicenseDate) {
		this.endLicenseDate = endLicenseDate;
	}

	public Date getEndOpeningDate() {
		return endOpeningDate;
	}

	public void setEndOpeningDate(Date endOpeningDate) {
		this.endOpeningDate = endOpeningDate;
	}

	public Double getEndTotalArea() {
		return endTotalArea;
	}

	public void setEndTotalArea(Double endTotalArea) {
		this.endTotalArea = endTotalArea;
	}

	public Double getEndOperationArea() {
		return endOperationArea;
	}

	public void setEndOperationArea(Double endOperationArea) {
		this.endOperationArea = endOperationArea;
	}

	public Long getEndVouchedPeopleCount() {
		return endVouchedPeopleCount;
	}

	public void setEndVouchedPeopleCount(Long endVouchedPeopleCount) {
		this.endVouchedPeopleCount = endVouchedPeopleCount;
	}

	public Long getEndPrivateRoomCount() {
		return endPrivateRoomCount;
	}

	public void setEndPrivateRoomCount(Long endPrivateRoomCount) {
		this.endPrivateRoomCount = endPrivateRoomCount;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Long getAttentionExtentId() {
		return attentionExtentId;
	}

	public void setAttentionExtentId(Long attentionExtentId) {
		this.attentionExtentId = attentionExtentId;
	}

	public Long getHasServiceTeamMember() {
		return hasServiceTeamMember;
	}

	public void setHasServiceTeamMember(Long hasServiceTeamMember) {
		this.hasServiceTeamMember = hasServiceTeamMember;
	}

	public Long getHasServiceRecord() {
		return hasServiceRecord;
	}

	public void setHasServiceRecord(Long hasServiceRecord) {
		this.hasServiceRecord = hasServiceRecord;
	}

	public String getImportantLocationType() {
		return importantLocationType;
	}

	public void setImportantLocationType(String importantLocationType) {
		this.importantLocationType = importantLocationType;
	}

}

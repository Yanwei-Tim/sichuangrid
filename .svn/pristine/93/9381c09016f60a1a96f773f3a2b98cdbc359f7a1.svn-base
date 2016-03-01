package com.tianque.baseInfo.buildDatas.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 楼宇信息表
 */
public class Builddatas extends BaseDomain {
	private static final long serialVersionUID = 1L;

	private String buildingid;
	/** 所属网格(ORGANIZATION) **/
	private Organization organization;

	private String orginternalcode;
	/** 楼宇名称(BUILDINGNAME) **/

	private String buildingname;

	private String buildingaddress;
	/** 业主(OWNER) **/

	private String owner;
	/** 负责人(RESPONSIBLEPERSON) **/

	private String responsibleperson;
	/** 联系电话(PHONE) **/

	private String phone;
	/** 建筑结构(BUILDINGSTRUCTURES) **/
	private PropertyDict buildingstructures;

	private Double centerx;

	private Double centery;
	/** 楼宇类型 */
	private PropertyDict type;

	private Long isLayout;

	private OpenLayersMapInfo openLayersMapInfo;

	private Integer isBind;
	/** 是否有物管 */
	private Boolean isPropertyManagement;

	public PropertyDict getType() {
		return type;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public void setBuildingid(String buildingid) {
		this.buildingid = buildingid;
	}

	public String getBuildingid() {
		return buildingid;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrginternalcode(String orginternalcode) {
		this.orginternalcode = orginternalcode;
	}

	public String getOrginternalcode() {
		return orginternalcode;
	}

	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}

	public String getBuildingname() {
		return buildingname;
	}

	public void setBuildingaddress(String buildingaddress) {
		this.buildingaddress = buildingaddress;
	}

	public String getBuildingaddress() {
		return buildingaddress;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public void setResponsibleperson(String responsibleperson) {
		this.responsibleperson = responsibleperson;
	}

	public String getResponsibleperson() {
		return responsibleperson;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public PropertyDict getBuildingstructures() {
		return buildingstructures;
	}

	public void setBuildingstructures(PropertyDict buildingstructures) {
		this.buildingstructures = buildingstructures;
	}

	public void setCenterx(Double centerx) {
		this.centerx = centerx;
	}

	public Double getCenterx() {
		return centerx;
	}

	public void setCentery(Double centery) {
		this.centery = centery;
	}

	public Double getCentery() {
		return centery;
	}

	public Long getIsLayout() {
		return isLayout;
	}

	public void setIsLayout(Long isLayout) {
		this.isLayout = isLayout;
	}

	public Integer getIsBind() {
		return isBind;
	}

	public void setIsBind(Integer isBind) {
		this.isBind = isBind;
	}

	public OpenLayersMapInfo getOpenLayersMapInfo() {
		return openLayersMapInfo;
	}

	public void setOpenLayersMapInfo(OpenLayersMapInfo openLayersMapInfo) {
		this.openLayersMapInfo = openLayersMapInfo;
	}

	public Boolean getIsPropertyManagement() {
		return isPropertyManagement;
	}

	public void setIsPropertyManagement(Boolean isPropertyManagement) {
		this.isPropertyManagement = isPropertyManagement;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(),
				this.orginternalcode, null);
	}
}

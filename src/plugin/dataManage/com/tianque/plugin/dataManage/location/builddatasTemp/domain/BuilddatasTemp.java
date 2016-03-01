package com.tianque.plugin.dataManage.location.builddatasTemp.domain;

import com.tianque.baseInfo.buildDatas.domain.OpenLayersMapInfo;
import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;

public class BuilddatasTemp extends BaseDomain {
	private ClaimDetail claimDetail;
	private String buildingid;
	/** 所属网格(ORGANIZATION) **/
	private Organization organization;

	private String orgInternalCode;;
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
	private Boolean isPropertyManagement = false;

	public ClaimDetail getClaimDetail() {
		if (null == claimDetail) {
			claimDetail = new ClaimDetail();
		}
		return claimDetail;
	}

	public void setClaimDetail(ClaimDetail claimDetail) {
		this.claimDetail = claimDetail;
	}

	public String getBuildingid() {
		return buildingid;
	}

	public void setBuildingid(String buildingid) {
		this.buildingid = buildingid;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getBuildingname() {
		return buildingname;
	}

	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}

	public String getBuildingaddress() {
		return buildingaddress;
	}

	public void setBuildingaddress(String buildingaddress) {
		this.buildingaddress = buildingaddress;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getResponsibleperson() {
		return responsibleperson;
	}

	public void setResponsibleperson(String responsibleperson) {
		this.responsibleperson = responsibleperson;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public PropertyDict getBuildingstructures() {
		return buildingstructures;
	}

	public void setBuildingstructures(PropertyDict buildingstructures) {
		this.buildingstructures = buildingstructures;
	}

	public Double getCenterx() {
		return centerx;
	}

	public void setCenterx(Double centerx) {
		this.centerx = centerx;
	}

	public Double getCentery() {
		return centery;
	}

	public void setCentery(Double centery) {
		this.centery = centery;
	}

	public PropertyDict getType() {
		return type;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public Long getIsLayout() {
		return isLayout;
	}

	public void setIsLayout(Long isLayout) {
		this.isLayout = isLayout;
	}

	public OpenLayersMapInfo getOpenLayersMapInfo() {
		return openLayersMapInfo;
	}

	public void setOpenLayersMapInfo(OpenLayersMapInfo openLayersMapInfo) {
		this.openLayersMapInfo = openLayersMapInfo;
	}

	public Integer getIsBind() {
		return isBind;
	}

	public void setIsBind(Integer isBind) {
		this.isBind = isBind;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Boolean getIsPropertyManagement() {
		return isPropertyManagement;
	}

	public void setIsPropertyManagement(Boolean isPropertyManagement) {
		this.isPropertyManagement = isPropertyManagement;
	}

}

package com.tianque.openLayersMap.domian.vo;

import java.io.Serializable;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 地图城市部件扩展类(含视频监控)
 * 
 * @author lizhixiang
 * 
 */
@SuppressWarnings("serial")
public class CityComponentsInfoVo implements Serializable {
	/** ID */
	private Long id;
	/** 所属网格 */
	private Organization organization;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 部件类型 */
	private PropertyDict partType;
	/** 部件名称（字典项） */
	private PropertyDict partName;
	/** 名称 */
	private String name;
	/** 部件标识码 */
	private String dustbinCode;
	/** 主管部门 */
	private String deptName;
	/** 权属单位 */
	private String ownershipUnitName;
	/** 养护单位 */
	private String maintenanceUnitName;
	/** 地址 */
	private String address;
	/** 经度 */
	private String lon;
	/** 纬度 */
	private String lat;
	/** 图像路径 */
	private String imgUrl;
	/** 距离 */
	public String distance;
	/** 范围 */
	private Integer range;

	/** 类型名称 */
	private String typeName;

	/** 详情查看url */
	private String viewUrl;
	/** 类型 */
	private String type;

	/** 经度 */
	private String lon2;
	/** 纬度 */
	private String lat2;

	public CityComponentsInfoVo() {
		// TODO Auto-generated constructor stub
	}

	public CityComponentsInfoVo(Long id, String lon, String lat, String lon2,
			String lat2) {
		super();
		this.id = id;
		this.lon = lon;
		this.lat = lat;
		this.lon2 = lon2;
		this.lat2 = lat2;
	}

	public String getLon2() {
		return lon2;
	}

	public void setLon2(String lon2) {
		this.lon2 = lon2;
	}

	public String getLat2() {
		return lat2;
	}

	public void setLat2(String lat2) {
		this.lat2 = lat2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public PropertyDict getPartType() {
		return partType;
	}

	public void setPartType(PropertyDict partType) {
		this.partType = partType;
	}

	public PropertyDict getPartName() {
		return partName;
	}

	public void setPartName(PropertyDict partName) {
		this.partName = partName;
	}

	public String getDustbinCode() {
		return dustbinCode;
	}

	public void setDustbinCode(String dustbinCode) {
		this.dustbinCode = dustbinCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getOwnershipUnitName() {
		return ownershipUnitName;
	}

	public void setOwnershipUnitName(String ownershipUnitName) {
		this.ownershipUnitName = ownershipUnitName;
	}

	public String getMaintenanceUnitName() {
		return maintenanceUnitName;
	}

	public void setMaintenanceUnitName(String maintenanceUnitName) {
		this.maintenanceUnitName = maintenanceUnitName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEncryptId() {
		String orgCode = this.orgInternalCode;
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), orgCode, null);
	}
}

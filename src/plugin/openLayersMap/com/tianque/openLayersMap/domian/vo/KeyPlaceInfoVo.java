package com.tianque.openLayersMap.domian.vo;

import java.io.Serializable;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;

/**
 * 二维地图重点场所数据对象
 * 
 */
@SuppressWarnings("serial")
public class KeyPlaceInfoVo implements Serializable {

	private Long id;

	/** 类型 */
	private String type;

	/** 名称 */
	private String name;

	/** 地址 */
	private String address;

	/** 经度 */
	private String lon;

	/** 纬度 */
	private String lat;

	/** 建筑物Id */
	private Long buildDataId;

	/** 范围 */
	private Integer range;

	/** 距离 */
	public String distance;

	/** 类型名称 */
	private String typeName;

	/** 详情查看url */
	private String viewUrl;

	/** 地图区域对应组织机构 */
	private Organization organization;

	/** 经度 */
	private String lon2;
	/** 纬度 */
	private String lat2;

	/** 楼宇id */
	private Long buildingId;

	private String orgInternalCode;

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

	public KeyPlaceInfoVo() {
	}

	public KeyPlaceInfoVo(Long id, String type, String lon, String lat,
			Long buildDataId, Long buildingId) {
		super();
		this.id = id;
		this.type = type;
		this.lon = lon;
		this.lat = lat;
		this.buildDataId = buildDataId;
		this.buildingId = buildingId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
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

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getBuildDataId() {
		return buildDataId;
	}

	public void setBuildDataId(Long buildDataId) {
		this.buildDataId = buildDataId;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(id, orgInternalCode,
				null);
	}
}

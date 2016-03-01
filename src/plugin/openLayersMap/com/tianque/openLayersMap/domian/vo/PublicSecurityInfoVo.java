package com.tianque.openLayersMap.domian.vo;

import java.io.Serializable;

import com.tianque.domain.Organization;

/**
 * 二维地图 公安部件数据对象
 * 
 */
@SuppressWarnings("serial")
public class PublicSecurityInfoVo implements Serializable {
	private Long id;
	/** 所属网格 */
	private Organization organization;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 位置描述 */
	private String address;
	/** 编号 */
	private String code;
	/** 距离 */
	public String distance;
	/** 类型 */
	public String type;
	/** 类型中文名 */
	public String typeName;
	/** 经度 */
	private String lon;

	/** 纬度 */
	private String lat;
	/** 经度 */
	private String lon2;
	/** 纬度 */
	private String lat2;
	/** 详细Url */
	private String viewUrl;

	public PublicSecurityInfoVo() {
		// TODO Auto-generated constructor stub
	}

	public PublicSecurityInfoVo(Long id, String lon, String lat, String lon2,
			String lat2, String type) {
		super();

		this.id = id;
		this.lon = lon;
		this.lat = lat;
		this.lon2 = lon2;
		this.lat2 = lat2;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

}

package com.tianque.openLayersMap.domian.vo;

import java.io.Serializable;

/**
 * 实体类：地图绑定
 */
@SuppressWarnings("serial")
public class GisBoundVo implements Serializable {
	/** 绑定对象id集合 */
	private String[] ids;
	/** 绑定坐标经度 */
	private String lon;
	/** 绑定坐标纬度 */
	private String lat;
	/** 绑定坐标经度 */
	private String lon2;
	/** 绑定坐标纬度 */
	private String lat2;
	/** 热点id */
	private String featureId;
	/** 地图类型 */
	private String gisType;
	/** 是否进行坐标转换 */
	private Boolean isTransformat = true;

	private Long buildDataId;

	// 房屋分表
	private Long orgId;

	public GisBoundVo() {

	}

	public GisBoundVo(String lon, String lat, String gisType,
			Boolean isTransformat) {
		this.lat = lat;
		this.lon = lon;
		this.gisType = gisType;
		this.isTransformat = isTransformat;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
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

	public String getFeatureId() {
		return featureId;
	}

	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}

	public String getGisType() {
		return gisType;
	}

	public void setGisType(String gisType) {
		this.gisType = gisType;
	}

	public Boolean getIsTransformat() {
		return isTransformat;
	}

	public void setIsTransformat(Boolean isTransformat) {
		this.isTransformat = isTransformat;
	}

	public Long getBuildDataId() {
		return buildDataId;
	}

	public void setBuildDataId(Long buildDataId) {
		this.buildDataId = buildDataId;
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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}

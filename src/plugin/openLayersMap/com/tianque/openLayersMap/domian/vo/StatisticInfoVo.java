package com.tianque.openLayersMap.domian.vo;


import com.tianque.domain.Organization;
import com.tianque.openLayersMap.domian.GisTypeManage;

/**
 * 二维地图	统计信息的实体类
 * @author jiangjinling
 *
 */
public class StatisticInfoVo {
	/** 总数 */
	private Integer sumNum;
	/** 绑定地图数量 */
	private Integer boundMapNum;
	/** 经度 */
	private String lon;
	/** 纬度 */
	private String lat;
	/** 组织机构 */
	private Organization organization;
	/** 类型名称 */
	private String typeName;
	/** 地图区域坐标集合 */
	private String points;
	/** 子类对象 */
	private GisTypeManage gisTypeManage;
	/** 模块名称 */
	private String moduleName;
	
	/** 经度 */
	private String lon2;
	/** 纬度 */
	private String lat2;
	/** 地图区域坐标集合 */
	private String points2;

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
	public String getPoints2() {
		return points2;
	}
	public void setPoints2(String points2) {
		this.points2 = points2;
	}
	public StatisticInfoVo() {
		// TODO Auto-generated constructor stub
	}
	public StatisticInfoVo(Integer sumNum, Integer boundMapNum, String lon, String lat, Organization organization,
			String typeName, String points, String moduleName) {
		super();
		this.sumNum = sumNum;
		this.boundMapNum = boundMapNum;
		this.lon = lon;
		this.lat = lat;
		this.organization = organization;
		this.typeName = typeName;
		this.points = points;
		this.moduleName = moduleName;
	}

	public Integer getSumNum() {
		return sumNum;
	}

	public void setSumNum(Integer sumNum) {
		this.sumNum = sumNum;
	}

	public Integer getBoundMapNum() {
		return boundMapNum;
	}

	public void setBoundMapNum(Integer boundMapNum) {
		this.boundMapNum = boundMapNum;
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

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public GisTypeManage getGisTypeManage() {
		return gisTypeManage;
	}

	public void setGisTypeManage(GisTypeManage gisTypeManage) {
		this.gisTypeManage = gisTypeManage;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}

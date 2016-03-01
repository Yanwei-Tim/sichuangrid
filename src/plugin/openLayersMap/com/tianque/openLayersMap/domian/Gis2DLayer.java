package com.tianque.openLayersMap.domian;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

@SuppressWarnings("serial")
public class Gis2DLayer extends BaseDomain {

	/** 地图区域对应组织机构 */
	private Organization organization;
	/** 地图区域对应组织机构编码 */
	private String orgInternalCode;
	/** 地图区域中心点经度 */
	private String centerX;
	/** 地图区域中心点纬度 */
	private String centerY;
	/** 地图区域层级 */
	private Integer zoom;
	/** 地图区域坐标集合 */
	private String points;
	/** 备注 */
	private String remark;
	private Integer countNum;// 该层级下指定类型的数量
	private String orgName;// 机构名称
	private Integer boundNum;// 一绑定住房数量
	/** 最小经度 */
	private Double minLon;
	/** 最大经度 */
	private Double maxLon;
	/** 最小纬度 */
	private Double minLat;
	/** 最大纬度 */
	private Double maxLat;

	// 2D,25D坐标不能对应，因此保存两份坐标数据
	/** 地图区域中心点经度 */
	private String centerX2;
	/** 地图区域中心点纬度 */
	private String centerY2;
	/** 地图区域坐标集合 */
	private String points2;
	/** 最小经度 */
	private Double minLon2;
	/** 最大经度 */
	private Double maxLon2;
	/** 最小纬度 */
	private Double minLat2;
	/** 最大纬度 */
	private Double maxLat2;
	private String gisType = "3D";
	private Boolean isTransformat = true;

	public Boolean getIsTransformat() {
		return isTransformat;
	}

	public void setIsTransformat(Boolean isTransformat) {
		this.isTransformat = isTransformat;
	}

	public String getGisType() {
		return gisType;
	}

	public void setGisType(String gisType) {
		this.gisType = gisType;
	}

	public Double getMinLon() {
		return minLon;
	}

	public void setMinLon(Double minLon) {
		this.minLon = minLon;
	}

	public Double getMaxLon() {
		return maxLon;
	}

	public void setMaxLon(Double maxLon) {
		this.maxLon = maxLon;
	}

	public Double getMinLat() {
		return minLat;
	}

	public void setMinLat(Double minLat) {
		this.minLat = minLat;
	}

	public Double getMaxLat() {
		return maxLat;
	}

	public void setMaxLat(Double maxLat) {
		this.maxLat = maxLat;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
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

	public String getCenterX() {
		return centerX;
	}

	public void setCenterX(String centerX) {
		this.centerX = centerX;
	}

	public String getCenterY() {
		return centerY;
	}

	public void setCenterY(String centerY) {
		this.centerY = centerY;
	}

	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getBoundNum() {
		return boundNum;
	}

	public void setBoundNum(Integer boundNum) {
		this.boundNum = boundNum;
	}

	public String getCenterX2() {
		return centerX2;
	}

	public void setCenterX2(String centerX2) {
		this.centerX2 = centerX2;
	}

	public String getCenterY2() {
		return centerY2;
	}

	public void setCenterY2(String centerY2) {
		this.centerY2 = centerY2;
	}

	public String getPoints2() {
		return points2;
	}

	public void setPoints2(String points2) {
		this.points2 = points2;
	}

	public Double getMinLon2() {
		return minLon2;
	}

	public void setMinLon2(Double minLon2) {
		this.minLon2 = minLon2;
	}

	public Double getMaxLon2() {
		return maxLon2;
	}

	public void setMaxLon2(Double maxLon2) {
		this.maxLon2 = maxLon2;
	}

	public Double getMinLat2() {
		return minLat2;
	}

	public void setMinLat2(Double minLat2) {
		this.minLat2 = minLat2;
	}

	public Double getMaxLat2() {
		return maxLat2;
	}

	public void setMaxLat2(Double maxLat2) {
		this.maxLat2 = maxLat2;
	}

}

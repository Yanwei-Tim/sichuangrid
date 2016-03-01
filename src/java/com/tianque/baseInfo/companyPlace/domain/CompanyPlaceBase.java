package com.tianque.baseInfo.companyPlace.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 单位场所基础信息
 * */
public class CompanyPlaceBase extends BaseDomain {
	/** 组织机构ID */
	private Organization org;
	private Organization organization;
	/** 组织机构内码 */
	private String orginternalcode;
	/** 名称 */
	private String name;
	/** 关注度,字典 */
	private PropertyDict attentionextent;
	/** 地址 */
	private String address;
	/** 经度(三维) */
	private String centerLon;
	/** 纬度(三维) */
	private String centerLat;
	/** 经度(二维) */
	private String centerLon2;
	/** 纬度(二维) */
	private String centerLat2;
	/** 热点ID */
	private String featureId;
	/** 楼宇id */
	private Long buildDataId;
	/** 是否关注 */
	private Integer isEmphasis;
	/** 关注日期 */
	private Date isEmphasisDate;
	/** 关注原因 */
	private String isEmphasisReason;
	/** 数据来源（手机，pc） */
	private Long pcOrMobile;

	/** 图片地址 */
	private String imgUrl;

	public Organization getOrg() {
		return null != org ? org : organization;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getOrginternalcode() {
		return orginternalcode;
	}

	public void setOrginternalcode(String orginternalcode) {
		this.orginternalcode = orginternalcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PropertyDict getAttentionextent() {
		return attentionextent;
	}

	public void setAttentionextent(PropertyDict attentionextent) {
		this.attentionextent = attentionextent;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Integer isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public String getCenterLon() {
		return centerLon;
	}

	public void setCenterLon(String centerLon) {
		this.centerLon = centerLon;
	}

	public String getCenterLat() {
		return centerLat;
	}

	public void setCenterLat(String centerLat) {
		this.centerLat = centerLat;
	}

	public String getCenterLon2() {
		return centerLon2;
	}

	public void setCenterLon2(String centerLon2) {
		this.centerLon2 = centerLon2;
	}

	public String getCenterLat2() {
		return centerLat2;
	}

	public void setCenterLat2(String centerLat2) {
		this.centerLat2 = centerLat2;
	}

	public String getFeatureId() {
		return featureId;
	}

	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}

	public Long getBuildDataId() {
		return buildDataId;
	}

	public void setBuildDataId(Long buildDataId) {
		this.buildDataId = buildDataId;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getIsEmphasisDate() {
		return isEmphasisDate;
	}

	public void setIsEmphasisDate(Date isEmphasisDate) {
		this.isEmphasisDate = isEmphasisDate;
	}

	public String getIsEmphasisReason() {
		return isEmphasisReason;
	}

	public void setIsEmphasisReason(String isEmphasisReason) {
		this.isEmphasisReason = isEmphasisReason;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getPcOrMobile() {
		return pcOrMobile;
	}

	public void setPcOrMobile(Long pcOrMobile) {
		this.pcOrMobile = pcOrMobile;
	}

	public Organization getOrganization() {
		return null == organization ? org : organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
package com.tianque.plugin.dataManage.location.newOrganizationsTemp.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.domain.PropertyDict;
import com.tianque.plugin.dataManage.location.allPlaceCommonTemp.domain.AllPlaceCommonTemp;

/**
 * 两新组织的公有属性类
 */
public class NewOrganizationsTempBaseDomain extends AllPlaceCommonTemp {

	/** 类别 */
	private PropertyDict type;
	/** 联系手机 */
	private String mobileNumber;
	/** 固定电话 */
	private String telephone;
	/** 党员人数 */
	private Integer partyNum;
	/** 简介 */
	private String introduction;
	/** 获得荣耀 */
	private String honor;
	/** 有效期开始日期 */
	private Date validityStartDate;
	/** 有效期结束日期 */
	private Date validityEndDate;

	/** 图片路径 */
	private String imgUrl;

	public PropertyDict getType() {
		return type;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getPartyNum() {
		return partyNum;
	}

	public void setPartyNum(Integer partyNum) {
		this.partyNum = partyNum;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getHonor() {
		return honor;
	}

	public void setHonor(String honor) {
		this.honor = honor;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getValidityStartDate() {
		return validityStartDate;
	}

	public void setValidityStartDate(Date validityStartDate) {
		this.validityStartDate = validityStartDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getValidityEndDate() {
		return validityEndDate;
	}

	public void setValidityEndDate(Date validityEndDate) {
		this.validityEndDate = validityEndDate;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}

package com.tianque.partyBuilding.baseInfos.domain;

import java.io.Serializable;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 图片上传表:实体类(CASEIMAGEUPLOAD)
 * 
 * @author
 */
public class CaseImageUpload extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 所属网格(ORGID) */
	private Organization organization;
	/** 所属网格编号(ORGINTERNALCODE) */
	private String orgInternalCode;
	/** 图片地址(IMGURL) */
	private String imgUrl;
	private boolean isSuccess = true;

	/** 基本情况类型 */
	private String baseInfoType;

	public CaseImageUpload() {
		super();
	}

	public CaseImageUpload(Organization organization, String orgInternalCode, String imgUrl) {
		super();
		this.organization = organization;
		this.orgInternalCode = orgInternalCode;
		this.imgUrl = imgUrl;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getBaseInfoType() {
		return baseInfoType;
	}

	public void setBaseInfoType(String baseInfoType) {
		this.baseInfoType = baseInfoType;
	}

}

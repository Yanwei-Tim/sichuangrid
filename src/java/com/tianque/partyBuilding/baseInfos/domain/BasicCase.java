package com.tianque.partyBuilding.baseInfos.domain;

import java.util.ArrayList;
import java.util.List;

import com.tianque.domain.Organization;

/**
 * 基本情况表:实体类(BASIC_CASE)
 * 
 * @author
 * @date 2014-01-14 15:32:52
 */
public class BasicCase extends com.tianque.core.base.BaseDomain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 所属网格(ORGID) */
	private Organization organization;
	/** 所属网格编号(ORGINTERNALCODE) */
	private String orgInternalCode;
	/** 基本情况图片地址(IMGURL) */
	private String imgUrl;
	/** 基本情况(BASECASE) */
	private String baseCase;
	/** 基本情况类型 */
	private String baseInfoType;

	private List<CaseImageUpload> caseImageUploads = new ArrayList<CaseImageUpload>();
	private List<PartyworkMembers> partyworkMembers = new ArrayList<PartyworkMembers>();

	public BasicCase() {

	}

	public BasicCase(Organization organization, String orgInternalCode, String imgUrl, String baseCase,
			List<CaseImageUpload> caseImageUploads) {
		this.organization = organization;
		this.orgInternalCode = orgInternalCode;
		this.imgUrl = imgUrl;
		this.baseCase = baseCase;
		this.caseImageUploads = caseImageUploads;
	}

	/** get 所属网格(orgId) */
	public Organization getOrganization() {
		return organization;
	}

	/** set 所属网格(ORGID) */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/** get 所属网格编号(orgInternalCode) */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	/** set 所属网格编号(ORGINTERNALCODE) */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	/** get 基本情况图片地址(imgUrl) */
	public String getImgUrl() {
		return imgUrl;
	}

	/** set 基本情况图片地址(IMGURL) */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/** get 基本情况(baseCase) */
	public String getBaseCase() {
		return baseCase;
	}

	/** set 基本情况(BASECASE) */
	public void setBaseCase(String baseCase) {
		this.baseCase = baseCase;
	}

	public List<CaseImageUpload> getCaseImageUploads() {
		return caseImageUploads;
	}

	public void setCaseImageUploads(List<CaseImageUpload> caseImageUploads) {
		this.caseImageUploads = caseImageUploads;
	}

	public String getBaseInfoType() {
		return baseInfoType;
	}

	public void setBaseInfoType(String baseInfoType) {
		this.baseInfoType = baseInfoType;
	}

	public List<PartyworkMembers> getPartyworkMembers() {
		return partyworkMembers;
	}

	public void setPartyworkMembers(List<PartyworkMembers> partyworkMembers) {
		this.partyworkMembers = partyworkMembers;
	}

}

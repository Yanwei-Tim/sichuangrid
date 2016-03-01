package com.tianque.openLayersMap.domian.vo;

import java.io.Serializable;
import java.util.List;

import com.tianque.domain.PropertyDict;

/**
 * 二维地图 人口信息的实体类
 * 
 * @author jiangjinling
 * 
 */
@SuppressWarnings("serial")
public class PersonInfoVo implements Serializable {
	private Long id;

	/** 名称 */
	private String name;

	/** 身份证号 */
	private String idCardNo;

	/** 性别 */
	private String gender;

	/** 地址 */
	private String address;

	/** 经度 */
	private String lon;

	/** 纬度 */
	private String lat;

	/** 人员类型 */
	private String personType;

	/** 人员类型中文名 */
	private String personTypeName;

	/** 证件类型 */
	private String certificateType;

	/** 详情Url */
	private String viewUrl;

	/** 姓名（境外人员的姓名） */
	private String englishName;

	/** 关注人群 */
	private String attentionCrowd;

	/** 关注人群 List */
	private List<AttentionCrowdTypeVo> attentionCrowdList;
	
	private PropertyDict genderId;
	
	private PropertyDict certificateTypeId;

	
	/** 经度 */
	private String lon2;
	/** 纬度 */
	private String lat2;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getPersonTypeName() {
		return personTypeName;
	}

	public void setPersonTypeName(String personTypeName) {
		this.personTypeName = personTypeName;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
	public PropertyDict getGenderId() {
		return genderId;
	}

	public void setGenderId(PropertyDict genderId) {
		this.genderId = genderId;
	}

	public String getAttentionCrowd() {
		StringBuffer buf = new StringBuffer();
		if(attentionCrowdList==null||attentionCrowdList.size()==0){
			return attentionCrowd;
		}else {
			for(int i=0;i<attentionCrowdList.size();i++){
				AttentionCrowdTypeVo attentionCrowdTypeVo = attentionCrowdList.get(i);
				buf.append(attentionCrowdTypeVo.getAttentionCrowdName()).append(",");
			}
			return buf.deleteCharAt(buf.length()-1).toString();
		}
		
	}

	public void setAttentionCrowd(String attentionCrowd) {
		this.attentionCrowd = attentionCrowd;
	}

	public List<AttentionCrowdTypeVo> getAttentionCrowdList() {
		return attentionCrowdList;
	}

	public void setAttentionCrowdList(List<AttentionCrowdTypeVo> attentionCrowdList) {
		this.attentionCrowdList = attentionCrowdList;
	}

	public PropertyDict getCertificateTypeId() {
		return certificateTypeId;
	}

	public void setCertificateTypeId(PropertyDict certificateTypeId) {
		this.certificateTypeId = certificateTypeId;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}
	
}

package com.tianque.domain;

import java.io.Serializable;
import java.util.Date;

import com.tianque.baseInfo.buildDatas.domain.OpenLayersMapInfo;
import com.tianque.core.base.BaseDomain;

public class KeyPlace extends BaseDomain implements Serializable {
	private Long id_key;
	private Long id;
	private Long orgId;
	private String name;
	private String fullPinyin;
	private String simplePinyin;
	private String address;
	private Date createDate;
	private String orgInternalCode;
	private String type;
	private String buildDatasId;
	/** 场所负责人 */
	private String chargePerson;

	/** 是否关注 */
	private Long emphasis;

	private OpenLayersMapInfo openLayersMapInfo;

	public Long getId_key() {
		return id_key;
	}

	public void setId_key(Long idKey) {
		id_key = idKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public KeyPlace() {
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

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBuildDatasId() {
		return buildDatasId;
	}

	public void setBuildDatasId(String buildDatasId) {
		this.buildDatasId = buildDatasId;
	}

	public String getChargePerson() {
		return chargePerson;
	}

	public void setChargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
	}

	public void setEmphasis(Long emphasis) {
		this.emphasis = emphasis;
	}

	public Long getEmphasis() {
		return emphasis;
	}

	public OpenLayersMapInfo getOpenLayersMapInfo() {
		return openLayersMapInfo;
	}

	public void setOpenLayersMapInfo(OpenLayersMapInfo openLayersMapInfo) {
		this.openLayersMapInfo = openLayersMapInfo;
	}
}
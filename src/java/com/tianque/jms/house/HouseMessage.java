package com.tianque.jms.house;

import java.io.Serializable;

import com.tianque.jms.OperateMode;

public class HouseMessage implements Serializable {

	private Long orgId;
	private String idCardNo;
	private Long houseId;
	private OperateMode mode;
	private String populationType;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public OperateMode getMode() {
		return mode;
	}

	public void setMode(OperateMode mode) {
		this.mode = mode;
	}

}

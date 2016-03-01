package com.tianque.jms.msg;

import com.tianque.jms.constants.JmsMessageType;

public class PopulationMsg extends BaseMsg {

	private static final long serialVersionUID = 1L;
	private String idCardNo;
	private Long baseinfoId;
	private Long addressId;
	private String createUser;
	private String type;

	public PopulationMsg() {
		setMsgType("population");
		setJmsMessageType(JmsMessageType.POPULATION_JMS_TYPE);
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getBaseinfoId() {
		return baseinfoId;
	}

	public void setBaseinfoId(Long baseinfoId) {
		this.baseinfoId = baseinfoId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

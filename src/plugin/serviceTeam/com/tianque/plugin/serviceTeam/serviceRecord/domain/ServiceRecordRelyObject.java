package com.tianque.plugin.serviceTeam.serviceRecord.domain;

public class ServiceRecordRelyObject {

	private Long id;
	/** 记录id **/
	private Long recordId;
	/** 对象类型 **/
	private String objectType;
	/** 对象id **/
	private Long objectId;
	/** 对象名称 **/
	private String objectName;
	/**删除基础数据的orgid*/
	private Long orgId;
	/**删除基础数据的orgid*/
	private String cardNoOrName;

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getCardNoOrName() {
		return cardNoOrName;
	}

	public void setCardNoOrName(String cardNoOrName) {
		this.cardNoOrName = cardNoOrName;
	}

}

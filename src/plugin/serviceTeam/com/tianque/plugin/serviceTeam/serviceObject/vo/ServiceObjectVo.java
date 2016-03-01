package com.tianque.plugin.serviceTeam.serviceObject.vo;

import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class ServiceObjectVo extends BaseDomain {
	/** 对象id **/
	private Long objectId;
	/** 对象大分类 **/
	private String objectBigType;
	/** 对象具体分类 **/
	private String objectType;
	/** 对象具体分类中文名 **/
	private String objectTypeCname;
	/** 组织机构对象 **/
	private Organization organization;
	/** 数据库表名 **/
	private String tableName;
	/** 服务成员Id **/
	private Long memberId;
	/** 服务成员在团队中的ids **/
	private List<Long> memberIds;
	/** ---------------人员搜索字段-------------- **/
	/** 姓名 **/
	private String name;
	/** 证件号 **/
	private String idCardNo;
	/** ---------------场所搜索字段-------------- **/
	/** 场所名 **/
	private String locationName;
	/** 场所名在数据库中的字段名 **/
	private String searchName;
	/** 为三个重点设置的值，用来区分在同一张数据库表格中搜出三个中的哪一个 */
	private String keyType;
	/** ---------------房屋搜索字段-------------- **/
	/** 房屋地址 **/
	private String houseAddress;

	/** -------------address收索的列----------------------- **/
	/**
	 * 数据库中存地址的列
	 */
	private String addressColumn;

	public String getObjectBigType() {
		return objectBigType;
	}

	public void setObjectBigType(String objectBigType) {
		this.objectBigType = objectBigType;
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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
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

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getObjectTypeCname() {
		return objectTypeCname;
	}

	public void setObjectTypeCname(String objectTypeCname) {
		this.objectTypeCname = objectTypeCname;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getAddressColumn() {
		return addressColumn;
	}

	public void setAddressColumn(String addressColumn) {
		this.addressColumn = addressColumn;
	}

	public List<Long> getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(List<Long> memberIds) {
		this.memberIds = memberIds;
	}

}

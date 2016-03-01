package com.tianque.domain.vo;

import com.tianque.core.base.BaseDomain;

public class SearchEstateInformationVo extends BaseDomain {
	/**
	 * 所属网格编号
	 */
	private String orgInternalCode;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 身份证号
	 */
	private String idCardNo;
	/**
	 * 房产证号
	 */
	private String proprietaryNo;
	/**
	 * 房产地址
	 */
	private String housePropertyPlace;

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
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

	public String getProprietaryNo() {
		return proprietaryNo;
	}

	public void setProprietaryNo(String proprietaryNo) {
		this.proprietaryNo = proprietaryNo;
	}

	public String getHousePropertyPlace() {
		return housePropertyPlace;
	}

	public void setHousePropertyPlace(String housePropertyPlace) {
		this.housePropertyPlace = housePropertyPlace;
	}

}

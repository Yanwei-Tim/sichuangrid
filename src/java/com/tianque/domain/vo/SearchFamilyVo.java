package com.tianque.domain.vo;

public class SearchFamilyVo {
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 户口号 */
	private Long yhh;
	/** 户主姓名 */
	private String headName;
	/** 户主身份证号 */
	private String headIdCardNo;
	/** 家庭电话 */
	private String telephone;
	/** 户籍地址 */
	private String houseAddress;

	public Long getYhh() {
		return yhh;
	}

	public void setYhh(Long yhh) {
		this.yhh = yhh;
	}

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public String getHeadIdCardNo() {
		return headIdCardNo;
	}

	public void setHeadIdCardNo(String headIdCardNo) {
		this.headIdCardNo = headIdCardNo;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

}

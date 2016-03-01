package com.tianque.plugin.serviceTeam.serviceObject.domain;

import java.util.Date;

public class SearchServiceObjectVo implements Comparable<SearchServiceObjectVo> {

	// /* 对象大类 */
	// private Long logId;
	/* 所属网格name */
	private String orgName;
	/* 所属网格name */
	private Long orgId;
	/* 所属网格name */
	private String orgInternalCode;
	/* 身份证 */
	private Long idCardNumber;
	/* 姓名 */
	private String name;
	/* 出生日期 从 */
	private Date startDate;
	/* 出生日期 到 */
	private Date endDate;
	/* 性别 */
	private Long genderId;
	/* 现居地 */
	private String address;
	/* 手机 */
	private Long phoneNumber;
	/* 人员类型 */
	private String personType;
	/* 人员业务大类型 */
	private String populationBigType;
	/* 人员业务子类型Id */
	private Long populationId;

	/* 人员业务子类型 */
	private String populationType;

	/** 注销（关注）状态 */
	private Long logOut;
	/** 注销（关注）字段名称 */
	private String column;

	// 服务组织Id
	private Long serviceTeamId;

	public SearchServiceObjectVo() {
		super();
	}

	public SearchServiceObjectVo(Long populationId, String name, String populationType) {
		super();
		this.populationId = populationId;
		this.name = name;
		this.populationType = populationType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Long getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(Long idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getGenderId() {
		return genderId;
	}

	public void setGenderId(Long genderId) {
		this.genderId = genderId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getPopulationBigType() {
		return populationBigType;
	}

	public void setPopulationBigType(String populationBigType) {
		this.populationBigType = populationBigType;
	}

	public Long getPopulationId() {
		return populationId;
	}

	public void setPopulationId(Long populationId) {
		this.populationId = populationId;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public Long getServiceTeamId() {
		return serviceTeamId;
	}

	public void setServiceTeamId(Long serviceTeamId) {
		this.serviceTeamId = serviceTeamId;
	}

	@Override
	public int compareTo(SearchServiceObjectVo o) {
		if (this.populationType.equals(o.populationType)) {
			return (int) (this.populationId - o.populationId);
		} else
			return this.populationType.hashCode() - o.populationType.hashCode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((populationId == null) ? 0 : populationId.hashCode());
		result = prime * result + ((populationType == null) ? 0 : populationType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchServiceObjectVo other = (SearchServiceObjectVo) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (populationId == null) {
			if (other.populationId != null)
				return false;
		} else if (!populationId.equals(other.populationId))
			return false;
		if (populationType == null) {
			if (other.populationType != null)
				return false;
		} else if (!populationType.equals(other.populationType))
			return false;
		return true;
	}

	public Long getLogOut() {
		return logOut;
	}

	public void setLogOut(Long logOut) {
		this.logOut = logOut;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

}

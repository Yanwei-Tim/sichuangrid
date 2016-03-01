package com.tianque.plugin.dataManage;

import java.io.Serializable;

import com.tianque.domain.Organization;

public class TargetDataVo implements Serializable {
	/** 查询 网格 */
	private Organization organization;
	/** 唯一性的查询条件，人口是身份证，场所是场所名称 */
	private String searchKey;
	/** 数据的注销，关注等状态 */
	private Long logout;
	/** 数据的id */
	private Long id;
	private Long hasRepeatActualPopu;

	public TargetDataVo() {
		super();
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TargetDataVo(Organization organization, String searchKey, Long logout, Long id) {
		super();
		this.organization = organization;
		this.searchKey = searchKey;
		this.logout = logout;
		this.id = id;
	}

	public Long getLogout() {
		return logout;
	}

	public void setLogout(Long logout) {
		this.logout = logout;
	}

	public Long getHasRepeatActualPopu() {
		return hasRepeatActualPopu;
	}

	public void setHasRepeatActualPopu(Long hasRepeatActualPopu) {
		this.hasRepeatActualPopu = hasRepeatActualPopu;
	}

}

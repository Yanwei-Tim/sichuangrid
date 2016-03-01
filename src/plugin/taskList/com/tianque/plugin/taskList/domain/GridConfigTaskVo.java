package com.tianque.plugin.taskList.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 
 * @author pengle
 * 
 */
public class GridConfigTaskVo extends BaseDomain {
	/**
	 * 职能部门配置实体类
	 */
	private GridConfigTask gridConfigTask;
	/**
	 * 组织机构
	 */
	private Organization organization;
	/**
	 * 职能部门是否进行过配置清单
	 */
	private Boolean isHasConfig;
	private PropertyDict orgType;
	private PropertyDict orgLevel;
	public GridConfigTask getGridConfigTask() {
		return gridConfigTask;
	}
	public void setGridConfigTask(GridConfigTask gridConfigTask) {
		this.gridConfigTask = gridConfigTask;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Boolean getIsHasConfig() {
		return isHasConfig;
	}
	public void setIsHasConfig(Boolean isHasConfig) {
		this.isHasConfig = isHasConfig;
	}
	public PropertyDict getOrgType() {
		return organization.getOrgType();
	}
	public PropertyDict getOrgLevel() {
		return organization.getOrgLevel();
	}
}

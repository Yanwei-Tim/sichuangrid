package com.tianque.newVillage.dao;

import com.tianque.newVillage.domain.OrganizationConstruction;

/**
 * @ClassName: OrganizationConstructionDao
 * @Description: 基层组织
 */
public interface OrganizationConstructionDao {
	/**
	 * 增加基层组织 数据
	 * 
	 * @param orgConstruction
	 * @return OrganizationConstruction
	 */
	public OrganizationConstruction addOrgConstruction(
			OrganizationConstruction orgConstruction);

	/**
	 * 根据id获得 基层组织数据
	 * 
	 * @param id
	 * @return OrganizationConstruction
	 */
	public OrganizationConstruction getOrgConstructionById(Long id);

	/**
	 * 根据id删除基层组织数据
	 * 
	 * @param id
	 */
	public void deleteOrgConstructionById(String[] id);

	/**
	 * 修改基层组织数据
	 * 
	 * @param orgConstruction
	 * @return OrganizationConstruction
	 */
	public OrganizationConstruction updateOrgConstruction(
			OrganizationConstruction orgConstruction);

	/***
	 * 根据basicId和year quarter查询数据
	 */
	public OrganizationConstruction getOrgConstructionByBasicId(Long basicId);
}

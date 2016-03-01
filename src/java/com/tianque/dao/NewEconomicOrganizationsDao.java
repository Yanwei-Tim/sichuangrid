package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.NewEconomicOrganizations;
import com.tianque.domain.vo.SearchNewEconomicOrganizationsVo;

public interface NewEconomicOrganizationsDao {

	public NewEconomicOrganizations addNewEconomicOrganizations(
			NewEconomicOrganizations newEconomicOrganizations);

	public PageInfo<NewEconomicOrganizations> findNewEconomicOrganizationsForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis);

	public NewEconomicOrganizations updateNewEconomicOrganizations(
			NewEconomicOrganizations newEconomicOrganizations);

	public NewEconomicOrganizations getNewEconomicOrganizationsById(Long id);

	public void deleteNewEconomicOrganizations(Long id);

	public void deleteNewEconomicOrganizationsForMore(Long[] ids);

	public PageInfo<NewEconomicOrganizations> searchNewEconomicOrganizationss(
			Integer pageNum, Integer pageSize,
			SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo);

	public List<NewEconomicOrganizations> searchAllNewEconomicOrganizationss(
			SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo);

	/**
	 * 根据name或者licenseNumber来查询所有信息
	 * 
	 * @param name
	 * @param licenseNumber
	 * @param orgId
	 * @return
	 */
	public List<NewEconomicOrganizations> getNewEconomicOrganizationsByNameAndLicenseNumberAndOrgId(
			String name, String licenseNumber, Long orgId);

	/**
	 * 根据name来查询所有信息
	 * 
	 * @param name
	 * @param orgId
	 * @return
	 */
	public NewEconomicOrganizations getNewEconomicOrganizationsByNameAndOrgId(
			String name, Long orgId);

	/**
	 * 根据licenseNumber来查询所有信息
	 * 
	 * @param licenseNumber
	 * @param orgId
	 * @return
	 */
	public NewEconomicOrganizations getNewEconomicOrganizationsByLicenseNumberAndOrgId(
			String licenseNumber, Long orgId);

	public void updateEmphasiseById(Long id, Long isEmphasis,
			String logOutReason);

	public Integer getCount(
			SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo);

	/******************** 组织机构迁移合并方法 *********************/
	/**
	 * 
	 * @Title: findRepeatNewEconomicOrganizationsForOrgChange
	 * @Description: TODO查询出源部门和目标部门重复的非公有制经济组织数据
	 * @param @param newOrgId
	 * @param @param oldOrgId
	 * @param @return 设定文件
	 * @return List<NewEconomicOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-18 下午05:04:16
	 */
	public List<NewEconomicOrganizations> findRepeatNewEconomicOrganizationsForOrgChange(
			Long newOrgId, Long oldOrgId);

	/**
	 * 
	 * @Title: findRepeatNewEconomicOrganizationsByNameForOrgChange
	 * @Description: TODO查询出源部门和目标部门名称重复的非公有制经济组织数据
	 * @param @param newOrgId
	 * @param @param oldOrgId
	 * @param @return 设定文件
	 * @return List<NewEconomicOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-18 下午05:35:43
	 */
	public List<NewEconomicOrganizations> findRepeatNewEconomicOrganizationsByNameForOrgChange(
			Long newOrgId, Long oldOrgId);
}

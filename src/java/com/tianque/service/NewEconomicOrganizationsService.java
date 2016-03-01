package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.NewEconomicOrganizations;
import com.tianque.domain.vo.SearchNewEconomicOrganizationsVo;

public interface NewEconomicOrganizationsService {

	/**
	 * 添加一条新经济组织信息
	 * 
	 * @param NewEconomicOrganizations
	 * @return NewEconomicOrganizations
	 **/
	public NewEconomicOrganizations addNewEconomicOrganizations(
			NewEconomicOrganizations newEconomicOrganizations);

	/**
	 * 根据id获取新经济组织对象
	 * 
	 * @param id
	 * @return
	 */
	public NewEconomicOrganizations getNewEconomicOrganizationsById(Long id);

	/**
	 * 根据网格、是否注销和页数信息查找新经济组织信息列表
	 * 
	 * @param orgId
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param isEmphasis
	 * @return
	 */
	public PageInfo<NewEconomicOrganizations> findNewEconomicOrganizationsForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, Long isEmphasis);

	public NewEconomicOrganizations updateNewEconomicOrganizations(
			NewEconomicOrganizations newEconomicOrganizations);

	public boolean deleteNewEconomicOrganizationsById(Long id);

	public void deleteNewEconomicOrganizationssByIdList(List<Long> idList);

	public List<NewEconomicOrganizations> updateLogOutOfNewEconomicOrganizationssByIdList(
			List<Long> idList, Long isEmphasis);

	public PageInfo<NewEconomicOrganizations> searchNewEconomicOrganizationss(
			Integer pageNum, Integer pageSize, String sortField, String order,
			SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo);

	public List<NewEconomicOrganizations> searchAllNewEconomicOrganizationss(
			String sortField, String order,
			SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo);

	/**
	 * 判断是否有重复的数据使用
	 * 
	 * @param licenseNumber
	 * @param organizationId
	 * @return
	 */
	public List<NewEconomicOrganizations> getNewEconomicOrganizationsByNameAndLicenseNumberAndOrgId(
			String name, String licenseNumber, Long orgId);

	/**
	 * 导入时的更新
	 * 
	 * @param name
	 * @param licenseNumber
	 * @param orgId
	 * @param domain
	 * @return
	 */
	public NewEconomicOrganizations updateNewEconomicOrganizationsByNameAndLicenseNumber(
			String name, String licenseNumber, Long orgId,
			NewEconomicOrganizations domain);

	public boolean hasDuplicateNewEconomicOrganizationsByName(String name,
			Long orgId, Long exceptedId);

	public boolean hasDuplicateNewEconomicOrganizationsByLicenseNumber(
			String licenseNumber, Long orgId, Long exceptedId);

	public void updateEmphasiseByIds(List<Long> ids,
			NewEconomicOrganizations location);

	public Integer getCount(
			SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo);

	/********************** 组织机构迁移合并方法 ***********************************/
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

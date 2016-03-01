package com.tianque.baseInfo.newSocietyOrganizations.service;

import java.util.List;

import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.core.vo.PageInfo;

public interface NewSocietyOrganitionsService {

	boolean hasDuplicateNewSocietyOrganizationsName(Long ownerOrgId,
			String newSocietyOrganizationsName, Long exceptedId);

	public PageInfo<NewSocietyOrganizations> findNewSocietyOrganizationsForPageByOrgInternalCode(
			Long orgId, Integer page, Integer rows, String sidx, String sord,
			Boolean isEmphasis);

	public NewSocietyOrganizations addNewSocietyOrganizations(
			NewSocietyOrganizations newSocietyOrganizations);

	public NewSocietyOrganizations getSimpleNewSocietyOrganizations(Long id);

	public NewSocietyOrganizations updateNewSocietyOrganizations(
			NewSocietyOrganizations newSocietyOrganizations);

	public void deleteNewSocietyOrganizationsByIds(List<Long> deleteIds);

	public void deleteNewSocietyOrganizationsById(Long id);

	public void updateEmphasiseByIds(List<Long> ids,
			NewSocietyOrganizations location);

	public NewSocietyOrganizations updateNewSocietyOrganizationsByName(
			String name, Long id, NewSocietyOrganizations domain);

	public int getNewSocietyOrganizationsCountByOrgId(Long orgId);

	void shiftNewSocietyOrganization(Long[] ids, Long id);

	/************************* 组织机构迁移合并 **********************/
	/**
	 * 
	 * @Title: findNewSocietyOrganizationsListForOrgChange
	 * @Description: TODO查询出源部门与目标部门重复的社会组织数据
	 * @param @param newOrgId
	 * @param @param oldOrgId
	 * @param @return 设定文件
	 * @return List<NewSocietyOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-18 下午02:21:59
	 */
	public List<NewSocietyOrganizations> findNewSocietyOrganizationsListForOrgChange(
			Long newOrgId, Long oldOrgId);

	/**
	 * 
	 * @Title: findTargetNewSocietyOrganizationsListForOrgChange
	 * @Description: TODO根据名字类型，组织机构id查询出目标部门的重复数据
	 * @param @param name
	 * @param @param type
	 * @param @param newOrgId
	 * @param @return 设定文件
	 * @return List<NewSocietyOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-18 下午02:35:59
	 */
	public List<NewSocietyOrganizations> findTargetNewSocietyOrganizationsListForOrgChange(
			NewSocietyOrganizations newOrg, Long newOrgId);
}

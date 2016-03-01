package com.tianque.baseInfo.newSocietyOrganizations.dao;

import java.util.List;

import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.core.vo.PageInfo;

public interface NewSocietyOrganizationsDao {

	public NewSocietyOrganizations addNewSocietyOrganizations(
			NewSocietyOrganizations addNnewSocietyOrganizations);

	public NewSocietyOrganizations getSimpleNewSocietyOrganizations(Long id);

	public NewSocietyOrganizations getNewSocietyOrganizationsByName(
			String name, Long id);

	public NewSocietyOrganizations updateNewSocietyOrganizations(
			NewSocietyOrganizations newSocietyOrganizations);

	public void deleteNewSocietyOrganizationsById(Long id);

	public void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logOutReason);

	public PageInfo<NewSocietyOrganizations> findNewSocietyOrganizationsForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Boolean isEmphasis);

	public int getNewSocietyOrganizationsCountByOrgId(Long orgId);

	public int getNewSocietyOrganizationsCountByOrgCode(String orgCode);

	/***************************** 组织机构迁移合并 ***********************************/
	/**
	 * 
	 * @Title: findNewSocietyOrganizationsListForOrgChange
	 * @Description: TODO查询出源部门与目标部门重复的社会组织数据
	 * @param @param newOrgId
	 * @param @param oldOrgId
	 * @param @return 设定文件
	 * @return List<NewSocietyOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-18 下午02:18:05
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
			String name, Long newOrgId);
}

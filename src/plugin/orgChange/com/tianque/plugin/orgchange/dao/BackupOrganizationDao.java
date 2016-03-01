package com.tianque.plugin.orgchange.dao;

import java.util.List;

import com.tianque.domain.Organization;

public interface BackupOrganizationDao {

	/**
	 * 
	 * @Title: findAllOrganizationsByParentId
	 * @Description: TODO根据父节点ID查询allorganizations表数据
	 * @param @param parentId
	 * @param @return 设定文件
	 * @return List<Organization> 返回类型
	 * @author wanggz
	 * @date 2014-10-22 下午02:58:04
	 */
	public List<Organization> findAllOrganizationsByParentId(Long parentId);

	/**
	 * 
	 * @Title: getSimpleAllOrgById
	 * @Description: TODO根据ID查询allorganization数据
	 * @param @param id
	 * @param @return 设定文件
	 * @return Organization 返回类型
	 * @author wanggz
	 * @date 2014-10-22 下午03:06:53
	 */
	public Organization getSimpleAllOrgById(Long id);

	/**
	 * 
	 * @Title: findAllOrgsByParentIdAndOrgTypeInternalIds
	 * @Description: TODO查询allorganization数据
	 * @param @param parentId
	 * @param @param orgTypeInterIds
	 * @param @return 设定文件
	 * @return List<Organization> 返回类型
	 * @author wanggz
	 * @date 2014-10-22 下午03:20:49
	 */
	public List<Organization> findAllOrgsByParentIdAndOrgTypeInternalIds(
			Long parentId, Long[] orgTypeInterIds);

	public List<Organization> findAllFunOrgsByParentIdAndOrgTypes(
			Long parentId, Long[] propertyDictIds);

	public List<Organization> findAllFunOrgsByParentOrgIdAndOrgTypes(
			Long parentOrgId, Long[] propertyDictIds);

}

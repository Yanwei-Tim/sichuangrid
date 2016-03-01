package com.tianque.plugin.orgchange.service;

import java.util.List;

import com.tianque.domain.Organization;

public interface BackupOrganizationService {

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
	 * @Title: getFullAllOrgById
	 * @Description: TODO查询出allorganization数据
	 * @param @param id
	 * @param @return 设定文件
	 * @return Organization 返回类型
	 * @author wanggz
	 * @date 2014-10-22 下午03:05:14
	 */
	public Organization getFullAllOrgById(Long id);

	/**
	 * 
	 * @Title: findAllOrgsByParentIdAndOrgTypeInternalIds
	 * @Description: TODO查询allorganization数据
	 * @param @param parentId
	 * @param @param orgTypeInternalIds
	 * @param @return 设定文件
	 * @return List<Organization> 返回类型
	 * @author wanggz
	 * @date 2014-10-22 下午03:20:00
	 */
	public List<Organization> findAllOrgsByParentIdAndOrgTypeInternalIds(
			Long parentId, Long[] orgTypeInternalIds);

	public List<Organization> findAllFunOrgsByParentId(Long parentId);

	public List<Organization> findAllFunOrgsByParentOrgId(Long parentOrgId);

	/**
	 * 查询用户所有关联的组织机构id，对上一条线，对下是所有下辖 startLevelId 代表对上的起点例如四川 那么就是对上到四川为止
	 * 如武胜县用户就是找到 广安市 四川省 和武胜县 武胜县所有下辖的组织机构id
	 * */
	public List<Long> findAllRelatedOrgIdsByUserOrgId(int startLevelId);

	/**
	 * 
	 * @Title: findAllorganizationsByParentIdAndChangetype
	 * @Description: TODO查询出备份组织机构数据
	 * @param @param Long
	 * @param @return 设定文件
	 * @return Organization 返回类型
	 * @author wanggz
	 * @date 2014-11-4 下午03:30:07
	 */
	public Organization findAllorganizationsByParentIdAndChangetype(Long orgId);
}

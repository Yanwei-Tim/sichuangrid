package com.tianque.sysadmin.dao;

import java.util.List;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.vo.OrganizationVo;

/**
 * 组织机构查询结果是<code>List&lt;Organization&gt;</code>查询直接在本地查询，不通过dubbo
 * 
 * @author 曾利民
 * @version 1.0.0
 * @since 2015年4月10日 下午4:45:02
 */
public interface OrganizationLocalDao {
	public List<Organization> findOrganizationByParentIdAndOrgType(
			Long parentId, Long orgType);

	public List<Organization> findOrganizationsByParentId(Long parentId);

	public List<Organization> findOrganizationsByOrgNameAndInternalCodeForPage(
			String orgInternalCode, String orgName, int pageNum, int pageSize);

	public List<Organization> findOrganizationsByorgNameAndOrgType(
			String orgInternalCode, String orgName, PropertyDict orgType,
			int pageNum, int pageSize);

	public List<Organization> findOrganizationsByOrgNameAndParentId(Long id,
			String orgName, Long parentId);

	public List<Organization> findOrganizationsByDepartmentNoAndTypeAndLevel(
			Organization organization);

	public List<Organization> findMultizonesByUserId(Long userId);

	public List<Organization> findOrgsByParentIdAndOrgTypeInternalIds(
			Long parentId, Long[] orgTypeInterIds);

	public List<Organization> findOrgsByParentOrgAndOrgTypeInternalId(
			String OrgInternalCode, Integer orgType);

	public List<Organization> findFunOrgsByParentIdAndOrgTypes(Long parentId,
			Long[] propertyDictIds);

	public List<Organization> findOrganizationsByOrgName(String orgName);

	public List<Organization> findAdminOrgsByParentIdAndName(Long parentId,
			String name);

	public List<Organization> findFunOrgsByParentIdAndName(Long parentId,
			String name);

	public List<Organization> findFunOrgsByParentFunOrgIdAndName(
			Long funParentId, String name);

	public List<Organization> findOrganizationsByOrgInternalCode(
			String orgInternalCode);

	public List<Organization> getOrganizationByIdAndOrgInternalCode();

	public List<Organization> findOrgsByParentDeptNoAndLevelIdExcludeFunOrgId(
			String departmentNo, Long orgLevelId, Long funOrgId);

	public List<Organization> fingOrganizationforLevel(Long orgLevelId);

	public List<OrganizationVo> getTableNameAndOrgId();

	public List<Organization> findOrgsByOrgCodeAndOrgLevelInternalsAndOrgTypeInternals(
			String orgCode, List<Integer> orgLevelInternals,
			List<Integer> orgTypeInternals, List<Long> exceptOrgIds);

	/**
	 * 根据关键字查询组织机构列表，提取一定数量的记录，并把父组织机构也捞取出来
	 * 
	 * @param keyword
	 *            搜索关键字
	 * @param size
	 *            共捞取前几条
	 * @return 组织机构的列表，包含了父组织机构的数据
	 */
	public List<Organization> findOrgsFetchParentOrgByKeyword(String keyword,
			int size);

	public List<Organization> findOrgsByOrgTypeIdAndOrgLevelIdAndParentOrgInternalCode(
			Long orgTypeId, Long orgLevelId, String parentOrgInternalCode);

	public List<Organization> findOrgsByOrgTypeIdAndOrgLevelId(Long orgTypeId,
			Long orgLevelId, String userOrgInternalCode);

	public List<Organization> findFunOrgsByFunParentId(Long funParentId);

	/**
	 * 根据组织机构id，组织机构名称，类型获取组织机构
	 * 
	 * @param orgInternalCode
	 * @param orgName
	 * @param type
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Organization> findOrganizationsByOrgNameAndInternalCodeAndTypeForPage(
			String orgInternalCode, String orgName, Long[] type, int pageNum,
			int pageSize);

	public List<Organization> getOrgZN(Long id);

	/**
	 * 根据部门编号(departmentno)模糊查询所有的子组织机构列表
	 * 
	 * @param departmentNo
	 * @return
	 */
	public List<Organization> findOrgsByDepartmentNo(String departmentNo);

	public List<Organization> findFunOrgsByParentOrgIdAndOrgTypes(
			Long parentOrgId, Long[] propertyDictIds);

	public List<Organization> findOrgsByParentIdAndOrgTypeInternalIdsAndFunctionalType(
			Long parentId, Long[] orgTypeInternalIds, Long orgFunctionalType);

	/**
	 * 根据网格类型id orgType 查询县级以下行政部门组织机构
	 * 
	 * @param orgTypeId
	 * @param searchOrgCode
	 * @param orgCodeFindLevel
	 * @return
	 */

	public List<Organization> findDistrictAdminOrgsByOrgType(Long orgTypeId,
			String searchOrgCode, String orgCodeFindLevel);

	public List<Organization> findProvinceOrganizationsByOrgId(Long orgId);

	/**
	 * 通ORGId制定查询某一层级的组织机构信息
	 */
	public List<Organization> findOrganizationByOrgIdAndNum(Long orgId,
			Integer Num);

	/**
	 * 得到当前用户的直属上级或者是下级 isUp:0上级 1:下级
	 */
	public List<Organization> findOrgsByOrgTypeIdAndOrgLevelIdAndOrgInternalCode(
			Long orgTypeId, Long orgLevelId, String userOrgInternalCode,
			Integer isUp);

	/***
	 * 查询职能部门信息 orgLevel:查询的组织结构的级别（省、市、县.....等） orgType:查询职能部门还是行政部门
	 * orgCode:某个层级下（是查询市级职能部门还是...） isUp:是查询当前，还是查询下辖所有
	 */
	public List<Organization> findFuncOrgInfoByCondition(Long orgLevel,
			Long orgType, String orgCode, Integer isUp);

	public List<Organization> findAllOrganization();

	public List<Organization> findAllOrgByParentOrgCode(String orgCode,
			Long orgType);

	/* add by zenglm for job optmize */
	List<Organization> findOrganIdForLevelExcludeGrid(Long orgLevelId,
			int taskItemNum, List<Long> idModList, int fetchNum, int year,
			int month, String targetIssueTable);

	// add by bing 2014年11月12日 下午6:07:51

	/**
	 * 根据搜索条件查询org集合
	 * 
	 * @param searchVo
	 * @return
	 */
	public List<Organization> findOrgsBySearchVo(OrganizationVo searchVo);

	/**
	 * 根据搜索条件查询orgName和remark集合
	 * 
	 * @param searchVo
	 * @return
	 */
	public List<Organization> findNameAndRemarkBySearchVo(
			OrganizationVo searchVo);

	/**
	 * 根据所属组织机构，查询指定层级的组织机构
	 * 
	 * @param orgId
	 * @param organizationLevel
	 *            (OrganizationType的层级)
	 * @return
	 */
	public List<Organization> findProvinceOrganizationsByOrgIdAndCityLevel(
			Long orgId, Integer organizationLevel);

	/************************ 组织机构迁移合并方法 *******************************/

	public void updateOrgSeqAndParentId(Long id, Long seq, Long parentId);

	public Organization updateOrgNameAndOrgTypeAndContactWay(
			Organization organization);

	public Organization updateOrgSubCount(Long id, int num);

	public void updateOrgInternalCode(Long id, String orgInternalCode);

	public void editChildOrganizationDeptNo(String oldDeptNo, String newDeptNo);

	public Organization getSimpleOrgById(Long id);

	public int countDatasByOrgIdAndTableName(String tableName, String orgIdStr,
			Long orgId);

	public void deleteOrgById(Long id);

	public void updateOrgsSeqAfterReferSeq(Long parentId, Long seq, Long index);

	public Organization updateFunParentOrgSubCount(Long id, int num);

	public int updateAllOrgSubCount(Long id);

	public void updateMaxCodeById(Long id);

	public Integer getMaxCodeById(Long id);

	public List<String> getDepartmentnosByParentOrgId(Long parentId);

}

package com.tianque.sysadmin.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.cache.service.impl.CacheNameSpace;
import com.tianque.core.cache.util.CacheKeyGenerator;
import com.tianque.core.util.OrganizationHelper;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.sysadmin.dao.OrganizationLocalDao;

/**
 * 此dao增加了缓存处理，对以下的API做了缓存： 1、getSimpleOrgById
 * 2、findOrgsByParentIdAndOrgTypeInternalId
 */
@Repository("organizationLocalDao")
@SuppressWarnings("unchecked")
public class OrganizationLocalDaoImpl extends AbstractBaseDao implements
		OrganizationLocalDao {

	@Autowired
	private CacheService cacheService;
	@Autowired
	private OrganizationHelper organizationHelper;

	@Override
	public List<Organization> findProvinceOrganizationsByOrgIdAndCityLevel(
			Long orgId, Integer organizationLevel) {
		Map map = new HashMap();
		map.put("orgId", orgId);
		map.put("num", organizationLevel);
		return getSqlMapClientTemplate().queryForList(
				"organization.findProvinceOrganizationsByOrgId", map);
	}

	@Override
	public List<OrganizationVo> getTableNameAndOrgId() {

		return (List<OrganizationVo>) getSqlMapClientTemplate().queryForList(
				"organization.getTableNameAndOrgId", null);
	}

	@Override
	public List<Organization> findOrganizationsByParentId(Long parentId) {
		List<Organization> list = (List<Organization>) cacheService
				.get(MemCacheConstant.getOrganizationNameSpace(parentId, null));
		if (list == null) {
			list = getSqlMapClientTemplate().queryForList(
					"organization.findOrganizationsByParentId", parentId);
			cacheService.set(
					MemCacheConstant.getOrganizationNameSpace(parentId, null),
					list);
		}
		return list;
	}

	@Override
	public List<Organization> findOrganizationsByOrgNameAndInternalCodeForPage(
			String orgInternalCode, String orgName, int pageNum, int pageSize) {

		Map map = new HashMap();
		map.put("orgName", orgName + "%");
		map.put("orgInternalCode", orgInternalCode + "%");

		return getSqlMapClientTemplate().queryForList(
				"organization.findOrganizationsByOrgNameAndInternalCode", map,
				(pageNum - 1) * pageSize, pageSize);
	}

	@Override
	public List<Organization> findMultizonesByUserId(Long userId) {
		return getSqlMapClientTemplate().queryForList(
				"organization.findMultizonesByUserId", userId);
	}

	@Override
	public List<Organization> findOrganizationsByOrgNameAndParentId(Long id,
			String orgName, Long parentId) {
		Map map = new HashMap();
		map.put("orgName", orgName);
		map.put("parentId", parentId);
		map.put("id", id);
		return getSqlMapClientTemplate().queryForList(
				"organization.findOrganizationsByOrgNameAndParentId", map);
	}

	@Override
	public List<Organization> findOrganizationsByDepartmentNoAndTypeAndLevel(
			Organization organization) {
		Map map = new HashMap();
		map.put("departmentNo", organization.getDepartmentNo());
		if (organization.getOrgType() != null
				&& organization.getOrgType().getId() != null)
			map.put("orgType", organization.getOrgType().getId());
		if (organization.getOrgLevel() != null
				&& organization.getOrgLevel().getId() != null)
			map.put("orgLevel", organization.getOrgLevel().getId());
		if (organization.getParentOrg() != null
				&& organization.getParentOrg().getId() != null)
			map.put("parentId", organization.getParentOrg().getId());
		if (organization.getId() != null)
			map.put("id", organization.getId());
		return getSqlMapClientTemplate().queryForList(
				"organization.findOrganizationsByOrgNoAndTypeAndLevel", map);
	}

	@Override
	public List<Organization> findOrgsByParentIdAndOrgTypeInternalIds(
			Long parentId, Long[] orgTypeInternalIds) {
		List<Organization> organizations = new ArrayList<Organization>();
		for (int i = 0; orgTypeInternalIds != null
				&& i < orgTypeInternalIds.length; i++) {
			if (OrganizationType.ADMINISTRATIVE_REGION == orgTypeInternalIds[i]
					.intValue())
				organizations.addAll(findOrgsByParentIdAndOrgTypeInternalId(
						parentId, OrganizationType.ADMINISTRATIVE_REGION));
			if (OrganizationType.FUNCTIONAL_ORG == orgTypeInternalIds[i]
					.intValue())
				organizations.addAll(findOrgsByParentIdAndOrgTypeInternalId(
						parentId, OrganizationType.FUNCTIONAL_ORG));
			if (OrganizationType.OTHER == orgTypeInternalIds[i].intValue())
				organizations.addAll(findOrgsByParentIdAndOrgTypeInternalId(
						parentId, OrganizationType.OTHER));
			if (OrganizationType.PARTYWORK == orgTypeInternalIds[i].intValue())
				organizations.addAll(findOrgsByParentIdAndOrgTypeInternalId(
						parentId, OrganizationType.PARTYWORK));
		}
		return organizations;
	}

	private class OrgComparator implements Comparator {

		@Override
		public int compare(Object o1, Object o2) {
			Organization organization1 = (Organization) o1;
			Organization organization2 = (Organization) o2;
			if (organization1.getSeq() > organization2.getSeq()) {
				return 1;
			} else if (organization1.getSeq() < organization2.getSeq()) {
				return -1;
			}
			return 0;
		}

	}

	private List<Organization> findOrgsByParentIdAndOrgTypeInternalId(
			Long parentId, int orgTypeInternalId) {
		long startTime = System.currentTimeMillis();
		List<Organization> organizations = (List<Organization>) cacheService
				.get(MemCacheConstant.getOrganizationNameSpace(parentId, null),
						MemCacheConstant.getOrganizationKey(parentId,
								orgTypeInternalId, null));
		if (null != organizations) {
			logger.error("nameSpace:"
					+ MemCacheConstant.getOrganizationNameSpace(parentId, null)
					+ ",key:"
					+ MemCacheConstant.getOrganizationKey(parentId,
							orgTypeInternalId, null) + "，从缓存获取组织机构耗时："
					+ (System.currentTimeMillis() - startTime));
		}
		if (organizations == null) {
			logger.error("操作缓存耗时：" + (System.currentTimeMillis() - startTime));
			long dbTime = System.currentTimeMillis();
			Map map = new HashMap();
			map.put("parentId", parentId);
			map.put("orgTypeInternalId", orgTypeInternalId);
			organizations = getSqlMapClientTemplate().queryForList(
					"organization.findOrgsByParentIdAndOrgTypeInternalId", map);
			cacheService.set(MemCacheConstant.getOrganizationNameSpace(
					parentId, null), MemCacheConstant.getOrganizationKey(
					parentId, orgTypeInternalId, null), organizations);
			logger.error("查询数据库耗时：" + (System.currentTimeMillis() - dbTime));
		}
		return organizations;
	}

	public List<Organization> findOrgsByParentOrgAndOrgTypeInternalId(
			String OrgInternalCode, Integer orgTypeInternalId) {
		List<Organization> organizations = new ArrayList<Organization>();
		Map map = new HashMap();
		map.put("OrgInternalCode", OrgInternalCode);
		map.put("orgTypeInternalId", orgTypeInternalId);
		organizations = getSqlMapClientTemplate().queryForList(
				"organization.findOrgsByOrgCodeAndOrgTypeInternalId", map);
		return organizations;
	}

	@Override
	public List<Organization> findFunOrgsByParentIdAndOrgTypes(Long parentId,
			Long[] orgTypes) {
		Map map = new HashMap();
		map.put("parentId", parentId);
		map.put("orgTypes", orgTypes);
		return getSqlMapClientTemplate().queryForList(
				"organization.findFunOrgsByParentIdAndOrgTypes", map);
	}

	@Override
	public List<Organization> findOrganizationsByOrgName(String orgName) {
		return getSqlMapClientTemplate().queryForList(
				"organization.findOrganizationsByOrgName", orgName);
	}

	@Override
	public List<Organization> findAdminOrgsByParentIdAndName(Long parentId,
			String name) {
		return findOrgsByParentIdAndOrgTypeInternalIdAndNameAndPinyin(parentId,
				OrganizationType.ADMINISTRATIVE_REGION, name);
	}

	private List<Organization> findOrgsByParentIdAndOrgTypeInternalIdAndNameAndPinyin(
			Long parentId, int orgTypeInternalId, String name) {
		Map map = new HashMap();
		map.put("parentId", parentId);
		map.put("orgTypeInternalId", orgTypeInternalId);
		map.put("name", name);
		List<Organization> organizations = getSqlMapClientTemplate()
				.queryForList(
						"organization.findOrgsByParentIdAndOrgTypeInternalIdAndNameAndPinyin",
						map);

		return organizations;
	}

	@Override
	public List<Organization> findFunOrgsByParentIdAndName(Long parentId,
			String name) {
		return findOrgsByParentIdAndOrgTypeInternalIdAndNameAndPinyin(parentId,
				OrganizationType.FUNCTIONAL_ORG, name);
	}

	@Override
	public List<Organization> findFunOrgsByParentFunOrgIdAndName(
			Long parentFunOrgId, String name) {
		Map map = new HashMap();
		map.put("parentFunOrgId", parentFunOrgId);
		map.put("name", name);
		List<Organization> organizations = getSqlMapClientTemplate()
				.queryForList("organization.findFunOrgsByFunParentIdAndName",
						map);

		return organizations;
	}

	@Override
	public List<Organization> findOrganizationsByOrgInternalCode(
			String orgInternalCode) {
		return getSqlMapClientTemplate().queryForList(
				"organization.findOrganizationsByOrgInternalCode",
				orgInternalCode);
	}

	@Override
	public List<Organization> getOrganizationByIdAndOrgInternalCode() {
		return getSqlMapClientTemplate().queryForList(
				"organization.getOrganizationByIdAndOrgInternalCode");
	}

	@Override
	public List<Organization> findOrgsByParentDeptNoAndLevelIdExcludeFunOrgId(
			String departmentNo, Long orgLevelId, Long funOrgId) {
		Map map = new HashMap();
		map.put("departmentNo", departmentNo);
		map.put("orgLevelId", orgLevelId);
		map.put("funOrgId", funOrgId);
		return getSqlMapClientTemplate().queryForList(
				"organization.findOrgsByParentDeptNoAndLevelIdExcludeFunOrgId",
				map);
	}

	@Override
	public List<Organization> fingOrganizationforLevel(Long orgLevelId) {
		Map map = new HashMap();
		map.put("orgLevelId", orgLevelId);
		return getSqlMapClientTemplate().queryForList(
				"organization.fingOrganizationforLevel", map);
	}

	@Override
	public List<Organization> findOrganizationsByorgNameAndOrgType(
			String orgInternalCode, String orgName, PropertyDict orgType,
			int pageNum, int pageSize) {

		Map map = new HashMap();
		map.put("orgName", orgName + "%");
		map.put("orgInternalCode", orgInternalCode + "%");
		map.put("orgType", orgType);
		return getSqlMapClientTemplate().queryForList(
				"organization.findOrganizationsByorgNameAndOrgType", map,
				(pageNum - 1) * pageSize, pageSize);

	}

	@Override
	public List<Organization> findOrganizationByParentIdAndOrgType(
			Long parentId, Long orgType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		map.put("orgType", orgType);
		return getSqlMapClientTemplate().queryForList(
				"organization.findOrganizationByParentIdAndOrgType", map);
	}

	@Override
	public List<Organization> findOrgsByOrgCodeAndOrgLevelInternalsAndOrgTypeInternals(
			String orgCode, List<Integer> orgLevelInternals,
			List<Integer> orgTypeInternals, List<Long> exceptOrgIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		map.put("orgLevelInternals", orgLevelInternals);
		map.put("orgTypeInternals", orgTypeInternals);
		map.put("exceptOrgIds", exceptOrgIds);
		return getSqlMapClientTemplate().queryForList(
				"organization.findOrganizationByOrgCodeAndOrgLevelInternals",
				map);
	}

	@Override
	public List<Organization> findOrgsFetchParentOrgByKeyword(String keyword,
			int size) {
		return getSqlMapClientTemplate().queryForList(
				"organization.findOrgsFetchParentOrgByKeyword", keyword + "%",
				0, size);
	}

	@Override
	public List<Organization> findOrgsByOrgTypeIdAndOrgLevelIdAndParentOrgInternalCode(
			Long orgTypeId, Long orgLevelId, String parentOrgInternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentOrgInternalCode", parentOrgInternalCode + "%");
		map.put("orgLevelId", orgLevelId);
		map.put("orgTypeId", orgTypeId);
		return getSqlMapClientTemplate()
				.queryForList(
						"organization.findOrgsByOrgTypeIdAndOrgLevelIdAndParentOrgInternalCode",
						map);
	}

	@Override
	public List<Organization> findOrgsByOrgTypeIdAndOrgLevelId(Long orgTypeId,
			Long orgLevelId, String userOrgInternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userOrgInternalCode", userOrgInternalCode + "%");
		map.put("orgLevelId", orgLevelId);
		map.put("orgTypeId", orgTypeId);
		return getSqlMapClientTemplate().queryForList(
				"organization.findOrgsByOrgTypeIdAndOrgLevelId", map);
	}

	@Override
	public List<Organization> findFunOrgsByFunParentId(Long funParentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("funParentId", funParentId);
		map.put("domainname", OrganizationType.ORG_TYPE_KEY);
		map.put("internalId", OrganizationType.FUNCTIONAL_ORG);
		return getSqlMapClientTemplate().queryForList(
				"organization.findFunOrgsByFunParentId", map);
	}

	@Override
	public List<Organization> findOrganizationsByOrgNameAndInternalCodeAndTypeForPage(
			String orgInternalCode, String orgName, Long[] type, int pageNum,
			int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("orgName", orgName);
		map.put("type", type);
		return getSqlMapClientTemplate()
				.queryForList(
						"organization.findOrganizationsByOrgNameAndInternalCodeAndTypeForPage",
						map, (pageNum - 1) * pageSize, pageSize);
	}

	@Override
	public List<Organization> getOrgZN(Long id) {
		return getSqlMapClientTemplate().queryForList("organization.getOrgZN",
				id);
	}

	@Override
	public List<Organization> findOrgsByDepartmentNo(String departmentNo) {
		Map map = new HashMap();
		map.put("departmentNo", departmentNo);
		return getSqlMapClientTemplate().queryForList(
				"organization.findOrgsByDepartmentNo", map);
	}

	@Override
	public List<Organization> findFunOrgsByParentOrgIdAndOrgTypes(
			Long parentOrgId, Long[] propertyDictIds) {
		Map map = new HashMap();
		map.put("parentId", parentOrgId);
		map.put("orgTypes", propertyDictIds);
		return getSqlMapClientTemplate().queryForList(
				"organization.findFunOrgsByParentOrgIdAndOrgTypes", map);
	}

	@Override
	public List<Organization> findOrgsByParentIdAndOrgTypeInternalIdsAndFunctionalType(
			Long parentId, Long[] orgTypeInternalIds, Long orgFunctionalType) {
		List<Organization> organizations = new ArrayList<Organization>();
		for (int i = 0; orgTypeInternalIds != null
				&& i < orgTypeInternalIds.length; i++) {
			if (OrganizationType.ADMINISTRATIVE_REGION == orgTypeInternalIds[i]
					.intValue())
				organizations
						.addAll(findOrgsByParentIdAndOrgTypeInternalIdAndFcuntionType(
								parentId,
								OrganizationType.ADMINISTRATIVE_REGION,
								orgFunctionalType));
			if (OrganizationType.FUNCTIONAL_ORG == orgTypeInternalIds[i]
					.intValue())
				organizations
						.addAll(findOrgsByParentIdAndOrgTypeInternalIdAndFcuntionType(
								parentId, OrganizationType.FUNCTIONAL_ORG,
								orgFunctionalType));
			if (OrganizationType.OTHER == orgTypeInternalIds[i].intValue())
				organizations
						.addAll(findOrgsByParentIdAndOrgTypeInternalIdAndFcuntionType(
								parentId, OrganizationType.OTHER,
								orgFunctionalType));
			if (OrganizationType.PARTYWORK == orgTypeInternalIds[i].intValue())
				organizations
						.addAll(findOrgsByParentIdAndOrgTypeInternalIdAndFcuntionType(
								parentId, OrganizationType.PARTYWORK,
								orgFunctionalType));
		}
		Collections.sort(organizations, new OrgComparator());
		return organizations;
	}

	private List<Organization> findOrgsByParentIdAndOrgTypeInternalIdAndFcuntionType(
			Long parentId, int orgTypeInternalId, Long orgFunctionalType) {
		List<Organization> organizations = (List<Organization>) cacheService
				.get(MemCacheConstant.getOrganizationNameSpace(parentId,
						orgFunctionalType), MemCacheConstant
						.getOrganizationKey(parentId, orgTypeInternalId,
								orgFunctionalType));
		if (organizations == null) {
			Map map = new HashMap();
			map.put("parentId", parentId);
			map.put("orgTypeInternalId", orgTypeInternalId);
			map.put("functionalOrgType", orgFunctionalType);
			organizations = getSqlMapClientTemplate().queryForList(
					"organization.findOrgsByParentIdAndOrgTypeInternalId", map);
			cacheService.set(MemCacheConstant.getOrganizationNameSpace(
					parentId, orgFunctionalType), MemCacheConstant
					.getOrganizationKey(parentId, orgTypeInternalId,
							orgFunctionalType), organizations);
		}
		return organizations;
	}

	@Override
	public List<Organization> findDistrictAdminOrgsByOrgType(Long orgTypeId,
			String searchOrgCode, String orgCodeFindLevel) {
		List<Organization> organizations;
		Map map = new HashMap();
		map.put("orgTypeId", orgTypeId);
		map.put("searchOrgCode", searchOrgCode);
		map.put("orgCodeFindLevel", orgCodeFindLevel);
		organizations = getSqlMapClientTemplate().queryForList(
				"organization.findDistrictAdminOrgsByOrgType", map);
		return organizations;
	}

	@Override
	public List<Organization> findProvinceOrganizationsByOrgId(Long orgId) {
		Map map = new HashMap();
		map.put("orgId", orgId);
		map.put("num", OrganizationType.ORG_LEVEL);
		return getSqlMapClientTemplate().queryForList(
				"organization.findProvinceOrganizationsByOrgId", map);
	}

	@Override
	public List<Organization> findOrganizationByOrgIdAndNum(Long orgId,
			Integer num) {
		Map map = new HashMap();
		map.put("orgId", orgId);
		map.put("num", num);
		return getSqlMapClientTemplate().queryForList(
				"organization.findProvinceOrganizationsByOrgId", map);
	}

	@Override
	public List<Organization> findOrgsByOrgTypeIdAndOrgLevelIdAndOrgInternalCode(
			Long orgTypeId, Long orgLevelId, String userOrgInternalCode,
			Integer isUp) {
		Map map = new HashMap();
		map.put("orgLevelId", orgLevelId);
		map.put("orgTypeId", orgTypeId);
		map.put("orgInternalCode", userOrgInternalCode);
		map.put("isUp", isUp);
		return getSqlMapClientTemplate()
				.queryForList(
						"organization.findOrgsByOrgTypeIdAndOrgLevelIdAndOrgInternalCode",
						map);
	}

	@Override
	public List<Organization> findFuncOrgInfoByCondition(Long orgLevel,
			Long orgType, String orgCode, Integer isUp) {
		Map map = new HashMap();
		map.put("orgLevel", orgLevel);
		map.put("orgType", orgType);
		map.put("orgCode", orgCode);
		map.put("isUp", isUp);
		return getSqlMapClientTemplate().queryForList(
				"organization.findFuncOrgInfoByCondition", map);
	}

	@Override
	public List<Organization> findAllOrgByParentOrgCode(String orgCode,
			Long orgType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		map.put("orgType", orgType);
		return getSqlMapClientTemplate().queryForList(
				"organization.findAllOrgByParentOrgCode", map);
	}

	@Override
	public List<Organization> findAllOrganization() {
		return getSqlMapClientTemplate().queryForList(
				"organization.findAllOrganization");
	}

	/* add by zenglm for job optmize */
	// add by bing 2014年11月12日 下午6:19:52
	@Override
	public List<Organization> findOrganIdForLevelExcludeGrid(Long orgLevelId,
			int taskItemNum, List<Long> idModList, int fetchNum, int year,
			int month, String targetIssueTable) {
		Map map = new HashMap();
		map.put("orgLevelId", orgLevelId);
		map.put("taskItemNum", taskItemNum);
		map.put("idModList", idModList);
		map.put("fetchNum", fetchNum);
		map.put("targetIssueTable", targetIssueTable);
		map.put("year", year);
		map.put("month", month);
		return getSqlMapClientTemplate().queryForList(
				"organization.findOrganIdForLevelExcludeGrid", map);
	}

	@Override
	public List<Organization> findOrgsBySearchVo(OrganizationVo searchVo) {
		Integer page = searchVo.getPage();
		Integer rows = searchVo.getRows();
		if (page != null && rows != null) {
			return getSqlMapClientTemplate().queryForList(
					"organization.findOrgsBySearchVo", searchVo,
					(page - 1) * rows, rows);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"organization.findOrgsBySearchVo", searchVo);
		}
	}

	@Override
	public List<Organization> findNameAndRemarkBySearchVo(
			OrganizationVo searchVo) {
		Integer page = searchVo.getPage();
		Integer rows = searchVo.getRows();
		if (page != null && rows != null) {
			return getSqlMapClientTemplate().queryForList(
					"organization.findNameAndRemarkBySearchVo", searchVo,
					(page - 1) * rows, rows);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"organization.findNameAndRemarkBySearchVo", searchVo);
		}
	}

	@Override
	public void updateOrgSeqAndParentId(Long id, Long seq, Long parentId) {
		Map map = new HashMap();
		map.put("parentId", parentId);
		map.put("seq", seq);
		map.put("id", id);
		getSqlMapClientTemplate().update(
				"organization.updateOrgSeqAndParentId", map);
		getSqlMapClientTemplate().update(
				"allorganization.updateOrgSeqAndParentId", map);

		cacheService
				.remove(MemCacheConstant.ORGANIZATION_NAMESPACE,
						MemCacheConstant.generateCacheKeyFromId(
								Organization.class, id));
		Organization parentOrg = new Organization();
		parentOrg.setId(parentId);
		invalidateFindOrgsByParentIdNamespace(parentOrg);
	}

	private void invalidateFindOrgsByParentIdNamespace(Organization parentOrg) {
		Long id = null;
		if (parentOrg != null) {
			id = parentOrg.getId();
		}
		cacheService.invalidateNamespaceCache(MemCacheConstant
				.getOrganizationNameSpace(id, null));
	}

	@Override
	public Organization updateOrgNameAndOrgTypeAndContactWay(
			Organization organization) {

		// getSqlMapClientTemplate()
		// .update("organization.updateOrganizationByOrgNameAndOrgTypeAndContactWay",
		// organization);
		// cacheService.remove(CacheKeyGenerator.generateCacheKeyFromId(
		// Organization.class, organization.getId()));
		//
		// organization = getSimpleOrgById(organization.getId());
		// invalidateFindOrgsByParentIdNamespace(organization.getParentOrg());
		// return organization;
		getSqlMapClientTemplate()
				.update("organization.updateOrganizationByOrgNameAndOrgTypeAndContactWay",
						organization);
		getSqlMapClientTemplate()
				.update("allorganization.updateOrganizationByOrgNameAndOrgTypeAndContactWay",
						organization);
		cacheService.remove(MemCacheConstant.ORGANIZATION_NAMESPACE,
				MemCacheConstant.generateCacheKeyFromId(Organization.class,
						organization.getId()));
		cacheService.remove(MemCacheConstant
				.getOrganizationNameSpaceByIdAndType(organization.getId(),
						"getFullOrgById"));
		organization = getSimpleOrgById(organization.getId());
		invalidateFindOrgsByParentIdNamespace(organization.getParentOrg());
		return organization;
	}

	@Override
	public Organization getSimpleOrgById(Long id) {
		Organization organization = (Organization) cacheService
				.get(MemCacheConstant.ORGANIZATION_NAMESPACE, MemCacheConstant
						.generateCacheKeyFromId(Organization.class, id));
		if (organization == null) {
			organization = (Organization) getSqlMapClientTemplate()
					.queryForObject("organization.findById", id);
			cacheService.set(MemCacheConstant.ORGANIZATION_NAMESPACE,
					MemCacheConstant.generateCacheKeyFromId(Organization.class,
							id), organization);
			organization.setFullOrgName(organizationHelper.getRelativeName(id));
			cacheService.set(MemCacheConstant
					.getOrganizationNameSpaceByIdAndType(id, "getFullOrgById"),
					organization);
		}
		return organization;
	}

	@Override
	public Organization updateOrgSubCount(Long id, int num) {
		// Organization org = getSimpleOrgById(id);
		// org.setSubCount(org.getSubCount() + num);
		// Map map = new HashMap();
		// map.put("id", id);
		// map.put("subCount", org.getSubCount());
		// getSqlMapClientTemplate().update("organization.updatOrgSubCount",
		// map);
		//
		// cacheService.remove(CacheKeyGenerator.generateCacheKeyFromId(
		// Organization.class, id));
		// invalidateFindOrgsByParentIdNamespace(org.getParentOrg());
		//
		// return getSimpleOrgById(id);
		// 迁移合并版本方法
		Organization org = getSimpleOrgById(id);
		org.setSubCount(org.getSubCount() + num);
		Map map = new HashMap();
		map.put("id", id);
		map.put("subCount", org.getSubCount());
		getSqlMapClientTemplate().update("organization.updatOrgSubCount", map);
		if (num != -1) {
			Organization allOrg = getSimpleOrgAllOrganizationById(id);
			allOrg.setSubCount(allOrg.getSubCount() + num);
			Map allOrgMap = new HashMap();
			allOrgMap.put("id", id);
			allOrgMap.put("subCount", allOrg.getSubCount());
			getSqlMapClientTemplate().update(
					"allorganization.updatOrgSubCount", allOrgMap);
		}
		cacheService
				.remove(MemCacheConstant.ORGANIZATION_NAMESPACE,
						MemCacheConstant.generateCacheKeyFromId(
								Organization.class, id));
		invalidateFindOrgsByParentIdNamespace(org.getParentOrg());

		return getSimpleOrgById(id);
	}

	private Organization getSimpleOrgAllOrganizationById(Long id) {
		Organization organization = (Organization) cacheService.get(
				CacheNameSpace.Organization_nameSpace,
				CacheKeyGenerator.generateCacheKey(Organization.class, id));
		if (organization == null) {
			organization = (Organization) getSqlMapClientTemplate()
					.queryForObject("organization.findAllOrganizationById", id);
		}
		return organization;
	}

	@Override
	public void updateOrgInternalCode(Long id, String orgInternalCode) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("orgInternalCode", orgInternalCode);
		getSqlMapClientTemplate().update("organization.updateOrgInternalCode",
				map);
		cacheService
				.remove(MemCacheConstant.ORGANIZATION_NAMESPACE,
						MemCacheConstant.generateCacheKeyFromId(
								Organization.class, id));
		invalidateFindOrgsByParentIdNamespace(getSimpleOrgById(id)
				.getParentOrg());
	}

	@Override
	public void editChildOrganizationDeptNo(String oldDeptNo, String newDeptNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oldDeptNo", oldDeptNo);
		map.put("newDeptNo", newDeptNo);
		getSqlMapClientTemplate().update(
				"organization.updateChildOrganizationDeptNo", map);
		getSqlMapClientTemplate().update(
				"allorganization.updateChildOrganizationDeptNo", map);
	}

	@Override
	public int countDatasByOrgIdAndTableName(String tableName, String orgIdStr,
			Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("orgIdStr", orgIdStr);
		map.put("orgId", orgId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"organization.getcountDatas", map);
	}

	@Override
	public void deleteOrgById(Long id) {
		// Organization organization = getSimpleOrgById(id);
		// getSqlMapClientTemplate().delete("organization.deleteById", id);
		// cacheService.remove(CacheKeyGenerator.generateCacheKeyFromId(
		// Organization.class, id));
		// invalidateFindOrgsByParentIdNamespace(organization.getParentOrg());
		Organization organization = getSimpleOrgById(id);
		getSqlMapClientTemplate().delete("organization.deleteById", id);
		Map map = new HashMap();
		map.put("id", id);
		map.put("changeDate", new Date());
		getSqlMapClientTemplate().update("allorganization.updateChangeInfo",
				map);
		cacheService
				.remove(MemCacheConstant.ORGANIZATION_NAMESPACE,
						MemCacheConstant.generateCacheKeyFromId(
								Organization.class, id));
		invalidateFindOrgsByParentIdNamespace(organization.getParentOrg());
	}

	@Override
	public void updateOrgsSeqAfterReferSeq(Long parentId, Long seq, Long index) {
		// Map map = new HashMap();
		// map.put("parentId", parentId);
		// map.put("seq", seq);
		// map.put("index", index);
		// getSqlMapClientTemplate().update(
		// "organization.updateOrgsSeqAfterReferSeq", map);
		//
		// List<Organization> organizations = getSqlMapClientTemplate()
		// .queryForList("organization.findOrgsSeqAfterReferSeq", map);
		// deleteOrgsFromCache(organizations);
		Map map = new HashMap();
		map.put("parentId", parentId);
		map.put("seq", seq);
		map.put("index", index);
		getSqlMapClientTemplate().update(
				"organization.updateOrgsSeqAfterReferSeq", map);
		getSqlMapClientTemplate().update(
				"allorganization.updateOrgsSeqAfterReferSeq", map);

		List<Organization> organizations = getSqlMapClientTemplate()
				.queryForList("organization.findOrgsSeqAfterReferSeq", map);
		deleteOrgsFromCache(organizations);
	}

	private void deleteOrgsFromCache(List<Organization> organizations) {
		for (Organization organization : organizations) {
			cacheService.remove(MemCacheConstant.ORGANIZATION_NAMESPACE,
					MemCacheConstant.generateCacheKeyFromId(Organization.class,
							organization.getId()));
			cacheService.remove(MemCacheConstant
					.getOrganizationNameSpaceByIdAndType(organization.getId(),
							"getFullOrgById"));
			invalidateFindOrgsByParentIdNamespace(organization.getParentOrg());
		}
	}

	@Override
	public Organization updateFunParentOrgSubCount(Long id, int num) {
		// Organization org = getSimpleOrgById(id);
		// org.setSubCountFun(org.getSubCountFun() + num);
		// Map map = new HashMap();
		// map.put("id", id);
		// map.put("subCountFun", org.getSubCountFun());
		// getSqlMapClientTemplate().update("organization.updatOrgSubCountFun",
		// map);
		//
		// cacheService.remove(CacheKeyGenerator.generateCacheKeyFromId(
		// Organization.class, id));
		// invalidateFindOrgsByParentIdNamespace(org.getParentOrg());
		//
		// return getSimpleOrgById(id);
		Organization org = getSimpleOrgById(id);
		org.setSubCountFun(org.getSubCountFun() + num);
		Map map = new HashMap();
		map.put("id", id);
		map.put("subCountFun", org.getSubCountFun());
		getSqlMapClientTemplate().update("organization.updatOrgSubCountFun",
				map);
		getSqlMapClientTemplate().update("allorganization.updatOrgSubCountFun",
				map);

		cacheService
				.remove(MemCacheConstant.ORGANIZATION_NAMESPACE,
						MemCacheConstant.generateCacheKeyFromId(
								Organization.class, id));
		invalidateFindOrgsByParentIdNamespace(org.getParentOrg());

		return getSimpleOrgById(id);
	}

	@Override
	public int updateAllOrgSubCount(Long id) {
		// 组织机构备份表SubCount通过查询统计
		return getSqlMapClientTemplate().update(
				"allorganization.updatAllOrgSubCount", id);
	}

	@Override
	public void updateMaxCodeById(Long id) {
		getSqlMapClientTemplate().update("organization.updateMaxCodeById", id);
	}

	@Override
	public Integer getMaxCodeById(Long id) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"organization.getMaxCodeById", id);
	}

	@Override
	public List<String> getDepartmentnosByParentOrgId(Long parentOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", parentOrgId);
		return getSqlMapClientTemplate().queryForList(
				"organization.getDepartmentnosByParentOrgId", map);
	}
}

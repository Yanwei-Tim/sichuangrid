package com.tianque.plugin.orgchange.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationType;

@Repository("backupOrganizationDao")
@SuppressWarnings("unchecked")
public class BackupOrganizationDaoImpl extends AbstractBaseDao implements
		BackupOrganizationDao {

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
	@Override
	public List<Organization> findAllOrganizationsByParentId(Long parentId) {
		return getSqlMapClientTemplate().queryForList(
				"allorganization.findAllOrganizationsByParentId", parentId);
	}

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
	@Override
	public Organization getSimpleAllOrgById(Long id) {
		Organization organization = (Organization) getSqlMapClientTemplate()
				.queryForObject("allorganization.findAllOrgById", id);
		return organization;
	}

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
	@Override
	public List<Organization> findAllOrgsByParentIdAndOrgTypeInternalIds(
			Long parentId, Long[] orgTypeInterIds) {
		List<Organization> organizations = new ArrayList<Organization>();
		for (int i = 0; orgTypeInterIds != null && i < orgTypeInterIds.length; i++) {
			if (OrganizationType.ADMINISTRATIVE_REGION == orgTypeInterIds[i]
					.intValue())
				organizations.addAll(findAllOrgsByParentIdAndOrgTypeInternalId(
						parentId, OrganizationType.ADMINISTRATIVE_REGION));
			if (OrganizationType.FUNCTIONAL_ORG == orgTypeInterIds[i]
					.intValue())
				organizations.addAll(findAllOrgsByParentIdAndOrgTypeInternalId(
						parentId, OrganizationType.FUNCTIONAL_ORG));
			if (OrganizationType.OTHER == orgTypeInterIds[i].intValue())
				organizations.addAll(findAllOrgsByParentIdAndOrgTypeInternalId(
						parentId, OrganizationType.OTHER));
			if (OrganizationType.PARTYWORK == orgTypeInterIds[i].intValue())
				organizations.addAll(findAllOrgsByParentIdAndOrgTypeInternalId(
						parentId, OrganizationType.PARTYWORK));
		}
		Collections.sort(organizations, new OrgComparator());
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

	private List<Organization> findAllOrgsByParentIdAndOrgTypeInternalId(
			Long parentId, int orgTypeInternalId) {
		List<Organization> organizations = new ArrayList<Organization>();

		Map map = new HashMap();
		map.put("parentId", parentId);
		map.put("orgTypeInternalId", orgTypeInternalId);
		organizations = getSqlMapClientTemplate().queryForList(
				"allorganization.findAllOrgsByParentIdAndOrgTypeInternalId",
				map);

		return organizations;
	}

	@Override
	public List<Organization> findAllFunOrgsByParentIdAndOrgTypes(
			Long parentId, Long[] propertyDictIds) {
		Map map = new HashMap();
		map.put("parentId", parentId);
		map.put("orgTypes", propertyDictIds);
		return getSqlMapClientTemplate().queryForList(
				"allorganization.findAlFunOrgsByParentIdAndOrgTypes", map);
	}

	@Override
	public List<Organization> findAllFunOrgsByParentOrgIdAndOrgTypes(
			Long parentOrgId, Long[] propertyDictIds) {
		Map map = new HashMap();
		map.put("parentId", parentOrgId);
		map.put("orgTypes", propertyDictIds);
		return getSqlMapClientTemplate().queryForList(
				"allorganization.findAllFunOrgsByParentOrgIdAndOrgTypes", map);
	}

}

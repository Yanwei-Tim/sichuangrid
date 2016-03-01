package com.tianque.plugin.orgchange.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.OrganizationHelper;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Session;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.orgchange.dao.BackupOrganizationDao;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

@Transactional
@Service("backupOrganizationService")
public class BackupOrganizationServiceImpl extends LogableService implements
		BackupOrganizationService {
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private BackupOrganizationDao backupOrganizationDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationHelper organizationHelper;

	@Override
	public List<Organization> findAllOrganizationsByParentId(Long parentId) {
		try {
			return backupOrganizationDao
					.findAllOrganizationsByParentId(parentId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类BackupOrganizationServiceImpl的findAllOrganizationsByParentId方法出错",
					"查询allorganization数据出错", e);
		}
	}

	@Override
	public Organization getFullAllOrgById(Long id) {
		Organization organization = backupOrganizationDao
				.getSimpleAllOrgById(id);
		if (organization.getParentOrg() != null
				&& organization.getParentOrg().getId() != null) {
			organization.setParentOrg(backupOrganizationDao
					.getSimpleAllOrgById(organization.getParentOrg().getId()));
			organization.getParentOrg().setOrgLevel(
					propertyDictService.getPropertyDictById(organization
							.getParentOrg().getOrgLevel().getId()));
		}
		if (organization.getParentFunOrg() != null
				&& organization.getParentFunOrg().getId() != null) {
			organization
					.setParentFunOrg(backupOrganizationDao
							.getSimpleAllOrgById(organization.getParentFunOrg()
									.getId()));
		}
		organization.setOrgLevel(propertyDictService
				.getPropertyDictById(organization.getOrgLevel().getId()));
		organization.setOrgType(propertyDictService
				.getPropertyDictById(organization.getOrgType().getId()));
		if (organization.getFunctionalOrgType() != null) {
			organization.setFunctionalOrgType(propertyDictService
					.getPropertyDictById(organization.getFunctionalOrgType()
							.getId()));
		}

		organization.setFullOrgName(organizationHelper.getRelativeName(id));
		return organization;
	}

	@Override
	public List<Organization> findAllOrgsByParentIdAndOrgTypeInternalIds(
			Long parentId, Long[] orgTypeInternalIds) {
		return backupOrganizationDao
				.findAllOrgsByParentIdAndOrgTypeInternalIds(parentId,
						orgTypeInternalIds);
	}

	@Override
	public List<Organization> findAllFunOrgsByParentId(Long parentId) {
		if (parentId == null) {
			Session session = ThreadVariable.getSession();
			parentId = permissionService.getSimpleUserById(session.getUserId())
					.getOrganization().getId();
		}

		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.FUNCTIONAL_ORG);
		Long[] propertyDictIds = new Long[propertyDicts.size()];
		for (int i = 0; i < propertyDicts.size(); i++) {
			propertyDictIds[i] = propertyDicts.get(i).getId();
		}
		return backupOrganizationDao.findAllFunOrgsByParentIdAndOrgTypes(
				parentId, propertyDictIds);
	}

	@Override
	public List<Organization> findAllFunOrgsByParentOrgId(Long parentOrgId) {
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.FUNCTIONAL_ORG);
		Long[] propertyDictIds = new Long[propertyDicts.size()];
		for (int i = 0; i < propertyDicts.size(); i++) {
			propertyDictIds[i] = propertyDicts.get(i).getId();
		}
		return backupOrganizationDao.findAllFunOrgsByParentOrgIdAndOrgTypes(
				parentOrgId, propertyDictIds);
	}

	@Override
	public List<Long> findAllRelatedOrgIdsByUserOrgId(int startLevelId) {
		return null;
	}

	/**
	 * 
	 * @Title: findAllorganizationsByParentIdAndChangetype
	 * @Description: TODO查询出备份组织机构数据
	 * @param @param map
	 * @param @return 设定文件
	 * @return Organization 返回类型
	 * @author wanggz
	 * @date 2014-11-4 下午03:30:07
	 */
	@Override
	public Organization findAllorganizationsByParentIdAndChangetype(Long orgId) {

		try {
			return backupOrganizationDao.getSimpleAllOrgById(orgId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类BackupOrganizationServiceImpl的findAllorganizationsByParentIdAndChangetype方法出错",
					"查询allorganization数据出错", e);
		}
	}

}

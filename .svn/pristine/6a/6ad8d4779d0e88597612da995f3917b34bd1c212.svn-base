package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.dao.MergeOrganizationDao;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.service.MergeOrganizationService;
import com.tianque.service.bridge.MergeOrganizationUtil;
import com.tianque.service.orgMergeHelper.AbstractMerge;
import com.tianque.service.orgMergeHelper.BaseInfoMerge;
import com.tianque.service.orgMergeHelper.OrgMerge;
import com.tianque.service.orgMergeHelper.UserMerge;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

@Service("mergeOrganizationService")
@Transactional
public class MergeOrganizationServiceImpl extends LogableService implements
		MergeOrganizationService {
	@Autowired
	private MergeOrganizationDao mergeOrganizationDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PermissionService permissionService;

	private List<AbstractMerge> getMerges() {
		List<AbstractMerge> merges = new ArrayList<AbstractMerge>();
		merges.add(new OrgMerge(mergeOrganizationDao));
		merges.add(new UserMerge(mergeOrganizationDao));
		merges.add(new BaseInfoMerge(mergeOrganizationDao));
		return merges;
	}

	@Override
	public void mergeOrganizationByOrgId(Long orgId, Long mergeOrgId) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(mergeOrgId);
		Organization mergeorganization = organizationDubboService
				.getSimpleOrgById(orgId);
		if (organization.getOrgLevel() == mergeorganization.getOrgLevel()) {
			isMergeOgridOrganization(organization, orgId);
		} else {
			deleteUserRoleRelasByRoleId(orgId);
			isMergeOgridOrganization(organization, orgId);
		}
		// organizationService.deleteOrgById(orgId);
	}

	private void updateObject(Long orgId, String orgInternalCode, Long mergeId,
			List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			mergeOrganizationDao.updatObject(orgId, orgInternalCode,
					list.get(i), mergeId);
		}
	}

	private void isMergeOgridOrganization(Organization organization, Long orgId) {
		if (organizationDubboService.isGridOrganization(organization.getId())) {
			updateObject(organization.getId(),
					organization.getOrgInternalCode(), orgId,
					MergeOrganizationUtil.tableList);
		} else {
			updateObject(organization.getId(),
					organization.getOrgInternalCode(), orgId,
					MergeOrganizationUtil.orgLevelTableList);
		}
	}

	private void deleteUserRoleRelasByRoleId(Long orgId) {
		List<User> list = permissionService.findUsersByOrgId(orgId);
		/*
		 * for (int i = 0; i < list.size(); i++) {
		 * mergeOrganizationDao.deleteUserRoleRelasByRoleId
		 * (list.get(i).getId()); }
		 */
		if (list.size() > 0) {
			List<String> ids = new ArrayList<String>();
			for (User user : list) {
				ids.add(user.getId() + "");
			}
			mergeOrganizationDao.deleteUserRoleRelasByRoleIds(ids);
		}
	}
}

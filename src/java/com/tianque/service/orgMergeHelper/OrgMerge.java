package com.tianque.service.orgMergeHelper;

import com.tianque.dao.MergeOrganizationDao;
import com.tianque.domain.Organization;

public class OrgMerge extends AbstractMerge {

	public OrgMerge(MergeOrganizationDao mergeOrganizationDao) {
		super(mergeOrganizationDao);
	}

	@Override
	public void merge(Organization fromOrg, Organization toOrg) {
		// 不合并职能部门
		// 修改parentId,parentFunOrgId,subCount,seq,maxCode,subCountFun,orgInternalCode
		// orgMergeDao.updateParentOrgId(fromOrg.getId(), toOrg.getId());

	}

}

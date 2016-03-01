package com.tianque.service.orgMergeHelper;

import com.tianque.dao.MergeOrganizationDao;
import com.tianque.domain.Organization;

public abstract class AbstractMerge {
	protected MergeOrganizationDao mergeOrganizationDao;

	public AbstractMerge(MergeOrganizationDao mergeOrganizationDao) {
		this.mergeOrganizationDao = mergeOrganizationDao;
	}

	public abstract void merge(Organization fromOrg, Organization toOrg);
}

package com.tianque.issue.service;

import com.tianque.issue.domain.SupervisionAdministration;

public interface SupervisionAdministrationService {

	public SupervisionAdministration getSupervisionAdministrationByOrgId(
			Long orgId);

	public SupervisionAdministration maintainSupervisionAdministration(
			SupervisionAdministration supervisionAdministration);
}

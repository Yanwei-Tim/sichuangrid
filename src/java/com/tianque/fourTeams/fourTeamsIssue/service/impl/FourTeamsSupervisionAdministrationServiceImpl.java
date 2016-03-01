package com.tianque.fourTeams.fourTeamsIssue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsSupervisionAdministrationDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsSupervisionAdministration;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsSupervisionAdministrationService;

@Transactional
@Service("fourTeamsSupervisionAdministrationService")
public class FourTeamsSupervisionAdministrationServiceImpl implements
		FourTeamsSupervisionAdministrationService {

	@Autowired
	private FourTeamsSupervisionAdministrationDao supervisionAdministrationDao;
	@Autowired
	private ValidateHelper validateHelper;

	@Override
	public FourTeamsSupervisionAdministration getSupervisionAdministrationByOrgId(
			Long orgId) {

		if (null == orgId) {
			throw new BusinessValidationException("参数错误");
		}
		return supervisionAdministrationDao
				.getSupervisionAdministrationByOrgId(orgId);
	}

	@Override
	public FourTeamsSupervisionAdministration maintainSupervisionAdministration(
			FourTeamsSupervisionAdministration supervisionAdministration) {
		if (null == supervisionAdministration) {
			throw new BusinessValidationException("参数错误");
		}
		if (StringUtil
				.isStringAvaliable(supervisionAdministration.getContent())) {
			if (validateHelper.illegalStringLength(0, 8000,
					supervisionAdministration.getContent())) {
				throw new BusinessValidationException("日常监督管理输入不能超过8000个字符");
			}
		}
		Organization org = ThreadVariable.getOrganization();
		supervisionAdministration.setOrgId(org.getId());
		supervisionAdministration.setOrgCode(org.getOrgInternalCode());
		if (null != supervisionAdministration.getId()) {
			supervisionAdministrationDao
					.updateSupervisionAdministration(supervisionAdministration);
		} else {
			supervisionAdministrationDao
					.addSupervisionAdministration(supervisionAdministration);
		}
		return supervisionAdministration;
	}
}

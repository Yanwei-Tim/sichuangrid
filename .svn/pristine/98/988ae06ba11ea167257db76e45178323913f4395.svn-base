package com.tianque.issue.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.issue.domain.AdministrativeOrgTimeLimitStandard;

public interface AdministrativeOrgTimeLimitStandardDao {

	public AdministrativeOrgTimeLimitStandard addAdministrativeOrgTimeLimitStandard(
			AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard);

	public AdministrativeOrgTimeLimitStandard updateAdministrativeOrgTimeLimitStandard(
			AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard);

	public void deleteAdministrativeOrgTimeLimitStandardById(Long id);

	public PageInfo<AdministrativeOrgTimeLimitStandard> findAdministrativeOrgTimeLimitStandardByOrgInternalId(
			Long orgInternalId, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	public AdministrativeOrgTimeLimitStandard getAdministrativeOrgTimeLimitStandardById(
			Long id);

	public AdministrativeOrgTimeLimitStandard getAdministrativeOrgTimeLimitStandardByUseLevelId(
			Long useLevelId);

	public AdministrativeOrgTimeLimitStandard getDefaultAdministrativeOrgTimeLimitStandard();

	public AdministrativeOrgTimeLimitStandard getAdminOrgTimeLimitStandardByOrgLevelIdAndIssueTypeId(
			Long orgLevelId, Long issueTypeId, Long orgId);

	public boolean validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId(
			AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard);

	public List<AdministrativeOrgTimeLimitStandard> getAdministrativeOrgTimeLimitStandardByOrginternalid(
			Long createOrg, Long internalId);
}

package com.tianque.fourTeams.fourTeamsIssue.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsAdministrativeOrgTimeLimitStandard;

public interface FourTeamsAdministrativeOrgTimeLimitStandardDao {

	public FourTeamsAdministrativeOrgTimeLimitStandard addAdministrativeOrgTimeLimitStandard(
			FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard);

	public FourTeamsAdministrativeOrgTimeLimitStandard updateAdministrativeOrgTimeLimitStandard(
			FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard);

	public void deleteAdministrativeOrgTimeLimitStandardById(Long id);

	public PageInfo<FourTeamsAdministrativeOrgTimeLimitStandard> findAdministrativeOrgTimeLimitStandardByOrgInternalId(
			Long orgInternalId, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	public FourTeamsAdministrativeOrgTimeLimitStandard getAdministrativeOrgTimeLimitStandardById(
			Long id);

	public FourTeamsAdministrativeOrgTimeLimitStandard getAdministrativeOrgTimeLimitStandardByUseLevelId(
			Long useLevelId);

	public FourTeamsAdministrativeOrgTimeLimitStandard getDefaultAdministrativeOrgTimeLimitStandard();

	public FourTeamsAdministrativeOrgTimeLimitStandard getAdminOrgTimeLimitStandardByOrgLevelIdAndIssueTypeId(
			Long orgLevelId, Long issueTypeId, Long orgId);

	public boolean validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId(
			FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard);
}

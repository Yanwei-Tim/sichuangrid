package com.tianque.fourTeams.fourTeamsIssue.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsAdministrativeOrgTimeLimitStandard;

public interface FourTeamsAdministrativeOrgTimeLimitStandardService {

	public FourTeamsAdministrativeOrgTimeLimitStandard addAdministrativeOrgTimeLimitStandard(
			FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard);

	public FourTeamsAdministrativeOrgTimeLimitStandard updateAdministrativeOrgTimeLimitStandard(
			FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard);

	public Boolean deleteAdministrativeOrgTimeLimitStandardByIds(Long[] ids);

	public FourTeamsAdministrativeOrgTimeLimitStandard getAdministrativeOrgTimeLimitStandardById(
			Long id);

	/**
	 * 
	 * @param userOrganization用户所在层级
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<FourTeamsAdministrativeOrgTimeLimitStandard> findAdministrativeOrgTimeLimitStandardByUserOrganization(
			Organization userOrganization, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * @说明:组织机构所在层级(字典项id)查询行政部门时限标准
	 * @param orgLevelId组织机构所在层级
	 *            (字典项id)
	 * @return
	 */
	public FourTeamsAdministrativeOrgTimeLimitStandard getAdminOrgTimeLimitStandardByOrgLevelIdAndIssueTypeId(
			Long orgLevelId, Long issueTypeId, Long orgId);

	public boolean validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId(
			FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard);
}

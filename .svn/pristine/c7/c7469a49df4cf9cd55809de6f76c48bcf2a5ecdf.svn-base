package com.tianque.issue.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.AdministrativeOrgTimeLimitStandard;

public interface AdministrativeOrgTimeLimitStandardService {

	public AdministrativeOrgTimeLimitStandard addAdministrativeOrgTimeLimitStandard(
			AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard);

	public AdministrativeOrgTimeLimitStandard updateAdministrativeOrgTimeLimitStandard(
			AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard);

	public Boolean deleteAdministrativeOrgTimeLimitStandardByIds(Long[] ids);

	public AdministrativeOrgTimeLimitStandard getAdministrativeOrgTimeLimitStandardById(
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
	public PageInfo<AdministrativeOrgTimeLimitStandard> findAdministrativeOrgTimeLimitStandardByUserOrganization(
			Organization userOrganization, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * @说明:组织机构所在层级(字典项id)查询行政部门时限标准
	 * @param orgLevelId组织机构所在层级
	 *            (字典项id)
	 * @return
	 */
	public AdministrativeOrgTimeLimitStandard getAdminOrgTimeLimitStandardByOrgLevelIdAndIssueTypeId(
			Long orgLevelId, Long issueTypeId, Organization org);

	public boolean validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId(
			AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard);

	public List<AdministrativeOrgTimeLimitStandard> getAdministrativeOrgTimeLimitStandardByOrginternalid(
			Long createOrg, Long internalId);
}

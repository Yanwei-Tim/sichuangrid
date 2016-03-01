package com.tianque.fourTeams.fourTeamsIssue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsAdministrativeOrgTimeLimitStandardDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsAdministrativeOrgTimeLimitStandard;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsAdministrativeOrgTimeLimitStandardService;
import com.tianque.fourTeams.fourTeamsIssue.validator.FourTeamsAdministrativeOrgTimeLimitStandardValidatorImpl;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Transactional
@Service("fourTeamsAdministrativeOrgTimeLimitStandardService")
public class FourTeamsAdministrativeOrgTimeLimitStandardServiceImpl implements
		FourTeamsAdministrativeOrgTimeLimitStandardService {
	@Autowired
	private FourTeamsAdministrativeOrgTimeLimitStandardDao administrativeOrgTimeLimitStandardDao;
	@Qualifier("fourTeamsAdministrativeOrgTimeLimitStandardValidator")
	@Autowired
	private FourTeamsAdministrativeOrgTimeLimitStandardValidatorImpl administrativeOrgTimeLimitStandardValidator;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public FourTeamsAdministrativeOrgTimeLimitStandard addAdministrativeOrgTimeLimitStandard(
			FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
		if (null == administrativeOrgTimeLimitStandard) {
			throw new BusinessValidationException("参数错误！");
		}
		fillOrgInternalId(administrativeOrgTimeLimitStandard);
		validateAdministrativeOrgTimeLimitStandard(administrativeOrgTimeLimitStandard);
		return administrativeOrgTimeLimitStandardDao
				.addAdministrativeOrgTimeLimitStandard(administrativeOrgTimeLimitStandard);
	}

	@Override
	public FourTeamsAdministrativeOrgTimeLimitStandard updateAdministrativeOrgTimeLimitStandard(
			FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
		if (null == administrativeOrgTimeLimitStandard
				|| null == administrativeOrgTimeLimitStandard.getId()) {
			throw new BusinessValidationException("参数错误！");
		}
		fillOrgInternalId(administrativeOrgTimeLimitStandard);
		validateAdministrativeOrgTimeLimitStandard(administrativeOrgTimeLimitStandard);
		return administrativeOrgTimeLimitStandardDao
				.updateAdministrativeOrgTimeLimitStandard(administrativeOrgTimeLimitStandard);
	}

	@Override
	public Boolean deleteAdministrativeOrgTimeLimitStandardByIds(Long[] ids) {
		Boolean deleteState;
		if (null == ids)
			throw new BusinessValidationException("请选择一条记录！");
		try {
			for (Long id : ids) {
				administrativeOrgTimeLimitStandardDao
						.deleteAdministrativeOrgTimeLimitStandardById(id);
			}
			deleteState = true;
		} catch (Exception e) {
			deleteState = false;
			throw new ServiceValidationException(e.getMessage(), e);
		}

		return deleteState;
	}

	@Override
	public PageInfo<FourTeamsAdministrativeOrgTimeLimitStandard> findAdministrativeOrgTimeLimitStandardByUserOrganization(
			Organization userOrganization, Integer page, Integer rows,
			String sidx, String sord) {

		if (null == userOrganization || null == userOrganization.getId()) {
			throw new BusinessValidationException("参数错误！");
		}
		userOrganization = organizationDubboService.getFullOrgById(userOrganization
				.getId());
		userOrganization.setOrgLevel(propertyDictService
				.getPropertyDictById(userOrganization.getOrgLevel().getId()));
		Long orgInternalId = Long.valueOf(userOrganization.getOrgLevel()
				.getInternalId() + "");
		PageInfo<FourTeamsAdministrativeOrgTimeLimitStandard> pageInfo = administrativeOrgTimeLimitStandardDao
				.findAdministrativeOrgTimeLimitStandardByOrgInternalId(
						orgInternalId, page, rows, sidx, sord);
		loadUseLevelDisplayName(pageInfo);
		return pageInfo;
	}

	@Override
	public FourTeamsAdministrativeOrgTimeLimitStandard getAdministrativeOrgTimeLimitStandardById(
			Long id) {
		if (null == id) {
			throw new BusinessValidationException("参数错误！");
		}
		FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandardDao
				.getAdministrativeOrgTimeLimitStandardById(id);
		if (null != administrativeOrgTimeLimitStandard.getUseLevel())
			administrativeOrgTimeLimitStandard.setUseLevel(propertyDictService
					.getPropertyDictById(administrativeOrgTimeLimitStandard
							.getUseLevel().getId()));
		return administrativeOrgTimeLimitStandard;
	}

	private void validateAdministrativeOrgTimeLimitStandard(
			FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
		ValidateResult validateResult = administrativeOrgTimeLimitStandardValidator
				.validate(administrativeOrgTimeLimitStandard);
		if (validateResult.hasError()) {
			throw new BusinessValidationException(
					validateResult.getErrorMessages());
		}
	}

	private void fillOrgInternalId(
			FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
		if (null != administrativeOrgTimeLimitStandard.getUseLevel()
				&& null != administrativeOrgTimeLimitStandard.getUseLevel()
						.getId()) {
			PropertyDict propertyDict = propertyDictService
					.getPropertyDictById(administrativeOrgTimeLimitStandard
							.getUseLevel().getId());
			administrativeOrgTimeLimitStandard.setOrgInternalId(Long.valueOf(""
					+ propertyDict.getInternalId()));
		}
	}

	private void loadUseLevelDisplayName(
			PageInfo<FourTeamsAdministrativeOrgTimeLimitStandard> pageInfo) {

		for (FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard : pageInfo
				.getResult()) {
			if (null != administrativeOrgTimeLimitStandard.getUseLevel())
				administrativeOrgTimeLimitStandard
						.setUseLevel(propertyDictService
								.getPropertyDictById(administrativeOrgTimeLimitStandard
										.getUseLevel().getId()));
			else
				administrativeOrgTimeLimitStandard
						.setUseLevel(new PropertyDict());
		}
	}

	@Override
	public FourTeamsAdministrativeOrgTimeLimitStandard getAdminOrgTimeLimitStandardByOrgLevelIdAndIssueTypeId(
			Long orgLevelId, Long issueTypeId, Long orgId) {
		FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandardDao
				.getAdminOrgTimeLimitStandardByOrgLevelIdAndIssueTypeId(
						orgLevelId, issueTypeId, orgId);
		if (null == administrativeOrgTimeLimitStandard) {
			administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandardDao
					.getDefaultAdministrativeOrgTimeLimitStandard();
		}
		return administrativeOrgTimeLimitStandard;
	}

	@Override
	public boolean validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId(
			FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
		if (null == administrativeOrgTimeLimitStandard) {
			throw new BusinessValidationException("参数错误");
		}
		administrativeOrgTimeLimitStandard.setCreateOrg(ThreadVariable
				.getOrganization());
		return administrativeOrgTimeLimitStandardDao
				.validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId(administrativeOrgTimeLimitStandard);
	}
}

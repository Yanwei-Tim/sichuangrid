package com.tianque.issue.service.impl;

import java.util.List;

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
import com.tianque.issue.dao.AdministrativeOrgTimeLimitStandardDao;
import com.tianque.issue.domain.AdministrativeOrgTimeLimitStandard;
import com.tianque.issue.service.AdministrativeOrgTimeLimitStandardService;
import com.tianque.issue.validator.AdministrativeOrgTimeLimitStandardValidatorImpl;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Transactional
@Service("administrativeOrgTimeLimitStandardService")
public class AdministrativeOrgTimeLimitStandardServiceImpl implements
		AdministrativeOrgTimeLimitStandardService {
	@Autowired
	private AdministrativeOrgTimeLimitStandardDao administrativeOrgTimeLimitStandardDao;
	@Qualifier("administrativeOrgTimeLimitStandardValidator")
	@Autowired
	private AdministrativeOrgTimeLimitStandardValidatorImpl administrativeOrgTimeLimitStandardValidator;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public AdministrativeOrgTimeLimitStandard addAdministrativeOrgTimeLimitStandard(
			AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
		if (null == administrativeOrgTimeLimitStandard) {
			throw new BusinessValidationException("参数错误！");
		}
		fillOrgInternalId(administrativeOrgTimeLimitStandard);
		validateAdministrativeOrgTimeLimitStandard(administrativeOrgTimeLimitStandard);
		return administrativeOrgTimeLimitStandardDao
				.addAdministrativeOrgTimeLimitStandard(administrativeOrgTimeLimitStandard);
	}

	@Override
	public AdministrativeOrgTimeLimitStandard updateAdministrativeOrgTimeLimitStandard(
			AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
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
	public PageInfo<AdministrativeOrgTimeLimitStandard> findAdministrativeOrgTimeLimitStandardByUserOrganization(
			Organization userOrganization, Integer page, Integer rows,
			String sidx, String sord) {

		if (null == userOrganization || null == userOrganization.getId()) {
			throw new BusinessValidationException("参数错误！");
		}
		userOrganization = organizationDubboService
				.getFullOrgById(userOrganization.getId());
		userOrganization.setOrgLevel(propertyDictService
				.getPropertyDictById(userOrganization.getOrgLevel().getId()));
		Long orgInternalId = Long.valueOf(userOrganization.getOrgLevel()
				.getInternalId() + "");
		PageInfo<AdministrativeOrgTimeLimitStandard> pageInfo = administrativeOrgTimeLimitStandardDao
				.findAdministrativeOrgTimeLimitStandardByOrgInternalId(
						orgInternalId, page, rows, sidx, sord);
		loadUseLevelDisplayName(pageInfo);
		return pageInfo;
	}

	@Override
	public AdministrativeOrgTimeLimitStandard getAdministrativeOrgTimeLimitStandardById(
			Long id) {
		if (null == id) {
			throw new BusinessValidationException("参数错误！");
		}
		AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandardDao
				.getAdministrativeOrgTimeLimitStandardById(id);
		if (null != administrativeOrgTimeLimitStandard.getUseLevel())
			administrativeOrgTimeLimitStandard.setUseLevel(propertyDictService
					.getPropertyDictById(administrativeOrgTimeLimitStandard
							.getUseLevel().getId()));
		return administrativeOrgTimeLimitStandard;
	}

	private void validateAdministrativeOrgTimeLimitStandard(
			AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
		ValidateResult validateResult = administrativeOrgTimeLimitStandardValidator
				.validate(administrativeOrgTimeLimitStandard);
		if (validateResult.hasError()) {
			throw new BusinessValidationException(
					validateResult.getErrorMessages());
		}
	}

	private void fillOrgInternalId(
			AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
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
			PageInfo<AdministrativeOrgTimeLimitStandard> pageInfo) {

		for (AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard : pageInfo
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
	public AdministrativeOrgTimeLimitStandard getAdminOrgTimeLimitStandardByOrgLevelIdAndIssueTypeId(
			Long orgLevelId, Long issueTypeId, Organization org) {
		AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard = null;
		if (org != null) {
			administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandardDao
					.getAdminOrgTimeLimitStandardByOrgLevelIdAndIssueTypeId(
							orgLevelId, issueTypeId, org.getId());
		}
		if (null == administrativeOrgTimeLimitStandard
				|| administrativeOrgTimeLimitStandard.getYellowLimitAccept() == null
				|| administrativeOrgTimeLimitStandard.getYellowLimitHandle() == null
				|| administrativeOrgTimeLimitStandard.getYellowLimitVerify() == null
				|| administrativeOrgTimeLimitStandard.getRedLimitAccept() == null
				|| administrativeOrgTimeLimitStandard.getRedLimitHandle() == null
				|| administrativeOrgTimeLimitStandard.getRedLimitVerify() == null) {
			administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandardDao
					.getDefaultAdministrativeOrgTimeLimitStandard();
		}
		return administrativeOrgTimeLimitStandard;
	}

	@Override
	public boolean validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId(
			AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
		if (null == administrativeOrgTimeLimitStandard) {
			throw new BusinessValidationException("参数错误");
		}
		administrativeOrgTimeLimitStandard.setCreateOrg(ThreadVariable
				.getOrganization());
		return administrativeOrgTimeLimitStandardDao
				.validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId(administrativeOrgTimeLimitStandard);
	}

	@Override
	public List<AdministrativeOrgTimeLimitStandard> getAdministrativeOrgTimeLimitStandardByOrginternalid(
			Long createOrg, Long internalId) {
		try {
			return administrativeOrgTimeLimitStandardDao
					.getAdministrativeOrgTimeLimitStandardByOrginternalid(
							createOrg, internalId);
		} catch (Exception e) {
			throw new ServiceValidationException("获取数据出错", e);
		}
	}
}

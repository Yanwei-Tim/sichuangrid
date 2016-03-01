package com.tianque.newVillage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.newVillage.dao.LeadingEnterpriseDao;
import com.tianque.newVillage.domain.LeadingEnterprise;
import com.tianque.newVillage.service.LeadingEnterpriseService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("leadingEnterpriseService")
@Transactional
public class LeadingEnterpriseServiceImpl implements LeadingEnterpriseService {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private LeadingEnterpriseDao leadingEnterpriseDao;
	@Qualifier("leadingEnterpriseValidator")
	@Autowired
	private DomainValidator<LeadingEnterprise> domainValidator;

	@Override
	public LeadingEnterprise addLeadingEnterprise(
			LeadingEnterprise leadingEnterprise) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(leadingEnterprise);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(leadingEnterprise.getOrganization().getId());
		leadingEnterprise.setOrganization(organization);
		try {
			return leadingEnterpriseDao.addLeadingEnterprise(leadingEnterprise);
		} catch (Exception e) {
			throw new ServiceValidationException("新增龙头企业信息错误", e);
		}
	}

	@Override
	public LeadingEnterprise updateLeadingEnterprise(
			LeadingEnterprise leadingEnterprise) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(leadingEnterprise);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			return leadingEnterpriseDao
					.updateLeadingEnterprise(leadingEnterprise);
		} catch (Exception e) {
			throw new ServiceValidationException("修改龙头企业信息错误", e);
		}
	}

	@Override
	public void deleteLeadingEnterpriseByIds(String[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("删除失败，未获得删除参数");
		}
		try {
			leadingEnterpriseDao.deleteLeadingEnterpriseByIds(ids);
		} catch (Exception e) {
			throw new ServiceValidationException("删除数据失败", e);
		}
	}

	@Override
	public LeadingEnterprise getLeadingEnterpriseById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("查询失败，未获得关键数据参数");
		}
		return leadingEnterpriseDao.getLeadingEnterpriseById(id);
	}

	@Override
	public PageInfo<LeadingEnterprise> findLeadingEnterpriseForPageInfo(
			String enterpriseName, Long searchType, Organization organization,
			Integer page, Integer rows, String sidx, String sord) {
		if (organization == null || organization.getId() == null
				|| searchType == null) {
			throw new BusinessValidationException("查询列表数据失败，参数错误");
		}
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		return leadingEnterpriseDao.findLeadingEnterpriseForPageInfo(
				enterpriseName, searchType, organization, page, rows, sidx,
				sord);
	}

}

package com.tianque.newVillage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.dao.OrganizationConstructionDao;
import com.tianque.newVillage.domain.OrganizationConstruction;
import com.tianque.newVillage.service.OrganizationConstructionService;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @ClassName: OrganizationConstructionServiceImpl
 * @Description: 基层组织
 */
@Service("organizationConstructionService")
@Transactional
public class OrganizationConstructionServiceImpl extends LogableService
		implements OrganizationConstructionService {
	@Autowired
	private OrganizationConstructionDao orgConstructionDao;
	@Qualifier("organizationConstructionValidator")
	@Autowired
	private DomainValidator<OrganizationConstruction> domainValidator;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public OrganizationConstruction addOrgConstruction(
			OrganizationConstruction orgConstruction) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(orgConstruction);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			Organization organization = organizationDubboService
					.getSimpleOrgById(orgConstruction.getOrganization().getId());
			orgConstruction.setOrganization(organization);
			return orgConstructionDao.addOrgConstruction(orgConstruction);
		} catch (Exception e) {
			throw new BusinessValidationException("新增信息出现错误");
		}
	}

	@Override
	public OrganizationConstruction getOrgConstructionById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误,获取信息失败");
		}
		return orgConstructionDao.getOrgConstructionById(id);
	}

	@Override
	public void deleteOrgConstructionById(String[] id) {
		if (id == null || id.length == 0) {
			throw new BusinessValidationException("参数错误,删除信息失败");
		}
		orgConstructionDao.deleteOrgConstructionById(id);
	}

	@Override
	public OrganizationConstruction updateOrgConstruction(
			OrganizationConstruction orgConstruction) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(orgConstruction);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			return orgConstructionDao.updateOrgConstruction(orgConstruction);
		} catch (Exception e) {
			throw new BusinessValidationException("修改信息出现错误");
		}
	}

	@Override
	public OrganizationConstruction getOrgConstructionByBasicId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误,获取信息失败");
		}
		return orgConstructionDao.getOrgConstructionByBasicId(id);
	}

}

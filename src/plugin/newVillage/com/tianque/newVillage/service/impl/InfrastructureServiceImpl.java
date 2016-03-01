package com.tianque.newVillage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.dao.InfrastructureDao;
import com.tianque.newVillage.domain.Infrastructure;
import com.tianque.newVillage.service.InfrastructureService;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @ClassName: InfrastructureServiceImpl
 * @Description: 基础设施
 */
@Service("infrastructureService")
@Transactional
public class InfrastructureServiceImpl extends LogableService implements
		InfrastructureService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private InfrastructureDao infrastructureDao;
	@Qualifier("infrastructureValidator")
	@Autowired
	private DomainValidator<Infrastructure> domainValidator;

	@Override
	public Infrastructure addInfrastructure(Infrastructure infrastructure) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(infrastructure);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		// 验证数据库数据唯一性
		if (infrastructure.getNewVillageBasic() != null
				&& infrastructure.getNewVillageBasic().getId() != null) {
			Infrastructure infrastructureNew = infrastructureDao
					.getInfrastructureByBasicId(infrastructure
							.getNewVillageBasic().getId());
			if (infrastructureNew != null) {
				throw new BusinessValidationException("保存基础设施失败");
			}
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(infrastructure.getOrganization().getId());
		infrastructure.setOrganization(organization);
		try {
			return infrastructureDao.addInfrastructure(infrastructure);
		} catch (Exception e) {
			throw new BusinessValidationException("新增信息出现错误");
		}
	}

	@Override
	public Infrastructure getInfrastructureById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误,获取信息失败");
		}
		return infrastructureDao.getInfrastructureById(id);
	}

	@Override
	public void deleteInfrastructureById(String[] id) {
		if (id == null || id.length == 0) {
			throw new BusinessValidationException("参数错误,删除信息失败");
		}
		infrastructureDao.deleteInfrastructureById(id);
	}

	@Override
	public Infrastructure updateInfrastructure(Infrastructure infrastructure) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(infrastructure);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			return infrastructureDao.updateInfrastructure(infrastructure);
		} catch (Exception e) {
			throw new BusinessValidationException("修改信息出现错误");
		}
	}

	@Override
	public Infrastructure getInfrastructureByBasicId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误,获取信息失败");
		}
		return infrastructureDao.getInfrastructureByBasicId(id);
	}
}

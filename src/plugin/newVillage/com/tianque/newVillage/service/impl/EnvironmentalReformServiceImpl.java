package com.tianque.newVillage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.dao.EnvironmentalReformDao;
import com.tianque.newVillage.domain.EnvironmentalReform;
import com.tianque.newVillage.service.EnvironmentalReformService;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @ClassName: EnvironmentalReformServiceImpl
 * @Description: 环境改造
 */
@Service("environmentalReformService")
@Transactional
public class EnvironmentalReformServiceImpl extends LogableService implements
		EnvironmentalReformService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private EnvironmentalReformDao environmentalReformDao;
	@Qualifier("environmentalReformValidator")
	@Autowired
	private DomainValidator<EnvironmentalReform> domainValidator;

	@Override
	public EnvironmentalReform addEnvironmentalReform(
			EnvironmentalReform environmentalReform) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(environmentalReform);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(environmentalReform.getOrganization().getId());
		environmentalReform.setOrganization(organization);
		try {
			return environmentalReformDao
					.addEnvironmentalReform(environmentalReform);
		} catch (Exception e) {
			throw new BusinessValidationException("新增信息出现错误");
		}
	}

	@Override
	public EnvironmentalReform getEnvironmentalReformById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("得到数据失败，未获得相关参数");
		}
		return environmentalReformDao.getEnvironmentalReformById(id);
	}

	@Override
	public void deleteEnvironmentalReformById(String[] id) {
		if (id == null || id.length == 0) {
			throw new BusinessValidationException("删除数据失败，未获得相关参数");
		}
		environmentalReformDao.deleteEnvironmentalReformById(id);
	}

	@Override
	public EnvironmentalReform updateEnvironmentalReform(
			EnvironmentalReform environmentalReform) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(environmentalReform);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			return environmentalReformDao
					.updateEnvironmentalReform(environmentalReform);
		} catch (Exception e) {
			throw new BusinessValidationException("修改信息出现错误");
		}
	}

	@Override
	public EnvironmentalReform getEnvironmentalReformByBasicId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("删除数据失败，未获得相关参数");
		}
		return environmentalReformDao.getEnvironmentalReformByBasicId(id);
	}

}

package com.tianque.newVillage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.dao.IndustryDevelopmentDao;
import com.tianque.newVillage.domain.IndustryDevelopment;
import com.tianque.newVillage.service.IndustryDevelopmentService;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @ClassName: IndustryDevelopmentServiceImpl
 * @Description: 产业发展
 */
@Service("industryDevelopmentService")
@Transactional
public class IndustryDevelopmentServiceImpl extends LogableService implements
		IndustryDevelopmentService {
	@Autowired
	private IndustryDevelopmentDao industryDevelopmentDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Qualifier("industryDevelopmentValidator")
	@Autowired
	private DomainValidator<IndustryDevelopment> domainValidator;

	@Override
	public IndustryDevelopment addIndustryDevelopment(
			IndustryDevelopment industryDevelopment) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(industryDevelopment);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(industryDevelopment.getOrganization().getId());
		industryDevelopment.setOrganization(organization);
		try {
			return industryDevelopmentDao
					.addIndustryDevelopment(industryDevelopment);
		} catch (Exception e) {
			throw new BusinessValidationException("新增信息出现错误");
		}
	}

	@Override
	public IndustryDevelopment getIndustryDevelopmentById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("得到数据失败，未获得相关参数");
		}
		return industryDevelopmentDao.getIndustryDevelopmentById(id);
	}

	@Override
	public void deleteIndustryDevelopmentById(String[] id) {
		if (id == null || id.length == 0) {
			throw new BusinessValidationException("删除数据失败，未获得相关参数");
		}
		industryDevelopmentDao.deleteIndustryDevelopmentById(id);
	}

	@Override
	public IndustryDevelopment updateIndustryDevelopment(
			IndustryDevelopment industryDevelopment) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(industryDevelopment);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			return industryDevelopmentDao
					.updateIndustryDevelopment(industryDevelopment);
		} catch (Exception e) {
			throw new BusinessValidationException("修改信息出现错误");
		}
	}

	@Override
	public IndustryDevelopment getIndustryDevelopmentByBasicId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("查询数据失败，未获得相关参数");
		}
		return industryDevelopmentDao.getIndustryDevelopmentByBasicId(id);
	}

}

package com.tianque.newVillage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.dao.CapitalInvestedDao;
import com.tianque.newVillage.domain.CapitalInvested;
import com.tianque.newVillage.service.CapitalInvestedService;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @ClassName: CapitalInvestedServiceImpl
 * @Description: 资金投入
 */
@Service("capitalInvestedService")
@Transactional
public class CapitalInvestedServiceImpl extends LogableService implements
		CapitalInvestedService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private CapitalInvestedDao capitalInvestedDao;
	@Qualifier("capitalInvestedValidator")
	@Autowired
	private DomainValidator<CapitalInvested> domainValidator;

	@Override
	public CapitalInvested addCapitalInvested(CapitalInvested capitalInvested) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(capitalInvested);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(capitalInvested.getOrganization().getId());
		capitalInvested.setOrganization(organization);
		try {
			return capitalInvestedDao.addCapitalInvested(capitalInvested);
		} catch (Exception e) {
			throw new BusinessValidationException("新增信息出现错误");
		}
	}

	@Override
	public CapitalInvested getCapitalInvestedById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("获取信息失败，未获得相关参数");
		}
		return capitalInvestedDao.getCapitalInvestedById(id);
	}

	@Override
	public void deleteCapitalInvestedById(String[] id) {
		if (id == null || id.length == 0) {
			throw new BusinessValidationException("删除数据失败，未获得相关参数");
		}
		capitalInvestedDao.deleteCapitalInvestedById(id);
	}

	@Override
	public CapitalInvested updateCapitalInvested(CapitalInvested capitalInvested) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(capitalInvested);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			return capitalInvestedDao.updateCapitalInvested(capitalInvested);
		} catch (Exception e) {
			throw new BusinessValidationException("修改信息出现错误");
		}
	}

	@Override
	public CapitalInvested getCapitalInvestedByBasicId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("获取信息失败，未获得相关参数");
		}
		return capitalInvestedDao.getCapitalInvestedByBasicId(id);
	}

}

package com.tianque.newVillage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.dao.NewVillageDao;
import com.tianque.newVillage.domain.NewVillage;
import com.tianque.newVillage.service.NewVillageService;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @ClassName: NewVillageServiceImpl
 * @Description: 新农村建设
 */
@Service("newVillageService")
@Transactional
public class NewVillageServiceImpl extends LogableService implements
		NewVillageService {
	@Autowired
	private NewVillageDao newVillageDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Qualifier("newVillageValidator")
	@Autowired
	private DomainValidator<NewVillage> domainValidator;

	@Override
	public NewVillage addNewVillage(NewVillage newVillage) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator.validate(newVillage);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(newVillage.getOrganization().getId());
		newVillage.setOrganization(organization);
		try {
			return newVillageDao.addNewVillage(newVillage);
		} catch (Exception e) {
			throw new BusinessValidationException("新增信息出现错误");
		}
	}

	@Override
	public NewVillage getNewVillageById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误,获取信息失败");
		}
		return newVillageDao.getNewVillageById(id);
	}

	@Override
	public void deleteNewVillageById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误,删除信息失败");
		}
		newVillageDao.deleteNewVillageById(id);
	}

	@Override
	public NewVillage updateNewVillage(NewVillage newVillage) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator.validate(newVillage);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			return newVillageDao.updateNewVillage(newVillage);
		} catch (Exception e) {
			throw new BusinessValidationException("修改信息出现错误");
		}
	}

	@Override
	public NewVillage getNewVillageByBasicId(Long basicId) {
		if (basicId == null) {
			throw new BusinessValidationException("参数错误,获取信息失败");
		}
		return newVillageDao.getNewVillageByBasicId(basicId);
	}

}

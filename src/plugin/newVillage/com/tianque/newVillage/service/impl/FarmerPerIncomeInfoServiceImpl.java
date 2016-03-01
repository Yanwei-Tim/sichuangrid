package com.tianque.newVillage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.dao.FarmerPerIncomeInfoDao;
import com.tianque.newVillage.domain.FarmerPerIncomeInfo;
import com.tianque.newVillage.service.FarmerPerIncomeInfoService;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @ClassName: FarmerPerIncomeInfoServiceImpl
 * @Description: 农民人均收入信息
 */
@Service("farmerPerIncomeInfoService")
@Transactional
public class FarmerPerIncomeInfoServiceImpl extends LogableService implements
		FarmerPerIncomeInfoService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private FarmerPerIncomeInfoDao farmerPerIncomeInfoDao;
	@Qualifier("farmerPerIncomeInfoValidator")
	@Autowired
	private DomainValidator<FarmerPerIncomeInfo> domainValidator;

	@Override
	public FarmerPerIncomeInfo addFarmerPerIncomeInfo(
			FarmerPerIncomeInfo farmerPerIncomeInfo) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(farmerPerIncomeInfo);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(farmerPerIncomeInfo.getOrganization().getId());
		farmerPerIncomeInfo.setOrganization(organization);
		try {
			return farmerPerIncomeInfoDao
					.addFarmerPerIncomeInfo(farmerPerIncomeInfo);
		} catch (Exception e) {
			throw new BusinessValidationException("新增信息出现错误");
		}
	}

	@Override
	public FarmerPerIncomeInfo getFarmerPerIncomeInfoById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("得到数据失败，未获得相关参数");
		}
		return farmerPerIncomeInfoDao.getFarmerPerIncomeInfoById(id);
	}

	@Override
	public void deleteFarmerPerIncomeInfoById(String[] id) {
		if (id == null || id.length == 0) {
			throw new BusinessValidationException("删除数据失败，未获得相关参数");
		}
		farmerPerIncomeInfoDao.deleteFarmerPerIncomeInfoById(id);
	}

	@Override
	public FarmerPerIncomeInfo updateFarmerPerIncomeInfo(
			FarmerPerIncomeInfo farmerPerIncomeInfo) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(farmerPerIncomeInfo);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			return farmerPerIncomeInfoDao
					.updateFarmerPerIncomeInfo(farmerPerIncomeInfo);
		} catch (Exception e) {
			throw new BusinessValidationException("修改信息出现错误");
		}
	}

	@Override
	public FarmerPerIncomeInfo getFarmerPerIncomeInfoByBasicId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("查询数据失败，未获得相关参数");
		}
		return farmerPerIncomeInfoDao.getFarmerPerIncomeInfoByBasicId(id);
	}

}

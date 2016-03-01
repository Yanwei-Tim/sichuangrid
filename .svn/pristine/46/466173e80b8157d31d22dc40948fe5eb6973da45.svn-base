package com.tianque.newVillage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.dao.CommonServiceInfoDao;
import com.tianque.newVillage.domain.CommonServiceInfo;
import com.tianque.newVillage.service.CommonServiceInfoService;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @ClassName: CommonServiceInfoServiceImpl
 * @Description: 公共服务
 */
@Service("commonServiceInfoService")
@Transactional
public class CommonServiceInfoServiceImpl extends LogableService implements
		CommonServiceInfoService {
	@Autowired
	private CommonServiceInfoDao commonServiceInfoDao;
	@Qualifier("commonServiceInfoValidator")
	@Autowired
	private DomainValidator<CommonServiceInfo> domainValidator;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public CommonServiceInfo addCommonServiceInfo(
			CommonServiceInfo commonServiceInfo) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(commonServiceInfo);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(commonServiceInfo.getOrganization().getId());
		commonServiceInfo.setOrganization(organization);
		try {
			return commonServiceInfoDao.addCommonServiceInfo(commonServiceInfo);
		} catch (Exception e) {
			throw new BusinessValidationException("新增信息出现错误");
		}
	}

	@Override
	public CommonServiceInfo getCommonServiceInfoById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("得到数据失败，未获得相关参数");
		}
		return commonServiceInfoDao.getCommonServiceInfoById(id);
	}

	@Override
	public void deleteCommonServiceInfoById(String[] id) {
		if (id == null || id.length == 0) {
			throw new BusinessValidationException("删除数据失败，未获得相关参数");
		}
		commonServiceInfoDao.deleteCommonServiceInfoById(id);
	}

	@Override
	public CommonServiceInfo updateCommonServiceInfo(
			CommonServiceInfo commonServiceInfo) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(commonServiceInfo);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			return commonServiceInfoDao
					.updateCommonServiceInfo(commonServiceInfo);
		} catch (Exception e) {
			throw new BusinessValidationException("修改信息出现错误");
		}
	}

	@Override
	public CommonServiceInfo getCommonServiceInfoByBasicId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("查询数据失败，未获得相关参数");
		}
		return commonServiceInfoDao.getCommonServiceInfoByBasicId(id);
	}

}

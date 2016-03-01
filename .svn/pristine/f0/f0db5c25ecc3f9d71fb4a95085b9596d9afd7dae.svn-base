package com.tianque.newVillage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.newVillage.dao.BasicInfomationDao;
import com.tianque.newVillage.domain.BasicInfoMation;
import com.tianque.newVillage.domain.NewVillageLeaderTeam;
import com.tianque.newVillage.service.BasicInfomationService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Service("basicInfomationService")
public class BasicInfomationServiceImpl implements BasicInfomationService {

	@Autowired
	private BasicInfomationDao basicInfomationDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Qualifier("newVillageLeaderTeamValidator")
	@Autowired
	private DomainValidator<NewVillageLeaderTeam> domainValidator;
	@Qualifier("basicInfoMationValidator")
	@Autowired
	private DomainValidator<BasicInfoMation> basicInfoMationValidator;

	@Override
	public BasicInfoMation addBasicInfoMation(BasicInfoMation basicInfoMation) {
		// 数据校验
		ValidateResult baseDataValidator = basicInfoMationValidator
				.validate(basicInfoMation);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		fullOrganization(basicInfoMation);
		try {
			return basicInfomationDao.addBasicInfoMation(basicInfoMation);
		} catch (Exception e) {
			throw new ServiceValidationException("新增失败", e);
		}

	}

	private void fullOrganization(BasicInfoMation basicInfoMation) {
		if (basicInfoMation.getOrganization().getId() != null) {
			Organization organization = organizationDubboService
					.getSimpleOrgById(basicInfoMation.getOrganization().getId());
			basicInfoMation.setOrganization(organization);
		}
	}

	@Override
	public BasicInfoMation updateBasicInfoMation(BasicInfoMation basicInfoMation) {
		// 数据校验
		ValidateResult baseDataValidator = basicInfoMationValidator
				.validate(basicInfoMation);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			return basicInfomationDao.updateBasicInfoMation(basicInfoMation);
		} catch (Exception e) {
			throw new ServiceValidationException("修改失败", e);
		}

	}

	@Override
	public BasicInfoMation getBasicInfoMationByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("获取数据失败，关键参数未获得");
		}
		return basicInfomationDao.getBasicInfoMationByOrgId(orgId);
	}

	@Override
	public BasicInfoMation getBasicInfoMationById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("获取数据失败，关键参数未获得");
		}
		return basicInfomationDao.getBasicInfoMationById(id);
	}

	@Override
	public BasicInfoMation updateBasicInfomationImgUrl(
			BasicInfoMation basicInfoMation) {
		return basicInfomationDao.updateBasicInfomationImgUrl(basicInfoMation);
	}

	@Override
	public BasicInfoMation updateBasicInfomationbasicIntroduction(
			BasicInfoMation basicInfoMation) {
		return basicInfomationDao
				.updateBasicInfomationbasicIntroduction(basicInfoMation);
	}

	@Override
	public NewVillageLeaderTeam addNewVillageLeaderTeam(
			NewVillageLeaderTeam newVillageLeaderTeam) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(newVillageLeaderTeam);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			Organization organization = organizationDubboService
					.getSimpleOrgById(newVillageLeaderTeam.getOrganization()
							.getId());
			newVillageLeaderTeam.setOrganization(organization);
			return basicInfomationDao
					.addNewVillageLeaderTeam(newVillageLeaderTeam);
		} catch (Exception e) {
			throw new ServiceValidationException("新增领导班子数据失败", e);
		}
	}

	@Override
	public NewVillageLeaderTeam updateNewVillageLeaderTeam(
			NewVillageLeaderTeam newVillageLeaderTeam) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(newVillageLeaderTeam);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			return basicInfomationDao
					.updateNewVillageLeaderTeam(newVillageLeaderTeam);
		} catch (Exception e) {
			throw new ServiceValidationException("修改领导班子数据失败", e);
		}
	}

	@Override
	public void deleteNewVillageLeaderTeamById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("删除数据失败，关键参数未获得");
		}
		try {
			basicInfomationDao.deleteNewVillageLeaderTeamById(id);
		} catch (Exception e) {
			throw new ServiceValidationException("删除领导班子信息失败", e);
		}

	}

	@Override
	public NewVillageLeaderTeam updateNewVillageLeaderTeamImgUrl(
			NewVillageLeaderTeam newVillageLeaderTeam) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(newVillageLeaderTeam);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			return basicInfomationDao
					.updateNewVillageLeaderTeamImgUrl(newVillageLeaderTeam);
		} catch (Exception e) {
			throw new ServiceValidationException("修改图像信息失败", e);
		}

	}

	@Override
	public NewVillageLeaderTeam getNewVillageLeaderTeamById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("获取信息失败");
		}
		return basicInfomationDao.getNewVillageLeaderTeamById(id);
	}

	@Override
	public List<NewVillageLeaderTeam> getNewVillageLeaderTeamByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("获取信息失败");
		}
		return basicInfomationDao.getNewVillageLeaderTeamByOrgId(orgId);
	}

}

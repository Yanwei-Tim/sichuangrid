package com.tianque.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.EstateInformationDao;
import com.tianque.domain.EstateInformation;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.EstateInformationService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("estateInformationService")
@Transactional
public class EstateInformationServiceImpl extends AbstractBaseService implements
		EstateInformationService {
	@Autowired
	private EstateInformationDao estateInformationDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public EstateInformation getEstateInformationById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id没有获得");
		}
		return estateInformationDao.getEstateInformationById(id);
	}

	@Override
	public EstateInformation addEstateInformation(
			EstateInformation estateInformation) {
		if (!validate(estateInformation)) {
			throw new BusinessValidationException("数据输入错误");
		}
		autoFillOrganizationInternalCode(estateInformation);
		return estateInformationDao.addEstateInformation(estateInformation);
	}

	@Override
	public PageInfo<EstateInformation> findEstateInformationsForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (orgId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return constructEmptyPageInfo();
			} else {
				return estateInformationDao
						.findEstateInformationForPageByOrgInternalCode(
								org.getOrgInternalCode(), pageNum, pageSize,
								sidx, sord);
			}
		}
	}

	@Override
	public EstateInformation updateEstateInInformation(
			EstateInformation estateInformation) {
		if (estateInformation.getId() == null) {
			throw new BusinessValidationException("id没有获得");
		}
		if (!validate(estateInformation)) {
			throw new BusinessValidationException("数据输入错误");
		}
		return estateInformationDao.updateEstateInformation(estateInformation);
	}

	@Override
	public void deleteEstateInformationById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id没有获得");
		}
		estateInformationDao.deleteEstateInformationById(id);
	}

	@Override
	public int getExistedProprietaryNoCount(EstateInformation estateInformation) {
		if (estateInformation == null
				|| estateInformation.getProprietaryNo() == null) {
			throw new BusinessValidationException("房产证号没有获得");
		}
		return estateInformationDao
				.getExistedProprietaryNoCount(estateInformation);
	}

	@Override
	public int getExistedLandPermitCount(EstateInformation estateInformation) {
		if (estateInformation == null
				|| estateInformation.getLandPermit() == null) {
			throw new BusinessValidationException("土地证号没有获得");
		}
		return estateInformationDao
				.getExistedLandPermitCount(estateInformation);
	}

	private boolean validate(EstateInformation estateInformation) {
		if (estateInformation == null
				|| estateInformation.getOrganization() == null
				|| estateInformation.getOrganization().getId() == null) {
			return false;
		}
		return true;
	}

	private void autoFillOrganizationInternalCode(
			EstateInformation estateInformation) {
		Organization org = organizationDubboService
				.getSimpleOrgById(estateInformation.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		estateInformation.setOrgInternalCode(org.getOrgInternalCode());
	}

	private PageInfo<EstateInformation> constructEmptyPageInfo() {
		PageInfo<EstateInformation> result = new PageInfo<EstateInformation>();
		result.setResult(new ArrayList<EstateInformation>());
		return result;
	}

}

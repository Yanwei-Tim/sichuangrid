package com.tianque.baseInfo.laborEmploymentUnit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.laborEmploymentUnit.dao.LaborEmploymentUnitDao;
import com.tianque.baseInfo.laborEmploymentUnit.domain.LaborEmploymentUnit;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("laborEmploymentUnitService")
@Transactional
public class LaborEmploymentUnitServiceImpl extends LogableService implements
		LaborEmploymentUnitService {

	@Autowired
	@Qualifier("laborEmploymentUnitValidator")
	private DomainValidator<LaborEmploymentUnit> domainValidator;
	@Autowired
	private OrganizationDubboService orgnizationService;
	@Autowired
	private LaborEmploymentUnitDao laborEmploymentUnitDao;

	@Override
	public LaborEmploymentUnit addLaborEmploymentUnit(
			LaborEmploymentUnit laborEmploymentUnit) {
		evaluationForIsEmphasisAndIsKey(laborEmploymentUnit, "add");
		ValidateResult idleValidator = domainValidator
				.validate(laborEmploymentUnit);
		if (idleValidator.hasError()) {
			throw new BusinessValidationException(
					idleValidator.getErrorMessages());
		} else if (hasDuplicateLaborEmploymentUnit(laborEmploymentUnit
				.getOrganization().getId(),
				laborEmploymentUnit.getCompanyName(),
				laborEmploymentUnit.getId())) {
			throw new BusinessValidationException("该网格下已存在相同单位");
		}
		laborEmploymentUnit
				.setOrgInternalCode(orgnizationService.getSimpleOrgById(
						laborEmploymentUnit.getOrganization().getId())
						.getOrgInternalCode());
		autoFillChinesePinyin(laborEmploymentUnit);
		return laborEmploymentUnitDao
				.addLaborEmploymentUnit(laborEmploymentUnit);
	}

	@Override
	public void deleteLaborEmploymentUnit(List<Long> idList) {
		if (null == idList) {
			throw new BusinessValidationException("删除实有单位时idList不能为空");
		}
		for (Long id : idList) {
			if (null == id || id < 0L) {
				throw new BusinessValidationException("删除实有单位时id不合法");
			}
			laborEmploymentUnitDao.deleteLaborEmploymentUnit(id);
		}
	}

	@Override
	public PageInfo<LaborEmploymentUnit> findLaborEmploymentUnitForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sortField,
			String sord, Boolean logOut) {
		return laborEmploymentUnitDao
				.findLaborEmploymentUnitForPageByOrgInternalCode(
						orgnizationService.getSimpleOrgById(orgId)
								.getOrgInternalCode(), pageNum, pageSize,
						sortField, sord, logOut);
	}

	@Override
	public LaborEmploymentUnit getLaborEmploymentUnitById(Long id) {
		if (null == id || id < 0L) {
			throw new BusinessValidationException("删除实有单位时id不合法");
		}
		return laborEmploymentUnitDao.getLaborEmploymentUnitById(id);
	}

	@Override
	public boolean hasDuplicateLaborEmploymentUnit(Long orgId,
			String companyName, Long exceptedId) {
		LaborEmploymentUnit exsited = laborEmploymentUnitDao
				.getLaborEmploymentUnitByCompanyNameAndOrganizationId(
						companyName, orgId);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	@Override
	public LaborEmploymentUnit updateBusinessData(
			LaborEmploymentUnit laborEmploymentUnit) {
		return laborEmploymentUnitDao.updateBusinessData(laborEmploymentUnit);
	}

	@Override
	public List<LaborEmploymentUnit> updateEmphasisByIdList(List<Long> idList,
			LaborEmploymentUnit domain) {
		validatorIdList(idList);
		List<LaborEmploymentUnit> laborEmploymentUnitList = new ArrayList<LaborEmploymentUnit>();
		for (Long id : idList) {
			LaborEmploymentUnit laborEmploymentUnit = laborEmploymentUnitDao
					.getLaborEmploymentUnitById(id);
			laborEmploymentUnit.setIsEmphasis(domain.getIsEmphasis());
			laborEmploymentUnit.setLogOutReason(domain.getLogOutReason());
			laborEmploymentUnit.setLogOutTime(domain.getLogOutTime());
			laborEmploymentUnit = updateLaborEmploymentUnit(laborEmploymentUnit);
			laborEmploymentUnitList.add(laborEmploymentUnit);
		}
		return laborEmploymentUnitList;
	}

	public List<Long> validatorIdList(List<Long> idList) {
		if (null == idList) {
			throw new BusinessValidationException("实有单位idList不能为空");
		}
		for (Long id : idList) {
			if (null == id || id < 0L) {
				throw new BusinessValidationException("实有单位id不合法");
			}
		}
		return idList;
	}

	@Override
	public LaborEmploymentUnit updateLaborEmploymentUnit(
			LaborEmploymentUnit laborEmploymentUnit) {
		evaluationForIsEmphasisAndIsKey(laborEmploymentUnit, "update");
		ValidateResult idleValidator = domainValidator
				.validate(laborEmploymentUnit);
		if (idleValidator.hasError()) {
			throw new BusinessValidationException(
					idleValidator.getErrorMessages());
		} else if (hasDuplicateLaborEmploymentUnit(laborEmploymentUnit
				.getOrganization().getId(),
				laborEmploymentUnit.getCompanyName(),
				laborEmploymentUnit.getId())) {
			throw new BusinessValidationException("该网格下已存在相同单位");
		}
		laborEmploymentUnit
				.setOrgInternalCode(orgnizationService.getSimpleOrgById(
						laborEmploymentUnit.getOrganization().getId())
						.getOrgInternalCode());
		autoFillChinesePinyin(laborEmploymentUnit);
		return laborEmploymentUnitDao
				.updateLaborEmploymentUnit(laborEmploymentUnit);
	}

	private void autoFillChinesePinyin(LaborEmploymentUnit domain) {
		Map<String, String> pinyin = Chinese2pinyin.changeChinese2Pinyin(domain
				.getCompanyName());
		domain.setFullPinyin((String) pinyin.get("fullPinyin"));
		domain.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	/** 为实有单位的注销状态和是否重点单位赋值 */
	private LaborEmploymentUnit evaluationForIsEmphasisAndIsKey(
			LaborEmploymentUnit domain, String mode) {
		if (mode.equals("add")) {
			domain.setIsEmphasis(true);
		} else if (mode.equals("update")) {
			LaborEmploymentUnit laborEmploymentUnit = laborEmploymentUnitDao
					.getLaborEmploymentUnitById(domain.getId());
			if (domain.getIsEmphasis() == null) {
				domain.setIsEmphasis(laborEmploymentUnit.getIsEmphasis());
			}
		}
		if (domain.getIsKey() == null) {
			domain.setIsKey(false);
		}
		return domain;
	}
}

package com.tianque.issue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issue.dao.PeaceVillageDao;
import com.tianque.issue.domain.PeaceVillage;
import com.tianque.issue.domain.vo.SearchPeaceVillageVo;
import com.tianque.issue.service.PeaceVillageService;

/**
 * 村社区平安三联创:业务逻辑层
 * 
 * @author
 * @date 2014-01-04 10:45:52
 */
@Repository("peaceVillageService")
public class PeaceVillageServiceImpl extends
		BaseServiceImpl<PeaceVillage, SearchPeaceVillageVo> implements
		PeaceVillageService {

	@Autowired
	@Qualifier("PeaceVillageValidator")
	private DomainValidator<PeaceVillage> domainValidator;

	@Resource(name = "peaceVillageDao")
	public void setBaseDao(PeaceVillageDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public PeaceVillage add(PeaceVillage peaceVillage) {
		peaceVillageValidator(peaceVillage);
		try {
			peaceVillage = getBaseDao().add(peaceVillage);
			return peaceVillage;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeaceVillageServiceImpl的add方法出现异常，原因：",
					"新增村社区平安三联创信息出现错误", e);
		}
	}

	@Override
	public PeaceVillage update(PeaceVillage peaceVillage) {
		peaceVillageValidator(peaceVillage);
		try {
			peaceVillage = getBaseDao().update(peaceVillage);
			return peaceVillage;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeaceVillageServiceImpl的update方法出现异常，原因：",
					"更新村社区平安三联创信息出现错误", e);
		}
	}

	private void peaceVillageValidator(PeaceVillage peaceVillage) {
		ValidateResult baseDataValidator = domainValidator
				.validate(peaceVillage);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public List<PeaceVillage> getPeaceVillageListBySearchVo(
			SearchPeaceVillageVo searchPeaceVillageVo) {
		if (searchPeaceVillageVo == null
				|| searchPeaceVillageVo.getOrganization() == null
				|| searchPeaceVillageVo.getOrganization().getId() == null
				|| searchPeaceVillageVo.getYear() == null
				|| searchPeaceVillageVo.getMonth() == null) {
			throw new BusinessValidationException("查询参数无效!");
		}
		return ((PeaceVillageDao) getBaseDao())
				.getPeaceVillageListBySearchVo(searchPeaceVillageVo);
	}

	@Override
	public void savePeaceVillage(List<PeaceVillage> peaceVillageList) {
		for (PeaceVillage peaceVillage : peaceVillageList) {
			PeaceVillage oldPeaceVillage = getPeaceVillageByOrgAndYearMonth(peaceVillage);
			if (oldPeaceVillage != null) {
				peaceVillage.setId(oldPeaceVillage.getId());
				update(peaceVillage);
			} else {
				add(peaceVillage);
			}
		}

	}

	@Override
	public PeaceVillage getPeaceVillageByOrgAndYearMonth(
			PeaceVillage peaceVillage) {
		return ((PeaceVillageDao) getBaseDao())
				.getPeaceVillageByOrgAndYearMonth(peaceVillage);
	}

	/**************************** 迁移合并 *****************************/
	@Override
	public int updatePeaceVillage(PeaceVillage peaceVillage) {
		return ((PeaceVillageDao) getBaseDao())
				.updatePeaceVillage(peaceVillage);
	}
}

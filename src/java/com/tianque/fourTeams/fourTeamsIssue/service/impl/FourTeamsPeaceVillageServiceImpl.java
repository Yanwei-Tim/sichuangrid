package com.tianque.fourTeams.fourTeamsIssue.service.impl;

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
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsPeaceVillageDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsPeaceVillage;
import com.tianque.fourTeams.fourTeamsIssue.domain.vo.SearchFourTeamsPeaceVillageVo;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsPeaceVillageService;

/**
 * 村社区平安三联创:业务逻辑层
 * 
 * @author
 * @date 2014-01-04 10:45:52
 */
@Repository("fourTeamsPeaceVillageService")
public class FourTeamsPeaceVillageServiceImpl extends
		BaseServiceImpl<FourTeamsPeaceVillage, SearchFourTeamsPeaceVillageVo>
		implements FourTeamsPeaceVillageService {

	@Autowired
	@Qualifier("PeaceVillageValidator")
	private DomainValidator<FourTeamsPeaceVillage> domainValidator;

	@Resource(name = "fourTeamsPeaceVillageDao")
	public void setBaseDao(FourTeamsPeaceVillageDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public FourTeamsPeaceVillage add(FourTeamsPeaceVillage peaceVillage) {
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
	public FourTeamsPeaceVillage update(FourTeamsPeaceVillage peaceVillage) {
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

	private void peaceVillageValidator(FourTeamsPeaceVillage peaceVillage) {
		ValidateResult baseDataValidator = domainValidator
				.validate(peaceVillage);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public List<FourTeamsPeaceVillage> getPeaceVillageListBySearchVo(
			SearchFourTeamsPeaceVillageVo searchPeaceVillageVo) {
		if (searchPeaceVillageVo == null
				|| searchPeaceVillageVo.getOrganization() == null
				|| searchPeaceVillageVo.getOrganization().getId() == null
				|| searchPeaceVillageVo.getYear() == null
				|| searchPeaceVillageVo.getMonth() == null) {
			throw new BusinessValidationException("查询参数无效!");
		}
		return ((FourTeamsPeaceVillageDao) getBaseDao())
				.getPeaceVillageListBySearchVo(searchPeaceVillageVo);
	}

	@Override
	public void savePeaceVillage(List<FourTeamsPeaceVillage> peaceVillageList) {
		for (FourTeamsPeaceVillage peaceVillage : peaceVillageList) {
			FourTeamsPeaceVillage oldPeaceVillage = getPeaceVillageByOrgAndYearMonth(peaceVillage);
			if (oldPeaceVillage != null) {
				peaceVillage.setId(oldPeaceVillage.getId());
				update(peaceVillage);
			} else {
				add(peaceVillage);
			}
		}

	}

	@Override
	public FourTeamsPeaceVillage getPeaceVillageByOrgAndYearMonth(
			FourTeamsPeaceVillage peaceVillage) {
		return ((FourTeamsPeaceVillageDao) getBaseDao())
				.getPeaceVillageByOrgAndYearMonth(peaceVillage);
	}
}

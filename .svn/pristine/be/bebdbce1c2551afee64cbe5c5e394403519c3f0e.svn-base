package com.tianque.partyBuilding.baseInfos.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.baseInfos.dao.DistrictBasiccaseDao;
import com.tianque.partyBuilding.baseInfos.domain.DistrictBasiccase;
import com.tianque.partyBuilding.baseInfos.domain.vo.SearchDistrictBasiccaseVo;
import com.tianque.partyBuilding.baseInfos.service.DistrictBasiccaseService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 社区基本情况表:业务逻辑层
 * 
 * @author
 * @date 2014-01-14 15:24:54
 */
@Transactional
@Service("districtBasiccaseService")
public class DistrictBasiccaseServiceImpl extends
		BaseServiceImpl<DistrictBasiccase, SearchDistrictBasiccaseVo> implements
		DistrictBasiccaseService {

	@Autowired
	private DistrictBasiccaseDao districtBasiccaseDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	@Qualifier("districtBasiccaseValidator")
	private DomainValidator<DistrictBasiccase> domainValidator;

	@Resource(name = "districtBasiccaseDao")
	public void setBaseDao(DistrictBasiccaseDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public DistrictBasiccase add(DistrictBasiccase districtBasiccase) {
		districtBasiccaseValidator(districtBasiccase);
		autoFillOrganizationInternalCode(districtBasiccase);
		try {
			districtBasiccase = getBaseDao().add(districtBasiccase);
			return districtBasiccase;
		} catch (Exception e) {
			return dealException("DistrictBasiccaseServiceImpl", "add",
					"新增社区基本情况表信息出现错误", e);
		}
	}

	@Override
	public DistrictBasiccase update(DistrictBasiccase districtBasiccase) {
		districtBasiccaseValidator(districtBasiccase);
		try {
			districtBasiccase = getBaseDao().update(districtBasiccase);
			return districtBasiccase;
		} catch (Exception e) {
			return dealException("DistrictBasiccaseServiceImpl", "update",
					"更新社区基本情况表信息出现错误", e);
		}
	}

	private void districtBasiccaseValidator(DistrictBasiccase districtBasiccase) {
		ValidateResult baseDataValidator = domainValidator
				.validate(districtBasiccase);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public DistrictBasiccase findDistrictBasiccaseByIdAndOrgId(Long id,
			Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构不能为空");
		}
		return districtBasiccaseDao
				.findDistrictBasiccaseByIdAndOrgId(id, orgId);
	}

	@Override
	public DistrictBasiccase deletePhotosAndOrgById(String imgUrl, Long id,
			Long orgId) {
		if (imgUrl == null || id == null || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		DistrictBasiccase districtBasiccase = districtBasiccaseDao
				.findDistrictBasiccaseByIdAndOrgId(id, orgId);
		if (("/" + districtBasiccase.getImgUrl()).equals(imgUrl)) {
			districtBasiccase.setImgUrl(null);
			districtBasiccase = districtBasiccaseDao.update(districtBasiccase);
		}
		return districtBasiccase;
	}

	private void autoFillOrganizationInternalCode(
			DistrictBasiccase districtBasiccase) {
		if (districtBasiccase.getOrganization() == null) {
			throw new BusinessValidationException("找不到指定网格");
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(districtBasiccase.getOrganization()
							.getId());
			districtBasiccase.setOrgInternalCode(organization
					.getOrgInternalCode());
		}
	}

}

package com.tianque.partyBuilding.organizations.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.organizations.dao.PartyresourceDao;
import com.tianque.partyBuilding.organizations.domain.Partyresource;
import com.tianque.partyBuilding.organizations.domain.vo.SearchPartyresourceVo;
import com.tianque.partyBuilding.organizations.service.PartyresourceService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 区域内主要党组织资源:业务逻辑层
 * 
 * @author
 * @date 2014-01-15 09:22:20
 */
@Repository("partyresourceService")
public class PartyresourceServiceImpl extends
		BaseServiceImpl<Partyresource, SearchPartyresourceVo> implements
		PartyresourceService {

	@Autowired
	@Qualifier("partyresourceValidator")
	private DomainValidator<Partyresource> domainValidator;
	@Autowired
	private PartyresourceDao partyresourceDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Resource(name = "partyresourceDao")
	public void setBaseDao(PartyresourceDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public Partyresource add(Partyresource partyresource) {
		partyresourceValidator(partyresource);
		try {
			partyresource = getBaseDao().add(partyresource);
			return partyresource;
		} catch (Exception e) {
			return dealException("PartyresourceServiceImpl", "add",
					"新增区域内主要党组织资源信息出现错误", e);
		}
	}

	@Override
	public Partyresource update(Partyresource partyresource) {
		partyresourceValidator(partyresource);
		try {
			partyresource = getBaseDao().update(partyresource);
			return partyresource;
		} catch (Exception e) {

			return dealException("PartyresourceServiceImpl", "update",
					"更新区域内主要党组织资源信息出现错误", e);
		}
	}

	private void partyresourceValidator(Partyresource partyresource) {
		ValidateResult baseDataValidator = domainValidator
				.validate(partyresource);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public Integer getTownAndVillagePartyResourceNum(Long internalId,
			String orgCode) {
		if (null == internalId && !StringUtil.isStringAvaliable(orgCode)) {
			throw new BusinessValidationException("参数错误");
		}
		OrganizationVo orgVo = new OrganizationVo();
		orgVo.setOrgInternalCode(orgCode);
		orgVo.setOrgLevelId(internalId);
		List<Long> orgIdList = organizationDubboService.findOrgIdsBySearchVo(orgVo);
		return partyresourceDao.getTownAndVillagePartyResourceNum(orgIdList);
	}
}

package com.tianque.partyBuilding.baseInfos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.baseInfos.dao.PartyworkMembersDao;
import com.tianque.partyBuilding.baseInfos.domain.PartyworkMembers;
import com.tianque.partyBuilding.baseInfos.domain.vo.SearchPartyworkMembersVo;
import com.tianque.partyBuilding.baseInfos.service.PartyworkMembersService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 党工委成员表:业务逻辑层
 * 
 * @author
 * @date 2014-01-14 15:32:52
 */
@Transactional
@Service("partyworkMembersService")
public class PartyworkMembersServiceImpl extends
		BaseServiceImpl<PartyworkMembers, SearchPartyworkMembersVo> implements
		PartyworkMembersService {

	@Autowired
	private PartyworkMembersDao partyworkMembersDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	@Qualifier("partyworkMembersValidator")
	private DomainValidator<PartyworkMembers> domainValidator;

	@Resource(name = "partyworkMembersDao")
	public void setBaseDao(PartyworkMembersDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public PartyworkMembers add(PartyworkMembers partyworkMembers) {
		partyworkMembersValidator(partyworkMembers);
		autoFillOrganizationInternalCode(partyworkMembers);
		try {
			partyworkMembers = getBaseDao().add(partyworkMembers);
			setParam(partyworkMembers);
			return partyworkMembers;
		} catch (Exception e) {
			return dealException("PartyworkMembersServiceImpl", "add",
					"新增党工委成员表信息出现错误", e);
		}
	}

	@Override
	public PartyworkMembers update(PartyworkMembers partyworkMembers) {
		partyworkMembersValidator(partyworkMembers);
		try {
			partyworkMembers = getBaseDao().update(partyworkMembers);
			setParam(partyworkMembers);
			return partyworkMembers;
		} catch (Exception e) {
			return dealException("PartyworkMembersServiceImpl", "update",
					"更新党工委成员表信息出现错误", e);
		}
	}

	private void partyworkMembersValidator(PartyworkMembers partyworkMembers) {
		ValidateResult baseDataValidator = domainValidator
				.validate(partyworkMembers);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public List<PartyworkMembers> getPartyWorkMembersListByOrgId(
			PartyworkMembers partyworkMembers) {
		List<PartyworkMembers> partyworkMemberList = partyworkMembersDao
				.getPartyWorkMembersListByOrgId(partyworkMembers);
		if (partyworkMemberList.size() > 0) {
			for (PartyworkMembers members : partyworkMemberList) {
				setParam(members);
			}
		}
		return partyworkMemberList;
	}

	@Override
	public void sortLeaderById(List<PartyworkMembers> partyworkMembersList) {
		if (partyworkMembersList != null && partyworkMembersList.size() > 0) {
			for (PartyworkMembers members : partyworkMembersList) {
				partyworkMembersDao.update(members);
			}
		}

	}

	private void setParam(PartyworkMembers partyworkMembers) {
		if (partyworkMembers.getGender().getId() != null) {
			PropertyDict propertyDict = propertyDictService
					.getPropertyDictById(partyworkMembers.getGender().getId());
			partyworkMembers.setGenderName(propertyDict.getDisplayName());
		}
	}

	private void autoFillOrganizationInternalCode(
			PartyworkMembers partyworkMembers) {
		if (partyworkMembers.getOrganization() == null) {
			throw new BusinessValidationException("找不到指定网格");
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(partyworkMembers.getOrganization()
							.getId());
			partyworkMembers.setOrgCode(organization.getOrgInternalCode());
		}
	}

}

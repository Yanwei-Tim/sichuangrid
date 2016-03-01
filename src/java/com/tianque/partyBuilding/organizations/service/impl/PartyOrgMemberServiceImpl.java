package com.tianque.partyBuilding.organizations.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.organizations.dao.PartyOrgMemberDao;
import com.tianque.partyBuilding.organizations.domain.PartyOrgMember;
import com.tianque.partyBuilding.organizations.domain.vo.SearchPartyOrgMemberVo;
import com.tianque.partyBuilding.organizations.service.PartyOrgMemberService;

/**
 * 两新组织党组织表:业务逻辑层
 * 
 * @author
 * @date 2014-01-14 15:44:08
 */
@Repository("partyOrgMemberService")
public class PartyOrgMemberServiceImpl extends
		BaseServiceImpl<PartyOrgMember, SearchPartyOrgMemberVo> implements
		PartyOrgMemberService {

	@Autowired
	@Qualifier("partyOrgMemberValidator")
	private DomainValidator<PartyOrgMember> domainValidator;
	@Autowired
	private PartyOrgMemberDao partyOrgMemberDao;

	@Resource(name = "partyOrgMemberDao")
	public void setBaseDao(PartyOrgMemberDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public PartyOrgMember add(PartyOrgMember partyOrgMember) {
		partyOrgMemberValidator(partyOrgMember);
		try {
			partyOrgMember = getBaseDao().add(partyOrgMember);
			return partyOrgMember;
		} catch (Exception e) {
			return dealException("PartyOrgMemberServiceImpl", "add",
					"新增两新组织党组织表信息出现错误", e);
		}
	}

	@Override
	public PartyOrgMember update(PartyOrgMember partyOrgMember) {
		partyOrgMemberValidator(partyOrgMember);
		try {
			partyOrgMember = getBaseDao().update(partyOrgMember);
			return partyOrgMember;
		} catch (Exception e) {
			return dealException("PartyOrgMemberServiceImpl", "update",
					"更新两新组织党组织表信息出现错误", e);
		}
	}

	@Override
	public List<PartyOrgMember> editPartyOrgMembers(Long partyOrgId,
			List<PartyOrgMember> members) {
		deleteByPartyOrgId(partyOrgId);
		if (members != null) {
			for (PartyOrgMember member : members) {
				if (member != null) {
					member.setPartyOrgId(partyOrgId);
					getBaseDao().add(member);
				}
			}
		}
		return null;
	}

	private void partyOrgMemberValidator(PartyOrgMember partyOrgMember) {
		ValidateResult baseDataValidator = domainValidator
				.validate(partyOrgMember);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public boolean deleteByPartyOrgId(Long partyOrgId) {
		return partyOrgMemberDao.deleteByPartyOrgId(partyOrgId);
	}

	@Override
	public List<PartyOrgMember> getByPartyOrgId(Long partyOrgId) {
		return partyOrgMemberDao.getByPartyOrgId(partyOrgId);
	}

}

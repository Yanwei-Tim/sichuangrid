package com.tianque.partyBuilding.members.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.StringUtil;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.members.dao.MemberAssociatePartyOrgDao;
import com.tianque.partyBuilding.members.domain.Member;
import com.tianque.partyBuilding.members.domain.MemberAssociatePartyOrg;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Service("memberAssociatePartyOrgService")
public class MemberAssociatePartyOrgServiceImpl implements
		MemberAssociatePartyOrgService {
	@Autowired
	private MemberAssociatePartyOrgDao associatePartyOrgDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public MemberAssociatePartyOrg addMemberAssociatePartyOrg(
			MemberAssociatePartyOrg associatePartyOrg) {
		return associatePartyOrgDao
				.addMemberAssociatePartyOrg(associatePartyOrg);
	}

	@Override
	public MemberAssociatePartyOrg updateMemberAssociatePartyOrg(
			MemberAssociatePartyOrg associatePartyOrg) {
		return associatePartyOrgDao
				.updateMemberAssociatePartyOrg(associatePartyOrg);
	}

	@Override
	public boolean deleteMemberAssociatePartyOrgByMemberIds(
			Integer partyOrgType, List<Long> ids) {
		return associatePartyOrgDao.deleteMemberAssociatePartyOrgByMemberIds(
				partyOrgType, ids);
	}

	@Override
	public MemberAssociatePartyOrg getMemberAssociatePartyOrg(
			MemberAssociatePartyOrg associatePartyOrg) {
		return associatePartyOrgDao
				.getMemberAssociatePartyOrg(associatePartyOrg);
	}

	@Override
	public Integer getMemberNumByPartyOrgTypeAndOrgIdOrOrgCode(
			int partyOrgType, Long internalId, String orgCode) {
		if (null == internalId && !StringUtil.isStringAvaliable(orgCode)) {
			throw new BusinessValidationException("参数错误");
		}
		OrganizationVo orgVo = new OrganizationVo();
		orgVo.setOrgInternalCode(orgCode);
		orgVo.setOrgLevelId(internalId);
		List<Long> orgIdList = organizationDubboService.findOrgIdsBySearchVo(orgVo);
		return associatePartyOrgDao
				.getMemberNumByPartyOrgTypeAndOrgIdOrOrgCode(partyOrgType,orgIdList);
	}

	@Override
	public Integer countByPartyOrgTypeAndPartyOrgAndOrgId(Integer partyOrgType,
			Long orgId, String partyOrg) {
		if (null == orgId && !StringUtil.isStringAvaliable(partyOrg)) {
			throw new BusinessValidationException("参数错误");
		}
		return associatePartyOrgDao.countByPartyOrgTypeAndPartyOrgAndOrgId(
				partyOrgType, orgId, partyOrg);
	}

	@Override
	public void updatePartyorgByPartyOrgTypeAndPartyorg(Integer partyOrgType,
			String oldPartyOrg, String newPartyOrg) {
		if (null == partyOrgType && !StringUtil.isStringAvaliable(oldPartyOrg)) {
			throw new BusinessValidationException("参数错误");
		}
		associatePartyOrgDao.updatePartyOrgByPartyOrgTypeAndPartyOrg(
				partyOrgType, oldPartyOrg, newPartyOrg);
	}

	@Override
	public boolean isExistMemberInPartyOrg(Member member,
			boolean isInteractiveParty) {
		return associatePartyOrgDao.isExistMemberInPartyOrg(member,
				isInteractiveParty);
	}
}

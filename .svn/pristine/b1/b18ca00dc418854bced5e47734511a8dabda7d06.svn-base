package com.tianque.partyBuilding.volunteerTeam.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.members.domain.Member;
import com.tianque.partyBuilding.volunteerTeam.dao.VolunteerMemberDao;
import com.tianque.partyBuilding.volunteerTeam.domain.VolunteerMember;
import com.tianque.partyBuilding.volunteerTeam.domain.vo.SearchVolunteerMemberVo;
import com.tianque.partyBuilding.volunteerTeam.service.VolunteerMemberService;

/**
 * 党员志愿者队伍成员表:业务逻辑层
 * 
 * @author
 * @date 2014-02-12 10:48:16
 */
@Repository("volunteerMemberService")
public class VolunteerMemberServiceImpl extends
		BaseServiceImpl<VolunteerMember, SearchVolunteerMemberVo> implements
		VolunteerMemberService {

	@Autowired
	@Qualifier("volunteerMemberValidator")
	private DomainValidator<VolunteerMember> domainValidator;

	@Autowired
	private VolunteerMemberDao volunteerMemberDao;

	@Resource(name = "volunteerMemberDao")
	public void setBaseDao(VolunteerMemberDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public VolunteerMember add(VolunteerMember volunteerMember) {
		volunteerMemberValidator(volunteerMember);
		try {
			volunteerMember = volunteerMemberDao.add(volunteerMember);
			return volunteerMember;
		} catch (Exception e) {
			return dealException(this, "add", "新增党员志愿者队伍成员表信息出现错误", e);
		}
	}

	@Override
	public VolunteerMember update(VolunteerMember volunteerMember) {
		volunteerMemberValidator(volunteerMember);
		try {
			volunteerMember = getBaseDao().update(volunteerMember);
			return volunteerMember;
		} catch (Exception e) {
			return dealException(this, "update", "更新党员志愿者队伍成员表信息出现错误", e);
		}
	}

	@Override
	public PageInfo findPagerBySearchVo(SearchVolunteerMemberVo searchVo,
			Integer page, Integer rows, String sidx, String sord) {
		return volunteerMemberDao.findPagerBySearchVo(searchVo, page, rows,
				sidx, sord);
	}

	@Override
	public Boolean deleteByTeamIdAndMemberId(Long teamId, Long memberId,
			Long orgId) {
		return volunteerMemberDao.deleteByTeamIdAndMemberId(teamId, memberId,
				orgId);
	}

	private void volunteerMemberValidator(VolunteerMember volunteerMember) {
		ValidateResult baseDataValidator = domainValidator
				.validate(volunteerMember);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public Member getMemberById(Long memberId) {
		return volunteerMemberDao.getMemberById(memberId);
	}

	@Override
	public PageInfo findMembersByOrgCodeAndTeamId(
			SearchVolunteerMemberVo searchVo, Integer page, Integer rows,
			String sidx, String sord) {
		return volunteerMemberDao.findMembersByOrgCodeAndTeamId(searchVo, page,
				rows, sidx, sord);
	}

}

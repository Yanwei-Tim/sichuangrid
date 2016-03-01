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
import com.tianque.partyBuilding.members.service.MemberAssociatePartyOrgService;
import com.tianque.partyBuilding.volunteerTeam.dao.VolunteerTeamDao;
import com.tianque.partyBuilding.volunteerTeam.domain.VolunteerTeam;
import com.tianque.partyBuilding.volunteerTeam.domain.vo.SearchVolunteerTeamVo;
import com.tianque.partyBuilding.volunteerTeam.service.VolunteerTeamService;

/**
 * 党员志愿者队伍表:业务逻辑层
 * 
 * @author
 * @date 2014-02-11 15:27:44
 */
@Repository("volunteerTeamService")
public class VolunteerTeamServiceImpl extends
		BaseServiceImpl<VolunteerTeam, SearchVolunteerTeamVo> implements
		VolunteerTeamService {

	@Autowired
	@Qualifier("volunteerTeamValidator")
	private DomainValidator<VolunteerTeam> domainValidator;
	@Autowired
	private VolunteerTeamDao volunteerTeamDao;
	@Autowired
	private MemberAssociatePartyOrgService memberAssociatePartyOrgService;

	@Resource(name = "volunteerTeamDao")
	public void setBaseDao(VolunteerTeamDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public VolunteerTeam add(VolunteerTeam volunteerTeam) {
		volunteerTeamValidator(volunteerTeam);
		try {
			volunteerTeam = getBaseDao().add(volunteerTeam);
			return volunteerTeam;
		} catch (Exception e) {
			return dealException(this, "add", "新增党员志愿者队伍表信息出现错误", e);
		}
	}

	@Override
	public VolunteerTeam update(VolunteerTeam volunteerTeam) {
		volunteerTeamValidator(volunteerTeam);
		try {
			volunteerTeam = getBaseDao().update(volunteerTeam);
			return volunteerTeam;
		} catch (Exception e) {
			return dealException(this, "update", "更新党员志愿者队伍表信息出现错误", e);
		}
	}

	@Override
	public PageInfo<VolunteerTeam> findPagerBySearchVo(
			SearchVolunteerTeamVo searchVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		PageInfo<VolunteerTeam> pageInfo = getBaseDao().findPagerBySearchVo(
				searchVo, pageNum, pageSize, sidx, sord);
		return pageInfo;
	}

	private void volunteerTeamValidator(VolunteerTeam volunteerTeam) {
		ValidateResult baseDataValidator = domainValidator
				.validate(volunteerTeam);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		if (hasDuplicate(volunteerTeam)) {
			throw new BusinessValidationException("此名称在系统中已经存在");
		}
	}

	@Override
	public Boolean hasDuplicate(VolunteerTeam domain) {
		try {
			VolunteerTeam volunteerTeam = volunteerTeamDao.getByOrgIdAndName(
					domain.getOrgId(), domain.getName());
			return (volunteerTeam == null) ? false
					: (domain.getId() == null) ? true : (domain.getId().equals(
							volunteerTeam.getId()) ? false : true);
		} catch (Exception e) {
			return dealException(this, "hasDuplicate", "唯一性校验出现错误", e);
		}
	}

}

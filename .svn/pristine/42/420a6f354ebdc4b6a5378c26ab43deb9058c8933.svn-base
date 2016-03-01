package com.tianque.party.service.impl;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.TeamInfoDao;
import com.tianque.domain.Organization;
import com.tianque.domain.TeamInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.party.service.PartyTeamService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("partyTeamService")
@Transactional
public class PartyTeamServiceImpl extends AbstractBaseService implements
		PartyTeamService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private TeamInfoDao teamInfoDao;

	@Override
	public TeamInfo addPartyTeam(TeamInfo team, String teamType) {
		team.getOwnerOrg().setOrgInternalCode(
				organizationDubboService
						.getSimpleOrgById(team.getOwnerOrg().getId())
						.getOrgInternalCode());
		Map<String, String> pinyin = Chinese2pinyin.changeChinese2Pinyin(team
				.getName());
		team.setSimplePinyin(pinyin.get("simplePinyin"));
		team.setFullPinyin(pinyin.get("fullPinyin"));
		return teamInfoDao.addTeamInfo(team, teamType);
	}

	@Override
	public PageInfo<TeamInfo> findTeamInfosByOwnerOrgIdAndTeamType(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String teamType) {
		if (orgId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return constructEmptyPageInfo();
			} else {
				return teamInfoDao.findTeamInfosByOwnerOrgIdAndTeamType(orgId,
						pageNum, pageSize, sidx, sord, teamType);
			}
		}
	}

	private PageInfo<TeamInfo> constructEmptyPageInfo() {
		PageInfo<TeamInfo> result = new PageInfo<TeamInfo>();
		result.setResult(new ArrayList<TeamInfo>());
		return result;
	}

	@Override
	public TeamInfo updateTeamInfo(TeamInfo team, String teamType) {
		Map<String, String> pinyin = Chinese2pinyin.changeChinese2Pinyin(team
				.getName());
		team.setSimplePinyin(pinyin.get("simplePinyin"));
		team.setFullPinyin(pinyin.get("fullPinyin"));
		return teamInfoDao.updateTeamInfo(team, teamType);
	}

	@Override
	public boolean deleteTeamInfoById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id不能为空");
		}
		TeamInfo team = teamInfoDao.getTeamById(id);
		if (team != null) {
			teamInfoDao.deleteTeamInfo(id);
		}
		return true;
	}

	@Override
	public TeamInfo getPartyTeamById(Long teamId) {
		return teamInfoDao.getTeamById(teamId);
	}

}

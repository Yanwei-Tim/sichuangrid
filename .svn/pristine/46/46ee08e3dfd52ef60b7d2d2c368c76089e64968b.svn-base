package com.tianque.tenHouseholdsJoint.service;

import java.util.List;

import org.oproject.framework.orm.PageResult;

import com.tianque.domain.DefaultSortAndPage;
import com.tianque.domain.Organization;
import com.tianque.tenHouseholdsJoint.domain.FamilyTeam;

public interface FamilyTeamService {

	public FamilyTeam addFamilyTeam(FamilyTeam familyTeam);

	public PageResult<FamilyTeam> queryFamilyTeamForPageResult(
			Organization organization, DefaultSortAndPage defaultSortAndPage);

	public FamilyTeam getFamilyTeamByName(String name,Long orgId);

	public FamilyTeam getFamilyTeamById(Long id);

	public int updateFamilyTeam(FamilyTeam familyTeam);

	public void delteFamilyTeamByIds(String ids);

	public PageResult<FamilyTeam> querySearchFamilyTeamForPageResult(
			FamilyTeam familyTeam, DefaultSortAndPage defaultSortAndPage);

	public List<FamilyTeam> queryFamilyTeamByOrgIdForList(Long orgId);

	public boolean checkFamilyTeam(FamilyTeam familyTeam);

	public List<FamilyTeam> findFamilyTeamsForList(FamilyTeam familyTeam);
}

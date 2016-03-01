package com.tianque.tenHouseholdsJoint.service;

import java.util.List;

import org.oproject.framework.orm.PageResult;

import com.tianque.domain.DefaultSortAndPage;
import com.tianque.tenHouseholdsJoint.domain.FamilyInfo;

public interface FamilyInfoService {
	public PageResult<FamilyInfo> queryFamilyInfoForPageResult(
			Long organizationId, DefaultSortAndPage defaultSortAndPage);

	public FamilyInfo addFamilyInfo(FamilyInfo familyInfo);

	public FamilyInfo updateFamilyInfo(FamilyInfo familyInfo);

	public void deleteFamilyInfo(String[] ids);

	public FamilyInfo getFamilyInfoById(Long id);

	public PageResult<FamilyInfo> queryFamilyByConditionForPageResult(
			FamilyInfo familyInfo, int pageNum, int pageSize);

	public void updateLogoutFamilyById(String[] ids, Integer logoutStatus);

	public Boolean checkHelpLine(FamilyInfo familyInfo);

	public FamilyInfo getFamilyInfoByHelpLine(String helpLine);

	public List<FamilyInfo> findTeamFamilyInfosByHelpLine(String helpLine);

	public List<FamilyInfo> findTeamFamilyInfosByTeamId(Long teamId);

	public List<FamilyInfo> findFamilyInfosForList(FamilyInfo familyInfo);

}

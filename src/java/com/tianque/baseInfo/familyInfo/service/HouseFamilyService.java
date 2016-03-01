package com.tianque.baseInfo.familyInfo.service;

import java.util.List;

import com.tianque.baseInfo.familyInfo.domain.HouseFamily;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchFamilyHouseVo;

public interface HouseFamilyService {
	public PageInfo<HouseFamily> findHouseFamilyByOrgId(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public PageInfo<HouseFamily> findHouseFamilyByOrgIdAndSearchCondition(
			SearchFamilyHouseVo searchFamilyHouseVo, Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public PageInfo<HouseFamily> findHouseFamilyByOrgIdAndMinuteSearchCondition(
			SearchFamilyHouseVo searchFamilyHouseVo, Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public HouseFamily findHouseFamilyById(Long familyId);

	public List<HouseFamily> findFamilyMemberInfoById(Long familyId);

	public void deleteHouseFamilyByIds(String ids);

	public void changeAccountNumber(String newAccNo, Long orgId, Long id,
			Long shardOrgId);

	public PageInfo findHouseFamilyMembersByOrgIdAndId(Long id, Long orgId,
			Integer page, Integer rows, String sidx, String sord);

	public void changeHouseHold(HouseholdStaff oldHouseHold,
			HouseholdStaff newHouseHold, Long id, Long orgId, Long shardOrgId);

	public boolean existAccountNumber(String newAccNo, Long orgId);

	public int haveRepatCardOrNo(String card);

}

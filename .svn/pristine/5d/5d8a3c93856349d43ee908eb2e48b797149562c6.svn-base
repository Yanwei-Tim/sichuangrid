package com.tianque.baseInfo.familyInfo.dao;

import java.util.List;

import com.tianque.baseInfo.familyInfo.domain.HouseFamily;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchFamilyHouseVo;

public interface HouseFamilyDao {
	public PageInfo<HouseFamily> findHouseFamilyByOrgId(
			Organization organization, Integer pageNum, Integer pageSize,
			String sidx, String sord, String shardCode, Long headOfHouseHoldId);

	public PageInfo<HouseFamily> findHouseFamilyByOrgIdAndSearchCondition(
			SearchFamilyHouseVo searchHouseFamilyVo, Organization organization,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode, Long headOfHouseHoldId);

	public PageInfo<HouseFamily> findHouseFamilyByOrgIdAndMinuteSearchCondition(
			SearchFamilyHouseVo searchHouseFamilyVo, Organization organization,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode, Long headOfHouseHoldId);

	public HouseFamily findHouseFamilyByIdAndOrgId(Long familyId);

	public List<HouseFamily> findFamilyMemberInforById(Long familyId,
			String ShardCode);

	public void deleteHouseFamilyById(long id);

	public void changeAccountNumber(String newAccNo, Long id);

	public PageInfo findHouseFamilyMembersByOrgIdAndId(Long id, Long orgId,
			Integer page, Integer rows, String sidx, String sord,
			String ShardCode);

	public void changeHouseHold(HouseholdStaff newHouseholdStaff, Long id);

	public void cleanFamilyHead(String orgInternalCode, String idCardNo);

	public boolean existAccountNumber(String newAccNo, Long orgId);

	public int haveRepatCardOrNo(String card);

	public List<Long> getHouseMarchType(Long familyid);

}

package com.tianque.baseInfo.familyInfo.dao;

import java.util.List;

import com.tianque.baseInfo.familyInfo.domain.GroupFamily;
import com.tianque.baseInfo.familyInfo.domain.GroupFamilyHasPopulation;
import com.tianque.baseInfo.familyInfo.domain.SavePeople;
import com.tianque.baseInfo.familyInfo.domain.SearchGroupFamilyVo;
import com.tianque.core.vo.PageInfo;

public interface GroupFamilyDao {

	long addGroupFamily(GroupFamily groupFamily);

	void addGroupFamilyHasPopulation(
			GroupFamilyHasPopulation groupFamilyHasPopulation);

	PageInfo<GroupFamily> pageGroupFamiliesByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long dictId, String shardCode);

	PageInfo<GroupFamily> pageGroupFamiliesBySearchGroupFamilyVo(
			String shardCode, SearchGroupFamilyVo searchGroupFamilyVo,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	PageInfo<GroupFamily> pageGroupFamiliesByFullSearchGroupFamilyVo(
			SearchGroupFamilyVo searchGroupFamilyVo, Integer pageNum,
			Integer pageSize, String sidx, String sord, String shardCode);

	GroupFamily updateGroupFamily(GroupFamily groupFamily);

	GroupFamily getGroupFamilyById(final Long id);

	GroupFamily findGroupFamilyByFamilyAccountAndOrgInternalCode(
			String familyAccount, String orgInternalCode);

	GroupFamilyHasPopulation getGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
			Long familyId, Long populationId, String populationType);

	int deleteGroupFamilyById(final Long id);

	int deleteGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
			final Long familyId, final Long populationId,
			final String populationType);

	PageInfo<GroupFamilyHasPopulation> pageFamilyMembersByFamilyIdAndOrgInternalCode(
			Long familyId, String orgInternalCode, Integer pageNum,
			Integer pageSize, String sidx, String sord, String shardCode);

	List<GroupFamilyHasPopulation> getFamilyMembersByFamilyId(
			final Long familyId);

	public List<GroupFamilyHasPopulation> findFamilyMembersByFamilyIdAndOrgCode(
			Long familyId, String orgCode, String shardCode);

	public int deleteGroupFamilyHasPopulationByFamilyId(Long familyId);

	public void updateGroupFamilyAccount(Long id, String newFamilyAccount);

	public GroupFamilyHasPopulation getGroupFamilyHasPopulationByPopulationIdAndPopulationType(
			Long populationId, String populationType);

	public void updateGroupFamilyMasterToNull(Long id);

	public void updateGroupFamilyHasPopulationRelation(Long populationId,
			String populationType, Long familyRelationId);

	public void updateGroupFamilyHasPopulationByFamilyIdAndFamilyrelation(
			Long familyId, Long familyRelationId, Long previousFamilyrelation);

	public GroupFamilyHasPopulation getFamilyMembersByFamilyIdAndOrgCodeAndFamilyrelation(
			Long familyId, Long familyrelation, String orgCode, String shardCode);

	public SavePeople getPeopleByIdcardNoAndOrgCode(String idCardNo,
			String orgCode, String shardCode);

	public PageInfo<GroupFamilyHasPopulation> pageFamilyMembersByFamilyIdNoMaster(
			Long familyId, Long familyRelationId, String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode);

	public void updateGroupFamilyMemberCountByGroupFamilyId(Long groupFamilyId,
			int membersCount);

	public GroupFamily getGroupFamilyByOrgCodeAndFamilyAccount(
			String orgInternalCode, String familyAccount);

	public int countFamilyMembersByFamilyId(Long familyId);

	/**
	 * 修改户籍人口家庭信息家长身份证号
	 * 
	 * @param groupFamily
	 */
	public void updateGroupFamilyIdcardById(GroupFamily groupFamily);
}

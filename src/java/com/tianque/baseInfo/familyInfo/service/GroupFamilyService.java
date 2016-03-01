package com.tianque.baseInfo.familyInfo.service;

import java.util.List;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.familyInfo.domain.GroupFamily;
import com.tianque.baseInfo.familyInfo.domain.GroupFamilyHasPopulation;
import com.tianque.baseInfo.familyInfo.domain.SavePeople;
import com.tianque.baseInfo.familyInfo.domain.SearchGroupFamilyVo;
import com.tianque.core.vo.PageInfo;

public interface GroupFamilyService {

	void practiseGroupFamilyForSynchro(GroupFamily groupFamily,
			Long populationId, String popluationType);

	PageInfo<GroupFamily> pageGroupFamiliesByOrgId(Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	PageInfo<GroupFamily> pageGroupFamiliesBySearchGroupFamilyVoAndOrgId(
			SearchGroupFamilyVo searchGroupFamilyVo, Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	PageInfo<GroupFamily> pageGroupFamiliesByFullSearchGroupFamilyVoAndOrgId(
			SearchGroupFamilyVo searchGroupFamilyVo, Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	GroupFamily getGroupFamilyByPopulation(Countrymen population);

	GroupFamily findGroupFamilyByFamilyAccountAndOrgInternalCode(
			String familyAccount, String orgInternalCode);

	GroupFamily findGroupFamilyByOrgIdAndFamilyAccount(Long orgId,
			String familyAccount);

	public GroupFamily getGroupFamilyById(Long familyId);

	PageInfo<GroupFamilyHasPopulation> pageFamilyMembersByFamilyId(
			Long familyId, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	Boolean whetherFamilyMaster(Long pid);

	public int getFamilyMembersByFamilyId(String ids);

	public void deleteGroupFamily(String ids);

	/**
	 * 将家长的信息置空
	 * 
	 * @param id
	 */
	public void updateGroupFamilyMasterToNull(Long id);

	/**
	 * 查家庭成员 （ 关联人口 人口且存在）
	 * 
	 * @return
	 */
	public List<GroupFamilyHasPopulation> findFamilyMembersByFamilyId(
			Long familyId);

	public int deleteGroupFamilyHasPopulationByFamilyId(Long familyId);

	public void resetFamilyAccount(Long id, String previousFamilyAccount,
			String newFamilyAccount);

	public void updateGroupFamilyAccount(Long id, String newFamilyAccount);

	public GroupFamilyHasPopulation getGroupFamilyHasPopulationByPopulationIdAndPopulationType(
			Long populationId, String populationType);

	public void deleteGroupFamilyByPopulationIdAndPopulationTypeAndIdCardNo(
			Long populationId, String populationType, String idCardNo);

	public void updateGroupFamily(GroupFamily groupFamily);

	public void updateGroupFamilyByObj(GroupFamily groupFamily,
			Long populationId, String populationType, boolean isDeath);

	/**
	 * 查家庭成员 (不含家长)
	 * 
	 * @param familyId
	 * @return
	 */
	public List<GroupFamilyHasPopulation> findFamilyMembersByFamilyIdNoMaster(
			Long familyId);

	/**
	 * 查家庭成员 (不含家长,每条信息含有关系)
	 * 
	 * @param familyId
	 * @return
	 */
	public PageInfo<GroupFamilyHasPopulation> findFamilyMembersByFamilyIdNoMasterAndIncludeRelation(
			Long familyId, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	/**
	 * 更换家长
	 * 
	 * @param groupFamilyId
	 * @param familyRelationId
	 * @param newFamilyMaster
	 */
	public void changeFamilyMaster(Long groupFamilyId, Long familyRelationId,
			String newFamilyMaster);

	public void updateGroupFamilyHasPopulationRelation(Long populationId,
			String populationType, Long familyRelationId);

	public void updateGroupFamilyHasPopulationByFamilyIdAndFamilyrelation(
			Long familyId, Long familyRelationId, Long previousFamilyrelation);

	public GroupFamilyHasPopulation addFamilyMember(Long groupFamilyId,
			Long familyRelationId, SavePeople savePeople,
			boolean whetherExistOtherFamily);

	/**
	 * 查找是否存在此人
	 * 
	 * @param idCardNo
	 * @param orgCode
	 * @return
	 */
	public SavePeople getPeopleByIdcardNoAndOrgCode(String idCardNo,
			String orgCode, Long orgId);

	public void deleteGroupFamilyMember(Long groupFamilyId, Long populationId,
			String populationType);

	public SavePeople judgeExistAtOrgCodeOfFamily(Long groupFamilyId,
			String idCardNo, Long familyRelationId);

	public void addGroupFamilyHasPopulation(
			GroupFamilyHasPopulation groupFamilyHasPopulation);

	public void deleteGroupFamilyHasPopulationByFamilyIdAndPopulationIdAndPopulationType(
			Long familyId, Long populationId, String populationType);

	public void updateGroupFamilyToNoMasterforLogOut(Long populationId,
			String populationType);

	public GroupFamily getGroupFamilyByOrgCodeAndFamilyAccount(
			String orgInternalCode, String familyAccount);

	/**
	 * 
	 * 修改户籍人口家庭信息家长身份证号
	 * 
	 * @param fillGroupFamilyInfo
	 */
	public void updateGroupFamilyIdcardById(GroupFamily groupFamily);
}

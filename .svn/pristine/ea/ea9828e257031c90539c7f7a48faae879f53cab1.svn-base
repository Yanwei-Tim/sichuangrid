package com.tianque.baseInfo.unemployedPeople.dao;

import java.util.List;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.unemployedPeople.domain.UnemployedPeople;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchUnemployedPeopleVo;

public interface UnemployedPeopleDao extends
		BaseInfoPopulationBaseDao<UnemployedPeople, SearchUnemployedPeopleVo> {

	/**
	 * 分页获取失业人员信息
	 * 
	 * @param orgInternalCode
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param isEmphasis
	 * @return
	 */
	public PageInfo<UnemployedPeople> findUnemployedPeopleForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis);

	/**
	 * 根据身份证号获取失业人员信息
	 * 
	 * @param idCardNo15
	 * @param idCardNo18
	 * @param organizationId
	 * @return
	 */
	public UnemployedPeople getUnemployedPeopleByIdCardNoAndOrganizationId(
			String idCardNo15, String idCardNo18, Long organizationId);

	/**
	 * 分页查找失业人员信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param searchUnemployedPeopleVo
	 * @return
	 */
	public PageInfo<UnemployedPeople> searchUnemployedPeoples(Integer pageNum,
			Integer pageSize, SearchUnemployedPeopleVo searchUnemployedPeopleVo);

	/**
	 * 全部查找失业人员信息
	 * 
	 * @param searchUnemployedPeopleVo
	 * @return
	 */
	public List<UnemployedPeople> searchAllUnemployedPeoples(
			SearchUnemployedPeopleVo searchUnemployedPeopleVo);

	public void addEmploymentIntention(Long unemployedPeopleId,
			Long propertyDictId);

	public void addTrainingIntention(Long unemployedPeopleId,
			Long propertyDictId);

	public void deleteEmploymentIntentionByUnemployedPeopleId(
			Long unemployedPeopleId);

	public void deleteTrainingIntentionByUnemployedPeopleId(
			Long unemployedPeopleId);

	public UnemployedPeople getFullUnemployedPeopleById(Long id);

	public void updateDeathAndLogoutStateById(Long long1, Boolean death,
			Long logoutState);

	public List<Long> getEmploymentIntentionIdsByEmploymentId(Long employmentId);

	public List<Long> getTrainingIntentionIdsByEmploymentId(Long employmentId);

	public Integer getCount(SearchUnemployedPeopleVo searchUnemployedPeopleVo);
	
	public void updateTableUpdateDateById(Long id);
}

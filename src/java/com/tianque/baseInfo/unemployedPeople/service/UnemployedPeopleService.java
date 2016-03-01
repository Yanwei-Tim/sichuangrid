package com.tianque.baseInfo.unemployedPeople.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.unemployedPeople.domain.UnemployedPeople;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchUnemployedPeopleVo;

public interface UnemployedPeopleService extends
		BaseInfoPopulationTemplateService {
	public UnemployedPeople addUnemployedPeople(
			UnemployedPeople unemployedPeople);

	public UnemployedPeople getUnemployedPeopleById(Long id);

	public UnemployedPeople getFullUnemployedPeopleById(Long id);

	public PageInfo<UnemployedPeople> findUnemployedPeopleForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, Long isEmphasis);

	public UnemployedPeople updateUnemployedPeople(
			UnemployedPeople unemployedPeople);

	public boolean deleteUnemployedPeopleById(Long id);

	public void deleteUnemployedPeoplesByIdList(List<Long> idList);

	public UnemployedPeople getUnemployedPeopleByIdCardNoAndOrganizationId(
			String idCardNo, Long organizationId);

	boolean hasDuplicateUnemployedPeople(Long orgId, String idCardNo,
			Long exceptedId);

	/**
	 * 导入时的更新
	 * 
	 * @param idCardNo
	 * @param orgId
	 * @param domain
	 * @return
	 */

	public UnemployedPeople updateUnemployedPeopleByIdCardNo(String idCardNo,
			Long orgId, UnemployedPeople domain);

	public List<UnemployedPeople> updateDeathByIds(List<Long> idList,
			Boolean death);

	public PageInfo<UnemployedPeople> searchUnemployedPeoples(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchUnemployedPeopleVo searchUnemployedPeopleVo,
			List<String> employmentIntentionIds,
			List<String> trainingIntentionIds);

	public List<UnemployedPeople> searchAllUnemployedPeoples(String sortField,
			String order, SearchUnemployedPeopleVo searchUnemployedPeopleVo,
			List<String> employmentIntentionIds,
			List<String> trainingIntentionIds);

	public UnemployedPeople updateUnemployedPeopleBaseInfo(
			UnemployedPeople domain);

	public UnemployedPeople updateUnemployedPeopleBusiness(
			UnemployedPeople domain, List<Long> trainingIntentionIds,
			List<Long> employmentIntentionIds);

	public void addEmploymentIntention(Long unemployedPeopleId,
			Long employmentIntentionId);

	public void addEmploymentIntention(Long unemployedPeopleId,
			List<Long> employmentIntentionIds);

	public void addTrainingIntention(Long unemployedPeopleId,
			Long trainingIntentionId);

	public void addTrainingIntention(Long unemployedPeopleId,
			List<Long> trainingIntentionIds);

	public void regrantEmploymentIntentionIds(Long unemployedPeopleId,
			List<Long> employmentIntentionIds);

	public void regrantTrainingIntentionIds(Long unemployedPeopleId,
			List<Long> trainingIntentionIds);

	public String[][] getExportPopertyArray();

	public UnemployedPeople getUnemployedPeopleByIdCardNo(String idCardNo,
			Long orgId);

	UnemployedPeople hasDuplicateUnemployedPeople(Long orgId, String idCardNo);

	void deleteUnemployedPeopleByIds(Long[] ids);

	public UnemployedPeople addUnemployedPeopleBaseInfo(
			UnemployedPeople population);

	public Integer getCount(SearchUnemployedPeopleVo searchUnemployedPeopleVo);
}

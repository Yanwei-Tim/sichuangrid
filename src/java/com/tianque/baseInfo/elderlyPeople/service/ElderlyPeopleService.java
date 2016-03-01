package com.tianque.baseInfo.elderlyPeople.service;

import java.util.List;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.core.vo.PageInfo;

public interface ElderlyPeopleService extends BaseInfoPopulationTemplateService {

	/**
	 * 添加一条老年人信息
	 * 
	 * @param elderlyPeople
	 * @return elderlyPeople
	 **/
	public ElderlyPeople addElderlyPeople(ElderlyPeople elderlyPeople);

	/**
	 * 根据id获取老年人对象
	 * 
	 * @param id
	 * @return
	 */
	public ElderlyPeople getElderlyPeopleById(Long id);

	/**
	 * 根据网格、是否注销和页数信息查找老年人信息列表
	 * 
	 * @param orgId
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param isEmphasis
	 * @return
	 */
	public PageInfo<ElderlyPeople> findElderlyPeopleForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, Long isEmphasis);

	public ElderlyPeople updateElderlyPeople(ElderlyPeople elderlyPeople);

	public boolean deleteElderlyPeopleById(Long id);

	public void deleteElderlyPeoplesByIdList(List<Long> idList);

	/**
	 * 判断是否有重复的数据使用
	 * 
	 * @param idCardNo
	 * @param organizationId
	 * @return
	 */
	public ElderlyPeople getElderlyPeopleByIdCardNoAndOrganizationId(
			String idCardNo, Long organizationId);

	public Long getElderlyPeopleByBaseinfIdAndOrgId(Long baseinfId, Long OrgId);

	boolean hasDuplicateElderlyPeople(Long orgId, String idCardNo,
			Long exceptedId);

	public ElderlyPeople updateElderlyPeopleByIdCardNo(String idCardNo,
			Long orgId, ElderlyPeople domain);

	public void updateDeathOfElderlyPeoplesByIdList(List<Long> populationIds,
			Boolean death);

	/**
	 * 修改老年人基本信息
	 * 
	 * @param domain
	 * @return
	 */
	public ElderlyPeople updateElderlyPeopleBaseInfo(ElderlyPeople domain);

	/**
	 * 修改老年人业务信息
	 * 
	 * @param domain
	 * @return
	 */
	public ElderlyPeople updateElderlyPeopleBusiness(ElderlyPeople domain);

	public ElderlyPeople hasDuplicateElderlyPeople(Long orgId, String idCardNo);

	public void deleteElderlyPeopleByIds(Long[] ids);

	public ElderlyPeople addElderlyPeopleBaseInfo(ElderlyPeople population);

	public ElderlyPeople addElderlyPeopleForJob(Countrymen countrymen,
			Long sourcesState);
}

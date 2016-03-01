package com.tianque.baseInfo.unsettledPopulation.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.BaseUnsettledPopulationSearchCondition;

public interface UnsettledPopulationService extends
		BaseInfoPopulationTemplateService {

	public UnsettledPopulation addUnsettledPopulation(
			UnsettledPopulation floatingPopulation);

	public PageInfo<UnsettledPopulation> findUnsettledPopulationsForPageByOrgId(
			Long logOut, Long isDeath, Long organizationId, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public UnsettledPopulation updateUnsettledPopulation(
			UnsettledPopulation unsettledPopulation);

	public UnsettledPopulation getUnsettledPopulationById(Long id);

	public void deleteUnsettledPopulationById(Long id);

	public void deleteUnsettledPopulationByIds(Long[] ids);

	public List<Long> deleteUnsettledPopulationsByIdList(List<Long> idList);

	public boolean hasDuplicateUnsettledPopulation(Long orgId, String idCardNo,
			Long exceptedId);

	public UnsettledPopulation updateUnsettledPopulationById(
			UnsettledPopulation unsettledPopulation);

	public UnsettledPopulation getUnsettledPopulationByIdCardNo(
			String idCardNo, Long orgId);

	public PageInfo<UnsettledPopulation> findUnsettledPopulationForPageByOrgId(
			BaseUnsettledPopulationSearchCondition unsettledPopulationCondition,
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	public List<UnsettledPopulation> findUnsettledPopulationList(
			BaseUnsettledPopulationSearchCondition unsettledPopulationCondition,
			Long orgId, String sidx, String sord);

	public List<UnsettledPopulation> updateLogOutOfUnsettledPopulationByIdList(
			List<Long> idList, Long logOut);

	public List<UnsettledPopulation> updateDeathOfUnsettledPopulationByIdList(
			List<Long> idList, Boolean death);

	// 数据管理中新增的方法
	public UnsettledPopulation hasDuplicateUnsettledPopulation(Long orgId,
			String idCardNo);

	/**
	 * 根据idCardNo和orgId查找未落户人口
	 * 
	 * @param idCardNo
	 * @param orgId
	 * @return
	 */
	public UnsettledPopulation getUnsettledPopulationByIdCardNoAndOrgId(
			String idCardNo, Long orgId);

	public Integer getCount(BaseUnsettledPopulationSearchCondition condition);
}
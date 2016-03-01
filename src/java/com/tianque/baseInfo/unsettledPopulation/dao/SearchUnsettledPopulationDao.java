package com.tianque.baseInfo.unsettledPopulation.dao;

import java.util.List;

import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.BaseUnsettledPopulationSearchCondition;

public interface SearchUnsettledPopulationDao {

	public PageInfo<UnsettledPopulation> searchUnsettledPopulation(
			BaseUnsettledPopulationSearchCondition condition, int pageNum,
			int pageSize, String sortField, String order);

	public PageInfo<UnsettledPopulation> fastSearchemptyUnsettledPopulationPage(
			BaseUnsettledPopulationSearchCondition condition, int pageNum,
			int pageSize, String sortField, String order);

	public PageInfo<UnsettledPopulation> findUnsettledPopulationForPageByOrgId(
			BaseUnsettledPopulationSearchCondition unsettledPopulationCondition,
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	public List<UnsettledPopulation> findUnsettledPopulationList(
			BaseUnsettledPopulationSearchCondition unsettledPopulationCondition,
			Long orgId, String sidx, String sord);

	public PageInfo<UnsettledPopulation> findUnsettledPopulationByorgCodeForGis(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public Integer getCount(BaseUnsettledPopulationSearchCondition condition);
}

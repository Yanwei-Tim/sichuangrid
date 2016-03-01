package com.tianque.baseInfo.aidNeedPopulation.dao;

import java.util.List;

import com.tianque.baseInfo.aidNeedPopulation.domain.AidNeedPopulation;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchAidNeedPopulationVo;

public interface SearchAidNeedPopulationDao {

	public PageInfo<AidNeedPopulation> searchAidNeedPopulation(
			SearchAidNeedPopulationVo AidNeedSearchCondition, int pageNum,
			int pageSize, String sortField, String order);

	public List<AidNeedPopulation> searchAidNeedPopulationForExport(
			SearchAidNeedPopulationVo AidNeedSearchCondition, Integer pageNum,
			Integer pageSize, String sortField, String order);

	public Integer getCount(SearchAidNeedPopulationVo searchAidNeedPopulationVo);
}

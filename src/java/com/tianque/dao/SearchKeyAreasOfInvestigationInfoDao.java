package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchKeyAreasOfInvestigationInfo;

public interface SearchKeyAreasOfInvestigationInfoDao {
	/**
	 * 根据日期分页查询信息
	 */
	public PageInfo<SearchKeyAreasOfInvestigationInfo> searchKeyAreasOfInvestigationInfo(
			SearchKeyAreasOfInvestigationInfo searchKeyAreasOfInvestigationInfo, Integer pageNum,
			Integer pageSize, String sortField, String order);

}

package com.tianque.baseInfo.superiorVisit.dao;

import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchSuperiorVisitVo;

public interface SearchSuperiorVisitDao {
	public PageInfo<SuperiorVisit> searchAttentionPersonnel(
			SearchSuperiorVisitVo searchSuperiorVisitVo, int pageNum,
			int pageSize, String sortField, String order);

	public List<SuperiorVisit> searchSuperiorVisitsForExport(
			SearchSuperiorVisitVo searchSuperiorVisitVo, Integer page,
			Integer rows, String sortField, String order);

	public List findSuperiorVisitByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode);

	public int getCountSuperiorVisitByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map);

	/**
	 * 快速检索
	 */
	public PageInfo<SuperiorVisit> fastSearchSuperiorVisit(
			SearchSuperiorVisitVo searchSuperiorVisitVo, Integer page,
			Integer rows, String sidx, String sord);

	public Integer getCount(SearchSuperiorVisitVo visitVo);

}

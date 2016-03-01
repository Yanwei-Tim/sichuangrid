package com.tianque.baseInfo.superiorVisit.service;

import java.util.List;

import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchSuperiorVisitVo;

public interface SearchSuperiorVisitService {
	public PageInfo<SuperiorVisit> searchAttentionPersonnel(
			SearchSuperiorVisitVo searchSuperiorVisitVo, int pageNum,
			int pageSize, String sortField, String order);

	public List<SuperiorVisit> searchSuperiorVisitsForExport(
			SearchSuperiorVisitVo searchSuperiorVisitVo, Integer page,
			Integer rows, String sortField, String order);

	public List<SuperiorVisit> findSuperiorVisitByNameAndPinyinAndOrgInternalCode(
			String name, String orgInternalCode);

	public PageInfo<SuperiorVisit> fastSearchSuperiorVisit(
			SearchSuperiorVisitVo searchSuperiorVisitVo, Integer page,
			Integer rows, String sidx, String sord);

	public String[][] getExportPopertyArray();

	public Integer getCount(SearchSuperiorVisitVo visitVo);
}

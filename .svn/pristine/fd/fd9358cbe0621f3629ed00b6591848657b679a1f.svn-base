package com.tianque.baseInfo.dangerousChemicalsUnit.dao;

import java.util.List;

import com.tianque.baseInfo.dangerousChemicalsUnit.domain.DangerousChemicalsUnit;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchDangerousChemicalsUnitVo;

public interface SearchDangerousChemicalsUnitDao {
	public PageInfo<DangerousChemicalsUnit> fastSearchDangerousChemicalsUnit(
			SearchDangerousChemicalsUnitVo searchDangerousChemicalsUnitVo,
			Integer pageNum, Integer pageSize, String sortField, String order);

	public PageInfo<DangerousChemicalsUnit> findDangerousChemicalsUnitByQueryCondition(
			SearchDangerousChemicalsUnitVo searchDangerousChemicalsUnitVo,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public List<DangerousChemicalsUnit> searchDangerousChemicalsUnitForExport(
			SearchDangerousChemicalsUnitVo searchDangerousChemicalsUnitVo,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public Integer getCount(
			SearchDangerousChemicalsUnitVo searchDangerousChemicalsUnitVo);
}

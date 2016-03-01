package com.tianque.service;

import java.util.List;

import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchDruggyVo;

public interface SearchDruggyService {

	public String[][] getExportPopertyArray();

	public PageInfo<Druggy> findDruggysByQueryCondition(
			SearchDruggyVo searchDruggyVo, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public List<Druggy> searchDruggysForExport(SearchDruggyVo searchDruggyVo,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public PageInfo<Druggy> fastSearchDruggy(SearchDruggyVo searchDruggyVo,
			Integer pageNum, Integer pageSize, String sortField, String order);

	public Integer getCount(SearchDruggyVo searchDruggyVo);
}

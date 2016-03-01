package com.tianque.baseInfo.druggy.dao;

import java.util.List;

import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchDruggyVo;

public interface SearchDruggyDao {
	/**
	 * 根据查询条件分页查询吸毒人员
	 */
	public PageInfo<Druggy> findDruggysByQueryCondition(
			SearchDruggyVo searchDruggyVo, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public List<Druggy> searchDruggysForExport(SearchDruggyVo searchDruggyVo,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public List findSuperiorVisitByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode);

	public Long getDetoxiCateCaseCount(String orgInternalCode, Long type);

	public Long getHelpedCount(String orgInternalCode, Long type);

	public Long getNotHelpedCount(String orgInternalCode, Long type);

	public Integer getCount(SearchDruggyVo searchDruggyVo);

	public PageInfo<Druggy> fastSearchDruggy(SearchDruggyVo searchDruggyVo,
			Integer pageNum, Integer pageSize, String sortField, String order);

}

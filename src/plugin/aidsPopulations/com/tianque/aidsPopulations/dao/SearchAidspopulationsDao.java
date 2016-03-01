package com.tianque.aidsPopulations.dao;

import java.util.List;

import com.tianque.aidsPopulations.domain.Aidspopulations;
import com.tianque.aidsPopulations.domain.vo.SearchAidspopulationsVo;
import com.tianque.core.vo.PageInfo;

public interface SearchAidspopulationsDao {

	/**
	 * 搜索
	 * 
	 * @param searchAidspopulationsVo
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @return
	 */
	public PageInfo<Aidspopulations> searchAidspopulations(
			SearchAidspopulationsVo searchAidspopulationsVo, Integer pageNum,
			Integer pageSize, String sortField, String order);

	/**
	 * 导出
	 * 
	 * @param searchAidspopulationsVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public List<Aidspopulations> searchAidspopulationsForExport(
			SearchAidspopulationsVo searchAidspopulationsVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public Integer getCount(SearchAidspopulationsVo searchAidspopulationsVo);

}

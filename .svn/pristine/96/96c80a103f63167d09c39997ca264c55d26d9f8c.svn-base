package com.tianque.aidsPopulations.service;

import java.util.List;

import com.tianque.aidsPopulations.domain.Aidspopulations;
import com.tianque.aidsPopulations.domain.vo.SearchAidspopulationsVo;
import com.tianque.core.vo.PageInfo;

public interface SearchAidspopulationsService {
	/**
	 * 判断是导出哪种模式用的数组
	 * 
	 * @return
	 */
	public String[][] getExportPopertyArray();

	/**
	 * 获取导出的数据
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

	/**
	 * 查询艾滋病人员通过vo对象
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

	public Integer getCount(SearchAidspopulationsVo searchAidspopulationsVo);

}

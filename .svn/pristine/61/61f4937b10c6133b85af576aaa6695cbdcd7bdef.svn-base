package com.tianque.analysis.api;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.Dimension;

public interface DimensionDubboService {
	/**
	 * 添加
	 * 
	 * @param dimension
	 * @return
	 */
	Dimension addDimension(Dimension dimension);

	/**
	 * 修改
	 * 
	 * @param dimension
	 * @return
	 */
	Dimension updateDimension(Dimension dimension);

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @param name
	 * @param keyName
	 * @param type
	 * @return
	 */
	PageInfo<Dimension> findDimensionForPage(Integer pageNum, Integer pageSize,
			String sortField, String order, Dimension dimension);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	void deleteDimensionById(Long id);

	/**
	 * 查询单个
	 * 
	 * @param id
	 * @return
	 */
	Dimension getDimensionById(Long id);

	/**
	 * 根据条件查询
	 * 
	 * @time: 2015-3-19 下午02:18:39
	 */
	Dimension getDimensionByConditions(Dimension dimension);

	List<Dimension> queryDimensionListByCombinationName(String combinationName);

}

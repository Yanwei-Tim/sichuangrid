package com.tianque.baseInfo.idleYouth.service;

import java.util.List;

import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.domain.vo.SearchIdleYouthVo;

public interface SearchIdleYouthService {

	/**
	 * 查询需要导出的数据
	 * 
	 * @param idleYouthSearchCondition
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @return
	 */
	public List<IdleYouth> findIdleYouthsForExport(
			SearchIdleYouthVo idleYouthSearchCondition, Integer pageNum,
			Integer pageSize, String sortField, String order);

	public Integer getCount(SearchIdleYouthVo idleYouthVo);
}

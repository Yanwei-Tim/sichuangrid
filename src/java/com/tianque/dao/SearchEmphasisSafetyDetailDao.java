package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchEmphasisSafetyDetail;
import com.tianque.domain.workingRecord.EmphasisSafetyDetail;

public interface SearchEmphasisSafetyDetailDao {
	/**
	 * 根据日期分页查询信息
	 */
	public PageInfo<EmphasisSafetyDetail> searchEmphasisSafetyDetail(
			SearchEmphasisSafetyDetail searchEmphasisSafetyDetail, Integer pageNum,
			Integer pageSize, String sortField, String order, String allDailyDirectoryId);
}

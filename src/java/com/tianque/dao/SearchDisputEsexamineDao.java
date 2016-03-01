package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchDisputEsexamine;
import com.tianque.domain.workingRecord.DisputEsexamine;

public interface SearchDisputEsexamineDao {
	/**
	 * 根据日期分页查询信息
	 */
	public PageInfo<DisputEsexamine> searchDisputEsexamine(
			SearchDisputEsexamine searchDisputEsexamine, Integer pageNum, Integer pageSize,
			String sortField, String order, String allDailyDirectoryId);
}

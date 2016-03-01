package com.tianque.baseInfo.superiorVisit.service;

import java.util.List;

import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisitHistory;
import com.tianque.core.vo.PageInfo;

public interface SuperiorVisitHistoryService {

	public SuperiorVisitHistory addSuperiorVisitHistory(
			SuperiorVisitHistory visitHistory, List<Long> visitTypes);

	public SuperiorVisitHistory updateSuperiorVisitHistory(
			SuperiorVisitHistory visitHistory, List<Long> visitTypes);

	public SuperiorVisitHistory getSuperiorVisitHistoryById(Long id);

	public void deleteSuperiorVisitHistoryByIds(Long[] deleteIds);

	public PageInfo<SuperiorVisitHistory> findSuperiorVisitHistorys(
			SuperiorVisitHistory visitHistory, Integer pageNum,
			Integer pageSize, String sidx, String sord);

}

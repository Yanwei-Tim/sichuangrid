package com.tianque.baseInfo.superiorVisit.dao;

import java.util.List;

import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisitHistory;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.VisitHisTypeVo;

public interface SuperiorVisitHistoryDao {

	public Long addSuperiorVisitHistory(SuperiorVisitHistory visitHistory);

	public SuperiorVisitHistory updateSuperiorVisitHistory(
			SuperiorVisitHistory visitHistory);

	public SuperiorVisitHistory getSuperiorVisitHistoryById(Long id);

	public int deleteSuperiorVisitHistory(Long id);

	public void addSuperiorVisitHisType(Long superiorVisitHistoryId,
			Long visitType);

	public PageInfo<SuperiorVisitHistory> findSuperiorVisitHistorys(
			SuperiorVisitHistory visitHistory, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public int deleteSuperiorVisitHistoryTypes(Long id);

	public List<VisitHisTypeVo> findVisitTypeById(Long id);

}

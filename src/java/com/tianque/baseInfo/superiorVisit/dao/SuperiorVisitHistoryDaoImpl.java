package com.tianque.baseInfo.superiorVisit.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisitHistory;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.VisitHisTypeVo;

@Repository("superiorVisitHistoryDao")
public class SuperiorVisitHistoryDaoImpl extends AbstractBaseDao implements
		SuperiorVisitHistoryDao {

	@Override
	public Long addSuperiorVisitHistory(
			SuperiorVisitHistory superiorVisitHistory) {
		return (Long) getSqlMapClientTemplate().insert(
				"superiorVisitHistory.addSuperiorVisitHistory",
				superiorVisitHistory);
	}

	@Override
	public SuperiorVisitHistory getSuperiorVisitHistoryById(Long id) {

		return (SuperiorVisitHistory) getSqlMapClientTemplate().queryForObject(
				"superiorVisitHistory.getSuperiorVisitHistoryById", id);
	}

	@Override
	public PageInfo<SuperiorVisitHistory> findSuperiorVisitHistorys(
			SuperiorVisitHistory visitHistory, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		PageInfo<SuperiorVisitHistory> pageInfo = new PageInfo<SuperiorVisitHistory>();
		if (StringUtil.isStringAvaliable(sortField)) {
			visitHistory.setOrder(order);
			visitHistory.setSortField(sortField);
		}
		List list = getSqlMapClientTemplate().queryForList(
				"superiorVisitHistory.findSuperiorVisitHistoryByVisitId",
				visitHistory, (pageNum - 1) * pageSize, pageSize);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"superiorVisitHistory.countSuperiorVisitHistory", visitHistory);
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;

	}

	@Override
	public SuperiorVisitHistory updateSuperiorVisitHistory(
			SuperiorVisitHistory SuperiorVisitHistory) {
		getSqlMapClientTemplate().update(
				"superiorVisitHistory.updateSuperiorVisitHistory",
				SuperiorVisitHistory);
		return getSuperiorVisitHistoryById(SuperiorVisitHistory.getId());
	}

	@Override
	public int deleteSuperiorVisitHistory(Long id) {
		deleteSuperiorVisitHistoryTypes(id);
		return getSqlMapClientTemplate().delete(
				"superiorVisitHistory.deleteSuperiorVisitHistoryById", id);
	}

	@Override
	public void addSuperiorVisitHisType(Long superiorVisitHistoryId,
			Long visitType) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("superiorVisitHistoryId", superiorVisitHistoryId);
		map.put("superiorVisitType", visitType);
		getSqlMapClientTemplate().insert(
				"superiorVisitHistory.addSuperiorVisitHisType", map);
	}

	@Override
	public int deleteSuperiorVisitHistoryTypes(Long id) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().delete(
				"superiorVisitHistory.deleteVisitHisTypeById", id);
	}

	@Override
	public List<VisitHisTypeVo> findVisitTypeById(Long id) {
		// TODO Auto-generated method stub
		return (List<VisitHisTypeVo>) getSqlMapClientTemplate().queryForList(
				"superiorVisitHistory.getVisitHisTypeById", id);
	}

}

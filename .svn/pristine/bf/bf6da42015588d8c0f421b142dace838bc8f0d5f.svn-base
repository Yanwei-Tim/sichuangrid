package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.ExamineScoresDao;
import com.tianque.domain.ExamineScores;

@Repository("examineScoresDao")
public class ExamineScoresDaoImpl extends AbstractBaseDao implements ExamineScoresDao {

	@Override
	public ExamineScores addExamineScores(ExamineScores examineScores) {
		Long id = (Long) getSqlMapClientTemplate().insert("examineScores.addExamineScores",
				examineScores);
		return getSimpleExamineScoresById(id);
	}

	@Override
	public ExamineScores getSimpleExamineScoresById(Long id) {
		return (ExamineScores) getSqlMapClientTemplate().queryForObject(
				"examineScores.getSimpleExamineScoresById", id);
	}

	@Override
	public Integer countExsistedExamineScoresByOrgIdAndYear(Long orgId, String year) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("year", year);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"examineScores.countExsistedExamineScoresByOrgIdAndYear", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<ExamineScores> findExamineScoresForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("filterOrgInternalCode", orgInternalCode);
		map.put("sortField", sidx);
		map.put("order", sord);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"examineScores.countExamineScoresForPageByOrgInternalCode", map);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<ExamineScores> list = getSqlMapClientTemplate().queryForList(
				"examineScores.findExamineScoresForPageByOrgInternalCode", map, (page - 1) * rows,
				rows);
		PageInfo<ExamineScores> pageInfo = new PageInfo<ExamineScores>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}
}
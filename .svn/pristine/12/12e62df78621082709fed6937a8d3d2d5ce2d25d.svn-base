package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.EvaluateDao;
import com.tianque.domain.Evaluate;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.state.EvaluateState;

@Repository("evaluateDao")
public class EvaluateDaoImpl extends AbstractBaseDao implements EvaluateDao {

	@Override
	public Evaluate addEvaluate(Evaluate evaluate) {
		validate(evaluate);

		// if(evaluate.getOrganization()==null){
		// throw new BusinessValidationException("部门为空");
		// }
		Long id = (Long) getSqlMapClientTemplate().insert(
				"evaluate.addEvaluate", evaluate);
		return getSimpleEvaluateById(id);
	}

	private void validate(Evaluate evaluate) {
		if (null == evaluate.getYear()
				|| evaluate.getYear().trim().length() == 0) {
			throw new BusinessValidationException("年度不能为空");
		}
		if (null == evaluate.getTitle()
				|| evaluate.getTitle().trim().length() == 0) {
			throw new BusinessValidationException("标题不能为空");
		}
	}

	@Override
	public void deleteEvaluateById(Long id) {
		getSqlMapClientTemplate().delete("evaluate.deleteEvaluateById", id);
	}

	@Override
	public Evaluate updateEvaluate(Evaluate evaluate) {
		if (null == evaluate || null == evaluate.getId()) {
			throw new BusinessValidationException("评估不能为空");
		}
		getSqlMapClientTemplate().update("evaluate.updateEvaluate", evaluate);
		return getSimpleEvaluateById(evaluate.getId());
	}

	@Override
	public Evaluate getSimpleEvaluateById(Long id) {
		return (Evaluate) getSqlMapClientTemplate().queryForObject(
				"evaluate.getSimpleEvaluateById", id);
	}

	@Override
	public PageInfo<Evaluate> findEvaluateResultsByOrgIdAndYearAndTitle(
			Long orgId, String year, String title, Long evaluateTypeId,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map map = new HashMap();
		map.put("orgId", orgId);
		map.put("year", year);
		map.put("title", title);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("evaluateTypeId", evaluateTypeId);
		List<Evaluate> evaluates = getSqlMapClientTemplate().queryForList(
				"evaluate.findEvaluatesByOrgIdAndYearAndTitle", map,
				(pageNum - 1) * pageSize, pageSize);

		PageInfo<Evaluate> pageInfo = new PageInfo<Evaluate>();
		pageInfo.setResult(evaluates);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setTotalRowSize((Integer) getSqlMapClientTemplate()
				.queryForObject(
						"evaluate.countEvaluatesByOrgIdAndYearAndTitle", map));
		return pageInfo;
	}

	@Override
	public List<Evaluate> findEvaluateResultsByOrgId(Long orgId) {
		Map map = new HashMap();
		map.put("orgId", orgId);
		List<Evaluate> evaluates = getSqlMapClientTemplate().queryForList(
				"evaluate.findEvaluatesByOrgIdAndYearAndTitle", map);
		return evaluates;
	}

	@Override
	public Evaluate getLastEvaluateResultByOrgIdAndEvaluateType(Long orgId,
			Long evaluateTypeId, int state) {
		Map map = new HashMap();
		map.put("orgId", orgId);
		map.put("evaluateTypeId", evaluateTypeId);
		map.put("state", state);
		List<Evaluate> evaluates = getSqlMapClientTemplate().queryForList(
				"evaluate.getLastEvaluateResultByOrgIdAndEvaluateType", map);
		if (evaluates == null || evaluates.size() <= 0) {
			return null;
		}
		return evaluates.get(0);
	}

	@Override
	public Evaluate ispigeonholeEvaluateById(Evaluate evaluate) {
		getSqlMapClientTemplate().update("evaluate.ispigeonholeEvaluateById",
				evaluate);
		return getSimpleEvaluateById(evaluate.getId());
	}

	@Override
	public List<Evaluate> findEvaluateResultByOrgIdAndYear(Long id,
			String year, Integer page, Integer rows, String sidx, String sord) {
		Map map = new HashMap();
		map.put("orgId", id);
		map.put("year", year);
		map.put("sortField", sidx);
		map.put("order", sord);

		List<Evaluate> evaluates = getSqlMapClientTemplate().queryForList(
				"evaluate.findEvaluateResultByOrgIdAndYear", map);
		return evaluates;
	}

	@Override
	public Long countEvaluateResultByOrgIdAndYear(Long id, String year) {
		Map map = new HashMap();
		map.put("orgId", id);
		map.put("year", year);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"evaluate.countEvaluateResultByOrgIdAndYear", map);
	}

	@Override
	public Long countUsedEvaluateByYear(String year) {
		Long countUsedEvaluate = (Long) getSqlMapClientTemplate()
				.queryForObject("evaluate.countUsedEvaluate", year);
		return countUsedEvaluate;
	}

	@Override
	public List<Evaluate> findEvaluatesByParentId(Long parentId) {
		return getSqlMapClientTemplate().queryForList(
				"evaluate.findEvaluatesByParentId", parentId);
	}

	@Override
	public void updateUnActiveEvaluateByEvaluateId(Long evaluateId) {
		Map map = new HashMap();
		map.put("evaluateId", evaluateId);
		map.put("state", EvaluateState.NONE);
		getSqlMapClientTemplate().update(
				"evaluate.updateUnActiveEvaluateByEvaluateId", map);
	}

	@Override
	public Long countUsedEvaluateByEvaluateId(Long id) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"evaluate.countUsedEvaluateByEvaluateId", id);
	}

	@Override
	public List<Evaluate> findUseEvaluatesByOrgIdAndYearAndState(String year,
			Long orgId, int state) {
		if (null == year || null == orgId) {
			return null;
		}
		Map query = new HashMap();
		query.put("year", year);
		query.put("orgId", orgId);
		query.put("state", state);
		return getSqlMapClientTemplate().queryForList(
				"evaluate.findUseEvaluatesByOrgIdAndYearAndState", query);
	}

	@Override
	public void updateTotalStandardScoreByEvaluateId(Long id) {
		getSqlMapClientTemplate().update(
				"evaluate.updateTotalStandardScoreByEvaluateId", id);
	}

	@Override
	public List<Evaluate> findEvaluatesByStandardEvaluateId(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"evaluate.findEvaluatesByStandardEvaluateId", id);
	}

	@Override
	public PageInfo<Evaluate> findAllLowerEvaluateResultList(Long orgId,
			String year, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		Map query = new HashMap();
		query.put("year", year);
		query.put("orgId", orgId);

		long totalRowSize = (Long) getSqlMapClientTemplate().queryForObject(
				"evaluate.countAllLowerEvaluateResultList", query);
		PageInfo<Evaluate> pageInfo = new PageInfo<Evaluate>();

		List<Evaluate> list = getSqlMapClientTemplate().queryForList(
				"evaluate.findAllLowerEvaluateResultList", query,
				(pageNum - 1) * pageSize, pageSize);
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(totalRowSize);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public PageInfo<Evaluate> findReportAllLowerEvaluateResultList(Long orgId,
			String year, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		Map query = new HashMap();
		query.put("year", year);
		query.put("orgId", orgId);

		long totalRowSize = (Long) getSqlMapClientTemplate().queryForObject(
				"evaluate.countReportAllLowerEvaluateResultList", query);
		PageInfo<Evaluate> pageInfo = new PageInfo<Evaluate>();

		List<Evaluate> list = getSqlMapClientTemplate().queryForList(
				"evaluate.findReportAllLowerEvaluateResultList", query,
				(pageNum - 1) * pageSize, pageSize);
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(totalRowSize);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public PageInfo<Evaluate> findNotReportAllLowerEvaluateResultList(
			Long orgId, String year, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map query = new HashMap();
		query.put("year", year);
		query.put("orgId", orgId);

		long totalRowSize = (Long) getSqlMapClientTemplate().queryForObject(
				"evaluate.countNotReportAllLowerEvaluateResultList", query);
		PageInfo<Evaluate> pageInfo = new PageInfo<Evaluate>();

		List<Evaluate> list = getSqlMapClientTemplate().queryForList(
				"evaluate.findNotReportAllLowerEvaluateResultList", query,
				(pageNum - 1) * pageSize, pageSize);
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(totalRowSize);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public PageInfo<Evaluate> findPigeOnHoleEvaluateResultsByOrgIdAndYearAndTitle(
			Long orgId, String year, String title, Long evaluatedTypeId,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map map = new HashMap();
		map.put("orgId", orgId);
		map.put("year", year);
		map.put("title", title);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("evaluateTypeId", evaluatedTypeId);
		List<Evaluate> evaluates = getSqlMapClientTemplate().queryForList(
				"evaluate.findPigeOnHoleEvaluatesByOrgIdAndYearAndTitle", map,
				(pageNum - 1) * pageSize, pageSize);

		PageInfo<Evaluate> pageInfo = new PageInfo<Evaluate>();
		pageInfo.setResult(evaluates);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setTotalRowSize((Integer) getSqlMapClientTemplate()
				.queryForObject(
						"evaluate.countPigeOnHoleEvaluatesByOrgIdAndYearAndTitle",
						map));
		return pageInfo;
	}

	@Override
	public PageInfo<Evaluate> findEvaluatesByOrgIdAndYearAndTitleAndState(
			Long orgId, String year, String title, Long id, int state,
			Integer page, Integer rows, String sidx, String sord) {
		Map map = new HashMap();
		map.put("orgId", orgId);
		map.put("year", year);
		map.put("title", title);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("evaluateTypeId", id);
		map.put("state", state);
		List<Evaluate> evaluates = getSqlMapClientTemplate().queryForList(
				"evaluate.findEvaluatesByOrgIdAndYearAndTitle", map,
				(page - 1) * rows, rows);

		PageInfo<Evaluate> pageInfo = new PageInfo<Evaluate>();
		pageInfo.setResult(evaluates);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		pageInfo.setTotalRowSize((Integer) getSqlMapClientTemplate()
				.queryForObject(
						"evaluate.countEvaluatesByOrgIdAndYearAndTitle", map));
		return pageInfo;
	}
}

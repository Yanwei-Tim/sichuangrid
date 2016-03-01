package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Evaluate;

public interface EvaluateDao {

	public Evaluate addEvaluate(Evaluate evaluate);

	public Evaluate updateEvaluate(Evaluate evaluate);

	public void deleteEvaluateById(Long id);

	public Evaluate getSimpleEvaluateById(Long id);

	public PageInfo<Evaluate> findEvaluateResultsByOrgIdAndYearAndTitle(
			Long orgId, String year, String title, Long evaluatedTypeId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public PageInfo<Evaluate> findPigeOnHoleEvaluateResultsByOrgIdAndYearAndTitle(
			Long orgId, String year, String title, Long evaluatedTypeId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public List<Evaluate> findEvaluateResultsByOrgId(Long orgId);

	public Evaluate getLastEvaluateResultByOrgIdAndEvaluateType(Long orgId,
			Long evaluateTypeId, int state);

	public Evaluate ispigeonholeEvaluateById(Evaluate evaluate);

	public List<Evaluate> findEvaluateResultByOrgIdAndYear(Long id,
			String year, Integer page, Integer rows, String sidx, String sord);

	public Long countEvaluateResultByOrgIdAndYear(Long id, String year);

	public Long countUsedEvaluateByYear(String year);

	public List<Evaluate> findEvaluatesByParentId(Long parentId);

	public void updateUnActiveEvaluateByEvaluateId(Long evaluateId);

	public Long countUsedEvaluateByEvaluateId(Long id);

	public List<Evaluate> findUseEvaluatesByOrgIdAndYearAndState(String year,
			Long orgId, int state);

	public void updateTotalStandardScoreByEvaluateId(Long id);

	public List<Evaluate> findEvaluatesByStandardEvaluateId(Long id);

	public PageInfo<Evaluate> findAllLowerEvaluateResultList(Long orgId,
			String year, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	public PageInfo<Evaluate> findReportAllLowerEvaluateResultList(Long orgId,
			String year, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	public PageInfo<Evaluate> findNotReportAllLowerEvaluateResultList(
			Long orgId, String year, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public PageInfo<Evaluate> findEvaluatesByOrgIdAndYearAndTitleAndState(
			Long orgId, String year, String title, Long id, int state,
			Integer page, Integer rows, String sidx, String sord);

}

package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.ExamineScores;

public interface ExamineScoresDao {
	public ExamineScores addExamineScores(ExamineScores examineScores);

	public ExamineScores getSimpleExamineScoresById(Long id);

	public Integer countExsistedExamineScoresByOrgIdAndYear(Long orgId, String year);

	public PageInfo<ExamineScores> findExamineScoresForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx, String sord);
}

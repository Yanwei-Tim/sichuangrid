package com.tianque.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.ExamineScores;

public interface ExamineScoresService {
	public ExamineScores addExamineScores(ExamineScores examineScores);

	public ExamineScores getFullExamineScoresById(Long id);

	public PageInfo<ExamineScores> findExamineScoresForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx, String sord);

	public Integer countExsistedExamineScoresByOrgIdAndYear(Long orgId, String year);
}

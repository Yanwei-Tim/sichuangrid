package com.tianque.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.ExamineScoreRecordDao;
import com.tianque.domain.ExamineScoreRecord;

@Repository("examineScoreRecordDao")
public class ExamineScoreRecordDaoImpl extends AbstractBaseDao implements ExamineScoreRecordDao {
	@Override
	public ExamineScoreRecord addExamineScoreRecord(ExamineScoreRecord examineScoreRecord) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"examineScoreRecord.addExamineScoreRecord", examineScoreRecord);
		return getSimpleExamineScoreRecordById(id);
	}

	@Override
	public ExamineScoreRecord getSimpleExamineScoreRecordById(Long id) {
		return (ExamineScoreRecord) getSqlMapClientTemplate().queryForObject(
				"examineScoreRecord.getSimpleExamineScoreRecordById", id);
	}

	@Override
	public void deleteExamineScoreRecordByExamineScoresId(Long id) {
		getSqlMapClientTemplate().delete(
				"examineScoreRecord.deleteExamineScoreRecordByExamineScoresId", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamineScoreRecord> findExamineScoreRecordsByExamineScoresId(Long examineScoresId) {
		return getSqlMapClientTemplate().queryForList(
				"examineScoreRecord.findExamineScoreRecordsByExamineScoresId", examineScoresId);
	}
}

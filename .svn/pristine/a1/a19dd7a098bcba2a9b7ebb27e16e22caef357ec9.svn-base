package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.ExamineScoreRecordDao;
import com.tianque.domain.ExamineScoreRecord;
import com.tianque.service.ExamineScoreRecordService;

@Service("examineScoreRecordService")
public class ExamineScoreRecordServiceImpl extends AbstractBaseDao implements
		ExamineScoreRecordService {
	@Autowired
	private ExamineScoreRecordDao examineScoreRecordDao;

	@Override
	public ExamineScoreRecord addExamineScoreRecord(ExamineScoreRecord examineScoreRecord) {
		return examineScoreRecordDao.addExamineScoreRecord(examineScoreRecord);
	}

	@Override
	public void deleteExamineScoreRecordByExamineScoresId(Long id) {
		examineScoreRecordDao.deleteExamineScoreRecordByExamineScoresId(id);
	}

	@Override
	public List<ExamineScoreRecord> findExamineScoreRecordsByExamineScoresId(Long examineScoresId) {
		return examineScoreRecordDao.findExamineScoreRecordsByExamineScoresId(examineScoresId);
	}
}

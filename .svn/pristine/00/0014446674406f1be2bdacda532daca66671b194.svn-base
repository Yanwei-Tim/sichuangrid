package com.tianque.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.ExamineScoresDao;
import com.tianque.domain.ExamineItem;
import com.tianque.domain.ExamineScoreRecord;
import com.tianque.domain.ExamineScores;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.ExamineItemService;
import com.tianque.service.ExamineScoreRecordService;
import com.tianque.service.ExamineScoresService;

@Service("examineScoresService")
public class ExamineScoresServiceImpl extends AbstractBaseDao implements
		ExamineScoresService {
	@Autowired
	private ExamineScoresDao examineScoresDao;
	@Autowired
	private ExamineScoreRecordService examineScoreRecordService;
	@Autowired
	private ExamineItemService examineItemService;

	@Override
	public ExamineScores addExamineScores(ExamineScores examineScores) {
		if (examineScores.getScoreRecords() == null)
			throw new BusinessValidationException();
		if (examineScoresDao.countExsistedExamineScoresByOrgIdAndYear(
				examineScores.getOrg().getId(), examineScores.getYear()) > 0)
			throw new BusinessValidationException();
		calculateScore(examineScores);
		ExamineScores addExamineScores = examineScoresDao
				.addExamineScores(examineScores);
		addExamineScoreRecord(examineScores.getScoreRecords(), addExamineScores
				.getId().intValue());
		return addExamineScores;
	}

	private void calculateScore(ExamineScores examineScores) {
		Map<Long, Double> actualScoreMap = new HashMap<Long, Double>();
		Map<Long, Double> maxScoreMap = new HashMap<Long, Double>();

		List<ExamineScoreRecord> examineScoreRecords = examineScores
				.getScoreRecords();

		for (ExamineScoreRecord examineScoreRecord : examineScoreRecords) {
			ExamineItem examineItem = examineItemService
					.getSimpleExamineItemById(examineScoreRecord
							.getExamineItem().getId());
			examineItem.setActualCaculateScore(examineScoreRecord.getScore());
			if (examineItem.getOwnerItem() != null) {
				validateExamineItemScore(examineItem);
				statementScore(examineItem, maxScoreMap, actualScoreMap);
			}
		}

		Map<String, Double> statTotalScores = new HashMap<String, Double>();

		statTotalScores = calculatePlanScore(maxScoreMap, statTotalScores);
		Double sum = calculateActualScore(maxScoreMap, actualScoreMap);

		Double minusTotal = statTotalScores.get("minusTotal");
		Double addTotal = statTotalScores.get("addTotal");

		examineScores.setAnnualMaxScore(addTotal + minusTotal);
		examineScores.setAnnualActualScore(minusTotal + sum);
	}

	private void validateExamineItemScore(ExamineItem examineItem) {
		Double planScore = examineItem.getPlanScore();
		Double actualScore = examineItem.getActualCaculateScore();
		if (actualScore > planScore || actualScore < 0) {
			throw new BusinessValidationException();
		}
	}

	private void statementScore(ExamineItem examineItem,
			Map<Long, Double> planScoreMap, Map<Long, Double> actualScoreMap) {
		if (planScoreMap.get(examineItem.getOwnerItem().getId()) == null) {
			planScoreMap.put(examineItem.getOwnerItem().getId(),
					examineItem.getPlanScore() * examineItem.getExamineType());
		}
		if (actualScoreMap.get(examineItem.getOwnerItem().getId()) == null) {
			actualScoreMap.put(
					examineItem.getOwnerItem().getId(),
					examineItem.getActualCaculateScore()
							* examineItem.getExamineType());
		} else {
			Double tempScore = actualScoreMap.get(examineItem.getOwnerItem()
					.getId());
			actualScoreMap.put(
					examineItem.getOwnerItem().getId(),
					examineItem.getActualCaculateScore()
							* examineItem.getExamineType() + tempScore);
		}
	}

	private Map<String, Double> calculatePlanScore(
			Map<Long, Double> planScoreMap, Map<String, Double> statTotalScores) {
		Double minusTotal = 0D;
		Double addTotal = 0D;
		for (Map.Entry<Long, Double> entry : planScoreMap.entrySet()) {
			if (entry.getValue() > 0) {
				addTotal += entry.getValue();
			} else {
				minusTotal += Math.abs(entry.getValue());
			}
		}
		statTotalScores.put("minusTotal", minusTotal);
		statTotalScores.put("addTotal", addTotal);
		return statTotalScores;
	}

	private Double calculateActualScore(Map<Long, Double> planScoreMap,
			Map<Long, Double> actualScoreMap) {
		Double sum = 0D;
		for (Map.Entry<Long, Double> entry : planScoreMap.entrySet()) {
			if (Math.abs(actualScoreMap.get(entry.getKey())) > Math.abs(entry
					.getValue())) {
				sum += entry.getValue();
			} else {
				sum += actualScoreMap.get(entry.getKey());
			}
		}
		return sum;
	}

	private void addExamineScoreRecord(List<ExamineScoreRecord> list, int id) {
		for (ExamineScoreRecord examineScoreRecord : list) {
			examineScoreRecord.setExamineScoresId(id);
			examineScoreRecordService.addExamineScoreRecord(examineScoreRecord);
		}
	}

	@Override
	public ExamineScores getFullExamineScoresById(Long id) {
		ExamineScores examineScores = examineScoresDao
				.getSimpleExamineScoresById(id);
		List<ExamineScoreRecord> list = examineScoreRecordService
				.findExamineScoreRecordsByExamineScoresId(examineScores.getId());
		for (ExamineScoreRecord examineScoreRecord : list) {
			examineScoreRecord.setExamineItem(examineItemService
					.getSimpleExamineItemById(examineScoreRecord
							.getExamineItem().getId()));
		}
		examineScores.setScoreRecords(list);
		return examineScores;
	}

	@Override
	public PageInfo<ExamineScores> findExamineScoresForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord) {
		PageInfo<ExamineScores> pageInfo = examineScoresDao
				.findExamineScoresForPageByOrgInternalCode(orgInternalCode,
						page, rows, sidx, sord);
		for (ExamineScores examineScores : pageInfo.getResult()) {
			List<ExamineScoreRecord> list = examineScoreRecordService
					.findExamineScoreRecordsByExamineScoresId(examineScores
							.getId());
			for (ExamineScoreRecord examineScoreRecord : list) {
				examineScoreRecord.setExamineItem(examineItemService
						.getSimpleExamineItemById(examineScoreRecord
								.getExamineItem().getId()));
			}
			examineScores.setScoreRecords(list);
		}
		return pageInfo;
	}

	@Override
	public Integer countExsistedExamineScoresByOrgIdAndYear(Long orgId,
			String year) {
		return examineScoresDao.countExsistedExamineScoresByOrgIdAndYear(orgId,
				year);
	}
}

package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.ScoresStandDao;
import com.tianque.domain.ScoresStand;

@Repository("scoresStandDao")
public class ScoresStandDaoImpl extends AbstractBaseDao implements ScoresStandDao {

	@Override
	public ScoresStand addScoresStand(ScoresStand scoresStand) {
		getSqlMapClientTemplate().insert("scoresStand.addScoresStand", scoresStand);
		return scoresStand;
	}

	@Override
	public List<ScoresStand> findScoresStandByEvaluateId(Long evaluateId) {
		List<ScoresStand> list = getSqlMapClientTemplate().queryForList(
				"scoresStand.findScoresStandByEvaluateId", evaluateId);
		return list;
	}

	@Override
	public ScoresStand getScoresStandByEvaluateIdAndPropertyDictId(Long propertyDictId,
			Long evaluateId) {
		Map<String, Long> query = new HashMap<String, Long>();
		query.put("propertyDictId", propertyDictId);
		query.put("evaluateId", evaluateId);
		ScoresStand scoresStand = (ScoresStand) getSqlMapClientTemplate().queryForObject(
				"scoresStand.getScoresStandByEvaluateIdAndPropertyDictId", query);
		return scoresStand;
	}

	@Override
	public ScoresStand updateScoresStandByEvaluateIdAndPropertyDictId(ScoresStand scoresStand) {
		getSqlMapClientTemplate().update(
				"scoresStand.updateScoresStandByEvaluateIdAndPropertyDictId", scoresStand);
		return scoresStand;
	}

	@Override
	public void deleteScoresStandByEvaluateId(Long evaluateId) {
		getSqlMapClientTemplate().delete("scoresStand.deleteScoresStandByEvaluateId", evaluateId);
	}
}

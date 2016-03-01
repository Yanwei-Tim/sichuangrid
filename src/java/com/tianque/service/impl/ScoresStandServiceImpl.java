package com.tianque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.dao.ScoresStandDao;
import com.tianque.domain.Evaluate;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.ScoresStand;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.service.ScoresStandService;
import com.tianque.sysadmin.service.PropertyDictService;

@Transactional
@Service("scoresStandService")
public class ScoresStandServiceImpl implements ScoresStandService {
	@Autowired
	private ScoresStandDao scoresStandDao;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public ScoresStand getScoresStandByEvaluateIdAndPropertyDictId(Long evaluateId,
			Long propertyDictId) {
		return scoresStandDao.getScoresStandByEvaluateIdAndPropertyDictId(propertyDictId,
				evaluateId);
	}

	@Override
	public ScoresStand updateScoresStandByEvaluateIdAndPropertyDictId(Evaluate evaluate,
			ScoresStand scoresStand) {
		scoresStand.setPropertyDict(getPropertyDictType(scoresStand));
		scoresStand.setEvaluate(evaluate);
		scoresStandDao.updateScoresStandByEvaluateIdAndPropertyDictId(scoresStand);
		return scoresStand;
	}

	private PropertyDict getPropertyDictType(ScoresStand scoresStand) {
		PropertyDict propertyDict = propertyDictService.findPropertyDictByDomainNameAndInternalId(
				PropertyTypes.SCORES_STANDARDS, scoresStand.getScoresStandardType()).get(0);
		return propertyDict;
	}

}

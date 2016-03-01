package com.tianque.xichang.working.flow.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.dao.impl.BaseDaoImpl;
import com.tianque.xichang.working.flow.dao.EvaluateFeedbacksDao;
import com.tianque.xichang.working.flow.domain.EvaluateFeedbacks;

@Repository("evaluateFeedbacksDao")
public class EvaluateFeedbacksDaoImp extends BaseDaoImpl<EvaluateFeedbacks> implements
		EvaluateFeedbacksDao {

	@Override
	public Boolean isCanEvaluatePeopleAspirationByIdAndAccountType(Long accountId,
			String accountType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", accountId);
		map.put("accountType", accountType);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"evaluateFeedbacks.countEvaluateFeedbacks", map) > 0;
	}

	@Override
	public EvaluateFeedbacks getEvaluateFeedbacksByAccountIdAndType(Long accountId,
			String accountType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", accountId);
		map.put("accountType", accountType);
		return (EvaluateFeedbacks) getSqlMapClientTemplate().queryForObject(
				"evaluateFeedbacks.getEvaluateFeedbacksByAccountIdAndType", map);
	}

	@Override
	public boolean addEvaluateFeedbacks(EvaluateFeedbacks evaluateFeedbacks) {
		Long id = (Long) getSqlMapClientTemplate().insert("evaluateFeedbacks.addEvaluateFeedbacks",
				evaluateFeedbacks);
		return id != null && id > 0;
	}

	@Override
	public void deleteEvaluateFeedbacksByAccountIdAndType(Long accountId, String accountType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", accountId);
		map.put("accountType", accountType);
		this.getSqlMapClientTemplate().delete(
				"evaluateFeedbacks.deleteEvaluateFeedbacksByAccountIdAndType", map);
	}

}

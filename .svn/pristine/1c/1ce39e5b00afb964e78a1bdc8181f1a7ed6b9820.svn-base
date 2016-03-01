package com.tianque.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.tianque.baseInfo.utils.Utils;
import com.tianque.controller.vo.DetailedRuleTreeGridData;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.DetailedRuleDao;
import com.tianque.domain.DetailedRule;
import com.tianque.domain.vo.TmpDetailedRule;
import com.tianque.exception.base.BusinessValidationException;

@Repository("detailedRuleDao")
public class DetailedRuleDaoImpl extends AbstractBaseDao implements
		DetailedRuleDao {

	@Override
	public DetailedRule addDetailedRule(DetailedRule detailedRule) {
		if (!validate(detailedRule)) {
			throw new BusinessValidationException();
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"detailedRule.addDetailedRule", detailedRule);
		return getSimpleDetailedRuleById(id);
	}

	private boolean validate(DetailedRule detailedRule) {
		if (detailedRule == null)
			return false;
		if (detailedRule.getContent() == null
				|| "".equals(detailedRule.getContent()))
			return false;
		if (detailedRule.getStandardScore() != null
				&& detailedRule.getStandardScore().longValue() <= 0)
			return false;
		return true;
	}

	@Override
	public DetailedRule getSimpleDetailedRuleById(Long id) {
		return (DetailedRule) getSqlMapClientTemplate().queryForObject(
				"detailedRule.getSimpleDetailedRuleById", id);
	}

	@Override
	public List<DetailedRuleTreeGridData> getDetailedRulesByEvaluateId(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"detailedRule.getDetailedRulesByEvaluateId", id);
	}

	@Override
	public void deleteDetailedRuleByid(Long id) {
		getSqlMapClientTemplate().delete("detailedRule.deleteDetailedRuleById",
				id);
	}

	public void deleteDetailedRuleByEvaluateId(Long id) {
		getSqlMapClientTemplate().delete(
				"detailedRule.deleteDetailedRuleByEvaluateId", id);
	}

	@Override
	public DetailedRule updateDetailedRule(DetailedRule detailedRule) {
		if (!validate(detailedRule)) {
			throw new BusinessValidationException();
		}

		getSqlMapClientTemplate().update("detailedRule.updateDetailedRule",
				detailedRule);
		return getSimpleDetailedRuleById(detailedRule.getId());
	}

	@Override
	public DetailedRule getSimpleDetailedRuleByContent(
			DetailedRule detailedRule, Long id) {
		Map query = new HashMap();
		query.put("content", detailedRule.getContent());
		query.put("id", id);
		return (DetailedRule) getSqlMapClientTemplate().queryForObject(
				"detailedRule.getSimpleDetailedRuleByContent", query);
	}

	@Override
	public List<DetailedRule> findDetailedRulesByEvaluateId(Long evaluateId) {
		return getSqlMapClientTemplate().queryForList(
				"detailedRule.findDetailedRulesByEvaluateId", evaluateId);
	}

	@Override
	public Long[] findDialyLogTypesByDetailedRuleId(Long detailedRuleId) {
		List<Long> dialyLogTypes = getSqlMapClientTemplate().queryForList(
				"detailedRule.findDialyLogTypesByDetailedRuleId",
				detailedRuleId);
		return Utils.analyticalIds(dialyLogTypes);
	}

	@Override
	public Integer getCountStandardScoreByParentRuleId(Long detailedRuleId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"detailedRule.getCountStandardScoreByParentRuleId",
				detailedRuleId);
	}

	@Override
	public Integer getCountStandardScoreByEvaluateId(Long evaluateId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"detailedRule.getCountStandardScoreByEvaluateId", evaluateId);
	}

	@Override
	public Integer getSumSelfAssessmentScoreByParentDetailedRuleId(
			Long parentDetailedRuleId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"detailedRule.getSumSelfAssessmentScoreByParentDetailedRuleId",
				parentDetailedRuleId);
	}

	@Override
	public Integer getSumSelfAssessmentScoreByEvaluateId(Long evaluateId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"detailedRule.getSumSelfAssessmentScoreByEvaluateId",
				evaluateId);
	}

	@Override
	public Integer getSumDetailedRuleResultByParentDetailedRuleId(
			Long parentDetailedRuleId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"detailedRule.getSumDetailedRuleResultByParentDetailedRuleId",
				parentDetailedRuleId);
	}

	@Override
	public Integer getSumTotalScoreByEvaluateId(Long evaluateId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"detailedRule.getSumTotalScoreByEvaluateId", evaluateId);
	}

	@Override
	public void updateFirstLevelStandardScore(Long evaluateId) {
		getSqlMapClientTemplate().update(
				"detailedRule.updateFirstLevelStandardScore", evaluateId);
	}

	@Override
	public List<TmpDetailedRule> getTmpIdsAndParentIds(Long evaluateId) {
		return getSqlMapClientTemplate().queryForList(
				"detailedRule.getTmpIdsAndParentIds", evaluateId);
	}

	@Override
	public void addDetailedRules(final List<DetailedRule> detailedRules) {

		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (int i = 0; i < detailedRules.size(); i++) {
					DetailedRule detailedRule = (DetailedRule) detailedRules
							.get(i);
					detailedRule.setCreateDate(new Date());
					executor.insert("detailedRule.addDetailedRules",
							detailedRule);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	@Override
	public Long getCountSelfAssessmentScore(Long evaluateId) {
		if (null == evaluateId) {
			return null;
		}
		return (Long) getSqlMapClientTemplate().queryForObject(
				"detailedRule.getCountSelfAssessmentScore", evaluateId);
	}

	@Override
	public List<DetailedRule> findDetailedRulesByParentId(Long detailedRuleId) {
		if (null == detailedRuleId) {
			return null;
		}
		return getSqlMapClientTemplate().queryForList(
				"detailedRule.findDetailedRulesByParentId", detailedRuleId);
	}
}

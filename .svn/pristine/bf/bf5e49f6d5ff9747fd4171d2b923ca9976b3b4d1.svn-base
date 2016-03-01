package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.EvaluateStatisticDao;
import com.tianque.domain.vo.EvaluateStatisticVo;
import com.tianque.domain.vo.EvaluateVo;
import com.tianque.service.state.EvaluateState;

@Repository("evaluateStatisticDao")
public class EvaluateStatisticDaoImpl extends AbstractBaseDao implements EvaluateStatisticDao {

	@Override
	public List<EvaluateStatisticVo> findEvaluateStatistic(Long evaluatetypeid,
			String evaluateResult, String orgInternalCode, int rowNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("evaluatetypeid", evaluatetypeid);
		map.put("state", EvaluateState.PIGEONHOLE);
		map.put("evaluateResult", evaluateResult);
		map.put("orgInternalCode", orgInternalCode);
		map.put("rownum", rowNum);
		map.put("state", EvaluateState.PIGEONHOLE);
		return (List<EvaluateStatisticVo>) getSqlMapClientTemplate().queryForList(
				"evaluateStatistics.findEvaluateStatistic", map);
	}

	@Override
	public List<EvaluateVo> findEvaluateVo(Long evaluatetypeid, String orgInternalCode, int rowNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("evaluatetypeid", evaluatetypeid);
		map.put("state", EvaluateState.PIGEONHOLE);
		map.put("rownum", rowNum);
		return (List<EvaluateVo>) getSqlMapClientTemplate().queryForList(
				"evaluateStatistics.findEvaluateVo", map);
	}

}

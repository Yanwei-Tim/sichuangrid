package com.tianque.baseInfo.buildDatas.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.analyzing.domain.StatisticSearchVo;

/**
 * @author hxpwangyi@163.com
 * @date 2013-3-26
 */
@Component("builddataStatDao")
public class BuilddataStatDaoImpl extends AbstractBaseDao implements BuilddataStatDao {

	@Override
	public void addHistoryStatistic(StatisticSearchVo statisticSearchVo) {
		getSqlMapClientTemplate().insert("baseInfoStatistic.addBuilddataHistortyStatistic",
				statisticSearchVo);
	}

	@Override
	public void deleteBuilddataStatType(String orgCode, int year, int month, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("orgCode", orgCode);
		map.put("year", year);
		map.put("month", month);
		getSqlMapClientTemplate().delete("baseInfoStatistic.deleteStatisticByMap", map);
	}

	@Override
	public void updateMonthCreateAttentionNumAndTotal(Map<String, Object> map) {
		getSqlMapClientTemplate().update("baseInfoStatistic.updateMonthCreateAttentionNumAndTotal",
				map);
	}

	public Map<String, Object> getMonthCreateNumAndTotal(StatisticSearchVo statisticSearchVo) {
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject(
				"baseInfoStatistic.getMonthTotal", statisticSearchVo);
	}

}

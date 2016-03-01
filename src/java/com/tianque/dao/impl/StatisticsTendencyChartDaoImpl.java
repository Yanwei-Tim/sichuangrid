package com.tianque.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.CalendarUtil;
import com.tianque.dao.StatisticsTendencyChartDao;

@Repository("statisticsTendencyChartDao")
public class StatisticsTendencyChartDaoImpl extends AbstractBaseDao implements
		StatisticsTendencyChartDao {

	@Override
	public void statisticsMonthTendencyChart(String tableName, String keyType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("types", "月报");
		map.put("nowDate", CalendarUtil.now());
		map.put("year", CalendarUtil.format("yyyy",new Date()));
		map.put("month", CalendarUtil.format("MM",new Date()));
		map.put("monthOfFirstDay", CalendarUtil.getMonthFirstDay());
		map.put("monthOfLastDay", CalendarUtil.getMonthLastDay());
		if (keyType != null) {
			map.put("keyType", keyType);
		}
		getSqlMapClientTemplate().insert(
				"statisticsTendencyChart.statisticsMonthTendencyChart", map);
	}
}

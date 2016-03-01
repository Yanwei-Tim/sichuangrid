package com.tianque.plugin.analyzing.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.BaseInfoTables;

@Repository("tendencyChartDao")
public class TendencyChartDaoImpl extends AbstractBaseDao implements TendencyChartDao {

	@Override
	public Integer findTendencyChart(String typeTableName, String orgInternalCode, String time) {
		String[] data = time.split("-");
		int year = Integer.valueOf(data[0]);
		int month = Integer.valueOf(data[1]);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typeName", BaseInfoTables.getKeyName().get(typeTableName.toUpperCase()));
		map.put("orgInternalCode", orgInternalCode);
		map.put("createDate", time);
		map.put("year", year);
		map.put("month", month);
		try {
			getSqlMapClientTemplate().queryForObject(
					"statisticsTendencyChart.findTendencyChartToal", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"statisticsTendencyChart.findTendencyChartToal", map);
		return countNum;
	}

}

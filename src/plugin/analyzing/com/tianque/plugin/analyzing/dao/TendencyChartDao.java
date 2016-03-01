package com.tianque.plugin.analyzing.dao;

public interface TendencyChartDao {
	/**
	 * 根据表名和orgCode以及时间统计该类的总和
	 * 
	 * @param typeTableName
	 * @param orgInternalCode
	 * @param time
	 * @return
	 */
	public Integer findTendencyChart(String typeTableName, String orgInternalCode, String time);
}

package com.tianque.plugin.analyzing.service;

import java.util.List;

import com.tianque.plugin.analyzing.domain.BaseSituationStatement;

public interface BaseSituationStatementService {

	/***
	 * job统计基础信息表数据
	 * 
	 * @param year
	 *            年份
	 * @param month
	 *            当前月上一个月
	 */
	public void addBaseStituationStatementHistory(int year, int month);

	/***
	 * 根据年月查询得到基本信息统计表数据
	 */
	public List<BaseSituationStatement> findBaseSituationStatementListByCondition(
			Long orgId, int year, int month, Integer type);

	/***
	 * 根据前端所选择字段对报表进行排序
	 */
	public List<BaseSituationStatement> findBaseSituationStatementListBySort(
			Long orgId, int year, int month, Integer type, String sortName,
			String sort);

}

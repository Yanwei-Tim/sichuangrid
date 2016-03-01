package com.tianque.plugin.statistics.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.plugin.statistics.domain.GeneralSituation;
import com.tianque.plugin.statistics.vo.GeneralStituationSearchVo;

public interface GeneralSituationDao {

	/***
	 * 生成任务清单研判数据（宣传核查、吸毒、刑释、社区服刑、）
	 * @param year 生成年份
	 * @param month 月份
	 * @param orgType 组织机构类别
	 * @param orgLevel 组织机构层级
	 * @param tableName 表名
	 * @param basesicType 大类别(流口、吸毒、发现治安隐患等6大类别)
	 * @param detailType 子类别(流口下的宣传核查等、治安隐患下的涉毒等)
	 * @param column (签收的字段，各大表中签收的字段都不一样，所以需要后台传输标识)
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 */
	public void createGeneralSituationByDate(String findGeneralSituationListByConditions,Integer year,Integer month,Long orgType,Long orgLevel,String tableName,
					String basesicType,String detailType,String column,Date startDate,Date endDate);
	
	/***
	 * 统计重症精神病人员历史数据
	 * @param year
	 * @param month
	 * @param orgType
	 * @param orgLevel
	 * @param basesicType
	 * @param startDate
	 * @param endDate
	 */
	public void createGeneralSituationOfMentalpatient(String findGeneralSituationListByConditions,Integer year,Integer month,Long orgType,Long orgLevel,
			String basesicType,Date startDate,Date endDate);
	/***
	 * 发现治安隐患、民警带领下开展工作、异常情况报告 历史数据查询
	 * @param year
	 * @param month
	 * @param orgType
	 * @param orgLevel
	 * @param basesicType 大类别
	 * @param detailType 子类别
	 * @param subType 字段类别
	 * @param column 签收字段
	 * @param startDate
	 * @param endDate
	 */
	public void createGeneralSituationByType(String statisticsTableName,Integer year,Integer month,Long orgType,Long orgLevel,String tableName,
			String basesicType,String detailType,String subType,String column,Date startDate,Date endDate);
	
	public void deleteGeneralSituationByDate(String tableName);
	
	/**
	 * 根据条件查询统计报表
	 * @param generalStituationSearchVo 查询条件封装类
	 * @return 
	 */
	public List<GeneralSituation> getStatisticInfoByConditions(GeneralStituationSearchVo generalStituationSearchVo);
	
	/***
	 * 大类数据查询
	 */
	public List<GeneralSituation> findGeneralSituationListByBasesicType(Map<String,Object> map);
	/***
	 * 子类数据查询
	 */
	public List<GeneralSituation> findGeneralSituationListByDetailType(Map<String,Object> map);
	/***
	 * 详细数据查询
	 */
	public List<GeneralSituation> findGeneralSituationListBySubType(Map<String,Object> map);
}

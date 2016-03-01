package com.tianque.plugin.analyzing.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.plugin.analyzing.domain.UserActivateReport;

/**
 * @Description:用户覆盖率持久层
 * @author zhangyouwei@hztianque.com
 * @date: 2015-1-16 上午12:27:29
 */
@DynamicIbatisDAO(value = "UserActivateReportDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("UserActivateReportDAO")
public interface UserActivateReportDAO {

	/***
	 * 根据时间查询统计用户登录情况
	 */
	public List<UserActivateReport> queryUserActivateReportRateByDateForList(
			Map<String, Object> map);

	/**
	 * 查询出前半部分(开通情况)的报表内容
	 * 
	 * @param map
	 * @return
	 */
	public List<UserActivateReport> querySimpleUserActivateReportForList(
			Map<String, Object> map);

	/**
	 * 新增
	 * 
	 * @param userActivateReport
	 * @return
	 */
	public Long addUserActivateReport(UserActivateReport userActivateReport);

	/**
	 * 查询出历史的（当前月用后半部分）报表内容
	 * 
	 * @param map
	 * @return
	 */
	public List<UserActivateReport> queryUserActivateReportHistoryForList(
			Map<String, Object> map);

	/**
	 * 根据年份和月份删除数据
	 * 
	 * @param map
	 */
	public void deleteAllDataByYearAndMonth(Map<String, Object> map);

}

package com.tianque.working.service;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.ReportWorkingRecord;

public interface ReportWorkingRecordService {
	/**
	 * 添加日常工作中上报信息
	 * 
	 * @param ReportWorkingRecord
	 *        上报信息对象
	 * @return ReportWorkingRecord 上报信息对象
	 */
	public ReportWorkingRecord addReportWorkingRecord(ReportWorkingRecord reportWorkingRecord);

	public ReportWorkingRecord getReportWorkingRecordById(Long id);

	/**
	 * 修改上报信息
	 */
	public ReportWorkingRecord updateReportWorkingRecord(ReportWorkingRecord reportWorkingRecord);

	/**
	 * 删除上报信息
	 */
	public void deleteReportWorkingRecord(Long id);

	/**
	 * 根据查询条件分页查询上报信息
	 */
	public PageInfo<ReportWorkingRecord> findReportWorkingRecordForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx, String sord,
			Long dailyDirectoryId);

	/**
	 * 修改上报信息
	 */
	public int updateSubmitState(Long id, Long submitStateId, Long expiredEntering);

	public int backWorkingRecord(Long id, Long submitStateId);

	/**
	 * 根据查询条件查询子网上报信息
	 * 
	 * @param dailyDirectoryId
	 *        dealDate orgId 目录 时间 父级网格
	 */
	public List<ReportWorkingRecord> findSunReportWorkingRecordByOrgIdAndDealDate(Long orgId,
			Long reportTime, Long dailyDirectoryId);

	/**
	 * 根据查询条件查询该月份子网格上报情况
	 * 
	 * @param dailyDirectoryId
	 *        dealDate orgId 目录 时间 网格
	 * @return true(为空) false(不为空)
	 */
	public boolean findSunReportWorkingByOrgIdAndDealDate(Long orgId, Date dealDate,
			Long dailyDirectoryId);

	/**
	 * 根据年度目录id，删除上报信息
	 */
	public void deleteReportWorkingRecordByYearId(Long yearId);

	/**
	 * 根据(年份)查询条件查询子网月报上报信息
	 * 
	 * @param dailyDirectoryId
	 *        dealDate orgId 目录 时间 父级网格
	 */
	public String[][] findAllReportWorkingRecordByOrgIdAndYear(Long orgId, Date dealDate,
			Long dailyDirectoryId, Long dailyYearId);

	/**
	 * 根据查询条件查询本网格上报信息
	 * 
	 * @param dailyDirectoryId
	 *        dealDate orgId 目录 时间 网格
	 */
	public List<ReportWorkingRecord> findReportWorkingRecordByOrgIdAndDealDate(Long orgId,
			Date dealDate, Long dailyDirectoryId);

	/**
	 * 根据时间段查询本网格月报信息
	 * 
	 * @param startDate
	 *        endDate dailyDirectoryId 时间 父级网格 目录
	 */
	public List<ReportWorkingRecord> findReportWorkingRecordByStartDateAndEndDate(Long orgId,
			Long startDate, Long endDate, Long dailyDirectoryId);

	/**
	 * 根据(年份)查询条件查询子网季报上报信息
	 * 
	 * @param dailyDirectoryId
	 *        dealDate orgId 目录 时间 父级网格
	 */
	public String[][] findAllReportWorkingRecordByOrgIdAndYearForQuarter(Long orgId, Date dealDate,
			Long dailyDirectoryId, Long dailyYearId);

	/**
	 * 根据(年份)查询条件查询子网半年报上报信息
	 * 
	 * @param dailyDirectoryId
	 *        dealDate orgId 目录 时间 父级网格
	 */
	public String[][] findAllReportWorkingRecordByOrgIdAndYearForHalf(Long orgId, Date dealDate,
			Long dailyDirectoryId, Long dailyYearId);

	/**
	 * 根据reprTime,dailyDirectoryId,orgid判断是否汇总
	 */
	public ReportWorkingRecord summarizingJudge(Long reportTime, Long dailyDirectoryId, Long orgid);

	/**
	 * 根据(年份)查询条件查询子网年报上报信息
	 * 
	 * @param dailyDirectoryId
	 *        dealDate orgId 目录 时间 父级网格
	 */
	public String[][] findAllReportWorkingRecordByOrgIdAndYearForYear(Long orgId, Date dealDate,
			Long dailyDirectoryId, Long dailyYearId);

	/**
	 * 根据查询条件查询本网格信息
	 * 
	 * @param dailyDirectoryId
	 *        reportTime orgId 目录 时间 网格 dailyDirectoryId一样，那么年报、季报、月报一样
	 */
	public List<ReportWorkingRecord> findReportWorkingRecordByOrgIdAndReportTime(Long orgId,
			Long reportTime, Long dailyDirectoryId);

	public List<ReportWorkingRecord> findReportWorkingRecord(Long orgId, Long dailyYearId,
			Long dailyDirectoryId);
}

package com.tianque.dao;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.ReportWorkingRecord;

public interface ReportWorkingRecordDao {
	/**
	 * 添加日常工作中上报信息
	 * 
	 * @param ReportWorkingRecord
	 *            上报信息对象
	 * @return ReportWorkingRecord 上报信息对象
	 */
	public ReportWorkingRecord addReportWorkingRecord(
			ReportWorkingRecord reportWorkingRecord);

	public ReportWorkingRecord getReportWorkingRecordById(Long id);

	/**
	 * 修改上报信息
	 */
	public ReportWorkingRecord updateReportWorkingRecord(
			ReportWorkingRecord reportWorkingRecord);

	/**
	 * 删除上报信息
	 */
	public void deleteReportWorkingRecord(Long id);

	/**
	 * 根据查询条件分页查询上报信息
	 */
	public PageInfo<ReportWorkingRecord> findReportWorkingRecordForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, String dailyDirectoryId);

	/**
	 * 修改上报信息上报状态
	 */
	public int updateSubmitState(Long id, Long submitStateId,
			Long expiredEntering);

	public int backWorkingRecord(Long id, Long submitStateId);

	/**
	 * 根据查询条件查询上报信息
	 * 
	 * @param dailyDirectoryId
	 *            dealDate orgId 目录 时间 网格
	 */
	public ReportWorkingRecord findReportWorkingRecordByOrgIdAndDealDate(
			Long orgId, Date dealDate, Long dailyDirectoryId);

	/**
	 * 根据年度目录id，删除上报信息
	 */
	public void deleteReportWorkingRecordByYearId(Long yearId);

	/**
	 * 根据查询条件查询上报信息
	 * 
	 * @param dailyDirectoryId
	 *            dealDate orgId 目录 时间 网格
	 */
	public List<ReportWorkingRecord> findAllReportWorkingRecordByOrgIdAndYear(
			Long orgId, Date dealDate, Long dailyDirectoryId);

	/**
	 * 根据时间段查询本网格月报信息
	 * 
	 * @param startDate
	 *            endDate dailyDirectoryId 时间 父级网格 目录
	 */
	public List<ReportWorkingRecord> findReportWorkingRecordByStartDateAndEndDate(
			Long orgId, Long startDate, Long endDate, Long dailyDirectoryId);

	/**
	 * 根据查询条件查询子网格月报上报信息
	 * 
	 * @param dailyDirectoryId
	 *            dealDate orgId dailyYearId 目录 时间 网格 年份
	 */
	public ReportWorkingRecord findSunReportWorkingRecordByOrgIdAndDealDate(
			Long submitType, Long orgId, Long reportTime, Long type,
			Long directoryReportTypeId, Long dailyYearId);

	/**
	 * 根据查询条件查询子网格上报信息
	 * 
	 * @param dailyDirectoryId
	 *            dealDate orgId 目录 时间 网格
	 */
	public List<ReportWorkingRecord> findSunAllReportWorkingRecordByOrgIdAndYear(
			Long orgId, Long dailyYearId, Long type, Long directoryReportTypeId);

	/**
	 * 根据reprTime,dailyDirectoryId,orgid判断是否汇总
	 */
	public ReportWorkingRecord summarizingJudge(Long reportTime,
			Long dailyDirectoryId, Long orgid);

	public List<ReportWorkingRecord> findSunAllReportWorkingRecordByOrgIdAndYearForQuert(
			Long orgId, Long dailyDirectoryId, Long type,
			Long directoryReportTypeId, Long dailyYearId);

	/**
	 * 根据reprTime,dailyDirectoryId,orgid查出数据
	 */
	public List<ReportWorkingRecord> findReportWorkingRecordByOrgIdAndReportTime(
			Long orgId, Long reportTime, Long dailyDirectoryId);

	public List<ReportWorkingRecord> findQuarterWorkingRecordByOrgIdAndReportTime(
			Long orgId, Long reportTime, Long dailyDirectoryId);

	public List<ReportWorkingRecord> findReportWorkingRecord(Long orgId,
			Long dailyYearId, Long dailyDirectoryId);
}

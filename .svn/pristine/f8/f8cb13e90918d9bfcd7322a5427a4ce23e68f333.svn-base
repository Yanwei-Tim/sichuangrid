package com.tianque.newVillage.dao;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.newVillage.domain.ReportDataSummary;

public interface VillageReportSummaryDao {

	public void addVillageReportSummary(ReportDataSummary reportDataSummary);

	public void updateReportSummaryReportStatus(Long id, Integer reportStatus,
			Date reportDate, String reportUser);

	public void updateReportSummaryCheckStatus(Long id, Integer checkStatus,
			Date checkDate, String checkUser);

	public void deleteReportSummaryById(Long id);

	public PageInfo<ReportDataSummary> findReportSummaryForList(Long orgId,
			Integer year, Integer quarter, Integer isPlaning, Integer page,
			Integer rows, String sidx, String sord);

	public List<ReportDataSummary> findReportSummaryByIds(List<Long> idList);

	public ReportDataSummary getReportSummaryById(Long id);

	public Integer checkReportSummary(Long orgId, Integer year,
			Integer quarter, Integer isPlaning);

	/**
	 * 报表统计
	 * 
	 * @param orgId
	 * @param year
	 *            年
	 * @param quarter
	 *            季度
	 * @return
	 */
	public List<ReportDataSummary> countReportSummaryForList(Long orgId,
			Integer year, Integer quarter, Integer isPlaning);

	public List<ReportDataSummary> findReportSummaryByYear(Long orgId,
			Integer year, Integer isPlaning, boolean isIncludeCurrent);
}

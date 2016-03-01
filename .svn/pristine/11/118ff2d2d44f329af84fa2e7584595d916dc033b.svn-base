package com.tianque.newVillage.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.newVillage.domain.NewVillageReport;
import com.tianque.newVillage.domain.ReportDataSummary;

public interface VillageReportSummaryService {

	public void addVillageReportSummary(ReportDataSummary reportDataSummary);

	public void updateReportSummaryReportStatus(List<Long> ids,
			Integer reportStatus, String idsStr, Integer isPlaning);

	public void updateReportSummaryCheckStatus(String[] ids, Integer checkStatus);

	public void deleteReportSummaryById(String[] ids);

	public PageInfo<ReportDataSummary> findReportSummaryForList(Long orgId,
			Integer year, Integer quarter, Integer isPlaning, Integer page,
			Integer rows, String sidx, String sord);

	public List<ReportDataSummary> findReportSummaryByIds(List<Long> idList);

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
	public List<NewVillageReport> countReportSummaryForList(Long orgId,
			Integer year, Integer quarter);
}

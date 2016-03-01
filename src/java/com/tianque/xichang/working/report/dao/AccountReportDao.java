package com.tianque.xichang.working.report.dao;

import java.util.List;

import com.tianque.xichang.working.report.domain.AccountReport;
import com.tianque.xichang.working.report.domain.AccountReportVo;

public interface AccountReportDao {

	/**
	 * 新增报表
	 * 
	 * @param accountReport
	 * @return
	 */
	AccountReport addAccountReport(AccountReport accountReport);

	/**
	 * 修改报表
	 * 
	 * @param accountReport
	 * @return
	 */
	AccountReport updateAccountReport(AccountReport accountReport);

	/**
	 * 根据id得到报表信息
	 * 
	 * @param id
	 * @return
	 */
	AccountReport getAccountReportById(Long id, Integer reportYear,
			Integer reportMonth);

	/**
	 * 根据查询条件得到报表信息
	 * 
	 * @param searchVo
	 * @return
	 */
	List<AccountReport> findAccountReportBySearchVo(AccountReportVo searchVo);

	/**
	 * 删除所有的社区级别的报表
	 * 
	 * @param orgCodeFindLevel
	 * @param reportType
	 * @param reportMonth
	 * @param reportYear
	 */
	public void deleteAllVillageAccountReport(Integer reportYear,
			Integer reportMonth, Integer reportType, String orgCodeFindLevel);

	/**
	 * 删除
	 * 
	 * @param orgId
	 * @param reportYear
	 * @param reportMonth
	 * @param reportType
	 * @param accountType
	 */
	public void deleteAccountReportByOrgIdAndTimeAndAcountType(Long orgId,
			Integer reportYear, Integer reportMonth, Integer reportType,
			String accountType);

	/**
	 * 统计是否有脏数据
	 * 
	 * @param idModList
	 * @param fetchNum
	 * @param year
	 * @param month
	 * @return
	 */
	public Integer countDirtyDataByModel(List<Long> idModList,
			int year, int month);

	/**
	 * 删除脏数据
	 * 
	 * @param idModList
	 * @param year
	 * @param month
	 */
	public void deleteDirtyDataByModel(List<Long> idModList,
			int year, int month);

	/**
	 * 根据条件查询
	 * 
	 * @param id
	 * @param reportYear
	 * @param reportMonth
	 * @param accountType
	 * @param reportType
	 * @return
	 */
	public List<AccountReport> findAccountReport(Long orgId,
			Integer reportYear, Integer reportMonth, String accountType,
			Integer reportType);

	/**
	 * 批量新增
	 * 
	 * @param accountReports
	 */
	public void batchAddDate(List<AccountReport> accountReports);

	/**
	 * 根据id和时间去删除数据
	 * 
	 * @param orgId
	 * @param reportYear
	 * @param reportMonth
	 * @param accountType
	 * @param reportType
	 */
	public void deleteAccountReportByOrgIdAndTimeAndType(Long id,
			Integer reportYear, Integer reportMonth, String accountType,
			Integer reportType);

}

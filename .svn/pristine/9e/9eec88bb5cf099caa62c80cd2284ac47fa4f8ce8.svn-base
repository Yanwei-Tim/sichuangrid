package com.tianque.xichang.working.report.service;

import java.util.List;

import com.tianque.xichang.working.report.domain.AccountReport;
import com.tianque.xichang.working.report.domain.AccountReportVo;

public interface AccountReportService {

	/**
	 * 编辑报表
	 * 
	 * @param accountReport
	 * @return
	 */
	public AccountReport editAccountReport(AccountReport accountReport);

	/**
	 * 刷新报表
	 * 
	 * @param accountReport
	 * @return
	 */
	public AccountReport refershAccountReport(AccountReport accountReport);

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
	public AccountReport findAccountReportBySearchVo(AccountReportVo searchVo);

	/**
	 * 编辑报表后保存
	 * 
	 * @param accountReport
	 * @return
	 */
	public AccountReport saveAccountReport(AccountReport accountReport);

	/**
	 * 根据台账报表查询台账报表
	 * 
	 * @param accountReport
	 * @return
	 */
	public AccountReport getAccountReportByAccountReport(
			AccountReport accountReport);

	/**
	 * 为社区级别的台账报表job用的(由于社区级别的台账没有可编辑的列，所以就不需要查看是否已经有值了，只需要在执行job前删除就可以了)
	 * 
	 * @param accountReports
	 */
	public void batchAddVillageAccountReportForJob(
			List<AccountReport> accountReports);

	/**
	 * 删除所有的社区级别的台账报表
	 * 
	 * @param orgCodeFindLevel
	 *            (删除的的层级)
	 * @param reportType
	 *            (报表类型)
	 * @param reportMonth
	 *            (报表月份)
	 * @param reportYear
	 *            (报表年份)
	 */
	public void deleteAllVillageAccountReport(Integer reportYear,
			Integer reportMonth, Integer reportType, String orgCodeFindLevel);

	/**
	 * 统计是否有脏数据
	 * 
	 * @param idModList
	 * @param fetchNum
	 * @param taskParameter
	 * @param year
	 * @param month
	 * @param orgLevelInternalId
	 * @param orgTypeInternalId
	 * @return
	 */
	public Integer countDirtyDataByModel(List<Long> idModList,
			String taskParameter, int year, int month, int orgLevelInternalId,
			int orgTypeInternalId);

	/**
	 * 删除脏数据
	 * 
	 * @param idModList
	 * @param fetchNum
	 * @param taskParameter
	 * @param year
	 * @param month
	 * @param orgLevelInternalId
	 * @param orgTypeInternalId
	 */
	public void deleteDirtyDataByModel(List<Long> idModList,
			String taskParameter, int year, int month, int orgLevelInternalId,
			int orgTypeInternalId);

	/**
	 * 为乡镇级别的台账报表job用的(由于乡镇级别的台账没有可编辑的列，所以就不需要查看是否已经有值了，只需要在执行job前删除就可以了)
	 * 
	 * @param accountReport
	 */
	public void batchTownAccountReportForJob(List<AccountReport> accountReports);

	/**
	 * 为县区级别的台账报表job用的
	 * 
	 * @param accountReport
	 */
	public void batchDistrictAccountReportForJob(
			List<AccountReport> accountReports);

}

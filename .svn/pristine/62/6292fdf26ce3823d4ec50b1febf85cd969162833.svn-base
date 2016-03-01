package com.tianque.xichang.working.report.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.util.ParametersConvertUtil;
import com.tianque.xichang.working.report.dao.AccountReportDao;
import com.tianque.xichang.working.report.domain.AccountReport;
import com.tianque.xichang.working.report.domain.AccountReportVo;

/**
 * 台账报表:数据操作层
 * 
 * @author
 * @date 2013-12-23 17:57:24
 */
@Repository("accountReportDao")
public class AccountReportDaoImpl extends AbstractBaseDao implements
		AccountReportDao {

	@Override
	public AccountReport addAccountReport(AccountReport accountReport) {
		long id = (Long) getSqlMapClientTemplate().insert(
				"accountReport.addAccountReport", accountReport);
		return getAccountReportById(id, accountReport.getReportYear(),
				accountReport.getReportMonth());

	}

	@Override
	public AccountReport updateAccountReport(AccountReport accountReport) {
		getSqlMapClientTemplate().update("accountReport.updateAccountReport",
				accountReport);
		return getAccountReportById(accountReport.getId(),
				accountReport.getReportYear(), accountReport.getReportMonth());
	}

	@Override
	public AccountReport getAccountReportById(Long id, Integer reportYear,
			Integer reportMonth) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportYear", reportYear);
		map.put("reportMonth", reportMonth);
		map.put("id", id);
		return (AccountReport) getSqlMapClientTemplate().queryForObject(
				"accountReport.getAccountReportById", map);
	}

	@Override
	public List<AccountReport> findAccountReportBySearchVo(
			AccountReportVo searchVo) {
		return getSqlMapClientTemplate().queryForList(
				"accountReport.findAccountReportBySearchVo", searchVo);
	}

	@Override
	public void deleteAllVillageAccountReport(Integer reportYear,
			Integer reportMonth, Integer reportType, String orgCodeFindLevel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportYear", reportYear);
		map.put("reportMonth", reportMonth);
		map.put("reportType", reportType);
		map.put("orgCodeFindLevel", orgCodeFindLevel);
		getSqlMapClientTemplate().delete(
				"accountReport.deleteAllVillageAccountReport", map);
	}

	@Override
	public void deleteAccountReportByOrgIdAndTimeAndAcountType(Long orgId,
			Integer reportYear, Integer reportMonth, Integer reportType,
			String accountType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportYear", reportYear);
		map.put("reportMonth", reportMonth);
		map.put("reportType", reportType);
		map.put("accountType", accountType);
		map.put("orgId", orgId);
		getSqlMapClientTemplate().delete(
				"accountReport.deleteAccountReportByOrgIdAndTimeAndAcountType",
				map);
	}

	@Override
	public Integer countDirtyDataByModel(List<Long> idModList,int year, int month) {
		if(idModList==null || idModList.size()==0){
			return 0;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgIdsList", ParametersConvertUtil.convertToListString(idModList));
		map.put("year", year);
		map.put("month", month);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"accountReport.countDirtyDataByModel", map);
	}

	@Override
	public void deleteDirtyDataByModel(List<Long> idModList,int year, int month) {
		if(idModList==null || idModList.size()==0){
			return ;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgIdsList", ParametersConvertUtil.convertToListString(idModList));
		map.put("year", year);
		map.put("month", month);
		getSqlMapClientTemplate().delete(
				"accountReport.deleteDirtyDataByModel", map);
	}

	@Override
	public List<AccountReport> findAccountReport(Long orgId,
			Integer reportYear, Integer reportMonth, String accountType,
			Integer reportType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("reportYear", reportYear);
		map.put("reportMonth", reportMonth);
		map.put("accountType", accountType);
		map.put("reportType", reportType);

		return (List<AccountReport>) getSqlMapClientTemplate().queryForList(
				"accountReport.findAccountReport", map);
	}

	@Override
	public void batchAddDate(List<AccountReport> accountReports) {
		if (accountReports == null || accountReports.size() == 0) {
			return;
		}

		batchInsertDate(accountReports, "accountReport.addAccountReport");
	}

	@Override
	public void deleteAccountReportByOrgIdAndTimeAndType(Long orgId,
			Integer reportYear, Integer reportMonth, String accountType,
			Integer reportType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("reportYear", reportYear);
		map.put("reportMonth", reportMonth);
		map.put("accountType", accountType);
		map.put("reportType", reportType);
		getSqlMapClientTemplate().delete(
				"accountReport.deleteAccountReportByOrgIdAndTimeAndType", map);
	}

}

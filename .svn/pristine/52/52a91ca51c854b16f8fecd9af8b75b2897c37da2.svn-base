package com.tianque.newVillage.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.vo.PageInfo;
import com.tianque.dao.AbstractBaseDao;
import com.tianque.newVillage.dao.VillageReportSummaryDao;
import com.tianque.newVillage.domain.ReportDataSummary;

@Repository("villageReportSummaryDao")
public class VillageReportSummaryDaoImpl extends AbstractBaseDao implements
		VillageReportSummaryDao {

	@Override
	public void addVillageReportSummary(ReportDataSummary reportDataSummary) {
		getSqlMapClientTemplate().insert(
				"villageReportSummary.addVillageReportSummary",
				reportDataSummary);
	}

	@Override
	public void updateReportSummaryReportStatus(Long id, Integer reportStatus,
			Date reportDate, String reportUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("reportStatus", reportStatus);
		map.put("reportDate", reportDate);
		map.put("reportUser", reportUser);
		getSqlMapClientTemplate().update(
				"villageReportSummary.updateReportSummaryReportStatus", map);

	}

	@Override
	public void updateReportSummaryCheckStatus(Long id, Integer checkStatus,
			Date checkDate, String checkUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("checkStatus", checkStatus);
		map.put("checkDate", checkDate);
		map.put("checkUser", checkUser);
		getSqlMapClientTemplate().update(
				"villageReportSummary.updateReportSummaryCheckStatus", map);
	}

	@Override
	public void deleteReportSummaryById(Long id) {
		getSqlMapClientTemplate().delete(
				"villageReportSummary.deleteReportSummaryById", id);
	}

	@Override
	public PageInfo<ReportDataSummary> findReportSummaryForList(Long orgId,
			Integer year, Integer quarter, Integer isPlaning, Integer page,
			Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("year", year);
		map.put("quarter", quarter);
		map.put("isPlaning", isPlaning);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"villageReportSummary.countReportSummaryForList", map);

		List<ReportDataSummary> list = getSqlMapClientTemplate().queryForList(
				"villageReportSummary.findReportSummaryForList", map,
				(page - 1) * rows, rows);
		PageInfo<ReportDataSummary> pageInfo = new PageInfo<ReportDataSummary>(
				page, rows, countNum, list);
		return pageInfo;
	}

	@Override
	public List<ReportDataSummary> findReportSummaryByIds(List<Long> idList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idList", idList);
		return getSqlMapClientTemplate().queryForList(
				"villageReportSummary.findReportSummaryByIds", map);
	}

	@Override
	public ReportDataSummary getReportSummaryById(Long id) {
		return (ReportDataSummary) getSqlMapClientTemplate().queryForObject(
				"villageReportSummary.getReportSummaryById", id);
	}

	@Override
	public Integer checkReportSummary(Long orgId, Integer year,
			Integer quarter, Integer isPlaning) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("year", year);
		map.put("quarter", quarter);
		map.put("isPlaning", isPlaning);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"villageReportSummary.countReportSummaryForList", map);
	}

	@Override
	public List<ReportDataSummary> countReportSummaryForList(Long orgId,
			Integer year, Integer quarter, Integer isPlaning) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("year", year);
		map.put("isPlaning", isPlaning);
		map.put("quarter", quarter);
		return getSqlMapClientTemplate().queryForList(
				"villageReportSummary.countAllReportSummaryForList", map);
	}

	@Override
	public List<ReportDataSummary> findReportSummaryByYear(Long orgId,
			Integer year, Integer isPlaning, boolean isIncludeCurrent) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("year", year);
		map.put("isIncludeCurrent", isIncludeCurrent);
		map.put("isPlaning", isPlaning);
		return getSqlMapClientTemplate().queryForList(
				"villageReportSummary.findReportSummaryByYear", map);
	}
}

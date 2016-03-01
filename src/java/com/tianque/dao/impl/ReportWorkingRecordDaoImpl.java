package com.tianque.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.ReportWorkingRecordDao;
import com.tianque.working.domain.ReportWorkingRecord;

@Repository("reportWorkingRecordDao")
public class ReportWorkingRecordDaoImpl extends AbstractBaseDao implements
		ReportWorkingRecordDao {

	@Override
	public ReportWorkingRecord addReportWorkingRecord(
			ReportWorkingRecord reportWorkingRecord) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"reportWorkingRecord.addReportWorkingRecord",
				reportWorkingRecord);
		return getReportWorkingRecordById(id);
	}

	@Override
	public ReportWorkingRecord getReportWorkingRecordById(Long id) {
		return (ReportWorkingRecord) getSqlMapClientTemplate().queryForObject(
				"reportWorkingRecord.getReportWorkingRecordById", id);
	}

	@Override
	public void deleteReportWorkingRecord(Long id) {
		getSqlMapClientTemplate().delete(
				"reportWorkingRecord.deleteReportWorkingRecordById", id);
	}

	@Override
	public PageInfo<ReportWorkingRecord> findReportWorkingRecordForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, String dailyDirectoryId) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orgId", orgId);
		map.put("dailyDirectoryId", dailyDirectoryId);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"reportWorkingRecord.countReportWorkingRecord", map);

		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<ReportWorkingRecord> list = getSqlMapClientTemplate()
				.queryForList("reportWorkingRecord.findReportWorkingRecord",
						map, (pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public ReportWorkingRecord updateReportWorkingRecord(
			ReportWorkingRecord reportWorkingRecord) {
		getSqlMapClientTemplate().update(
				"reportWorkingRecord.updateReportWorkingRecord",
				reportWorkingRecord);
		return getReportWorkingRecordById(reportWorkingRecord.getId());
	}

	private PageInfo<ReportWorkingRecord> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<ReportWorkingRecord> pageInfo = new PageInfo<ReportWorkingRecord>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	@Override
	public int updateSubmitState(Long id, Long submitStateId,
			Long expiredEntering) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("submitStateId", submitStateId);
		map.put("submitTime", ThreadVariable.getSession().getAccessTime());
		map.put("expiredEntering", expiredEntering);
		return getSqlMapClientTemplate().update(
				"reportWorkingRecord.updateSubmitState", map);
	}

	@Override
	public int backWorkingRecord(Long id, Long submitStateId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("submitStateId", submitStateId);
		map.put("updateDate", ThreadVariable.getSession().getAccessTime());
		map.put("backTime", ThreadVariable.getSession().getAccessTime());
		return getSqlMapClientTemplate().update(
				"reportWorkingRecord.backWorkingRecord", map);
	}

	@Override
	public ReportWorkingRecord findReportWorkingRecordByOrgIdAndDealDate(
			Long orgId, Date dealDate, Long dailyDirectoryId) {
		String updateDate = "";
		if (dealDate != null) {
			SimpleDateFormat timePattern = new SimpleDateFormat("yyyy-MM");
			updateDate = timePattern.format(dealDate);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("dealDate", updateDate);
		map.put("dailyDirectoryId", dailyDirectoryId);
		return (ReportWorkingRecord) getSqlMapClientTemplate()
				.queryForObject(
						"reportWorkingRecord.findReportWorkingRecordByOrgIdAndDealDate",
						map);
	}

	@Override
	public void deleteReportWorkingRecordByYearId(Long yearId) {
		getSqlMapClientTemplate()
				.delete("reportWorkingRecord.deleteReportWorkingRecordByYearId",
						yearId);
	}

	@Override
	public List<ReportWorkingRecord> findAllReportWorkingRecordByOrgIdAndYear(
			Long orgId, Date dealDate, Long dailyDirectoryId) {
		String updateDate = "";
		if (dealDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			updateDate = sdf.format(dealDate);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("dealDate", updateDate);
		map.put("dailyDirectoryId", dailyDirectoryId);
		List<ReportWorkingRecord> list = getSqlMapClientTemplate()
				.queryForList(
						"reportWorkingRecord.findAllReportWorkingRecordByOrgIdAndYear",
						map);
		return list;
	}

	@Override
	public List<ReportWorkingRecord> findReportWorkingRecordByStartDateAndEndDate(
			Long orgId, Long startDate, Long endDate, Long dailyDirectoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("orgId", orgId);
		map.put("dailyDirectoryId", dailyDirectoryId);
		List<ReportWorkingRecord> list = getSqlMapClientTemplate()
				.queryForList(
						"reportWorkingRecord.findReportWorkingRecordByStartDateAndEndDate",
						map);
		return list;
	}

	@Override
	public ReportWorkingRecord findSunReportWorkingRecordByOrgIdAndDealDate(
			Long submitType, Long orgId, Long reportTime, Long type,
			Long directoryReportTypeId, Long dailyYearId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("reportTime", reportTime);
		map.put("directoryReportTypeId", directoryReportTypeId);
		map.put("submitType", submitType);
		map.put("type", type);
		map.put("dailyYearId", dailyYearId);
		return (ReportWorkingRecord) getSqlMapClientTemplate()
				.queryForObject(
						"reportWorkingRecord.findSunReportWorkingRecordByOrgIdAndDealDate",
						map);
	}

	@Override
	public List<ReportWorkingRecord> findSunAllReportWorkingRecordByOrgIdAndYear(
			Long orgId, Long dailyYearId, Long type, Long directoryReportTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("dailyYearId", dailyYearId);
		map.put("type", type);
		map.put("directoryReportTypeId", directoryReportTypeId);
		map.put("order", "reportTime");
		List<ReportWorkingRecord> list = getSqlMapClientTemplate()
				.queryForList(
						"reportWorkingRecord.findSunAllReportWorkingRecordByOrgIdAndYear",
						map);
		return list;
	}

	@Override
	public ReportWorkingRecord summarizingJudge(Long reportTime,
			Long dailyDirectoryId, Long orgid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportTime", reportTime);
		map.put("dailyDirectoryId", dailyDirectoryId);
		map.put("orgId", orgid);
		return (ReportWorkingRecord) getSqlMapClientTemplate().queryForObject(
				"reportWorkingRecord.summarizingJudge", map);
	}

	@Override
	public List<ReportWorkingRecord> findSunAllReportWorkingRecordByOrgIdAndYearForQuert(
			Long orgId, Long dailyDirectoryId, Long type,
			Long directoryReportTypeId, Long dailyYearId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("dailyDirectoryId", dailyDirectoryId);
		map.put("type", type);
		map.put("dailyYearId", dailyYearId);
		map.put("directoryReportTypeId", directoryReportTypeId);
		map.put("order", "reportTime");
		List<ReportWorkingRecord> list = getSqlMapClientTemplate()
				.queryForList(
						"reportWorkingRecord.findSunAllReportWorkingRecordByOrgIdAndYearForQuert",
						map);
		return list;
	}

	@Override
	public List<ReportWorkingRecord> findReportWorkingRecordByOrgIdAndReportTime(
			Long orgId, Long reportTime, Long dailyDirectoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportTime", reportTime);
		map.put("dailyDirectoryId", dailyDirectoryId);
		map.put("orgId", orgId);
		return getSqlMapClientTemplate()
				.queryForList(
						"reportWorkingRecord.findReportWorkingRecordByOrgIdAndReportTime",
						map);
	}

	@Override
	public List<ReportWorkingRecord> findQuarterWorkingRecordByOrgIdAndReportTime(
			Long orgId, Long reportTime, Long dailyDirectoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportTime", reportTime);
		map.put("dailyDirectoryId", dailyDirectoryId);
		map.put("orgId", orgId);
		return getSqlMapClientTemplate()
				.queryForList(
						"reportWorkingRecord.findReportWorkingRecordByOrgIdAndReportTime",
						map);
	}

	@Override
	public List<ReportWorkingRecord> findReportWorkingRecord(Long orgId,
			Long dailyYearId, Long dailyDirectoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dailyYearId", dailyYearId);
		map.put("dailyDirectoryId", dailyDirectoryId);
		map.put("orgId", orgId);
		return getSqlMapClientTemplate().queryForList(
				"reportWorkingRecord.findReportWorkingRecords", map);
	}
}

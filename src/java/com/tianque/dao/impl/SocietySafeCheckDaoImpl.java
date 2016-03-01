package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.SocietySafeCheckDao;
import com.tianque.domain.SocietySafeCheck;

@Repository("societySafeCheckDao")
public class SocietySafeCheckDaoImpl extends AbstractBaseDao implements
		SocietySafeCheckDao {

	@Override
	public void addSocietySafeCheck(SocietySafeCheck societySafeCheck) {
		getSqlMapClientTemplate().insert(
				"societySafeCheck.addSocietySafeCheck", societySafeCheck);
	}

	@Override
	public void deleteSocietySafeCheckById(Long id) {
		getSqlMapClientTemplate().delete(
				"societySafeCheck.deleteSocietySafeCheckById", id);
	}

	@Override
	public void updateSocietySafeCheck(SocietySafeCheck societySafeCheck) {
		getSqlMapClientTemplate().update(
				"societySafeCheck.updateSocietySafeCheck", societySafeCheck);
	}

	@Override
	public SocietySafeCheck getSocietySafeCheckById(Long id) {
		return (SocietySafeCheck) getSqlMapClientTemplate().queryForObject(
				"societySafeCheck.getSocietySafeCheckById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SocietySafeCheck> findAllSocietySafeCheck() {
		return getSqlMapClientTemplate().queryForList(
				"societySafeCheck.findAllSocietySafeCheck");
	}

	@Override
	public SocietySafeCheck getSocietySafeCheckByDailyLogId(Long dailyLogId) {
		return (SocietySafeCheck) getSqlMapClientTemplate().queryForObject(
				"societySafeCheck.getSocietySafeCheckByDailyLogId", dailyLogId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SocietySafeCheck> findCollectionData(Integer reportState,
			String reportYear, Integer reportMonth, String orgCode,
			Integer reportType) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("reportState", reportState);
		map.put("reportYear", reportYear);
		map.put("reportMonth", reportMonth);
		map.put("orgCode", orgCode);
		map.put("reportType", reportType);
		return getSqlMapClientTemplate().queryForList(
				"societySafeCheck.findCollectionData", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SocietySafeCheck> findQuarterData(Integer reportState,
			String reportYear, Integer quarter, String orgCode) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("reportState", reportState);
		map.put("reportYear", reportYear);
		map.put("quarter", quarter);
		map.put("orgCode", orgCode);
		return getSqlMapClientTemplate().queryForList(
				"societySafeCheck.findQuarterData", map);
	}

	@Override
	public SocietySafeCheck getSocietySafeCheckBySignCode(String signCode) {
		return (SocietySafeCheck) getSqlMapClientTemplate().queryForObject(
				"societySafeCheck.getSocietySafeCheckBySignCode", signCode);
	}

	@Override
	public void backReport(Long id) {
		getSqlMapClientTemplate().update("societySafeCheck.backReport", id);
	}

	// 方法没有显式调用
	// @SuppressWarnings("unchecked")
	// @Override
	// public PageInfo<IssueSubmitInfoVo> findIssueSubmitInfo(Integer page,
	// Integer rows, Long orgId,
	// String reportYear, String directoryNameSign) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("orgId", orgId);
	// map.put("reportYear", reportYear);
	// map.put("directoryNameSign", directoryNameSign);
	// // map.put("nowMonth", CalendarUtil.getNowMonth());
	// Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
	// "societySafeCheck.issueSubmitInfoCount", map);
	// int pageCount = 0;
	// if (rows != 0 && countNum > 0)
	// pageCount = (countNum - 1) / rows + 1;
	// page = page > pageCount ? pageCount : page;
	// List<IssueSubmitInfoVo> list = getSqlMapClientTemplate().queryForList(
	// "societySafeCheck.findIssueSubmitInfo", map, (page - 1) * rows, rows);
	// PageInfo<IssueSubmitInfoVo> pageInfo = new PageInfo<IssueSubmitInfoVo>();
	// pageInfo.setResult(list);
	// pageInfo.setTotalRowSize(countNum);
	// pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
	// pageInfo.setPerPageSize(rows);
	// return pageInfo;
	// }

}

package com.tianque.mobileErrorLogs.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.vo.PageInfo;
import com.tianque.dao.AbstractBaseDao;
import com.tianque.domain.Role;
import com.tianque.mobileErrorLogs.domain.MobileErrorLogs;
import com.tianque.plugin.taskList.domain.PositiveInfoRecord;

/**
 * @author weiminglong
 *2016年1月7日下午2:43:44
 */
@Repository("mobileErrorLogsDao")
public class MobileErrorLogsDaoImpl extends AbstractBaseDao implements MobileErrorLogsDao{

	@Override
	public MobileErrorLogs addMobileErrorLogs(MobileErrorLogs mobileErrorLogs) {
		getSqlMapClientTemplate().insert(
				"mobileErrorLogs.addMobileErrorLogs", mobileErrorLogs);
		return mobileErrorLogs;
	}

	@Override
	public PageInfo<MobileErrorLogs> findMobileErrorLogs(
			MobileErrorLogs mobileErrorLogs, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", mobileErrorLogs.getOrgCode());
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"mobileErrorLogs.countMobileErrorLogs", map);
		List<MobileErrorLogs> list = new ArrayList<MobileErrorLogs>();
		if (pageNum == null || pageSize == null) {
			list = getSqlMapClientTemplate().queryForList(
					"mobileErrorLogs.findMobileErrorLogs", map);
			return new PageInfo<MobileErrorLogs>(1, countNum, countNum, list);
		} else {
			list = getSqlMapClientTemplate().queryForList(
					"mobileErrorLogs.findMobileErrorLogs", map,(pageNum - 1) * pageSize,
					pageSize);
			return new PageInfo<MobileErrorLogs>(pageNum, pageSize, countNum, list);
		}
	}

	@Override
	public PageInfo<MobileErrorLogs> advancedSearchMobileErrorLogs(
			MobileErrorLogs mobileErrorLogs, Integer page, Integer rows,
			String sidx, String sord) {
	if (mobileErrorLogs == null) {
		return emptyRolePage(rows);
	}
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("name", mobileErrorLogs.getName());
	map.put("errorLogsName", mobileErrorLogs.getErrorLogsName());
	map.put("occurDate", mobileErrorLogs.getOccurDate());
	map.put("occurFromDate", mobileErrorLogs.getOccurFromDate());
	map.put("occurEndDate", mobileErrorLogs.getOccurEndDate());
	map.put("orgCode", mobileErrorLogs.getOrgCode());
	PageInfo<MobileErrorLogs> pageInfo = new PageInfo<MobileErrorLogs>();
	Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
			"mobileErrorLogs.countSearchMobileErrorLogs", map);
	int pageCount = 0;
	if (countNum / rows == 0) {
		pageCount = countNum / rows;
	} else {
		pageCount = countNum / rows + 1;
	}
	page = page > pageCount ? pageCount : page;
	if (countNum > 0) {
		List<MobileErrorLogs> list = getSqlMapClientTemplate().queryForList("mobileErrorLogs.searchMobileErrorLogs", map,
				(page - 1) * rows, rows);
		pageInfo.setResult(list);
	} else {
		pageInfo.setResult(new ArrayList<MobileErrorLogs>());
	}
	pageInfo.setTotalRowSize(countNum);
	pageInfo.setPerPageSize(rows);
	pageInfo.setCurrentPage(page);
	return pageInfo;
}
 private PageInfo<MobileErrorLogs> emptyRolePage(int rows) {
		PageInfo<MobileErrorLogs> pageInfo = new PageInfo<MobileErrorLogs>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(rows);
		pageInfo.setResult(new ArrayList<MobileErrorLogs>());
		return pageInfo;
	}
}
package com.tianque.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.working.dao.DailyYearDao;
import com.tianque.working.domain.DailyYear;

@Repository("dailyYearDao")
public class DailyYearDaoImpl extends AbstractBaseDao implements DailyYearDao {

	@Override
	public DailyYear addDailyYear(DailyYear dailyYear) {
		if (!validate(dailyYear))
			throw new BusinessValidationException();

		Long id = (Long) getSqlMapClientTemplate().insert(
				"dailyYear.addDailyYear", dailyYear);
		return getSimpleDailyYearById(id);
	}

	@Override
	public List<DailyYear> findDailyYearList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", "name");
		map.put("order", "desc");
		return getSqlMapClientTemplate().queryForList(
				"dailyYear.findDailyYears", map);
	}

	private boolean validate(DailyYear dailyYear) {
		if (dailyYear == null)
			return false;
		if (dailyYear.getName() == null || "".equals(dailyYear.getName()))
			return false;
		return true;
	}

	@Override
	public DailyYear getSimpleDailyYearById(Long id) {
		return (DailyYear) getSqlMapClientTemplate().queryForObject(
				"dailyYear.getSimpleDailyYearById", id);
	}

	@Override
	public PageInfo<DailyYear> findDailyYearForPageByOrgId(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		Integer countNum = 0;
		countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"dailyYear.countDailyYearsByOrgId", map);
		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<DailyYear> list = getSqlMapClientTemplate().queryForList(
				"dailyYear.findDailyYearsByOrgId", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	private PageInfo<DailyYear> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<DailyYear> pageInfo = new PageInfo<DailyYear>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public List<DailyYear> findDailyYearByName(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		return getSqlMapClientTemplate().queryForList(
				"dailyYear.findDailyYears", map);
	}

	@Override
	public List<DailyYear> findDailyYearByOrgId(Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		return getSqlMapClientTemplate().queryForList(
				"dailyYear.findDailyYears", map);
	}

	@Override
	public List<DailyYear> findDailyYearsByOrgIdAndStatus(Long orgId,
			Long status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("status", status);
		return getSqlMapClientTemplate().queryForList(
				"dailyYear.findDailyYearsByOrgIdAndStatus", map);
	}

	@Override
	public DailyYear getInitYear() {
		return (DailyYear) getSqlMapClientTemplate().queryForObject(
				"dailyYear.getInitYear");
	}

	@Override
	public DailyYear findDailyYearByParentOrgIdAndYear(Long orgId, String name) {
		Map map = new HashMap();
		map.put("orgId", orgId);
		map.put("name", name);
		return (DailyYear) getSqlMapClientTemplate().queryForObject(
				"dailyYear.findDailyYearByParentOrgIdAndYear", map);
	}

	@Override
	public void deleteDailyYear(Long yearId) {
		getSqlMapClientTemplate().delete("dailyYear.deleteDailyYearById",
				yearId);
	}

	@Override
	public DailyYear updateDailyYear(DailyYear dailyYear) {
		int id = getSqlMapClientTemplate().update("dailyYear.updateDailyYear",
				dailyYear);
		if (id > 0) {
			return this.getSimpleDailyYearById(dailyYear.getId());
		}
		return new DailyYear();
	}

	@Override
	public DailyYear startAndStopDailyYearById(DailyYear dailyYear) {
		if (dailyYear == null || dailyYear.getId() == null) {
			throw new BusinessValidationException();
		}
		getSqlMapClientTemplate().update("dailyYear.startAndStopDailyYearById",
				dailyYear);
		return this.getSimpleDailyYearById(dailyYear.getId());
	}

	@Override
	public int countDailyYearByOrgIdAndYear(Long orgId, Integer year) {
		if (orgId == null || year == null) {
			throw new BusinessValidationException();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("yearDate", year);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"dailyYear.countDailyYearByOrgIdAndYear", map);
	}

	@Override
	public DailyYear getDailyYearByOrgIdAndYear(Long currentOrgId, Integer year) {
		if (null == currentOrgId || null == year) {
			throw new BusinessValidationException();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", currentOrgId);
		map.put("year", year);
		return (DailyYear) getSqlMapClientTemplate().queryForObject(
				"dailyYear.getDailyYearByOrgIdAndYear", map);
	}

	@Override
	public DailyYear setWhetherAutoStartDailyYear(DailyYear dailyYear) {
		int id = getSqlMapClientTemplate().update(
				"dailyYear.setWhetherAutoStartDailyYear", dailyYear);
		if (id > 0) {
			return this.getSimpleDailyYearById(dailyYear.getId());
		}
		return new DailyYear();
	}

	@Override
	public List<DailyYear> findAutoStartDailyYear() {
		Date sysDate = new Date();
		return getSqlMapClientTemplate().queryForList(
				"dailyYear.findAutoStartDailyYear", sysDate);
	}

	@Override
	public List<DailyYear> findManuallyStartDailyYear() {
		Date sysDate = new Date();
		return getSqlMapClientTemplate().queryForList(
				"dailyYear.findManuallyStartDailyYear", sysDate);
	}
}

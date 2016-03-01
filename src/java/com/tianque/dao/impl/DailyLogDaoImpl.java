package com.tianque.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.IssueInspect;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.IssueSubmitInfoVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.working.dao.DailyLogDao;
import com.tianque.working.domain.DailyLog;

@Repository("dailyLogDao")
public class DailyLogDaoImpl extends AbstractBaseDao implements DailyLogDao {

	@Override
	public DailyLog addDailyLog(DailyLog dailyLog) {
		if (!validate(dailyLog))
			throw new BusinessValidationException("参数错误！");
		Long id = (Long) getSqlMapClientTemplate().insert(
				"dailyLog.addDailyLog", dailyLog);
		return getSimpleDailyLogById(id);
	}

	@Override
	public void deleteDailyLogById(Long id) {
		getSqlMapClientTemplate().delete("dailyLog.deleteDailyLogById", id);
	}

	@Override
	public PageInfo<DailyLog> findDailyLogByDailyDirectoryId(Long id,
			Long organizationId, Integer page, Integer rows, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dailyDirectoryId", id);
		map.put("organizationId", organizationId);
		map.put("sortField", sidx);
		map.put("order", sord);

		return searchDailyLog(map, page, rows, "dailyLog.countDailyLogs",
				"dailyLog.findDailyLogs");
	}

	@Override
	public List<IssueInspect> findDetailIssueInspect(String orglevelcode,
			String begindate, String enddate) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orginternalcode", orglevelcode);
		map.put("begindate", begindate);
		map.put("enddate", enddate);
		List<IssueInspect> list = getSqlMapClientTemplate().queryForList(
				"issueInspect.findIssueInspects", map);
		return list;
	}

	@Override
	public PageInfo<IssueSubmitInfoVo> findIssueSubmitInfo(Integer page,
			Integer rows, String orgcode, String iyear) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgcode", orgcode);
		map.put("iyear", iyear);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"issueInspect.issueSubmitInfoCount", map);
		int pageCount = 0;
		if (rows != 0 && countNum > 0)
			pageCount = (countNum - 1) / rows + 1;
		page = page > pageCount ? pageCount : page;
		List<IssueSubmitInfoVo> list = getSqlMapClientTemplate().queryForList(
				"issueInspect.findIssueSubmitInfo", map, (page - 1) * rows,
				rows);
		PageInfo<IssueSubmitInfoVo> pageInfo = new PageInfo<IssueSubmitInfoVo>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	public List<IssueInspect> findDetailIssueInspectForQuarter(
			String orglevelcode, String begindate, String enddate) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orginternalcode", orglevelcode);
		map.put("begindate", begindate);
		map.put("enddate", enddate);
		List<IssueInspect> list = getSqlMapClientTemplate().queryForList(
				"issueInspect.findIssueInspectsForQuarter", map);
		return list;
	}

	public List<IssueInspect> findSumIssueInspectByRepdate(String orglevelcode,
			String issueID) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("union", orglevelcode);
		map.put("repdate", issueID.substring(0, 4));
		List<IssueInspect> list = getSqlMapClientTemplate().queryForList(
				"issueInspect.findIssueInspectsFullYearByRepdate", map);
		return list;
	}

	public List<IssueInspect> findSumIssueInspectByRepdateForQuarter(
			String orglevelcode, String issueID) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("union", orglevelcode);
		map.put("repdate", issueID.substring(0, 4));
		List<IssueInspect> list = getSqlMapClientTemplate().queryForList(
				"issueInspect.findIssueInspectsFullYearByRepdateForQuarter",
				map);
		return list;
	}

	@Override
	public List<Map> findDetailIssueInspectByID(long issueID) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("inssueid", issueID);
		List<Map> list = getSqlMapClientTemplate().queryForList(
				"issueInspect.findIssueInspectsByid", map);
		return list;
	}

	public List<Map> findSumIssueInspectByID(IssueInspect issueInspect) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id", issueInspect.getId());
		map.put("issuelevel", issueInspect.getIssueLevel());
		List<Map> list = getSqlMapClientTemplate().queryForList(
				"issueInspect.findIssueInspectsFullYearByid", map);
		return list;
	}

	public List<IssueInspect> findDetailIssueInspectByDeptLevel(String issueID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", issueID);
		List<IssueInspect> list = getSqlMapClientTemplate().queryForList(
				"issueInspect.findIssueInspectsByLevel", map);
		return list;
	}

	public IssueInspect findDetailIssueInspectById(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		IssueInspect issueInspect = (IssueInspect) getSqlMapClientTemplate()
				.queryForObject("issueInspect.findIssuesById", map);
		return issueInspect;
	}
	
	@Override
	public List<Long> findSubmitedAreaOrgIds(DailyLog dailyLog) {
		Map<String, Object> map = new HashMap<String, Object>();
		String updateDate = "";
		if (dailyLog.getDealDate() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			updateDate = sdf.format(dailyLog.getDealDate());
		}
		map.put("updatedate", updateDate);
		return getSqlMapClientTemplate().queryForList(
				"dailyLog.findSubmitedAreaOrgIdsByUpdatedate", map);
	}

	public void saveIssueDetail(Map map) {
		getSqlMapClientTemplate().insert("issueInspect.saveIssueeDetails", map);
	}

	public void deleteIssueInspect(long id) {
		getSqlMapClientTemplate().delete("issueInspect.deleteIssueInspect", id);
	}

	public void deleteIssueDetailByIssueId(long id) {

		getSqlMapClientTemplate().delete(
				"issueInspect.deleteIssueDetailByIssueDetailId", id);
	}

	public void updateIssueByDetl(long id, long state, String auditperson,
			String createperson) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("id", id);
		map.put("summitstate", state);
		map.put("submitdate", new Date());
		map.put("auditperson", auditperson);
		map.put("createperson", createperson);
		getSqlMapClientTemplate().update("issueInspect.updateIssueState", map);
	}

	public void updateIssueStateByID(long id, long state) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("id", id);
		map.put("summitstate", state);
		getSqlMapClientTemplate().update("issueInspect.updateIssueStateByID",
				map);
	}

	public Integer countUnderLineAreaNotSubmit(IssueInspect issueInspect) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgcode", issueInspect.getCreaterepUnion());
		map.put("createDate", issueInspect.getCreaterepDate().substring(0, 7));
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"issueInspect.countNotSubmitArea", map);
		return countNum;
	}

	public IssueInspect addIssueInspect(IssueInspect inspect) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("createrepunion", inspect.getCreaterepUnion());
		map.put("createrepdate", inspect.getCreaterepDate());
		map.put("createrepperson", inspect.getCreaterepPerson());
		map.put("submitstate", inspect.getSubmitState());
		map.put("issuelevel", inspect.getIssueLevel());
		map.put("dailydirectoryid", inspect.getDailyDirectoryId());
		Long id = (Long) getSqlMapClientTemplate().insert(
				"issueInspect.addIssueInspect", map);
		return findDetailIssueInspectById(id);
	}

	public void updateIssueInspect(IssueInspect inspect) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("createrepdate", inspect.getCreaterepDate());
		map.put("createrepperson", inspect.getCreaterepPerson());
		map.put("id", inspect.getId());
		getSqlMapClientTemplate()
				.insert("issueInspect.updateIssueInspect", map);
	}

	@Override
	public DailyLog getSimpleDailyLogById(Long id) {
		DailyLog dailyLog = null;
		dailyLog = (DailyLog) getSqlMapClientTemplate().queryForObject(
				"dailyLog.getSimpleDailyLogById", id);
		return dailyLog;
	}

	@Override
	public DailyLog updateDailyLog(DailyLog dailyLog) {
		if (!validate(dailyLog))
			throw new BusinessValidationException("参数错误！");
		getSqlMapClientTemplate().update("dailyLog.updateDailyLog", dailyLog);
		return getSimpleDailyLogById(dailyLog.getId());
	}

	private boolean validate(DailyLog dailyLog) {
		if (dailyLog == null)
			return false;
		if (dailyLog.getName() == null || "".equals(dailyLog.getName().trim()))
			return false;
		if (dailyLog.getDailyDirectory() == null)
			return false;
		if (dailyLog.getDealDate() == null)
			return false;
		if (dailyLog.getDailyYear() == null)
			return false;
		if (dailyLog.getOrganization() == null)
			return false;
		if (dailyLog.getOrgInternalCode() == null
				|| "".equals(dailyLog.getName().trim()))
			return false;
		return true;
	}

	@Override
	public PageInfo<DailyLog> filterDailyLogByDate(Long id,
			Long organizationId, Date dealFrom, Date dealTo, Integer page,
			Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dailyDirectoryId", id);
		map.put("dealFrom", dealFrom);
		map.put("organizationId", organizationId);
		map.put("dealTo", dealTo);
		map.put("sortField", sidx);
		map.put("order", sord);

		return searchDailyLog(map, page, rows, "dailyLog.countDailyLogs",
				"dailyLog.findDailyLogs");
	}

	@Override
	public PageInfo<DailyLog> filterDailyLogByDateAndDailyDirectoryParentId(
			Long directoryParentId, Long organizationId, Date dealFrom,
			Date dealTo, Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentDailyDirectoryId", directoryParentId);
		map.put("dealFrom", dealFrom);
		map.put("organizationId", organizationId);
		map.put("dealTo", dealTo);
		map.put("sortField", sidx);
		map.put("order", sord);

		return searchDailyLog(map, page, rows, "dailyLog.countParentDailyLogs",
				"dailyLog.findParentDailyLogs");
	}

	@Override
	public PageInfo<IssueInspect> findIssueInspect(Integer page, Integer rows,
			IssueInspect orgid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issuelevel", orgid.getIssueLevel());
		map.put("orgid", orgid.getCreaterepUnion());
		map.put("dailydirectoryid", orgid.getDailyDirectoryId());
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"issueInspect.countIssues", map);
		int pageCount = 0;
		if (rows != 0 && countNum > 0)
			pageCount = (countNum - 1) / rows + 1;
		page = page > pageCount ? pageCount : page;
		List<IssueInspect> list = getSqlMapClientTemplate().queryForList(
				"issueInspect.findIssues", map, (page - 1) * rows, rows);
		PageInfo<IssueInspect> pageInfo = new PageInfo<IssueInspect>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	public PageInfo<IssueInspect> findIssueInspectByDate(Integer page,
			Integer rows, IssueInspect issue, Organization organization,
			String dealfrom, String dealto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issuelevel",
				issue.getIssueLevel() == 0 ? null : issue.getIssueLevel());
		map.put("dealfrom", dealfrom);
		map.put("dealto", dealto);
		map.put("orgid", organization.getId());
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"issueInspect.countIssuesByDate", map);
		int pageCount = 0;
		if (rows != 0 && countNum > 0)
			pageCount = (countNum - 1) / rows + 1;
		page = page > pageCount ? pageCount : page;
		List<IssueInspect> list = getSqlMapClientTemplate().queryForList(
				"issueInspect.findIssuesByDate", map, (page - 1) * rows, rows);
		PageInfo<IssueInspect> pageInfo = new PageInfo<IssueInspect>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	private PageInfo<DailyLog> searchDailyLog(Map<String, Object> map,
			Integer page, Integer rows, String countSql, String pageSql) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				countSql, map);
		int pageCount = 0;
		if (rows != 0 && countNum > 0)
			pageCount = (countNum - 1) / rows + 1;
		page = page > pageCount ? pageCount : page;
		List<DailyLog> list = getSqlMapClientTemplate().queryForList(pageSql,
				map, (page - 1) * rows, rows);
		PageInfo<DailyLog> pageInfo = new PageInfo<DailyLog>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<DailyLog> findDailyLogByParentDailyDirectoryId(Long id,
			Long organizationId, Integer page, Integer rows, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentDailyDirectoryId", id);
		map.put("organizationId", organizationId);
		map.put("sortField", sidx);
		map.put("order", sord);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"dailyLog.countParentDailyLogs", map);
		int pageCount = 0;
		if (rows != 0 && countNum > 0)
			pageCount = (countNum - 1) / rows + 1;
		page = page > pageCount ? pageCount : page;
		List<DailyLog> list = getSqlMapClientTemplate().queryForList(
				"dailyLog.findParentDailyLogs", map, (page - 1) * rows, rows);
		PageInfo<DailyLog> pageInfo = new PageInfo<DailyLog>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<IssueInspect> findIssueInspectsByParentId(Long id,
			Long organizationId, Integer page, Integer rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentid", id);
		map.put("orgid", organizationId);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"issueInspect.countfindIssuesBydirPrentid", map);
		int pageCount = 0;
		if (rows != 0 && countNum > 0)
			pageCount = (countNum - 1) / rows + 1;
		page = page > pageCount ? pageCount : page;
		List<IssueInspect> list = getSqlMapClientTemplate().queryForList(
				"issueInspect.findIssuesBydirPrentid", map, (page - 1) * rows,
				rows);
		PageInfo<IssueInspect> pageInfo = new PageInfo<IssueInspect>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public int countExsistedDailyLogByDirectoryId(Long directoryId) {
		if (directoryId == null) {
			throw new BusinessValidationException("参数不正确");
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"dailyLog.countExsistedDailyLogByDirectoryId", directoryId);
	}

	@Override
	public List<DailyLog> findDailyLogByDailyDirectoryId(Long directoryId,
			Long organizationId) {
		Map map = new HashMap();
		map.put("dailyDirectoryId", directoryId);
		map.put("organizationId", organizationId);
		return getSqlMapClientTemplate().queryForList("dailyLog.findDailyLogs",
				map);
	}

	@Override
	public List<DailyLog> findDailyLogsByDetailedRuleId(Long detailedRuleId) {
		return getSqlMapClientTemplate().queryForList(
				"dailyLog.findDailyLogsByDetailedRuleId", detailedRuleId);
	}

	@Override
	public void deleteDailyLogsByYearId(Long yearId) {
		getSqlMapClientTemplate().delete("dailyLog.deleteDailyLogByYearId",
				yearId);
	}

}

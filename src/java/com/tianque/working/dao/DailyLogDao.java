package com.tianque.working.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.IssueInspect;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.IssueSubmitInfoVo;
import com.tianque.working.domain.DailyLog;

public interface DailyLogDao {

	public DailyLog addDailyLog(DailyLog dailyLog);

	public DailyLog updateDailyLog(DailyLog dailyLog);

	public void deleteDailyLogById(Long id);

	public DailyLog getSimpleDailyLogById(Long id);

	public PageInfo<DailyLog> findDailyLogByDailyDirectoryId(Long id, Long organizationId,
			Integer page, Integer rows, String sidx, String sord);

	public List<IssueInspect> findDetailIssueInspect(String orglevelcode, String begindate,
			String enddate);

	public List<IssueInspect> findDetailIssueInspectForQuarter(String orglevelcode,
			String begindate, String enddate);

	public List<IssueInspect> findDetailIssueInspectByDeptLevel(String issueID);

	public PageInfo<IssueSubmitInfoVo> findIssueSubmitInfo(Integer page, Integer rows,
			String orgcode, String iyear);

	public void saveIssueDetail(Map map);

	public void deleteIssueInspect(long id);

	public void updateIssueStateByID(long id, long state);

	public Integer countUnderLineAreaNotSubmit(IssueInspect issueInspect);

	public void updateIssueByDetl(long id, long state, String auditperson, String createperson);

	public IssueInspect addIssueInspect(IssueInspect inspect);

	public void updateIssueInspect(IssueInspect inspect);

	public void deleteIssueDetailByIssueId(long id);

	public List<Map> findDetailIssueInspectByID(long issueID);

	public List<IssueInspect> findSumIssueInspectByRepdate(String orglevelcode, String issueID);

	public List<IssueInspect> findSumIssueInspectByRepdateForQuarter(String orglevelcode,
			String issueID);

	public List<Map> findSumIssueInspectByID(IssueInspect issueInspect);

	public PageInfo<IssueInspect> findIssueInspect(Integer page, Integer rows, IssueInspect orgid);

	public PageInfo<IssueInspect> findIssueInspectByDate(Integer page, Integer rows,
			IssueInspect issue, Organization organization, String dealfrom, String dealto);

	public PageInfo<DailyLog> filterDailyLogByDate(Long id, Long organizationId, Date dealFrom,
			Date dealTo, Integer page, Integer rows, String sidx, String sord);

	public PageInfo<DailyLog> findDailyLogByParentDailyDirectoryId(Long id, Long organizationId,
			Integer page, Integer rows, String sidx, String sord);

	public PageInfo<IssueInspect> findIssueInspectsByParentId(Long id, Long organizationId,
			Integer page, Integer rows);

	public int countExsistedDailyLogByDirectoryId(Long directoryId);

	public List<DailyLog> findDailyLogByDailyDirectoryId(Long directoryId, Long organizationId);

	public PageInfo<DailyLog> filterDailyLogByDateAndDailyDirectoryParentId(Long directoryParentId,
			Long organizationId, Date dealFrom, Date dealTo, Integer page, Integer rows,
			String sidx, String sord);

	public List<DailyLog> findDailyLogsByDetailedRuleId(Long detailedRuleId);

	public void deleteDailyLogsByYearId(Long yearId);

	public List<Long> findSubmitedAreaOrgIds(DailyLog dailyLog);

}

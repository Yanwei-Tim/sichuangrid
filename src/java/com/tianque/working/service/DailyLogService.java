package com.tianque.working.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.IssueInspect;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.IssueSubmitInfoVo;
import com.tianque.working.domain.DailyLog;

public interface DailyLogService {

	public DailyLog addDailyLog(DailyLog dailyLog);

	public DailyLog updateDailyLog(DailyLog dailyLog);

	public void deleteDailyLogById(Long id);

	public DailyLog getSimpleDailyLogById(Long id);

	public PageInfo<DailyLog> findDailyLogByDailyDirectoryId(Long id, Long organizationId,
			Integer page, Integer rows, String sidx, String sord);

	public PageInfo<DailyLog> findDailyLogByParentDailyDirectoryId(Long id, Long organizationId,
			Integer page, Integer rows, String sidx, String sord);

	public PageInfo<IssueInspect> findIssueInspectsByParentId(Long id, Long organizationId,
			Integer page, Integer rows);

	public PageInfo<IssueSubmitInfoVo> findIssueSubmitInfo(Integer page, Integer rows,
			String orgcode, String iyear);

	public List<IssueInspect> findDetailIssueInspect(String begindate, String enddate,
			String unioncode);

	public List<IssueInspect> findDetailIssueInspectForQuarter(String begindate, String enddate,
			String unioncode);

	public void deleteIssueInspect(long id);

	public void saveIssueDetail(List details, long issueid, long state, String auditperson,
			String createperson);

	public IssueInspect addIssueInspect(IssueInspect inspect);

	public void updateIssueInspect(IssueInspect inspect);

	public Integer countUnderLineAreaNotSubmit(IssueInspect issueInspect);

	public void updateIssueStateByID(long id, long state);

	public List<Map> findDetailIssueInspectByID(IssueInspect issueInspect);

	public PageInfo<IssueInspect> findIssueInspect(Integer page, Integer rows, IssueInspect issue);

	public PageInfo<IssueInspect> findIssueInspectByDate(Integer page, Integer rows,
			IssueInspect issue, Organization organization, String dealfrom, String dealto);

	public PageInfo<DailyLog> filterDailyLogByDate(Long id, Long organizationId, Date dealFrom,
			Date dealTo, Integer page, Integer rows, String sidx, String sord);

	public int countExsistedDailyLogByDirectoryId(Long directoryId);

	public PageInfo<DailyLog> filterDailyLogByDateAndDailyDirectoryParentId(Long directoryParentId,
			Long organizationId, Date dealFrom, Date dealTo, Integer page, Integer rows,
			String sidx, String sord);

	public Integer countUnderLineAreaNotSubmit(DailyLog dailyLog);

}

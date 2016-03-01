package com.tianque.working.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.IssueInspect;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.vo.IssueSubmitInfoVo;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.ParametersConvertUtil;
import com.tianque.working.dao.DailyLogDao;
import com.tianque.working.domain.DailyLog;
import com.tianque.working.service.DailyLogService;

@Service("dailyLogService")
@Transactional
public class DailyLogServiceImpl extends AbstractBaseService implements
		DailyLogService {

	@Autowired
	private DailyLogDao dailyLogDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public DailyLog addDailyLog(DailyLog dailyLog) {
		if (!validate(dailyLog))
			throw new BusinessValidationException();
		dailyLog.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
				dailyLog.getOrganization().getId()).getOrgInternalCode());
		return dailyLogDao.addDailyLog(dailyLog);
	}

	@Override
	public void deleteDailyLogById(Long id) {
		dailyLogDao.deleteDailyLogById(id);
	}

	@Override
	public PageInfo<DailyLog> findDailyLogByDailyDirectoryId(Long id,
			Long organizationId, Integer page, Integer rows, String sidx,
			String sord) {
		return dailyLogDao.findDailyLogByDailyDirectoryId(id, organizationId,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<DailyLog> findDailyLogByParentDailyDirectoryId(Long id,
			Long organizationId, Integer page, Integer rows, String sidx,
			String sord) {
		return dailyLogDao.findDailyLogByParentDailyDirectoryId(id,
				organizationId, page, rows, sidx, sord);
	}

	public PageInfo<IssueInspect> findIssueInspectsByParentId(Long id,
			Long organizationId, Integer page, Integer rows) {
		return dailyLogDao.findIssueInspectsByParentId(id, organizationId,
				page, rows);
	}

	@Override
	public PageInfo<IssueInspect> findIssueInspect(Integer page, Integer rows,
			IssueInspect issue) {
		return dailyLogDao.findIssueInspect(page, rows, issue);
	}

	public PageInfo<IssueInspect> findIssueInspectByDate(Integer page,
			Integer rows, IssueInspect issue, Organization organization,
			String dealfrom, String dealto) {
		return dailyLogDao.findIssueInspectByDate(page, rows, issue,
				organization, dealfrom, dealto);
	}

	@Override
	public List<IssueInspect> findDetailIssueInspect(String begindate,
			String enddate, String unioncode) {
		List<IssueInspect> issues = dailyLogDao.findDetailIssueInspect(
				unioncode, begindate, enddate);
		issues.addAll(dailyLogDao.findSumIssueInspectByRepdate(unioncode,
				begindate));
		return issues;
	}

	public List<IssueInspect> findDetailIssueInspectForQuarter(
			String begindate, String enddate, String unioncode) {
		List<IssueInspect> issues = dailyLogDao
				.findDetailIssueInspectForQuarter(unioncode, begindate, enddate);
		issues.addAll(dailyLogDao.findSumIssueInspectByRepdateForQuarter(
				unioncode, begindate));
		return issues;
	}

	public PageInfo<IssueSubmitInfoVo> findIssueSubmitInfo(Integer page,
			Integer rows, String orgcode, String iyear) {
		return dailyLogDao.findIssueSubmitInfo(page, rows, orgcode, iyear);
	}

	public void deleteIssueInspect(long id) {
		dailyLogDao.deleteIssueInspect(id);
		dailyLogDao.deleteIssueDetailByIssueId(id);
	}

	public void saveIssueDetail(List details, long issueid, long state,
			String auditperson, String createperson) {
		dailyLogDao.deleteIssueDetailByIssueId(issueid);
		dailyLogDao
				.updateIssueByDetl(issueid, state, auditperson, createperson);
		for (int i = 0; i < details.size(); i++) {
			Map map = (Map) details.get(i);
			dailyLogDao.saveIssueDetail(map);
		}

	}

	public void updateIssueStateById(long id, long state, String auditperson,
			String createperson) {
		dailyLogDao.updateIssueByDetl(id, state, auditperson, createperson);
	}

	public Integer countUnderLineAreaNotSubmit(IssueInspect issueInspect) {
		return dailyLogDao.countUnderLineAreaNotSubmit(issueInspect);
	}

	public void updateIssueStateByID(long id, long state) {
		dailyLogDao.updateIssueStateByID(id, state);
	}

	public IssueInspect addIssueInspect(IssueInspect inspect) {
		Session session = (Session) this.getCurrentUser();
		String orgInternalCode = session.getOrgInternalCode();
		inspect.setCreaterepUnion(orgInternalCode);
		return dailyLogDao.addIssueInspect(inspect);
	}

	public void updateIssueInspect(IssueInspect inspect) {
		Session session = (Session) this.getCurrentUser();
		String orgInternalCode = session.getOrgInternalCode();
		inspect.setCreaterepUnion(orgInternalCode);
		dailyLogDao.updateIssueInspect(inspect);
	}

	@Override
	public List<Map> findDetailIssueInspectByID(IssueInspect issueInspect) {
		List<Map> issues = dailyLogDao.findDetailIssueInspectByID(issueInspect
				.getId());
		issues.addAll(dailyLogDao.findSumIssueInspectByID(issueInspect));
		return issues;
	}

	@Override
	public DailyLog getSimpleDailyLogById(Long id) {
		return dailyLogDao.getSimpleDailyLogById(id);
	}

	@Override
	public DailyLog updateDailyLog(DailyLog dailyLog) {
		if (!validate(dailyLog))
			throw new BusinessValidationException();
		dailyLog.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
				dailyLog.getOrganization().getId()).getOrgInternalCode());
		return dailyLogDao.updateDailyLog(dailyLog);
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
		return true;
	}

	@Override
	public PageInfo<DailyLog> filterDailyLogByDate(Long id,
			Long organizationId, Date dealFrom, Date dealTo, Integer page,
			Integer rows, String sidx, String sord) {
		return dailyLogDao.filterDailyLogByDate(id, organizationId, dealFrom,
				dealTo, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<DailyLog> filterDailyLogByDateAndDailyDirectoryParentId(
			Long directoryParentId, Long organizationId, Date dealFrom,
			Date dealTo, Integer page, Integer rows, String sidx, String sord) {

		return dailyLogDao.filterDailyLogByDateAndDailyDirectoryParentId(
				directoryParentId, organizationId, dealFrom, dealTo, page,
				rows, sidx, sord);
	}

	@Override
	public int countExsistedDailyLogByDirectoryId(Long directoryId) {
		if (directoryId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return dailyLogDao.countExsistedDailyLogByDirectoryId(directoryId);
	}

	public Integer countUnderLineAreaNotSubmit(DailyLog dailyLog) {
		List<Long> orgIdList = dailyLogDao.findSubmitedAreaOrgIds(dailyLog);
		if(orgIdList!=null && orgIdList.size()>0){
			OrganizationVo organizationVo = new OrganizationVo();
			organizationVo.setOrgIdsList(ParametersConvertUtil.convertToListString(orgIdList));
			organizationVo.setOrgInternalCode(dailyLog.getOrgInternalCode());
			return organizationDubboService.countOrgByOrgIdsListAndResidentStatusDict(organizationVo);
		}
		return 0;
	}
}

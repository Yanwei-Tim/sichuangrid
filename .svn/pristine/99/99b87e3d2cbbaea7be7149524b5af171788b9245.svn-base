package com.tianque.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.dao.IssueLogDaoNew;
import com.tianque.dao.IssueStatueStanalDao;
import com.tianque.dao.IssueTypeStanalDao;
import com.tianque.domain.IssueStatueStanal;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.service.IssueStatueStanalService;
import com.tianque.service.WorkCalendarService;
import com.tianque.state.IssueDealState;
import com.tianque.state.IssueDealType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.IssueLogDealOverTimeHelper;

@Service("issueStatueStanalService")
@Scope("prototype")
public class IssueStatueStanalServiceImpl extends AbstractBaseService implements
		IssueStatueStanalService {
	@Autowired
	private IssueStatueStanalDao issueStatueStanalDao;
	@Autowired
	private IssueTypeStanalDao issueTypeStanalDao;
	@Autowired
	private IssueLogDaoNew issueLogDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private WorkCalendarService workCalendarService;

	private Long year;
	private Long month;

	@Override
	public IssueStatueStanal addIssueStatueStanal(IssueStatueStanal issueStatueStanal) {
		initDate(issueStatueStanal);
		year = issueStatueStanal.getYear();
		month = issueStatueStanal.getMonth();
		Date endDate = issueStatueStanal.getEndDate();
		getListOrganizationsByOrgInternalCode(issueStatueStanal.getOrganization()
				.getOrgInternalCode());
		List<IssueLogNew> issueLogNews = getListIssueLogsByOrgInternalCode(issueStatueStanal
				.getOrganization().getOrgInternalCode(), issueStatueStanal.getIssueStatDate(),
				endDate);
		if (issueLogNews != null && issueLogNews.size() > 0) {
			changeIssueStatueStanals(issueLogNews);
		}
		issueStatueStanalDao.deleteIssueStatueStanal(issueStatueStanal);
		Iterator<Entry<Long, IssueStatueStanal>> entryKeyIterator = map.entrySet().iterator();
		while (entryKeyIterator.hasNext()) {
			IssueStatueStanal entryIssueStatueStanal = (IssueStatueStanal) entryKeyIterator.next()
					.getValue();
			Long overTimeNotDoneCount = issueLogDao.countOverTimeNotDoneByOverTimeLogTable(year,
					month, issueStatueStanal.getIssueStatDate(), issueStatueStanal
							.getOrganization().getId());
			entryIssueStatueStanal.setOverTimeNotDone(entryIssueStatueStanal.getOverTimeNotDone()
					+ overTimeNotDoneCount);
			issueStatueStanalDao.addIssueStatueStanal(entryIssueStatueStanal);
		}
		return issueStatueStanalDao.getIssueStatueStanalStatCountByYearAndMonth(issueStatueStanal
				.getOrganization().getId(), year.intValue(), month.intValue());
	}

	private void changeIssueStatueStanals(List<IssueLogNew> issueLogNews) {
		for (IssueLogNew issueLogNew : issueLogNews) {
			IssueStatueStanal issueStatueStanal = map.get(issueLogNew.getTargeOrg().getId());
			IssueStatueStanal changedIssueStatueStanal = changeIssueStatueStanal(issueLogNew,
					issueStatueStanal);
			map.put(issueLogNew.getTargeOrg().getId(), changedIssueStatueStanal);
		}
	}

	private List<IssueLogNew> getListIssueLogsByOrgInternalCode(String orgInternalCode,
			Date startDate, Date endDate) {
		List<IssueLogNew> issueLogNews = issueLogDao.findIssueLogByOrgInternalCodeAndDate(
				orgInternalCode, startDate, endDate);
		return issueLogNews;
	}

	private static Calendar endDate(Calendar startCalendar) {
		startCalendar.set(startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH) + 1,
				startCalendar.getActualMinimum(Calendar.DAY_OF_MONTH),
				startCalendar.getActualMinimum(Calendar.HOUR_OF_DAY),
				startCalendar.getActualMinimum(Calendar.SECOND),
				startCalendar.getActualMinimum(Calendar.MINUTE));
		return startCalendar;
	}

	private void initDate(IssueStatueStanal issueStatueStanal) {
		Date startDate = CalendarUtil.parseDate(issueStatueStanal.getYear() + ""
				+ issueStatueStanal.getMonth(), "yyyyM");

		Calendar tempCal = Calendar.getInstance();
		Date endCal = CalendarUtil.parseDate(String.valueOf(issueStatueStanal.getYear())
				+ issueStatueStanal.getMonth(), "yyyyM");
		tempCal.setTime(endCal);
		tempCal.add(Calendar.MONTH, 1);
		tempCal.add(Calendar.DAY_OF_MONTH, -1);
		issueStatueStanal.setIssueStatDate(startDate);
		issueStatueStanal.setEndDate(endDate(tempCal).getTime());
	}

	Map<Long, IssueStatueStanal> map = new HashMap<Long, IssueStatueStanal>();

	/**
	 * 查找所有的组织部门,并存入到map中
	 */
	private void getListOrganizations() {
		List<Organization> orgList = issueTypeStanalDao.findOrganizations();
		Date date = initMonthStartCal().getTime();
		Calendar endCal = Calendar.getInstance();
		endCal.set(endCal.get(Calendar.YEAR), endCal.get(Calendar.MONTH),
				endCal.getActualMinimum(Calendar.DAY_OF_MONTH),
				endCal.getActualMinimum(Calendar.HOUR_OF_DAY),
				endCal.getActualMinimum(Calendar.MINUTE), endCal.getActualMinimum(Calendar.SECOND));
		for (Organization org : orgList) {
			map.put(org.getId(), initIssueStatueStanal(org, date, endCal.getTime()));
		}
	}

	/**
	 * 查找所选组织机构下所有部门,并存入到map中
	 */
	private void getListOrganizationsByOrgInternalCode(String orgInternalCode) {
		List<Organization> orgList = issueTypeStanalDao
				.findOrganizationsByOrgInternalCode(orgInternalCode);
		Calendar startCal = Calendar.getInstance();
		startCal.set(year.intValue(), month.intValue() - 1,
				startCal.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date date = startCal.getTime();
		Calendar endCal = Calendar.getInstance();
		endCal.set(year.intValue(), month.intValue(),
				endCal.getActualMinimum(Calendar.DAY_OF_MONTH),
				endCal.getActualMinimum(Calendar.HOUR_OF_DAY),
				endCal.getActualMinimum(Calendar.MINUTE), endCal.getActualMinimum(Calendar.SECOND));
		for (Organization org : orgList) {
			map.put(org.getId(), initIssueStatueStanal(org, date, endCal.getTime()));
		}
	}

	/**
	 * 初始化Map中服务办事统计信息
	 * 
	 * @param organization
	 * @return
	 */
	private IssueStatueStanal initIssueStatueStanal(Organization organization, Date date,
			Date endDate) {
		IssueStatueStanal issueStatueStanal = new IssueStatueStanal();
		issueStatueStanal.setOrganization(organization);
		issueStatueStanal.setOrgInternalCode(organization.getOrgInternalCode());
		issueStatueStanal.setNormalDone(0L);
		issueStatueStanal.setNormalNotDone(0L);
		issueStatueStanal.setOverTimeDone(0L);
		issueStatueStanal.setOverTimeNotDone(0L);
		initDate();
		issueStatueStanal.setYear(year);
		issueStatueStanal.setMonth(month);
		issueStatueStanal.setIssueStatDate(date);
		issueStatueStanal.setEndDate(endDate);
		return issueStatueStanal;
	}

	/**
	 * 初始化状态统计中的年月
	 */
	private void initDate() {
		if (year == null && month == null) {
			year = (long) initMonthStartCal().get(Calendar.YEAR);
			month = (long) initMonthStartCal().get(Calendar.MONTH + 1);
		}
	}

	/**
	 * 根据时间获得所有的服务办事步骤
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private List<IssueLogNew> getListIssueLogs(Long orgId, Date startDate, Date endDate) {
		List<IssueLogNew> issueLogNews = issueLogDao.findIssueLogByOrgIdAndDate(orgId, startDate,
				endDate);
		return issueLogNews;
	}

	/**
	 * 初始化全部待办,已办数据
	 */
	public void initIssueStatueStanalJob() {
		getListOrganizations();
		List<IssueLogNew> issueLogNews = getListIssueLogs(null, initMonthStartCal().getTime(),
				initMonthEndCal().getTime());
		for (IssueLogNew issueLogNew : issueLogNews) {
			IssueStatueStanal issueStatueStanal = map.get(issueLogNew.getTargeOrg().getId());
			IssueStatueStanal changedIssueStatueStanal = changeIssueStatueStanal(issueLogNew,
					issueStatueStanal);
			map.put(issueLogNew.getTargeOrg().getId(), changedIssueStatueStanal);
		}
	}

	/**
	 * 根据服务办事处理类型更新map中的服务办事状态
	 * 
	 * @param issueLogNew
	 * @param issueStatueStanal
	 * @returnpublic
	 */
	private IssueStatueStanal changeIssueStatueStanal(IssueLogNew issueLogNew,
			IssueStatueStanal issueStatueStanal) {
		if (issueLogNew.getDealState().equals(IssueDealState.UN_DONE)
				|| issueLogNew.getDealState().equals(IssueDealState.UN_READ)) {
			if (IssueLogDealOverTimeHelper.isDealOverTime(issueLogNew, workCalendarService)) {
				issueStatueStanal.setOverTimeNotDone(issueStatueStanal.getOverTimeNotDone() + 1L);
			} else {
				issueStatueStanal.setNormalNotDone(issueStatueStanal.getNormalNotDone() + 1L);
			}
		} else if (issueLogNew.getDealState().equals(IssueDealState.DOING)) {
			if (IssueLogDealOverTimeHelper.isDealOverTime(issueLogNew, workCalendarService)) {
				issueStatueStanal.setOverTimeNotDone(issueStatueStanal.getOverTimeNotDone() + 1L);
			} else {
				issueStatueStanal.setNormalNotDone(issueStatueStanal.getNormalNotDone() + 1L);
			}
		} else if (issueLogNew.getDealState() >= 1001L
				&& (issueLogNew.getDealType().equals(IssueDealType.Add)
						|| issueLogNew.getDealType().equals(IssueDealType.SUBMIT_FORWARD) || issueLogNew
						.getDealType().equals(IssueDealType.ASSIGN))) {
			if (issueLogNew.getLogCompleteTime().after(issueStatueStanal.getEndDate())) {
				issueLogNew.setDealState(IssueDealState.UN_DONE);
				if (IssueLogDealOverTimeHelper.isDealOverTime(issueLogNew, workCalendarService)) {
					issueStatueStanal
							.setOverTimeNotDone(issueStatueStanal.getOverTimeNotDone() + 1L);
				} else {
					issueStatueStanal.setNormalNotDone(issueStatueStanal.getNormalNotDone());
				}
			}
		} else if (issueLogNew.getDealState().equals(IssueDealState.DONE)
				&& (issueLogNew.getDealType().equals(IssueDealType.CONCEPT)
						|| issueLogNew.getDealType().equals(IssueDealType.COMMENT) || issueLogNew
						.getDealType().equals(IssueDealType.SEND_BACK))) {
			if (issueLogNew.getLogCompleteTime().after(issueStatueStanal.getEndDate())) {
				issueLogNew.setDealState(IssueDealState.DOING);
				if (IssueLogDealOverTimeHelper.isDealOverTime(issueLogNew, workCalendarService)) {
					issueStatueStanal
							.setOverTimeNotDone(issueStatueStanal.getOverTimeNotDone() + 1L);
				} else {
					issueStatueStanal.setNormalNotDone(issueStatueStanal.getNormalNotDone() + 1L);
				}
			} else {
				if (IssueLogDealOverTimeHelper.isDealOverTime(issueLogNew, workCalendarService)) {
					issueStatueStanal.setOverTimeDone(issueStatueStanal.getOverTimeDone() + 1L);
				} else {
					issueStatueStanal.setNormalDone(issueStatueStanal.getNormalDone() + 1L);
				}
			}
		} else if (issueLogNew.getDealState().equals(IssueDealState.COMPLETE)) {
			if (IssueLogDealOverTimeHelper.isDealOverTime(issueLogNew, workCalendarService)) {
				issueStatueStanal.setOverTimeDone(issueStatueStanal.getOverTimeDone() + 1L);
			} else {
				issueStatueStanal.setNormalDone(issueStatueStanal.getNormalDone() + 1L);
			}
		} else if (issueLogNew.getDealState().equals(IssueDealState.READED)) {
			if (IssueLogDealOverTimeHelper.isDealOverTime(issueLogNew, workCalendarService)) {
				issueStatueStanal.setOverTimeDone(issueStatueStanal.getOverTimeDone() + 1L);
			} else {
				issueStatueStanal.setNormalDone(issueStatueStanal.getNormalDone() + 1L);
			}
		}
		return issueStatueStanal;
	}

	/**
	 * job中初始化开始时间
	 * 
	 * @return
	 */
	private Calendar initMonthStartCal() {
		Calendar startCal = Calendar.getInstance();
		startCal.set(startCal.get(Calendar.YEAR), startCal.get(Calendar.MONTH - 1),
				startCal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return startCal;
	}

	/**
	 * job中初始化结束时间
	 * 
	 * @return
	 */
	private Calendar initMonthEndCal() {
		Calendar endCal = Calendar.getInstance();
		endCal.set(endCal.get(Calendar.YEAR), endCal.get(Calendar.MONTH),
				endCal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return endCal;
	}

	public IssueStatueStanal addManualGenerationIssueStatueStanal(String targeOrgInternalCode,
			Date startDate, Date endDate) {
		IssueStatueStanal issueStatueStanal = new IssueStatueStanal();
		return issueStatueStanal;
	}

	@Override
	public IssueStatueStanal getIssueStatueStanalStatCount(IssueStatueStanal issueStatueStanal) {
		IssueStatueStanal issueStatueStanals = issueStatueStanalDao
				.getIssueStatueStanalStatCount(issueStatueStanal);
		if (issueStatueStanals.getNormalNotDone() == null) {
			issueStatueStanals = initIssueStatueStanal(issueStatueStanal.getOrganization(), null,
					null);
		}
		return issueStatueStanals;
	}

	@Override
	public IssueStatueStanal getIssueStatueStanalStatCountByYearAndMonth(Long orgId,
			Integer sysBeginYear, Integer month) {
		return issueStatueStanalDao.getIssueStatueStanalStatCountByYearAndMonth(orgId,
				sysBeginYear, month);
	}
}

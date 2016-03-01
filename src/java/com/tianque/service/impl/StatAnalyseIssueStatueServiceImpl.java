package com.tianque.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.CalendarUtil;
import com.tianque.dao.StatAnalyseIssueDao;
import com.tianque.domain.BaseInfoStanalObject;
import com.tianque.domain.IssueStatueStanal;
import com.tianque.domain.StatAnalyseIssue;
import com.tianque.domain.vo.StatAnalyseIssueVo;
import com.tianque.service.IssueStatueStanalService;
import com.tianque.service.StatAnalyseIssueStatueService;
import com.tianque.state.IssueStanalState;

@Service("statAnalyseIssueStatueService")
@Transactional
@SuppressWarnings({ "static-access", "unchecked" })
public class StatAnalyseIssueStatueServiceImpl extends LogableService implements
		StatAnalyseIssueStatueService {
	@Autowired
	StatAnalyseIssueDao statAnalyseIssueDao;
	@Autowired
	IssueStatueStanalService issueStatueStanalService;

	@Override
	public List<StatAnalyseIssue> getIssueState(StatAnalyseIssue statAnalyseIssue) {
		IssueStatueStanal issueStatueStanal = new IssueStatueStanal();
		issueStatueStanal.setOrganization(statAnalyseIssue.getOrganization());
		issueStatueStanal.setIssueStatDate(statAnalyseIssue.getStartDate());
		issueStatueStanal.setEndDate(statAnalyseIssue.getEndDate());
		issueStatueStanal = issueStatueStanalService
				.getIssueStatueStanalStatCount(issueStatueStanal);
		int extendNoIssue = issueStatueStanal.getOverTimeNotDone().intValue();
		int extendAlreadyIssue = issueStatueStanal.getOverTimeDone().intValue();
		int normalNoIssue = issueStatueStanal.getNormalNotDone().intValue();
		int normalAlredayIssue = issueStatueStanal.getNormalDone().intValue();
		int totalIssue = extendAlreadyIssue + normalAlredayIssue + extendNoIssue + normalNoIssue;
		List<StatAnalyseIssue> list = new ArrayList<StatAnalyseIssue>();
		list.add(new StatAnalyseIssue(IssueStanalState.OVERTIMENOTDONE, "待办(超期)", extendNoIssue,
				getRatio(extendNoIssue, totalIssue), statAnalyseIssue.getStartDate(),
				statAnalyseIssue.getEndDate()));
		list.add(new StatAnalyseIssue(IssueStanalState.OVERTIMEDONE, "已办(超期)", extendAlreadyIssue,
				getRatio(extendAlreadyIssue, totalIssue), statAnalyseIssue.getStartDate(),
				statAnalyseIssue.getEndDate()));
		list.add(new StatAnalyseIssue(IssueStanalState.NORMALNOTDONE, "待办(正常)", normalNoIssue,
				getRatio(normalNoIssue, totalIssue), statAnalyseIssue.getStartDate(),
				statAnalyseIssue.getEndDate()));
		list.add(new StatAnalyseIssue(IssueStanalState.NORMALDONE, "已办(正常)", normalAlredayIssue,
				getRatio(normalAlredayIssue, totalIssue), statAnalyseIssue.getStartDate(),
				statAnalyseIssue.getEndDate()));
		return list;
	}

	@Override
	public List showLine(StatAnalyseIssue statAnalyseIssue) {
		Calendar can = Calendar.getInstance();
		int sysBeginYear = 0;
		int sysBeginMonth = 0;
		int nowYear = 0;
		int nowMonth = 0;

		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(statAnalyseIssue.getStartDate());
		sysBeginYear = startCalendar.get(startCalendar.YEAR);
		sysBeginMonth = startCalendar.get(startCalendar.MONTH) - 1;
		can.setTime(statAnalyseIssue.getEndDate());
		nowYear = can.get(can.YEAR);
		nowMonth = (can.get(can.MONTH)) + 1;
		return getStatue(sysBeginYear, nowYear, nowMonth, sysBeginMonth, statAnalyseIssue);
	}

	public List getStatue(int sysBeginYear, int nowYear, int nowMonth, int sysBeginMonth,
			StatAnalyseIssue statAnalyseIssue) {
		List list = new ArrayList();
		List<StatAnalyseIssueVo> noDealExtendList = new ArrayList<StatAnalyseIssueVo>();
		List<StatAnalyseIssueVo> alreadyDealExtendList = new ArrayList<StatAnalyseIssueVo>();
		List<StatAnalyseIssueVo> noDealNormalList = new ArrayList<StatAnalyseIssueVo>();
		List<StatAnalyseIssueVo> alreadyDealNormalList = new ArrayList<StatAnalyseIssueVo>();
		int middleYear = nowYear - sysBeginYear;
		if (middleYear == 0) {
			if (sysBeginMonth == nowMonth) {
				setStatAnalyseIssue(statAnalyseIssue, formatDate(sysBeginYear, sysBeginMonth - 1),
						formatDate(sysBeginYear, sysBeginMonth));
				IssueStatueStanal issueStatueStanal = new IssueStatueStanal();
				issueStatueStanal.setOrganization(statAnalyseIssue.getOrganization());
				issueStatueStanal.setIssueStatDate(statAnalyseIssue.getStartDate());
				issueStatueStanal.setEndDate(statAnalyseIssue.getEndDate());
				// issueStatueStanal = issueStatueStanalService
				// .getIssueStatueStanalStatCount(issueStatueStanal);
				issueStatueStanal = issueStatueStanalService
						.getIssueStatueStanalStatCountByYearAndMonth(statAnalyseIssue
								.getOrganization().getId(), sysBeginYear, sysBeginMonth - 1);
				noDealExtendList.add(this.getStatAnalyseIssueCount(
						issueStatueStanal.getOverTimeNotDone(), statAnalyseIssue, sysBeginYear,
						sysBeginMonth - 1));
				alreadyDealExtendList.add(this.getStatAnalyseAlreadyDealExtend(
						issueStatueStanal.getOverTimeDone(), statAnalyseIssue, sysBeginYear,
						sysBeginMonth - 1));
				noDealNormalList.add(this.getStatAnalyseNoDealNormal(
						issueStatueStanal.getNormalNotDone(), statAnalyseIssue, sysBeginYear,
						sysBeginMonth - 1));
				alreadyDealNormalList.add(this.getStatAnalyseAlreadyDealNormal(
						issueStatueStanal.getNormalDone(), statAnalyseIssue, sysBeginYear,
						sysBeginMonth - 1));
			}
			for (int i = sysBeginMonth; i <= nowMonth; i++) {
				setStatAnalyseIssue(statAnalyseIssue, formatDate(sysBeginYear, i),
						formatDate(sysBeginYear, i + 1));
				IssueStatueStanal issueStatueStanal = new IssueStatueStanal();
				issueStatueStanal.setOrganization(statAnalyseIssue.getOrganization());
				issueStatueStanal.setIssueStatDate(statAnalyseIssue.getStartDate());
				issueStatueStanal.setEndDate(statAnalyseIssue.getEndDate());
				// issueStatueStanal = issueStatueStanalService
				// .getIssueStatueStanalStatCount(issueStatueStanal);
				issueStatueStanal = issueStatueStanalService
						.getIssueStatueStanalStatCountByYearAndMonth(statAnalyseIssue
								.getOrganization().getId(), sysBeginYear, i);
				noDealExtendList.add(this.getStatAnalyseIssueCount(
						issueStatueStanal.getOverTimeNotDone(), statAnalyseIssue, sysBeginYear, i));
				alreadyDealExtendList.add(this.getStatAnalyseAlreadyDealExtend(
						issueStatueStanal.getOverTimeDone(), statAnalyseIssue, sysBeginYear, i));
				noDealNormalList.add(this.getStatAnalyseNoDealNormal(
						issueStatueStanal.getNormalNotDone(), statAnalyseIssue, sysBeginYear, i));
				alreadyDealNormalList.add(this.getStatAnalyseAlreadyDealNormal(
						issueStatueStanal.getNormalDone(), statAnalyseIssue, sysBeginYear, i));
			}
			if (sysBeginMonth == nowMonth) {
				setStatAnalyseIssue(statAnalyseIssue, formatDate(sysBeginYear, nowMonth + 1),
						formatDate(sysBeginYear, nowMonth + 2));
				IssueStatueStanal issueStatueStanal = new IssueStatueStanal();
				issueStatueStanal.setOrganization(statAnalyseIssue.getOrganization());
				issueStatueStanal.setIssueStatDate(statAnalyseIssue.getStartDate());
				issueStatueStanal.setEndDate(statAnalyseIssue.getEndDate());
				// issueStatueStanal = issueStatueStanalService
				// .getIssueStatueStanalStatCount(issueStatueStanal);
				issueStatueStanal = issueStatueStanalService
						.getIssueStatueStanalStatCountByYearAndMonth(statAnalyseIssue
								.getOrganization().getId(), sysBeginYear, nowMonth + 1);
				noDealExtendList.add(this.getStatAnalyseIssueCount(
						issueStatueStanal.getOverTimeNotDone(), statAnalyseIssue, sysBeginYear,
						nowMonth + 2));
				alreadyDealExtendList.add(this.getStatAnalyseAlreadyDealExtend(
						issueStatueStanal.getOverTimeDone(), statAnalyseIssue, sysBeginYear,
						nowMonth + 2));
				noDealNormalList.add(this.getStatAnalyseNoDealNormal(
						issueStatueStanal.getNormalNotDone(), statAnalyseIssue, sysBeginYear,
						nowMonth + 2));
				alreadyDealNormalList.add(this.getStatAnalyseAlreadyDealNormal(
						issueStatueStanal.getNormalDone(), statAnalyseIssue, sysBeginYear,
						nowMonth + 2));
			}
		} else if (middleYear > 0) {
			int totalMonth = middleYear * 12 - (sysBeginMonth - 1) + nowMonth;
			for (int i = sysBeginMonth; i < totalMonth + sysBeginMonth; i++) {
				setStatAnalyseIssue(statAnalyseIssue, formatDate(sysBeginYear, i),
						formatDate(sysBeginYear, i + 1));
				IssueStatueStanal issueStatueStanal = new IssueStatueStanal();
				issueStatueStanal.setOrganization(statAnalyseIssue.getOrganization());
				issueStatueStanal.setIssueStatDate(statAnalyseIssue.getStartDate());
				Integer year = new Integer(CalendarUtil.format("yyyy-MM-dd",
						statAnalyseIssue.getStartDate()).split("-")[0]);
				Integer month = new Integer(CalendarUtil.format("yyyy-MM-dd",
						statAnalyseIssue.getStartDate()).split("-")[1]);
				issueStatueStanal.setEndDate(statAnalyseIssue.getEndDate());
				// issueStatueStanal = issueStatueStanalService
				// .getIssueStatueStanalStatCount(issueStatueStanal);
				issueStatueStanal = issueStatueStanalService
						.getIssueStatueStanalStatCountByYearAndMonth(statAnalyseIssue
								.getOrganization().getId(), year, month);
				noDealExtendList.add(this.getStatAnalyseIssueCount(
						issueStatueStanal.getOverTimeNotDone(), statAnalyseIssue, sysBeginYear, i));
				alreadyDealExtendList.add(this.getStatAnalyseAlreadyDealExtend(
						issueStatueStanal.getOverTimeDone(), statAnalyseIssue, sysBeginYear, i));
				noDealNormalList.add(this.getStatAnalyseNoDealNormal(
						issueStatueStanal.getNormalNotDone(), statAnalyseIssue, sysBeginYear, i));
				alreadyDealNormalList.add(this.getStatAnalyseAlreadyDealNormal(
						issueStatueStanal.getNormalDone(), statAnalyseIssue, sysBeginYear, i));
			}
		}

		list.add(noDealExtendList);
		list.add(alreadyDealExtendList);
		list.add(noDealNormalList);
		list.add(alreadyDealNormalList);
		return list;
	}

	private StatAnalyseIssue setStatAnalyseIssue(StatAnalyseIssue statAnalyseIssue, Date startDate,
			Date endDate) {
		statAnalyseIssue.setStartDate(startDate);
		statAnalyseIssue.setEndDate(endDate);
		return statAnalyseIssue;
	}

	private static Date formatDate(int year, int month) {
		Calendar can = Calendar.getInstance();
		can.set(year, month - 1, 1, 0, 0, 0);
		Date date = can.getTime();
		return date;
	}

	public StatAnalyseIssueVo getStatAnalyseIssueCount(Long count,
			StatAnalyseIssue statAnalyseIssue, int year, int month) {
		StatAnalyseIssueVo statAnalyseIssueVo = new StatAnalyseIssueVo();
		statAnalyseIssueVo.setCount(count == null ? 0 : count.intValue());
		statAnalyseIssueVo.setYear(year);
		statAnalyseIssueVo.setMonth(month);
		return statAnalyseIssueVo;
	}

	public StatAnalyseIssueVo getStatAnalyseAlreadyDealExtend(Long count,
			StatAnalyseIssue statAnalyseIssue, int year, int month) {
		StatAnalyseIssueVo statAnalyseIssueVo = new StatAnalyseIssueVo();
		statAnalyseIssueVo.setCount(count == null ? 0 : count.intValue());
		statAnalyseIssueVo.setYear(year);
		statAnalyseIssueVo.setMonth(month);
		return statAnalyseIssueVo;
	}

	public StatAnalyseIssueVo getStatAnalyseNoDealNormal(Long count,
			StatAnalyseIssue statAnalyseIssue, int year, int month) {
		StatAnalyseIssueVo statAnalyseIssueVo = new StatAnalyseIssueVo();
		statAnalyseIssueVo.setCount(count == null ? 0 : count.intValue());
		statAnalyseIssueVo.setYear(year);
		statAnalyseIssueVo.setMonth(month);
		return statAnalyseIssueVo;
	}

	public StatAnalyseIssueVo getStatAnalyseAlreadyDealNormal(Long count,
			StatAnalyseIssue statAnalyseIssue, int year, int month) {
		StatAnalyseIssueVo statAnalyseIssueVo = new StatAnalyseIssueVo();
		statAnalyseIssueVo.setCount(count == null ? 0 : count.intValue());
		statAnalyseIssueVo.setYear(year);
		statAnalyseIssueVo.setMonth(month);
		return statAnalyseIssueVo;
	}

	private String getRatio(int statueCount, int totalCount) {
		String ratio = "";
		if (statueCount == 0) {
			ratio = 0 + "%";
		} else {
			DecimalFormat df = new DecimalFormat("0.00");
			ratio = df.format(statueCount * 100 / (double) totalCount) + "%";
		}
		return ratio;
	}

	@Override
	public List<BaseInfoStanalObject> statIssueNormalNotDone(Long orgId, Date startDate,
			Date endDate) {
		return statAnalyseIssueDao.statIssueNormalNotDone(orgId, startDate, endDate);
	}

	@Override
	public List<BaseInfoStanalObject> statIssueOverTimeNotDone(Long orgId, Date startDate,
			Date endDate) {
		return statAnalyseIssueDao.statIssueOverTimeNotDone(orgId, startDate, endDate);
	}

	@Override
	public List<BaseInfoStanalObject> statIssueNormalDone(Long orgId, Date startDate, Date endDate) {
		return statAnalyseIssueDao.statIssueNormalDone(orgId, startDate, endDate);
	}

	@Override
	public List<BaseInfoStanalObject> statIssueOverTimeDone(Long orgId, Date startDate, Date endDate) {
		return statAnalyseIssueDao.statIssueOverTimeDone(orgId, startDate, endDate);
	}

}

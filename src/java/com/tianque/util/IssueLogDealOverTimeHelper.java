package com.tianque.util;

import java.util.Date;

import com.tianque.core.util.CalendarUtil;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.service.WorkCalendarService;
import com.tianque.state.IssueDealState;
import com.tianque.state.IssueDealType;
import com.tianque.state.OverTime;

public class IssueLogDealOverTimeHelper {

	public static boolean isDealOverTime(IssueLogNew issueLogNew,
			WorkCalendarService workCalendarService) {
		boolean flag = false;
		if (issueLogNew.getDealState().equals(IssueDealState.UN_DONE)
				|| issueLogNew.getDealState().equals(IssueDealState.UN_READ)) {
			Date normalDealTime = workCalendarService.getFutureDate(issueLogNew.getDealTime(),
					OverTime.YELLOW_CONCEPT);
			if (CalendarUtil.now("yyyy-MM-dd HH:mm:ss").after(normalDealTime))
				flag = true;
		} else if (issueLogNew.getDealState().equals(IssueDealState.DOING)) {
			Date normalDealTime = workCalendarService.getFutureDate(issueLogNew.getDealTime(),
					OverTime.YELLOW_DONE);
			if (CalendarUtil.now("yyyy-MM-dd HH:mm:ss").after(normalDealTime))
				flag = true;
		} else if (issueLogNew.getDealState() >= 1001L
				&& (issueLogNew.getDealType().equals(IssueDealType.Add)
						|| issueLogNew.getDealType().equals(IssueDealType.SUBMIT_FORWARD) || issueLogNew
						.getDealType().equals(IssueDealType.ASSIGN))) {
			Date normalDealTime = workCalendarService.getFutureDate(issueLogNew.getDealTime(),
					OverTime.YELLOW_CONCEPT);
			if (issueLogNew.getLogCompleteTime().after(normalDealTime))
				flag = true;
		} else if (issueLogNew.getDealState() >= 1001L
				&& (issueLogNew.getDealType().equals(IssueDealType.CONCEPT)
						|| issueLogNew.getDealType().equals(IssueDealType.COMMENT) || issueLogNew
						.getDealType().equals(IssueDealType.SEND_BACK))) {
			Date normalDealTime = workCalendarService.getFutureDate(issueLogNew.getDealTime(),
					OverTime.YELLOW_DONE);
			if (issueLogNew.getLogCompleteTime().after(normalDealTime))
				flag = true;
		} else if (issueLogNew.getDealState().equals(IssueDealState.COMPLETE)) {
			Date normalDate = workCalendarService.getFutureDate(issueLogNew.getForLogEntryTime(),
					OverTime.YELLOW_DONE);
			if (issueLogNew.getDealTime().after(normalDate))
				flag = true;
		} else if (issueLogNew.getDealState().equals(IssueDealState.READED)) {
			Date normalDate = workCalendarService.getFutureDate(issueLogNew.getForLogEntryTime(),
					OverTime.YELLOW_CONCEPT);
			if (issueLogNew.getDealTime().after(normalDate))
				flag = true;
		}
		return flag;
	}

	public static boolean isOvertime(Date enddate, Date startDate,
			WorkCalendarService workCalendarService, Integer dateLineType) {
		Date lastDealTime = workCalendarService.getFutureDate(startDate, dateLineType);
		return enddate.after(lastDealTime);
	}
}

package com.tianque.issue.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkDiaryTypes;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.event.IssueChangeEvent;
import com.tianque.issue.state.IssueStepGroup;
import com.tianque.state.IssueDealType;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.service.WorkDiaryService;

/**
 * 保存工作台帐记录
 */
@Repository("appendToWorkDaily")
public class AppendToWorkDaily extends NothingDoIssueChangeListener {
	@Autowired
	private WorkDiaryService workDiaryService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public void onEntry(IssueNew issue, IssueStep step) {

		String oprateType = IssueDealType.toString(IssueDealType.Add.intValue());

		String content = workDiaryService.assemblingContent(issue.getSubject(), oprateType,
				issue.getIssueContent(), WorkDiaryTypes.ISSUE_DEAL, "", "");

		PropertyDict diaryType = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(PropertyTypes.WORKDIARY_DIARY_TYPE,
						WorkDiaryTypes.ISSUE_DEAL);

		workDiaryService.addWorkDiary(diaryType, WorkDiaryTypes.TYPE_ISSUENEW, issue.getId(),
				content, issue.getOccurLocation(), getCurrentLoginedUserName(),
				CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public void onComplete(IssueNew issue, IssueStep step, IssueChangeEvent event) {
		buildAndSaveIssueOperateWorkDaily(issue, event);
	}

	@Override
	public void onChanged(IssueNew issue, IssueStepGroup steps, IssueChangeEvent event) {
		buildAndSaveIssueOperateWorkDaily(issue, event);
	}

	private void buildAndSaveIssueOperateWorkDaily(IssueNew issue, IssueChangeEvent event) {
		String content = buildWorkDailyContent(issue, event);
		appendWorkDaily(issue, content);
	}

	private String buildWorkDailyContent(IssueNew issue, IssueChangeEvent event) {
		return workDiaryService.assemblingContent(issue.getSubject(),
				event.getOperate().toString(), issue.getIssueContent(), WorkDiaryTypes.ISSUE_DEAL,
				"", "");
	}

	private void appendWorkDaily(IssueNew issue, String content) {

		PropertyDict diaryType = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(PropertyTypes.WORKDIARY_DIARY_TYPE,
						WorkDiaryTypes.ISSUE_DEAL);

		workDiaryService.addWorkDiary(diaryType, WorkDiaryTypes.TYPE_ISSUENEW, issue.getId(),
				content, issue.getOccurLocation(), getCurrentLoginedUserName(),
				CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
	}

}

package com.tianque.fourTeams.fourTeamsIssue.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkDiaryTypes;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.event.FourTeamsIssueChangeEvent;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;
import com.tianque.state.IssueDealType;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.service.WorkDiaryService;

/**
 * 保存工作台帐记录
 */
@Repository("fourTeamsAppendToWorkDaily")
public class FourTeamsAppendToWorkDaily extends FourTeamsNothingDoIssueChangeListener {
	@Autowired
	private WorkDiaryService workDiaryService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public void onEntry(FourTeamsIssueNew issue, FourTeamsIssueStep step) {

		String oprateType = IssueDealType
				.toString(IssueDealType.Add.intValue());

		String content = workDiaryService.assemblingContent(issue.getSubject(),
				oprateType, issue.getIssueContent(), WorkDiaryTypes.ISSUE_DEAL,
				"", "");

		PropertyDict diaryType = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.WORKDIARY_DIARY_TYPE,
						WorkDiaryTypes.ISSUE_DEAL);

		workDiaryService.addWorkDiary(diaryType, WorkDiaryTypes.TYPE_ISSUENEW,
				issue.getId(), content, issue.getOccurLocation(),
				getCurrentLoginedUserName(), CalendarUtil
						.now("yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public void onComplete(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueChangeEvent event) {
		buildAndSaveIssueOperateWorkDaily(issue, event);
	}

	@Override
	public void onChanged(FourTeamsIssueNew issue, FourTeamsIssueStepGroup steps,
			FourTeamsIssueChangeEvent event) {
		buildAndSaveIssueOperateWorkDaily(issue, event);
	}

	private void buildAndSaveIssueOperateWorkDaily(FourTeamsIssueNew issue,
			FourTeamsIssueChangeEvent event) {
		String content = buildWorkDailyContent(issue, event);
		appendWorkDaily(issue, content);
	}

	private String buildWorkDailyContent(FourTeamsIssueNew issue, FourTeamsIssueChangeEvent event) {
		return workDiaryService.assemblingContent(issue.getSubject(), event
				.getOperate().toString(), issue.getIssueContent(),
				WorkDiaryTypes.ISSUE_DEAL, "", "");
	}

	private void appendWorkDaily(FourTeamsIssueNew issue, String content) {

		PropertyDict diaryType = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.WORKDIARY_DIARY_TYPE,
						WorkDiaryTypes.ISSUE_DEAL);

		workDiaryService.addWorkDiary(diaryType, WorkDiaryTypes.TYPE_ISSUENEW,
				issue.getId(), content, issue.getOccurLocation(),
				getCurrentLoginedUserName(), CalendarUtil
						.now("yyyy-MM-dd HH:mm:ss"));
	}

}

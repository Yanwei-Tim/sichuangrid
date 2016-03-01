package com.tianque.working.service;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.working.domain.WorkDiary;

public interface WorkDiaryService {

	WorkDiary addWorkDiary(WorkDiary workDiary);

	WorkDiary getWorkDiaryById(Long id);

	PageInfo<WorkDiary> findWorkDiarysForPage(Long orgId, Integer rows, Integer page, String sidx,
			String sord, boolean searchChild);

	WorkDiary updateWorkDiary(WorkDiary workDiary);

	Long deleteWorkDiaryById(List<Long> ids);

	void addWorkDiary(PropertyDict proertyDict, String objectType, Long objectId,
			String workContent, String workPlace, String workUserName, Date time);

	void updateWorkDiary(String objectType, Long objectId, String workContent, String workPlace,
			String workUserName, Date time);

	String assemblingContent(String issueName, String operatType, String content,
			String workDiaryType, String modeType, String objectType);

	/** 根据事件添加工作日志 */
	void addWorkDiary(Long issueId, IssueLogNew issueLogNew);

}

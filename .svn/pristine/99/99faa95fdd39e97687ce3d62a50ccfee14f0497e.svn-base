package com.tianque.fourTeams.fourTeamsIssue.controller.strategy;

import java.util.List;
import java.util.Map;

import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.IssueType;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.validator.FourTeamsIssueOperationLogValidator;
import com.tianque.fourTeams.fourTeamsIssue.validator.FourTeamsIssueValidator;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueTypeViewDefine;

/***
 * 事件策略模式接口
 */
public interface FourTeamsIssueManageStrategy {

	List<FourTeamsIssueTypeViewDefine> getIssueTypeNames();

	Map<String, List<IssueType>> loadEnabledIssueTypes(
			List<FourTeamsIssueTypeViewDefine> issueTypeNames);

	String forwardToAdd();

	String forwardToEdit();

	String forwardToView();

	void setValidator(FourTeamsIssueValidator validator);

	void setLogValidator(FourTeamsIssueOperationLogValidator validator);

	ValidateResult validate(FourTeamsIssueNew issue, List<FourTeamsIssueAttachFile> files);

	void fillIssueSourceProperty(FourTeamsIssueNew issue);

	String forwardToViewDetail();

	String forwardToEmptyViewDetail();

	String forwardToDownLoadAttachFile();

	String forwardToDealing();

	ValidateResult validateDealLog(FourTeamsIssueOperate operate, FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> files);

	String forwardToAddEventSourceIssue();
}

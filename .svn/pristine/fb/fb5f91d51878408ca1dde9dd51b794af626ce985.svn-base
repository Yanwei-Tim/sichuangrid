package com.tianque.issue.controller.strategy;

import java.util.List;
import java.util.Map;

import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.IssueType;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.validator.IssueOperationLogValidator;
import com.tianque.issue.validator.IssueValidator;
import com.tianque.issue.vo.IssueTypeViewDefine;

/***
 * 事件策略模式接口
 */
public interface IssueManageStrategy {

	List<IssueTypeViewDefine> getIssueTypeNames();

	Map<String, List<IssueType>> loadEnabledIssueTypes(
			List<IssueTypeViewDefine> issueTypeNames);

	String forwardToAdd();

	String forwardToEdit();

	String forwardToView();

	void setValidator(IssueValidator validator);

	void setLogValidator(IssueOperationLogValidator validator);

	ValidateResult validate(IssueNew issue, List<IssueAttachFile> files);

	void fillIssueSourceProperty(IssueNew issue);

	String forwardToViewDetail();

	String forwardToEmptyViewDetail();

	String forwardToDownLoadAttachFile();

	String forwardToDealing();

	String forwardToWorkBenchDeal();

	ValidateResult validateDealLog(IssueOperate operate, IssueLogNew log,
			List<IssueAttachFile> files);

	String forwardToAddEventSourceIssue();
}

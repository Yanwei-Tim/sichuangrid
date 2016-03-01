package com.tianque.plugin.account.strategy;

import java.util.List;

import com.tianque.account.validate.ThreeRecordsIssueOperationLogValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;

/***
 * 台账策略模式接口
 */
public interface ThreeRecordsIssueManageStrategy {

	String forwardToAdd();

	String forwardToEdit();

	String forwardToView();

	void setLogValidator(ThreeRecordsIssueOperationLogValidator validator);

	String forwardToViewDetail();

	String forwardToEmptyViewDetail();

	String forwardToDownLoadAttachFile();

	String forwardToDealing();

	String forwardToTmpDealing();

	String forwardToWorkBenchDeal();

	ValidateResult validateDealLog(ThreeRecordsIssueOperate operate,
			ThreeRecordsIssueLogNew log, List<ThreeRecordsIssueAttachFile> files);

	String forwardToAddEventSourceIssue();
}

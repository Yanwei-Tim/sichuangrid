package com.tianque.plugin.account.strategy.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tianque.account.validate.ThreeRecordsIssueOperationLogValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.constants.CommonalityParameter;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.strategy.ThreeRecordsIssueManageStrategy;

@Component("threeRecordsDefaultIssueManageStrategy")
public class ThreeRecordsDefaultIssueManageStrategy implements
		ThreeRecordsIssueManageStrategy {

	private ThreeRecordsIssueOperationLogValidator logValidator;

	@Override
	public String forwardToAdd() {
		return CommonalityParameter.ADD_ISSUE;
	}

	@Override
	public String forwardToEdit() {
		return CommonalityParameter.EDIT_ISSUE;
	}

	@Override
	public String forwardToAddEventSourceIssue() {
		return CommonalityParameter.EVENT;
	}

	@Override
	public void setLogValidator(ThreeRecordsIssueOperationLogValidator validator) {
		this.logValidator = validator;
	}

	@Override
	public String forwardToViewDetail() {
		return CommonalityParameter.DEFAULT_VIEW;
	}

	@Override
	public String forwardToEmptyViewDetail() {
		return CommonalityParameter.EMPTY_VIEW;
	}

	@Override
	public String forwardToDownLoadAttachFile() {
		return CommonalityParameter.STREAM_SUCCESS;
	}

	@Override
	public String forwardToDealing() {
		return CommonalityParameter.DEFAULT_DEALING;
	}

	@Override
	public String forwardToTmpDealing() {
		return CommonalityParameter.TMP_DEALING;
	}

	@Override
	public ValidateResult validateDealLog(ThreeRecordsIssueOperate operate,
			ThreeRecordsIssueLogNew log, List<ThreeRecordsIssueAttachFile> files) {
		return logValidator.validate(operate, log, files);
	}

	@Override
	public String forwardToView() {
		return CommonalityParameter.VIEW_ISSUE;
	}

	@Override
	public String forwardToWorkBenchDeal() {
		return CommonalityParameter.WORK_BENCH_DEAL;
	}
}

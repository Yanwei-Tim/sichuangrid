package com.tianque.threeRecordsIssue.dataTrans.dataConvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.account.api.ThreeRecordsIssueProcessDubboService;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;

/**
 *
 */
@Component("threeRecordsIssueStepDataConverter")
@Scope("prototype")
public class ThreeRecordsIssueStepDataConverter extends
		ThreeAbstractDataConverter<ThreeRecordsIssueStep> {

	@Autowired
	private ThreeRecordsIssueProcessDubboService threeRecordsIssueProcessServiceImpl;

	@Override
	public ValidateResult validate(ThreeRecordsIssueStep domain, int realRow) {
		return new ValidateResult();
	}

	@Override
	public ThreeRecordsIssueStep persistenceDomain(ThreeRecordsIssueStep domain) {
		if (domain.getDealType() != null
				&& ThreeRecordsIssueOperate.SUBMIT.getCode() == domain
						.getDealType().intValue()) {
			domain.setSubmit(1);
		}
		return threeRecordsIssueProcessServiceImpl.addLedgerStep(domain);
	}

	@Override
	public ThreeRecordsIssueStep updateDomain(ThreeRecordsIssueStep domain) {
		if (domain.getId() != null) {
			threeRecordsIssueProcessServiceImpl.updateGroupId(domain);
		} else {
			threeRecordsIssueProcessServiceImpl.updateStateAndCode(domain
					.getLedgerId(), domain.getLedgerType());
		}
		return domain;
	}

	@Override
	public ThreeRecordsIssueStep convertToDomain(String[] cellValues,
			ValidateResult result) {
		// TODO Auto-generated method stub
		return null;
	}

}

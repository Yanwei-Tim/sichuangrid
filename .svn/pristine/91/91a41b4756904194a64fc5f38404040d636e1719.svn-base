package com.tianque.threeRecordsIssue.dataTrans.dataConvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.account.api.LedgerSteadyWorkDubboService;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.domain.LedgerSteadyWork;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.util.IdCardUtil;

/**
 *
 */
@Component("steadyWorkDataConverter")
@Scope("prototype")
public class SteadyWorkDataConverter extends
		ThreeAbstractDataConverter<LedgerSteadyWork> {

	@Autowired
	private LedgerSteadyWorkDubboService ledgerSteadyWorkDubboService;

	@Override
	public ValidateResult validate(LedgerSteadyWork domain, int realRow) {
		return new ValidateResult();
	}

	@Override
	public LedgerSteadyWork persistenceDomain(LedgerSteadyWork domain) {
		domain.setStatus(ThreeRecordsIssueState.DEALING);
		if (StringUtil.isStringAvaliable(domain.getIdCardNo())) {
			domain.setBirthDay(IdCardUtil.parseBirthday(domain.getIdCardNo()));
		}
		return ledgerSteadyWorkDubboService.saveLedgerSteadyWork(domain);
	}

	@Override
	public LedgerSteadyWork updateDomain(LedgerSteadyWork domain) {
		domain.setIsCanFeedBack(LedgerConstants.LEDGER_FEEDBACK_YES);
		ledgerSteadyWorkDubboService.updateLedgerSteadyWorkStatus(domain);
		return domain;
	}

	@Override
	public void registerProcess(LedgerSteadyWork domain) {
		ledgerSteadyWorkDubboService.registerProcess(domain);
	}

	@Override
	public LedgerSteadyWork convertToDomain(String[] cellValues,
			ValidateResult result) {
		// TODO Auto-generated method stub
		return null;
	}

}

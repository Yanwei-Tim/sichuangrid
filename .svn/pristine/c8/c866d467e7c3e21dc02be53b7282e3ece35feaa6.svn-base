package com.tianque.threeRecordsIssue.dataTrans.dataConvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.account.api.PeopleAspirationDubboService;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.domain.LedgerPeopleAspirations;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.util.IdCardUtil;

/**
 *
 */
@Component("peopleAspirationsDataConverter")
@Scope("prototype")
public class PeopleAspirationsDataConverter extends
		ThreeAbstractDataConverter<LedgerPeopleAspirations> {

	@Autowired
	private PeopleAspirationDubboService ledgerPeopleAspirationsService;

	@Override
	public ValidateResult validate(LedgerPeopleAspirations domain, int realRow) {
		return new ValidateResult();
	}

	@Override
	public LedgerPeopleAspirations persistenceDomain(
			LedgerPeopleAspirations domain) {
		domain.setStatus(ThreeRecordsIssueState.DEALING);
		if (StringUtil.isStringAvaliable(domain.getIdCardNo())) {
			domain.setBirthDay(IdCardUtil.parseBirthday(domain.getIdCardNo()));
		}
		if (StringUtil.isStringAvaliable(domain.getHistoryId())) {

		}
		return ledgerPeopleAspirationsService.savePeopleAspirations(domain);
	}

	@Override
	public LedgerPeopleAspirations updateDomain(LedgerPeopleAspirations domain) {
		domain.setIsCanFeedBack(LedgerConstants.LEDGER_FEEDBACK_YES);
		ledgerPeopleAspirationsService
				.updateLedgerPeopleAspirationStatus(domain);
		return domain;
	}

	@Override
	public LedgerPeopleAspirations convertToDomain(String[] cellValues,
			ValidateResult result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerProcess(LedgerPeopleAspirations domain) {
		ledgerPeopleAspirationsService.registerProcess(domain);
	}
}

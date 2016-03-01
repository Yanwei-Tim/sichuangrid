package com.tianque.threeRecordsIssue.dataTrans.dataConvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.account.api.LedgerPoorPeopleDubboService;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.domain.LedgerPoorPeople;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.util.IdCardUtil;

/**
 *
 */
@Component("poorPeopleDataConverter")
@Scope("prototype")
public class PoorPeopleDataConverter extends
		ThreeAbstractDataConverter<LedgerPoorPeople> {

	@Autowired
	private LedgerPoorPeopleDubboService ledgerPoorPeopleDubboService;

	@Override
	public ValidateResult validate(LedgerPoorPeople domain, int realRow) {
		return new ValidateResult();
	}

	@Override
	public LedgerPoorPeople persistenceDomain(LedgerPoorPeople domain) {
		domain.setStatus(ThreeRecordsIssueState.DEALING);
		if (StringUtil.isStringAvaliable(domain.getIdCardNo())) {
			domain.setBirthDay(IdCardUtil.parseBirthday(domain.getIdCardNo()));
		}
		return ledgerPoorPeopleDubboService.saveLedgerPoorPeople(domain);
	}

	@Override
	public void registerProcess(LedgerPoorPeople domain) {
		domain.setIsCanFeedBack(LedgerConstants.LEDGER_FEEDBACK_YES);
		ledgerPoorPeopleDubboService.registerProcess(domain);
	}

	@Override
	public LedgerPoorPeople updateDomain(LedgerPoorPeople domain) {
		ledgerPoorPeopleDubboService.updateLedgerPoorPeopleStatus(domain);
		return domain;
	}

	@Override
	public LedgerPoorPeople convertToDomain(String[] cellValues,
			ValidateResult result) {
		return null;
	}

}

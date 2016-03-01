package com.tianque.threeRecordsIssue.dataTrans.dataConvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.account.api.LedgerSteadyWorkPeopleInfoDubboService;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.LedgerSteadyWorkPeopleInfo;
import com.tianque.util.IdCardUtil;

/**
 *
 */
@Component("steadyWorkPeopleDataConverter")
@Scope("prototype")
public class SteadyWorkPeopleDataConverter extends
		ThreeAbstractDataConverter<LedgerSteadyWorkPeopleInfo> {

	@Autowired
	private LedgerSteadyWorkPeopleInfoDubboService ledgerSteadyWorkPeopleInfoDubboService;

	@Override
	public ValidateResult validate(LedgerSteadyWorkPeopleInfo domain,
			int realRow) {
		return new ValidateResult();
	}

	@Override
	public LedgerSteadyWorkPeopleInfo persistenceDomain(
			LedgerSteadyWorkPeopleInfo domain) {
		if (StringUtil.isStringAvaliable(domain.getIdCardNo())) {
			domain.setBirthDay(IdCardUtil.parseBirthday(domain.getIdCardNo()));
		}
		return ledgerSteadyWorkPeopleInfoDubboService
				.addLedgerSteadyWorkPeopleInfo(domain);
	}

	@Override
	public LedgerSteadyWorkPeopleInfo updateDomain(
			LedgerSteadyWorkPeopleInfo domain) {
		return domain;
	}

	@Override
	public LedgerSteadyWorkPeopleInfo convertToDomain(String[] cellValues,
			ValidateResult result) {
		// TODO Auto-generated method stub
		return null;
	}

}

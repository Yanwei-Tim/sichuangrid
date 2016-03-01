package com.tianque.threeRecordsIssue.dataTrans.dataConvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.account.api.LedgerPoorPeopleMemberDubboService;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.LedgerPoorPeopleMember;
import com.tianque.util.IdCardUtil;

/**
 *
 */
@Component("poorMemberDataConverter")
@Scope("prototype")
public class PoorMemberDataConverter extends
		ThreeAbstractDataConverter<LedgerPoorPeopleMember> {

	@Autowired
	private LedgerPoorPeopleMemberDubboService ledgerPoorPeopleMemberDubboService;

	@Override
	public ValidateResult validate(LedgerPoorPeopleMember domain, int realRow) {
		return new ValidateResult();
	}

	@Override
	public LedgerPoorPeopleMember persistenceDomain(
			LedgerPoorPeopleMember domain) {
		if (StringUtil.isStringAvaliable(domain.getIdCardNo())) {
			domain.setBirthday(IdCardUtil.parseBirthday(domain.getIdCardNo()));
		}
		return ledgerPoorPeopleMemberDubboService
				.addLedgerPoorPeopleMember(domain);
	}

	@Override
	public LedgerPoorPeopleMember updateDomain(LedgerPoorPeopleMember domain) {

		return domain;
	}

	@Override
	public LedgerPoorPeopleMember convertToDomain(String[] cellValues,
			ValidateResult result) {
		return null;
	}

}

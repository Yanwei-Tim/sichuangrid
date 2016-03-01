package com.tianque.threeRecordsIssue.dataTrans.dataConvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.account.api.ThreeRecordsIssueLogDubboService;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;

/**
 *
 */
@Component("threeRecordsIssueStepLogDataConverter")
@Scope("prototype")
public class ThreeRecordsIssueStepLogDataConverter extends
		ThreeAbstractDataConverter<ThreeRecordsIssueLogNew> {

	@Autowired
	private ThreeRecordsIssueLogDubboService threeRecordsIssueLogServiceImpl;

	@Override
	public ValidateResult validate(ThreeRecordsIssueLogNew domain, int realRow) {
		return new ValidateResult();
	}

	@Override
	public ThreeRecordsIssueLogNew persistenceDomain(
			ThreeRecordsIssueLogNew domain) {
		if (domain.getOperateTime() == null) {
			domain.setOperateTime(domain.getDealTime());
		}
		return threeRecordsIssueLogServiceImpl.addLog(domain);
	}

	@Override
	public ThreeRecordsIssueLogNew updateDomain(ThreeRecordsIssueLogNew domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ThreeRecordsIssueLogNew convertToDomain(String[] cellValues,
			ValidateResult result) {
		// TODO Auto-generated method stub
		return null;
	}

}

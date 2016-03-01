package com.tianque.threeRecordsIssue.dataTrans.dataConvert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.account.api.ThreeRecordsIssueProcessDubboService;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;

/**
 *
 */
@Component("threeRecordsIssueStepGroupDataConverter")
@Scope("prototype")
public class ThreeRecordsIssueStepGroupDataConverter extends
		ThreeAbstractDataConverter<ThreeRecordsIssueStepGroup> {

	@Autowired
	private ThreeRecordsIssueProcessDubboService threeRecordsIssueProcessServiceImpl;

	@Override
	public ValidateResult validate(ThreeRecordsIssueStepGroup domain,
			int realRow) {
		return new ValidateResult();
	}

	@Override
	public ThreeRecordsIssueStepGroup persistenceDomain(
			ThreeRecordsIssueStepGroup domain) {
		return threeRecordsIssueProcessServiceImpl.addIssueStepGroup(domain);
	}

	@Override
	public ThreeRecordsIssueStepGroup updateDomain(
			ThreeRecordsIssueStepGroup domain) {
		List<ThreeRecordsIssueStepGroup> list = threeRecordsIssueProcessServiceImpl
				.getIssueStepGroupByIssueId(domain.getLedgerId(), domain
						.getLedgerType());
		if (null != list && list.size() > 1) {
			ThreeRecordsIssueStepGroup isg = list.get(list.size() - 2);
			isg.setOutLog(domain.getEntyLog());
			threeRecordsIssueProcessServiceImpl.updateOutLog(isg);
		}
		if (null != list) {
			ThreeRecordsIssueStepGroup isg = list.get(list.size() - 1);
			isg.setOutLog(null);
			threeRecordsIssueProcessServiceImpl.updateOutLog(isg);
		}
		return null;
	}

	@Override
	public ThreeRecordsIssueStepGroup convertToDomain(String[] cellValues,
			ValidateResult result) {
		// TODO Auto-generated method stub
		return null;
	}

}

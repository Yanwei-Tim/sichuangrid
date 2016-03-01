package com.tianque.datatransfer.dataconvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.issueAbutmentJoint.domain.IssueJoint;
import com.tianque.issueAbutmentJoint.service.IssueJointService;

/**
 * @Description:事件对接导入
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-23 下午06:44:56
 */
@Component("issueJointDataConverter")
@Scope("prototype")
public class IssueJointDataConverter extends AbstractDataConverter<IssueJoint> {

	@Qualifier("issueJointValidator")
	@Autowired
	private DomainValidator<IssueJoint> issueJointValidator;
	@Autowired
	private IssueJointService issueJointService;

	@Override
	public ValidateResult validate(IssueJoint domain, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		ValidateResult result = issueJointValidator.validate(domain);
		return result;
	}

	@Override
	public IssueJoint persistenceDomain(IssueJoint domain) {
		return issueJointService.addIssueJointByImport(domain);
	}

	@Override
	public IssueJoint updateDomain(IssueJoint domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IssueJoint convertToDomain(String[] cellValues, ValidateResult result) {
		// TODO Auto-generated method stub
		return null;
	}

}

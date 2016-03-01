package com.tianque.fourTeams.fourTeamsIssue.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueEvaluate;

@Component("fourTeamsIssueEvaluateValidator")
public class FourTeamsIssueEvaluateValidatorImpl implements
		DomainValidator<FourTeamsIssueEvaluate> {

	@Autowired
	public ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(FourTeamsIssueEvaluate issueEvaluate) {
		ValidateResult result = new ValidateResult();
		if (issueEvaluate.getIssue() == null
				|| issueEvaluate.getIssue().getId() == null) {
			result.addErrorMessage("没有验证对象！");
		}
		if (issueEvaluate.getEvaluateType() == null) {
			result.addErrorMessage("请填写验证等级！");
		} else if (validateHelper.illegalNumberZZ(issueEvaluate
				.getEvaluateType().toString())
				|| issueEvaluate.getEvaluateType() < 1
				|| issueEvaluate.getEvaluateType() > 4) {
			result.addErrorMessage("评论类型只能是1-4的正整数！");
		}
		if (issueEvaluate.getEvaluateContent() == null
				|| "".equals(issueEvaluate.getEvaluateContent())) {
			result.addErrorMessage("请填写验证内容！");
		} else {
			if (validateHelper.illegalStringLength(0, 200, issueEvaluate
					.getEvaluateContent())) {
				result.addErrorMessage("名称不能大于200个字符");
			}
		}
		if (issueEvaluate.getEvaluateTime() == null
				|| "".equals(issueEvaluate.getEvaluateTime().toString())) {
			result.addErrorMessage("请选择验证时间！");
		}
		return result;
	}

}

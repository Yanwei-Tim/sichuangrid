package com.tianque.fourTeams.fourTeamsIssue.validator.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.validator.FourTeamsIssueOperationLogValidator;

@Component("fourTeamsDefaultIssueLogValidator")
public class FourTeamsDefaultIssueLogValidator extends FourTeamsAbstractValidator implements
		FourTeamsIssueOperationLogValidator {

	@Override
	public ValidateResult validate(FourTeamsIssueOperate operate, FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> files) {
		ValidateResult result = new ValidateResult();
		if (log.getDealOrg() == null || log.getDealOrg().getId() == null) {
			result.addErrorMessage("请输入处理部门!");
		}
		if (operate == null) {
			result.addErrorMessage("请选择处理类型!");
		}
		if (!StringUtil.isStringAvaliable(log.getDealUserName())) {
			result.addErrorMessage("请输入承办人!");
		}
		if (!StringUtil.isStringAvaliable(log.getMobile())) {
			result.addErrorMessage("请输入承办人联想手机!");
		}
		if (mustInputDealContent(operate)
				&& !StringUtil.isStringAvaliable(log.getContent())) {
			result.addErrorMessage("请输入处理意见!");
		}
		if (files != null && files.size() > 10) {
			result.addErrorMessage("事件处理附件最多只能上传10个!");
		}
		return result;
	}

	private boolean mustInputDealContent(FourTeamsIssueOperate operate) {
		if (!FourTeamsIssueOperate.REPORT_TO.equals(operate)
				&& !FourTeamsIssueOperate.CONCEPT.equals(operate)
				&& !FourTeamsIssueOperate.READ.equals(operate)
				&& !FourTeamsIssueOperate.CANCEL_SUPERVISE.equals(operate)
				&& !FourTeamsIssueOperate.HISTORIC.equals(operate)
				&& !FourTeamsIssueOperate.CANCEL_HISTORIC.equals(operate)
				&& !FourTeamsIssueOperate.URGENT.equals(operate)
				&& !FourTeamsIssueOperate.CANCEL_URGENT.equals(operate)) {
			return true;
		} else {
			return false;
		}
	}
}

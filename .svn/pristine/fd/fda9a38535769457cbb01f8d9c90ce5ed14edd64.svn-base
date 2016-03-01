package com.tianque.issue.validator.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.validator.IssueOperationLogValidator;
import com.tianque.sysadmin.service.PermissionService;

@Component("defaultIssueLogValidator")
public class DefaultIssueLogValidator extends AbstractValidator implements
		IssueOperationLogValidator {
	@Autowired
	private PermissionService permissionService;

	@Override
	public ValidateResult validate(IssueOperate operate, IssueLogNew log,
			List<IssueAttachFile> files) {
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
		// 如果是交办和上报，判断交办和上报的层级是否有可用用户
		if (operate.getCode() == IssueOperate.ASSIGN.getCode()
				|| operate.getCode() == IssueOperate.SUBMIT.getCode()) {
			if (permissionService.countAllLoginCount(log.getTargeOrg()
					.getId()) == 0) {
				result.addErrorMessage("事件处理的主办单位没有可办理的用户");
			}
		}
		return result;
	}

	private boolean mustInputDealContent(IssueOperate operate) {
		if (!IssueOperate.REPORT_TO.equals(operate)
				&& !IssueOperate.CONCEPT.equals(operate)
				&& !IssueOperate.READ.equals(operate)
				&& !IssueOperate.CANCEL_SUPERVISE.equals(operate)
				&& !IssueOperate.HISTORIC.equals(operate)
				&& !IssueOperate.CANCEL_HISTORIC.equals(operate)
				&& !IssueOperate.URGENT.equals(operate)
				&& !IssueOperate.CANCEL_URGENT.equals(operate)) {
			return true;
		} else {
			return false;
		}
	}
}

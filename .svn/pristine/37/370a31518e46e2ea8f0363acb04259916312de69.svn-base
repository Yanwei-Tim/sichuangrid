package com.tianque.fourTeams.fourTeamsIssue.validator.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.validator.FourTeamsIssueValidator;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("defaultFourTeamsIssueValidator")
public class DefaultFourTeamsIssueValidator extends FourTeamsAbstractValidator
		implements FourTeamsIssueValidator {
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private void validateIssueSubject(FourTeamsIssueNew issue, ValidateResult vr) {
		if (!StringUtil.isStringAvaliable(issue.getSubject())) {
			vr.addErrorMessage("请为该事件填写一个不小于2-50个字的主题!");
		} else if (issue.getSubject().length() < 2
				|| issue.getSubject().length() > 50) {
			vr.addErrorMessage("事件主题不能小于2个或大于50个字符!");
		}
		if (StringUtil.isStringAvaliable(issue.getSubject())
				&& !legalTitleString(issue.getSubject())) {
			vr.addErrorMessage("事件主题只能输入中英文、数字、引号、括号、空格、书名号、减号等字符!");
		}
	}

	private void validateIssueOccurLocation(FourTeamsIssueNew issue, ValidateResult vr) {
		if (!StringUtil.isStringAvaliable(issue.getOccurLocation())) {
			vr.addErrorMessage("请为该事件填写一个事发地点!");
		} else if (issue.getOccurLocation().length() > 50) {
			vr.addErrorMessage("事发地点不能大于50个字符!");
		}
		if (StringUtil.isStringAvaliable(issue.getOccurLocation())
				&& !legalAddressString(issue.getOccurLocation())) {
			vr.addErrorMessage("事发地点只能输入中英文、数字、括号、空格、减号、#号等字符!");
		}
	}

	private void validateIssueOccurDate(FourTeamsIssueNew issue, ValidateResult vr) {
		if (issue.getOccurDate() == null) {
			vr.addErrorMessage("请输该事件的发生时间!");
		} else if (CalendarUtil.now().before(issue.getOccurDate())) {
			vr.addErrorMessage("事件发生时间不能大于当前时间!");
		}
	}

	private void validateIssueMainCharacters(FourTeamsIssueNew issue, ValidateResult vr) {
		if (issue.getMainCharacters() != null) {
			if (issue.getMainCharacters().length() > 30) {
				vr.addErrorMessage("主要当事人不能大于30个字符!");
			}
			if (!legalNamesString(issue.getMainCharacters())) {
				vr.addErrorMessage("主要当事人中只能输入中英文、数字、逗号、顿号等字符!");
			}
		}
	}

	private void validateIssueRelatePeopleCount(FourTeamsIssueNew issue,
			ValidateResult vr) {
		if (issue.getRelatePeopleCount() != null) {
			if (issue.getRelatePeopleCount() < 0
					|| issue.getRelatePeopleCount() > 999999) {
				vr.addErrorMessage("涉及人数只能输入1到999999之间的整数!");
			}
		}
	}

	private void validateIssueContent(FourTeamsIssueNew issue, ValidateResult vr) {
		if (!StringUtil.isStringAvaliable(issue.getIssueContent())) {
			vr.addErrorMessage("请填写该事件的具体情况!");
		}
	}

	/*
	 * private void validateIssueKind(IssueNew issue,ValidateResult vr){ if
	 * (issue.getIssueKind() == null || issue.getIssueKind().getId() == null){
	 * vr.addErrorMessage("请为该事件选择选择一个正确的规模!"); }else { if
	 * (!propertyDictService.
	 * findPropertyDictByDomainName(PropertyTypes.ISSUE_KIND
	 * ).contains(issue.getIssueKind())){
	 * vr.addErrorMessage("请为该事件选择选择一个正确的规模!"); } } }
	 */

	private void validateIssueOccurOrg(FourTeamsIssueNew issue, ValidateResult vr) {
		if (issue.getOccurOrg() == null || issue.getOccurOrg().getId() == null) {
			vr.addErrorMessage("请为该事件选择一个乡镇(街道)及以下级别的发生地点所属部门!");
		} else {
			Organization org = organizationDubboService.getFullOrgById(issue
					.getOccurOrg().getId());
			if (OrganizationLevel.levelCompare(org.getOrgLevel()
					.getInternalId(), OrganizationLevel.TOWN) > 0) {
				vr.addErrorMessage("请为该事件选择一个乡镇(街道)及以下级别的发生地点所属部门!");
			}
		}
	}

	private void validateIssueTypes(FourTeamsIssueNew issue, ValidateResult vr) {
		if (issue.getIssueTypes() == null || issue.getIssueTypes().size() == 0) {
			vr.addErrorMessage("请至少为该事件选择一种事件类型!");
		}
	}

	@Override
	public ValidateResult validate(FourTeamsIssueNew issue) {
		ValidateResult result = new ValidateResult();
		validateIssueSubject(issue, result);
		// validateIssueOccurLocation(issue, result);
		validateIssueOccurDate(issue, result);
		validateIssueMainCharacters(issue, result);
		validateIssueRelatePeopleCount(issue, result);
		validateIssueContent(issue, result);
		// validateIssueKind(issue,result);
		validateIssueOccurOrg(issue, result);
		validateIssueTypes(issue, result);
		return result;
	}

	@Override
	public ValidateResult validateAttachFiles(List<FourTeamsIssueAttachFile> files) {
		ValidateResult result = new ValidateResult();
		if (files != null && files.size() > 10) {
			result.addErrorMessage("事件处理附件最多只能上传10个!");
		}
		return result;
	}

}

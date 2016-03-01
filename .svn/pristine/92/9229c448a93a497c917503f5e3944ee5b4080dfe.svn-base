package com.tianque.fourTeams.fourTeamsIssue.controller.strategy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.IssueType;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueConstants;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueTypes;
import com.tianque.fourTeams.fourTeamsIssue.controller.strategy.FourTeamsIssueManageStrategy;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.validator.FourTeamsIssueOperationLogValidator;
import com.tianque.fourTeams.fourTeamsIssue.validator.FourTeamsIssueValidator;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueTypeViewDefine;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("fourTeamsDefaultIssueManageStrategy")
public class FourTeamsDefaultIssueManageStrategy implements FourTeamsIssueManageStrategy {
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private PropertyDictService propertyDictService;

	private FourTeamsIssueValidator validator;

	private FourTeamsIssueOperationLogValidator logValidator;

	private PropertyDict getIssueSourceKind() {
		return propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.SOURCE_KIND, FourTeamsIssueConstants.MANUAL_INPUT);
	}

	private Long getCurrentLoginedOrgId() {
		return ThreadVariable.getUser().getOrganization().getId();
	}

	@Override
	public String forwardToAdd() {
		return "addIssue";
	}

	@Override
	public String forwardToEdit() {
		return "editIssue";
	}

	@Override
	public String forwardToAddEventSourceIssue() {
		return "event";
	}

	@Override
	public List<FourTeamsIssueTypeViewDefine> getIssueTypeNames() {
		List<FourTeamsIssueTypeViewDefine> result = new ArrayList<FourTeamsIssueTypeViewDefine>();
		/*
		 * result.add(new IssueTypeViewDefine(IssueTypes.CONTRADICTION, 4, 3,
		 * 650)); result.add(new IssueTypeViewDefine(IssueTypes.SECURITYTROUBLE,
		 * 5)); result.add(new
		 * IssueTypeViewDefine(IssueTypes.PEOPLELIVE_SERVICE)); result.add(new
		 * IssueTypeViewDefine(IssueTypes.OTHERTYPE, 3)); result.add(new
		 * IssueTypeViewDefine(IssueTypes.ITEM, 3));
		 */
		result.add(new FourTeamsIssueTypeViewDefine(FourTeamsIssueTypes.PEOPLELIVE_SERVICE));
		result
				.add(new FourTeamsIssueTypeViewDefine(
						FourTeamsIssueTypes.RESOLVETHECONTRADICTIONS));
		result.add(new FourTeamsIssueTypeViewDefine(FourTeamsIssueTypes.SECURITYPRECAUTIONS));
		result.add(new FourTeamsIssueTypeViewDefine(FourTeamsIssueTypes.SPECIALPOPULATIONS));
		result.add(new FourTeamsIssueTypeViewDefine(FourTeamsIssueTypes.SOCIALCONDITIONS));
		result.add(new FourTeamsIssueTypeViewDefine(FourTeamsIssueTypes.POLICIESANDLAWS));
		result.add(new FourTeamsIssueTypeViewDefine(FourTeamsIssueTypes.EMERGENCIES));
		result.add(new FourTeamsIssueTypeViewDefine(FourTeamsIssueTypes.OTHERMANAGE));
		return result;
	}

	@Override
	public Map<String, List<IssueType>> loadEnabledIssueTypes(
			List<FourTeamsIssueTypeViewDefine> issueTypeNames) {
		Map<String, List<IssueType>> result = new HashMap<String, List<IssueType>>();
		for (FourTeamsIssueTypeViewDefine type : issueTypeNames) {
			result.put(type.getTypeName(), issueTypeService
					.findEnabledIssueTypesByParentName(
							getCurrentLoginedOrgId(), type.getTypeName()));
		}
		return result;
	}

	@Override
	public ValidateResult validate(FourTeamsIssueNew issue, List<FourTeamsIssueAttachFile> files) {
		ValidateResult result = validator.validate(issue);
		result.addAllErrorMessage(validator.validateAttachFiles(files));
		return result;
	}

	@Override
	public void setValidator(FourTeamsIssueValidator validator) {
		this.validator = validator;
	}

	@Override
	public void setLogValidator(FourTeamsIssueOperationLogValidator validator) {
		this.logValidator = validator;
	}

	@Override
	public void fillIssueSourceProperty(FourTeamsIssueNew issue) {
		issue.setSourceKind(getIssueSourceKind());
	}

	@Override
	public String forwardToViewDetail() {
		return "default_view";
	}

	@Override
	public String forwardToEmptyViewDetail() {
		return "empty_view";
	}

	@Override
	public String forwardToDownLoadAttachFile() {
		return "streamSuccess";
	}

	@Override
	public String forwardToDealing() {
		return "default_dealing";
	}

	@Override
	public ValidateResult validateDealLog(FourTeamsIssueOperate operate,
			FourTeamsIssueLogNew log, List<FourTeamsIssueAttachFile> files) {
		return logValidator.validate(operate, log, files);
	}

	@Override
	public String forwardToView() {
		return "viewIssue";
	}

}

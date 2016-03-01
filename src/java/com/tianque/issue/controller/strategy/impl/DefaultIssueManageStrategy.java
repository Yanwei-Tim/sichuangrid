package com.tianque.issue.controller.strategy.impl;

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
import com.tianque.issue.constants.IssueConstants;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.controller.strategy.IssueManageStrategy;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.validator.IssueOperationLogValidator;
import com.tianque.issue.validator.IssueValidator;
import com.tianque.issue.vo.IssueTypeViewDefine;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("defaultIssueManageStrategy")
public class DefaultIssueManageStrategy implements IssueManageStrategy {
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private PropertyDictService propertyDictService;

	private IssueValidator validator;

	private IssueOperationLogValidator logValidator;

	private PropertyDict getIssueSourceKind() {
		return propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.SOURCE_KIND, IssueConstants.MANUAL_INPUT);
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
	public List<IssueTypeViewDefine> getIssueTypeNames() {
		List<IssueTypeViewDefine> result = new ArrayList<IssueTypeViewDefine>();
		/*
		 * result.add(new IssueTypeViewDefine(IssueTypes.CONTRADICTION, 4, 3,
		 * 650)); result.add(new IssueTypeViewDefine(IssueTypes.SECURITYTROUBLE,
		 * 5)); result.add(new
		 * IssueTypeViewDefine(IssueTypes.PEOPLELIVE_SERVICE)); result.add(new
		 * IssueTypeViewDefine(IssueTypes.OTHERTYPE, 3)); result.add(new
		 * IssueTypeViewDefine(IssueTypes.ITEM, 3));
		 */
		result.add(new IssueTypeViewDefine(IssueTypes.PEOPLELIVE_SERVICE));
		result.add(new IssueTypeViewDefine(IssueTypes.RESOLVETHECONTRADICTIONS));
		result.add(new IssueTypeViewDefine(IssueTypes.SECURITYPRECAUTIONS));
		result.add(new IssueTypeViewDefine(IssueTypes.SPECIALPOPULATIONS));
		result.add(new IssueTypeViewDefine(IssueTypes.SOCIALCONDITIONS));
		result.add(new IssueTypeViewDefine(IssueTypes.POLICIESANDLAWS));
		result.add(new IssueTypeViewDefine(IssueTypes.EMERGENCIES));
		result.add(new IssueTypeViewDefine(IssueTypes.OTHERMANAGE));
		return result;
	}

	@Override
	public Map<String, List<IssueType>> loadEnabledIssueTypes(
			List<IssueTypeViewDefine> issueTypeNames) {
		Map<String, List<IssueType>> result = new HashMap<String, List<IssueType>>();
		for (IssueTypeViewDefine type : issueTypeNames) {
			result.put(type.getTypeName(), issueTypeService
					.findEnabledIssueTypesByParentName(
							getCurrentLoginedOrgId(), type.getTypeName()));
		}
		return result;
	}

	@Override
	public ValidateResult validate(IssueNew issue, List<IssueAttachFile> files) {
		ValidateResult result = validator.validate(issue);
		result.addAllErrorMessage(validator.validateAttachFiles(files));
		return result;
	}

	@Override
	public void setValidator(IssueValidator validator) {
		this.validator = validator;
	}

	@Override
	public void setLogValidator(IssueOperationLogValidator validator) {
		this.logValidator = validator;
	}

	@Override
	public void fillIssueSourceProperty(IssueNew issue) {
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
	public ValidateResult validateDealLog(IssueOperate operate,
			IssueLogNew log, List<IssueAttachFile> files) {
		return logValidator.validate(operate, log, files);
	}

	@Override
	public String forwardToView() {
		return "viewIssue";
	}

	@Override
	public String forwardToWorkBenchDeal() {
		return "workBenchDeal";
	}
}

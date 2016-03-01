package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkDiaryTypes;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.service.IssueLogService;
import com.tianque.service.IssueNewService;
import com.tianque.service.impl.IssueBusinessDelegate;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.service.WorkDiaryService;

@Controller("issueBusinessController")
@Scope("prototype")
@SuppressWarnings("serial")
@Transactional
public class IssueBusinessController extends BaseAction {
	private IssueNew issue;
	private IssueViewObject issueViewObject;
	private IssueLogNew issueLog;
	private List<IssueOperate> canDoList;
	private String targeOrg;
	private String tellOrgIds;
	private String[] attachFiles;
	private Long stepId;
	private int dealType = -1;
	private IssueOperate operate;

	private String tag;
	private boolean searchAdmin;
	private String exceptOrgIds;
	private List<AutoCompleteData> transferTargets = new ArrayList<AutoCompleteData>();
	private AutoCompleteData uniqueAdminTarget;

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	// @Autowired
	// private IssueLogService issueLogService;
	@Autowired
	private IssueNewService issueService;
	@Autowired
	private IssueLogService issueLogService;
	@Autowired
	private IssueBusinessDelegate issueBusinessDelegate;
	// @Autowired
	// private TargeOrgManager targeOrgManager;
	@Autowired
	private WorkDiaryService workDiaryService;
	@Autowired
	private PropertyDictService propertyDictService;

	public String dispatchDeal() throws Exception {
		if (null == stepId) {
			appendErrorMessage("参数不正确!");
			return ERROR;
		}
		IssueStep step = issueService.getIssueStepById(stepId);
		if (!getCurrentUser().getOrganization().equals(step.getTarget())) {
			appendErrorMessage("参数不正确!");
		}
		issueLog = new IssueLogNew();
		issueLog.setIssue(step.getIssue());
		fillDealUserAndOrg(null);
		if (isSuperviseOperate()) {
			return "toSupervise";
		} else if (isInstructOperate()) {
			return "toInstruct";
		} else {
			canDoList = issueService.getCurrentLoginedOrgCanDo(
					issueLog.getIssue(), issueLog.getDealOrg());
			if (step.getBackTo() == null) {
				canDoList.remove(IssueOperate.BACK);
			}
			canDoList.remove(IssueOperate.CANCEL_SUPERVISE);
			canDoList.remove(IssueOperate.CONCEPT);
			canDoList.remove(IssueOperate.INSTRUCT);
			canDoList.remove(IssueOperate.NORMAL_SUPERVISE);
			canDoList.remove(IssueOperate.READ);
			canDoList.remove(IssueOperate.REPORT_TO);
			canDoList.remove(IssueOperate.YELLOW_SUPERVISE);
			canDoList.remove(IssueOperate.RED_SUPERVISE);
			return canDoList.size() > 0 ? "deal" : "simpleDeal";
		}
	}

	public String dealIssue() throws Exception {
		operate = IssueOperate.parse(dealType);
		if (!validateDealInput()) {
			return ERROR;
		}
		issue = issueService.getFullIssueByIssueStepId(stepId);
		IssueStep step = issueService.getIssueStepById(stepId);
		if (IssueOperate.REPORT_TO.equals(operate)) {
			issueViewObject = issueService.reportToCommandCenter(issue, step,
					issueLog);
		} else if (IssueOperate.GIVETO.equals(operate)) {
			issueViewObject = issueService.giveTo(issue, step, issueLog,
					conversionStringsToLongList(tellOrgIds), attachFiles);
		} else if (IssueOperate.CONCEPT.equals(operate)) {
			issueViewObject = issueService.concept(issue, step, issueLog);
		} else if (IssueOperate.READ.equals(operate)) {
			issueViewObject = issueService.read(issue, step, issueLog);
		} else if (IssueOperate.COMMENT.equals(operate)) {
			issueViewObject = issueService.comment(issue, step, issueLog,
					attachFiles);
		} else if (IssueOperate.COMPLETE.equals(operate)) {
			issueViewObject = issueService.complete(issue, step, issueLog,
					attachFiles);
		} else if (IssueOperate.ASSIGN.equals(operate)) {
			issueViewObject = issueService.assign(issue, step, issueLog,
					conversionStringsToLongList(tellOrgIds), attachFiles);
		} else if (IssueOperate.SUBMIT.equals(operate)) {
			issueViewObject = issueService.submit(issue, step, issueLog,
					conversionStringsToLongList(tellOrgIds), attachFiles);
		} else if (IssueOperate.BACK.equals(operate)) {
			issueViewObject = issueService.back(issue, step, issueLog,
					attachFiles);
		}
		if (mustInputDealContent()) {
			String content = workDiaryService.assemblingContent(
					issue.getSubject(), operate.toString(),
					issueLog.getContent(), WorkDiaryTypes.ISSUE_DEAL, "", "");
			workDiaryService.addWorkDiary(propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.WORKDIARY_DIARY_TYPE,
							WorkDiaryTypes.ISSUE_DEAL),
					WorkDiaryTypes.TYPE_ISSUEBUSINESS, issue.getId(), content,
					issue.getOccurLocation(), issueLog.getDealUserName(),
					CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		}
		return SUCCESS;
	}

	// @PermissionFilter(ename =
	// "normalIssue,yellowCardIssue,redCardIssue,normalJurisdictionsIssue,yellowCardJurisdictionsIssue,redCardJurisdictionsIssue")
	@PermissionFilters({
			@PermissionFilter(ename = "normalIssue", actionName = "supervise"),
			@PermissionFilter(ename = "yellowCardIssue", actionName = "supervise"),
			@PermissionFilter(ename = "redCardIssue", actionName = "supervise"),
			@PermissionFilter(ename = "normalJurisdictionsIssue", actionName = "supervise"),
			@PermissionFilter(ename = "yellowCardJurisdictionsIssue", actionName = "supervise"),
			@PermissionFilter(ename = "redCardJurisdictionsIssue", actionName = "supervise") })
	public String supervise() throws Exception {
		operate = IssueOperate.parse(dealType);
		IssueStep step = issueService.getIssueStepById(stepId);
		if (IssueOperate.NORMAL_SUPERVISE.equals(operate)) {
			issueViewObject = issueService.normalSupervise(step, issueLog);
		} else if (IssueOperate.YELLOW_SUPERVISE.equals(operate)) {
			issueViewObject = issueService.yellowSupervise(step, issueLog);
		} else if (IssueOperate.RED_SUPERVISE.equals(operate)) {
			issueViewObject = issueService.redSupervise(step, issueLog);
		} else {
			errorMessage = "未知的督办类型";
			return ERROR;
		}
		return SUCCESS;
	}

	// @PermissionFilter(ename =
	// "cancleSuperviseIssue,cancleSuperviseJurisdictionsIssue")
	@PermissionFilters({
			@PermissionFilter(ename = "cancleSuperviseIssue", actionName = "cancelSupervise"),
			@PermissionFilter(ename = "cancleSuperviseJurisdictionsIssue", actionName = "cancelSupervise") })
	public String cancelSupervise() throws Exception {
		IssueStep step = issueService.getIssueStepById(stepId);
		issueViewObject = issueService.cancelSupervise(step);
		return SUCCESS;
	}

	// @PermissionFilter(ename = "commandIssue,commandJurisdictionsIssue")
	@PermissionFilters({
			@PermissionFilter(ename = "commandIssue", actionName = "instruct"),
			@PermissionFilter(ename = "commandJurisdictionsIssue", actionName = "instruct") })
	public String instruct() throws Exception {
		IssueStep step = issueService.getIssueStepById(stepId);
		issueViewObject = issueService.instruct(step, issueLog);
		return SUCCESS;
	}

	public String searchTransferTarget() throws Exception {
		List<Organization> orgs = issueBusinessDelegate
				.findTransferTargetsByName(IssueOperate.parse(dealType),
						stepId, tag, this.exceptOrgIds, this.searchAdmin);
		for (Organization org : orgs) {
			transferTargets.add(convertOrg2AutoCompleteData(org));
		}
		return SUCCESS;
	}

	public String searchTellTarget() throws Exception {
		List<Organization> orgs = issueBusinessDelegate.findTellTargetsByName(
				IssueOperate.parse(dealType), stepId, tag, this.exceptOrgIds);
		for (Organization org : orgs) {
			transferTargets.add(convertOrg2AutoCompleteData(org));
		}
		return SUCCESS;
	}

	public String getUniqueTrgetAdminOrg() throws Exception {
		operate = IssueOperate.parse(dealType);
		IssueStep step = issueService.getIssueStepById(stepId);
		if (IssueOperate.SUBMIT.equals(operate)) {
			Organization org = organizationDubboService.getSimpleOrgById(step
					.getTarget().getId());
			uniqueAdminTarget = convertOrg2AutoCompleteData(org.getParentOrg() != null ? organizationDubboService
					.getSimpleOrgById(org.getParentOrg().getId()) : null);
		} else if (IssueOperate.ASSIGN.equals(operate)) {
			List<Organization> orgs = organizationDubboService
					.findAdminOrgsByParentId(step.getTarget().getId());
			if (orgs != null && orgs.size() > 0) {
				uniqueAdminTarget = convertOrg2AutoCompleteData(orgs.get(0));
			}
		}
		return SUCCESS;
	}

	private AutoCompleteData convertOrg2AutoCompleteData(Organization org) {
		if (org != null) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setValue(org.getId().toString());
			autoCompleteData.setLabel(org.getOrgName());
			autoCompleteData.setDesc(org.getRemark());
			return autoCompleteData;
		}
		return null;

	}

	private void fillDealUserAndOrg(Organization org) {
		issueLog.setDealUserName(ThreadVariable.getUser().getName());
		issueLog.setMobile(ThreadVariable.getUser().getMobile());
		issueLog.setDealOrg(ThreadVariable.getSession().getOrganization());
		// issueLog.setDealOrgInternalCode(org.getOrgInternalCode());
	}

	private User getCurrentUser() {
		return permissionService.getFullUserById(ThreadVariable.getSession()
				.getUserId());
	}

	private boolean validateDealInput() {
		boolean result = true;
		if (issueLog.getDealOrg() == null
				|| issueLog.getDealOrg().getId() == null || operate == null) {
			appendErrorMessage("参数不正确!");
			result = false;
		}
		if (!StringUtil.isStringAvaliable(issueLog.getDealUserName())) {
			appendErrorMessage("处理人不能为空!");
			result = false;
		}
		if (mustInputDealContent() && issueLog.getContent() == null) {
			appendErrorMessage("意见不能为空!");
			result = false;
		}
		return result;
	}

	private List<Long> conversionStringsToLongList(String ids) {
		List<Long> list = null;
		if (ids != null) {
			list = new ArrayList<Long>();
			String[] strs = ids.split(",");
			if (strs.length < 0) {
				return null;
			}

			for (String str : strs) {
				if (StringUtil.isStringAvaliable(str.trim())) {
					list.add(Long.valueOf(str.trim()));
				}
			}
		}
		return list;
	}

	private boolean isSuperviseOperate() {
		operate = operate == null ? IssueOperate.parse(dealType) : operate;
		if (operate != null
				&& (IssueOperate.NORMAL_SUPERVISE.equals(operate)
						|| IssueOperate.YELLOW_SUPERVISE.equals(operate) || IssueOperate.RED_SUPERVISE
						.equals(operate))) {
			return true;
		}
		return false;
	}

	private boolean isInstructOperate() {
		operate = operate == null ? IssueOperate.parse(dealType) : operate;
		if (operate != null && IssueOperate.INSTRUCT.equals(operate)) {
			return true;
		}
		return false;
	}

	private boolean mustInputDealContent() {
		if (!IssueOperate.REPORT_TO.equals(operate)
				&& !IssueOperate.CANCEL_SUPERVISE.equals(operate)
				&& !IssueOperate.CONCEPT.equals(operate)
				&& !IssueOperate.NORMAL_SUPERVISE.equals(operate)
				&& !IssueOperate.READ.equals(operate)
				&& !IssueOperate.RED_SUPERVISE.equals(operate)
				&& !IssueOperate.YELLOW_SUPERVISE.equals(operate)) {
			return true;
		} else {
			return false;
		}
	}

	public String conceptIssue() throws Exception {
		IssueLogNew issueLogNews = issueLogService.getFullIssueLogById(issueLog
				.getId());
		issueLog.setIssue(issueService.getSimpleIssueById(issueLogNews
				.getIssue().getId()));
		issueLog = issueLogService.concept(issueLog);
		issueViewObject = issueService.getIssueViewObjectById(issueLog.getId());
		return SUCCESS;
	}

	public IssueNew getIssue() {
		return issue;
	}

	public void setIssue(IssueNew issue) {
		this.issue = issue;
	}

	public IssueLogNew getIssueLog() {
		return issueLog;
	}

	public void setIssueLog(IssueLogNew issueLog) {
		this.issueLog = issueLog;
	}

	public List<IssueOperate> getCanDoList() {
		return canDoList;
	}

	public void setCanDoList(List<IssueOperate> canDoList) {
		this.canDoList = canDoList;
	}

	public String getTargeOrg() {
		return targeOrg;
	}

	public void setTargeOrg(String targeOrg) {
		this.targeOrg = targeOrg;
	}

	public String getTellOrgIds() {
		return tellOrgIds;
	}

	public void setTellOrgIds(String tellOrgIds) {
		this.tellOrgIds = tellOrgIds;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public IssueViewObject getIssueViewObject() {
		return issueViewObject;
	}

	public void setIssueViewObject(IssueViewObject issueViewObject) {
		this.issueViewObject = issueViewObject;
	}

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	public int getDealType() {
		return dealType;
	}

	public void setDealType(int dealType) {
		this.dealType = dealType;
	}

	public boolean isSearchAdmin() {
		return searchAdmin;
	}

	public void setSearchAdmin(boolean searchAdmin) {
		this.searchAdmin = searchAdmin;
	}

	public String getExceptOrgIds() {
		return exceptOrgIds;
	}

	public void setExceptOrgIds(String exceptOrgIds) {
		this.exceptOrgIds = exceptOrgIds;
	}

	public List<AutoCompleteData> getTransferTargets() {
		return transferTargets;
	}

	public void setTransferTargets(List<AutoCompleteData> transferTargets) {
		this.transferTargets = transferTargets;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public AutoCompleteData getUniqueAdminTarget() {
		return uniqueAdminTarget;
	}

	public void setUniqueAdminTarget(AutoCompleteData uniqueAdminTarget) {
		this.uniqueAdminTarget = uniqueAdminTarget;
	}

	public IssueOperate getOperate() {
		return operate;
	}

}

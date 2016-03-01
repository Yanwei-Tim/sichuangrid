package com.tianque.fourTeams.fourTeamsIssue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.fourTeams.fourTeamsIssue.FourTeamsIssueHelper;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueLogDao;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueProcessDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueSkiprule;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.event.FourTeamsIssueChangeEvent;
import com.tianque.fourTeams.fourTeamsIssue.event.listener.FourTeamsIssueChangeListener;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueSkipruleService;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueWorkFlowEngine;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperationContext;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueSourceState;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueState;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepInfo;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsOrganizationInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

public abstract class FourTeamsAbstractIssueWorkFlowEngineImpl extends
		AbstractBaseService implements FourTeamsIssueWorkFlowEngine {
	@Autowired
	protected FourTeamsIssueProcessDao issueProcessDao;
	@Autowired
	protected OrganizationDubboService organizationDubboService;
	@Autowired
	protected PropertyDictService propertyDictService;
	@Autowired
	private FourTeamsIssueSkipruleService issueSkipruleService;
	@Autowired
	private FourTeamsIssueLogDao issueLogDao;

	/***
	 * 创建事件处理步骤实对象
	 * 
	 * @param issue
	 *            事件
	 * @return
	 */
	protected abstract FourTeamsIssueStep createEntryIssueStep(
			FourTeamsIssueNew issue);

	/***
	 * 获取事件监听器类
	 * 
	 * @return
	 */
	protected abstract List<FourTeamsIssueChangeListener> getIssueChangeListeners();

	@Override
	public FourTeamsIssueStep register(FourTeamsIssueNew issue) {
		issue.setCreateOrg(organizationDubboService.getFullOrgById(issue
				.getCreateOrg().getId()));
		issue.setOccurOrg(organizationDubboService.getFullOrgById(issue
				.getOccurOrg().getId()));
		FourTeamsIssueStep result = issueProcessDao
				.addIssueStep(createEntryIssueStep(issue));
		FourTeamsIssueStepGroup issueStepGroup = new FourTeamsIssueStepGroup();
		issueStepGroup.setKeyStep(result);
		issueStepGroup.setIssue(issue);
		fetchSourceAndTargetOrg(result);
		fireIssueEntry(issue, result);
		fireIssueGroup(issueStepGroup);
		return result;
	}

	@Override
	public boolean unRegister(Long issueId) {
		issueProcessDao.deleteIssueStepsByIssueId(issueId);
		return true;
	}

	@Override
	public FourTeamsIssueStep getFullIssueStepById(Long stepId) {
		FourTeamsIssueStep step = getSimpleIssueStepById(stepId);
		fetchSourceAndTargetOrg(step);
		return step;
	}

	@Override
	public FourTeamsIssueStep complete(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> attachFiles) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null);
			FourTeamsIssueHelper.constructIssueStateFromStep(step).complete(
					issue, step, context);
			issueProcessDao.updateIssueStepExceptOrg(step);
			FourTeamsIssueStep result = getFullIssueStepById(step.getId());
			fireIssueComplete(issue, result, log, attachFiles);
			return result;
		} catch (Exception e) {
			throw new ServiceValidationException("办结过程中发生错误", e);
		}
	}

	@Override
	public FourTeamsIssueStep submit(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log, Long targetOrg,
			Long[] tells, List<FourTeamsIssueAttachFile> attachFiles) {
		try {
			FourTeamsIssueOperationContext context = constructOperationContext(
					targetOrg, tells);
			FourTeamsIssueSkiprule issueSkiprule = issueSkipruleService
					.getFourTeamsIssueSkipruleByIssue(issue);
			FourTeamsIssueStepGroup steps = saveStepGroup(FourTeamsIssueHelper
					.constructIssueStateFromStep(step).submit(issue, step,
							context, issueSkiprule));
			step.setSubmit(FourTeamsIssueSourceState.submit);
			step = getFullIssueStepById(issueProcessDao
					.updateIssueStepExceptOrg(step).getId());
			fireIssueChanged(issue, steps, FourTeamsIssueOperate.SUBMIT, log,
					attachFiles);
			fireIssueGroup(steps);
			return steps.getKeyStep();
		} catch (Exception e) {
			throw new ServiceValidationException("上报过程中发生错误", e);
		}
	}

	@Override
	public FourTeamsIssueStep giveTo(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log, Long targetOrg,
			Long[] tells, List<FourTeamsIssueAttachFile> attachFiles) {
		try {
			FourTeamsIssueOperationContext context = constructOperationContext(
					targetOrg, tells);
			FourTeamsIssueStepGroup steps = saveStepGroup(FourTeamsIssueHelper
					.constructIssueStateFromStep(step).giveTo(issue, step,
							context));
			step = getFullIssueStepById(issueProcessDao
					.updateIssueStepExceptOrg(step).getId());
			fireIssueChanged(issue, steps, FourTeamsIssueOperate.SUBMIT, log,
					attachFiles);
			fireIssueGroup(steps);
			return steps.getKeyStep();
		} catch (Exception e) {
			throw new ServiceValidationException("上报过程中发生错误", e);
		}
	}

	@Override
	public FourTeamsIssueStep assign(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log, Long targetOrg,
			Long[] tells, List<FourTeamsIssueAttachFile> attachFiles) {
		try {
			FourTeamsIssueOperationContext context = constructOperationContext(
					targetOrg, tells);
			FourTeamsIssueStepGroup steps = saveStepGroup(FourTeamsIssueHelper
					.constructIssueStateFromStep(step).assign(issue, step,
							context));
			step = getFullIssueStepById(issueProcessDao
					.updateIssueStepExceptOrg(step).getId());
			fireIssueChanged(issue, steps, FourTeamsIssueOperate.ASSIGN, log,
					attachFiles);
			fireIssueGroup(steps);
			return steps.getKeyStep();
		} catch (Exception e) {
			throw new ServiceValidationException("交办过程中发生错误", e);
		}
	}

	@Override
	public FourTeamsIssueStep concept(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					loadFullCurrentLoginedOrganization(), null);
			FourTeamsIssueHelper.constructIssueStateFromStep(step).concept(
					issue, step, context);
			step = getFullIssueStepById(issueProcessDao
					.updateIssueStepExceptOrg(step).getId());
			fireIssueChanged(issue, step, FourTeamsIssueOperate.CONCEPT, log,
					new ArrayList<FourTeamsIssueAttachFile>());
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("受理过程中发生错误", e);
		}
	}

	@Override
	public FourTeamsIssueStep back(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> attachFiles) {
		try {
			FourTeamsIssueStep backTo = getSimpleIssueStepById(step.getBackTo()
					.getId());
			step.setBackTo(backTo);
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					loadFullOrganization(backTo.getTarget().getId()), null);
			FourTeamsIssueStepGroup steps = saveStepGroup(FourTeamsIssueHelper
					.constructIssueStateFromStep(step).back(issue, step,
							context));
			step = getFullIssueStepById(issueProcessDao
					.updateIssueStepExceptOrg(step).getId());
			fireIssueChanged(issue, steps, FourTeamsIssueOperate.BACK, log,
					attachFiles);
			fireIssueGroup(steps);
			return steps.getKeyStep();
		} catch (Exception e) {
			throw new ServiceValidationException("回退时发生错误", e);
		}
	}

	@Override
	public FourTeamsIssueStep read(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					loadFullCurrentLoginedOrganization(), null);
			FourTeamsIssueHelper.constructIssueStateFromStep(step).read(issue,
					step, context);
			step = getFullIssueStepById(issueProcessDao
					.updateIssueStepExceptOrg(step).getId());
			fireIssueChanged(issue, step, FourTeamsIssueOperate.READ, log,
					new ArrayList<FourTeamsIssueAttachFile>());
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("阅读过程中发生错误", e);
		}
	}

	@Override
	public FourTeamsIssueStep reportTo(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					getNearestCommandCenter(step.getTarget()), null);
			FourTeamsIssueStep newStep = FourTeamsIssueHelper
					.constructIssueStateFromStep(step).reportTo(issue, step,
							context);
			newStep = addAndReloadFullIssueStep(newStep);
			issueProcessDao.updateIssueStepExceptOrg(step);
			FourTeamsIssueStepGroup steps = new FourTeamsIssueStepGroup();
			steps.setKeyStep(newStep);
			steps.setIssue(issue);
			fireIssueChanged(issue, newStep, FourTeamsIssueOperate.REPORT_TO,
					log, new ArrayList<FourTeamsIssueAttachFile>());
			fireIssueGroup(steps);
			return newStep;
		} catch (Exception e) {
			throw new ServiceValidationException("上报指挥中心过程中发生错误", e);
		}
	}

	@Override
	public FourTeamsIssueStep comment(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> attachFiles) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					loadFullCurrentLoginedOrganization(), null);
			FourTeamsIssueHelper.constructIssueStateFromStep(step).comment(
					issue, step, context);
			step = getFullIssueStepById(issueProcessDao
					.updateIssueStepExceptOrg(step).getId());
			fireIssueChanged(issue, step, FourTeamsIssueOperate.COMMENT, log,
					attachFiles);
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("填写处理意见过程中发生错误", e);
		}
	}

	@Override
	public FourTeamsIssueStep instruct(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					organizationDubboService
							.getFullOrgById(step.getTarget().getId()),
					null);
			FourTeamsIssueHelper.constructIssueStateFromStep(step).instruct(
					issue, step, context);
			fireIssueChanged(issue, step, FourTeamsIssueOperate.INSTRUCT, log,
					new ArrayList<FourTeamsIssueAttachFile>());
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("填写处理意见过程中发生错误", e);
		}
	}

	@Override
	public FourTeamsIssueStep cancelSupervise(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					organizationDubboService
							.getFullOrgById(step.getTarget().getId()),
					null);
			FourTeamsIssueHelper.constructIssueStateFromStep(step)
					.cancelSupervise(issue, step, context);
			step = getFullIssueStepById(issueProcessDao
					.updateIssueStepExceptOrg(step).getId());
			fireIssueChanged(issue, step,
					FourTeamsIssueOperate.CANCEL_SUPERVISE, log,
					new ArrayList<FourTeamsIssueAttachFile>());
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("填写处理意见过程中发生错误", e);
		}
	}

	@Override
	public FourTeamsIssueStep supervise(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			FourTeamsIssueOperate type) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					organizationDubboService
							.getFullOrgById(step.getTarget().getId()),
					null);
			context.addParameter(
					FourTeamsIssueOperationContext.SUPERVISE_LEVEL_KEY,
					getSuperviseLevel(type));
			FourTeamsIssueHelper.constructIssueStateFromStep(step).supervise(
					issue, step, context);
			step = getFullIssueStepById(issueProcessDao
					.updateIssueStepExceptOrg(step).getId());
			fireIssueChanged(issue, step, type, log,
					new ArrayList<FourTeamsIssueAttachFile>());
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("督办过程中发生错误", e);
		}
	}

	@Override
	public void fireIssueChanged(FourTeamsIssueNew issue,
			FourTeamsIssueOperate operate, FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> attachFiles) {
		FourTeamsIssueChangeEvent event = new FourTeamsIssueChangeEvent(log,
				attachFiles, operate);
		if (getIssueChangeListeners() != null) {
			for (FourTeamsIssueChangeListener listener : getIssueChangeListeners()) {
				listener.onChanged(issue, null, event);
			}
		}
	}

	@Override
	public List<FourTeamsIssueOperate> getIssueCandoForOrg(Long stepId,
			Organization org) {
		FourTeamsIssueStep step = getSimpleIssueStepById(stepId);
		fetchFullSourceAndTargetOrg(step);
		org = loadFullOrganization(org.getId());
		FourTeamsIssueStepInfo si = new FourTeamsIssueStepInfo();
		si.setOperationOrg(step.getTarget());
		si.setSuperviseLevel(step.getSuperviseLevel());
		FourTeamsOrganizationInfo oi = new FourTeamsOrganizationInfo();
		oi.setLeafOrg((org.getSubCount() + org.getSubCountFun()) == 0);
		oi.setTopOrg(org.getParentOrg() == null);
		oi.setOrganization(org);
		try {
			return FourTeamsIssueHelper.constructIssueStateFromStep(step)
					.getCando(si, oi);
		} catch (Exception e) {
			throw new ServiceValidationException("获取可以进行的操作时发生以下错误", e);
		}
	}

	@Override
	public PageInfo<AutoCompleteData> findAdminTargetsByName(Long stepid,
			FourTeamsIssueOperate operate, String tag, Long[] exceptId,
			int page, int rows) {
		FourTeamsIssueStep step = getFullIssueStepById(stepid);
		if (FourTeamsIssueOperate.GIVETO.equals(operate)) {
			return findGiveToAdminTargetsByName(tag, exceptId, step, page, rows);
		} else if (FourTeamsIssueOperate.SUBMIT.equals(operate)) {
			return findSubmitAdminTargetsByName(tag, exceptId, step);
		} else if (FourTeamsIssueOperate.ASSIGN.equals(operate)) {
			return findAssignAdminTargetsByName(tag, exceptId, step, page, rows);
		}
		return createEmptyAutoCompleteDataPage();
	}

	@Override
	public PageInfo<AutoCompleteData> findFunctionTargetsByName(Long stepid,
			FourTeamsIssueOperate operate, String tag, Long[] exceptIds,
			int pageIndex, int rows) {
		// TODO
		FourTeamsIssueStep step = getFullIssueStepById(stepid);
		if (FourTeamsIssueOperate.GIVETO.equals(operate)) {
			return findGiveToFunctionTargetsByName(tag, exceptIds, step, rows,
					pageIndex);
		} else if (FourTeamsIssueOperate.SUBMIT.equals(operate)) {
			return findSubmitFunctionTargetsByName(tag, exceptIds, step,
					pageIndex, rows);
		} else if (FourTeamsIssueOperate.ASSIGN.equals(operate)) {
			return findAssignFunctionTargetsByName(tag, exceptIds, step,
					pageIndex, rows);
		}
		return createEmptyAutoCompleteDataPage();
	}

	@Override
	public PageInfo<AutoCompleteData> findTellTargetsByName(Long stepid,
			FourTeamsIssueOperate operate, String tag, boolean transferToAdmin,
			Long[] exceptIds, int page, int rows) {
		FourTeamsIssueStep step = getFullIssueStepById(stepid);
		if (FourTeamsIssueOperate.GIVETO.equals(operate)) {
			return findGiveToTellsByName(tag, exceptIds, step, transferToAdmin,
					page, rows);
		} else if (FourTeamsIssueOperate.SUBMIT.equals(operate)) {
			return findSubmitTellsByName(tag, exceptIds, step, transferToAdmin,
					page, rows);
		} else if (FourTeamsIssueOperate.ASSIGN.equals(operate)) {
			return findAssignTellsByName(tag, exceptIds, step, transferToAdmin,
					page, rows);
		}
		return createEmptyAutoCompleteDataPage();
	}

	protected Organization getNearestCommandCenter(Organization org) {
		return organizationDubboService.findOrganizationsByParentId(null).get(0);
	}

	private PageInfo<AutoCompleteData> findGiveToTellsByName(String tag,
			Long[] exceptIds, FourTeamsIssueStep step, boolean transferToAdmin,
			int page, int rows) {
		if (exceptIds == null || exceptIds.length == 0) {
			return createEmptyAutoCompleteDataPage();
		} else {
			Organization transferTo = loadFullOrganization(exceptIds[0]);
			if (isAdminOrg(transferTo)) {
				PropertyDict dict = propertyDictService
						.findPropertyDictByDomainNameAndInternalId(
								OrganizationType.ORG_TYPE_KEY,
								OrganizationType.FUNCTIONAL_ORG).get(0);
				return issueProcessDao.findChildOrgsByParentIdAndName(dict,
						transferTo.getId(), tag, exceptIds, page, rows);
			} else {
				return findParentAndSiblingFunOrgs(tag, exceptIds, transferTo);
			}
		}
	}

	private PageInfo<AutoCompleteData> findParentAndSiblingFunOrgs(String tag,
			Long[] exceptIds, Organization funOrg) {
		Organization parent = organizationDubboService.getSimpleOrgById(funOrg
				.getParentOrg().getId());
		PropertyDict dict = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.FUNCTIONAL_ORG).get(0);
		PageInfo<AutoCompleteData> result = issueProcessDao
				.findChildOrgsByParentIdAndName(dict, parent.getId(), tag,
						exceptIds, 1, 10);
		if (!inExceptOrg(parent.getId(), exceptIds)) {
			insertOrgToPages(parent, result);
		}
		return result;
	}

	private PageInfo<AutoCompleteData> findAssignTellsByName(String tag,
			Long[] exceptIds, FourTeamsIssueStep step, boolean transferToAdmin,
			int page, int rows) {
		Organization operationOrg = organizationDubboService
				.getFullOrgById(ThreadVariable.getSession().getOrganization()
						.getId());
		if (isAdminOrg(operationOrg)) {
			PropertyDict dict = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							OrganizationType.ORG_TYPE_KEY,
							OrganizationType.FUNCTIONAL_ORG).get(0);
			return issueProcessDao.findChildOrgsByParentIdAndName(dict,
					ThreadVariable.getSession().getOrganization().getId(), tag,
					exceptIds, page, rows);
		} else {
			PageInfo<AutoCompleteData> result = createEmptyAutoCompleteDataPage();
			if (!inExceptOrg(operationOrg.getParentOrg().getId(), exceptIds)) {
				insertOrgToPages(
						organizationDubboService.getSimpleOrgById(operationOrg
								.getParentOrg().getId()), result);
			}
			return result;
		}
	}

	private PageInfo<AutoCompleteData> findSubmitTellsByName(String tag,
			Long[] exceptIds, FourTeamsIssueStep step, boolean transferToAdmin,
			int page, int rows) {
		Organization operationOrg = organizationDubboService
				.getFullOrgById(ThreadVariable.getSession().getOrganization()
						.getId());
		if (transferToAdmin) {
			if (isAdminOrg(operationOrg)) {
				PropertyDict dict = propertyDictService
						.findPropertyDictByDomainNameAndInternalId(
								OrganizationType.ORG_TYPE_KEY,
								OrganizationType.FUNCTIONAL_ORG).get(0);
				return issueProcessDao.findChildOrgsByParentIdAndName(dict,
						step.getTarget().getParentOrg().getId(), tag,
						exceptIds, page, rows);
			} else {
				return createEmptyAutoCompleteDataPage();
			}
		} else {
			if (isAdminOrg(operationOrg)) {
				PropertyDict dict = propertyDictService
						.findPropertyDictByDomainNameAndInternalId(
								OrganizationType.ORG_TYPE_KEY,
								OrganizationType.FUNCTIONAL_ORG).get(0);
				PageInfo<AutoCompleteData> result = issueProcessDao
						.findChildOrgsByParentIdAndName(dict, operationOrg
								.getParentOrg().getId(), tag, exceptIds, 1, 10);
				if (!inExceptOrg(operationOrg.getParentOrg().getId(), exceptIds)) {
					insertOrgToPages(
							organizationDubboService.getSimpleOrgById(operationOrg
									.getParentOrg().getId()), result);
				}
				return result;
			} else {
				PageInfo<AutoCompleteData> result = createEmptyAutoCompleteDataPage();
				if (!inExceptOrg(operationOrg.getParentOrg().getId(), exceptIds)) {
					insertOrgToPages(
							organizationDubboService.getSimpleOrgById(operationOrg
									.getParentOrg().getId()), result);
				}
				return result;
			}
		}
	}

	private boolean isAdminOrg(Organization org) {
		return OrganizationType.ADMINISTRATIVE_REGION == org.getOrgType()
				.getInternalId();
	}

	private PageInfo<AutoCompleteData> createEmptyAutoCompleteDataPage() {
		return createAutoCompleteDataPage(1, 0, 0,
				new ArrayList<AutoCompleteData>());
	}

	private PageInfo<AutoCompleteData> createAutoCompleteDataPage(
			int pageIndex, int pagesize, int totalCount,
			List<AutoCompleteData> data) {
		PageInfo<AutoCompleteData> result = new PageInfo<AutoCompleteData>();
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pagesize == 0 ? 1 : pagesize);
		result.setTotalRowSize(totalCount);
		result.setResult(data);
		return result;
	}

	private boolean inExceptOrg(Long id, Long[] exceptIds) {
		if (id != null && (exceptIds == null || exceptIds.length == 0))
			return false;
		if (id == null)
			return false;
		for (Long exceptId : exceptIds) {
			if (exceptId.equals(id))
				return true;
		}
		return false;
	}

	private AutoCompleteData convertToAutoCompleteData(Organization org) {
		AutoCompleteData autoCompleteData = new AutoCompleteData();
		autoCompleteData.setValue(org.getId().toString());
		autoCompleteData.setLabel(org.getOrgName());
		autoCompleteData.setDesc(org.getRemark());
		return autoCompleteData;
	}

	private PageInfo<AutoCompleteData> findAssignFunctionTargetsByName(
			String tag, Long[] exceptIds, FourTeamsIssueStep step,
			int pageIndex, int rows) {
		Organization org = organizationDubboService.getFullOrgById(step.getTarget()
				.getId());
		if (OrganizationType.FUNCTIONAL_ORG == org.getOrgType().getInternalId()) {
			return issueProcessDao.findChildFunOrgsByParentFunIdAndName(
					org.getId(), tag, exceptIds);
		} else {
			PropertyDict dict = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							OrganizationType.ORG_TYPE_KEY,
							OrganizationType.FUNCTIONAL_ORG).get(0);
			return issueProcessDao.findChildOrgsByParentIdAndName(dict, step
					.getTarget().getId(), tag, exceptIds, pageIndex, rows);
		}
	}

	private PageInfo<AutoCompleteData> findAssignAdminTargetsByName(String tag,
			Long[] exceptIds, FourTeamsIssueStep step, int page, int rows) {
		PropertyDict dict = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.ADMINISTRATIVE_REGION).get(0);
		return issueProcessDao.findChildOrgsByParentIdAndName(dict, step
				.getTarget().getId(), tag, exceptIds, page, rows);
	}

	private PageInfo<AutoCompleteData> findSubmitAdminTargetsByName(String tag,
			Long[] exceptIds, FourTeamsIssueStep step) {
		Organization org = step.getTarget();
		if (org.getParentOrg() == null
				|| inExceptOrg(org.getParentOrg().getId(), exceptIds)) {
			return createEmptyAutoCompleteDataPage();
		} else {
			PageInfo<AutoCompleteData> result = createAutoCompleteDataPage(1,
					1, 1, new ArrayList<AutoCompleteData>());
			result.getResult().add(
					convertToAutoCompleteData(organizationDubboService
							.getSimpleOrgById(org.getParentOrg().getId())));
			return result;
		}
	}

	private PageInfo<AutoCompleteData> findSubmitFunctionTargetsByName(
			String tag, Long[] exceptIds, FourTeamsIssueStep step,
			int pageIndex, int rows) {
		Organization org = organizationDubboService.getFullOrgById(step.getTarget()
				.getId());
		if (org.getParentOrg() == null) {
			return createEmptyAutoCompleteDataPage();
		} else if (OrganizationType.FUNCTIONAL_ORG == org.getOrgType()
				.getInternalId()) {
			return issueProcessDao.findParentFunOrgsByIdAndName(org.getId(),
					tag, exceptIds);
		} else {
			PropertyDict dict = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							OrganizationType.ORG_TYPE_KEY,
							OrganizationType.FUNCTIONAL_ORG).get(0);
			return issueProcessDao.findChildOrgsByParentIdAndName(dict, org
					.getParentOrg().getId(), tag, exceptIds, pageIndex, rows);
		}
	}

	private PageInfo<AutoCompleteData> findGiveToAdminTargetsByName(String tag,
			Long[] exceptIds, FourTeamsIssueStep step, int page, int rows) {
		PropertyDict dict = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.ADMINISTRATIVE_REGION).get(0);
		return issueProcessDao.findChildOrgsByOrgcodeAndNameAndType(dict, step
				.getTarget().getOrgInternalCode(), tag, exceptIds, page, rows);
	}

	private PageInfo<AutoCompleteData> findGiveToFunctionTargetsByName(
			String tag, Long[] exceptIds, FourTeamsIssueStep step,
			int pageIndex, int rows) {
		PropertyDict dict = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.FUNCTIONAL_ORG).get(0);
		return issueProcessDao.findChildOrgsByOrgcodeAndNameAndType(dict, step
				.getTarget().getOrgInternalCode(), tag, exceptIds, rows,
				pageIndex);
	}

	private void insertOrgToPages(Organization org,
			PageInfo<AutoCompleteData> datas) {
		datas.getResult().add(0, convertToAutoCompleteData(org));
		datas.setPerPageSize(datas.getResult().size());
		datas.setTotalRowSize(datas.getResult().size());
	}

	private Organization loadFullCurrentLoginedOrganization() {
		return loadFullOrganization(ThreadVariable.getSession()
				.getOrganization().getId());
	}

	private List<Organization> loadFullOrganizations(Long[] tells) {
		List<Organization> result = new ArrayList<Organization>();
		if (tells != null && tells.length > 0) {
			for (Long id : tells) {
				result.add(loadFullOrganization(id));
			}
		}
		return result;
	}

	private Organization loadFullOrganization(Long id) {
		return null == id ? null : organizationDubboService.getFullOrgById(id);
	}

	private FourTeamsIssueStep addAndReloadFullIssueStep(FourTeamsIssueStep step) {
		fetchSourceAndTargetOrg(step);
		FourTeamsIssueStep result = issueProcessDao.addIssueStep(step);
		fetchSourceAndTargetOrg(result);
		return result;
	}

	private FourTeamsIssueStep getSimpleIssueStepById(Long stepId) {
		return issueProcessDao.getSimpleIssueStepById(stepId);
	}

	private FourTeamsIssueStepGroup saveStepGroup(FourTeamsIssueStepGroup steps) {

		steps.setKeyStep(addAndReloadFullIssueStep(steps.getKeyStep()));
		steps.setIssue(steps.getKeyStep().getIssue());
		if (steps.hasIncidentalStep()) {
			for (int index = 0; index < steps.getIncidentalSteps().size(); index++) {
				steps.getIncidentalSteps().set(
						index,
						addAndReloadFullIssueStep(steps.getIncidentalSteps()
								.get(index)));
			}
		}
		return steps;
	}

	private void saveGroupId(FourTeamsIssueStepGroup steps) {
		FourTeamsIssueStep keyStep = steps.getKeyStep();
		List<FourTeamsIssueStep> incidentalSteps = steps.getIncidentalSteps();
		if (null != keyStep) {
			keyStep.setGroupId(steps.getId());
			issueProcessDao.updateGroupId(keyStep);
		}
		if (null != incidentalSteps && incidentalSteps.size() > 0) {
			for (FourTeamsIssueStep step : incidentalSteps) {
				step.setGroupId(steps.getId());
				issueProcessDao.updateGroupId(step);
			}
		}
	}

	private void fireIssueGroup(FourTeamsIssueStepGroup issueStepGroup) {
		issueStepGroup.setEntyLog(issueLogDao.getIssueLogsByStepId(
				issueStepGroup.getKeyStep().getId()).get(0));
		FourTeamsIssueStepGroup stepGroup = issueProcessDao
				.addIssueStepGroup(issueStepGroup);
		stepGroup.setIncidentalSteps(issueStepGroup.getIncidentalSteps());
		saveGroupId(stepGroup);
		List<FourTeamsIssueStepGroup> list = issueProcessDao
				.getIssueStepGroupByIssueId(stepGroup.getIssue().getId());
		if (null != list && list.size() > 1) {
			FourTeamsIssueStepGroup isg = list.get(list.size() - 2);
			isg.setOutLog(issueStepGroup.getEntyLog());
			issueProcessDao.updateOutLog(isg);
		}
	}

	private FourTeamsIssueOperationContext constructIssueOperationContext(
			Organization target, List<Organization> tells) {
		Organization org = organizationDubboService.getFullOrgById(ThreadVariable
				.getSession().getOrganization().getId());
		FourTeamsIssueOperationContext context = new FourTeamsIssueOperationContext(
				org, target, "", ThreadVariable.getSession().getUserRealName());
		if (tells != null) {
			for (Organization tellorg : tells) {
				context.addTellOrg(tellorg);
			}
		}
		return context;
	}

	private void fetchSourceAndTargetOrg(FourTeamsIssueStep step) {
		step.setSource(organizationDubboService.getSimpleOrgById(step.getSource()
				.getId()));
		step.setTarget(organizationDubboService.getSimpleOrgById(step.getTarget()
				.getId()));
	}

	private void fetchFullSourceAndTargetOrg(FourTeamsIssueStep step) {
		step.setSource(loadFullOrganization(step.getSource().getId()));
		step.setTarget(loadFullOrganization(step.getTarget().getId()));
	}

	private void fireIssueEntry(FourTeamsIssueNew issue, FourTeamsIssueStep step) {
		if (getIssueChangeListeners() != null) {
			for (FourTeamsIssueChangeListener listener : getIssueChangeListeners()) {
				listener.onEntry(issue, step);
			}
		}
	}

	private void fireIssueComplete(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> attachFiles) {
		FourTeamsIssueChangeEvent event = new FourTeamsIssueChangeEvent(log,
				attachFiles, FourTeamsIssueOperate.COMPLETE);
		if (getIssueChangeListeners() != null) {
			for (FourTeamsIssueChangeListener listener : getIssueChangeListeners()) {
				listener.onComplete(issue, step, event);
			}
		}
	}

	@Override
	public void fireIssueChanged(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueOperate operate,
			FourTeamsIssueLogNew log, List<FourTeamsIssueAttachFile> attachFiles) {
		FourTeamsIssueChangeEvent event = new FourTeamsIssueChangeEvent(log,
				attachFiles, operate);
		FourTeamsIssueStepGroup steps = new FourTeamsIssueStepGroup();
		steps.setKeyStep(step);
		if (getIssueChangeListeners() != null) {
			for (FourTeamsIssueChangeListener listener : getIssueChangeListeners()) {
				listener.onChanged(issue, steps, event);
			}
		}
	}

	private void fireIssueChanged(FourTeamsIssueNew issue,
			FourTeamsIssueStepGroup steps, FourTeamsIssueOperate operate,
			FourTeamsIssueLogNew log, List<FourTeamsIssueAttachFile> attachFiles) {
		FourTeamsIssueChangeEvent event = new FourTeamsIssueChangeEvent(log,
				attachFiles, operate);
		if (getIssueChangeListeners() != null) {
			for (FourTeamsIssueChangeListener listener : getIssueChangeListeners()) {
				listener.onChanged(issue, steps, event);
			}
		}
	}

	private FourTeamsIssueOperationContext constructOperationContext(
			Long targetOrg, Long[] tells) {
		Organization target = loadFullOrganization(targetOrg);
		List<Organization> tellOrgs = loadFullOrganizations(tells);
		FourTeamsIssueOperationContext context = constructIssueOperationContext(
				target, tellOrgs);
		return context;
	}

	private int getSuperviseLevel(FourTeamsIssueOperate superviseType) {
		if (FourTeamsIssueOperate.NORMAL_SUPERVISE.equals(superviseType)) {
			return FourTeamsIssueState.NORMAL_SUPERVISE;
		} else if (FourTeamsIssueOperate.YELLOW_SUPERVISE.equals(superviseType)) {
			return FourTeamsIssueState.YELLOW_CARD_SUPERVISE;
		} else if (FourTeamsIssueOperate.RED_SUPERVISE.equals(superviseType)) {
			return FourTeamsIssueState.RED_CARD_SUPERVISE;
		} else {
			return FourTeamsIssueState.NO_SUPERVISE;
		}
	}

	@Override
	public List<FourTeamsIssueStep> findIssueStepListByIssueState(
			int[] issueStates) {
		List<FourTeamsIssueStep> list = issueProcessDao
				.findIssueStepListByIssueState(issueStates);
		for (FourTeamsIssueStep issueStep : list) {
			issueStep.setSource(organizationDubboService.getSimpleOrgById(issueStep
					.getSource().getId()));
			issueStep.setTarget(organizationDubboService.getSimpleOrgById(issueStep
					.getTarget().getId()));
		}
		return list;
	}

}

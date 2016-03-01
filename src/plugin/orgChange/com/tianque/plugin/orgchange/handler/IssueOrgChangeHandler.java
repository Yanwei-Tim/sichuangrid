package com.tianque.plugin.orgchange.handler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CollectionUtil;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.IssueApplyDelay;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.service.IssueApplyDelayService;
import com.tianque.issue.service.IssueService;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.constant.IssuesStat;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;
import com.tianque.sysadmin.service.OrganizationDubboService;

/****
 * 事件迁移合并
 * 
 * @author LG
 * 
 */
@Component("issueOrgChangeHandler")
public class IssueOrgChangeHandler extends AbstractOrgChangeHandler {
	private final static Logger logger = LoggerFactory
			.getLogger(IssueOrgChangeHandler.class);

	@Autowired
	private IssueService issueService;

	@Autowired
	private IssueApplyDelayService issueApplyDelayService;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public void init(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin init.orgMapping:{}", orgMapping);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		int count = 0;
		if (orgMapping.getOldOrgId().equals(orgMapping.getNewOrgId())) {
			Set<String> stepsId = getIdListForFransfer(orgMapping);
			orgMapping.setQueryResults(stepsId);
			count = stepsId.size();
			log.appendSuccessDesc("共需要更改的数据量[" + count + "]");
		} else {
			// 如果是同级合并，只对未办理结束的进行操作，其余不做操作
			int needDoCount = commonHandlerDAO.getCount(OrgChangeUtils
					.replaceScript(orgMapping, IssuesStat.SELECT_COUNT_SQL
							+ IssuesStat.SELECT_STEPSID_NEEDDO_LIST_SQL + ")"));
			int verificationCount = commonHandlerDAO.getCount(OrgChangeUtils
					.replaceScript(orgMapping, IssuesStat.SELECT_COUNT_SQL
							+ IssuesStat.SELECT_STEPSID_VERIFICATION_LIST_SQL
							+ ")"));
			// 上报以及交办的事件查询
			int skipIssueCount = commonHandlerDAO.getCount(OrgChangeUtils
					.replaceScript(orgMapping, IssuesStat.SELECT_COUNT_SQL
							+ IssuesStat.SELECT_STEPSID_SKIP_LIST_SQL + ")"));
			count = needDoCount + verificationCount + skipIssueCount;
			log.appendSuccessDesc("共需要更改的数据量[" + count + "],其中待办数量["
					+ needDoCount + "],待验证数量[" + verificationCount
					+ "],其中上报交办数量[" + skipIssueCount + "]");
		}
		if (count > 0) {
			orgMapping.setHasData(true);
		} else {
			orgMapping.setHasData(false);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("begin init.orgMapping:{}", orgMapping);
		}
	}

	@Override
	public void backup(OrgMapping orgMapping) {

	}

	@Override
	public void transfer(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin transfer.orgMapping:{}", orgMapping);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		int count = 0;
		Set<String> transferIds = orgMapping.getQueryResults();
		if (transferIds.size() == 0) {
			transferIds = getIdListForFransfer(orgMapping);
		}
		for (String stepId : transferIds) {
			count += executeUpdate(orgMapping, stepId);
			synchronizedApplyDelay(orgMapping, stepId);
		}
		log.appendSuccessDesc("迁移更改的数据量[" + count + "]");
		if (logger.isDebugEnabled()) {
			logger.debug("begin transfer.orgMapping:{}", orgMapping);
		}
	}

	@Override
	public void merge(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin merge.orgMapping:{}", orgMapping);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		int count = 0;
		if (orgMapping.getOldOrgId().equals(orgMapping.getNewOrgId())) {
			transfer(orgMapping);
		} else {
			// 待办的类容
			List<String> needDoList = commonHandlerDAO
					.queryStringForList(OrgChangeUtils.replaceScript(
							orgMapping,
							IssuesStat.SELECT_STEPSID_NEEDDO_LIST_SQL));
			List<String> verificationList = commonHandlerDAO
					.queryStringForList(OrgChangeUtils.replaceScript(
							orgMapping,
							IssuesStat.SELECT_STEPSID_VERIFICATION_LIST_SQL));
			needDoList.addAll(verificationList);
			if (CollectionUtil.isAvaliable(needDoList)) {
				for (String stepId : needDoList) {
					count += executeUpdate(orgMapping, stepId);
					synchronizedApplyDelay(orgMapping, stepId);
				}
			}
			List<String> skipIssueList = commonHandlerDAO
					.queryStringForList(OrgChangeUtils
							.replaceScript(orgMapping,
									IssuesStat.SELECT_STEPSID_SKIP_LIST_SQL));
			if (CollectionUtil.isAvaliable(skipIssueList)) {
				for (String stepId : skipIssueList) {
					executeUpdate(orgMapping, stepId);
					// executeSkipIssueUpdate(orgMapping, stepId);
				}
			}

			// 同步有回退到该部门的(事件直接全都跟着组织机构迁移合并走)
			// synchronizedStepBack(orgMapping);
		}
		log.appendSuccessDesc("合并更改的数据量[" + count + "]");
		if (logger.isDebugEnabled()) {
			logger.debug("begin merge.orgMapping:{}", orgMapping);
		}
	}

	// 执行更新
	private int executeUpdate(OrgMapping orgMapping, String stepId) {
		int updateCout = 0;
		IssueNew issue = issueService
				.getFullIssueByStepId(Long.valueOf(stepId));
		IssueStep currentStep = issueService.getIssueStepById(Long
				.valueOf(stepId));
		String whereSql = " WHERE ID = " + stepId;
		// if (currentStep != null
		// && currentStep.getSource() != null
		// && orgMapping.getOldOrgId().equals(
		// currentStep.getSource().getId())) {
		// // 如果来源部门也是当前部门，那么也进行更改
		// updateCout = commonHandlerDAO.updateData(OrgChangeUtils
		// .replaceScript(orgMapping, IssuesStat.UPDATE_TARGET_SQL
		// + "," + IssuesStat.UPDATE_SOURCE_SQL + whereSql));
		// } else {
		// // 只有目标部门的是
		// updateCout = commonHandlerDAO.updateData(OrgChangeUtils
		// .replaceScript(orgMapping, IssuesStat.UPDATE_TARGET_SQL
		// + whereSql));
		// }
		if (currentStep != null) {
			// 如果事件所有步骤中 来源部门code处理
			if (currentStep.getSource() != null
					&& orgMapping.getOldOrgId().equals(
							currentStep.getSource().getId())) {
				updateCout = commonHandlerDAO.updateData(OrgChangeUtils
						.replaceScript(orgMapping, IssuesStat.UPDATE_SOURCE_SQL
								+ whereSql + " AND SOURCE = #OLDORGID#"));
			}
			// 如果事件所有步骤中 目标部门code处理
			if (currentStep.getTarget() != null
					&& orgMapping.getOldOrgId().equals(
							currentStep.getTarget().getId())) {
				updateCout = commonHandlerDAO.updateData(OrgChangeUtils
						.replaceScript(orgMapping, IssuesStat.UPDATE_TARGET_SQL
								+ whereSql + " AND TARGET = #OLDORGID#"));
			}

		}
		synchronizedIssue(orgMapping, issue);
		return updateCout;
	}

	// 同步更改issues中的数据
	private int synchronizedIssue(OrgMapping orgMapping, IssueNew issue) {
		Long oldOrgId = orgMapping.getOldOrgId();
		Long issueId = issue.getId();
		int updateCount = 0;
		StringBuffer updateIssueSql = new StringBuffer(
				IssuesStat.UPDATE_ISSUES_HEAD_SQL + " SET ID = " + issueId);
		String sql = updateIssueSql.toString();
		if (oldOrgId.equals(issue.getOccurOrg().getId())) {
			updateIssueSql.append(IssuesStat.UPDATE_ISSUES_OCCURORG_SQL);
		}
		if (oldOrgId.equals(issue.getCreateOrg().getId())) {
			updateIssueSql.append(IssuesStat.UPDATE_ISSUES_CREATE_SQL);
		}
		if (oldOrgId.equals(issue.getLastOrg().getId())) {
			updateIssueSql.append(IssuesStat.UPDATE_ISSUES_LASTORG_SQL);
		}
		if (!sql.equals(updateIssueSql.toString())) {
			updateIssueSql.append(" WHERE ID = " + issueId);
			updateCount += commonHandlerDAO.updateData(OrgChangeUtils
					.replaceScript(orgMapping, updateIssueSql.toString()));
		}
		return updateCount;
	}

	// 转移时获取需要更新的步骤ID
	private Set<String> getIdListForFransfer(OrgMapping orgMapping) {
		Set<String> set = new HashSet<String>();
		List<String> sourceIds = commonHandlerDAO
				.queryStringForList(OrgChangeUtils.replaceScript(orgMapping,
						IssuesStat.SELECT_SOURCE_ID_SQL));
		List<String> targetIds = commonHandlerDAO
				.queryStringForList(OrgChangeUtils.replaceScript(orgMapping,
						IssuesStat.SELECT_TARGET_ID_SQL));
		if (CollectionUtil.isAvaliable(sourceIds)) {
			set.addAll(sourceIds);
		}
		if (CollectionUtil.isAvaliable(targetIds)) {
			set.addAll(targetIds);
		}
		return set;
	}

	// 同步回退（只有合并的时候，如果有回退到被合并部门的事件，则清空改步骤的back）
	private int synchronizedStepBack(OrgMapping orgMapping) {
		int updateCount = 0;
		List<String> stepList = commonHandlerDAO
				.queryStringForList(OrgChangeUtils.replaceScript(orgMapping,
						IssuesStat.SELECT_UNCOMPLETE_LIST_SQL));
		if (!CollectionUtil.isAvaliable(stepList)) {
			return updateCount;
		}
		for (String stepId : stepList) {
			IssueStep issueStep = issueService.getIssueStepById(Long
					.valueOf(stepId));
			Organization targetOrg = getBackTargetOrg(issueStep);
			if (targetOrg != null && targetOrg.getId() != null) {
				if (orgMapping.getOldOrgId().equals(targetOrg.getId())) {
					updateCount += commonHandlerDAO.updateData(OrgChangeUtils
							.replaceScript(orgMapping,
									IssuesStat.UPDATE_ISSUESTEP_BACK_SQL
											.replace("#STEPID#", stepId)));
				}
			}
		}
		return updateCount;
	}

	// 根据currentStep获取回退的组织机构
	private Organization getBackTargetOrg(IssueStep issueStep) {
		Organization targetOrg = null;
		if (issueStep != null && issueStep.getBackTo() != null
				&& issueStep.getBackTo().getId() != null) {
			// 根据backId获取backStep
			IssueStep backStep = issueService.getIssueStepById(issueStep
					.getBackTo().getId());
			targetOrg = backStep.getTarget();
		}
		return targetOrg;
	}

	// 同步申请延期（迁移或合并，申请的延期也随之变化）
	private int synchronizedApplyDelay(OrgMapping orgMapping, String stepId) {
		int updateCount = 0;
		IssueApplyDelay applyDelay = issueApplyDelayService
				.getIssueApplyDelayByIssueStepIdAndDelayState(Long
						.valueOf(stepId));
		if (applyDelay == null) {
			orgMapping.getOrgChangeLog().appendSuccessDesc(
					" IssueApplyDelay is null stepId:[" + stepId + "]");
			return 0;
		}
		Organization auditOrg = applyDelay.getAuditOrg();
		if (auditOrg != null && auditOrg.getId() != null) {
			Long targetOrgId = orgMapping.getNewOrgId();
			Organization targetOrg = organizationDubboService
					.getSimpleOrgById(targetOrgId);
			// 获取新组织结构的父节点
			Organization targetParentOrg = targetOrg.getParentOrg();
			if (!targetParentOrg.getId().equals(auditOrg.getId())) {
				// 新组织机构的父类节点与该步骤申请延期的审核中如果不相同，那么一先的组织机构为准
				updateCount = commonHandlerDAO
						.updateData(IssuesStat.UPDATE_AUDITORG_ID_SQL.replace(
								"#AUDITORG#", targetParentOrg.getId() + "")
								.replace("#ID#", applyDelay.getId() + ""));
			}
		}
		return updateCount;
	}

	// 执行上报交办更新
	private void executeSkipIssueUpdate(OrgMapping orgMapping, String stepId) {
		IssueNew issue = issueService
				.getFullIssueByStepId(Long.valueOf(stepId));
		synchronizedIssue(orgMapping, issue);
	}
}

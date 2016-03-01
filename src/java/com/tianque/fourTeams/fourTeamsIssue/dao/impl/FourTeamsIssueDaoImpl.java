package com.tianque.fourTeams.fourTeamsIssue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.approval.domain.property.ApprovalItemStatus;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Organization;
import com.tianque.file.constants.AttachFileModule;
import com.tianque.file.domain.AttachFile;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueAttachFileType;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueTag;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueViewType;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueEvaluate;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueSourceState;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueState;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;
import com.tianque.issue.constants.IssueConstants;

@Repository("fourTeamsIssueDao")
public class FourTeamsIssueDaoImpl extends AbstractBaseDao implements
		FourTeamsIssueDao {

	private PageInfo<FourTeamsIssueViewObject> createIssueVoPageInfoInstance(
			int totalRecord, int pageSize, int pageIndex) {
		PageInfo<FourTeamsIssueViewObject> result = new PageInfo<FourTeamsIssueViewObject>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

	private int getMyDoneCount(Map params) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.countMyDone", params);
	}

	private int getJurisdictionsDone(Map<String, Object> map, String tablePrefix) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				tablePrefix + ".countJurisdictionsDone", map);
	}

	private int getJurisdictionsSkipIssue(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.countJurisdictionsSkipIssue", map);
	}

	private int getJurisdictionsCompleted(Map<String, Object> map,
			String tablePrefix) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				tablePrefix + ".countJurisdictionsCompleted", map);
	}

	private int getJurisdictionsSubmit(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.countJurisdictionsSubmit", map);
	}

	private int getMyCompleted(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.countMyCompleted", map);
	}

	private int getJurisdictionsNeedDoCount(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.countJurisdictionsNeedDo", map);
	}

	@Override
	public int getJurisdictionsAuditDelayCount(Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.countJurisdictionsAuditDelay", map);
	}

	private int getJurisdictionsAssginCount(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.countJurisdictionsAssgin", map);
	}

	private int getJurisdictionsDoingCount(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.countJurisdictionsDoing", map);
	}

	private int getMyHistoricCount(String orgCode) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.countMyHistoric", orgCode);
	}

	private int getMyPublicltyCount(Long orgId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.countMyPubliclty", orgId);
	}

	@Override
	public FourTeamsIssueNew addIssue(FourTeamsIssueNew issue) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"fourTeamsIssue.addIssue", issue);
		addIssueTypes(id, issue.getIssueTypes());
		return getFullIssueById(id);
	}

	/** 保存事件类型 */
	private void addIssueTypes(Long issueId, List<IssueType> list) {
		if (list != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("issueId", issueId);
			for (IssueType type : list) {
				params.put("type", type);
				getSqlMapClientTemplate().insert(
						"fourTeamsIssue.addIssueHasType", params);
			}
		}
	}

	@Override
	public FourTeamsIssueNew updateIssue(FourTeamsIssueNew issue) {
		getSqlMapClientTemplate().update("fourTeamsIssue.updateIssue", issue);
		return getFullIssueById(issue.getId());
	}

	@Override
	public boolean deleteIssueById(Long issueId) {
		getSqlMapClientTemplate().delete("fourTeamsIssue.removeIssue", issueId);
		return true;
	}

	@Override
	public boolean removeAllIssueAttachFiles(Long issueId) {
		getSqlMapClientTemplate()
				.delete("fourTeamsIssue.removeAllIssueAttachFilesFromBaseFile",
						issueId);
		getSqlMapClientTemplate().delete(
				"fourTeamsIssue.removeAllIssueAttachFiles", issueId);
		return true;
	}

	@Override
	public FourTeamsIssueNew getFullIssueById(Long id) {
		return (FourTeamsIssueNew) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.getFullIssueById", id);
	}

	@Override
	public void addIssueAttachFiles(List<FourTeamsIssueAttachFile> attachFiles) {
		if (attachFiles != null && !attachFiles.isEmpty()) {

			Map<String, Object> params = new HashMap<String, Object>();

			for (FourTeamsIssueAttachFile file : attachFiles) {
				params.put("issueId", file.getIssue().getId());
				if (file.getIssueLog() == null) {
					params.put("fileType",
							FourTeamsIssueAttachFileType.ISSUE_FILE);
				} else {
					params.put("fileType",
							FourTeamsIssueAttachFileType.ISSUELOG_FILE);
					params.put("issueLogId", file.getIssueLog().getId());
				}
				Long id = (Long) getSqlMapClientTemplate().insert(
						"fourTeamsIssue.addIssueAttachFiles", params);
				getSqlMapClientTemplate().insert("attachFiles.addAttachFile",
						constractAttachFile(id, file));
			}
		}
	}

	@Override
	public void addIssueEvaluateAttachFiles(
			List<FourTeamsIssueAttachFile> attachFiles) {
		if (attachFiles != null && !attachFiles.isEmpty()) {
			Map<String, Object> params = new HashMap<String, Object>();
			for (FourTeamsIssueAttachFile file : attachFiles) {
				params.put("issueId", file.getIssue().getId());
				params.put("fileType",
						FourTeamsIssueAttachFileType.ISSUEEVALUATE_FILE);
				Long id = (Long) getSqlMapClientTemplate().insert(
						"fourTeamsIssue.addIssueAttachFiles", params);
				getSqlMapClientTemplate().insert("attachFiles.addAttachFile",
						constractAttachFile(id, file));
			}
		}
	}

	private AttachFile constractAttachFile(Long objId,
			FourTeamsIssueAttachFile file) {
		AttachFile attachFile = new AttachFile();
		attachFile.setModuleKey(AttachFileModule.MODULE_TYPE);
		attachFile.setModuleObjectId(objId.toString());
		attachFile.setName(file.getFileName());
		attachFile.setPhysicsFullFileName(file.getFileActualUrl());
		return attachFile;
	}

	@Override
	public List<FourTeamsIssueAttachFile> loadIssueAttachFilesByIssueAndLog(
			Long issueId, Long logId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("issueId", issueId);
		if (null != logId) {
			params.put("issueLogId", logId);
		}
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.loadIssueAttachFilesByIssueAndLog", params);
	}

	@Override
	public void updateIssueCurrentStepAndOrg(FourTeamsIssueNew issue) {
		getSqlMapClientTemplate().update(
				"fourTeamsIssue.updateIssueCurrentStepAndOrg", issue);
	}

	@Override
	public FourTeamsIssueNew getFullIssueByStepId(Long stepId) {
		return (FourTeamsIssueNew) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.getFullIssueByStepId", stepId);
	}

	@Override
	public FourTeamsIssueAttachFile getIssueAttachFileById(Long id) {
		return (FourTeamsIssueAttachFile) getSqlMapClientTemplate()
				.queryForObject("fourTeamsIssue.getIssueAttachFileById", id);
	}

	public List<FourTeamsIssueAttachFile> getIssueAttachFileByIssueId(
			Long issueId) {
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.getIssueAttachFileByIssueId", issueId);
	}

	@Override
	public boolean updateIssueHistoricState(Long issueId, boolean state) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", state);
		params.put("issueid", issueId);
		getSqlMapClientTemplate().update(
				"fourTeamsIssue.updateIssueHistoricState", params);
		return true;
	}

	@Override
	public boolean updateIssueUrgentState(Long issueId, boolean state) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", state);
		params.put("issueid", issueId);
		getSqlMapClientTemplate().update(
				"fourTeamsIssue.updateIssueUrgentState", params);
		return true;
	}

	@Override
	public boolean alreadyPubliclty(Long issueId, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("issueId", issueId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.countPubliclty", map) > 0;
	}

	@Override
	public void publiclty(Long orgId, Long issueId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("issueId", issueId);
		getSqlMapClientTemplate()
				.insert("fourTeamsIssue.addPublicltyCass", map);
	}

	@Override
	public void removePubliclty(Long orgId, Long issueId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("issueId", issueId);
		getSqlMapClientTemplate().delete(
				"fourTeamsIssue.removeIssueFromPublicltyCass", map);
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findMyNeedDoIssues(Long orgId,
			Long issueType, int page, int rows, String sidx, String sord) {

		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getMyNeedDoCount(orgId, issueType), rows, page);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orgId", orgId);
		if (null != issueType) {
			map.put("issueType", issueType);
			map.put("status", ApprovalItemStatus.IN_PROCESS);
		}
		map.put("completeCode", FourTeamsIssueState.STEPCOMPLETE_CODE);
		map.put("sortField", sidx);
		map.put("order", sord);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.findMyNeedDoIssues", map, (page - 1) * rows,
				rows));
		return result;
	}

	public PageInfo<FourTeamsIssueViewObject> findcommandCenterMyNeedDoIssues(
			Long orgId, Long issueType, int page, int rows, String sidx,
			String sord, String commandCenterNum) {
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getMyNeedDoCount(orgId, issueType), rows, page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		if (null != issueType) {
			map.put("issueType", issueType);
			map.put("status", ApprovalItemStatus.IN_PROCESS);
		}
		map.put("completeCode", FourTeamsIssueState.STEPCOMPLETE_CODE);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("commandCenterNum", commandCenterNum);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.findcommandCenterMyNeedDoIssues", map,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findMyDone(Long orgId,
			FourTeamsIssueNew issue, Integer page, Integer rows, String sidx,
			String sord, Long issueType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		map.put("status", issue == null ? null
				: (issue.getStatus() == -1 ? null : issue.getStatus()));
		map.put("completeCode", FourTeamsIssueState.STEPCOMPLETE_CODE);
		map.put("sortField", sidx);
		map.put("order", sord);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getMyDoneCount(map), rows, page);
		result.setResult(getSqlMapClientTemplate()
				.queryForList("fourTeamsIssue.findMyDoneIssues", map,
						(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findcommandCenterMyDone(
			Long orgId, FourTeamsIssueNew issue, Integer page, Integer rows,
			String sidx, String sord, String commandCenterNum, int stateKind) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		if (stateKind == -1) {
			map.put("stepcompleteCode", FourTeamsIssueState.DEALING_CODE);
		} else if (stateKind == 300) {
			Organization organization = (Organization) getSqlMapClientTemplate()
					.queryForObject("organization.findById", orgId);
			map.put("issuecompleteCode", FourTeamsIssueState.DEALING);
			map.put("orgCode", organization.getOrgInternalCode());
		} else {
			Organization organization = (Organization) getSqlMapClientTemplate()
					.queryForObject("organization.findById", orgId);
			map.put("stepcompleteCode", FourTeamsIssueState.DEALING_CODE);
			map.put("issuecompleteCode", FourTeamsIssueState.DEALING);
			map.put("orgCode", organization.getOrgInternalCode());
		}
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("commandCenterNum", commandCenterNum);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getMyDoneCount(map), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.findcommandCenterMyDoneIssues", map,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public int getMyNeedDoCount(Long orgId, Long issueType) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != issueType) {
			map.put("issueType", issueType);
			map.put("status", ApprovalItemStatus.IN_PROCESS);
		}
		map.put("orgId", orgId);
		map.put("completeCode", FourTeamsIssueState.STEPCOMPLETE_CODE);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.countMyNeedDo", map);
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findMyHistoricIssues(Long orgId,
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("orgCode", orgInternalCode);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getMyHistoricCount(orgInternalCode), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.findMyHistoricIssues", map, (page - 1) * rows,
				rows));
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findMyPublicltyIssues(Long orgId,
			Integer page, Integer rows, String sidx, String sord) {
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getMyPublicltyCount(orgId), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.findMyPublicltyIssues", orgId,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsSkipIssue(
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (leaderView != null && !"".equals(leaderView)) {
			map.put("leaderView", leaderView);
		}
		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		map.put("functionalOrgType", functionalOrgType);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		// map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
		map.put("issueTag", FourTeamsIssueTag.SKIP_ISSUE);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsSkipIssue(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.findJurisdictionsSkeipIssue", map,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsDone(
			String statusType, String fourTeamsType, String seachValue,
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (leaderView != null && !"".equals(leaderView)) {
			map.put("leaderView", leaderView);
		}
		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		map.put("functionalOrgType", functionalOrgType);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("seachValue", seachValue);
		map.put("fourTeamsType", fourTeamsType);
		map.put("completeCode", FourTeamsIssueState.STEPCOMPLETE_CODE);
		map.put("issueTag", FourTeamsIssueTag.DONE_ISSUE);
		String tablePrefix = getTablePrefix(statusType);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsDone(map, tablePrefix), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					tablePrefix + ".findJurisdictionsDoneForFourTeams", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					tablePrefix + ".findJurisdictionsDoneForFourTeams", map,
					(page - 1) * rows, rows));
		}
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsCompleted(
			String fourTeamsType, String seachValue, Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, String statusType, Integer viewProcess,
			Long sourceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (leaderView != null && !"".equals(leaderView)) {
			map.put("leaderView", leaderView);
		}
		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		map.put("functionalOrgType", functionalOrgType);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("seachValue", seachValue);
		map.put("fourTeamsType", fourTeamsType);
		map.put("statusType", statusType);
		map.put("completeCode", FourTeamsIssueState.ISSUECOMPLETE_CODE);
		map.put("issueTag", FourTeamsIssueTag.COMPLETED_ISSUE);
		String tablePrefix = getTablePrefix(statusType);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsCompleted(map, tablePrefix), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					tablePrefix + ".findJurisdictionsCompletedForFourTeams",
					map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					tablePrefix + ".findJurisdictionsCompletedForFourTeams",
					map, (page - 1) * rows, rows));
		}
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findMyCompleted(Long orgId,
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Long issueType) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		map.put("orgId", orgId);
		map.put("orgCode", orgInternalCode);
		map.put("completeCode", FourTeamsIssueState.ISSUECOMPLETE_CODE);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getMyCompleted(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.findMyCompleted", map, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsNeedDo(
			String fourTeamsType, String seachValue, Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (leaderView != null && !"".equals(leaderView)) {
			map.put("leaderView", leaderView);
		}
		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		map.put("functionalOrgType", functionalOrgType);
		map.put("seachValue", seachValue);
		map.put("fourTeamsType", fourTeamsType);
		map.put("orgId", org.getId());
		map.put("orgCode", org.getOrgInternalCode());
		map.put("completeCode", FourTeamsIssueState.STEPCOMPLETE_CODE);
		map.put("issueTag", FourTeamsIssueTag.NEEDDO_ISSUE);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsNeedDoCount(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					"fourTeamsIssue.findJurisdictionsNeedDoForFourTeams", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"fourTeamsIssue.findJurisdictionsNeedDoForFourTeams", map,
					(page - 1) * rows, rows));
		}

		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsDoing(
			String seachValue, Organization org, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (leaderView != null && !"".equals(leaderView)) {
			map.put("leaderView", leaderView);
		}
		map.put("functionalOrgType", functionalOrgType);
		map.put("seachValue", seachValue);
		map.put("orgId", org.getId());
		map.put("orgCode", org.getOrgInternalCode());
		map.put("unconceptedCode", FourTeamsIssueState.UNCONCEPTED_CODE);
		map.put("completeCode", FourTeamsIssueState.STEPCOMPLETE_CODE);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsDoingCount(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					"fourTeamsIssue.findJurisdictionsDoing", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"fourTeamsIssue.findJurisdictionsDoing", map,
					(page - 1) * rows, rows));
		}
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsAssgin(
			String seachValue, Organization org, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (leaderView != null && !"".equals(leaderView)) {
			map.put("leaderView", leaderView);
		}
		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		map.put("functionalOrgType", functionalOrgType);
		map.put("seachValue", seachValue);
		map.put("orgId", org.getId());
		map.put("orgCode", org.getOrgInternalCode());
		map.put("completeCode", FourTeamsIssueState.STEPCOMPLETE_CODE);
		map.put("assgin", FourTeamsIssueSourceState.assign);
		map.put("issueTag", FourTeamsIssueTag.ASSIGN_ISSUE);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsAssginCount(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					"fourTeamsIssue.findJurisdictionsAssgin", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"fourTeamsIssue.findJurisdictionsAssgin", map,
					(page - 1) * rows, rows));
		}
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsSubmit(
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (leaderView != null && !"".equals(leaderView)) {
			map.put("leaderView", leaderView);
		}
		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		map.put("functionalOrgType", functionalOrgType);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("completeCode", FourTeamsIssueState.STEPCOMPLETE_CODE);
		map.put("submit", FourTeamsIssueSourceState.submit);
		map.put("issueTag", FourTeamsIssueTag.SUBMIT_ISSUE);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsSubmit(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					"fourTeamsIssue.findJurisdictionsSubmit", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"fourTeamsIssue.findJurisdictionsSubmit", map,
					(page - 1) * rows, rows));
		}
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsNeedDoForProcess(
			String seachValue, Organization org, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seachValue", seachValue);
		map.put("orgId", org.getId());
		map.put("orgCode", org.getOrgInternalCode());
		map.put("completeCode", FourTeamsIssueState.STEPCOMPLETE_CODE);
		map.put("sortField", sidx);
		map.put("order", sord);
		PageInfo<FourTeamsIssueViewObject> result = new PageInfo<FourTeamsIssueViewObject>();
		result.setResult(getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.findJurisdictionsNeedDo", map));
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsDoneForProcess(
			String seachValue, Organization org, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seachValue", seachValue);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("completeCode", FourTeamsIssueState.ISSUECOMPLETE_CODE);
		PageInfo<FourTeamsIssueViewObject> result = new PageInfo<FourTeamsIssueViewObject>();
		map.put("sortField", sidx);
		map.put("order", sord);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.findJurisdictionsCompleted", map));
		return result;
	}

	@Override
	public List<FourTeamsIssueNew> searchAllRoundIssues(GisInfo minOption,
			GisInfo maxOption) {
		Map<String, GisInfo> map = new HashMap<String, GisInfo>();
		map.put("minOption", minOption);
		map.put("maxOption", maxOption);
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.searchAllRoundIssues", map);
	}

	@Override
	public void addIssueHasTypes(Long issueId, Long IssueTypeId,
			Long IssueTypeDomainId) {
		Map<String, Object> map = getparameters(issueId, IssueTypeId,
				IssueTypeDomainId);
		getSqlMapClientTemplate().insert("fourTeamsIssue.addIssueHasType", map);

	}

	private Map<String, Object> getparameters(Long issueId, Long IssueTypeId,
			Long IssueTypeDomainId) {
		Map<String, Object> map = new HashMap<String, Object>();
		IssueType type = new IssueType();
		IssueTypeDomain issueDomain = new IssueTypeDomain();
		issueDomain.setId(IssueTypeDomainId);

		type.setId(IssueTypeId);
		type.setIssueTypeDomain(issueDomain);

		map.put("issueId", issueId);
		map.put("type", type);
		return map;
	}

	@Override
	public void deleteIssueHasTypesByIssueId(Long issueId) {
		getSqlMapClientTemplate().delete(
				"fourTeamsIssue.deleteIssueHasTypesByIssueId", issueId);
	}

	@Override
	public void deleteIssueHasTypesByIssueIdAndIssueTypeId(Long issueId,
			Long IssueTypeId) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("issueId", issueId);
		map.put("IssueTypeId", IssueTypeId);
		getSqlMapClientTemplate().delete(
				"fourTeamsIssue.deleteIssueHasTypesByIssueIdAndIssueTypeId",
				map);

	}

	@Override
	public void deleteAttachFileByAttachfilesId(Long fileId) {
		getSqlMapClientTemplate().delete(
				"fourTeamsIssue.deleteAttachFileBymoduleobjectId", fileId);
		getSqlMapClientTemplate().delete(
				"fourTeamsIssue.deleteIssuehasattachfilesById", fileId);
	}

	@Override
	public FourTeamsIssueNew getIssueBySerialNumber(String serialNumber) {
		if (null == serialNumber || "".equals(serialNumber.trim())) {
			return null;
		}
		return (FourTeamsIssueNew) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.getIssueBySerialNumber", serialNumber);
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionAuditDelay(
			Long orgId, Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsAuditDelayCount(orgId), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.findJurisdictionAuditDelay", map,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public FourTeamsIssueEvaluate getIssueEvaluateById(Long id) {
		return (FourTeamsIssueEvaluate) getSqlMapClientTemplate()
				.queryForObject("fourTeamsIssue.getIssueEvaluateById", id);
	}

	@Override
	public Integer getIssueStepDelayWorkDaysById(Long stepId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.getIssueStepDelayWorkDaysById", stepId);
	}

	@Override
	public List<FourTeamsIssueAttachFile> getIssueAttachFileAndNotLogAttachFileByIssueId(
			Long issueId) {
		return getSqlMapClientTemplate()
				.queryForList(
						"fourTeamsIssue.getIssueAttachFileAndNotLogAttachFileByIssueId",
						issueId);
	}

	@Override
	public List<FourTeamsIssueAttachFile> getIssueEvaluateAttachFileAttachFileByIssueId(
			Long issueId) {
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.getIssueEvaluateAttachFileAttachFileByIssueId",
				issueId);
	}

	@Override
	public List<FourTeamsIssueStep> searchIssueStepsByIssueId(Long issueId) {
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.searchIssueStepsByIssueId", issueId);
	}

	@Override
	public List<FourTeamsIssueStep> searchAllIssueStepsByStepId(Long stepId) {
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.searchAllIssueStepsByStepId", stepId);
	}

	@Override
	public List<FourTeamsIssueAttachFile> getDocfilesByIdAndModuleKey(
			Long issueId, String moduleType, String fileType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileId", issueId);
		map.put("moduleKey", moduleType);
		map.put("fileType", fileType);
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssue.getDocfilesByIdAndModuleKey", map);
	}

	@Override
	public Integer findIssueExistForMobile(FourTeamsIssueNew issue) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssue.queryIssueExistForCount", issue);
	}

	private String getTablePrefix(String statusType) {
		if (IssueConstants.STATUSTYPE_HISTORY.equals(statusType)) {
			return "fourTeamsIssue_history";
		}
		return "fourTeamsIssue";
	}
}

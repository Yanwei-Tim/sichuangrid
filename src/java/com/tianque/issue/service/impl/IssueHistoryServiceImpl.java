package com.tianque.issue.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.approval.service.ApprovalItemService;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.constants.IssueConstants;
import com.tianque.issue.constants.IssueTag;
import com.tianque.issue.dao.IssueDao;
import com.tianque.issue.dao.IssueHistoryDao;
import com.tianque.issue.dao.TopIssueDao;
import com.tianque.issue.domain.CompletedIssue;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueMap;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.service.CompletedIssueService;
import com.tianque.issue.service.IssueHistoryService;
import com.tianque.issue.service.IssueRelatedPeopleService;
import com.tianque.issue.state.IssueStepGroup;
import com.tianque.issue.uitl.IssueMapUtil;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.jms.OperateMode;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.tqSearch.constant.TqSolrSearchType;
import com.tianque.tqSearch.dubboService.TqSearchDubboService;
import com.tianque.userAuth.api.PermissionDubboService;

/**
 * @Description: 事件分离服务
 */
@Transactional
@Service("issueHistoryService")
public class IssueHistoryServiceImpl implements IssueHistoryService {

	@Autowired
	private IssueHistoryDao issueHistoryDao;
	@Autowired
	protected IssueDao issueDao;
	@Autowired
	protected TopIssueDao topIssueDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private CompletedIssueService completedIssueService;
	@Autowired
	private IssueRelatedPeopleService issueRelatedPeopleService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private ApprovalItemService approvalItemService;
	@Autowired
	private PermissionDubboService permissionDubboService;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private TqSearchDubboService tqSearchDubboService;

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsSkipIssueHistory(
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode) {
		return issueHistoryDao.findJurisdictionsSkipIssueHistory(org, page,
				rows, sidx, sord, issueType, orgLevel, leaderView,
				functionalOrgType, viewProcess, sourceType, orgCodeFindLevel,
				searchOrgId, searchOrgCode);
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsDoneHistory(
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode) {
		return issueHistoryDao.findJurisdictionsDoneHistory(org, page, rows,
				sidx, sord, issueType, orgLevel, leaderView, functionalOrgType,
				viewProcess, sourceType, orgCodeFindLevel, searchOrgId,
				searchOrgCode);
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsAssginHistory(
			String seachValue, Organization org, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType, int orgCodeFindLevel, Long searchOrgId,
			String searchOrgCode) {
		return issueHistoryDao.findJurisdictionsAssginHistory(seachValue, org,
				page, rows, sidx, sord, issueType, orgLevel, leaderView,
				functionalOrgType, viewProcess, sourceType, orgCodeFindLevel,
				searchOrgId, searchOrgCode);
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsSubmitHistory(
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode) {
		return issueHistoryDao.findJurisdictionsSubmitHistory(org, page, rows,
				sidx, sord, issueType, orgLevel, leaderView, functionalOrgType,
				viewProcess, sourceType, orgCodeFindLevel, searchOrgId,
				searchOrgCode);
	}

	@Override
	public List<IssueLogNew> loadIssueOperationLogsHistoryByIssueId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("事件id不能为空");
		}
		return issueHistoryDao.loadIssueOperationLogsHistoryByIssueId(id);
	}

	@Override
	public IssueNew getFullIssueHistoryById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("事件id不能为空");
		}
		IssueNew issue = issueHistoryDao.getFullIssueHistoryById(id);
		populationIssueProperty(issue);
		return issue;
	}

	private void populationIssueProperty(IssueNew issue) {
		if (issue == null) {
			return;
		}
		if (issue.getSourceKind() != null
				&& issue.getSourceKind().getId() != null) {
			issue.setSourceKind(propertyDictService.getPropertyDictById(issue
					.getSourceKind().getId()));
		}
		if (issue.getCurrentOrg() != null
				&& issue.getCurrentOrg().getId() != null) {
			issue.setCurrentOrg(organizationDubboService.getSimpleOrgById(issue
					.getCurrentOrg().getId()));
		}
		if (issue.getLastOrg() != null && issue.getLastOrg().getId() != null) {
			issue.setLastOrg(organizationDubboService.getSimpleOrgById(issue
					.getLastOrg().getId()));
		}
		if (issue.getOccurOrg() != null && issue.getOccurOrg().getId() != null) {
			issue.setOccurOrg(organizationDubboService.getSimpleOrgById(issue
					.getOccurOrg().getId()));
		}
		if (issue.getCreateUser() != null) {
			issue.setSourceMobile(permissionDubboService.getFullUserByUerName(
					issue.getCreateUser()).getMobile());
		}
	}

	@Override
	public List<IssueMap> getIssueHistoryMap(Long issueId) {

		List<IssueStepGroup> stepGroupList = issueHistoryDao
				.getIssueStepGroupHistoryByIssueId(issueId);
		List<IssueMap> issueMapList;
		if (null != stepGroupList && stepGroupList.size() > 0) {
			issueMapList = loadIssueMap(stepGroupList);
		} else {
			issueMapList = new ArrayList<IssueMap>();
		}
		return issueMapList;

	}

	private List<IssueMap> loadIssueMap(List<IssueStepGroup> stepGroupList) {
		List<IssueMap> issueMapList = new ArrayList<IssueMap>();
		for (int i = 0; i < stepGroupList.size(); i++) {
			IssueStepGroup isg = stepGroupList.get(i);
			if (i == stepGroupList.size() - 1) {
				isg.setOutLog(isg.getEntyLog());
			}
			IssueMap im = issueHistoryDao.getIssueMapHistoryByStepGroup(isg);
			if (im != null) {
				im.setId(isg.getId());
				// 设置事件为历史事件
				im.setStatusType(IssueConstants.STATUSTYPE_HISTORY);
				if (i + 1 < stepGroupList.size()) {
					im.setTo(stepGroupList.get(i + 1).getId());
				}
				im = IssueMapUtil.setRelationAndStates(im);
				im.setFunctionalOrg(propertyDictService.getPropertyDictById(
						organizationDubboService
								.getSimpleOrgById(im.getOrgId()).getOrgType()
								.getId()).getInternalId() == OrganizationType.FUNCTIONAL_ORG);
				issueMapList.add(im);
			}
		}
		return issueMapList;
	}

	@Override
	public List<IssueLogNew> getIssueDealLogHistory(IssueMap issueMap) {
		IssueStepGroup group = issueHistoryDao
				.getSimpleIssueStepHistoryGroupById(issueMap.getId());

		List<IssueLogNew> logs = issueHistoryDao
				.findIssueDealLogHistoryByIssueMapAndIssueStepGroup(issueMap,
						group);
		return logs;
	}

	@Override
	public List<IssueStep> searchIssueStepsHistoryByIssueId(Long issueId) {
		if (issueId == null) {
			throw new BusinessValidationException("事件id不能为空");
		}
		return issueHistoryDao.searchIssueStepsHistoryByIssueId(issueId);
	}

	@Override
	public List<IssueStep> searchAllIssueStepsHistoryByStepId(Long stepId) {
		if (stepId == null) {
			throw new BusinessValidationException("步骤id不能为空");
		}
		return issueHistoryDao.searchAllIssueStepsHistoryByStepId(stepId);
	}

	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.ISSUES_KEY)
	public boolean deleteIssueHistoryByIssueId(Long issueId) {
		List<IssueStep> issueSteps = issueHistoryDao
				.searchIssueStepsHistoryByIssueId(issueId);
		IssueNew issue = getFullIssueHistoryById(issueId);
		approvalItemService.deleteApprovalItemByApprovalNumber(issue
				.getSerialNumber());
		removeIssueAllAttachFiles(issueId);
		removeIssueFromPublilty(issueId);
		// issueDao.deleteIssueHasTypesByIssueId(issueId);
		issueHistoryDao.deleteIssueLogHistoryByIssueId(issueId);
		issueHistoryDao.deleteIssueStepsHistoryByIssueId(issueId);
		issueTypeService.deleteRelatePersons(issueId);
		issueTypeService.deleteRelatePlaces(issueId);
		issueHistoryDao.deleteIssueHistoryById(issueId);
		topIssueDao.deleteTopIssue(issueId, ThreadVariable.getOrganization()
				.getId(), IssueTag.NEEDDO_ISSUE);
		issueRelatedPeopleService.deleteIssueRelatedPeopleByIssueId(issueId);// 删除相关人员信息
		CompletedIssue completedIssue = completedIssueService
				.getCompletedByIssueId(issueId);// 删除事件操作时，判断是否是已办结中的操作
		if (completedIssue != null && completedIssue.getIssueId() != null) {
			completedIssueService.removeCompletedIssueByIssueId(issueId);
		}
		syncSolrIndexForDelete(issueSteps);
		return true;
	}
	
	private void syncSolrIndexForDelete(List<IssueStep> issueSteps){
		if (Boolean.valueOf(globalSettingService
				.getGlobalValue(GlobalSetting.IS_TQSEARCH_OPEN))) {
			List<String> solrIds = new ArrayList<String>();
			if (issueSteps != null && issueSteps.size() > 0) {
				for (IssueStep issueStep : issueSteps) {
					solrIds.add(TqSolrSearchType.ISSUE_TYPE + "_"
							+ issueStep.getId());
				}
			}
			tqSearchDubboService.deleteSolrIndexById(solrIds,
					TqSolrSearchType.ISSUE_TYPE);
		}
	}

	/***************************************************************************
	 * 删除事件的附件和附件关联关系
	 * 
	 * @param issueId
	 *            事件id
	 */
	private void removeIssueAllAttachFiles(Long issueId) {
		List<IssueAttachFile> issueAttachFiles = loadIssueAttachFilesByIssueId(issueId);
		if (issueAttachFiles != null && issueAttachFiles.size() > 0) {
			String webRootPath = FileUtil.getWebRoot();
			for (IssueAttachFile issueFile : issueAttachFiles) {
				File file = new File(webRootPath + File.separator
						+ issueFile.getFileActualUrl());
				if (file.exists()) {
					file.delete();
				}
			}
		}
		issueDao.removeAllIssueAttachFiles(issueId);
	}

	/**
	 * 从宣传案例中删除事件
	 * 
	 * @param issueId
	 *            事件id
	 */
	private void removeIssueFromPublilty(Long issueId) {
		issueDao.removePubliclty(null, issueId);
	}

	private List<IssueAttachFile> loadIssueAttachFilesByIssueId(Long issueId) {
		return issueDao.loadIssueAttachFilesByIssueAndLog(issueId, null);
	}

	@Override
	public IssueNew getFullIssueByHistoryStepId(Long stepId) {
		return issueHistoryDao.getFullIssueByHistoryStepId(stepId);
	}

	@Override
	public IssueStep getSimpleIssueStepHistoryById(Long stepId) {
		return issueHistoryDao.getSimpleIssueStepHistoryById(stepId);
	}

}

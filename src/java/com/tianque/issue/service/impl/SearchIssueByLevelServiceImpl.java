package com.tianque.issue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.utils.CustomDateUtil;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issue.constants.IssueConstants;
import com.tianque.issue.controller.IssueOvertimeHelper;
import com.tianque.issue.dao.SearchIssueByLevelDao;
import com.tianque.issue.domain.CompletedIssue;
import com.tianque.issue.domain.IssueEvaluate;
import com.tianque.issue.service.IssueEvaluateService;
import com.tianque.issue.service.SearchIssueByLevelService;
import com.tianque.issue.service.SearchIssueHistoryByLevelService;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.tqSearch.common.SolrResultRelationalObject;
import com.tianque.tqSearch.constant.TqSolrSearchType;
import com.tianque.tqSearch.domain.IssueSearchSolrParams;
import com.tianque.tqSearch.domain.TqSolrDocumentList;
import com.tianque.tqSearch.dubboService.TqSearchDubboService;
import com.tianque.util.DateFormat;

@Transactional
@Repository("searchIssueByLevelService")
public class SearchIssueByLevelServiceImpl implements SearchIssueByLevelService {
	@Autowired
	protected OrganizationDubboService organizationDubboService;
	@Autowired
	protected SearchIssueByLevelDao searchIssueByLevelDao;
	@Autowired
	private IssueOvertimeHelper issueOvertimeHelper;
	@Autowired
	private IssueEvaluateService issueEvaluateService;
	@Autowired
	private SearchIssueHistoryByLevelService searchIssueHistoryByLevelService;
	@Autowired
	private TqSearchDubboService tqSearchDubboService;
	@Autowired
	private GlobalSettingService globalSettingService;

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsNeedDo(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		setOrgCode(searchIssueVo);
		setOrderField(searchIssueVo, sidx, sord);
		PageInfo<IssueViewObject> pageInfo = null;
		if (Boolean.valueOf(globalSettingService
				.getGlobalValue(GlobalSetting.IS_TQSEARCH_OPEN))) {
			StringBuffer query = new StringBuffer();
			query.append("stateCode:[* TO 500}");
			createSolrQueryBySearchVo(searchIssueVo, query);
			createSolrQuery(query, searchIssueVo.getOrgLevel(),
					searchIssueVo.getSearchOrgId(),
					searchIssueVo.getFunctionalOrgType(),
					searchIssueVo.getSearchOrgCode(),
					searchIssueVo.getOrgCode());
			pageInfo = createPageInfoFromSolr(page, rows, sidx, sord, query);
		} else {
			pageInfo = searchIssueByLevelDao.findJurisdictionsNeedDo(
					searchIssueVo, page, rows);
		}
		for (IssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionAuditDelay(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		if (searchIssueVo.getSearchOrgId() != null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(searchIssueVo.getSearchOrgId());
			if (org != null) {
				searchIssueVo.setOrgCode(org.getOrgInternalCode());
			}
		} else {
			createEmptyIssueVoPageInfo(rows, 0);
		}
		setOrderField(searchIssueVo, sidx, sord);
		searchIssueVo.setTargeOrgId(ThreadVariable.getOrganization().getId());
		PageInfo<IssueViewObject> pageInfo = searchIssueByLevelDao
				.findJurisdictionAuditDelay(searchIssueVo, page, rows);
		for (IssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsSkipIssue(
			String statusType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord) {
		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		setOrgCode(searchIssueVo);
		setOrderField(searchIssueVo, sidx, sord);
		PageInfo<IssueViewObject> pageInfo;
		if (IssueConstants.STATUSTYPE_HISTORY.equals(statusType)) {
			pageInfo = searchIssueHistoryByLevelService
					.findJurisdictionsSkipIssueHistory(searchIssueVo, page,
							rows);
		} else {
			if (Boolean.valueOf(globalSettingService
					.getGlobalValue(GlobalSetting.IS_TQSEARCH_OPEN))) {
				StringBuffer query = new StringBuffer();
				query.append("emergencyLevel:[0 TO *]");
				createSolrQueryBySearchVo(searchIssueVo, query);
				createSolrQuery(query, searchIssueVo.getOrgLevel(),
						searchIssueVo.getSearchOrgId(),
						searchIssueVo.getFunctionalOrgType(),
						searchIssueVo.getSearchOrgCode(),
						searchIssueVo.getOrgCode());
				pageInfo = createPageInfoFromSolr(page, rows, sidx, sord, query);
			} else {
				pageInfo = searchIssueByLevelDao.findJurisdictionsSkipIssue(
						searchIssueVo, page, rows);
			}
		}
		for (IssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsFollow(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord) {
		try {
			if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
				return createEmptyIssueVoPageInfo(rows, 0);
			}
			setOrgCode(searchIssueVo);
			setOrderField(searchIssueVo, sidx, sord);
			PageInfo<IssueViewObject> pageInfo = null;
			if (Boolean.valueOf(globalSettingService
					.getGlobalValue(GlobalSetting.IS_TQSEARCH_OPEN))) {
				StringBuffer query = new StringBuffer();
				query.append("stateCode:[500 TO 1000} AND status:[* TO 300}");
				createSolrQueryBySearchVo(searchIssueVo, query);
				createSolrQuery(query, searchIssueVo.getOrgLevel(),
						searchIssueVo.getSearchOrgId(),
						searchIssueVo.getFunctionalOrgType(),
						searchIssueVo.getSearchOrgCode(),
						searchIssueVo.getOrgCode());
				pageInfo = createPageInfoFromSolr(page, rows, sidx, sord, query);
			} else {
				pageInfo = searchIssueByLevelDao.findJurisdictionsFollow(
						searchIssueVo, page, rows);
			}
			for (IssueViewObject issueViewObject : pageInfo.getResult()) {
				issueOvertimeHelper.fillEaringWarnField(issueViewObject);
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("待跟进搜索过程中发生错误", e);
		}
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsDone(String statusType,
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		setOrgCode(searchIssueVo);
		setOrderField(searchIssueVo, sidx, sord);
		PageInfo<IssueViewObject> pageInfo;
		if (IssueConstants.STATUSTYPE_HISTORY.equals(statusType)) {
			pageInfo = searchIssueHistoryByLevelService
					.findJurisdictionsDoneHistory(searchIssueVo, page, rows);
		} else {
			if (Boolean.valueOf(globalSettingService
					.getGlobalValue(GlobalSetting.IS_TQSEARCH_OPEN))) {
				StringBuffer query = new StringBuffer();
				query.append("stateCode:[500 TO *} AND isStayStep:1");
				createSolrQueryBySearchVo(searchIssueVo, query);
				createSolrQuery(query, searchIssueVo.getOrgLevel(),
						searchIssueVo.getSearchOrgId(),
						searchIssueVo.getFunctionalOrgType(),
						searchIssueVo.getSearchOrgCode(),
						searchIssueVo.getOrgCode());
				pageInfo = createPageInfoFromSolr(page, rows, sidx, sord, query);
			} else {
				pageInfo = searchIssueByLevelDao.findJurisdictionsDone(
						searchIssueVo, page, rows);
			}
		}
		for (IssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<CompletedIssue> findJurisdictionsCompleted(Long keyId,
			Long issueType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord) {
		int isCurrentOrg = IssueConstants.NOT_CURRENT_ORG;// 已办结列表是不是查询自己本层级数据，默认查询下辖已办结列表
		Organization org = organizationDubboService.getSimpleOrgById(keyId);
		if ((org.getOrgLevel().getId()).equals(searchIssueVo.getOrgLevel())) {
			isCurrentOrg = IssueConstants.IS_CURRENT_ORG;
		}
		searchIssueVo.setIsCurrentOrg(isCurrentOrg + "");
		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfoOfComplete(rows, 0);
		}
		setOrgCode(searchIssueVo);
		setOrderField(searchIssueVo, sidx, sord);
		PageInfo<CompletedIssue> pageInfo = searchIssueByLevelDao
				.findJurisdictionsCompleted(searchIssueVo, page, rows);
		for (IssueViewObject issueViewObject : pageInfo.getResult()) {
			IssueEvaluate issueEvaluate = issueEvaluateService
					.getIssueEvaluateByIssueId(issueViewObject.getIssueId());
			if (issueEvaluate != null && issueEvaluate.getId() != null) {
				issueViewObject.setEvaluateType(issueEvaluate.getEvaluateType());
				issueViewObject.setStatus(IssueState.GRADEDELAY);
			}
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsPublicltyCassDone(
			Long issueType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord) {
		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		setOrgCode(searchIssueVo);
		setOrderField(searchIssueVo, sidx, sord);
		PageInfo<IssueViewObject> pageInfo;
		if (Boolean.valueOf(globalSettingService
				.getGlobalValue(GlobalSetting.IS_TQSEARCH_OPEN))) {
			StringBuffer query = new StringBuffer();
			query.append("stateCode:[1000 TO *] AND publicltycass:1");
			createSolrQueryBySearchVo(searchIssueVo, query);
			createSolrQueryForCreateOrg(query, searchIssueVo.getOrgLevel(),
					searchIssueVo.getSearchOrgId(),
					searchIssueVo.getFunctionalOrgType(),
					searchIssueVo.getSearchOrgCode(),
					searchIssueVo.getOrgCode());
			pageInfo = createPageInfoFromSolr(page, rows, sidx, sord, query);
		} else {
			pageInfo = searchIssueByLevelDao
					.findJurisdictionsPublicltyCassDone(searchIssueVo, page,
							rows);
		}
		for (IssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> findTimeOutIssue(
			SearchIssueVoNew searchIssueVo, Integer superviseType,
			Integer page, Integer rows, String sidx, String sord) {
		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		setOrgCode(searchIssueVo);
		setOrderField(searchIssueVo, sidx, sord);
		PageInfo<IssueViewObject> pageInfo = null;
		if (Boolean.valueOf(globalSettingService
				.getGlobalValue(GlobalSetting.IS_TQSEARCH_OPEN))) {
			StringBuffer query = new StringBuffer();
			query.append("(stateCode:800 OR stateCode:[* TO 500})");
			createSolrQueryBySearchVo(searchIssueVo, query);
			if (superviseType == 0) {
				query.append(" AND superviseLevel:[100 TO *]");
			}
			if (superviseType == 100) {
				query.append(" AND superviseLevel:100");
			}
			if (superviseType == 200) {
				query.append(" AND superviseLevel:200");
			}
			createSolrQuery(query, searchIssueVo.getOrgLevel(),
					searchIssueVo.getSearchOrgId(),
					searchIssueVo.getFunctionalOrgType(),
					searchIssueVo.getSearchOrgCode(),
					searchIssueVo.getOrgCode());
			pageInfo = createPageInfoFromSolr(page, rows, sidx, sord, query);
		} else {
			pageInfo = searchIssueByLevelDao.findTimeOutIssue(searchIssueVo,
					page, rows);
		}
		for (IssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<CompletedIssue> findJurisdictionsGradeDelay(Long keyId,
			Long issueType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord) {
		int isCurrentOrg = IssueConstants.NOT_CURRENT_ORG;// 已办结列表是不是查询自己本层级数据，默认查询下辖已办结列表
		Organization org = organizationDubboService.getSimpleOrgById(keyId);
		if ((org.getOrgLevel().getId()).equals(searchIssueVo.getOrgLevel())) {
			isCurrentOrg = IssueConstants.IS_CURRENT_ORG;
		}
		searchIssueVo.setIsCurrentOrg(isCurrentOrg + "");

		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfoOfComplete(rows, 0);
		}
		setOrgCode(searchIssueVo);
		setOrderField(searchIssueVo, sidx, sord);
		/** 20天以内的展示的条件 */
		searchIssueVo.setLimitDate(CustomDateUtil
				.dateBeforeNowDateByDays(CustomDateUtil.DAYS_BEFORE));
		PageInfo<CompletedIssue> pageInfo = searchIssueByLevelDao
				.findJurisdictionsGradeDelay(searchIssueVo, page, rows);
		for (IssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
			fillStatusForGradeDelayIssue(issueViewObject);
		}
		return pageInfo;
	}

	// 待评分页面中，对于已评分的数据进行状态修改
	private void fillStatusForGradeDelayIssue(IssueViewObject issueViewObject) {
		IssueEvaluate evaluate = issueEvaluateService
				.getIssueEvaluateByIssueId(issueViewObject.getIssueId());
		if (evaluate != null) {
			issueViewObject.setStatus(IssueState.GRADEDELAY);
		}
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsVerification(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		setOrgCode(searchIssueVo);
		setOrderField(searchIssueVo, sidx, sord);
		PageInfo<IssueViewObject> pageInfo = null;
		if (Boolean.valueOf(globalSettingService
				.getGlobalValue(GlobalSetting.IS_TQSEARCH_OPEN))) {
			StringBuffer query = new StringBuffer();
			query.append("stateCode:800");
			createSolrQueryBySearchVo(searchIssueVo, query);
			createSolrQueryForCreateOrg(query, searchIssueVo.getOrgLevel(),
					searchIssueVo.getSearchOrgId(),
					searchIssueVo.getFunctionalOrgType(),
					searchIssueVo.getSearchOrgCode(),
					searchIssueVo.getOrgCode());
			pageInfo = createPageInfoFromSolr(page, rows, sidx, sord, query);
		} else {
			pageInfo = searchIssueByLevelDao.findJurisdictionsVerification(
					searchIssueVo, page, rows);
		}

		for (IssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsSubmit(String statusType,
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		setOrgCode(searchIssueVo);
		setOrderField(searchIssueVo, sidx, sord);
		PageInfo<IssueViewObject> pageInfo;
		if (IssueConstants.STATUSTYPE_HISTORY.equals(statusType)) {
			pageInfo = searchIssueHistoryByLevelService
					.findJurisdictionsSubmitHistory(searchIssueVo, page, rows);
		} else {
			if (Boolean.valueOf(globalSettingService
					.getGlobalValue(GlobalSetting.IS_TQSEARCH_OPEN))) {
				StringBuffer query = new StringBuffer();
				query.append("submit:1");
				createSolrQueryBySearchVo(searchIssueVo, query);
				createSolrQuery(query, searchIssueVo.getOrgLevel(),
						searchIssueVo.getSearchOrgId(),
						searchIssueVo.getFunctionalOrgType(),
						searchIssueVo.getSearchOrgCode(),
						searchIssueVo.getOrgCode());
				pageInfo = createPageInfoFromSolr(page, rows, sidx, sord, query);
			} else {
				pageInfo = searchIssueByLevelDao.findJurisdictionsSubmit(
						searchIssueVo, page, rows);
			}
		}
		for (IssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsAssgin(String statusType,
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord) {
		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		setOrgCode(searchIssueVo);
		setOrderField(searchIssueVo, sidx, sord);
		PageInfo<IssueViewObject> pageInfo;
		if (IssueConstants.STATUSTYPE_HISTORY.equals(statusType)) {
			pageInfo = searchIssueHistoryByLevelService
					.findJurisdictionsAssginHistory(searchIssueVo, page, rows);
		} else {
			if (Boolean.valueOf(globalSettingService
					.getGlobalValue(GlobalSetting.IS_TQSEARCH_OPEN))) {
				StringBuffer query = new StringBuffer();
				query.append("assign:1");
				createSolrQueryBySearchVo(searchIssueVo, query);
				createSolrQuery(query, searchIssueVo.getOrgLevel(),
						searchIssueVo.getSearchOrgId(),
						searchIssueVo.getFunctionalOrgType(),
						searchIssueVo.getSearchOrgCode(),
						searchIssueVo.getOrgCode());
				pageInfo = createPageInfoFromSolr(page, rows, sidx, sord, query);
			} else {
				pageInfo = searchIssueByLevelDao.findJurisdictionsAssgin(
						searchIssueVo, page, rows);
			}
		}
		for (IssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	private PageInfo<IssueViewObject> createEmptyIssueVoPageInfo(int pageSize,
			int pageIndex) {
		PageInfo<IssueViewObject> result = new PageInfo<IssueViewObject>();
		result.setTotalRowSize(0);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

	private PageInfo<CompletedIssue> createEmptyIssueVoPageInfoOfComplete(
			int pageSize, int pageIndex) {
		PageInfo<CompletedIssue> result = new PageInfo<CompletedIssue>();
		result.setTotalRowSize(0);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

	private void setOrderField(SearchIssueVoNew searchIssueVo, String sidx,
			String sord) {
		searchIssueVo.setSortField(sidx);
		searchIssueVo.setOrder(sord);
	}

	private void setOrgCode(SearchIssueVoNew searchIssueVo) {
		Organization org = organizationDubboService
				.getSimpleOrgById(searchIssueVo.getTargeOrgId());
		if (org != null && org.getOrgInternalCode() != null) {
			searchIssueVo.setTargeOrgInternalCode(org.getOrgInternalCode());
			searchIssueVo.setOrgId(searchIssueVo.getTargeOrgId());
			searchIssueVo.setOrgCode(org.getOrgInternalCode());
		}
	}

	private void createSolrQueryBySearchVo(SearchIssueVoNew searchIssueVo,
			StringBuffer query) {
		if (searchIssueVo.getIssueTypeDomain() != null
				&& searchIssueVo.getIssueTypeDomain().getId() != null) {
			query.append(" AND issueTypedDomainId:"
					+ searchIssueVo.getIssueTypeDomain().getId());
			if (searchIssueVo.getIssueTypes() != null
					&& searchIssueVo.getIssueTypes().size() > 0) {
				query.append(" AND issueTypeId:"
						+ searchIssueVo.getIssueTypes().get(0).getId());
			}
		}
		if (searchIssueVo.getIssueType() != null) {
			query.append(" AND issueTypedDomainId:"
					+ searchIssueVo.getIssueType());
		}
		if (StringUtil.isStringAvaliable(searchIssueVo.getSubject())) {
			query.append(" AND subject:" + searchIssueVo.getSubject() + "*");
		}
		if (StringUtil.isStringAvaliable(searchIssueVo.getSerialNumber())) {
			query.append(" AND serialNumber:" + searchIssueVo.getSerialNumber()
					+ "*");
		}
		if (searchIssueVo.getOccurOrg() != null
				&& searchIssueVo.getOccurOrg().getId() != null) {
			query.append(" AND occurOrg:" + searchIssueVo.getOccurOrg().getId());
		}
		if (searchIssueVo.getIssueKind() != null
				&& searchIssueVo.getIssueKind().getId() != null) {
			query.append(" AND issueKind:"
					+ searchIssueVo.getIssueKind().getId());
		}
		if (searchIssueVo.getIssueState() != null
				&& searchIssueVo.getIssueState() != -1) {
			query.append(" AND status:" + searchIssueVo.getIssueState());
		}
		if (searchIssueVo.getRelatePeopleMinCount() !=null){
			query.append(" AND relatePeopleCount:["
					+searchIssueVo.getRelatePeopleMinCount()+ " TO *]");
		}
		if (searchIssueVo.getRelatePeopleMaxCount() !=null){
			query.append(" AND relatePeopleCount:[* TO "
					+searchIssueVo.getRelatePeopleMaxCount()+ "]");
		}
		if (searchIssueVo.getRelateMinAmount() != null) {
			query.append(" AND relatePeopleCount:["
					+ searchIssueVo.getRelateMinAmount() + " TO *]");
		}
		if (searchIssueVo.getRelateMaxAmount() != null) {
			query.append(" AND relatePeopleCount:[* TO "
					+ searchIssueVo.getRelateMaxAmount() + "]");
		}
		if (searchIssueVo.getOccurFrom() != null) {
			query.append(" AND dealTime:["
					+ DateFormat.getSolrDateString(searchIssueVo.getOccurFrom())
					+ " TO *]");
		}
		if (searchIssueVo.getOccurEnd() != null) {
			query.append(" AND dealTime:[* TO "
					+ DateFormat.getSolrDateString(searchIssueVo.getOccurEnd()) + "]");
		}
		if (StringUtil.isStringAvaliable(searchIssueVo.getOccurLocation())) {
			query.append(" AND occurLocation:"
					+ searchIssueVo.getOccurLocation() + "*");
		}
		if (searchIssueVo.getEndTimeFrom() != null) {
			query.append(" AND endDate:["
					+ DateFormat.getSolrDateString(searchIssueVo
							.getEndTimeFrom()) + " TO *]");
		}
		if (searchIssueVo.getEndTimeEnd() != null) {
			query.append(" AND endDate:[* TO "
					+ DateFormat.getSolrDateString(searchIssueVo
							.getEndTimeEnd()) + "]");
		}
		if (StringUtil.isStringAvaliable(searchIssueVo.getEvaluateType())) {
			query.append(" AND evaluateType:" + searchIssueVo.getEvaluateType());
		}
	}

	private PageInfo<IssueViewObject> createPageInfoFromSolr(Integer page,
			Integer rows, String sidx, String sord, StringBuffer query) {
		TqSolrDocumentList documents = tqSearchDubboService.searchForSolrIndex(
				new IssueSearchSolrParams((page - 1) * rows, rows, sidx, sord)
						.setSolrQuery(query.toString()),
				TqSolrSearchType.ISSUE_TYPE);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>(
				page, rows, documents.getNumFound(), null);
		SolrResultRelationalObject.conversionPageInfoResult(documents,
				pageInfo, IssueViewObject.class, TqSolrSearchType.ISSUE_TYPE);
		return pageInfo;
	}

	private void createSolrQuery(StringBuffer query, Long orgLevel,
			Long searchOrgId, Long functionalorgType, String searchOrgCode,
			String targetOrgCode) {
		if (orgLevel != null) {
			query.append(" AND targetOrgLevel:" + orgLevel);
			if (searchOrgId == null) {
				query.append(" AND targetInternalCode:" + targetOrgCode + "*");
			} else {
				query.append(" AND targetInternalCode:" + searchOrgCode + "*");
			}
			if (functionalorgType == null) {
				query.append(" AND targetOrgFunctionalOrgType:0");
			} else {
				query.append(" AND targetOrgFunctionalOrgType:"
						+ functionalorgType);
			}
		} else {
			query.append(" AND targetInternalCode:" + targetOrgCode);
		}
	}

	private void createSolrQueryForCreateOrg(StringBuffer query, Long orgLevel,
			Long searchOrgId, Long functionalorgType, String searchOrgCode,
			String targetOrgCode) {
		if (orgLevel != null) {
			query.append(" AND createOrgLevel:" + orgLevel);
			if (searchOrgId == null) {
				query.append(" AND createOrginternalCode:" + targetOrgCode
						+ "*");
			} else {
				query.append(" AND createOrginternalCode:" + searchOrgCode
						+ "*");
			}
			if (functionalorgType == null) {
				query.append(" AND createOrgFunctionalOrgType:0");
			} else {
				query.append(" AND createOrgFunctionalOrgType:"
						+ functionalorgType);
			}
		} else {
			query.append(" AND createOrginternalCode:" + targetOrgCode);
		}
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsCompletedFromSolr(
			Long keyId, Long issueType, SearchIssueVoNew searchIssueVo,
			Integer page, Integer rows, String sidx, String sord) {
		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		setOrgCode(searchIssueVo);
		setOrderField(searchIssueVo, sidx, sord);
		StringBuffer query = new StringBuffer();
		query.append("stateCode:1000");
		createSolrQueryBySearchVo(searchIssueVo, query);
		createSolrQueryForCurrentOrg(query, searchIssueVo.getOrgLevel(),
				searchIssueVo.getSearchOrgId(),
				searchIssueVo.getFunctionalOrgType(),
				searchIssueVo.getSearchOrgCode(), searchIssueVo.getOrgCode());
//		createSolrQueryForCreateOrg(query, searchIssueVo.getOrgLevel(),
//				searchIssueVo.getSearchOrgId(),
//				searchIssueVo.getFunctionalOrgType(),
//				searchIssueVo.getSearchOrgCode(), searchIssueVo.getOrgCode());
		PageInfo<IssueViewObject> pageInfo = createPageInfoFromSolr(page, rows,
				sidx, sord, query);
		for (IssueViewObject issueViewObject : pageInfo.getResult()) {
			IssueEvaluate issueEvaluate = issueEvaluateService
					.getIssueEvaluateByIssueId(issueViewObject.getIssueId());
			if (issueEvaluate != null && issueEvaluate.getId() != null) {
				issueViewObject.setEvaluateType(issueEvaluate.getEvaluateType());
				issueViewObject.setStatus(IssueState.GRADEDELAY);
			}
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsGradeDelayFromSolr(
			Long keyId, Long issueType, SearchIssueVoNew searchIssueVo,
			Integer page, Integer rows, String sidx, String sord) {
		if (searchIssueVo == null || searchIssueVo.getTargeOrgId() == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		setOrgCode(searchIssueVo);
		setOrderField(searchIssueVo, sidx, sord);
		StringBuffer query = new StringBuffer();
		query.append("stateCode:1000");
		query.append(" AND endDate:["
				+ DateFormat.getSolrAddDateString(
						DateFormat.DEFAULT_DATE_FORMAT, -20) + " TO *]");
		createSolrQueryBySearchVo(searchIssueVo, query);
		createSolrQueryForCreateOrg(query, searchIssueVo.getOrgLevel(),
				searchIssueVo.getSearchOrgId(),
				searchIssueVo.getFunctionalOrgType(),
				searchIssueVo.getSearchOrgCode(), searchIssueVo.getOrgCode());
		PageInfo<IssueViewObject> pageInfo = createPageInfoFromSolr(page, rows,
				sidx, sord, query);
		for (IssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
			if (issueViewObject.getEvaluateType() != null) {
				issueViewObject.setStatus(IssueState.GRADEDELAY);
			}
		}
		return pageInfo;
	}
	private void createSolrQueryForCurrentOrg(StringBuffer query, Long orgLevel,
			Long searchOrgId,Long functionalorgType, String searchOrgCode, String targetOrgCode) {
		if (orgLevel != null) {
			query.append(" AND currentOrgLevel:" + orgLevel);
			if (searchOrgId == null) {
				query.append(" AND currentOrginternalCode:" + targetOrgCode
						+ "*");
			} else {
				query.append(" AND currentOrginternalCode:" + searchOrgCode
						+ "*");
			}
			if (functionalorgType == null) {
				query.append(" AND currentOrgFunctionalType:0");
			} else {
				query.append(" AND currentOrgFunctionalType:"
						+ functionalorgType);
			}
		} else {
			query.append(" AND currentOrginternalCode:" + targetOrgCode);
		}
	}
}

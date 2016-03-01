package com.tianque.issueLeaderViewCache.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.constants.IssueViewType;
import com.tianque.issue.controller.IssueOvertimeHelper;
import com.tianque.issue.domain.CompletedIssue;
import com.tianque.issue.domain.IssueEvaluate;
import com.tianque.issue.service.IssueEvaluateService;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.issueLeaderViewCache.constant.IssueAllViewType;
import com.tianque.issueLeaderViewCache.dao.IssueLeaderViewCacheDao;
import com.tianque.issueLeaderViewCache.domain.IssueCacheVO;
import com.tianque.issueLeaderViewCache.service.IssueLeaderViewCacheService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("issueLeaderViewCacheService")
public class IssueLeaderViewCacheServiceImpl implements
		IssueLeaderViewCacheService {
	private static String COUNT = "count";
	private static String defaulOrgCode = ".1.";
	private static Long defaultOrgId = 2L;

	@Autowired
	private IssueLeaderViewCacheDao issueLeaderViewCacheDao;

	private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting()
			.setVersion(2.2).create();

	@Autowired
	private IssueOvertimeHelper issueOvertimeHelper;
	@Autowired
	private IssueEvaluateService issueEvaluateService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public PageInfo<IssueViewObject> findPageInfoByCacheKey(String cacheKey,
			Integer page, Integer rows) {
		if (!StringUtil.isStringAvaliable(cacheKey)) {
			throw new BusinessValidationException("参数错误");
		}
		List<String> dataObjectList = issueLeaderViewCacheDao
				.findPageInfoByCacheKey(cacheKey, page, rows);
		List<IssueViewObject> result = new ArrayList<IssueViewObject>();
		for (String dataObject : dataObjectList) {
			result.add(gson.fromJson(dataObject, IssueViewObject.class));
		}
		return new PageInfo<IssueViewObject>(page, rows,
				issueLeaderViewCacheDao.getCountByCacheKey(cacheKey + "_"
						+ COUNT), result);
	}

	@Override
	public PageInfo<CompletedIssue> findPageInfoByCacheKeyForCompleted(
			String cacheKey, Integer page, Integer rows) {
		if (!StringUtil.isStringAvaliable(cacheKey)) {
			throw new BusinessValidationException("参数错误");
		}
		List<String> dataObjectList = issueLeaderViewCacheDao
				.findPageInfoByCacheKey(cacheKey, page, rows);
		List<CompletedIssue> result = new ArrayList<CompletedIssue>();
		for (String dataObject : dataObjectList) {
			result.add(gson.fromJson(dataObject, CompletedIssue.class));
		}
		return new PageInfo<CompletedIssue>(page, rows,
				issueLeaderViewCacheDao.getCountByCacheKey(cacheKey + "_"
						+ COUNT), result);
	}

	@Override
	public void handleIssueCache() {
		List<PropertyDict> orgLevels = null;
		while (true) {
			orgLevels = propertyDictService
					.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
			if (orgLevels != null && orgLevels.size() > 0) {
				break;
			}
		}
		for (PropertyDict dict : orgLevels) {
			if (dict.getInternalId() < OrganizationLevel.COUNTRY) {
				for (String viewType : IssueAllViewType.issueViewTypes) {
					String key = null;
					if (viewType.equals("historyDone")) {
						key = IssueViewType.DONE + "_" + defaultOrgId + "_"
								+ dict.getId() + "_history";
					} else {
						key = viewType + "_" + defaultOrgId + "_"
								+ dict.getId();
					}
					String countKey = key + "_" + COUNT;
					Integer count = countJurisdictionsIssue(dict.getId()
							.toString(), defaulOrgCode, viewType);
					if (viewType.equals(IssueViewType.COMPLETED)
							|| viewType.equals(IssueViewType.CHECKGRADE)) {
						List<CompletedIssue> completedIssues = findGradeDelayAndCompletedIssueByPage(
								dict.getId().toString(), defaulOrgCode,
								viewType);
						List<IssueCacheVO> IssueCacheVOs = createIssueCacheVO(
								key, completedIssues);
						updateIssueLeaderViewCache(key, countKey,
								IssueCacheVOs, count);
					} else {
						List<IssueViewObject> IssueViewObjects = findJurisdictionsIssue(
								dict.getId().toString(), defaulOrgCode,
								viewType);
						List<IssueCacheVO> IssueCacheVOs = createIssueCacheVO(
								key, IssueViewObjects);
						updateIssueLeaderViewCache(key, countKey,
								IssueCacheVOs, count);
					}

				}
			}
		}

	}

	private void updateIssueLeaderViewCache(String key, String countKey,
			List<IssueCacheVO> IssueCacheVOs, Integer count) {
		issueLeaderViewCacheDao.deleteByCacheKey(key);
		issueLeaderViewCacheDao.deleteByCacheKey(countKey);
		issueLeaderViewCacheDao.addIssueCacheForBatch(IssueCacheVOs);
		issueLeaderViewCacheDao.addIssueCache(new IssueCacheVO(countKey, count,
				null));
	}

	private List<IssueCacheVO> createIssueCacheVO(String key, List list) {
		List<IssueCacheVO> IssueCacheVOs = new ArrayList<IssueCacheVO>();
		for (Object obj : list) {
			IssueCacheVOs.add(new IssueCacheVO(key, null, gson.toJson(obj)));
		}
		return IssueCacheVOs;
	}

	@Override
	public Integer countJurisdictionsIssue(String orgLevel, String orgCode,
			String issueViewType) {

		return issueLeaderViewCacheDao.countJurisdictionsIssue(orgLevel,
				orgCode, issueViewType);
	}

	@Override
	public List<IssueViewObject> findJurisdictionsIssue(String orgLevel,
			String orgCode, String issueViewType) {
		List<IssueViewObject> issueViewObjects = issueLeaderViewCacheDao
				.findJurisdictionsIssue(orgLevel, orgCode, issueViewType);
		if (IssueViewType.PUBLICLTYCASSDONE.equals(issueViewType)) {
			for (IssueViewObject issueViewObject : issueViewObjects) {
				issueOvertimeHelper.fillEaringWarnField(issueViewObject);
				fillStatusForGradeDelayIssue(issueViewObject);
			}
		} else {
			for (IssueViewObject issueViewObject : issueViewObjects) {
				issueOvertimeHelper.fillEaringWarnField(issueViewObject);
			}

		}
		return issueViewObjects;
	}

	private void fillStatusForGradeDelayIssue(IssueViewObject issueViewObject) {
		IssueEvaluate evaluate = issueEvaluateService
				.getIssueEvaluateByIssueId(issueViewObject.getIssueId());
		if (evaluate != null) {
			issueViewObject.setStatus(IssueState.GRADEDELAY);
		}
	}

	@Override
	public List<CompletedIssue> findGradeDelayAndCompletedIssueByPage(
			String orgLevel, String orgCode, String issueViewType) {
		List<CompletedIssue> completedIssues = issueLeaderViewCacheDao
				.findGradeDelayAndCompletedIssueByPage(orgLevel, orgCode,
						issueViewType);
		List<CompletedIssue> list = new ArrayList<CompletedIssue>();
		for (CompletedIssue completedIssue : completedIssues) {
			IssueEvaluate issueEvaluate = issueEvaluateService
					.getIssueEvaluateByIssueId(completedIssue.getIssueId());
			if (issueEvaluate != null && issueEvaluate.getId() != null) {
				completedIssue.setEvaluateType(issueEvaluate.getEvaluateType());
				completedIssue.setStatus(IssueState.GRADEDELAY);
			}
			list.add(completedIssue);
		}
		return list;
	}

	@Override
	public Integer getCountByCacheKey(String cacheKey) {
		return issueLeaderViewCacheDao.getCountByCacheKey(cacheKey);
	}

}

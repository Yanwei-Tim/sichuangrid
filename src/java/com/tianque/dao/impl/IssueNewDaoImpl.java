package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.IssueNewDao;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.gis.util.GisCountTypeMapUtil;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.vo.IssueViewObject;

@Repository("issueNewDao")
@SuppressWarnings("unchecked")
public class IssueNewDaoImpl extends AbstractBaseDao implements IssueNewDao {

	@Override
	public IssueNew getSimpleIssueById(Long id) {
		IssueNew result = (IssueNew) getSqlMapClientTemplate().queryForObject(
				"issueNew.getSimpleIssueById", id);
		return result;
	}

	@Override
	public IssueStep getSimpleIssueByIssueId(Long issueId) {
		IssueStep result = (IssueStep) getSqlMapClientTemplate()
				.queryForObject("issueNew.getSimpleIssueByIssueId", issueId);
		return result;
	}

	@Override
	public IssueNew addIssue(IssueNew issueNew) {
		if (!validate(issueNew))
			throw new BusinessValidationException();
		Long id = (Long) getSqlMapClientTemplate().insert("issueNew.addIssue",
				issueNew);
		return getSimpleIssueById(id);
	}

	@Override
	public void addIssueHasTypes(Long issueId, Long issueTypeId,
			Long issueTypeDomainId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", issueId);
		map.put("issueTypeId", issueTypeId);
		map.put("issueTypeDomainId", issueTypeDomainId);
		getSqlMapClientTemplate().insert("issueNew.addIssueHasTypes", map);
	}

	@Override
	public void deleteIssueById(Long id) {
		getSqlMapClientTemplate().delete("issueNew.deleteIssueById", id);
	}

	@Override
	public void deleteIssueHasTypesByIssueId(Long issueId) {
		getSqlMapClientTemplate().delete(
				"issueNew.deleteIssueHasTypesByIssueId", issueId);
	}

	@Override
	public IssueNew updateIssue(IssueNew issueNew) {
		if (!validate(issueNew))
			throw new BusinessValidationException();
		getSqlMapClientTemplate().update("issueNew.updateIssue", issueNew);
		return getSimpleIssueById(issueNew.getId());
	}

	@Override
	public void deleteIssueHasTypesByIssueIdAndIssueTypeId(Long issueId,
			Long issueTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", issueId);
		map.put("issueTypeId", issueTypeId);
		getSqlMapClientTemplate().delete(
				"issueNew.deleteIssueHasTypesByIssueIdAndIssueTypeId", map);
	}

	@Override
	public IssueNew getFullIssueById(Long id) {
		IssueNew result = (IssueNew) getSqlMapClientTemplate().queryForObject(
				"issueNew.getFullIssueById", id);
		return result;
	}

	@Override
	public IssueNew getFullIssueByIssueStepId(Long id) {
		IssueNew result = (IssueNew) getSqlMapClientTemplate().queryForObject(
				"issueNew.getFullIssueByIssueStepId", id);
		return result;
	}

	@Override
	public PageInfo<IssueViewObject> findMyNeedDoForPageByTargeOrgIdAndDealState(
			Long targeOrgId, Long dealState, Integer page, Integer rows,
			String sidx, String sord) {

		Integer countNum = (Integer) getNeedDoCount(targeOrgId);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targeOrgId", targeOrgId);
		map.put("dealState", IssueState.STEPCOMPLETE_CODE);
		map.put("sortField", sidx);
		map.put("order", sord);
		List<IssueViewObject> list = getSqlMapClientTemplate().queryForList(
				"searchIssueNew.findMyNeedDoIssues", map, (page - 1) * rows,
				rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> findMyDoneForPageByTargeOrgIdAndDealState(
			Long targeOrgId, List<Long> dealStateList, Integer page,
			Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targeOrgId", targeOrgId);
		map.put("dealState", IssueState.STEPCOMPLETE_CODE);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countMyDoneIssues", map);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;

		List<IssueViewObject> list = getSqlMapClientTemplate()
				.queryForList("searchIssueNew.findMyDoneIssues", map,
						(page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> findMyJurisdictionsDoneForPageByTargeOrgInternalCodeAndDealState(
			Long targeOrgId, String targeOrgInternalCode, Long dealState,
			Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("targeOrgId", targeOrgId);
		map.put("targeOrgInternalCode", targeOrgInternalCode);
		map.put("dealState", IssueState.ISSUECOMPLETE_CODE);
		map.put("sortField", sidx);
		map.put("order", sord);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countMyJurisdictionsDoneIssues", map);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<IssueViewObject> list = getSqlMapClientTemplate().queryForList(
				"searchIssueNew.findMyJurisdictionsDoneIssues", map,
				(page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> findMyJurisdictionsNeedDoForPageByTargeOrgInternalCodeAndDealState(
			Long targeOrgId, String targeOrgInternalCode, Long dealState,
			Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("targeOrgId", targeOrgId);
		map.put("targeOrgInternalCode", targeOrgInternalCode);
		map.put("dealState", IssueState.STEPCOMPLETE_CODE);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("orgInternalCode", targeOrgInternalCode);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countMyJurisdictionsNeedDoIssues", map);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<IssueViewObject> list = getSqlMapClientTemplate().queryForList(
				"searchIssueNew.findMyJurisdictionsNeedDoIssues", map,
				(page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	private boolean validate(IssueNew issueNew) {
		if (issueNew == null)
			return false;
		if (issueNew.getSerialNumber() == null)
			return false;
		if (issueNew.getOccurOrg() == null
				|| issueNew.getOccurOrg().getId() == null)
			return false;
		if (issueNew.getOccurOrgInternalCode() == null
				|| issueNew.getOccurOrgInternalCode().equals(""))
			return false;
		if (issueNew.getSubject() == null
				|| "".equals(issueNew.getSubject().trim()))
			return false;
		if (issueNew.getIssueKind() == null
				|| issueNew.getIssueKind().getId() == null)
			return false;
		if (issueNew.getOccurDate() == null)
			return false;
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateIssueWhenDeal(IssueNew issue) {
		if (null == issue || null == issue.getId()
				|| 0 == issue.getId().intValue()) {
			throw new BusinessValidationException("参数不正确");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", issue.getId());
		map.put("currentOrgDisplayName", issue.getCurrentOrgDisplayName());
		map.put("lastOrg", issue.getLastOrg());
		map.put("lastOrgInternalCode", issue.getLastOrgInternalCode());
		map.put("lastUsername", issue.getLastUsername());
		map.put("status", issue.getStatus());

		getSqlMapClientTemplate().update("issueNew.updateIssueWhenDeal", map);
	}

	@Override
	public IssueViewObject getViewIssueViewObjectById(Long issueLogId) {
		return (IssueViewObject) getSqlMapClientTemplate().queryForObject(
				"issueNew.getIssueViewObjectById", issueLogId);
	}

	@Override
	public IssueViewObject getViewIssueViewObjectByIssueId(Long id) {
		return (IssueViewObject) getSqlMapClientTemplate().queryForObject(
				"issueNew.getIssueViewObjectByIssueId", id);
	}

	@Override
	public PageInfo<IssueViewObject> findSuperviseIssueList(String orgCode,
			Long dealState1, Long dealState2, Integer page, Integer rows,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		map.put("dealstate1", dealState1);
		map.put("dealState2", dealState2);
		map.put("sortField", sidx);
		map.put("order", sord);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"issueNew.countSperiviseIssues", map);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<IssueViewObject> list = getSqlMapClientTemplate().queryForList(
				"issueNew.findSperviseIssues", map, (page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public Integer getMyNeedDoForByTargeOrgIdAndDealState(Long targeOrgId,
			Long dealState) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("targeOrgId", targeOrgId);
		map.put("dealState", dealState);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"issueNew.countMyNeedDoIssues", map);
	}

	@Override
	public Integer countIssueByIssueTypeId(Long id) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"issueNew.countIssueByIssueTypeId", id);
	}

	@Override
	public void updateIssueCurrentStepAndOrg(IssueNew saveIssueNew) {
		getSqlMapClientTemplate().update(
				"issueNew.updateIssueCurrentStepAndOrg", saveIssueNew);
	}

	@Override
	public Integer getNeedDoCount(Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targeOrgId", orgId);
		map.put("dealState", IssueState.STEPCOMPLETE_CODE);

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countMyNeedDoIssues", map);
	}

	public Integer getCallCenterCount(Long orgId, List<PropertyDict> sourcekind) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targeOrgId", orgId);
		map.put("dealState", IssueState.STEPCOMPLETE_CODE);
		map.put("sourcekindList", sourcekind);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countCallCenterIssues", map);
	}

	@Override
	public void updateIssueUrgentState(Long id, boolean urgented) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("urgented", urgented);
		getSqlMapClientTemplate()
				.update("issueNew.updateIssueUrgentState", map);
	}

	@Override
	public IssueNew getFullIssueMobileByIssueStepId(Long id) {
		IssueNew result = (IssueNew) getSqlMapClientTemplate().queryForObject(
				"issueNew.getFullIssueMobileByIssueStepId", id);
		return result;
	}

	// 按照事件种类
	private List<IssueNew> countIssueTypeNewsByorgCode(String orgInternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("typeIssue", giscountType());
		return getSqlMapClientTemplate().queryForList(
				"issueNew.countDomainnameIssueNewsByorgCode", map);
	}

	// 按照是否完成
	private List<IssueNew> countIssueStateNewsByorgCode(String orgInternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("occurorginternalcode", orgInternalCode);
		map.put("giscountState", IssueState.ISSUECOMPLETE_CODE);

		map.put("dealing", GisCountTypeMapUtil.DEALING);
		map.put("issueComplete", GisCountTypeMapUtil.ISSUECOMPLETE);

		return getSqlMapClientTemplate().queryForList(
				"issueNew.countStatusIssueNewsByorgCode", map);
	}

	// 按照重大紧急分类
	private List<IssueNew> countIssueIsUrentTypeByorgCode(String orgInternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("occurorginternalcode", orgInternalCode);
		map.put("important", GisCountTypeMapUtil.IMPORTANT);
		map.put("isEmergency", GisCountTypeMapUtil.ISEMERGENCY);
		return getSqlMapClientTemplate().queryForList(
				"issueNew.countIssueIsUrentTypeByorgCode", map);
	}

	@Override
	public List<IssueNew> countActualHouseByOrgInternalCode(
			String orgInternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("occurorginternalcode", orgInternalCode);
		map.put("totalNum", GisCountTypeMapUtil.TOTALNUM);
		List<IssueNew> result = getSqlMapClientTemplate().queryForList(
				"issueNew.countActualHouseByOrgInternalCode", map);
		result.addAll(countIssueTypeNewsByorgCode(orgInternalCode));
		result.addAll(countIssueStateNewsByorgCode(orgInternalCode));
		result.addAll(countIssueIsUrentTypeByorgCode(orgInternalCode));
		return result;
	}

	private List<String> giscountType() {
		List<String> list = new ArrayList<String>();
		list.add(IssueTypes.CONTRADICTION);
		list.add(IssueTypes.SECURITYTROUBLE);
		list.add(IssueTypes.PEOPLELIVE_SERVICE);
		return list;
	}

	@Override
	public PageInfo<IssueNew> issueNewsListSearchByQueryStrAndOrgId(
			String orgInternalCode, String queryStr, Integer page,
			Integer rows, String sidx, String sord) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("occurorginternalcode", orgInternalCode);
		map.put("queryStr", queryStr);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"issueNew.countIssueNewsListSearchByQueryStrAndOrgId", map);
		List<IssueNew> list = getSqlMapClientTemplate().queryForList(
				"issueNew.issueNewsListSearchByQueryStrAndOrgId", map,
				(page - 1) * rows, rows);
		return createPageInfo(page, rows, countNum, list);
	}

	public PageInfo<IssueNew> createPageInfo(int PageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<IssueNew> pageInfo = new PageInfo<IssueNew>();
		pageInfo.setCurrentPage(PageNum);
		pageInfo.setTotalRowSize(pageSize);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setResult(list);
		return pageInfo;
	}

	@Override
	public IssueNew updateIssueNewsInfoForGis(IssueNew issueNew) {
		getSqlMapClientTemplate().update("issueNew.updateIssueNewsInfoForGis",
				issueNew);
		return getSimpleIssueById(issueNew.getId());
	}

	@Override
	public IssueNew unBindIssueNewsOnMap(Long issueId) {
		getSqlMapClientTemplate().update("issueNew.unBindIssueNewsOnMap",
				issueId);
		return getSimpleIssueById(issueId);
	}

	@Override
	public List<IssueNew> getIssueNewsDatialInfoByIssueId(Long orgId,
			Long issueId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", issueId);
		map.put("occurOrgId", orgId);
		return getSqlMapClientTemplate().queryForList(
				"issueNew.getIssueNewsDatialInfoByIssueId", map);
	}

	@Override
	public PageInfo<IssueNew> searchTypeKeyIssueNewsListByOrgCode(
			String orgInternalCode, Object issueNewsfield,
			String issueNewsType, Integer page, Integer rows, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("occurorginternalcode", orgInternalCode);
		map.put("issueNewsfield", issueNewsfield);
		map.put("issueNewsType",
				exchengeIssueNewsTypeToTypeDomainName(issueNewsType));
		// map.put("important", GisCountTypeMapUtil.IMPORTANT_ISSUES);
		// map.put("isemergency", GisCountTypeMapUtil.ISEMERGENCY_ISSUES);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"issueNew.countTypeKeyIssueNewsListByOrgCode", map);
		List<IssueNew> list = getSqlMapClientTemplate().queryForList(
				"issueNew.searchTypeKeyIssueNewsListByOrgCode", map,
				(page - 1) * rows, rows);
		return createPageInfo(page, rows, countNum, list);
	}

	/* 已完成，未完成 */
	@Override
	public PageInfo<IssueNew> searchStateKeyIssueNewsListByOrgId(
			String orgInternalCode, Object issueNewsfield,
			String issueNewsType, Integer page, Integer rows, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("occurorginternalcode", orgInternalCode);
		map.put("issueNewsfield", issueNewsfield);
		map.put("issueNewsType", issueNewsType);
		map.put("dealState", IssueState.STEPCOMPLETE_CODE);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"issueNew.countStateKeyIssueNewsListByOrgId", map);
		List<IssueNew> list = getSqlMapClientTemplate().queryForList(
				"issueNew.searchStateKeyIssueNewsListByOrgId", map,
				(page - 1) * rows, rows);
		return createPageInfo(page, rows, countNum, list);
	}

	// 按照已完成未完成过滤
	@Override
	public PageInfo<IssueNew> gisIssueNewsFutureSearchByOrgCode(
			String orgInternalCode, Object issueNewsfield,
			String issueNewsType, String issueNewsState, Integer page,
			Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("occurorginternalcode", orgInternalCode);
		map.put("issueNewsfield", issueNewsfield);

		map.put(exchengeIssueNewsTypeToTypeDomainName(issueNewsType),
				exchengeIssueNewsTypeToTypeDomainName(issueNewsType));
		map.put("issueNewsState", issueNewsState);
		map.put("issueNewsStatefield",
				GisCountTypeMapUtil.getGisIssueNewFilds(issueNewsState));
		map.put("dealState", IssueState.STEPCOMPLETE_CODE);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (issueNewsState.equals(GisCountTypeMapUtil.UNCOMPLETEISSUE)) {
			Integer countNum = (Integer) getSqlMapClientTemplate()
					.queryForObject(
							"issueNew.countGisUncompleteIssueNewsFutureSearchByOrgCode",
							map);
			List<IssueNew> list = getSqlMapClientTemplate().queryForList(
					"issueNew.gisUncompleteIssueNewsFutureSearchByOrgCode",
					map, (page - 1) * rows, rows);
			return createPageInfo(page, rows, countNum, list);
		} else {
			Integer countNum = (Integer) getSqlMapClientTemplate()
					.queryForObject(
							"issueNew.countGisCompleteIssueNewsFutureSearchByOrgCode",
							map);
			List<IssueNew> list = getSqlMapClientTemplate().queryForList(
					"issueNew.gisCompleteIssueNewsFutureSearchByOrgCode", map,
					(page - 1) * rows, rows);
			return createPageInfo(page, rows, countNum, list);
		}
	}

	private String exchengeIssueNewsTypeToTypeDomainName(String issueNewsType) {
		if (issueNewsType.equals(GisCountTypeMapUtil.PEOPLELIVEISSUE)
				|| issueNewsType.equals(GisCountTypeMapUtil.CONTRADICTIONISSUE)
				|| issueNewsType.equals(GisCountTypeMapUtil.SECURITYISSUE)) {
			return GisCountTypeMapUtil.TYPEDOMAINNAME;
		} else {
			return issueNewsType;
		}
	}

	@Override
	public List<IssueNew> findBindingIssueNewsByOrgCodeAndType(
			String orgInternalCode, String issueNewsType,
			Object issueNewsfield, Integer dealState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("issueNewsType", issueNewsType);
		map.put("issueNewsfield", issueNewsfield);
		map.put("dealState", dealState);
		return getSqlMapClientTemplate().queryForList(
				"issueNew.findBindingIssueNewsByOrgCodeAndType", map);
	}

	@Override
	public List<IssueNew> findAllBindingIssueNewsByOrgInternalCode(
			String orgInternalCode) {
		return getSqlMapClientTemplate().queryForList(
				"issueNew.findAllBindingIssueNewsByOrgInternalCode",
				orgInternalCode);
	}

	@Override
	public IssueNew getIssueBySerialNumber(String serialNumber) {
		if (null == serialNumber || "".equals(serialNumber.trim())) {
			return null;
		}
		return (IssueNew) getSqlMapClientTemplate().queryForObject(
				"issueNew.getIssueBySerialNumber", serialNumber);
	}

	@Override
	public IssueNew getIssueByFromSerialNumber(String fromSerialNumber) {
		if (null == fromSerialNumber || "".equals(fromSerialNumber.trim())) {
			return null;
		}
		return (IssueNew) getSqlMapClientTemplate().queryForObject(
				"issueNew.getIssueByFromSerialNumber", fromSerialNumber);
	}

}

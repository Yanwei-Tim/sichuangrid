package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.vo.IssueViewObject;

/**
 * 服务办事
 */
public interface IssueNewDao {

	public IssueNew getSimpleIssueById(Long id);

	public IssueStep getSimpleIssueByIssueId(Long issueId);

	public IssueNew addIssue(IssueNew issueNew);

	public void addIssueHasTypes(Long issueId, Long issueTypeId,
			Long issueTypeDomainId);

	public void deleteIssueById(Long id);

	public void deleteIssueHasTypesByIssueId(Long issueId);

	public IssueNew updateIssue(IssueNew issueNew);

	public void deleteIssueHasTypesByIssueIdAndIssueTypeId(Long issueId,
			Long issueTypeId);

	public IssueNew getFullIssueById(Long id);

	public PageInfo<IssueViewObject> findMyNeedDoForPageByTargeOrgIdAndDealState(
			Long targeOrgId, Long dealState, Integer page, Integer rows,
			String sidx, String sord);

	public PageInfo<IssueViewObject> findSuperviseIssueList(String orgCode,
			Long dealState1, Long dealState2, Integer page, Integer rows,
			String sidx, String sord);

	public PageInfo<IssueViewObject> findMyJurisdictionsNeedDoForPageByTargeOrgInternalCodeAndDealState(
			Long targeOrgId, String targeOrgInternalCode, Long dealState,
			Integer page, Integer rows, String sidx, String sord);

	public PageInfo<IssueViewObject> findMyDoneForPageByTargeOrgIdAndDealState(
			Long targeOrgId, List<Long> dealStateList, Integer page,
			Integer rows, String sidx, String sord);

	public PageInfo<IssueViewObject> findMyJurisdictionsDoneForPageByTargeOrgInternalCodeAndDealState(
			Long targeOrgId, String targeOrgInternalCode, Long dealState,
			Integer page, Integer rows, String sidx, String sord);

	public void updateIssueWhenDeal(IssueNew issue);

	public IssueViewObject getViewIssueViewObjectById(Long issueLogId);

	public Integer getMyNeedDoForByTargeOrgIdAndDealState(Long targeOrgId,
			Long dealState);

	public Integer countIssueByIssueTypeId(Long id);

	public void updateIssueCurrentStepAndOrg(IssueNew saveIssueNew);

	public IssueNew getFullIssueByIssueStepId(Long id);

	public Integer getNeedDoCount(Long orgId);

	public void updateIssueUrgentState(Long id, boolean urgented);

	IssueViewObject getViewIssueViewObjectByIssueId(Long id);

	public IssueNew getFullIssueMobileByIssueStepId(Long id);

	public List<IssueNew> countActualHouseByOrgInternalCode(
			String orgInternalCode);

	public PageInfo<IssueNew> issueNewsListSearchByQueryStrAndOrgId(
			String orgInternalCode, String queryStr, Integer page,
			Integer rows, String sidx, String sord);

	public IssueNew updateIssueNewsInfoForGis(IssueNew issueNew);

	public IssueNew unBindIssueNewsOnMap(Long issueId);

	public List<IssueNew> getIssueNewsDatialInfoByIssueId(Long orgId,
			Long issueId);

	/**
	 * 查询gis 事件列表中的全部，重大，紧急，以及事件类型。
	 * 
	 * @param orgInternalCode
	 *            组织机构
	 * @param issueNewsfield
	 *            事件类型字段
	 * @param issueNewsType
	 *            类型
	 * @return
	 */
	public PageInfo<IssueNew> searchTypeKeyIssueNewsListByOrgCode(
			String orgInternalCode, Object issueNewsfield,
			String issueNewsType, Integer page, Integer rows, String sidx,
			String sord);

	/**
	 * 查询gis 时间列表中的已完成，未完成事件
	 * 
	 * @param orgInternalCode
	 *            组织机构
	 * @param issueNewsfield
	 *            事件类型字段
	 * @param issueNewsType
	 *            类型
	 * @return
	 */
	public PageInfo<IssueNew> searchStateKeyIssueNewsListByOrgId(
			String orgInternalCode, Object issueNewsfield,
			String issueNewsType, Integer page, Integer rows, String sidx,
			String sord);

	public PageInfo<IssueNew> gisIssueNewsFutureSearchByOrgCode(
			String orgInternalCode, Object issueNewsfield,
			String issueNewsType, String issueNewsState, Integer page,
			Integer rows, String sidx, String sord);

	public List<IssueNew> findBindingIssueNewsByOrgCodeAndType(
			final String orgInternalCode, final String issueNewsType,
			final Object issueNewsfield, final Integer dealState);

	public List<IssueNew> findAllBindingIssueNewsByOrgInternalCode(
			final String orgInternalCode);

	public IssueNew getIssueBySerialNumber(String serialNumber);

	public IssueNew getIssueByFromSerialNumber(String fromSerialNumber);
}
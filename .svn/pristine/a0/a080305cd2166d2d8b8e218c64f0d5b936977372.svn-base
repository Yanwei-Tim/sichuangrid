package com.tianque.fourTeams.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;

/**
 * 服务办事
 */
public interface FourTeamsIssueNewDao {

	public FourTeamsIssueNew getSimpleIssueById(Long id);

	public FourTeamsIssueStep getSimpleIssueByIssueId(Long issueId);

	public FourTeamsIssueNew addIssue(FourTeamsIssueNew issueNew);

	public void addIssueHasTypes(Long issueId, Long issueTypeId,
			Long issueTypeDomainId);

	public void deleteIssueById(Long id);

	public void deleteIssueHasTypesByIssueId(Long issueId);

	public FourTeamsIssueNew updateIssue(FourTeamsIssueNew issueNew);

	public void deleteIssueHasTypesByIssueIdAndIssueTypeId(Long issueId,
			Long issueTypeId);

	public FourTeamsIssueNew getFullIssueById(Long id);

	public PageInfo<FourTeamsIssueViewObject> findMyNeedDoForPageByTargeOrgIdAndDealState(
			Long targeOrgId, Long dealState, Integer page, Integer rows,
			String sidx, String sord);

	public PageInfo<FourTeamsIssueViewObject> findSuperviseIssueList(String orgCode,
			Long dealState1, Long dealState2, Integer page, Integer rows,
			String sidx, String sord);

	public PageInfo<FourTeamsIssueViewObject> findMyJurisdictionsNeedDoForPageByTargeOrgInternalCodeAndDealState(
			Long targeOrgId, String targeOrgInternalCode, Long dealState,
			Integer page, Integer rows, String sidx, String sord);

	public PageInfo<FourTeamsIssueViewObject> findMyDoneForPageByTargeOrgIdAndDealState(
			Long targeOrgId, List<Long> dealStateList, Integer page,
			Integer rows, String sidx, String sord);

	public PageInfo<FourTeamsIssueViewObject> findMyJurisdictionsDoneForPageByTargeOrgInternalCodeAndDealState(
			Long targeOrgId, String targeOrgInternalCode, Long dealState,
			Integer page, Integer rows, String sidx, String sord);

	public void updateIssueWhenDeal(FourTeamsIssueNew issue);

	public FourTeamsIssueViewObject getViewIssueViewObjectById(Long issueLogId);

	public Integer getMyNeedDoForByTargeOrgIdAndDealState(Long targeOrgId,
			Long dealState);

	public Integer countIssueByIssueTypeId(Long id);

	public void updateIssueCurrentStepAndOrg(FourTeamsIssueNew saveIssueNew);

	public FourTeamsIssueNew getFullIssueByIssueStepId(Long id);

	public Integer getNeedDoCount(Long orgId);

	public void updateIssueUrgentState(Long id, boolean urgented);

	FourTeamsIssueViewObject getViewIssueViewObjectByIssueId(Long id);

	public FourTeamsIssueNew getFullIssueMobileByIssueStepId(Long id);

	public List<FourTeamsIssueNew> countActualHouseByOrgInternalCode(
			String orgInternalCode);

	public PageInfo<FourTeamsIssueNew> issueNewsListSearchByQueryStrAndOrgId(
			String orgInternalCode, String queryStr, Integer page,
			Integer rows, String sidx, String sord);

	public FourTeamsIssueNew updateIssueNewsInfoForGis(FourTeamsIssueNew issueNew);

	public FourTeamsIssueNew unBindIssueNewsOnMap(Long issueId);

	public List<FourTeamsIssueNew> getIssueNewsDatialInfoByIssueId(Long orgId,
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
	public PageInfo<FourTeamsIssueNew> searchTypeKeyIssueNewsListByOrgCode(
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
	public PageInfo<FourTeamsIssueNew> searchStateKeyIssueNewsListByOrgId(
			String orgInternalCode, Object issueNewsfield,
			String issueNewsType, Integer page, Integer rows, String sidx,
			String sord);

	public PageInfo<FourTeamsIssueNew> gisIssueNewsFutureSearchByOrgCode(
			String orgInternalCode, Object issueNewsfield,
			String issueNewsType, String issueNewsState, Integer page,
			Integer rows, String sidx, String sord);

	public List<FourTeamsIssueNew> findBindingIssueNewsByOrgCodeAndType(
			final String orgInternalCode, final String issueNewsType,
			final Object issueNewsfield, final Integer dealState);

	public List<FourTeamsIssueNew> findAllBindingIssueNewsByOrgInternalCode(
			final String orgInternalCode);
}
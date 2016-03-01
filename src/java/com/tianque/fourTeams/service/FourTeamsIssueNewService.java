package com.tianque.fourTeams.service;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.EmphasesVo;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;

/**
 * 服务办事
 * 
 * @author Death_Soul
 */
public interface FourTeamsIssueNewService {

	public FourTeamsIssueNew getSimpleIssueById(Long id);

	public FourTeamsIssueStep getSimpleIssueByIssueId(Long issueId);

	/**
	 * @param issueMode
	 *            北京羊坊店事件处理时用到，为备案，上报或者处理中。 其他项目不会用到，默认值为null
	 */
	public FourTeamsIssueViewObject addIssue(FourTeamsIssueNew issueNew, String[] files,
			Map<String, String> map, String issueMode);

	public void deleteIssueById(Long id);

	public FourTeamsIssueViewObject updateIssue(FourTeamsIssueNew issueNew, Long issueLogId,
			Map<String, String> map, String[] attachFiles);

	public FourTeamsIssueNew getFullIssueById(Long id);

	public FourTeamsIssueViewObject getIssueViewObjectById(Long id);

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

	public Map<String, List<EmphasesVo>> findRelatePerson(Long issueId);

	public Map<String, List<EmphasesVo>> findRelatePlace(Long issueId);

	public List<FourTeamsIssueAttachFile> findIssueAttachFilesById(Long issueId);

	public Integer getMyNeedDoForByTargeOrgIdAndDealState(Long targeOrgId,
			Long dealState);

	public FourTeamsIssueNew getFullIssueByIssueStepId(Long id);

	public List<FourTeamsIssueOperate> getCurrentLoginedOrgCanDo(FourTeamsIssueNew issue,
			Organization org);

	public FourTeamsIssueStep getIssueStepById(Long stepId);

	public Integer getNeedDoCount(Long orgId);

	public FourTeamsIssueViewObject reportToCommandCenter(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log);

	public FourTeamsIssueViewObject giveTo(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueLogNew issueLog, List<Long> tells, String[] attachFiles);

	public FourTeamsIssueViewObject concept(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueLogNew issueLog);

	public FourTeamsIssueViewObject comment(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueLogNew issueLog, String[] attachFiles);

	public FourTeamsIssueViewObject complete(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueLogNew issueLog, String[] attachFiles);

	public FourTeamsIssueViewObject backUps(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueLogNew issueLog);

	public FourTeamsIssueViewObject assign(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueLogNew issueLog, List<Long> tellOrgs, String[] attachFiles);

	public FourTeamsIssueViewObject submit(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueLogNew issueLog, List<Long> tellOrgs, String[] attachFiles);

	public FourTeamsIssueViewObject back(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueLogNew issueLog, String[] attachFiles);

	public FourTeamsIssueViewObject read(FourTeamsIssueNew issue, FourTeamsIssueStep step, FourTeamsIssueLogNew log);

	public FourTeamsIssueViewObject normalSupervise(FourTeamsIssueStep step, FourTeamsIssueLogNew issueLog);

	public FourTeamsIssueViewObject yellowSupervise(FourTeamsIssueStep step, FourTeamsIssueLogNew issueLog);

	public FourTeamsIssueViewObject redSupervise(FourTeamsIssueStep step, FourTeamsIssueLogNew issueLog);

	public FourTeamsIssueViewObject cancelSupervise(FourTeamsIssueStep step);

	public FourTeamsIssueViewObject instruct(FourTeamsIssueStep step, FourTeamsIssueLogNew issueLog);

	public FourTeamsIssueViewObject cancelUrgent(FourTeamsIssueNew issue);

	public FourTeamsIssueViewObject urgent(FourTeamsIssueNew issue, FourTeamsIssueLogNew log);

	public FourTeamsIssueViewObject historical(FourTeamsIssueNew issue);

	public FourTeamsIssueViewObject cancelHistorical(FourTeamsIssueNew issue);

	public FourTeamsIssueViewObject publiclty(FourTeamsIssueNew issue);

	public FourTeamsIssueViewObject cancelPubliclty(FourTeamsIssueNew issue);

	public FourTeamsIssueViewObject feedBack(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueLogNew log, List<Long> tellOrgs, String[] attachFiles);

	public FourTeamsIssueNew getFullIssueMobileByIssueId(Long id);

	public List<FourTeamsIssueNew> countActualHouseByOrgId(Long orgId);

	public PageInfo<FourTeamsIssueNew> issueNewsListSearchByQueryStrAndOrgId(Long orgId,
			String queryStr, Integer page, Integer rows, String sidx,
			String sord);

	/**
	 * GIS事件处理列表
	 * 
	 * @param orgInternalCode
	 *            组织机构代码
	 * @param issueNewsfield
	 *            查询的字段
	 * @param issueNewsType
	 *            事件类型
	 * @return
	 */
	public PageInfo<FourTeamsIssueNew> searchKeyIssueNewsListByOrgCode(
			String orgInternalCode, Object issueNewsfield,
			String issueNewsType, Integer page, Integer rows, String sidx,
			String sord);

	public List<FourTeamsIssueNew> getIssueNewsDatialInfoByIssueId(Long orgId,
			Long unitId);

	public FourTeamsIssueNew updateIssueNewsInfoForGis(Long issueId, GisInfo gisInfo);

	public FourTeamsIssueNew unBindIssueNewsOnMap(Long issueId);

	/**
	 * GIS事件处理二次过滤
	 * 
	 * @param orgInternalCode
	 *            组织机构代码
	 * @param issueNewsfield
	 *            查询的字段
	 * @param issueNewsType
	 *            事件类型
	 * @param issueNewsState
	 *            事件状态
	 * @return
	 */
	public PageInfo<FourTeamsIssueNew> gisIssueNewsFutureSearchByOrgCode(
			String orgInternalCode, Object issueNewsfield,
			String issueNewsType, String issueNewsState, Integer page,
			Integer rows, String sidx, String sord);

	public List<FourTeamsIssueNew> findBindingIssueNewsByOrgIdAndType(
			final String orgInternalCode, final String issueNewsType,
			final Object issueNewsfield);

	public List<FourTeamsIssueNew> findAllBindingIssueNewsByOrgInternalCode(
			final String orgInternalCode);
}

package com.tianque.service;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.EmphasesVo;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.vo.IssueViewObject;

/**
 * 服务办事
 * 
 * @author Death_Soul
 */
public interface IssueNewService {

	public IssueNew getSimpleIssueById(Long id);

	public IssueStep getSimpleIssueByIssueId(Long issueId);

	/**
	 * @param issueMode
	 *            北京羊坊店事件处理时用到，为备案，上报或者处理中。 其他项目不会用到，默认值为null
	 */
	public IssueViewObject addIssue(IssueNew issueNew, String[] files,
			Map<String, String> map, String issueMode);

	public void deleteIssueById(Long id);

	public IssueViewObject updateIssue(IssueNew issueNew, Long issueLogId,
			Map<String, String> map, String[] attachFiles);

	public IssueNew getFullIssueById(Long id);

	public IssueViewObject getIssueViewObjectById(Long id);

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

	public Map<String, List<EmphasesVo>> findRelatePerson(Long issueId);

	public Map<String, List<EmphasesVo>> findRelatePlace(Long issueId);

	public List<IssueAttachFile> findIssueAttachFilesById(Long issueId);

	public Integer getMyNeedDoForByTargeOrgIdAndDealState(Long targeOrgId,
			Long dealState);

	public IssueNew getFullIssueByIssueStepId(Long id);

	public List<IssueOperate> getCurrentLoginedOrgCanDo(IssueNew issue,
			Organization org);

	public IssueStep getIssueStepById(Long stepId);

	public Integer getNeedDoCount(Long orgId);

	public IssueViewObject reportToCommandCenter(IssueNew issue,
			IssueStep step, IssueLogNew log);

	public IssueViewObject giveTo(IssueNew issue, IssueStep step,
			IssueLogNew issueLog, List<Long> tells, String[] attachFiles);

	public IssueViewObject concept(IssueNew issue, IssueStep step,
			IssueLogNew issueLog);

	public IssueViewObject comment(IssueNew issue, IssueStep step,
			IssueLogNew issueLog, String[] attachFiles);

	public IssueViewObject complete(IssueNew issue, IssueStep step,
			IssueLogNew issueLog, String[] attachFiles);

	public IssueViewObject backUps(IssueNew issue, IssueStep step,
			IssueLogNew issueLog);

	public IssueViewObject assign(IssueNew issue, IssueStep step,
			IssueLogNew issueLog, List<Long> tellOrgs, String[] attachFiles);

	public IssueViewObject submit(IssueNew issue, IssueStep step,
			IssueLogNew issueLog, List<Long> tellOrgs, String[] attachFiles);

	public IssueViewObject back(IssueNew issue, IssueStep step,
			IssueLogNew issueLog, String[] attachFiles);

	public IssueViewObject read(IssueNew issue, IssueStep step, IssueLogNew log);

	public IssueViewObject normalSupervise(IssueStep step, IssueLogNew issueLog);

	public IssueViewObject yellowSupervise(IssueStep step, IssueLogNew issueLog);

	public IssueViewObject redSupervise(IssueStep step, IssueLogNew issueLog);

	public IssueViewObject cancelSupervise(IssueStep step);

	public IssueViewObject instruct(IssueStep step, IssueLogNew issueLog);

	public IssueViewObject cancelUrgent(IssueNew issue);

	public IssueViewObject urgent(IssueNew issue, IssueLogNew log);

	public IssueViewObject historical(IssueNew issue);

	public IssueViewObject cancelHistorical(IssueNew issue);

	public IssueViewObject publiclty(IssueNew issue);

	public IssueViewObject cancelPubliclty(IssueNew issue);

	public IssueViewObject feedBack(IssueNew issue, IssueStep step,
			IssueLogNew log, List<Long> tellOrgs, String[] attachFiles);

	public IssueNew getFullIssueMobileByIssueId(Long id);

	public List<IssueNew> countActualHouseByOrgId(Long orgId);

	public PageInfo<IssueNew> issueNewsListSearchByQueryStrAndOrgId(Long orgId,
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
	public PageInfo<IssueNew> searchKeyIssueNewsListByOrgCode(
			String orgInternalCode, Object issueNewsfield,
			String issueNewsType, Integer page, Integer rows, String sidx,
			String sord);

	public List<IssueNew> getIssueNewsDatialInfoByIssueId(Long orgId,
			Long unitId);

	public IssueNew updateIssueNewsInfoForGis(Long issueId, GisInfo gisInfo);

	public IssueNew unBindIssueNewsOnMap(Long issueId);

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
	public PageInfo<IssueNew> gisIssueNewsFutureSearchByOrgCode(
			String orgInternalCode, Object issueNewsfield,
			String issueNewsType, String issueNewsState, Integer page,
			Integer rows, String sidx, String sord);

	public List<IssueNew> findBindingIssueNewsByOrgIdAndType(
			final String orgInternalCode, final String issueNewsType,
			final Object issueNewsfield);

	public List<IssueNew> findAllBindingIssueNewsByOrgInternalCode(
			final String orgInternalCode);

	public IssueNew getIssueBySerialNumber(String serialNumber);

	public IssueNew getIssueByFromSerialNumber(String fromSerialNumber);
}

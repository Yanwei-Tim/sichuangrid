package com.tianque.fourTeams.fourTeamsIssue.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.domain.Organization;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueEvaluate;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;

public interface FourTeamsIssueDao {

	/**
	 * 根据事件单号查询事件
	 * 
	 * @param serialNumber
	 *            事件单号
	 * @return
	 */
	public FourTeamsIssueNew getIssueBySerialNumber(String serialNumber);

	/***
	 * 新增事件
	 * 
	 * @param issue
	 *            事件
	 * @return
	 */
	FourTeamsIssueNew addIssue(FourTeamsIssueNew issue);

	/***
	 * 修改事件
	 * 
	 * @param issue
	 *            事件
	 * @return
	 */
	FourTeamsIssueNew updateIssue(FourTeamsIssueNew issue);

	/**
	 * 更新事件的当前处理不步骤和部门
	 * 
	 * @param issue
	 */
	void updateIssueCurrentStepAndOrg(FourTeamsIssueNew issue);

	/**
	 * 根据id删除事件
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	boolean deleteIssueById(Long issueId);

	/**
	 * 设置事件为宣传案例
	 * 
	 * @param orgId
	 *            部门id
	 * @param issueId
	 *            事件id
	 */
	void publiclty(Long orgId, Long issueId);

	/**
	 * 判断事件是否已经设为宣传案例
	 * 
	 * @param issueId
	 *            事件id
	 * @param orgId
	 *            部门id
	 * @return
	 */
	boolean alreadyPubliclty(Long issueId, Long orgId);

	/**
	 * 删除宣传案例
	 * 
	 * @param orgId
	 *            部门id
	 * @param issueId
	 *            事件id
	 */
	void removePubliclty(Long orgId, Long issueId);

	/**
	 * 根据id获取完整事件对象
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	FourTeamsIssueNew getFullIssueById(Long issueId);

	/**
	 * 根据事件处理步骤id获取完整事件对象
	 * 
	 * @param stepId
	 *            事件处理步骤id
	 * @return
	 */
	FourTeamsIssueNew getFullIssueByStepId(Long stepId);

	boolean updateIssueHistoricState(Long issueId, boolean state);

	boolean updateIssueUrgentState(Long issueId, boolean state);

	/***
	 * 查询我的事项-待办事项的列表
	 * 
	 * @param orgId
	 * @param issueType
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<FourTeamsIssueViewObject> findMyNeedDoIssues(Long orgId,
			Long issueType, int page, int rows, String sidx, String sord);

	public PageInfo<FourTeamsIssueViewObject> findcommandCenterMyNeedDoIssues(
			Long orgId, Long issueType, int page, int rows, String sidx,
			String sord, String commandCenterNum);

	public PageInfo<FourTeamsIssueViewObject> findMyDone(Long orgId,
			FourTeamsIssueNew issue, Integer page, Integer rows, String sidx,
			String sord, Long issueType);

	public PageInfo<FourTeamsIssueViewObject> findcommandCenterMyDone(
			Long orgId, FourTeamsIssueNew issue, Integer page, Integer rows,
			String sidx, String sord, String commandCenterNum, int stateKind);

	PageInfo<FourTeamsIssueViewObject> findMyHistoricIssues(Long orgId,
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord);

	PageInfo<FourTeamsIssueViewObject> findMyPublicltyIssues(Long orgId,
			Integer page, Integer rows, String sidx, String sord);

	PageInfo<FourTeamsIssueViewObject> findJurisdictionsDone(String statusType,
			String fourTeamsType, String seachValue, Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType);

	PageInfo<FourTeamsIssueViewObject> findJurisdictionsCompleted(
			String fourTeamsType, String seachValue, Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, String statusType, Integer viewProcess,
			Long sourceType);

	PageInfo<FourTeamsIssueViewObject> findMyCompleted(Long orgId,
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Long issueType);

	PageInfo<FourTeamsIssueViewObject> findJurisdictionsNeedDo(
			String fourTeamsType, String seachValue, Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalorgType, Integer viewProcess, Long sourceType);

	PageInfo<FourTeamsIssueViewObject> findJurisdictionsDoing(
			String seachValue, Organization org, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess);

	PageInfo<FourTeamsIssueViewObject> findJurisdictionsAssgin(
			String seachValue, Organization org, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType);

	PageInfo<FourTeamsIssueViewObject> findJurisdictionsSubmit(
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType);

	int getMyNeedDoCount(Long orgId, Long issueType);

	/**
	 * 新增事件附件
	 * 
	 * @param files
	 */
	void addIssueAttachFiles(List<FourTeamsIssueAttachFile> files);

	/**
	 * 根据id删除事件附件
	 * 
	 * @param fileId
	 *            附件id
	 */
	void deleteAttachFileByAttachfilesId(Long fileId);

	/**
	 * 删除事件所有附件
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	boolean removeAllIssueAttachFiles(Long issueId);

	/**
	 * 根据id获取事件附件
	 * 
	 * @param id
	 *            附件id
	 * @return
	 */
	FourTeamsIssueAttachFile getIssueAttachFileById(Long id);

	/***
	 * 获取事件的所有附件
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	List<FourTeamsIssueAttachFile> getIssueAttachFileByIssueId(Long issueId);

	/**
	 * 获取事件的处理记录附件
	 * 
	 * @param issueId
	 *            事件id
	 * @param logId
	 *            处理记录id
	 * @return
	 */
	List<FourTeamsIssueAttachFile> loadIssueAttachFilesByIssueAndLog(
			Long issueId, Long logId);

	/**
	 * 删除事件类型关联关系
	 * 
	 * @param issueId
	 */
	void deleteIssueHasTypesByIssueId(Long issueId);

	/**
	 * 新增事件类型
	 * 
	 * @param issueId
	 * @param IssueTypeId
	 * @param IssueTypeDomainId
	 */
	void addIssueHasTypes(Long issueId, Long IssueTypeId, Long IssueTypeDomainId);

	/**
	 * 删除事件类型
	 * 
	 * @param issueId
	 * @param IssueTypeId
	 */
	void deleteIssueHasTypesByIssueIdAndIssueTypeId(Long issueId,
			Long IssueTypeId);

	List<FourTeamsIssueNew> searchAllRoundIssues(GisInfo minOption,
			GisInfo maxOption);

	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsNeedDoForProcess(
			String seachValue, Organization org, String sidx, String sord);

	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsDoneForProcess(
			String seachValue, Organization org, String sidx, String sord);

	public PageInfo<FourTeamsIssueViewObject> findJurisdictionAuditDelay(
			Long orgId, Integer page, Integer rows, String sidx, String sord);

	public FourTeamsIssueEvaluate getIssueEvaluateById(Long id);

	public Integer getIssueStepDelayWorkDaysById(Long stepId);

	List<FourTeamsIssueAttachFile> getIssueAttachFileAndNotLogAttachFileByIssueId(
			Long issueId);

	/** 查询该部门延期审核事件的数量 */
	public int getJurisdictionsAuditDelayCount(Long orgId);

	public void addIssueEvaluateAttachFiles(
			List<FourTeamsIssueAttachFile> attachFiles);

	public List<FourTeamsIssueAttachFile> getIssueEvaluateAttachFileAttachFileByIssueId(
			Long issueId);

	public List<FourTeamsIssueStep> searchIssueStepsByIssueId(Long issueId);

	public List<FourTeamsIssueStep> searchAllIssueStepsByStepId(Long issueId);

	/***
	 * 越级
	 * 
	 * @param org
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param issueType
	 * @param orgLevel
	 * @param leaderView
	 * @param functionalOrgType
	 * @param viewProcess
	 *            (预留这个参数)
	 * @return
	 */
	PageInfo<FourTeamsIssueViewObject> findJurisdictionsSkipIssue(
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType);

	public List<FourTeamsIssueAttachFile> getDocfilesByIdAndModuleKey(
			Long issueId, String moduleType, String fileType);

	/**
	 * 
	 * @Title: findIssueExistForMobile
	 * @Description: TODO通过uniqueidformobile查询事件是否已存在 手机端
	 * @param @return 设定文件
	 * @return Integer 返回类型
	 * @author wanggz
	 * @date 2014-5-14 上午11:06:26
	 */
	public Integer findIssueExistForMobile(FourTeamsIssueNew issue);
}

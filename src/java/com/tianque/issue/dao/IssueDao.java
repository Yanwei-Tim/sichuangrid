package com.tianque.issue.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueEvaluate;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.uitl.IssueAttachFileRetuenUtil;
import com.tianque.issue.vo.IssueViewObject;

public interface IssueDao {

	/**
	 * 根据事件单号查询事件
	 * 
	 * @param serialNumber
	 *            事件单号
	 * @return
	 */
	public IssueNew getIssueBySerialNumber(String serialNumber);

	/***
	 * 新增事件
	 * 
	 * @param issue
	 *            事件
	 * @return
	 */
	IssueNew addIssue(IssueNew issue);

	/***
	 * 修改事件
	 * 
	 * @param issue
	 *            事件
	 * @return
	 */
	IssueNew updateIssue(IssueNew issue);

	/**
	 * 更新事件的当前处理不步骤和部门
	 * 
	 * @param issue
	 */
	void updateIssueCurrentStepAndOrg(IssueNew issue);

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
	 * 删除宣传案例
	 * 
	 * @param issueId
	 *            事件id
	 */
	void removePublicltyByIssueId(Long issueId);

	/**
	 * 根据id获取完整事件对象
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	IssueNew getFullIssueById(Long issueId);

	/**
	 * 根据事件处理步骤id获取完整事件对象
	 * 
	 * @param stepId
	 *            事件处理步骤id
	 * @return
	 */
	IssueNew getFullIssueByStepId(Long stepId);

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
	PageInfo<IssueViewObject> findMyNeedDoIssues(Long orgId, Long issueType,
			int page, int rows, String sidx, String sord);

	public PageInfo<IssueViewObject> findcommandCenterMyNeedDoIssues(
			Long orgId, Long issueType, int page, int rows, String sidx,
			String sord, String commandCenterNum);

	public PageInfo<IssueViewObject> findMyDone(Long orgId, IssueNew issue,
			Integer page, Integer rows, String sidx, String sord, Long issueType);

	public PageInfo<IssueViewObject> findcommandCenterMyDone(Long orgId,
			IssueNew issue, Integer page, Integer rows, String sidx,
			String sord, String commandCenterNum, int stateKind);

	PageInfo<IssueViewObject> findMyHistoricIssues(Long orgId,
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord);

	PageInfo<IssueViewObject> findMyPublicltyIssues(Long orgId, Integer page,
			Integer rows, String sidx, String sord);

	PageInfo<IssueViewObject> findJurisdictionsFollow(Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode);

	/**
	 * 早期查询已办y页签的事件（已办包括办理中和已办结的事件）
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
	 * @param sourceType
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsDone(Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode);

	PageInfo<IssueViewObject> findTimeOutIssue(Organization org, Integer page,
			Integer rows, String sidx, String sord, Long issueType,
			Integer superviseType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode);

	PageInfo<IssueViewObject> findJurisdictionsCompleted(Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, String statusType, Integer viewProcess,
			Long sourceType, String issueGradeIsVisabled, int orgCodeFindLevel,
			Long searchOrgId, String searchOrgCode);

	PageInfo<IssueViewObject> findJurisdictionsVerification(Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, String statusType, Integer viewProcess,
			Long sourceType, int orgCodeFindLevel, Long searchOrgId,
			String searchOrgCode);

	PageInfo<IssueViewObject> findJurisdictionsGradeDelay(Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, String statusType, Integer viewProcess,
			Long sourceType, int orgCodeFindLevel, Long searchOrgId,
			String searchOrgCode);

	PageInfo<IssueViewObject> findJurisdictionsPublicltyCassDone(
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, String statusType, Integer viewProcess,
			Long sourceType, String issueGradeIsVisabled, int orgCodeFindLevel,
			Long searchOrgId, String searchOrgCode);

	PageInfo<IssueViewObject> findMyCompleted(Long orgId,
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Long issueType);

	PageInfo<IssueViewObject> findJurisdictionsNeedDo(String seachValue,
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalorgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode);

	/**
	 * 为手机端提供待办已督办事件
	 * 
	 * @param seachValue
	 * @param org
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param issueType
	 * @param orgLevel
	 * @param leaderView
	 * @param functionalorgType
	 * @param viewProcess
	 * @param sourceType
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsNeedDoForOverseerForMobile(
			String seachValue, Organization org, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalorgType, Integer viewProcess,
			Long sourceType);

	PageInfo<IssueViewObject> findJurisdictionsDoing(String seachValue,
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, int orgCodeFindLevel,
			Long searchOrgId, String searchOrgCode);

	PageInfo<IssueViewObject> findJurisdictionsAssgin(String seachValue,
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode);

	PageInfo<IssueViewObject> findJurisdictionsSubmit(Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode);

	int getMyNeedDoCount(Long orgId, Long issueType);

	/**
	 * 新增事件附件
	 * 
	 * @param files
	 */
	IssueAttachFileRetuenUtil addIssueAttachFiles(List<IssueAttachFile> files);

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
	IssueAttachFile getIssueAttachFileById(Long id);

	/***
	 * 获取事件的所有附件
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	List<IssueAttachFile> getIssueAttachFileByIssueId(Long issueId);

	/**
	 * 获取事件的处理记录附件
	 * 
	 * @param issueId
	 *            事件id
	 * @param logId
	 *            处理记录id
	 * @return
	 */
	List<IssueAttachFile> loadIssueAttachFilesByIssueAndLog(Long issueId,
			Long logId);

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

	List<IssueNew> searchAllRoundIssues(GisInfo minOption, GisInfo maxOption);

	public PageInfo<IssueViewObject> findJurisdictionsNeedDoForProcess(
			String seachValue, Organization org, String sidx, String sord);

	public PageInfo<IssueViewObject> findJurisdictionsDoneForProcess(
			String seachValue, Organization org, String sidx, String sord);

	public PageInfo<IssueViewObject> findJurisdictionAuditDelay(
			Organization org, Long orgLevel, Long functionalOrgType,
			Integer page, Integer rows, String sidx, String sord,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode,
			Long issueType);

	public IssueEvaluate getIssueEvaluateById(Long id);

	public Integer getIssueStepDelayWorkDaysById(Long stepId);

	List<IssueAttachFile> getIssueAttachFileAndNotLogAttachFileByIssueId(
			Long issueId);

	/** 查询该部门延期审核事件的数量 */
	public int getJurisdictionsAuditDelayCount(Long orgId);

	/** 查询下辖部门延期审核事件的数量 */
	public int getJurisdictionsAuditDelayCount(Long orgLevel, Organization org,
			Long functionalOrgType, int orgCodeFindLevel, Long searchOrgId,
			String searchOrgCode, Long issueType);

	/** 查询下辖部门待评价事件的数量 */
	public int getJurisdictionsVerificationCountForViewTab(Long orgLevel,
			Organization org, Long functionalOrgType);

	/** 查询下辖部门待评分事件的数量 */
	public int getJurisdictionsGradeDelayCountForViewTab(Long orgLevel,
			Organization org, Long functionalOrgType);

	public void addIssueEvaluateAttachFiles(List<IssueAttachFile> attachFiles);

	public List<IssueAttachFile> getIssueEvaluateAttachFileAttachFileByIssueId(
			Long issueId);

	public List<IssueStep> searchIssueStepsByIssueId(Long issueId);

	public List<IssueStep> searchAllIssueStepsByStepId(Long issueId);

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
	PageInfo<IssueViewObject> findJurisdictionsSkipIssue(Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode);

	public List<IssueAttachFile> getDocfilesByIdAndModuleKey(Long issueId,
			String moduleType, String fileType);

	/**
	 * 
	 * @Title: findIssueExistForMobile
	 * @Description: TODO通过uniqueidformobile查询事件是否已存在 手机端
	 * @param @return 设定文件
	 * @return Integer 返回类型
	 * @author wanggz
	 * @date 2014-5-14 上午11:06:26
	 */
	public Integer findIssueExistForMobile(IssueNew issue);

	/**
	 * 
	 * @Title: findIssueStepByIssueId
	 * @Description: TODO为手机端增加一个查询事件步骤的方法
	 * @param @param issueId
	 * @param @return 设定文件
	 * @return IssueStep 返回类型
	 * @author wanggz
	 * @date 2014-6-13 下午06:31:47
	 */
	public IssueStep findIssueStepByIssueId(Long issueId);

	/**
	 * 统计已办的事件数量
	 * 
	 * @param map
	 * @return
	 */
	public int getMyDoneCount(Map map);

	/**
	 * 查询待验证的数据
	 * 
	 * @param map
	 * @return
	 */
	public int getJurisdictionsVerification(Map<String, Object> map);

	/**
	 * 查询待办的数量
	 * 
	 * @param map
	 * @return
	 */
	public int getJurisdictionsNeedDoCount(Map<String, Object> map);

	/**
	 * 查询待办，待审核，待验证，待评分的总数
	 * 
	 * @param seachValue
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
	 * @param sourceType
	 * @return
	 */
	public int getIssueNumWorkBench(String seachValue, Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			boolean needIssuePermission, boolean checkPendingIssuePermission,
			boolean verificationIssuePermission,
			boolean checkGradeIssuePermission);

	public void updateIssueFromSerialNumber(Long id, String demandCode);

	public void updateIssueDeadLine(IssueNew issue);

	public void updateTableUpdateDateById(Long id);

	public int getJurisdictionsNeedDoCountForOverseerForMobile(
			Map<String, Object> map);

	void publiclty(Long issueId, int flag);
	
	/*
	 * 更新历史事件表中的记录
	 */
	void publicltyHistory(Long issueId, int flag);

	public void updateEventStateByIssueId(Long issueId);
}

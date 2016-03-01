package com.tianque.issue.service;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.EmphasesVo;
import com.tianque.issue.domain.IssueAccord;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueEvaluate;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueMap;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.domain.TopIssue;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.vo.IssueViewObject;

public interface IssueService {

	/***
	 * 根据服务单号获取事件
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
	 * @param files
	 *            附件
	 * @return
	 */
	IssueViewObject addIssue(IssueNew issue, List<IssueAttachFile> files,
			Map<String, String> map, String issueRelatedPeopleNames,
			String issueRelatedPeopleTelephones,
			String issueRelatedPeopleFixPhones);

	/***
	 * 修改事件
	 * 
	 * @param issue
	 *            事件
	 * @param files
	 *            附件
	 * @return
	 */
	public IssueViewObject updateIssue(IssueNew issue,
			List<IssueAttachFile> files, long stepId, Map<String, String> map,
			String issueRelatedPeopleNames,
			String issueRelatedPeopleTelephones,
			String issueRelatedPeopleFixPhones);

	/**
	 * 根据事件id删除事件
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	boolean deleteIssueByIssueId(Long issueId);

	/***
	 * 置顶事件
	 * 
	 * @param topIssue
	 *            事件置顶
	 * @return
	 */
	boolean topIssue(TopIssue topIssue);

	/**
	 * 结案
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @param files
	 *            附件
	 * @return
	 */
	IssueViewObject complete(Long stepId, IssueLogNew log,
			List<IssueAttachFile> files);

	/**
	 * 验证
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @param files
	 *            附件
	 * @return
	 */
	IssueViewObject verification(Long stepId, IssueLogNew log,
			List<IssueAttachFile> files, int isChangeIntoThreeRecords);

	/**
	 * 上报
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @param target
	 *            上报目标部门id
	 * @param tells
	 *            抄告部门id
	 * @param files
	 *            附件
	 * @return
	 */
	IssueViewObject submit(Long stepId, IssueLogNew log, Long target,
			Long[] tells, List<IssueAttachFile> files);

	/**
	 * 受理
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	IssueViewObject concept(Long stepId, IssueLogNew log);

	/**
	 * 办理中
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	IssueViewObject comment(Long stepId, IssueLogNew log,
			List<IssueAttachFile> files);

	/**
	 * 交办
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @param target
	 *            交办目标部门id
	 * @param tellorgs
	 *            抄告部门id
	 * @param files
	 *            附件
	 * @return
	 */
	IssueViewObject assign(Long stepId, IssueLogNew log, Long target,
			Long[] tellorgs, List<IssueAttachFile> files);

	/**
	 * 上报指挥中心
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	IssueViewObject reportToCommandCenter(Long stepId, IssueLogNew log);

	/**
	 * 阅读(抄告职能部门的时候)
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	IssueViewObject read(Long stepId, IssueLogNew log);

	/***
	 * 下单(指挥中心下派)
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @param target
	 *            下单目标部门
	 * @param telorg
	 *            抄告部门id
	 * @param files
	 *            附件
	 * @return
	 */
	IssueViewObject giveTo(Long stepId, IssueLogNew log, Long target,
			Long[] telorg, List<IssueAttachFile> files);

	/**
	 * 回退
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @param files
	 *            附件
	 * @return
	 */
	IssueViewObject back(Long stepId, IssueLogNew log,
			List<IssueAttachFile> files);

	/**
	 * 批示
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	IssueViewObject instruct(Long stepId, IssueLogNew log);

	/**
	 * 督办
	 * 
	 * @param superviseLevel
	 *            督办等级
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	IssueViewObject supervise(IssueOperate superviseLevel, Long stepId,
			IssueLogNew log);

	/**
	 * 取消督办
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	IssueViewObject cancelSupervise(Long stepId, IssueLogNew log);

	/**
	 * 加急
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	IssueViewObject urgent(Long stepId, IssueLogNew log);

	/**
	 * 取消加急
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	IssueViewObject cancelUrgent(Long stepId, IssueLogNew log);

	/**
	 * 历史遗留
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	IssueViewObject historic(Long stepId, IssueLogNew log);

	/**
	 * 取消历史遗留
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	IssueViewObject cancelHistoric(Long stepId, IssueLogNew log);

	/**
	 * 设置事件为宣传案例
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	IssueViewObject publiclty(Long issueId);

	/**
	 * 取消宣传案例
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	IssueViewObject cancelPubliclty(Long issueId);

	/***
	 * 我的事项-待办事项列表
	 * 
	 * @param orgId
	 *            登录部门的id
	 * @param issueType
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findMyNeedDoIssues(Long orgId, Long issueType,
			Integer page, Integer rows, String sidx, String sord);

	/***
	 * 社情民意中心的待办诉求列表
	 * 
	 * @param orgId
	 *            诉求所属的网格id
	 * @param issueType
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findcommandCenterMyNeedDoIssues(Long orgId,
			Long issueType, Integer page, Integer rows, String sidx, String sord);

	/***
	 * 已办事项列表
	 * 
	 * @param orgId
	 * @param issue
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findMyDone(Long orgId, IssueNew issue,
			Integer page, Integer rows, String sidx, String sord, Long issueType);

	/**
	 * 社情民意中心 已办诉求列表
	 * 
	 * @param orgId
	 *            部门id
	 * @param issue
	 *            事件
	 * @param page
	 *            页码
	 * @param rows
	 *            每页显示数量
	 * @param sidx
	 *            排序字段
	 * @param sord
	 *            排序方式
	 * @param stateKind
	 * @return
	 */
	PageInfo<IssueViewObject> findcommandCenterMyDone(Long orgId,
			IssueNew issue, Integer page, Integer rows, String sidx,
			String sord, int stateKind);

	/***
	 * 宣传案例事件列表
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findMyPublicltyIssues(Long orgId, Integer page,
			Integer rows, String sidx, String sord);

	/***
	 * 下辖部门待办事项列表
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param issueType
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsNeedDo(String seachValue,
			Long orgId, Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalorgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId);

	/**
	 * 为手机端提供待办已经督办事件
	 * 
	 * @param seachValue
	 * @param orgId
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
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalorgType, Integer viewProcess,
			Long sourceType);

	/***
	 * 工作台待办事项列表
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param issueType
	 * @return
	 */
	PageInfo<IssueViewObject> findIssueNeedDo(String seachValue, Long orgId,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalorgType, Integer viewProcess, Long sourceType);

	/**
	 * 工作台，待办事项的条数
	 * 
	 * @param seachValue
	 * @param orgId
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
	public int getIssueNumWorkBench(String seachValue, Long orgId,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalorgType, Integer viewProcess, Long sourceType,
			Long userId);

	/***
	 * 下辖部门办理中事项列表
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param issueType
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsDoing(String seachValue,
			Long orgId, Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, int orgCodeFindLevel,
			Long searchOrgId);

	/***
	 * 下辖部门上级交办事项列表
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param issueType
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsAssgin(String seachValue,
			Long orgId, Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, String statusType, Integer viewProcess,
			Long sourceType, int orgCodeFindLevel, Long searchOrgId);

	/**
	 * 下辖部门待跟进事项列表
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsFollow(Long orgId, Integer page,
			Integer rows, String sidx, String sord, Long issueType,
			Long orgLevel, String leaderView, Long functionalOrgType,
			Integer viewProcess, Long sourceType, int orgCodeFindLevel,
			Long searchOrgId);

	/**
	 * 下辖部门已办事项列表
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsDone(Long orgId, Integer page,
			Integer rows, String sidx, String sord, Long issueType,
			Long orgLevel, String leaderView, Long functionalOrgType,
			String statusType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId);

	/**
	 * 下辖部门超时事项列表
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findTimeOutIssue(Long orgId, Integer page,
			Integer rows, String sidx, String sord, Long issueType,
			Integer superviseType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId);

	/**
	 * 下辖部门已办结事项列表
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsCompleted(Long orgId,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, String statusType, Integer viewProcess,
			Long sourceType, String issueGradeIsVisabled, int orgCodeFindLevel,
			Long searchOrgId, String evaluationType);

	/**
	 * 下辖部门待验证结事项列表
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsVerification(Long orgId,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, String statusType, Integer viewProcess,
			Long sourceType, int orgCodeFindLevel, Long searchOrgId);

	/**
	 * 下辖部门待评分事项列表
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsGradeDelay(Long orgId,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, String statusType, Integer viewProcess,
			Long sourceType, int orgCodeFindLevel, Long searchOrgId,
			String evaluationType);

	/***
	 * 下辖部门待验证结事件数量
	 * 
	 * @param orgId
	 * @return
	 */
	int getJurisdictionsVerificationCountForViewTab(Long orgLevel, Long orgId,
			Long functionalOrgType);

	/***
	 * 下辖部门待验证结事件数量
	 * 
	 * @param orgId
	 * @return
	 */
	int getJurisdictionsGradeDelayCountForViewTab(Long orgLevel, Long orgId,
			Long functionalOrgType);

	/**
	 * 下辖部门上报事项列表
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsSubmit(Long orgId, Integer page,
			Integer rows, String sidx, String sord, Long issueType,
			Long orgLevel, String leaderView, Long functionalOrgType,
			String statusType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId);

	/**
	 * 我的事项-已办结事项
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findMyCompleted(Long orgId, Integer page,
			Integer rows, String sidx, String sord, Long issueType);

	/**
	 * 被设置为历史遗留的事件列表
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findMyHistoricIssues(Long orgId, Integer page,
			Integer rows, String sidx, String sord);

	/***
	 * 根据处理步骤id 获取完整的事件对象
	 * 
	 * @param keyId
	 *            处理步骤id
	 * @return
	 */
	IssueNew getFullIssueByStepId(Long keyId);

	/***
	 * 根据事件id 获取完整的事件对象
	 * 
	 * @param keyId
	 *            事件id
	 * @return
	 */
	IssueNew getFullIssueByIssueId(Long keyId);

	/**
	 * 获取事件的所有附件
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	List<IssueAttachFile> loadIssueAttachFilesByIssueId(Long issueId);

	/***
	 * 获取事件的所有处理记录
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	List<IssueLogNew> loadIssueOperationLogsByIssueId(Long issueId);

	/**
	 * 根据id获取事件附件
	 * 
	 * @param id
	 *            事件附件id
	 * @return
	 */
	IssueAttachFile getIssueAttachFileById(Long id);

	List<IssueOperate> getIssueCandoForOrg(Long stepId, Organization operateOrg);

	/***
	 * 根据id获取事件处理步骤
	 * 
	 * @param stepId
	 *            步骤id
	 * @return
	 */
	IssueStep getIssueStepById(Long stepId);

	/**
	 * 根据关键字查询事件的主办单位(行政单位)
	 * 
	 * @param stepid
	 *            步骤id
	 * @param tag
	 *            用户输入的关键字
	 * @param operate
	 *            操作类型
	 * @param exceptIds
	 *            已选择部门id
	 * @param pageIndex
	 * @return
	 */
	PageInfo<AutoCompleteData> findAdminTargetsByName(Long stepid, String tag,
			IssueOperate operate, Long[] exceptIds, int pageIndex, int rows);

	/**
	 * 根据关键字查询事件的主办单位(职能部门)
	 * 
	 * @param stepid
	 *            步骤id
	 * @param tag
	 *            用户输入的关键字
	 * @param operate
	 *            操作类型
	 * @param exceptIds
	 *            已选择部门id
	 * @param pageIndex
	 * @return
	 */
	PageInfo<AutoCompleteData> findFunctionTargetsByName(Long stepid,
			String tag, IssueOperate operate, Long[] exceptIds, int pageIndex,
			int rows);

	/**
	 * 根据用户输入的关键字查询抄告单位(行政部门)
	 * 
	 * @param stepid
	 *            步骤id
	 * @param tag
	 *            用户输入的关键字
	 * @param operate
	 *            操作类型
	 * @param transferToAdmin
	 *            主送目标是否是行政部门
	 * @param exceptIds
	 *            其中exceptIds[0]为主送目标
	 * @param pageIndex
	 * @return
	 */
	PageInfo findTellTargetsByName(Long stepid, String tag,
			IssueOperate operate, boolean transferToAdmin, Long[] exceptIds,
			int page, int rows);

	public List<IssueNew> searchAllRoundIssues(GisInfo minOption,
			GisInfo maxOption);

	/***
	 * 获取事件的处理记录图表视图数据
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	List<IssueMap> getIssueMap(Long issueId);

	/***
	 * 获取事件在某个部门的处理记录
	 * 
	 * @param issueMap
	 *            issueMap中包含issueId 和 orgId
	 * @return
	 */
	List<IssueLogNew> getIssueDealLog(IssueMap issueMap);

	/**
	 * 添加事附件
	 * 
	 * @param postIssue
	 * @param existIssue
	 * @param issueLog
	 * @param files
	 */
	void addIssueAttachFiles(IssueNew postIssue, IssueNew existIssue,
			List<IssueAttachFile> files);

	public Map<String, List<EmphasesVo>> findRelatePerson(Long id);

	public Map<String, List<EmphasesVo>> findRelatePlace(Long id);

	/**
	 * 查询下辖待办动态列表数据
	 * 
	 * @param seachValue
	 * @param orgId
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<IssueViewObject> findJurisdictionsNeedDoForProcess(
			String seachValue, Long orgId, String sidx, String sord);

	/**
	 * 查询下辖已办结动态列表数据
	 * 
	 * @param orgId
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<IssueViewObject> findJurisdictionsDoneForProcess(
			String seachValue, Long orgId, String sidx, String sord);

	public IssueNew saveIssueGrade(Long id, IssueAccord issueAccord,
			IssueEvaluate issueEvaluate);

	public PageInfo<IssueViewObject> findJurisdictionAuditDelay(Long orgId,
			Long orgLevel, Long functionalOrgType, Integer page, Integer rows,
			String sidx, String sord, int orgCodeFindLevel, Long searchOrgId,
			Long issueType);

	public IssueEvaluate getIssueEvaluateById(Long keyId);

	public List<IssueAttachFile> loadIssueAttachFilesByIssueIdAndIssueLogId(
			Long issueId, Long issueLogId);

	/***
	 * 根据步骤id获取事件处理步骤申请延期时间
	 * 
	 * @param stepId
	 *            步骤id
	 * @return
	 */
	public Integer getIssueStepDelayWorkDaysById(Long stepId);

	/**
	 * issueLog修改
	 * 
	 * @param issueLog
	 * @param files
	 * @return
	 */
	public IssueLogNew updateIssueLog(IssueLogNew issueLog,
			List<IssueAttachFile> files);

	public Integer getIssueStepCountByIssueId(Long issueId);

	public void addIssueEvaluateAttachFile(IssueEvaluate issueEvaluate,
			List<IssueAttachFile> files);

	public List<IssueAttachFile> getIssueEvaluateAttachFileAttachFileByIssueId(
			Long issueId);

	/***
	 * 越级
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param issueType
	 * @param orgLevel
	 * @param leaderView
	 * @param functionalOrgType
	 * @param viewProcess
	 * @return
	 */
	public PageInfo<IssueViewObject> findJurisdictionsSkipIssue(Long orgId,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, String statusType, Integer viewProcess,
			Long sourceType, int orgCodeFindLevel, Long searchOrgId);

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
	 * 更新本步骤的重大紧急等级,以确定是否越级上报
	 * 
	 * @param issueStep
	 */
	public void updateEmergencylevelByIssueStepId(IssueStep issueStep);

	/***
	 * 新增事件前验证事件数据是正确
	 * 
	 * @param issue
	 * @param files
	 * @param issueRelatedPeopleNames
	 * @param issueRelatedPeopleTelephones
	 * @param issueRelatedPeopleFixPhones
	 */
	public void validateDataForAddIssue(IssueNew issue,
			List<IssueAttachFile> files, String issueRelatedPeopleNames,
			String issueRelatedPeopleTelephones,
			String issueRelatedPeopleFixPhones);

	/**
	 * 统计已办的事件数量
	 * 
	 * @param map
	 * @return
	 */
	public int getMyDoneCount(Organization organization);

	/**
	 * 查询待验证的数据
	 * 
	 * @param map
	 * @return
	 */
	public int getJurisdictionsVerification(Organization organization);

	/**
	 * 查询待办的数量
	 * 
	 * @param map
	 * @return
	 */
	public int getJurisdictionsNeedDoCount(Organization organization);

	/**
	 * 下辖部门已办结事项列表
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsPublicltyCassDone(Long orgId,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, String statusType, Integer viewProcess,
			Long sourceType, String issueGradeIsVisabled, int orgCodeFindLevel,
			Long searchOrgId);

	/***
	 * 更新事件转入三本台帐后 事件的状态
	 * 
	 * @return
	 */
	public void updateEventStateByIssueId(Long issueId);
}

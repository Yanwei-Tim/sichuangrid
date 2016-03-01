package com.tianque.fourTeams.fourTeamsIssue.service;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.EmphasesVo;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAccord;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueEvaluate;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueMap;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsTopIssue;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;

public interface FourTeamsIssueService {

	/***
	 * 根据服务单号获取事件
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
	 * @param files
	 *            附件
	 * @return
	 */
	FourTeamsIssueViewObject addIssue(FourTeamsIssueNew issue,
			List<FourTeamsIssueAttachFile> files, Map<String, String> map,
			String issueRelatedPeopleNames,
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
	public FourTeamsIssueViewObject updateIssue(FourTeamsIssueNew issue,
			List<FourTeamsIssueAttachFile> files, long stepId,
			Map<String, String> map, String issueRelatedPeopleNames,
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
	boolean topIssue(FourTeamsTopIssue topIssue);

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
	FourTeamsIssueViewObject complete(Long stepId, FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> files);

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
	FourTeamsIssueViewObject submit(Long stepId, FourTeamsIssueLogNew log,
			Long target, Long[] tells, List<FourTeamsIssueAttachFile> files);

	/**
	 * 受理
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	FourTeamsIssueViewObject concept(Long stepId, FourTeamsIssueLogNew log);

	/**
	 * 办理中
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	FourTeamsIssueViewObject comment(Long stepId, FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> files);

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
	FourTeamsIssueViewObject assign(Long stepId, FourTeamsIssueLogNew log,
			Long target, Long[] tellorgs, List<FourTeamsIssueAttachFile> files);

	/**
	 * 上报指挥中心
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	FourTeamsIssueViewObject reportToCommandCenter(Long stepId,
			FourTeamsIssueLogNew log);

	/**
	 * 阅读(抄告职能部门的时候)
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	FourTeamsIssueViewObject read(Long stepId, FourTeamsIssueLogNew log);

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
	FourTeamsIssueViewObject giveTo(Long stepId, FourTeamsIssueLogNew log,
			Long target, Long[] telorg, List<FourTeamsIssueAttachFile> files);

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
	FourTeamsIssueViewObject back(Long stepId, FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> files);

	/**
	 * 批示
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	FourTeamsIssueViewObject instruct(Long stepId, FourTeamsIssueLogNew log);

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
	FourTeamsIssueViewObject supervise(FourTeamsIssueOperate superviseLevel,
			Long stepId, FourTeamsIssueLogNew log);

	/**
	 * 取消督办
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	FourTeamsIssueViewObject cancelSupervise(Long stepId,
			FourTeamsIssueLogNew log);

	/**
	 * 加急
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	FourTeamsIssueViewObject urgent(Long stepId, FourTeamsIssueLogNew log);

	/**
	 * 取消加急
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	FourTeamsIssueViewObject cancelUrgent(Long stepId, FourTeamsIssueLogNew log);

	/**
	 * 历史遗留
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	FourTeamsIssueViewObject historic(Long stepId, FourTeamsIssueLogNew log);

	/**
	 * 取消历史遗留
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @return
	 */
	FourTeamsIssueViewObject cancelHistoric(Long stepId,
			FourTeamsIssueLogNew log);

	/**
	 * 设置事件为宣传案例
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	FourTeamsIssueViewObject publiclty(Long issueId);

	/**
	 * 取消宣传案例
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	FourTeamsIssueViewObject cancelPubliclty(Long issueId);

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
	PageInfo<FourTeamsIssueViewObject> findMyNeedDoIssues(Long orgId,
			Long issueType, Integer page, Integer rows, String sidx, String sord);

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
	PageInfo<FourTeamsIssueViewObject> findcommandCenterMyNeedDoIssues(
			Long orgId, Long issueType, Integer page, Integer rows,
			String sidx, String sord);

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
	PageInfo<FourTeamsIssueViewObject> findMyDone(Long orgId,
			FourTeamsIssueNew issue, Integer page, Integer rows, String sidx,
			String sord, Long issueType);

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
	PageInfo<FourTeamsIssueViewObject> findcommandCenterMyDone(Long orgId,
			FourTeamsIssueNew issue, Integer page, Integer rows, String sidx,
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
	PageInfo<FourTeamsIssueViewObject> findMyPublicltyIssues(Long orgId,
			Integer page, Integer rows, String sidx, String sord);

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
	PageInfo<FourTeamsIssueViewObject> findJurisdictionsNeedDo(
			String fourTeamsType, String seachValue, Long orgId, Integer page,
			Integer rows, String sidx, String sord, Long issueType,
			Long orgLevel, String leaderView, Long functionalorgType,
			Integer viewProcess, Long sourceType);

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
	PageInfo<FourTeamsIssueViewObject> findJurisdictionsDoing(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess);

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
	PageInfo<FourTeamsIssueViewObject> findJurisdictionsAssgin(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType);

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
	PageInfo<FourTeamsIssueViewObject> findJurisdictionsDone(String statusType,
			String fourTeamsType, String seachValue, Long orgId, Integer page,
			Integer rows, String sidx, String sord, Long issueType,
			Long orgLevel, String leaderView, Long functionalOrgType,
			Integer viewProcess, Long sourceType);

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
	PageInfo<FourTeamsIssueViewObject> findJurisdictionsCompleted(
			String fourTeamsType, String seachValue, Long orgId, Integer page,
			Integer rows, String sidx, String sord, Long issueType,
			Long orgLevel, String leaderView, Long functionalOrgType,
			String statusType, Integer viewProcess, Long sourceType);

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
	PageInfo<FourTeamsIssueViewObject> findJurisdictionsSubmit(Long orgId,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType);

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
	PageInfo<FourTeamsIssueViewObject> findMyCompleted(Long orgId,
			Integer page, Integer rows, String sidx, String sord, Long issueType);

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
	PageInfo<FourTeamsIssueViewObject> findMyHistoricIssues(Long orgId,
			Integer page, Integer rows, String sidx, String sord);

	/***
	 * 根据处理步骤id 获取完整的事件对象
	 * 
	 * @param keyId
	 *            处理步骤id
	 * @return
	 */
	FourTeamsIssueNew getFullIssueByStepId(Long keyId);

	/***
	 * 根据事件id 获取完整的事件对象
	 * 
	 * @param keyId
	 *            事件id
	 * @return
	 */
	FourTeamsIssueNew getFullIssueByIssueId(Long keyId);

	/**
	 * 获取事件的所有附件
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	List<FourTeamsIssueAttachFile> loadIssueAttachFilesByIssueId(Long issueId);

	/***
	 * 获取事件的所有处理记录
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	List<FourTeamsIssueLogNew> loadIssueOperationLogsByIssueId(Long issueId);

	/**
	 * 根据id获取事件附件
	 * 
	 * @param id
	 *            事件附件id
	 * @return
	 */
	FourTeamsIssueAttachFile getIssueAttachFileById(Long id);

	List<FourTeamsIssueOperate> getIssueCandoForOrg(Long stepId,
			Organization operateOrg);

	/***
	 * 根据id获取事件处理步骤
	 * 
	 * @param stepId
	 *            步骤id
	 * @return
	 */
	FourTeamsIssueStep getIssueStepById(Long stepId);

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
			FourTeamsIssueOperate operate, Long[] exceptIds, int pageIndex,
			int rows);

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
			String tag, FourTeamsIssueOperate operate, Long[] exceptIds,
			int pageIndex, int rows);

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
			FourTeamsIssueOperate operate, boolean transferToAdmin,
			Long[] exceptIds, int page, int rows);

	public List<FourTeamsIssueNew> searchAllRoundIssues(GisInfo minOption,
			GisInfo maxOption);

	/***
	 * 获取事件的处理记录图表视图数据
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	List<FourTeamsIssueMap> getIssueMap(Long issueId);

	/***
	 * 获取事件在某个部门的处理记录
	 * 
	 * @param issueMap
	 *            issueMap中包含issueId 和 orgId
	 * @return
	 */
	List<FourTeamsIssueLogNew> getIssueDealLog(FourTeamsIssueMap issueMap);

	/**
	 * 添加事附件
	 * 
	 * @param postIssue
	 * @param existIssue
	 * @param issueLog
	 * @param files
	 */
	void addIssueAttachFiles(FourTeamsIssueNew postIssue,
			FourTeamsIssueNew existIssue, List<FourTeamsIssueAttachFile> files);

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
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsNeedDoForProcess(
			String seachValue, Long orgId, String sidx, String sord);

	/**
	 * 查询下辖已办结动态列表数据
	 * 
	 * @param orgId
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsDoneForProcess(
			String seachValue, Long orgId, String sidx, String sord);

	public FourTeamsIssueNew saveIssueGrade(Long id,
			FourTeamsIssueAccord issueAccord);

	public PageInfo<FourTeamsIssueViewObject> findJurisdictionAuditDelay(
			Integer page, Integer rows, String sidx, String sord);

	public FourTeamsIssueEvaluate getIssueEvaluateById(Long keyId);

	public List<FourTeamsIssueAttachFile> loadIssueAttachFilesByIssueIdAndIssueLogId(
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
	public FourTeamsIssueLogNew updateIssueLog(FourTeamsIssueLogNew issueLog,
			List<FourTeamsIssueAttachFile> files);

	public Integer getIssueStepCountByIssueId(Long issueId);

	public void addIssueEvaluateAttachFile(
			FourTeamsIssueEvaluate issueEvaluate,
			List<FourTeamsIssueAttachFile> files);

	public List<FourTeamsIssueAttachFile> getIssueEvaluateAttachFileAttachFileByIssueId(
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
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsSkipIssue(
			Long orgId, Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
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

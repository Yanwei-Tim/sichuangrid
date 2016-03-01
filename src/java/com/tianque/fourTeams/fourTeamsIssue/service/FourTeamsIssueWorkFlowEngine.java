package com.tianque.fourTeams.fourTeamsIssue.service;

import java.util.List;

import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;

public interface FourTeamsIssueWorkFlowEngine {

	FourTeamsIssueStep register(FourTeamsIssueNew issue);

	boolean unRegister(Long issueId);

	/**
	 * 上报指挥中心
	 * 
	 * @param issue
	 * @param step
	 * @param log
	 * @return
	 */
	FourTeamsIssueStep reportTo(FourTeamsIssueNew issue, FourTeamsIssueStep step, FourTeamsIssueLogNew log);

	/**
	 * 结案
	 * 
	 * @param issue
	 *            事件
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	FourTeamsIssueStep complete(FourTeamsIssueNew issue, FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> attachFiles);

	/**
	 * 上报
	 * 
	 * @param issue
	 *            事件
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param submitTarget
	 *            上报目标部门
	 * @param tells
	 *            抄告部门
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	FourTeamsIssueStep submit(FourTeamsIssueNew issue, FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			Long submitTarget, Long[] tells, List<FourTeamsIssueAttachFile> attachFiles);

	/**
	 * 回退
	 * 
	 * @param issue
	 *            事件
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	FourTeamsIssueStep back(FourTeamsIssueNew issue, FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> attachFiles);

	/**
	 * 受理
	 * 
	 * @param issue
	 *            事件
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @return
	 */
	FourTeamsIssueStep concept(FourTeamsIssueNew issue, FourTeamsIssueStep step, FourTeamsIssueLogNew log);

	/***
	 * 办理中
	 * 
	 * @param issue
	 *            事件
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	FourTeamsIssueStep comment(FourTeamsIssueNew issue, FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> attachFiles);

	/***
	 * 交办
	 * 
	 * @param issue
	 *            事件
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param targetOrg
	 *            交办目标部门
	 * @param tells
	 *            抄告部门
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	FourTeamsIssueStep assign(FourTeamsIssueNew issue, FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			Long targetOrg, Long[] tells, List<FourTeamsIssueAttachFile> attachFiles);

	/**
	 * 下单(指挥中心下派)
	 * 
	 * @param issue
	 *            事件
	 * @param step
	 *            处理步骤
	 * @param log
	 *            处理记录
	 * @param targetOrgId
	 *            下单目标部门id
	 * @param tellorgIds
	 *            抄告部门id
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	FourTeamsIssueStep giveTo(FourTeamsIssueNew issue, FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			Long targetOrgId, Long[] tellorgIds,
			List<FourTeamsIssueAttachFile> attachFiles);

	/**
	 * 阅读(职能部门)
	 * 
	 * @param issue
	 *            事件
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @return
	 */
	FourTeamsIssueStep read(FourTeamsIssueNew issue, FourTeamsIssueStep step, FourTeamsIssueLogNew log);

	/**
	 * 领导批示
	 * 
	 * @param issue
	 *            事件
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @return
	 */
	FourTeamsIssueStep instruct(FourTeamsIssueNew issue, FourTeamsIssueStep step, FourTeamsIssueLogNew log);

	/**
	 * 取消督办
	 * 
	 * @param issue
	 *            事件
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @return
	 */
	FourTeamsIssueStep cancelSupervise(FourTeamsIssueNew issue, FourTeamsIssueStep step, FourTeamsIssueLogNew log);

	/**
	 * 督办
	 * 
	 * @param issue
	 *            事件
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param type
	 *            操作类型
	 * @return
	 */
	FourTeamsIssueStep supervise(FourTeamsIssueNew issue, FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			FourTeamsIssueOperate type);

	/**
	 * 根据id获取完整的事件处理步骤对象
	 * 
	 * @param stepId
	 *            步骤id
	 * @return
	 */
	FourTeamsIssueStep getFullIssueStepById(Long stepId);

	List<FourTeamsIssueOperate> getIssueCandoForOrg(Long stepId, Organization org);

	PageInfo<AutoCompleteData> findAdminTargetsByName(Long stepid,
			FourTeamsIssueOperate operate, String tag, Long[] exceptIds, int pageIndex,
			int rows);

	PageInfo<AutoCompleteData> findFunctionTargetsByName(Long stepid,
			FourTeamsIssueOperate operate, String tag, Long[] exceptIds, int pageIndex,
			int rows);

	PageInfo<AutoCompleteData> findTellTargetsByName(Long stepid,
			FourTeamsIssueOperate operate, String tag, boolean transferToAdmin,
			Long[] exceptIds, int page, int rows);

	void fireIssueChanged(FourTeamsIssueNew issue, FourTeamsIssueOperate operate,
			FourTeamsIssueLogNew log, List<FourTeamsIssueAttachFile> attachFiles);

	void fireIssueChanged(FourTeamsIssueNew issue, FourTeamsIssueStep step, FourTeamsIssueOperate operate,
			FourTeamsIssueLogNew log, List<FourTeamsIssueAttachFile> attachFiles);

	/**
	 * 根据事件步骤的状态获取对应的 事件列表
	 * 
	 * @return List<IssueViewObject>
	 */
	List<FourTeamsIssueStep> findIssueStepListByIssueState(int[] issueStates);

}

package com.tianque.issue.service;

import java.util.List;

import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueOperate;

public interface IssueWorkFlowEngine {

	IssueStep register(IssueNew issue);

	boolean unRegister(Long issueId);

	/**
	 * 上报指挥中心
	 * 
	 * @param issue
	 * @param step
	 * @param log
	 * @return
	 */
	IssueStep reportTo(IssueNew issue, IssueStep step, IssueLogNew log);

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
	IssueStep complete(IssueNew issue, IssueStep step, IssueLogNew log,
			List<IssueAttachFile> attachFiles);

	/**
	 * 验证
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
	IssueStep verification(IssueNew issue, IssueStep step, IssueLogNew log,
			List<IssueAttachFile> attachFiles);

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
	IssueStep submit(IssueNew issue, IssueStep step, IssueLogNew log,
			Long submitTarget, Long[] tells, List<IssueAttachFile> attachFiles);

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
	IssueStep back(IssueNew issue, IssueStep step, IssueLogNew log,
			List<IssueAttachFile> attachFiles);

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
	IssueStep concept(IssueNew issue, IssueStep step, IssueLogNew log);

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
	IssueStep comment(IssueNew issue, IssueStep step, IssueLogNew log,
			List<IssueAttachFile> attachFiles);

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
	IssueStep assign(IssueNew issue, IssueStep step, IssueLogNew log,
			Long targetOrg, Long[] tells, List<IssueAttachFile> attachFiles);

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
	IssueStep giveTo(IssueNew issue, IssueStep step, IssueLogNew log,
			Long targetOrgId, Long[] tellorgIds,
			List<IssueAttachFile> attachFiles);

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
	IssueStep read(IssueNew issue, IssueStep step, IssueLogNew log);

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
	IssueStep instruct(IssueNew issue, IssueStep step, IssueLogNew log);

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
	IssueStep cancelSupervise(IssueNew issue, IssueStep step, IssueLogNew log);

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
	IssueStep supervise(IssueNew issue, IssueStep step, IssueLogNew log,
			IssueOperate type);

	/**
	 * 根据id获取完整的事件处理步骤对象
	 * 
	 * @param stepId
	 *            步骤id
	 * @return
	 */
	IssueStep getFullIssueStepById(Long stepId);

	List<IssueOperate> getIssueCandoForOrg(Long stepId, Organization org);

	PageInfo<AutoCompleteData> findAdminTargetsByName(Long stepid,
			IssueOperate operate, String tag, Long[] exceptIds, int pageIndex,
			int rows);

	PageInfo<AutoCompleteData> findFunctionTargetsByName(Long stepid,
			IssueOperate operate, String tag, Long[] exceptIds, int pageIndex,
			int rows);

	PageInfo<AutoCompleteData> findTellTargetsByName(Long stepid,
			IssueOperate operate, String tag, boolean transferToAdmin,
			Long[] exceptIds, int page, int rows);

	void fireIssueChanged(IssueNew issue, IssueOperate operate,
			IssueLogNew log, List<IssueAttachFile> attachFiles);

	void fireIssueChanged(IssueNew issue, IssueStep step, IssueOperate operate,
			IssueLogNew log, List<IssueAttachFile> attachFiles);

	/**
	 * 根据事件步骤的状态获取对应的 事件列表
	 * 
	 * @return List<IssueViewObject>
	 */
	List<IssueStep> findIssueStepListByIssueState(int[] issueStates);

}

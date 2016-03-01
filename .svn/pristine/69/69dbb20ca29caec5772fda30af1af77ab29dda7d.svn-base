package com.tianque.fourTeams.fourTeamsIssue.dao;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueMap;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;

public interface FourTeamsIssueProcessDao {

	/***
	 * 保存事件的处理步骤
	 * 
	 * @param issueStep
	 *            事件步骤
	 * @return
	 */
	FourTeamsIssueStep addIssueStep(FourTeamsIssueStep issueStep);

	boolean deleteIssueStepsByIssueId(Long issueId);

	/**
	 * 根据id获取事件步骤
	 * 
	 * @param id
	 *            事件步骤id
	 * @return
	 */
	FourTeamsIssueStep getSimpleIssueStepById(Long id);

	FourTeamsIssueStep updateIssueStepExceptOrg(FourTeamsIssueStep step);

	boolean updateGroupId(FourTeamsIssueStep step);

	FourTeamsIssueStepGroup addIssueStepGroup(FourTeamsIssueStepGroup issueStepGroup);

	FourTeamsIssueStepGroup getSimpleIssueStepGroupById(Long id);

	/***
	 * 根据事件id 获取该事件所有的处理步骤组
	 * 
	 * @param issueId
	 * @return
	 */
	List<FourTeamsIssueStepGroup> getIssueStepGroupByIssueId(Long issueId);

	boolean updateOutLog(FourTeamsIssueStepGroup issueStepGroup);

	PageInfo<AutoCompleteData> findChildOrgsByOrgcodeAndNameAndType(
			PropertyDict dict, String orgInternalCode, String tag,
			Long[] exceptIds, int pageSize, int pageIndex);

	PageInfo<AutoCompleteData> findChildOrgsByParentIdAndName(
			PropertyDict dict, Long parentId, String tag, Long[] exceptId,
			int page, int rows);

	PageInfo<AutoCompleteData> findChildFunOrgsByParentFunIdAndName(
			Long parentId, String tag, Long[] exceptIds);

	PageInfo<AutoCompleteData> findParentFunOrgsByIdAndName(Long id,
			String tag, Long[] exceptIds);

	/***
	 * 根据事件IssueStepGroup issuemap
	 * 
	 * @param issueStepGroup
	 *            处理步骤组
	 * @return
	 */
	FourTeamsIssueMap getIssueMapByStepGroup(FourTeamsIssueStepGroup issueStepGroup);

	/**
	 * 根据事件步骤的状态获取对应的 事件列表
	 * 
	 * @return List<IssueViewObject>
	 */
	List<FourTeamsIssueStep> findIssueStepListByIssueState(int[] issueStates);

	/**
	 * 根据步骤ID修改延期时间和延期状态
	 * 
	 * @param id
	 * @param delayState
	 * @param lastEndDate
	 */
	@Deprecated
	void updateDelayStateOrLastEndDateById(Long id, Integer delayState,
			Date lastEndDate);

	/**
	 * 根据步骤ID修改延期时间和延期状态
	 * 
	 * @param id
	 * @param delayState
	 * @param delayDay
	 */
	void updateDelayStateOrDelayDateByIssueStepId(Long issueStepId,
			Integer delayState, Integer delayWorkday);

	Integer getIssueStepCountByIssueId(Long issueId);

}

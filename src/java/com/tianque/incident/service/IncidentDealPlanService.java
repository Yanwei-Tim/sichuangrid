package com.tianque.incident.service;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.incident.domain.IncidentDealPlan;
import com.tianque.incident.domain.IncidentDealTeam;
import com.tianque.incident.domain.IncidentPlanStep;
import com.tianque.incident.domain.IncidentType;

public interface IncidentDealPlanService {

	/**
	 * 对应急事件预案对象的 查询
	 * 
	 * @param incidentDealPlan
	 * @return
	 */
	public IncidentDealPlan findIncidentDealPlan(IncidentDealPlan incidentDealPlan);

	/**
	 * 对应急事件预案小组 对象 中的处置小组 查询
	 * 
	 * @param incidentDealTeam
	 * @return
	 */
	public PageInfo<IncidentDealTeam> findIncidentDealTeamsList(IncidentDealTeam incidentDealTeam,
			Integer pageNum, Integer pageSize);

	/**
	 * 对应急事件预案处置步骤对象的查询
	 * 
	 * @param incidentPlanStep
	 * @return
	 */
	public PageInfo<IncidentPlanStep> findIncidentPlanSteps(IncidentPlanStep incidentPlanStep,
			Integer pageNum, Integer pageSize);

	public List<IncidentType> getIncidentTypeTreeListByPropertyDictId(Long propertyDictId);

	/**
	 * 添加应急事件预案小组 对象
	 * 
	 * @param incidentDealTeam
	 */
	public void addIncidentDealTeam(IncidentDealTeam incidentDealTeam);

	/**
	 * 修改应急事件预案小组 对象
	 * 
	 * @param incidentDealTeam
	 */
	public IncidentDealTeam updateIncidentDealTeam(IncidentDealTeam incidentDealTeam);

	/**
	 * 对应急事件预案小组 对象 中的某个对象查询
	 * 
	 * @param incidentDealTeam
	 * @return
	 */
	public IncidentDealTeam findIncidentDealTeams(IncidentDealTeam incidentDealTeam);

	/**
	 * 删除应急事件预案小组 对象
	 * 
	 * @param idtMap
	 */
	public void deleteIncidentDealTeams(Map<String, Object> idtMap);

	public IncidentPlanStep findMaxSeqIndexIncidentPlanStep(IncidentPlanStep incidentPlanStep);

	public IncidentPlanStep findIncidentPlanStep(IncidentPlanStep incidentPlanStep);

	/**
	 * 修改应急事件预案处置步骤对象
	 * 
	 * @param incidentPlanStep
	 */
	public IncidentPlanStep updateIncidentPlanStep(IncidentPlanStep incidentPlanStep);

	/**
	 * 添加应急事件预案处置步骤对象
	 * 
	 * @param incidentPlanStep
	 */
	public void addIncidentPlanSteps(IncidentPlanStep incidentPlanStep);

	/**
	 * 删除应急事件预案处置步骤对象
	 * 
	 * @param idtMap
	 */
	public void deleteIncidentPlanStep(Map<String, Object> idtMap);

	/**
	 * 修改应急事件预案对象
	 * 
	 * @param incidentDealPlan
	 */
	public IncidentDealPlan updateIncidentDealPlan(IncidentDealPlan incidentDealPlan);

	/**
	 * 添加应急事件预案对象
	 * 
	 * @param incidentDealPlan
	 */
	public void addIncidentDealPlan(IncidentDealPlan incidentDealPlan);

	/**
	 * 删除应急事件预案对象
	 * 
	 * @param incidentDealPlan
	 */
	public void deleteIncidentDealPlan(IncidentDealPlan incidentDealPlan);

	/**
	 * 对 前台上移 ，下移 ，到顶 ，到底 的 IncidentPlanStep 对象的 SeqIndex进行修改
	 * 
	 * @param seqIndexIdListStr
	 */
	public void updateIncidentPlanStepSeqIndex(String seqIndexIdListStr);

	public List<IncidentDealTeam> findIncidentDealTeamsListByIncidentDealPlanId(
			IncidentDealTeam incidentDealTeam);

	public List<IncidentPlanStep> findIncidentPlanStepsByIncidentDealPlanId(
			IncidentPlanStep incidentPlanStep);

	public IncidentDealPlan updateIncidentDealPlanTow(IncidentDealPlan incidentDealPlan);

	public void copyIncidentDealPlanData(IncidentDealPlan incidentDealPlan,
			IncidentDealPlan incidentDealPlanOwn);

}

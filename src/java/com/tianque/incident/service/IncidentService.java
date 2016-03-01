package com.tianque.incident.service;

import java.util.List;

import com.tianque.incident.domain.IncidentStepFeedbacks;
import com.tianque.incident.domain.IncidentSteps;
import com.tianque.incident.domain.IncidentType;
import com.tianque.incident.domain.Incidents;

public interface IncidentService {
	/**
	 * 查找应急事件对象集合
	 * 
	 * @param incidents
	 * @return
	 */
	public List<Incidents> findIncidentsList(Incidents incidents);

	/**
	 * 查找应急事件对象
	 * 
	 * @param incidents
	 * @return
	 */
	public Incidents findIncidents(Incidents incidents);

	/**
	 * 将预警对象转换为处理中对象 并 将 预案定制中的 预案小组和 预案步骤拷贝到 应急事件处理步骤对象中去
	 */
	public void updateIncidentsHandle(Incidents incidents);

	/**
	 * 将预警对象提交的案例库
	 */
	public void updateIncidentsCaseLibrary(Incidents incidents);

	/**
	 * 对应急处置启动处置时添加应急事件处理步骤对象
	 * 
	 * @param incidentSteps
	 */
	public void addIncidentsteps(List<IncidentSteps> incidentStepsList);

	/**
	 * 查找应急事件处理步骤对象集合
	 * 
	 * @param incidentSteps
	 * @return
	 */
	public List<IncidentSteps> findIncidentSteps(IncidentSteps incidentSteps);

	/**
	 * 查找应急事件处理步骤对象
	 * 
	 * @param incidents
	 * @return
	 */
	public IncidentSteps findIncidentStep(IncidentSteps incidentSteps);

	/**
	 * 修改当前的处理步骤将其步骤启动
	 */
	public void updateIncidentsteps(IncidentSteps incidentSteps);

	/**
	 * 对应急处置启动处置时添加应急事件处理步骤对象
	 * 
	 * @param incidentSteps
	 */
	public void addIncidentsteps(IncidentSteps incidentSteps);

	/**
	 * 添加应急事件处置反馈对象
	 * 
	 * @param incidentStepFeedbacks
	 */
	public void addIncidentStepFeedback(IncidentStepFeedbacks incidentStepFeedback);

	/**
	 * 根据 id 事件大类，事件小类，查询案例库中的案例
	 * 
	 * @return incidents
	 */
	public List<Incidents> loadCaseLibraryList(Incidents incidents);

	/**
	 * 根据iD 查找应急事件
	 * 
	 * @param id
	 * @return
	 */
	public Incidents loadCaseLibrary(Long id);

	/**
	 * 获取应急事件处置步骤
	 * 
	 * @param id
	 * @return
	 */
	public List<IncidentSteps> loadloadIncidentSteps(Long id);

	/**
	 * 获取执行预警分类（小类）
	 * 
	 * @param id
	 * @return
	 */
	public List<IncidentType> findPlansSubdivisionBySubjection(Long id);

	/**
	 * 增加应急处置预警案例
	 * 
	 * @param incidents
	 * @return
	 */
	public Incidents addEarlyWarningIncidents(Incidents incidents);

	/**
	 * 修改应急处置预警案例
	 * 
	 * @param incidents
	 * @return
	 */
	public Incidents updateEarlyWarningIncidents(Incidents incidents);

}

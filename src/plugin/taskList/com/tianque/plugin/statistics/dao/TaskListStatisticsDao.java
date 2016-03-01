package com.tianque.plugin.statistics.dao;

import java.util.List;
import java.util.Map;

import com.tianque.domain.Organization;
import com.tianque.plugin.statistics.domain.GeneralSituation;
import com.tianque.plugin.statistics.vo.TaskListMapVo;

public interface TaskListStatisticsDao {

	public List<GeneralSituation>  getTaskListColumn(Map<String,Object> map);
	
	public List<GeneralSituation>  getFloatingColumn(Map<String,Object> map);
	
	public List<GeneralSituation>  getTaskListForSubType(Map<String,Object> map);
	
	public Integer getTaskListForTrend(Map<String,Object> map);
	
	public Integer getMonthCreateSignByCondition(Map<String,Object> map);
	
	public List<TaskListMapVo> getMaxCreatSignOrgByType(Map<String,Object> map);
	
	public List<GeneralSituation> getTaskListColumnForMap(Map<String,Object> map);
	
	public List<GeneralSituation> getTaskListColumnForBasesicTypeMap(Map<String,Object> map);
	
	public List<GeneralSituation> getTaskListColumnForDetailTypeMap(Map<String,Object> map);
	
	public List<GeneralSituation> getTaskListColumnForSubTypeTypeMap(Map<String,Object> map);
	
	public List<GeneralSituation> getTaskListColumnForSubTypesTypeMap(Map<String,Object> map);
}


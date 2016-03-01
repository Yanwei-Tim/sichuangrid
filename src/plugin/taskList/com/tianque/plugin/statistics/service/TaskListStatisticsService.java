package com.tianque.plugin.statistics.service;

import java.util.List;
import java.util.Map;

import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.statistics.vo.TaskListMapVo;
import com.tianque.plugin.statistics.vo.TaskListStatisticsVo;

public interface TaskListStatisticsService {

	/**
	 * 总况(总况、流口总况)柱状图
	 * @param taskListStatisticsVo
	 * @return
	 */
	public HighchartColumnVo getTaskListColumn(TaskListStatisticsVo taskListStatisticsVo);
	/***
	 * 总况(总况、流口总况)饼状图
	 * @param taskListStatisticsVo
	 * @return
	 */
	public List<Object[]> getTaskListPie(TaskListStatisticsVo taskListStatisticsVo);
	
	/***
	 * 任务清单线性图(所有)
	 */
	public HighchartColumnVo getTaskListTrend(TaskListStatisticsVo taskListStatisticsVo);
	/***
	 * 民警工作、异常、隐患柱状图
	 */
	public HighchartColumnVo getTaskListSubTypeOfColumn(TaskListStatisticsVo taskListStatisticsVo);
	
	/***
	 *  民警工作、异常、隐患饼状图
	 */
	public List<Object[]> getTaskListSubTypeOfPie(TaskListStatisticsVo taskListStatisticsVo);
	
	/***
	 * 地图显示-根据条件得到当前月份签收数据 同比差值比例和环比差值比例
	 * 
	 */
	public TaskListMapVo getTaskListForMap(TaskListStatisticsVo taskListStatisticsVo);
	/***
	 * 根据组织机构查询签收数排名前三的组织
	 */
	public List<TaskListMapVo> getMaxCreatSignOrgByType(TaskListStatisticsVo taskListStatisticsVo);
	/***
	 * 地图饼图查询
	 */
	public List<Object[]> getTaskListPieForMap(TaskListStatisticsVo taskListStatisticsVo);
}

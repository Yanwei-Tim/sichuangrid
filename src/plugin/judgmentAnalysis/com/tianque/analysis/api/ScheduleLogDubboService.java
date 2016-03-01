package com.tianque.analysis.api;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.ScheduleLog;

public interface ScheduleLogDubboService {

	public ScheduleLog getScheduleLogById(Long id);
	public ScheduleLog addScheduleLog(ScheduleLog scheduleLog);
	public ScheduleLog updateScheduleLog(ScheduleLog scheduleLog);
	public int deleteScheduleLogByIds(List<Long> ids);
	public PageInfo<ScheduleLog> findScheduleLogForPage(int pageNum, int pageSize, 
            String sortField, String order,ScheduleLog scheduleLog);
}

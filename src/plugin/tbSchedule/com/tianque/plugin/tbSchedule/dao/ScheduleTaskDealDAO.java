package com.tianque.plugin.tbSchedule.dao;

import java.util.List;

import com.tianque.core.vo.GridPage;
import com.tianque.plugin.tbSchedule.domain.IdCardNoExpLog;
import com.tianque.plugin.tbSchedule.domain.TBScheduleLog;
import com.tianque.plugin.tbSchedule.domain.TBScheduleLogException;

/**
 * @ClassName: ScheduleTaskDealDAO
 * @Description: 淘宝任务调度DAO
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年11月14日 下午4:38:46
 */
public interface ScheduleTaskDealDAO {

	Long saveTBScheduleLog(TBScheduleLog tbScheduleLog);

	void addTBScheduleLogForException(
			TBScheduleLogException tbScheduleLogException);

	void updateTBScheduleLogIsEnd(TBScheduleLog tbScheduleLog);

	GridPage queryTBScheduleLogForPage(TBScheduleLog tbSchedule, Integer page,
			Integer rows, String sidx, String sord);

	TBScheduleLog getFullTBScheduleLogForView(Long id);

	void updateTBScheduleLogIsException(TBScheduleLog tbScheduleLog);

	void addIdCardNoExpLog(IdCardNoExpLog idCardNoExpLog);

	boolean checkExsitForIdCardNo(String idCardNo);

	GridPage queryIdCardNoExpLogForPage(IdCardNoExpLog idCardNoExpLog,
			Integer page, Integer rows, String sidx, String sord);

	List<IdCardNoExpLog> queryIdCardNoExpLogForPage(
			IdCardNoExpLog idCardNoExpLog, String sidx, String sord);

	/**
	 * @param tbSchedule
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	GridPage queryExceptionForPage(Long logId, Integer page, Integer rows,
			String sidx, String sord);

}

package com.tianque.job.optmize;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taobao.pamirs.schedule.TaskItemDefine;
import com.tianque.plugin.tbSchedule.IScheduleTaskDealSingleBase;
import com.tianque.plugin.tbSchedule.constant.ScheduleNameConstant;
import com.tianque.xichang.working.statisticsAccountTimeout.service.StatisticsAccountTimeoutsJobHelper;

/***
 * @Description:三本台账时限考核成绩job（分成十组）
 * @author zhangyouwei@hztianque.com
 * @date: 2014-11-17 下午02:57:43
 */
@Component("statisticsAccountTimeoutsScoreJob")
public class StatisticsAccountTimeoutsScoreJob extends
		IScheduleTaskDealSingleBase<Long> {
	private static Logger logger = LoggerFactory
			.getLogger(StatisticsAccountTimeoutsScoreJob.class);
	@Autowired
	private StatisticsAccountTimeoutsJobHelper statisticsAccountTimeoutsJobHelper;

	@Override
	public Comparator<Long> getComparator() {
		return new Comparator<Long>() {

			@Override
			public int compare(Long o1, Long o2) {
				return o1.compareTo(o2);
			}
		};
	}

	@Override
	public List<Long> queryTasks(String taskParameter, String ownSign,
			int taskItemNum, List<TaskItemDefine> taskItemList, int fetchNum)
			throws Exception {
		List<Long> idModList = new ArrayList<Long>();
		//String str = "";
		for (int i = 0; i < taskItemList.size(); i++) {
			String taskItemId = ((TaskItemDefine) taskItemList.get(i))
					.getTaskItemId();
			if (taskItemId != null && !"".equals(taskItemId.trim())) {
				//str += taskItemId.trim() + ",";
				idModList.add(Long.parseLong(taskItemId.trim()));
			}
		}
		long start = System.currentTimeMillis();
		size.set(statisticsAccountTimeoutsJobHelper.executeDataByModel(
				idModList, taskParameter));

		long end = System.currentTimeMillis();

		logger.error("任务参数：" + taskParameter + "  耗时：" + (end - start));
		return new ArrayList<Long>();
	}

	@Override
	public boolean execute(Long orgId, String ownSign) throws Exception {
		return true;
	}

	@Override
	public void callBack() {
	}

	@Override
	public String getJobRemark() {
		return ScheduleNameConstant.STATISTICS_ACCOUNT_TIMEOUTS_SCORE;
	}

}

package com.tianque.job.optmize;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taobao.pamirs.schedule.TaskItemDefine;
import com.tianque.core.util.CalendarUtil;
import com.tianque.plugin.tbSchedule.IScheduleTaskDealSingleBase;
import com.tianque.plugin.tbSchedule.constant.ScheduleNameConstant;
import com.tianque.xichang.working.report.service.AccountReportJobOptmizeHelper;

@Component("accountReportVillageJob")
public class AccountReportVillageJob extends IScheduleTaskDealSingleBase<Long> {
	private static Logger logger = LoggerFactory
			.getLogger(AccountReportVillageJob.class);
	@Autowired
	private AccountReportJobOptmizeHelper accountReportJobOptmizeHelper;

	@Override
	public boolean execute(Long villageOrgId, String ownSign) throws Exception {
		return true;
	}

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
		// String str = "";
		int year = CalendarUtil.getNowYear();
		int month = CalendarUtil.getNowMonth();
		for (int i = 0; i < taskItemList.size(); i++) {
			String taskItemId = ((TaskItemDefine) taskItemList.get(i))
					.getTaskItemId();
			if (taskItemId != null && !"".equals(taskItemId.trim())) {
				// str += taskItemId.trim() + ",";
				idModList.add(Long.parseLong(taskItemId.trim()));
			}
		}
		long start = System.currentTimeMillis();
		size.set(accountReportJobOptmizeHelper
				.executeDataByModelForAccountReportVillage(year, month,
						idModList, taskParameter));
		long end = System.currentTimeMillis();

		logger.error("任务参数：" + taskParameter + "耗时：" + (end - start));
		return new ArrayList<Long>();
	}

	@Override
	public void callBack() {
	}

	@Override
	public String getJobRemark() {
		return ScheduleNameConstant.ACCOUNT_REPORT_VILLAGE;
	}

}

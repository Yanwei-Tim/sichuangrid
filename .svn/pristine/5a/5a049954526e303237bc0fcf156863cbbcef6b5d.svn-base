package com.tianque.task.schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.SpringBeanUtil;
import com.tianque.task.domain.Task;
import com.tianque.task.domain.TaskPloy;
import com.tianque.task.schedule.job.TaskJob;

/**
 * 调度运行类
 * 
 * @author 王乐 2013-06-08 11:21:00
 * @version 1.0.0
 * */
public class SchedulerMain {
	public static Scheduler scheduler = null;
	private static Logger logger = LoggerFactory.getLogger(SchedulerMain.class);
	private static SchedulerMain sm = new SchedulerMain();

	private SchedulerMain() {
		logger.info("SchedulerMain is created");
	};

	public static SchedulerMain getInstance() {
		return sm;
	}

	/**
	 * 任务开启全局主函数
	 * 
	 * @param args
	 * @throws Exception
	 */
	public SchedulerMain run() throws Exception {
		if (scheduler == null) {
			scheduler = (StdScheduler) SpringBeanUtil
					.getBeanFromSpringByBeanName("schedulerFactoryBean");
		}
		if (scheduler.isShutdown() || !scheduler.isStarted()) {
			scheduleClean();
		}
		return sm;
	}

	public void addScheduleJob(Task task, TaskPloy taskPloy) {
		try {
			JobDetail job = new JobDetail(task.getName(), task.getTaskGroup(),
					TaskJob.class);

			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("task", task);
			jobDataMap.put("taskPloy", taskPloy);
			job.setJobDataMap(jobDataMap);

			job.setDurability(false);
			CronExpression cronExpression = new CronExpression(task.getConfig());
			CronTrigger cronTrigger = new CronTrigger();
			cronTrigger.setCronExpression(cronExpression);
			cronTrigger.setName(task.getName());
			cronTrigger.setGroup(task.getTaskGroup());
			scheduler.scheduleJob(job, cronTrigger);
			scheduler.start();

			// Trigger trigger = null;
			// PropertyDict frequency = task.getFrequency();
			// Date startDate = task.getStartDate();
			// int month = getSingleValueOfDate(startDate, MONTH);
			// int day = getSingleValueOfDate(startDate, DAY);
			// int hour = getSingleValueOfDate(startDate, HOUR);
			// int minute = getSingleValueOfDate(startDate, MINUTE);
			// int second = getSingleValueOfDate(startDate, SECOND);
			// if (frequency != null) {
			// if ("每年".equals(frequency.getDisplayName())) {
			// try {
			// String express = second + " " + minute + " " + hour + " "
			// + day + " " + (month - 1) + " ? ";
			// trigger = new CronTrigger(task.getName(), task
			// .getTaskGroup(), express);
			// } catch (ParseException e) {
			// e.printStackTrace();
			// }
			// } else if ("每月".equals(frequency.getDisplayName())) {
			// trigger = TriggerUtils.makeMonthlyTrigger(task.getInterval(),
			// hour, minute);
			// } else if ("每周".equals(frequency.getDisplayName())) {
			// trigger = TriggerUtils.makeWeeklyTrigger(task.getInterval(),
			// hour, minute);
			// } else if ("每天".equals(frequency.getDisplayName())) {
			// trigger = TriggerUtils.makeDailyTrigger(hour, minute);
			// } else if ("每时".equals(frequency.getDisplayName())) {
			// trigger = TriggerUtils.makeHourlyTrigger(task.getInterval());
			// } else if ("每分".equals(frequency.getDisplayName())) {
			// trigger = TriggerUtils.makeMinutelyTrigger(task.getInterval());
			// } else {
			// trigger = TriggerUtils.makeSecondlyTrigger(task.getInterval());
			// }
			// } else {
			// trigger = TriggerUtils.makeSecondlyTrigger(task.getInterval());
			// }
			// trigger.setName(task.getName());
			// trigger.setGroup(task.getTaskGroup());
			//
			// trigger.setStartTime(task.getStartDate());
			// trigger.setEndTime(task.getEndDate());
			//
			// scheduler.scheduleJob(job, trigger);
			// scheduleStart();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scheduleClean() throws SchedulerException {
		String[] gns = scheduler.getTriggerGroupNames();
		for (int i = 0; i < gns.length; i++) {
			String[] names = scheduler.getTriggerNames(gns[i]);
			for (int j = 0; j < names.length; j++) {
				scheduler.unscheduleJob(names[j], gns[i]);
			}
		}
		gns = scheduler.getJobGroupNames();
		for (int i = 0; i < gns.length; i++) {
			String[] names = scheduler.getJobNames(gns[i]);
			for (int j = 0; j < names.length; j++) {
				scheduler.deleteJob(names[j], gns[i]);
			}
		}
	}

	public void taskClean(Task task) throws SchedulerException {
		String name = task.getName();
		String group = task.getTaskGroup();
		scheduler.unscheduleJob(name, group);
		scheduler.deleteJob(name, group);
	}

	public void taskStop(Task task) throws SchedulerException {
		String name = task.getName();
		String group = task.getTaskGroup();
		scheduler.unscheduleJob(name, group);
	}

	public void taskStart(Task task, TaskPloy taskPloy)
			throws SchedulerException {
		String name = task.getName();
		String group = task.getTaskGroup();
		Trigger trigger = scheduler.getTrigger(name, group);
		JobDetail job = scheduler.getJobDetail(name, group);
		taskClean(task);
		if (trigger != null && job != null) {
			scheduler.scheduleJob(job, trigger);
		} else {
			addScheduleJob(task, taskPloy);
		}
	}

	public void scheduleStart() throws SchedulerException {
		if (scheduler == null || scheduler.isStarted()) {
			return;
		}
		scheduler.start();
	}

	public void scheduleStop() throws SchedulerException {
		if (scheduler == null || scheduler.isShutdown()) {
			return;
		}
		scheduler.shutdown();
	}

	public int getSingleValueOfDate(Date date, int index) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Integer> result = new ArrayList<Integer>(6);
		String str = sdf.format(date);
		String[] dateTime = str.split("\\s");
		if (dateTime.length == 2) {
			String[] dateStr = dateTime[0].split("-");
			String[] time = dateTime[1].split(":");
			for (String s : dateStr) {
				result.add(Integer.parseInt(s));
			}
			for (String s : time) {
				result.add(Integer.parseInt(s));
			}
		}
		return result.get(index);
	}
}

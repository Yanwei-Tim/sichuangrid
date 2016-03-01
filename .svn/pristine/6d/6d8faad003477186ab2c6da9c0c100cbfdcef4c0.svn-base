package com.tianque.plugin.loader;

import java.text.ParseException;
import java.util.List;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.SpringBeanUtil;
import com.tianque.plugin.AbstractJobPlugin;

@Component("jobPluginLoader")
public class JobPluginLoader extends AbastractPluginLoader {

	private static final String TRIGGER_GROUP_NAME = "triggerGroup";
	private static final String JOB_GROUP_NAME = "jobGroup";

	private static Logger logger = LoggerFactory
			.getLogger(JobPluginLoader.class);

	@Autowired(required = false)
	private List<AbstractJobPlugin> jobPlugins;

	@Override
	public void loader() {
		try {
			StdScheduler stdScheduler = (StdScheduler) SpringBeanUtil
					.getBeanFromSpringByBeanName("schedulerFactoryBean");
			if (null == jobPlugins) {
				return;
			}
			for (AbstractJobPlugin jobPlugin : jobPlugins) {
				loadJob(stdScheduler, jobPlugin);
			}
		} catch (Exception e) {
			logger.error("job plugin init:", e);
		}
	}

	private void loadJob(StdScheduler stdScheduler, AbstractJobPlugin jobPlugin)
			throws SchedulerException, ParseException {
		CronTrigger trigger = (CronTrigger) stdScheduler.getTrigger(
				jobPlugin.getName(), TRIGGER_GROUP_NAME);
		if (trigger == null) {
			addJob(stdScheduler, jobPlugin);
		} else {
			updateJob(stdScheduler, jobPlugin, trigger);
		}

	}

	private void updateJob(StdScheduler stdScheduler,
			AbstractJobPlugin jobPlugin, CronTrigger trigger)
			throws ParseException, SchedulerException {
		trigger.setCronExpression(jobPlugin.getCronExpression());
		stdScheduler.rescheduleJob(jobPlugin.getName(), TRIGGER_GROUP_NAME,
				trigger);
		JobDetail jobDetail = stdScheduler.getJobDetail(jobPlugin.getName(),
				JOB_GROUP_NAME);
		jobDetail.setJobClass(jobPlugin.getClass());
		stdScheduler.addJob(jobDetail, true);
	}

	private void addJob(StdScheduler stdScheduler, AbstractJobPlugin jobPlugin)
			throws ParseException, SchedulerException {
		JobDetail jobDetail = new JobDetail(jobPlugin.getName(),
				JOB_GROUP_NAME, jobPlugin.getClass());
		CronExpression cexp = new CronExpression(jobPlugin.getCronExpression());
		CronTrigger trigger = new CronTrigger();
		trigger.setCronExpression(cexp);
		trigger.setName(jobPlugin.getName());
		trigger.setGroup(TRIGGER_GROUP_NAME);
		stdScheduler.deleteJob(jobPlugin.getName(), JOB_GROUP_NAME);
		stdScheduler.scheduleJob(jobDetail, trigger);
	}
}

package com.tianque.task.schedule.job;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.job.JobHelper;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.task.dao.SqlHelper;
import com.tianque.task.domain.TaskPloy;

@Component("taskJob")
public class TaskJob implements Job {

	private static SqlHelper sh;
	private static Logger logger;
	static {
		logger = LoggerFactory.getLogger(TaskJob.class);
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Object taskPloyObj = arg0.getJobDetail().getJobDataMap()
				.get("taskPloy");
		TaskPloy taskPloy = null;
		if (taskPloyObj != null && taskPloyObj instanceof TaskPloy) {
			taskPloy = (TaskPloy) taskPloyObj;
			executePloy(taskPloy);
		} else {
			logger.error("策略为空");
		}
	}

	private void executePloy(TaskPloy taskPloy) {
		// JobMonitorService jobMonitorService = (JobMonitorService)
		// SpringBeanUtil
		// .getBeanFromSpringByBeanName("jobMonitorService");
		PermissionService permissionService = (PermissionService) SpringBeanUtil
				.getBeanFromSpringByBeanName("permissionService");
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(permissionService.findUserByUserName("admin"));
		// 这里的参数传的是策略的英文名称
		// Long id = jobMonitorService.addJobMonitor(taskPloy.getEname());
		try {
			// long startTime = System.currentTimeMillis();
			Object succMessage = null;
			// String succStr = "";
			String type = taskPloy.getType().getDisplayName();
			String code = taskPloy.getCode();
			logger.info("策略类型:" + type);
			logger.info("策略代码:" + code);
			if ("存储过程".equals(type)) {// 执行存储过程
				sh.procExecute(code);
			} else if ("函数".equals(type)) {// 执行函数
				sh.funExecute(code);
			} else if ("sql语句".equals(type)) {// 执行sql语句
				sh.execute(code);
			} else { // 执行java方法
				String domain = code.substring(0, code.lastIndexOf("."));
				String method = code.substring(code.lastIndexOf(".") + 1);
				Object clazz = SpringBeanUtil
						.getBeanFromSpringByBeanName(domain);
				Method m = clazz.getClass().getMethod(method);
				succMessage = m.invoke(clazz);
			}
			// if (succMessage != null && succMessage instanceof String) {
			// succStr = ", " + (String) succMessage;
			// }
			// jobMonitorService.updateJobMonitor(id,
			// "花了" + (System.currentTimeMillis() - startTime) + "执行"
			// + taskPloy.getEname() + succStr, true);
		} catch (InvocationTargetException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.getTargetException().printStackTrace(pw);
			String st = sw.toString();
			// jobMonitorService.updateJobMonitor(id, taskPloy.getEname()
			// + "出现异常：\n" + st, false);
			logger.error("Job执行失败", e);
		} catch (Exception e) {
			// jobMonitorService.updateJobMonitor(id, taskPloy.getEname()
			// + "出现异常：" + e.getMessage(), false);
			logger.error("Job执行失败", e);
		}
	}
}

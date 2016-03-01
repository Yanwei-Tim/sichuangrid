package com.tianque.job.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tianque.core.util.GridProperties;
import com.tianque.job.JobHelper;
import com.tianque.task.service.TaskService;

@Component("jobListener")
public class JobListener implements ServletContextListener {

	private TaskService taskService;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		JobHelper.createMockAdminSession();

		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		if (GridProperties.IS_JOBSERVER) {
			taskService = (TaskService) ac.getBean("taskService");
			taskService.afterStartRunTask();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

}

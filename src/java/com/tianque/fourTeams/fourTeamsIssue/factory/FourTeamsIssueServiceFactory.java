package com.tianque.fourTeams.fourTeamsIssue.factory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.tianque.core.util.StringUtil;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueService;

@Component("fourTeamsIssueServiceFactory")
public class FourTeamsIssueServiceFactory implements ApplicationContextAware {

	public static final String DEFAULT_SERIES = "Standard";

	private final String DEFAULT_SERVICE = "fourTeamsDefaultIssueService";
	private final String SERVICE_SUFIX = "IssueService";

	private ApplicationContext appContext;

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.appContext = context;
	}

	public FourTeamsIssueService getIssueServiceBySeries(String series) {
		FourTeamsIssueService result = null;
		if (!StringUtil.isStringAvaliable(series)
				|| DEFAULT_SERIES.equalsIgnoreCase(series)) {
			result = (FourTeamsIssueService) appContext
					.getBean(DEFAULT_SERVICE);
		} else {
			result = (FourTeamsIssueService) appContext.getBean(series
					+ SERVICE_SUFIX);
		}
		return result;
	}

}

package com.tianque.issue.factory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.tianque.core.util.StringUtil;
import com.tianque.issue.service.IssueService;

@Component("issueServiceFactory")
public class IssueServiceFactory implements ApplicationContextAware {

	public static final String DEFAULT_SERIES = "Standard";

	private final String DEFAULT_SERVICE = "defaultIssueService";
	private final String SERVICE_SUFIX = "IssueService";

	private ApplicationContext appContext;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.appContext = context;
	}

	public IssueService getIssueServiceBySeries(String series) {
		IssueService result = null;
		if (!StringUtil.isStringAvaliable(series) || DEFAULT_SERIES.equalsIgnoreCase(series)) {
			result = (IssueService) appContext.getBean(DEFAULT_SERVICE);
		} else {
			result = (IssueService) appContext.getBean(series + SERVICE_SUFIX);
		}
		return result;
	}

}

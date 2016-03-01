package com.tianque.issue.factory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.tianque.core.util.StringUtil;
import com.tianque.issue.controller.strategy.IssueManageStrategy;
import com.tianque.issue.validator.IssueOperationLogValidator;
import com.tianque.issue.validator.IssueValidator;

@Service("issueStrategyFactory")
public class IssueManageStrategyFactory implements ApplicationContextAware {

	private final String DEFAULT_STRATEGY = "defaultIssueManageStrategy";

	private final String STRATEGY_SUFIX = "IssueManageStrategy";

	private final String DEFAULT_ISSUE_VALIDATOR = "defaultIssueValidator";

	private final String ISSUE_VALIDATOR_SUFIX = "IssueValidator";

	private final String DEFAULT_ISSUE_LOG_VALIDATOR = "defaultIssueLogValidator";

	private ApplicationContext appContext;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.appContext = context;
	}

	public IssueManageStrategy getIssueManageStrategyByModule(String module) {
		IssueManageStrategy result = null;
		if (!StringUtil.isStringAvaliable(module)) {
			result = (IssueManageStrategy) appContext.getBean(DEFAULT_STRATEGY);
		} else {
			result = (IssueManageStrategy) appContext.getBean(module + STRATEGY_SUFIX);
		}
		if (result != null) {
			result.setValidator(getIssueValidatorByModule(module));
			result.setLogValidator(getIssueLogValidatorByModule(module));
		}
		return result;
	}

	private IssueValidator getIssueValidatorByModule(String module) {
		if (!StringUtil.isStringAvaliable(module)) {
			return (IssueValidator) appContext.getBean(DEFAULT_ISSUE_VALIDATOR);
		} else {
			return (IssueValidator) appContext.getBean(module + ISSUE_VALIDATOR_SUFIX);
		}
	}

	private IssueOperationLogValidator getIssueLogValidatorByModule(String module) {
		return (IssueOperationLogValidator) appContext.getBean(DEFAULT_ISSUE_LOG_VALIDATOR);
	}

}

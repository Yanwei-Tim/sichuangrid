package com.tianque.fourTeams.fourTeamsIssue.factory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.tianque.core.util.StringUtil;
import com.tianque.fourTeams.fourTeamsIssue.controller.strategy.FourTeamsIssueManageStrategy;
import com.tianque.fourTeams.fourTeamsIssue.validator.FourTeamsIssueOperationLogValidator;
import com.tianque.fourTeams.fourTeamsIssue.validator.FourTeamsIssueValidator;

@Service("fourTeamsIssueStrategyFactory")
public class FourTeamsIssueManageStrategyFactory implements
		ApplicationContextAware {

	private final String DEFAULT_STRATEGY = "fourTeamsDefaultIssueManageStrategy";

	private final String STRATEGY_SUFIX = "fourTeamsDefaultIssueManageStrategy";

	private final String DEFAULT_ISSUE_VALIDATOR = "defaultFourTeamsIssueValidator";

	private final String ISSUE_VALIDATOR_SUFIX = "IssueValidator";

	private final String DEFAULT_ISSUE_LOG_VALIDATOR = "fourTeamsDefaultIssueLogValidator";

	private ApplicationContext appContext;

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.appContext = context;
	}

	public FourTeamsIssueManageStrategy getIssueManageStrategyByModule(String module) {
		FourTeamsIssueManageStrategy result = null;
		if (!StringUtil.isStringAvaliable(module)) {
			result = (FourTeamsIssueManageStrategy) appContext.getBean(DEFAULT_STRATEGY);
		} else {
			result = (FourTeamsIssueManageStrategy) appContext.getBean(module
					+ STRATEGY_SUFIX);
		}
		if (result != null) {
			result.setValidator(getIssueValidatorByModule(module));
			result.setLogValidator(getIssueLogValidatorByModule(module));
		}
		return result;
	}

	private FourTeamsIssueValidator getIssueValidatorByModule(String module) {
		if (!StringUtil.isStringAvaliable(module)) {
			return (FourTeamsIssueValidator) appContext
					.getBean(DEFAULT_ISSUE_VALIDATOR);
		} else {
			return (FourTeamsIssueValidator) appContext.getBean(module
					+ ISSUE_VALIDATOR_SUFIX);
		}
	}

	private FourTeamsIssueOperationLogValidator getIssueLogValidatorByModule(
			String module) {
		return (FourTeamsIssueOperationLogValidator) appContext
				.getBean(DEFAULT_ISSUE_LOG_VALIDATOR);
	}

}

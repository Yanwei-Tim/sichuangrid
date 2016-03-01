package com.tianque.plugin.account.factory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.tianque.account.validate.ThreeRecordsIssueOperationLogValidator;
import com.tianque.core.util.StringUtil;
import com.tianque.plugin.account.strategy.ThreeRecordsIssueManageStrategy;

@Service("threeRecordsIssueStrategyFactory")
public class ThreeRecordsIssueManageStrategyFactory implements
		ApplicationContextAware {

	private final String DEFAULT_STRATEGY = "threeRecordsDefaultIssueManageStrategy";

	private final String STRATEGY_SUFIX = "threeRecordsDefaultIssueManageStrategy";

	private final String DEFAULT_ISSUE_LOG_VALIDATOR = "threeRecordsDefaultIssueLogValidator";

	private ApplicationContext appContext;

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.appContext = context;
	}

	public ThreeRecordsIssueManageStrategy getIssueManageStrategyByModule(
			String module) {
		ThreeRecordsIssueManageStrategy result = null;
		if (!StringUtil.isStringAvaliable(module)) {
			result = (ThreeRecordsIssueManageStrategy) appContext
					.getBean(DEFAULT_STRATEGY);
		} else {
			result = (ThreeRecordsIssueManageStrategy) appContext
					.getBean(module + STRATEGY_SUFIX);
		}
		if (result != null) {
			result.setLogValidator(getIssueLogValidatorByModule(module));
		}
		return result;
	}

	private ThreeRecordsIssueOperationLogValidator getIssueLogValidatorByModule(
			String module) {
		return (ThreeRecordsIssueOperationLogValidator) appContext
				.getBean(DEFAULT_ISSUE_LOG_VALIDATOR);
	}

}

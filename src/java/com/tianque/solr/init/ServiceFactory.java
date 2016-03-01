package com.tianque.solr.init;

import com.tianque.core.util.GlobalValue;
import com.tianque.init.ContextType;
import com.tianque.util.ApplicationContextFactory;

public class ServiceFactory {
	public static Object getService(String beanName) {
		return ApplicationContextFactory.getInstance().getBean(
				Enum.valueOf(ContextType.class, GlobalValue.environment), beanName);
	}
}

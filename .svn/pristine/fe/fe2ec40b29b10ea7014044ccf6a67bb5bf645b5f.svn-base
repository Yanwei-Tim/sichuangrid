package com.tianque.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tianque.init.ContextType;

public final class ApplicationContextFactory {
	private static ApplicationContextFactory instance;

	private Map<ContextType, ApplicationContext> contexts = new HashMap<ContextType, ApplicationContext>();

	private ApplicationContextFactory() {

	}

	public static ApplicationContextFactory getInstance() {
		if (instance == null) {
			instance = new ApplicationContextFactory();
		}
		return instance;
	}

	public ApplicationContext getApplicationContext(ContextType type) {
		ApplicationContext result = contexts.get(type);
		if (result == null) {
			result = new ClassPathXmlApplicationContext(new String[] {
					"applicationContext-" + type + ".xml", "applicationContext.xml" });
			contexts.put(type, result);
		}
		return result;
	}

	public void close(ContextType type) {
		ClassPathXmlApplicationContext result = (ClassPathXmlApplicationContext) contexts.get(type);
		if (null != result) {
			result.close();
		}
	}

	public Object getBean(ContextType type, String beanName) {
		return getApplicationContext(type).getBean(beanName);
	}
}

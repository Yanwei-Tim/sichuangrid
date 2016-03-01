package com.tianque.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IssueStateFactoryNew {
	private static Logger logger = LoggerFactory.getLogger(IssueStateFactoryNew.class);

	public synchronized static IssueStateNew createIssueStateInstance(String className) {
		try {
			return (IssueStateNew) Class.forName(className).newInstance();
		} catch (ClassNotFoundException e) {
			logger.error("异常信息", e);
		} catch (InstantiationException e) {
			logger.error("异常信息", e);
		} catch (IllegalAccessException e) {
			logger.error("异常信息", e);
		}
		return null;
	}
}

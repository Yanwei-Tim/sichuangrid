package com.tianque.fourTeams.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FourTeamsIssueStateFactoryNew {
	private static Logger logger = LoggerFactory.getLogger(FourTeamsIssueStateFactoryNew.class);

	public synchronized static FourTeamsIssueStateNew createIssueStateInstance(String className) {
		try {
			return (FourTeamsIssueStateNew) Class.forName(className).newInstance();
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

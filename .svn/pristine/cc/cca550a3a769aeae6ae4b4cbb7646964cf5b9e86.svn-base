package com.tianque.job.persistenceJob;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.userAuth.api.SessionManagerDubboService;

/**
 * @Description:session超时job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-18 下午03:16:34
 */
@Component("sessionTimeOutDispatch")
public class SessionTimeOutDispatch implements Serializable {
	private static Logger logger = LoggerFactory
			.getLogger(SessionTimeOutDispatch.class);
	@Autowired
	private SessionManagerDubboService sessionManagerDubboService;

	public void deleteTimeOutSessions() {
		try {
			JobHelper.createMockAdminSession();
			sessionManagerDubboService.deleteSessionsWhenTimeOut();
		} catch (Exception e) {
			logger.error("SessionTimeOut job出错", e);
		}
	}

}

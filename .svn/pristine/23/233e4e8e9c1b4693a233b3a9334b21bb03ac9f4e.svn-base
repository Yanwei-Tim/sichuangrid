package com.tianque.plugin.orgchange.handler;

import com.tianque.plugin.orgchange.domain.OrgChangeLog;

/**
 * 组织机构执行异常
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月29日
 */
public class OrgChangeHandlerException extends RuntimeException {
	private OrgChangeLog log;

	public OrgChangeHandlerException(OrgChangeLog log) {
		super(log.getDescription());
		this.log = log;
	}

	public OrgChangeHandlerException(OrgChangeLog log, String message) {
		super(message);
		this.log = log;
	}

	public OrgChangeLog getLog() {
		return log;
	}

	public void setLog(OrgChangeLog log) {
		this.log = log;
	}

}

package com.tianque.sms.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SMSConfig implements Serializable {

	public int getSmsLoaderCount() {
		return smsLoaderCount;
	}

	public void setSmsLoaderCount(int smsLoaderCount) {
		this.smsLoaderCount = smsLoaderCount;
	}

	/**
	 * 一次从数据库获取短信并进入队列条数
	 */
	private int smsLoaderCount = 300;

}

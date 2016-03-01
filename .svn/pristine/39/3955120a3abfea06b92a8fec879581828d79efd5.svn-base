package com.tianque.plugin.transfer.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.plugin.transfer.util.Context;

public abstract class Handler {
	public final static Logger logger = LoggerFactory.getLogger(Handler.class);
	/**
	 * 持有下一个处理请求的对象
	 */
	protected Handler successor = null;

	/**
	 * 处理迁移的方法
	 */
	public abstract void invoke(String type, Long id, Long toOrgId,
			Context context);

	/**
	 * 处理迁移的方法
	 */
	public abstract void invoke(String type, Long id, Long toOrgId,
			Long fromOrgId, Context context);

	/**
	 * 验证迁移是否可行的方法
	 */
	public abstract void validate(String type, Long id, Long toOrgId,
			Context context);

	/**
	 * 验证迁移是否可行的方法
	 */
	public abstract void validate(String type, Long id, Long toOrgId,
			Long fromOrgId, Context context);

	/**
	 * 取值方法
	 */
	public Handler getSuccessor() {
		return successor;
	}

	/**
	 * 设置下一个处理请求的对象
	 */
	public void setSuccessor(Handler successor) {
		this.successor = successor;
	}

}
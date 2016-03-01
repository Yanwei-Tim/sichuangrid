package com.tianque.jms.msg;

import com.tianque.cache.PageInfoCacheThreadLocal;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.jms.constants.JmsMessageType;

/**
 * @ClassName: RedisCacheMsg
 * @Description: redis缓存jsm消息模板
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2015年1月20日 下午3:46:42
 */
public class RedisCacheMsg extends BaseMsg {
	private PageInfoCacheThreadLocal pageInfoCacheThreadLocal;

	public RedisCacheMsg(PageInfoCacheThreadLocal pageInfoCacheThreadLocal) {
		this.pageInfoCacheThreadLocal = pageInfoCacheThreadLocal;
		setObjectType(MemCacheConstant.getCachePageKey(pageInfoCacheThreadLocal
				.getBaseDomain().getClass().getSimpleName()));
		setOrgId(pageInfoCacheThreadLocal.getOrgId());
		setJmsMessageType(JmsMessageType.POPULATION_JMS_TYPE);
	}

	public PageInfoCacheThreadLocal getPageInfoCacheThreadLocal() {
		return pageInfoCacheThreadLocal;
	}

	public void setPageInfoCacheThreadLocal(
			PageInfoCacheThreadLocal pageInfoCacheThreadLocal) {
		this.pageInfoCacheThreadLocal = pageInfoCacheThreadLocal;
	}
}

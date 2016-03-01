package com.tianque.init.impl;

import com.tianque.core.cache.util.MemCachedManage;
import com.tianque.init.Initialization;

public class DestoryCacheConnection implements Initialization {
	private MemCachedManage cacheClient;

	public DestoryCacheConnection(MemCachedManage cacheClient) {
		this.cacheClient = cacheClient;
	}

	@Override
	public void init() throws Exception {
		// cacheClient.shutdown();
	}

}

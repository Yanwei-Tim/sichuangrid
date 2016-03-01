package com.tianque.dbRouterManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.cache.service.CacheService;
import com.tianque.dbrouter.RouterManager;
import com.tianque.dbrouter.factory.RouterManagerBeanFactory;

/**
 * 
 * @author 龙振东
 * @功能：JDBC路由层级初始化
 */
public class InitDevelopmentRouterManager {
	@Autowired
	private CacheService cacheService;

	static {
		System.out.println("开始加载JDBC路由层");
		RouterManager routerManager = RouterManagerBeanFactory.getManager();
		routerManager.addRouters("/dbRouter-development.xml");
		System.out.println("加载JDBC路由层完成");
	}

	private void init() {
		RouterManagerBeanFactory.getManager().setCacheService(cacheService);
	}
}

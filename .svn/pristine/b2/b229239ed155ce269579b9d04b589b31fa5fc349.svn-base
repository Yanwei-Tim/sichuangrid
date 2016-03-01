package com.tianque.sysadmin.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.cache.service.CacheService;

/**
 * 
 * @ClassName: RefreshMobileCacheController
 * @Description: TODO刷新手机代理缓存 action
 * @author wanggz
 * @date 2014-7-25 下午03:30:18
 */

@Transactional
@Scope("prototype")
@Controller("refreshMobileCacheController")
@Namespace("/sysadmin/refreshMobileCacheManage")
public class RefreshMobileCacheController extends BaseAction {

	@Autowired
	private CacheService cacheService;

	public static String nameSpace = "MOBILECACHEPAPERS";

	/**
	 * 
	 * @Title: refreshMobileCache
	 * @Description: TODO刷新手机代理缓存方法
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-7-25 下午03:30:36
	 */
	@Action(value = "refreshMobileCache", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String refreshMobileCache() throws Exception {
		cacheService
				.invalidateNamespaceCache(RefreshMobileCacheController.nameSpace);
		return SUCCESS;
	}

}

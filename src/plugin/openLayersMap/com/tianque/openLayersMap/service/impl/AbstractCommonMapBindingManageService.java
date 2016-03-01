package com.tianque.openLayersMap.service.impl;

import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.MapBindingManageService;


/**
 * 用于外置的绑定业务--主要用于注入参数信息
 * @author 张忠祥(zhangzhongxiang@hztianque.com)
 * @date 2013-3-18
 * @param <T>
 */
public abstract class AbstractCommonMapBindingManageService<T> extends BaseService implements MapBindingManageService<T> {

	protected String	tableName;
	protected String	functionType;
	protected String	event;

	public void initParams(String tableName, String functionType,String event) {
		this.tableName = tableName;
		this.functionType = functionType;
		this.event = event;
	}

}

package com.tianque.plugin.dataManage.util;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.tianque.plugin.AbstractBaseInfoTablesPlugin;

@Component("dataManagePluginBaseInfoTables")
public class DataManageBaseInfoTables extends AbstractBaseInfoTablesPlugin {

	@Override
	public void addKeyNames(Map<String, String> keyNames) {
		keyNames.put(
				DataManageBaseInfoTypes.INTERNET_BAR_TEMP,
				"数据管理"
						+ DataManageBaseInfoUtil.getDataManageInfoByType(
								DataManageBaseInfoTypes.INTERNET_BAR_TEMP).getCname());
	}

}

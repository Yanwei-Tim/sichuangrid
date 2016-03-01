package com.tianque.plugin.example.util;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.tianque.plugin.AbstractBaseInfoTablesPlugin;

@Component("pluginBaseInfoTables")
public class BaseInfoTables extends AbstractBaseInfoTablesPlugin {

	@Override
	public void addKeyNames(Map<String, String> keyNames) {
	}

}

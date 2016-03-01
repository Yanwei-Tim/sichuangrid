package com.tianque.plugin.loader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.tianque.core.util.BaseInfoTables;
import com.tianque.plugin.AbstractBaseInfoTablesPlugin;

@Component("baseinfoTablesPluginLoader")
public class BaseinfoTablesPluginLoader extends PluginLoader {

	@Autowired(required = false)
	private List<AbstractBaseInfoTablesPlugin> abstractBaseInfoTables;

	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		if (null == abstractBaseInfoTables) {
			return;
		}
		if (applicationEvent instanceof ContextRefreshedEvent) {
			for (AbstractBaseInfoTablesPlugin abstractBaseInfoTable : abstractBaseInfoTables) {
				abstractBaseInfoTable.addKeyNames(BaseInfoTables.getKeyName());
			}
		}
	}
}

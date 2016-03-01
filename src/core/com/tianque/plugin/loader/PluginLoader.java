package com.tianque.plugin.loader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.tianque.core.util.GridProperties;

@Component("pluginLoader")
public class PluginLoader implements ApplicationListener {

	@Autowired(required = false)
	private List<AbastractPluginLoader> abastractPluginLoaders;

	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		if (null == abastractPluginLoaders) {
			return;
		}
		boolean isJobServer = GridProperties.IS_JOBSERVER;
		if (applicationEvent instanceof ContextRefreshedEvent) {
			for (AbastractPluginLoader abstractBaseInfoTable : abastractPluginLoaders) {
				if (abstractBaseInfoTable instanceof JobPluginLoader
						&& !isJobServer) {
					continue;
				}
				abstractBaseInfoTable.loader();
			}
		}
	}

}

package com.tianque.plugin.loader;

import org.springframework.stereotype.Component;

import com.tianque.service.util.PopulationCatalog;

@Component("populationCatalogPluginLoader")
public class PopulationCatalogPluginLoader extends AbastractPluginLoader {

	@Override
	public void loader() {
		PopulationCatalog.getAllActualPopulationCatalog();
	}

}

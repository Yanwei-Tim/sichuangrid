package com.tianque.plugin.example.init;

import com.tianque.plugin.AbstractSystemPropertiesPlugin;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.PropertyDomainService;

public class ExamplePropertyInit extends AbstractSystemPropertiesPlugin {

	public ExamplePropertyInit(PropertyDomainService propertyDomainService,
			PropertyDictService propertyDictService) {
		super(propertyDomainService, propertyDictService);
	}

	@Override
	public void init() throws Exception {
		initRentalBuildings();
	}

	private void initRentalBuildings() {
		propertyDomain = addPropertyDomain("插件类型", false, null);
		addPropertyDict("类型a", 0, 1);
		addPropertyDict("类型b", 0, 2);
	}
}

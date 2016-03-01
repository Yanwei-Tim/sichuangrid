package com.tianque.init;

import com.tianque.constant.PropertyTypes;
import com.tianque.plugin.AbstractSystemPropertiesPlugin;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.PropertyDomainService;

public class AidspopulationsPropertyInit extends AbstractSystemPropertiesPlugin {

	public AidspopulationsPropertyInit(
			PropertyDomainService propertyDomainService,
			PropertyDictService propertyDictService) {
		super(propertyDomainService, propertyDictService);
	}

	@Override
	public void init() throws Exception {
		initInfectWay();
		initReceivedLevel();
	}

	private void initInfectWay() {
		propertyDomain = addPropertyDomain(PropertyTypes.INFECT_WAY, true, null);
		addPropertyDict("体液传播", 0, 1);
		addPropertyDict("血液传播", 0, 2);
		addPropertyDict("母婴垂直传播", 0, 3);

	}

	private void initReceivedLevel() {
		propertyDomain = addPropertyDomain(PropertyTypes.RECEIVED_LEVEL, true,
				null);
		addPropertyDict("省级", 0, 1);
		addPropertyDict("市级", 0, 2);
		addPropertyDict("县(区)级", 0, 3);

	}
}

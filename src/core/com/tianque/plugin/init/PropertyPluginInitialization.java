package com.tianque.plugin.init;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.tianque.exception.base.OperationFailedException;
import com.tianque.init.ContextType;
import com.tianque.init.Initialization;
import com.tianque.init.impl.SystemPropertiesInitialization;
import com.tianque.init.xml.XmlUtil;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.PropertyDomainService;

public class PropertyPluginInitialization extends
		SystemPropertiesInitialization {
	private PropertyDomainService propertyDomainService;

	private PropertyDictService propertyDictService;
	private ContextType contextType;

	public PropertyPluginInitialization(
			PropertyDomainService propertyDomainService,
			PropertyDictService propertyDictService, ContextType contextType) {
		super(propertyDomainService, propertyDictService);
		this.propertyDictService = propertyDictService;
		this.propertyDomainService = propertyDomainService;
		this.contextType = contextType;
	}

	@Override
	public void init() {
		try {
			initPlugin();
		} catch (Exception e) {
			throw new OperationFailedException("初始化属性字典插件异常", e);
		}
	}

	private void initPlugin() throws Exception, IllegalAccessException,
			InvocationTargetException {
		String[] propertyInitClasses = XmlUtil
				.getSystemPropertiesInitClasses(contextType);
		for (String initClass : propertyInitClasses) {
			System.out.println(initClass);
			Class init = Class.forName(initClass);
			Constructor constructor = init.getDeclaredConstructor(new Class[] {
					PropertyDomainService.class, PropertyDictService.class });
			Initialization initialization = (Initialization) constructor
					.newInstance(new Object[] { propertyDomainService,
							propertyDictService });
			initialization.init();
		}
	}
}

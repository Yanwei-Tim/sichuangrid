package com.tianque.init;

import com.tianque.core.cache.util.MemCachedManage;
import com.tianque.init.impl.CreateSessionForTestInitialization;
import com.tianque.init.impl.DatabaseInitialization;
import com.tianque.init.impl.GlobalSettingInitialization;
import com.tianque.init.impl.PermissionXmlInit;
import com.tianque.init.impl.StopClearSessionSchedulerInitialization;
import com.tianque.init.impl.SystemPropertiesInitialization;
import com.tianque.init.impl.UserRoleInitialization;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.PropertyDomainService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.util.ApplicationContextFactory;

public class FunctionTestEnvironmentBuilder extends InitializationsRunner {
	public FunctionTestEnvironmentBuilder() throws Exception {
		new DatabaseInitialization(ContextType.test).init();
		getXMemcachedClient().flushAll();
		addInitialization(new StopClearSessionSchedulerInitialization(
				ContextType.test));
		addInitialization(new CreateSessionForTestInitialization());
		addInitialization(new SystemPropertiesInitialization(
				getPropertyDomainService(), getPropertyDictService()));

		// addInitialization(createPermissionPersistentInitialization());
		// addInitialization(new
		// OrganizationInitialization(getOrganizationDubboService(),getPropertyDictService()));
		// addInitialization(new
		// UserRoleInitialization(getPermissionService(),getOrganizationDubboService(),
		// getPropertyDictService()));
		// addInitialization(new PermissionTreeInitialization());
		addInitialization(new PermissionXmlInit(getPermissionService()));
		// addInitialization(new
		// RoleXmlInit(getPropertyDictService(),getPermissionService()));
		addInitialization(new OrganizationExcelNewInit(
				getPropertyDictService(), getOrganizationDubboService(), false));
		addInitialization(new UserRoleInitialization(
				getPermissionService(), getOrganizationDubboService(),
				getPropertyDictService()));
		// addInitialization(new
		// ExamineScoreInitialization(getExamineCatalogService(),
		// getExamineItemService()));
		addInitialization(new GlobalSettingInitialization(
				getGlobalSettingService()));
	}

	private com.tianque.core.globalSetting.service.GlobalSettingService getGlobalSettingService() {
		return (com.tianque.core.globalSetting.service.GlobalSettingService) ApplicationContextFactory
				.getInstance()
				.getBean(ContextType.test, "globalSettingService");
	}

	private MemCachedManage getXMemcachedClient() {
		return (MemCachedManage) ApplicationContextFactory.getInstance()
				.getBean(ContextType.test, "memCachedManage");
	}

	private PropertyDomainService getPropertyDomainService() {
		return (PropertyDomainService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.test, "propertyDomainService");
	}

	public void builderTestEnv() throws Exception {
		executeInitialization();
		logger.info("功能测试环境初始化结束!");
	}

	private OrganizationDubboService getOrganizationDubboService() {
		return (OrganizationDubboService) ApplicationContextFactory
				.getInstance().getBean(ContextType.test,
						"organizationDubboService");
	}

	private PermissionService getPermissionService() {
		return (PermissionService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.test, "permissionService");
	}

	private PropertyDictService getPropertyDictService() {
		return (PropertyDictService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.test, "propertyDictService");
	}

	// private ExamineCatalogService getExamineCatalogService() {
	// return (ExamineCatalogService)
	// ApplicationContextFactory.getInstance().getBean(ContextType.test,
	// "examineCatalogService");
	// }
	//
	// private ExamineItemService getExamineItemService() {
	// return (ExamineItemService)
	// ApplicationContextFactory.getInstance().getBean(ContextType.test,
	// "examineItemService");
	// }
}

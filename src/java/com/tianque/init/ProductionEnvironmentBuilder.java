package com.tianque.init;

import com.tianque.core.cache.util.MemCachedManage;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.init.impl.CreateSessionForTestInitialization;
import com.tianque.init.impl.DailyDirectoryInitialization;
import com.tianque.init.impl.DatabaseInitialization;
import com.tianque.init.impl.DestoryCacheConnection;
import com.tianque.init.impl.DetailedRuleInitialization;
import com.tianque.init.impl.DirectorySettingInitialization;
import com.tianque.init.impl.ExamineScoreInitialization;
import com.tianque.init.impl.GlobalSettingInitialization;
import com.tianque.init.impl.IssueTypeDomainsInitialization;
import com.tianque.init.impl.PermissionXmlInit;
import com.tianque.init.impl.ShardTablesInit;
import com.tianque.init.impl.StopClearSessionSchedulerInitialization;
import com.tianque.init.impl.SystemPropertiesInitialization;
import com.tianque.init.impl.TaskJobInit;
import com.tianque.init.impl.UserRoleInitialization;
import com.tianque.plugin.init.DatabasePluginInitialization;
import com.tianque.plugin.init.PermissionPluginInitialization;
import com.tianque.plugin.init.PropertyPluginInitialization;
import com.tianque.resourcePool.service.DirectorySettingService;
import com.tianque.service.DetailedRuleService;
import com.tianque.service.EvaluateService;
import com.tianque.service.ExamineCatalogService;
import com.tianque.service.ExamineItemService;
import com.tianque.service.IdCardNoNativeAddressService;
import com.tianque.service.IssueTypeDomainService;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.PropertyDomainService;
import com.tianque.util.ApplicationContextFactory;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyYearService;

public class ProductionEnvironmentBuilder extends InitializationsRunner {
	public ProductionEnvironmentBuilder() throws Exception {
		new DatabaseInitialization(ContextType.production).init();
		getXMemcachedClient().flushAll();
		addInitialization(new StopClearSessionSchedulerInitialization(
				ContextType.production));
		addInitialization(new DatabasePluginInitialization(
				ContextType.production));

		addInitialization(new CreateSessionForTestInitialization());
		addInitialization(new SystemPropertiesInitialization(
				getPropertyDomainService(), getPropertyDictService()));

		addInitialization(new PropertyPluginInitialization(
				getPropertyDomainService(), getPropertyDictService(),
				ContextType.production));

		addInitialization(new PermissionXmlInit(getPermissionService()));
		addInitialization(new PermissionPluginInitialization(
				getPermissionService(), ContextType.production));

		addInitialization(new OrganizationExcelInit(getPropertyDictService(),
				getOrganizationDubboService(), true));
		addInitialization(new UserRoleInitialization(getPermissionService(),
				getOrganizationDubboService(), getPropertyDictService()));

		addInitialization(new IssueTypeDomainsInitialization(
				getIssueTypeDomainService(), getIssueTypeService()));
		addInitialization(new ExamineScoreInitialization(
				getExamineCatalogService(), getExamineItemService()));
		addInitialization(new DailyDirectoryInitialization(
				getDailyYearService(), getDailyDirectoryService(),
				getPropertyDictService()));
		addInitialization(new GlobalSettingInitialization(
				getGlobalSettingService()));
		addInitialization(new DetailedRuleInitialization(
				getDetailedRuleService(), getEvaluateService(),
				getOrganizationDubboService(), getPropertyDictService()));
		addInitialization(new DestoryCacheConnection(getXMemcachedClient()));
		addInitialization(new IdCardNoFromExcelInit(
				getIdCardNoNativeAddressService()));
		addInitialization(new DirectorySettingInitialization(
				getDirectorySettingService()));
		addInitialization(new ShardTablesInit(getOrganizationDubboService(),
				getPropertyDictService(), ContextType.production));
		addInitialization(new TaskJobInit(ContextType.production));
	}

	private IdCardNoNativeAddressService getIdCardNoNativeAddressService() {
		return (IdCardNoNativeAddressService) ApplicationContextFactory
				.getInstance().getBean(ContextType.production,
						"idCardNoNativeAddressService");
	}

	private DetailedRuleService getDetailedRuleService() {
		return (DetailedRuleService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.production, "detailedRuleService");
	}

	private EvaluateService getEvaluateService() {
		return (EvaluateService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.production, "evaluateService");
	}

	public void builderTestEnv() throws Exception {
		executeInitialization();
		logger.info("产品环境初始化结束!");
		System.exit(0);
	}

	private IssueTypeService getIssueTypeService() {
		return (IssueTypeService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.production, "issueTypeService");
	}

	private MemCachedManage getXMemcachedClient() {
		return (MemCachedManage) ApplicationContextFactory.getInstance()
				.getBean(ContextType.production, "memCachedManage");
	}

	private GlobalSettingService getGlobalSettingService() {
		return (GlobalSettingService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.production, "globalSettingService");
	}

	private DailyYearService getDailyYearService() {
		return (DailyYearService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.production, "dailyYearService");
	}

	private DailyDirectoryService getDailyDirectoryService() {
		return (DailyDirectoryService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.production, "dailyDirectoryService");
	}

	private PermissionService getPermissionService() {
		return (PermissionService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.production, "permissionService");
	}

	private OrganizationDubboService getOrganizationDubboService() {
		return (OrganizationDubboService) ApplicationContextFactory
				.getInstance().getBean(ContextType.production,
						"organizationDubboService");
	}

	private PropertyDictService getPropertyDictService() {
		return (PropertyDictService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.production, "propertyDictService");
	}

	private PropertyDomainService getPropertyDomainService() {
		return (PropertyDomainService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.production, "propertyDomainService");
	}

	private ExamineCatalogService getExamineCatalogService() {
		return (ExamineCatalogService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.production, "examineCatalogService");
	}

	private IssueTypeDomainService getIssueTypeDomainService() {
		return (IssueTypeDomainService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.production, "issueTypeDomainService");
	}

	private ExamineItemService getExamineItemService() {
		return (ExamineItemService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.production, "examineItemService");
	}

	private DirectorySettingService getDirectorySettingService() {
		return (DirectorySettingService) ApplicationContextFactory
				.getInstance().getBean(ContextType.production,
						"directorySettingService");
	}
}

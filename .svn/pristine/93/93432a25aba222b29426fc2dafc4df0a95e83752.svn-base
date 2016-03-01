package com.tianque.init;

import com.tianque.core.cache.util.MemCachedManage;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.util.GridProperties;
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
import com.tianque.init.impl.project.YFDGlobalSettingInit;
import com.tianque.init.impl.project.YFDOrganizationExcelInit;
import com.tianque.init.impl.project.YFDPermissionXmlInit;
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

public class DevelopmentEnvironmentBuilder extends InitializationsRunner {

	public DevelopmentEnvironmentBuilder() throws Exception {
		String projectName = GridProperties.CURRENT_PROJECT;
		if (projectName == null || "".equals(projectName)) {
			addDefaultInitializations();
		} else {
			addInitializations(projectName);
		}
	}

	private void addInitializations(String projectName) throws Exception {
		if (Project.YANGFANGDIAN.toString().equalsIgnoreCase(projectName)) {
			addYFDInitializations();
		} else {
			addDefaultInitializations();
		}
	}

	/**
	 * 默认数据初始化
	 * 
	 * @throws Exception
	 */
	private void addDefaultInitializations() throws Exception {
		new DatabaseInitialization(ContextType.development).init();
		getXMemcachedClient().flushAll();
		addInitialization(new StopClearSessionSchedulerInitialization(
				ContextType.development));
		addInitialization(new DatabasePluginInitialization(
				ContextType.development));

		addInitialization(new CreateSessionForTestInitialization());
		addInitialization(new SystemPropertiesInitialization(
				getPropertyDomainService(), getPropertyDictService()));

		addInitialization(new PropertyPluginInitialization(
				getPropertyDomainService(), getPropertyDictService(),
				ContextType.development));

		addInitialization(new PermissionXmlInit(getPermissionService()));
		addInitialization(new PermissionPluginInitialization(
				getPermissionService(), ContextType.development));

		addInitialization(new OrganizationExcelInit(getPropertyDictService(),
				getOrganizationDubboService(), false));
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
				getPropertyDictService(), ContextType.development));

		// job持久化初始化
		addInitialization(new TaskJobInit(ContextType.development));
	}

	/**
	 * 北京羊坊店数据初始化
	 * 
	 * @throws Exception
	 */
	private void addYFDInitializations() throws Exception {
		new DatabaseInitialization(ContextType.development).init();
		getXMemcachedClient().flushAll();
		addInitialization(new StopClearSessionSchedulerInitialization(
				ContextType.development));
		addInitialization(new CreateSessionForTestInitialization());
		addInitialization(new SystemPropertiesInitialization(
				getPropertyDomainService(), getPropertyDictService()));
		addInitialization(new YFDPermissionXmlInit(getPermissionService()));
		// addInitialization(new RoleXmlInit(getPropertyDictService(),
		// getPermissionService()));
		addInitialization(new YFDOrganizationExcelInit(
				getPropertyDictService(), getOrganizationDubboService(), false));
		addInitialization(new UserRoleInitialization(getPermissionService(),
				getOrganizationDubboService(), getPropertyDictService()));
		addInitialization(new IssueTypeDomainsInitialization(
				getIssueTypeDomainService(), getIssueTypeService()));
		addInitialization(new ExamineScoreInitialization(
				getExamineCatalogService(), getExamineItemService()));
		addInitialization(new DailyDirectoryInitialization(
				getDailyYearService(), getDailyDirectoryService(),
				getPropertyDictService()));
		addInitialization(new YFDGlobalSettingInit(getGlobalSettingService()));
		addInitialization(new DetailedRuleInitialization(
				getDetailedRuleService(), getEvaluateService(),
				getOrganizationDubboService(), getPropertyDictService()));
		addInitialization(new DestoryCacheConnection(getXMemcachedClient()));
		addInitialization(new IdCardNoFromExcelInit(
				getIdCardNoNativeAddressService()));
		addInitialization(new DirectorySettingInitialization(
				getDirectorySettingService()));
	}

	private DirectorySettingService getDirectorySettingService() {
		return (DirectorySettingService) ApplicationContextFactory
				.getInstance().getBean(ContextType.development,
						"directorySettingService");
	}

	private IdCardNoNativeAddressService getIdCardNoNativeAddressService() {
		return (IdCardNoNativeAddressService) ApplicationContextFactory
				.getInstance().getBean(ContextType.development,
						"idCardNoNativeAddressService");
	}

	private DetailedRuleService getDetailedRuleService() {
		return (DetailedRuleService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "detailedRuleService");
	}

	private EvaluateService getEvaluateService() {
		return (EvaluateService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "evaluateService");
	}

	private IssueTypeService getIssueTypeService() {
		return (IssueTypeService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "issueTypeService");
	}

	public void builderTestEnv() throws Exception {
		executeInitialization();
		logger.info("开发环境初始化结束!");
		System.exit(0);
	}

	private MemCachedManage getXMemcachedClient() {
		return (MemCachedManage) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "memCachedManage");
	}

	private GlobalSettingService getGlobalSettingService() {
		return (GlobalSettingService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "globalSettingService");
	}

	private DailyYearService getDailyYearService() {
		return (DailyYearService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "dailyYearService");
	}

	private DailyDirectoryService getDailyDirectoryService() {
		return (DailyDirectoryService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "dailyDirectoryService");
	}

	private PermissionService getPermissionService() {
		return (PermissionService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "permissionService");
	}

	private OrganizationDubboService getOrganizationDubboService() {
		return (OrganizationDubboService) ApplicationContextFactory
				.getInstance().getBean(ContextType.development,
						"organizationDubboService");
	}

	private PropertyDictService getPropertyDictService() {
		return (PropertyDictService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "propertyDictService");
	}

	private PropertyDomainService getPropertyDomainService() {
		return (PropertyDomainService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "propertyDomainService");
	}

	private IssueTypeDomainService getIssueTypeDomainService() {
		return (IssueTypeDomainService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "issueTypeDomainService");
	}

	private ExamineCatalogService getExamineCatalogService() {
		return (ExamineCatalogService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "examineCatalogService");
	}

	private ExamineItemService getExamineItemService() {
		return (ExamineItemService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "examineItemService");
	}
}

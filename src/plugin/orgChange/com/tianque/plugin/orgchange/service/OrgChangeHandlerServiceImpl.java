package com.tianque.plugin.orgchange.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.tianque.core.cache.service.CacheService;
import com.tianque.core.cache.service.impl.CacheNameSpace;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.domain.Permission;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.domain.ModuleTable;
import com.tianque.plugin.orgchange.domain.OrgChangeInfo;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;
import com.tianque.plugin.orgchange.domain.ProgressInfo;
import com.tianque.plugin.orgchange.handler.OrgChangeHandler;
import com.tianque.plugin.orgchange.handler.OrgChangeHandlerException;
import com.tianque.plugin.orgchange.handler.OrganizationHandler;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 组织机构迁移合并执行实现
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月24日
 */
@Service("orgChangeHandlerService")
public class OrgChangeHandlerServiceImpl implements OrgChangeHandlerService {
	private final static Logger logger = LoggerFactory
			.getLogger(OrgChangeHandlerServiceImpl.class);

	@Autowired
	private OrgChangeLogInfoService orgChangeLogInfoService;
	@Autowired
	private OrgChangeInfoService orgChangeInfoService;
	@Autowired
	private ModuleTableService moduleTableService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private OrganizationHandler organizationHandler;
	@Autowired
	@Qualifier("commonHandler")
	private OrgChangeHandler commonHandler;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private PlatformTransactionManager jtaTransactionManager;
	@Autowired
	private PermissionService permissionService;

	@Override
	public void executeChange(Long changeId) {
		List<ModuleTable> moduleTableList = moduleTableService
				.queryAllForList(true);
		List<ModuleTable>[] moduleTabArray = getModuleTabeByLevel(moduleTableList);
		OrgChangeInfo orgChangeInfo = orgChangeInfoService
				.getInfoById(changeId);
		if (orgChangeInfo != null && !orgChangeInfo.getIsDeal()) {
			ProgressInfo progressInfo = new ProgressInfo(orgChangeInfo.getId(),
					moduleTableList.size() + 1, cacheService);
			orgChangeInfo.setProgressInfo(progressInfo);
			executeChange(moduleTabArray, orgChangeInfo);
		}
	}

	@Override
	public void executeChange() {
		if (logger.isDebugEnabled())
			logger.debug("begin executeChange.");

		OrgChangeInfo condition = new OrgChangeInfo();
		condition.setIsDeal(false);
		int count = orgChangeInfoService.getCount(condition);
		if (count > 0) {
			List<OrgChangeInfo> orgChangeInfoList = orgChangeInfoService
					.queryAllForList(condition, 0, count);

			// 按层级进行归类
			List<ModuleTable> moduleTableList = moduleTableService
					.queryAllForList(true);
			List<ModuleTable>[] moduleTabArray = getModuleTabeByLevel(moduleTableList);
			for (OrgChangeInfo orgChangeInfo : orgChangeInfoList) {
				if (orgChangeInfo != null && !orgChangeInfo.getIsDeal()) {
					ProgressInfo progressInfo = new ProgressInfo(
							orgChangeInfo.getId(), moduleTableList.size() + 1,
							cacheService);
					orgChangeInfo.setProgressInfo(progressInfo);
					executeChange(moduleTabArray, orgChangeInfo);
				}
			}
		}

		if (logger.isDebugEnabled())
			logger.debug("end executeChange.");

	}

	private TransactionStatus beginTransaction() {
		DefaultTransactionDefinition result = new DefaultTransactionDefinition();
		result.setTimeout(12000);
		result.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return jtaTransactionManager.getTransaction(result);
	}

	private void executeChange(List<ModuleTable>[] moduleTabArray,
			OrgChangeInfo orgChangeInfo) {
		List<OrgMapping> orgMappingList = null;
		ProgressInfo progressInfo = orgChangeInfo.getProgressInfo();
		try {
			// 进行组织机构表organizations变更
			progressInfo.setCurrentModule("组织机构[ORGANIZATIONS]");
			long start = System.currentTimeMillis();
			orgMappingList = orgChange(orgChangeInfo);
			cacheService
					.invalidateNamespaceCache(CacheNameSpace.Organization_nameSpace);
			progressInfo.setCurrentModuleDelayTime(System.currentTimeMillis()
					- start);

			// 执行其它模块表变更
			moduleChange(orgChangeInfo, moduleTabArray, orgMappingList);
			progressInfo.addCurrentModule(progressInfo.getCurrentModule());
			progressInfo.setCurrentModule("完成");

			// 合并情况下被合并的组织机构删除
			if (OrgChangeUtils.MERGE == orgChangeInfo.getOperateType()) {
				organizationDubboService.deleteOrgById(orgChangeInfo
						.getSourceOrgId());
				cacheService
						.invalidateNamespaceCache(CacheNameSpace.Organization_nameSpace);
			}

			// 迁移情况下被迁移的SubCount通过查询统计
			if (OrgChangeUtils.TRANSFER == orgChangeInfo.getOperateType()) {
				organizationDubboService.updateOrgSubCount(
						orgChangeInfo.getSourceParentid(), -1);
				organizationDubboService.updateAllOrgSubCount(orgChangeInfo
						.getSourceParentid());
			}

			orgChangeInfo.commit();
			orgChangeLogInfoService.addLog(orgChangeInfo.getLog());
			orgChangeInfo.setIsDeal(true);
			orgChangeInfoService.updateInfo(orgChangeInfo);
		} catch (Throwable t) {
			// 查看错误信息用
			logger.error("迁移合并异常", t);
			cacheService
					.invalidateNamespaceCache(CacheNameSpace.Organization_nameSpace);
			orgChangeInfo.setThrowable(t);
			orgChangeInfo.rollback();
			rollback(orgChangeInfo);
			progressInfo.setIsException(OrgChangeUtils.ORGCHANGE_EXCEPTION);
		}
	}

	private void rollback(OrgChangeInfo orgChangeInfo) {
		Throwable t = orgChangeInfo.getThrowable();
		if (t instanceof OrgChangeHandlerException) {
			OrgChangeHandlerException oche = (OrgChangeHandlerException) t;
			orgChangeLogInfoService.addLog(oche.getLog());
		} else {
			OrgChangeLog log = new OrgChangeLog(orgChangeInfo);
			if (t != null) {
				log.setErrorDesc(t.getMessage());
			} else {
				log.setErrorDesc("未知异常");
			}
			orgChangeLogInfoService.addLog(log);
		}
	}

	/**
	 * 按执行层级进行分类
	 * 
	 * @param moduleTableList
	 * @return
	 */
	private List<ModuleTable>[] getModuleTabeByLevel(
			List<ModuleTable> moduleTableList) {
		Map<Integer, List<ModuleTable>> moduleTableMap = new HashMap<Integer, List<ModuleTable>>();
		for (ModuleTable moduleTable : moduleTableList) {
			if (moduleTable != null) {
				Integer level = moduleTable.getExecuteLevel();
				if (moduleTableMap.containsKey(level)) {
					moduleTableMap.get(level).add(moduleTable);
				} else {
					List<ModuleTable> temp = new ArrayList<ModuleTable>();
					temp.add(moduleTable);
					moduleTableMap.put(level, temp);
				}
			}
		}

		// 按层级进行排序
		TreeSet<Integer> treeSet = new TreeSet<Integer>(moduleTableMap.keySet());
		List<ModuleTable>[] moduleTableListArry = new List[treeSet.size()];
		int index = 0;
		for (Integer level : treeSet) {
			moduleTableListArry[index++] = moduleTableMap.get(level);
		}
		return moduleTableListArry;
	}

	private List<OrgMapping> orgChange(OrgChangeInfo orgChangeInfo) {
		if (logger.isDebugEnabled())
			logger.debug("begin exceuteChange.orgChangeInfo:{}", orgChangeInfo);
		orgChangeInfo.setJtaTransactionManager(jtaTransactionManager);
		orgChangeInfo.setTransactionStatus(beginTransaction());
		OrgChangeLog log = new OrgChangeLog(orgChangeInfo);
		orgChangeInfo.setLog(log);
		List<OrgMapping> orgMappingList = null;
		try {
			if (organizationHandler == null) {
				log.setErrorDesc("系统内部异常：organizationHandler is null.");
				logger.error("organizationHandler is null.");
				throw new BusinessValidationException(
						"organizationHandler is null.");
			}

			// 备份
			organizationHandler.backup(orgChangeInfo, log);
			if (OrgChangeUtils.TRANSFER == orgChangeInfo.getOperateType()) {
				orgMappingList = organizationHandler.transfer(orgChangeInfo,
						log);
			} else if (OrgChangeUtils.MERGE == orgChangeInfo.getOperateType()) {
				orgMappingList = organizationHandler.merge(orgChangeInfo, log);
			} else {
				log.setErrorDesc("未支持的变更类型[" + orgChangeInfo.getOperateType()
						+ "]不存在。");
				throw new OrgChangeHandlerException(log);
			}
		} catch (Throwable e) {
			log.setErrorDesc(log.getDescription() + e.getCause());
			organizationHandler.recover(orgChangeInfo, log);
			throw new OrgChangeHandlerException(log);
		}
		log.setLogType(OrgChangeUtils.LOG_SUCCESS);
		log.setEndTime(new Date());
		if (logger.isDebugEnabled())
			logger.debug("end exceuteChange.");
		return orgMappingList;
	}

	private void moduleChange(OrgChangeInfo orgChangeInfo,
			List<ModuleTable>[] moduleTabArray,
			final List<OrgMapping> orgMappingList) {
		if (orgMappingList != null && moduleTabArray != null
				&& moduleTabArray.length > 0 && !orgMappingList.isEmpty()) {
			for (List<ModuleTable> moduleTableList : moduleTabArray) {
				// moduleTabArray里是按层级归类按顺序的ModuleTable，多线程优化要注意必须一层级执行完毕后才能执行下一层级
				for (final ModuleTable moduleTable : moduleTableList) {
					Permission permission = new Permission();
					if ("householdStaffManagement".equals(moduleTable
							.getPermission().getEname())) {
						permission.setCname("户籍人口");
					} else if ("floatingPopulationManagement"
							.equals(moduleTable.getPermission().getEname())) {
						permission.setCname("流动人口");
					} else if ("actualHouseManagement".equals(moduleTable
							.getPermission().getEname())) {
						permission.setCname("房屋信息");
					} else if ("houseFamilyInfo".equals(moduleTable
							.getPermission().getEname())) {
						permission.setCname("户籍家庭");
					} else {
						permission = permissionService
								.findPermissionByEname(moduleTable
										.getPermission().getEname());
					}
					if (permission != null) {
						moduleTable.setPermission(permission);
						orgChangeInfo.getProgressInfo().addCurrentModule(
								permission.getCname() + "["
										+ moduleTable.getTableName() + "]");
						long start = System.currentTimeMillis();
						for (OrgMapping orgMapping : orgMappingList) {
							orgMapping = orgMapping.clone();
							orgMapping.setOrgChangeInfo(orgChangeInfo);
							orgMapping.setModuleTable(moduleTable);
							moduleChange(orgMapping);
							orgChangeLogInfoService.addLog(orgMapping
									.getOrgChangeLog());
						}
						orgChangeInfo.getProgressInfo()
								.setCurrentModuleDelayTime(
										System.currentTimeMillis() - start);
					} else {
						logger.error("不存在的的permission:"
								+ moduleTable.getPermission().getEname());
					}
				}
			}
		}
	}

	private void moduleChange(OrgMapping orgMapping) {
		ModuleTable moduleTable = orgMapping.getModuleTable();
		OrgChangeLog log = new OrgChangeLog(orgMapping);
		orgMapping.setOrgChangeLog(log);
		OrgChangeHandler orgChangeHandler = null;
		if (OrgChangeUtils.EXECUTE_DEFAULT == moduleTable.getExecuteType()) {
			orgChangeHandler = commonHandler;
		} else if (OrgChangeUtils.EXECUTE_CUSTOM == moduleTable
				.getExecuteType()) {
			Object handler = SpringBeanUtil
					.getBeanFromSpringByBeanName(moduleTable.getBeanName());
			if (handler instanceof OrgChangeHandler) {
				orgChangeHandler = (OrgChangeHandler) handler;
			} else {
				log.setLogType(OrgChangeUtils.LOG_ERROR);
				log.setEndTime(new Date());
				log.setDescription("未找到执行者：" + moduleTable.getUpdateScript());
				throw new OrgChangeHandlerException(log);
			}
		} else {
			log.setLogType(OrgChangeUtils.LOG_ERROR);
			log.setEndTime(new Date());
			log.setErrorDesc("未知的执行类型：" + moduleTable.getExecuteType());
			throw new OrgChangeHandlerException(log);
		}

		OrgChangeInfo orgChangeInfo = orgMapping.getOrgChangeInfo();

		try {
			orgMapping.setHasData(true);
			// 初始化操作,判断业务表里是否有变更组织相关的数据
			orgChangeHandler.init(orgMapping);
			// 业务表里有相关的数据再进行后续操作
			if (orgMapping.isHasData()) {
				// 备份
				orgChangeHandler.backup(orgMapping);

				if (OrgChangeUtils.TRANSFER == orgChangeInfo.getOperateType()) {
					orgChangeHandler.transfer(orgMapping);
				} else if (OrgChangeUtils.MERGE == orgChangeInfo
						.getOperateType()) {
					orgChangeHandler.merge(orgMapping);
				} else {
					log.setDescription("未支持的变更类型["
							+ orgChangeInfo.getOperateType() + "]不存在。");
					throw new OrgChangeHandlerException(log);
				}
			}
			log.setLogType(OrgChangeUtils.LOG_SUCCESS);
		} catch (Throwable e) {
			orgChangeHandler.recover(orgMapping);
			log.setLogType(OrgChangeUtils.LOG_ERROR);
			log.setErrorDesc(log.getDescription() + "moduleTable:"
					+ moduleTable + "，异常信息：" + e.getMessage());
			logger.error("组织机构迁移合并异常：", e);
			throw new OrgChangeHandlerException(log);
		}
	}
}

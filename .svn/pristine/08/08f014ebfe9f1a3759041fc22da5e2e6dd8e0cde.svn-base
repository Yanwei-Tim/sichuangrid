package com.tianque.plugin.orgchange.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.util.StringUtil;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.dao.CommonHandlerDAO;
import com.tianque.plugin.orgchange.domain.ModuleTable;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;
import com.tianque.plugin.orgchange.service.BackupBaseDataService;
import com.tianque.tableManage.dao.TableManageDao;

/**
 * 组织机构合并迁移抽象类
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月25日
 */
public abstract class AbstractOrgChangeHandler implements OrgChangeHandler {
	private final static Logger logger = LoggerFactory
			.getLogger(AbstractOrgChangeHandler.class);

	@Autowired
	protected CommonHandlerDAO commonHandlerDAO;
	@Autowired
	protected BackupBaseDataService backupBaseDataService;
	@Autowired
	protected TableManageDao tableManageDao;

	private final static String COUNT_SQL = "SELECT COUNT(*) FROM #TABLENAME# WHERE #ORGIDCOLUMN# = #OLDORGID#";

	private final static String UPDATE_ID_SQL = "UPDATE #TABLENAME# SET #ORGIDCOLUMN#=#NEWORGID# WHERE  #ORGIDCOLUMN# = #OLDORGID#";
	private final static String UPDATE_CODE_SQL = "UPDATE #TABLENAME# SET #ORGCODECOLUMN#=#NEWORGCODE# WHERE #ORGIDCOLUMN# = #OLDORGID#";
	private final static String UPDATE_ID_CODE_SQL = "UPDATE #TABLENAME# SET #ORGIDCOLUMN#=#NEWORGID#,#ORGCODECOLUMN#=#NEWORGCODE# WHERE #ORGIDCOLUMN# = #OLDORGID#";

	@Override
	public void init(OrgMapping orgMapping) {
		if (logger.isDebugEnabled())
			logger.debug("begin init.orgMapping:{}", orgMapping);
		ModuleTable moduleTable = orgMapping.getModuleTable();
		if (moduleTable.isSplit() != null && moduleTable.isSplit()) {
			moduleTable.setTableName(moduleTable.getTableName() + "_"
					+ orgMapping.getDeptNoTail());
			// 只对分表名设置一次值
			moduleTable.setSplit(false);
		}
		String countSql = moduleTable.getCountScript();
		// 判断是否配置统计语句，如果没有则用默认语句
		if (!StringUtil.isStringAvaliable(countSql)) {
			countSql = COUNT_SQL;
		}
		if (tableManageDao.IsCreateTable(moduleTable.getTableName())) {
			countSql = OrgChangeUtils.replaceScript(orgMapping, countSql);
			int count = commonHandlerDAO.getCount(countSql);
			if (count > 0) {
				orgMapping.setHasData(true);
			} else {
				orgMapping.setHasData(false);
			}
			OrgChangeLog log = orgMapping.getOrgChangeLog();
			log.appendSuccessDesc("需要变更的数据量[" + count + "]");
		} else {
			orgMapping.setHasData(false);
			OrgChangeLog log = orgMapping.getOrgChangeLog();
			log.appendSuccessDesc("表[" + moduleTable.getTableName() + "]不存在。");
		}
		if (logger.isDebugEnabled())
			logger.debug("end init.orgMapping:{}", orgMapping);
	}

	@Override
	public void transfer(OrgMapping orgMapping) {
		if (logger.isDebugEnabled())
			logger.debug("begin transfer.");

		// 业务表中存在orgCode字段才需要执行变更
		execute(orgMapping);
		if (logger.isDebugEnabled())
			logger.debug("end transfer.");
	}

	@Override
	public abstract void merge(OrgMapping orgMapping);

	/**
	 * 无需进行唯一性校验的业务数据可以直接调用该方法
	 * 
	 * @param orgMapping
	 */
	protected void execute(OrgMapping orgMapping) {
		if (logger.isDebugEnabled())
			logger.debug("begin execute.orgMapping:{}", orgMapping);
		// 有数据而且orgId有变更或者业务表有orgCode字段
		if (!orgMapping.getOldOrgId().equals(orgMapping.getNewOrgId())
				|| StringUtil.isStringAvaliable(orgMapping.getModuleTable()
						.getOrgCodeColumn())) {
			ModuleTable moduleTable = orgMapping.getModuleTable();
			String updateSql = moduleTable.getUpdateScript();
			if (!StringUtil.isStringAvaliable(updateSql)) {
				String orgCodeColumn = moduleTable.getOrgCodeColumn();
				// 判断是否有orgCode字段
				if (StringUtil.isStringAvaliable(orgCodeColumn)) {
					// 判断是否只更改了orgCode
					if (orgMapping.getOldOrgId().equals(
							orgMapping.getNewOrgId())) {
						updateSql = UPDATE_CODE_SQL;
					} else {
						updateSql = UPDATE_ID_CODE_SQL;
					}
				} else {
					updateSql = UPDATE_ID_SQL;
				}
			}

			int num;
			String deleteSql = OrgChangeUtils.replaceScript(orgMapping,
					moduleTable.getDeleteScript());
			OrgChangeLog log = orgMapping.getOrgChangeLog();
			if (StringUtil.isStringAvaliable(deleteSql)) {
				// 先执行删除语句
				if ("DELETE".equals(OrgChangeUtils.getFirstWord(deleteSql))) {
					num = commonHandlerDAO.deleteData(deleteSql);
					log.setSuccessDesc(log.getDescription() + "删除数据量[" + num
							+ "]");
				} else {
					log.setErrorDesc(log.getDescription()
							+ "删除脚本错误：非delete开头的删除语句");
					throw new OrgChangeHandlerException(log);
				}
			}

			// 再执行更新语句
			updateSql = OrgChangeUtils.replaceScript(orgMapping, updateSql);
			if ("UPDATE".equals(OrgChangeUtils.getFirstWord(updateSql))) {
				num = commonHandlerDAO.updateData(updateSql);
				log.setSuccessDesc(log.getDescription() + "更新数据量[" + num + "]");
			} else {
				log.setErrorDesc(log.getDescription() + "更新脚本错误：非update开头的更新语句");
				throw new OrgChangeHandlerException(log);
			}
		}
		if (logger.isDebugEnabled())
			logger.debug("end execute.");
	}

	@Override
	public void delete(OrgMapping orgMapping) {
		if (logger.isDebugEnabled())
			logger.debug("begin delete.orgMapping:{}", orgMapping);
		if (logger.isDebugEnabled())
			logger.debug("end delete.");
	}

	@Override
	public void backup(OrgMapping orgMapping) {
		if (logger.isDebugEnabled())
			logger.debug("begin backup.orgMapping:{}", orgMapping);
		ModuleTable moduleTable = orgMapping.getModuleTable();
		String delScript = OrgChangeUtils.replaceScript(orgMapping,
				moduleTable.getDeleteScript());
		if (delScript != null && delScript.length() != 0) {
			String selectScript = OrgChangeUtils.replaceScript(orgMapping,
					moduleTable.getSelectScript());
			if (!StringUtil.isStringAvaliable(selectScript)) {
				delScript = OrgChangeUtils.getWhereIgnoreCase(delScript);
				String[] del = delScript.split(" ");
				if (del != null) {
					if (del.length > 2 && "FROM".equalsIgnoreCase(del[1])) {
						selectScript = delScript.replace(
								del[0],
								"SELECT ID dataId,"
										+ moduleTable.getOrgIdColumn()
										+ " dataOrgId,'' expansionData ");
					} else {
						selectScript = delScript.replace(
								del[0],
								"SELECT ID dataId,"
										+ moduleTable.getOrgIdColumn()
										+ " dataOrgId,'' expansionData  FROM ");
					}
				}
			}
			orgMapping.getModuleTable().setSelectScript(selectScript);
			backupBaseDataService.addBackupBaseData(orgMapping);
		}

		if (logger.isDebugEnabled())
			logger.debug("end backup.");
	}

	@Override
	public void recover(OrgMapping orgMapping) {
		if (logger.isDebugEnabled())
			logger.debug("begin recover.orgMapping:{}", orgMapping);

		if (logger.isDebugEnabled())
			logger.debug("end recover.");
	}
}

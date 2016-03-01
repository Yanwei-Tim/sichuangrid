package com.tianque.hbase.dao.impl;

import java.sql.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.bigData.api.PhoenixHbaseDubbo;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.hbase.HbaseDataSource;
import com.tianque.hbase.uitl.TransferData;
import com.tianque.jms.sender.ActiveMQMessageSender;
import com.tianque.service.util.PopulationType;
import com.tianque.systemOperateLog.dao.SystemOperateLogDao;
import com.tianque.systemOperateLog.domain.SystemOperateLog;
import com.tianque.systemOperateLog.util.TransferUtilPop;
import com.tianque.systemOperateLog.vo.SystemOperateLogVo;

@Repository("systemOperateLogHbaseDao")
public class SystemOperateLogHbaseDaoImpl implements SystemOperateLogDao {
	private static Logger logger = LoggerFactory
			.getLogger(SystemOperateLogHbaseDaoImpl.class);
	@Qualifier("dataSourceHbase")
	@Autowired
	private HbaseDataSource dataSourceHbase;
	@Qualifier("systemOperateLogDao")
	@Autowired
	private SystemOperateLogDao systemOperateLogDao;
	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;
	@Autowired
	private PhoenixHbaseDubbo phoenixHbaseDubbo;

	@Override
	public SystemOperateLog addSystemOperateLog(
			SystemOperateLog systemOperateLog) {
		if (PopulationType.AIDSPOPULATIONS_KEY.equals(systemOperateLog
				.getBusinessType())) {
			systemOperateLog
					.setBusinessType(NewBaseInfoTables.AIDSPOPULATIONS_KEY);
		}
		if (PopulationType.OVERSEA_STAFF.equals(systemOperateLog
				.getBusinessType())) {
			systemOperateLog
					.setBusinessType(NewBaseInfoTables.OVERSEAPERSONNEL_KEY);
		}
		if (PopulationType.AIDSPOPULATIONS_KEY.equals(systemOperateLog
				.getOperateSource())) {
			systemOperateLog
					.setOperateSource(NewBaseInfoTables.AIDSPOPULATIONS_KEY);
		}
		if (PopulationType.OVERSEA_STAFF.equals(systemOperateLog
				.getOperateSource())) {
			systemOperateLog
					.setOperateSource(NewBaseInfoTables.OVERSEAPERSONNEL_KEY);
		}
		if (PopulationType.ACTUAL_HOUSE.equals(systemOperateLog
				.getBusinessType())) {
			systemOperateLog.setDataId(Long.parseLong(systemOperateLog
					.getDataId().toString().substring(4)));
			systemOperateLog.setDataKeyword(systemOperateLog.getDataKeyword()
					.substring(4));
		} else if (PopulationType.RENTAL_HOUSE.equals(systemOperateLog
				.getBusinessType())) {
			systemOperateLog.setDataKeyword(systemOperateLog.getDataKeyword()
					.substring(4));
		}
		systemOperateLog.setId(systemOperateLogDao.getSystemOperateLogKey());
		systemOperateLog.setOperateDateHbase(new Date(systemOperateLog
				.getOperateDate().getTime()));
		activeMQMessageSender.senderMsg(TransferData
				.convertToJmsVo(systemOperateLog));
		return null;
	}

	@Override
	public PageInfo<SystemOperateLog> findAllSystemLogsForPage(
			SystemOperateLogVo systemOperateLogVo, int pageNum, int pageSize,
			String sortField, String order) {
		String findLogsSql = "";
		if (systemOperateLogVo != null) {
			if (systemOperateLogVo.getDataKeyword() != null
					&& !"".equals(systemOperateLogVo.getDataKeyword())) {
				if (PopulationType.ACTUAL_HOUSE.equals(systemOperateLogVo
						.getSearchType())
						|| PopulationType.RENTAL_HOUSE
								.equals(systemOperateLogVo.getSearchType())) {
					systemOperateLogVo.setDataKeyword(systemOperateLogVo
							.getDataKeyword().substring(4));
				}
				findLogsSql += " and dataKeyword = '"
						+ systemOperateLogVo.getDataKeyword() + "'";
			}
			if (systemOperateLogVo.getDataId() != null) {
				findLogsSql += " and dataId = "
						+ systemOperateLogVo.getDataId() + "";
			}
			if (StringUtil
					.isStringAvaliable(systemOperateLogVo.getSearchType())) {
				if (PopulationType.AIDSPOPULATIONS_KEY
						.equals(systemOperateLogVo.getSearchType())) {
					findLogsSql += " and (businessType = '"
							+ NewBaseInfoTables.AIDSPOPULATIONS_KEY + "'";
				} else if (PopulationType.OVERSEA_STAFF
						.equals(systemOperateLogVo.getSearchType())) {
					findLogsSql += " and (businessType = '"
							+ NewBaseInfoTables.OVERSEAPERSONNEL_KEY + "'";
				} else {
					findLogsSql += " and (businessType = '"
							+ systemOperateLogVo.getSearchType() + "'";
				}
				if (TransferUtilPop.isAllAttentionPopulation(systemOperateLogVo
						.getSearchType())) {
					findLogsSql += " or businessType in ('baseInfo','householdStaff','floatingPopulation'))";
				} else if (NewBaseInfoTables.ACTUALHOUSE_KEY
						.equals(systemOperateLogVo.getSearchType())
						|| NewBaseInfoTables.RENTALHOUSE_KEY
								.equals(systemOperateLogVo.getSearchType())) {
					findLogsSql += " or businessType in ('rentalHouse','actualHouse'))";
				} else if (NewBaseInfoTables.COMPANYPLACEKEY
						.equals(systemOperateLogVo.getSearchType())) {
					findLogsSql += " or businessType in ('CompanyPlace','CompanyPlaceBusiness'))";
				} else {
					findLogsSql += ")";
				}
			}
		}
		return phoenixHbaseDubbo.findAllSystemLogsForPage(findLogsSql, pageNum,
				pageSize, sortField, order);
	}

	@Override
	public SystemOperateLog getSystemOperateLogById(Long id) {
		return phoenixHbaseDubbo.getSystemOperateLogById(id);
	}

	// @Override
	// public void addSystemOperateLogsForBatch(final List<SystemOperateLog>
	// list) {
	// if (list != null) {
	// this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
	// public Object doInSqlMapClient(SqlMapExecutor executor)
	// throws SQLException {
	// executor.startBatch();
	// for (int i = 0; i < list.size(); i++) {
	// fillFildForSystemOperateLog(list.get(i));
	// executor.insert(
	// "systemOperateLoghbase.addSystemOperateLog",
	// list.get(i));
	// }
	// executor.executeBatch();
	// return null;
	// }
	// });
	//
	// }
	//
	// }

	// private void fillFildForSystemOperateLog(SystemOperateLog
	// systemOperateLog) {
	// systemOperateLog.setOperateDateHbase(new Date(systemOperateLog
	// .getOperateDate().getTime()));
	// }

	@Override
	public SystemOperateLog updateSystemOperateLogById(
			SystemOperateLog systemOperateLog) {
		// systemOperateLog.setOperateDateHbase(new Date(systemOperateLog
		// .getOperateDate().getTime()));
		// Connection conn = null;
		// PreparedStatement pst = null;
		// try {
		// conn = dataSourceHbase.getConnection();
		// String updateSql =
		// "UPSERT INTO systemOperateLogs (id,dataBeforeOperate,dataAfterOperate";
		// if (null != systemOperateLog.getContrastState()) {
		// updateSql += ",contrastState";
		// }
		// if (null != systemOperateLog.getOperateDescribe()) {
		// updateSql += ",operateDescribe";
		// }
		// updateSql += ") VALUES(?,?,?";
		// if (null != systemOperateLog.getContrastState()) {
		// updateSql += ",?";
		// }
		// if (null != systemOperateLog.getOperateDescribe()) {
		// updateSql += ",?";
		// }
		// updateSql += ")";
		// pst = conn.prepareStatement(updateSql);
		// pst.setLong(1, systemOperateLog.getId());
		// pst.setString(2, null);
		// pst.setString(3, null);
		// if (null != systemOperateLog.getContrastState()) {
		// pst.setInt(4, systemOperateLog.getContrastState());
		// }
		// if (null != systemOperateLog.getOperateDescribe()) {
		// pst.setString(5, systemOperateLog.getOperateDescribe());
		// }
		// pst.executeUpdate();
		// conn.commit();
		// } catch (Exception e) {
		// logger.error("修改出错：", e);
		// throw new BusinessValidationException(e);
		// } finally {
		// try {
		// pst.close();
		// conn.close();
		// } catch (SQLException e) {
		// throw new BusinessValidationException(e);
		// }
		//
		// }
		return null;

	}

	@Override
	public Long getSystemOperateLogKey() {
		return null;
	}

	@Override
	public PageInfo<SystemOperateLog> findSystemLogsForPageImportToHbase(
			int pageNum, int pageSize, String sortField, String order) {
		return null;
	}

	@Override
	public void updateSystemOperateDataKeyWord(Long orgId, Long dataId,
			String moduleType, String businessType, String beforeDataKeyWord,
			String afterDataKeyWord) {

	}

	private Long generateID() {
		Long currentTime = System.currentTimeMillis();
		return Long
				.valueOf(new Random().nextInt(1000) + currentTime.toString());
	}
}

package com.tianque.systemOperateLog.service;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.systemOperateLog.dao.SystemOperateLogDao;
import com.tianque.systemOperateLog.domain.SystemOperateLog;
import com.tianque.systemOperateLog.util.ContrastState;
import com.tianque.systemOperateLog.vo.SystemOperateLogVo;

@Transactional
@Service("systemOperateLogService")
public class SystemOperateLogServiceImpl extends AbstractBaseService implements
		SystemOperateLogService {
	@Autowired
	private Map<String, SystemOperateLogDao> SystemOperateLogMap;

	@Override
	public SystemOperateLog addSystemOperateLog(
			SystemOperateLog systemOperateLog) {
		if (systemOperateLog == null) {
			throw new BusinessValidationException("新增日志时出错");
		}
		try {
			return getDaoByName().addSystemOperateLog(systemOperateLog);
		} catch (Exception e) {
			throw new ServiceValidationException("新增日志时出错", e);
		}
	}

	@Override
	public SystemOperateLog addSystemOperateLog(String businesType,
			String dataKeyword, Organization dataOrg, String orgCode,
			String operateSource, Integer operateType, Long dataId,
			Long oldOrgId) {
		SystemOperateLog systemOperateLog = createSystemOperateLog(businesType,
				dataKeyword, dataOrg, orgCode, operateSource, operateType,
				dataId, oldOrgId);
		try {
			return this.addSystemOperateLog(systemOperateLog);
		} catch (Exception e) {
			throw new ServiceValidationException("新增日志时出错", e);
		}
	}

	private SystemOperateLog createSystemOperateLog(String businesType,
			String dataKeyword, Organization dataOrg, String orgCode,
			String operateSource, Integer operateType, Long dataId,
			Long oldOrgId) {
		SystemOperateLog systemOperateLog = new SystemOperateLog();
		Session session = ThreadVariable.getSession();
		systemOperateLog.setOperateDate(session.getAccessTime());
		systemOperateLog.setOperatePerson(session.getUserName());
		systemOperateLog.setBusinessType(businesType);
		systemOperateLog.setContrastState(ContrastState.UNMODIFIED);
		systemOperateLog.setDataKeyword(dataKeyword);
		systemOperateLog.setDataOrgId(dataOrg);
		systemOperateLog.setDataOrgCode(orgCode);
		systemOperateLog.setDataId(dataId);
		systemOperateLog.setModuleType(NewBaseInfoTables.PEOPLE_KEY);
		systemOperateLog.setOperateSource(operateSource);
		systemOperateLog.setOperateType(operateType);
		if (oldOrgId != null) {
			Organization org = new Organization();
			org.setId(oldOrgId);
			systemOperateLog.setDataBeforeOrgId(org);
		}
		return systemOperateLog;
	}

	@Override
	public SystemOperateLog getSystemOperateLogById(Long id) {
		return getDaoByName().getSystemOperateLogById(id);
	}

	@Override
	public PageInfo<SystemOperateLog> findAllSystemLogsForPage(
			SystemOperateLogVo systemOperateLogVo, int pageNum, int pageSize,
			String sortField, String order) {
		try {
			return getDaoByName().findAllSystemLogsForPage(systemOperateLogVo,
					pageNum, pageSize, sortField, order);
		} catch (Exception e) {
			throw new ServiceValidationException("显示日志列表出错", e);
		}
	}

	@Override
	public SystemOperateLog updateSystemOperateLogById(
			SystemOperateLog systemOperateLog) {
		return getDaoByName().updateSystemOperateLogById(systemOperateLog);
	}

	private SystemOperateLogDao getDaoByName() {
		SystemOperateLogDao dao = null;
		for (Entry<String, SystemOperateLogDao> entry : SystemOperateLogMap
				.entrySet()) {
			if (entry.getKey().startsWith(
					GridProperties.HBASE_LOCAL ? "systemOperateLogDao"
							: "systemOperateLogHbaseDao")) {
				dao = entry.getValue();
				break;
			}
		}
		return dao;
	}

	@Override
	public void updateSystemOperateDataKeyWord(Long orgId, Long dataId,
			String moduleType, String businessType, String beforeDataKeyWord,
			String afterDataKeyWord) {
		if (StringUtils.isBlank(moduleType)
				|| StringUtils.isBlank(afterDataKeyWord)
				|| StringUtils.isBlank(beforeDataKeyWord)) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			this.getDaoByName().updateSystemOperateDataKeyWord(orgId, dataId,
					moduleType, businessType, beforeDataKeyWord,
					afterDataKeyWord);
		} catch (Exception e) {
			throw new ServiceValidationException("修改轨迹信息错误", e);
		}

	}

}

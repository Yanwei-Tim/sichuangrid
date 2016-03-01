package com.tianque.baseInfo.excelimportlog.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.earlyWarning.domain.EarlyWarning;
import com.tianque.baseInfo.earlyWarning.service.EarlyWarningService;
import com.tianque.baseInfo.excelimportlog.dao.ExcelImportLogDao;
import com.tianque.baseInfo.excelimportlog.domain.ExcelImportLog;
import com.tianque.baseInfo.excelimportlog.domain.ExcelImportLogConstants;
import com.tianque.baseInfo.excelimportlog.domain.ExcelImportLogVO;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.job.JobHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

@Service("excelImportLogService")
public class ExcelImportLogServiceImpl extends AbstractBaseService implements
		ExcelImportLogService {
	@Autowired
	private ExcelImportLogDao excelImportLogDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	EarlyWarningService earlyWarningService;

	@Override
	public ExcelImportLog addImportLog(String uuid, String fileName,
			String fileType, Integer importDataNum, String importModuleName) {
		if (uuid == null) {
			throw new BusinessValidationException("uuid 不能为空");
		}
		try {
			ExcelImportLog excelImportLog = new ExcelImportLog();
			excelImportLog.setUuid(uuid);
			excelImportLog.setFileName(fileName);
			excelImportLog.setFileType(fileType);
			excelImportLog.setStatus(ExcelImportLogConstants.DISPOSED);
			excelImportLog.setImportDataNum(importDataNum);
			excelImportLog
					.setCurrentDealNum(ExcelImportLogConstants.CURRENTDEALNUM);
			excelImportLog
					.setErrorCountNum(ExcelImportLogConstants.ERROTCOUNTNUM);
			excelImportLog.setImportModuleName(importModuleName);
			Organization org = organizationDubboService
					.getSimpleOrgById(ThreadVariable.getUser()
							.getOrganization().getId());
			excelImportLog.setOrganizationId(org);
			return excelImportLogDao.addImportLog(excelImportLog);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ExcelImportLogServiceImpl的addImportLog方法出现异常，原因：",
					"新增日志信息出现错误", e);
		}
	}

	@Override
	public ExcelImportLog updateImportLogCurrentNum(String uuid,
			Integer currentDealNum, Integer errorCountNum) {
		if (uuid == null) {
			throw new BusinessValidationException("uuid 不能为空");
		}
		try {
			return excelImportLogDao.updateImportLogCurrentNum(uuid,
					currentDealNum, errorCountNum);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ExcelImportLogServiceImpl的updateImportLogCurrentNum方法出现异常，原因：",
					"更新日志信息出现错误", e);
		}
	}

	@Override
	public ExcelImportLog updateImportStatus(String uuid, Number status) {
		if (uuid == null) {
			throw new BusinessValidationException("uuid 不能为空");
		}
		try {
			return excelImportLogDao.updateImportStatus(uuid, status);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ExcelImportLogServiceImpl的updateImportStatus方法出现异常，原因：",
					"更新日志信息出现错误", e);
		}
	}

	@Override
	public PageInfo<ExcelImportLog> searchImportLog(Integer pageNum,
			Integer pageSize, String sidx, String sord, Integer status) {
		try {
			String createUser = null;
			if (!ThreadVariable.getUser().isAdmin()) {
				createUser = ThreadVariable.getUser().getUserName();
			}
			PageInfo<ExcelImportLog> pageInfo = excelImportLogDao
					.searchImportLog(pageNum, pageSize, sidx, sord, createUser,
							status);
			User user = null;
			if (pageInfo != null && pageInfo.getResult() != null) {
				for (ExcelImportLog log : pageInfo.getResult()) {
					Organization org = organizationDubboService
							.getSimpleOrgById(log.getOrganizationId().getId());
					log.setOrganizationId(org);
					user = permissionService.findUserByUserName(log
							.getCreateUser());
					if (user != null) {
						log.setUserName(user.getName());
					}
				}
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ExcelImportLogServiceImpl的searchImportLog方法出现异常，原因：",
					"查询导入日志信息出现错误", e);
		}
	}

	@Override
	public PageInfo<ExcelImportLog> selectExcelimportlog(
			ExcelImportLogVO excelImportLogVO, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		PageInfo<ExcelImportLog> pageInfo;
		if (excelImportLogVO == null
				|| excelImportLogVO.getOrganizationId() == null
				|| excelImportLogVO.getOrganizationId().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			excelImportLogVO.setOrganizationId(organizationDubboService
					.getSimpleOrgById(excelImportLogVO.getOrganizationId()
							.getId()));
			pageInfo = excelImportLogDao.selectExcelimportlog(excelImportLogVO,
					pageNum, pageSize, sortField, order);
			User user = null;
			for (ExcelImportLog log : pageInfo.getResult()) {
				Organization org = organizationDubboService
						.getSimpleOrgById(log.getOrganizationId().getId());
				log.setOrganizationId(org);
				user = permissionService
						.findUserByUserName(log.getCreateUser());
				if (user != null) {
					log.setUserName(user.getName());
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ExcelImportLogServiceImpl的selectExcelimportlog方法出现异常，原因：",
					"高级查询导入日志信息出现错误", e);
		}
		return pageInfo;
	}

	private static final String EXCEL_IMPORT_LOG_CLEAN = "excelImportLogClean";

	@Override
	public void excelImportLogClean() {
		EarlyWarning excelImportLogClean = earlyWarningService
				.getEarlyWarningByName(EXCEL_IMPORT_LOG_CLEAN);
		if (excelImportLogClean == null
				|| excelImportLogClean.getRemindTime() == null) {
			throw new BusinessValidationException("导入日志删除获取时间错误");
		}
		try {
			Date cleanTime = JobHelper.getMaxTime(excelImportLogClean
					.getRemindTime().intValue());
			excelImportLogDao.excelImportLogClean(cleanTime);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ExcelImportLogServiceImpl的excelImportLogClean方法出现异常，原因：",
					"删除过期导入日志信息出现错误", e);
		}
	}

	@Override
	public ExcelImportLog getExcelimportlogById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			return excelImportLogDao.getExcelimportlogById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ExcelImportLogServiceImpl的getExcelimportlogById方法出现异常，原因：",
					"根据id获取导入日志信息出现错误", e);
		}
	}
}

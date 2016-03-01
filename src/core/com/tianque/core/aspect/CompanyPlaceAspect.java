package com.tianque.core.aspect;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tianque.baseInfo.companyPlace.constant.IsKeyType;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceBusiness;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceBaseSevice;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceBusinessService;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.exception.base.IllegalOperationException;
import com.tianque.systemOperateLog.domain.SystemOperateLog;
import com.tianque.systemOperateLog.service.SystemOperateLogService;
import com.tianque.systemOperateLog.util.ContrastState;
import com.tianque.systemOperateLog.util.SystemOperateType;

@Aspect
@Repository("companyPlaceAspect")
public class CompanyPlaceAspect {
	final static Logger logger = LoggerFactory
			.getLogger(CompanyPlaceAspect.class);
	private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting()
			.setVersion(2.2).create();

	private SystemOperateLog systemOperateLog;
	@Autowired
	private SystemOperateLogService systemOperateLogService;
	@Autowired
	private CompanyPlaceBaseSevice companyPlaceBaseSevice;
	@Autowired
	private CompanyPlaceBusinessService companyPlaceBusinessService;

	private void initSystemOperateLog() throws IllegalOperationException {
		Session session = ThreadVariable.getSession();
		if (session == null || session.getUserName() == null) {
			logger.error("系统尝试在无登陆的情况下添加日志记录");
			throw new IllegalOperationException("Session不存在，系统不能正常工作!");
		}
		systemOperateLog = new SystemOperateLog();
		systemOperateLog.setOperateDate(session.getAccessTime());
		systemOperateLog.setOperatePerson(session.getUserName());
	}

	@Around("execution(public *  com.tianque.baseInfo.companyPlace.dao.CompanyPlaceDAO.add*(..))")
	public Object createActualCompanyPlaceAddLog(ProceedingJoinPoint pjp) {
		Object retVal = null; // 连接点方法返回值
		try {
			// 获取执行方法的参数
			Object arg = pjp.getArgs()[0];
			initSystemOperateLog();
			retVal = pjp.proceed();
			CompanyPlace companyPlace = (CompanyPlace) arg;
			if (ConstantsProduct.SourcesState.IMPORT.equals(companyPlace
					.getSourcesState())) {
				systemOperateLog = createActualCompanyPlaceLogAll(companyPlace,
						SystemOperateType.IMPORT);
				systemOperateLog.setOperateType(SystemOperateType.IMPORT);
			} else {
				systemOperateLog = createActualCompanyPlaceLogAll(companyPlace,
						SystemOperateType.ADD);
				systemOperateLog.setOperateType(SystemOperateType.ADD);
			}
			systemOperateLog.setDataId(((Long) retVal));

			systemOperateLogService.addSystemOperateLog(systemOperateLog);
		} catch (Exception e) {
			logger.error("记录日志时出错：", e);
		} catch (Throwable e) {
			logger.error("记录日志时出错：", e);
		}
		return retVal;
	}

	@Around("execution(public *  com.tianque.baseInfo.companyPlace.dao.CompanyPlaceBusinessDAO.add*(..))")
	public Object createActualCompanyPlaceBusunessAddLog(ProceedingJoinPoint pjp) {
		Object retVal = null; // 连接点方法返回值
		try {

			// 获取执行方法的参数
			Object arg = pjp.getArgs()[0];
			initSystemOperateLog();
			retVal = pjp.proceed();
			CompanyPlaceBusiness companyPlaceBusiness = (CompanyPlaceBusiness) arg;
			// CompanyPlaceBusinessRlation businessRlation = null;
			if (companyPlaceBusiness == null) {
				logger.error("信息未找到");
				return retVal;
			}
			if (StringUtils.isBlank(companyPlaceBusiness
					.getCompanyPlaceSource())) {
				systemOperateLog
						.setOperateSource(NewBaseInfoTables.COMPANYPLACEKEY);
			} else {
				systemOperateLog.setOperateSource(companyPlaceBusiness
						.getCompanyPlaceSource());
			}
			CompanyPlace companyPlace = companyPlaceBaseSevice
					.getCompanyPlaceInfoByCid(companyPlaceBusiness
							.getCompanyPlaceId());
			systemOperateLog.setDataAfterOperate(gson
					.toJson(companyPlaceBusiness));
			systemOperateLog.setDataBeforeOperate(gson
					.toJson(companyPlaceBusiness));
			systemOperateLog.setContrastState(ContrastState.UNMODIFIED);
			systemOperateLog.setDataId(companyPlace.getCid());
			systemOperateLog.setModuleType(NewBaseInfoTables.COMPANYPLACEKEY);
			systemOperateLog.setDataKeyword(String.valueOf(companyPlace
					.getCid()));
			systemOperateLog
					.setBusinessType(NewBaseInfoTables.COMPANYPLACEBUSINESS);
			systemOperateLog.setDataName(companyPlace.getAddress());
			if (companyPlace.getOrg() != null) {
				systemOperateLog.setDataOrgCode(companyPlace
						.getOrginternalcode());
				Organization org = new Organization();
				org.setId(companyPlace.getOrg().getId());
				systemOperateLog.setDataOrgId(org);
			}

			if (companyPlaceBusiness.getIsKeyType().equals(
					IsKeyType.PRODUCTION_KEY_TYPE)) {
				systemOperateLog
						.setOperateType(SystemOperateType.ADD_SAFETYPRODUCTIONKEY);
			}
			if (companyPlaceBusiness.getIsKeyType().equals(
					IsKeyType.FIRESAFETY_KEY_TYPE)) {
				systemOperateLog
						.setOperateType(SystemOperateType.ADD_FIRESAFETYKEY);
			}
			if (companyPlaceBusiness.getIsKeyType().equals(
					IsKeyType.SECURITY_KEY_TYPE)) {
				systemOperateLog
						.setOperateType(SystemOperateType.ADD_SECURITYKEY);
			}
			if (companyPlaceBusiness.getIsKeyType().equals(
					IsKeyType.POLLUTION_SOURCE_TYPE)) {
				systemOperateLog
						.setOperateType(SystemOperateType.ADD_POLLUTIONSOURCE);
			}
			systemOperateLogService.addSystemOperateLog(systemOperateLog);
		} catch (Exception e) {
			logger.error("记录日志时出错：", e);
		} catch (Throwable e) {
			logger.error("记录日志时出错：", e);
		}
		return retVal;
	}

	@Before("execution(public *  com.tianque.baseInfo.companyPlace.dao.CompanyPlaceBusinessDAO.update*(..)) && args(baseDomain,..)")
	public void createActualCompanyPlaceBusunessUpdateLog(JoinPoint point,
			BaseDomain baseDomain) {
		try {
			initSystemOperateLog();
			// 获取执行方法的参数
			CompanyPlaceBusiness afterCompanyPlaceBusiness = (CompanyPlaceBusiness) baseDomain;
			CompanyPlaceBusiness beforCompanyPlaceBusiness = null;
			if (afterCompanyPlaceBusiness == null) {
				logger.error("信息未找到");
				return;
			}
			if (StringUtils.isBlank(afterCompanyPlaceBusiness
					.getCompanyPlaceSource())) {
				systemOperateLog
						.setOperateSource(NewBaseInfoTables.COMPANYPLACEKEY);
			} else {
				systemOperateLog.setOperateSource(afterCompanyPlaceBusiness
						.getCompanyPlaceSource());
			}
			beforCompanyPlaceBusiness = companyPlaceBusinessService
					.getCompanyPlaceBusiness(afterCompanyPlaceBusiness.getId());

			CompanyPlace companyPlace = companyPlaceBaseSevice
					.getCompanyPlaceInfoByCid(afterCompanyPlaceBusiness
							.getCompanyPlaceId());
			if (afterCompanyPlaceBusiness != null) {
				systemOperateLog.setDataAfterOperate(gson
						.toJson(afterCompanyPlaceBusiness));
			}
			systemOperateLog.setDataBeforeOperate(gson
					.toJson(beforCompanyPlaceBusiness));
			systemOperateLog.setContrastState(ContrastState.UNKNOWN);
			systemOperateLog.setDataId(companyPlace.getCid());
			systemOperateLog.setModuleType(NewBaseInfoTables.COMPANYPLACEKEY);
			systemOperateLog.setDataKeyword(String.valueOf(companyPlace
					.getCid()));
			systemOperateLog
					.setBusinessType(NewBaseInfoTables.COMPANYPLACEBUSINESS);
			systemOperateLog.setDataName(companyPlace.getAddress());
			if (companyPlace.getOrg() != null) {
				systemOperateLog.setDataOrgCode(companyPlace
						.getOrginternalcode());
				Organization org = new Organization();
				org.setId(companyPlace.getOrg().getId());
				systemOperateLog.setDataOrgId(org);
			}

			if (afterCompanyPlaceBusiness.getIsKeyType().equals(
					IsKeyType.PRODUCTION_KEY_TYPE)) {
				systemOperateLog
						.setOperateType(SystemOperateType.UPDATE_SAFETYPRODUCTIONKEY);
			}
			if (afterCompanyPlaceBusiness.getIsKeyType().equals(
					IsKeyType.FIRESAFETY_KEY_TYPE)) {
				systemOperateLog
						.setOperateType(SystemOperateType.UPDATE_FIRESAFETYKEY);
			}
			if (afterCompanyPlaceBusiness.getIsKeyType().equals(
					IsKeyType.SECURITY_KEY_TYPE)) {
				systemOperateLog
						.setOperateType(SystemOperateType.UPDATE_SECURITYKEY);
			}
			if (afterCompanyPlaceBusiness.getIsKeyType().equals(
					IsKeyType.POLLUTION_SOURCE_TYPE)) {
				systemOperateLog
						.setOperateType(SystemOperateType.UPDATE_POLLUTIONSOURCE);
			}
			systemOperateLogService.addSystemOperateLog(systemOperateLog);
		} catch (Exception e) {
			logger.error("记录日志时出错：", e);
		} catch (Throwable e) {
			logger.error("记录日志时出错：", e);
		}
	}

	@Before("execution(public * com.tianque.baseInfo.companyPlace.dao.CompanyPlaceBaseDAO.update*(..)) &&  args(baseDomain,..)")
	public void createActualCompanyPlaceUpdateLog(JoinPoint point,
			BaseDomain baseDomain) {
		try {
			initSystemOperateLog();
			CompanyPlace companyPlace = null;
			companyPlace = (CompanyPlace) baseDomain;
			systemOperateLog = createActualCompanyPlaceLogAll(companyPlace,
					SystemOperateType.UPDATE);
			systemOperateLog.setOperateType(SystemOperateType.UPDATE);
			systemOperateLogService.addSystemOperateLog(systemOperateLog);
		} catch (Exception e) {
			logger.error("记录日志时出错：", e);
		}
	}

	@Before("execution(public * com.tianque.baseInfo.companyPlace.dao.CompanyPlaceBaseDAO.updateCompanyPlaceBaseOrgById(..)) && args(baseDomain,..)")
	public void transferCompanyPlaceLog(BaseDomain baseDomain) {
		try {
			initSystemOperateLog();
			// 获取执行方法的参数
			CompanyPlace companyPlace = (CompanyPlace) baseDomain;
			if (StringUtils.isBlank(companyPlace.getCompanyPlaceSource())) {
				systemOperateLog
						.setOperateSource(NewBaseInfoTables.COMPANYPLACEKEY);
			} else {
				systemOperateLog.setOperateSource(companyPlace
						.getCompanyPlaceSource());
			}
			systemOperateLog.setDataAfterOperate(gson.toJson(companyPlace));
			CompanyPlace afterCompanyPlace = companyPlaceBaseSevice
					.getCompanyPlaceInfoByCid(companyPlace.getCid());
			if (afterCompanyPlace != null) {
				systemOperateLog.setDataBeforeOperate(gson
						.toJson(afterCompanyPlace));
			}
			systemOperateLog.setDataBeforeOrgId(companyPlace.getOrg());
			systemOperateLog.setContrastState(ContrastState.MODIFIED);
			systemOperateLog.setDataId(afterCompanyPlace.getCid());
			systemOperateLog.setModuleType(NewBaseInfoTables.COMPANYPLACEKEY);
			systemOperateLog.setDataKeyword(afterCompanyPlace.getCid()
					.toString());
			systemOperateLog.setBusinessType(NewBaseInfoTables.COMPANYPLACEKEY);
			systemOperateLog.setDataName(afterCompanyPlace.getAddress());
			if (companyPlace.getOrg() != null) {
				systemOperateLog.setDataOrgCode(afterCompanyPlace
						.getOrginternalcode());
				Organization org = new Organization();
				org.setId(afterCompanyPlace.getOrg().getId());
				systemOperateLog.setDataOrgId(org);
			}

			systemOperateLog.setOperateType(SystemOperateType.TRANSFER);
			systemOperateLogService.addSystemOperateLog(systemOperateLog);

		} catch (Exception e) {
			logger.error("记录日志时出错：", e);
		} catch (Throwable e) {
			logger.error("记录日志时出错：", e);
		}
	}

	/**
	 * 关注、取消关注
	 * 
	 * @param rentalHouse
	 * @param baseInfo
	 * @param operateType
	 * @return
	 */
	@Before("execution(public * com.tianque.baseInfo.companyPlace.dao.CompanyPlaceBaseDAO.updateCompanyPlaceBaseEmphasisById(..)) &&  args(baseDomain,..)")
	public void updateEmphasiseLog(BaseDomain baseDomain) {
		try {
			initSystemOperateLog();
			// 获取执行方法的参数
			CompanyPlace companyPlace = (CompanyPlace) baseDomain;
			if (companyPlace == null) {
				logger.error("信息未找到");
				return;
			}
			if (StringUtils.isBlank(companyPlace.getCompanyPlaceSource())) {
				systemOperateLog
						.setOperateSource(NewBaseInfoTables.COMPANYPLACEKEY);
			} else {
				systemOperateLog.setOperateSource(companyPlace
						.getCompanyPlaceSource());
			}
			if (companyPlace.getIsEmphasis() == null
					&& companyPlace.getIsEmphasisDate() == null
					&& companyPlace.getIsEmphasisReason() == null) {
				return;
			}

			if (companyPlace != null) {
				systemOperateLog
						.setDataBeforeOperate(gson.toJson(companyPlace));
			}
			systemOperateLog.setContrastState(ContrastState.MODIFIED);
			systemOperateLog.setDataId(companyPlace.getCid());
			systemOperateLog.setModuleType(NewBaseInfoTables.COMPANYPLACEKEY);
			systemOperateLog.setDataKeyword(companyPlace.getCid().toString());
			systemOperateLog.setBusinessType(NewBaseInfoTables.COMPANYPLACEKEY);
			systemOperateLog.setDataName(companyPlace.getAddress());
			if (companyPlace.getOrg() != null) {
				systemOperateLog.setDataOrgCode(companyPlace
						.getOrginternalcode());
				Organization org = new Organization();
				org.setId(companyPlace.getOrg().getId());
				systemOperateLog.setDataOrgId(org);
			}

			if (companyPlace.getIsEmphasis() == 1) {// 1是取消关注
				systemOperateLog.setOperateType(SystemOperateType.EMPHASISE);
			} else if (companyPlace.getIsEmphasis() == 0
					|| companyPlace.getIsEmphasis() == null) {
				systemOperateLog.setOperateType(SystemOperateType.REEMPHASISE);
			}
			systemOperateLogService.addSystemOperateLog(systemOperateLog);
		} catch (Exception e) {
			logger.error("记录日志时出错：", e);
		} catch (Throwable e) {
			logger.error("记录日志时出错：", e);
		}
	}

	private SystemOperateLog createActualCompanyPlaceLogAll(
			BaseDomain baseDomain, Integer operateType) {
		if (baseDomain == null) {
			return null;
		}
		CompanyPlace companyPlace = (CompanyPlace) baseDomain;
		CompanyPlace afterCompanyPlace = null;
		if (StringUtils.isBlank(companyPlace.getCompanyPlaceSource())) {
			systemOperateLog
					.setOperateSource(NewBaseInfoTables.COMPANYPLACEKEY);
		} else {
			systemOperateLog.setOperateSource(companyPlace
					.getCompanyPlaceSource());
		}
		if (operateType == SystemOperateType.UPDATE) {
			systemOperateLog.setDataAfterOperate(gson.toJson(companyPlace));
			afterCompanyPlace = companyPlaceBaseSevice
					.getCompanyPlaceInfoByCid(companyPlace.getCid());
			if (afterCompanyPlace != null) {
				systemOperateLog.setDataBeforeOperate(gson
						.toJson(afterCompanyPlace));
			}
			systemOperateLog.setContrastState(ContrastState.UNKNOWN);
			systemOperateLog.setDataId(afterCompanyPlace.getCid());
			systemOperateLog.setModuleType(NewBaseInfoTables.COMPANYPLACEKEY);
			systemOperateLog.setDataKeyword(afterCompanyPlace.getCid()
					.toString());
			systemOperateLog.setBusinessType(NewBaseInfoTables.COMPANYPLACEKEY);
			systemOperateLog.setDataName(afterCompanyPlace.getAddress());
			if (companyPlace.getOrg() != null) {
				systemOperateLog.setDataOrgCode(afterCompanyPlace
						.getOrginternalcode());
				Organization org = new Organization();
				org.setId(afterCompanyPlace.getOrg().getId());
				systemOperateLog.setDataOrgId(org);
			}
		} else {
			systemOperateLog.setContrastState(ContrastState.UNMODIFIED);
			systemOperateLog.setDataId(companyPlace.getCid());
			systemOperateLog.setModuleType(NewBaseInfoTables.COMPANYPLACEKEY);
			systemOperateLog
					.setDataKeyword(String.valueOf(companyPlace.getId()));
			systemOperateLog.setBusinessType(NewBaseInfoTables.COMPANYPLACEKEY);
			systemOperateLog.setDataName(companyPlace.getAddress());
			if (companyPlace.getOrg() != null) {
				systemOperateLog.setDataOrgCode(companyPlace
						.getOrginternalcode());
				Organization org = new Organization();
				org.setId(companyPlace.getOrg().getId());
				systemOperateLog.setDataOrgId(org);
			}
		}
		return systemOperateLog;
	}
}

package com.tianque.core.aspect;

import java.lang.reflect.Method;
import java.util.Map;

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
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.base.service.AddressInfoService;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.exception.base.IllegalOperationException;
import com.tianque.exception.base.OperationFailedException;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.systemOperateLog.domain.SystemOperateLog;
import com.tianque.systemOperateLog.service.SystemOperateLogService;
import com.tianque.systemOperateLog.util.ContrastState;
import com.tianque.systemOperateLog.util.SystemOperateType;

@Aspect
@Repository("systemOperateLogAspect")
public class SystemOperateLogAspect {
	final static Logger logger = LoggerFactory
			.getLogger(SystemOperateLogAspect.class);

	@Autowired
	private SystemOperateLogService systemOperateLogService;
	@Autowired
	private AddressInfoService addressInfoService;

	private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting()
			.setVersion(2.2).create();

	private SystemOperateLog systemOperateLog;

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

	@Around("execution(public * com.tianque.baseInfo.base.dao.BaseInfoDao.add(..))")
	public Object createBaseInfoAddLog(ProceedingJoinPoint pjp) {
		Object retVal = null; // 连接点方法返回值
		try {
			// 获取执行方法的参数
			Object arg = pjp.getArgs()[0];
			initSystemOperateLog();
			if (arg instanceof Countrymen) {
				BaseDomain baseDomain = (BaseDomain) arg;
				systemOperateLog = createPopulationLog(baseDomain,
						NewBaseInfoTables.BASEINFO_KEY, SystemOperateType.ADD);
				if (ConstantsProduct.SourcesState.IMPORT.equals(baseDomain
						.getSourcesState())) {
					systemOperateLog.setOperateType(SystemOperateType.IMPORT);
				} else if (ConstantsProduct.SourcesState.SETTLED
						.equals(baseDomain.getSourcesState())) {
					systemOperateLog.setOperateType(SystemOperateType.SETTLED);
				} else {
					systemOperateLog.setOperateType(SystemOperateType.ADD);
				}
				retVal = pjp.proceed();
				systemOperateLog.setDataId(((BaseDomain) retVal).getId());
				systemOperateLogService.addSystemOperateLog(systemOperateLog);
			}
		} catch (Exception e) {
			logger.error("记录日志时出错：", e);
		} catch (Throwable e) {
			logger.error("记录日志时出错：", e);
		}
		return retVal;
	}

	@Before("execution(public * com.tianque.baseInfo.base.dao.BaseInfoDao.update(..)) &&  args(baseDomain,..)")
	public void createBaseInfoUpdateLog(BaseDomain baseDomain) {
		try {
			if (ConstantsProduct.SourcesState.JOB.equals(baseDomain
					.getSourcesState())
					|| ConstantsProduct.SourcesState.TRANSFER.equals(baseDomain
							.getSourcesState())) {
				return;
			}
			initSystemOperateLog();
			if (baseDomain instanceof Countrymen) {
				Countrymen countrymen = (Countrymen) baseDomain;
				if (countrymen.getAttentionPopulationType() != null
						&& !"".equals(countrymen.getAttentionPopulationType())) {
					systemOperateLog.setOperateSource(countrymen
							.getAttentionPopulationType());
				} else {
					String sourceName = baseDomain.getClass().getSimpleName();
					systemOperateLog.setOperateSource(sourceName
							.substring(0, 1).toLowerCase()
							+ sourceName.substring(1));
				}
				if (countrymen.isDeath()) {
					systemOperateLog.setDataOrgId(countrymen.getOrganization());
					systemOperateLog.setDataOrgCode(countrymen
							.getOrgInternalCode());
					systemOperateLog.setOperateType(SystemOperateType.ISDEATH);
					systemOperateLog.setDataKeyword(countrymen.getIdCardNo());
					systemOperateLog.setDataName(countrymen.getName());
					systemOperateLog
							.setModuleType(NewBaseInfoTables.PEOPLE_KEY);
					systemOperateLog
							.setBusinessType(NewBaseInfoTables.BASEINFO_KEY);
					systemOperateLog.setContrastState(ContrastState.UNMODIFIED);
					systemOperateLogService
							.addSystemOperateLog(systemOperateLog);
				}
				systemOperateLog = createPopulationLog(baseDomain,
						NewBaseInfoTables.BASEINFO_KEY,
						SystemOperateType.UPDATE);
				systemOperateLog.setOperateType(SystemOperateType.UPDATE);
				systemOperateLogService.addSystemOperateLog(systemOperateLog);
			}
		} catch (Exception e) {
			logger.error("记录日志时出错：", e);
		} catch (Throwable e) {
			logger.error("记录日志时出错：", e);
		}
	}

	@Around("(execution(public * com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao.add*(..))) || "
			+ "(execution(public * com.tianque.baseInfo.householdStaff.dao.HouseholdStaffDao.addHouseholdStaff(..)))")
	public Object createBusinessAddLog(ProceedingJoinPoint pjp) {
		Object retVal = null; // 连接点方法返回值
		try {
			// 获取执行方法的参数
			Object arg = pjp.getArgs()[0];
			initSystemOperateLog();
			if (arg instanceof Countrymen) {
				BaseDomain baseDomain = (BaseDomain) arg;
				if (ConstantsProduct.SourcesState.TRANSFER.equals(baseDomain
						.getSourcesState())) {
					retVal = pjp.proceed();
					return retVal;
				}
				systemOperateLog = createPopulationLog(baseDomain, null,
						SystemOperateType.ADD);
				if (ConstantsProduct.SourcesState.JOB.equals(baseDomain
						.getSourcesState())) {
					baseDomain
							.setSourcesState(ConstantsProduct.SourcesState.ADDED);
				}
				if (ConstantsProduct.SourcesState.IMPORT.equals(baseDomain
						.getSourcesState())) {
					systemOperateLog.setOperateType(SystemOperateType.IMPORT);
				} else if (ConstantsProduct.SourcesState.SETTLED
						.equals(baseDomain.getSourcesState())) {
					systemOperateLog.setOperateType(SystemOperateType.SETTLED);
				} else if (ConstantsProduct.SourcesState.CLAIM
						.equals(baseDomain.getSourcesState())) {
					systemOperateLog.setOperateType(SystemOperateType.CLAIM);
				}else {
					systemOperateLog.setOperateType(SystemOperateType.ADD);
				}
				retVal = pjp.proceed();
				systemOperateLog.setDataId(((BaseDomain) retVal).getId());
				systemOperateLogService.addSystemOperateLog(systemOperateLog);
			}
		} catch (Exception e) {
			logger.error("记录日志时出错：", e);
		} catch (Throwable e) {
			logger.error("记录日志时出错：", e);
		}
		return retVal;
	}

	@Before("execution(public * com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao.updateBusiness*(..)) &&  args(baseDomain,..)")
	public void createPopulationUpdateLog(BaseDomain baseDomain) {
		try {
			if (ConstantsProduct.SourcesState.IMPORT.equals(baseDomain
					.getSourcesState())) {
				return;
			}
			initSystemOperateLog();
			if (baseDomain instanceof Countrymen) {
				systemOperateLog = createPopulationLog(baseDomain, null,
						SystemOperateType.UPDATE);
				systemOperateLog.setOperateType(SystemOperateType.UPDATE);
				systemOperateLogService.addSystemOperateLog(systemOperateLog);
			}
		} catch (Exception e) {
			logger.error("记录日志时出错：", e);
		} catch (Throwable e) {
			logger.error("记录日志时出错：", e);
		}
	}

	@Before("execution(public * com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao.update(..)) &&  args(baseDomain,..)")
	public void createBusinessUpdateLog(BaseDomain baseDomain) {
		try {
			initSystemOperateLog();
			if (baseDomain instanceof Countrymen) {
				Countrymen countrymen = (Countrymen) baseDomain;
				if (countrymen.isDeath()
						&& (NewBaseInfoTables.UNSETTLEDPOPULATION_KEY
								.equals(countrymen.getActualPopulationType()) || NewBaseInfoTables.OVERSEASTAFF_KEY
								.equals(countrymen.getActualPopulationType()))) {
					systemOperateLog.setDataOrgId(countrymen.getOrganization());
					systemOperateLog.setDataOrgCode(countrymen
							.getOrgInternalCode());
					systemOperateLog.setOperateType(SystemOperateType.ISDEATH);
					systemOperateLog.setDataKeyword(countrymen.getIdCardNo());
					systemOperateLog.setContrastState(ContrastState.UNMODIFIED);
					systemOperateLog.setDataName(countrymen.getName());
					systemOperateLog.setDataId(countrymen.getId());
					systemOperateLog
							.setModuleType(NewBaseInfoTables.PEOPLE_KEY);
					systemOperateLog.setOperateSource(countrymen
							.getActualPopulationType());
					systemOperateLog.setBusinessType(countrymen
							.getActualPopulationType());
					systemOperateLogService
							.addSystemOperateLog(systemOperateLog);
				}
				systemOperateLog = createPopulationLog(baseDomain, null,
						SystemOperateType.UPDATE);
				systemOperateLog.setOperateType(SystemOperateType.UPDATE);
				systemOperateLogService.addSystemOperateLog(systemOperateLog);
			}
		} catch (Exception e) {
			logger.error("记录日志时出错：", e);
		} catch (Throwable e) {
			logger.error("记录日志时出错：", e);
		}
	}

	@Before("execution(public * com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao.updateLogOutByMap(..)) &&  args(map,..)")
	public void createUpdateLogOutStateLog(Map<String, Object> map) {
		try {
			initSystemOperateLog();
			LogoutDetail logoutDetail = (LogoutDetail) map.get("logoutDetail");
			if (IsEmphasis.Emphasis.equals(logoutDetail.getLogout())) {
				systemOperateLog
						.setOperateType(SystemOperateType.CANCEL_ISLOGOUT);
			} else {
				systemOperateLog.setOperateType(SystemOperateType.ISLOGOUT);
			}
			systemOperateLog.setContrastState(ContrastState.UNMODIFIED);
			systemOperateLog.setOperateSource((String) map
					.get("populationType"));
			Countrymen countrymen = (Countrymen) getPopulationByIdAndType(
					(String) map.get("populationType"), (Long) map.get("id"));
			systemOperateLog.setDataId((Long) map.get("id"));
			systemOperateLog.setDataKeyword(countrymen.getIdCardNo());
			systemOperateLog.setDataName(countrymen.getName());
			systemOperateLog.setDataOrgCode(countrymen.getOrgInternalCode());
			Organization org = new Organization();
			org.setId(countrymen.getOrganization().getId());
			systemOperateLog.setDataOrgId(org);
			systemOperateLog.setModuleType(NewBaseInfoTables.PEOPLE_KEY);
			systemOperateLog
					.setBusinessType((String) map.get("populationType"));
			systemOperateLogService.addSystemOperateLog(systemOperateLog);
		} catch (Exception e) {
			logger.error("记录日志时出错：", e);
		} catch (Throwable e) {
			logger.error("记录日志时出错：", e);
		}
	}

	@Before("execution(public * com.tianque.baseInfo.base.dao.BaseInfoDao.updateDeathStateById(..)) &&  args(map,..)")
	public void createUpdateIsDeathStateLog(Map<String, Object> map) {
		try {
			initSystemOperateLog();
			systemOperateLog.setOperateType(SystemOperateType.CANCEL_ISDEATH);
			Countrymen countrymen = (Countrymen) getPopulationByIdAndType(
					NewBaseInfoTables.BASEINFO_KEY, (Long) map.get("id"));
			systemOperateLog.setDataKeyword(countrymen.getIdCardNo());
			systemOperateLog.setDataName(countrymen.getName());
			systemOperateLog.setModuleType(NewBaseInfoTables.PEOPLE_KEY);
			systemOperateLog.setDataKeyword((String) map.get("idCardNo"));
			Organization org = new Organization();
			org.setId((Long) map.get("orgId"));
			systemOperateLog.setDataOrgId(org);
			systemOperateLog.setDataOrgCode((String) map.get("orgCode"));
			systemOperateLog.setOperateSource((String) map
					.get("operateScource"));
			systemOperateLog.setContrastState(ContrastState.UNMODIFIED);
			systemOperateLog.setBusinessType(NewBaseInfoTables.BASEINFO_KEY);
			systemOperateLogService.addSystemOperateLog(systemOperateLog);
		} catch (Exception e) {
			logger.error("记录日志时出错：", e);
		} catch (Throwable e) {
			logger.error("记录日志时出错：", e);
		}
	}

	private Object getPopulationByIdAndType(String populationType,
			Long populationId) {
		Object object;
		if ((NewBaseInfoTables.AIDSPOPULATIONS_KEY + "s")
				.equalsIgnoreCase(populationType)) {
			String temp = NewBaseInfoTables.AIDSPOPULATIONS_KEY;
			object = SpringBeanUtil.getBeanFromSpringByBeanName(temp.substring(
					0, 1).toLowerCase()
					+ temp.substring(1) + "Service");

		} else if (BaseInfoTables.OVERSEAPERSONNEL_KEY
				.equalsIgnoreCase(populationType)) {
			object = SpringBeanUtil.getBeanFromSpringByBeanName(populationType
					.substring(0, 1).toLowerCase()
					+ populationType.substring(1) + "Service");
			populationType = "overseaPersonnel";
		} else if (NewBaseInfoTables.OVERSEAPERSONNEL_KEY
				.equalsIgnoreCase(populationType)) {
			object = SpringBeanUtil
					.getBeanFromSpringByBeanName(NewBaseInfoTables.OVERSEASTAFF_KEY
							.substring(0, 1).toLowerCase()
							+ NewBaseInfoTables.OVERSEASTAFF_KEY.substring(1)
							+ "Service");
		} else {
			object = SpringBeanUtil.getBeanFromSpringByBeanName(populationType
					.substring(0, 1).toLowerCase()
					+ populationType.substring(1) + "Service");
		}
		String methodName = "get"
				+ populationType.substring(0, 1).toUpperCase()
				+ populationType.substring(1) + "ById";
		try {
			Method method = object.getClass().getMethod(methodName, Long.class);
			return method.invoke(object, new Object[] { populationId });
		} catch (Exception e) {
			throw new OperationFailedException("获取人口信息出错:", e);
		}
	}

	private SystemOperateLog createPopulationLog(BaseDomain baseDomain,
			String baseInfo, Integer operateType) {
		Countrymen temp = (Countrymen) baseDomain;
		String className;
		if (temp.getAttentionPopulationType() != null
				&& !"".equals(temp.getAttentionPopulationType())) {
			systemOperateLog
					.setOperateSource(temp.getAttentionPopulationType());
		} else {
			String sourceName = baseDomain.getClass().getSimpleName();
			systemOperateLog.setOperateSource(sourceName.substring(0, 1)
					.toLowerCase() + sourceName.substring(1));
		}
		if (baseInfo == null) {
			className = baseDomain.getClass().getSimpleName();
		} else {
			className = baseInfo;
		}

		Countrymen countrymen;
		if (operateType == SystemOperateType.UPDATE) {
			if (baseInfo == null) {
				systemOperateLog.setDataAfterOperate(gson.toJson(baseDomain));
				countrymen = (Countrymen) getPopulationByIdAndType(className,
						baseDomain.getId());
				// 轨迹修改时基本信息页面 房屋编号不比较
				countrymen.setHouseCode(null);
			} else {
				systemOperateLog.setDataAfterOperate(gson.toJson((temp)));
				countrymen = (Countrymen) getPopulationByIdAndType(className,
						temp.getBaseInfoId());
				if (temp.getAddressInfoId() != null) {
					Countrymen addressInfo = addressInfoService.get(temp
							.getAddressInfoId());
					clearPopulationHouseInfo(countrymen, addressInfo);
				}
			}
			systemOperateLog.setDataBeforeOperate(gson.toJson(countrymen));
			systemOperateLog.setContrastState(ContrastState.UNKNOWN);
		} else {
			countrymen = (Countrymen) baseDomain;
			systemOperateLog.setContrastState(ContrastState.UNMODIFIED);
		}
		systemOperateLog.setDataId(countrymen.getId());
		systemOperateLog.setModuleType(NewBaseInfoTables.PEOPLE_KEY);
		systemOperateLog.setBusinessType(className.substring(0, 1)
				.toLowerCase() + className.substring(1));
		systemOperateLog.setDataKeyword(countrymen.getIdCardNo());
		systemOperateLog.setDataName(countrymen.getName());
		if (countrymen.getOrganization() != null) {
			systemOperateLog.setDataOrgCode(countrymen.getOrgInternalCode());
			Organization org = new Organization();
			org.setId(countrymen.getOrganization().getId());
			systemOperateLog.setDataOrgId(org);
		} else if (temp.getOrganization() != null) {
			systemOperateLog.setDataOrgCode(temp.getOrgInternalCode());
			Organization org = new Organization();
			org.setId(temp.getOrganization().getId());
			systemOperateLog.setDataOrgId(org);
		}
		return systemOperateLog;
	}

	private void clearPopulationHouseInfo(Countrymen countrymen,
			Countrymen addressInfo) {
		if (addressInfo == null) {
			return;
		}
		countrymen.setIsHaveHouse(addressInfo.getIsHaveHouse());
		countrymen.setNoHouseReason(addressInfo.getNoHouseReason());
		countrymen.setCurrentAddress(addressInfo.getCurrentAddress());
		countrymen.setOtherAddress(addressInfo.getOtherAddress());
		countrymen.setRemark(addressInfo.getRemark());
	}
}

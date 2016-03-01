package com.tianque.core.aspect;

import java.lang.reflect.Method;
import java.util.Map;

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
import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.exception.base.IllegalOperationException;
import com.tianque.exception.base.OperationFailedException;
import com.tianque.systemOperateLog.domain.SystemOperateLog;
import com.tianque.systemOperateLog.service.SystemOperateLogService;
import com.tianque.systemOperateLog.util.ContrastState;
import com.tianque.systemOperateLog.util.DoNotRecordMethod;
import com.tianque.systemOperateLog.util.SystemOperateType;

@Aspect
@Repository("houseOperateLogAspect")
public class HouseOperateLogAspect {

	final static Logger logger = LoggerFactory
			.getLogger(SystemOperateLogAspect.class);
	private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting()
			.setVersion(2.2).create();

	private SystemOperateLog systemOperateLog;

	@Autowired
	private SystemOperateLogService systemOperateLogService;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private RentalHouseService rentalHouseService;

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

	/*
	 * 
	 * @param pjp
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	@Around("execution(public *  com.tianque.baseInfo.*House.dao.*HouseDao.add*(..))")
	public Object createActualHouseInfoAddLog(ProceedingJoinPoint pjp) {
		Object retVal = null; // 连接点方法返回值
		try {

			// 获取执行方法的参数
			Object arg = pjp.getArgs()[0];
			initSystemOperateLog();
			retVal = pjp.proceed();
			if (arg == null) {
				return retVal;
			}
			HouseInfo houseInfo = (HouseInfo) arg;
			systemOperateLog = createActualHouseLogAll(houseInfo,
					SystemOperateType.ADD);

			if (ConstantsProduct.SourcesState.IMPORT.equals(houseInfo
					.getSourcesState())) {
				systemOperateLog.setOperateType(SystemOperateType.IMPORT);
			} else {
				systemOperateLog.setOperateType(SystemOperateType.ADD);
			}
			systemOperateLog.setDataId(((BaseDomain) retVal).getId());
			systemOperateLogService.addSystemOperateLog(systemOperateLog);
		} catch (Exception e) {
			logger.error("记录日志时出错：", e);
		} catch (Throwable e) {
			logger.error("记录日志时出错：", e);
		}
		return retVal;
	}

	@Before("execution(public * com.tianque.baseInfo.*House.dao.*HouseDao.updateHouse*(..)) &&  args(baseDomain,..)")
	public void createActualHouseUpdateLog(JoinPoint point,
			BaseDomain baseDomain) {
		try {
			initSystemOperateLog();
			HouseInfo houseInfo = null;
			if (baseDomain instanceof RentalHouse) {
				String methodName = point.getSignature().getName();
				if (DoNotRecordMethod.METHODNAME.equals(methodName)) {
					return;
				}
				houseInfo = (RentalHouse) baseDomain;

			} else {
				houseInfo = (HouseInfo) baseDomain;
			}
			systemOperateLog = createActualHouseLogAll(houseInfo,
					SystemOperateType.UPDATE);
			systemOperateLog.setOperateType(SystemOperateType.UPDATE);
			systemOperateLogService.addSystemOperateLog(systemOperateLog);
		} catch (Exception e) {
			logger.error("记录日志时出错：", e);
		} catch (Throwable e) {
			logger.error("记录日志时出错：", e);
		}
	}

	@Around("execution(public * com.tianque.baseInfo.*House.dao.*HouseDao.delete*(..))")
	public void createBaseInfoDeleteLog(ProceedingJoinPoint pjp) {
		try {
			Long houseInfoId = (Long) pjp.getArgs()[0];
			String shardCode = null;
			if(pjp.getArgs().length > 1){
				shardCode = (String) pjp.getArgs()[1];
			}
			initSystemOperateLog();
			if (houseInfoId != null) {
				HouseInfo houseInfo = null;
				RentalHouse rentalHouse = null;
				if (shardCode != null) {
					// 房屋信息
					houseInfo = actualHouseService
							.getActualHouseById(houseInfoId);
					if (houseInfo != null) {
						systemOperateLog
								.setOperateSource(NewBaseInfoTables.ACTUALHOUSE_KEY);
						systemOperateLog = createActualHouseLogAll(houseInfo,
								SystemOperateType.DELETE);
					}
				} else {
					// 出租房
					rentalHouse = rentalHouseService
							.getRentalHouseById(houseInfoId);
					if (rentalHouse != null) {
						systemOperateLog
								.setOperateSource(NewBaseInfoTables.RENTALHOUSE_KEY);
						systemOperateLog = createActualHouseLogAll(rentalHouse,
								SystemOperateType.DELETE);
					}
				}
				systemOperateLog.setOperateType(SystemOperateType.DELETE);
				pjp.proceed();
				systemOperateLogService.addSystemOperateLog(systemOperateLog);
			}
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
	@Before("execution(public * com.tianque.baseInfo.rentalHouse.dao.RentalHouseDao.updateEmphasiseById(..)) &&  args(map,..)")
	public void updateEmphasiseLog(Map<String, Object> map) {
		Long houseId = (Long) map.get("houseId");
		Long isEmphasis = (Long) map.get("isEmphasis");
		try {
			initSystemOperateLog();
			if (houseId != null) {
				RentalHouse rentalHouse = (RentalHouse) getPopulationByIdAndType(
						NewBaseInfoTables.RENTALHOUSE_KEY, houseId);
				if (rentalHouse == null) {
					throw new OperationFailedException("房屋信息未找到");
				}
				if (isEmphasis == null) {
					throw new OperationFailedException("房屋信息注销状态未找到");
				}

				if (rentalHouse.getHouseOperateSource() != null
						&& rentalHouse.getHouseOperateSource().trim().length() != 0) {
					systemOperateLog.setOperateSource(rentalHouse
							.getHouseOperateSource());
				} else {
					systemOperateLog
							.setOperateSource(NewBaseInfoTables.RENTALHOUSE_KEY);
				}
				if (isEmphasis == 1) {// 1是注销
					systemOperateLog.setOperateType(SystemOperateType.ISLOGOUT);
				} else if (isEmphasis == 0 || isEmphasis == null) {
					systemOperateLog
							.setOperateType(SystemOperateType.CANCEL_ISLOGOUT);
				}
				systemOperateLog.setContrastState(ContrastState.UNMODIFIED);
				systemOperateLog.setDataId(rentalHouse.getId());
				if (rentalHouse.getHouseId() != null) {
					systemOperateLog.setDataKeyword(rentalHouse.getHouseId()
							.toString());
				}
				systemOperateLog.setDataName(rentalHouse.getAddress());
				systemOperateLog.setDataOrgCode(rentalHouse
						.getOrgInternalCode());
				Organization org = new Organization();
				if (rentalHouse.getOrganization() != null) {
					org.setId(rentalHouse.getOrganization().getId());
					systemOperateLog.setDataOrgId(org);
				}
				systemOperateLog.setModuleType(NewBaseInfoTables.PEOPLE_KEY);
				systemOperateLog
						.setBusinessType(NewBaseInfoTables.RENTALHOUSE_KEY);
				systemOperateLogService.addSystemOperateLog(systemOperateLog);
			}
		} catch (Exception e) {
			logger.error("记录日志时出错：", e);
		} catch (Throwable e) {
			logger.error("记录日志时出错：", e);
		}
	}

	private SystemOperateLog createActualHouseLogAll(BaseDomain baseDomain,
			Integer operateType) {
		if (baseDomain == null || baseDomain.getId() == null) {
			return new SystemOperateLog();
		}
		HouseInfo houseInfo = null;
		if (baseDomain instanceof RentalHouse) {
			houseInfo = (RentalHouse) baseDomain;
		} else {
			houseInfo = (HouseInfo) baseDomain;
		}
		String className;
		className = houseInfo.getClass().getSimpleName();// HouseInfo
		if (houseInfo.getHouseOperateSource() != null
				&& houseInfo.getHouseOperateSource().trim().length() != 0) {
			systemOperateLog
					.setOperateSource(houseInfo.getHouseOperateSource());
		} else {
			systemOperateLog
					.setOperateSource(NewBaseInfoTables.ACTUALHOUSE_KEY);
		}
		HouseInfo houseTmp = null;
		RentalHouse rentalHouse = null;
		if (baseDomain instanceof RentalHouse) {
			Object obj = getPopulationByIdAndType(
					NewBaseInfoTables.RENTALHOUSE_KEY, houseInfo.getId());
			if (obj instanceof RentalHouse) {
				rentalHouse = (RentalHouse) obj;
			}
			houseTmp = rentalHouse;
		} else {
			Object obj = getPopulationByIdAndType(className, houseInfo.getId());
			if (obj instanceof HouseInfo) {
				houseTmp = (HouseInfo) obj;
			}
		}

		if (houseTmp == null) {
			houseTmp = houseInfo;
		}

		if (operateType == SystemOperateType.UPDATE) {
			systemOperateLog.setDataAfterOperate(gson.toJson(houseInfo));
			systemOperateLog.setDataBeforeOperate(gson.toJson(houseTmp));
			systemOperateLog.setContrastState(ContrastState.UNKNOWN);
		} else {
			systemOperateLog.setContrastState(ContrastState.UNMODIFIED);
		}

		systemOperateLog.setDataId(houseTmp.getId());
		systemOperateLog.setModuleType(NewBaseInfoTables.HOUSE_KEY);
		if (NewBaseInfoTables.RENTALHOUSE_KEY.equalsIgnoreCase(className)) {
			if (rentalHouse != null && rentalHouse.getHouseId() != null) {
				systemOperateLog.setDataKeyword(rentalHouse.getHouseId()
						.toString());
			} else {
				systemOperateLog.setDataKeyword(houseTmp.getId().toString());
			}
		} else {
			systemOperateLog.setDataKeyword(houseTmp.getId().toString());
		}

		if (NewBaseInfoTables.RENTALHOUSE_KEY.equalsIgnoreCase(className)) {
			systemOperateLog.setBusinessType(NewBaseInfoTables.RENTALHOUSE_KEY);
		} else {
			systemOperateLog.setBusinessType(NewBaseInfoTables.ACTUALHOUSE_KEY);
		}

		systemOperateLog.setDataName(houseTmp.getAddress());
		if (houseTmp.getOrganization() != null) {
			systemOperateLog.setDataOrgCode(houseTmp.getOrgInternalCode());
			Organization org = new Organization();
			org.setId(houseTmp.getOrganization().getId());
			systemOperateLog.setDataOrgId(org);
		}
		return systemOperateLog;
	}

	private Object getPopulationByIdAndType(String populationType,
			Long populationId) {
		Object object;
		if ((NewBaseInfoTables.HOUSEINFO_KEY).equalsIgnoreCase(populationType)) {
			String temp = NewBaseInfoTables.ACTUALHOUSE_KEY;
			object = SpringBeanUtil.getBeanFromSpringByBeanName(temp.substring(
					0, 1).toLowerCase()
					+ temp.substring(1) + "Service");

		} else if ((NewBaseInfoTables.RENTALHOUSE_KEY)
				.equalsIgnoreCase(populationType)) {
			String temp = NewBaseInfoTables.RENTALHOUSE_KEY;
			object = SpringBeanUtil.getBeanFromSpringByBeanName(temp.substring(
					0, 1).toLowerCase()
					+ temp.substring(1) + "Service");
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
			throw new OperationFailedException("获取房屋类别出错:", e);
		}
	}

}

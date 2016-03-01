package com.tianque.plugin.transfer.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.base.domain.People;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.baseInfo.dangerousChemicalsUnit.domain.DangerousChemicalsUnit;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.baseInfo.internetBar.domain.InternetBar;
import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.baseInfo.positiveInfo.service.PositiveInfoService;
import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.baseInfo.rectificativePerson.service.RectificativePersonService;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.baseInfo.unsettledPopulation.service.UnsettledPopulationService;
import com.tianque.cache.PageInfoCacheThreadLocal;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Enterprise;
import com.tianque.domain.HouseHasActualPopulation;
import com.tianque.domain.LocationBaseInfo;
import com.tianque.domain.NewEconomicOrganizations;
import com.tianque.domain.Organization;
import com.tianque.domain.OtherLocale;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.School;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.transfer.dao.TransferOtherInfoDao;
import com.tianque.plugin.transfer.util.Constants;
import com.tianque.plugin.transfer.util.Context;
import com.tianque.plugin.transfer.util.TransferUtil;
import com.tianque.plugin.transfer.vo.ErrorMessageVo;
import com.tianque.recoverDatas.service.RecoverDatasService;
import com.tianque.service.ActualPopulationMutexService;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.HouseHasActualPopulationService;
import com.tianque.service.HouseInfoService;
import com.tianque.service.HousePopulationApplyService;
import com.tianque.service.PopulationTypeService;
import com.tianque.service.util.PopulationType;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.shard.util.IdConversionShardUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.ObjectConvertMapUtil;

@Component("selfHandler")
public class SelfHandler extends Handler implements ApplicationContextAware {
	private ApplicationContext appContext;
	@Autowired
	protected ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	public FloatingPopulationService floatingPopulationService;

	// 原来的dubboService包下的
	@Autowired
	public OrganizationDubboService organizationDubboService;
	@Autowired
	public HouseholdStaffService householdStaffService;
	@Autowired
	PopulationTypeService populationTypeService;
	@Autowired
	private TransferOtherInfoDao transferOtherInfoDao;
	@Autowired
	private BaseInfoService baseInfoService;

	// copy otherinfohandler
	@Autowired
	private ActualPopulationMutexService actualPopulationMutexService;
	@Autowired
	private RectificativePersonService rectificativePersonService;
	@Autowired
	private PositiveInfoService positiveInfoService;
	@Autowired
	private UnsettledPopulationService unsettledPopulationService;

	@Autowired
	private HousePopulationApplyService housePopulationApplyService;
	@Autowired
	private HouseHasActualPopulationService houseHasActualPopulationService;
	@Autowired
	private HouseInfoService houseInfoService;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private RentalHouseService rentalHouseService;
	@Autowired
	private RecoverDatasService recoverDatasService;

	@Override
	public void invoke(String type, Long id, Long toOrgId, Context context) {
		Organization org = organizationDubboService.getSimpleOrgById(toOrgId);
		// 如果是流动 或者是 户籍
		if (BaseInfoTables.HOUSEHOLDSTAFF_KEY.equals(type)) {
			HouseholdStaff household = householdStaffService
					.getHouseholdStaffById(id);
			if (household == null) {
				throw new BusinessValidationException("户籍人口,不存在id=" + id);
			}
			String idcardNo = household.getIdCardNo();

			convertOrUpdateOrInsertActualPopulation(toOrgId, idcardNo, type,
					household, context);

		} else if (BaseInfoTables.FLOATINGPOPULATION_KEY.equals(type)) {
			FloatingPopulation floatingPopulation = floatingPopulationService
					.getFloatingPopulationById(id);
			if (floatingPopulation == null) {
				throw new BusinessValidationException("流动人口,不存在id=" + id);
			}
			String idcardNo = floatingPopulation.getIdCardNo();

			convertOrUpdateOrInsertActualPopulation(toOrgId, idcardNo, type,
					floatingPopulation, context);

		} else if (NewBaseInfoTables.ACTUALHOUSE_KEY.equals(type)) {// ====================如果是房屋转移====================

			HouseInfo houseInfo = houseInfoService.getSimpleHouseInfoById(id);

			if (houseInfo == null) {
				throw new BusinessValidationException("房屋信息不存在,id=" + id);
			}
			convertOrUpdateOrInsertActualHouse(toOrgId, houseInfo, type,
					context);

		} else {
			// 如果是 场所 ，房屋 ，业务人口信息 未落户 和 境外人员
			// 获取标准名称的service

			String servicesName = getServiceNameByType(type);
			Object services = appContext.getBean(servicesName);

			Class<?> serviceClass = services.getClass();

			String tableName = getTableNameByType(type);

			String MethodName = TransferUtil.getMethodNameByType(type);

			// 准备条件
			Map<String, Object> map = getMapByOrgId(toOrgId);
			map.put("id", id);

			try {
				Method method = serviceClass.getMethod(MethodName, Long.class);
				Object entryObject = method.invoke(services, id);
				if (entryObject == null) {
					throw new BusinessValidationException(type + "不存在,id=" + id);
				}
				Class<?> getResultClass = entryObject.getClass();

				// NewEconomicOrganizations 集成的是 countrymen 不是 LocationBaseInfo
				if (entryObject instanceof LocationBaseInfo
						|| entryObject instanceof NewEconomicOrganizations) {// 如果是场所
					String localeName = getLocaleNameByClass(getResultClass);
					String nameString = getUniqNameByMethodName(localeName,
							getResultClass, entryObject);
					if (StringUtils.isEmpty(nameString)) {
						throw new BusinessValidationException("没有获取可以判断唯一性的标识");
					}
					transferOtherInfoDao.updateOrg(tableName, map);

				} else if (entryObject instanceof AttentionPopulation
						|| entryObject instanceof OverseaPersonnel
						|| entryObject instanceof UnsettledPopulation) {
					// 如果是业务人口 境外人员,和 未落户 也一起在这里处理
					// 查询是否存在 hasDuplicateDruggy
					if (entryObject instanceof OverseaPersonnel) {
						context.getOldMap().put(type,
								(OverseaPersonnel) entryObject);
						transferOtherInfoDao.updateOrg(tableName, map);

						return;
					}

					if (entryObject instanceof UnsettledPopulation) {
						// 1 没有身份证 直接新增 ,有身份证判断下
						String idcardno = getUniqNameByMethodName(
								"getIdCardNo", getResultClass, entryObject);
						context.getOldMap().put(type, (Countrymen) entryObject);
						transferOtherInfoDao.updateOrg(tableName, map);
						return;
					}

					String idcardno = getUniqNameByMethodName("getIdCardNo",
							getResultClass, entryObject);

					if (StringUtils.isEmpty(idcardno)) {
						throw new BusinessValidationException(
								"没有获取可以判断唯一性的更新标识");
					}
					// 判断业务人口 在目标网格是否存在 ，不存在是就改业务人员的网格
					String hasDuplicateName = getDuplicateMethodName(type);
					Method hasDuplicateMethod = serviceClass.getMethod(
							hasDuplicateName, Long.class, String.class);
					Object targetObject = hasDuplicateMethod.invoke(services,
							toOrgId, idcardno);
					if (targetObject != null) {
						context.setExistedToOrgId(true);
					} else {
						if (type.equals(Constants.RECTIFICATIVEPERSON_KEY)) {
							AttentionPopulation attentionPopulation = positiveInfoService
									.getPositiveInfoByIdCardNo(idcardno,
											toOrgId);
							if (null != attentionPopulation) {
								context.setExistedToOrgId(true);
							}

							// 缓存计数器
							transferPageInfoCacheThreadLocal(type, id, org);
						} else if (type.equals(Constants.POSITIVEINFO_KEY)) {
							AttentionPopulation attentionPopulation = rectificativePersonService
									.hasDuplicateRectificativePerson(toOrgId,
											idcardno);
							if (null != attentionPopulation) {
								context.setExistedToOrgId(true);
							}

							// 缓存计数器
							transferPageInfoCacheThreadLocal(type, id, org);
						}
						transferOtherInfoDao.updateOrg(tableName, map);
					}

				} else {
					// 如果是房屋直接新增
					if (Constants.RENTALHOUSE_KEY.equals(type)) {
						tableName = "RentalHouse";
					} else if (Constants.RENTALHOUSE_KEY.equals(type)) {
						tableName = "houseinfo";
					}
					transferOtherInfoDao.updateOrg(tableName, map);
				}

			} catch (SecurityException e) {
				throw new ServiceValidationException("处理被转移对象的信息出错！", e);
			} catch (NoSuchMethodException e) {
				throw new ServiceValidationException("处理被转移对象的信息出错！", e);
			} catch (IllegalArgumentException e) {
				throw new ServiceValidationException("处理被转移对象的信息出错！", e);
			} catch (IllegalAccessException e) {
				throw new ServiceValidationException("处理被转移对象的信息出错！", e);
			} catch (InvocationTargetException e) {
				throw new ServiceValidationException("处理被转移对象的信息出错！", e);
			} catch (Exception e) {
				throw new ServiceValidationException("处理被转移对象的信息出错！", e);
			}
		}

	}

	private void convertOrUpdateOrInsertActualHouse(Long toOrgId,
			HouseInfo houseInfo, String type, Context context) {
		boolean havaSameHouseInfo = false;
		// 先查询目标网格是否存在房屋准确地址相同的信息，没有则直接转移
		/** 不考虑重复的数据了 **/
		/*
		 * havaSameHouseInfo = checkSameData(toOrgId, houseInfo); if
		 * (havaSameHouseInfo) { // 將當前房屋信息刪除并備份 // List<Long> idsList = new
		 * ArrayList<Long>(); // // idsList.add(houseInfo.getId()); // 先将当前信息删除
		 * actualHouseService.deleteHouseInfosById(houseInfo.getId()); // 信息备份
		 * List<HouseInfo> houseInfos = new ArrayList<HouseInfo>();
		 * houseInfos.add(houseInfo);
		 * recoverDatasService.deleteActualHouse(houseInfos);
		 * 
		 * context.getOldMap().put(type, houseInfo);
		 * context.setExistedToOrgId(havaSameHouseInfo); return; }
		 */
		// 将房屋信息转移
		String servicesName = getServiceNameByType(type);
		Object services = appContext.getBean(servicesName);

		Class<?> serviceClass = services.getClass();

		String tableName = null;
		if (NewBaseInfoTables.ACTUALHOUSE_KEY.equals(type)) {
			tableName = getTableNameByType(type) + "_"
					+ IdConversionShardUtil.getShardCodeById(houseInfo.getId());
		} else {
			tableName = getTableNameByType(type);
		}
		String MethodName = TransferUtil.getMethodNameByType(type);

		// 准备条件
		Map<String, Object> map = getMapByOrgId(toOrgId);
		map.put("id", houseInfo.getId());
		// map.put("houseInfo", houseInfo);// 添加日志信息时使用
		Method method;
		try {
			method = serviceClass.getMethod(MethodName, Long.class);
			Object entryObject = method.invoke(services, houseInfo.getId());
			if (entryObject == null) {
				throw new BusinessValidationException("操作失败，请重试");
			}
			Class<?> getResultClass = entryObject.getClass();

			context.getOldMap().put(type, entryObject);
			transferOtherInfoDao.updateOrg(tableName, map);
			HouseInfo newHouseInfo = actualHouseService
					.getHouseInfoById(houseInfo.getId());
			if (newHouseInfo == null) {
				throw new BusinessValidationException("转移失败，请重试");
			}
			context.getOldMap().put(type, houseInfo);
			context.getNewMap().put(type, newHouseInfo);
			context.setExistedToOrgId(havaSameHouseInfo);
			return;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解除房屋建筑物关联关系
	 */
	private void deleteHouseAndBuildding(Long id) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		actualHouseService.updateHouseInfo(ids, null);
	}

	/**
	 * 检查出租屋是否有关联建筑，有则解除关联
	 * 
	 * @param houseId
	 */
	private void checkBuilddingRelation(Long houseId) {
		HouseInfo houseInfo = houseInfoService.getSimpleHouseInfoById(houseId);

		if (houseInfo.getBuilddatasId() != null) {// 有关联的建筑物信息
			List<Long> ids = new ArrayList<Long>();
			ids.add(houseInfo.getId());
			actualHouseService.updateHouseInfo(ids, null);
		}

	}

	/**
	 * 查询该出租屋信息在目标网格是否有相同信息，有则将当前房屋信息删除，并备份
	 * 
	 * @param id
	 * @param toOrgId
	 * @param rentalHouseInfo
	 */
	private void checkSameDataRentalHouse(Long id, Long toOrgId,
			RentalHouse rentalHouseInfo) {
		List<HouseInfo> houseInfoOrgId = houseInfoService
				.findHouseInfoByOrgIdAddress(toOrgId,
						rentalHouseInfo.getAddress());
		if (houseInfoOrgId != null && houseInfoOrgId.size() != 0) {
			// 將當前房屋信息刪除并備份
			List<Long> idsList = new ArrayList<Long>();
			idsList.add(id);
			// 先将当前信息删除
			actualHouseService.deleteHouseInfosByIdList(idsList);
			// 信息备份
			List<HouseInfo> houseInfos = new ArrayList<HouseInfo>();
			houseInfos.add(rentalHouseInfo);
			recoverDatasService.deleteActualHouse(houseInfos);
			return;

		}

	}

	/**
	 * 根据房屋地址查询目标网格中是否有相同信息，有则将当前房屋信息删除，并备份到数据库
	 * 
	 * @param id
	 * @param toOrgId
	 * @param houseInfo
	 */
	private boolean checkSameData(Long toOrgId, HouseInfo houseInfo) {
		List<HouseInfo> houseInfoOrgId = houseInfoService
				.findHouseInfoByOrgIdAddress(toOrgId, houseInfo.getAddress());
		if (houseInfoOrgId != null && houseInfoOrgId.size() != 0) {
			return true;
		}
		return false;
	}

	/**
	 * 将出租屋的房屋信息一并转移到目标网格
	 * 
	 * @param houseId
	 * @param toOrgId
	 */
	private void judgementTheActualHouse(Long houseId, Long toOrgId) {
		Map<String, Object> map = getMapByOrgId(toOrgId);
		map.put("id", houseId);
		String tableName = getTableNameByType(Constants.ACTUALHOUSE_KEY);
		transferOtherInfoDao.updateOrg(tableName, map);
	}

	/**
	 * 将出租屋的关系解绑
	 * 
	 * @param id
	 * @param toOrgId
	 */
	private void judgementTheRentalHouse(Long id, Long toOrgId) {
		// 通过房屋ID查询得到出租房信息
		RentalHouse rentalHouseInfo = rentalHouseService
				.getHouseInfoByHouseId(id);
		// 将出租房信息的所有关系解绑
		Map<String, Object> map = getMapByOrgId(toOrgId);
		map.put("id", rentalHouseInfo.getId());
		String tableName = getTableNameByType(Constants.RENTALHOUSE_KEY);
		transferOtherInfoDao.updateOrg(tableName, map);
	}

	/**
	 * 查询房屋信息的关系，然后解绑
	 * 
	 * @param id
	 */
	private void judgementTheHouseHavePupolution(Long id) {
		List<HouseHasActualPopulation> houseHasActualPopulations = houseHasActualPopulationService
				.getHouseHasActualPopulationByHouseId(id);// 通过住房ID查询得到房客信息
		if (houseHasActualPopulations != null
				&& houseHasActualPopulations.size() != 0) {
			for (HouseHasActualPopulation houseHasActualPopulation : houseHasActualPopulations) {
				// 删除房客关联的住房信息以及地址信息
				houseHasActualPopulationService
						.deleteHouseHasActualPopulationByPopulationTypeAndHouseId(
								houseHasActualPopulation.getPopulationType(),
								id, houseHasActualPopulation.getPopulationId());
			}
		}
	}

	private String getTableNameByType(String type) {
		String result = "";
		if (TransferUtil.isEnterPrise(type)) {
			result = "enterprises";
		} else if (Constants.SCHOOL_KEY.equals(type)) {
			result = "schools";
		} else if (Constants.DANGEROUSCHEMICALSUNIT_KEY.equals(type)) {
			result = "dangerousChemicalsUnit";
		} else if (Constants.INTERNETBAR_KEY.equals(type)) {
			result = "InternetBar";
		} else if (Constants.PUBLICPLACE_KEY.equals(type)) {
			result = "PUBLICPLACE";
		} else if (Constants.OTHERLOCALE_KEY.equals(type)) {
			result = "otherLocales";
		} else if (Constants.NEWSOCIETYORGANIZATIONS_KEY.equals(type)) {
			result = "newSocietyOrganizations";
		} else if (Constants.NEWECONOMICORGANIZATIONS_KEY.equals(type)) {
			result = "newEconomicOrganizations";
		} else if (Constants.ACTUALHOUSE_KEY.equals(type)) {
			result = "houseinfo";
		} else if (Constants.RENTALHOUSE_KEY.equals(type)) {
			result = "rentalHouse";
		} else if (Constants.OVERSEAPERSONNEL_KEY.equals(type)) {
			result = "Overseapersonnel";
		} else if (Constants.AIDSPOPULATIONS_KEY.equals(type)) {
			result = "aidspopulations";
		} else if (Constants.FPERSONNEL_KEY.equals(type)) {
			result = "fPersonnels";
		} else if (Constants.QPERSONNEL_KEY.equals(type)) {
			result = "qPersonnels";
		} else if (Constants.MPERSONNEL_KEY.equals(type)) {
			result = "mPersonnels";
		} else if (Constants.GOOD_SAMARITAN_KEY.equals(type)) {
			result = "goodSamaritan";
		} else {
			result = BaseInfoTables.getKeytables().get(type);
		}

		return result;
	}

	private Object getHasDuplicateMethodValue(Method hasMethod,
			Object services, Long toOrgId, String nameString, String type) {

		Object object = null;
		try {
			if (Constants.SAFETYPRODUCTIONKEY_KEY.equals(type)) {
				String keyTypeString = "safetyProductionKey";
				object = hasMethod.invoke(services, toOrgId, nameString,
						keyTypeString);
			} else if (Constants.FIRESAFETYKEY_KEY.equals(type)) {
				String keyTypeString = "fireSafetyKey";
				object = hasMethod.invoke(services, toOrgId, nameString,
						keyTypeString);
			} else if (Constants.SECURITYKEY_KEY.equals(type)) {
				String keyTypeString = "securityKey";
				object = hasMethod.invoke(services, toOrgId, nameString,
						keyTypeString);
			} else if (Constants.ENTERPRISEKEY_KEY.equals(type)) {
				String keyTypeString = "enterpriseKey";
				object = hasMethod.invoke(services, toOrgId, nameString,
						keyTypeString);
			} else if (Constants.ENTERPRISEDOWNKEY_KEY.equals(type)) {
				String keyTypeString = "enterpriseDownKey";
				object = hasMethod.invoke(services, toOrgId, nameString,
						keyTypeString);
			} else {
				object = hasMethod.invoke(services, toOrgId, nameString);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return object;
	}

	private Method getHasDuplicateMethod(String type, Class<?> serviceClass,
			String hasDuplicateName) {
		Method method = null;
		try {

			if (TransferUtil.isEnterPrise(type)) {
				method = serviceClass.getMethod(hasDuplicateName, Long.class,
						String.class, String.class);
			} else {
				method = serviceClass.getMethod(hasDuplicateName, Long.class,
						String.class);
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return method;
	}

	private String getDuplicateMethodName(String type) {
		String MethodName = "";
		String name = "hasDuplicate%s";
		if (TransferUtil.isEnterPrise(type)) {
			MethodName = "hasDuplicateEnterprise";
		} else {
			String newType = type.substring(0, 1).toUpperCase()
					+ type.substring(1);
			MethodName = String.format(name, newType);
		}
		return MethodName;
	}

	private String getServiceNameByType(String type) {

		String result = "";
		if (TransferUtil.isEnterPrise(type)) {
			result = "enterpriseService";
		} else if (Constants.OVERSEAPERSONNEL_KEY.equals(type)) {
			result = "overseaStaffService";
		} else if (Constants.AIDSPOPULATIONS_KEY.equals(type)) {
			result = "aidspopulationService";
		} else {
			result = type + "Service";
		}

		return result;
	}

	private Map<String, Object> getMapByOrgId(Long toOrgId) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(toOrgId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", organization.getId());
		map.put("orgCode", organization.getOrgInternalCode());
		return map;
	}

	private String getUniqNameByMethodName(String localeName,
			Class<?> getResultClass, Object entryObject) {

		Method getNameMethod;
		String nameString = "";
		try {
			getNameMethod = getResultClass.getMethod(localeName);
			nameString = (String) getNameMethod.invoke(entryObject);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return nameString;
	}

	/**
	 * 获取组织场所标识名
	 * 
	 * @param getResultClass
	 * @return
	 */
	private String getLocaleNameByClass(Class<?> getResultClass) {
		// DangerousChemicalsUnit--unitName
		// Enterprise ,NewSocietyOrganizations,OtherLocale --name
		// InternetBar,PublicPlace --placeName
		// School ---chineseName
		// ActualCompany-- companyName
		String localeName;
		if (getResultClass == ActualCompany.class) {
			localeName = "getCompanyName";
		} else if (getResultClass == DangerousChemicalsUnit.class) {
			localeName = "getUnitName";
		} else if (getResultClass == Enterprise.class
				|| getResultClass == NewSocietyOrganizations.class
				|| getResultClass == OtherLocale.class
				|| getResultClass == NewEconomicOrganizations.class) {
			localeName = "getName";
		} else if (getResultClass == School.class) {
			localeName = "getChineseName";
		} else if (getResultClass == InternetBar.class
				|| getResultClass == PublicPlace.class) {
			localeName = "getPlaceName";
		} else {
			throw new BusinessValidationException("场所标识name获取不到");
		}
		return localeName;
	}

	/**
	 * @param toOrgId
	 * @param idcardNo
	 * @param type
	 * @param countrymen
	 *            本身网格的人员对象
	 * @param context
	 */
	private void convertOrUpdateOrInsertActualPopulation(Long toOrgId,
			String idcardNo, String type, Countrymen countrymen, Context context) {
		boolean isHouseHoldStaff = false;
		Countrymen result;
		// Long pexPopulationId = countrymen.getId();
		if (BaseInfoTables.HOUSEHOLDSTAFF_KEY.equals(type)) {
			isHouseHoldStaff = true;
		}
		// 查询目标网格是否存在该 身份证
		ActualPopulation actualPopulation = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNo(toOrgId, idcardNo);
		if (actualPopulation != null) {
			context.setExistedToOrgId(true);
			if (type.equalsIgnoreCase(actualPopulation
					.getActualPopulationType())) {
				result = actualPopulation;
			} else {
				if (BaseInfoTables.HOUSEHOLDSTAFF_KEY.equals(actualPopulation
						.getActualPopulationType())) {
					result = householdStaffService
							.copyAndUpdate(householdStaffService
									.getHouseholdStaffById(actualPopulation
											.getId()));
				} else {
					result = floatingPopulationService
							.copyAndUpdate(floatingPopulationService
									.getFloatingPopulationById(actualPopulation
											.getId()));
				}
			}
			;
		} else {
			// 如果目标网格不存在，直接修改org 和 orgcode 就可以
			result = addActualPopulation(isHouseHoldStaff, toOrgId, countrymen);
		}
		context.getOldMap().put(type, countrymen);
		context.getNewMap().put(type, result);
	}

	/**
	 * 添加实有人口
	 * 
	 * @param isHouseHoldStaff
	 * @param toOrgId
	 * @param countrymen
	 */
	private Countrymen addActualPopulation(boolean isHouseHoldStaff,
			Long toOrgId, Countrymen countrymen) {
		Countrymen result;
		Long populationId = countrymen.getId();
		// 获取orgid
		Organization organization = organizationDubboService
				.getSimpleOrgById(toOrgId);
		if (organization == null) {
			throw new BusinessValidationException("获取Organization出错：id="
					+ toOrgId);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", organization.getId());
		map.put("orgCode", organization.getOrgInternalCode());
		map.put("id", populationId);
		String tableName = "";
		// 更新 orgcode
		if (isHouseHoldStaff) {
			// 新增户籍人口
			tableName = "householdstaffs";
		} else {
			tableName = "floatingpopulations";
		}
		transferOtherInfoDao.updateOrg(tableName, map);

		// 更新之后再获取
		if (isHouseHoldStaff) {
			result = householdStaffService.getHouseholdStaffById(populationId);
		} else {
			result = floatingPopulationService
					.getFloatingPopulationById(populationId);
		}
		return result;
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.appContext = context;

	}

	// copy otherinfohandle 并非和那边完全一样，这边验证自己的信息 验证相反
	@Override
	public void validate(String type, Long id, Long toOrgId, Context context) {
		Organization org = organizationDubboService.getSimpleOrgById(toOrgId);
		// 境外人员 特殊处理

		String orgName = org.getOrgName();

		try {
			List<ErrorMessageVo> errorList = (List<ErrorMessageVo>) context
					.getMap().get(Constants.ERRORLIST);
			List<Long> errorIdList = (List<Long>) context.getMap().get(
					Constants.ERRORIDLIST);
			// 验证其它信息：两类
			// 1. 实口----验证实口信息
			if (TransferUtil.isActualPopulation(type)) {
				// 验证未落户和境外人员
				if (Constants.UNSETTLEDPOPULATION_KEY.equals(type)
						|| Constants.OVERSEAPERSONNEL_KEY.equals(type)
						|| PopulationType.OVERSEA_STAFF.equals(type)) {
					validateUNsetAndOversea(id, type, errorList, errorIdList,
							toOrgId, orgName);
					return;
				}
				ActualPopulation actualPopulation = getOldActualPopulation(id,
						type);
				if (actualPopulation != null) {
					String idCardNo = actualPopulation.getIdCardNo();
					ActualPopulation result = actualPopulationProcessorService
							.getActualPopulationbyOrgIdAndIdCardNo(toOrgId,
									idCardNo);
					if (result != null) {
						// context.setExistedToOrgId(true);
						// PopulationCatalog catalog = PopulationCatalog
						// .parse(result.getActualPopulationType());
						// String message = catalog.toString() + "信息重复";
						// ErrorMessageVo errorMessageVo = new ErrorMessageVo(
						// result.getName(),
						// result.getActualPopulationType(), orgName,
						// result.getIdCardNo(), message);
						// errorList.add(errorMessageVo);
						// errorIdList.add(id);

					}

				}

			}
			// 2. 业务 ----验证业务信息
			else if (TransferUtil.isAllAttentionPopulation(type)) {
				Countrymen countrymen = getAllAttentionPopulationById(id, type,
						toOrgId);
				if (countrymen != null) {
					String name = countrymen.getName();
					String idcardno = countrymen.getIdCardNo();
					// 社区矫正 刑释解教 互斥
					if (countrymen instanceof RectificativePerson) {
						// disposeRectificativePerson(toOrgId, name, idcardno,
						// type, id, orgName, errorList, errorIdList);
					} else if (countrymen instanceof PositiveInfo) {
						// disposePositiveInfoPerson(toOrgId, name, idcardno,
						// type, id, orgName, errorList, errorIdList);
					} else {

						// 其他业务人员 判断存在问题
						String hasDuplicateName = getDuplicateMethodName(type);
						String servicesName = getServiceNameByType(type);
						Object services = appContext.getBean(servicesName);
						Class<?> serviceClass = services.getClass();
						Method hasDuplicateMethod = serviceClass.getMethod(
								hasDuplicateName, Long.class, String.class);
						Object targetObject = hasDuplicateMethod.invoke(
								services, toOrgId, idcardno);
						if (targetObject != null) {
							Map<String, String> map = new HashMap<String, String>();
							map = ObjectConvertMapUtil.convertPopulation(
									targetObject, targetObject.getClass());
							if (IsEmphasis.IsNotEmphasis.equals(Long
									.parseLong(map.get("ISEMPHASIS")))) {
								Method updateEmphasiseByIdMethod = serviceClass
										.getMethod(
												"updateLogOutDetailAndCountByPopulationTypeAndIds",
												Long.class, LogoutDetail.class,
												String.class, Long[].class);
								LogoutDetail logoutDetail = new LogoutDetail();
								logoutDetail.setLogout(IsEmphasis.Emphasis);
								Long[] ids = new Long[1];
								ids[0] = Long.parseLong(map.get("DATAID"));
								updateEmphasiseByIdMethod.invoke(services,
										Long.parseLong(map.get("ORGID")),
										logoutDetail, type, ids);
							}
							// context.setExistedToOrgId(true);
							// 判断业务人口 和 实口 不存在的情况下 才能新增
							// PopulationCatalog catalog = PopulationCatalog
							// .parse(countrymen
							// .getAttentionPopulationType());
							// String message = catalog.toString() + "信息重复";
							// ErrorMessageVo errorMessageVo = new
							// ErrorMessageVo(
							// countrymen.getName(),
							// countrymen.getPopulationType(), orgName,
							// countrymen.getIdCardNo(), message);
							// errorList.add(errorMessageVo);
							// errorIdList.add(id);
						}

					}
				}
			} else {
				String servicesName = getServiceNameByType(type);
				Object services = appContext.getBean(servicesName);
				Class<?> serviceClass = services.getClass();
				String MethodName = TransferUtil.getMethodNameByType(type);
				Method method = serviceClass.getMethod(MethodName, Long.class);
				Object entryObject = method.invoke(services, id);
				String hasDuplicateName = getDuplicateMethodName(type);
				Method hasDuplicateMethod = null;
				Class<?> getResultClass = entryObject.getClass();

				// fateson add 验证场所 排除房屋 那块 就是 全部场所
				if (!Constants.RENTALHOUSE_KEY.equals(type)
						&& !Constants.RENTALHOUSE_KEY.equals(type)) {
					if (entryObject instanceof LocationBaseInfo
							|| entryObject instanceof NewEconomicOrganizations) {
						// 如果是场所
						String localeName = getLocaleNameByClass(getResultClass);

						String nameString = getUniqNameByMethodName(localeName,
								getResultClass, entryObject);
						if (StringUtils.isEmpty(nameString)) {
							throw new BusinessValidationException(
									"没有获取可以判断唯一性的标识");
						}
						// 查询 在目标网格是否存在
						hasDuplicateMethod = getHasDuplicateMethod(type,
								serviceClass, hasDuplicateName);
						Object targetObject = getHasDuplicateMethodValue(
								hasDuplicateMethod, services, toOrgId,
								nameString, type);

						if (targetObject != null) {
							String message = nameString + "信息重复";

							ErrorMessageVo errorMessageVo = new ErrorMessageVo(
									nameString, "", orgName, "", message);
							errorList.add(errorMessageVo);
							errorIdList.add(id);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException("验证被转移数据的其它信息方法出错！", e);
		}
	}

	private Countrymen getAllAttentionPopulationById(Long id, String type,
			Long toOrgId) {
		try {
			String servicesName = getServiceNameByType(type);
			Object services = appContext.getBean(servicesName);
			Class<?> serviceClass = services.getClass();
			String MethodName = TransferUtil.getMethodNameByType(type);

			Method method = serviceClass.getMethod(MethodName, Long.class);
			Object entryObject = method.invoke(services, id);
			if (entryObject == null) {
				throw new BusinessValidationException(type + "不存在,id=" + id);
			} else {
				return (Countrymen) entryObject;
			}

		} catch (BeansException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void validateUNsetAndOversea(Long id, String type,
			List<ErrorMessageVo> errorList, List<Long> errorIdList,
			Long toOrgId, String orgName) {

		type = type.equals(PopulationType.OVERSEA_STAFF) ? Constants.OVERSEAPERSONNEL_KEY
				: type;
		try {
			String servicesName = getServiceNameByType(type);
			Object services = appContext.getBean(servicesName);
			Class<?> serviceClass = services.getClass();
			String MethodName = TransferUtil.getMethodNameByType(type);
			Method method = serviceClass.getMethod(MethodName, Long.class);
			Object entryObject = method.invoke(services, id);
			String hasDuplicateName = getDuplicateMethodName(type);
			Method hasDuplicateMethod = null;
			Class<?> getResultClass = entryObject.getClass();
			if (entryObject instanceof OverseaPersonnel) {
				hasDuplicateMethod = serviceClass.getMethod(hasDuplicateName,
						Long.class, Long.class, String.class);
				String certificateNo = getUniqNameByMethodName(
						"getCertificateNo", getResultClass, entryObject);

				Method getCertificateTypeMethod = getResultClass
						.getMethod("getCertificateType");
				PropertyDict CertificateType = (PropertyDict) getCertificateTypeMethod
						.invoke(entryObject);

				Long certificateType = null;
				if (CertificateType != null) {
					certificateType = CertificateType.getId();
				}

				Object targetObject = hasDuplicateMethod.invoke(services,
						toOrgId, certificateType, certificateNo);
				if (targetObject != null) {
					Method getEnglishNameMethod = getResultClass
							.getMethod("getEnglishName");
					String englishName = (String) getEnglishNameMethod
							.invoke(entryObject);
					String message = "境外人员信息重复";
					ErrorMessageVo errorMessageVo = new ErrorMessageVo(
							englishName, type, orgName, certificateNo, message);
					errorList.add(errorMessageVo);
					errorIdList.add(id);
				}
				return;
			}

			if (entryObject instanceof UnsettledPopulation) {
				hasDuplicateMethod = serviceClass.getMethod(hasDuplicateName,
						Long.class, String.class);
				// 1 没有身份证 直接新增 ,有身份证判断下
				String idcardno = getUniqNameByMethodName("getIdCardNo",
						getResultClass, entryObject);

				if (!StringUtils.isEmpty(idcardno)) {
					Object targetObject = hasDuplicateMethod.invoke(services,
							toOrgId, idcardno);

					if (targetObject != null) {
						Method getNameMethod = getResultClass
								.getMethod("getName");
						String name = (String) getNameMethod
								.invoke(entryObject);
						String message = "未落户人口信息重复";
						ErrorMessageVo errorMessageVo = new ErrorMessageVo(
								name, type, orgName, idcardno, message);
						errorList.add(errorMessageVo);
						errorIdList.add(id);
					}
					Countrymen baseinfo = baseInfoService
							.getBaseInfoByIdCardNo(idcardno);
					if (baseinfo != null) {
						Countrymen existed = householdStaffService
								.getHouseholdStaffByBaseInfoId(
										baseinfo.getId(), toOrgId);
						if (existed != null) {
							Method getNameMethod = getResultClass
									.getMethod("getName");
							String name = (String) getNameMethod
									.invoke(entryObject);
							String message = "该身份证在目标网格户籍人口中已存在";
							ErrorMessageVo errorMessageVo = new ErrorMessageVo(
									name, type, orgName, idcardno, message);
							errorList.add(errorMessageVo);
							errorIdList.add(id);
						} else {
							existed = floatingPopulationService
									.getFloatingPopulationByBaseInfoId(
											baseinfo.getId(), toOrgId);
							if (existed != null) {
								Method getNameMethod = getResultClass
										.getMethod("getName");
								String name = (String) getNameMethod
										.invoke(entryObject);
								String message = "该身份证在目标网格流动人口中已存在";
								ErrorMessageVo errorMessageVo = new ErrorMessageVo(
										name, type, orgName, idcardno, message);
								errorList.add(errorMessageVo);
								errorIdList.add(id);
							}
						}
					}

				}
				return;
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理刑释解教到新网格时存在社区矫正
	 * 
	 * @param toOrgId
	 * @param populationTypeBean
	 * @param id
	 * @param orgName
	 * @param errorList
	 * @param errorIdList
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void disposePositiveInfoPerson(Long toOrgId,
			PopulationTypeBean populationTypeBean, Long id, String orgName,
			List errorList, List errorIdList) throws Exception {
		AttentionPopulation attentionPopulation = null;
		String serviceBeanName = populationTypeBean.getActualType() + "Service";
		Object service = SpringBeanUtil
				.getBeanFromSpringByBeanName(serviceBeanName);
		attentionPopulation = rectificativePersonService
				.hasDuplicateRectificativePerson(toOrgId,
						getOldActualPopulation(populationTypeBean, service)
								.getIdCardNo());
		if (null != attentionPopulation) {
			String message = "该刑释人员在目标网格为社区矫正人员";
			ErrorMessageVo errorMessageVo = new ErrorMessageVo(
					attentionPopulation.getName(),
					populationTypeBean.getPopulationType(), orgName,
					attentionPopulation.getIdCardNo(), message);
			errorList.add(errorMessageVo);
			errorIdList.add(id);
		}
	}

	private void disposePositiveInfoPerson(Long toOrgId, String name,
			String idCardNo, String type, Long id, String orgName,
			List errorList, List errorIdList) throws Exception {
		AttentionPopulation attentionPopulation = null;
		attentionPopulation = rectificativePersonService
				.hasDuplicateRectificativePerson(toOrgId, idCardNo);
		if (null != attentionPopulation) {
			String message = "该刑释人员在目标网格为社区矫正人员";
			ErrorMessageVo errorMessageVo = new ErrorMessageVo(name, type,
					orgName, idCardNo, message);
			errorList.add(errorMessageVo);
			errorIdList.add(id);
		}
	}

	/**
	 * 处理社区矫正到新网格时存在刑释解教
	 * 
	 * @param toOrgId
	 * @param populationTypeBean
	 * @param id
	 * @param orgName
	 * @param errorList
	 * @param errorIdList
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void disposeRectificativePerson(Long toOrgId,
			PopulationTypeBean populationTypeBean, Long id, String orgName,
			List errorList, List errorIdList) throws Exception {
		String serviceBeanName = populationTypeBean.getActualType() + "Service";
		Object service = SpringBeanUtil
				.getBeanFromSpringByBeanName(serviceBeanName);
		ActualPopulation oldActualPopulation = getOldActualPopulation(
				populationTypeBean, service);
		boolean flag = positiveInfoService.hasDuplicatePosiviteInfos(toOrgId,
				oldActualPopulation.getIdCardNo(), null);
		if (flag) {
			String message = "该社区矫正人员在目标网格为刑释人员";
			ErrorMessageVo errorMessageVo = new ErrorMessageVo(
					oldActualPopulation.getName(),
					populationTypeBean.getPopulationType(), orgName,
					oldActualPopulation.getIdCardNo(), message);
			errorList.add(errorMessageVo);
			errorIdList.add(id);
		}
	}

	@SuppressWarnings("unused")
	private void disposeRectificativePerson(Long toOrgId, String name,
			String idCardNo, String type, Long id, String orgName,
			List errorList, List errorIdList) throws Exception {

		boolean flag = positiveInfoService.hasDuplicatePosiviteInfos(toOrgId,
				idCardNo, null);
		if (flag) {
			String message = "该社区矫正人员在目标网格为刑释人员";
			ErrorMessageVo errorMessageVo = new ErrorMessageVo(name, type,
					orgName, idCardNo, message);
			errorList.add(errorMessageVo);
			errorIdList.add(id);
		}
	}

	/**
	 * 实口----验证业务信息
	 * 
	 * @param populationTypeBean
	 * @param toOrgId
	 * @return
	 */
	@SuppressWarnings("unused")
	private AttentionPopulation actualPopulationValidate(
			PopulationTypeBean populationTypeBean, Long toOrgId) {
		AttentionPopulation attentionPopulation = null;
		String serviceBeanName = populationTypeBean.getPopulationType()
				+ "Service";
		// 取出被转移实口的业务人员信息
		String methodName = TransferUtil.getMethodNameByType(populationTypeBean
				.getPopulationType());
		try {
			Object service = SpringBeanUtil
					.getBeanFromSpringByBeanName(serviceBeanName);
			Method method = service.getClass()
					.getMethod(methodName, Long.class);
			attentionPopulation = (AttentionPopulation) method.invoke(service,
					populationTypeBean.getPopulationId());
			if (null != attentionPopulation) {
				// 在新网格中查找是否有重复的业务人员信息
				String hasDuplicateName = getDuplicateMethodName(populationTypeBean
						.getPopulationType());
				Method hasDuplicateMethod = service.getClass().getMethod(
						hasDuplicateName, Long.class, String.class);
				attentionPopulation = (AttentionPopulation) hasDuplicateMethod
						.invoke(service, toOrgId,
								attentionPopulation.getIdCardNo());
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"OtherInfoHandler类的actualPopulationValidate方法出错\n",
					"验证被转移数据的其它信息方法出错！", e);
		}
		return attentionPopulation;
	}

	/**
	 * 业务人员----验证实口信息
	 * 
	 * @param id
	 * @param type
	 * @param toOrgId
	 * @param context
	 * @return
	 */
	@SuppressWarnings("unused")
	private ActualPopulation businessPopulationValidate(Long id, String type,
			Long toOrgId, Context context) {
		// 根据业务人员类型和ID取与实口的关联关系对象
		PopulationTypeBean populationTypeBean = populationTypeService
				.getPopulationTypeByPopulationIdAndType(id, type);
		AttentionPopulation attentionPopulation = null;
		ActualPopulation actualPopolation = null;
		ActualPopulation actualPopolation_new = null;
		if (null != populationTypeBean) {
			try {
				String serviceBeanName = populationTypeBean.getPopulationType()
						+ "Service";
				Object service = SpringBeanUtil
						.getBeanFromSpringByBeanName(serviceBeanName);
				attentionPopulation = getOldAttentionPopulation(
						populationTypeBean, service);
				actualPopolation = getActualPopulation(attentionPopulation
						.getOrganization().getId(),
						attentionPopulation.getIdCardNo(),
						populationTypeBean.getActualType());
				if (context.getOldMap().get(type) == null) {
					context.getOldMap().put(type, actualPopolation);
				}
				// 在新网格中查找是否有重复的实口
				actualPopolation_new = getActualPopulation(toOrgId,
						attentionPopulation.getIdCardNo(),
						populationTypeBean.getActualType());
			} catch (Exception e) {
				throw new ServiceValidationException(
						"OtherInfoHandler类的businessPopulationValidate方法出错\n",
						"验证被转移数据的其它信息方法出错！", e);
			}
			// 新网格中没有重复的实口
			if (null == actualPopolation_new) {
				// 新网格中实口互斥验证
				if (!actualPopulationMutexService
						.isActualPopuationByOrgIdAndIdCardNoAndExcludeActualPopulationType(
								0L, toOrgId, attentionPopulation.getIdCardNo(),
								populationTypeBean.getActualType())) {
					return getActualPopulationOtherType(toOrgId,
							attentionPopulation.getIdCardNo(),
							populationTypeBean.getActualType());
				}
			}
		}
		return actualPopolation_new;
	}

	/**
	 * 根据类型在实口中查找数据
	 * 
	 * @param orgId
	 * @param idCardNo
	 * @param type
	 * @return
	 */
	private ActualPopulation getActualPopulation(Long orgId, String idCardNo,
			String type) {
		ActualPopulation actualPopolation = null;
		if (type.equals(Constants.HOUSEHOLDSTAFF_KEY)) {
			actualPopolation = householdStaffService
					.findHouseholdStaffByCardNoAndOrgId(idCardNo, orgId);
		}
		if (type.equals(Constants.FLOATINGPOPULATION_KEY)) {
			actualPopolation = floatingPopulationService
					.findFloatingPopulationByCardNoAndOrgId(idCardNo, orgId);
		}
		if (type.equals(Constants.UNEMPLOYEDPEOPLE_KEY)) {
			actualPopolation = unsettledPopulationService
					.getUnsettledPopulationByIdCardNo(idCardNo, orgId);
		}
		return actualPopolation;
	}

	/**
	 * 根据类型在其它实口中查找数据
	 * 
	 * @param orgId
	 * @param idCardNo
	 * @param type
	 * @return
	 */
	private ActualPopulation getActualPopulationOtherType(Long orgId,
			String idCardNo, String type) {
		ActualPopulation actualPopolation = null;
		if (!type.equals(Constants.HOUSEHOLDSTAFF_KEY)) {
			actualPopolation = householdStaffService
					.findHouseholdStaffByCardNoAndOrgId(idCardNo, orgId);
		}
		if (null == actualPopolation
				&& !type.equals(Constants.FLOATINGPOPULATION_KEY)) {
			actualPopolation = floatingPopulationService
					.findFloatingPopulationByCardNoAndOrgId(idCardNo, orgId);
		}
		if (null == actualPopolation
				&& !type.equals(Constants.UNEMPLOYEDPEOPLE_KEY)) {
			actualPopolation = unsettledPopulationService
					.getUnsettledPopulationByIdCardNo(idCardNo, orgId);
		}
		return actualPopolation;
	}

	/**
	 * 获得原始网格的实口信息
	 * 
	 * @param populationTypeBean
	 * @param service
	 * @return
	 * @throws Exception
	 */
	private ActualPopulation getOldActualPopulation(
			PopulationTypeBean populationTypeBean, Object service)
			throws Exception {
		// 取出被转移业务人员的实口信息
		String methodName = TransferUtil.getMethodNameByType(populationTypeBean
				.getActualType());
		Method method = service.getClass().getMethod(methodName, Long.class);
		return (ActualPopulation) method.invoke(service,
				populationTypeBean.getActualId());
	}

	/**
	 * 调用类似 get****ById 结果
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private ActualPopulation getOldActualPopulation(Long id, String type)
			throws Exception {
		// 取出被转移业务人员的实口信息
		String methodName = TransferUtil.getMethodNameByType(type);
		String servicesName = getServiceNameByType(type);
		Object service = appContext.getBean(servicesName);
		Method method = service.getClass().getMethod(methodName, Long.class);
		return (ActualPopulation) method.invoke(service, id);
	}

	/**
	 * 获得原始网格的业务信息
	 * 
	 * @param populationTypeBean
	 * @param service
	 * @return
	 * @throws Exception
	 */
	private AttentionPopulation getOldAttentionPopulation(
			PopulationTypeBean populationTypeBean, Object service)
			throws Exception {
		// 取出被转移业务人员的实口信息
		String methodName = TransferUtil.getMethodNameByType(populationTypeBean
				.getPopulationType());
		Method method = service.getClass().getMethod(methodName, Long.class);
		return (AttentionPopulation) method.invoke(service,
				populationTypeBean.getPopulationId());
	}

	@Override
	public void validate(String type, Long id, Long toOrgId, Long fromOrgId,
			Context context) {
		validate(type, id, toOrgId, context);
	}

	@Override
	public void invoke(String type, Long id, Long toOrgId, Long fromOrgId,
			Context context) {
		invoke(type, id, toOrgId, context);
	}

	// 缓存计数器增减
	private void transferPageInfoCacheThreadLocal(String type, Long id,
			Organization org) {
		// 过滤只有人口的才有缓存计数器
		if (Constants.getClassNameByType(type) != null) {
			String modelName = MemCacheConstant.getCachePageKey(StringUtil
					.firstCharUpperCase(type));
			People fromPeople = TransferUtil.getPeopleByPopulationTypeAndId(
					type, id);
			PageInfoCacheThreadLocal.decrease(modelName, fromPeople, 1);
			People toPeople = TransferUtil.getPeopleByPopulationTypeAndId(type,
					id);
			toPeople.setOrganization(org);
			PageInfoCacheThreadLocal.increment(modelName, toPeople, 1);
		}
	}
}

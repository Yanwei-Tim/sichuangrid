package com.tianque.baseInfo.base.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.baseInfo.elderlyPeople.service.ElderlyPeopleService;
import com.tianque.baseInfo.familyInfo.domain.GroupFamily;
import com.tianque.baseInfo.familyInfo.domain.GroupFamilyHasPopulation;
import com.tianque.baseInfo.familyInfo.service.GroupFamilyService;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.baseInfo.idleYouth.service.IdleYouthService;
import com.tianque.baseInfo.nurturesWomen.domain.NurturesWomen;
import com.tianque.baseInfo.nurturesWomen.service.NurturesWomenService;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.baseInfo.personnelTrackInfo.service.PersonnelTrackInfoService;
import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.baseInfo.utils.PopulationCopyUtil;
import com.tianque.cache.PageInfoCacheThreadLocal;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.DateUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.RelationShipWithHead;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.OperationFailedException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.transfer.util.TransferUtil;
import com.tianque.recoverDatas.service.RecoverDatasService;
import com.tianque.service.AbstractBusinessPopulationService;
import com.tianque.service.HouseHasActualPopulationService;
import com.tianque.service.PopulationTypeService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.shard.util.IdConversionShardUtil;
import com.tianque.shard.util.ShardConversion;
import com.tianque.shard.util.ShardTables;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.IdCardUtil;
import com.tianque.util.PluginServiceHelpUtil;

public abstract class BaseInfoPopulationTemplateImpl extends
		AbstractBusinessPopulationService implements
		BaseInfoPopulationTemplateService {

	@Autowired
	@Qualifier("logoutDetailValidator")
	private DomainValidator<LogoutDetail> logoutDetailValidator;
	// @Autowired
	// private GlobalSettingService globalSettingService;

	private BaseInfoPopulationBaseDao baseInfoPopulationBaseDao;
	@Autowired
	private GroupFamilyService groupFamilyService;
	@Autowired
	public PersonnelTrackInfoService personnelTrackService;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private RecoverDatasService recoverDatasService;
	@Autowired
	private PopulationRelationService populationRelationService;
	@Autowired
	private PopulationTypeService populationTypeService;
	@Autowired
	protected BaseInfoService baseInfoService;
	@Autowired
	protected AddressInfoService addressInfoService;
	@Autowired
	private ShardConversion shardConversion;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private HouseHasActualPopulationService houseHasActualPopulationService;

	private ElderlyPeopleService elderlyPeopleService;
	private NurturesWomenService nurturesWomenService;
	private IdleYouthService idleYouthService;

	private static final String HOUSEHOLDSTAFF = "householdStaff";

	@Resource(name = "nurturesWomenService")
	protected void setNurturesWomenService(
			NurturesWomenService nurturesWomenService) {
		this.nurturesWomenService = nurturesWomenService;
	}

	@Resource(name = "elderlyPeopleService")
	protected void setElderlyPeopleService(
			ElderlyPeopleService elderlyPeopleService) {
		this.elderlyPeopleService = elderlyPeopleService;
	}

	@Resource(name = "idleYouthService")
	protected void setIdleYouthService(IdleYouthService idleYouthService) {
		this.idleYouthService = idleYouthService;
	}

	/**
	 * 设置人口的房屋信息
	 * 
	 * @param countryMen
	 */
	protected void contructCurrentAddress(Countrymen countryMen) {
		if (null != countryMen.getIsHaveHouse() && countryMen.getIsHaveHouse()
				&& countryMen.getCurrentAddress() != null) {
			HouseInfo houseInfo = actualHouseService
					.getHouseInfoById(countryMen.getHouseId());
			if (houseInfo != null) {
				countryMen.setCurrentAddressType(houseInfo.getAddressType());
				countryMen.setCurrentAddress(houseInfo.getAddress());
			} else {
				countryMen.setCurrentAddress(countryMen.getCurrentAddress());
				countryMen.setCurrentAddressType(propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								"现居住址类型", "其他"));
			}
			countryMen.setNoHouseReason("");
		} else {
			countryMen.setCurrentAddress("");
			countryMen.setCurrentAddressType(null);
		}
	}

	/**
	 * 人口业务模块，关注和取消关注的操作，并修改该模块的count计数器
	 * 
	 * @param logoutDetail
	 * @param populationType
	 * @param ids
	 * @return
	 */
	public List<Long> updateLogOutDetailAndCountByPopulationTypeAndIds(
			Long orgId, LogoutDetail logoutDetail, String populationType,
			Long[] ids) {
		try {
			List<Long> resultList = new ArrayList<Long>();

			validatorIds(ids);

			ValidateResult baseDataValidator = logoutDetailValidator
					.validate(logoutDetail);

			if (baseDataValidator.hasError()) {
				throw new BusinessValidationException(
						baseDataValidator.getErrorMessages());
			}

			for (Long id : ids) {
				updateLogOutByPopulationTypeAndId(logoutDetail, populationType,
						id);
				resultList.add(id);
			}
			return resultList;
		} catch (Exception e) {
			throw new ServiceValidationException("取消注销错误操作失败", e);
		}
	}

	public List<Long> updateLogOutDetailByPopulationTypeAndIds(
			LogoutDetail logoutDetail, String populationType, Long[] ids) {
		try {
			List<Long> resultList = new ArrayList<Long>();

			validatorIds(ids);

			ValidateResult baseDataValidator = logoutDetailValidator
					.validate(logoutDetail);

			if (baseDataValidator.hasError()) {
				throw new BusinessValidationException(
						baseDataValidator.getErrorMessages());
			}

			for (Long id : ids) {
				updateLogOutByPopulationTypeAndId(logoutDetail, populationType,
						id);
				resultList.add(id);
			}
			return resultList;
		} catch (Exception e) {
			throw new ServiceValidationException("取消注销错误操作失败", e);
		}
	}

	protected void updateLogOutByPopulationTypeAndId(LogoutDetail logoutDetail,
			String populationType, Long id) {
		PopulationCatalog catalog = PopulationCatalog.parse(populationType);
		Map<String, Object> map = buildMap(logoutDetail, catalog, id);
		map.put("populationType", populationType);
		// 房屋人数
		Long houseId = houseHasActualPopulationService
				.getHouseIdByPopulationTypeAndPopulationId(populationType, id);
		if (PopulationCatalog.ACTUAL_POPULATION.getCatalog().equals(
				catalog.getParentCatalog())) {
			if (IsEmphasis.Emphasis.equals(logoutDetail.getLogout())) {
				groupFamilyService
						.deleteGroupFamilyByPopulationIdAndPopulationTypeAndIdCardNo(
								id, populationType, null);
				// 房屋人数
				if (houseId != null) {
					String shardCode = IdConversionShardUtil
							.getShardCodeById(houseId);
					actualHouseService.updateHouseInfoMemberNum(shardCode,
							houseId, 1);
				}
			} else {
				groupFamilyService.updateGroupFamilyToNoMasterforLogOut(id,
						populationType);
				// 房屋人数
				if (houseId != null) {
					String shardCode = IdConversionShardUtil
							.getShardCodeById(houseId);
					actualHouseService.updateHouseInfoMemberNum(shardCode,
							houseId, -1);
				}
			}
			if (PopulationType.HOUSEHOLD_STAFF.equals(populationType)
					|| PopulationType.FLOATING_POPULATION
							.equals(populationType)) {
				populationRelationService.actualPopulationLogOut(id,
						populationType, logoutDetail);
			}
		} else {
			Long IsEmphasisArg = 1l;
			// 重新关注的话，要先判断实口的状态，如果实口是关注状态，不修改本身的房屋信息，如果实口是注销状态，修改房屋信息
			if (IsEmphasis.Emphasis.equals(logoutDetail.getLogout())) {
				Map<String, Object> idCardAndOrgId = getIdCardNoAndOrgIdByPopulationTableAndId(
						catalog.getTableName(), id);

				// 重新关注业务人员，如果实口是注销的做取消注销操作
				Map<String, Object> actualPopulation = getActualPopulationLogoutByIdCardNoAndOrgId(idCardAndOrgId);
				Long logout;
				if (null == actualPopulation) {
					logout = null;
				} else if (actualPopulation != null
						&& null == actualPopulation.get("LOGOUT")) {
					// 查询出来的数据注销状态为空的，当未注销处理
					logout = IsEmphasis.IsNotEmphasis;
				} else {
					logout = ((BigDecimal) actualPopulation.get("LOGOUT"))
							.longValue();
				}
				if (logout != null && IsEmphasis.IsNotEmphasis.equals(logout)) {
					String actualType = (String) actualPopulation
							.get("ACTUALTYPE");
					Long actualId = ((BigDecimal) actualPopulation.get("ID"))
							.longValue();
					updateLogOutByPopulationTypeAndId(logoutDetail, actualType,
							actualId);
				}
				IsEmphasisArg = 0l;
				PopulationTypeBean temp = populationTypeService
						.getPopulationTypeByPopulationIdAndType(id,
								populationType);
				if (temp == null) {
					temp = populationTypeService
							.getActualPopulationTypeBeanByOrgIdAndIdCardNo(
									(Long) idCardAndOrgId.get("orgId"),
									(String) idCardAndOrgId.get("idCardNo"));
					if (temp == null) {
						throw new BusinessValidationException(
								"业务人员对应的实口还处于注销状态，无法重新关注");
					}
					temp.setPopulationId(id);
					temp.setPopulationType(populationType);
					populationTypeService.addPopulationType(temp);
				}
			} else {
				PopulationTypeBean temp = populationTypeService
						.getPopulationTypeByPopulationIdAndType(id,
								populationType);
				if (temp != null) {
					populationTypeService
							.deletePopulationTypeByPopulationIdAndType(id,
									populationType);
				}
			}
			try {
				PluginServiceHelpUtil.doService("routerService",
						"updateServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class, Long.class },
						populationType, id, IsEmphasisArg);
			} catch (Exception e) {
				throw new ServiceValidationException("修改注销状态时，修改服务对象关联出错", e);
			}
		}
		if (ShardTables.isShardTables(catalog.getTableName())) {
			String shardCode = IdConversionShardUtil.getShardCodeById(id);
			map.put("table", catalog.getTableName() + "_" + shardCode);
		}
		baseInfoPopulationBaseDao.updateLogOutByMap(map);

		// 注销、取消注销 业务信息 count缓存计数器
		IsEmphasisPageInfoCacheThreadLocal(populationType, id);
	}

	private void IsEmphasisPageInfoCacheThreadLocal(String populationType,
			Long id) {
		if (PopulationType.HOUSEHOLD_STAFF.equals(populationType)) {
			// 户籍
			HouseholdStaff householdStaff = (HouseholdStaff) TransferUtil
					.getPeopleByPopulationTypeAndId(populationType, id);
			if (householdStaff != null) {
				if (IsEmphasis.Emphasis.equals(householdStaff.getLogOut())) {
					PageInfoCacheThreadLocal.increment(MemCacheConstant
							.getCachePageKey(householdStaff.getClass()),
							householdStaff, 1);
				} else {
					PageInfoCacheThreadLocal.decrease(MemCacheConstant
							.getCachePageKey(householdStaff.getClass()),
							householdStaff, 1);
				}
			}
		}
		if (PopulationType.FLOATING_POPULATION.equals(populationType)) {
			// 流口
			FloatingPopulation floatingPopulation = (FloatingPopulation) TransferUtil
					.getPeopleByPopulationTypeAndId(populationType, id);
			if (floatingPopulation != null) {
				if (IsEmphasis.Emphasis.equals(floatingPopulation.getLogOut())) {
					PageInfoCacheThreadLocal.increment(MemCacheConstant
							.getCachePageKey(floatingPopulation.getClass()),
							floatingPopulation, 1);
				} else {
					PageInfoCacheThreadLocal.decrease(MemCacheConstant
							.getCachePageKey(floatingPopulation.getClass()),
							floatingPopulation, 1);
				}
			}
		}
		if (PopulationType.UNSETTLED_POPULATION.equals(populationType)) {
			// 未落户
			UnsettledPopulation unsettledPopulation = (UnsettledPopulation) TransferUtil
					.getPeopleByPopulationTypeAndId(populationType, id);
			if (unsettledPopulation != null) {
				if (IsEmphasis.Emphasis.equals(unsettledPopulation.getLogOut())) {
					PageInfoCacheThreadLocal.increment(MemCacheConstant
							.getCachePageKey(unsettledPopulation.getClass()),
							unsettledPopulation, 1);
				} else {
					PageInfoCacheThreadLocal.decrease(MemCacheConstant
							.getCachePageKey(unsettledPopulation.getClass()),
							unsettledPopulation, 1);
				}
			}

		}
		if (PopulationType.OVERSEA_STAFF.equals(populationType)) {
			// 境外人员
			OverseaPersonnel overseaPersonnel = (OverseaPersonnel) TransferUtil
					.getPeopleByPopulationTypeAndId(populationType, id);
			if (overseaPersonnel != null) {
				if (IsEmphasis.Emphasis.equals(overseaPersonnel.getLogOut())) {
					PageInfoCacheThreadLocal.increment(MemCacheConstant
							.getCachePageKey(overseaPersonnel.getClass()),
							overseaPersonnel, 1);
				} else {
					PageInfoCacheThreadLocal.decrease(MemCacheConstant
							.getCachePageKey(overseaPersonnel.getClass()),
							overseaPersonnel, 1);
				}
			}

		}
		if (PopulationType.getCnameByActualType(populationType) == null) {
			if (populationType.equals("aidspopulation")) {
				populationType = PopulationType.AIDSPOPULATIONS_KEY;
			}
			// 业务信息
			AttentionPopulation people = (AttentionPopulation) TransferUtil
					.getPeopleByPopulationTypeAndId(populationType, id);
			if (people != null) {
				if (IsEmphasis.Emphasis.equals(people.getIsEmphasis())) {
					PageInfoCacheThreadLocal.increment(MemCacheConstant
							.getCachePageKey(StringUtil
									.firstCharUpperCase(populationType)),
							people, 1);
				} else {
					PageInfoCacheThreadLocal.decrease(MemCacheConstant
							.getCachePageKey(StringUtil
									.firstCharUpperCase(populationType)),
							people, 1);
				}
			}
		}
	}

	private Map<String, Object> buildMap(LogoutDetail logoutDetail,
			PopulationCatalog catalog, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", catalog.getTableName());
		map.put("id", id);
		map.put("logoutDetail", logoutDetail);
		map.put("catalog", catalog);
		return map;
	}

	protected Map<String, Object> getIdCardNoAndOrgIdByPopulationTableAndId(
			String type, Long id) {
		if (ShardTables.isShardTables(type)) {
			String shardCode = IdConversionShardUtil.getShardCodeById(id);
			type = type + "_" + shardCode;
		}
		return baseInfoPopulationBaseDao
				.getIdCardNoAndOrgIdByPopulationTableAndId(type, id);
	}

	protected Map<String, Object> getActualPopulationLogoutByIdCardNoAndOrgId(
			Map<String, Object> map) {
		Countrymen countrymen = baseInfoService.existBaseInfo((String) map
				.get("idCardNo"));
		String shardCode = shardConversion
				.getShardCode((Long) map.get("orgId"));
		map.put("shardCode", shardCode);
		if (countrymen != null) {
			map.put("baseInfoId", countrymen.getId());
		} else {
			map.put("baseInfoId", null);
		}
		return baseInfoPopulationBaseDao
				.getActualPopulationLogoutByIdCardNoAndOrgId(map);
	}

	protected Countrymen convertCountrymen(Map<String, Object> searchResultMap) {
		Countrymen countrymen = new Countrymen();
		countrymen
				.setGender(convertPropertyDict(searchResultMap.get("gender")));
		countrymen.setIdCardNo(convertString(searchResultMap.get("idCardNo")));
		countrymen.setName(convertString(searchResultMap.get("name")));
		countrymen.setUsedName(convertString(searchResultMap.get("usedName")));
		countrymen.setFullPinyin(convertString(searchResultMap
				.get("fullPinYin")));
		countrymen.setSimplePinyin(convertString(searchResultMap
				.get("simplePinYin")));
		countrymen
				.setTelephone(convertString(searchResultMap.get("telephone")));
		countrymen.setMobileNumber(convertString(searchResultMap
				.get("mobileNumber")));
		countrymen.setProvince(convertString(searchResultMap.get("province")));
		countrymen.setDistrict(convertString(searchResultMap.get("district")));
		countrymen.setCity(convertString(searchResultMap.get("city")));
		countrymen.setNativePlaceAddress(convertString(searchResultMap
				.get("nativePlaceAddress")));
		countrymen.setNativePoliceStation(convertString(searchResultMap
				.get("nativePoliceStation")));
		countrymen.setBirthday(convertSimpleDate(searchResultMap
				.get("birthday")));
		countrymen.setWorkUnit(convertString(searchResultMap.get("workUnit")));
		countrymen.setDeath(convertBoolean((searchResultMap.get("isDeath"))));
		countrymen
				.setNation(convertPropertyDict(searchResultMap.get("nation")));
		countrymen.setPoliticalBackground(convertPropertyDict(searchResultMap
				.get("politicalBackground")));
		countrymen.setSchooling(convertPropertyDict(searchResultMap
				.get("schooling")));
		countrymen
				.setCareer(convertPropertyDict(searchResultMap.get("career")));
		countrymen.setMaritalState(convertPropertyDict(searchResultMap
				.get("maritalState")));
		countrymen.setBloodType(convertPropertyDict(searchResultMap
				.get("bloodType")));
		countrymen.setFaith(convertPropertyDict(searchResultMap.get("faith")));
		countrymen.setStature(convertLong(searchResultMap.get("stature")));
		countrymen.setEmail(convertString(searchResultMap.get("email")));
		return countrymen;
	}

	protected String convertString(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof String) {
			return (String) value;
		} else {
			return null;
		}
	}

	protected Long convertLong(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof Long) {
			return (Long) value;
		} else {
			try {
				return Long.valueOf((String) value);
			} catch (NumberFormatException e) {
				return null;
			}
		}
	}

	protected PropertyDict convertPropertyDict(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof Long) {
			PropertyDict propertyDict = new PropertyDict();
			propertyDict.setId((Long) value);
			return propertyDict;
		} else {
			return null;
		}

	}

	protected Boolean convertBoolean(Object value) {
		if (value == null) {
			return false;
		}
		String s = String.valueOf(value);
		if ("1".equals(s) || "true".equalsIgnoreCase(s)) {
			return true;
		}
		return false;
	}

	protected Date convertSimpleDate(Object value) {
		if (value == null
				|| !StringUtil.isStringAvaliable(String.valueOf(value))) {
			return null;
		}
		String s = String.valueOf(value);
		return DateUtil.parseDate(s, "yyyy-MM-dd'T'HH:mm:ss");
	}

	protected Date convertLocalDate(Object value) {
		if (value == null
				|| !StringUtil.isStringAvaliable(String.valueOf(value))) {
			return null;
		}
		String s = String.valueOf(value);
		return DateUtil.parseDate(s, "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
	}

	/**
	 * 修改家庭信息的家长信息（要判断是不是家长是家长则修改）
	 * 
	 * @param countrymen
	 */
	public void updateGroupFamily(Countrymen countrymen) {
		// 是家长修改家庭信息家长身份证号
		GroupFamilyHasPopulation groupFamilyHasPopulation = groupFamilyService
				.getGroupFamilyHasPopulationByPopulationIdAndPopulationType(
						countrymen.getId(),
						countrymen.getActualPopulationType());
		if (groupFamilyHasPopulation != null
				&& groupFamilyHasPopulation.getFamilyRelation() != null
				&& getPropertyDictById(
						groupFamilyHasPopulation.getFamilyRelation().getId())
						.getInternalId() == RelationShipWithHead.HEADER) {
			groupFamilyService.updateGroupFamilyIdcardById(fillGroupFamilyInfo(
					countrymen, groupFamilyHasPopulation));

		}
	}

	/**
	 * 修改业务人员相关（老年人、{青少年【青少年、少先队员、共青团员】由于是实时查询的不需要做处理}、育龄妇女、重点青少年）
	 * 
	 * @param countrymen
	 * @param idCardNo
	 */
	public void updateBusinessPopulation(Countrymen countrymen, String idCardNo) {
		// 维护重点青少年
		processIdleYouth(countrymen, idCardNo);
		// 维护老年人
		processElderlyPeople(countrymen, idCardNo);
		// 维护育龄妇女
		processNurturesWomen(countrymen, idCardNo);
	}

	/**
	 * 维护育龄妇女
	 * 
	 * @param countrymen
	 * @param idCardNo
	 */
	private void processNurturesWomen(Countrymen countrymen, String idCardNo) {
		if (countrymen == null
				|| !StringUtil.isStringAvaliable(countrymen.getIdCardNo())
				|| !StringUtil.isStringAvaliable(idCardNo)
				|| countrymen.getOrganization() == null
				|| countrymen.getOrganization().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		// 有且仅有新的身份证不是育龄妇女年并且原身份证号码存在育龄妇女信息
		NurturesWomen nurturesWomen = nurturesWomenService
				.hasDuplicateNurturesWomen(
						countrymen.getOrganization().getId(), idCardNo);
		if (!correctNurturesWomenAgeAndGender(countrymen.getIdCardNo())
				&& correctNurturesWomenAgeAndGender(idCardNo)
				&& nurturesWomenService.hasDuplicateNurturesWomen(countrymen
						.getOrganization().getId(), idCardNo, null)
				&& nurturesWomen != null && nurturesWomen.getId() != null) {
			List<Long> list = new ArrayList<Long>();
			list.add(nurturesWomen.getId());
			nurturesWomenService.deleteNurturesWomenByIdList(list);
		}

	}

	/**
	 * 根据身份证号验证年龄和性别是否是维护育龄妇女【是维护育龄妇女返回true】
	 * 
	 * @param idCardNo
	 * @return
	 */
	private boolean correctNurturesWomenAgeAndGender(String idCardNo) {
		if (15 == idCardNo.length()) { // 15位身份证号码
			if (idCardNo.charAt(14) / 2 * 2 != idCardNo.charAt(14)) {
				return false;
			}
		}
		if (18 == idCardNo.length()) { // 18位身份证号码
			if (idCardNo.charAt(16) / 2 * 2 != idCardNo.charAt(16)) {
				return false;
			}
		}

		Date idCardDate = IdCardUtil.parseBirthday(idCardNo);
		if (idCardDate == null) {
			return false;
		}
		Date afterDate = null, beforeDate = null;
		Date date = new Date();// 2049
		String dates = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String afterDateStr = (Integer.parseInt(dates.substring(0, 4)) - 15)// 2033
				+ dates.substring(4, 10);
		String beforeDateStr = (Integer.parseInt(dates.substring(0, 4)) - 49)// 2000
				+ dates.substring(4, 10);
		try {
			afterDate = new SimpleDateFormat("yyyy-MM-dd").parse(afterDateStr);

			beforeDate = new SimpleDateFormat("yyyy-MM-dd")
					.parse(beforeDateStr);
		} catch (ParseException e) {
			logger.error("根据身份证号判断是否是育龄妇女错误", e);
			return false;
		}
		if (idCardDate.before(afterDate) && idCardDate.after(beforeDate)) {
			return true;
		}

		return false;
	}

	/**
	 * 维护老年人
	 * 
	 * @param countrymen
	 * @param idCardNo
	 */
	private void processElderlyPeople(Countrymen countrymen, String idCardNo) {
		if (countrymen == null
				|| !StringUtil.isStringAvaliable(countrymen.getIdCardNo())
				|| !StringUtil.isStringAvaliable(idCardNo)
				|| countrymen.getOrganization() == null
				|| countrymen.getOrganization().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		// 有且仅有新的身份证不是老年人年并且原身份证号码存在老年人信息
		ElderlyPeople elderlyPeople = elderlyPeopleService
				.hasDuplicateElderlyPeople(
						countrymen.getOrganization().getId(), idCardNo);
		if (!correctElderlyPeopleAge(countrymen.getIdCardNo())
				&& correctElderlyPeopleAge(idCardNo)
				&& elderlyPeopleService.hasDuplicateElderlyPeople(countrymen
						.getOrganization().getId(), idCardNo, null)
				&& elderlyPeople != null && elderlyPeople.getId() != null) {
			elderlyPeopleService.deleteElderlyPeopleById(elderlyPeople.getId());
		}

	}

	/**
	 * 根据身份证号验证年龄是否是老年人【是老年人返回true】
	 * 
	 * @param idCardNo
	 * @return
	 */
	private boolean correctElderlyPeopleAge(String idCardNo) {
		Date idCardDate = IdCardUtil.parseBirthday(idCardNo);
		if (idCardDate == null) {
			return false;
		} else {
			Date afterDate = null;
			Date date = new Date();
			String dates = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String date2 = (Integer.parseInt(dates.substring(0, 4)) - 60)
					+ dates.substring(4, 10);
			try {
				afterDate = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
			} catch (ParseException e) {
				logger.error("根据身份证号判断是否是老年人错误", e);
				return false;
			}
			if (!idCardDate.after(afterDate)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 维护重点青少年
	 * 
	 * @param countrymen
	 * @param idCardNo
	 */
	private void processIdleYouth(Countrymen countrymen, String idCardNo) {
		if (countrymen == null
				|| !StringUtil.isStringAvaliable(countrymen.getIdCardNo())
				|| !StringUtil.isStringAvaliable(idCardNo)
				|| countrymen.getOrganization() == null
				|| countrymen.getOrganization().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		// 有且仅有新的身份证不是青少年并且原身份证号码存在重点青少年信息
		IdleYouth idleYouth = idleYouthService.hasDuplicateIdleYouth(countrymen
				.getOrganization().getId(), idCardNo);
		if (!correctIdleYouthAge(countrymen.getIdCardNo())
				&& correctIdleYouthAge(idCardNo)
				&& idleYouthService.hasDuplicateIdleYouth(countrymen
						.getOrganization().getId(), idCardNo, null)
				&& idleYouth != null && idleYouth.getId() != null) {

			idleYouthService.deleteIdleYouthByIds(new Long[] { idleYouth
					.getId() });
		}
	}

	/**
	 * 根据身份证号验证年龄是否是青少年【是青少年返回true】
	 * 
	 * @param idCardNo
	 * @return
	 */
	private boolean correctIdleYouthAge(String idCardNo) {
		Date idCardDate = IdCardUtil.parseBirthday(idCardNo);
		if (idCardDate == null) {
			return false;
		} else {
			Date beforeDate = null;
			Date afterDate = null;
			Date date = new Date();
			String dates = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String date2 = (Integer.parseInt(dates.substring(0, 4)) - 35)
					+ dates.substring(4, 10);
			String date1 = (Integer.parseInt(dates.substring(0, 4)))
					+ dates.substring(4, 10);

			try {
				beforeDate = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
				afterDate = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
			} catch (ParseException e) {
				logger.error("根据身份证号判断是否是青少年错误", e);
				return false;
			}

			if (idCardDate.after(afterDate) && idCardDate.before(beforeDate)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 人口默认列表查询添加基本信息和地址信息
	 * 
	 * @param countrymenPageInfo
	 */
	protected <T extends Countrymen> void fitCountrymen(
			PageInfo<T> countrymenPageInfo) {
		Set<Long> baseInfoIds = new HashSet<Long>();
		Set<Long> houseInfoIds = new HashSet<Long>();
		for (Countrymen countrymen : countrymenPageInfo.getResult()) {
			houseInfoIds.add(countrymen.getAddressInfoId());
			baseInfoIds.add(countrymen.getBaseInfoId());
		}
		List<Countrymen> houseList = addressInfoService
				.getAddressInfoByIdForList(new ArrayList<Long>(houseInfoIds));
		List<Countrymen> baseInfoList = baseInfoService
				.getBaseInfoByIdForList(new ArrayList<Long>(baseInfoIds));
		for (Countrymen person : countrymenPageInfo.getResult()) {
			for (Countrymen countrymen : houseList) {
				if (countrymen.getId().equals(person.getAddressInfoId())) {
					PopulationCopyUtil.copyAddressInfoMessage(countrymen,
							person);
					break;
				}
			}
			for (Countrymen countrymen : baseInfoList) {
				if (countrymen.getId().equals(person.getBaseInfoId())) {
					PopulationCopyUtil.copyBaseInfoMessage(countrymen, person);
					break;
				}
			}
		}
	}

	/**
	 * 填充家庭信息需要修改的家长的身份证
	 * 
	 * @param countrymen
	 * @param groupFamilyHasPopulation
	 * @return
	 */
	private GroupFamily fillGroupFamilyInfo(Countrymen countrymen,
			GroupFamilyHasPopulation groupFamilyHasPopulation) {
		if (groupFamilyHasPopulation == null
				|| groupFamilyHasPopulation.getFamilyId() == null
				|| countrymen.getIdCardNo() == null
				|| "".equals(countrymen.getIdCardNo())) {
			return null;
		}
		GroupFamily groupFamily = new GroupFamily();
		groupFamily.setId(groupFamilyHasPopulation.getFamilyId());
		groupFamily.setMasterCardNo(countrymen.getIdCardNo());
		return groupFamily;
	}

	private PropertyDict getPropertyDictById(Long id) {
		PropertyDict propertyDict = propertyDictService.getPropertyDictById(id);
		if (propertyDict == null) {
			throw new BusinessValidationException("PropertyDict不能为空!");
		}
		return propertyDict;
	}

	private void validatorIds(Long[] ids) {
		if (ids == null || ids.length <= 0) {
			throw new BusinessValidationException("传入参数为空");
		}
	}

	protected void copyProperties(Object dest, Object orig) {
		try {
			PropertyUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			throw new OperationFailedException("复制属性失败", e);
		}
	}

	public <T extends Countrymen> PageInfo<T> fitServiceMemberHasObject(
			String populationType, PageInfo<T> peopleInfoPageInfos) {
		PageInfo<T> peopleInfoPageInfosNew = new PageInfo<T>();
		List<T> peopleInfo = new ArrayList<T>();
		for (T person : peopleInfoPageInfos.getResult()) {
			person.setHasServiceTeamMember(baseInfoPopulationBaseDao
					.countServiceMemberHasObject(populationType, person.getId()));
			person.setLastRecordTime(baseInfoPopulationBaseDao
					.findServiceLastRecordTime(populationType, person.getId()));
			peopleInfo.add(person);
		}
		peopleInfoPageInfosNew.setResult(peopleInfo);
		return peopleInfoPageInfosNew;
	}

	@Override
	public <T extends AttentionPopulation> boolean checkDataExitInCache(
			T attentionPopulation, String cacheKey, String cacheValue) {
		String key = MemCacheConstant.getPopulationKey(cacheKey,
				attentionPopulation.getIdCardNo(), attentionPopulation
						.getOrganization().getId());
		return !cacheService.add(MemCacheConstant.POPULATION_NAMESPACE, key,
				300, cacheValue);
	}

	@Override
	public <T extends AttentionPopulation> void cleanDataInCache(
			T attentionPopulation, String cacheKey) {
		String key = MemCacheConstant.getPopulationKey(cacheKey,
				attentionPopulation.getIdCardNo(), attentionPopulation
						.getOrganization().getId());
		cacheService.remove(MemCacheConstant.POPULATION_NAMESPACE, key);
	}

	public BaseInfoPopulationBaseDao getBaseInfoPopulationBaseDao() {
		return baseInfoPopulationBaseDao;
	}

	public void setBaseInfoPopulationBaseDao(
			BaseInfoPopulationBaseDao baseInfoPopulationBaseDao) {
		this.baseInfoPopulationBaseDao = baseInfoPopulationBaseDao;
	}

	public RecoverDatasService getRecoverDatasService() {
		return recoverDatasService;
	}

	public PopulationRelationService getPopulationRelationService() {
		return populationRelationService;
	}

}

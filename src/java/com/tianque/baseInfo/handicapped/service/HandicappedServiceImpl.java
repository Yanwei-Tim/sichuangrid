package com.tianque.baseInfo.handicapped.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateImpl;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.baseInfo.base.service.PopulationRelationService;
import com.tianque.baseInfo.handicapped.dao.HandicappedDao;
import com.tianque.baseInfo.handicapped.domain.Handicapped;
import com.tianque.baseInfo.handicapped.domain.HandicappedSdisabilityType;
import com.tianque.cache.PageInfoCacheThreadLocal;
import com.tianque.cache.UpdateType;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.PeopleHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.SearchHandicappedVo;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.HelpPersonnelService;
import com.tianque.service.HelpPrecordService;
import com.tianque.service.HousePopulationMaintanceService;
import com.tianque.service.PopulationProccessorService;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.Arrays;
import com.tianque.util.IdCardUtil;
import com.tianque.util.PluginServiceHelpUtil;
import com.tianque.validate.AbstractCountrymenValidator;

@Service("handicappedService")
@Transactional
public class HandicappedServiceImpl extends BaseInfoPopulationTemplateImpl
		implements HandicappedService, PopulationProccessorService {

	private static final String CACHE_ADDHANDICAPPED_VALUE = "CACHE_ADDHANDICAPPED";
	private static final String CACHE_ADDHANDICAPPEDBASEINFO_VALUE = "CACHE_ADDHANDICAPPEDBASEINFO";

	@Autowired
	private HandicappedDao handicappedDao;
	@Autowired
	OrganizationDubboService organizationDubboService;
	@Autowired
	private HelpPersonnelService helpPersonnelService;
	@Autowired
	private HelpPrecordService helpPrecordService;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private ManagePopulationByHouseHelper managePopulationByHouseHelper;
	@Autowired
	private HousePopulationMaintanceService housePopulationMaintanceService;
	@Autowired
	private PopulationRelationService populationRelationService;
	@Autowired
	private BaseInfoService baseInfoService;
	@Autowired
	private PermissionDubboService permissionDubboService;
	@Autowired
	@Qualifier("peopleHelper")
	PeopleHelper peopleHelper;
	@Qualifier("handicappedValidator")
	@Autowired
	private AbstractCountrymenValidator<Handicapped> updateValidator;

	// @Autowired
	// private CacheService cacheService;

	@Resource(name = "handicappedDao")
	public void setBaseInfoPopulationBaseDao(HandicappedDao handicappedDao) {
		super.setBaseInfoPopulationBaseDao(handicappedDao);
	}

	@Override
	public Handicapped addHandicapped(Handicapped handicapped) {
		try {
			ValidateResult idleValidator = updateValidator
					.validate(handicapped);
			if (!ExcelImportHelper.isImport.get()) {
				if (idleValidator.hasError()) {
					throw new BusinessValidationException(
							idleValidator.getErrorMessages());
				} else if (hasDuplicateHandicapped(handicapped
						.getOrganization().getId(), handicapped.getIdCardNo(),
						handicapped.getId())) {
					throw new BusinessValidationException("该网格下已存在相同身份证号码");
				}
			}
			if (checkDataExitInCache(handicapped,
					MemCacheConstant.CACHE_ADDHANDICAPPED,
					CACHE_ADDHANDICAPPED_VALUE)) {
				return handicapped;
			}
			if (ExcelImportHelper.isImport.get()
					&& handicapped.getDisabilityTypeIds() == null
					&& handicapped.getDisabilityLevelIds() == null) {

				handicapped
						.setDisabilityTypeIds(getIdListByPropertyDictList(handicapped
								.getDisabilityTypes()));// 残疾类别
				handicapped
						.setDisabilityLevelIds(getDisabilityLevelIdsByHandicapped(handicapped));// 残疾等级

			}
			List<HandicappedSdisabilityType> handicappedSdisabilityTypeList = editHandicapped(
					handicapped, false);
			Handicapped handicappedTemp = add(handicapped);
			HandicappedSdisabilityType handicappedSdisabilityType = new HandicappedSdisabilityType();
			handicappedSdisabilityType.setId(handicappedTemp.getId());
			saveHandicappedList(handicappedSdisabilityTypeList,
					handicappedTemp.getId());
			return handicappedTemp;
		} catch (Exception e) {
			logger.error("HandicappedServiceImpl addHandicapped", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(handicapped, MemCacheConstant.CACHE_ADDHANDICAPPED);
		}
	}

	private List<Long> getDisabilityLevelIdsByHandicapped(
			Handicapped handicapped) {
		List<Long> ids = new ArrayList<Long>();
		if (handicapped.getDisabilityIntellect() != null) {
			ids.add(handicapped.getDisabilityIntellect().getId());
		}

		if (handicapped.getDisabilitysLimbs() != null) {
			ids.add(handicapped.getDisabilitysLimbs().getId());
		}
		if (handicapped.getDisabilitysMental() != null) {
			ids.add(handicapped.getDisabilitysMental().getId());
		}
		if (handicapped.getDisabilitysHearing() != null) {
			ids.add(handicapped.getDisabilitysHearing().getId());
		}
		if (handicapped.getDisabilitysSpeech() != null) {
			ids.add(handicapped.getDisabilitysSpeech().getId());
		}
		if (handicapped.getDisabilitysVision() != null) {
			ids.add(handicapped.getDisabilitysVision().getId());
		}
		return ids;
	}

	private List<Long> getIdListByPropertyDictList(
			List<PropertyDict> disabilitys) {
		List<Long> ids = null;
		if (disabilitys != null && disabilitys.size() > 0) {
			ids = new ArrayList<Long>();
			for (PropertyDict dict : disabilitys) {
				ids.add(dict.getId());
			}
		}
		return ids;
	}

	private Handicapped add(Handicapped handicapped) {
		handicapped.setIdCardNo(handicapped.getIdCardNo().toUpperCase());
		autoFillOrganizationInternalCode(handicapped);
		autoFillChinesePinyin(handicapped);
		autoFillGender(handicapped);
		autoFillBirthday(handicapped);
		autoIsDeath(handicapped);
		contructCurrentAddress(handicapped);
		try {
			Countrymen temp = populationRelationService.businessOption(
					handicapped, handicapped.getActualPopulationType());
			handicapped.setBaseInfoId(temp.getBaseInfoId());
			handicapped.setAddressInfoId(temp.getAddressInfoId());
			handicapped.setSourcesState(null);
			handicapped = handicappedDao.add(handicapped);
			populationRelationService.addPopulationRelation(temp.getId(),
					handicapped.getActualPopulationType(), handicapped.getId(),
					BaseInfoTables.HANDICAPPED_KEY);
			handicapped.setHouseId(temp.getHouseId());
			// proccessHouseBind(handicapped);
			rebuildHouseAddress(handicapped);

			if (IsEmphasis.Emphasis.equals(handicapped.getIsEmphasis())) {
				// 缓存计数器
				PageInfoCacheThreadLocal.increment(MemCacheConstant
						.getCachePageKey(Handicapped.class.getSimpleName()),
						handicapped, 1);
			}
			return handicapped;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类HandicappedServiceImpl的addHandicapped方法出现异常，原因：",
					"新增信息出现错误", e);
		}
	}

	/**
	 * 如果人口的房屋信息（CurrentAddress）不为空，并且房屋id不存在，新增一个房屋，并且建立关联关系, 如果房屋id不为空直接建立关联关系
	 * 如果房屋信息为空,并且有房屋id不为空，则删除人房关系
	 * 
	 * @param householdStaff
	 */
	private void rebuildHouseAddress(Handicapped householdStaff) {

		if (householdStaff.getIsHaveHouse() != null
				&& householdStaff.getIsHaveHouse()
				&& householdStaff.getCurrentAddress() != null) {

			if (null == householdStaff.getHouseId()
					|| householdStaff.getHouseId().equals(01L)) {
				// 新增一个实有房屋,并且建立人房关系
				HouseInfo houseInfo = new HouseInfo();
				houseInfo.setAddress(householdStaff.getCurrentAddress());
				houseInfo.setAddressType(propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypes.CURRENT_ADDRESS_TYPE, "其他"));
				houseInfo.setOrganization(householdStaff.getOrganization());
				houseInfo
						.setHouseOperateSource(NewBaseInfoTables.HANDICAPPED_KEY);
				houseInfo = actualHouseService.addHouseInfo(houseInfo);

				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.HANDICAPPED, householdStaff,
						houseInfo.getId());
			} else if (householdStaff.getHouseId() != null) {
				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.HANDICAPPED, householdStaff,
						householdStaff.getHouseId());
			}
		} else {
			housePopulationMaintanceService.releaseHouse(
					PopulationCatalog.HANDICAPPED, householdStaff.getId(),
					householdStaff.getHouseId());
		}
	}

	private void autoFillOrganizationInternalCode(Handicapped handicapped) {
		if (handicapped.getOrganization() == null) {
			throw new BusinessValidationException("找不到指定的网格");
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(handicapped.getOrganization().getId());
			if (organization == null) {
				throw new BusinessValidationException("找不到指定的网格");
			}
			handicapped.setOrgInternalCode(organization.getOrgInternalCode());
		}

	}

	private void autoFillChinesePinyin(Handicapped handicapped) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(handicapped.getName());
		handicapped.setFullPinyin((String) pinyin.get("fullPinyin"));
		handicapped.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	/**
	 * 填充性别
	 * 
	 * @param householdStaff
	 */
	private void autoFillGender(Handicapped handicapped) {
		handicapped.setGender(peopleHelper.autoFillGender(PropertyTypes.GENDER,
				handicapped.getIdCardNo()));
	}

	private void autoFillBirthday(Handicapped handicapped) {
		if (StringUtil.isStringAvaliable(handicapped.getIdCardNo())) {
			handicapped.setBirthday(IdCardUtil.parseBirthday(handicapped
					.getIdCardNo()));
		}
	}

	private void autoIsDeath(Handicapped handicapped) {
		if (handicapped.isDeath()) {
			handicapped.setIsEmphasis(IsEmphasis.IsNotEmphasis);
		} else {
			handicapped.setIsEmphasis(IsEmphasis.Emphasis);
		}
	}

	@Override
	public Handicapped updateHandicapped(Handicapped handicapped) {
		handicapped.setIdCardNo(handicapped.getIdCardNo().toUpperCase());
		autoFillOrganizationInternalCode(handicapped);
		ValidateResult idleValidator = updateValidator.validate(handicapped);

		if (idleValidator.hasError()) {
			throw new BusinessValidationException(
					idleValidator.getErrorMessages());
		} else if (hasDuplicateHandicapped(handicapped.getOrganization()
				.getId(), handicapped.getIdCardNo(), handicapped.getId())) {
			throw new BusinessValidationException("该网格下已存在相同身份证号码");
		}
		autoFillChinesePinyin(handicapped);
		autoFillGender(handicapped);
		autoFillBirthday(handicapped);
		if (handicapped.isDeath()) {
			handicapped.setIsEmphasis(IsEmphasis.IsNotEmphasis);
			this.updateEmphasiseById(handicapped.getId(),
					handicapped.getIsEmphasis());
		}
		try {
			updateHandicappedBaseInfo(handicapped);
			updateHandicappedBusiness(handicapped);
			return handicapped;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类HandicappedServiceImpl的updateHandicapped方法出现异常，原因：",
					"修改信息出现错误", e);
		}
	}

	private LogoutDetail buildLogoutDetail(Boolean death) {
		LogoutDetail result = new LogoutDetail();
		if (death) {
			result.setLogoutDate(new Date());
			result.setLogoutReason(LogoutDetail.LOGOUT_REASON_FOR_DEATH);
			result.setLogout(IsEmphasis.IsNotEmphasis);
		} else if (!death) {
			result.setLogout(IsEmphasis.Emphasis);
		}
		return result;
	}

	@Override
	public PageInfo<Handicapped> findHandicappedsForPageByOrganizationId(
			Long organizationId, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis) {
		/*
		 * Handicapped handicapped = new Handicapped();
		 * handicapped.setOrgInternalCode
		 * (organizationDubboService.getSimpleOrgById(
		 * organizationId).getOrgInternalCode());
		 * handicapped.setSortField(sidx); handicapped.setOrder(sord);
		 * handicapped.setIsEmphasis(isEmphasis); return
		 * handicappedDao.findPagerUsingCacheBySearchVo(organizationId,
		 * handicapped, pageNum, pageSize, "Handicappeds",
		 * MemCacheConstant.getCachePageKey(Handicapped.class
		 * .getSimpleName()));
		 */

		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgInternalCode",
				organizationDubboService.getSimpleOrgById(organizationId)
						.getOrgInternalCode());
		query.put("isEmphasis", isEmphasis);
		PageInfo<Handicapped> pageInfos = handicappedDao
				.findPagerUsingCacheBySearchVo(organizationId, query, pageNum,
						pageSize, "HandicappedDefaultList",
						MemCacheConstant.getCachePageKey(Handicapped.class));
		fitCountrymen(pageInfos);
		fitServiceMemberHasObject(BaseInfoTables.HANDICAPPED_KEY, pageInfos);
		//隐藏身份证中间4位
		pageInfos=hiddenIdCard(pageInfos);
		return pageInfos;

		// return handicappedDao.findHandicappedsForPageByOrgInternalCode(
		// organizationDubboService.getSimpleOrgById(organizationId)
		// .getOrgInternalCode(), pageNum, pageSize, sidx, sord,
		// isEmphasis);
	}
	
	//隐藏身份证中间4位
		private PageInfo<Handicapped> hiddenIdCard(PageInfo<Handicapped> pageInfo){
							//判断权限，有权限不隐藏
							if(permissionDubboService.
									isUserHasPermission(ThreadVariable.getUser().getId(), "isHandicappedManagementNotHidCard")){
								return pageInfo;
							}
							List<Handicapped> list = pageInfo.getResult();
							int index=0;
							for (Handicapped verification:list) {
								verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
								list.set(index, verification);
								index++;
							}
							pageInfo.setResult(list);
							return pageInfo;
	}

	@Override
	public Handicapped getHandicappedById(Long id) {

		if (id == null) {
			throw new BusinessValidationException("残疾人id不能为空");
		}
		return handicappedDao.get(id);
	}

	@Override
	public boolean deleteHandicapped(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		boolean ok = false;

		handicappedDao.delete(id);
		this.deletePopulationTypeByPopulationIdAndType(id,
				PopulationType.HANDICAPPED);
		if (handicappedDao.get(id) == null) {
			ok = true;
		}

		return ok;
	}

	@Override
	public Handicapped getHandicappedByIdCardNoAndOrganizationId(
			String idCardNo, Long organizationId) {

		if (idCardNo == null) {
			throw new BusinessValidationException("参数错误");
		}
		String idCardNo15 = "";
		String idCardNo18 = "";
		if (idCardNo.length() == 15) {
			idCardNo15 = idCardNo;
			idCardNo18 = IdCardUtil.idCardNo15to18(idCardNo, "19");
		} else if (idCardNo.length() == 18) {
			idCardNo15 = IdCardUtil.idCardNo18to15(idCardNo);
			idCardNo18 = idCardNo;
		}
		return handicappedDao.getHandicappedByIdCardNoAndOrganizationId(
				idCardNo15, idCardNo18, organizationId);
	}

	@Override
	public Handicapped updateHandicappedByName(String idCardNo, Long orgId,
			Handicapped domain) {
		try {
			Handicapped older = this.getHandicappedByIdCardNo(idCardNo, orgId);
			domain.setId(older.getId());
			domain.setCreateDate(older.getCreateDate());
			domain.setCreateUser(older.getCreateUser());
			if (ExcelImportHelper.isImport.get()
					&& domain.getDisabilityTypeIds() == null
					&& domain.getDisabilityLevelIds() == null) {

				domain.setDisabilityTypeIds(getIdListByPropertyDictList(domain
						.getDisabilityTypes()));// 残疾类别
				domain.setDisabilityLevelIds(getDisabilityLevelIdsByHandicapped(domain));// 残疾等级

			}
			return updateHandicapped(domain);
		} catch (Exception e) {
			logger.error("HandicappedServiceImpl updateHandicappedByName", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public boolean hasDuplicateHandicapped(Long orgId, String idCardNo,
			Long exceptedId) {
		if (idCardNo == null || "".equals(idCardNo.trim()) || orgId == null) {
			return false;
		}
		idCardNo = idCardNo.toUpperCase();
		// String idCardNo15 = "";
		// String idCardNo18 = "";
		// if (idCardNo.length() == 15) {
		// idCardNo15 = idCardNo;
		// idCardNo18 = IdCardUtil.idCardNo15to18(idCardNo, "19");
		// } else if (idCardNo.length() == 18) {
		// idCardNo15 = IdCardUtil.idCardNo18to15(idCardNo);
		// idCardNo18 = idCardNo;
		// }
		Countrymen countrymen = baseInfoService.getBaseInfoByIdCardNo(idCardNo);
		if (countrymen == null) {
			return false;
		}
		Long exsited = handicappedDao.getIdByBaseinfoIdAndOrgId(
				countrymen.getId(), orgId);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited));
	}

	@Override
	public Handicapped getHandicappedByIdCardNo(String idCardNo, Long orgId) {
		return this.getHandicappedByIdCardNoAndOrganizationId(idCardNo, orgId);
	}

	private void deleteHandicappedById(Long id) {
		Handicapped domain = getHandicappedById(id);
		if (null != domain) {
			helpPrecordService.deleteHelpPrecord(id, "handicapped");
			helpPersonnelService.deleteHelpPersonnel(id, "handicapped");
			HandicappedSdisabilityType handicappedSdisabilityType = new HandicappedSdisabilityType();
			handicappedSdisabilityType.setId(id);
			deleteDisbilityType(handicappedSdisabilityType);

			domain.setPopulationTypeBean(getPopulationRelationService()
					.getBusinessPopulationTypeBean(id,
							PopulationType.HANDICAPPED));
			getRecoverDatasService().deleteActualPopulation(domain);
			populationRelationService.businessDeletePopulationRelation(id,
					PopulationType.HANDICAPPED);

			handicappedDao.delete(id);
			try {
				PluginServiceHelpUtil.doService("routerService",
						"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class },
						PopulationType.HANDICAPPED, id);
				/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
				PluginServiceHelpUtil.doService("routerService",
						"setOrgIdAndCardNoOrName", new Class[] { Long.class,
								String.class, String.class, Long.class },
						domain.getOrganization().getId(), domain.getIdCardNo(),
						PopulationType.HANDICAPPED, id);

				if (IsEmphasis.Emphasis.equals(domain.getIsEmphasis())) {
					// 缓存计数器
					PageInfoCacheThreadLocal.decrease(
							MemCacheConstant.getCachePageKey(Handicapped.class
									.getSimpleName()), domain, 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public PageInfo<Handicapped> searchHandicappeds(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchHandicappedVo searchHandicappedVo) {
		searchHandicappedVo.setSortField(sortField);
		searchHandicappedVo.setOrder(order);

		PageInfo<Handicapped> pageInfos = handicappedDao.searchHandicappeds(pageNum, pageSize,
				searchHandicappedVo);
		return pageInfos=hiddenIdCard(pageInfos);
	}

	@Override
	public PageInfo<Handicapped> searchHandicappedsForMobile(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchHandicappedVo searchHandicappedVo) {
		try {
			searchHandicappedVo.setSortField(sortField);
			searchHandicappedVo.setOrder(order);

			return handicappedDao.searchHandicappedsForMobile(pageNum,
					pageSize, searchHandicappedVo);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类HandicappedServiceImpl的searchHandicappedsForMobile方法出错",
					"查询残疾人信息出错", e);
		}
	}

	@Override
	public List<Handicapped> updateDeathByIds(Long[] analyzePopulationIds,
			boolean death) {
		List<Handicapped> returnList = new ArrayList<Handicapped>();
		for (int i = 0; i < analyzePopulationIds.length; i++) {
			Handicapped population = this
					.getHandicappedById(analyzePopulationIds[i]);
			updateLogOutByPopulationTypeAndId(
					LogoutDetail.buildLogoutDetail(death),
					BaseInfoTables.HANDICAPPED_KEY, population.getId());
			baseInfoService.updateDeathStateById(population.getBaseInfoId(),
					death, population.getIdCardNo(), population
							.getOrganization().getId(), population
							.getOrgInternalCode(),
					NewBaseInfoTables.HANDICAPPED_KEY);
			returnList.add(population);
			// 缓存计数器
			PageInfoCacheThreadLocal.increment(MemCacheConstant
					.getCachePageKey(Handicapped.class.getSimpleName()),
					population, 1);
		}
		return returnList;
	}

	@Override
	public List<Handicapped> searchAllHandicappeds(
			SearchHandicappedVo searchHandicappedVo) {

		return handicappedDao.searchAllHandicappeds(searchHandicappedVo);
	}

	@Override
	public void deletePopulationByPopulationId(Long populationId) {
		if (null != populationId) {
			this.deleteHandicapped(populationId);
			this.deletePopulationTypeByPopulationIdAndType(populationId,
					PopulationType.HANDICAPPED);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tianque.service.PopulationProccessor#
	 * getPopulationSpecializedInfoByOrgIdAndIdCardNo(java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public Map<String, Map<String, Object>> getPopulationSpecializedInfoByOrgIdAndIdCardNo(
			Long orgId, String idCardNo) {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		try {
			Handicapped handicapped = this.getHandicappedByIdCardNo(idCardNo,
					orgId);
			if (null != handicapped) {
				Map<String, Object> handicappedMap = new HashMap<String, Object>();
				handicappedMap.put("id", handicapped.getId());
				handicappedMap.put("isEmphasis", handicapped.getIsEmphasis());

				handicappedMap.put("guardianName",
						handicapped.getGuardianName());
				handicappedMap.put("disabilityCardNo",
						handicapped.getDisabilityCardNo());
				handicappedMap.put("disabilityReason",
						handicapped.getDisabilityReason());
				handicappedMap.put("disability", handicapped.getDisability());
				handicappedMap.put("disabilityType",
						handicapped.getDisabilityType());
				handicappedMap.put("skillProfile",
						handicapped.getSkillProfile());
				handicappedMap.put("workProfile", handicapped.getWorkProfile());
				handicappedMap.put("hadDisabilityCard",
						handicapped.getHadDisabilityCard());
				handicappedMap.put("disabilityDate",
						handicapped.getDisabilityDate());
				handicappedMap.put("attentionExtent",
						handicapped.getAttentionExtent());
				map.put(PopulationType.HANDICAPPED, handicappedMap);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类HandicappedServiceImpl的getPopulationSpecializedInfoByOrgIdAndIdCardNo方法出现异常，原因：",
					"获取人员信息出现错误", e);
		}
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PopulationProccessor#proccessPopulationSpecializedInfo
	 * (com.tianque.domain.ActualPopulation, java.lang.String[], java.util.Map)
	 */
	@Override
	public Long proccessPopulationSpecializedInfo(
			ActualPopulation actualPopulation, String[] populationType,
			Map<String, Object> population) {
		actualPopulation
				.setAttentionPopulationType(NewBaseInfoTables.HANDICAPPED_KEY);
		Long orgId = Long
				.valueOf(((String[]) population.get("organization.id"))[0]);
		String idCardNo = ((String[]) population.get("idCardNo"))[0];
		Handicapped handicapped = this
				.getHandicappedByIdCardNo(idCardNo, orgId);
		if (!com.tianque.util.Arrays.hasObjectInArray(populationType,
				PopulationType.HANDICAPPED)) {
			if (null != handicapped) {
				handicapped.setIsEmphasis(IsEmphasis.IsNotEmphasis);
				this.updateEmphasiseById(handicapped.getId(),
						IsEmphasis.IsNotEmphasis);
			}
		} else {
			if (null == handicapped) {
				handicapped = new Handicapped();
				copyProperty(actualPopulation, population, handicapped);
				this.addHandicapped(handicapped);
			} else {
				Long id = handicapped.getId();
				copyProperty(actualPopulation, population, handicapped);
				handicapped.setId(id);
				handicapped.setIsEmphasis(IsEmphasis.Emphasis);
				this.updateHandicappedBusiness(handicapped);
				this.updateEmphasiseById(handicapped.getId(),
						IsEmphasis.Emphasis);
			}
		}
		return handicapped == null
				|| handicapped.getIsEmphasis() == IsEmphasis.IsNotEmphasis
						.longValue() ? null : handicapped.getId();
	}

	private void copyProperty(ActualPopulation actualPopulation,
			Map<String, Object> population, Handicapped handicapped) {
		copyProperties(handicapped, actualPopulation);
		handicapped.setGuardianName(Arrays.getStringValueFromArray(population,
				"guardianName"));
		handicapped.setDisabilityCardNo(Arrays.getStringValueFromArray(
				population, "disabilityCardNo"));
		handicapped.setDisabilityReason(Arrays.getStringValueFromArray(
				population, "disabilityReason"));
		handicapped.setDisability(Arrays.getPropertyDictFromArray(population,
				"disability.id"));
		handicapped.setDisabilityType(Arrays.getPropertyDictFromArray(
				population, "disabilityType.id"));
		handicapped.setSkillProfile(Arrays.getPropertyDictFromArray(population,
				"skillProfile.id"));
		handicapped.setWorkProfile(Arrays.getPropertyDictFromArray(population,
				"workProfile.id"));
		handicapped.setRemark(Arrays.getStringValueFromArray(population,
				"remark"));
		handicapped.setHadDisabilityCard(Arrays.getBooleanValueFromArray(
				population, "hadDisabilityCard"));
		handicapped.setDisabilityDate(Arrays.getDateValueFromArray(population,
				"disabilityDate"));
		handicapped.setAttentionExtent(Arrays.getPropertyDictFromArray(
				population, "attentionExtent.id"));
		handicapped.setAttentionPopulationType(BaseInfoTables.HANDICAPPED_KEY);
		handicapped.setDisabilityTypeIds(Arrays.getLongsFromArray(population,
				"disabilityTypeIds"));
		List<Long> disabilityLevelIds = Arrays.getLongsFromArray(population,
				"disabilityLevelIds");
		List<Long> nullArr = new ArrayList<Long>();
		nullArr.add(null);
		disabilityLevelIds.removeAll(nullArr);
		handicapped.setDisabilityLevelIds(disabilityLevelIds);
		handicapped.setDisabilityTypes(Arrays.getPropertyDictsFromArray(
				population, "disabilityTypeIds"));
		handicapped.setDisabilitys(Arrays.getPropertyDictsFromArray(population,
				"disabilityLevelIds"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PopulationProccessor#updatePopulationBaseInfo(com
	 * .tianque.domain.ActualPopulation)
	 */
	@Override
	public void updatePopulationBaseInfo(ActualPopulation actualPopulation) {
		try {
			Handicapped population = handicappedDao.getByOrgIdAndIdCardNo(
					actualPopulation.getOrganization().getId(),
					actualPopulation.getIdCardNo());
			if (null != population) {
				Long id = population.getId();
				PropertyUtils.copyProperties(population, actualPopulation);
				population.setId(id);
				updateHandicapped(population);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类HandicappedServiceImpl的updatePopulationBaseInfo方法出现异常，原因：",
					"修改残疾人基本信息出现错误", e);
		}
	}

	private Long getLevelToLong(String displayName) {
		if (displayName == null || "".equals(displayName)) {
			return 1L;
		}
		return displayName.indexOf("二级") != -1 ? 2L : (displayName
				.indexOf("三级") != -1 ? 3L
				: (displayName.indexOf("四级") != -1 ? 4L : 1L));
	}

	@Override
	public void saveHandicappedList(
			List<HandicappedSdisabilityType> handicappedSdisabilityTypeList,
			Long id) {
		if (handicappedSdisabilityTypeList != null) {
			for (HandicappedSdisabilityType handicappedSdisabilityType : handicappedSdisabilityTypeList) {
				handicappedSdisabilityType.setDisId(id);
				handicappedDao.saveDisabilityType(handicappedSdisabilityType);
			}
		}
	}

	@Override
	public List<HandicappedSdisabilityType> editHandicapped(
			Handicapped population, boolean isDataManage) {
		List<Long> typeIds = population.getDisabilityTypeIds();
		List<Long> levelIds = population.getDisabilityLevelIds();
		List<HandicappedSdisabilityType> handicappedSdisabilityTypeList = new ArrayList<HandicappedSdisabilityType>();
		if (typeIds != null && typeIds.size() != 0 && levelIds != null
				&& levelIds.size() == typeIds.size()) {
			Long maxLevel = 1L;
			String displayName = "一级";
			for (int i = 0; i < typeIds.size(); i++) {
				if (typeIds.get(i) == null || levelIds.get(i) == null) {
					continue;
				}
				PropertyDict pd = propertyDictService
						.getPropertyDictById(levelIds.get(i));
				if (getLevelToLong(pd.getDisplayName()) > maxLevel) {
					maxLevel = getLevelToLong(pd.getDisplayName());
					if (pd.getDisplayName().length() == 6) {
						displayName = pd.getDisplayName().substring(4);
					}
				}
				HandicappedSdisabilityType handicappedSdisabilityType = new HandicappedSdisabilityType();
				handicappedSdisabilityType
						.setHandicappedslevel(levelIds.get(i));
				handicappedSdisabilityType.setHandicappedstype(typeIds.get(i));
				handicappedSdisabilityType.setDataManage(isDataManage);
				handicappedSdisabilityTypeList.add(handicappedSdisabilityType);
			}
			PropertyDict pd1 = new PropertyDict();
			PropertyDict pd2 = new PropertyDict();
			if (typeIds.size() == 1) {
				pd1.setId(typeIds.get(0));
				population.setDisabilityType(pd1);
				pd2.setId(levelIds.get(0));
				population.setDisability(pd2);
			} else {
				List<PropertyDict> pList = propertyDictService
						.findPropertyDictByDomainName(PropertyTypes.DISABILITY_TYPE);
				for (PropertyDict pd : pList) {
					if ("多重残疾".equals(pd.getDisplayName())) {
						pd1.setId(pd.getId());
						break;
					}
				}
				population.setDisabilityType(pd1);
				List<PropertyDict> pList2 = propertyDictService
						.findPropertyDictByDomainName(PropertyTypes.DISABILITY_STATUS);
				for (PropertyDict pd : pList2) {
					if (("多重残疾" + displayName).equals(pd.getDisplayName())) {
						pd2.setId(pd.getId());
						break;
					}
				}
				population.setDisability(pd2);
			}
		}
		return handicappedSdisabilityTypeList;
	}

	@Override
	public Handicapped updateHandicappedBusiness(Handicapped population) {
		try {
			if (population.getId() == null) {
				throw new BusinessValidationException("修改残疾人业务信息缺少ID");
			}
			HandicappedSdisabilityType handicappedSdisabilityType = new HandicappedSdisabilityType();
			handicappedSdisabilityType.setId(population.getId());
			deleteDisbilityType(handicappedSdisabilityType);
			saveHandicappedList(editHandicapped(population, false),
					population.getId());
			population = handicappedDao.updateBusiness(population);
			PageInfoCacheThreadLocal.update(
					MemCacheConstant.getCachePageKey(Handicapped.class),
					population, UpdateType.BUSINESS);
			return population;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类HandicappedServiceImpl的updateHandicappedBusiness方法出现异常，原因：",
					"修改残疾人业务信息出现错误", e);
		}
	}

	@Override
	public Handicapped updateHandicappedBaseInfo(Handicapped population) {
		try {
			population.setIdCardNo(population.getIdCardNo().toUpperCase());
			autoFillBirthday(population);
			autoFillChinesePinyin(population);
			autoFillGender(population);
			autoFillOrganizationInternalCode(population);
			if (population.isDeath()) {
				population.setLogoutDetail(buildLogoutDetail(population
						.isDeath()));
				// 缓存计数器
				PageInfoCacheThreadLocal.decrease(MemCacheConstant
						.getCachePageKey(Handicapped.class.getSimpleName()),
						population, 1);
			}
			contructCurrentAddress(population);
			// this.proccessHouseBind(population);
			Countrymen temp = populationRelationService.businessOption(
					population, population.getActualPopulationType());
			population.setHouseId(temp.getHouseId());
			rebuildHouseAddress(population);
			handicappedDao.updateTableUpdateDateById(population.getId());
			return population;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类HandicappedServiceImpl的updateHandicappedBaseInfo方法出现异常，原因：",
					"修改残疾人基本信息出现错误", e);
		}
	}

	public List<Handicapped> updateEmphasiseByIds(Long[] ids, Long isEmphasis) {
		List<Handicapped> list = new ArrayList<Handicapped>();
		try {
			for (int i = 0; i < ids.length; i++) {
				Handicapped handicapped = this.getHandicappedById(ids[i]);
				updateEmphasiseById(ids[i], isEmphasis);
				list.add(handicapped);
			}
			return list;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类HandicappedServiceImpl的updateEmphasiseByIds方法出现异常，原因：",
					"修改残疾人关注状态出现错误", e);

		}

	}

	private void updateEmphasiseById(Long id, Long isEmphasis) {
		LogoutDetail logoutDetail = new LogoutDetail();
		logoutDetail.setLogout(isEmphasis);
		updateLogOutByPopulationTypeAndId(logoutDetail,
				BaseInfoTables.HANDICAPPED_KEY, id);
	}

	@Override
	public void deleteHandicappedByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			for (int i = 0; i < ids.length; i++) {
				deleteHandicappedById(ids[i]);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类DruggyServiceImpl的deleteDruggyByIds方法出现异常，原因：",
					"删除吸毒人员信息出现错误", e);
		}

	}

	@Override
	public String[][] getExportPopertyArray() {
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return SpecialGroupsContext.getHandicappedPropertyArraySlf();
		} else {
			return SpecialGroupsContext.getHandicappedPropertyArrayRla();
		}
	}

	@Override
	public Handicapped hasDuplicateHandicapped(Long orgId, String idCardNo) {
		if (idCardNo == null || "".equals(idCardNo.trim()) || orgId == null) {
			return null;
		}
		idCardNo = idCardNo.toUpperCase();
		String idCardNo15 = "";
		String idCardNo18 = "";
		if (idCardNo.length() == 15) {
			idCardNo15 = idCardNo;
			idCardNo18 = IdCardUtil.idCardNo15to18(idCardNo, "19");
		} else if (idCardNo.length() == 18) {
			idCardNo15 = IdCardUtil.idCardNo18to15(idCardNo);
			idCardNo18 = idCardNo;
		}
		return handicappedDao.getHandicappedByIdCardNoAndOrganizationId(
				idCardNo15, idCardNo18, orgId);

	}

	@Override
	public Handicapped addHandicappedBaseInfo(Handicapped handicapped) {
		ValidateResult idleValidator = updateValidator
				.validateBaseInfo(handicapped);
		if (idleValidator.hasError()) {
			throw new BusinessValidationException(
					idleValidator.getErrorMessages());
		} else if (hasDuplicateHandicapped(handicapped.getOrganization()
				.getId(), handicapped.getIdCardNo(), handicapped.getId())) {
			throw new BusinessValidationException("该网格下已存在相同身份证号码");
		}
		try {
			if (checkDataExitInCache(handicapped,
					MemCacheConstant.CACHE_ADDHANDICAPPEDBASEINFO,
					CACHE_ADDHANDICAPPEDBASEINFO_VALUE)) {
				return handicapped;
			}
			return add(handicapped);
		} catch (Exception e) {
			logger.error("HandicappedServiceImpl addHandicappedBaseInfo", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(handicapped,
					MemCacheConstant.CACHE_ADDHANDICAPPEDBASEINFO);
		}
	}

	@Override
	public Integer getCount(SearchHandicappedVo searchHandicappedVo) {
		return handicappedDao.getCount(searchHandicappedVo);
	}

	@Override
	public List<HandicappedSdisabilityType> queryDisabilityLevelById(
			HandicappedSdisabilityType handicappedSdisabilityType) {
		return handicappedDao
				.queryDisabilityLevelById(handicappedSdisabilityType);
	}

	@Override
	public void deleteDisbilityType(
			HandicappedSdisabilityType handicappedSdisabilityType) {
		handicappedDao.deleteDisbilityType(handicappedSdisabilityType);
	}

	@Override
	public void saveHandicappedSdisabilityTypeToReal(
			HandicappedSdisabilityType handicappedSdisabilityType) {
		handicappedDao
				.saveHandicappedSdisabilityTypeToReal(handicappedSdisabilityType);
	}

	private Handicapped addHandicappedFromHouseHoldStaff(Handicapped handicapped) {
		try {
			ValidateResult idleValidator = updateValidator
					.validate(handicapped);
			if (!ExcelImportHelper.isImport.get()) {
				if (idleValidator.hasError()) {
					throw new BusinessValidationException(
							idleValidator.getErrorMessages());
				} else if (hasDuplicateHandicapped(handicapped
						.getOrganization().getId(), handicapped.getIdCardNo(),
						handicapped.getId())) {
					throw new BusinessValidationException("该网格下已存在相同身份证号码");
				}
			}
			List<Long> levelIds = handicapped.getDisabilityLevelIds();
			List<Long> newLevelIds = new ArrayList<Long>();
			Iterator<Long> it = levelIds.iterator();
			while (it.hasNext()) {
				Long id = it.next();
				if (id != null)
					newLevelIds.add(id);
			}
			handicapped.setDisabilityLevelIds(newLevelIds);
			List<HandicappedSdisabilityType> handicappedSdisabilityTypeList = editHandicapped(
					handicapped, false);
			handicapped = add(handicapped);
			saveHandicappedList(handicappedSdisabilityTypeList,
					handicapped.getId());
			return handicapped;
		} catch (Exception e) {
			logger.error(
					"HandicappedServiceImpladdHandicappedFromHouseHoldStaff", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public void deleteBusinessPopulationDuplicate(Long id) {
		this.deleteHandicappedById(id);
	}

}

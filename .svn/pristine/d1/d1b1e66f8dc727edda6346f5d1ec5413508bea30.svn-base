package com.tianque.aidsPopulations.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.aidsPopulations.dao.AidspopulationsDao;
import com.tianque.aidsPopulations.domain.Aidspopulations;
import com.tianque.aidsPopulations.service.AidspopulationsService;
import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateImpl;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.baseInfo.base.service.PopulationRelationService;
import com.tianque.cache.PageInfoCacheThreadLocal;
import com.tianque.cache.UpdateType;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
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
import com.tianque.validate.AbstractCountrymenValidator;

/**
 * :业务逻辑层
 * 
 * @author liaoshanshan
 * @date 2013-06-09 16:34:04
 */
@Service("aidspopulationService")
@Transactional
public class AidspopulationsServiceImpl extends BaseInfoPopulationTemplateImpl
		implements AidspopulationsService, PopulationProccessorService {

	private static Logger logger = LoggerFactory
			.getLogger(AidspopulationsServiceImpl.class);

	private static final String CACHE_ADDAIDSPOPULATIONS_VALUE = "CACHE_ADDAIDSPOPULATIONS";
	private static final String CACHE_ADDAIDSPOPULATIONBASEINFO_VALUE = "CACHE_ADDAIDSPOPULATIONBASEINFO";

	@Qualifier("aidspopulationsValidator")
	@Autowired
	private AbstractCountrymenValidator<Aidspopulations> updateValidator;
	@Autowired
	private AidspopulationsDao aidspopulationsDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PopulationRelationService populationRelationService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private ManagePopulationByHouseHelper managePopulationByHouseHelper;
	@Autowired
	private HousePopulationMaintanceService housePopulationMaintanceService;
	@Autowired
	private BaseInfoService baseInfoService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Resource(name = "aidspopulationsDao")
	public void setBaseInfoPopulationBaseDao(
			AidspopulationsDao aidspopulationsDao) {
		super.setBaseInfoPopulationBaseDao(aidspopulationsDao);
	}

	@Override
	public Aidspopulations addAidspopulationBaseInfo(
			Aidspopulations aidspopulations) {
		if (!ExcelImportHelper.isImport.get()) {
			updateValidator.validateBaseInfo(aidspopulations);
		}
		try {
			if (checkDataExitInCache(aidspopulations,
					MemCacheConstant.CACHE_ADDAIDSPOPULATIONBASEINFO,
					CACHE_ADDAIDSPOPULATIONBASEINFO_VALUE)) {
				return aidspopulations;
			}
			return add(aidspopulations);
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(aidspopulations,
					MemCacheConstant.CACHE_ADDAIDSPOPULATIONBASEINFO);
		}
	}

	@Override
	public Aidspopulations addAidspopulations(Aidspopulations aidspopulations) {
		if (!ExcelImportHelper.isImport.get()) {
			aidsPopulationsValidator(aidspopulations);
		}
		try {
			if (checkDataExitInCache(aidspopulations,
					MemCacheConstant.CACHE_ADDAIDSPOPULATIONS,
					CACHE_ADDAIDSPOPULATIONS_VALUE)) {
				return aidspopulations;
			}
			return add(aidspopulations);
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(aidspopulations,
					MemCacheConstant.CACHE_ADDAIDSPOPULATIONS);
		}
	}

	private Aidspopulations add(Aidspopulations aidspopulations) {
		autoFilled(aidspopulations);
		autoIsDeath(aidspopulations);
		contructCurrentAddress(aidspopulations);
		try {
			Countrymen temp = populationRelationService.businessOption(
					aidspopulations, aidspopulations.getActualPopulationType());
			aidspopulations.setBaseInfoId(temp.getBaseInfoId());
			aidspopulations.setAddressInfoId(temp.getAddressInfoId());
			aidspopulations.setSourcesState(null);
			aidspopulations = aidspopulationsDao.add(aidspopulations);
			populationRelationService
					.addPopulationRelation(temp.getId(),
							aidspopulations.getActualPopulationType(),
							aidspopulations.getId(),
							BaseInfoTables.AIDSPOPULATIONS_KEY);
			aidspopulations.setHouseId(temp.getHouseId());
			rebuildHouseAddress(aidspopulations);
			
			if (IsEmphasis.Emphasis.equals(aidspopulations.getIsEmphasis())) {
				// 缓存计数器
				PageInfoCacheThreadLocal.increment(MemCacheConstant
						.getCachePageKey(Aidspopulations.class.getSimpleName()),
						aidspopulations, 1);
			}
			return aidspopulations;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类AidspopulationsServiceImpl的addAidspopulations方法出现异常，原因：",
					"新增艾滋病人员信息出现错误", e);
		}
	}

	private void autoFilled(Aidspopulations aidspopulations) {
		aidspopulations
				.setIdCardNo(aidspopulations.getIdCardNo().toUpperCase());
		autoFillOrganizationInternalCode(aidspopulations);
		autoFillChinesePinyin(aidspopulations);
		autoFillBirthday(aidspopulations);
	}

	private void autoIsDeath(Aidspopulations aidspopulations) {
		if (aidspopulations.isDeath()) {
			aidspopulations.setIsEmphasis(IsEmphasis.IsNotEmphasis);
		} else {
			aidspopulations.setIsEmphasis(IsEmphasis.Emphasis);
		}
	}

	private void autoFillChinesePinyin(Aidspopulations aidspopulations) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(aidspopulations.getName());
		aidspopulations.setSimplePinyin(pinyin.get("simplePinyin"));
		aidspopulations.setFullPinyin(pinyin.get("fullPinyin"));
	}

	private void autoFillBirthday(Aidspopulations aidspopulations) {
		if (StringUtil.isStringAvaliable(aidspopulations.getIdCardNo())) {
			aidspopulations.setBirthday(IdCardUtil
					.parseBirthday(aidspopulations.getIdCardNo()));
		}
	}

	private void aidsPopulationsValidator(Aidspopulations aidspopulations) {
		ValidateResult baseDataValidator = updateValidator
				.validate(aidspopulations);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	private void autoFillOrganizationInternalCode(
			Aidspopulations aidspopulations) {
		Organization org = organizationDubboService
				.getSimpleOrgById(aidspopulations.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		aidspopulations.setOrgInternalCode(org.getOrgInternalCode());
	}

	@Override
	public void deleteAidspopulationByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			for (int i = 0; i < ids.length; i++) {
				deleteAidspopulationsById(ids[i]);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类AidspopulationsServiceImpl的deleteAidspopulationsByIds方法出现异常，原因：",
					"删除艾滋病人员信息出现错误", e);
		}
	}

	private void deleteAidspopulationsById(Long id) {
		if (id == null || id < 0L) {
			throw new BusinessValidationException("传入参数为空");
		}
		// 删除
		Aidspopulations aidspopulations = getAidspopulationsById(id);
		if (aidspopulations == null) {
			return;
		}
		aidspopulations.setPopulationTypeBean(getPopulationRelationService()
				.getBusinessPopulationTypeBean(id,
						PopulationType.AIDSPOPULATIONS));
		getRecoverDatasService().deleteActualPopulation(aidspopulations);
		populationRelationService.businessDeletePopulationRelation(id,
				PopulationType.AIDSPOPULATIONS);

		aidspopulationsDao.delete(id);
		if (IsEmphasis.Emphasis.equals(aidspopulations.getIsEmphasis())) {
			// 缓存计数器
			PageInfoCacheThreadLocal.decrease(MemCacheConstant
					.getCachePageKey(Aidspopulations.class.getSimpleName()),
					aidspopulations, 1);
		}
	}

	@Override
	public PageInfo<Aidspopulations> findAidspopulationsForPage(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			Long isEmphasis) {
		try {
			if (null == orgId) {
				return constructEmptyPageInfo();
			} else {
				Organization org = organizationDubboService
						.getSimpleOrgById(orgId);
				if (org == null) {
					return constructEmptyPageInfo();
				} else {
					Map<String, Object> query = new HashMap<String, Object>();
					query.put("orgInternalCode", org.getOrgInternalCode());
					query.put("isEmphasis", isEmphasis);
					PageInfo<Aidspopulations> pageInfos = aidspopulationsDao
							.findPagerUsingCacheBySearchVo(
									orgId,
									query,
									pageNum,
									pageSize,
									"AidspopulationsDefaultList",
									MemCacheConstant
											.getCachePageKey(Aidspopulations.class));
					fitCountrymen(pageInfos);
					fitServiceMemberHasObject(
							BaseInfoTables.AIDSPOPULATIONS_KEY, pageInfos);
					//隐藏身份证中间4位
					pageInfos=hiddenIdCard(pageInfos);
					return pageInfos;

				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类aidspopulationsServiceImpl的findAidspopulationsForPage方法出现异常，原因：",
					"查询艾滋病人员信息出现错误", e);
		}
	}

	//隐藏身份证中间4位
		private PageInfo<Aidspopulations> hiddenIdCard(PageInfo<Aidspopulations> pageInfo){
					//判断权限，有权限不隐藏
					if(permissionDubboService.
							isUserHasPermission(ThreadVariable.getUser().getId(), "isAidspopulationsManagementNotHidCard")){
						return pageInfo;
					}
					List<Aidspopulations> list = pageInfo.getResult();
					int index=0;
					for (Aidspopulations verification:list) {
						verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
						list.set(index, verification);
						index++;
					}
					pageInfo.setResult(list);
					return pageInfo;
			}
	/**
	 * 构造空pageInfo
	 * 
	 * @return
	 */
	private PageInfo<Aidspopulations> constructEmptyPageInfo() {
		PageInfo<Aidspopulations> result = new PageInfo<Aidspopulations>();
		result.setResult(new ArrayList<Aidspopulations>());
		return result;
	}

	@Override
	public Aidspopulations getAidspopulationById(Long id) {
		return getAidspopulationsById(id);
	}

	@Override
	public Aidspopulations getAidspopulationsById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			return aidspopulationsDao.get(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类AidspopulationsServiceImpl的getAidspopulationsById方法出现异常，原因：",
					"获取艾滋病人员信息出现错误", e);
		}
	}

	@Override
	public Aidspopulations updateAidspopulations(Aidspopulations aidspopulations) {
		if (!ExcelImportHelper.isImport.get()) {
			aidsPopulationsValidator(aidspopulations);
		}
		try {
			autoFilled(aidspopulations);
			if (aidspopulations.isDeath()) {
				aidspopulations.setIsEmphasis(IsEmphasis.IsNotEmphasis);
			}
			Countrymen temp = populationRelationService.businessOption(
					aidspopulations, aidspopulations.getActualPopulationType());
			aidspopulations.setHouseId(temp.getHouseId());
			rebuildHouseAddress(aidspopulations);
			aidspopulations = aidspopulationsDao.update(aidspopulations);
			return aidspopulations;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类aidspopulationsServiceImpl的updateAidspopulations方法出现异常，原因：",
					"修改艾滋病人员信息出现错误", e);
		}
	}

	@Override
	public Aidspopulations updateAidspopulationsBusiness(
			Aidspopulations aidspopulations) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				ValidateResult specializedValidator = updateValidator
						.validateSpecializedInfo(aidspopulations);
				if (null != specializedValidator
						&& specializedValidator.hasError()) {
					throw new BusinessValidationException(
							specializedValidator.getErrorMessages());
				}
			}
			aidspopulations = aidspopulationsDao
					.updateBusiness(aidspopulations);
			PageInfoCacheThreadLocal.update(
					MemCacheConstant.getCachePageKey(Aidspopulations.class),
					aidspopulations, UpdateType.BUSINESS);
			return aidspopulations;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类aidsPopulationsServiceImpl的updateaidsPopulationsBusiness方法出现异常，原因：",
					"修改艾滋病人员业务信息出现错误", e);
		}
	}

	@Override
	public void deletePopulationByPopulationId(Long populationId) {
		if (null != populationId) {
			this.deleteAidspopulationsById(populationId);
			this.deletePopulationTypeByPopulationIdAndType(populationId,
					PopulationType.AIDSPOPULATIONS);
		}
	}

	@Override
	public Map<String, Map<String, Object>> getPopulationSpecializedInfoByOrgIdAndIdCardNo(
			Long orgId, String idCardNo) {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		try {
			Aidspopulations aidsPopulations = aidspopulationsDao
					.getByOrgIdAndIdCardNo(orgId, idCardNo);
			if (null != aidsPopulations) {
				Map<String, Object> aidsPopulationsMap = new HashMap<String, Object>();
				aidsPopulationsMap.put("id", aidsPopulations.getId());
				aidsPopulationsMap.put("isEmphasis",
						aidsPopulations.getIsEmphasis());
				aidsPopulationsMap.put("infectway",
						aidsPopulations.getInfectway());
				aidsPopulationsMap.put("violationsofthelaw",
						aidsPopulations.getViolationsofthelaw());
				aidsPopulationsMap.put("crimetype",
						aidsPopulations.getCrimetype());
				aidsPopulationsMap.put("receivedlevel",
						aidsPopulations.getReceivedlevel());
				aidsPopulationsMap.put("helpcircumstances",
						aidsPopulations.getHelpcircumstances());
				aidsPopulationsMap.put("addressno",
						aidsPopulations.getAddressno());
				aidsPopulationsMap.put("receivedorganization",
						aidsPopulations.getReceivedorganization());
				map.put(PopulationType.AIDSPOPULATIONS, aidsPopulationsMap);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类aidsPopulationsServiceImpl的getPopulationSpecializedInfoByOrgIdAndIdCardNo方法出现异常，原因：",
					"获取艾滋病人员信息出现错误", e);
		}
		return map;
	}

	@Override
	public Long proccessPopulationSpecializedInfo(
			ActualPopulation actualPopulation, String[] populationType,
			Map<String, Object> population) {
		actualPopulation
				.setAttentionPopulationType(NewBaseInfoTables.AIDSPOPULATIONS_KEY);
		try {
			Long orgId = Long.valueOf(((String[]) population
					.get("organization.id"))[0]);
			String idCardNo = ((String[]) population.get("idCardNo"))[0];
			Aidspopulations aidspopulations = aidspopulationsDao
					.getByOrgIdAndIdCardNo(orgId, idCardNo);
			if (!com.tianque.util.Arrays.hasObjectInArray(populationType,
					PopulationType.AIDSPOPULATIONS_KEY)) {
				if (null != aidspopulations) {
					updateLogOutByPopulationTypeAndId(
							buildLogoutDetailByIsEmphasisAndReason(
									IsEmphasis.IsNotEmphasis,
									actualPopulation.getActualPopulationType()),
							BaseInfoTables.AIDSPOPULATIONS_KEY, aidspopulations
									.getId());

				}
			} else {
				ExcelImportHelper.isImport.set(false);
				if (null == aidspopulations) {
					aidspopulations = new Aidspopulations();
					actualPopulation.setAttentionPopulationType(aidspopulations
							.getAttentionPopulationType());
					copyProperty(actualPopulation, population, aidspopulations);
					addAidspopulations(aidspopulations);
				} else {
					Long id = aidspopulations.getId();
					copyProperty(actualPopulation, population, aidspopulations);
					aidspopulations.setId(id);
					aidspopulations.setIsEmphasis(IsEmphasis.Emphasis);
					updateAidspopulations(aidspopulations);
					updateLogOutByPopulationTypeAndId(
							buildLogoutDetailByIsEmphasisAndReason(
									IsEmphasis.Emphasis,
									actualPopulation.getActualPopulationType()),
							BaseInfoTables.AIDSPOPULATIONS_KEY, aidspopulations
									.getId());
				}
			}
			return aidspopulations == null
					|| aidspopulations.getIsEmphasis() == IsEmphasis.IsNotEmphasis
							.longValue() ? null : aidspopulations.getId();
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类aidsPopulationsServiceImpl的proccessPopulationSpecializedInfo方法出现异常，原因：",
					"处理艾滋病人员信息出现错误", e);
		}
	}

	/**
	 * 如果人口的房屋信息（CurrentAddress）不为空，并且房屋id不存在，新增一个房屋，并且建立关联关系, 如果房屋id不为空直接建立关联关系
	 * 如果房屋信息为空,并且有房屋id不为空，则删除人房关系
	 * 
	 * @param householdStaff
	 */
	private void rebuildHouseAddress(Aidspopulations householdStaff) {

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

				houseInfo = actualHouseService.addHouseInfo(houseInfo);

				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.AIDS_POPULATIONS, householdStaff,
						houseInfo.getId());
			} else if (householdStaff.getHouseId() != null) {
				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.AIDS_POPULATIONS, householdStaff,
						householdStaff.getHouseId());
			}
		} else {
			housePopulationMaintanceService.releaseHouse(
					PopulationCatalog.AIDS_POPULATIONS, householdStaff.getId(),
					householdStaff.getHouseId());
		}
	}

	/**
	 * 实有人口属性转为艾滋病人员中的基本信息
	 * 
	 * @param actualPopulation
	 * @param population
	 * @param aidspopulations
	 */
	private void copyProperty(ActualPopulation actualPopulation,
			Map<String, Object> population, Aidspopulations aidspopulations) {
		String typeName = "";
		try {
			PropertyUtils.copyProperties(aidspopulations, actualPopulation);

			aidspopulations.setInfectway(Arrays.getPropertyDictFromArray(
					population, typeName + "infectway.id"));
			aidspopulations.setAttentionExtent(Arrays.getPropertyDictFromArray(
					population, typeName + "attentionExtent.id"));
			aidspopulations.setViolationsofthelaw(Arrays
					.getPropertyDictFromArray(population, typeName
							+ "violationsofthelaw.id"));
			aidspopulations.setCrimetype(Arrays.getPropertyDictFromArray(
					population, typeName + "crimetype.id"));
			aidspopulations.setReceivedlevel(Arrays.getPropertyDictFromArray(
					population, typeName + "receivedlevel.id"));
			aidspopulations.setHelpcircumstances(Arrays.getLongValueFromArray(
					population, typeName + "helpcircumstances"));
			aidspopulations.setReceivedorganization(Arrays
					.getStringValueFromArray(population, typeName
							+ "receivedorganization"));

			aidspopulations
					.setAttentionPopulationType(BaseInfoTables.AIDSPOPULATIONS_KEY);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceValidationException(e.getMessage(), e);
		}
	}

	private LogoutDetail buildLogoutDetailByIsEmphasisAndReason(Long logout,
			String actualType) {
		String reason = "此人口在" + PopulationCatalog.parse(actualType).toString()
				+ "模块";
		LogoutDetail result = new LogoutDetail();
		if (IsEmphasis.IsNotEmphasis.equals(logout)) {
			result.setLogoutDate(new Date());
			result.setLogoutReason(reason + "注销");
			result.setLogout(logout);
		} else if (IsEmphasis.Emphasis.equals(logout)) {
			result.setLogout(logout);
		}

		return result;
	}

	@Override
	public void updatePopulationBaseInfo(ActualPopulation actualPopulation) {
		try {
			Aidspopulations aidspopulations = aidspopulationsDao
					.getByOrgIdAndIdCardNo(actualPopulation.getOrganization()
							.getId(), actualPopulation.getIdCardNo());
			if (null != aidspopulations) {
				Long id = aidspopulations.getId();
				PropertyUtils.copyProperties(aidspopulations, actualPopulation);
				aidspopulations.setId(id);
				updateAidspopulations(aidspopulations);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类AidspopulationsServiceImpl的updatePopulationBaseInfo方法出现异常，原因：",
					"修改艾滋病人员基本信息出现错误", e);
		}
	}

	@Override
	public Aidspopulations updateAidspopulationsBaseInfo(
			Aidspopulations aidspopulations) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				ValidateResult baseDataValidator = updateValidator
						.validateBaseInfo(aidspopulations);
				if (baseDataValidator.hasError()) {
					throw new BusinessValidationException(
							baseDataValidator.getErrorMessages());
				}
			}
			aidspopulations.setIdCardNo(aidspopulations.getIdCardNo()
					.toUpperCase());
			autoFillBirthday(aidspopulations);
			autoFillChinesePinyin(aidspopulations);
			autoFillOrganizationInternalCode(aidspopulations);
			if (aidspopulations.isDeath()) {
				aidspopulations
						.setLogoutDetail(buildLogoutDetail(aidspopulations
								.isDeath()));
				// 缓存计数器
				PageInfoCacheThreadLocal.decrease(
						MemCacheConstant.getCachePageKey(Aidspopulations.class
								.getSimpleName()), aidspopulations, 1);
			}
			Countrymen temp = populationRelationService.businessOption(
					aidspopulations, aidspopulations.getActualPopulationType());
			aidspopulations.setHouseId(temp.getHouseId());
			rebuildHouseAddress(aidspopulations);
			aidspopulationsDao.updateTableUpdateDateById(aidspopulations.getId());
			return aidspopulations;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类aidspopulationsServiceImpl的updateaidspopulationsBaseInfo方法出现异常，原因：",
					"修改艾滋病人员基本信息出现错误", e);
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
	public boolean hasDuplicateAidspopulations(Long orgId, String idCardNo,
			Long exceptedId) {
		if (idCardNo == null || "".equals(idCardNo.trim()) || orgId == null) {
			return false;
		}
		try {
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
			Countrymen countrymen = baseInfoService
					.getBaseInfoByIdCardNo(idCardNo);
			if (countrymen == null) {
				return false;
			}
			Long exsited = aidspopulationsDao.getIdByBaseinfoIdAndOrgId(
					countrymen.getId(), orgId);
			return exceptedId == null ? exsited != null
					: (exsited != null && !exceptedId.equals(exsited));
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类AidspopulationsServiceImpl的hasDuplicateAidspopulations方法出现异常，原因：",
					"判断艾滋病人员身份证号码是否存在出现错误", e);
		}
	}

	@Override
	public Aidspopulations hasDuplicateAidspopulations(Long orgId,
			String idCardNo) {
		if (idCardNo == null || "".equals(idCardNo.trim()) || orgId == null) {
			return null;
		}
		try {
			idCardNo = idCardNo.toUpperCase();
			List<String> list = new ArrayList<String>();
			list.add(idCardNo);
			if (idCardNo.length() == 18) {
				list.add(IdCardUtil.idCardNo18to15(idCardNo));
			} else if (idCardNo.length() == 15) {
				list.add(IdCardUtil.idCardNo15to18(idCardNo, "19"));
				list.add(IdCardUtil.idCardNo15to18(idCardNo, "20"));
			}
			return aidspopulationsDao.getByIdCard(list, orgId);

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类AidspopulationsServiceImpl的hasDuplicateAidspopulations方法出现异常，原因：",
					"判断艾滋病人员身份证号码是否存在出现错误", e);
		}
	}

	@Override
	public Aidspopulations updateAidspopulationsByIdCardNoAndOrgId(
			String idCardNo, Long orgId, Aidspopulations domain) {
		try {
			Aidspopulations older = this.getAidspopulationsByIdCardNo(idCardNo,
					orgId);
			domain.setId(older.getId());
			domain.setCreateDate(older.getCreateDate());
			domain.setCreateUser(older.getCreateUser());
			return updateAidspopulations(domain);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类AidspopulationsServiceImpl的updateAidspopulationsByIdCardNoAndOrgId方法出现异常，原因：",
					"修改艾滋病人员信息出现错误", e);
		}
	}

	@Override
	public Aidspopulations getAidspopulationsByIdCardNo(String idcardNo,
			Long claimOrgId) {
		if (idcardNo == null || "".equals(idcardNo.trim())
				|| claimOrgId == null) {
			return null;
		}
		idcardNo = idcardNo.toUpperCase();
		List<String> list = new ArrayList<String>();
		list.add(idcardNo);
		if (idcardNo.length() == 18) {
			list.add(IdCardUtil.idCardNo18to15(idcardNo));
		} else if (idcardNo.length() == 15) {
			list.add(IdCardUtil.idCardNo15to18(idcardNo, "19"));
			list.add(IdCardUtil.idCardNo15to18(idcardNo, "20"));
		}
		return aidspopulationsDao.getByIdCard(list, claimOrgId);
	}

	@Override
	public List<Aidspopulations> updateDeathByIds(Long[] populationIds,
			Boolean death) {
		List<Aidspopulations> list = new ArrayList<Aidspopulations>();
		for (int i = 0; i < populationIds.length; i++) {
			Aidspopulations population = this
					.getAidspopulationsById(populationIds[i]);
			updateLogOutByPopulationTypeAndId(
					LogoutDetail.buildLogoutDetail(death),
					BaseInfoTables.AIDSPOPULATIONS_KEY, population.getId());
			baseInfoService.updateDeathStateById(population.getBaseInfoId(),
					death, population.getIdCardNo(), population
							.getOrganization().getId(), population
							.getOrgInternalCode(),
					NewBaseInfoTables.AIDSPOPULATIONS_KEY);
			list.add(population);
			// 缓存计数器
			PageInfoCacheThreadLocal.increment(MemCacheConstant
					.getCachePageKey(Aidspopulations.class.getSimpleName()),
					population, 1);
		}
		return list;
	}

	@Override
	public void deleteBusinessPopulationDuplicate(Long id) {
		this.deleteAidspopulationsById(id);
	}

}

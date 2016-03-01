package com.tianque.baseInfo.elderlyPeople.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.tianque.baseInfo.base.service.AddressInfoService;
import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateImpl;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.baseInfo.base.service.PopulationRelationService;
import com.tianque.baseInfo.elderlyPeople.dao.ElderlyPeopleDao;
import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.baseInfo.utils.CollectionsUtil;
import com.tianque.cache.PageInfoCacheThreadLocal;
import com.tianque.cache.UpdateType;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.PeopleHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.SearchElderlyPeopleVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.OperationFailedException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.HousePopulationMaintanceService;
import com.tianque.service.PopulationProccessorService;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.Arrays;
import com.tianque.util.IdCardUtil;
import com.tianque.util.PluginServiceHelpUtil;
import com.tianque.util.PropertyUtil;
import com.tianque.validate.AbstractCountrymenValidator;

@Transactional
@Service("elderlyPeopleService")
public class ElderlyPeopleServiceImpl extends BaseInfoPopulationTemplateImpl
		implements ElderlyPeopleService, PopulationProccessorService {

	private static final String CACHE_ADDELDERLYPEOPLE_VALUE = "CACHE_ADDELDERLYPEOPLE";
	private static final String CACHE_ADDELDERLYPEOPLEBASEINFO_VALUE = "CACHE_ADDELDERLYPEOPLEBASEINFO";

	@Qualifier("elderlyPeopleValidator")
	@Autowired
	private AbstractCountrymenValidator<ElderlyPeople> updateValidator;
	@Autowired
	private ElderlyPeopleDao elderlyPeopleDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
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
	private AddressInfoService addressInfoService;
	@Autowired
	@Qualifier("peopleHelper")
	PeopleHelper peopleHelper;
	@Autowired
	private ShardConversion shardConversion;
	@Autowired
	private PermissionDubboService permissionDubboService;

	// @Autowired
	// private CacheService cacheService;

	@Resource(name = "elderlyPeopleDao")
	public void setBaseInfoPopulationBaseDao(ElderlyPeopleDao elderlyPeopleDao) {
		super.setBaseInfoPopulationBaseDao(elderlyPeopleDao);
	}

	public ElderlyPeopleServiceImpl() {
		setElderlyPeopleService(this);
	}

	@Override
	public ElderlyPeople addElderlyPeople(ElderlyPeople elderlyPeople) {
		if (!ExcelImportHelper.isImport.get()) {
			ValidateResult elderlyPeopleValidator = updateValidator
					.validate(elderlyPeople);
			if (elderlyPeopleValidator.hasError()) {
				throw new BusinessValidationException(
						elderlyPeopleValidator.getErrorMessages());
			} else if (hasDuplicateElderlyPeople(elderlyPeople
					.getOrganization().getId(), elderlyPeople.getIdCardNo(),
					elderlyPeople.getId())) {
				throw new BusinessValidationException("该网格下已存在相同身份证号码");
			}
		}
		try {
			if (checkDataExitInCache(elderlyPeople,
					MemCacheConstant.CACHE_ADDELDERLYPEOPLE,
					CACHE_ADDELDERLYPEOPLE_VALUE)) {
				return elderlyPeople;
			}
			return add(elderlyPeople);
		} catch (Exception e) {
			logger.error("ElderlyPeopleServiceImpl addElderlyPeople", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(elderlyPeople,
					MemCacheConstant.CACHE_ADDELDERLYPEOPLE);
		}
	}

	// private ElderlyPeople checkAddOrUpdate(ElderlyPeople elderlyPeople) {
	// ElderlyPeople people = null;
	// if (elderlyPeople != null && elderlyPeople.getId() != null) {
	// people = elderlyPeopleDao.get(elderlyPeople.getId());
	// } else {
	// people = elderlyPeopleDao.getElderlyPeopleByIdCardNoAndOrgId(
	// elderlyPeople.getIdCardNo(), elderlyPeople
	// .getOrganization().getId());
	// }
	// if (people != null) {
	// elderlyPeople.setOrganization(people.getOrganization());
	// elderlyPeople.setOrgInternalCode(people.getOrgInternalCode());
	// elderlyPeople.setBaseInfoId(people.getBaseInfoId());
	// elderlyPeople.setAddressInfoId(people.getAddressInfoId());
	// elderlyPeople.setId(people.getId());
	// elderlyPeople.setActualPopulationType(people
	// .getActualPopulationType());
	// return updateElderlyPeopleBaseInfo(elderlyPeople);
	// } else {
	// return add(elderlyPeople);
	// }
	// }

	private ElderlyPeople add(ElderlyPeople elderlyPeople) {
		elderlyPeople.setIdCardNo(elderlyPeople.getIdCardNo().toUpperCase());
		autoFillOrganizationInternalCode(elderlyPeople);
		autoFillBirthday(elderlyPeople);
		autoFillChinesePinyin(elderlyPeople);
		autoFillGender(elderlyPeople);
		autoIsDeath(elderlyPeople);
		contructCurrentAddress(elderlyPeople);
		elderlyPeople.setShardCode(shardConversion.getShardCode(elderlyPeople
				.getOrganization().getId()));
		try {
			Countrymen temp = populationRelationService.businessOption(
					elderlyPeople, elderlyPeople.getActualPopulationType());
			elderlyPeople.setBaseInfoId(temp.getBaseInfoId());
			elderlyPeople.setAddressInfoId(temp.getAddressInfoId());
			elderlyPeople.setSourcesState(null);
			elderlyPeople = elderlyPeopleDao.addShard(elderlyPeople);
			populationRelationService.addPopulationRelation(temp.getId(),
					elderlyPeople.getActualPopulationType(),
					elderlyPeople.getId(), BaseInfoTables.ELDERLYPEOPLE_KEY);
			elderlyPeople.setHouseId(temp.getHouseId());
			rebuildHouseAddress(elderlyPeople);
			if (IsEmphasis.Emphasis.equals(elderlyPeople.getIsEmphasis())) {
				// 缓存计数器
				PageInfoCacheThreadLocal.increment(MemCacheConstant
						.getCachePageKey(ElderlyPeople.class.getSimpleName()),
						elderlyPeople, 1);
			}
			return elderlyPeople;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ElderlyPeopleServiceImpl的addElderlyPeople方法出现异常，原因：",
					"新增信息出现错误", e);
		}
	}

	@Override
	public ElderlyPeople addElderlyPeopleForJob(Countrymen countrymen,
			Long sourcesState) {
		if (countrymen == null || countrymen.getAddressInfoId() == null
				|| countrymen.getBaseInfoId() == null
				|| countrymen.getId() == null) {
			throw new BusinessValidationException("基础信息不全");
		}
		ElderlyPeople elderlyPeople = new ElderlyPeople();
		try {
			PropertyUtil.copyPropertiesWithRecursion(Countrymen.class,
					elderlyPeople, countrymen, new String[] { "id" });
		} catch (Exception e) {
			throw new BusinessValidationException("基础信息不全或不正确");
		}
		elderlyPeople.setSourcesState(sourcesState);
		elderlyPeople.setIsEmphasis(IsEmphasis.Emphasis);
		elderlyPeople
				.setCreateDate(ThreadVariable.getSession().getAccessTime());
		elderlyPeople.setCreateUser(ThreadVariable.getSession().getUserName());
		elderlyPeople
				.setUpdateDate(ThreadVariable.getSession().getAccessTime());
		String shardCode = shardConversion.getShardCode(elderlyPeople.getOrganization().getId());
		elderlyPeople.setShardCode(shardCode);
		Long elderlyPeopleId = elderlyPeopleDao
				.saveElderlyPeopleForJob(elderlyPeople);
		elderlyPeople.setId(elderlyPeopleId);
		populationRelationService.addPopulationRelation(countrymen.getId(),
				elderlyPeople.getActualPopulationType(), elderlyPeopleId,
				BaseInfoTables.ELDERLYPEOPLE_KEY);
		if (countrymen.getHouseId() != null) {
			managePopulationByHouseHelper.reBindHouseIfNecc(
					PopulationCatalog.ELDERLY_PEOPLE, elderlyPeople,
					countrymen.getHouseId());
		}
		// JOB的不累加缓存计数器
		if (!ConstantsProduct.SourcesState.JOB.equals(sourcesState)) {
			// 缓存计数器
			PageInfoCacheThreadLocal.increment(MemCacheConstant
					.getCachePageKey(ElderlyPeople.class.getSimpleName()),
					elderlyPeople, 1);
		}
		return elderlyPeople;
	}

	/**
	 * 如果人口的房屋信息（CurrentAddress）不为空，并且房屋id不存在，新增一个房屋，并且建立关联关系, 如果房屋id不为空直接建立关联关系
	 * 如果房屋信息为空,并且有房屋id不为空，则删除人房关系
	 * 
	 * @param householdStaff
	 */
	private void rebuildHouseAddress(ElderlyPeople householdStaff) {

		if (householdStaff.getIsHaveHouse() != null
				&& householdStaff.getIsHaveHouse()
				&& StringUtil.isStringAvaliable(householdStaff
						.getCurrentAddress())) {

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
						.setHouseOperateSource(NewBaseInfoTables.ELDERLYPEOPLE_KEY);
				houseInfo = actualHouseService.addHouseInfo(houseInfo);

				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.ELDERLY_PEOPLE, householdStaff,
						houseInfo.getId());
			} else if (householdStaff.getHouseId() != null) {
				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.ELDERLY_PEOPLE, householdStaff,
						householdStaff.getHouseId());
			}
		} else {
			housePopulationMaintanceService.releaseHouse(
					PopulationCatalog.ELDERLY_PEOPLE, householdStaff.getId(),
					householdStaff.getHouseId());
		}
	}

	@Override
	public boolean deleteElderlyPeopleById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id不能为空");
		}
		ElderlyPeople domain = getElderlyPeopleById(id);
		if (null != domain) {

			domain.setPopulationTypeBean(getPopulationRelationService()
					.getBusinessPopulationTypeBean(id,
							PopulationType.ELDERLY_PEOPLE));
			getRecoverDatasService().deleteActualPopulation(domain);
			populationRelationService.businessDeletePopulationRelation(id,
					PopulationType.ELDERLY_PEOPLE);

			elderlyPeopleDao.deleteShard(id);
			try {
				PluginServiceHelpUtil.doService("routerService",
						"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class },
						PopulationType.ELDERLY_PEOPLE, id);
				/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
				PluginServiceHelpUtil.doService("routerService",
						"setOrgIdAndCardNoOrName", new Class[] { Long.class,
								String.class, String.class, Long.class },
						domain.getOrganization().getId(), domain.getIdCardNo(),
						PopulationType.ELDERLY_PEOPLE, id);

				if (IsEmphasis.Emphasis.equals(domain.getIsEmphasis())) {
					// 缓存计数器
					PageInfoCacheThreadLocal.decrease(MemCacheConstant
							.getCachePageKey(ElderlyPeople.class
									.getSimpleName()), domain, 1);
				}

			} catch (Exception e) {
				logger.error(
						"ElderlyPeopleServiceImpl deleteElderlyPeopleById", e);
			} finally {
				this.deletePopulationTypeByPopulationIdAndType(id,
						PopulationType.ELDERLY_PEOPLE);
			}
		}
		return true;
	}

	@Override
	public PageInfo<ElderlyPeople> findElderlyPeopleForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, Long isEmphasis) {
		if (orgId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return constructEmptyPageInfo();
			} else {
				SearchElderlyPeopleVo elderlyPeopleVo = new SearchElderlyPeopleVo();
				elderlyPeopleVo.setOrgInternalCode(org.getOrgInternalCode());
				elderlyPeopleVo.setIsEmphasis(isEmphasis);
				String shardCode = shardConversion.getShardCode(org
						.getDepartmentNo());
				elderlyPeopleVo.setShardCode(shardCode);
				PageInfo<ElderlyPeople> pageInfo = elderlyPeopleDao
						.findPagerUsingCacheBySearchVo(orgId, elderlyPeopleVo,
								pageNum, pageSize, sidx, sord, MemCacheConstant
										.getCachePageKey(ElderlyPeople.class
												.getSimpleName()));

				// PageInfo<ElderlyPeople> pageInfo = elderlyPeopleDao
				// .findElderlyPeopleForPageByOrgInternalCode(
				// org.getOrgInternalCode(), pageNum, pageSize,
				// sidx, sord, isEmphasis);
				fitCountrymen(pageInfo);
				fitServiceMemberHasObject(BaseInfoTables.ELDERLYPEOPLE_KEY,
						pageInfo);
				new CollectionsUtil<ElderlyPeople>().sortList(
						pageInfo.getResult(), sord, sidx);
				//隐藏身份证中间4位
				pageInfo=hiddenIdCard(pageInfo);
				return pageInfo;
			}
		}
	}

	//隐藏身份证中间4位
	private PageInfo<ElderlyPeople> hiddenIdCard(PageInfo<ElderlyPeople> pageInfo){
						//判断权限，有权限不隐藏
						if(permissionDubboService.
								isUserHasPermission(ThreadVariable.getUser().getId(), "isElderlyPeopleManagementNotHidCard")){
							return pageInfo;
						}
						List<ElderlyPeople> list = pageInfo.getResult();
						int index=0;
						for (ElderlyPeople verification:list) {
							verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
							list.set(index, verification);
							index++;
						}
						pageInfo.setResult(list);
						return pageInfo;
	}
			
	private PageInfo<ElderlyPeople> constructEmptyPageInfo() {
		PageInfo<ElderlyPeople> result = new PageInfo<ElderlyPeople>();
		result.setResult(new ArrayList<ElderlyPeople>());
		return result;
	}

	@Override
	public ElderlyPeople getElderlyPeopleById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("ID不能为空");
		}
		return elderlyPeopleDao.getShard(id);
	}

	@Override
	public ElderlyPeople getElderlyPeopleByIdCardNoAndOrganizationId(
			String idCardNo, Long organizationId) {
		if (idCardNo == null || organizationId == null) {
			throw new BusinessValidationException("参数错误");
		}
		idCardNo = idCardNo.toUpperCase();
		String shardCode = shardConversion.getShardCode(organizationId);
		return elderlyPeopleDao.getElderlyPeopleByIdCardNoAndOrganizationId(
				idCardNo, organizationId, shardCode);
	}

	@Override
	public Long getElderlyPeopleByBaseinfIdAndOrgId(Long baseInfoId,
			Long organizationId) {
		if (baseInfoId == null || organizationId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return elderlyPeopleDao.getIdByBaseinfoIdAndOrgId(baseInfoId,
				organizationId);
	}

	@Override
	public boolean hasDuplicateElderlyPeople(Long orgId, String idCardNo,
			Long exceptedId) {
		if (idCardNo == null || "".equals(idCardNo.trim()) || orgId == null) {
			return false;
		}
		idCardNo = idCardNo.toUpperCase();
		Countrymen countrymen = baseInfoService.getBaseInfoByIdCardNo(idCardNo);
		if (countrymen == null) {
			return false;
		}
		String shardCode = shardConversion.getShardCode(orgId);
		Long exsited = elderlyPeopleDao.getIdByBaseinfoIdAndOrgId(
				countrymen.getId(), orgId, shardCode);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited));
	}

	@Override
	public ElderlyPeople updateElderlyPeople(ElderlyPeople elderlyPeople) {
		elderlyPeople.setIdCardNo(elderlyPeople.getIdCardNo().toUpperCase());
		autoFillOrganizationInternalCode(elderlyPeople);
		autoFillBirthday(elderlyPeople);
		ValidateResult elderlyPeopleValidator = updateValidator
				.validate(elderlyPeople);

		if (elderlyPeopleValidator.hasError()) {
			throw new BusinessValidationException(
					elderlyPeopleValidator.getErrorMessages());
		} else if (hasDuplicateElderlyPeople(elderlyPeople.getOrganization()
				.getId(), elderlyPeople.getIdCardNo(), elderlyPeople.getId())) {
			throw new BusinessValidationException("该网格下已存在相同身份证号码");
		}
		autoFillChinesePinyin(elderlyPeople);
		autoFillGender(elderlyPeople);
		if (elderlyPeople.isDeath()) {
			elderlyPeople.setIsEmphasis(1L);
			deletePopulationTypeByPopulationIdAndType(elderlyPeople.getId(),
					PopulationType.ELDERLY_PEOPLE);
		}
		try {
			contructCurrentAddress(elderlyPeople);
			elderlyPeople = elderlyPeopleDao.updateBusinessShard(elderlyPeople);
			this.proccessHouseBind(elderlyPeople);
			return elderlyPeople;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ElderlyPeopleServiceImpl的updateElderlyPeople方法出现异常，原因：",
					"修改信息出现错误", e);
		}
	}

	/**
	 * 根据老年人对象的网格id来获取网格对象
	 * 
	 * @param elderlyPeople
	 */
	private void autoFillOrganizationInternalCode(ElderlyPeople elderlyPeople) {
		if (elderlyPeople.getOrganization() == null) {
			throw new BusinessValidationException("找不到指定的网格");
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(elderlyPeople.getOrganization().getId());
			if (organization == null) {
				throw new BusinessValidationException("找不到指定的网格");
			}
			elderlyPeople.setOrgInternalCode(organization.getOrgInternalCode());
		}
	}

	private void autoFillChinesePinyin(ElderlyPeople elderlyPeople) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(elderlyPeople.getName());
		elderlyPeople.setFullPinyin((String) pinyin.get("fullPinyin"));
		elderlyPeople.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	/**
	 * 填充性别
	 * 
	 * @param householdStaff
	 */
	private void autoFillGender(ElderlyPeople elderlyPeople) {
		elderlyPeople.setGender(peopleHelper.autoFillGender(
				PropertyTypes.GENDER, elderlyPeople.getIdCardNo()));
	}

	/**
	 * 根据老年人的身份证号来获取出生日期
	 * 
	 * @param elderlyPeople
	 */
	private void autoFillBirthday(ElderlyPeople elderlyPeople) {
		if (StringUtil.isStringAvaliable(elderlyPeople.getIdCardNo())) {
			elderlyPeople.setBirthday(IdCardUtil.parseBirthday(elderlyPeople
					.getIdCardNo()));
		}
	}

	private void autoIsDeath(ElderlyPeople elderlyPeople) {
		if (elderlyPeople.isDeath()) {
			elderlyPeople.setIsEmphasis(IsEmphasis.IsNotEmphasis);
		} else {
			elderlyPeople.setIsEmphasis(IsEmphasis.Emphasis);
		}
	}

	@Override
	public ElderlyPeople updateElderlyPeopleByIdCardNo(String idCardNo,
			Long orgId, ElderlyPeople domain) {
		try {
			ElderlyPeople older = this
					.getElderlyPeopleByIdCardNoAndOrganizationId(idCardNo,
							orgId);
			domain.setId(older.getId());
			domain.setCreateDate(older.getCreateDate());
			domain.setCreateUser(older.getCreateUser());
			return updateElderlyPeople(domain);
		} catch (Exception e) {
			logger.error(
					"ElderlyPeopleServiceImpl updateElderlyPeopleByIdCardNo", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public void deleteElderlyPeoplesByIdList(List<Long> idList) {
		if (null == idList) {
			throw new BusinessValidationException("老年人idList不能为空");
		}
		for (Long id : idList) {
			this.deleteElderlyPeopleById(id);
		}
	}

	@Override
	public void updateDeathOfElderlyPeoplesByIdList(List<Long> populationIds,
			Boolean death) {
		for (Long id : populationIds) {
			ElderlyPeople population = this.getElderlyPeopleById(id);
			updateLogOutByPopulationTypeAndId(
					LogoutDetail.buildLogoutDetail(death),
					BaseInfoTables.ELDERLYPEOPLE_KEY, population.getId());
			baseInfoService.updateDeathStateById(population.getBaseInfoId(),
					death, population.getIdCardNo(), population
							.getOrganization().getId(), population
							.getOrgInternalCode(),
					NewBaseInfoTables.ELDERLYPEOPLE_KEY);
			// 缓存计数器
			PageInfoCacheThreadLocal.increment(MemCacheConstant
					.getCachePageKey(ElderlyPeople.class.getSimpleName()),
					population, 1);
		}
	}

	@Override
	public Map<String, Map<String, Object>> getPopulationSpecializedInfoByOrgIdAndIdCardNo(
			Long orgId, String idCardNo) {
		ElderlyPeople elderlyPeople = elderlyPeopleDao.getByOrgIdAndIdCardNo(
				orgId, idCardNo);
		if (null == elderlyPeople) {
			return null;
		}
		Map<String, Object> elderlyPeopleMap = new HashMap<String, Object>();
		elderlyPeopleMap.put("id", elderlyPeople.getId());
		elderlyPeopleMap.put("isEmphasis", elderlyPeople.getIsEmphasis());
		elderlyPeopleMap.put("elderPeopleId", elderlyPeople.getElderPeopleId());
		elderlyPeopleMap.put("peopleType", elderlyPeople.getPeopleType());
		elderlyPeopleMap.put("currentStatus", elderlyPeople.getCurrentStatus());
		elderlyPeopleMap.put("liveStatus", elderlyPeople.getLiveStatus());
		elderlyPeopleMap.put("spouseStatus", elderlyPeople.getSpouseStatus());
		elderlyPeopleMap.put("incomeSource", elderlyPeople.getIncomeSource());
		elderlyPeopleMap.put("enterWorkDate", elderlyPeople.getEnterWorkDate());
		elderlyPeopleMap.put("retireUnitAndDuty",
				elderlyPeople.getRetireUnitAndDuty());
		elderlyPeopleMap.put("retireDate", elderlyPeople.getRetireDate());
		elderlyPeopleMap.put("zhiwu", elderlyPeople.getZhiwu());
		elderlyPeopleMap.put("pensionStringValue",
				elderlyPeople.getPensionStringValue());
		elderlyPeopleMap.put("attentionExtent",
				elderlyPeople.getAttentionExtent());
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		map.put(PopulationType.ELDERLY_PEOPLE, elderlyPeopleMap);
		return map;
	}

	@Override
	public Long proccessPopulationSpecializedInfo(
			ActualPopulation actualPopulation, String[] populationType,
			Map<String, Object> population) {
		actualPopulation
				.setAttentionPopulationType(NewBaseInfoTables.ELDERLYPEOPLE_KEY);
		Long orgId = Long
				.valueOf(((String[]) population.get("organization.id"))[0]);
		String idCardNo = ((String[]) population.get("idCardNo"))[0];
		ElderlyPeople elderlyPeople = elderlyPeopleDao.getByOrgIdAndIdCardNo(
				orgId, idCardNo);
		if (!com.tianque.util.Arrays.hasObjectInArray(populationType,
				PopulationType.ELDERLY_PEOPLE)) {
			if (null != elderlyPeople) {
				elderlyPeople.setIsEmphasis(IsEmphasis.IsNotEmphasis);
				updateEmphasiseById(elderlyPeople.getId(),
						IsEmphasis.IsNotEmphasis);
			}
		} else {
			if (null == elderlyPeople) {
				elderlyPeople = new ElderlyPeople();
				copyProperty(actualPopulation, population, elderlyPeople);
				addElderlyPeople(elderlyPeople);
			} else {
				Long id = elderlyPeople.getId();
				copyProperty(actualPopulation, population, elderlyPeople);
				elderlyPeople.setId(id);
				elderlyPeople.setIsEmphasis(IsEmphasis.Emphasis);
				updateElderlyPeopleBusiness(elderlyPeople);
				updateEmphasiseById(elderlyPeople.getId(), IsEmphasis.Emphasis);
			}
		}
		return elderlyPeople == null
				|| elderlyPeople.getIsEmphasis() == IsEmphasis.IsNotEmphasis
						.longValue() ? null : elderlyPeople.getId();
	}

	private void updateEmphasiseById(Long id, Long isEmphasis) {
		LogoutDetail logoutDetail = new LogoutDetail();
		logoutDetail.setLogout(isEmphasis);
		updateLogOutByPopulationTypeAndId(logoutDetail,
				BaseInfoTables.ELDERLYPEOPLE_KEY, id);
	}

	private void copyProperty(ActualPopulation actualPopulation,
			Map<String, Object> population, ElderlyPeople elderlyPeople) {
		try {
			PropertyUtils.copyProperties(elderlyPeople, actualPopulation);
		} catch (Exception e) {
			throw new OperationFailedException("复制属性失败", e);
		}
		elderlyPeople.setElderPeopleId(Arrays.getStringValueFromArray(
				population, "elderPeopleId"));

		elderlyPeople.setPeopleType(Arrays.getPropertyDictFromArray(population,
				"peopleType.id"));

		elderlyPeople.setCurrentStatus(Arrays.getPropertyDictFromArray(
				population, "currentStatus.id"));

		elderlyPeople.setLiveStatus(Arrays.getPropertyDictFromArray(population,
				"liveStatus.id"));

		elderlyPeople.setSpouseStatus(Arrays.getPropertyDictFromArray(
				population, "spouseStatus.id"));

		elderlyPeople.setIncomeSource(Arrays.getPropertyDictFromArray(
				population, "incomeSource.id"));

		elderlyPeople.setEnterWorkDate(Arrays.getDateValueFromArray(population,
				"enterWorkDate"));
		elderlyPeople.setRetireUnitAndDuty(Arrays.getStringValueFromArray(
				population, "retireUnitAndDuty"));
		elderlyPeople.setRetireDate(Arrays.getDateValueFromArray(population,
				"retireDate"));
		elderlyPeople.setZhiwu(Arrays.getStringValueFromArray(population,
				"zhiwu"));
		elderlyPeople.setPension(Arrays.getDoubleValueFromArray(population,
				"pension"));
		elderlyPeople.setAttentionExtent(Arrays.getPropertyDictFromArray(
				population, "attentionExtent.id"));
		elderlyPeople
				.setAttentionPopulationType(BaseInfoTables.ELDERLYPEOPLE_KEY);
	}

	@Override
	public void updatePopulationBaseInfo(ActualPopulation actualPopulation) {
		ElderlyPeople elderlyPeople = elderlyPeopleDao.getByOrgIdAndIdCardNo(
				actualPopulation.getOrganization().getId(),
				actualPopulation.getIdCardNo());
		if (null == elderlyPeople) {
			return;
		}
		Long id = elderlyPeople.getId();
		try {
			PropertyUtils.copyProperties(elderlyPeople, actualPopulation);
		} catch (Exception e) {
			throw new OperationFailedException("复制属性失败", e);
		}
		elderlyPeople.setId(id);
		updateElderlyPeople(elderlyPeople);
	}

	@Override
	public void deletePopulationByPopulationId(Long populationId) {
		if (null != populationId) {
			this.deleteElderlyPeopleById(populationId);
			this.deletePopulationTypeByPopulationIdAndType(populationId,
					PopulationType.ELDERLY_PEOPLE);
		}
	}

	@Override
	public ElderlyPeople updateElderlyPeopleBaseInfo(ElderlyPeople elderlyPeople) {
		elderlyPeople.setIdCardNo(elderlyPeople.getIdCardNo().toUpperCase());
		autoFillOrganizationInternalCode(elderlyPeople);
		autoFillBirthday(elderlyPeople);
		ValidateResult elderlyPeopleValidator = updateValidator
				.validateBaseInfo(elderlyPeople);
		if (elderlyPeopleValidator.hasError()) {
			throw new BusinessValidationException(
					elderlyPeopleValidator.getErrorMessages());
		} else if (hasDuplicateElderlyPeople(elderlyPeople.getOrganization()
				.getId(), elderlyPeople.getIdCardNo(), elderlyPeople.getId())) {
			throw new BusinessValidationException("该网格下已存在相同身份证号码");
		}
		autoFillChinesePinyin(elderlyPeople);
		autoFillGender(elderlyPeople);
		if (elderlyPeople.isDeath()) {
			elderlyPeople.setIsEmphasis(IsEmphasis.IsNotEmphasis);
			elderlyPeople.setLogoutDetail(buildLogoutDetail(elderlyPeople
					.isDeath()));
			// 缓存计数器
			PageInfoCacheThreadLocal.decrease(MemCacheConstant
					.getCachePageKey(ElderlyPeople.class.getSimpleName()),
					elderlyPeople, 1);
		}
		Countrymen temp = populationRelationService.businessOption(
				elderlyPeople, elderlyPeople.getActualPopulationType());
		elderlyPeople.setHouseId(temp.getHouseId());
		rebuildHouseAddress(elderlyPeople);
		elderlyPeopleDao.updateTableUpdateDateById(elderlyPeople.getId());
		return elderlyPeople;
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
	public ElderlyPeople updateElderlyPeopleBusiness(ElderlyPeople elderlyPeople) {
		ValidateResult elderlyPeopleValidator = updateValidator
				.validateSpecializedInfo(elderlyPeople);

		if (elderlyPeopleValidator.hasError()) {
			throw new BusinessValidationException(
					elderlyPeopleValidator.getErrorMessages());
		}
//		elderlyPeople.setShardCode(shardConversion.getShardCode(elderlyPeople
//				.getOrganization().getId()));
		elderlyPeople = elderlyPeopleDao.updateBusinessShard(elderlyPeople);
		PageInfoCacheThreadLocal.update(
				MemCacheConstant.getCachePageKey(ElderlyPeople.class),
				elderlyPeople, UpdateType.BUSINESS);
		return elderlyPeople;
	}

	@Override
	public ElderlyPeople hasDuplicateElderlyPeople(Long orgId, String idCardNo) {
		if (idCardNo == null || "".equals(idCardNo.trim()) || orgId == null) {
			return null;
		}
		idCardNo = idCardNo.toUpperCase();
		String shardCode = shardConversion.getShardCode(orgId);
		return elderlyPeopleDao.getElderlyPeopleByIdCardNoAndOrganizationId(
				idCardNo, orgId, shardCode);
	}

	@Override
	public void deleteElderlyPeopleByIds(Long[] ids) {
		if (null == ids) {
			throw new BusinessValidationException("老年人idList不能为空");
		}
		for (Long id : ids) {
			this.deleteElderlyPeopleById(id);
		}

	}

	@Override
	public ElderlyPeople addElderlyPeopleBaseInfo(ElderlyPeople elderlyPeople) {
		if (!ExcelImportHelper.isImport.get()) {
			ValidateResult elderlyPeopleValidator = updateValidator
					.validateBaseInfo(elderlyPeople);
			if (elderlyPeopleValidator.hasError()) {
				throw new BusinessValidationException(
						elderlyPeopleValidator.getErrorMessages());
			} else if (hasDuplicateElderlyPeople(elderlyPeople
					.getOrganization().getId(), elderlyPeople.getIdCardNo(),
					elderlyPeople.getId())) {
				throw new BusinessValidationException("该网格下已存在相同身份证号码");
			}
		}
		try {
			if (checkDataExitInCache(elderlyPeople,
					MemCacheConstant.CACHE_ADDELDERLYPEOPLEBASEINFO,
					CACHE_ADDELDERLYPEOPLEBASEINFO_VALUE)) {
				return elderlyPeople;
			}
			return add(elderlyPeople);
		} catch (Exception e) {
			logger.error("ElderlyPeopleServiceImpl addElderlyPeopleBaseInfo", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(elderlyPeople,
					MemCacheConstant.CACHE_ADDELDERLYPEOPLEBASEINFO);
		}
	}

	@Override
	public void deleteBusinessPopulationDuplicate(Long id) {
		this.deleteElderlyPeopleById(id);
	}

}

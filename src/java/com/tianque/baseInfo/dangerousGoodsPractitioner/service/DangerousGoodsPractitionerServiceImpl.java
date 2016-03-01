package com.tianque.baseInfo.dangerousGoodsPractitioner.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.base.helper.CardNoHelper;
import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateImpl;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.baseInfo.base.service.PopulationRelationService;
import com.tianque.baseInfo.dangerousGoodsPractitioner.dao.DangerousGoodsPractitionerDao;
import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
import com.tianque.cache.PageInfoCacheThreadLocal;
import com.tianque.cache.UpdateType;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.OperationFailedException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.HousePopulationMaintanceService;
import com.tianque.service.IssueTypeService;
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

@Service("dangerousGoodsPractitionerService")
@Transactional
public class DangerousGoodsPractitionerServiceImpl extends
		BaseInfoPopulationTemplateImpl implements
		DangerousGoodsPractitionerService, PopulationProccessorService {

	private static final String CACHE_ADDDANGEROUSGOODSPRACTITIONER_VALUE = "CACHE_ADDDANGEROUSGOODSPRACTITIONER";
	private static final String CACHE_ADDDANGEROUSGOODSPRACTITIONERFORMOBILE_VALUE = "CACHE_ADDDANGEROUSGOODSPRACTITIONERFORMOBILE";
	private static final String CACHE_ADDDANGEROUSGOODSPRACTITIONERBASEINFO_VALUE = "CACHE_ADDDANGEROUSGOODSPRACTITIONERBASEINFO";

	@Autowired
	private DangerousGoodsPractitionerDao dangerousGoodsPractitionerDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Qualifier("dangerousGoodsPractitionerValidator")
	@Autowired
	private AbstractCountrymenValidator<DangerousGoodsPractitioner> updateValidator;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private ManagePopulationByHouseHelper managePopulationByHouseHelper;
	@Autowired
	private HousePopulationMaintanceService housePopulationMaintanceService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private PopulationRelationService populationRelationService;
	@Autowired
	private BaseInfoService baseInfoService;
	@Autowired
	private PermissionDubboService permissionDubboService;

	// @Autowired
	// private CacheService cacheService;

	@Resource(name = "dangerousGoodsPractitionerDao")
	public void setBaseInfoPopulationBaseDao(
			DangerousGoodsPractitionerDao dangerousGoodsPractitionerDao) {
		super.setBaseInfoPopulationBaseDao(dangerousGoodsPractitionerDao);
	}

	@Override
	public DangerousGoodsPractitioner addDangerousGoodsPractitioner(
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		if (!ExcelImportHelper.isImport.get()) {
			dangerousGoodsValidator(dangerousGoodsPractitioner);
		}
		try {
			if (checkDataExitInCache(dangerousGoodsPractitioner,
					MemCacheConstant.CACHE_ADDDANGEROUSGOODSPRACTITIONER,
					CACHE_ADDDANGEROUSGOODSPRACTITIONER_VALUE)) {
				return dangerousGoodsPractitioner;
			}
			return add(dangerousGoodsPractitioner);
		} catch (Exception e) {
			logger.error("", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(dangerousGoodsPractitioner,
					MemCacheConstant.CACHE_ADDDANGEROUSGOODSPRACTITIONER);
		}
	}

	/**
	 * 
	 * @Title: addDangerousGoodsPractitionerForMobile
	 * @Description: TODO提取新方法 增加危化品从业单位人员
	 * @param @param dangerousGoodsPractitioner
	 * @param @return 设定文件
	 * @return DangerousGoodsPractitioner 返回类型
	 * @author wanggz
	 * @date 2014-6-19 下午02:55:47
	 */
	@Override
	public DangerousGoodsPractitioner addDangerousGoodsPractitionerForMobile(
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		if (!ExcelImportHelper.isImport.get()) {
			dangerousGoodsValidatorForMobile(dangerousGoodsPractitioner);
		}
		try {
			if (checkDataExitInCache(
					dangerousGoodsPractitioner,
					MemCacheConstant.CACHE_ADDDANGEROUSGOODSPRACTITIONERFORMOBILE,
					CACHE_ADDDANGEROUSGOODSPRACTITIONERFORMOBILE_VALUE)) {
				return dangerousGoodsPractitioner;
			}
			return add(dangerousGoodsPractitioner);
		} catch (Exception e) {
			logger.error("", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(
					dangerousGoodsPractitioner,
					MemCacheConstant.CACHE_ADDDANGEROUSGOODSPRACTITIONERFORMOBILE);
		}
	}

	private DangerousGoodsPractitioner add(
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		autoFillFields(dangerousGoodsPractitioner);
		try {
			Countrymen temp = populationRelationService.businessOption(
					dangerousGoodsPractitioner,
					dangerousGoodsPractitioner.getActualPopulationType());
			dangerousGoodsPractitioner.setBaseInfoId(temp.getBaseInfoId());
			dangerousGoodsPractitioner
					.setAddressInfoId(temp.getAddressInfoId());
			dangerousGoodsPractitioner.setSourcesState(null);
			dangerousGoodsPractitioner = dangerousGoodsPractitionerDao
					.add(dangerousGoodsPractitioner);
			populationRelationService.addPopulationRelation(temp.getId(),
					dangerousGoodsPractitioner.getActualPopulationType(),
					dangerousGoodsPractitioner.getId(),
					BaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY);
			dangerousGoodsPractitioner.setHouseId(temp.getHouseId());
			rebuildHouseAddress(dangerousGoodsPractitioner);

			if (IsEmphasis.Emphasis.equals(dangerousGoodsPractitioner
					.getIsEmphasis())) {
				// 缓存计数器
				PageInfoCacheThreadLocal.increment(MemCacheConstant
						.getCachePageKey(DangerousGoodsPractitioner.class
								.getSimpleName()), dangerousGoodsPractitioner,
						1);
			}
			return dangerousGoodsPractitioner;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类dangerousGoodsPractitionerServiceImpl的adddangerousGoodsPractitioner方法出现异常，原因：",
					"新增dangerousGoodsPractitioner信息出现错误", e);
		}
	}

	/**
	 * 如果人口的房屋信息（CurrentAddress）不为空，并且房屋id不存在，新增一个房屋，并且建立关联关系, 如果房屋id不为空直接建立关联关系
	 * 如果房屋信息为空,并且有房屋id不为空，则删除人房关系
	 * 
	 * @param householdStaff
	 */
	private void rebuildHouseAddress(DangerousGoodsPractitioner householdStaff) {

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
						.setHouseOperateSource(NewBaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY);
				houseInfo = actualHouseService.addHouseInfo(houseInfo);

				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.DANGEROUS_GOODS_PRACTITIONER,
						householdStaff, houseInfo.getId());
			} else if (householdStaff.getHouseId() != null) {
				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.DANGEROUS_GOODS_PRACTITIONER,
						householdStaff, householdStaff.getHouseId());
			}
		} else {
			housePopulationMaintanceService.releaseHouse(
					PopulationCatalog.DANGEROUS_GOODS_PRACTITIONER,
					householdStaff.getId(), householdStaff.getHouseId());
		}
	}

	private void dangerousGoodsValidator(
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		ValidateResult baseDataValidator = updateValidator
				.validate(dangerousGoodsPractitioner);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	private void dangerousGoodsValidatorForMobile(
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		ValidateResult baseDataValidator = updateValidator
				.validate(dangerousGoodsPractitioner);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		} else if (existDangerousGoodsPractitioner(dangerousGoodsPractitioner
				.getOrganization().getId(),
				dangerousGoodsPractitioner.getIdCardNo(),
				dangerousGoodsPractitioner.getId())) {
			throw new BusinessValidationException("该网格下已存在相同身份证号码");
		}
	}

	private void autoFillFields(
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		autoFillOrganizationInternalCode(dangerousGoodsPractitioner);
		autoFillChinesePinyin(dangerousGoodsPractitioner);
		autoFillBirthday(dangerousGoodsPractitioner);
		dangerousGoodsPractitioner.setIdCardNo(dangerousGoodsPractitioner
				.getIdCardNo().toUpperCase());
		autoIsDeath(dangerousGoodsPractitioner);
	}

	private void autoIsDeath(
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		if (dangerousGoodsPractitioner.isDeath()) {
			dangerousGoodsPractitioner.setIsEmphasis(IsEmphasis.IsNotEmphasis);
		} else {
			dangerousGoodsPractitioner.setIsEmphasis(IsEmphasis.Emphasis);
		}
	}

	@Override
	public DangerousGoodsPractitioner getSimpleDangerousGoodsPractitionerById(
			Long id) {
		if (validateObjId(id)) {
			throw new BusinessValidationException("获取危险品从业人员时，ID不合法");
		}
		return dangerousGoodsPractitionerDao.get(id);
	}

	@Override
	public DangerousGoodsPractitioner getDangerousGoodsPractitionerById(Long id) {
		return getSimpleDangerousGoodsPractitionerById(id);
	}

	@Override
	public PageInfo<DangerousGoodsPractitioner> findDangerousGoodsPractitionersForPageByOrgId(
			Long organizationId, Integer page, Integer rows, String sidx,
			String sord, Long isEmphasis) {
		if (organizationId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (org == null) {
				return constructEmptyPageInfo();
			} else {
				// return dangerousGoodsPractitionerDao
				// .findDangerousGoodsPractitionersForPageByOrgInternalCode(
				// org.getOrgInternalCode(), page, rows, sidx,
				// sord, isEmphasis);
				/*
				 * DangerousGoodsPractitioner dangerousGoodsPractitioner = new
				 * DangerousGoodsPractitioner();
				 * dangerousGoodsPractitioner.setOrgInternalCode(org
				 * .getOrgInternalCode());
				 * dangerousGoodsPractitioner.setIsEmphasis(isEmphasis);
				 * dangerousGoodsPractitioner.setSortField(sidx);
				 * dangerousGoodsPractitioner.setOrder(sord); return
				 * dangerousGoodsPractitionerDao .findPagerUsingCacheBySearchVo(
				 * organizationId, dangerousGoodsPractitioner, page, rows,
				 * "DangerousGoodsPractitioners", MemCacheConstant
				 * .getCachePageKey(DangerousGoodsPractitioner.class
				 * .getSimpleName()));
				 */
				Map<String, Object> query = new HashMap<String, Object>();
				query.put("orgInternalCode", org.getOrgInternalCode());
				query.put("isEmphasis", isEmphasis);
				PageInfo<DangerousGoodsPractitioner> pageInfos = dangerousGoodsPractitionerDao
						.findPagerUsingCacheBySearchVo(
								organizationId,
								query,
								page,
								rows,
								"DangerousGoodsPractitionerDefaultList",
								MemCacheConstant
										.getCachePageKey(DangerousGoodsPractitioner.class));
				fitCountrymen(pageInfos);
				fitServiceMemberHasObject(
						BaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY,
						pageInfos);
				//隐藏身份证中间4位
				pageInfos=hiddenIdCard(pageInfos);
				return pageInfos;
			}
		}
	}
	
	//隐藏身份证中间4位
		private PageInfo<DangerousGoodsPractitioner> hiddenIdCard(PageInfo<DangerousGoodsPractitioner> pageInfo){
						//判断权限，有权限不隐藏
						if(permissionDubboService.
								isUserHasPermission(ThreadVariable.getUser().getId(), "isDangerousGoodsPractitionerManagementNotHidCard")){
							return pageInfo;
						}
						List<DangerousGoodsPractitioner> list = pageInfo.getResult();
						int index=0;
						for (DangerousGoodsPractitioner verification:list) {
							verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
							list.set(index, verification);
							index++;
						}
						pageInfo.setResult(list);
						return pageInfo;
				}


	@Override
	public DangerousGoodsPractitioner updateDangerousGoodsPractitioner(
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		if (!ExcelImportHelper.isImport.get()) {
			dangerousGoodsValidator(dangerousGoodsPractitioner);
		}
		try {
			autoFillFields(dangerousGoodsPractitioner);
			// if (dangerousGoodsPractitioner.isDeath()) {
			// dangerousGoodsPractitioner
			// .setIsEmphasis(IsEmphasis.IsNotEmphasis);
			// deletePopulationTypeByPopulationIdAndType(
			// dangerousGoodsPractitioner.getId(),
			// PopulationType.DANGEROUS_GOODS_PRACTITIONER);
			// }
			updateDangerousGoodsPractitionerBaseInfo(dangerousGoodsPractitioner);
			populationRelationService.businessOption(
					dangerousGoodsPractitioner,
					dangerousGoodsPractitioner.getActualPopulationType());
			// dangerousGoodsPractitioner = dangerousGoodsPractitionerDao
			// .update(dangerousGoodsPractitioner);
			return dangerousGoodsPractitioner;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类DangerousGoodsPractitionerServiceImpl的updateDangerousGoodsPractitioner方法出现异常，原因：",
					"修改信息出现错误", e);
		}

	}

	/**
	 * 
	 * @Title: DangerousGoodsPractitionerForMobile
	 * @Description: TODO修改危化品从业人员信息 手机端
	 * @param @param population
	 * @param @return 设定文件
	 * @return DangerousGoodsPractitioner 返回类型
	 * @author wanggz
	 * @date 2014-9-15 下午06:09:11
	 */
	public DangerousGoodsPractitioner updateDangerousGoodsPractitionerForMobile(
			DangerousGoodsPractitioner population) {
		if (!ExcelImportHelper.isImport.get()) {
			dangerousGoodsValidator(population);
		}
		try {
			autoFillFields(population);
			updateDangerousGoodsPractitionerBaseInfo(population);
			populationRelationService.businessOption(population,
					population.getActualPopulationType());
			population = this
					.updateDangerousGoodsPractitionerBusiness(population);
			return population;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类DangerousGoodsPractitionerServiceImpl的updateDangerousGoodsPractitioner方法出现异常，原因：",
					"修改信息出现错误", e);
		}
	}

	public DangerousGoodsPractitioner updateDangerousGoodsPractitionerBaseInfo(
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		if (!ExcelImportHelper.isImport.get()) {
			ValidateResult baseDataValidator = updateValidator
					.validateBaseInfo(dangerousGoodsPractitioner);
			if (baseDataValidator.hasError()) {
				throw new BusinessValidationException(
						baseDataValidator.getErrorMessages());
			}
		}
		try {
			autoFillFields(dangerousGoodsPractitioner);
			if (dangerousGoodsPractitioner.isDeath()) {
				dangerousGoodsPractitioner
						.setLogoutDetail(buildLogoutDetail(dangerousGoodsPractitioner
								.isDeath()));
				// 缓存计数器
				PageInfoCacheThreadLocal.decrease(MemCacheConstant
						.getCachePageKey(DangerousGoodsPractitioner.class
								.getSimpleName()), dangerousGoodsPractitioner,
						1);
			}
			Countrymen temp = populationRelationService.businessOption(
					dangerousGoodsPractitioner,
					dangerousGoodsPractitioner.getActualPopulationType());
			dangerousGoodsPractitioner.setHouseId(temp.getHouseId());
			rebuildHouseAddress(dangerousGoodsPractitioner);
			dangerousGoodsPractitionerDao
					.updateTableUpdateDateById(dangerousGoodsPractitioner
							.getId());
			return dangerousGoodsPractitioner;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类DangerousGoodsPractitionerServiceImpl的updateDangerousGoodsPractitioner方法出现异常，原因：",
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

	public DangerousGoodsPractitioner updateDangerousGoodsPractitionerBusiness(
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		if (!ExcelImportHelper.isImport.get()) {
			ValidateResult baseDataValidator = updateValidator
					.validateSpecializedInfo(dangerousGoodsPractitioner);
			if (baseDataValidator.hasError()) {
				throw new BusinessValidationException(
						baseDataValidator.getErrorMessages());
			}
		}
		try {

			dangerousGoodsPractitioner = dangerousGoodsPractitionerDao
					.updateBusiness(dangerousGoodsPractitioner);
			PageInfoCacheThreadLocal.update(MemCacheConstant
					.getCachePageKey(DangerousGoodsPractitioner.class),
					dangerousGoodsPractitioner, UpdateType.BUSINESS);
			return dangerousGoodsPractitioner;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类DangerousGoodsPractitionerServiceImpl的updateDangerousGoodsPractitioner方法出现异常，原因：",
					"修改信息出现错误", e);
		}

	}

	@Override
	public boolean deleteDangerousGoodsPractitioner(Long id) {
		if (validateObjId(id)) {
			throw new BusinessValidationException("删除危险品从业人员时，ID不合法");
		}
		dangerousGoodsPractitionerDao.delete(id);
		return true;
	}

	private void autoFillChinesePinyin(
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(dangerousGoodsPractitioner.getName());
		dangerousGoodsPractitioner.setSimplePinyin(pinyin.get("simplePinyin"));
		dangerousGoodsPractitioner.setFullPinyin(pinyin.get("fullPinyin"));
	}

	private void autoFillOrganizationInternalCode(
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		Organization org = organizationDubboService
				.getSimpleOrgById(dangerousGoodsPractitioner.getOrganization()
						.getId());
		if (org == null)
			throw new BusinessValidationException("数据传入错误");
		dangerousGoodsPractitioner.setOrgInternalCode(org.getOrgInternalCode());
	}

	private PageInfo<DangerousGoodsPractitioner> constructEmptyPageInfo() {
		PageInfo<DangerousGoodsPractitioner> emptyPageInfo = new PageInfo<DangerousGoodsPractitioner>();
		emptyPageInfo.setResult(new ArrayList<DangerousGoodsPractitioner>());
		return emptyPageInfo;
	}

	private void autoFillBirthday(
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		if (StringUtil.isStringAvaliable(dangerousGoodsPractitioner
				.getIdCardNo())) {
			dangerousGoodsPractitioner.setBirthday(IdCardUtil
					.parseBirthday(dangerousGoodsPractitioner.getIdCardNo()));
		}
	}

	@Override
	public DangerousGoodsPractitioner updateDangerousGoodsPractitionerByName(
			String name, Long orgId, DangerousGoodsPractitioner domain) {
		try {
			DangerousGoodsPractitioner older = this
					.getDangerousGoodsPractitionerByIdCardNo(name, orgId);
			domain.setId(older.getId());
			domain.setCreateDate(older.getCreateDate());
			domain.setCreateUser(older.getCreateUser());
			domain = updateDangerousGoodsPractitioner(domain);
		} catch (Exception e) {
			logger.error("", e);
			if (ExcelImportHelper.isImport.get()) {
				return null;
			}
		}
		return domain;
	}

	@Override
	public DangerousGoodsPractitioner getDangerousGoodsPractitionerByIdCardNo(
			String idCardNo, Long orgId) {
		if (idCardNo == null || "".equals(idCardNo.trim()) || orgId == null) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		list.add(idCardNo);
		if (idCardNo.length() == 18) {
			list.add(IdCardUtil.idCardNo18to15(idCardNo));
		} else if (idCardNo.length() == 15) {
			list.add(IdCardUtil.idCardNo15to18(idCardNo, "19"));
			list.add(IdCardUtil.idCardNo15to18(idCardNo, "20"));
		}
		return dangerousGoodsPractitionerDao.getByIdCard(list, orgId);
	}

	public void updateEmphasiseById(Long id, Long isEmphasis) {
		LogoutDetail logoutDetail = new LogoutDetail();
		logoutDetail.setLogout(isEmphasis);
		updateLogOutByPopulationTypeAndId(logoutDetail,
				BaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY, id);
	}

	@Override
	public List<DangerousGoodsPractitioner> updateDeathByIds(
			List<Long> populationIds, Boolean death) {
		List<DangerousGoodsPractitioner> list = new ArrayList<DangerousGoodsPractitioner>();
		for (Long id : populationIds) {
			DangerousGoodsPractitioner population = this
					.getSimpleDangerousGoodsPractitionerById(id);
			updateLogOutByPopulationTypeAndId(
					LogoutDetail.buildLogoutDetail(death),
					BaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY,
					population.getId());
			baseInfoService.updateDeathStateById(population.getBaseInfoId(),
					death, population.getIdCardNo(), population
							.getOrganization().getId(), population
							.getOrgInternalCode(),
					NewBaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY);
			list.add(population);
			// 缓存计数器
			PageInfoCacheThreadLocal.increment(MemCacheConstant
					.getCachePageKey(DangerousGoodsPractitioner.class
							.getSimpleName()), population, 1);
		}
		return list;
	}

	@Override
	public List<Long> deleteDangerousGoodsPractitionerByIds(List<Long> personIds) {
		if (personIds == null) {
			throw new BusinessValidationException("id没有获得");
		}
		for (Long id : personIds) {
			deleteDangerousGoodsPractitionerById(id);
		}
		return personIds;
	}

	private void deleteDangerousGoodsPractitionerById(Long id) {
		DangerousGoodsPractitioner domain = dangerousGoodsPractitionerDao
				.get(id);
		if (null != domain) {
			log(WARN, DANGEROUSEGOODS_PRACTITIONER, ThreadVariable.getSession()
					.getUserName() + "删除危险品从业人员" + domain.getName(),
					OperatorType.DELETE, ObjectToJSON.convertJSON(domain));
			domain.setPopulationTypeBean(getPopulationRelationService()
					.getBusinessPopulationTypeBean(id,
							PopulationType.DANGEROUS_GOODS_PRACTITIONER));
			getRecoverDatasService().deleteActualPopulation(domain);
			populationRelationService.businessDeletePopulationRelation(id,
					PopulationType.DANGEROUS_GOODS_PRACTITIONER);
			dangerousGoodsPractitionerDao.delete(id);
			try {
				PluginServiceHelpUtil.doService("routerService",
						"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class },
						PopulationType.DANGEROUS_GOODS_PRACTITIONER, id);
				/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
				PluginServiceHelpUtil.doService("routerService",
						"setOrgIdAndCardNoOrName", new Class[] { Long.class,
								String.class, String.class, Long.class },
						domain.getOrganization().getId(), domain.getIdCardNo(),
						PopulationType.DANGEROUS_GOODS_PRACTITIONER, id);
				issueTypeService.setOrgIdAndCardNoOrNameForPerson(domain
						.getOrganization().getId(), domain.getIdCardNo(),
						PopulationType.DANGEROUS_GOODS_PRACTITIONER, id);
				if (IsEmphasis.Emphasis.equals(domain.getIsEmphasis())) {
					// 缓存计数器
					PageInfoCacheThreadLocal.decrease(MemCacheConstant
							.getCachePageKey(DangerousGoodsPractitioner.class
									.getSimpleName()), domain, 1);
				}
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}

	@Override
	public boolean existDangerousGoodsPractitioner(Long orgId, String idCardNo,
			Long hopeId) {
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
		Long dr = dangerousGoodsPractitionerDao.getIdByBaseinfoIdAndOrgId(
				countrymen.getId(), orgId);
		return hopeId == null ? dr != null
				: (dr != null && hopeId.longValue() != dr.longValue());
	}

	@Override
	public DangerousGoodsPractitioner hasDuplicateDangerousGoodsPractitioner(
			Long orgId, String idCardNo) {
		if (idCardNo == null || "".equals(idCardNo.trim()) || orgId == null) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		idCardNo = idCardNo.toUpperCase();
		list.add(idCardNo);
		if (idCardNo.length() == 18) {
			list.add(IdCardUtil.idCardNo18to15(idCardNo));
		} else if (idCardNo.length() == 15) {
			list.add(IdCardUtil.idCardNo15to18(idCardNo, "19"));
			list.add(IdCardUtil.idCardNo15to18(idCardNo, "20"));
		}
		return dangerousGoodsPractitionerDao.getByIdCard(list, orgId);
	}

	private boolean validateObjId(Long id) {
		return null == id || id < 1L;
	}

	@Override
	public Map<String, Map<String, Object>> getPopulationSpecializedInfoByOrgIdAndIdCardNo(
			Long orgId, String idCardNo) {
		DangerousGoodsPractitioner dangerousGoodsPractitioner = dangerousGoodsPractitionerDao
				.getByOrgIdAndIdCardNo(orgId, idCardNo);
		if (null == dangerousGoodsPractitioner) {
			return null;
		}
		Map<String, Object> dangerousGoodsPractitionerMap = new HashMap<String, Object>();
		dangerousGoodsPractitionerMap.put("id",
				dangerousGoodsPractitioner.getId());
		dangerousGoodsPractitionerMap.put("isEmphasis",
				dangerousGoodsPractitioner.getIsEmphasis());
		dangerousGoodsPractitionerMap.put("dangerousWorkingType",
				dangerousGoodsPractitioner.getDangerousWorkingType());
		dangerousGoodsPractitionerMap.put("workingCertificate",
				dangerousGoodsPractitioner.getWorkingCertificate());
		dangerousGoodsPractitionerMap.put("workingCertificateNo",
				dangerousGoodsPractitioner.getWorkingCertificateNo());
		dangerousGoodsPractitionerMap.put("periodOfValidityStart",
				dangerousGoodsPractitioner.getPeriodOfValidityStart());
		dangerousGoodsPractitionerMap.put("periodOfValidityEnd",
				dangerousGoodsPractitioner.getPeriodOfValidityEnd());
		dangerousGoodsPractitionerMap.put("workUnit",
				dangerousGoodsPractitioner.getWorkUnit());
		dangerousGoodsPractitionerMap.put("legalPerson",
				dangerousGoodsPractitioner.getLegalPerson());
		dangerousGoodsPractitionerMap.put("legalPersonMobileNumber",
				dangerousGoodsPractitioner.getLegalPersonMobileNumber());
		dangerousGoodsPractitionerMap.put("legalPersonTelephone",
				dangerousGoodsPractitioner.getLegalPersonTelephone());
		dangerousGoodsPractitionerMap.put("attentionExtent",
				dangerousGoodsPractitioner.getAttentionExtent());
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		map.put(PopulationType.DANGEROUS_GOODS_PRACTITIONER,
				dangerousGoodsPractitionerMap);
		return map;
	}

	@Override
	public Long proccessPopulationSpecializedInfo(
			ActualPopulation actualPopulation, String[] populationType,
			Map<String, Object> population) {
		actualPopulation
				.setAttentionPopulationType(NewBaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY);
		Long orgId = Long
				.valueOf(((String[]) population.get("organization.id"))[0]);
		String idCardNo = ((String[]) population.get("idCardNo"))[0];
		DangerousGoodsPractitioner dangerousGoodsPractitioner = dangerousGoodsPractitionerDao
				.getByOrgIdAndIdCardNo(orgId, idCardNo);
		if (!com.tianque.util.Arrays.hasObjectInArray(populationType,
				PopulationType.DANGEROUS_GOODS_PRACTITIONER)) {
			if (null != dangerousGoodsPractitioner) {
				dangerousGoodsPractitioner
						.setIsEmphasis(IsEmphasis.IsNotEmphasis);
				updateEmphasiseById(dangerousGoodsPractitioner.getId(),
						IsEmphasis.IsNotEmphasis);
			}
		} else {
			if (null == dangerousGoodsPractitioner) {
				dangerousGoodsPractitioner = new DangerousGoodsPractitioner();
				copyProperty(actualPopulation, population,
						dangerousGoodsPractitioner);
				addDangerousGoodsPractitioner(dangerousGoodsPractitioner);
			} else {
				Long id = dangerousGoodsPractitioner.getId();
				copyProperty(actualPopulation, population,
						dangerousGoodsPractitioner);
				dangerousGoodsPractitioner.setId(id);
				dangerousGoodsPractitioner.setIsEmphasis(IsEmphasis.Emphasis);
				updateDangerousGoodsPractitionerBusiness(dangerousGoodsPractitioner);
				updateEmphasiseById(dangerousGoodsPractitioner.getId(),
						IsEmphasis.Emphasis);
			}
		}
		return dangerousGoodsPractitioner == null
				|| dangerousGoodsPractitioner.getIsEmphasis() == IsEmphasis.IsNotEmphasis
						.longValue() ? null : dangerousGoodsPractitioner
				.getId();
	}

	private void copyProperty(ActualPopulation actualPopulation,
			Map<String, Object> population,
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		try {
			PropertyUtils.copyProperties(dangerousGoodsPractitioner,
					actualPopulation);
		} catch (Exception e) {
			throw new OperationFailedException("复制属性失败", e);
		}

		dangerousGoodsPractitioner
				.setDangerousWorkingType(Arrays.getPropertyDictFromArray(
						population, "dangerousWorkingType.id"));

		dangerousGoodsPractitioner.setWorkingCertificate(Arrays
				.getStringValueFromArray(population, "workingCertificate"));
		dangerousGoodsPractitioner.setWorkingCertificateNo(Arrays
				.getStringValueFromArray(population, "workingCertificateNo"));
		dangerousGoodsPractitioner.setPeriodOfValidityStart(Arrays
				.getDateValueFromArray(population, "periodOfValidityStart"));
		dangerousGoodsPractitioner.setPeriodOfValidityEnd(Arrays
				.getDateValueFromArray(population, "periodOfValidityEnd"));
		dangerousGoodsPractitioner.setLegalPerson(Arrays
				.getStringValueFromArray(population, "legalPerson"));
		dangerousGoodsPractitioner
				.setLegalPersonMobileNumber(Arrays.getStringValueFromArray(
						population, "legalPersonMobileNumber"));
		dangerousGoodsPractitioner.setLegalPersonTelephone(Arrays
				.getStringValueFromArray(population, "legalPersonTelephone"));

		dangerousGoodsPractitioner
				.setAttentionPopulationType(BaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY);
		dangerousGoodsPractitioner.setAttentionExtent(Arrays
				.getPropertyDictFromArray(population, "attentionExtent.id"));
	}

	@Override
	public void updatePopulationBaseInfo(ActualPopulation actualPopulation) {
		DangerousGoodsPractitioner dangerousGoodsPractitioner = dangerousGoodsPractitionerDao
				.getByOrgIdAndIdCardNo(actualPopulation.getOrganization()
						.getId(), actualPopulation.getIdCardNo());
		if (null == dangerousGoodsPractitioner) {
			return;
		}
		Long id = dangerousGoodsPractitioner.getId();
		if (!StringUtil.isStringAvaliable(actualPopulation.getWorkUnit())) {
			actualPopulation.setWorkUnit(dangerousGoodsPractitioner
					.getWorkUnit());
		}
		try {
			PropertyUtils.copyProperties(dangerousGoodsPractitioner,
					actualPopulation);
		} catch (Exception e) {
			throw new OperationFailedException("复制属性失败", e);
		}
		dangerousGoodsPractitioner.setId(id);
		updateDangerousGoodsPractitioner(dangerousGoodsPractitioner);
	}

	@Override
	public void deletePopulationByPopulationId(Long populationId) {
		if (null != populationId) {
			this.deleteDangerousGoodsPractitioner(populationId);
			this.deletePopulationTypeByPopulationIdAndType(populationId,
					PopulationType.DANGEROUS_GOODS_PRACTITIONER);
		}
	}

	@Override
	public DangerousGoodsPractitioner findDangerousGoodsPractitionerByIdCardNoAndOrgId(
			String idCardNo, Long orgId) {
		return getDangerousGoodsPractitionerByName(idCardNo, orgId);
	}

	private DangerousGoodsPractitioner getDangerousGoodsPractitionerByName(
			String idCardNo, Long orgId) {
		List<String> list = new ArrayList<String>();
		list.add(idCardNo);
		if (idCardNo.length() == 18) {
			list.add(IdCardUtil.idCardNo18to15(idCardNo));
		} else if (idCardNo.length() == 15) {
			list.add(IdCardUtil.idCardNo15to18(idCardNo, "19"));
			list.add(IdCardUtil.idCardNo15to18(idCardNo, "20"));
		}
		return dangerousGoodsPractitionerDao.getByIdCard(list, orgId);
	}

	@Override
	public List<Long> deleteDangerousGoodsPractitionerByIds(Long[] personIds) {
		List<Long> resultList = new ArrayList<Long>();
		if (personIds == null) {
			throw new BusinessValidationException("id没有获得");
		}
		for (Long id : personIds) {
			deleteDangerousGoodsPractitionerById(id);
			resultList.add(id);
		}
		return resultList;
	}

	@Override
	public DangerousGoodsPractitioner addDangerousGoodsPractitionerBaseInfo(
			DangerousGoodsPractitioner dangerousGoodsPractitioner) {
		ValidateResult baseDataValidator = updateValidator
				.validateBaseInfo(dangerousGoodsPractitioner);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			if (checkDataExitInCache(
					dangerousGoodsPractitioner,
					MemCacheConstant.CACHE_ADDDANGEROUSGOODSPRACTITIONERBASEINFO,
					CACHE_ADDDANGEROUSGOODSPRACTITIONERBASEINFO_VALUE)) {
				return dangerousGoodsPractitioner;
			}
			return add(dangerousGoodsPractitioner);
		} catch (Exception e) {
			logger.error("", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(
					dangerousGoodsPractitioner,
					MemCacheConstant.CACHE_ADDDANGEROUSGOODSPRACTITIONERBASEINFO);
		}
	}

	public AttentionPopulation getAttentionPopulationById(Long id) {
		return dangerousGoodsPractitionerDao.get(id);
	}

	// fateson add move function start
	@Override
	public void moveTempByIds(String[] peoperIds, Long targetOrgId) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(targetOrgId);

		for (String id : peoperIds) {
			Long peoperId = Long.parseLong(id);
			if (!StringUtils.isEmpty(id)) {
				// get peoper
				DangerousGoodsPractitioner people = getSimpleDangerousGoodsPractitionerById(peoperId);
				if (people == null) {
					continue;
				}
				Long currentOrgid = people.getOrganization().getId();
				people.setOrganization(organization);
				// String orgCode = people.getOrgInternalCode();
				String orgCode = people.getOrganization().getOrgInternalCode();
				String idCardNo = people.getIdCardNo();
				// 验证目标组织机构是否存在这个人
				boolean result = existDangerousGoodsPractitioner(targetOrgId,
						idCardNo, peoperId);
				if (result) {
					// 把存在的旧数据成为修改现在现在要移动的新数据
					if (currentOrgid.longValue() != targetOrgId.longValue()) {
						updateMovePersonByIdCardNo(idCardNo, targetOrgId,
								people);
						// 删掉要移动的数据 删除掉不在同一个网格
						deleteDangerousGoodsPractitioner(peoperId);
					}

				} else {
					// 转移网格数据
					dangerousGoodsPractitionerDao.updateData(id, targetOrgId,
							orgCode, people);
				}
				people.setCreateDate(null);
			}

		}

	}

	public void updateMovePersonByIdCardNo(String idcard, Long targetorgId,
			DangerousGoodsPractitioner people) {
		List<String> cardnos = CardNoHelper.createIdCardNo(idcard);
		// 获取
		DangerousGoodsPractitioner older = dangerousGoodsPractitionerDao
				.getByIdCard(cardnos, targetorgId);
		people.setId(older.getId());
		people.setCreateDate(older.getCreateDate());
		people.setCreateUser(older.getCreateUser());
		dangerousGoodsPractitionerDao.update(people);
	}

	@Override
	public void deleteBusinessPopulationDuplicate(Long id) {
		this.deleteDangerousGoodsPractitionerById(id);

	}

}

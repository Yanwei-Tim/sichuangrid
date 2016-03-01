package com.tianque.baseInfo.fPersonnel.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.tianque.baseInfo.fPersonnel.dao.FPersonnelDao;
import com.tianque.baseInfo.fPersonnel.domain.FPersonnel;
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

@Service("fPersonnelService")
@Transactional
public class FPersonnelServiceImpl extends BaseInfoPopulationTemplateImpl
		implements FPersonnelService, PopulationProccessorService {

	private static final String CACHE_ADDFPERSONNEL_VALUE = "CACHE_ADDFPERSONNEL";
	private static final String CACHE_ADDFPERSONNELBASEINFO_VALUE = "CACHE_ADDFPERSONNELBASEINFO";

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	FPersonnelDao fPersonnelDao;
	@Qualifier("fPersonnelValidator")
	@Autowired
	private AbstractCountrymenValidator<FPersonnel> updateValidator;
	@Autowired
	private IssueTypeService issueTypeService;
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

	// @Autowired
	// private CacheService cacheService;

	@Resource(name = "fPersonnelDao")
	public void setBaseInfoPopulationBaseDao(FPersonnelDao fPersonnelDao) {
		super.setBaseInfoPopulationBaseDao(fPersonnelDao);
	}

	@Override
	public Long proccessPopulationSpecializedInfo(
			ActualPopulation actualPopulation, String[] populationType,
			Map<String, Object> population) {
		actualPopulation
				.setAttentionPopulationType(NewBaseInfoTables.FPERSONNEL_KEY);
		Long orgId = Long
				.valueOf(((String[]) population.get("organization.id"))[0]);
		String idCardNo = ((String[]) population.get("idCardNo"))[0];
		FPersonnel FPersonnel = fPersonnelDao.getByOrgIdAndIdCardNo(orgId,
				idCardNo);
		if (!com.tianque.util.Arrays.hasObjectInArray(populationType,
				PopulationType.F_PERSONNEL)) {
			if (null != FPersonnel) {
				FPersonnel.setIsEmphasis(IsEmphasis.IsNotEmphasis);
				updateEmphasiseById(FPersonnel.getId(),
						IsEmphasis.IsNotEmphasis);
			}
		} else {
			if (null == FPersonnel) {
				FPersonnel = new FPersonnel();
				copyProperty(actualPopulation, population, FPersonnel);
				addFPersonnel(FPersonnel);
			} else {
				Long id = FPersonnel.getId();
				copyProperty(actualPopulation, population, FPersonnel);
				FPersonnel.setId(id);
				FPersonnel.setIsEmphasis(IsEmphasis.Emphasis);
				updateFPersonnelBusiness(FPersonnel);
				updateEmphasiseById(FPersonnel.getId(), IsEmphasis.Emphasis);
			}
		}
		return FPersonnel == null
				|| FPersonnel.getIsEmphasis() == IsEmphasis.IsNotEmphasis
						.longValue() ? null : FPersonnel.getId();
	}

	private void updateEmphasiseById(Long id, Long isEmphasis) {
		LogoutDetail logoutDetail = new LogoutDetail();
		logoutDetail.setLogout(isEmphasis);
		updateLogOutByPopulationTypeAndId(logoutDetail,
				BaseInfoTables.FPERSONNEL_KEY, id);
	}

	@Override
	public FPersonnel updateFPersonnel(FPersonnel FPersonnel) {
		if (!ExcelImportHelper.isImport.get()) {
			FPersonnelValidator(FPersonnel);
		}
		try {
			autoFillFields(FPersonnel);
			if (FPersonnel.isDeath()) {
				FPersonnel.setIsEmphasis(IsEmphasis.IsNotEmphasis);
				deletePopulationTypeByPopulationIdAndType(FPersonnel.getId(),
						PopulationType.F_PERSONNEL);
			}
			FPersonnel = fPersonnelDao.update(FPersonnel);
			return FPersonnel;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类FPersonnelServiceImpl的updateFPersonnel方法出现异常，原因：",
					"修改信息出现错误", e);
		}
	}

	private void FPersonnelValidator(FPersonnel FPersonnel) {
		ValidateResult baseDataValidator = updateValidator.validate(FPersonnel);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public FPersonnel addFPersonnel(FPersonnel fPersonnel) {
		if (!ExcelImportHelper.isImport.get()) {
			FPersonnelValidator(fPersonnel);
		}
		try {
			if (checkDataExitInCache(fPersonnel,
					MemCacheConstant.CACHE_ADDFPERSONNEL,
					CACHE_ADDFPERSONNEL_VALUE)) {
				return fPersonnel;
			}
			return add(fPersonnel);
		} catch (Exception e) {
			logger.error("FPersonnelServiceImpl addFPersonnel", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(fPersonnel, MemCacheConstant.CACHE_ADDFPERSONNEL);
		}
	}

	private void copyProperty(ActualPopulation actualPopulation,
			Map<String, Object> population, FPersonnel fPersonnel) {

		copyProperties(fPersonnel, actualPopulation);
		fPersonnel.setAttentionExtent(Arrays.getPropertyDictFromArray(
				population, "attentionExtent.id"));
		fPersonnel.setFbusinessRemark(Arrays.getStringValueFromArray(
				population, "fbusinessRemark"));
		fPersonnel.setAttentionPopulationType(BaseInfoTables.FPERSONNEL_KEY);
	}

	@Override
	public Map<String, Map<String, Object>> getPopulationSpecializedInfoByOrgIdAndIdCardNo(
			Long orgId, String idCardNo) {
		FPersonnel fPersonnel = fPersonnelDao.getByOrgIdAndIdCardNo(orgId,
				idCardNo);
		if (null == fPersonnel) {
			return null;
		}
		Map<String, Object> dangerousGoodsPractitionerMap = new HashMap<String, Object>();
		dangerousGoodsPractitionerMap.put("id", fPersonnel.getId());
		dangerousGoodsPractitionerMap.put("isEmphasis",
				fPersonnel.getIsEmphasis());
		dangerousGoodsPractitionerMap.put("workUnit", fPersonnel.getWorkUnit());
		dangerousGoodsPractitionerMap.put("attentionExtent",
				fPersonnel.getAttentionExtent());
		dangerousGoodsPractitionerMap.put("fbusinessRemark",
				fPersonnel.getFbusinessRemark());
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		map.put(PopulationType.F_PERSONNEL, dangerousGoodsPractitionerMap);
		return map;
	}

	@Override
	public void updatePopulationBaseInfo(ActualPopulation actualPopulation) {
		if (actualPopulation == null
				|| actualPopulation.getOrganization() == null) {
			return;
		}
		FPersonnel FPersonnel = fPersonnelDao.getByOrgIdAndIdCardNo(
				actualPopulation.getOrganization().getId(),
				actualPopulation.getIdCardNo());
		if (null == FPersonnel) {
			return;
		}
		Long id = FPersonnel.getId();
		if (!StringUtil.isStringAvaliable(actualPopulation.getWorkUnit())) {
			actualPopulation.setWorkUnit(FPersonnel.getWorkUnit());
		}
		copyProperties(FPersonnel, actualPopulation);
		FPersonnel.setId(id);
		updateFPersonnel(FPersonnel);
	}

	@Override
	public void deletePopulationByPopulationId(Long populationId) {
		if (null != populationId) {
			this.deleteFPersonnelById(populationId);
			this.deletePopulationTypeByPopulationIdAndType(populationId,
					PopulationType.F_PERSONNEL);
		}
	}

	@Override
	public PageInfo<FPersonnel> findFPersonnelsForPageByOrgId(
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
				/*
				 * Map<String, Object> map = new HashMap<String, Object>();
				 * map.put("orgInternalCode", org.getOrgInternalCode());
				 * map.put("sortField", sidx); map.put("order", sord);
				 * map.put("isEmphasis", isEmphasis);
				 * map.put("attentionPopulationType",
				 * BaseInfoTables.FPERSONNEL_KEY); return
				 * fPersonnelDao.findPagerUsingCacheBySearchVo( organizationId,
				 * map, page, rows, "FPersonnels",
				 * MemCacheConstant.getCachePageKey(FPersonnel.class
				 * .getSimpleName()));
				 */

				Map<String, Object> query = new HashMap<String, Object>();
				query.put("orgInternalCode", org.getOrgInternalCode());
				query.put("isEmphasis", isEmphasis);
				query.put("attentionPopulationType",
						BaseInfoTables.FPERSONNEL_KEY);
				PageInfo<FPersonnel> pageInfos = fPersonnelDao
						.findPagerUsingCacheBySearchVo(organizationId, query,
								page, rows, "FPersonnelDefaultList",
								MemCacheConstant
										.getCachePageKey(FPersonnel.class));
				fitCountrymen(pageInfos);
				fitServiceMemberHasObject(BaseInfoTables.FPERSONNEL_KEY,
						pageInfos);
				//隐藏身份证中间4位
				pageInfos=hiddenIdCard(pageInfos);
				return pageInfos;

				// return fPersonnelDao.findFPersonnelsForPageByOrgInternalCode(
				// org.getOrgInternalCode(), page, rows, sidx, sord,
				// isEmphasis);
			}
		}
	}
	
	//隐藏身份证中间4位
		private PageInfo<FPersonnel> hiddenIdCard(PageInfo<FPersonnel> pageInfo){
						//判断权限，有权限不隐藏
						if(permissionDubboService.
								isUserHasPermission(ThreadVariable.getUser().getId(), "isFPersonnelManagementNotHidCard")){
							return pageInfo;
						}
						List<FPersonnel> list = pageInfo.getResult();
						int index=0;
						for (FPersonnel verification:list) {
							verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
							list.set(index, verification);
							index++;
						}
						pageInfo.setResult(list);
						return pageInfo;
	}

	private PageInfo<FPersonnel> constructEmptyPageInfo() {
		PageInfo<FPersonnel> emptyPageInfo = new PageInfo<FPersonnel>();
		emptyPageInfo.setResult(new ArrayList<FPersonnel>());
		return emptyPageInfo;
	}

	@Override
	public Boolean existFPersonnel(Long orgId, String idCardNo, Long hopeId) {
		if (idCardNo == null || "".equals(idCardNo.trim()) || orgId == null) {
			return false;
		}
		idCardNo = idCardNo.toUpperCase();
		Countrymen countrymen = baseInfoService.getBaseInfoByIdCardNo(idCardNo);
		if (countrymen == null) {
			return false;
		}
		Long dr = fPersonnelDao.getIdByBaseinfoIdAndOrgId(countrymen.getId(),
				orgId);
		return hopeId == null ? dr != null
				: (dr != null && hopeId.longValue() != dr.longValue());
	}

	@Override
	public FPersonnel hasDuplicateFPersonnel(Long orgId, String idCardNo) {
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
			return fPersonnelDao.getByIdCard(list, orgId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类FPersonnelServiceImpl的hasDuplicateFPersonnel方法出现异常，原因：",
					"判断F人员身份证号码是否存在出现错误", e);
		}
	}

	@Override
	public FPersonnel updateFPersonnelBaseInfo(FPersonnel population) {
		if (!ExcelImportHelper.isImport.get()) {
			ValidateResult baseDataValidator = updateValidator
					.validateBaseInfo(population);
			if (baseDataValidator.hasError()) {
				throw new BusinessValidationException(
						baseDataValidator.getErrorMessages());
			}
		}
		try {
			autoFillFields(population);
			if (population.isDeath()) {
				population.setLogoutDetail(buildLogoutDetail(population
						.isDeath()));
				// 缓存计数器
				PageInfoCacheThreadLocal.decrease(
						MemCacheConstant.getCachePageKey(population.getClass()
								.getSimpleName()), population, 1);
			}
			Countrymen temp = populationRelationService.businessOption(
					population, population.getActualPopulationType());
			contructCurrentAddress(population);
			population.setHouseId(temp.getHouseId());
			rebuildHouseAddress(population);
			return population;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类FPersonnelServiceImpl的updateFPersonnelBaseInfo方法出现异常，原因：",
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

	private void autoFillFields(FPersonnel FPersonnel) {
		autoFillOrganizationInternalCode(FPersonnel);
		autoFillChinesePinyin(FPersonnel);
		autoFillBirthday(FPersonnel);
		FPersonnel.setIdCardNo(FPersonnel.getIdCardNo().toUpperCase());
		autoIsDeath(FPersonnel);
	}

	private void autoIsDeath(FPersonnel domain) {
		if (domain.isDeath()) {
			domain.setIsEmphasis(IsEmphasis.IsNotEmphasis);
		} else {
			domain.setIsEmphasis(IsEmphasis.Emphasis);
		}
	}

	private void autoFillOrganizationInternalCode(FPersonnel FPersonnel) {
		Organization org = organizationDubboService.getSimpleOrgById(FPersonnel
				.getOrganization().getId());
		if (org == null)
			throw new BusinessValidationException("数据传入错误");
		FPersonnel.setOrgInternalCode(org.getOrgInternalCode());
	}

	private void autoFillChinesePinyin(FPersonnel FPersonnel) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(FPersonnel.getName());
		FPersonnel.setSimplePinyin(pinyin.get("simplePinyin"));
		FPersonnel.setFullPinyin(pinyin.get("fullPinyin"));
	}

	private void autoFillBirthday(FPersonnel FPersonnel) {
		if (StringUtil.isStringAvaliable(FPersonnel.getIdCardNo())) {
			FPersonnel.setBirthday(IdCardUtil.parseBirthday(FPersonnel
					.getIdCardNo()));
		}
	}

	@Override
	public FPersonnel addFPersonnelBaseInfo(FPersonnel population) {
		ValidateResult baseDataValidator = updateValidator
				.validateBaseInfo(population);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			if (checkDataExitInCache(population,
					MemCacheConstant.CACHE_ADDFPERSONNELBASEINFO,
					CACHE_ADDFPERSONNELBASEINFO_VALUE)) {
				return population;
			}
			return add(population);
		} catch (Exception e) {
			logger.error("FPersonnelServiceImpl addFPersonnelBaseInfo", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(population,
					MemCacheConstant.CACHE_ADDFPERSONNELBASEINFO);
		}
	}

	private FPersonnel add(FPersonnel FPersonnel) {
		autoFillFields(FPersonnel);
		try {
			contructCurrentAddress(FPersonnel);
			Countrymen temp = populationRelationService.businessOption(
					FPersonnel, FPersonnel.getActualPopulationType());
			FPersonnel.setBaseInfoId(temp.getBaseInfoId());
			FPersonnel.setAddressInfoId(temp.getAddressInfoId());
			FPersonnel.setSourcesState(null);
			FPersonnel = fPersonnelDao.add(FPersonnel);
			populationRelationService.addPopulationRelation(temp.getId(),
					FPersonnel.getActualPopulationType(), FPersonnel.getId(),
					BaseInfoTables.FPERSONNEL_KEY);
			FPersonnel.setHouseId(temp.getHouseId());
			rebuildHouseAddress(FPersonnel);
			// 人员轨迹
			// addPersonnelTrackWhenAttentionedOrCancel(FPersonnel, null,
			// PersonnelTrackOperationType.ATTENTIONED,
			// PersonInitType.IMPORT, "");

			if (IsEmphasis.Emphasis.equals(FPersonnel.getIsEmphasis())) {
				// 缓存计数器
				PageInfoCacheThreadLocal.increment(
						MemCacheConstant.getCachePageKey(FPersonnel.getClass()
								.getSimpleName()), FPersonnel, 1);
			}
			return FPersonnel;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类FPersonnelServiceImpl的add方法出现异常，原因：",
					"新增FPersonnel信息出现错误", e);
		}
	}

	/**
	 * 如果人口的房屋信息（CurrentAddress）不为空，并且房屋id不存在，新增一个房屋，并且建立关联关系, 如果房屋id不为空直接建立关联关系
	 * 如果房屋信息为空,并且有房屋id不为空，则删除人房关系
	 * 
	 * @param householdStaff
	 */
	private void rebuildHouseAddress(FPersonnel householdStaff) {
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
						.setHouseOperateSource(NewBaseInfoTables.FPERSONNEL_KEY);
				houseInfo = actualHouseService.addHouseInfo(houseInfo);

				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.F_PERSONNEL, householdStaff,
						houseInfo.getId());
			} else if (householdStaff.getHouseId() != null) {
				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.F_PERSONNEL, householdStaff,
						householdStaff.getHouseId());
			}
		} else {
			housePopulationMaintanceService.releaseHouse(
					PopulationCatalog.F_PERSONNEL, householdStaff.getId(),
					householdStaff.getHouseId());
		}
	}

	private void addPersonnelTrackWhenAttentionedOrCancel(
			FPersonnel dangerousGoodsPractitioner,
			Organization oldPersonnelOrg, Integer operationType,
			Integer personInitType, String operationContent) {
		personnelTrackService.addPersonnelTrackWhenAttentionedOrCancel(
				dangerousGoodsPractitioner, oldPersonnelOrg,
				BaseInfoTables.FPERSONNEL_KEY, operationType, personInitType,
				operationContent);
	}

	@Override
	public FPersonnel updateFPersonnelBusiness(FPersonnel population) {
		if (!ExcelImportHelper.isImport.get()) {
			ValidateResult baseDataValidator = updateValidator
					.validateSpecializedInfo(population);
			if (baseDataValidator.hasError()) {
				throw new BusinessValidationException(
						baseDataValidator.getErrorMessages());
			}
		}
		try {
			population = fPersonnelDao.updateBusiness(population);
			PageInfoCacheThreadLocal.update(
					MemCacheConstant.getCachePageKey(FPersonnel.class),
					population, UpdateType.BUSINESS);
			return population;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类FPersonnelServiceImpl的updateFPersonnelBusiness方法出现异常，原因：",
					"修改信息出现错误", e);
		}

	}

	@Override
	public List<Long> deleteFPersonnelByIds(List<Long> personIds) {
		if (personIds == null) {
			throw new BusinessValidationException("id没有获得");
		}
		for (Long id : personIds) {
			deleteFPersonnelById(id);
		}
		return personIds;
	}

	private void deleteFPersonnelById(Long id) {

		FPersonnel domain = fPersonnelDao.get(id);
		if (null != domain) {
			log(WARN, F_PERSONNEL, ThreadVariable.getSession().getUserName()
					+ "删除F" + domain.getName(), OperatorType.DELETE,
					ObjectToJSON.convertJSON(domain));
			domain.setPopulationTypeBean(getPopulationRelationService()
					.getBusinessPopulationTypeBean(id,
							PopulationType.F_PERSONNEL));
			getRecoverDatasService().deleteActualPopulation(domain);
			populationRelationService.businessDeletePopulationRelation(id,
					PopulationType.F_PERSONNEL);
			fPersonnelDao.delete(id);
			try {
				PluginServiceHelpUtil.doService("routerService",
						"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class },
						PopulationType.F_PERSONNEL, id);
				/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
				PluginServiceHelpUtil.doService("routerService",
						"setOrgIdAndCardNoOrName", new Class[] { Long.class,
								String.class, String.class, Long.class },
						domain.getOrganization().getId(), domain.getIdCardNo(),
						PopulationType.F_PERSONNEL, id);
				issueTypeService.setOrgIdAndCardNoOrNameForPerson(domain
						.getOrganization().getId(), domain.getIdCardNo(),
						"fPersonnel", id);

				if (IsEmphasis.Emphasis.equals(domain.getIsEmphasis())) {
					// 缓存计数器
					PageInfoCacheThreadLocal.decrease(MemCacheConstant
							.getCachePageKey(FPersonnel.class.getSimpleName()),
							domain, 1);
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					logger.error("", e1);
				}
			} catch (Exception e) {
				logger.error("FPersonnelServiceImpl deleteFPersonnelById", e);
			}
		}

	}

	private boolean validateObjId(Long id) {
		return null == id || id < 1L;
	}

	@Override
	public FPersonnel getSimpleFPersonnelById(Long id) {
		if (validateObjId(id)) {
			throw new BusinessValidationException("获取F时，ID不合法");
		}
		return fPersonnelDao.get(id);
	}

	@Override
	public List<FPersonnel> updateDeathByIds(List<Long> populationIds,
			boolean death) {
		List<FPersonnel> list = new ArrayList<FPersonnel>();
		for (Long id : populationIds) {
			FPersonnel population = this.getSimpleFPersonnelById(id);
			updateLogOutByPopulationTypeAndId(
					LogoutDetail.buildLogoutDetail(death),
					BaseInfoTables.FPERSONNEL_KEY, population.getId());
			baseInfoService.updateDeathStateById(population.getBaseInfoId(),
					death, population.getIdCardNo(), population
							.getOrganization().getId(), population
							.getOrgInternalCode(),
					NewBaseInfoTables.FPERSONNEL_KEY);
			list.add(population);
			// 缓存计数器
			PageInfoCacheThreadLocal.increment(MemCacheConstant
					.getCachePageKey(FPersonnel.class.getSimpleName()),
					population, 1);
		}
		return list;
	}

	@Override
	public FPersonnel getFPersonnelByIdCardNo(String idCardNo, Long id) {
		if (idCardNo == null || "".equals(idCardNo.trim())) {
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
		return fPersonnelDao.getByIdCard(list, id);
	}

	@Override
	public FPersonnel updateFPersonnelByName(String idCardNo, Long id,
			FPersonnel domain) {
		try {
			FPersonnel older = this.getFPersonnelByIdCardNo(idCardNo, id);
			domain.setId(older.getId());
			domain.setCreateDate(older.getCreateDate());
			domain.setCreateUser(older.getCreateUser());
			return updateFPersonnel(domain);
		} catch (Exception e) {
			logger.error("FPersonnelServiceImpl updateFPersonnelByName", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public void deleteFPersonnelByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			for (int i = 0; i < ids.length; i++) {
				deleteFPersonnelById(ids[i]);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类FPersonnelServiceImpl的deleteFPersonnelByIds方法出现异常，原因：",
					"删除F信息出现错误", e);
		}

	}

	@Override
	public FPersonnel getFPersonnelById(Long id) {
		return getSimpleFPersonnelById(id);
	}

	@Override
	public void deleteBusinessPopulationDuplicate(Long id) {
		deleteFPersonnelById(id);
	}

}

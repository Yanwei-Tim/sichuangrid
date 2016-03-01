package com.tianque.baseInfo.superiorVisit.service;

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
import com.tianque.baseInfo.superiorVisit.dao.SuperiorVisitDao;
import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
import com.tianque.cache.PageInfoCacheThreadLocal;
import com.tianque.cache.UpdateType;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.VisitTypeVo;
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

@Service("superiorVisitService")
@Transactional
public class SuperiorVisitServiceImpl extends BaseInfoPopulationTemplateImpl
		implements SuperiorVisitService, PopulationProccessorService {
	private static final String CACHE_ADDSUPERIORVISIT_VALUE = "CACHE_ADDSUPERIORVISIT";
	private static final String CACHE_ADDSUPERIORVISITFORMOBILE_VALUE = "CACHE_ADDSUPERIORVISITFORMOBILE";
	private static final String CACHE_ADDSUPERIORVISITBASEINFO_VALUE = "CACHE_ADDSUPERIORVISITBASEINFO";

	@Qualifier("superiorVisitValidator")
	@Autowired
	private AbstractCountrymenValidator<SuperiorVisit> updateValidator;

	@Autowired
	private SuperiorVisitDao superiorVisitDao;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private IssueTypeService issueTypeService;
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

	@Resource(name = "superiorVisitDao")
	public void setBaseInfoPopulationBaseDao(SuperiorVisitDao superiorVisitDao) {
		super.setBaseInfoPopulationBaseDao(superiorVisitDao);
	}

	@Override
	public SuperiorVisit addSuperiorVisit(SuperiorVisit superiorVisit,
			List<Long> visitTypes) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				superiorVisitValidator(superiorVisit);
			}
			if (checkDataExitInCache(superiorVisit,
					MemCacheConstant.CACHE_ADDSUPERIORVISIT,
					CACHE_ADDSUPERIORVISIT_VALUE)) {
				return superiorVisit;
			}
			return add(superiorVisit, visitTypes);
		} catch (Exception e) {
			logger.error("", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(superiorVisit,
					MemCacheConstant.CACHE_ADDSUPERIORVISIT);
		}
	}

	private SuperiorVisit add(SuperiorVisit superiorVisit, List<Long> visitTypes) {
		autoFilled(superiorVisit);
		autoIsDeath(superiorVisit);
		try {
			contructCurrentAddress(superiorVisit);
			Countrymen temp = populationRelationService.businessOption(
					superiorVisit, superiorVisit.getActualPopulationType());
			superiorVisit.setBaseInfoId(temp.getBaseInfoId());
			superiorVisit.setAddressInfoId(temp.getAddressInfoId());
			superiorVisit.setSourcesState(null);
			superiorVisit = superiorVisitDao.add(superiorVisit);
			populationRelationService.addPopulationRelation(temp.getId(),
					superiorVisit.getActualPopulationType(),
					superiorVisit.getId(), BaseInfoTables.SUPERIORVISIT_KEY);
			if (visitTypes != null && visitTypes.size() > 0) {
				addVisitTypes(visitTypes, superiorVisit.getId());
			}
			superiorVisit.setHouseId(temp.getHouseId());
			rebuildHouseAddress(superiorVisit);

			// 缓存计数器
			if (IsEmphasis.Emphasis.equals(superiorVisit.getIsEmphasis())) {
				PageInfoCacheThreadLocal.increment(MemCacheConstant
						.getCachePageKey(SuperiorVisit.class.getSimpleName()),
						superiorVisit, 1);
			}
			return superiorVisit;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SuperiorVisitServiceImpl的addSuperiorVisit方法出现异常，原因：",
					"新增重点上访人员信息出现错误", e);
		}
	}

	/**
	 * 如果人口的房屋信息（CurrentAddress）不为空，并且房屋id不存在，新增一个房屋，并且建立关联关系, 如果房屋id不为空直接建立关联关系
	 * 如果房屋信息为空,并且有房屋id不为空，则删除人房关系
	 * 
	 * @param householdStaff
	 */
	private void rebuildHouseAddress(SuperiorVisit householdStaff) {

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
						.setHouseOperateSource(NewBaseInfoTables.SUPERIORVISIT_KEY);
				houseInfo = actualHouseService.addHouseInfo(houseInfo);

				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.SUPERIORVISIT, householdStaff,
						houseInfo.getId());
			} else if (householdStaff.getHouseId() != null) {
				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.SUPERIORVISIT, householdStaff,
						householdStaff.getHouseId());
			}
		} else {
			housePopulationMaintanceService.releaseHouse(
					PopulationCatalog.SUPERIORVISIT, householdStaff.getId(),
					householdStaff.getHouseId());
		}
	}

	private void addVisitTypes(List<Long> visitTypes, Long superiorVisitId) {
		if (visitTypes != null) {
			for (int i = 0; i < visitTypes.size(); i++) {
				superiorVisitDao.addSuperiorVisitType(superiorVisitId,
						visitTypes.get(i));
			}
		}
	}

	private void deleteSuperiorVisitById(Long id) {
		if (id == null || id < 0L) {
			throw new BusinessValidationException("传入参数为空");
		}
		SuperiorVisit domain = superiorVisitDao.get(id);
		domain.setCreateDate(new Date());
		domain.setPopulationTypeBean(getPopulationRelationService()
				.getBusinessPopulationTypeBean(id,
						PopulationType.SUPERIOR_VISIT));
		getRecoverDatasService().deleteActualPopulation(domain);
		populationRelationService.businessDeletePopulationRelation(id,
				PopulationType.SUPERIOR_VISIT);

		superiorVisitDao.deleteVisitTypeBySuperiorVisitId(id);
		superiorVisitDao.delete(id);
		try {
			PluginServiceHelpUtil.doService("routerService",
					"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
					new Class[] { String.class, Long.class },
					PopulationType.SUPERIOR_VISIT, id);
			/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
			PluginServiceHelpUtil.doService("routerService",
					"setOrgIdAndCardNoOrName", new Class[] { Long.class,
							String.class, String.class, Long.class }, domain
							.getOrganization().getId(), domain.getIdCardNo(),
					PopulationType.SUPERIOR_VISIT, id);
			issueTypeService.setOrgIdAndCardNoOrNameForPerson(domain
					.getOrganization().getId(), domain.getIdCardNo(),
					PopulationType.SUPERIOR_VISIT, id);

			if (IsEmphasis.Emphasis.equals(domain.getIsEmphasis())) {
				// 缓存计数器
				PageInfoCacheThreadLocal.decrease(MemCacheConstant
						.getCachePageKey(SuperiorVisit.class.getSimpleName()),
						domain, 1);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Override
	public void deleteSuperiorVisitByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			for (int i = 0; i < ids.length; i++) {
				deleteSuperiorVisitById(ids[i]);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SuperiorVisitServiceImpl的deleteSuperiorVisitByIds方法出现异常，原因：",
					"删除重点上访人员信息出现错误", e);
		}
	}

	@Override
	public SuperiorVisit getSuperiorVisitById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			SuperiorVisit superiorVisit = superiorVisitDao.get(id);
			List<VisitTypeVo> list = superiorVisitDao.findVisitTypeById(id);
			if (list != null && list.size() > 0) {
				List<PropertyDict> pros = new ArrayList<PropertyDict>();
				String typeName = getVisitType(superiorVisit.getVisitType())
						+ "：";
				for (int i = 0; i < list.size(); i++) {
					PropertyDict pro = propertyDictService
							.getPropertyDictById(list.get(i)
									.getSuperiorVisitType());
					pros.add(pro);
					typeName += pro.getDisplayName() + ",";
				}
				superiorVisit.setVisitTypes(pros);
				superiorVisit.setTypeName(typeName.substring(0,
						typeName.length() - 1));
			}
			return superiorVisit;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SuperiorVisitServiceImpl的getSuperiorVisitById方法出现异常，原因：",
					"获取重点上访人员信息出现错误", e);
		}
	}

	private String getVisitType(Long id) {
		if (id == null) {
			return "";
		}
		if (id == 1) {
			return "正常访";
		} else if (id == 0) {
			return "异常访";
		}
		return "";
	}

	@Override
	public SuperiorVisit updateSuperiorVisit(SuperiorVisit superiorVisit,
			List<Long> visitTypes) {
		if (!ExcelImportHelper.isImport.get()) {
			superiorVisitValidator(superiorVisit);
		}
		try {
			autoFilled(superiorVisit);
			if (superiorVisit.isDeath()) {
				superiorVisit.setIsEmphasis(IsEmphasis.IsNotEmphasis);
			}
			superiorVisit = superiorVisitDao.update(superiorVisit);
			superiorVisitDao.deleteVisitTypeBySuperiorVisitId(superiorVisit
					.getId());
			this.addVisitTypes(visitTypes, superiorVisit.getId());
			proccessHouseBind(superiorVisit);
			return superiorVisit;
		} catch (Exception e) {
			logger.error(
					"类SuperiorVisitServiceImpl的getSuperiorVisitById方法出现异常，原因：",
					e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("获取重点上访人员信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public PageInfo<SuperiorVisit> findSuperiorVisitForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, Long isEmphasis) {
		try {
			if (null == orgId) {
				return constructEmptyPageInfo();
			} else {
				Organization org = organizationDubboService
						.getSimpleOrgById(orgId);
				if (org == null) {
					return constructEmptyPageInfo();
				} else {
					//
					// return superiorVisitDao
					// .findSuperiorVisitForPageByOrgInternalCode(
					// org.getOrgInternalCode(), pageNum,
					// pageSize, sidx, sord, isEmphasis);
					// SearchSuperiorVisitVo superiorVisitVo = new
					// SearchSuperiorVisitVo();
					// superiorVisitVo
					// .setOrgInternalCode(org.getOrgInternalCode());
					// superiorVisitVo.setIsEmphasis(isEmphasis);
					// return superiorVisitDao.findPagerUsingCacheBySearchVo(
					// orgId, superiorVisitVo, pageNum, pageSize, sidx,
					// sord, MemCacheConstant
					// .getCachePageKey(SuperiorVisit.class
					// .getSimpleName()));
					Map<String, Object> query = new HashMap<String, Object>();
					query.put("orgInternalCode", org.getOrgInternalCode());
					query.put("isEmphasis", isEmphasis);
					PageInfo<SuperiorVisit> pageInfos = superiorVisitDao
							.findPagerUsingCacheBySearchVo(
									orgId,
									query,
									pageNum,
									pageSize,
									"SuperiorVisitDefaultList",
									MemCacheConstant
											.getCachePageKey(SuperiorVisit.class));
					fitCountrymen(pageInfos);
					fitServiceMemberHasObject(BaseInfoTables.SUPERIORVISIT_KEY,
							pageInfos);
					//隐藏身份证中间4位
					pageInfos=hiddenIdCard(pageInfos);
					return pageInfos;

				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SuperiorVisitServiceImpl的findSuperiorVisitForPageByOrgInternalCode方法出现异常，原因：",
					"查询重点上访人员信息出现错误", e);
		}
	}

	//隐藏身份证中间4位
			private PageInfo<SuperiorVisit> hiddenIdCard(PageInfo<SuperiorVisit> pageInfo){
						//判断权限，有权限不隐藏
						if(permissionDubboService.
								isUserHasPermission(ThreadVariable.getUser().getId(), "isSuperiorVisitManagementNotHidCard")){
							return pageInfo;
						}
						List<SuperiorVisit> list = pageInfo.getResult();
						int index=0;
						for (SuperiorVisit verification:list) {
							verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
							list.set(index, verification);
							index++;
						}
						pageInfo.setResult(list);
						return pageInfo;
		}
	@Override
	public boolean hasDuplicateSuperiorVisit(Long orgId, String idCardNo,
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
			Long exsited = superiorVisitDao.getIdByBaseinfoIdAndOrgId(
					countrymen.getId(), orgId);
			return exceptedId == null ? exsited != null
					: (exsited != null && !exceptedId.equals(exsited));
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SuperiorVisitServiceImpl的hasDuplicateSuperiorVisit方法出现异常，原因：",
					"判断重点上访人员身份证号码是否存在出现错误", e);
		}
	}

	@Override
	public SuperiorVisit hasDuplicateSuperiorVisit(Long orgId, String idCardNo) {
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
			return superiorVisitDao.getByIdCard(list, orgId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SuperiorVisitServiceImpl的hasDuplicateSuperiorVisit方法出现异常，原因：",
					"判断重点上访人员身份证号码是否存在出现错误", e);
		}
	}

	private PageInfo<SuperiorVisit> constructEmptyPageInfo() {
		PageInfo<SuperiorVisit> result = new PageInfo<SuperiorVisit>();
		result.setResult(new ArrayList<SuperiorVisit>());
		return result;
	}

	private void autoFillChinesePinyin(SuperiorVisit superiorVisit) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(superiorVisit.getName());
		superiorVisit.setSimplePinyin(pinyin.get("simplePinyin"));
		superiorVisit.setFullPinyin(pinyin.get("fullPinyin"));
	}

	private void autoFillBirthday(SuperiorVisit superiorVisit) {
		if (StringUtil.isStringAvaliable(superiorVisit.getIdCardNo())) {
			superiorVisit.setBirthday(IdCardUtil.parseBirthday(superiorVisit
					.getIdCardNo()));
		}
	}

	private SuperiorVisit getSuperiorVisitByIdCardNo(String idCardNo, Long orgId) {
		if (idCardNo == null || "".equals(idCardNo.trim()) || orgId == null) {
			return null;
		}
		idCardNo = idCardNo.toUpperCase();
		List<String> list = new ArrayList<String>();
		list.add(idCardNo);
		if (idCardNo.length() == 18) {
			list.add(IdCardUtil.idCardNo18to15(idCardNo));
		} else if (idCardNo.length() == 15) {
			list.add(IdCardUtil.idCardNo15to18(idCardNo, "19"));
			list.add(IdCardUtil.idCardNo15to18(idCardNo, "20"));
		}
		return superiorVisitDao.getByIdCard(list, orgId);
	}

	private void autoFilled(SuperiorVisit superiorVisit) {
		superiorVisit.setIdCardNo(superiorVisit.getIdCardNo().toUpperCase());
		autoFillOrganizationInternalCode(superiorVisit);
		autoFillChinesePinyin(superiorVisit);
		autoFillBirthday(superiorVisit);
	}

	private void autoIsDeath(SuperiorVisit superiorVisit) {
		if (superiorVisit.isDeath()) {
			superiorVisit.setIsEmphasis(IsEmphasis.IsNotEmphasis);
		} else {
			superiorVisit.setIsEmphasis(IsEmphasis.Emphasis);
		}
	}

	private void autoFillOrganizationInternalCode(SuperiorVisit superiorVisit) {
		Organization org = organizationDubboService
				.getSimpleOrgById(superiorVisit.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		superiorVisit.setOrgInternalCode(org.getOrgInternalCode());
	}

	private void superiorVisitValidator(SuperiorVisit superiorVisit) {
		ValidateResult baseDataValidator = updateValidator
				.validate(superiorVisit);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	private void superiorVisitValidatorForMobile(SuperiorVisit superiorVisit) {
		ValidateResult baseDataValidator = updateValidator
				.validate(superiorVisit);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		} else if (hasDuplicateSuperiorVisit(superiorVisit.getOrganization()
				.getId(), superiorVisit.getIdCardNo(), superiorVisit.getId())) {
			throw new BusinessValidationException("该网格下已存在相同身份证号码");
		}
	}

	@Override
	public Long proccessPopulationSpecializedInfo(
			ActualPopulation actualPopulation, String[] populationType,
			Map<String, Object> population) {
		actualPopulation
				.setAttentionPopulationType(NewBaseInfoTables.SUPERIORVISIT_KEY);
		try {
			Long orgId = Long.valueOf(((String[]) population
					.get("organization.id"))[0]);
			String idCardNo = ((String[]) population.get("idCardNo"))[0];
			SuperiorVisit superiorVisit = superiorVisitDao
					.getByOrgIdAndIdCardNo(orgId, idCardNo);
			if (!com.tianque.util.Arrays.hasObjectInArray(populationType,
					PopulationType.SUPERIOR_VISIT)) {
				if (null != superiorVisit) {
					superiorVisit.setIsEmphasis(IsEmphasis.IsNotEmphasis);
					this.updateEmphasiseById(superiorVisit.getId(),
							IsEmphasis.IsNotEmphasis);
				}
			} else {
				if (null == superiorVisit) {
					superiorVisit = new SuperiorVisit();
					copyProperty(actualPopulation, population, superiorVisit);
					List<Long> visitTypes = (List<Long>) population
							.get("visitTypes");
					this.addSuperiorVisit(superiorVisit, visitTypes);
				} else {
					Long id = superiorVisit.getId();
					copyProperty(actualPopulation, population, superiorVisit);
					superiorVisit.setId(id);
					superiorVisit.setIsEmphasis(IsEmphasis.Emphasis);
					List<Long> visitTypes = (List<Long>) population
							.get("visitTypes");
					this.updateSuperiorVisitBusiness(superiorVisit, visitTypes);
					this.updateEmphasiseById(superiorVisit.getId(),
							IsEmphasis.Emphasis);
				}
			}
			return superiorVisit == null
					|| superiorVisit.getIsEmphasis() == IsEmphasis.IsNotEmphasis
							.longValue() ? null : superiorVisit.getId();
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SuperiorVisitServiceImpl的proccessPopulationSpecializedInfo方法出现异常，原因：",
					"处理重点上访人员信息出现错误", e);
		}
	}

	private void updateEmphasiseById(Long id, Long isEmphasis) {
		LogoutDetail logoutDetail = new LogoutDetail();
		logoutDetail.setLogout(isEmphasis);
		updateLogOutByPopulationTypeAndId(logoutDetail,
				BaseInfoTables.SUPERIORVISIT_KEY, id);
	}

	private void copyProperty(ActualPopulation actualPopulation,
			Map<String, Object> population, SuperiorVisit superiorVisit) {
		copyProperties(superiorVisit, actualPopulation);

		superiorVisit.setVisitReason(Arrays.getStringValueFromArray(population,
				"visitReason"));

		superiorVisit.setVisitState(Arrays.getPropertyDictFromArray(population,
				"visitState.id"));
		superiorVisit.setVisitType(Arrays.getLongValueFromArray(population,
				"visitType"));

		superiorVisit.setAttentionExtent(Arrays.getPropertyDictFromArray(
				population, "attentionExtent.id"));
		superiorVisit
				.setAttentionPopulationType(BaseInfoTables.SUPERIORVISIT_KEY);
	}

	@Override
	public Map<String, Map<String, Object>> getPopulationSpecializedInfoByOrgIdAndIdCardNo(
			Long orgId, String idCardNo) {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		try {
			SuperiorVisit superiorVisit = superiorVisitDao
					.getByOrgIdAndIdCardNo(orgId, idCardNo);
			if (null != superiorVisit) {
				List<VisitTypeVo> list = superiorVisitDao
						.findVisitTypeById(superiorVisit.getId());
				if (list != null && list.size() > 0) {
					List<PropertyDict> pros = new ArrayList<PropertyDict>();
					String typeName = getVisitType(superiorVisit.getVisitType())
							+ "：";
					for (int i = 0; i < list.size(); i++) {
						PropertyDict pro = propertyDictService
								.getPropertyDictById(list.get(i)
										.getSuperiorVisitType());
						pros.add(pro);
						typeName += pro.getDisplayName() + ",";
					}
					superiorVisit.setVisitTypes(pros);
					superiorVisit.setTypeName(typeName.substring(0,
							typeName.length() - 1));
				}
				Map<String, Object> superiorVisitMap = new HashMap<String, Object>();
				superiorVisitMap.put("id", superiorVisit.getId());
				superiorVisitMap.put("isEmphasis",
						superiorVisit.getIsEmphasis());
				superiorVisitMap.put("visitReason",
						superiorVisit.getVisitReason());
				superiorVisitMap.put("visitState",
						superiorVisit.getVisitState());
				superiorVisitMap.put("visitType", superiorVisit.getVisitType());
				superiorVisitMap.put("visitTypes",
						superiorVisit.getVisitTypes());
				superiorVisitMap.put("typeName", superiorVisit.getTypeName());
				superiorVisitMap.put("attentionExtent",
						superiorVisit.getAttentionExtent());
				map.put(PopulationType.SUPERIOR_VISIT, superiorVisitMap);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SuperiorVisitServiceImpl的getPopulationSpecializedInfoByOrgIdAndIdCardNo方法出现异常，原因：",
					"获取重点上访人员信息出现错误", e);
		}
		return map;
	}

	@Override
	public void updatePopulationBaseInfo(ActualPopulation actualPopulation) {
		try {
			SuperiorVisit superiorVisit = superiorVisitDao
					.getByOrgIdAndIdCardNo(actualPopulation.getOrganization()
							.getId(), actualPopulation.getIdCardNo());
			if (null != superiorVisit) {
				Long id = superiorVisit.getId();
				PropertyUtils.copyProperties(superiorVisit, actualPopulation);
				superiorVisit.setId(id);
				updateSuperiorVisitBaseInfo(superiorVisit);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SuperiorVisitServiceImpl的updatePopulationBaseInfo方法出现异常，原因：",
					"修改重点上访人员基本信息出现错误", e);
		}
	}

	@Override
	public void deletePopulationByPopulationId(Long populationId) {
		if (null != populationId) {
			this.deleteSuperiorVisitById(populationId);
			this.deletePopulationTypeByPopulationIdAndType(populationId,
					PopulationType.SUPERIOR_VISIT);
		}
	}

	@Override
	public List<SuperiorVisit> updateDeathByIds(Long[] populationIds,
			Boolean death) {
		List<SuperiorVisit> populationList = new ArrayList<SuperiorVisit>();
		for (int i = 0; i < populationIds.length; i++) {
			SuperiorVisit population = this
					.getSuperiorVisitById(populationIds[i]);
			updateLogOutByPopulationTypeAndId(
					LogoutDetail.buildLogoutDetail(death),
					BaseInfoTables.SUPERIORVISIT_KEY, population.getId());
			baseInfoService.updateDeathStateById(population.getBaseInfoId(),
					death, population.getIdCardNo(), population
							.getOrganization().getId(), population
							.getOrgInternalCode(),
					NewBaseInfoTables.SUPERIORVISIT_KEY);
			// 缓存计数器
			PageInfoCacheThreadLocal.increment(MemCacheConstant
					.getCachePageKey(SuperiorVisit.class.getSimpleName()),
					population, 1);
			populationList.add(population);
		}
		return populationList;
	}

	@Override
	public SuperiorVisit updateSuperiorVisitBaseInfo(SuperiorVisit superiorVisit) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				ValidateResult baseDataValidator = updateValidator
						.validateBaseInfo(superiorVisit);
				if (baseDataValidator.hasError()) {
					throw new BusinessValidationException(
							baseDataValidator.getErrorMessages());
				}
			}
			superiorVisit
					.setIdCardNo(superiorVisit.getIdCardNo().toUpperCase());
			autoFillBirthday(superiorVisit);
			autoFillChinesePinyin(superiorVisit);
			autoFillOrganizationInternalCode(superiorVisit);
			if (superiorVisit.isDeath()) {
				superiorVisit.setIsEmphasis(IsEmphasis.IsNotEmphasis);
				superiorVisit.setLogoutDetail(buildLogoutDetail(superiorVisit
						.isDeath()));
				// 缓存计数器
				PageInfoCacheThreadLocal.decrease(MemCacheConstant
						.getCachePageKey(SuperiorVisit.class.getSimpleName()),
						superiorVisit, 1);
			}
			Countrymen temp = populationRelationService.businessOption(
					superiorVisit, superiorVisit.getActualPopulationType());
			contructCurrentAddress(superiorVisit);
			superiorVisit.setHouseId(temp.getHouseId());
			rebuildHouseAddress(superiorVisit);
			superiorVisitDao.updateTableUpdateDateById(superiorVisit.getId());
			return superiorVisit;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SuperiorVisitServiceImpl的updateSuperiorVisitBaseInfo方法出现异常，原因：",
					"修改重点上访人员基本信息出现错误", e);
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
	public SuperiorVisit updateSuperiorVisitBusiness(
			SuperiorVisit superiorVisit, List<Long> visitTypes) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				ValidateResult specializedValidator = updateValidator
						.validateSpecializedInfo(superiorVisit);
				if (specializedValidator.hasError()) {
					throw new BusinessValidationException(
							specializedValidator.getErrorMessages());
				}
			}
			superiorVisit = superiorVisitDao.updateBusiness(superiorVisit);
			superiorVisitDao.deleteVisitTypeBySuperiorVisitId(superiorVisit
					.getId());
			if (visitTypes != null && visitTypes.size() > 0) {
				addVisitTypes(visitTypes, superiorVisit.getId());
			}
			PageInfoCacheThreadLocal.update(
					MemCacheConstant.getCachePageKey(SuperiorVisit.class),
					superiorVisit, UpdateType.BUSINESS);
			return superiorVisit;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SuperiorVisitServiceImpl的updateSuperiorVisitBusiness方法出现异常，原因：",
					"修改重点上访人员业务信息出现错误", e);
		}
	}

	@Override
	public SuperiorVisit getSuperiorVistByIdCardNoAndOrgId(String idCardNo,
			Long orgId) {
		return getSuperiorVisitByIdCardNo(idCardNo, orgId);
	}

	@Override
	public SuperiorVisit addSuperiorVisit(SuperiorVisit superiorVisit) {
		List<Long> list = getVisitTypeIds(superiorVisit);
		return addSuperiorVisit(superiorVisit, list);
	}

	private List<Long> getVisitTypeIds(SuperiorVisit superiorVisit) {
		List<PropertyDict> dicts = superiorVisit.getVisitTypes();
		List<Long> list = new ArrayList<Long>();
		if (dicts != null) {
			for (PropertyDict dict : dicts) {
				list.add(dict.getId());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Title: addSuperiorVisitForMobile
	 * @Description: TODO提取新方法，添加重点上访人员
	 * @param @param superiorVisit
	 * @param @param visitTypes
	 * @param @return
	 * @param @ 设定文件
	 * @return SuperiorVisit 返回类型
	 * @author wanggz
	 * @date 2014-6-19 下午02:49:00
	 */
	@Override
	public SuperiorVisit addSuperiorVisitForMobile(SuperiorVisit superiorVisit,
			List<Long> visitTypes) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				superiorVisitValidatorForMobile(superiorVisit);
			}
			if (checkDataExitInCache(superiorVisit,
					MemCacheConstant.CACHE_ADDSUPERIORVISITFORMOBILE,
					CACHE_ADDSUPERIORVISITFORMOBILE_VALUE)) {
				return superiorVisit;
			}
			return add(superiorVisit, visitTypes);
		} catch (Exception e) {
			logger.error("", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(superiorVisit,
					MemCacheConstant.CACHE_ADDSUPERIORVISITFORMOBILE);
		}
	}

	@Override
	public SuperiorVisit updateSuperiorVisitBusiness(SuperiorVisit domain) {
		List<PropertyDict> dicts = domain.getVisitTypes();
		List<Long> list = new ArrayList<Long>();
		for (PropertyDict dict : dicts) {
			list.add(dict.getId());
		}
		return updateSuperiorVisitBusiness(domain, list);
	}

	@Override
	public SuperiorVisit updateSuperiorVisit(SuperiorVisit superiorVisit) {
		List<Long> list = getVisitTypeIds(superiorVisit);
		return updateSuperiorVisit(superiorVisit, list);
	}

	@Override
	public SuperiorVisit addSuperiorVisitBaseInfo(SuperiorVisit superiorVisit) {
		ValidateResult baseDataValidator = updateValidator
				.validateBaseInfo(superiorVisit);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			if (checkDataExitInCache(superiorVisit,
					MemCacheConstant.CACHE_ADDSUPERIORVISITBASEINFO,
					CACHE_ADDSUPERIORVISITBASEINFO_VALUE)) {
				return superiorVisit;
			}
			return add(superiorVisit, getVisitTypeIds(superiorVisit));
		} catch (Exception e) {
			logger.error("", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(superiorVisit,
					MemCacheConstant.CACHE_ADDSUPERIORVISITBASEINFO);
		}
	}

	public AttentionPopulation getAttentionPopulationById(Long id) {
		return superiorVisitDao.get(id);
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
				SuperiorVisit people = getSuperiorVisitById(peoperId);
				if (people == null) {
					continue;
				}
				Long currentOrgid = people.getOrganization().getId();
				people.setOrganization(organization);
				// String orgCode = people.getOrgInternalCode();
				String orgCode = people.getOrganization().getOrgInternalCode();
				String idCardNo = people.getIdCardNo();
				// 验证目标组织机构是否存在这个人
				boolean result = hasDuplicateSuperiorVisit(targetOrgId,
						idCardNo, peoperId);
				if (result) {
					// 把存在的旧数据成为修改现在现在要移动的新数据
					if (currentOrgid.longValue() != targetOrgId.longValue()) {
						updateMovePersonByIdCardNo(idCardNo, targetOrgId,
								people);
						// 删掉要移动的数据 删除掉不在同一个网格
						deleteSuperiorVisitById(peoperId);
					}

				} else {
					// 转移网格数据
					superiorVisitDao.updateData(id, targetOrgId, orgCode,
							people);
				}
				people.setCreateDate(null);
			}

		}

	}

	public void updateMovePersonByIdCardNo(String idcard, Long targetorgId,
			SuperiorVisit people) {
		List<String> cardnos = CardNoHelper.createIdCardNo(idcard);
		// 获取
		SuperiorVisit older = superiorVisitDao
				.getByIdCard(cardnos, targetorgId);
		people.setId(older.getId());
		people.setCreateDate(older.getCreateDate());
		people.setCreateUser(older.getCreateUser());
		superiorVisitDao.update(people);
	}

	@Override
	public void deleteBusinessPopulationDuplicate(Long id) {
		this.deleteSuperiorVisitById(id);
	}
}

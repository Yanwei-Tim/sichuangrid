package com.tianque.baseInfo.qPersonnel.service;

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
import com.tianque.baseInfo.qPersonnel.dao.QPersonnelDao;
import com.tianque.baseInfo.qPersonnel.domain.QPersonnel;
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

@Service("qPersonnelService")
@Transactional
public class QPersonnelServiceImpl extends BaseInfoPopulationTemplateImpl
		implements QPersonnelService, PopulationProccessorService {

	private static final String CACHE_ADDQPERSONNEL_VALUE = "CACHE_ADDQPERSONNEL";
	private static final String CACHE_ADDQPERSONNELBASEINFO_VALUE = "CACHE_ADDQPERSONNELBASEINFO";
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	QPersonnelDao qPersonnelDao;

	@Qualifier("qPersonnelValidator")
	@Autowired
	private AbstractCountrymenValidator<QPersonnel> updateValidator;
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

	@Resource(name = "qPersonnelDao")
	public void setBaseInfoPopulationBaseDao(QPersonnelDao qPersonnelDao) {
		super.setBaseInfoPopulationBaseDao(qPersonnelDao);
	}

	@Override
	public Long proccessPopulationSpecializedInfo(
			ActualPopulation actualPopulation, String[] populationType,
			Map<String, Object> population) {
		actualPopulation
				.setAttentionPopulationType(NewBaseInfoTables.QPERSONNEL_KEY);
		Long orgId = Long
				.valueOf(((String[]) population.get("organization.id"))[0]);
		String idCardNo = ((String[]) population.get("idCardNo"))[0];
		QPersonnel QPersonnel = qPersonnelDao.getByOrgIdAndIdCardNo(orgId,
				idCardNo);
		if (!com.tianque.util.Arrays.hasObjectInArray(populationType,
				PopulationType.Q_PERSONNEL)) {
			if (null != QPersonnel) {
				QPersonnel.setIsEmphasis(IsEmphasis.IsNotEmphasis);
				updateEmphasiseById(QPersonnel.getId(),
						IsEmphasis.IsNotEmphasis);
			}
		} else {
			if (null == QPersonnel) {
				QPersonnel = new QPersonnel();
				copyProperty(actualPopulation, population, QPersonnel);
				addQPersonnel(QPersonnel);
			} else {
				Long id = QPersonnel.getId();
				copyProperty(actualPopulation, population, QPersonnel);
				QPersonnel.setId(id);
				QPersonnel.setIsEmphasis(IsEmphasis.Emphasis);
				updateQPersonnelBusiness(QPersonnel);
				updateEmphasiseById(QPersonnel.getId(), IsEmphasis.Emphasis);
			}
		}
		return QPersonnel == null
				|| QPersonnel.getIsEmphasis() == IsEmphasis.IsNotEmphasis
						.longValue() ? null : QPersonnel.getId();
	}

	private void updateEmphasiseById(Long id, Long isEmphasis) {
		LogoutDetail logoutDetail = new LogoutDetail();
		logoutDetail.setLogout(isEmphasis);
		updateLogOutByPopulationTypeAndId(logoutDetail,
				BaseInfoTables.QPERSONNEL_KEY, id);
	}

	@Override
	public QPersonnel updateQPersonnel(QPersonnel QPersonnel) {

		if (!ExcelImportHelper.isImport.get()) {
			QPersonnelValidator(QPersonnel);
		}
		try {
			autoFillFields(QPersonnel);
			if (QPersonnel.isDeath()) {
				QPersonnel.setIsEmphasis(IsEmphasis.IsNotEmphasis);
				deletePopulationTypeByPopulationIdAndType(QPersonnel.getId(),
						PopulationType.Q_PERSONNEL);
			}
			QPersonnel = qPersonnelDao.update(QPersonnel);
			return QPersonnel;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类QPersonnelServiceImpl的updateQPersonnel方法出现异常，原因：",
					"修改信息出现错误", e);
		}
	}

	private void QPersonnelValidator(QPersonnel QPersonnel) {
		ValidateResult baseDataValidator = updateValidator.validate(QPersonnel);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public QPersonnel addQPersonnel(QPersonnel qPersonnel) {
		if (!ExcelImportHelper.isImport.get()) {
			QPersonnelValidator(qPersonnel);
		}
		try {
			if (checkDataExitInCache(qPersonnel,
					MemCacheConstant.CACHE_ADDQPERSONNEL,
					CACHE_ADDQPERSONNEL_VALUE)) {
				return qPersonnel;
			}
			return add(qPersonnel);
		} catch (Exception e) {
			logger.error("", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(qPersonnel, MemCacheConstant.CACHE_ADDQPERSONNEL);
		}
	}

	private void copyProperty(ActualPopulation actualPopulation,
			Map<String, Object> population, QPersonnel qPersonnel) {

		copyProperties(qPersonnel, actualPopulation);
		qPersonnel.setAttentionExtent(Arrays.getPropertyDictFromArray(
				population, "attentionExtent.id"));
		qPersonnel.setQbusinessRemark(Arrays.getStringValueFromArray(
				population, "qbusinessRemark"));
		qPersonnel.setAttentionPopulationType(BaseInfoTables.QPERSONNEL_KEY);

	}

	@Override
	public Map<String, Map<String, Object>> getPopulationSpecializedInfoByOrgIdAndIdCardNo(
			Long orgId, String idCardNo) {
		QPersonnel qPersonnel = qPersonnelDao.getByOrgIdAndIdCardNo(orgId,
				idCardNo);
		if (null == qPersonnel) {
			return null;
		}
		Map<String, Object> dangerousGoodsPractitionerMap = new HashMap<String, Object>();
		dangerousGoodsPractitionerMap.put("id", qPersonnel.getId());
		dangerousGoodsPractitionerMap.put("isEmphasis",
				qPersonnel.getIsEmphasis());
		dangerousGoodsPractitionerMap.put("workUnit", qPersonnel.getWorkUnit());
		dangerousGoodsPractitionerMap.put("attentionExtent",
				qPersonnel.getAttentionExtent());
		dangerousGoodsPractitionerMap.put("qbusinessRemark",
				qPersonnel.getQbusinessRemark());
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		map.put(PopulationType.Q_PERSONNEL, dangerousGoodsPractitionerMap);
		return map;
	}

	@Override
	public void updatePopulationBaseInfo(ActualPopulation actualPopulation) {
		QPersonnel QPersonnel = qPersonnelDao.getByOrgIdAndIdCardNo(
				actualPopulation.getOrganization().getId(),
				actualPopulation.getIdCardNo());
		if (null == QPersonnel) {
			return;
		}
		Long id = QPersonnel.getId();
		if (!StringUtil.isStringAvaliable(actualPopulation.getWorkUnit())) {
			actualPopulation.setWorkUnit(QPersonnel.getWorkUnit());
		}
		copyProperties(QPersonnel, actualPopulation);
		QPersonnel.setId(id);
		updateQPersonnel(QPersonnel);
	}

	@Override
	public void deletePopulationByPopulationId(Long populationId) {
		if (null != populationId) {
			this.deleteQPersonnelById(populationId);
			this.deletePopulationTypeByPopulationIdAndType(populationId,
					PopulationType.Q_PERSONNEL);
		}
	}

	@Override
	public PageInfo<QPersonnel> findQPersonnelsForPageByOrgId(
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
				 * BaseInfoTables.QPERSONNEL_KEY); return
				 * qPersonnelDao.findPagerUsingCacheBySearchVo( organizationId,
				 * map, page, rows, "QPersonnels",
				 * MemCacheConstant.getCachePageKey(QPersonnel.class
				 * .getSimpleName()));
				 */

				Map<String, Object> query = new HashMap<String, Object>();
				query.put("orgInternalCode", org.getOrgInternalCode());
				query.put("isEmphasis", isEmphasis);
				query.put("attentionPopulationType",
						BaseInfoTables.QPERSONNEL_KEY);
				PageInfo<QPersonnel> pageInfos = qPersonnelDao
						.findPagerUsingCacheBySearchVo(organizationId, query,
								page, rows, "QPersonnelDefaultList",
								MemCacheConstant
										.getCachePageKey(QPersonnel.class));
				fitCountrymen(pageInfos);
				fitServiceMemberHasObject(BaseInfoTables.QPERSONNEL_KEY,
						pageInfos);
				//隐藏身份证中间4位
				pageInfos=hiddenIdCard(pageInfos);
				return pageInfos;

				// return qPersonnelDao.findQPersonnelsForPageByOrgInternalCode(
				// org.getOrgInternalCode(), page, rows, sidx, sord,
				// isEmphasis);
			}
		}
	}
	
	//隐藏身份证中间4位
			private PageInfo<QPersonnel> hiddenIdCard(PageInfo<QPersonnel> pageInfo){
							//判断权限，有权限不隐藏
							if(permissionDubboService.
									isUserHasPermission(ThreadVariable.getUser().getId(), "isQPersonnelManagementNotHidCard")){
								return pageInfo;
							}
							List<QPersonnel> list = pageInfo.getResult();
							int index=0;
							for (QPersonnel verification:list) {
								verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
								list.set(index, verification);
								index++;
							}
							pageInfo.setResult(list);
							return pageInfo;
		}

	private PageInfo<QPersonnel> constructEmptyPageInfo() {
		PageInfo<QPersonnel> emptyPageInfo = new PageInfo<QPersonnel>();
		emptyPageInfo.setResult(new ArrayList<QPersonnel>());
		return emptyPageInfo;
	}

	@Override
	public Boolean existQPersonnel(Long orgId, String idCardNo, Long hopeId) {
		if (idCardNo == null || "".equals(idCardNo.trim()) || orgId == null) {
			return false;
		}
		idCardNo = idCardNo.toUpperCase();
		Countrymen countrymen = baseInfoService.getBaseInfoByIdCardNo(idCardNo);
		if (countrymen == null) {
			return false;
		}
		Long dr = qPersonnelDao.getIdByBaseinfoIdAndOrgId(countrymen.getId(),
				orgId);
		return hopeId == null ? dr != null
				: (dr != null && hopeId.longValue() != dr.longValue());
	}

	@Override
	public QPersonnel hasDuplicateQPersonnel(Long orgId, String idCardNo) {
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
			return qPersonnelDao.getByIdCard(list, orgId);

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类QPersonnelServiceImpl的hasDuplicateQPersonnel方法出现异常，原因：",
					"判断Q人员身份证号码是否存在出现错误", e);
		}
	}

	@Override
	public QPersonnel updateQPersonnelBaseInfo(QPersonnel population) {
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
				PageInfoCacheThreadLocal.decrease(MemCacheConstant
						.getCachePageKey(QPersonnel.class.getSimpleName()),
						population, 1);
			}
			Countrymen temp = populationRelationService.businessOption(
					population, population.getActualPopulationType());
			contructCurrentAddress(population);
			population.setHouseId(temp.getHouseId());
			rebuildHouseAddress(population);
			return population;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类QPersonnelServiceImpl的updateQPersonnelBaseInfo方法出现异常，原因：",
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

	private void autoFillFields(QPersonnel QPersonnel) {
		autoFillOrganizationInternalCode(QPersonnel);
		autoFillChinesePinyin(QPersonnel);
		autoFillBirthday(QPersonnel);
		QPersonnel.setIdCardNo(QPersonnel.getIdCardNo().toUpperCase());
		autoIsDeath(QPersonnel);
	}

	private void autoIsDeath(QPersonnel domain) {
		if (domain.isDeath()) {
			domain.setIsEmphasis(IsEmphasis.IsNotEmphasis);
		} else {
			domain.setIsEmphasis(IsEmphasis.Emphasis);
		}
	}

	private void autoFillOrganizationInternalCode(QPersonnel QPersonnel) {
		Organization org = organizationDubboService.getSimpleOrgById(QPersonnel
				.getOrganization().getId());
		if (org == null)
			throw new BusinessValidationException("数据传入错误");
		QPersonnel.setOrgInternalCode(org.getOrgInternalCode());
	}

	private void autoFillChinesePinyin(QPersonnel QPersonnel) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(QPersonnel.getName());
		QPersonnel.setSimplePinyin(pinyin.get("simplePinyin"));
		QPersonnel.setFullPinyin(pinyin.get("fullPinyin"));
	}

	private void autoFillBirthday(QPersonnel QPersonnel) {
		if (StringUtil.isStringAvaliable(QPersonnel.getIdCardNo())) {
			QPersonnel.setBirthday(IdCardUtil.parseBirthday(QPersonnel
					.getIdCardNo()));
		}
	}

	@Override
	public QPersonnel addQPersonnelBaseInfo(QPersonnel population) {
		ValidateResult baseDataValidator = updateValidator
				.validateBaseInfo(population);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			if (checkDataExitInCache(population,
					MemCacheConstant.CACHE_ADDQPERSONNELBASEINFO,
					CACHE_ADDQPERSONNELBASEINFO_VALUE)) {
				return population;
			}
			return add(population);
		} catch (Exception e) {
			logger.error("", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(population,
					MemCacheConstant.CACHE_ADDQPERSONNELBASEINFO);
		}
	}

	private QPersonnel add(QPersonnel QPersonnel) {
		autoFillFields(QPersonnel);
		try {
			contructCurrentAddress(QPersonnel);
			Countrymen temp = populationRelationService.businessOption(
					QPersonnel, QPersonnel.getActualPopulationType());
			QPersonnel.setBaseInfoId(temp.getBaseInfoId());
			QPersonnel.setAddressInfoId(temp.getAddressInfoId());
			QPersonnel.setSourcesState(null);
			QPersonnel = qPersonnelDao.add(QPersonnel);
			populationRelationService.addPopulationRelation(temp.getId(),
					QPersonnel.getActualPopulationType(), QPersonnel.getId(),
					BaseInfoTables.QPERSONNEL_KEY);
			QPersonnel.setHouseId(temp.getHouseId());
			rebuildHouseAddress(QPersonnel);
			// 人员轨迹
			// addPersonnelTrackWhenAttentionedOrCancel(QPersonnel, null,
			// PersonnelTrackOperationType.ATTENTIONED,
			// PersonInitType.IMPORT, "");

			if (IsEmphasis.Emphasis.equals(QPersonnel.getIsEmphasis())) {
				// 缓存计数器
				PageInfoCacheThreadLocal.increment(MemCacheConstant
						.getCachePageKey(QPersonnel.class.getSimpleName()),
						QPersonnel, 1);
			}
			return QPersonnel;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类QPersonnelServiceImpl的add方法出现异常，原因：",
					"新增QPersonnel信息出现错误", e);
		}
	}

	/**
	 * 如果人口的房屋信息（CurrentAddress）不为空，并且房屋id不存在，新增一个房屋，并且建立关联关系, 如果房屋id不为空直接建立关联关系
	 * 如果房屋信息为空,并且有房屋id不为空，则删除人房关系
	 * 
	 * @param householdStaff
	 */
	private void rebuildHouseAddress(QPersonnel householdStaff) {

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
						.setHouseOperateSource(NewBaseInfoTables.QPERSONNEL_KEY);
				houseInfo = actualHouseService.addHouseInfo(houseInfo);

				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.Q_PERSONNEL, householdStaff,
						houseInfo.getId());
			} else if (householdStaff.getHouseId() != null) {
				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.Q_PERSONNEL, householdStaff,
						householdStaff.getHouseId());
			}
		} else {
			housePopulationMaintanceService.releaseHouse(
					PopulationCatalog.Q_PERSONNEL, householdStaff.getId(),
					householdStaff.getHouseId());
		}
	}

	private void addPersonnelTrackWhenAttentionedOrCancel(
			QPersonnel dangerousGoodsPractitioner,
			Organization oldPersonnelOrg, Integer operationType,
			Integer personInitType, String operationContent) {
		personnelTrackService.addPersonnelTrackWhenAttentionedOrCancel(
				dangerousGoodsPractitioner, oldPersonnelOrg,
				BaseInfoTables.QPERSONNEL_KEY, operationType, personInitType,
				operationContent);
	}

	@Override
	public QPersonnel updateQPersonnelBusiness(QPersonnel population) {
		if (!ExcelImportHelper.isImport.get()) {
			ValidateResult baseDataValidator = updateValidator
					.validateSpecializedInfo(population);
			if (baseDataValidator.hasError()) {
				throw new BusinessValidationException(
						baseDataValidator.getErrorMessages());
			}
		}
		try {

			population = qPersonnelDao.updateBusiness(population);
			PageInfoCacheThreadLocal.update(
					MemCacheConstant.getCachePageKey(QPersonnel.class),
					population, UpdateType.BUSINESS);
			return population;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类QPersonnelServiceImpl的updateQPersonnelBusiness方法出现异常，原因：",
					"修改信息出现错误", e);
		}

	}

	@Override
	public List<Long> deleteQPersonnelByIds(List<Long> personIds) {
		if (personIds == null) {
			throw new BusinessValidationException("id没有获得");
		}
		for (Long id : personIds) {
			deleteQPersonnelById(id);
		}
		return personIds;
	}

	private void deleteQPersonnelById(Long id) {

		QPersonnel domain = qPersonnelDao.get(id);
		if (null != domain) {
			log(WARN, Q_PERSONNEL, ThreadVariable.getSession().getUserName()
					+ "删除Q" + domain.getName(), OperatorType.DELETE,
					ObjectToJSON.convertJSON(domain));

			domain.setPopulationTypeBean(getPopulationRelationService()
					.getBusinessPopulationTypeBean(id,
							PopulationType.Q_PERSONNEL));
			getRecoverDatasService().deleteActualPopulation(domain);
			populationRelationService.businessDeletePopulationRelation(id,
					PopulationType.Q_PERSONNEL);

			qPersonnelDao.delete(id);
			try {
				PluginServiceHelpUtil.doService("routerService",
						"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class },
						PopulationType.Q_PERSONNEL, id);
				/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
				PluginServiceHelpUtil.doService("routerService",
						"setOrgIdAndCardNoOrName", new Class[] { Long.class,
								String.class, String.class, Long.class },
						domain.getOrganization().getId(), domain.getIdCardNo(),
						PopulationType.Q_PERSONNEL, id);
				issueTypeService.setOrgIdAndCardNoOrNameForPerson(domain
						.getOrganization().getId(), domain.getIdCardNo(),
						"qPersonnel", id);

				if (IsEmphasis.Emphasis.equals(domain.getIsEmphasis())) {
					// 缓存计数器
					PageInfoCacheThreadLocal.decrease(MemCacheConstant
							.getCachePageKey(QPersonnel.class.getSimpleName()),
							domain, 1);
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private boolean validateObjId(Long id) {
		return null == id || id < 1L;
	}

	@Override
	public QPersonnel getSimpleQPersonnelById(Long id) {
		if (validateObjId(id)) {
			throw new BusinessValidationException("获取Q时，ID不合法");
		}
		return qPersonnelDao.get(id);
	}

	@Override
	public List<QPersonnel> updateDeathByIds(List<Long> populationIds,
			boolean death) {
		List<QPersonnel> list = new ArrayList<QPersonnel>();
		for (Long id : populationIds) {
			QPersonnel population = this.getSimpleQPersonnelById(id);
			updateLogOutByPopulationTypeAndId(
					LogoutDetail.buildLogoutDetail(death),
					BaseInfoTables.QPERSONNEL_KEY, population.getId());
			baseInfoService.updateDeathStateById(population.getBaseInfoId(),
					death, population.getIdCardNo(), population
							.getOrganization().getId(), population
							.getOrgInternalCode(),
					NewBaseInfoTables.QPERSONNEL_KEY);
			list.add(population);
			// 缓存计数器
			PageInfoCacheThreadLocal.increment(MemCacheConstant
					.getCachePageKey(QPersonnel.class.getSimpleName()),
					population, 1);
		}
		return list;
	}

	@Override
	public QPersonnel getQPersonnelByIdCardNo(String idCardNo, Long id) {
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
		return qPersonnelDao.getByIdCard(list, id);
	}

	@Override
	public QPersonnel updateQPersonnelByName(String idCardNo, Long id,
			QPersonnel domain) {
		try {
			QPersonnel older = this.getQPersonnelByIdCardNo(idCardNo, id);
			domain.setId(older.getId());
			domain.setCreateDate(older.getCreateDate());
			domain.setCreateUser(older.getCreateUser());
			return updateQPersonnel(domain);
		} catch (Exception e) {
			logger.error("", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public void deleteQPersonnelByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			for (int i = 0; i < ids.length; i++) {
				deleteQPersonnelById(ids[i]);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类QPersonnelServiceImpl的deleteQPersonnelByIds方法出现异常，原因：",
					"删除Q信息出现错误", e);
		}

	}

	@Override
	public QPersonnel getQPersonnelById(Long id) {
		return getSimpleQPersonnelById(id);
	}

	@Override
	public void deleteBusinessPopulationDuplicate(Long id) {
		deleteQPersonnelById(id);
	}

}

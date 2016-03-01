package com.tianque.baseInfo.overseaPersonnel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.helper.CardNoHelper;
import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateImpl;
import com.tianque.baseInfo.familyInfo.domain.GroupFamily;
import com.tianque.baseInfo.familyInfo.service.GroupFamilyService;
import com.tianque.baseInfo.overseaPersonnel.dao.OverseaPersonnelDao;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.baseInfo.overseaPersonnel.validator.OverseaValidatorImpl;
import com.tianque.cache.PageInfoCacheThreadLocal;
import com.tianque.cache.UpdateType;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchOverseaPersonnelDao;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.SearchOverseaPersonnelVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.gis.domain.vo.PopulationVo;
import com.tianque.gis.service.SearchGisPopulationService;
import com.tianque.jms.OperateMode;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.plugin.tqSearch.sqlMap.UpdateSqlMap;
import com.tianque.service.ActualPopulationService;
import com.tianque.service.HouseInfoService;
import com.tianque.service.HousePopulationMaintanceService;
import com.tianque.service.bridge.BaseInfoDeleter;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.solr.domain.DocumentType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("overseaStaffService")
@Transactional
public class OverseaPersonnelServiceImpl extends BaseInfoPopulationTemplateImpl
		implements OverseaPersonnelService, SearchGisPopulationService,
		ActualPopulationService {

	private static final String CACHE_ADDOVERSEAPERSONNEL_VALUE = "CACHE_ADDOVERSEAPERSONNEL";

	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OverseaPersonnelDao overseaPersonnelDao;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private BaseInfoDeleter baseInfoDeleter;
	@Autowired
	private OverseaValidatorImpl overseaValidator;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ManagePopulationByHouseHelper managePopulationByHouseHelper;
	@Autowired
	private SearchOverseaPersonnelDao searchOverseaPersonnelDao;
	@Autowired
	private HouseInfoService houseInfoService;
	@Autowired
	private HousePopulationMaintanceService housePopulationMaintanceService;

	@Autowired
	private GroupFamilyService groupFamilyService;
	@Autowired
	private CacheService cacheService;

	@Resource(name = "overseaPersonnelDao")
	public void setBaseInfoPopulationBaseDao(
			OverseaPersonnelDao overseaPersonnelDao) {
		super.setBaseInfoPopulationBaseDao(overseaPersonnelDao);
	}

	@Override
	public OverseaPersonnel addOverseaPersonnel(
			OverseaPersonnel overseaPersonnel) {

		try {
			if (!ExcelImportHelper.isImport.get()) {
				ValidateResult overseaValidater = overseaValidator
						.validateSpecializedInfo(overseaPersonnel);
				if (overseaValidater.hasError()) {
					throw new BusinessValidationException(
							overseaValidater.getErrorMessages());
				} else if (hasDuplicateOverseaPersonnel(
						overseaPersonnel.getOrganization().getId(),
						overseaPersonnel.getCertificateType() == null ? null
								: overseaPersonnel.getCertificateType().getId(),
						overseaPersonnel.getCertificateNo(), overseaPersonnel
								.getId())) {
					throw new BusinessValidationException("该网格下已存在相同身份证号码");
				}
			}
			autoFillOrganizationInternalCode(overseaPersonnel);
			autoFillChinesePinyin(overseaPersonnel);

			overseaPersonnel.setLogOut(0L);
			Long houseId = overseaPersonnel.getHouseId();

			contructCurrentAddress(overseaPersonnel);
			if (checkDataExitInCache(overseaPersonnel,
					MemCacheConstant.CACHE_ADDOVERSEAPERSONNEL,
					CACHE_ADDOVERSEAPERSONNEL_VALUE)) {
				return overseaPersonnel;
			}
			overseaPersonnel = overseaPersonnelDao.add(overseaPersonnel);

			overseaPersonnel.setHouseId(houseId);

			// 人员轨迹
			// addPersonnelTrackWhenAttentionedOrCancel(overseaPersonnel, null,
			// PersonnelTrackOperationType.ATTENTIONED,
			// PersonInitType.IMPORT, "");

			rebuildHouseAddress(overseaPersonnel);
			// 缓存计数器
			PageInfoCacheThreadLocal.increment(MemCacheConstant
					.getCachePageKey(OverseaPersonnel.class.getSimpleName()),
					overseaPersonnel, 1);
			return overseaPersonnel;
		} catch (Exception e) {
			logger.error("", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			cleanDataInCache(overseaPersonnel,
					MemCacheConstant.CACHE_ADDOVERSEAPERSONNEL);
		}
	}

	@Autowired
	private SystemLogService systemLogService;

	private PageInfo<OverseaPersonnel> constructEmptyPageInfo() {
		PageInfo<OverseaPersonnel> result = new PageInfo<OverseaPersonnel>();
		result.setResult(new ArrayList<OverseaPersonnel>());
		return result;
	}

	private void autoFillOrganizationInternalCode(
			OverseaPersonnel overseaPersonnel) {
		Organization org = organizationDubboService
				.getSimpleOrgById(overseaPersonnel.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		overseaPersonnel.setOrgInternalCode(org.getOrgInternalCode());
	}

	private void autoFillChinesePinyin(OverseaPersonnel overseaPersonnel) {
		if (overseaPersonnel.getName() != null) {
			Map<String, String> map = Chinese2pinyin
					.changeChinese2Pinyin(overseaPersonnel.getName());
			overseaPersonnel.setSimplePinyin(map.get("simplePinyin"));
			overseaPersonnel.setFullPinyin(map.get("fullPinyin"));
		}
	}

	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.OVERSEAPERSONNEL_KEY)
	public List<Long> deleteForOverseaPersonnelByIds(List<Long> personIds) {
		if (personIds == null) {
			throw new BusinessValidationException("id没有获得");
		}
		for (Long id : personIds) {
			deleteOverseaPersonnelById(id);
		}
		return personIds;
	}

	private void deleteForOverseaPersonnelById(Long id) {
		OverseaPersonnel domain = getOverseaPersonnelById(id);
		if (null != domain) {
			systemLogService.log(WARN, ModuleConstants.OVERSEAPEOPLE,
					OperatorType.DELETE,
					ThreadVariable.getSession().getUserName() + "删除境外人员"
							+ domain.getEnglishName(),
					ObjectToJSON.convertJSON(domain));
			logOperate(domain.getOrganization().getId(), OVERSEAPEOPLE,
					ThreadVariable.getSession().getUserName() + "删除境外人员"
							+ domain.getEnglishName(), OperatorType.DELETE,
					BaseInfoTables.OVERSEAPERSONNEL_KEY,
					ObjectToJSON.convertJSON(domain));
			overseaPersonnelDao.deleteOverseaPersonnelById(id);

			groupFamilyService
					.deleteGroupFamilyByPopulationIdAndPopulationTypeAndIdCardNo(
							domain.getId(),
							BaseInfoTables.OVERSEAPERSONNEL_KEY,
							domain.getCertificateNo());

		}
	}

	public void deleteOverseaPersonnelByIds(Long[] ids) {
		if (ids == null) {
			throw new BusinessValidationException("id没有获得");
		}
		for (Long id : ids) {
			deleteOverseaPersonnelById(id);
		}
	}

	private void deleteOverseaPersonnelById(Long id) {
		OverseaPersonnel domain = getOverseaPersonnelById(id);
		if (null != domain) {
			systemLogService.log(WARN, ModuleConstants.OVERSEAPEOPLE,
					OperatorType.DELETE,
					ThreadVariable.getSession().getUserName() + "删除境外人员"
							+ domain.getEnglishName(),
					ObjectToJSON.convertJSON(domain));
			logOperate(domain.getOrganization().getId(), OVERSEAPEOPLE,
					ThreadVariable.getSession().getUserName() + "删除境外人员"
							+ domain.getEnglishName(), OperatorType.DELETE,
					BaseInfoTables.OVERSEAPERSONNEL_KEY,
					ObjectToJSON.convertJSON(domain));

			managePopulationByHouseHelper.releasePopulationBindedHouses(
					PopulationCatalog.OVERSEA_POPULATION, id);

			overseaPersonnelDao.deleteOverseaPersonnelById(id);

			groupFamilyService
					.deleteGroupFamilyByPopulationIdAndPopulationTypeAndIdCardNo(
							domain.getId(),
							BaseInfoTables.OVERSEAPERSONNEL_KEY,
							domain.getCertificateNo());

			if (IsEmphasis.Emphasis.equals(domain.getLogOut())) {
				// 缓存计数器
				PageInfoCacheThreadLocal.decrease(
						MemCacheConstant.getCachePageKey(OverseaPersonnel.class
								.getSimpleName()), domain, 1);
			}
		}
	}

	@Override
	@Transactional(readOnly = true)
	public PageInfo<OverseaPersonnel> findOverseaPersonnelForListPage(
			Long orgId, int pageNum, int pageSize, String sortField,
			String order, Long isEmphasis) {
		if (orgId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return constructEmptyPageInfo();
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("orgInternalCode", org.getOrgInternalCode());
				map.put("sortField", sortField);
				map.put("order", order);
				map.put("logOut", isEmphasis);
				return overseaPersonnelDao.findPagerUsingCacheBySearchVo(orgId,
						map, pageNum, pageSize, "OverseaPersonnelForListPage",
						MemCacheConstant.getCachePageKey(OverseaPersonnel.class
								.getSimpleName()));

				// return overseaPersonnelDao
				// .findOverseaPersonnelForListPageByOrgInternalCode(
				// org.getOrgInternalCode(), pageNum, pageSize,
				// sortField, order, isEmphasis);
			}
		}
	}

	@Override
	@Transactional(readOnly = true)
	public OverseaPersonnel getOverseaPersonnelById(Long id) {
		if (id == null) {
			return null;
		}
		OverseaPersonnel result = overseaPersonnelDao
				.getOverseaPersonnelById(id);
		managePopulationByHouseHelper.loadLivingHouseIfNecc(
				PopulationCatalog.OVERSEA_POPULATION, result);
		return result;

	}

	@Override
	public OverseaPersonnel getOverseaPersonnelByCertificateNoAndOrganizationId(
			String CertificateNo, Long organizationId) {
		if (CertificateNo == null || organizationId == null) {
			throw new BusinessValidationException("参数输入有误");
		}
		return overseaPersonnelDao
				.getOverseaPersonnelByCertificateNoAndOrganizationId(
						CertificateNo, organizationId);
	}

	@Override
	public OverseaPersonnel getOverseaPersonnelByIdCardNo(String idCardNo,
			Long orgId) {
		return getOverseaPersonnelByCertificateNoAndOrganizationId(idCardNo,
				orgId);
	}

	@Override
	public boolean hasDuplicateOverseaPersonnel(Long orgId,
			Long certificateType, String certificateNo, Long exceptedId) {
		// List<String> list = new ArrayList<String>();
		// list.add(certificateNo);
		// if(certificateNo.length() == 18) {
		// list.add(IdCardUtil.idCardNo18to15(certificateNo));
		// }else if(certificateNo.length() == 15){
		// list.add(IdCardUtil.idCardNo15to18(certificateNo, "19"));
		// list.add(IdCardUtil.idCardNo15to18(certificateNo, "20"));
		// }
		OverseaPersonnel exsited = overseaPersonnelDao
				.getOverseaPersonnelByCertificate(certificateType,
						certificateNo, orgId);

		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	public OverseaPersonnel hasDuplicateOverseaPersonnel(Long orgId,
			Long certificateType, String certificateNo) {
		OverseaPersonnel exsited = overseaPersonnelDao
				.getOverseaPersonnelByCertificate(certificateType,
						certificateNo, orgId);

		return exsited;
	}

	@Override
	public boolean hasRelatePersons(List<Long> personIds) {
		if (personIds == null) {
			throw new BusinessValidationException("id没有获得");
		}
		List<Long> exitPersonnelIdsList = baseInfoDeleter.getRelatepersonId(
				personIds, DocumentType.OVERSEAPERSONNEL.toString());
		if (exitPersonnelIdsList != null && exitPersonnelIdsList.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	@SolrServerIndex(mode = OperateMode.EDIT, value = UpdateSqlMap.OVERSEAPERSONNEL_KEY)
	public OverseaPersonnel updateOverseaPersonnel(
			OverseaPersonnel overseaPersonnel) {
		autoFillOrganizationInternalCode(overseaPersonnel);
		autoFillChinesePinyin(overseaPersonnel);
		Long houseId = overseaPersonnel.getHouseId();

		contructCurrentAddress(overseaPersonnel);

		overseaPersonnel = overseaPersonnelDao.update(overseaPersonnel);

		overseaPersonnel.setHouseId(houseId);

		groupFamilyService.updateGroupFamilyByObj(
				parseToGroupFamilyByOverseaPersonnel(overseaPersonnel),
				overseaPersonnel.getId(), BaseInfoTables.OVERSEAPERSONNEL_KEY,
				false);

		rebuildHouseAddress(overseaPersonnel);

		PageInfoCacheThreadLocal.update(MemCacheConstant
				.getCachePageKey(OverseaPersonnel.class.getSimpleName()),
				overseaPersonnel, UpdateType.BUSINESS);

		return overseaPersonnel;
	}

	private GroupFamily parseToGroupFamilyByOverseaPersonnel(
			OverseaPersonnel overseaPersonnel) {
		GroupFamily groupFamily = new GroupFamily();
		groupFamily.setFamilyMaster(overseaPersonnel.getEnglishName());
		groupFamily.setMasterCardNo(overseaPersonnel.getCertificateNo());
		groupFamily.setMasterMobileNumber(overseaPersonnel.getMobileNumber());
		groupFamily.setMasterTelephone(overseaPersonnel.getTelephone());
		return groupFamily;
	}

	@Override
	public OverseaPersonnel updateOverseaPersonnelByCertificate(
			Long certificateType, String certificateNo, Long orgId,
			OverseaPersonnel domain) {
		try {
			OverseaPersonnel older = this
					.getOverseaPersonnelByCertificateAndOrganizationId(
							certificateType, certificateNo, orgId);
			domain.setId(older.getId());
			domain.setCreateDate(older.getCreateDate());
			domain.setCreateUser(older.getCreateUser());
			domain.setOrganization(older.getOrganization());
			domain.setOrgInternalCode(older.getOrgInternalCode());
			if (domain.getLogOut() == null) {
				domain.setLogOut(0L);
			}
			return updateOverseaPersonnel(domain);
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
	public OverseaPersonnel updateOverseaPersonnelById(
			OverseaPersonnel overseaPersonnel) {
		overseaPersonnel = updateOverseaPersonnel(overseaPersonnel);
		return overseaPersonnel;

	}

	@Override
	public OverseaPersonnel getOverseaPersonnelByCertificateAndOrganizationId(
			Long certificateType, String certificateNo, Long organizationId) {
		// fateson update certificateType == null 可以非必填
		if (certificateNo == null || organizationId == null) {
			throw new BusinessValidationException("参数输入有误");
		}
		return overseaPersonnelDao.getOverseaPersonnelByCertificate(
				certificateType, certificateNo, organizationId);
	}

	@Override
	public PageInfo<OverseaPersonnel> searchOverseaPersonnel(
			SearchOverseaPersonnelVo searchOverseaPersonnelVo, int pageNum,
			int pageSize, String sortField, String order) {

		return searchOverseaPersonnelDao.searchOverseaPersonnel(
				searchOverseaPersonnelVo, pageNum, pageSize, sortField, order);
	}

	@Override
	public List<OverseaPersonnel> findOverseaPersonnelByNameAndPinyinAndOrgInternalCode(
			String name, String orgInternalCode) {
		return searchOverseaPersonnelDao
				.findOverseaPersonnelByNameAndPinyinAndOrgInternalCode(name,
						orgInternalCode);
	}

	@Override
	public PageInfo<OverseaPersonnel> fastSearchOverseaPersonnel(
			SearchOverseaPersonnelVo overseaPersonnelVo, int pageNum,
			int pageSize, String sortField, String order) {
		return searchOverseaPersonnelDao.fastSearchOverseaPersonnel(
				overseaPersonnelVo, pageNum, pageSize, sortField, order);
	}

	@Override
	public List<OverseaPersonnel> searchOverseaPersonnelForExport(
			SearchOverseaPersonnelVo overseaPersonnelCondition, Integer page,
			Integer rows, String sidx, String sord) {
		List<OverseaPersonnel> overperList = searchOverseaPersonnelDao
				.searchOverseaPersonnelForExport(overseaPersonnelCondition,
						page, rows, sidx, sord);
		for (OverseaPersonnel op : overperList) {
			if (op.getIsHaveHouse() == null) {
				continue;
			}

			if (op.getIsHaveHouse()) {
				op.setHouseId(managePopulationByHouseHelper
						.getPopulationLivingHouseId(
								PopulationCatalog.OVERSEA_POPULATION,
								op.getId()));
				op.setHouseCode(houseInfoService.getSimpleHouseInfoById(
						op.getHouseId()).getHouseCode());
			}
		}
		return overperList;
	}

	@Override
	public List<PopulationVo> findPopulationByPersonId(Long personId) {
		if (personId == null || personId <= 0L) {
			throw new BusinessValidationException("gis人口详细获取失败");
		} else {
			OverseaPersonnel overseaPersonnel = overseaPersonnelDao
					.findGisOverseaStaffById(personId);
			return exchangeOverseaStaffToPopulationVo(overseaPersonnel);
		}
	}

	private List<PopulationVo> exchangeOverseaStaffToPopulationVo(
			OverseaPersonnel overseaPersonnel) {
		PopulationVo populationVo = new PopulationVo();
		populationVo.setAddress(overseaPersonnel.getCurrentAddress());
		populationVo.setName(overseaPersonnel.getEnglishName());
		populationVo.setGenderName(getPropertyDictText(PropertyTypes.GENDER,
				overseaPersonnel.getGender().getId()));
		populationVo.setCertificateType(getPropertyDictText(
				PropertyTypes.CERTIFICATE_TYPE, overseaPersonnel
						.getCertificateType().getId()));
		populationVo.setIdCardNo(overseaPersonnel.getCertificateNo());
		populationVo.setPersonId(overseaPersonnel.getId());
		populationVo.setImgUrl(overseaPersonnel.getImgUrl());
		populationVo.setActulType(PopulationType
				.getCnameByActualType(overseaPersonnel.getActualType()));
		populationVo.setKeyPersonType(overseaPersonnel.getActualType());
		List<PopulationVo> populationVos = new ArrayList<PopulationVo>();
		populationVos.add(populationVo);

		return populationVos;
	}

	private String getPropertyDictText(String domainName, Long id) {
		if (null == id) {
			return "";
		} else {
			PropertyDict dict = propertyDictService
					.getPropertyDictById(id);
			return dict == null ? "" : dict.getDisplayName();
		}
	}

	@Override
	public PageInfo<PopulationVo> findPopulationsByOrgId(Long orgId,
			Integer page, Integer rows, String sidx, String sord) {
		if (orgId == null) {
			return new PageInfo<PopulationVo>();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			PageInfo<OverseaPersonnel> pageInfo = overseaPersonnelDao
					.findOVerseaPersonnelByOrgInternalCodeForGis(
							org.getOrgInternalCode(), page, rows, sord, sidx);
			List<OverseaPersonnel> overseaPersonnels = pageInfo.getResult();
			return exchangeOverseaPsersonelToPopulationVoPageInfo(pageInfo,
					overseaPersonnels);
		}

	}

	private PageInfo<PopulationVo> exchangeOverseaPsersonelToPopulationVoPageInfo(
			PageInfo<OverseaPersonnel> pageInfo,
			List<OverseaPersonnel> overseaPersonnels) {
		List<PopulationVo> populationVos = new ArrayList<PopulationVo>();
		shiftViewOverseaPersonelToPopulationVo(populationVos, overseaPersonnels);
		PageInfo<PopulationVo> popuInfo = new PageInfo<PopulationVo>();
		popuInfo.setResult(populationVos);
		popuInfo.setCurrentPage(pageInfo.getCurrentPage());
		popuInfo.setPerPageSize(pageInfo.getPerPageSize());
		popuInfo.setTotalRowSize(pageInfo.getTotalRowSize());
		return popuInfo;
	}

	private void shiftViewOverseaPersonelToPopulationVo(
			List<PopulationVo> populationVos,
			List<OverseaPersonnel> overseaPersonnels) {
		for (OverseaPersonnel overseaStaff : overseaPersonnels) {
			populationVos
					.add(shiftGisPopulationFromOverseaPerson(overseaStaff));
		}
	}

	private PopulationVo shiftGisPopulationFromOverseaPerson(
			OverseaPersonnel overseaStaff) {
		PopulationVo populationVo = new PopulationVo();
		if (overseaStaff.getIsHaveHouse() != null
				&& overseaStaff.getIsHaveHouse()) {
			managePopulationByHouseHelper.loadLivingHouseIfNecc(
					PopulationCatalog.OVERSEA_POPULATION, overseaStaff);
			if (overseaStaff.getHouseId() != null) {
				HouseInfo houseInfo = actualHouseService
						.getHouseInfoById(overseaStaff.getHouseId());
				populationVo.setHouseId(overseaStaff.getHouseId());
				populationVo.setOrgId(houseInfo.getOrganization().getId());
				populationVo.setAddress(houseInfo.getAddress());
				if (null != houseInfo && null != houseInfo.getGisInfo()) {
					populationVo.setGisInfo(houseInfo.getGisInfo());
					populationVo.setEnableBind(true);
				} else {
					populationVo.setEnableBind(false);
				}
			}
		} else {
			populationVo.setEnableBind(false);
			if (null != overseaStaff.getNoHouseReason()) {
				populationVo.setNoHouseReason(overseaStaff.getNoHouseReason());
			} else {
				populationVo.setNoHouseReason("暂未填写原因");
			}
		}
		populationVo.setKeyPersonType(PopulationType.OVERSEA_STAFF);
		populationVo.setIsHaveHouse(overseaStaff.getIsHaveHouse());
		populationVo.setGender(overseaStaff.getGender());
		populationVo.setGenderName(getPropertyDictText(PropertyTypes.GENDER,
				overseaStaff.getGender().getId()));
		populationVo.setIdCardNo(overseaStaff.getCertificateNo());
		populationVo.setCertificateType(getPropertyDictText(
				PropertyTypes.CERTIFICATE_TYPE, overseaStaff
						.getCertificateType().getId()));
		populationVo.setImgUrl(overseaStaff.getImgUrl());
		populationVo.setName(overseaStaff.getEnglishName());
		populationVo.setPersonId(overseaStaff.getId());
		return populationVo;
	}

	@Override
	public PageInfo<PopulationVo> getFurtherStepGisPopulationInfoByPersonType(
			Long orgId, String personType, String queryStr, Integer page,
			Integer rows, String sidx, String sord) {
		return null;
	}

	@Override
	public PageInfo<PopulationVo> getFurtherStepGisPopulationInfoByPersonTypeList(
			Long orgId, List personType, String queryStr, Integer page,
			Integer rows, String sidx, String sord) {
		return null;
	}

	@Override
	public ActualPopulation getActualPopulationById(Long id) {
		return null;
	}

	@Override
	public ActualPopulation getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(
			Long excludePopulationId, Long orgId, String idCardNo) {
		return null;
	}

	@Override
	public List<ActualPopulation> getActualPopulationByOrgIdAndIdCardNoExcludePopulationIdList(
			Long excludePopulationId, Long orgId, String idCardNo) {
		return null;
	}

	@Override
	public ActualPopulation getActualPopulationHouseId(
			ActualPopulation actualPopulation) {
		return null;
	}

	@Override
	public void syncActualPopulationByDeleteHousePopulationRela(
			Long populationId, Boolean isHaveHouse, String noHouseReason) {
		OverseaPersonnel overseaPersonnel = this.overseaPersonnelDao
				.getOverseaPersonnelById(populationId);
		overseaPersonnel.setIsHaveHouse(isHaveHouse);
		if (!StringUtil.isStringAvaliable(overseaPersonnel.getNoHouseReason()))
			overseaPersonnel.setNoHouseReason(noHouseReason);

		emptyAddress(overseaPersonnel);
		this.overseaPersonnelDao.updateOverseaPersonnel(overseaPersonnel);
	}

	@Override
	public void updateOverseaPersonnelHouseInfo(Long populationId,
			Boolean isHaveHouse, String noHouseReason) {
		OverseaPersonnel overseaPersonnel = this.overseaPersonnelDao
				.getOverseaPersonnelById(populationId);
		overseaPersonnel.setIsHaveHouse(isHaveHouse);
		if (!StringUtil.isStringAvaliable(overseaPersonnel.getNoHouseReason()))
			overseaPersonnel.setNoHouseReason(noHouseReason);

		emptyAddress(overseaPersonnel);
		this.overseaPersonnelDao.updateOverseaPersonnel(overseaPersonnel);
	}

	private void emptyAddress(OverseaPersonnel overseaPersonnel) {
		overseaPersonnel.setCurrentAddress("");
		overseaPersonnel.setCurrentAddressType(null);
		overseaPersonnel.setCommunity("");
		overseaPersonnel.setBlock("");
		overseaPersonnel.setUnit("");
		overseaPersonnel.setRoom("");

	}

	@Override
	public void sysActualPopulationByAddHousePopulationRela(Long populationId,
			String address) {
		overseaPersonnelDao.updateActualPopulationToHasHouseState(populationId,
				address);
	}

	@Override
	public List<PopulationVo> findGisPopulationByOrgid(Long orgid) {
		if (null == orgid) {
			return new ArrayList<PopulationVo>();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgid);
			List<OverseaPersonnel> overseaPersonnels = overseaPersonnelDao
					.findAllBindingOverseaStaffByorgCodeForGis(
							PopulationCatalog.OVERSEA_POPULATION,
							org.getOrgInternalCode());
			return exchangeOverseaPsersonelToPopulationVoPageInfo(overseaPersonnels);
		}
	}

	private List<PopulationVo> exchangeOverseaPsersonelToPopulationVoPageInfo(
			List<OverseaPersonnel> overseaPersonnels) {
		List<PopulationVo> populationVos = new ArrayList<PopulationVo>();
		shiftViewOverseaPersonelToPopulationVo(populationVos, overseaPersonnels);
		return populationVos;
	}

	@Override
	public PopulationVo shiftGisPopulationById(Long id) {
		return shiftGisPopulationFromOverseaPerson(overseaPersonnelDao
				.getOverseaPersonnelById(id));
	}

	@Override
	public void sysActualPopulationByAddHousePopulationRela(Long populationId,
			HouseInfo houseInfo) {
		this.sysActualPopulationByAddHousePopulationRela(populationId,
				houseInfo.getAddress());

	}

	@Override
	public OverseaPersonnel getOverseaPersonnelByIdCardNoAndOrgId(
			String idCardNo, Long orgId) {
		return null;
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
				OverseaPersonnel people = getOverseaPersonnelById(peoperId);
				if (people == null) {
					continue;
				}
				Long currentOrgid = people.getOrganization().getId();
				people.setOrganization(organization);
				// String orgCode = people.getOrgInternalCode();
				String orgCode = people.getOrganization().getOrgInternalCode();
				Long certificateType = people.getCertificateType().getId();
				String idCardNo = people.getCertificateNo();
				// 验证目标组织机构是否存在这个人
				boolean result = hasDuplicateOverseaPersonnel(targetOrgId,
						certificateType, idCardNo, peoperId);
				if (result) {
					// 把存在的旧数据成为修改现在现在要移动的新数据
					if (currentOrgid.longValue() != targetOrgId.longValue()) {
						updateMovePersonByIdCardNo(idCardNo, targetOrgId,
								people);
						// 删掉要移动的数据 删除掉不在同一个网格
						deleteForOverseaPersonnelById(peoperId);
					}

				} else {
					// 转移网格数据
					overseaPersonnelDao.updateData(id, targetOrgId, orgCode,
							people);
				}
				people.setCreateDate(null);
			}

		}

	}

	public void updateMovePersonByIdCardNo(String idcard, Long targetorgId,
			OverseaPersonnel people) {
		List<String> cardnos = CardNoHelper.createIdCardNo(idcard);
		// 获取
		OverseaPersonnel older = overseaPersonnelDao.getByIdCard(cardnos,
				targetorgId);
		people.setId(older.getId());
		people.setCreateDate(older.getCreateDate());
		people.setCreateUser(older.getCreateUser());
		overseaPersonnelDao.update(people);
	}

	// fateson add move end
	/**
	 * 如果人口的房屋信息（CurrentAddress）不为空，并且房屋id不存在，新增一个房屋，并且建立关联关系, 如果房屋id不为空直接建立关联关系
	 * 
	 * 如果房屋信息为空,并且有房屋id不为空，则删除人房关系
	 * 
	 * @param overseaPersonnel
	 */
	private void rebuildHouseAddress(OverseaPersonnel overseaPersonnel) {

		if (overseaPersonnel.getIsHaveHouse() != null
				&& overseaPersonnel.getIsHaveHouse()
				&& overseaPersonnel.getCurrentAddress() != null) {

			if (null == overseaPersonnel.getHouseId()
					|| overseaPersonnel.getHouseId().equals(01L)) {
				// 新增一个实有房屋,并且建立人房关系
				HouseInfo houseInfo = new HouseInfo();
				houseInfo.setAddress(overseaPersonnel.getCurrentAddress());
				houseInfo.setAddressType(propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypes.CURRENT_ADDRESS_TYPE, "其他"));
				houseInfo.setOrganization(overseaPersonnel.getOrganization());
				houseInfo
						.setHouseOperateSource(NewBaseInfoTables.OVERSEAPERSONNEL_KEY);
				houseInfo = actualHouseService.addHouseInfo(houseInfo);

				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.OVERSEA_POPULATION, overseaPersonnel,
						houseInfo.getId());
			} else if (overseaPersonnel.getHouseId() != null) {
				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.OVERSEA_POPULATION, overseaPersonnel,
						overseaPersonnel.getHouseId());
			}
		} else {
			housePopulationMaintanceService.releaseHouse(
					PopulationCatalog.OVERSEA_POPULATION,
					overseaPersonnel.getId(), overseaPersonnel.getHouseId());
		}

	}

	@Override
	public Integer getCount(SearchOverseaPersonnelVo searchOverseaPersonnelVo) {
		return searchOverseaPersonnelDao.getCount(searchOverseaPersonnelVo);
	}

	@Override
	public ActualPopulation getActualPopulationByOrgIdAndIdCardNoExcludePopulationIdIncludeLogout(
			Long excludePopulationId, Long orgId, String idCardNo) {
		return null;
	}

	// 检查缓存
	private boolean checkDataExitInCache(OverseaPersonnel overseaPersonnel,
			String cacheKey, String cacheValue) {
		String key = cacheKey + "_"
				+ overseaPersonnel.getCertificateType().getId() + "_"
				+ overseaPersonnel.getCertificateNo() + "_"
				+ overseaPersonnel.getOrganization().getId() + "_state";
		return !cacheService.add(MemCacheConstant.POPULATION_NAMESPACE, key,
				300, cacheValue);
	}

	// 清除缓存
	private void cleanDataInCache(OverseaPersonnel overseaPersonnel,
			String cacheKey) {
		cacheService.remove(MemCacheConstant.POPULATION_NAMESPACE, cacheKey
				+ "_" + overseaPersonnel.getCertificateType().getId() + "_"
				+ overseaPersonnel.getCertificateNo() + "_"
				+ overseaPersonnel.getOrganization().getId() + "_state");
	}

}

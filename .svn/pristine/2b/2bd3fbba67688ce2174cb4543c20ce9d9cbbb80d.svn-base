package com.tianque.baseInfo.householdStaff.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.base.domain.People;
import com.tianque.baseInfo.base.service.AddressInfoService;
import com.tianque.baseInfo.base.service.BaseInfoIdCardChangeProcessorService;
import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateImpl;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.baseInfo.familyInfo.dao.HouseFamilyDao;
import com.tianque.baseInfo.familyInfo.domain.GroupFamily;
import com.tianque.baseInfo.familyInfo.service.GroupFamilyService;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.baseInfo.householdStaff.commLog.CommonLog;
import com.tianque.baseInfo.householdStaff.dao.HouseholdStaffDao;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.householdStaff.shardTable.dao.HouseHoldStaffLogDao;
import com.tianque.baseInfo.householdStaff.shardTable.vo.HouseHoldStaffLogVo;
import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.baseInfo.unsettledPopulation.service.UnsettledPopulationService;
import com.tianque.baseInfo.utils.CollectionsUtil;
import com.tianque.baseInfo.utils.PopulationCopyUtil;
import com.tianque.cache.PageInfoCacheThreadLocal;
import com.tianque.cache.UpdateType;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.controller.vo.HouseholdStaffVo;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.CollectionUtil;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.PeopleHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.CensusRegisterFamily;
import com.tianque.domain.Organization;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.RelationShipWithHead;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.gis.domain.vo.PopulationVo;
import com.tianque.gis.service.SearchGisPopulationService;
import com.tianque.jms.OperateMode;
import com.tianque.jms.sender.ActiveMQMessageSender;
import com.tianque.jms.util.TransferData;
import com.tianque.mongodb.dao.HouseholdStaffMongoDao;
import com.tianque.plugin.tqSearch.domain.TqSearchResults;
import com.tianque.plugin.tqSearch.domain.TqSearchVo;
import com.tianque.plugin.tqSearch.dubboService.TqSearchService;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.plugin.tqSearch.sqlMap.UpdateSqlMap;
import com.tianque.plugin.tqSearch.syncSolrIndex.SyncPopulationSolrIndexForMQ;
import com.tianque.service.ActualBaseInfoSyncService;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.ActualPopulationService;
import com.tianque.service.CensusRegisterFamilyService;
import com.tianque.service.HouseInfoService;
import com.tianque.service.HousePopulationMaintanceService;
import com.tianque.service.PopulationTypeService;
import com.tianque.service.SyncActualPopulationToBusinessPopulationService;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.shard.util.IdConversionShardUtil;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.systemOperateLog.service.SystemOperateLogService;
import com.tianque.systemOperateLog.util.SystemOperateType;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;
import com.tianque.util.PropertyUtil;
import com.tianque.validate.AbstractCountrymenValidator;

@Service("householdStaffService")
@Transactional
public class HouseholdStaffServiceImpl extends BaseInfoPopulationTemplateImpl
		implements HouseholdStaffService, ActualPopulationService,
		SearchGisPopulationService, ActualBaseInfoSyncService,
		BaseInfoIdCardChangeProcessorService {
	private static final String NUMERAL_TYPE = "numeralType";// 数字类型
	private static final String CHARACTER_TYPE = "characterType";// 字母类型
	private static final String OTHER_TYPE = "otherType";// 其他类型
	// private static final String CACHE_ADDHOUSEHOLDSTAFF =
	// "addHouseholdStaff";
	// private static final String CACHE_ADDHOUSEHOLDSTAFFFORMOBILE =
	// "addHouseholdStaffFormobile";
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private HouseholdStaffDao householdStaffDao;
	@Autowired
	private HouseFamilyDao houseFamilyDao;
	@Autowired
	private CensusRegisterFamilyService censusRegisterFamilyService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ManagePopulationByHouseHelper managePopulationByHouseHelper;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private SyncActualPopulationToBusinessPopulationService syncActualPopulationToBusinessPopulationService;
	@Autowired
	private HouseInfoService houseInfoService;
	@Autowired
	private GroupFamilyService groupFamilyService;
	@Autowired
	protected UnsettledPopulationService unsettledPopulationService;
	@Autowired
	protected FloatingPopulationService floatingPopulationService;
	@Autowired
	private PopulationTypeService populationTypeService;
	@Autowired
	private HousePopulationMaintanceService housePopulationMaintanceService;
	@Autowired
	private BaseInfoService baseInfoService;
	@Autowired
	private AddressInfoService addressInfoService;
	@Autowired
	private SystemOperateLogService systemOperateLogService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;
	// @Autowired
	// private Datastore datastore;
	@Autowired
	private HouseholdStaffMongoDao householdStaffMongoDao;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Qualifier("householdStaffValidator")
	@Autowired
	private AbstractCountrymenValidator<HouseholdStaff> validator;

	@Autowired
	@Qualifier("peopleHelper")
	PeopleHelper peopleHelper;

	@Resource(name = "householdStaffDao")
	public void setBaseInfoPopulationBaseDao(HouseholdStaffDao householdStaffDao) {
		super.setBaseInfoPopulationBaseDao(householdStaffDao);
	}

	@Autowired
	private ValidateHelper validateHelper;

	@Autowired
	private TqSearchService tqSearchService;

	@Autowired
	private ShardConversion shardConversion;
	@Autowired
	private HouseHoldStaffLogDao houseHoldStaffLogDao;
	@Autowired
	private SyncPopulationSolrIndexForMQ syncPopulationSolrIndexForMQ;

	@Override
	public void syncActualPopulationByDeleteHousePopulationRela(
			Long populationId, Boolean isHaveHouse, String noHouseReason) {
		householdStaffDao.updateFloatingPopulationByDeleteHousePopulationRela(
				populationId, isHaveHouse, noHouseReason,
				IdConversionShardUtil.getShardCodeById(populationId));
	}

	@Override
	public ActualPopulation updateActualPopulationBaseInfo(
			ActualPopulation actualPopulation) {
		try {
			HouseholdStaff householdStaff = getHouseholdStaffById(actualPopulation
					.getId());
			String houseCode = householdStaff.getHouseCode();
			Long houseId = householdStaff.getHouseId();
			PropertyUtils.copyProperties(householdStaff, actualPopulation);
			householdStaff.setHouseId(houseId);
			householdStaff.setHouseCode(houseCode);
			return updateHouseholdStaff(householdStaff);
		} catch (Exception e) {
			throw new ServiceValidationException("修改户籍人口基本信息出错", e);
		}
	}

	public List<HouseholdStaff> findHouseholdStaffList(
			HouseholdStaffVo householdStaffVo, Long orgId, String sidx,
			String sord) {
		String shardCode = shardConversion.getShardCode(orgId);
		fillConditionTypeByFastSearchKeyWords(householdStaffVo);
		List<HouseholdStaff> hhsList = householdStaffDao
				.findHouseholdStaffList(householdStaffVo, orgId, sidx, sord,
						shardCode);
		for (HouseholdStaff householdStaff : hhsList) {
			// 现在 IsHaveHouse 为null 表示 未知
			if (householdStaff.getIsHaveHouse() == null) {
				continue;
			}
			if (householdStaff.getIsHaveHouse()) {
				householdStaff.setHouseId(managePopulationByHouseHelper
						.getPopulationLivingHouseId(
								PopulationCatalog.HOUSEHOULD_POPULATION,
								householdStaff.getId()));
				householdStaff.setHouseCode(houseInfoService
						.getSimpleHouseInfoById(householdStaff.getHouseId())
						.getHouseCode());
			}
		}
		return hhsList;
	}

	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.HOUSEHOLDSTAFF_KEY)
	public void deleteHouseholdStaffByIds(Long[] ids) {
		List<Long> householdStaffIds = new ArrayList<Long>();
		for (int i = 0; null != ids && i < ids.length; i++) {
			deleteHouseholdStaffById(ids[i]);
			householdStaffIds.add(ids[i]);
		}
	}

	private void deletePopulationByActualIdAndType(Long id) {
		HouseholdStaff householdStaff = this.householdStaffDao
				.getHouseholdStaffById(id);
		if (null != householdStaff) {
			this.syncActualPopulationToBusinessPopulationService
					.deletePopulationByOrgIdAndIdCardNo(householdStaff
							.getOrganization().getId(), householdStaff
							.getIdCardNo());
		}
	}

	public void logoutHouseholdStaff(Long id) {
		if (null == id || 0 >= id) {
			return;
		}
		HouseholdStaff householdStaff = getHouseholdStaffById(id);
		householdStaffDao.deleteHouseHoldStaff(id);
		deleteCensusRegisterFamilyById(householdStaff);
		this.deletePopulationByActualIdAndType(id);
		this.deletePopulationTypeByActualIdAndType(id,
				PopulationType.HOUSEHOLD_STAFF);
	}

	public void accountNumberInTheOrgLevelIsPresent(String accountNumber,
			Long orgId) {
		if (findHouseholdStaffByAccountNumberAndorgIdAndInternalId(
				accountNumber, orgId, OrganizationLevel.TOWN).size() > 0) {
			throw new BusinessValidationException("该户口号在同乡镇其他网格已存在!");
		}
	}

	public List<HouseholdStaff> findHouseholdStaffByAccountNumberAndorgIdAndInternalId(
			String accountNumber, Long orgId, Integer internalId) {
		String shardCode = shardConversion.getShardCode(orgId);
		Organization org = organizationDubboService
				.getOrgByOrgIdAndOrgLevelInternalId(orgId, internalId);
		return householdStaffDao
				.findHouseholdStaffByAccountNumberAndorgIdAndInternalId(
						accountNumber, org, shardCode);
	}

	public void deleteHouseholdStaffByFamilyId(Long familyId, Long orgId) {
		if (null == familyId || 0 >= familyId || null == orgId || 0 >= orgId) {
			return;
		}
		String shardCode = shardConversion.getShardCode(orgId);
		List<HouseholdStaff> list = householdStaffDao
				.findHouseholdIdByFamilyId(new Long[] { familyId }, shardCode);
		for (int i = 0; i < list.size(); i++) {
			deleteHouseholdStaffById(list.get(i).getId());
		}
	}

	public PageInfo<HouseholdStaff> findHouseholdStaffForPageByOrgId(
			HouseholdStaffVo householdStaffVo, Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		if (orgId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return constructEmptyPageInfo();
			}
			String shardCode = shardConversion.getShardCode(org
					.getDepartmentNo());
			fillConditionTypeByFastSearchKeyWords(householdStaffVo);
			PageInfo<HouseholdStaff> hhsPage = householdStaffDao
					.findHouseholdStaffForPageByOrgInternalCode(
							householdStaffVo, org.getOrgInternalCode(),
							pageNum, pageSize, sidx, sord, shardCode);
			for (HouseholdStaff hhs : hhsPage.getResult()) {
				// 获取人员信息（需要）
				managePopulationByHouseHelper.loadLivingHouseIfNecc(
						PopulationCatalog.HOUSEHOULD_POPULATION, hhs);
			}
			return hhsPage;
		}
	}

	public PageInfo<HouseholdStaff> fastFindHouseholdStaffForPageByOrgId(
			HouseholdStaffVo householdStaffVo, Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		if (orgId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null || org.getOrgInternalCode() == null) {
				return constructEmptyPageInfo();
			}
			fillConditionTypeByFastSearchKeyWords(householdStaffVo);
			PageInfo<HouseholdStaff> hhsPage = new PageInfo<HouseholdStaff>();
			String shardCode = shardConversion.getShardCode(org
					.getDepartmentNo());
			boolean isTqSearch = GridProperties.ISTQSEARCH;
			if (isTqSearch) {
				try {
					hhsPage = fastSearchFormTqSearch(
							householdStaffVo.getFastSearchKeyWords(),
							org.getOrgInternalCode(), pageNum, pageSize);
					fillMeassgesByPageInfo(hhsPage, shardCode);
					return hhsPage;
				} catch (Exception e) {
					isTqSearch = false;
					logger.error("户籍人口统一搜索快速检索失败：" + e);
				}
			}
			if (!isTqSearch) {
				hhsPage = householdStaffDao
						.fastFindHouseholdStaffForPageByOrgInternalCode(
								householdStaffVo, org.getOrgInternalCode(),
								pageNum, pageSize, sidx, sord, shardCode);
			}
			Set<Long> houseInfoIds = new HashSet<Long>();
			for (HouseholdStaff householdStaff : hhsPage.getResult()) {
				houseInfoIds.add(householdStaff.getAddressInfoId());
			}
			List<Countrymen> houseList = addressInfoService
					.getAddressInfoByIdForList(new ArrayList<Long>(houseInfoIds));
			for (HouseholdStaff hhs : hhsPage.getResult()) {
				for (Countrymen countrymen : houseList) {
					if (countrymen.getId().equals(hhs.getAddressInfoId())) {
						PopulationCopyUtil.copyAddressInfoMessage(countrymen,
								hhs);
					}
				}
				// 检索的时候（不需要）
				// managePopulationByHouseHelper.loadLivingHouseIfNecc(
				// PopulationCatalog.HOUSEHOULD_POPULATION, hhs);
			}
			return hhsPage;
		}
	}

	/**
	 * 填充检索条件类型是数字、还是拼音、还是名字
	 * 
	 * @param householdStaffVo
	 */
	private void fillConditionTypeByFastSearchKeyWords(
			HouseholdStaffVo householdStaffVo) {
		if (householdStaffVo != null) {

			if (!StringUtil.isStringAvaliable(householdStaffVo
					.getFastSearchKeyWords())) {
				householdStaffVo.setConditionType(OTHER_TYPE);
			} else if (ValidateHelper.checkNumeric(householdStaffVo
					.getFastSearchKeyWords())) {
				householdStaffVo.setConditionType(NUMERAL_TYPE);
			} else if (!ValidateHelper.illegalLetter(householdStaffVo
					.getFastSearchKeyWords())) {
				householdStaffVo.setConditionType(CHARACTER_TYPE);
			} else {
				householdStaffVo.setConditionType(OTHER_TYPE);
			}
		}
	}

	private PageInfo<HouseholdStaff> fastSearchFormTqSearch(
			String fastSearchKeyWords, String orgInternalCode, Integer pageNum,
			Integer pageSize) {
		PageInfo<HouseholdStaff> hhsPage = new PageInfo<HouseholdStaff>();
		TqSearchVo tqSearchVo = new TqSearchVo();
		tqSearchVo.setPage(pageNum);
		tqSearchVo.setRows(pageSize);
		tqSearchVo.setType("populationFastSearch");
		tqSearchVo.getSearchs().put("orgInternalCode", orgInternalCode + "*");
		tqSearchVo.getSearchs().put("dataType", "householdStaff");
		tqSearchVo.getSearchs().put("logOut", 0);
		String sql = "idCardNo:?* OR name:?* OR simplepinyin:?* OR fullpinyin:?*";
		sql = sql.replaceAll("\\?", fastSearchKeyWords);
		tqSearchVo.setSql(sql);
		TqSearchResults tqSearchResults = tqSearchService
				.queryForResult(tqSearchVo);
		hhsPage = new PageInfo(tqSearchResults.getPageNum(),
				tqSearchResults.getPageSize(),
				(int) tqSearchResults.getCountNum(),
				convertHouseholdStaffList(tqSearchResults.getResult()));
		return hhsPage;
	}

	private List<HouseholdStaff> convertHouseholdStaffList(
			List<Map<String, Object>> valueMaps) {
		List<HouseholdStaff> householdStaffs = new ArrayList<HouseholdStaff>();
		for (Map<String, Object> valueMap : valueMaps) {
			householdStaffs.add(convertHouseholdStaff(valueMap));
		}
		return householdStaffs;
	}

	private HouseholdStaff convertHouseholdStaff(Map<String, Object> map) {
		HouseholdStaff householdStaff = new HouseholdStaff();
		householdStaff.setId(convertLong(map.get("dataId")));
		householdStaff.setBaseInfoId(convertLong(map.get("baseinfoId")));
		householdStaff.setAddressInfoId(convertLong(map.get("addressinfoId")));
		return householdStaff;
	}

	public PageInfo<HouseholdStaff> findHouseholdStaffByOrgIdDefaultList(
			HouseholdStaffVo householdStaffVo, Long orgId, Integer pageNum,
			Integer pageSize, final String sidx, final String sord) {
		if (orgId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return constructEmptyPageInfo();
			}
			String shardCode = shardConversion.getShardCode(org
					.getDepartmentNo());
			Map<String, Object> query = new HashMap<String, Object>();
			query.put("orgInternalCode", org.getOrgInternalCode());
			query.put("logOut", householdStaffVo.getLogout());
			query.put("isDeath", householdStaffVo.getIsDeath());
			query.put("sortField", "id");
			query.put("order", "desc");
			query.put("shardCode", shardCode);
			PageInfo<HouseholdStaff> hhsPage = householdStaffDao
					.findPagerUsingCacheBySearchVo(orgId, query, pageNum,
							pageSize, "HouseholdStaffDefaultList",
							MemCacheConstant
									.getCachePageKey(HouseholdStaff.class
											.getSimpleName()));
			// PageInfo<HouseholdStaff> hhsPage = householdStaffDao
			// .findHouseholdStaffByOrgIdDefaultList(householdStaffVo,
			// org.getOrgInternalCode(), pageNum, pageSize,
			// "id", "desc");
			fillMeassgesByPageInfo(hhsPage, null);
			new CollectionsUtil<HouseholdStaff>().sortList(hhsPage.getResult(),
					sord, sidx);
			return hhsPage;
		}
	}

	private void fillMeassgesByPageInfo(PageInfo<HouseholdStaff> hhsPage,
			String shardCode) {
		if (hhsPage.getResult() == null || hhsPage.getResult().size() < 1) {
			return;
		}
		Set<Long> baseInfoIds = new HashSet<Long>();
		Set<Long> houseInfoIds = new HashSet<Long>();
		List<Long> householdstaffIds = new ArrayList<Long>();
		for (HouseholdStaff householdStaff : hhsPage.getResult()) {
			houseInfoIds.add(householdStaff.getAddressInfoId());
			baseInfoIds.add(householdStaff.getBaseInfoId());
			householdstaffIds.add(householdStaff.getId());
		}

		List<Countrymen> houseList = addressInfoService
				.getAddressInfoByIdForList(new ArrayList<Long>(houseInfoIds));
		List<Countrymen> baseInfoList = baseInfoService
				.getBaseInfoByIdForList(new ArrayList<Long>(baseInfoIds));
		List<HouseholdStaff> householdStaffList = new ArrayList<HouseholdStaff>();
		if (shardCode != null) {
			householdStaffList = findHouseholdStaffByIds(householdstaffIds,
					shardCode);
		}
		List<HouseholdStaff> notExisted = new ArrayList<HouseholdStaff>();
		for (HouseholdStaff householdStaff : hhsPage.getResult()) {
			for (Countrymen countrymen : houseList) {
				if (countrymen.getId()
						.equals(householdStaff.getAddressInfoId())) {
					PopulationCopyUtil.copyAddressInfoMessage(countrymen,
							householdStaff);
				}
			}
			for (Countrymen countrymen : baseInfoList) {
				if (countrymen.getId().equals(householdStaff.getBaseInfoId())) {
					PopulationCopyUtil.copyBaseInfoMessage(countrymen,
							householdStaff);
				}
			}
			if (shardCode != null) {
				boolean isExisted = false;
				for (HouseholdStaff countrymen : householdStaffList) {
					if (countrymen.getId().equals(householdStaff.getId())) {
						PopulationCopyUtil.copyHouseholdStaffMessage(
								countrymen, householdStaff);
						isExisted = true;
					}
				}
				if (!isExisted) {
					notExisted.add(householdStaff);
				}
			}
			// 默认列表查询的时候（不需要）
			// managePopulationByHouseHelper.loadLivingHouseIfNecc(
			// PopulationCatalog.HOUSEHOULD_POPULATION,
			// householdStaff);
		}
		hhsPage.getResult().removeAll(notExisted);
		hhsPage.setTotalRowSize(hhsPage.getTotalRowSize() - notExisted.size());
	}

	public PageInfo<HouseholdStaff> findHouseholdStaffByOrgIdDefaultListTest(
			HouseholdStaffVo householdStaffVo, Long orgId, Integer pageNum,
			Integer pageSize, final String sidx, final String sord,
			CommonLog commonLog) {
		long start = System.currentTimeMillis();
		if (orgId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return constructEmptyPageInfo();
			}
			String shardCode = shardConversion.getShardCode(org
					.getDepartmentNo());
			PageInfo<HouseholdStaff> hhsPage = householdStaffDao
					.findHouseholdStaffByOrgIdDefaultListTest(householdStaffVo,
							org.getOrgInternalCode(), pageNum, pageSize, "id",
							"desc", commonLog, shardCode);

			Set<Long> baseInfoIds = new HashSet<Long>();
			Set<Long> houseInfoIds = new HashSet<Long>();
			for (HouseholdStaff householdStaff : hhsPage.getResult()) {
				houseInfoIds.add(householdStaff.getAddressInfoId());
				baseInfoIds.add(householdStaff.getBaseInfoId());
			}

			List<Countrymen> houseList = addressInfoService
					.getAddressInfoByIdForList(new ArrayList<Long>(houseInfoIds));
			List<Countrymen> baseInfoList = baseInfoService
					.getBaseInfoByIdForList(new ArrayList<Long>(baseInfoIds));
			for (HouseholdStaff householdStaff : hhsPage.getResult()) {
				for (Countrymen countrymen : houseList) {
					if (countrymen.getId().equals(
							householdStaff.getAddressInfoId())) {
						PopulationCopyUtil.copyAddressInfoMessage(countrymen,
								householdStaff);
					}
				}
				for (Countrymen countrymen : baseInfoList) {
					if (countrymen.getId().equals(
							householdStaff.getBaseInfoId())) {
						PopulationCopyUtil.copyBaseInfoMessage(countrymen,
								householdStaff);
					}
				}
				// managePopulationByHouseHelper
				// .loadLivingHouseIfNecc(
				// PopulationCatalog.HOUSEHOULD_POPULATION,
				// householdStaff);
			}
			Collections.sort(hhsPage.getResult(),
					new Comparator<HouseholdStaff>() {
						@Override
						public int compare(HouseholdStaff o1, HouseholdStaff o2) {
							if ("asc".equals(sord.toLowerCase())) {
								Object obj = invokeMemthod(o1);
								if (obj instanceof Long) {
									Object objTemp = invokeMemthod(o2);
									if (objTemp instanceof Long) {
										return (Long) obj > (Long) objTemp ? 1
												: -1;
									} else {
										return 1;
									}
								} else {
									return obj.toString().compareTo(
											invokeMemthod(o2).toString());
								}
							} else {
								Object obj = invokeMemthod(o2);
								if (obj instanceof Long) {
									Object objTemp = invokeMemthod(o1);
									if (objTemp instanceof Long) {
										return (Long) obj > (Long) objTemp ? 1
												: -1;
									} else {
										return 1;
									}
								} else {
									return obj.toString().compareTo(
											invokeMemthod(o1).toString());
								}
							}
						}

						private Object invokeMemthod(Object arg0) {
							Object obj = null;
							try {
								obj = arg0
										.getClass()
										.getMethod(
												"get"
														+ (sidx.charAt(0) + "")
																.toUpperCase()
														+ sidx.substring(1))
										.invoke(arg0);
							} catch (Exception e) {
								logger.error("HouseholdStaffServiceImpl", e);
							}
							if (obj instanceof PropertyDict) {
								obj = ((PropertyDict) obj).getId();
							}
							if (obj instanceof Date) {
								obj = ((Date) obj).getTime();
							}
							return obj == null ? 0 : obj;
						}
					});
			commonLog.setServiceTime(System.currentTimeMillis() - start);
			return hhsPage;
		}
	}

	public PageInfo<HouseholdStaff> findHouseholdStaffForPageByOrgIdAndId(
			HouseholdStaffVo householdStaffVo, Long orgId, Long id,
			Boolean exceptHead, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (orgId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return constructEmptyPageInfo();
			}
			String houseHold = null;
			if (exceptHead != null && exceptHead == true) {
				houseHold = getHouseHold();
			}
			String shardCode = shardConversion.getShardCode(org
					.getDepartmentNo());
			PageInfo<HouseholdStaff> hhsPage = householdStaffDao
					.findHouseholdStaffForPageByOrgInternalCodeAndId(
							householdStaffVo, org.getOrgInternalCode(), id,
							houseHold, pageNum, pageSize, sidx, sord, shardCode);
			// 查看人员信息-户籍信息tab页【现有成员】时（不需要）
			// for (HouseholdStaff hhs : hhsPage.getResult()) {
			// managePopulationByHouseHelper.loadLivingHouseIfNecc(
			// PopulationCatalog.HOUSEHOULD_POPULATION, hhs);
			// }
			return hhsPage;
		}
	}

	private String getHouseHold() {
		String houseHold;
		List<PropertyDict> propertyDicts = findPropertyDictsForHead();
		houseHold = "";
		for (PropertyDict propertyDict : propertyDicts) {
			houseHold += propertyDict.getId() + ",";
		}
		houseHold = houseHold.substring(0, houseHold.length() - 1);
		return houseHold;
	}

	private PageInfo<HouseholdStaff> constructEmptyPageInfo() {
		PageInfo<HouseholdStaff> result = new PageInfo<HouseholdStaff>();
		result.setResult(new ArrayList<HouseholdStaff>());
		return result;
	}

	@Override
	public void deleteHouseholdStaffById(Long id) {
		HouseholdStaff householdStaff = getHouseholdStaffById(id);
		if (householdStaff == null) {
			return;
		}
		CensusRegisterFamily family = getCensusRegisterFamilyByOrgIdAndAccountNumber(
				householdStaff.getAccountNumber(), householdStaff
						.getOrganization().getId());
		if (householdStaff.getRelationShipWithHead() != null
				&& propertyDictService.getPropertyDictById(
						householdStaff.getRelationShipWithHead().getId())
						.getInternalId() == RelationShipWithHead.HEADER) {
			if (null != family) {
				emptyCensusRegisterFamily(family);
			}
		}
		householdStaff.setPopulationTypes(getPopulationRelationService()
				.getActualPopulationTypeBeans(householdStaff.getId(),
						BaseInfoTables.HOUSEHOLDSTAFF_KEY));
		getRecoverDatasService().deleteActualPopulation(householdStaff);
		getPopulationRelationService().actualPopulationDel(id,
				BaseInfoTables.HOUSEHOLDSTAFF_KEY);
		getPopulationRelationService().actualDeletePopulationRelation(id,
				BaseInfoTables.HOUSEHOLDSTAFF_KEY);
		addressInfoService.delete(householdStaff.getAddressInfoId());

		managePopulationByHouseHelper.releasePopulationBindedHouses(
				PopulationCatalog.HOUSEHOULD_POPULATION, id);

		householdStaffDao.deleteHouseHoldStaff(id);

		groupFamilyService
				.deleteGroupFamilyByPopulationIdAndPopulationTypeAndIdCardNo(
						householdStaff.getId(),
						BaseInfoTables.HOUSEHOLDSTAFF_KEY,
						householdStaff.getIdCardNo());
		if (IsEmphasis.Emphasis.equals(householdStaff.getLogOut())) {
			PageInfoCacheThreadLocal.decrease(
					MemCacheConstant.getCachePageKey(householdStaff.getClass()
							.getSimpleName()), householdStaff, 1);
		}
		/*
		 * List<HouseholdStaff> householdStaffs = householdStaffDao
		 * .findHouseholdStaffByAccountNumberAndOrgId(householdStaff
		 * .getAccountNumber(), householdStaff.getOrganization() .getId()); if
		 * (null == householdStaffs || householdStaffs.size() == 0) {
		 * regrantFamilyHouse(householdStaff, null);
		 * censusRegisterFamilyService.deleteCensusRegisterFamilyById(family
		 * .getId()); }
		 */
	}

	private void deleteCensusRegisterFamilyById(HouseholdStaff householdStaff) {
		if (householdStaff != null
				&& getPropertyDictById(
						householdStaff.getRelationShipWithHead().getId())
						.getInternalId() == RelationShipWithHead.HEADER
				&& householdStaff.getCensusRegisterFamily() != null) {
			censusRegisterFamilyService.deleteCensusRegisterFamilyById(
					householdStaff.getCensusRegisterFamily().getId(),
					householdStaff.getOrganization().getId());
		}
	}

	public HouseholdStaff updateHouseholdStaff(HouseholdStaff householdStaff) {
		try {
			householdStaff.setIdCardNo(householdStaff.getIdCardNo()
					.toUpperCase());
			if (!ExcelImportHelper.isImport.get()) {
				checkUpdateHouseholdStaff(householdStaff);
			}
			autoFillProperty(householdStaff);
			bindHouse(householdStaff);
			validateBaseInfo(householdStaff);
			autoFillPropertyWhenAdd(householdStaff);
			contructCurrentAddress(householdStaff);
			rebuildHouseAddress(householdStaff);
			householdStaff.setShardCode(shardConversion
					.getShardCode(householdStaff.getOrganization().getId()));// 设置分表code
			updateIsDeath(householdStaff);
			baseInfoService.update(householdStaff);
			maintainAddressInfo(householdStaff);
			HouseholdStaff temp = householdStaffDao
					.updateHouseholdStaff(householdStaff);
			householdStaff.setBaseInfoId(temp.getBaseInfoId());
			householdStaff.setAddressInfoId(temp.getAddressInfoId());
			householdStaff = updateHousePopulationBusinessInfo(householdStaff);
			groupFamilyService.updateGroupFamilyByObj(
					parseToGroupFamilyByHouseholdStaff(householdStaff),
					householdStaff.getId(), BaseInfoTables.HOUSEHOLDSTAFF_KEY,
					householdStaff.isDeath());

			householdStaff = householdStaffDao
					.getHouseholdStaffById(householdStaff.getId());

			// 在新增修改业务人员返回实口信息时，把新增的房屋id返回
			if (householdStaff.getIsHaveHouse() != null
					&& householdStaff.getIsHaveHouse()) {
				householdStaff.setHouseId(managePopulationByHouseHelper
						.getPopulationLivingHouseId(
								PopulationCatalog.HOUSEHOULD_POPULATION,
								householdStaff.getId()));
			}

			if (householdStaff.getAccountNumber() != null
					&& !"".equals(householdStaff.getAccountNumber())) {
				getCensusRegisterFamilyByAccountNumberAndOrgId(householdStaff);
			}
			syncPopulationSolrIndexForMQ.sendPopulationSolrIndexForMQ(
					householdStaff, BaseInfoTables.HOUSEHOLDSTAFF_KEY,
					OperateMode.EDIT);
			return householdStaff;
		} catch (Exception e) {
			logger.error("HouseholdStaffServiceImplupdateHouseholdStaff", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改信息出现错误");
			} else {
				return null;
			}
		}
	}

	private GroupFamily parseToGroupFamilyByHouseholdStaff(
			HouseholdStaff householdStaff) {
		GroupFamily groupFamily = new GroupFamily();
		groupFamily.setFamilyMaster(householdStaff.getName());
		groupFamily.setMasterCardNo(householdStaff.getIdCardNo());
		groupFamily.setMasterMobileNumber(householdStaff.getMobileNumber());
		groupFamily.setMasterTelephone(householdStaff.getTelephone());
		return groupFamily;
	}

	private void bindHouse(HouseholdStaff householdStaff) {

		if (null != householdStaff.getIsHaveHouse()
				&& householdStaff.getIsHaveHouse()
				&& householdStaff.getCurrentAddress() != null) {
			managePopulationByHouseHelper.reBindHouseIfNecc(
					PopulationCatalog.parse(BaseInfoTables.HOUSEHOLDSTAFF_KEY),
					householdStaff, householdStaff.getHouseId());
		} else {
			managePopulationByHouseHelper.releasePopulationBindedHouses(
					PopulationCatalog.parse(BaseInfoTables.HOUSEHOLDSTAFF_KEY),
					householdStaff.getId());
		}

	}

	private void autoFillProperty(HouseholdStaff householdStaff) {
		autoFillChinesePinyin(householdStaff);
		autoFillGender(householdStaff);
		householdStaff.setOrgInternalCode(organizationDubboService
				.getSimpleOrgById(householdStaff.getOrganization().getId())
				.getOrgInternalCode());
		if (householdStaff.isDeath() == true) {
			householdStaff.setLogOut(IsEmphasis.IsNotEmphasis);
		}
		householdStaff
				.setCensusRegisterFamily(getCensusRegisterFamilyByAccountNumberAndOrgId(householdStaff));
	}

	private void checkUpdateHouseholdStaff(HouseholdStaff householdStaff) {
		if (householdStaff == null) {
			throw new BusinessValidationException("HouseholdStaff不能为空!");
		}
		if (householdStaff.getId() == null) {
			throw new BusinessValidationException("householdStaff.getId()不能为空!");
		}
		if (hasDuplicateHouseholdStaff(householdStaff.getIdCardNo(),
				householdStaff.getOrganization().getId(),
				householdStaff.getId())) {
			throw new BusinessValidationException("身份证号码已存!");
		}
		HouseholdStaff staff = getHouseholdStaffById(householdStaff.getId());
		if (staff.getRelationShipWithHead() != null) {
			if (getPropertyDictById(staff.getRelationShipWithHead().getId())
					.getInternalId() != RelationShipWithHead.HEADER
					&& RelationShipWithHead.HEADER == getPropertyDictById(
							householdStaff.getRelationShipWithHead().getId())
							.getInternalId()) {
				checkedFamilyIdCardNo(
						householdStaff,
						getCensusRegisterFamilyByOrgIdAndAccountNumber(
								householdStaff.getAccountNumber(),
								householdStaff.getOrganization().getId()));
			}
		}

	}

	public HouseholdStaff getHouseholdStaffById(Long id) {
		if (null == id || 0 >= id) {
			return null;
		}
		HouseholdStaff result = householdStaffDao.getHouseholdStaffById(id);
		if (result == null) {
			throw new BusinessValidationException("根据id获取户籍人口失败");
		}
		CensusRegisterFamily censusRegisterFamily = censusRegisterFamilyService
				.getCensusRegisterFamilyByOrgIdAndAccountNumber(result
						.getAccountNumber(), result.getOrganization().getId());
		if (result != null && result.getAccountNumber() != null
				&& censusRegisterFamily != null) {
			result.setHouseMarchType(censusRegisterFamilyService
					.getHouseFamilyTypesByFamilyId(censusRegisterFamily.getId()));
		}
		// 获取人员信息（需要）
		managePopulationByHouseHelper.loadLivingHouseIfNecc(
				PopulationCatalog.HOUSEHOULD_POPULATION, result);
		return result;
	}

	public void updateAccountNumberByFamilyIdAndOrgId(String accountNumber,
			Long orgId, Long familyId) {
		if (accountNumber == null || accountNumber.trim().length() == 0
				|| orgId == null || familyId == null) {
			throw new BusinessValidationException("户口号、所属网格、户籍家庭不能为空!");
		}
		householdStaffDao.updateAccountNumberByFamilyIdAndOrgId(accountNumber,
				orgId, familyId, shardConversion.getShardCode(orgId));
	}

	public HouseholdStaff findHouseholdStaffByCardNoAndOrgId(String idCardNo,
			Long orgId) {
		if (idCardNo == null || "".equals(idCardNo.trim()) || orgId == null) {
			return null;
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
			return null;
		}
		return getHouseholdStaffByBaseInfoId(countrymen.getId(), orgId);
	}

	@Override
	public HouseholdStaff addHouseholdStaff(HouseholdStaff householdStaff) {
		String key = "";
		try {
			if (householdStaff != null && householdStaff.getIdCardNo() != null
					&& householdStaff.getOrganization() != null
					&& householdStaff.getOrganization().getId() != null) {
				key = MemCacheConstant.POPULATION_NAMESPACE
						+ MemCacheConstant.getPopulationKey(
								MemCacheConstant.HOUSEHOLDSTAFF_KEY,
								householdStaff.getIdCardNo(), householdStaff
										.getOrganization().getId());
				// String value = (String) cacheService.get(key);
				// if (value == null || "".equals(value)) {
				// cacheService.set(key, 300, "CACHE_ADDHOUSEHOLDSTAFF");
				// } else {
				// return householdStaff;
				// }
				// 使用相同的key防止手机录入和excel导入重复数据
				if (!cacheService.add(key, 300,
						NewBaseInfoTables.HOUSEHOLDSTAFF_KEY)) {
					return householdStaff;
				}
			}
			return setHouseholdStaff(householdStaff);
		} finally {
			if (!"".equals(key)) {
				cacheService.remove(key);
			}
		}
	}

	@Override
	public HouseholdStaff setHouseholdStaff(HouseholdStaff householdStaff) {
		OperateMode mode = OperateMode.IMPORT;
		if (!ExcelImportHelper.isImport.get()) {
			mode = OperateMode.ADD;
			ValidateResult baseDataValidator = validator
					.validate(householdStaff);
			if (baseDataValidator.hasError()) {
				throw new BusinessValidationException(
						baseDataValidator.getErrorMessages());
			}
		}
		ValidateResult result = new ValidateResult();
		if (householdStaff.getOrganization() != null
				&& householdStaff.getOrganization().getId() != null) {
			result = validator.validatorIdCardNoExistedMessage(householdStaff
					.getOrganization().getId(), householdStaff.getIdCardNo(),
					householdStaff.getActualPopulationType(), null, result);
			String errorMessages = result.getErrorMessages();
			if (errorMessages != null && !"".equals(errorMessages)) {
				throw new BusinessValidationException(result.getErrorMessages());
			}
		}
		try {
			HouseholdStaff temp = add(householdStaff, mode);
			householdStaff.setBaseInfoId(temp.getBaseInfoId());
			householdStaff.setAddressInfoId(temp.getAddressInfoId());
			if (householdStaff.getHouseMarchType() != null
					&& householdStaff.getHouseMarchType().size() > 0) {
				List<Long> houseMarchTypeList = new ArrayList<Long>();
				for (PropertyDict dict : householdStaff.getHouseMarchType()) {
					if (dict.getId() != null) {
						houseMarchTypeList.add(dict.getId());
					}
				}
				if (houseMarchTypeList != null
						&& houseMarchTypeList.size() != 0) {
					regrantFamilyHouse(householdStaff, houseMarchTypeList);
				}
			}
			householdStaff = updateHousePopulationBusinessInfo(householdStaff);

			syncPopulationSolrIndexForMQ.sendPopulationSolrIndexForMQ(
					householdStaff, BaseInfoTables.HOUSEHOLDSTAFF_KEY,
					OperateMode.ADD);
			return householdStaff;
		} catch (Exception e) {
			logger.error("HouseholdStaffServiceImplsetHouseholdStaff", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public HouseholdStaff addHouseholdStaffForMobile(
			HouseholdStaff householdStaff) {
		if (!ExcelImportHelper.isImport.get()) {
			ValidateResult baseDataValidator = validator
					.validate(householdStaff);
			if (baseDataValidator.hasError()) {
				throw new BusinessValidationException(
						baseDataValidator.getErrorMessages());
			}
		}
		ValidateResult result = new ValidateResult();
		String key = "";
		if (householdStaff != null && householdStaff.getIdCardNo() != null
				&& householdStaff.getOrganization() != null
				&& householdStaff.getOrganization().getId() != null) {
			key = NewBaseInfoTables.HOUSEHOLDSTAFF_KEY + "_"
					+ householdStaff.getOrganization().getId() + "_"
					+ householdStaff.getIdCardNo() + "_state";
			// 使用相同的key防止手机录入和excel导入重复数据
			if (!cacheService.add(key, 300,
					NewBaseInfoTables.HOUSEHOLDSTAFF_KEY)) {
				return householdStaff;
			}
		}
		try {
			if (householdStaff.getOrganization() != null
					&& householdStaff.getOrganization().getId() != null) {
				result = validator.validatorIdCardNoExistedMessage(
						householdStaff.getOrganization().getId(),
						householdStaff.getIdCardNo(),
						householdStaff.getActualPopulationType(), null, result);
				String errorMessages = result.getErrorMessages();
				if (errorMessages != null && !"".equals(errorMessages)) {
					throw new BusinessValidationException(errorMessages
							+ "重复身份证");
				}
			}
			HouseholdStaff temp = add(householdStaff, null);
			householdStaff.setBaseInfoId(temp.getBaseInfoId());
			householdStaff.setAddressInfoId(temp.getAddressInfoId());
			householdStaff = updateHousePopulationBusinessInfo(householdStaff);
			syncPopulationSolrIndexForMQ.sendPopulationSolrIndexForMQ(
					householdStaff, BaseInfoTables.HOUSEHOLDSTAFF_KEY,
					OperateMode.ADD);
			return householdStaff;
		} catch (Exception e) {
			logger.error("HouseholdStaffServiceaddHouseholdStaffForMobile", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		} finally {
			if (!"".equals(key)) {
				cacheService.remove(key);
			}
		}
	}

	private HouseholdStaff add(HouseholdStaff householdStaff, OperateMode mode) {
		String attentionPopulationType = householdStaff
				.getAttentionPopulationType();

		householdStaff.setIdCardNo(householdStaff.getIdCardNo().toUpperCase());

		autoFillPropertyWhenAdd(householdStaff);

		householdStaff
				.setCensusRegisterFamily(getCensusRegisterFamilyByAccountNumberAndOrgId(householdStaff));

		Long houseId = householdStaff.getHouseId();

		contructCurrentAddress(householdStaff);
		householdStaff.setShardCode(shardConversion.getShardCode(householdStaff
				.getOrganization().getId()));
		if (householdStaff.getSettleTime() != null
				&& !"".equals(householdStaff.getSettleTime())) {
			householdStaff
					.setSourcesState(ConstantsProduct.SourcesState.SETTLED);
		}

		Countrymen temp;

		if (householdStaff.getBaseInfoId() == null) {
			temp = baseInfoService.getBaseInfoByIdCardNo(householdStaff
					.getIdCardNo());
			if (temp != null) {
				householdStaff.setBaseInfoId(temp.getId());
				temp = baseInfoService.update(householdStaff);
			} else {
				temp = baseInfoService.add(householdStaff);
			}
		} else {
			temp = baseInfoService.update(householdStaff);
		}
		if (temp.isDeath()) {
			householdStaff.setLogOut(IsEmphasis.IsNotEmphasis);
		}
		householdStaff.setBaseInfoId(temp.getId());
		maintainAddressInfo(householdStaff);
		// householdStaff.setOrganization(temp.getOrganization());
		// householdStaff.setOrgInternalCode(temp.getOrgInternalCode());
		householdStaff = householdStaffDao.addShard(householdStaff);
		householdStaff.setHouseId(houseId);

		if (householdStaff.getSettleTime() != null) {
			updatePopulationLogOut(householdStaff);
		}
		rebuildHouseAddress(householdStaff);
		// 在新增修改业务人员返回实口信息时，把新增的房屋id返回
		if (householdStaff.getIsHaveHouse() != null
				&& householdStaff.getIsHaveHouse()) {
			householdStaff.setHouseId(managePopulationByHouseHelper
					.getPopulationLivingHouseId(
							PopulationCatalog.HOUSEHOULD_POPULATION,
							householdStaff.getId()));
		}
		if (mode != null
				&& !BaseInfoTables.NURTURESWOMEN_KEY
						.equals(attentionPopulationType)
				&& !BaseInfoTables.ELDERLYPEOPLE_KEY
						.equals(attentionPopulationType)) {
			sendActiveMQ(householdStaff, mode);
		}
		if (IsEmphasis.Emphasis.equals(householdStaff.getLogOut())) {
			// 缓存计数器
			PageInfoCacheThreadLocal.increment(
					MemCacheConstant.getCachePageKey(householdStaff.getClass()
							.getSimpleName()), householdStaff, 1);
		}
		return householdStaff;
	}

	private void maintainAddressInfo(HouseholdStaff population) {
		Countrymen temp = null;
		Long id = population.getId();
		if (population.getAddressInfoId() != null) {
			temp = addressInfoService.update(population);
		} else {
			temp = addressInfoService.add(population);
		}
		population.setId(id);
		population.setAddressInfoId(temp.getId());
	}

	/**
	 * 注销转过来的未落户或者流动人口的信息 并且同步家庭信息
	 * 
	 * @param householdStaff
	 */
	private void updatePopulationLogOut(HouseholdStaff householdStaff) {
		LogoutDetail ld = new LogoutDetail();
		ld.setLogout(1L);
		ld.setLogoutDate(new Date());
		ld.setLogoutReason("落户");

		UnsettledPopulation unsettledPopulation = unsettledPopulationService
				.getUnsettledPopulationByIdCardNo(householdStaff.getIdCardNo(),
						householdStaff.getOrganization().getId());

		GroupFamily groupFamily = null;
		if (unsettledPopulation != null) {
			groupFamily = groupFamilyService
					.getGroupFamilyByPopulation(unsettledPopulation);
			unsettledPopulationService
					.deleteUnsettledPopulationById(unsettledPopulation.getId());
		}
		if (groupFamily != null) {
			groupFamily.setOrgInternalCode(householdStaff.getOrgInternalCode());
			if (groupFamily.getId() != null) {
				groupFamilyService.practiseGroupFamilyForSynchro(groupFamily,
						householdStaff.getId(),
						householdStaff.getActualPopulationType());
			}
		}
	}

	private void autoFillPropertyWhenAdd(HouseholdStaff householdStaff) {
		autoFillChinesePinyin(householdStaff);
		autoFillGender(householdStaff);
		if (householdStaff.getBirthday() != null) {
			autoFillBirthday(householdStaff);
		}
		householdStaff.setLogOut(IsEmphasis.Emphasis);
		Organization organization = organizationDubboService
				.getSimpleOrgById(householdStaff.getOrganization().getId());
		householdStaff.setOrgInternalCode(organization.getOrgInternalCode());
	}

	private void autoFillChinesePinyin(HouseholdStaff householdStaff) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(householdStaff.getName());
		householdStaff.setSimplePinyin(pinyin.get("simplePinyin"));
		householdStaff.setFullPinyin(pinyin.get("fullPinyin"));
	}

	/**
	 * 填充性别
	 * 
	 * @param householdStaff
	 */
	private void autoFillGender(HouseholdStaff householdStaff) {
		householdStaff.setGender(peopleHelper.autoFillGender(
				PropertyTypes.GENDER, householdStaff.getIdCardNo()));
	}

	/**
	 * 填充出生日期
	 * 
	 * @param householdStaff
	 */
	private void autoFillBirthday(HouseholdStaff householdStaff) {
		peopleHelper.autoFillBirthday(householdStaff);
	}

	private CensusRegisterFamily getCensusRegisterFamilyByAccountNumberAndOrgId(
			HouseholdStaff householdStaff) {
		CensusRegisterFamily family = getCensusRegisterFamilyByOrgIdAndAccountNumber(
				householdStaff.getAccountNumber(), householdStaff
						.getOrganization().getId());
		if (family != null) {
			return whetherUpdateCensusRegisterFamily(householdStaff, family);
		} else {
			return addCensusRegisterFamily(householdStaff);
		}
	}

	private CensusRegisterFamily getCensusRegisterFamilyByOrgIdAndAccountNumber(
			String accountNumber, Long orgId) {
		if (null == accountNumber || "".equals(accountNumber.trim())
				|| null == orgId) {
			return null;
		}
		return censusRegisterFamilyService
				.getCensusRegisterFamilyByOrgIdAndAccountNumber(accountNumber,
						orgId);
	}

	private CensusRegisterFamily addCensusRegisterFamily(
			HouseholdStaff householdStaff) {
		if (null == householdStaff.getRelationShipWithHead()) {
			return null;
		}
		CensusRegisterFamily family = new CensusRegisterFamily();
		family.setAccountNumber(householdStaff.getAccountNumber());
		PropertyDict propertyDict = getPropertyDictById(householdStaff
				.getRelationShipWithHead().getId());
		if (propertyDict.getInternalId() == RelationShipWithHead.HEADER) {
			householdStafftocensusRegisterFamily(family, householdStaff);
		}
		if (householdStaff.getCurrentAddress() != null) {
			family.setCurrentAddress(householdStaff.getCurrentAddress());
		}
		family.setOrganization(householdStaff.getOrganization());
		family.setOrgInternalCode(householdStaff.getOrgInternalCode());
		return censusRegisterFamilyService.addCensusRegisterFamily(family);
	}

	private CensusRegisterFamily whetherUpdateCensusRegisterFamily(
			HouseholdStaff householdStaff, CensusRegisterFamily family) {
		if (null == householdStaff.getRelationShipWithHead()) {
			return null;
		}
		PropertyDict propertyDict = getPropertyDictById(householdStaff
				.getRelationShipWithHead().getId());
		if (propertyDict.getInternalId() == RelationShipWithHead.HEADER
				&& (null == family.getIdCardNo()
						|| "".equals(family.getIdCardNo().trim()) || householdStaff
						.getIdCardNo().equals(family.getIdCardNo()))) {
			censusRegisterFamilyService
					.updateCensusRegisterFamily(householdStafftocensusRegisterFamily(
							family, householdStaff));
		}
		if (propertyDict.getInternalId() != RelationShipWithHead.HEADER
				&& householdStaff.getIdCardNo().equals(family.getIdCardNo())) {
			emptyCensusRegisterFamily(family);
		}
		return family;
	}

	@Override
	public void emptyCensusRegisterFamily(CensusRegisterFamily family) {
		family.setIdCardNo("");
		family.setHouseholdName("");
		family.setHomePhone("");
		family.setProvince("");
		family.setCity("");
		family.setDistrict("");
		family.setMobileNumber("");
		family.setTelePhone("");
		censusRegisterFamilyService.updateCensusRegisterFamily(family);
	}

	public boolean whetherHousehold(Long id) {
		PropertyDict propertyDict = getPropertyDictById(id);
		if (propertyDict.getInternalId() == RelationShipWithHead.HEADER) {
			return true;
		} else {
			return false;
		}
	}

	private void checkedFamilyIdCardNo(HouseholdStaff householdStaff,
			CensusRegisterFamily family) {
		if (null == family) {
			return;
		}
		if (null != family.getIdCardNo() && !"".equals(family.getIdCardNo())) {
			throw new BusinessValidationException("该户口号已有户主!");
		}
	}

	public List<PropertyDict> getHouseFamilyTypeList(
			HouseholdStaff householdStaff) {
		CensusRegisterFamily family = censusRegisterFamilyService
				.getCensusRegisterFamilyByOrgIdAndAccountNumber(householdStaff
						.getAccountNumber(), householdStaff.getOrganization()
						.getId());
		if (family == null || family.getId() == null) {
			return new ArrayList<PropertyDict>();
		}

		return censusRegisterFamilyService.getHouseFamilyTypesByFamilyId(family
				.getId());
	}

	private PropertyDict getPropertyDictById(Long id) {
		PropertyDict propertyDict = propertyDictService.getPropertyDictById(id);
		if (propertyDict == null) {
			throw new BusinessValidationException("PropertyDict不能为空!");
		}
		return propertyDict;
	}

	private CensusRegisterFamily householdStafftocensusRegisterFamily(
			CensusRegisterFamily family, HouseholdStaff householdStaff) {
		family.setIdCardNo(householdStaff.getIdCardNo());
		family.setHouseholdName(householdStaff.getName());
		family.setHomePhone(householdStaff.getHomePhone());
		family.setProvince(householdStaff.getProvince());
		family.setCity(householdStaff.getCity());
		family.setDistrict(householdStaff.getDistrict());
		family.setMobileNumber(householdStaff.getMobileNumber());
		family.setTelePhone(householdStaff.getTelephone());
		family.setOrgInternalCode(householdStaff.getOrgInternalCode());
		family.setOrganization(householdStaff.getOrganization());
		if (householdStaff.getCurrentAddress() != null) {
			family.setCurrentAddress(householdStaff.getCurrentAddress());
		}
		return family;
	}

	public boolean hasDuplicateHouseholdStaff(String idCardNo, Long orgId,
			Long id) {
		idCardNo = idCardNo.toUpperCase();
		HouseholdStaff householdStaff = findHouseholdStaffByCardNoAndOrgId(
				idCardNo, orgId);
		return id == null ? householdStaff != null
				: (householdStaff != null && !id.equals(householdStaff.getId()));
	}

	public HouseholdStaff hasDuplicateHouseholdStaff(Long orgId, String idCardNo) {
		idCardNo = idCardNo.toUpperCase();
		HouseholdStaff householdStaff = findHouseholdStaffByCardNoAndOrgId(
				idCardNo, orgId);
		return householdStaff;
	}

	@Override
	public ActualPopulation getActualPopulationById(Long id) {
		return getHouseholdStaffById(id);
	}

	/**
	 * 查找列表（used for GIS）
	 */
	@Override
	public PageInfo<PopulationVo> findPopulationsByOrgId(Long orgId,
			Integer page, Integer rows, String sidx, String sord) {
		if (orgId == null) {
			return new PageInfo<PopulationVo>();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			String shardCode = shardConversion.getShardCode(org
					.getDepartmentNo());
			PageInfo<HouseholdStaff> pageInfo = householdStaffDao
					.findHouseholdStaffByorgCodeForGis(
							org.getOrgInternalCode(), page, rows, sidx, sord,
							shardCode);
			return exchangeHouseholdStaffToPopulationVoPageInfo(pageInfo);
		}
	}

	private void shiftViewHouseholdStaffToPopulationVo(
			List<PopulationVo> populationVos,
			List<HouseholdStaff> householdStaffs) {
		for (HouseholdStaff householdStaff : householdStaffs) {
			populationVos
					.add(shiftGisPopulationFromHouseholdStaff(householdStaff));
		}
	}

	private PopulationVo shiftGisPopulationFromHouseholdStaff(
			HouseholdStaff householdStaff) {
		PopulationVo populationVo = new PopulationVo();
		if (householdStaff.getIsHaveHouse() != null
				&& householdStaff.getIsHaveHouse()) {
			managePopulationByHouseHelper.loadLivingHouseIfNecc(
					PopulationCatalog.HOUSEHOULD_POPULATION, householdStaff);
			if (null != householdStaff.getHouseId()) {
				HouseInfo houseInfo = actualHouseService
						.getHouseInfoById(householdStaff.getHouseId());
				populationVo.setHouseId(householdStaff.getHouseId());
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
			if (null != householdStaff.getNoHouseReason()) {
				populationVo
						.setNoHouseReason(householdStaff.getNoHouseReason());
			} else {
				populationVo.setNoHouseReason("暂未填写");
			}
		}
		populationVo.setKeyPersonType(PopulationType.HOUSEHOLD_STAFF);
		populationVo.setIsHaveHouse(householdStaff.getIsHaveHouse());
		populationVo.setGender(householdStaff.getGender());
		populationVo.setGenderName(getPropertyDictText(PropertyTypes.GENDER,
				householdStaff.getGender().getId()));
		populationVo.setIdCardNo(householdStaff.getIdCardNo());
		populationVo.setImgUrl(householdStaff.getImgUrl());
		populationVo.setName(householdStaff.getName());
		populationVo.setPersonId(householdStaff.getId());
		return populationVo;
	}

	@Override
	public List<PopulationVo> findPopulationByPersonId(Long personId) {
		List<HouseholdStaff> hStaff = householdStaffDao
				.findGisHouseHoldStaffById(personId);
		List<PopulationVo> populationVos = new ArrayList<PopulationVo>();
		for (HouseholdStaff householdStaff : hStaff) {
			PopulationVo populationVo = new PopulationVo();
			populationVo.setAddress(householdStaff.getCurrentAddress());
			populationVo.setName(householdStaff.getName());
			populationVo.setGenderName(getPropertyDictText(
					PropertyTypes.GENDER, householdStaff.getGender().getId()));
			populationVo.setIdCardNo(householdStaff.getIdCardNo());
			populationVo.setPersonId(householdStaff.getId());
			populationVo.setImgUrl(householdStaff.getImgUrl());
			populationVo.setPopulationType(PopulationType
					.getCnameByPopulationType(householdStaff
							.getPopulationType()));
			populationVo.setActulType(PopulationType
					.getCnameByActualType(householdStaff.getActualtype()));
			populationVo.setKeyPersonType(householdStaff.getActualtype());
			populationVos.add(populationVo);
		}
		return populationVos;
	}

	@Override
	public List<HouseholdStaff> updateDeathOfHouseholdStaffByIdList(
			List<Long> idList, Boolean death) {
		if (null == idList) {
			throw new BusinessValidationException("户籍人口idList不能为空");
		}
		List<HouseholdStaff> populationList = new ArrayList<HouseholdStaff>();
		Long logoutState = death ? IsEmphasis.IsNotEmphasis
				: IsEmphasis.Emphasis;
		for (Long id : idList) {
			HouseholdStaff population = getHouseholdStaffById(id);
			LogoutDetail logoutDetail = new LogoutDetail();
			logoutDetail.setLogout(logoutState);
			updateLogOutByPopulationTypeAndId(logoutDetail,
					population.getActualPopulationType(), population.getId());
			baseInfoService.updateDeathStateById(population.getBaseInfoId(),
					death, population.getIdCardNo(), population
							.getOrganization().getId(), population
							.getOrgInternalCode(),
					NewBaseInfoTables.HOUSEHOLDSTAFF_KEY);
			groupFamilyService
					.deleteGroupFamilyByPopulationIdAndPopulationTypeAndIdCardNo(
							id, BaseInfoTables.HOUSEHOLDSTAFF_KEY, null);
			HouseholdStaff temp = getHouseholdStaffById(id);
			populationList.add(temp);
			syncPopulationSolrIndexForMQ.sendPopulationSolrIndexForMQ(temp,
					BaseInfoTables.HOUSEHOLDSTAFF_KEY, OperateMode.EDIT);
		}
		/*
		 * datastore.update(
		 * datastore.find(HouseholdStaff.class).filter("id in", idList),
		 * datastore.createUpdateOperations(HouseholdStaff.class) .set("logOut",
		 * logoutState).set("isDeath", death), false);
		 */
		return populationList;
	}

	@Override
	public List<Long> updateLogOutOfHouseholdStaffByIds(Long orgId,
			LogoutDetail logoutDetail, String populationType, Long[] ids) {
		List<Long> resultList = new ArrayList<Long>();
		resultList = updateLogOutDetailByPopulationTypeAndIds(orgId,
				logoutDetail, populationType, ids);
		for (Long resultId : resultList) {
			HouseholdStaff population = getHouseholdStaffById(resultId);
			syncPopulationSolrIndexForMQ.sendPopulationSolrIndexForMQ(
					population, BaseInfoTables.HOUSEHOLDSTAFF_KEY,
					OperateMode.EDIT);
		}
		return resultList;
	}

	@Override
	public List<ActualPopulation> getActualPopulationByOrgIdAndIdCardNoExcludePopulationIdList(
			Long excludePopulationId, Long orgId, String idCardNo) {
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
		List<ActualPopulation> actualPopulationList = new ArrayList<ActualPopulation>();
		try {
			List<HouseholdStaff> householdStaffList = this.householdStaffDao
					.getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(
							excludePopulationId, orgId, idCardNo15, idCardNo18);
			for (HouseholdStaff householdStaff : householdStaffList) {
				householdStaff
						.setActualPopulationType(PopulationType.HOUSEHOLD_STAFF);
				actualPopulationList.add(householdStaff);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("查询户籍人口出现异常", e);
		}
		return actualPopulationList;
	}

	@Override
	public ActualPopulation getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(
			Long excludePopulationId, Long orgId, String idCardNo) {
		if (null == idCardNo || "".equals(idCardNo))
			return null;
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
		// ActualPopulation actualPopulation = null;
		// List<HouseholdStaff> householdStaffList = this.householdStaffDao
		// .getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(
		// excludePopulationId, orgId, idCardNo15, idCardNo18);
		// if (!householdStaffList.isEmpty()) {
		// actualPopulation = householdStaffList.get(0);
		// }
		Countrymen countrymen = baseInfoService.getBaseInfoByIdCardNo(idCardNo);
		if (countrymen == null) {
			return null;
		}
		return getHouseholdStaffByBaseInfoId(countrymen.getId(), orgId);
	}

	@Override
	public ActualPopulation getActualPopulationByOrgIdAndIdCardNoExcludePopulationIdIncludeLogout(
			Long excludePopulationId, Long orgId, String idCardNo) {
		if (null == idCardNo || "".equals(idCardNo))
			return null;
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
		ActualPopulation actualPopulation = null;
		List<HouseholdStaff> householdStaffList = this.householdStaffDao
				.getActualPopulationByOrgIdAndIdCardNoExcludePopulationIdIncludeLogout(
						excludePopulationId, orgId, idCardNo15, idCardNo18);
		if (!householdStaffList.isEmpty()) {
			actualPopulation = householdStaffList.get(0);
		}
		return actualPopulation;
	}

	private String getPropertyDictText(String domainName, Long id) {
		if (null == id) {
			return "";
		} else {
			PropertyDict dict = propertyDictService.getPropertyDictById(id);
			return dict == null ? "" : dict.getDisplayName();
		}
	}

	@Override
	public ActualPopulation getActualPopulationHouseId(
			ActualPopulation actualPopulation) {
		actualPopulation.setHouseId(managePopulationByHouseHelper
				.getPopulationLivingHouseId(
						PopulationCatalog.HOUSEHOULD_POPULATION,
						actualPopulation.getId()));
		return actualPopulation;
	}

	@Override
	public PageInfo<PopulationVo> getFurtherStepGisPopulationInfoByPersonType(
			Long orgId, String personType, String queryStr, Integer page,
			Integer rows, String sidx, String sord) {
		if (null == orgId) {
			return new PageInfo<PopulationVo>();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			String shardCode = shardConversion.getShardCode(org
					.getDepartmentNo());
			PageInfo<HouseholdStaff> housePageInfo = householdStaffDao
					.getFurtherStepGisPopulationInfoByPersonType(
							org.getOrgInternalCode(), personType, page, rows,
							sidx, sord, shardCode);
			return exchangeHouseholdStaffToPopulationVoPageInfo(housePageInfo);
		}
	}

	@Override
	public PageInfo<PopulationVo> getFurtherStepGisPopulationInfoByPersonTypeList(
			Long orgId, List personType, String queryStr, Integer page,
			Integer rows, String sidx, String sord) {
		if (null == orgId) {
			return new PageInfo<PopulationVo>();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			String shardCode = shardConversion.getShardCode(org
					.getDepartmentNo());
			PageInfo<HouseholdStaff> housePageInfo = householdStaffDao
					.getFurtherStepGisPopulationInfoByPersonType(
							org.getOrgInternalCode(), personType, page, rows,
							sidx, sord, shardCode);
			return exchangeHouseholdStaffToPopulationVoPageInfo(housePageInfo);
		}
	}

	private PageInfo<PopulationVo> exchangeHouseholdStaffToPopulationVoPageInfo(
			PageInfo<HouseholdStaff> housePageInfo) {
		List<PopulationVo> populationVos = new ArrayList<PopulationVo>();
		for (HouseholdStaff householdStaff : housePageInfo.getResult()) {
			populationVos
					.add(shiftGisPopulationFromHouseholdStaff(householdStaff));
		}
		PageInfo<PopulationVo> pageInfo = new PageInfo<PopulationVo>();
		pageInfo.setResult(populationVos);
		pageInfo.setCurrentPage(housePageInfo.getCurrentPage());
		pageInfo.setTotalRowSize(housePageInfo.getTotalRowSize());
		pageInfo.setPerPageSize(housePageInfo.getPerPageSize());
		return pageInfo;
	}

	@Override
	public void asynActualPopulation(ActualPopulation actualPopulation) {
		if (null != actualPopulation.getId())
			updateHouseholdStaff((HouseholdStaff) actualPopulation);
		else
			addHouseholdStaff((HouseholdStaff) actualPopulation);
	}

	@Override
	public ActualPopulation getFullActualPopulationByCardNoAndOrgId(
			String idCardNo, Long orgId) {
		return findHouseholdStaffByCardNoAndOrgId(idCardNo, orgId);
	}

	@Override
	public void sysActualPopulationByAddHousePopulationRela(Long populationId,
			String address) {
		householdStaffDao.updateActualPopulationToHasHouseState(populationId,
				address, IdConversionShardUtil.getShardCodeById(populationId));
	}

	@Override
	public void regrantFamilyHouse(HouseholdStaff population,
			List<Long> familyHouseIdList) {
		if (familyHouseIdList == null) {
			if (population.getCensusRegisterFamily() != null
					&& population.getCensusRegisterFamily().getId() != null) {
				censusRegisterFamilyService
						.deleteFamilyHouseTypeByFamilyId(population
								.getCensusRegisterFamily().getId());
			}
			return;
		}
		CensusRegisterFamily censusRegisterFamily = censusRegisterFamilyService
				.getCensusRegisterFamilyByOrgIdAndAccountNumber(population
						.getAccountNumber(), population.getOrganization()
						.getId());
		censusRegisterFamilyService
				.deleteFamilyHouseTypeByFamilyId(censusRegisterFamily.getId());
		for (Long fhi : familyHouseIdList) {
			censusRegisterFamilyService.addFamilyHouse(
					censusRegisterFamily.getId(), fhi);
		}
	}

	@Override
	public void addFamilyHouse(HouseholdStaff population,
			List<Long> familyHouseIdList) {
		if (familyHouseIdList == null)
			return;
		CensusRegisterFamily censusRegisterFamily = censusRegisterFamilyService
				.getCensusRegisterFamilyByOrgIdAndAccountNumber(population
						.getAccountNumber(), population.getOrganization()
						.getId());
		for (Long fhi : familyHouseIdList) {
			censusRegisterFamilyService.addFamilyHouse(
					censusRegisterFamily.getId(), fhi);
		}
	}

	@Override
	public HouseholdStaff updateHousePopulationBusinessInfo(
			HouseholdStaff population) {
		// 判断当前这个人是否户主关系为户主
		PropertyDict propertyDict = getPropertyDictById(population
				.getRelationShipWithHead().getId());

		if (propertyDict.getInternalId() == RelationShipWithHead.HEADER) {// 是户主的话根据户口号取当前户口号下的户主信息
			CensusRegisterFamily censusRegisterFamily = censusRegisterFamilyService
					.getCensusRegisterFamilyByOrgIdAndAccountNumber(population
							.getAccountNumber(), population.getOrganization()
							.getId());
			// 比较取出来的户主和要修改的是否是同一个人 ，不是的话抛出异常
			if (censusRegisterFamily != null
					&& censusRegisterFamily.getIdCardNo() != null
					&& !population.getIdCardNo().equals(
							censusRegisterFamily.getIdCardNo())) {
				throw new BusinessValidationException("该户口号已有户主!");
			}
		}
		population.setShardCode(shardConversion.getShardCode(population
				.getOrganization().getId()));
		population.setIdCardNo(population.getIdCardNo().toUpperCase());
		population
				.setCensusRegisterFamily(getCensusRegisterFamilyByAccountNumberAndOrgId(population));

		HouseholdStaff householdStaff = householdStaffDao
				.updateBusiness(population);
		updateCensusRegisterFamilyByOrgIdAndAccountNumber(householdStaff);
		return householdStaff;
	}

	@Override
	public HouseholdStaff transferHousePopulationBusinessInfo(
			HouseholdStaff population) {
		// 判断当前这个人是否户主关系为户主
		PropertyDict propertyDict = getPropertyDictById(population
				.getRelationShipWithHead().getId());

		if (propertyDict.getInternalId() == RelationShipWithHead.HEADER) {// 是户主的话根据户口号取当前户口号下的户主信息
			CensusRegisterFamily censusRegisterFamily = censusRegisterFamilyService
					.getCensusRegisterFamilyByOrgIdAndAccountNumber(population
							.getAccountNumber(), population.getOrganization()
							.getId());
			// 如果取出来的户主和要修改的不是同一个人 ，与户主关系修改为其他
			if (censusRegisterFamily != null
					&& censusRegisterFamily.getIdCardNo() != null
					&& !population.getIdCardNo().equals(
							censusRegisterFamily.getIdCardNo())) {
				List<PropertyDict> list = propertyDictService
						.findPropertyDictByDomainNameAndInternalId(
								PropertyTypes.RELATION_SHIP_WITH_HEAD,
								RelationShipWithHead.OTHER);
				if (!list.isEmpty()) {
					PropertyDict propertyDictOther = list.get(0);
					population.setRelationShipWithHead(propertyDictOther);
				}
			}
		}

		population.setIdCardNo(population.getIdCardNo().toUpperCase());
		population
				.setCensusRegisterFamily(getCensusRegisterFamilyByAccountNumberAndOrgId(population));

		HouseholdStaff householdStaff = householdStaffDao
				.updateBusiness(population);
		updateCensusRegisterFamilyByOrgIdAndAccountNumber(householdStaff);
		return householdStaff;
	}

	private void updateCensusRegisterFamilyByOrgIdAndAccountNumber(
			HouseholdStaff population) {
		if (population.getId() == null)
			return;
		CensusRegisterFamily censusRegisterFamily = censusRegisterFamilyService
				.getCensusRegisterFamilyByOrgIdAndAccountNumber(population
						.getAccountNumber(), population.getOrganization()
						.getId());
		if (null != censusRegisterFamily) {
			updateCensusRegisterFamily(censusRegisterFamily, population,
					propertyDictService.getPropertyDictById(population
							.getRelationShipWithHead().getId()));
		} else {
			addCensusRegisterFamily(population);
		}

	}

	private void updateCensusRegisterFamily(
			CensusRegisterFamily censusRegisterFamily,
			HouseholdStaff population, PropertyDict dict) {
		if (RelationShipWithHead.HEADER == dict.getInternalId()) {
			if (population.getAccountNumber() != null
					|| population.getOrganization().getId() != null) {
				censusRegisterFamily.setCity(population.getCity());
				censusRegisterFamily.setDistrict(population.getDistrict());
				censusRegisterFamily.setProvince(population.getProvince());
				censusRegisterFamily.setHomePhone(population.getHomePhone());
				censusRegisterFamily.setTelePhone(population.getTelephone());
				censusRegisterFamily.setIdCardNo(population.getIdCardNo());
				censusRegisterFamily.setMobileNumber(population
						.getMobileNumber());
				censusRegisterFamily.setHouseholdName(population.getName());
				censusRegisterFamily.setCurrentAddress(population
						.getCurrentAddress());
				censusRegisterFamilyService
						.updateCensusRegisterFamily(censusRegisterFamily);
				return;
			}
		} else {
			if (censusRegisterFamily.getIdCardNo() != null
					&& censusRegisterFamily.getIdCardNo().equals(
							population.getIdCardNo())) {
				censusRegisterFamily.setCity(null);
				censusRegisterFamily.setDistrict(null);
				censusRegisterFamily.setProvince(null);
				censusRegisterFamily.setHomePhone(null);
				censusRegisterFamily.setTelePhone(null);
				censusRegisterFamily.setIdCardNo(null);
				censusRegisterFamily.setMobileNumber(null);
				censusRegisterFamily.setHouseholdName(null);
				censusRegisterFamily.setCurrentAddress(null);
				censusRegisterFamilyService
						.updateCensusRegisterFamily(censusRegisterFamily);
			}
		}
	}

	@Override
	public List<PopulationVo> findGisPopulationByOrgid(Long orgid) {
		if (null == orgid) {
			return new ArrayList<PopulationVo>();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgid);
			String shardCode = shardConversion.getShardCode(org
					.getDepartmentNo());
			List<HouseholdStaff> householdStaffs = householdStaffDao
					.findAllBindingHouseholdStaffByorgCodeForGis(
							PopulationCatalog.HOUSEHOULD_POPULATION,
							org.getOrgInternalCode(), shardCode);
			return exchangeHouseholdStaffToPopulationVoPageInfo(householdStaffs);
		}
	}

	private List<PopulationVo> exchangeHouseholdStaffToPopulationVoPageInfo(
			List<HouseholdStaff> householdStaffs) {
		List<PopulationVo> populationVos = new ArrayList<PopulationVo>();
		shiftViewHouseholdStaffToPopulationVo(populationVos, householdStaffs);
		return populationVos;
	}

	@Override
	public List<HouseholdStaff> findhouseholdStaffWhenIsOldFetchHouseId(
			int pageSize, String shardCode) {
		try {
			// return householdStaffDao.findhouseholdStaffWhenIsOldFetchHouseId(
			// pageNum, pageSize);
			return householdStaffDao
					.findhouseholdStaffWhenIsOldFetchHouseIdForMark(pageSize,
							shardCode);
		} catch (Exception e) {
			throw new ServiceValidationException("在户籍人口获取老年人时出错", e);
		}
	}

	@Override
	public Integer countHouseholdStaffWhenIsOldFetchHouseId(String shardCode) {
		try {
			// return
			// householdStaffDao.countHouseholdStaffWhenIsOldFetchHouseId();
			return householdStaffDao
					.countHouseholdStaffWhenIsOldFetchHouseIdForMark(shardCode);
		} catch (Exception e) {
			throw new ServiceValidationException("在户籍人口统计老年人数量时出错", e);
		}
	}

	@Override
	public List<HouseholdStaff> findhouseholdStaffWhenIsNurturesWomen(
			int pageSize, String shardCode) {
		try {
			// return householdStaffDao.findhouseholdStaffWhenIsNurturesWomen(
			// pageNum, pageSize);
			return householdStaffDao
					.findhouseholdStaffWhenIsNurturesWomenForMark(pageSize,
							shardCode);
		} catch (Exception e) {
			throw new ServiceValidationException("在户籍人口获取老年人时出错", e);
		}
	}

	@Override
	public Integer countHouseholdStaffWhenIsNurturesWomen(String shardCode) {
		try {
			// return
			// householdStaffDao.countHouseholdStaffWhenIsNurturesWomen();
			return householdStaffDao
					.countHouseholdStaffWhenIsNurturesWomenForMark(shardCode);
		} catch (Exception e) {
			throw new ServiceValidationException("在户籍人口统计老年人数量时出错", e);
		}
	}

	@Override
	public PopulationVo shiftGisPopulationById(Long id) {
		return shiftGisPopulationFromHouseholdStaff(householdStaffDao
				.getHouseholdStaffById(id));
	}

	@Override
	public void sysActualPopulationByAddHousePopulationRela(Long populationId,
			HouseInfo houseInfo) {
		householdStaffDao
				.updateActualPopulationToHasHouseState(populationId, houseInfo,
						IdConversionShardUtil.getShardCodeById(populationId));
	}

	@Override
	public void updateLogOutByPopulationTypeAndId(LogoutDetail logoutDetail,
			String populationType, Long id) {
		super.updateLogOutByPopulationTypeAndId(logoutDetail, populationType,
				id);
	}

	@Override
	public List<HouseholdStaff> findHouseholdStaffsExceptHeadByFamilyId(
			Long familyId, Long orgId) {
		String houseHold = getHouseHold();
		String shardCode = shardConversion.getShardCode(orgId);
		return householdStaffDao.findHouseholdStaffsExceptHeadByFamilyId(
				familyId, houseHold, shardCode);
	}

	@Override
	public void addFamilyMemberByIdCardNo(Long orgId, Long familyId,
			String idCardNo, Long relationshipWithHeadId, String accountNumber) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		String shardCode = shardConversion.getShardCode(organization
				.getDepartmentNo());
		List<PropertyDict> propertyDicts = findPropertyDictsForHead();
		HouseholdStaff householdStaff = householdStaffDao
				.getByOrgInternalCodeAndIdCardNo(
						organization.getOrgInternalCode(), idCardNo, shardCode);
		if (householdStaff == null) {
			throw new BusinessValidationException("此人在该网格下的户籍人口中不存在！");
		} else {
			if (accountNumber.equals(householdStaff.getAccountNumber())) {
				throw new BusinessValidationException("此人在该户口号下已存在！");
			}
		}
		boolean isHead = false;
		for (PropertyDict propertyDict : propertyDicts) {
			if (householdStaff != null
					&& householdStaff.getRelationShipWithHead() != null) {
				if (propertyDict.getId().equals(
						householdStaff.getRelationShipWithHead().getId())) {
					isHead = true;
					break;
				}
			}
		}
		if (isHead) {
			houseFamilyDao.cleanFamilyHead(organization.getOrgInternalCode(),
					idCardNo);
		}
		householdStaffDao.addFamilyMemberByIdCardNo(
				organization.getOrgInternalCode(), familyId, idCardNo,
				relationshipWithHeadId, accountNumber,
				shardConversion.getShardCode(organization));
	}

	private List<PropertyDict> findPropertyDictsForHead() {
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.RELATION_SHIP_WITH_HEAD,
						RelationShipWithHead.HEADER);
		return propertyDicts;
	}

	@Override
	public void moveHouseMember(Long orgId, String idCardNo,
			Long relationshipWithHeadId, String accountNumber) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		CensusRegisterFamily censusRegisterFamily = censusRegisterFamilyService
				.getCensusRegisterFamilyByOrgIdAndAccountNumber(accountNumber,
						orgId);
		if (censusRegisterFamily == null) {
			censusRegisterFamily = new CensusRegisterFamily();
			censusRegisterFamily.setOrganization(organization);
			censusRegisterFamily.setOrgInternalCode(organization
					.getOrgInternalCode());
			censusRegisterFamily.setAccountNumber(accountNumber);
			censusRegisterFamily.setCreateUser(ThreadVariable.getUser()
					.getUserName());
			censusRegisterFamily.setCreateDate(new Date());
			censusRegisterFamily = censusRegisterFamilyService
					.addCensusRegisterFamily(censusRegisterFamily);
		}
		householdStaffDao.moveHouseMember(organization.getOrgInternalCode(),
				idCardNo, relationshipWithHeadId, accountNumber,
				censusRegisterFamily.getId(),
				shardConversion.getShardCode(organization));
	}

	@Override
	public HouseholdStaff getHouseholdStaffByIdCardNo(String idCardNo,
			Long claimOrgId) {
		if (idCardNo == null || "".equals(idCardNo.trim())
				|| claimOrgId == null) {
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
		return householdStaffDao.getByIdCard(list, claimOrgId);
	}

	@Override
	public HouseholdStaff getHouseholdStaffByIdAndBusinessType(Long id,
			String type, Long orgId) {
		String shardCode = shardConversion.getShardCode(orgId);
		HouseholdStaff householdStaff = householdStaffDao
				.getHouseholdStaffByIdAndBusinessType(id, type, shardCode);
		if (null != householdStaff) {
			householdStaff
					.setHouseMarchType(getHouseFamilyTypeList(householdStaff));
		}
		return householdStaff;
	}

	@Override
	public HouseholdStaff updateHousePopulationBusinessInfo(
			HouseholdStaff population, List<Long> houseMarchTypeList) {
		population = updateHousePopulationBusinessInfo(population);
		syncPopulationSolrIndexForMQ.sendPopulationSolrIndexForMQ(population,
				BaseInfoTables.HOUSEHOLDSTAFF_KEY, OperateMode.EDIT);
		regrantFamilyHouse(population, houseMarchTypeList);
		PageInfoCacheThreadLocal.update(
				MemCacheConstant.getCachePageKey(HouseholdStaff.class),
				population, UpdateType.BUSINESS);
		return population;
	}

	@Override
	@SolrServerIndex(mode = OperateMode.EDIT, value = UpdateSqlMap.HOUSEHOLDSTAFF_KEY)
	public HouseholdStaff updateHouseholdStaffBaseInfo(
			HouseholdStaff householdStaff) {
		if (!ExcelImportHelper.isImport.get()) {
			validateBaseInfo(householdStaff);
		}
		autoFillPropertyWhenAdd(householdStaff);
		contructCurrentAddress(householdStaff);
		rebuildHouseAddress(householdStaff);
		updateIsDeath(householdStaff);
		baseInfoService.update(householdStaff);
		maintainAddressInfo(householdStaff);
		householdStaff = householdStaffDao.getHouseholdStaffById(householdStaff
				.getId());

		// 在新增修改业务人员返回实口信息时，把新增的房屋id返回
		if (householdStaff.getIsHaveHouse() != null
				&& householdStaff.getIsHaveHouse()) {
			householdStaff.setHouseId(managePopulationByHouseHelper
					.getPopulationLivingHouseId(
							PopulationCatalog.HOUSEHOULD_POPULATION,
							householdStaff.getId()));
		}

		if (householdStaff.getAccountNumber() != null
				&& !"".equals(householdStaff.getAccountNumber())) {
			getCensusRegisterFamilyByAccountNumberAndOrgId(householdStaff);
		}
		syncPopulationSolrIndexForMQ.sendPopulationSolrIndexForMQ(
				householdStaff, BaseInfoTables.HOUSEHOLDSTAFF_KEY,
				OperateMode.EDIT);
		Long householdStaffId = householdStaff.getId();
		householdStaffDao.updateTableUpdateDateById(householdStaffId);
		return householdStaff;
	}

	private void updateIsDeath(HouseholdStaff population) {
		if (population.isDeath() == true) {
			LogoutDetail logoutDetail = new LogoutDetail();
			logoutDetail.setLogout(IsEmphasis.IsNotEmphasis);
			logoutDetail.setLogoutDate(new Date());
			logoutDetail.setLogoutReason(LogoutDetail.LOGOUT_REASON_FOR_DEATH);
			// updateLogOutByPopulationTypeAndId(logoutDetail,
			// population.getActualPopulationType(), population.getId());
			List<HouseholdStaff> householdStaffs = getHouseholdStaffByBaseInfoId(population
					.getBaseInfoId());
			if (householdStaffs != null) {
				for (HouseholdStaff h : householdStaffs) {
					updateLogOutByPopulationTypeAndId(logoutDetail,
							BaseInfoTables.HOUSEHOLDSTAFF_KEY, h.getId());
				}
			}
			List<FloatingPopulation> floatingPopulations = floatingPopulationService
					.getFloatingPopulationByBaseInfoId(population
							.getBaseInfoId());
			if (floatingPopulations != null) {
				for (FloatingPopulation f : floatingPopulations) {
					updateLogOutByPopulationTypeAndId(logoutDetail,
							BaseInfoTables.FLOATINGPOPULATION_KEY, f.getId());
				}
			}
		}
	}

	/**
	 * 如果人口的房屋信息（CurrentAddress）不为空，并且房屋id不存在，新增一个房屋，并且建立关联关系, 如果房屋id不为空直接建立关联关系
	 * 如果房屋信息为空,并且有房屋id不为空，则删除人房关系
	 * 
	 * @param householdStaff
	 */
	private void rebuildHouseAddress(HouseholdStaff householdStaff) {

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
						.setHouseOperateSource(NewBaseInfoTables.HOUSEHOLDSTAFF_KEY);
				houseInfo = actualHouseService.addHouseInfo(houseInfo);

				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.HOUSEHOULD_POPULATION,
						householdStaff, houseInfo.getId());
				householdStaff.setHouseId(houseInfo.getId());
				businessPopulationHouseRelationUpdate(householdStaff);
			} else if (householdStaff.getHouseId() != null) {
				managePopulationByHouseHelper.reBindHouseIfNecc(
						PopulationCatalog.HOUSEHOULD_POPULATION,
						householdStaff, householdStaff.getHouseId());
				businessPopulationHouseRelationUpdate(householdStaff);
			}
		} else {
			housePopulationMaintanceService.releaseHouse(
					PopulationCatalog.HOUSEHOULD_POPULATION,
					householdStaff.getId(), householdStaff.getHouseId());
			businessPopulationHouseRelationDelete(householdStaff);
		}
	}

	// 清除户籍房屋地址时 对应清楚业务人员房屋的关联关系
	private void businessPopulationHouseRelationDelete(HouseholdStaff population) {
		List<PopulationTypeBean> list = populationTypeService
				.getPopulationTypeByActualIdAndType(population.getId(),
						population.getActualtype());
		for (PopulationTypeBean temp : list) {
			housePopulationMaintanceService.releaseHouse(PopulationCatalog
					.populationCatalog(temp.getPopulationType()), temp
					.getPopulationId(), population.getHouseId());
		}
	}

	// 修改户籍房屋地址时 对应修改业务人员房屋的关联关系
	private void businessPopulationHouseRelationUpdate(HouseholdStaff population) {
		List<PopulationTypeBean> list = populationTypeService
				.getPopulationTypeByActualIdAndType(population.getId(),
						population.getActualtype());
		People tempPeople = null;
		for (PopulationTypeBean temp : list) {
			tempPeople = new People();
			tempPeople.setId(temp.getPopulationId());
			managePopulationByHouseHelper.reBindHouseIfNecc(PopulationCatalog
					.populationCatalog(temp.getPopulationType()), tempPeople,
					population.getHouseId());
		}
	}

	private void validateBaseInfo(HouseholdStaff population) {
		ValidateResult baseDataValidator = validator
				.validateBaseInfo(population);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public HouseholdStaff addHouseholdStaffBaseInfo(
			HouseholdStaff householdStaff) {
		String key = "";
		try {
			if (householdStaff.getOrganization() != null
					&& householdStaff.getOrganization().getId() != null) {
				key = MemCacheConstant.POPULATION_NAMESPACE
						+ MemCacheConstant.getPopulationKey(
								MemCacheConstant.HOUSEHOLDSTAFF_KEY,
								householdStaff.getIdCardNo(), householdStaff
										.getOrganization().getId());
				if (!cacheService.add(key, 300,
						NewBaseInfoTables.HOUSEHOLDSTAFF_KEY)) {
					throw new BusinessValidationException(
							"所属网格下已经存在相同的身份证，不能再添加！");
				}

			} else {
				throw new BusinessValidationException("所属网格不能为空！");
			}
			OperateMode mode = OperateMode.IMPORT;
			if (!ExcelImportHelper.isImport.get()) {
				validateBaseInfo(householdStaff);
				mode = OperateMode.ADD;
			}
			ValidateResult result = new ValidateResult();
			result = validator.validatorIdCardNoExistedMessage(householdStaff
					.getOrganization().getId(), householdStaff.getIdCardNo(),
					householdStaff.getActualPopulationType(), null, result);
			String errorMessages = result.getErrorMessages();
			if (errorMessages != null && !"".equals(errorMessages)) {
				throw new BusinessValidationException(errorMessages);
			}

			householdStaff = add(householdStaff, mode);
			syncPopulationSolrIndexForMQ.sendPopulationSolrIndexForMQ(
					householdStaff, BaseInfoTables.HOUSEHOLDSTAFF_KEY,
					OperateMode.ADD);

		} finally {
			cacheService.remove(key);
		}
		return householdStaff;
	}

	private void sendActiveMQ(HouseholdStaff householdStaff, OperateMode mode) {
		// 判断是否为育龄妇女
		if (IdCardUtil.autoIdCardNoWhenIsNurturesWomen(householdStaff
				.getIdCardNo())) {
			activeMQMessageSender.send(TransferData.convertToBaseMsg(
					householdStaff, BaseInfoTables.NURTURESWOMEN_KEY, mode));
		} else if (IdCardUtil.autoIdCardNoWhenIsElderlyPeople(householdStaff
				.getIdCardNo())) {
			// 判断是否为老年人
			activeMQMessageSender.send(TransferData.convertToBaseMsg(
					householdStaff, BaseInfoTables.ELDERLYPEOPLE_KEY, mode));
		}
	}

	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.HOUSEHOLDSTAFF_KEY)
	public void toFloatingPopulation(Long id) {
		HouseholdStaff householdStaff = this.getHouseholdStaffById(id);
		changeHouseFamilyInfo(householdStaff);
		// 这里添加 是为了转换的时候跳过验证不报错。。
		// householdStaff.setAttentionPopulationType("convert");
		copyAndUpdate(householdStaff);
	}

	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.HOUSEHOLDSTAFF_KEY)
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void toFloatingPopulationByIds(List<Long> ids) {
		if (!CollectionUtil.isAvaliable(ids)) {
			throw new BusinessValidationException("参数错误！");
		}
		String toFloatingPopulationMessage = "";
		HouseholdStaff householdStaff = null;
		for (Long id : ids) {
			try {
				householdStaff = this.getHouseholdStaffById(Long.valueOf(id));
				changeHouseFamilyInfo(householdStaff);
				copyAndUpdate(householdStaff);
			} catch (Exception e) {
				toFloatingPopulationMessage += (householdStaff.getId() + "-"
						+ householdStaff.getIdCardNo() + ";");
			}
		}
		if (StringUtil.isStringAvaliable(toFloatingPopulationMessage)) {
			throw new BusinessValidationException("户籍人口转为流动人口失败信息：{"
					+ toFloatingPopulationMessage + "}");
		}
	}

	// 如果转移的是户主，将户主关系和户主信息清空
	private void changeHouseFamilyInfo(HouseholdStaff householdStaff) {
		CensusRegisterFamily censusRegisterFamily = censusRegisterFamilyService
				.getCensusRegisterFamilyByOrgIdAndAccountNumber(householdStaff
						.getAccountNumber(), householdStaff.getOrganization()
						.getId());
		if (censusRegisterFamily != null) {
			if (householdStaff.getName().equals(
					censusRegisterFamily.getHouseholdName())) {
				censusRegisterFamily.setIdCardNo(null);
				censusRegisterFamily.setTelePhone(null);
				censusRegisterFamily.setHouseholdName(null);
				censusRegisterFamily.setMobileNumber(null);
				censusRegisterFamilyService
						.updateCensusRegisterFamily(censusRegisterFamily);
				householdStaff.getRelationShipWithHead().setId(null);
				householdStaff.getCensusRegisterFamily().setId(null);
				householdStaffDao.setRelationShipWithHeadNull(householdStaff
						.getId(), householdStaff.getRelationShipWithHead()
						.getId(), IdConversionShardUtil
						.getShardCodeById(householdStaff.getId()));
			}
		}
	}

	// 根据流动人口是否存在及存在状态，进行insert/update
	@Override
	public Countrymen copyAndUpdate(HouseholdStaff householdStaff) {
		FloatingPopulation floatingPopulation = floatingPopulationService
				.findFloatingPopulationByCardNoAndOrgId(householdStaff
						.getIdCardNo(), householdStaff.getOrganization()
						.getId());
		systemOperateLogService.addSystemOperateLog(
				NewBaseInfoTables.FLOATINGPOPULATION_KEY,
				householdStaff.getIdCardNo(), householdStaff.getOrganization(),
				householdStaff.getOrgInternalCode(),
				NewBaseInfoTables.HOUSEHOLDSTAFF_KEY,
				SystemOperateType.TOFLOATINGPOPULATION, null, null);
		if (null == floatingPopulation) {
			floatingPopulation = new FloatingPopulation();
			copyProperties(householdStaff, floatingPopulation);
			deleteHouseHoldStaff(householdStaff);
			// updateLogOut(householdStaff);
			FloatingPopulation newFloatingPopulation = floatingPopulationService
					.addFloatingPopulationBaseInfo(floatingPopulation);
			updateGroupFamily(householdStaff, newFloatingPopulation.getId(),
					newFloatingPopulation.getActualPopulationType());
		} else {
			if (floatingPopulation.isDeath()) {
				throw new BusinessValidationException(
						"此身份证号码在流动人口中为已死亡状态不允许转为流动人口!");
			} else {
				copyProperties(householdStaff, floatingPopulation);
				deleteHouseHoldStaff(householdStaff);
				// updateLogOut(householdStaff);
				floatingPopulation.setLogOut(IsEmphasis.Emphasis);
				FloatingPopulation newFloatingPopulation = floatingPopulationService
						.updateFloatingPopulationBaseInfo(floatingPopulation);
				updateGroupFamily(householdStaff,
						newFloatingPopulation.getId(),
						newFloatingPopulation.getActualPopulationType());
			}
		}
		shiftRelation(householdStaff, floatingPopulation);
		return floatingPopulation;
	}

	// 户籍转流口时删除户籍
	public void deleteHouseHoldStaff(HouseholdStaff householdStaff) {
		housePopulationMaintanceService.releaseHouse(
				PopulationCatalog.HOUSEHOULD_POPULATION,
				householdStaff.getId(), householdStaff.getHouseId());
		householdStaffDao.deleteHouseHoldStaff(householdStaff.getId());
		getRecoverDatasService().deleteActualPopulation(householdStaff);
		if (IsEmphasis.Emphasis.equals(householdStaff.getLogOut())) {
			PageInfoCacheThreadLocal.decrease(
					MemCacheConstant.getCachePageKey(householdStaff.getClass()
							.getSimpleName()), householdStaff, 1);
		}
	}

	// 户籍转流口时修改家庭关联关系
	private void updateGroupFamily(HouseholdStaff householdStaff, Long floatId,
			String newType) {
		Map map = new HashMap();
		map.put("oldId", householdStaff.getId());
		map.put("type", householdStaff.getActualPopulationType());
		map.put("newType", newType);
		map.put("newId", floatId);
		householdStaffDao.updateGroupFamily(map);
	}

	// 拷贝基本信息，户籍人口—>流动人口
	private void copyProperties(HouseholdStaff householdStaff,
			FloatingPopulation floatingPopulation) {
		try {
			PropertyUtil.copyPropertiesWithRecursion(ActualPopulation.class,
					floatingPopulation, householdStaff, new String[] { "id",
							"logOut", "actualPopulationType" });
		} catch (Exception e) {
			throw new ServiceValidationException("拷贝基本信息出错！", e);
		}
	}

	// 注销户籍人口
	// private void updateLogOut(HouseholdStaff householdStaff) {
	// LogoutDetail ld = new LogoutDetail();
	// ld.setLogout(IsEmphasis.IsNotEmphasis);
	// ld.setLogoutDate(new Date());
	// ld.setLogoutReason("已转为流动人口");
	// householdStaff.setLogoutDetail(ld);
	// householdStaffDao.updateLogOutPopulationById(
	// householdStaff.getLogoutDetail(), householdStaff.getId());
	// }

	// 根据户籍人口的人员关联关系，新增流动的人员关系{根据户籍人口的信息取人员关联关系，}
	private void shiftRelation(HouseholdStaff householdStaff,
			FloatingPopulation floatingPopulation) {
		List<PopulationTypeBean> populationTypes = populationTypeService
				.getPopulationTypeByActualIdAndType(householdStaff.getId(),
						householdStaff.getActualPopulationType());
		for (PopulationTypeBean populationType : populationTypes) {
			populationType.setActualId(floatingPopulation.getId());
			populationType.setActualType(floatingPopulation
					.getActualPopulationType());
			populationTypeService.addPopulationType(populationType);
			populationTypeService.deletePopulationTypeByActualIdAndType(
					householdStaff.getId(),
					householdStaff.getActualPopulationType());
		}
		GroupFamily groupFamily = groupFamilyService
				.getGroupFamilyByPopulation(householdStaff);
		groupFamily.setOrgInternalCode(floatingPopulation.getOrgInternalCode());
		if (groupFamily.getId() != null) {
			groupFamilyService.practiseGroupFamilyForSynchro(groupFamily,
					floatingPopulation.getId(),
					floatingPopulation.getActualPopulationType());
			groupFamilyService.deleteGroupFamilyMember(groupFamily.getId(),
					householdStaff.getId(),
					householdStaff.getActualPopulationType());
		}
	}

	@Override
	public Integer getCount(HouseholdStaffVo householdStaffVo) {
		Organization org = organizationDubboService
				.getOrganizationByOrgInternalCode(householdStaffVo
						.getOrgInternalCode());
		String shardCode = shardConversion.getShardCode(org.getId());
		return householdStaffDao.getCount(householdStaffVo, shardCode);
	}

	@Override
	public HouseholdStaff getHouseholdStaffByBaseInfoId(Long baseInfoId,
			Long orgId) {
		String shardCode = shardConversion.getShardCode(orgId);
		return householdStaffDao.getHouseholdStaffByBaseInfoId(baseInfoId,
				orgId, shardCode);
	}

	@Override
	public List<HouseholdStaff> getHouseholdStaffByBaseInfoId(Long baseInfoId) {
		List<HouseholdStaff> allHouseholdStaffList = new ArrayList<HouseholdStaff>();
		List<String> shardCodeList = shardConversion.getAllShardCode();
		for (String shardCode : shardCodeList) {
			allHouseholdStaffList.addAll(householdStaffDao
					.getHouseholdStaffByBaseInfoId(baseInfoId, shardCode));
		}
		if (allHouseholdStaffList.size() <= 0)
			allHouseholdStaffList = null;
		return allHouseholdStaffList;
	}

	@Override
	public boolean getActualPopulationHasTypes(Long id) {
		List<PopulationTypeBean> businessTypes = null;
		try {
			businessTypes = getPopulationRelationService()
					.getActualPopulationTypeBeans(id,
							BaseInfoTables.HOUSEHOLDSTAFF_KEY);
		} catch (Exception e) {
			throw new ServiceValidationException("查询人口信息业务类型失败", e);
		}
		return businessTypes == null || businessTypes.size() <= 0 ? false
				: true;
	}

	@Override
	public void deleteHouseholdStaffHouseFamilyInfo(Long houseFamilyId,
			Long orgId) {
		if (houseFamilyId == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			householdStaffDao.deleteHouseholdStaffHouseFamilyInfo(
					houseFamilyId, shardConversion.getShardCode(orgId));
		} catch (Exception e) {
			throw new ServiceValidationException("清空已注销的户籍人口户籍家庭信息错误", e);
		}
	}

	@Override
	public Countrymen updateBaseInfoIdCardNo(Countrymen countrymen,
			String idCardNo) {
		if (countrymen == null || countrymen.getIdCardNo() == null
				|| countrymen.getId() == null || idCardNo == null
				|| countrymen.getOrganization() == null
				|| countrymen.getOrganization().getId() == null
				|| "".equals(countrymen.getIdCardNo()) || "".equals(idCardNo)) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			// 是户主修改户籍家庭户主身份证号和地址
			updateCensusRegisterFamily(countrymen);
			// 是修改家庭信息家长身份证号
			updateGroupFamily(countrymen);
			// 业务人员相关（老年人、{青少年【青少年、少先队员、共青团员】由于是实时查询的不需要做处理}、育龄妇女、重点青少年）
			updateBusinessPopulation(countrymen, idCardNo);
			Countrymen result = baseInfoService.updateBaseInfoIdCardNo(
					countrymen, idCardNo);
			// 修改身份证号码所对应的出生日期和性别
			updateBirthdayAndGender(result.getId(), countrymen);
			return result;
		} catch (Exception e) {
			throw new ServiceValidationException("户籍人口修改身份证号错误", e);
		}
	}

	/**
	 * 修改身份证号码和性别
	 * 
	 * @param baseInfoId
	 */
	private void updateBirthdayAndGender(Long baseinfoId, Countrymen countrymen) {
		householdStaffDao.updateBirthdayAndGender(baseinfoId, countrymen
				.getBirthday(), countrymen.getGender().getId(), shardConversion
				.getShardCode(countrymen.getOrganization().getId()));
	}

	/**
	 * 是户主修改户籍家庭户主身份证号和地址(要判断是否是户主)
	 * 
	 * @param countrymen
	 */
	public void updateCensusRegisterFamily(Countrymen countrymen) {
		HouseholdStaff householdStaff = getHouseholdStaffById(countrymen
				.getId());
		if (householdStaff != null
				&& householdStaff.getRelationShipWithHead() != null) {
			// 是户主修改户籍家庭户主身份证号和地址
			if (getPropertyDictById(
					householdStaff.getRelationShipWithHead().getId())
					.getInternalId() == RelationShipWithHead.HEADER) {

				censusRegisterFamilyService
						.updateCensusRegisterFamilyIdcardById(fillCensusRegisterFamilyInfo(
								countrymen, householdStaff));
			}
		}
	}

	/**
	 * 填充户籍家庭需要修改的户主的身份证和地址
	 * 
	 * @param householdStaff
	 * @return
	 */
	private CensusRegisterFamily fillCensusRegisterFamilyInfo(
			Countrymen countrymen, HouseholdStaff householdStaff) {
		if (householdStaff == null || householdStaff.getIdCardNo() == null
				|| householdStaff.getCensusRegisterFamily() == null
				|| "".equals(householdStaff.getIdCardNo())
				|| householdStaff.getCensusRegisterFamily().getId() == null) {
			return null;
		}
		CensusRegisterFamily censusRegisterFamily = new CensusRegisterFamily();
		censusRegisterFamily.setId(householdStaff.getCensusRegisterFamily()
				.getId());
		censusRegisterFamily
				.setAccountNumber(householdStaff.getAccountNumber());
		censusRegisterFamily.setProvince(countrymen.getProvince());
		censusRegisterFamily.setCity(countrymen.getCity());
		censusRegisterFamily.setDistrict(countrymen.getDistrict());
		censusRegisterFamily.setOrganization(householdStaff.getOrganization());
		censusRegisterFamily.setIdCardNo(countrymen.getIdCardNo());
		return censusRegisterFamily;
	}

	@Override
	public Countrymen existBaseInfo(String actualPopulationType,
			String idCardNo, Long orgId) {
		if (!StringUtils.isNotBlank(actualPopulationType)
				|| !StringUtils.isNotBlank(idCardNo) || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			return baseInfoService.existBaseInfo(idCardNo);
		} catch (Exception e) {
			throw new ServiceValidationException("验证户籍人口重复错误", e);
		}
	}

	/**
	 * 注销时同步mongodb
	 */
	public List<Long> updateLogOutDetailByPopulationTypeAndIds(Long orgId,
			LogoutDetail logoutDetail, String populationType, Long[] ids) {
		List<Long> result = super
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						logoutDetail, populationType, ids);
		return result;
	}

	@Override
	public boolean moveMongodb(Integer beginId) {
		if (GridProperties.ISMONGODBENABLED) {
			householdStaffMongoDao.move(beginId);
		}
		return GridProperties.ISMONGODBENABLED;
	}

	/****
	 * 只使用一次
	 */
	@Override
	public void moveToShardTable() {
		long maxId = houseHoldStaffLogDao.getMaxHouseHoldStaffLogId() + 1;
		long startStep = 0;
		long count = 0;
		logger.error("=========开始迁移=============");
		long start = System.currentTimeMillis();
		while (true) {
			Long endId;
			Long startId = startStep;
			startStep += 2000;
			List<HouseHoldStaffLogVo> list = null;
			long startTime = System.currentTimeMillis();
			if (startStep >= maxId) {
				endId = maxId;
				list = houseHoldStaffLogDao
						.findHouseHoldStaffLogByStartIdAndEndId(startId, endId);
				handle(list);
				logger.error("==============增量迁移完成===========");
				logger.error("总共耗时:" + (System.currentTimeMillis() - start));
				break;
			} else {
				endId = startStep;
				list = houseHoldStaffLogDao
						.findHouseHoldStaffLogByStartIdAndEndId(startId, endId);
				handle(list);
			}
			if (list != null)
				count += list.size();
			logger.error("endId:" + endId + ",本次循环导入数据:" + list.size()
					+ ",已经导入总数：" + count + ",本次循环耗时:"
					+ (System.currentTimeMillis() - startTime));
		}
	}

	private void handle(List<HouseHoldStaffLogVo> list) {
		if (list != null && list.size() > 0) {
			for (HouseHoldStaffLogVo houseHoldStaffLogVo : list) {
				try {
					if (houseHoldStaffLogVo.getOPERATORTYPE().equalsIgnoreCase(
							"insert")) {
						HouseHoldStaffLogVo addHoldStaffLogVo = houseHoldStaffLogDao
								.getHouseHoldStaffById(houseHoldStaffLogVo
										.getID());
						if (addHoldStaffLogVo == null) {
							continue;
						}
						addHoldStaffLogVo.setID(houseHoldStaffLogVo.getNEWID());
						addHoldStaffLogVo.setTABLENAME(houseHoldStaffLogVo
								.getTABLENAME());
						houseHoldStaffLogDao
								.addHouseHoldStaff(addHoldStaffLogVo);
					} else if (houseHoldStaffLogVo.getOPERATORTYPE()
							.equalsIgnoreCase("update")) {
						HouseHoldStaffLogVo updateHoldStaffLogVo = houseHoldStaffLogDao
								.getHouseHoldStaffById(houseHoldStaffLogVo
										.getID());
						if (updateHoldStaffLogVo == null) {
							continue;
						}
						updateHoldStaffLogVo.setID(houseHoldStaffLogVo
								.getNEWID());
						updateHoldStaffLogVo.setTABLENAME(houseHoldStaffLogVo
								.getTABLENAME());
						houseHoldStaffLogDao
								.updateHouseHoldStaff(updateHoldStaffLogVo);
					} else if (houseHoldStaffLogVo.getOPERATORTYPE()
							.equalsIgnoreCase("delete")) {
						houseHoldStaffLogDao
								.deleteHouseHoldStaff(houseHoldStaffLogVo);
					}
				} catch (Exception e) {
					logger.error("迁移抛出异常:", e);
				}
			}
		}
	}

	@Override
	public List<HouseholdStaff> findHouseholdStaffByIds(List<Long> ids,
			String shardCode) {
		if (null == ids) {
			throw new BusinessValidationException("参数错误");
		}
		return householdStaffDao.findHouseholdStaffByIds(ids, shardCode);
	}

	@Override
	public List<HouseholdStaff> findhouseholdStaffWhenIsOldOptmize(
			List<String> shardCodes, int fetchNum) {
		List<HouseholdStaff> result = new ArrayList<HouseholdStaff>();
		for (String shardCode : shardCodes) {
			result = householdStaffDao
					.findhouseholdStaffWhenIsOldFetchHouseIdForMark(fetchNum,
							shardCode);
			if (!result.isEmpty()) {
				return result;
			}
		}
		return result;
	}

	@Override
	public List<HouseholdStaff> findhouseholdStaffWhenIsNurturesWomenOptmize(
			List<String> shardCodes, int fetchNum) {
		List<HouseholdStaff> result = new ArrayList<HouseholdStaff>();
		for (String shardCode : shardCodes) {
			result = householdStaffDao
					.findhouseholdStaffWhenIsNurturesWomenForMark(fetchNum,
							shardCode);
			if (!result.isEmpty()) {
				return result;
			}
		}
		return result;

	}

	@Override
	public Organization findOrgByAddress(List<String> shardCodes, Long address) {
		List<Organization> result = null;
		for (String shardCode : shardCodes) {
			result = householdStaffDao.findOrgByAddress(address, shardCode);
			if (result.size() > 0)
				return result.get(0);
		}
		return null;
	}

	@Override
	public List<Long> findBaseinfoIdByAccountNumber(String shardCode,
			String accountNumber) {
		if (!StringUtil.isStringAvaliable(shardCode)
				|| !StringUtil.isStringAvaliable(accountNumber)) {
			throw new BusinessValidationException("参数错误");
		}
		return householdStaffDao.findBaseinfoIdByAccountNumber(shardCode,
				accountNumber);
	}
}

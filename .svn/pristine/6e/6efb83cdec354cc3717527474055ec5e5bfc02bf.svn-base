package com.tianque.baseInfo.actualHouse.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.dao.ActualHouseDao;
import com.tianque.baseInfo.actualHouse.dao.ActualHouseFromMongodbDao;
import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.base.service.AddressInfoService;
import com.tianque.baseInfo.buildDatas.domain.Builddatas;
import com.tianque.baseInfo.buildDatas.domain.OpenLayersMapInfo;
import com.tianque.baseInfo.buildDatas.service.BuilddatasService;
import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.baseInfo.houseTrack.service.HouseTracksService;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.HousePopulationDao;
import com.tianque.domain.GisInfo;
import com.tianque.domain.HouseHasActualPopulation;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.CurrentAddressType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.SearchHouseInfoVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.gis.service.BuildingService;
import com.tianque.gis.service.HouseService;
import com.tianque.jms.OperateMode;
import com.tianque.mongodb.dao.HouseholdStaffMongoDao;
import com.tianque.plugin.analyzing.util.LingRateUtil;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.plugin.tqSearch.sqlMap.UpdateSqlMap;
import com.tianque.recoverDatas.service.RecoverDatasService;
import com.tianque.service.HouseHasActualPopulationService;
import com.tianque.service.HousesDependentService;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.PropertyUtil;
import com.tianque.validate.AbstractHouseValidator;

@Service("actualHouseService")
@Transactional
public class ActualHouseServiceImpl extends AbstractBaseService implements
		ActualHouseService, ActualHouseExternalService, BuildingService,
		HouseService {
	public final static Logger loggerForMongodb = LoggerFactory
			.getLogger(ActualHouseServiceImpl.class);
	public final static String DEFAUTL_PROPORVETION_VAL = "0.00%";
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private HousesDependentService housesDependentService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Qualifier("actualHouseValidator")
	@Autowired
	private AbstractHouseValidator<RentalHouse> updateValidator;

	@Autowired
	private ActualHouseDao houseInfoDao;

	@Autowired
	private HousePopulationDao housePopulationDao;

	@Autowired
	private BuilddatasService builddatasService;

	@Autowired
	private HouseHasActualPopulationService hasActualPopulationService;

	@Autowired
	private HouseTracksService houseTracksService;
	@Autowired
	private AddressInfoService addressInfoService;
	@Autowired
	private RecoverDatasService recoverDatasService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private ActualHouseFromMongodbDao actualHouseFromMongodbDao;
	/** 临时用一次 */
	@Autowired
	private HouseholdStaffMongoDao householdStaffMongoDao;
	@Autowired
	private ShardConversion shardConversion;
	@Autowired
	private CacheService cacheService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.ActualHouseService#addHouseInfo(com.tianque.domain
	 * .HouseInfo)
	 */
	@Transactional
	@Override
	public HouseInfo addHouseInfo(HouseInfo houseInfo) {
		autoFillHouseInfo(houseInfo);
		if (!ExcelImportHelper.isImport.get()) {
			this.actualHouseValidator(houseInfo);
		}
		try {
			if (houseInfo.getAddressType() == null
					|| houseInfo.getAddressType().getId() == null) {
				PropertyDict addressType = convertToPropertyDict(
						PropertyTypes.CURRENT_ADDRESS_TYPE,
						PropertyTypes.CURRENT_ADDRESS_TYPE_OTHER);
				houseInfo.setAddressType(addressType);
			}
			houseInfo.setShardCode(shardConversion.getShardCode(houseInfo
					.getOrganization().getId()));
			HouseInfo houseSave = houseInfoDao.addHouseInfo(houseInfo);
			// 轨迹
			// houseTracksService.addHouseTracks(houseSave,
			// BaseInfoTables.ACTUALHOUSE_KEY, HouseInitType.IMPORT, null,
			// HouseTracksOperationType.ADDT, "实有房屋新增");
			systemLogService.log(
					com.tianque.core.logger.Logger.INFO,
					ModelType.ACTUALHOUSE,
					OperatorType.ADD,
					ThreadVariable.getSession().getUserName()
							+ OperatorType.toString(OperatorType.ADD)
							+ ModelType.ACTUALHOUSE
							+ "位于"
							+ ControllerHelper.getOrganizationRelativeName(
									houseInfo.getOrganization().getId(),
									organizationDubboService) + "下的"
							+ houseInfo.getAddress(), null);
			return houseSave;
		} catch (Exception e) {
			logger.error("类ActualHouseServiceImpl的addHouseInfo方法出现异常，原因：", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("保存实有房屋信息出现错误");
			} else {
				return null;
			}
		}
	}

	private PropertyDict convertToPropertyDict(String propertyDomainName,
			String dictDisplayName) {
		if (!StringUtil.isStringAvaliable(dictDisplayName)) {
			return new PropertyDict();
		}
		return propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						propertyDomainName, dictDisplayName);
	}

	@Transactional
	@Override
	public HouseInfo addActualHouse(HouseInfo houseInfo) {
		autoFillHouseInfo(houseInfo);
		if (!ExcelImportHelper.isImport.get()) {
			this.actualHouseValidator(houseInfo);
		}
		try {
			HouseInfo houseSave = houseInfoDao.addHouseInfo(houseInfo);
			// 轨迹
			// houseTracksService.addHouseTracks(houseSave,
			// BaseInfoTables.ACTUALHOUSE_KEY, HouseInitType.IMPORT, null,
			// HouseTracksOperationType.ADDT, "实有房屋新增");
			return houseSave;
		} catch (Exception e) {
			logger.error("类ActualHouseServiceImpl的addHouseInfo方法出现异常，原因：", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("保存实有房屋信息出现错误");
			} else {
				return null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.ActualHouseService#updateHouseInfo(com.tianque.domain
	 * .HouseInfo)
	 */
	@Override
	public HouseInfo updateHouseInfo(HouseInfo houseInfo) {
		String shardCode = shardConversion.getShardCode(houseInfo
				.getOrganization());
		houseInfo.setShardCode(shardCode);
		joinAddress(houseInfo);
		if (null == houseInfo.getIsRentalHouse()) {
			houseInfo.setIsRentalHouse(false);
		}
		if (!ExcelImportHelper.isImport.get()) {
			this.actualHouseValidator(houseInfo);
		}

		List<HouseHasActualPopulation> actualPopulations = hasActualPopulationService
				.getHouseHasActualPopulationByHouseId(houseInfo.getId());
		if (actualPopulations != null) {
			for (HouseHasActualPopulation actualPopulation : actualPopulations) {
				addressInfoService
						.updateAddressByPopulationTypeAndPopulationId(
								actualPopulation.getPopulationType(),
								actualPopulation.getPopulationId(),
								houseInfo.getAddress());
			}
		}

		try {
			HouseInfo oldHouseInfo = houseInfoDao.getHouseInfoById(houseInfo
					.getId());
			houseInfo = houseInfoDao.updateHouseInfo(houseInfo);
			systemLogService.log(
					com.tianque.core.logger.Logger.INFO,
					ModelType.ACTUALHOUSE,
					OperatorType.UPDATE,
					ThreadVariable.getSession().getUserName()
							+ OperatorType.toString(OperatorType.UPDATE)
							+ ModelType.ACTUALHOUSE
							+ "位于"
							+ ControllerHelper.getOrganizationRelativeName(
									houseInfo.getOrganization().getId(),
									organizationDubboService)
							+ "下的"
							+ (houseInfo.getAddress().equals(
									oldHouseInfo.getAddress()) ? houseInfo
									.getAddress() : oldHouseInfo.getAddress()
									+ "->" + houseInfo.getAddress()),
					ObjectToJSON.convertJSON(houseInfo), oldHouseInfo.getId()
							+ "", houseInfo.getId() + "", oldHouseInfo
							.getAddress(), houseInfo.getAddress());
			return houseInfo;
		} catch (Exception e) {
			logger.error("类ActualHouseServiceImpl的updateHouseInfo方法出现异常，原因：", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改实有房屋信息出现错误");
			} else {
				return null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.ActualHouseService#findHouseInfosForPage(java.lang
	 * .Long, java.lang.Integer, java.lang.Integer, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public PageInfo<HouseInfo> findHouseInfosForPage(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		try {
			PageInfo<HouseInfo> pageInfo = null;
			pageInfo = houseInfoDao.findHouseInfosForPage(
					organizationDubboService.getSimpleOrgById(orgId)
							.getOrgInternalCode(), pageNum, pageSize, sidx,
					sord);
			// 房屋人数
			// for (HouseInfo houseInfo : pageInfo.getResult()) {
			// houseInfo.setMemberNum(housePopulationDao
			// .countLivingHouseAllActualPopulationInfos(houseInfo
			// .getId(), shardConversion
			// .getShardCode(houseInfo.getOrganization()
			// .getId())));
			// }
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ActualHouseServiceImpl的findHouseInfosForPage方法出现异常，原因：",
					"查询实有房屋信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.ActualHouseService#searchHouseInfos(java.lang.Integer
	 * , java.lang.Integer, java.lang.String, java.lang.String,
	 * com.tianque.domain.vo.SearchHouseInfoVo)
	 */
	@Override
	public PageInfo<HouseInfo> searchHouseInfos(Integer pageNum,
			Integer pageSize, String sidx, String sord,
			SearchHouseInfoVo searchHouseInfoVo) {
		try {
			if (searchHouseInfoVo != null
					&& searchHouseInfoVo.getOrganization() != null
					&& searchHouseInfoVo.getOrganization().getId() != null
					&& !StringUtil.isStringAvaliable(searchHouseInfoVo
							.getOrgInternalCode())) {
				Organization org = organizationDubboService
						.getSimpleOrgById(searchHouseInfoVo.getOrganization()
								.getId());
				if (org != null) {
					searchHouseInfoVo.setOrgInternalCode(org
							.getOrgInternalCode());
				}
			}
			PageInfo<HouseInfo> pageInfo = null;
			pageInfo = houseInfoDao.searchHouseInfos(pageNum, pageSize, sidx,
					sord, searchHouseInfoVo);
			// 房屋人数
			// for (HouseInfo houseInfo : pageInfo.getResult()) {
			// houseInfo.setMemberNum(housePopulationDao
			// .countLivingHouseAllActualPopulationInfos(houseInfo
			// .getId(), shardConversion
			// .getShardCode(houseInfo.getOrganization()
			// .getId())));
			// }
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ActualHouseServiceImpl的searchHouseInfos方法出现异常，原因：",
					"查询实有房屋信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.ActualHouseService#getHouseInfoByHouseCodeAndOrgId
	 * (java.lang .String)
	 */
	@Override
	public HouseInfo getHouseInfoByHouseCodeAndOrgId(String houseCode,
			Long orgId) {
		try {
			HouseInfo houseInfo = houseInfoDao
					.getHouseInfoByHouseCodeAndOrganizationId(houseCode, orgId);
			return houseInfo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ActualHouseServiceImpl的getHouseInfoByHouseCode方法出现异常，原因：",
					"查询实有房屋信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.ActualHouseService#deleteHouseInfosByIdList(java.
	 * util.List)
	 */
	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.HOUSEINFO_KEY)
	public void deleteHouseInfosByIdList(List<Long> idList) {
		if (idList.isEmpty()) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			List<HouseInfo> houseInfos = this.houseInfoDao
					.getHouseInfoByIds(idList);
			if (null != houseInfos) {
				recoverDatasService.deleteActualHouse(houseInfos);
				this.housesDependentService
						.synActualHouseToRentalHouseForDelete(houseInfos);
			}
			for (Long houseId : idList) {
				HouseInfo oldHouseInfo = houseInfoDao.getHouseInfoById(houseId);
				this.deleteHouseInfosById(houseId);
				// fateson add 解除实有房屋和人员的关系
				if (oldHouseInfo != null) {
					deleteHousePopulationRelation(houseId);
				}
				systemLogService.log(
						com.tianque.core.logger.Logger.INFO,
						ModelType.ACTUALHOUSE,
						OperatorType.DELETE,
						ThreadVariable.getSession().getUserName()
								+ OperatorType.toString(OperatorType.DELETE)
								+ ModelType.ACTUALHOUSE
								+ "位于"
								+ ControllerHelper.getOrganizationRelativeName(
										oldHouseInfo.getOrganization().getId(),
										organizationDubboService) + "->"
								+ oldHouseInfo.getAddress(), ObjectToJSON
								.convertJSON(oldHouseInfo),
						oldHouseInfo.getId() + "", "", oldHouseInfo
								.getAddress(), "");
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ActualHouseServiceImpl的deleteHouseInfosByIdList方法出现异常，原因：",
					"删除实有房屋出现错误", e);
		}
	}

	private void deleteHousePopulationRelation(Long houseId) {
		if (houseId == null) {
			return;
		}
		List<HouseHasActualPopulation> houseHasActualPopulations = hasActualPopulationService
				.getHouseHasActualPopulationByHouseId(houseId);
		for (HouseHasActualPopulation houseHasActualPopulation : houseHasActualPopulations) {
			hasActualPopulationService
					.deleteHouseHasActualPopulationByPopulationTypeAndHouseId(
							houseHasActualPopulation.getPopulationType(),
							houseHasActualPopulation.getHouseId(),
							houseHasActualPopulation.getPopulationId());
		}
		hasActualPopulationService
				.deleteHousehasimportantpopulationByHouseId(houseId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tianque.baseInfo.actualHouse.service.ActualHouseService#
	 * deleteHouseInfosById(java.lang.Long)
	 */
	@Override
	public void deleteHouseInfosById(Long houseId) {
		try {
			HouseInfo houseInfo = getActualHouseById(houseId);
			if (houseInfo == null) {
				return;
			}
			String shardCode = shardConversion.getShardCode(houseInfo
					.getOrganization());

			this.houseInfoDao.deleteHouseInfoById(houseInfo.getId(), shardCode);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ActualHouseServiceImpl的deleteHouseInfosById方法出现异常，原因：",
					"删除实有房屋信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.ActualHouseService#searchAllHouseInfos(com.tianque
	 * .domain.vo.SearchHouseInfoVo)
	 */
	@Override
	public List<HouseInfo> searchAllHouseInfos(
			SearchHouseInfoVo searchHouseInfoVo) {
		try {
			return houseInfoDao.searchAllHouseInfos(searchHouseInfoVo);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ActualHouseServiceImpl的searchAllHouseInfos方法出现异常，原因：",
					"获取实有房屋信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.ActualHouseService#hasDuplicateHouseInfo(java.lang
	 * .Long, java.lang.String, java.lang.Long)
	 */
	@Override
	public boolean hasDuplicateHouseInfo(Long orgId, String houseCode,
			Long houseId) {
		try {
			HouseInfo exsited = houseInfoDao
					.getHouseInfoByHouseCodeAndOrganizationId(houseCode, orgId);
			return houseId == null ? exsited != null
					: (exsited != null && !houseId.equals(exsited.getId()));
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ActualHouseServiceImpl的hasDuplicateHouseInfo方法出现异常，原因：",
					"获取实有房屋信息出现错误", e);
		}
	}

	@Override
	public boolean hasDuplicateHouseInfoForAddress(Long orgId,
			String houseCode, Long houseId) {
		try {
			HouseInfo exsited = houseInfoDao
					.getHouseInfoByHouseAddressAndOrganizationId(houseCode,
							orgId);
			return houseId == null ? exsited != null
					: (exsited != null && !houseId.equals(exsited.getId()));
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ActualHouseServiceImpl的hasDuplicateHouseInfo方法出现异常，原因：",
					"获取实有房屋信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.ActualHouseService#getHouseInfoById(java.lang.Long)
	 */
	@Override
	public HouseInfo getHouseInfoById(Long id) {
		try {
			return houseInfoDao.getHouseInfoById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ActualHouseServiceImpl的getHouseInfoById方法出现异常，原因：",
					"获取实有房屋信息出现错误", e);
		}
	}

	private void autoFillOrganizationInternalCode(HouseInfo houseInfo) {
		if (houseInfo.getOrganization() == null) {
			throw new BusinessValidationException("请指定的网格");
		}
		Organization org = organizationDubboService.getSimpleOrgById(houseInfo
				.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		houseInfo.setOrgInternalCode(org.getOrgInternalCode());
	}

	/**
	 * 数据校验
	 */
	private void actualHouseValidator(HouseInfo houseInfo) {
		RentalHouse rentalHouse = new RentalHouse();
		PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class, rentalHouse,
				houseInfo);
		autoFillOrganizationInternalCode(houseInfo);
		rentalHouse.setAddressType(propertyDictService
				.getPropertyDictById(rentalHouse.getAddressType().getId()));
		ValidateResult actualHouseBaseInfoValidator = updateValidator
				.validateHouseBaseInfo(rentalHouse);
		if (actualHouseBaseInfoValidator.hasError()) {
			throw new BusinessValidationException(
					actualHouseBaseInfoValidator.getErrorMessages());
			// } else if
			// (hasDuplicateHouseInfo(houseInfo.getOrganization().getId(),
			// houseInfo.getHouseCode(), houseInfo.getId())) {
			// throw new BusinessValidationException("该网格下已存在住房编号");
		}
		ValidateResult actualHouseSpecialValidator = updateValidator
				.validateSpecializedInfo(rentalHouse);
		if (actualHouseSpecialValidator.hasError()) {
			throw new BusinessValidationException(
					actualHouseSpecialValidator.getErrorMessages());
		}
		autoFillChinesePinyin(houseInfo);
	}

	private void autoFillChinesePinyin(HouseInfo houseInfo) {
		if (!StringUtils.isEmpty(houseInfo.getName())) {
			Map<String, String> pinyin = Chinese2pinyin
					.changeChinese2Pinyin(houseInfo.getName());
			houseInfo.setSimplePinyin(pinyin.get("simplePinyin"));
			houseInfo.setFullPinyin(pinyin.get("fullPinyin"));
		}
	}

	@Override
	public Integer countHouseByBuildingId(Long buildingId) {
		try {
			Builddatas builddatas = builddatasService
					.getBuilddatasById(buildingId);
			String shardCode = null;
			if (builddatas != null) {
				shardCode = shardConversion.getShardCode(builddatas
						.getOrganization());
			}
			return houseInfoDao.countHouseByBuildingId(buildingId, shardCode);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ActualHouseServiceImpl的countHouseByBuildingId方法出现异常，原因：",
					"获取实楼宇中有多少个房屋出现错误", e);
		}
	}

	@Override
	public PageInfo findHouseInfosForPage(Long orgId, String address,
			Integer page, Integer rows, String sidx, String sord, String tag) {
		try {
			return houseInfoDao.findHouseInfosForPageByTag(
					organizationDubboService.getSimpleOrgById(orgId)
							.getOrgInternalCode(), page, rows, sidx, sord,
					address);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ActualHouseServiceImpl的findHouseInfosForPage方法出现异常，原因：",
					"GIS视图中获取房子列表时出现错误", e);
		}
	}

	@Override
	public List<Long> updateHouseGisInfo(List<Long> houseIds, Long buildingId,
			Double pointX, Double pointY) {
		GisInfo gisInfo = new GisInfo();
		gisInfo.setBuildingId(buildingId);
		gisInfo.setCenterX(pointX);
		gisInfo.setCenterY(pointY);
		for (Long id : houseIds) {
			updateHouseInfoForGis(id, gisInfo);
		}
		return houseIds;
	}

	@Override
	public HouseInfo updateHouseInfoForGis(Long id, GisInfo gisInfo) {
		HouseInfo houseInfo = new HouseInfo();
		houseInfo.setId(id);
		houseInfo.setGisInfo(gisInfo);
		return houseInfoDao.updateHousePropertyForGis(houseInfo);
	}

	@Override
	public PageInfo findHouseInfosByBuildingIdForPage(Long buildingId,
			Integer page, Integer rows, String sidx, String sord) {
		return houseInfoDao.findHouseInfosByBuildingIdForPage(buildingId, page,
				rows, sidx, sord);
	}

	@Override
	public void unbindHousePropertyById(Long houseId) {
		houseInfoDao.unbindHousePropertyById(houseId);
	}

	@Override
	public HouseInfo addHouseInfoForPopulation(HouseInfo houseInfo) {
		ExcelImportHelper.isImport.set(false);
		return this.addHouseInfo(houseInfo);
	}

	@Override
	public HouseInfo updateHouseInfoForPopulation(HouseInfo houseInfo) {
		autoFillHouseInfo(houseInfo);
		ExcelImportHelper.isImport.set(false);
		if (!ExcelImportHelper.isImport.get()) {
			this.actualHouseValidator(houseInfo);
		}
		try {

			List<HouseHasActualPopulation> actualPopulations = hasActualPopulationService
					.getHouseHasActualPopulationByHouseId(houseInfo.getId());
			if (actualPopulations != null) {
				for (HouseHasActualPopulation actualPopulation : actualPopulations) {
					addressInfoService
							.updateAddressByPopulationTypeAndPopulationId(
									actualPopulation.getPopulationType(),
									actualPopulation.getPopulationId(),
									houseInfo.getAddress());
				}
			}
			return this.houseInfoDao.updateHouseInfoForPopulation(houseInfo);
		} catch (Exception e) {
			logger.error(
					"类ActualHouseServiceImpl的updateHouseInfoForPopulation方法出现异常，原因：",
					e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改实口实有房屋信息时出现错误");
			} else {
				return null;
			}
		}
	}

	private void autoFillHouseInfo(HouseInfo houseInfo) {
		joinAddress(houseInfo);
		autoFillOrganizationInternalCode(houseInfo);
		autoFillChinesePinyin(houseInfo);
		if (null == houseInfo.getIsRentalHouse()) {
			houseInfo.setIsRentalHouse(false);
		}
	}

	private void joinAddress(HouseInfo houseInfo) {
		if (houseInfo.getAddressType() == null
				|| propertyDictService.getPropertyDictById(houseInfo
						.getAddressType().getId()) == null
				|| propertyDictService.getPropertyDictById(
						houseInfo.getAddressType().getId()).getInternalId() == CurrentAddressType.OTHER) {
			return;
		}
		// StringBuffer address = new StringBuffer();
		// if (null != houseInfo.getCommunity()
		// && !"".equals(houseInfo.getCommunity()))
		// address.append(houseInfo.getCommunity()).append("小区");
		// if (null != houseInfo.getBlock() && !"".equals(houseInfo.getBlock()))
		// address.append(houseInfo.getBlock()).append("幢");
		// if (null != houseInfo.getUnit() && !"".equals(houseInfo.getUnit()))
		// address.append(houseInfo.getUnit()).append("单元");
		// if (null != houseInfo.getRoom() && !"".equals(houseInfo.getRoom()))
		// address.append(houseInfo.getRoom()).append("室");
		houseInfo.setAddress(houseInfo.getAddress());
	}

	@Override
	public List<HouseInfo> searchMapScope(GisInfo minOption, GisInfo maxOption) {
		return houseInfoDao.searchMapScope(minOption, maxOption);
	}

	/************************************ 以下是实有房屋与楼宇绑定 ****************************************/

	@Override
	public PageInfo<HouseInfo> findHouseInfosByBuilddatasIdForPage(
			Long builddatasId, Integer pageNum, Integer pageSize, String sidx,
			String sord, String shardCode) {
		return houseInfoDao.findHouseInfosByBuilddatasIdForPage(builddatasId,
				pageNum, pageSize, sidx, sord, shardCode);
	}

	@Override
	public PageInfo<HouseInfo> findUnBoundedByOrgId(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String queryStr) {
		return houseInfoDao.findHouseInfosListForPage(organizationDubboService
				.getSimpleOrgById(orgId).getOrgInternalCode(), pageNum,
				pageSize, sidx, sord, queryStr);
	}

	@Override
	@SolrServerIndex(mode = OperateMode.EDIT, value = UpdateSqlMap.HOUSEINFO_KEY)
	public List<Long> updateHouseInfo(List<Long> houseIds, Long builddatasId) {
		if (houseIds == null) {
			throw new BusinessValidationException("id没有获得");
		}
		List<Long> delPersonIdList = new ArrayList<Long>();
		delPersonIdList.clear();
		delPersonIdList.addAll(houseIds);

		Builddatas builddatas = new Builddatas();
		GisInfo gisInfo = new GisInfo();
		OpenLayersMapInfo openLayersMapInfo = new OpenLayersMapInfo();
		if (builddatasId != null) {
			builddatas = builddatasService.getBuilddatasById(builddatasId);

			gisInfo.setCenterX(builddatas.getCenterx());
			gisInfo.setCenterY(builddatas.getCentery());

			openLayersMapInfo = builddatas.getOpenLayersMapInfo();
			if (openLayersMapInfo == null) {
				openLayersMapInfo = new OpenLayersMapInfo();
			} else {
				openLayersMapInfo.setFeatureId(builddatas.getBuildingid());
			}

		}

		for (Long id : delPersonIdList) {
			HouseInfo getHouseInfo = getHouseInfoById(id);
			getHouseInfo.setBuilddatasId(builddatasId);
			getHouseInfo.setGisInfo(gisInfo);
			getHouseInfo.setOpenLayersMapInfo(openLayersMapInfo);
			houseInfoDao.updateBuildDatasId(getHouseInfo, shardConversion
					.getShardCode(getHouseInfo.getOrganization()));
		}
		return delPersonIdList;
	}

	@Override
	public List<HouseInfo> findHouseInfosListByBuildingId(Long buildingId) {
		if (buildingId == null) {
			throw new BusinessValidationException("参数为空");
		}
		Builddatas builddatas = builddatasService.getBuilddatasById(buildingId);
		String shardCode = shardConversion.getShardCode(builddatas
				.getOrganization());
		List<HouseInfo> houseList = houseInfoDao
				.findHouseInfosListByBuildingId(buildingId, shardCode);
		setBackGround(houseList);
		return houseList;
	}

	private void setBackGround(List<HouseInfo> houseList) {
		for (HouseInfo houseInfo : houseList) {
			if (houseInfo.getHouseUses() != null) {
				PropertyDict houseUses = propertyDictService
						.getPropertyDictById(houseInfo.getHouseUses().getId());
				if (houseUses != null) {
					houseInfo.setHouseUses(houseUses);
				} else {
					houseInfo.setBackGround("");
				}
			}
		}
	}

	@Override
	public Long countHouseInfoByBuilddatasidAndHouseUses(Long builddatasid,
			Long houseUse) {
		if (builddatasid == null || houseUse == null) {
			throw new BusinessValidationException("buildingId不能为空或房屋用途不能为空");
		}
		Builddatas builddatas = builddatasService
				.getBuilddatasById(builddatasid);
		return houseInfoDao.countHouseInfoByBuilddatasidAndHouseUse(
				builddatasid, houseUse,
				shardConversion.getShardCode(builddatas.getOrganization()));
	}

	@Override
	public Long countHouseInfoByBuilddatasidAndisrentalhouse(Long builddatasid,
			int isrentalhouse) {
		if (builddatasid == null) {
			throw new BusinessValidationException("buildingId不能为空");
		}
		return houseInfoDao.countHouseInfoByBuilddatasidAndisrentalhouse(
				builddatasid, isrentalhouse);
	}

	@Override
	public void changeHouseCode(Long orgId, String oldHouseCode,
			String houseCode) {
		houseInfoDao.changeHouseCode(orgId, oldHouseCode, houseCode);
	}

	@Override
	public int exist(Long orgId, String houseCode) {
		return houseInfoDao.countHouseByOrgIdAndHouseCode(orgId, houseCode);
	}

	@Override
	public List<HouseInfo> findHouseInfosByHouseCodeAndOrganizationId(
			String houseCode, Long organizationId) {
		return houseInfoDao.findHouseInfosByHouseCodeAndOrganizationId(
				houseCode, organizationId);
	}

	@Override
	public PageInfo findHouseInfosForPage(Long orgId, Long houseId,
			Integer page, Integer rows, String sidx, String sord) {

		try {
			PageInfo<HouseInfo> pageInfo = houseInfoDao.findHouseInfosForPage(
					orgId, houseId, page, rows, sidx, sord);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ActualHouseServiceImpl的findHouseInfosForPage方法出现异常，原因：",
					"查询实有房屋信息出现错误", e);
		}
	}

	@Override
	public HouseInfo getHouseInfoByHouseAddressAndOrgId(String address,
			Long orgId) {

		try {
			HouseInfo houseInfo = houseInfoDao
					.getHouseInfoByHouseAddressAndOrganizationId(address, orgId);
			return houseInfo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ActualHouseServiceImpl的getHouseInfoByHouseCode方法出现异常，原因：",
					"查询实有房屋信息出现错误", e);
		}
	}

	@Override
	public void deleteActualHouseByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			for (int i = 0; i < ids.length; i++) {
				this.deleteHouseInfosById(ids[i]);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ActualHouseServiceImpl的deleteActualHouseByIds方法出现异常，原因：",
					"删除实有房屋信息出现错误", e);
		}
	}

	@Override
	public List<HouseInfo> findHouseInfosByHouseAddressAndOrgId(String address,
			Long organizationId) {
		return houseInfoDao.findHouseInfosByHouseAddressAndOrgId(address,
				organizationId);
	}

	/**
	 * 
	 * @Title: findHouseInfosByHouseAddressAndOrgIdForMobile
	 * @Description: TODO为手机端新增一个查询地址的方法
	 * @param @param address
	 * @param @param id
	 * @param @return 设定文件
	 * @return List<HouseInfo> 返回类型
	 * @author wanggz
	 * @date 2014-6-20 上午03:20:18
	 */
	@Override
	public PageInfo<HouseInfo> findHouseInfosByHouseAddressAndOrgIdForMobile(
			String address, Long id) {
		PageInfo<HouseInfo> pageInfo = null;
		try {
			pageInfo = houseInfoDao
					.findHouseInfosByHouseAddressAndOrgIdForMobile(address, id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ActualHouseServiceImpl的findHouseInfosByHouseAddressAndOrgIdForMobile方法出现异常，原因：",
					"查询数据出现错误", e);
		}
		return pageInfo;
	}

	@Override
	public Integer getCount(SearchHouseInfoVo searchHouseInfoVo) {

		return houseInfoDao.getCount(searchHouseInfoVo);
	}

	@Override
	public List<HouseInfo> checkIsHasHouseByHouseCodeAndOrgId(String houseCode,
			Long organizationId) {
		return houseInfoDao.checkIsHasHouseByHouseCodeAndOrgId(houseCode,
				organizationId);
	}

	@Override
	public PageInfo<HouseInfo> findHouseInfoByHouseAddressAndOrgId(
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String address, Long orgId, Long id) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构为空");
		}
		if (address == null) {
			throw new BusinessValidationException("房屋准确地址为空");
		}

		return houseInfoDao.findHouseInfoByHouseAddressAndOrgId(pageNum,
				pageSize, sidx, sord, address, orgId, id);
	}

	@Override
	public void updateSimpleHouseInfo(HouseInfo houseInfo) {
		houseInfoDao.updateHouseInfoSimple(houseInfo);
	}

	@Override
	public HouseInfo getActualHouseById(Long id) {
		try {
			return houseInfoDao.getHouseInfoById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ActualHouseServiceImpl的getHouseInfoById方法出现异常，原因：",
					"获取实有房屋信息出现错误", e);
		}
	}

	@Override
	public void moveToMongodb() {
		long maxId = houseInfoDao.getMaxHouseInfoId() + 1;
		long startStep = 0;
		long count = 0;
		loggerForMongodb.error("开始迁移房屋");
		while (true) {
			Long endId;
			Long startId = startStep;
			startStep += 2000;
			List<HouseInfo> list = null;
			long startTime = System.currentTimeMillis();
			if (startStep >= maxId) {
				endId = maxId;
				list = houseInfoDao.findHouseInfosByStartIdAndEndId(startId,
						endId);
				actualHouseFromMongodbDao.batchSave(list);
				loggerForMongodb.error("迁移房屋完成");
				loggerForMongodb.error("endId:" + endId + ",本次导入："
						+ list.size() + ",本次循环耗时:"
						+ (System.currentTimeMillis() - startTime));
				break;
			} else {
				endId = startStep;
				list = houseInfoDao.findHouseInfosByStartIdAndEndId(startId,
						endId);
				actualHouseFromMongodbDao.batchSave(list);
			}
			if (list != null)
				count += list.size();
			loggerForMongodb.error("endId:" + endId + ",本次循环导入数据:"
					+ list.size() + ",已经导入总数：" + count + ",本次循环耗时:"
					+ (System.currentTimeMillis() - startTime));
		}
	}

	@Override
	public void deleteMongodb() {
		SimpleDateFormat formatDate = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date startDate = null;
		try {
			startDate = formatDate.parse("2014-12-22 15:00:00");
		} catch (ParseException e) {
		}
		List<Long> houseHoldStaffIds = houseInfoDao
				.findIdsFromRecoverDataInfosByStartDateAndType(startDate,
						NewBaseInfoTables.HOUSEHOLDSTAFF_KEY);
		List<Long> houseInfoIds = houseInfoDao
				.findIdsFromRecoverDataInfosByStartDateAndType(startDate,
						NewBaseInfoTables.ACTUALHOUSE_KEY);
		if (houseHoldStaffIds != null && houseHoldStaffIds.size() > 0) {
			loggerForMongodb.error("开始删除户籍");
			householdStaffMongoDao.delete(houseHoldStaffIds);
			loggerForMongodb.error("删除户籍结束,本次删除总数：" + houseHoldStaffIds.size());
		}
		if (houseInfoIds != null && houseInfoIds.size() > 0) {
			loggerForMongodb.error("开始删除房屋");
			actualHouseFromMongodbDao.delete(houseInfoIds);
			loggerForMongodb.error("删除房屋结束,本次删除总数：" + houseInfoIds.size());
		}
	}

	// 房屋人数
	@Override
	public void updateHouseInfoMemberNum(String shardCode, Long houseId, int num) {
		houseInfoDao.updateHouseInfoMemberNum(shardCode, houseId, num);
	}

	// 房屋人数
	@Override
	public Long getLogOutByPopulationTypeAndPopulationId(String shardCode,
			String populationType, Long populationId) {
		return houseInfoDao.getLogOutByPopulationTypeAndPopulationId(shardCode,
				populationType, populationId);
	}

	@Override
	public String dealHousePeopleProportion(Long orgId, String houseType) {
		if (orgId == null) {
			throw new BusinessValidationException("查询失败，未获得组织机构参数");
		}
		if (!StringUtil.isStringAvaliable(houseType)) {
			throw new BusinessValidationException("查询失败，未获得房屋类别参数");
		}
		String key = "houseProportion_" + houseType + "_" + orgId;
		String result = (String) cacheService.get(key);
		if (StringUtil.isStringAvaliable(result)) {
			return result;
		}

		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);

		if (organization == null) {
			throw new BusinessValidationException("查询未得到组织机构信息");
		}

		String shardCode = shardConversion.getShardCode(organization.getId());

		Long buildingUses = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.BUILDING_USES, ModulTypes.RESIDENTIAL)
				.getId();

		float houseCount = houseInfoDao.countResidential(
				organization.getOrgInternalCode(), buildingUses, shardCode,
				houseType);
		float proportionCount = houseInfoDao.countRelation(
				organization.getOrgInternalCode(), buildingUses, shardCode,
				houseType);

		if (houseCount == 0 || proportionCount == 0) {
			result = DEFAUTL_PROPORVETION_VAL;
		} else {
			result = LingRateUtil.getLingRate(proportionCount, houseCount);
		}
		cacheService.set(key, result, 36000);
		return result;
	}
}

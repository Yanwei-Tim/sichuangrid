package com.tianque.baseInfo.rentalHouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.dao.ActualHouseDao;
import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.houseTrack.service.HouseTracksService;
import com.tianque.baseInfo.rentalHouse.dao.RentalHouseDao;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.HousePopulationDao;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.CurrentAddressType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.SearchHouseInfoVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.service.HousesDependentService;
import com.tianque.service.KeyPlaceService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.shard.util.ShardConversion;
import com.tianque.solr.domain.DocumentType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.PluginServiceHelpUtil;
import com.tianque.util.PropertyUtil;
import com.tianque.validate.AbstractHouseValidator;

@Service("rentalHouseService")
public class RentalHouseServiceImpl extends AbstractBaseService implements
		RentalHouseService, RentalHouseExternalService {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Qualifier("rentalHouseValidator")
	@Autowired
	private AbstractHouseValidator<RentalHouse> updateValidator;

	@Autowired
	private RentalHouseDao rentalHouseDao;
	@Autowired
	private ActualHouseDao actualHouseDao;
	@Autowired
	private HousePopulationDao housePopulationDao;
	@Autowired
	private HousesDependentService housesDependentService;
	@Autowired
	private KeyPlaceService placeService;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private HouseTracksService houseTracksService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private ShardConversion shardConversion;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.RentalHouseService#addHouseInfo(com.tianque.domain
	 * .RentalHouse)
	 */
	@Transactional
	@Override
	public RentalHouse addHouseInfo(RentalHouse rentalHouse) {
		autoFillChinesePinyin(rentalHouse);
		if (!ExcelImportHelper.isImport.get()) {
			rentalHouse.setAddressType(propertyDictService
					.getPropertyDictById(rentalHouse.getAddressType().getId()));
		}
		try {
			Organization org = organizationDubboService.getSimpleOrgById(rentalHouse
					.getOrganization().getId());
			if (null != org) {
				rentalHouse.setOrgInternalCode(org.getOrgInternalCode());
			}
			rentalHouse = rentalHouseDao
					.addHouseInfo(autoFillDefaule(rentalHouse));
			placeService.execute(DocumentType.RENTALHOUSE,
					OperateMode.ADD.toString(), rentalHouse);
			// houseTracksService.addHouseTracks(rentalHouse,
			// BaseInfoTables.RENTALHOUSE_KEY, HouseInitType.IMPORT, null,
			// HouseTracksOperationType.ADDT, "出租房新增");
			systemLogService.log(
					com.tianque.core.logger.Logger.INFO,
					ModelType.TEMPORARYRESIDENT,
					OperatorType.ADD,
					ThreadVariable.getSession().getUserName()
							+ OperatorType.toString(OperatorType.ADD)
							+ ModelType.TEMPORARYRESIDENT
							+ "位于"
							+ ControllerHelper.getOrganizationRelativeName(
									rentalHouse.getOrganization().getId(),
									organizationDubboService) + "下的"
							+ rentalHouse.getAddress(), null);
			return rentalHouse;
		} catch (Exception e) {
			logger.error("类ActualHouseServiceImpl的addHouseInfo方法出现异常，原因：", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("保存出租房信息出现错误");
			} else {
				return null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.RentalHouseService#updateHouseBaseInfo(com.tianque
	 * .domain.RentalHouse)
	 */
	@Transactional
	@Override
	public RentalHouse updateHouseBaseInfo(RentalHouse rentalHouse) {
		autoFillChinesePinyin(rentalHouse);
		joinAddress(rentalHouse);
		// 通过出租屋的ID去查询出租屋信息，如果是注销状态，先取消注销状态再修改
		RentalHouse rentalHouseInfo = rentalHouseDao
				.getHouseInfoById(rentalHouse.getId());
		if (rentalHouseInfo.getIsEmphasis() != null
				&& rentalHouseInfo.getIsEmphasis() == 1) {
			rentalHouse.setIsEmphasis(0l);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("houseId", rentalHouse.getId());
			map.put("isEmphasis", rentalHouse.getIsEmphasis());
			rentalHouseDao.updateEmphasiseById(map);
		}

		if (!ExcelImportHelper.isImport.get()) {
			this.rentalHouseBaseInfoValidator(rentalHouse);
		}
		try {
			RentalHouse oldRentalHouse = rentalHouseDao
					.getRentalHouseInfoByHouseId(rentalHouse.getHouseId());
			rentalHouse = rentalHouseDao.updateHouseBaseInfo(rentalHouse);
			systemLogService.log(
					com.tianque.core.logger.Logger.INFO,
					ModelType.TEMPORARYRESIDENT,
					OperatorType.UPDATE,
					ThreadVariable.getSession().getUserName()
							+ OperatorType.toString(OperatorType.UPDATE)
							+ ModelType.TEMPORARYRESIDENT
							+ "位于"
							+ ControllerHelper.getOrganizationRelativeName(
									rentalHouse.getOrganization().getId(),
									organizationDubboService)
							+ "下的"
							+ (rentalHouse.getAddress().equals(
									oldRentalHouse.getAddress()) ? rentalHouse
									.getAddress() : oldRentalHouse.getAddress()
									+ "->" + rentalHouse.getAddress()),
					ObjectToJSON.convertJSON(rentalHouse),
					oldRentalHouse.getId() + "", rentalHouse.getId() + "",
					oldRentalHouse.getAddress(), rentalHouse.getAddress());
			return rentalHouse;
		} catch (Exception e) {
			logger.error(
					"类RentalHouseServiceImpl的updateHouseBaseInfo方法出现异常，原因：", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改出租房基本信息出现错误");
			} else {
				return null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.RentalHouseService#updateHouseBusinessInfo(com.tianque
	 * .domain.RentalHouse)
	 */
	@Override
	public RentalHouse updateHouseBusinessInfo(RentalHouse rentalHouse) {
		autoFillChinesePinyin(rentalHouse);
		if (null == rentalHouse.getIsFireChannels()) {
			rentalHouse.setIsFireChannels(0L);
		}
		if (null == rentalHouse.getIsSafetyChannel()) {
			rentalHouse.setIsSafetyChannel(0L);
		}
		if (null == rentalHouse.getIsSignGuarantee()) {
			rentalHouse.setIsSignGuarantee(0L);
		}
		if (!ExcelImportHelper.isImport.get()) {
			this.rentalHouseSpecialValidator(rentalHouse);
		}
		try {
			return rentalHouseDao.updateHouseBusinessInfo(rentalHouse);
		} catch (Exception e) {
			logger.error(
					"类RentalHouseServiceImpl的updateHouseBaseInfo方法出现异常，原因：", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改出租房业务信息出现错误");
			} else {
				return null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.RentalHouseService#findHouseInfosForPage(java.lang
	 * .Long, java.lang.Integer, java.lang.Integer, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public PageInfo<RentalHouse> findHouseInfosForPage(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		try {
			return rentalHouseDao.findHouseInfosForPage(organizationDubboService
					.getSimpleOrgById(orgId).getOrgInternalCode(), pageNum,
					pageSize, sidx, sord);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类RentalHouseServiceImpl的findHouseInfosForPage方法出现异常，原因：",
					"查询出租房信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.RentalHouseService#searchHouseInfos(java.lang.Integer
	 * , java.lang.Integer, java.lang.String, java.lang.String,
	 * com.tianque.domain.vo.SearchHouseInfoVo)
	 */
	@Override
	public PageInfo<RentalHouse> searchHouseInfos(Integer pageNum,
			Integer pageSize, String sidx, String sord,
			SearchHouseInfoVo searchHouseInfoVo) {
		try {
			PageInfo<RentalHouse> pageInfo = rentalHouseDao.searchHouseInfos(
					pageNum, pageSize, sidx, sord, searchHouseInfoVo);
			for (RentalHouse rentalHouse : pageInfo.getResult()) {
				rentalHouse.setMemberNum(housePopulationDao
						.findLivingHouseAllActualPopulationInfos(
								rentalHouse.getHouseId(),
								shardConversion.getShardCode(rentalHouse
										.getOrganization().getId())).size());
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类RentalHouseServiceImpl的searchHouseInfos方法出现异常，原因：",
					"查询出租房信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.RentalHouseService#deleteHouseInfosByIdList(java.
	 * util.List)
	 */
	@Override
	public void deleteRentalHouseByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			for (Long id : ids) {
				RentalHouse rentalHouse = rentalHouseDao.getHouseInfoById(id);
				systemLogService
						.log(com.vladium.logging.Logger.INFO,
								ModelType.TEMPORARYRESIDENT,
								OperatorType.DELETE,
								ThreadVariable.getSession().getUserName()
										+ OperatorType
												.toString(OperatorType.DELETE)
										+ ModelType.TEMPORARYRESIDENT
										+ "位于"
										+ organizationDubboService
												.getOrganizationRelativeNameByRootOrgIdAndOrgId(
														rentalHouse
																.getOrganization()
																.getId(),
														OrganizationServiceHelper
																.getRootOrg(
																		organizationDubboService)
																.getId())
										+ "->" + rentalHouse.getAddress(),
								ObjectToJSON.convertJSON(rentalHouse));
				this.rentalHouseDao.deleteHouseInfoById(id);
				if (rentalHouse != null && rentalHouse.getHouseId() != null) {
					HouseInfo houseInfo = actualHouseDao
							.getHouseInfoById(rentalHouse.getHouseId());
					if (houseInfo != null) {
						houseInfo.setShardCode(shardConversion
								.getShardCode(houseInfo.getOrganization()));
						houseInfo.setIsRentalHouse(false);
						actualHouseDao.updateHouseInfo(houseInfo);
					}
				}

				try {
					PluginServiceHelpUtil
							.doService(
									"routerService",
									"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
									new Class[] { String.class, Long.class },
									BaseInfoTables.RENTALHOUSE_KEY, id);
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类RentalHouseServiceImpl的deleteHouseInfosByIdList方法出现异常，原因：",
					"删除出租房信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.RentalHouseService#searchAllHouseInfos(com.tianque
	 * .domain.vo.SearchHouseInfoVo)
	 */
	@Override
	public List<RentalHouse> searchAllHouseInfos(
			SearchHouseInfoVo searchHouseInfoVo) {
		try {
			return rentalHouseDao.searchAllHouseInfos(searchHouseInfoVo);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类RentalHouseServiceImpl的searchAllHouseInfos方法出现异常，原因：",
					"获取出租房信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.RentalHouseService#hasDuplicateHouseInfo(java.lang
	 * .Long, java.lang.String, java.lang.Long)
	 */
	@Override
	public boolean hasDuplicateHouseInfo(Long orgId, String houseCode,
			Long houseId) {
		try {
			// fateson add 使用地址来 判断唯一
			// RentalHouse exsited = rentalHouseDao
			// .getHouseInfoByHouseCodeAndOrganizationId(houseCode, orgId);

			RentalHouse exsited = rentalHouseDao
					.getHouseInfoByHouseCodeAndOrganizationId(houseCode, orgId);
			return houseId == null ? exsited != null
					: (exsited != null && !houseId.equals(exsited.getId()));
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类RentalHouseServiceImpl的hasDuplicateHouseInfo方法出现异常，原因：",
					"获取出租房信息出现错误", e);
		}
	}

	@Override
	public boolean hasDuplicateHouseInfoForAddress(Long orgId,
			String houseCode, Long houseId) {
		try {
			// fateson add 使用地址来 判断唯一
			// RentalHouse exsited = rentalHouseDao
			// .getHouseInfoByHouseCodeAndOrganizationId(houseCode, orgId);

			RentalHouse exsited = rentalHouseDao
					.getHouseInfoByAdressAndOrganizationId(houseCode, orgId);
			return houseId == null ? exsited != null
					: (exsited != null && !houseId.equals(exsited.getId()));
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类RentalHouseServiceImpl的hasDuplicateHouseInfo方法出现异常，原因：",
					"获取出租房信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.RentalHouseService#getHouseInfoById(java.lang.Long)
	 */
	@Override
	public RentalHouse getHouseInfoById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			return rentalHouseDao.getHouseInfoById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类RentalHouseServiceImpl的getSimpleHouseInfoById方法出现异常，原因：",
					"获取出租房信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.RentalHouseService#getHouseInfoByHouseCodeAndOrgId
	 * (java.lang.String, java.lang.Long)
	 */
	@Override
	public RentalHouse getHouseInfoByHouseCodeAndOrgId(String houseCode,
			Long orgId) {
		try {
			return rentalHouseDao.getHouseInfoByHouseCodeAndOrganizationId(
					houseCode, orgId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类RentalHouseServiceImpl的getHouseInfoByHouseCodeAndOrgId方法出现异常，原因：",
					"获取出租房信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tianque.baseInfo.rentalHouse.service.RentalHouseService#
	 * updateEmphasiseByIds(java.util.List, java.lang.Boolean)
	 */
	@Override
	public void updateEmphasiseByIds(List<Long> houseIds, Long isEmphasis) {
		if (houseIds.isEmpty()) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			for (Long houseId : houseIds) {
				RentalHouse oldRentalHouse = getHouseInfoById(houseId);
				this.housesDependentService
						.synRentalHouseToActualHouseForIsEmphasis(houseId,
								isEmphasis);
				// 轨迹
				// houseTracksService
				// .addHouseTracks(
				// this.getHouseInfoById(houseId),
				// BaseInfoTables.RENTALHOUSE_KEY,
				// HouseInitType.TRANSFOR_DOOM,
				// null,
				// isEmphasis == 1l ? HouseTracksOperationType.LOGOUT
				// : HouseTracksOperationType.CANCEL_LOGOUT,
				// "出租房"
				// + (isEmphasis == 1l ? HouseTracksOperationType.LOGOUT
				// : HouseTracksOperationType.CANCEL_LOGOUT));
				try {
					PluginServiceHelpUtil
							.doService(
									"routerService",
									"updateServiceTeamHasObjectsAndServiceMemberHasObject",
									new Class[] { String.class, Long.class,
											Long.class },
									BaseInfoTables.RENTALHOUSE_KEY, houseId,
									isEmphasis == 1l ? 1l : 0l);
					Integer operatorType = isEmphasis == 0l ? OperatorType.EMPHASISE
							: OperatorType.CANCELEMPHASISE;
					systemLogService
							.log(com.tianque.core.logger.Logger.INFO,
									ModelType.TEMPORARYRESIDENT,
									operatorType,
									ThreadVariable.getSession().getUserName()
											+ OperatorType
													.toString(operatorType)
											+ ModelType.TEMPORARYRESIDENT
											+ "位于"
											+ organizationDubboService
													.getOrganizationRelativeNameByRootOrgIdAndOrgId(
															oldRentalHouse
																	.getOrganization()
																	.getId(),
															OrganizationServiceHelper
																	.getRootOrg(
																			organizationDubboService)
																	.getId())
											+ "->"
											+ oldRentalHouse.getAddress()
											+ ModelType.TEMPORARYRESIDENT, null);

				} catch (Exception e) {
					throw new ServiceValidationException("更新出租屋注销状态出现错误", e);
				} finally {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("houseId", houseId);
					map.put("isEmphasis", isEmphasis);
					rentalHouseDao.updateEmphasiseById(map);
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类RentalHouseServiceImpl的updateEmphasiseByIds方法出现异常，原因：",
					"更新出租房注销状态出现错误", e);
		}
	}

	/**
	 * 数据校验
	 * 
	 * @param houseInfo
	 */
	private void rentalHouseBaseInfoValidator(RentalHouse rentalHouse) {
		ValidateResult rentalHouseBaseInfoValidator = updateValidator
				.validateHouseBaseInfo(rentalHouse);
		if (rentalHouseBaseInfoValidator.hasError()) {
			throw new BusinessValidationException(
					rentalHouseBaseInfoValidator.getErrorMessages());
			// } else if
			// (hasDuplicateHouseInfo(rentalHouse.getOrganization().getId(),
			// rentalHouse.getHouseCode(), rentalHouse.getId())) {
			// throw new BusinessValidationException("该网格下已存在住房编号");
		}

	}

	private void rentalHouseSpecialValidator(RentalHouse rentalHouse) {
		ValidateResult rentalHouseSpecialValidator = updateValidator
				.validateSpecializedInfo(rentalHouse);
		if (rentalHouseSpecialValidator.hasError()) {
			throw new BusinessValidationException(
					rentalHouseSpecialValidator.getErrorMessages());
		}
	}

	private void autoFillChinesePinyin(RentalHouse rentalHouse) {
		if (null != rentalHouse && !StringUtils.isEmpty(rentalHouse.getName())) {
			Map<String, String> pinyin = Chinese2pinyin
					.changeChinese2Pinyin(rentalHouse.getName());
			rentalHouse.setSimplePinyin(pinyin.get("simplePinyin"));
			rentalHouse.setFullPinyin(pinyin.get("fullPinyin"));
		}
	}

	private RentalHouse autoFillDefaule(RentalHouse rentalHouse) {
		if (null == rentalHouse.getIsEmphasis()) {
			rentalHouse.setIsEmphasis(Long.valueOf(0));
		}
		if (null == rentalHouse.getIsFireChannels()) {
			rentalHouse.setIsFireChannels(0L);
		}
		if (null == rentalHouse.getIsSafetyChannel()) {
			rentalHouse.setIsSafetyChannel(0L);
		}
		if (null == rentalHouse.getIsSignGuarantee()) {
			rentalHouse.setIsSignGuarantee(0L);
		}
		if (rentalHouse.getAddressType() == null
				|| rentalHouse.getAddressType().getId() == null) {
			PropertyDict addressType = convertToPropertyDict(
					PropertyTypes.CURRENT_ADDRESS_TYPE,
					PropertyTypes.CURRENT_ADDRESS_TYPE_OTHER);
			rentalHouse.setAddressType(addressType);
		}
		joinAddress(rentalHouse);
		return rentalHouse;
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

	private void joinAddress(HouseInfo houseInfo) {
		if (houseInfo.getAddressType() == null
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
	public RentalHouse addRentalHouseForPopulation(RentalHouse houseInfo) {
		return this.addHouseInfo(houseInfo);
	}

	@Override
	public RentalHouse updateRentalHouseForPopulation(RentalHouse houseInfo) {
		autoFillChinesePinyin(houseInfo);
		// 通过出租屋的ID去查询出租屋信息，如果是注销状态，先取消注销状态再修改
		RentalHouse rentalHouseInfo = rentalHouseDao.getHouseInfoById(houseInfo
				.getId());
		if (rentalHouseInfo.getIsEmphasis() != null
				&& rentalHouseInfo.getIsEmphasis() == 1) {
			houseInfo.setIsEmphasis(0l);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("houseId", houseInfo.getId());
			map.put("isEmphasis", houseInfo.getIsEmphasis());
			rentalHouseDao.updateEmphasiseById(map);
		}

		if (!ExcelImportHelper.isImport.get()) {
			this.rentalHouseSpecialValidator(houseInfo);
		}
		if (null == houseInfo.getIsFireChannels()) {
			houseInfo.setIsFireChannels(0L);
		}
		if (null == houseInfo.getIsSafetyChannel()) {
			houseInfo.setIsSafetyChannel(0L);
		}
		if (null == houseInfo.getIsSignGuarantee()) {
			houseInfo.setIsSignGuarantee(0L);
		}
		try {
			return rentalHouseDao
					.updateHouseBusinessInfoForPopulation(houseInfo);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类RentalHouseServiceImpl的updateRentalHouseForPopulation方法出现异常，原因：",
					"修改实口出租房信息出现错误", e);
		}
	}

	@Override
	public RentalHouse serchRentalHouseByHouseCode(String houseCode, Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException(
					"根据房屋编号和orgId获取出租房时时异常！，orgId获取失败");
		} else {
			return rentalHouseDao.serchRentalHouseByHouseCode(houseCode, orgId);
		}
	}

	@Override
	public RentalHouse getHouseInfoByHouseCodeAndOrgId(String houseCode,
			Long id, Long logoutType) {
		try {
			return rentalHouseDao.getHouseInfoByHouseCodeAndOrganizationId(
					houseCode, id, logoutType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类RentalHouseServiceImpl的getHouseInfoByHouseCodeAndOrgId方法出现异常，原因：",
					"获取出租房信息出现错误", e);
		}
	}

	@Override
	public void deleteRentalHousesByIdList(List<Long> idList) {
		try {
			rentalHouseDao.deleteHouseInfoByIds(idList);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"RentalHouseServiceImpl的deleteRentalHouseByIdList方法出现异常", e);
		}
	}

	@Override
	public void shiftRentalHouse(Long[] ids, Long orgId) {
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == null) {
				continue;
			}
			RentalHouse lettingHouse = getHouseInfoById(ids[i]);
			lettingHouse.setOrganization(organization);
			lettingHouse.setOrgInternalCode(organization.getOrgInternalCode());
			updateHouseBaseInfo(lettingHouse);
		}
	}

	@Override
	public RentalHouse getHouseInfoByHouseId(Long houseId) {
		try {
			return rentalHouseDao.getHouseInfoByHouseId(houseId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类RentalHouseServiceImpl的getSimpleHouseInfoById方法出现异常，原因：",
					"获取出租房信息出现错误", e);
		}
	}

	@Override
	public RentalHouse getHouseInfoByHouseIdAndOrgId(Long id, Long id2) {
		return rentalHouseDao.getHouseInfoByHouseIdAndOrgId(id, id2);
	}

	@Override
	public RentalHouse addRentalHouse(RentalHouse rentalHouse) {
		HouseInfo houseInfo = new HouseInfo();
		Long houseId;
		try {
			PropertyUtil.copyPropertiesWithOutRecursion(HouseInfo.class,
					houseInfo, rentalHouse);
			houseInfo.setIsRentalHouse(true);
			houseId = actualHouseService.addHouseInfo(houseInfo).getId();
			rentalHouse.setHouseId(houseId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.addHouseInfo(rentalHouse);
	}

	@Override
	public Integer getCount(SearchHouseInfoVo searchHouseInfoVo) {
		return rentalHouseDao.getCount(searchHouseInfoVo);
	}

	@Override
	public RentalHouse getHouseInfoByHouseId(Long id, Long emphasis) {
		try {
			return rentalHouseDao.getHouseInfoByHouseId(id, emphasis);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类RentalHouseServiceImpl的getHouseInfoByHouseId方法出现异常，原因：",
					"获取出租房信息出现错误", e);
		}
	}

	@Override
	public RentalHouse getRentalHouseInfoByHouseId(Long id) {
		if (null == id) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			return rentalHouseDao.getRentalHouseInfoByHouseId(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类RentalHouseServiceImpl的getSimpleHouseInfoById方法出现异常，原因：",
					"获取出租房信息出现错误", e);
		}
	}

	@Override
	public void updateRentalHouseInfoByNewHouseId(Long oldHouseId,
			Long newHouseId) {
		if (oldHouseId == null || newHouseId == null) {
			throw new BusinessValidationException("房屋信息为空");
		}
		rentalHouseDao
				.updateRentalHouseInfoByNewHouseId(oldHouseId, newHouseId);
	}

	@Override
	public RentalHouse getRentalHouseById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("房屋信息为空");
		}
		return getHouseInfoById(id);
	}

	@Transactional
	@Override
	public RentalHouse updateHouseBaseInfoForMobile(RentalHouse rentalHouse) {

		autoFillChinesePinyin(rentalHouse);
		joinAddress(rentalHouse);
		// 通过出租屋的ID去查询出租屋信息，如果是注销状态，先取消注销状态再修改
		RentalHouse rentalHouseInfo = rentalHouseDao
				.getHouseInfoById(rentalHouse.getId());
		if (rentalHouseInfo.getIsEmphasis() != null
				&& rentalHouseInfo.getIsEmphasis() == 1) {
			rentalHouse.setIsEmphasis(0l);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("houseId", rentalHouse.getId());
			map.put("isEmphasis", rentalHouse.getIsEmphasis());
			rentalHouseDao.updateEmphasiseById(map);
		}

		if (!ExcelImportHelper.isImport.get()) {
			this.rentalHouseBaseInfoValidator(rentalHouse);
		}
		try {
			return rentalHouseDao.updateHouseBaseInfoForMobile(rentalHouse);
		} catch (Exception e) {
			logger.error(
					"类RentalHouseServiceImpl的updateHouseBaseInfo方法出现异常，原因：", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改出租房基本信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public void deleteRentalhouseInfoById(Long id) {
		try{
			rentalHouseDao.deleteHouseInfoById(id);
		}catch(Exception e){
			throw new ServiceValidationException(
					"RentalHouseServiceImpl的deleteRentalhouseInfoById方法出现异常", e);
		}
	}

}

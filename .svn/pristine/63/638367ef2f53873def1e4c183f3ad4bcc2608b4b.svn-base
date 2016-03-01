package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.HouseInfoDao;
import com.tianque.dao.SearchHouseForAutoCompleteDao;
import com.tianque.domain.HouseHasActualPopulation;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.HouseSimpleInfoForSearch;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.recoverDatas.service.RecoverDatasService;
import com.tianque.search.result.HouseSimpleInfo;
import com.tianque.search.vo.SearchHouseByAddressLibCondition;
import com.tianque.service.HouseHasActualPopulationService;
import com.tianque.service.HouseInfoService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.PropertyUtil;
import com.tianque.validate.impl.HouseInfoValidatorImpl;

@Service("houseInfoService")
public class HouseInfoServiceImpl extends AbstractBaseService implements
		HouseInfoService {

	private static final String REPEAT_HOUSE_SYMBOL = "";
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService properTypeDictService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private HouseInfoDao houseInfoDao;
	@Autowired
	private SearchHouseForAutoCompleteDao autoCompleteDao;
	@Autowired
	private PropertyDictService propertyService;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private HouseHasActualPopulationService houseHasActualPopulationService;
	@Autowired
	private RecoverDatasService recoverDatasService;
	@Autowired
	private RentalHouseService rentalHouseService;

	@Override
	public HouseInfo addHouseInfo(HouseInfo houseInfo) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				checkHouseInfo(houseInfo);
			}
			autoFillOrganizationInternalCode(houseInfo);
			return houseInfoDao.addHouseInfo(houseInfo);
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new ServiceValidationException("新增信息出现错误", e);
			} else {
				return null;
			}
		}
	}

	@Override
	public HouseInfo updateHouseInfo(HouseInfo houseInfo) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				checkHouseInfo(houseInfo);
				validateUnmodifiedFields(houseInfo);
			}
			autoFillOrganizationInternalCode(houseInfo);
			houseInfo = houseInfoDao.updateHouseInfo(houseInfo);
			return houseInfo;
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new ServiceValidationException("修改信息出现错误", e);
			} else {
				return null;
			}
		}
	}

	@Override
	public void deleteInfrastructureByHouseInfoId(Long houseInfoId) {
		houseInfoDao.deleteInfrastructureByHouseInfoId(houseInfoId);
	}

	private void autoFillOrganizationInternalCode(HouseInfo houseInfo) {
		Organization org = organizationDubboService.getSimpleOrgById(houseInfo
				.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		houseInfo.setOrgInternalCode(org.getOrgInternalCode());
		if (houseInfo.getAddressType() == null
				|| houseInfo.getAddressType().getId() == null) {
			PropertyDict addressType = convertToPropertyDict(
					PropertyTypes.CURRENT_ADDRESS_TYPE,
					PropertyTypes.CURRENT_ADDRESS_TYPE_OTHER);
			houseInfo.setAddressType(addressType);
		}
	}

	private PropertyDict convertToPropertyDict(String propertyDomainName,
			String dictDisplayName) {
		if (!StringUtil.isStringAvaliable(dictDisplayName)) {
			return new PropertyDict();
		}
		return propertyService.findPropertyDictByDomainNameAndDictDisplayName(
				propertyDomainName, dictDisplayName);
	}

	private void checkHouseInfo(HouseInfo houseInfo) {
		ValidateResult idleValidator = createHouseInfoValidator().validate(
				houseInfo);
		if (idleValidator.hasError()) {
			throw new BusinessValidationException(
					idleValidator.getErrorMessages());
		} else if (hasDuplicateHouseInfo(houseInfo.getOrganization().getId(),
				houseInfo.getHouseCode(), houseInfo.getId())) {
			throw new BusinessValidationException("该网格下已存在住房编号");
		}
	}

	private HouseInfoValidatorImpl createHouseInfoValidator() {
		HouseInfoValidatorImpl houseInfoValidatorImpl = new HouseInfoValidatorImpl();
		houseInfoValidatorImpl
				.setOrganizationDubboService(organizationDubboService);
		houseInfoValidatorImpl.setValidateHelper(validateHelper);
		return houseInfoValidatorImpl;
	}

	private void validateUnmodifiedFields(HouseInfo update) {
		HouseInfo houseInfo = this.houseInfoDao.getSimpleHouseInfoById(update
				.getId());
		if (null != update.getOrganization()
				&& null != update.getOrganization().getId()
				&& !update.getOrganization().getId()
						.equals(houseInfo.getOrganization().getId())) {
			throw new BusinessValidationException("所属网格不能修改");
		}
		if (null != update.getCreateUser()
				&& !update.getCreateUser().equals(houseInfo.getCreateUser())) {
			throw new BusinessValidationException("创建人不能修改");
		}
		if (null != update.getCreateDate()
				&& !update.getCreateDate().equals(houseInfo.getCreateDate())) {
			throw new BusinessValidationException("创建时间不能修改");
		}
	}

	@Override
	public HouseInfo getSimpleHouseInfoById(Long id) {
		return houseInfoDao.getSimpleHouseInfoById(id);
	}

	@Override
	public boolean hasDuplicateHouseInfo(Long orgId, String houseCode,
			Long exceptedId) {
		HouseInfo exsited = houseInfoDao
				.getHouseInfoByHouseCodeAndOrganizationId(houseCode, orgId);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	@Override
	public List<HouseSimpleInfo> findHousesForAutoComplete(
			SearchHouseByAddressLibCondition condition) {
		processCondition(condition);
		List<HouseSimpleInfoForSearch> houses = new ArrayList<HouseSimpleInfoForSearch>();
		if (SearchHouseByAddressLibCondition.BY_HOUSE_ADDRESS
				.equalsIgnoreCase(condition.getSearchType())) {
			houses = autoCompleteDao.searchHouseInfoByAddress(
					condition.getOrgInternalCode(), condition.getAddress(),
					condition.getHouseType(), condition.getRows());
		} else if (SearchHouseByAddressLibCondition.BY_HOUSE_CODE
				.equalsIgnoreCase(condition.getSearchType())) {
			houses = autoCompleteDao.searchHouseInfoByHouseCode(
					condition.getOrgInternalCode(), condition.getHouseCode(),
					condition.getHouseType(), condition.getRows());
		} else if (SearchHouseByAddressLibCondition.BY_COMMUNITY
				.equalsIgnoreCase(condition.getSearchType())) {
			houses = autoCompleteDao.searchHouseInfoByCommunity(
					condition.getOrgInternalCode(), condition.getCommunity(),
					condition.getHouseType(), condition.getRows());
		} else if (SearchHouseByAddressLibCondition.BY_COMMUNITY_BLOCK
				.equalsIgnoreCase(condition.getSearchType())) {
			houses = autoCompleteDao.searchHouseInfoByCommunityAndBlock(
					condition.getOrgInternalCode(), condition.getCommunity(),
					condition.getBlock(), condition.getHouseType(),
					condition.getRows());
		} else if (SearchHouseByAddressLibCondition.BY_COMMUNITY_BLOCK_UNIT
				.equalsIgnoreCase(condition.getSearchType())) {
			houses = autoCompleteDao.searchHouseInfoByCommunityAndBlockAndUnit(
					condition.getOrgInternalCode(), condition.getCommunity(),
					condition.getBlock(), condition.getUnit(),
					condition.getHouseType(), condition.getRows());
		} else if (SearchHouseByAddressLibCondition.BY_COMMUNITY_BLOCK_UNIT_ROOM
				.equalsIgnoreCase(condition.getSearchType())) {
			houses = autoCompleteDao
					.searchHouseInfoByCommunityAndBlockAndUnitAndRoom(
							condition.getOrgInternalCode(),
							condition.getCommunity(), condition.getBlock(),
							condition.getUnit(), condition.getRoom(),
							condition.getHouseType(), condition.getRows());
		}
		return convert2HouseSimpleInfoList(condition, houses);
	}

	private void processCondition(SearchHouseByAddressLibCondition condition) {
		condition.setAddress(replaceSomeChar(condition.getAddress()));
		condition.setBlock(replaceSomeChar(condition.getBlock()));
		condition.setCommunity(replaceSomeChar(condition.getCommunity()));
		condition.setRoom(replaceSomeChar(condition.getRoom()));
		condition.setUnit(replaceSomeChar(condition.getUnit()));
	}

	private String replaceSomeChar(String text) {
		if (text != null) {
			text = text.replaceAll("%", "");
			text = text.replaceAll("\\?", "");
		}
		return text;

	}

	private List<HouseSimpleInfo> convert2HouseSimpleInfoList(
			SearchHouseByAddressLibCondition condition,
			List<HouseSimpleInfoForSearch> houses) {
		List<HouseSimpleInfo> result = new ArrayList<HouseSimpleInfo>();
		if (houses != null && houses.size() > 0) {
			for (HouseSimpleInfoForSearch house : houses) {
				result.add(convert2HouseSimpleInfo(condition, house));
			}
		}
		return result;
	}

	private HouseSimpleInfo convert2HouseSimpleInfo(
			SearchHouseByAddressLibCondition condition,
			HouseSimpleInfoForSearch house) {
		HouseSimpleInfo result = constructNewHouseSimpleInfo();
		if (SearchHouseByAddressLibCondition.BY_HOUSE_ADDRESS
				.equalsIgnoreCase(condition.getSearchType())) {
			result.setAddress(house.getAddress());
		} else if (SearchHouseByAddressLibCondition.BY_HOUSE_CODE
				.equalsIgnoreCase(condition.getSearchType())) {
			result.setAddressType(house.getAddressType());
			if (StringUtils.isEmpty(house.getComnunity())) {
				result.setAddress(house.getAddress());
			} else {
				result.setComnunity(house.getComnunity());
				result.setBlock(house.getBlock());
				result.setUnit(house.getUnit());
				result.setRoom(house.getRoom());
			}
		} else if (SearchHouseByAddressLibCondition.BY_COMMUNITY
				.equalsIgnoreCase(condition.getSearchType())) {
			result.setComnunity(house.getComnunity());
			if (!hasRepeatHouse(house)) {
				result.setBlock(house.getBlock());
				result.setUnit(house.getUnit());
				result.setRoom(house.getRoom());
			}
		} else if (SearchHouseByAddressLibCondition.BY_COMMUNITY_BLOCK
				.equalsIgnoreCase(condition.getSearchType())) {
			result.setComnunity(condition.getCommunity());
			result.setBlock(house.getBlock());
			if (!hasRepeatHouse(house)) {
				result.setUnit(house.getUnit());
				result.setRoom(house.getRoom());
			}
		} else if (SearchHouseByAddressLibCondition.BY_COMMUNITY_BLOCK_UNIT
				.equalsIgnoreCase(condition.getSearchType())) {
			result.setComnunity(condition.getCommunity());
			result.setBlock(condition.getBlock());
			result.setUnit(house.getUnit());
			if (!hasRepeatHouse(house)) {
				result.setRoom(house.getRoom());
			}
		} else if (SearchHouseByAddressLibCondition.BY_COMMUNITY_BLOCK_UNIT_ROOM
				.equalsIgnoreCase(condition.getSearchType())) {
			result.setComnunity(condition.getCommunity());
			result.setBlock(condition.getBlock());
			result.setUnit(condition.getUnit());
			result.setRoom(house.getRoom());
		}
		result.setHouseId(String.valueOf(house.getMaxId()));
		result.setHouseCode(house.getHouseCode());
		// result.setHouseId(house.getRepeatCount()>1?REPEAT_HOUSE_SYMBOL:house.getMaxId().toString());
		return result;
	}

	private HouseSimpleInfo constructNewHouseSimpleInfo() {
		HouseSimpleInfo result = new HouseSimpleInfo();
		result.setAddress(REPEAT_HOUSE_SYMBOL);
		result.setBlock(REPEAT_HOUSE_SYMBOL);
		result.setComnunity(REPEAT_HOUSE_SYMBOL);
		result.setHouseId(REPEAT_HOUSE_SYMBOL);
		result.setRoom(REPEAT_HOUSE_SYMBOL);
		result.setUnit(REPEAT_HOUSE_SYMBOL);
		return result;
	}

	private boolean hasRepeatHouse(HouseSimpleInfoForSearch house) {
		return house.getRepeatCount() > 1;
	}

	@Override
	public HouseInfo findGisHouseInfoById(Long houseId) {
		if (null == houseId) {
			logger.info("HouseInfoServiceImpl", "根据房屋Id的查询房屋信息时，Id为空！");
			throw new BusinessValidationException("根据房屋Id的查询房屋信息时，Id为空！");
		} else {
			return houseInfoDao.findGisHouseInfoById(houseId);
		}

	}

	@Override
	public HouseInfo unBundPserson(Long id, String shardCode) {
		return houseInfoDao.unBundHouseInfoById(id, shardCode);
	}

	@Override
	public PageInfo<HouseInfo> getPageInfoByQueryStrForSearchHouseInfo(
			String orgIngernalCode, String queryStr, Integer page,
			Integer rows, String sidx, String sord) {
		return houseInfoDao.getGisByQueryStrAndOrgId(orgIngernalCode, queryStr,
				page, rows, sidx, sord);
	}

	@Override
	public PageInfo<HouseInfo> searchkeyHouseInfoListByorgId(
			String orgInternalCode, String houseType, Integer page,
			Integer rows, String sidx, String sord) {
		return houseInfoDao.getkeyHouseInfoForGisByorgInternalCode(
				orgInternalCode, houseType, page, rows, sidx, sord);
	}

	@Override
	public List<HouseInfo> countActualHouseByOrgId(Long orgId) {
		if (null == orgId) {
			return new ArrayList<HouseInfo>();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			return houseInfoDao.countActualHouseByOrgCode(org
					.getOrgInternalCode());
		}
	}

	@Override
	public List<HouseInfo> findAllBindingHouseInfoByOrgInternalCode(
			String orgInternalCode) {
		return houseInfoDao
				.findAllBindingHouseInfoByOrgInternalCode(orgInternalCode);
	}

	@Override
	public List<HouseInfo> findAllBindingHouseInfoByBuildingId(Long buildingId) {
		return houseInfoDao.findAllBindingHouseInfoByBuildingId(buildingId);
	}

	@Override
	public List<HouseInfo> findHouseInfoByOrgIdAddress(Long orgId,
			String address) {
		return houseInfoDao.findHouseInfoByOrgIdAddress(orgId, address);
	}

	/**
	 * useRentalInfo 在两个合并房屋都是出租房的情况下，useRentalInfo==0
	 * 则默认使用主房屋信息，useRentalInfo==1 则使用被合并房屋出租房信息
	 */
	@Override
	public boolean disposeHouseMegerInfo(HouseInfo coalescedHouseInfo,
			Long megerId, Integer useRentalInfo) {
		// coalescedHouseInfo 合并后的信息
		if (megerId == null || coalescedHouseInfo == null) {
			throw new BusinessValidationException("合并失败，房屋ID为空");
		}
		// coalescedHouseInfo = changeThePropertyDictByName(coalescedHouseInfo);
		try {
			// 通过被合并房屋信息查询该房屋信息
			HouseInfo megerHouseInfo = actualHouseService
					.getHouseInfoById(megerId);
			HouseInfo houseInfo = actualHouseService
					.getHouseInfoById(coalescedHouseInfo.getId());// 主房屋信息被合并前
			if (megerHouseInfo == null || houseInfo == null) {
				throw new BusinessValidationException("合并失败，房屋信息为空");
			}
			// judgeIsRentalHouseAndCombine(houseInfo,megerHouseInfo,coalescedHouseInfo,megerId);

			judgeUseRentalHouse(houseInfo, megerHouseInfo, coalescedHouseInfo,
					megerId, useRentalInfo);

			transferPersonAndHouse(megerId, houseInfo);

			deletRentalHouseInfoById(coalescedHouseInfo, houseInfo);

			judgeBuildRelation(houseInfo, megerHouseInfo);

			// 将被合并房屋信息覆盖掉原有房屋信息，然后删除被合并房屋信息，并备份
			actualHouseService.updateSimpleHouseInfo(coalescedHouseInfo);

			deletHouseInfoByMegerHouseId(megerHouseInfo);

			// 数据备份
			List<HouseInfo> houseInfoList = new ArrayList<HouseInfo>();
			houseInfoList.add(megerHouseInfo);
			recoverDatasService.deleteActualHouse(houseInfoList);

			return true;
		} catch (Exception e) {
			throw new BusinessValidationException("房屋信息合并失败" + e.getMessage());
		}
	}

	// 根据房屋ID删除房屋信息，并解除房屋关联关系
	private void deletHouseInfoByMegerHouseId(HouseInfo megerHouseInfo) {
		List<Long> idsList = new ArrayList<Long>();
		idsList.add(megerHouseInfo.getId());
		actualHouseService.deleteHouseInfosByIdList(idsList);
	}

	// 判断是否存在建筑物关联关系
	private void judgeBuildRelation(HouseInfo houseInfo,
			HouseInfo megerHouseInfo) {
		// 判断建筑关联关系
		if (houseInfo.getBuilddatasId() != null
				&& megerHouseInfo.getBuilddatasId() != null) {
			// 删除被合并房屋与建筑物关联
			List<Long> ids = new ArrayList<Long>();
			ids.add(megerHouseInfo.getId());
			actualHouseService.updateHouseInfo(ids, null);

		}
		if (houseInfo.getBuilddatasId() == null
				&& megerHouseInfo.getBuilddatasId() != null) {
			// 将被合并房屋与建筑物关联信息转移到主房屋信息
			List<Long> ids = new ArrayList<Long>();
			ids.add(houseInfo.getId());
			actualHouseService.updateHouseInfo(ids,
					megerHouseInfo.getBuilddatasId());
		}

	}

	// 如果合并前是出租房，合并后不是，删除合并前的出租房信息
	private void deletRentalHouseInfoById(HouseInfo coalescedHouseInfo,
			HouseInfo houseInfo) {
		if ((!coalescedHouseInfo.getIsRentalHouse())
				&& (houseInfo.getIsRentalHouse())) {
			// 通过房屋ID查询出租房信息
			RentalHouse rentalhouse = rentalHouseService
					.getHouseInfoByHouseId(houseInfo.getId());
			if (rentalhouse != null && rentalhouse.getId() != null) {
				rentalHouseService.deleteRentalhouseInfoById(rentalhouse
						.getId());
			}
		}

	}

	// 转移人房关系
	private void transferPersonAndHouse(Long megerId, HouseInfo houseInfo) {
		List<HouseHasActualPopulation> houseHasActualPopulationList = houseHasActualPopulationService
				.getHouseHasActualPopulationByHouseId(megerId);
		if (houseHasActualPopulationList != null
				&& houseHasActualPopulationList.size() != 0) {
			for (HouseHasActualPopulation houseActual : houseHasActualPopulationList) {
				houseHasActualPopulationService
						.updateHouseHasActualPopulationByPopulationByHouseId(
								houseActual.getPopulationType(), houseActual
										.getPopulationId(), megerId, houseInfo
										.getId(), houseInfo.getOrganization()
										.getId());
			}

		}

	}

	// 如果合并前后都是出租房，而且使用被合并出租房的房屋信息，则将被合并出租房的实有房屋信息修改，然后归于合并后房屋下,还删除合并前的主房屋出租房信息
	private void judgeUseRentalHouse(HouseInfo houseInfo,
			HouseInfo megerHouseInfo, HouseInfo coalescedHouseInfo,
			Long megerId, Integer useRentalInfo) {
		try {
			if ((!houseInfo.getIsRentalHouse())
					&& (coalescedHouseInfo.getIsRentalHouse())) {// 合并前不是，合并后是,将出租房信息归于合并后房屋下
				RentalHouse megerRentalhouse = rentalHouseService
						.getHouseInfoByHouseId(megerId);// 被合并的出租房屋信息
				PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class,
						megerRentalhouse, coalescedHouseInfo,
						new String[] { "id" });
				// 将被合并房屋的出租信息ID赋值给主房屋的信息
				// 通过被合并房屋的ID修改主房屋的出租房信息中房屋的信息
				rentalHouseService.updateHouseBaseInfo(megerRentalhouse);

				// 防止主房屋的出租房为注销状态
				// 得到主房屋的出租信息
				RentalHouse rentalhouse = rentalHouseService
						.getHouseInfoByHouseId(houseInfo.getId());
				// 通过主房屋ID删除出租信息
				if (rentalhouse != null) {
					List<Long> ids = new ArrayList<Long>();
					rentalHouseService.deleteRentalhouseInfoById(rentalhouse
							.getId());
				}

				// 然后将该出租房的houseID更改为被合并出租房的ID
				rentalHouseService.updateRentalHouseInfoByNewHouseId(megerId,
						coalescedHouseInfo.getId());
			} else if (houseInfo.getIsRentalHouse()
					&& megerHouseInfo.getIsRentalHouse()) {// 合并前和被合并房屋都是出租房
				if (useRentalInfo != null && useRentalInfo == 1) {// 使用被合并房屋信息

					RentalHouse megerRentalhouse = rentalHouseService
							.getHouseInfoByHouseId(megerId);// 被合并的出租房屋
					PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class,
							megerRentalhouse, houseInfo, new String[] { "id" });
					// 将被合并房屋的出租信息ID赋值给主房屋的信息
					// 通过被合并房屋的ID修改主房屋的出租房信息中房屋的信息
					rentalHouseService.updateHouseBaseInfo(megerRentalhouse);

					// 得到主房屋的出租信息
					RentalHouse rentalhouse = rentalHouseService
							.getHouseInfoByHouseId(houseInfo.getId());
					// 通过主房屋ID删除出租信息
					if (rentalhouse != null) {
						List<Long> ids = new ArrayList<Long>();
						rentalHouseService
								.deleteRentalhouseInfoById(rentalhouse.getId());
					}
					rentalHouseService.updateRentalHouseInfoByNewHouseId(
							megerId, houseInfo.getId());
				}
			}
		} catch (Exception e) {
			throw new BusinessValidationException("操作失败，请重试");
		}

	}

}

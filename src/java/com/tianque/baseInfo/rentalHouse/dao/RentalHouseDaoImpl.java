package com.tianque.baseInfo.rentalHouse.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchHouseInfoVo;

@Repository("rentalHouseDao")
public class RentalHouseDaoImpl extends AbstractBaseDao implements
		RentalHouseDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.RentalHouseDao#addHouseInfo(com.tianque.domain.RentalHouse
	 * )
	 */
	@Override
	public RentalHouse addHouseInfo(RentalHouse rentalHouse) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"rentalHouse.addRentalHouse", rentalHouse);
		return getHouseInfoById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.RentalHouseDao#updateHouseBaseInfo(com.tianque.domain
	 * .RentalHouse)
	 */
	@Override
	public RentalHouse updateHouseBaseInfo(RentalHouse rentalHouse) {
		getSqlMapClientTemplate().update(
				"rentalHouse.updateRentalHouseBaseInfo", rentalHouse);
		return getHouseInfoById(rentalHouse.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.RentalHouseDao#updateHouseBusinessInfo(com.tianque.domain
	 * .RentalHouse)
	 */
	@Override
	public RentalHouse updateHouseBusinessInfo(RentalHouse rentalHouse) {
		getSqlMapClientTemplate().update(
				"rentalHouse.updateRentalHouseBusinessInfo", rentalHouse);
		return getHouseInfoById(rentalHouse.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.baseInfo.rentalHouse.dao.RentalHouseDao#updateEmphasiseById
	 * (java.lang.Long, java.lang.Boolean)
	 */
	@Override
	public void updateEmphasiseById(Map<String, Object> map) {
		getSqlMapClientTemplate()
				.update("rentalHouse.updateEmphasiseById", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tianque.baseInfo.rentalHouse.dao.RentalHouseDao#
	 * updateHouseBusinessInfoForPopulation
	 * (com.tianque.baseInfo.rentalHouse.domain.RentalHouse)
	 */
	@Override
	public RentalHouse updateHouseBusinessInfoForPopulation(
			RentalHouse rentalHouse) {
		getSqlMapClientTemplate()
				.update("rentalHouse.updateHouseBusinessInfoForPopulation",
						rentalHouse);
		return getHouseInfoById(rentalHouse.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.dao.RentalHouseDao#deleteHouseInfoById(java.lang.Long)
	 */
	@Override
	public void deleteHouseInfoById(Long id) {
		getSqlMapClientTemplate().delete("rentalHouse.deleteRentalHouseById",
				id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.RentalHouseDao#getHouseInfoByHouseCodeAndOrganizationId
	 * (java.lang.String, java.lang.Long)
	 */
	@Override
	public RentalHouse getHouseInfoByHouseCodeAndOrganizationId(
			String houseCode, Long organizationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseCode", houseCode);
		map.put("organizationId", organizationId);
		return (RentalHouse) getSqlMapClientTemplate().queryForObject(
				"rentalHouse.getHouseInfoByHouseCodeAndOrgId", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.dao.RentalHouseDao#getHouseInfoById(java.lang.Long)
	 */
	@Override
	public RentalHouse getHouseInfoById(Long id) {
		return (RentalHouse) getSqlMapClientTemplate().queryForObject(
				"rentalHouse.getSimpleHouseInfoById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.RentalHouseDao#findHouseInfosForPage(java.lang.String,
	 * java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public PageInfo<RentalHouse> findHouseInfosForPage(String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		return findHouseInfos(pageNum, pageSize, map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.dao.RentalHouseDao#searchHouseInfos(java.lang.Integer,
	 * java.lang.Integer, java.lang.String, java.lang.String,
	 * com.tianque.domain.vo.SearchHouseInfoVo)
	 */
	@Override
	public PageInfo<RentalHouse> searchHouseInfos(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchHouseInfoVo searchHouseInfoVo) {
		if (null == searchHouseInfoVo)
			return emptyHouseInfoPage(pageSize);
		searchHouseInfoVo.setSortField(sortField);
		searchHouseInfoVo.setOrder(order);
		if (searchHouseInfoVo.getCreateDate() != null) {
			// 高级搜索中增加搜索条件-登记日期，由于日期与日期精确到毫秒级无法进行“等于”比较，
			// 因而转化为字符串，用Sql中用like去匹配。例如：createDate like
			// to_date(#registerDate#,'yyyy-mm-dd')
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String registerDate = sdf.format(searchHouseInfoVo.getCreateDate());
			searchHouseInfoVo.setRegisterDate(registerDate);
		}
		PageInfo<RentalHouse> pageInfo = new PageInfo<RentalHouse>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"rentalHouse.countSearchHouseInfo", searchHouseInfoVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<RentalHouse> list = getSqlMapClientTemplate().queryForList(
					"rentalHouse.searchHouseInfo", searchHouseInfoVo,
					(pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<RentalHouse>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private Map getConditionMap(SearchHouseInfoVo condition, String sortField,
			String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (condition == null) {
			return map;
		}
		map.put("orgInternalCode", condition.getOrgInternalCode());
		map.put("houseCodeAndAddress", condition.getHouseCodeAndAddress());
		if (!StringUtils.isEmpty(condition.getHouseCode())
				&& !"null".equals(condition.getHouseCode())) {
			map.put("houseCode", condition.getHouseCode());
		}
		map.put("address", condition.getAddress());
		map.put("propertyTypes", condition.getPropertyTypes());
		map.put("name", condition.getName());
		map.put("fullPinyin", condition.getFullPinyin());
		map.put("simplePinyin", condition.getSimplePinyin());
		map.put("certificateType", condition.getCertificateType());
		map.put("certificateNumbe", condition.getCertificateNumbe());
		map.put("houseUses", condition.getHouseUses());
		map.put("houseStructure", condition.getHouseStructure());
		map.put("builtYearFrom", condition.getBuiltYearFrom());
		map.put("builtYearEnd", condition.getBuiltYearEnd());
		map.put("houseAreaFrom", condition.getHouseAreaFrom());
		map.put("houseAreaEnd", condition.getHouseAreaEnd());
		map.put("isRentalHouse", condition.getIsRentalHouse());
		map.put("buildingName", condition.getBuildingName());
		map.put("buildingUses", condition.getBuildingUses());
		map.put("propertyName", condition.getPropertyName());
		map.put("ownProperty", condition.getOwnProperty());
		map.put("rentalBuildings", condition.getRentalBuildings());
		map.put("housingVouchers", condition.getHousingVouchers());
		map.put("houseRightNumber", condition.getHouseRightNumber());
		map.put("houseRightNumberDateFrom",
				condition.getHouseRightNumberDateFrom());
		map.put("houseRightNumberDateEnd",
				condition.getHouseRightNumberDateEnd());
		map.put("rentMonthFrom", condition.getRentMonthFrom());
		map.put("rentMonthEnd", condition.getRentMonthEnd());
		map.put("landDocuments", condition.getLandDocuments());
		map.put("landRightsNumbe", condition.getLandRightsNumbe());
		map.put("landRightsNumbeDateFrom",
				condition.getLandRightsNumbeDateFrom());
		map.put("landRightsNumbeDateEnd", condition.getLandRightsNumbeDateEnd());
		map.put("hiddenDangerLevel", condition.getHiddenDangerLevel());
		if (null != condition.getUsage()
				&& null != condition.getUsage().getId()) {
			map.put("usage", condition.getUsage());
		}
		map.put("lessorType", condition.getLessorType());
		map.put("rentalPerson", condition.getRentalPerson());
		map.put("houseFileNum", condition.getHouseFileNum());
		map.put("mangerTypes", condition.getMangerTypes());
		map.put("lessorDateFrom", condition.getLessorDateFrom());
		map.put("lessorDateEnd", condition.getLessorDateEnd());
		map.put("roomNumberFrom", condition.getRoomNumberFrom());
		map.put("roomNumberEnd", condition.getRoomNumberEnd());
		map.put("isFireChannels", condition.getIsFireChannels());
		map.put("isSafetyChannel", condition.getIsSafetyChannel());
		map.put("isSignGuarantee", condition.getIsSignGuarantee());
		if (null != condition.getIsEmphasis()) {
			map.put("isEmphasis", condition.getIsEmphasis());
		}
		map.put("sortField", sortField);
		map.put("order", order);
		return map;
	}

	private PageInfo<RentalHouse> findHouseInfos(Integer pageNum,
			Integer pageSize, Map<String, Object> map) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"rentalHouse.countHouseInfos", map);
		List<RentalHouse> list = new ArrayList<RentalHouse>();
		if (countNum > 0) {
			list = getSqlMapClientTemplate().queryForList(
					"rentalHouse.findHouseInfos", map,
					(pageNum - 1) * pageSize, pageSize);
		}
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<RentalHouse> emptyHouseInfoPage(int pageSize) {
		PageInfo<RentalHouse> pageInfo = new PageInfo<RentalHouse>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<RentalHouse>());
		return pageInfo;
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	private PageInfo<RentalHouse> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<RentalHouse> pageInfo = new PageInfo<RentalHouse>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public List<RentalHouse> searchAllHouseInfos(
			SearchHouseInfoVo searchHouseInfoVo) {
		return getSqlMapClientTemplate().queryForList(
				"rentalHouse.searchAllHouseInfos", searchHouseInfoVo);
	}

	@Override
	public RentalHouse serchRentalHouseByHouseCode(String houseCode, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseCode", houseCode);
		map.put("orgId", orgId);
		return (RentalHouse) getSqlMapClientTemplate().queryForObject(
				"rentalHouse.serchRentalHouseByHouseCode", map);

	}

	@Override
	public RentalHouse getHouseInfoByHouseCodeAndOrganizationId(
			String houseCode, Long id, Long logoutType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseCode", houseCode);
		map.put("organizationId", id);
		map.put("logoutType", logoutType);

		return (RentalHouse) getSqlMapClientTemplate().queryForObject(
				"rentalHouse.getHouseInfoByHouseCodeAndOrgId", map);
	}

	@Override
	public RentalHouse getHouseInfoByHouseId(Long houseId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", houseId);
		return (RentalHouse) getSqlMapClientTemplate().queryForObject(
				"rentalHouse.getHouseInfoByHouseId", map);
	}

	@Override
	public RentalHouse getHouseInfoByHouseIdAndOrgId(Long id, Long id2) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", id);
		map.put("orgId", id2);
		return (RentalHouse) getSqlMapClientTemplate().queryForObject(
				"rentalHouse.serchRentalHouseByHouseId", map);
	}

	@Override
	public RentalHouse getHouseInfoByAdressAndOrganizationId(String houseCode,
			Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("address", houseCode);
		map.put("organizationId", orgId);
		return (RentalHouse) getSqlMapClientTemplate().queryForObject(
				"rentalHouse.getHouseInfoByAddressAndOrgId", map);
	}

	@Override
	public Integer getCount(SearchHouseInfoVo searchHouseInfoVo) {
		if (null == searchHouseInfoVo) {
			return 0;
		}
		if (searchHouseInfoVo.getCreateDate() != null) {
			// 高级搜索中增加搜索条件-登记日期，由于日期与日期精确到毫秒级无法进行“等于”比较，
			// 因而转化为字符串，用Sql中用like去匹配。例如：createDate like
			// to_date(#registerDate#,'yyyy-mm-dd')
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String registerDate = sdf.format(searchHouseInfoVo.getCreateDate());
			searchHouseInfoVo.setRegisterDate(registerDate);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"rentalHouse.countSearchHouseInfo", searchHouseInfoVo);
	}

	@Override
	public RentalHouse getHouseInfoByHouseId(Long id, Long emphasis) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", id);
		map.put("logoutType", emphasis);
		return (RentalHouse) getSqlMapClientTemplate().queryForObject(
				"rentalHouse.getHouseInfoByHouseId", map);
	}

	@Override
	public RentalHouse getRentalHouseInfoByHouseId(Long id) {
		return (RentalHouse) getSqlMapClientTemplate().queryForObject(
				"rentalHouse.getRentalHouseInfoByHouseId", id);
	}

	@Override
	public void updateRentalHouseInfoByNewHouseId(Long oldHouseId,
			Long newHouseId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oldHouseId", oldHouseId);
		map.put("newHouseId", newHouseId);
		getSqlMapClientTemplate().update(
				"rentalHouse.updateRentalHouseInfoByNewHouseId", map);

	}

	@Override
	public RentalHouse updateHouseBaseInfoForMobile(RentalHouse rentalHouse) {
		getSqlMapClientTemplate().update(
				"rentalHouse.updateRentalHouseInfoForMobile", rentalHouse);
		return getHouseInfoById(rentalHouse.getId());
	}

	@Override
	public void deleteHouseInfoByIds(List<Long> idList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idList", idList);
		getSqlMapClientTemplate().delete("rentalHouse.deleteRentalHouseByIds",
				map);

	}
}

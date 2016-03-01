package com.tianque.baseInfo.actualHouse.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.buildDatas.domain.OpenLayersMapInfo;
import com.tianque.core.base.BaseDaoImpl;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.domain.vo.SearchHouseInfoVo;
import com.tianque.shard.util.IdConversionShardUtil;
import com.tianque.shard.util.ShardConversion;
import com.tianque.shard.util.ShardTables;

@Repository("actualHouseDao")
public class ActualHouseDaoImpl extends
		BaseDaoImpl<HouseInfo, SearchHouseInfoVo> implements ActualHouseDao {
	@Autowired
	private ShardConversion shardConversion;

	// @Autowired
	// private ActiveMQMessageSender activeMQMessageSender;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.ActualHouseDao#addHouseInfo(com.tianque.domain.HouseInfo)
	 */
	@Override
	public HouseInfo addHouseInfo(HouseInfo houseInfo) {
		Long id = (Long) getSqlMapClientTemplate().queryForObject(
				"houseInfo.getId");
		id = IdConversionShardUtil.IdAdditionalShard(id,
				houseInfo.getShardCode());
		houseInfo.setId(id);
		getSqlMapClientTemplate().insert("houseInfo.addActualHouse", houseInfo);
		return getHouseInfoById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.ActualHouseDao#updateHouseInfo(com.tianque.domain.HouseInfo
	 * )
	 */
	@Override
	public HouseInfo updateHouseInfo(HouseInfo houseInfo) {
		getSqlMapClientTemplate().update("houseInfo.updateActualHouse",
				houseInfo);

		houseInfo = getHouseInfoById(houseInfo.getId());
		// activeMQMessageSender.send(new HouseMsg(houseInfo.getId(),
		// OperateMode.EDIT));

		return houseInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.baseInfo.actualHouse.dao.ActualHouseDao#
	 * updateHouseInfoForPopulation
	 * (com.tianque.baseInfo.actualHouse.domain.HouseInfo)
	 */
	@Override
	public HouseInfo updateHouseInfoForPopulation(HouseInfo houseInfo) {
		houseInfo.setShardCode(shardConversion.getShardCode(houseInfo
				.getOrganization()));
		getSqlMapClientTemplate().update(
				"houseInfo.updateHouseInfoForPopulation", houseInfo);
		houseInfo = getHouseInfoById(houseInfo.getId());

		// activeMQMessageSender.send(new HouseMsg(houseInfo.getOrganization()
		// .getId(), houseInfo.getHouseCode(), OperateMode.EDIT));

		return houseInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.dao.ActualHouseDao#deleteHouseInfoById(java.lang.Long)
	 */
	@Override
	public void deleteHouseInfoById(Long id, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().delete("houseInfo.deleteHouseInfoById", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.ActualHouseDao#getHouseInfoByHouseCodeAndOrganizationId
	 * (java.lang.String, java.lang.Long)
	 */
	@Override
	public HouseInfo getHouseInfoByHouseCodeAndOrganizationId(String houseCode,
			Long organizationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseCode", houseCode);
		map.put("organizationId", organizationId);
		map.put("shardCode", shardConversion.getShardCode(organizationId));
		return (HouseInfo) getSqlMapClientTemplate().queryForObject(
				"houseInfo.getHouseInfoByHouseCodeAndOrgId", map);
	}

	@Override
	public List<HouseInfo> findHouseInfosByHouseCodeAndOrganizationId(
			String houseCode, Long organizationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseCode", houseCode);
		map.put("organizationId", organizationId);
		map.put("shardCode", shardConversion.getShardCode(organizationId));
		return getSqlMapClientTemplate().queryForList(
				"houseInfo.findHouseInfosByHouseCodeAndOrgId", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.dao.ActualHouseDao#getHouseInfoById(java.lang.Long)
	 */
	@Override
	public HouseInfo getHouseInfoById(Long id) {
		if (id == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String shardCode = IdConversionShardUtil.getShardCodeById(id);
		map.put("id", id);
		map.put("shardCode", shardCode);
		return (HouseInfo) getSqlMapClientTemplate().queryForObject(
				"houseInfo.getSimpleHouseInfoById", map);
	}

	public List<HouseInfo> getHouseInfoByIds(List<Long> ids) {
		if (ids == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String shardCode = IdConversionShardUtil.getShardCodeById(ids.get(0));
		map.put("ids", ids);
		map.put("shardCode", shardCode);
		return (List<HouseInfo>) getSqlMapClientTemplate().queryForList(
				"houseInfo.getSimpleHouseInfoByIds", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.ActualHouseDao#findHouseInfosForPage(java.lang.String,
	 * java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public PageInfo<HouseInfo> findHouseInfosForPage(String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		String shardCode = shardConversion
				.getShardCodeByOrgCode(orgInternalCode);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("shardCode", shardCode);

		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		return findHouseInfos(pageNum, pageSize, map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.dao.ActualHouseDao#searchHouseInfos(java.lang.Integer,
	 * java.lang.Integer, java.lang.String, java.lang.String,
	 * com.tianque.domain.vo.SearchHouseInfoVo)
	 */
	@Override
	public PageInfo<HouseInfo> searchHouseInfos(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchHouseInfoVo searchHouseInfoVo) {
		if (null == searchHouseInfoVo) {
			return emptyHouseInfoPage(pageSize);
		}
		Map<String, Object> map = getConditionMap(searchHouseInfoVo, sortField,
				order);
		PageInfo<HouseInfo> pageInfo = new PageInfo<HouseInfo>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseInfo.countSearchHouseInfo", map);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<HouseInfo> list = getSqlMapClientTemplate().queryForList(
					"houseInfo.searchHouseInfo", map, (pageNum - 1) * pageSize,
					pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<HouseInfo>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.ActualHouseDao#searchAllHouseInfos(com.tianque.domain
	 * .vo.SearchHouseInfoVo)
	 */
	@Override
	public List<HouseInfo> searchAllHouseInfos(
			SearchHouseInfoVo searchHouseInfoVo) {
		return getSqlMapClientTemplate().queryForList(
				"houseInfo.searchAllHouseInfos", searchHouseInfoVo);
	}

	private PageInfo<HouseInfo> findHouseInfos(Integer pageNum,
			Integer pageSize, Map<String, Object> map) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseInfo.countHouseInfos", map);
		List<HouseInfo> list = new ArrayList<HouseInfo>();
		if (countNum > 0) {
			list = getSqlMapClientTemplate().queryForList(
					"houseInfo.findHouseInfos", map, (pageNum - 1) * pageSize,
					pageSize);
		}
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private Map getConditionMap(SearchHouseInfoVo condition, String sortField,
			String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (condition == null) {
			return map;
		}
		String shardCode = null;
		if (condition.getOrganization() != null) {
			shardCode = shardConversion.getShardCode(condition
					.getOrganization());
		} else {
			shardCode = shardConversion.getShardCodeByOrgCode(condition
					.getOrgInternalCode());
		}
		map.put("orgInternalCode", condition.getOrgInternalCode());
		map.put("houseCodeAndAddress", condition.getHouseCodeAndAddress());
		map.put("houseCode", condition.getHouseCode());
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
		map.put("landDocuments", condition.getLandDocuments());
		map.put("landRightsNumbe", condition.getLandRightsNumbe());
		map.put("landRightsNumbeDateFrom",
				condition.getLandRightsNumbeDateFrom());
		map.put("landRightsNumbeDateEnd", condition.getLandRightsNumbeDateEnd());
		map.put("houseSource", condition.getHouseSource());
		map.put("memberNum", condition.getMemberNum());
		map.put("sortField", sortField);
		map.put("order", order);
		map.put("houseId", condition.getId());
		map.put("id", condition.getHouseId());
		map.put("shardCode", shardCode);
		return map;
	}

	private PageInfo<HouseInfo> emptyHouseInfoPage(int pageSize) {
		PageInfo<HouseInfo> pageInfo = new PageInfo<HouseInfo>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<HouseInfo>());
		return pageInfo;
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	private PageInfo<HouseInfo> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<HouseInfo> pageInfo = new PageInfo<HouseInfo>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Integer countHouseByBuildingId(Long buildingId, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buildingId", buildingId);
		map.put("shardCode", shardCode);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"houseInfo.countHouseByBuildingId", map);
	}

	@Override
	public PageInfo findHouseInfosForPageByTag(String orgInternalCode,
			Integer page, Integer rows, String sidx, String sord, String tag) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("sord", sord);
		map.put("tag", tag);
		map.put("page", page);
		map.put("rows", rows);
		map.put("orgInternalCode", orgInternalCode);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseInfo.countHouseInfosForPageByTag", map);

		List<HouseInfo> list = getSqlMapClientTemplate().queryForList(
				"houseInfo.findHouseInfosForPageByTag", map, (page - 1) * rows,
				rows);

		return createPageInfo(page, rows, countNum, list);
	}

	@Override
	public HouseInfo updateHousePropertyForGis(HouseInfo houseInfo) {
		houseInfo.setShardCode(shardConversion.getShardCode(houseInfo
				.getOrganization()));
		getSqlMapClientTemplate().update("houseInfo.updateHousePropertyForGis",
				houseInfo);
		return getHouseInfoById(houseInfo.getId());
	}

	@Override
	public PageInfo findHouseInfosByBuildingIdForPage(Long buildingId,
			Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("sord", sord);
		map.put("page", page);
		map.put("rows", rows);
		map.put("buildingId", buildingId);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseInfo.countHouseInfosByBuildingIdForPage", map);

		List<HouseInfo> list = getSqlMapClientTemplate().queryForList(
				"houseInfo.findHouseInfosByBuildingIdForPage", map,
				(page - 1) * rows, rows);

		return createPageInfo(page, rows, countNum, list);
	}

	@Override
	public void unbindHousePropertyById(Long houseId) {
		getSqlMapClientTemplate().update("houseInfo.unbindHousePropertyById",
				houseId);
	}

	@Override
	public List<HouseInfo> searchMapScope(GisInfo minOption, GisInfo maxOption) {
		Map<String, GisInfo> map = new HashMap<String, GisInfo>();
		map.put("minOption", minOption);
		map.put("maxOption", maxOption);
		return (List<HouseInfo>) getSqlMapClientTemplate().queryForList(
				"houseInfo.searchMapScope", map);
	}

	@Override
	public int countUnRentHouseByOrgInternalCode(String orgInternalCode) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"houseInfo.countUnRentHouseByOrgInternalCode", orgInternalCode);
	}

	@Override
	public int countRentHouseByOrgInternalCode(String orgInternalCode) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"houseInfo.countRentHouseByOrgInternalCode", orgInternalCode);
	}

	/************************************ 以下是实有房屋与楼宇绑定 ****************************************/

	@Override
	public PageInfo<HouseInfo> findHouseInfosByBuilddatasIdForPage(
			Long builddatasId, Integer pageNum, Integer pageSize, String sidx,
			String sord, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("builddatasId", builddatasId);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("shardCode", shardCode);

		return findHouseInfos(pageNum, pageSize, map);
	}

	@Override
	public PageInfo<HouseInfo> findHouseInfosListForPage(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, String queryStr) {
		String shardCode = shardConversion
				.getShardCodeByOrgCode(orgInternalCode);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("address", queryStr);
		map.put("houseOwner", queryStr);
		map.put("isBuilddatasId", "0");
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("shardCode", shardCode);
		return findHouseInfos(pageNum, pageSize, map);
	}

	@Override
	public Boolean updateBuildDatasId(HouseInfo houseInfo, String shardCode) {
		houseInfo.setShardCode(shardCode);
		return getSqlMapClientTemplate().update("houseInfo.updateBuildDatasId",
				houseInfo) > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HouseInfo> findHouseInfosListByBuildingId(Long builddatasId,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("builddatasId", builddatasId);
		map.put("shardCode", shardCode);

		List<HouseInfo> list = getSqlMapClientTemplate().queryForList(
				"houseInfo.findHouseInfos", map);
		return list;
	}

	@Override
	public Long countHouseInfoByBuilddatasidAndHouseUse(Long builddatasid,
			Long houseUse, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("builddatasid", builddatasid);
		map.put("houseUse", houseUse);
		map.put("shardCode", shardCode);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"houseInfo.countHouseInfoByBuilddatasidAndHouseUse", map);
	}

	@Override
	public Long countHouseInfoByBuilddatasidAndisrentalhouse(Long builddatasid,
			int isrentalhouse) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("builddatasid", builddatasid);
		map.put("isrentalhouse", isrentalhouse);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"houseInfo.countHouseInfoByBuilddatasidAndisrentalhouse", map);
	}

	@Override
	public void changeHouseCode(Long orgId, String oldHouseCode,
			String houseCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("houseCode", houseCode);
		map.put("oldHouseCode", oldHouseCode);
		map.put("shardCode", shardConversion.getShardCode(orgId));
		getSqlMapClientTemplate().update("houseInfo.changeHouseCode", map);
		getSqlMapClientTemplate()
				.update("houseInfo.changeRentalHouseCode", map);
	}

	@Override
	public int countHouseByOrgIdAndHouseCode(Long orgId, String houseCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("houseCode", houseCode);
		map.put("shardCode", shardConversion.getShardCode(orgId));
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"houseInfo.countHouseByOrgIdAndHouseCode", map);
	}

	@Override
	public void bound(Long id, String centerLat, String centerLon,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("centerLon", centerLon);
		map.put("centerLat", centerLat);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().update("houseInfo.bound", map);

	}

	@Override
	public void unbound(Long id, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().update("houseInfo.unbound", map);

	}

	@Override
	public PageInfo<HouseInfo> findHouseInfosForPage(Long orgId, Long houseId,
			Integer page, Integer rows, String sidx, String sord) {
		String shardCode = shardConversion.getShardCode(orgId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("houseId", houseId);
		map.put("sidx", sidx);
		map.put("sord", sord);
		map.put("shardCode", shardCode);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseInfo.countFindHouseListByOrgIdExcludeHousId", map);

		List<HouseInfo> list = getSqlMapClientTemplate().queryForList(
				"houseInfo.findHouseListByOrgIdExcludeHousId", map,
				(page - 1) * rows, rows);

		return new PageInfo<HouseInfo>(page, rows, countNum, list);
	}

	@Override
	public HouseInfo getHouseInfoByHouseAddressAndOrganizationId(
			String address, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("address", address);
		map.put("organizationId", orgId);
		map.put("shardCode", shardConversion.getShardCode(orgId));
		return (HouseInfo) getSqlMapClientTemplate().queryForObject(
				"houseInfo.getHouseInfoByHouseAddressAndOrgId", map);
	}

	@Override
	public void synchronizationBuildName(Long id, String buildingName,
			OpenLayersMapInfo openLayersMapInfo, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("buildingName", buildingName);
		map.put("openLayersMapInfo", openLayersMapInfo);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().update("houseInfo.updateBuildingName", map);
	}

	@Override
	public List<HouseInfo> findHouseInfosByHouseAddressAndOrgId(String address,
			Long organizationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("address", address);
		map.put("organizationId", organizationId);
		map.put("shardCode", shardConversion.getShardCode(organizationId));
		return getSqlMapClientTemplate().queryForList(
				"houseInfo.findHouseInfosByHouseAddressAndOrgId", map);
	}

	/**
	 * 
	 * @Title: findHouseInfosByHouseAddressAndOrgIdForMobile
	 * @Description: TODO为手机端新增查询地址方法
	 * @param @param address
	 * @param @param organizationId
	 * @param @return 设定文件
	 * @return List<HouseInfo> 返回类型
	 * @author wanggz
	 * @date 2014-6-20 上午03:22:26
	 */
	@Override
	public PageInfo<HouseInfo> findHouseInfosByHouseAddressAndOrgIdForMobile(
			String address, Long organizationId) {
		PageInfo<HouseInfo> pageInfo = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("address", address);
		map.put("organizationId", organizationId);
		map.put("shardCode", shardConversion.getShardCode(organizationId));
		List<HouseInfo> list = getSqlMapClientTemplate().queryForList(
				"houseInfo.findHouseInfosByHouseAddressAndOrgId", map,
				(1 - 1) * 20, 20);
		pageInfo = new PageInfo<HouseInfo>();
		pageInfo.setResult(list);
		return pageInfo;
	}

	@Override
	public Integer getCount(SearchHouseInfoVo searchHouseInfoVo) {
		if (null == searchHouseInfoVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"houseInfo.countSearchHouseInfo",
				getConditionMap(searchHouseInfoVo, null, null));
	}

	@Override
	public List<HouseInfo> checkIsHasHouseByHouseCodeAndOrgId(String houseCode,
			Long organizationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseCode", houseCode);
		map.put("organizationId", organizationId);
		return getSqlMapClientTemplate().queryForList(
				"houseInfo.checkIsHasHouseByHouseCodeAndOrgId", map);
	}

	@Override
	public PageInfo<HouseInfo> findHouseInfoByHouseAddressAndOrgId(
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String address, Long orgId, Long id) {
		String shardCode = shardConversion.getShardCode(orgId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("address", address);
		map.put("orgId", orgId);
		map.put("id", id);
		map.put("shardCode", shardCode);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseInfo.countHouseInfoByAddressAndOrgId", map);
		int pageCount = 0;
		pageCount = countNum % pageSize == 0 ? countNum / pageSize : countNum
				/ pageSize + 1;

		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<HouseInfo> list = getSqlMapClientTemplate().queryForList(
				"houseInfo.findHouseInfoByAddressAndOrgId", map,
				(pageNum - 1) * pageSize, pageSize);

		return new PageInfo<HouseInfo>(pageNum, pageSize, countNum, list);
	}

	@Override
	public void updateHouseInfoSimple(HouseInfo houseInfo) {
		houseInfo.setShardCode(shardConversion.getShardCode(houseInfo
				.getOrganization()));
		getSqlMapClientTemplate().update("houseInfo.updateSimpleHouseInfo",
				houseInfo);
	}

	@Override
	public List<HouseInfo> findHouseInfosByStartIdAndEndId(Long startId,
			Long endId) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("startId", startId);
		map.put("endId", endId);
		return getSqlMapClientTemplate().queryForList(
				"houseInfo.findHouseInfosByStartIdAndEndId", map);
	}

	@Override
	public Integer getMaxHouseInfoId() {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"houseInfo.getMaxHouseInfoId");
	}

	@Override
	public List<Long> findIdsFromRecoverDataInfosByStartDateAndType(
			Date startDate, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("type", type);
		return getSqlMapClientTemplate().queryForList(
				"houseInfo.findIdsFromRecoverDataInfosByStartDateAndType", map);
	}

	// 房屋人数
	@Override
	public void updateHouseInfoMemberNum(String shardCode, Long houseId, int num) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", "houseinfo_" + shardCode);
		map.put("houseId", houseId);
		map.put("num", num);
		getSqlMapClientTemplate().update("houseInfo.updateHouseInfoMemberNum",
				map);
	}

	// 房屋人数
	@Override
	public Long getLogOutByPopulationTypeAndPopulationId(String shardCode,
			String populationType, Long populationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (ShardTables.SHARD_HOUSEHOLDSTAFFS
				.equalsIgnoreCase(BaseInfoTables.personnelTables
						.get(populationType))) {
			map.put("tableName",
					BaseInfoTables.personnelTables.get(populationType) + "_"
							+ shardCode);
		} else {
			map.put("tableName",
					BaseInfoTables.personnelTables.get(populationType));
		}
		map.put("populationId", populationId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"houseInfo.getLogOutByPopulationTypeAndPopulationId", map);
	}

	@Override
	public Integer countResidential(String orgCode, Long buildingUses,
			String shardCode, String houseType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		map.put("buildingUses", buildingUses);
		map.put("shardCode", shardCode);
		if (NewBaseInfoTables.RENTALHOUSE_KEY.equals(houseType)) {
			map.put("isRentalHouse", 1);
		}
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseInfo.countResidential", map);
		return count == null ? 0 : count;
	}

	@Override
	public Integer countRelation(String orgCode, Long buildingUses,
			String shardCode, String houseType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		map.put("buildingUses", buildingUses);
		map.put("shardCode", shardCode);
		if (NewBaseInfoTables.RENTALHOUSE_KEY.equals(houseType)) {
			map.put("isRentalHouse", 1);
		}
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseInfo.countRelation", map);
		return count == null ? 0 : count;
	}
}

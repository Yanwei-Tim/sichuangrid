package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.buildDatas.domain.vo.LayoutTagVo;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.HouseInfoDao;
import com.tianque.gis.util.GisGlobalValue;
import com.tianque.shard.util.IdConversionShardUtil;
import com.tianque.shard.util.ShardConversion;

@Repository("houseInfoDao")
public class HouseInfoDaoImpl extends AbstractBaseDao implements HouseInfoDao {
	@Autowired
	private ShardConversion shardConversion;

	@Override
	public HouseInfo addHouseInfo(HouseInfo houseInfo) {
		Long id = (Long) getSqlMapClientTemplate().queryForObject(
				"houseInfo.getId");
		id = IdConversionShardUtil.IdAdditionalShard(id,
				houseInfo.getShardCode());
		houseInfo.setId(id);
		getSqlMapClientTemplate().insert("houseInfo.addHouseInfo", houseInfo);
		return getSimpleHouseInfoById(id);
	}

	@Override
	public HouseInfo updateHouseInfo(HouseInfo houseInfo) {
		getSqlMapClientTemplate()
				.update("houseInfo.updateHouseInfo", houseInfo);
		return getSimpleHouseInfoById(houseInfo.getId());
	}

	@Override
	public void deleteInfrastructureByHouseInfoId(Long houseInfoId) {
		getSqlMapClientTemplate().delete(
				"houseInfo.deleteInfrastructureByHouseInfoId", houseInfoId);
	}

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
	public HouseInfo getSimpleHouseInfoById(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		String shardCode = IdConversionShardUtil.getShardCodeById(id);
		map.put("id", id);
		map.put("shardCode", shardCode);
		return (HouseInfo) getSqlMapClientTemplate().queryForObject(
				"houseInfo.getSimpleHouseInfoById", map);
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
	public HouseInfo unBundHouseInfoById(Long id, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().update("houseInfo.unBundHouseInfoById", id);
		return getSimpleHouseInfoById(id);
	}

	@Override
	public HouseInfo findGisHouseInfoById(Long houseId) {
		return (HouseInfo) getSqlMapClientTemplate().queryForObject(
				"houseInfo.findGisHouseInfoById", houseId);
	}

	@Override
	public PageInfo<HouseInfo> getGisByQueryStrAndOrgId(String orgCode,
			String queryStr, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgCode);
		map.put("queryStr", queryStr);

		return getGisHouseInfos(pageNum, pageSize, map);
	}

	private PageInfo<HouseInfo> getGisHouseInfos(Integer pageNum,
			Integer pageSize, Map<String, Object> map) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseInfo.countGisHouseInfos", map);
		List<HouseInfo> list = getSqlMapClientTemplate().queryForList(
				"houseInfo.getGisHouseInfoByQueryStrAndOrgCode", map,
				(pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo<HouseInfo> getkeyHouseInfoForGisByorgInternalCode(
			String orgInternalCode, String houseType, Integer page,
			Integer rows, String sidx, String sord) {
		return isRentalHouse(houseType, orgInternalCode, page, rows);
	}

	private PageInfo<HouseInfo> isRentalHouse(String houseType,
			String orgInternalCode, Integer page, Integer rows) {
		if (houseType.equals(GisGlobalValue.RENTALHOUSE)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgInternalCode", orgInternalCode);
			map.put("houseType", true);
			Integer countNum = (Integer) getSqlMapClientTemplate()
					.queryForObject("houseInfo.countKeyRentalGishouseInfos",
							map);
			List<HouseInfo> list = getSqlMapClientTemplate().queryForList(
					"houseInfo.getKeyRentalGishouseInfosByOrgCode", map,
					(page - 1) * rows, rows);
			return createPageInfo(page, rows, countNum, list);
		} else {
			Integer countNum = (Integer) getSqlMapClientTemplate()
					.queryForObject("houseInfo.countKeyGishouseInfos",
							orgInternalCode);
			List<HouseInfo> list = getSqlMapClientTemplate().queryForList(
					"houseInfo.getKeyGishouseInfosByOrgCode", orgInternalCode,
					(page - 1) * rows, rows);
			return createPageInfo(page, rows, countNum, list);
		}
	}

	@Override
	public List<HouseInfo> countActualHouseByOrgCode(String orgInternalCode) {
		List<HouseInfo> result = getSqlMapClientTemplate().queryForList(
				"houseInfo.countActualHouseByOrgCode", orgInternalCode);
		result.addAll(countRentalHouseByorgCode(orgInternalCode));
		return result;
	}

	private List<HouseInfo> countRentalHouseByorgCode(String orgInternalCode) {
		return getSqlMapClientTemplate().queryForList(
				"houseInfo.countRentalHouseByorgCode", orgInternalCode);
	}

	@Override
	public List<HouseInfo> findAllBindingHouseInfoByOrgInternalCode(
			String orgInternalCode) {
		return getSqlMapClientTemplate().queryForList(
				"houseInfo.findAllBindingHouseInfoByOrgInternalCode",
				orgInternalCode);
	}

	@Override
	public List<HouseInfo> findAllBindingHouseInfoByBuildingId(Long buildingId) {
		return getSqlMapClientTemplate().queryForList(
				"houseInfo.findAllBindingHouseInfoByBuildingId", buildingId);
	}

	@Override
	public List<LayoutTagVo> searchAllHouseName4LayoutTag(
			String orgInternalCode, Long builddatasId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("builddatasid", builddatasId);
		map.put("shardCode",
				shardConversion.getShardCodeByOrgCode(orgInternalCode));
		List<LayoutTagVo> layoutTagList = (List<LayoutTagVo>) getSqlMapClientTemplate()
				.queryForList("houseInfo.searchAllHouseName", map);
		return layoutTagList;
	}

	@Override
	public String getHouseRoomAndNameById(Long id) {
		HouseInfo houseInfo = getSimpleHouseInfoById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("shardCode",
				shardConversion.getShardCode(houseInfo.getOrganization()));
		return (String) getSqlMapClientTemplate().queryForObject(
				"houseInfo.getHouseRoomAndNameById", map);
	}

	@Override
	public List<HouseInfo> findHouseInfoByOrgIdAddress(Long orgId,
			String address) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("address", address);
		map.put("shardCode", shardConversion.getShardCode(orgId));
		return getSqlMapClientTemplate().queryForList(
				"houseInfo.findHouseInfoByOrgIdAddress", map);
	}

}

package com.tianque.baseInfo.permanentAddress.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.permanentAddress.domain.PermanentAddress;
import com.tianque.baseInfo.permanentAddress.domain.PermanentAddressLog;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.cache.service.impl.CacheNameSpace;
import com.tianque.core.vo.PageInfo;

@Repository("permanentAddressDao")
public class PermanentAddressDaoImpl extends AbstractBaseDao implements
		PermanentAddressDao {

	@Autowired
	private CacheService cacheService;

	@Override
	public PermanentAddress getPermanentAddressByNo(
			PermanentAddress permanentAddress) {
		if (null == permanentAddress || null == permanentAddress.getAddressNo()) {
			return null;
		}
		return (PermanentAddress) getSqlMapClientTemplate().queryForObject(
				"permanentAddress.getPermanentAddressByNo", permanentAddress);
	}

	@Override
	public PermanentAddress getPermanentAddressByAddressNo(String addressNo) {
		if (null == addressNo || "".equals(addressNo)) {
			return null;
		}
		return (PermanentAddress) getSqlMapClientTemplate().queryForObject(
				"permanentAddress.getPermanentAddressByAddressNo", addressNo);
	}

	@Override
	public PermanentAddress addPermanentAddress(
			PermanentAddress permanentAddress, PermanentAddressLog palog) {
		if (null != permanentAddress
				&& null != permanentAddress.getAddressName()
				&& null != permanentAddress.getAddressNo()) {
			getSqlMapClientTemplate().insert(
					"permanentAddress.addPermanentAddress", permanentAddress);
			if (null != palog) {
				String parentName = (palog.getProvince() != null ? palog
						.getProvince() : "")
						+ (palog.getCity() != null ? palog.getCity() : "");
				removeCacheByKey(parentName);
			}
			return permanentAddress;
		}
		return null;
	}

	@Override
	public PageInfo<PermanentAddress> getPermanentAddressByLevel(String level,
			Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap();
		map.put("level", level);
		map.put("sortField", sidx);
		map.put("order", sord);
		return page("getPermanentAddressByLevel",
				"getPermanentAddressByLevelCount", map, page, rows);
	}

	@Override
	public List<PermanentAddress> getPermanentAddressByParentid(String parentid) {
		return getSqlMapClientTemplate().queryForList(
				"permanentAddress.getPermanentAddressByParentid", parentid);
	}

	@Override
	public List<PermanentAddress> getPermanentAddressByParentName(
			String parentName) {
		List<PermanentAddress> list = (List<PermanentAddress>) cacheService
				.get(CacheNameSpace.PermanentAddressDao_getPermanentAddressByParentName,
						"parentName_" + parentName);
		if (null == list || list.size() < 1) {
			list = getSqlMapClientTemplate().queryForList(
					"permanentAddress.getPermanentAddressByParentName",
					parentName);
			cacheService
					.set(CacheNameSpace.PermanentAddressDao_getPermanentAddressByParentName,
							"parentName_" + parentName, list);
		}
		return list;
	}

	@Override
	public List<PermanentAddress> getPermanentAddressByParentNameAndPId(
			String provinceName, String parentName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("province", provinceName);
		map.put("address", parentName);
		List<PermanentAddress> list = (List<PermanentAddress>) cacheService
				.get(CacheNameSpace.PermanentAddressDao_getPermanentAddressByParentNameAndPId,
						"parentNameAndPid_" + provinceName + parentName);
		if (null == list || list.size() < 1) {
			list = getSqlMapClientTemplate().queryForList(
					"permanentAddress.getPermanentAddressByParentNameAndPId",
					map);
			cacheService
					.set(CacheNameSpace.PermanentAddressDao_getPermanentAddressByParentNameAndPId,
							"parentNameAndPid_" + provinceName + parentName,
							list);
		}
		return list;
	}

	@Override
	public PageInfo<PermanentAddress> findPermanentAddress(Integer pageNum,
			Integer pageSize, String sortField, String order) {
		Map<String, Object> map = new HashMap();
		map.put("sortField", sortField);
		map.put("order", order);
		return page("findPermanentAddress", "findPermanentAddressCount", map,
				pageNum, pageSize);
	}

	private PageInfo<PermanentAddress> page(String listFounctionName,
			String countFounctionName, Map<String, Object> map,
			Integer pageNum, Integer pageSize) {
		List<PermanentAddress> list = getSqlMapClientTemplate().queryForList(
				"permanentAddress." + listFounctionName, map,
				(pageNum - 1) * pageSize, pageSize);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"permanentAddress." + countFounctionName, map);

		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		PageInfo<PermanentAddress> pageInfo = new PageInfo<PermanentAddress>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum > pageCount ? pageCount : pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;

	}

	@Override
	public boolean deletePermanentAddress(String addressNo,
			PermanentAddressLog palog) {
		int result = getSqlMapClientTemplate().delete(
				"permanentAddress.deletePermanentAddress", addressNo);
		if (null != palog) {
			String parentName = "";
			String province = palog.getProvince();
			String city = palog.getCity();
			String district = palog.getDistrict();
			if (city != null && !"".equals(city)) {
				parentName += province != null ? province : "";
				if (district != null && !"".equals(district)) {
					parentName += city != null ? city : "";
				}
			}
			removeCacheByKey(parentName);
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<PermanentAddress> getPermanentAddressByLevel(String level) {
		List<PermanentAddress> list = (List<PermanentAddress>) cacheService
				.get(CacheNameSpace.PermanentAddressDao_getPermanentAddressByLevel);
		if (null == list || list.size() < 1) {
			Map<String, Object> map = new HashMap();
			map.put("level", level);
			list = getSqlMapClientTemplate().queryForList(
					"permanentAddress.getPermanentAddressByLevel", map);
			cacheService
					.set(CacheNameSpace.PermanentAddressDao_getPermanentAddressByLevel,
							list);
		}
		return list;
	}

	@Override
	public PageInfo<PermanentAddress> searchPermanentAddress(String str,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap();
		map.put("str", str);
		map.put("sortField", sidx);
		map.put("order", sord);
		return page("searchPermanentAddress", "searchPermanentAddressCount",
				map, pageNum, pageSize);
	}

	@Override
	public PermanentAddress updatePermanentAddress(
			PermanentAddress permanentAddress, PermanentAddressLog palog) {
		int result = getSqlMapClientTemplate().update(
				"permanentAddress.updatePermanentAddress", permanentAddress);
		if (null != palog) {
			String parentName = "";
			String province = palog.getProvince();
			String city = palog.getCity();
			String district = palog.getDistrict();
			if (city != null && !"".equals(city)) {
				parentName += province != null ? province : "";
				if (district != null && !"".equals(district)) {
					parentName += city != null ? city : "";
				}
			}
			removeCacheByKey(parentName);
		}
		if (result > 0) {
			return getPermanentAddressByAddressNo(permanentAddress
					.getAddressNo());
		}
		return null;
	}

	private void removeCacheByKey(String parentName) {
		cacheService
				.remove(CacheNameSpace.PermanentAddressDao_getPermanentAddressByParentName,
						"parentName_" + parentName);
		cacheService
				.remove(CacheNameSpace.PermanentAddressDao_getPermanentAddressByParentNameAndPId,
						"parentNameAndPid_" + parentName);

		cacheService
				.remove(CacheNameSpace.PermanentAddressDao_getPermanentAddressByLevel);
	}

	@Override
	public List<PermanentAddress> getPermanentAddressByAddressNameAndPIdAndAddressNo(
			PermanentAddress permanentAddress) {
		return getSqlMapClientTemplate()
				.queryForList(
						"permanentAddress.getPermanentAddressByAddressNameAndPIdAndAddressNo",
						permanentAddress);
	}

	@Override
	public String getPermanentAddressByParentNames(Map allParentAddress) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"permanentAddress.getPermanentAddressByParentNames",
				allParentAddress);
	}

	@Override
	public List<String> getPermanentAddressByParentNameAndAddressLevel(
			String parentName, String addressLevel) {
		Map<String, Object> map = new HashMap();
		map.put("addressName", parentName);
		map.put("addresslevel", addressLevel);
		List<String> list = getSqlMapClientTemplate()
				.queryForList(
						"permanentAddress.getPermanentAddressByParentNameAndAddressLevel",
						map);
		return list;
	}

	public List<PermanentAddress> getPermanentAddressByAddressNameAndAddressLevel(
			String addressName, String addressLevel) {
		Map<Object, String> map = new HashMap<Object, String>();
		map.put("addressName", addressName);
		map.put("addressLevel", addressLevel);
		return getSqlMapClientTemplate()
				.queryForList(
						"permanentAddress.getPermanentAddressByAddressNameAndAddressLevel",
						map);
	}

	@Override
	public List<String> getPermanentAddressByParentNameAndAddressNameAndAddressLevel(
			String addressName, String addressLevel, String parentName) {
		Map<Object, String> map = new HashMap<Object, String>();
		map.put("addressName", addressName);
		map.put("addressLevel", addressLevel);
		map.put("parentName", parentName);
		List<String> list = getSqlMapClientTemplate()
				.queryForList(
						"permanentAddress.getPermanentAddressByParentNameAndAddressNameAndAddressLevel",
						map);
		return list;
	}
}

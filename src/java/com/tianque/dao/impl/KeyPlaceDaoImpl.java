package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.buildDatas.domain.vo.LayoutTagVo;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.KeyPlaceDao;
import com.tianque.domain.KeyPlace;

@Repository("keyPlaceDao")
public class KeyPlaceDaoImpl extends AbstractBaseDao implements KeyPlaceDao {

	@Override
	public KeyPlace addKeyPlace(KeyPlace keyPlace) {
		final Long id_key = (Long) getSqlMapClientTemplate().insert(
				"keyPlace.addKeyPlace", keyPlace);
		return getKeyPlaceByIdKey(id_key);
	}

	@Override
	public KeyPlace getKeyPlaceByIdKey(Long id_key) {
		KeyPlace keyPlace = (KeyPlace) getSqlMapClientTemplate()
				.queryForObject("keyPlace.getKeyPlaceById", id_key);
		return keyPlace;
	}

	@Override
	public KeyPlace updateKeyPlace(KeyPlace keyPlace) {
		if (null == keyPlace.getId() && null == keyPlace.getType()) {
			return null;
		}
		getSqlMapClientTemplate().update("keyPlace.updateKeyPlace", keyPlace);
		return keyPlace;
	}

	@Override
	public PageInfo<KeyPlace> searchKeyPlaceByName(String name,
			String orgInternalCode, Integer page, Integer rows) {
		if (null == orgInternalCode) {
			return null;
		}
		Map<String, String> query = new HashMap<String, String>();
		query.put("name", name + "%");
		query.put("orgInternalCode", orgInternalCode + "%");
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"keyPlace.countSearchKeyPlaceByName", query);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<KeyPlace> keyPlaces = (List<KeyPlace>) getSqlMapClientTemplate()
				.queryForList("keyPlace.searchKeyPlaceByName", query,
						(page - 1) * rows, rows);
		return createPageInfo(keyPlaces, countNum, page, rows);
	}

	private PageInfo<KeyPlace> createPageInfo(List<KeyPlace> keyPlaces,
			Integer countNum, Integer page, Integer rows) {
		PageInfo<KeyPlace> pageInfo = new PageInfo<KeyPlace>();
		pageInfo.setResult(keyPlaces);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	public List<KeyPlace> searchKeyPlaceByName(Map<String, String> map) {
		if (null == map.get("name")) {
			return null;
		}

		map.put("name", map.get("name") + "%");
		List<KeyPlace> keyPlaces = (List<KeyPlace>) getSqlMapClientTemplate()
				.queryForList("keyPlace.searchKeyPlaceByName", map);
		return keyPlaces;
	}

	@Override
	public void deleteKeyPlaceByIdAndType(KeyPlace keyPlace) {
		if (null == keyPlace.getId() || null == keyPlace.getType()) {
			return;
		}
		getSqlMapClientTemplate().delete("keyPlace.deleteKeyPlaceByIdAndType",
				keyPlace);
	}

	@Override
	public void deleteKeyPlaceByIdAndInType(Long id, String types) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", String.valueOf(id));
		map.put("types", types);
		getSqlMapClientTemplate().delete(
				"keyPlace.deleteKeyPlaceByIdAndInType", map);
	}

	@Override
	public void deleteKeyPlaceByIdsAndInType(List<Long> ids, String types) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("types", types);
		getSqlMapClientTemplate().delete(
				"keyPlace.deleteKeyPlaceByIdsAndInType", map);
	}

	@Override
	public PageInfo<KeyPlace> findBoundKeyPlaceByBuilddatasIdForPage(
			Long builddatasid, int page, int rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("builddatasId", builddatasid);
		map.put("sortField", "id_key");
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"keyPlace.countBoundKeyPlaceByBuilddatasId", map);

		List<KeyPlace> list = getSqlMapClientTemplate().queryForList(
				"keyPlace.findBoundKeyPlaceByBuilddatasIdForPage", map,
				(page - 1) * rows, rows);
		for (int i = 0, len = list.size(); i < len; i++) {
			KeyPlace place = list.get(i);
			if (place.getType().equals(BaseInfoTables.OTHER_LOCALE_KEY)) {
				place.setType(BaseInfoTables.OTHERLOCALE_KEY);
			}
			place.setType(BaseInfoTables.getTypeDisplayNames(place.getType()));
		}
		PageInfo<KeyPlace> pageInfo = new PageInfo<KeyPlace>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);

		return pageInfo;
	}

	@Override
	public PageInfo<KeyPlace> findUnBoundKeyPlaceByOrgId(
			String orgInternalCode, int page, int rows, String sidx,
			String sord, String queryStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", "id_key");
		map.put("order", sord);
		map.put("queryStr", queryStr);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"keyPlace.countUnboundKeyPlaceByOrg", map);

		List<KeyPlace> list = getSqlMapClientTemplate().queryForList(
				"keyPlace.findUnboundKeyPlaceByOrgForPage", map,
				(page - 1) * rows, rows);
		for (int i = 0, len = list.size(); i < len; i++) {
			KeyPlace place = list.get(i);
			if (place.getType().equals(BaseInfoTables.OTHER_LOCALE_KEY)) {
				place.setType(BaseInfoTables.OTHERLOCALE_KEY);
			}
			place.setType(BaseInfoTables.getTypeDisplayNames(place.getType()));
		}
		PageInfo<KeyPlace> pageInfo = new PageInfo<KeyPlace>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public void boundKeyPlace(List<Long> keyPlaceIds, Long builddatasId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", keyPlaceIds);
		map.put("buildDatasId", builddatasId);
		getSqlMapClientTemplate().update("keyPlace.boundKeyPlace", map);
	}

	@Override
	public void unboundKeyPlace(List<Long> keyPlaceIds) {
		if (keyPlaceIds != null && keyPlaceIds.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ids", keyPlaceIds);
			getSqlMapClientTemplate().update("keyPlace.unboundKeyPlace", map);
		}
	}

	@Override
	public List<LayoutTagVo> searchAllKeyPlaceName4LayoutTag(
			String orgInternalCode, Long buildDatasId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("builddatasId", buildDatasId);
		List<LayoutTagVo> layoutTagList = (List<LayoutTagVo>) getSqlMapClientTemplate()
				.queryForList("keyPlace.searchAllKeyPlaceName", map);
		return layoutTagList;
	}

	@Override
	public List<KeyPlace> findKeyPlaceListByBuilddatasId(Long buildDatasId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("builddatasId", buildDatasId);
		List<KeyPlace> list = getSqlMapClientTemplate().queryForList(
				"keyPlace.findBoundKeyPlaceByBuilddatasIdForPage", map);
		return list;
	}

	@Override
	public Integer countKeyPlaceByBuilddatasIdAndKeyPlaceType(
			long buildDatasId, String keyPlaceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("builddatasId", buildDatasId);
		map.put("type", keyPlaceType);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"keyPlace.countKeyPlaceByBuilddatasIdAndKeyPlaceType", map);
	}

	@Override
	public String getKeyPlaceNameByIdAndType(Long id, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		return (String) getSqlMapClientTemplate().queryForObject(
				"keyPlace.getKeyPlaceNameByIdAndType", map);
	}

	@Override
	public void emphasisAndNotEmphasis(KeyPlace keyPlace) {
		keyPlace.setUpdateDate(ThreadVariable.getSession().getAccessTime());
		getSqlMapClientTemplate().update("keyPlace.emphasisAndNotEmphasis",
				keyPlace);
	}

	@Override
	public void emphasisKeyPlacesByIdsAndTypes(List<Long> ids, String types,
			Long emphasis) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("types", types);
		map.put("emphasis", emphasis);
		map.putAll(getUpdateDateAndUser());
		getSqlMapClientTemplate().update(
				"keyPlace.emphasisKeyPlacesByIdsAndTypes", map);
	}

	@Override
	public List<KeyPlace> getKeyPlaceByIdInType(Long id, String types) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("types", types);
		List<KeyPlace> list = getSqlMapClientTemplate().queryForList(
				"keyPlace.getKeyPlaceByIdInType", map);
		return list;
	}

	@Override
	public KeyPlace getKeyPlaceByIdAndType(Long id, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		return (KeyPlace) getSqlMapClientTemplate().queryForObject(
				"keyPlace.getKeyPlaceByIdAndType", map);
	}

	@Override
	public void updateKeyPlaceByIdAndOrgInType(Long id, Long orgId,
			String orgInternalCode, String types) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", types);
		map.put("orgId", orgId);
		map.put("orgInternalCode", orgInternalCode);
		map.putAll(getUpdateDateAndUser());
		getSqlMapClientTemplate().update(
				"keyPlace.updateKeyPlaceByIdAndOrgInType", map);
	}

	@Override
	public void updateDomainByKeyplaces(Map<String, Object> map) {
		getSqlMapClientTemplate().update("keyPlace.updateDomainByKeyplaces",
				map);
	}
}

package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.FloorpersonDao;
import com.tianque.domain.Floorperson;

@Repository("floorpersonDao")
public class FloorpersonDaoImpl extends AbstractBaseDao implements FloorpersonDao {

	@Override
	public Floorperson addFloorperson(Floorperson floorperson) {
		Long id = (Long) getSqlMapClientTemplate()
				.insert("floorperson.addFloorperson", floorperson);
		return getFloorperson(id);
	}

	@Override
	public PageInfo<Floorperson> findFloorperson(Long placeId, int pageNum, int pageSize,
			String sortField, String order, String placeType) {
		PageInfo<Floorperson> pageInfo = new PageInfo<Floorperson>();
		Map<String, Object> query = new HashMap<String, Object>();
		if (StringUtil.isStringAvaliable(sortField)) {
			query.put("sortField", sortField);
			query.put("order", order);
		}
		query.put("placeId", placeId);
		query.put("placeType", placeType);
		List list = getSqlMapClientTemplate().queryForList("floorperson.findFloorperson", query,
				(pageNum - 1) * pageSize, pageSize);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"floorperson.countFloorperson", query);
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Floorperson getFloorperson(Long id) {
		return (Floorperson) getSqlMapClientTemplate().queryForObject(
				"floorperson.getFloorpersonById", id);
	}

	@Override
	public Floorperson updateFloorperson(Floorperson floorperson) {
		getSqlMapClientTemplate().update("floorperson.updateFloorperson", floorperson);
		return getFloorperson(floorperson.getId());
	}

	@Override
	public void deleteFloorperson(Long placeId, String placeType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("placeId", placeId);
		map.put("placeType", placeType);
		getSqlMapClientTemplate().delete("floorperson.deleteFloorperson", map);
	}

	@Override
	public void deleteFloorpersonById(Long id) {
		getSqlMapClientTemplate().delete("floorperson.deleteFloorpersonById", id);
	}

}

package com.tianque.baseInfo.roadTraffic.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.roadTraffic.domain.RoadTraffic;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("roadTrafficDao")
public class RoadTrafficDaoImpl extends AbstractBaseDao implements
		RoadTrafficDao {

	@Override
	public PageInfo<RoadTraffic> findRoadTrafficForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer countNum = 0;
		List<RoadTraffic> list = null;
		map.put("orgInternalCode", orgInternalCode);

		countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"roadTraffic.countRoadTraffic", map);

		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		list = getSqlMapClientTemplate().queryForList(
				"roadTraffic.findRoadTraffic", map, (pageNum - 1) * pageSize,
				pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	private PageInfo<RoadTraffic> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<RoadTraffic> pageInfo = new PageInfo<RoadTraffic>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public RoadTraffic getRoadTrafficById(Long id) {

		return (RoadTraffic) getSqlMapClientTemplate().queryForObject(
				"roadTraffic.getRoadTrafficById", id);

	}

	@Override
	public RoadTraffic addRoadTraffic(RoadTraffic roadTraffic) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"roadTraffic.addRoadTraffic", roadTraffic);
		return getRoadTrafficById(id);

	}

	@Override
	public RoadTraffic updateRoadTraffic(RoadTraffic roadTraffic) {
		checkIsNull(roadTraffic);
		getSqlMapClientTemplate().update("roadTraffic.updateRoadTraffic",
				roadTraffic);
		roadTraffic = getRoadTrafficById(roadTraffic.getId());
		return roadTraffic;
	}

	private void checkIsNull(RoadTraffic roadTraffic) {
		if (roadTraffic == null) {
			throw new BusinessValidationException("不能为空!");
		} else {
			if (null == roadTraffic.getObjName()) {
				throw new BusinessValidationException("部件名称不能为空!");
			}

			if (null == roadTraffic.getOrganization()
					|| null == roadTraffic.getOrganization().getId()) {
				throw new BusinessValidationException("所属网格不能为空!");
			}
		}
	}

	@Override
	public void deleteRoadTrafficById(Long id) {
		getSqlMapClientTemplate().delete("roadTraffic.deleteRoadTrafficbyId",
				id);
	}

	@Override
	public RoadTraffic getRoadTrafficByObjNum(String objNum, Long orgId) {
		RoadTraffic roadTraffic = null;
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("objNum", objNum);
		roadTraffic = (RoadTraffic) getSqlMapClientTemplate().queryForObject(
				"roadTraffic.getRoadTrafficByObjNum", query);
		return roadTraffic;
	}

}

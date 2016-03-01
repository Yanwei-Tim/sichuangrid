package com.tianque.baseInfo.buildDatas.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.buildDatas.domain.HouseBuilddatasBinding;
import com.tianque.core.base.BaseDaoImpl;
import com.tianque.core.vo.PageInfo;

/**
 * @描述:
 * @版权:
 * @公司:
 * @作者:王熙斌
 * @创建时间：2013-1-29 下午1:29:41
 **/
@Repository("houseBuilddatasBindingDao")
public class HouseBuilddatasBindingDaoImpl extends
		BaseDaoImpl<HouseBuilddatasBinding, HouseBuilddatasBinding> implements
		HouseBuilddatasBindingDao {

	@Override
	public PageInfo<HouseInfo> findHouseInfosByBuilddatasId(Long builddatasId, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("builddatasId", builddatasId);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseBuilddatasBinding.countHouseInfosByBuilddatasId", builddatasId);
		List<HouseInfo> resultList = getSqlMapClientTemplate().queryForList(
				"houseBuilddatasBinding.findHouseInfosByBuilddatasId", map,
				(pageNum - 1) * pageSize, pageSize);
		return new PageInfo<HouseInfo>(pageSize, pageSize, countNum, resultList);
	}

	@Override
	public PageInfo<HouseInfo> findUnBoundedByOrgInternalCode(String orgInternalCode, int page,
			int rows, String sidx, String sord, String queryStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("address", queryStr);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseBuilddatasBinding.countHouseInfosByOrgInternalCode", map);
		List<HouseInfo> resultList = getSqlMapClientTemplate().queryForList(
				"houseBuilddatasBinding.findHouseInfosByOrgInternalCode", map, (page - 1) * rows,
				rows);
		return new PageInfo<HouseInfo>(rows, rows, countNum, resultList);

	}

	@Override
	public void addBinding(HouseBuilddatasBinding houseBuilddatasBinding) {
		getSqlMapClientTemplate().insert("houseBuilddatasBinding.addBinding",
				houseBuilddatasBinding);
	}

	@Override
	public void removeBindingHouseInfo(List<Long> houseIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseIds", houseIds);
		getSqlMapClientTemplate().delete("houseBuilddatasBinding.removeBinding", map);
	}
}

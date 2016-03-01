package com.tianque.openLayersMap.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.AbstractDao;
import com.tianque.openLayersMap.dao.HourseInfoDao;
import com.tianque.openLayersMap.domian.HourseInfo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

/**
 * 二维地图 房屋dao方法的实现
 * 
 * @author zhanghuafei
 */
@Repository("hourseInfoDao")
public class HourseInfoDaoImpl extends AbstractDao<HourseInfo> implements HourseInfoDao {

	@Override
	public HourseInfo addHourseInfo(HourseInfo hourseInfo) {
		Long id = (Long) this.getSqlMapClientTemplate().insert("hourseInfo.addHourseInfo",
				hourseInfo);
		return getHourseInfoById(id);
	}
	
	@Override
	public HourseInfo updateHourseInfo(HourseInfo hourseInfo) {
		this.getSqlMapClientTemplate().update("hourseInfo.updateHourseInfo", hourseInfo);
		return getHourseInfoById(hourseInfo.getId());
	}

	@Override
	public HourseInfo getHourseInfoById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		return (HourseInfo) getSqlMapClientTemplate().queryForObject(
				"hourseInfo.getHourseInfoById", id);
	}

	@Override
	public void deleteHourseInfoById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		getSqlMapClientTemplate().delete("hourseInfo.deleteHourseInfoById", id);
	}

	@Override
	public PageInfo<HourseInfo> findTwoDimensionMapHoursePageInfoByOrgCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = getMap(orgInternalCode, null, "buildingname",
				sord, screenCoordinateVo, null, null, null, null);
		return getPageInfo(
				"hourseInfo.countTwoDimensionMapHourseInfosByOrgCodeAndScreenCoordinateVo",
				"hourseInfo.findTwoDimensionMapHoursePageInfoByOrgCodeAndScreenCoordinateVo",
				pageNum, pageSize, map);
	}

	@Override
	public HourseInfo getHourseInfoAndBoundObjectNumById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		return (HourseInfo) getSqlMapClientTemplate().queryForObject(
				"hourseInfo.getHourseInfoAndBoundObjectNumById", id);
	}

	@Override
	public Integer countHourseInfosTwoDimensionMapByOrgInternalCode(String orgInternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orginternalcode", orgInternalCode);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"hourseInfo.countBoundedTwoDimensionMapPageInfoByOrgInternalCode", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HourseInfo> findHourseInfosByOrgCodeAndScreenCoordinateVo(String orgInternalCode,
			ScreenCoordinateVo screenCoordinateVo) {
		Map<String, Object> map = getMap(orgInternalCode, null, null, null,
				screenCoordinateVo, null, null, null, null);
		return getSqlMapClientTemplate().queryForList(
				"hourseInfo.findTwoDimensionMapHoursePageInfoByOrgCodeAndScreenCoordinateVo", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HourseInfo> findUnboundHouseInfoByOrganizationId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		return getSqlMapClientTemplate().queryForList(
				"hourseInfo.findUnboundHouseInfoByOrganizationId", map);
	}

	@Override
	public HourseInfo getHourseInfoByFeatureId(String featureId) {
		if (featureId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return (HourseInfo) getSqlMapClientTemplate().queryForObject(
				"hourseInfo.getHourseInfoByFeatureId", featureId);
	}
}

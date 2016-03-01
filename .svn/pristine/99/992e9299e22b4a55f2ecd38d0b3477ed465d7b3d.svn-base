package com.tianque.openLayersMap.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.AbstractDao;
import com.tianque.openLayersMap.dao.GridTeamMapDao;
import com.tianque.openLayersMap.dao.HourseInfoDao;
import com.tianque.openLayersMap.domian.HourseInfo;
import com.tianque.openLayersMap.domian.vo.GirdTeamVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

/**
 * 二维地图   网格员管理dao实现
 * 
 * @author songzhiqiang
 */
@Repository("gridTeamMapDao")
public class GridTeamMapDaoImpl extends AbstractDao<GirdTeamVo> implements GridTeamMapDao {

	@Override
	public PageInfo<GirdTeamVo> getGridTeamInfoByType(String orCode, Long type,
			Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", orCode);
		map.put("type", type);
		
		return queryForPageInfo(
				"gridTeam.countTwoDimensionMapPageInfoByOrgIdAndTypeName",
				"gridTeam.findTwoDimensionMapPageInfoByOrgIdAndTypeName",
				pageNum, pageSize, map);
	}

	@Override
	public Integer get2DMapInfoBySerachType(String orgCode, String serachType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		map.put("serachType", serachType);
		return (Integer) queryForCount(
				"gridTeam.get2DMapInfoBySerachType",
				map);
	}

	@Override
	public Integer get2DMapInfoByRigthSerachType(String orgCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		return (Integer) queryForCount(
				"gridTeam.get2DMapInfoByRigthSerachType",
				map);
	}

	@Override
	public GirdTeamVo getGirdTeamVoInfoById(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id",  id);
		return (GirdTeamVo)queryForObject("gridTeam.getGirdTeamVoInfoById", map);
	}
}

package com.tianque.baseInfo.primaryOrg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.primaryOrg.dao.GridTeamDao;
import com.tianque.baseInfo.primaryOrg.domain.GridTeam;
import com.tianque.baseInfo.primaryOrg.domain.vo.GridTeamVo;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.AbstractBaseDao;

@Repository("gridTeamDao")
public class GridTeamDaoImpl extends AbstractBaseDao implements GridTeamDao{

	@Override
	public PageInfo<GridTeam> findGridTeamForList(
			GridTeam gridTeam, Integer page, Integer rows, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("orgCode", gridTeam.getOrganization().getOrgInternalCode());
		if(StringUtil.isStringAvaliable(gridTeam.getMemeberName())){
			map.put("memeberName", gridTeam.getMemeberName());
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"gridTeam.countFindGridTeamForList", map);

		List<GridTeam> list = getSqlMapClientTemplate().queryForList(
				"gridTeam.findGridTeamForList", map, (page - 1) * rows, rows);
		PageInfo<GridTeam> pageInfo = new PageInfo<GridTeam>(page, rows,
				countNum, list);
		return pageInfo;
	}

	@Override
	public GridTeam addGridTeam(GridTeam gridTeam) {
		Long id = (Long) getSqlMapClientTemplate().insert("gridTeam.addGridTeam",gridTeam);
		return getGridTeamById(id);
	}

	@Override
	public GridTeam updateGridTeam(GridTeam gridTeam) {
		getSqlMapClientTemplate().update("gridTeam.updateGridTeam",gridTeam);
		return getGridTeamById(gridTeam.getId());
	}

	@Override
	public void deleteGridTeamByIds(String[] ids) {
		getSqlMapClientTemplate().delete("gridTeam.deleteGridTeamByIds",ids);
	}
	
	@Override
	public void deleteGridTeamByUserIds(Long[] userIds){
		getSqlMapClientTemplate().delete("gridTeam.deleteGridTeamByUserIds",userIds);
	}
	
	@Override
	public GridTeam getGridTeamById(Long id) {
		return (GridTeam) getSqlMapClientTemplate().queryForObject("gridTeam.getGridTeamById",id);
	}

	@Override
	public Long countGridTeam(String orgCode) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"gridTeam.countIsActivatedFindGridTeam", orgCode);
	}

	@Override
	public Long countGridTeamByOrgAndPositionType(String orgCode,Long positionTypeId) {
		Map map=new HashMap();
		map.put("orgCode", orgCode);
		map.put("positionTypeId", positionTypeId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"gridTeam.countGridTeamByOrgAndPositionType", map);
	}

	@Override
	public Long countPhoneGridTeam(String orgCode) {
		return  (Long) getSqlMapClientTemplate().queryForObject(
				"gridTeam.countPhoneGridTeam", orgCode);
	}

	@Override
	public Long countUserDefinedGrid(String orgCode) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"gridTeam.countUserDefinedGrid", orgCode);
	}

	@Override
	public List<GridTeam> getGridTeamByPhoneNumber(String phoneNumber) {
		return getSqlMapClientTemplate().queryForList("gridTeam.getGridTeamByPhoneNumber",phoneNumber);
	}

	@Override
	public GridTeam getGridTeamByIdCardNo(Long orgId, String idCardNo) {
		Map map=new HashMap();
		map.put("orgId", orgId);
		map.put("idCardNo", idCardNo);
		return (GridTeam) getSqlMapClientTemplate().queryForObject("gridTeam.getGridTeamByIdCardNo",map);
	}

	@Override
	public GridTeam getGridTeamByUserId(Long userId) {
		return (GridTeam) getSqlMapClientTemplate().queryForObject("gridTeam.getGridTeamByUserId",userId);
	}

	@Override
	public List<GridTeamVo> statisticsGridTeam(Long parentOrgId, Long gridOrgLevelId,
			Long fullTimeId, Long partTimeId) {
		Map map=new HashMap();
		map.put("parentOrgId", parentOrgId);
		map.put("gridOrgLevelId", gridOrgLevelId);
		map.put("fullTimeId", fullTimeId);
		map.put("partTimeId", partTimeId);
		return getSqlMapClientTemplate().queryForList("gridTeam.statisticsGridTeam",map);
	}
}

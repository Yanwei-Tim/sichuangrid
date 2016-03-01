package com.tianque.baseInfo.orgLocationTrack.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.orgLocationTrack.domain.OrgLocationTracks;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;

@Repository("orgLocationTracksDao")
public class OrgLocationTracksDaoImpl extends AbstractBaseDao implements
		OrgLocationTracksDao {

	public PageInfo<OrgLocationTracks> findOrgLocationTracksByOrgIdAndName(
			Integer orgLocationOrgId, String orgLocationName, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgLocationOrgId", orgLocationOrgId);
		map.put("orgLocationName", orgLocationName);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"orgLocationTracks.countOrgLocationTracksByOrgIdAndName", map);
		if (sidx != null && !"".equals(sidx.trim())) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<OrgLocationTracks> list = getSqlMapClientTemplate().queryForList(
				"orgLocationTracks.findOrgLocationTracksByOrgIdAndName", map,
				(pageNum - 1) * pageSize, pageSize);
		return new PageInfo<OrgLocationTracks>(pageNum, pageSize, countNum,
				list);
	}

	public OrgLocationTracks addOrgLocationTracks(
			OrgLocationTracks orgLocationTracks) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"orgLocationTracks.addOrgLocationTracks", orgLocationTracks);
		return getOrgLocationTracksById(id);
	}

	private OrgLocationTracks getOrgLocationTracksById(Long id) {
		return (OrgLocationTracks) this.getSqlMapClientTemplate()
				.queryForObject("orgLocationTracks.getOrgLocationTracksById",
						id);
	}

}

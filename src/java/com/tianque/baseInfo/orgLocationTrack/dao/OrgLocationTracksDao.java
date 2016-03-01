package com.tianque.baseInfo.orgLocationTrack.dao;

import com.tianque.baseInfo.orgLocationTrack.domain.OrgLocationTracks;
import com.tianque.core.vo.PageInfo;

public interface OrgLocationTracksDao {

	PageInfo<OrgLocationTracks> findOrgLocationTracksByOrgIdAndName(
			Integer orgLocationOrgId, String orgLocationName, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	OrgLocationTracks addOrgLocationTracks(OrgLocationTracks orgLocationTracks);

}

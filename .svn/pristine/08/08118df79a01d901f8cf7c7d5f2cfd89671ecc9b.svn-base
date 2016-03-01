package com.tianque.baseInfo.roadTraffic.dao;

import com.tianque.baseInfo.roadTraffic.domain.RoadTraffic;
import com.tianque.core.vo.PageInfo;

public interface RoadTrafficDao {
	public PageInfo<RoadTraffic> findRoadTrafficForPageByOrgInternalCode(String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public RoadTraffic getRoadTrafficById(Long id);

	public RoadTraffic addRoadTraffic(RoadTraffic roadTraffic);

	public RoadTraffic updateRoadTraffic(RoadTraffic roadTraffic);

	// public void updateEmphasiseById(Long id, Boolean isEmphasis,String logOutReason);
	public void deleteRoadTrafficById(Long id);

	public RoadTraffic getRoadTrafficByObjNum(String objNum, Long orgId);

}

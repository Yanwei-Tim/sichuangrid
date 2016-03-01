package com.tianque.baseInfo.roadTraffic.service;

import com.tianque.baseInfo.roadTraffic.domain.RoadTraffic;
import com.tianque.core.vo.PageInfo;

public interface RoadTrafficService {
	public PageInfo<RoadTraffic> findRoadTrafficForPageByOrgInternalCode(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public RoadTraffic getRoadTrafficById(Long id);

	public RoadTraffic addRoadTraffic(RoadTraffic cleaner);

	public RoadTraffic updateRoadTraffic(RoadTraffic cleaner);

	public void deleteRoadTrafficByIds(Long[] ids);

	public boolean hasDuplicateRoadTrafficObjNum(Long orgId, String objNum, Long exceptedId);
	// public RoadTraffic updateRoadTrafficByObjNameAndOrgId(String ObjName, Long orgId, RoadTraffic
	// domain);
	// public void updateEmphasiseByIds(Long[] ids, Boolean isEmphasis,String logOutReason);
	// public RoadTraffic existRoadTraffic(String name,Long orgId);
}

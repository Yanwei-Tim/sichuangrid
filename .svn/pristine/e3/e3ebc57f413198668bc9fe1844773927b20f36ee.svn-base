package com.tianque.baseInfo.landscaping.service;

import com.tianque.baseInfo.landscaping.domain.Landscaping;
import com.tianque.core.vo.PageInfo;

public interface LandscapingService {
	public PageInfo<Landscaping> findLandscapingForPageByOrgInternalCode(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public Landscaping getLandscapingById(Long id);

	public Landscaping addLandscaping(Landscaping landscaping);

	public Landscaping updateLandscaping(Landscaping landscaping);

	public void deleteLandscapingByIds(Long[] ids);

	public boolean hasDuplicateLandscapingObjNum(Long orgId, String objNum, Long exceptedId);
	// public Landscaping updateLandscapingByObjNameAndOrgId(String ObjName, Long orgId, Landscaping
	// domain);
	// public void updateEmphasiseByIds(Long[] ids, Boolean isEmphasis,String logOutReason);
	// public Landscaping existLandscaping(String name,Long orgId);
}

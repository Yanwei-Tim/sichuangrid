package com.tianque.baseInfo.landscaping.dao;

import com.tianque.baseInfo.landscaping.domain.Landscaping;
import com.tianque.core.vo.PageInfo;

public interface LandscapingDao {
	public PageInfo<Landscaping> findLandscapingForPageByOrgInternalCode(String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public Landscaping getLandscapingById(Long id);

	public Landscaping addLandscaping(Landscaping landscaping);

	public Landscaping updateLandscaping(Landscaping landscaping);

	// public void updateEmphasiseById(Long id, Boolean isEmphasis,String logOutReason);
	public void deleteLandscapingById(Long id);

	public Landscaping getLandscapingByObjNum(String objNum, Long orgId);

}

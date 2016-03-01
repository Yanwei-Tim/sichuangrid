package com.tianque.baseInfo.otherFacilities.dao;

import com.tianque.baseInfo.otherFacilities.domain.OtherFacilities;
import com.tianque.core.vo.PageInfo;

public interface OtherFacilitiesDao {
	public PageInfo<OtherFacilities> findOtherFacilitiesForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize, String sidx, String sord);

	public OtherFacilities getOtherFacilitiesById(Long id);

	public OtherFacilities addOtherFacilities(OtherFacilities otherFacilities);

	public OtherFacilities updateOtherFacilities(OtherFacilities otherFacilities);

	// public void updateEmphasiseById(Long id, Boolean isEmphasis,String logOutReason);
	public void deleteOtherFacilitiesById(Long id);

	public OtherFacilities getOtherFacilitiesByObjNum(String objNum, Long orgId);

}

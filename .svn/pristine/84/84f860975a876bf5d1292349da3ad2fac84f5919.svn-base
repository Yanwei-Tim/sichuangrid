package com.tianque.baseInfo.otherFacilities.service;

import com.tianque.baseInfo.otherFacilities.domain.OtherFacilities;
import com.tianque.core.vo.PageInfo;

public interface OtherFacilitiesService {
	public PageInfo<OtherFacilities> findOtherFacilitiesForPageByOrgInternalCode(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public OtherFacilities getOtherFacilitiesById(Long id);

	public OtherFacilities addOtherFacilities(OtherFacilities otherFacilities);

	public OtherFacilities updateOtherFacilities(OtherFacilities otherFacilities);

	public void deleteOtherFacilitiesByIds(Long[] ids);

	public boolean hasDuplicateOtherFacilitiesObjNum(Long orgId, String objNum, Long exceptedId);
	// public OtherFacilities updateOtherFacilitiesByObjNameAndOrgId(String ObjName, Long orgId,
	// OtherFacilities domain);
	// public void updateEmphasiseByIds(Long[] ids, Boolean isEmphasis,String logOutReason);
	// public OtherFacilities existOtherFacilities(String name,Long orgId);
}

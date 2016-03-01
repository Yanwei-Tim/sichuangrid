package com.tianque.baseInfo.idleYouth.dao;

import java.util.List;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchIdleYouthVo;

public interface IdleYouthDao extends
		BaseInfoPopulationBaseDao<IdleYouth, SearchIdleYouthVo> {

	public PageInfo<IdleYouth> findIdleYouthsForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String order, Long isEmphasis);

	public IdleYouth getIdleYouthByIdCardNoAndOrganizationId(String idCardNo15,
			String idCardNo18, Long organizationId);

	public Long addStaffType(Long idleYouthId, Long staffTypeId);

	public void deleteStaffTypeByIdleYouthId(Long idleYouthId);

	public void updateEmphasiseById(Long id, Long isEmphasis);

	public List<IdleYouth> findIdleYouthsByBIRTHDAY();

	public void updateDeathAndLogoutStateById(Long long1, Boolean death,
			Long logoutState);

	public List<Long> getStaffTypeIdsByIdleYouthId(Long id);

	public Integer countIdleYouthsByBirthdayForMark();

	public List<IdleYouth> findIdleYouthsByBirthdayForMark(int pageNum,
			int pageSize);
	
	public void updateTableUpdateDateById(Long id);

}

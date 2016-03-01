package com.tianque.partyBuilding.doubleRegActivities.service;

import java.util.Date;

import com.tianque.core.base.BaseService;
import com.tianque.partyBuilding.doubleRegActivities.domain.PartyOrgReport;
import com.tianque.partyBuilding.doubleRegActivities.domain.Vo.SearchPartyOrgReportVo;

/**
 * 党组织报到表:业务逻辑层接口
 * 
 * @author
 * @date 2014-02-25 11:13:54
 */
public interface PartyOrgReportService extends
		BaseService<PartyOrgReport, SearchPartyOrgReportVo> {

	/**
	 * 根据ids更新党组织报到的关注情况
	 * 
	 * @param ids
	 * @param isEmphasis
	 * @param logOutReason
	 * @param logOutTime
	 */
	void updateEmphasiseByIds(Long[] ids, Long isEmphasis, String logOutReason,
			Date logOutTime);

	/**
	 * 验证是否存在相同的单位名称
	 * 
	 * @param id
	 * @param orgId
	 * @param name
	 * @return
	 */
	String hasDuplicateName(Long id, Long orgId, String name);

	/**
	 * 根据ids和类型删除党组织报到信息
	 * 
	 * @param ids
	 */
	void deletePartyorgReportByIdsAndType(Long[] ids, String type);

}

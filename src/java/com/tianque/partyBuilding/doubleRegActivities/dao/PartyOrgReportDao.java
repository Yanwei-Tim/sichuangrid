package com.tianque.partyBuilding.doubleRegActivities.dao;

import java.util.Date;

import com.tianque.core.base.BaseDao;
import com.tianque.partyBuilding.doubleRegActivities.domain.PartyOrgReport;
import com.tianque.partyBuilding.doubleRegActivities.domain.Vo.SearchPartyOrgReportVo;

/**
 * 党组织报到表:数据操作层接口
 * 
 * @author
 * @date 2014-02-25 11:13:54
 */
public interface PartyOrgReportDao extends
		BaseDao<PartyOrgReport, SearchPartyOrgReportVo> {

	/**
	 * 根据name得到党组织报到信息
	 * 
	 * @param name
	 * @return
	 */
	PartyOrgReport getPartyOrgReportByNameAndOrgId(String name, Long orgId);

	/**
	 * 根据id更新天网的关注情况
	 * 
	 * @param id
	 * @param isEmphasis
	 * @param logOutReason
	 * @param logOutTime
	 */
	void updateEmphasiseById(Long id, Long isEmphasis, String logOutReason,
			Date logOutTime);

	/**
	 * 批量根据id更新天网的关注情况
	 * 
	 * @param id
	 * @param isEmphasis
	 * @param logOutReason
	 * @param logOutTime
	 */
	void updateEmphasiseByIds(Long[] idList, Long isEmphasis,
			String logOutReason, Date logOutTime);

	/**
	 * 根据id和类型删除党组织报到信息
	 * 
	 * @param id
	 * @param type
	 */
	void deletePartyorgReportByIdAndType(Long id, String type);

	/**
	 * 根据ids和类型删除党组织报到信息
	 * 
	 * @param id
	 * @param type
	 */
	void deletePartyorgReportByIdsAndType(Long[] ids, String type);

}

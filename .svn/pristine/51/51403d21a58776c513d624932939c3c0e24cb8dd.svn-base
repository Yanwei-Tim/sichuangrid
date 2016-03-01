package com.tianque.partyBuilding.doubleRegActivities.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.doubleRegActivities.dao.PartyOrgReportDao;
import com.tianque.partyBuilding.doubleRegActivities.domain.PartyOrgReport;
import com.tianque.partyBuilding.doubleRegActivities.domain.Vo.SearchPartyOrgReportVo;

/**
 * 党组织报到表:数据操作层
 * 
 * @author
 * @date 2014-02-25 11:13:54
 */
@Repository("partyOrgReportDao")
public class PartyorgReportDaoImpl extends
		BaseDaoImpl<PartyOrgReport, SearchPartyOrgReportVo> implements
		PartyOrgReportDao {

	@Override
	public PartyOrgReport getPartyOrgReportByNameAndOrgId(String name,
			Long orgId) {
		if (name == null || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("orgId", orgId);
		return (PartyOrgReport) getSqlMapClientTemplate().queryForObject(
				"partyOrgReport.getPartyOrgReportByNameAndOrgId", map);
	}

	@Override
	public void updateEmphasiseById(Long id, Long isEmphasis,
			String logOutReason, Date logOutTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		map.put("logoutReason", logOutReason);
		map.put("logoutTime", logOutTime);
		getSqlMapClientTemplate().update("partyOrgReport.updateEmphasiseById",
				map);

	}

	@Override
	public void updateEmphasiseByIds(Long[] idList, Long isEmphasis,
			String logOutReason, Date logOutTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idList", idList);
		map.put("isEmphasis", isEmphasis);
		map.put("logoutReason", logOutReason);
		map.put("logoutTime", logOutTime);
		getSqlMapClientTemplate().update("partyOrgReport.updateEmphasiseByIds",
				map);

	}

	@Override
	public void deletePartyorgReportByIdAndType(Long id, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		getSqlMapClientTemplate().delete(
				"partyOrgReport.deletePartyorgReportByIdAndType", map);
	}

	@Override
	public void deletePartyorgReportByIdsAndType(Long[] ids, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idList", ids);
		map.put("type", type);
		getSqlMapClientTemplate().delete(
				"partyOrgReport.deletePartyorgReportByIdsAndType", map);
	}

}

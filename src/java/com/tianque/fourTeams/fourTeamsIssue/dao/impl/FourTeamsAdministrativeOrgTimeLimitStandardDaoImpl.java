package com.tianque.fourTeams.fourTeamsIssue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsAdministrativeOrgTimeLimitStandardDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsAdministrativeOrgTimeLimitStandard;

@Repository("fourTeamsAdministrativeOrgTimeLimitStandardDao")
public class FourTeamsAdministrativeOrgTimeLimitStandardDaoImpl extends
		AbstractBaseDao implements
		FourTeamsAdministrativeOrgTimeLimitStandardDao {

	@Override
	public FourTeamsAdministrativeOrgTimeLimitStandard addAdministrativeOrgTimeLimitStandard(
			FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
		Long id = (Long) getSqlMapClientTemplate()
				.insert(
						"fourTeamsAdministrativeOrgTimeLimitStandard.addAdministrativeOrgTimeLimitStandard",
						administrativeOrgTimeLimitStandard);
		return getAdministrativeOrgTimeLimitStandardById(id);
	}

	@Override
	public FourTeamsAdministrativeOrgTimeLimitStandard updateAdministrativeOrgTimeLimitStandard(
			FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
		getSqlMapClientTemplate()
				.update(
						"fourTeamsAdministrativeOrgTimeLimitStandard.updateAdministrativeOrgTimeLimitStandard",
						administrativeOrgTimeLimitStandard);
		return getAdministrativeOrgTimeLimitStandardById(administrativeOrgTimeLimitStandard
				.getId());
	}

	@Override
	public void deleteAdministrativeOrgTimeLimitStandardById(Long id) {
		getSqlMapClientTemplate()
				.delete(
						"fourTeamsAdministrativeOrgTimeLimitStandard.deleteAdministrativeOrgTimeLimitStandard",
						id);
	}

	@Override
	public PageInfo<FourTeamsAdministrativeOrgTimeLimitStandard> findAdministrativeOrgTimeLimitStandardByOrgInternalId(
			Long orgInternalId, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalId", orgInternalId);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("createOrg", ThreadVariable.getOrganization().getId());
		return createPageInfo(
				pageNum,
				pageSize,
				(Integer) getSqlMapClientTemplate()
						.queryForObject(
								"fourTeamsAdministrativeOrgTimeLimitStandard.countAdministrativeOrgTimeLimitStandardByOrgInternalId",
								map),
				getSqlMapClientTemplate()
						.queryForList(
								"fourTeamsAdministrativeOrgTimeLimitStandard.findAdministrativeOrgTimeLimitStandardByOrgInternalId",
								map));
	}

	@Override
	public FourTeamsAdministrativeOrgTimeLimitStandard getAdministrativeOrgTimeLimitStandardById(
			Long id) {

		return (FourTeamsAdministrativeOrgTimeLimitStandard) getSqlMapClientTemplate()
				.queryForObject(
						"fourTeamsAdministrativeOrgTimeLimitStandard.getAdministrativeOrgTimeLimitStandardById",
						id);
	}

	private PageInfo<FourTeamsAdministrativeOrgTimeLimitStandard> createPageInfo(
			int pageNum, int pageSize, Integer countNum,
			List<FourTeamsAdministrativeOrgTimeLimitStandard> list) {
		PageInfo<FourTeamsAdministrativeOrgTimeLimitStandard> pageInfo = new PageInfo<FourTeamsAdministrativeOrgTimeLimitStandard>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public FourTeamsAdministrativeOrgTimeLimitStandard getAdministrativeOrgTimeLimitStandardByUseLevelId(
			Long useLevelId) {

		return (FourTeamsAdministrativeOrgTimeLimitStandard) getSqlMapClientTemplate()
				.queryForObject(
						"fourTeamsAdministrativeOrgTimeLimitStandard.getAdministrativeOrgTimeLimitStandardByUseLevelId",
						useLevelId);
	}

	@Override
	public FourTeamsAdministrativeOrgTimeLimitStandard getDefaultAdministrativeOrgTimeLimitStandard() {
		return (FourTeamsAdministrativeOrgTimeLimitStandard) getSqlMapClientTemplate()
				.queryForObject(
						"fourTeamsAdministrativeOrgTimeLimitStandard.getDefaultAdministrativeOrgTimeLimitStandard");
	}

	@Override
	public FourTeamsAdministrativeOrgTimeLimitStandard getAdminOrgTimeLimitStandardByOrgLevelIdAndIssueTypeId(
			Long orgLevelId, Long issueTypeId, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgLevelId", orgLevelId);
		map.put("issueTypeId", issueTypeId);
		map.put("createOrg", orgId);
		return (FourTeamsAdministrativeOrgTimeLimitStandard) getSqlMapClientTemplate()
				.queryForObject(
						"fourTeamsAdministrativeOrgTimeLimitStandard.getAdminOrgTimeLimitStandardByOrgLevelIdAndIssueTypeId",
						map);
	}

	@Override
	public boolean validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId(
			FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
		Integer count = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"fourTeamsAdministrativeOrgTimeLimitStandard.validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId",
						administrativeOrgTimeLimitStandard);
		return count == null || count == 0;
	}
}

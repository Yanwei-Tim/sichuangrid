package com.tianque.issue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.issue.dao.AdministrativeOrgTimeLimitStandardDao;
import com.tianque.issue.domain.AdministrativeOrgTimeLimitStandard;

@Repository("administrativeOrgTimeLimitStandardDao")
public class AdministrativeOrgTimeLimitStandardDaoImpl extends AbstractBaseDao
		implements AdministrativeOrgTimeLimitStandardDao {

	@Override
	public AdministrativeOrgTimeLimitStandard addAdministrativeOrgTimeLimitStandard(
			AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
		Long id = (Long) getSqlMapClientTemplate()
				.insert("administrativeOrgTimeLimitStandard.addAdministrativeOrgTimeLimitStandard",
						administrativeOrgTimeLimitStandard);
		return getAdministrativeOrgTimeLimitStandardById(id);
	}

	@Override
	public AdministrativeOrgTimeLimitStandard updateAdministrativeOrgTimeLimitStandard(
			AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
		getSqlMapClientTemplate()
				.update("administrativeOrgTimeLimitStandard.updateAdministrativeOrgTimeLimitStandard",
						administrativeOrgTimeLimitStandard);
		return getAdministrativeOrgTimeLimitStandardById(administrativeOrgTimeLimitStandard
				.getId());
	}

	@Override
	public void deleteAdministrativeOrgTimeLimitStandardById(Long id) {
		getSqlMapClientTemplate()
				.delete("administrativeOrgTimeLimitStandard.deleteAdministrativeOrgTimeLimitStandard",
						id);
	}

	@Override
	public PageInfo<AdministrativeOrgTimeLimitStandard> findAdministrativeOrgTimeLimitStandardByOrgInternalId(
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
								"administrativeOrgTimeLimitStandard.countAdministrativeOrgTimeLimitStandardByOrgInternalId",
								map),
				getSqlMapClientTemplate()
						.queryForList(
								"administrativeOrgTimeLimitStandard.findAdministrativeOrgTimeLimitStandardByOrgInternalId",
								map, (pageNum - 1) * pageSize, pageSize));
	}

	@Override
	public AdministrativeOrgTimeLimitStandard getAdministrativeOrgTimeLimitStandardById(
			Long id) {

		return (AdministrativeOrgTimeLimitStandard) getSqlMapClientTemplate()
				.queryForObject(
						"administrativeOrgTimeLimitStandard.getAdministrativeOrgTimeLimitStandardById",
						id);
	}

	private PageInfo<AdministrativeOrgTimeLimitStandard> createPageInfo(
			int pageNum, int pageSize, Integer countNum,
			List<AdministrativeOrgTimeLimitStandard> list) {
		PageInfo<AdministrativeOrgTimeLimitStandard> pageInfo = new PageInfo<AdministrativeOrgTimeLimitStandard>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public AdministrativeOrgTimeLimitStandard getAdministrativeOrgTimeLimitStandardByUseLevelId(
			Long useLevelId) {

		return (AdministrativeOrgTimeLimitStandard) getSqlMapClientTemplate()
				.queryForObject(
						"administrativeOrgTimeLimitStandard.getAdministrativeOrgTimeLimitStandardByUseLevelId",
						useLevelId);
	}

	@Override
	public AdministrativeOrgTimeLimitStandard getDefaultAdministrativeOrgTimeLimitStandard() {
		return (AdministrativeOrgTimeLimitStandard) getSqlMapClientTemplate()
				.queryForObject(
						"administrativeOrgTimeLimitStandard.getDefaultAdministrativeOrgTimeLimitStandard");
	}

	@Override
	public AdministrativeOrgTimeLimitStandard getAdminOrgTimeLimitStandardByOrgLevelIdAndIssueTypeId(
			Long orgLevelId, Long issueTypeId, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgLevelId", orgLevelId);
		map.put("issueTypeId", issueTypeId);
		map.put("createOrg", orgId);
		return (AdministrativeOrgTimeLimitStandard) getSqlMapClientTemplate()
				.queryForObject(
						"administrativeOrgTimeLimitStandard.getAdminOrgTimeLimitStandardByOrgLevelIdAndIssueTypeId",
						map);
	}

	@Override
	public boolean validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId(
			AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
		Integer count = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"administrativeOrgTimeLimitStandard.validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId",
						administrativeOrgTimeLimitStandard);
		return count == null || count == 0;
	}

	public List<AdministrativeOrgTimeLimitStandard> getAdministrativeOrgTimeLimitStandardByOrginternalid(
			Long createOrg, Long internalId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("internalId", internalId);
		map.put("createOrg", createOrg);
		return getSqlMapClientTemplate()
				.queryForList(
						"administrativeOrgTimeLimitStandard.getAdministrativeOrgTimeLimitStandardByOrginternalid",
						map);
	}
}

package com.tianque.working.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.SignificantIssuedeal;
import com.tianque.domain.vo.SignificantIssuedealVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.working.dao.SignificantIssuedealDao;

@Repository("significantIssuedealDao")
public class SignificantIssuedealDaoImpl extends AbstractBaseDao implements
		SignificantIssuedealDao {

	@Override
	public SignificantIssuedeal addSignificantIssuedeal(
			SignificantIssuedeal significantIssuedeal) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"significantIssuedeal.addSignificantIssuedeal",
				significantIssuedeal);
		return getSignificantIssuedealById(id);
	}

	@Override
	public PageInfo<SignificantIssuedeal> findSignificantIssuedealsByOrgIdAndDirectoryId(
			Long orgId, String directoryId, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("directoryId", directoryId);
		query.put("sortField", sortField);
		query.put("order", order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"significantIssuedeal.countSignificantIssuedeals", query);
		List<SignificantIssuedeal> list = getSqlMapClientTemplate()
				.queryForList("significantIssuedeal.findSignificantIssuedeals",
						query, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<SignificantIssuedeal> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<SignificantIssuedeal> pageInfo = new PageInfo<SignificantIssuedeal>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public PageInfo<SignificantIssuedeal> findSignificantIssuedealsForPageByOrgIdAndDirectoryParentId(
			Long organizationId, Long directoryId, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", organizationId);
		query.put("directoryId", directoryId);
		query.put("sortField", sortField);
		query.put("order", order);

		return findSignificantIssuedeals(
				"significantIssuedeal.countSignificantIssuedealsByOrgIdAndDirectoryParentId",
				"significantIssuedeal.findSignificantIssuedealsByOrgIdAndDirectoryParentId",
				query, pageNum, pageSize, sortField, order);
	}

	@Override
	public PageInfo<SignificantIssuedeal> getSignificantIssuedealsByInvestigationDate(
			SignificantIssuedealVo significantIssuedealVo, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		Integer countSignificantIssuedeal = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"significantIssuedeal.countSignificantIssuedealsByInvestigationDate",
						significantIssuedealVo);
		int pageCount = 0;
		if ((countSignificantIssuedeal % pageSize) == 0) {
			pageCount = countSignificantIssuedeal / pageSize;
		} else {
			pageCount = countSignificantIssuedeal / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<SignificantIssuedeal> list = getSqlMapClientTemplate()
				.queryForList(
						"significantIssuedeal.getSignificantIssuedealsByInvestigationDate",
						significantIssuedealVo, (pageNum - 1) * pageSize,
						pageSize);
		PageInfo<SignificantIssuedeal> pageInfo = new PageInfo<SignificantIssuedeal>();
		pageInfo.setResult(list);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setTotalRowSize(countSignificantIssuedeal);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public SignificantIssuedeal getSignificantIssuedealById(Long id) {
		return (SignificantIssuedeal) getSqlMapClientTemplate().queryForObject(
				"significantIssuedeal.getSignificantIssuedealById", id);
	}

	@Override
	public boolean deleteSignificantIssuedealById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("ID为空");
		}
		int i = getSqlMapClientTemplate().delete(
				"significantIssuedeal.deleteSignificantIssuedealById", id);
		return i > 0 ? true : false;
	}

	@Override
	public SignificantIssuedeal updateSignificantIssuedeal(
			SignificantIssuedeal significantIssuedeal) {
		getSqlMapClientTemplate().update(
				"significantIssuedeal.updateSignificantIssuedeal",
				significantIssuedeal);
		return getSignificantIssuedealById(significantIssuedeal.getId());
	}

	@Override
	public SignificantIssuedeal updateSignificantIssuedealForStautsAndReportTimeById(
			Long id, Long status, Date now, Long expiredEntering) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (id == null || status == null) {
			throw new BusinessValidationException("参数错误");
		}
		map.put("id", id);
		map.put("status", status);
		map.put("reportedDate", now);
		map.put("expiredEntering", expiredEntering);

		getSqlMapClientTemplate()
				.update("significantIssuedeal.updateSignificantIssuedealForStautsAndReportTimeById",
						map);
		return getSignificantIssuedealById(id);
	}

	@Override
	public List<SignificantIssuedeal> findSignificantIssuedealsForPrint(
			Long organizationId, String selectids) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", organizationId);
		query.put("significantIssuedealIds", selectids);

		List<SignificantIssuedeal> list = getSqlMapClientTemplate()
				.queryForList(
						"significantIssuedeal.findSignificantIssuedealsForPrint",
						query);
		return list;
	}

	private PageInfo<SignificantIssuedeal> findSignificantIssuedeals(
			String countStatementName, String listStatementName,
			Map<String, Object> query, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		Integer countSignificantIssuedeal = (Integer) getSqlMapClientTemplate()
				.queryForObject(countStatementName, query);

		int pageCount = 0;
		if ((countSignificantIssuedeal % pageSize) == 0) {
			pageCount = countSignificantIssuedeal / pageSize;
		} else {
			pageCount = countSignificantIssuedeal / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<SignificantIssuedeal> list = getSqlMapClientTemplate()
				.queryForList(listStatementName, query,
						(pageNum - 1) * pageSize, pageSize);

		PageInfo<SignificantIssuedeal> pageInfo = new PageInfo<SignificantIssuedeal>();
		pageInfo.setResult(list);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setTotalRowSize(countSignificantIssuedeal);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public List<SignificantIssuedeal> findSignificantIssuedeals(Long orgId,
			Long directoryId) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("directoryId", directoryId);
		return getSqlMapClientTemplate().queryForList(
				"significantIssuedeal.findSignificantIssuedeals", query);
	}

	@Override
	public PageInfo<SignificantIssuedeal> searchSignificantIssuedealWorkingRecord(
			SignificantIssuedealVo searchSignificantIssuedealWorkingRecord,
			Integer pageNum, Integer pageSize, String sortField, String order,
			String allDailyDirectoryId) {
		if (searchSignificantIssuedealWorkingRecord == null)
			return emptyAttentionObjectPage(pageSize);
		Map<String, Object> map = createParams(
				searchSignificantIssuedealWorkingRecord, allDailyDirectoryId);
		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"significantIssuedeal.countSearchSignificantIssuedealWorkingRecord",
						map);
		map.put("order", order);
		map.put("sortField", sortField);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<SignificantIssuedeal> list = getSqlMapClientTemplate()
				.queryForList(
						"significantIssuedeal.searchSignificantIssuedealWorkingRecordByQuery",
						map, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private Map<String, Object> createParams(
			SignificantIssuedealVo searchSignificantIssuedealWorkingRecord,
			String allDailyDirectoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (searchSignificantIssuedealWorkingRecord.getOrgIdsList()!=null
				&& searchSignificantIssuedealWorkingRecord.getOrgIdsList().size()>0) {
			map.put("investigationOrgIdList",searchSignificantIssuedealWorkingRecord.getOrgIdsList());
		}
		if (searchSignificantIssuedealWorkingRecord.getInvestigationMinDate() != null) {
			map.put("investigationMinDate",
					searchSignificantIssuedealWorkingRecord
							.getInvestigationMinDate());
		}
		if (searchSignificantIssuedealWorkingRecord.getInvestigationMaxDate() != null) {
			map.put("investigationMaxDate",
					searchSignificantIssuedealWorkingRecord
							.getInvestigationMaxDate());
		}
		if (StringUtil.isStringAvaliable(allDailyDirectoryId)) {
			map.put("dailyDirectoryId", allDailyDirectoryId);
		}
		map.put("orgId", searchSignificantIssuedealWorkingRecord
				.getOrganization().getId());
		if (StringUtil
				.isStringAvaliable(searchSignificantIssuedealWorkingRecord
						.getAccountabilityLeading())) {
			map.put("accountabilityLeading",
					searchSignificantIssuedealWorkingRecord
							.getAccountabilityLeading());
		}
		if (StringUtil
				.isStringAvaliable(searchSignificantIssuedealWorkingRecord
						.getAccountabilityUnit())) {
			map.put("accountabilityUnit",
					searchSignificantIssuedealWorkingRecord
							.getAccountabilityUnit());
		}
		if (StringUtil
				.isStringAvaliable(searchSignificantIssuedealWorkingRecord
						.getAddress())) {
			map.put("address",
					searchSignificantIssuedealWorkingRecord.getAddress());
		}
		if (StringUtil
				.isStringAvaliable(searchSignificantIssuedealWorkingRecord
						.getSignificantIssuedealReason())) {
			map.put("significantIssuedealReason",
					searchSignificantIssuedealWorkingRecord
							.getSignificantIssuedealReason());
		}
		if (StringUtil
				.isStringAvaliable(searchSignificantIssuedealWorkingRecord
						.getRemarks())) {
			map.put("remarks",
					searchSignificantIssuedealWorkingRecord.getRemarks());
		}
		return map;
	}

	private PageInfo<SignificantIssuedeal> emptyAttentionObjectPage(int pageSize) {
		PageInfo<SignificantIssuedeal> pageInfo = new PageInfo<SignificantIssuedeal>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SignificantIssuedeal>());
		return pageInfo;
	}

	@Override
	public SignificantIssuedeal getSignificantIssuedealByFromId(Long fromId) {
		return (SignificantIssuedeal) getSqlMapClientTemplate().queryForObject(
				"significantIssuedeal.getSignificantIssuedealByFromId", fromId);
	}

}

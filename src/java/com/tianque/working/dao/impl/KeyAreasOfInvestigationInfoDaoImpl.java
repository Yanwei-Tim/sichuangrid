package com.tianque.working.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.KeyAreasOfInvestigationInfo;
import com.tianque.domain.vo.KeyAreasOfInvestigationInfoVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.working.dao.KeyAreasOfInvestigationInfoDao;

@Repository("keyAreasOfInvestigationInfoDao")
public class KeyAreasOfInvestigationInfoDaoImpl extends AbstractBaseDao
		implements KeyAreasOfInvestigationInfoDao {

	@Override
	public KeyAreasOfInvestigationInfo addKeyAreasOfInvestigationInfo(
			KeyAreasOfInvestigationInfo keyAreasOfInvestigationInfo) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"keyAreasOfInvestigationInfo.addKeyAreasOfInvestigationInfo",
				keyAreasOfInvestigationInfo);
		return getKeyAreasOfInvestigationInfoById(id);
	}

	@Override
	public PageInfo<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfosByOrgIdAndDirectoryId(
			Long orgId, String directoryId, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("directoryId", directoryId);
		query.put("sortField", sortField);
		query.put("order", order);

		return findKeyAreasOfInvestigationInfos(
				"keyAreasOfInvestigationInfo.countKeyAreasOfInvestigationInfos",
				"keyAreasOfInvestigationInfo.findKeyAreasOfInvestigationInfos",
				query, pageNum, pageSize, sortField, order);
	}

	@Override
	public List<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfosForPrint(
			Long organizationId, String selectids) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", organizationId);
		query.put("keyAreasOfInvestigationInfoIds", selectids);

		List<KeyAreasOfInvestigationInfo> list = getSqlMapClientTemplate()
				.queryForList(
						"keyAreasOfInvestigationInfo.findKeyAreasOfInvestigationInfosForPrint",
						query);

		return list;

	}

	@Override
	public PageInfo<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfosForPageByOrgIdAndDirectoryParentId(
			Long organizationId, Long directoryId, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", organizationId);
		query.put("directoryId", directoryId);
		query.put("sortField", sortField);
		query.put("order", order);

		return findKeyAreasOfInvestigationInfos(
				"keyAreasOfInvestigationInfo.countKeyAreasOfInvestigationInfosByOrgIdAndDirectoryParentId",
				"keyAreasOfInvestigationInfo.findKeyAreasOfInvestigationInfosByOrgIdAndDirectoryParentId",
				query, pageNum, pageSize, sortField, order);
	}

	@Override
	public PageInfo<KeyAreasOfInvestigationInfo> getKeyAreasOfInvestigationInfosByInvestigationDate(
			KeyAreasOfInvestigationInfoVo keyAreasOfInvestigationInfoVo,
			Integer pageNum, Integer pageSize, String sortField, String order) {

		Integer countKeyAreasOfInvestigationInfo = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"keyAreasOfInvestigationInfo.countKeyAreasOfInvestigationInfosByInvestigationDate",
						keyAreasOfInvestigationInfoVo);
		int pageCount = 0;
		if ((countKeyAreasOfInvestigationInfo % pageSize) == 0) {
			pageCount = countKeyAreasOfInvestigationInfo / pageSize;
		} else {
			pageCount = countKeyAreasOfInvestigationInfo / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<KeyAreasOfInvestigationInfo> list = getSqlMapClientTemplate()
				.queryForList(
						"keyAreasOfInvestigationInfo.getKeyAreasOfInvestigationInfosByInvestigationDate",
						keyAreasOfInvestigationInfoVo,
						(pageNum - 1) * pageSize, pageSize);
		PageInfo<KeyAreasOfInvestigationInfo> pageInfo = new PageInfo<KeyAreasOfInvestigationInfo>();
		pageInfo.setResult(list);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setTotalRowSize(countKeyAreasOfInvestigationInfo);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public KeyAreasOfInvestigationInfo getKeyAreasOfInvestigationInfoById(
			Long id) {
		return (KeyAreasOfInvestigationInfo) getSqlMapClientTemplate()
				.queryForObject(
						"keyAreasOfInvestigationInfo.getKeyAreasOfInvestigationInfosById",
						id);
	}

	@Override
	public boolean deleteKeyAreasOfInvestigationInfoById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("ID为空");
		}
		int i = getSqlMapClientTemplate()
				.delete("keyAreasOfInvestigationInfo.deleteKeyAreasOfInvestigationInfoById",
						id);
		return i > 0 ? true : false;
	}

	@Override
	public KeyAreasOfInvestigationInfo updateKeyAreasOfInvestigationInfo(
			KeyAreasOfInvestigationInfo keyAreasOfInvestigationInfo) {
		getSqlMapClientTemplate()
				.update("keyAreasOfInvestigationInfo.updateKeyAreasOfInvestigationInfo",
						keyAreasOfInvestigationInfo);
		return getKeyAreasOfInvestigationInfoById(keyAreasOfInvestigationInfo
				.getId());
	}

	@Override
	public KeyAreasOfInvestigationInfo updateKeyAreasOfInvestigationInfoForStautsAndReportTimeById(
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
				.update("keyAreasOfInvestigationInfo.updateKeyAreasOfInvestigationInfoForStautsAndReportTimeById",
						map);
		return getKeyAreasOfInvestigationInfoById(id);
	}

	private PageInfo<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfos(
			String countStatementName, String listStatementName,
			Map<String, Object> query, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		Integer countKeyAreasOfInvestigationInfo = (Integer) getSqlMapClientTemplate()
				.queryForObject(countStatementName, query);

		int pageCount = 0;
		if ((countKeyAreasOfInvestigationInfo % pageSize) == 0) {
			pageCount = countKeyAreasOfInvestigationInfo / pageSize;
		} else {
			pageCount = countKeyAreasOfInvestigationInfo / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<KeyAreasOfInvestigationInfo> list = getSqlMapClientTemplate()
				.queryForList(listStatementName, query,
						(pageNum - 1) * pageSize, pageSize);

		PageInfo<KeyAreasOfInvestigationInfo> pageInfo = new PageInfo<KeyAreasOfInvestigationInfo>();
		pageInfo.setResult(list);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setTotalRowSize(countKeyAreasOfInvestigationInfo);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Integer countKeyAreasOfInvestigationInfoById(Long orgId, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("id", id);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"keyAreasOfInvestigationInfo.countKeyAreasOfInvestigationInfoById",
						map);

	}

	@Override
	public List<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfos(
			Long orgId, Long directoryId) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("directoryId", directoryId);

		return getSqlMapClientTemplate().queryForList(
				"keyAreasOfInvestigationInfo.findKeyAreasOfInvestigationInfos",
				query);
	}

	@Override
	public PageInfo<KeyAreasOfInvestigationInfo> searchKeyAreasOfInvestigationInfoWorkingRecord(
			KeyAreasOfInvestigationInfoVo searchActivityWorkingRecord,
			Integer pageNum, Integer pageSize, String sortField, String order,
			String allDailyDirectoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (searchActivityWorkingRecord == null){
			return PageInfo.emptyPage(pageSize);
		}
		if (StringUtil.isStringAvaliable(searchActivityWorkingRecord
				.getAreaName())) {
			map.put("areaName", searchActivityWorkingRecord.getAreaName());
		}
		map.put("orgId", searchActivityWorkingRecord.getOrganization().getId());
		if (searchActivityWorkingRecord.getOrgIdsList()!=null
				&& searchActivityWorkingRecord.getOrgIdsList().size()>0) {
			map.put("orgIdsList", searchActivityWorkingRecord.getOrgIdsList());
		}
		if (searchActivityWorkingRecord.getInvestigationMinDate() != null) {
			map.put("investigationMinDate",
					searchActivityWorkingRecord.getInvestigationMinDate());
		}
		if (searchActivityWorkingRecord.getInvestigationMaxDate() != null) {
			map.put("investigationMaxDate",
					searchActivityWorkingRecord.getInvestigationMaxDate());
		}
		if (StringUtil.isStringAvaliable(allDailyDirectoryId)) {
			map.put("dailyDirectoryId", allDailyDirectoryId);
		}
		if (StringUtil.isStringAvaliable(searchActivityWorkingRecord
				.getMainProblem())) {
			map.put("mainProblem", searchActivityWorkingRecord.getMainProblem());
		}
		if (StringUtil.isStringAvaliable(searchActivityWorkingRecord
				.getPoliciesAndMeasures())) {
			map.put("policiesAndMeasures",
					searchActivityWorkingRecord.getPoliciesAndMeasures());
		}
		if (StringUtil.isStringAvaliable(searchActivityWorkingRecord
				.getRemark())) {
			map.put("remark", searchActivityWorkingRecord.getRemark());
		}
		if (StringUtil.isStringAvaliable(searchActivityWorkingRecord
				.getWarningOrListing())) {
			map.put("warningOrListing",
					searchActivityWorkingRecord.getWarningOrListing());
		}
		map.put("order", order);
		map.put("sortField", sortField);
		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"keyAreasOfInvestigationInfo.countSearchKeyAreasOfInvestigationInfoWorkingRecord",
						map);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<KeyAreasOfInvestigationInfo> list = getSqlMapClientTemplate()
				.queryForList(
						"keyAreasOfInvestigationInfo.searchKeyAreasOfInvestigationInfoWorkingRecordByDate",
						map, (pageNum - 1) * pageSize, pageSize);
		return new PageInfo<KeyAreasOfInvestigationInfo>(pageNum, pageSize, countNum, list);
	}
}

package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchKeyAreasOfInvestigationInfoWorkingRecordDao;
import com.tianque.domain.KeyAreasOfInvestigationInfo;
import com.tianque.domain.vo.KeyAreasOfInvestigationInfoVo;

@Repository("searchKeyAreasOfInvestigationInfoWorkingRecordDao")
public class SearchKeyAreasOfInvestigationInfoWorkingRecordDaoImpl extends AbstractBaseDao
		implements SearchKeyAreasOfInvestigationInfoWorkingRecordDao {

	@Override
	public PageInfo<KeyAreasOfInvestigationInfo> searchKeyAreasOfInvestigationInfoWorkingRecord(
			KeyAreasOfInvestigationInfoVo searchActivityWorkingRecord, Integer pageNum,
			Integer pageSize, String sortField, String order, String allDailyDirectoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (searchActivityWorkingRecord == null)
			return emptyAttentionObjectPage(pageSize);
		if (StringUtil.isStringAvaliable(searchActivityWorkingRecord.getAreaName())) {
			map.put("areaName", searchActivityWorkingRecord.getAreaName());
		}
		map.put("orgId", searchActivityWorkingRecord.getOrganization().getId());
		if (searchActivityWorkingRecord.getOrgIdsList()!=null
				&& searchActivityWorkingRecord.getOrgIdsList().size()>0) {
			map.put("orgIdsList", searchActivityWorkingRecord.getOrgIdsList());
		}
		if (searchActivityWorkingRecord.getInvestigationMinDate() != null) {
			map.put("investigationMinDate", searchActivityWorkingRecord.getInvestigationMinDate());
		}
		if (searchActivityWorkingRecord.getInvestigationMaxDate() != null) {
			map.put("investigationMaxDate", searchActivityWorkingRecord.getInvestigationMaxDate());
		}
		if (StringUtil.isStringAvaliable(allDailyDirectoryId)) {
			map.put("dailyDirectoryId", allDailyDirectoryId);
		}
		if (StringUtil.isStringAvaliable(searchActivityWorkingRecord.getMainProblem())) {
			map.put("mainProblem", searchActivityWorkingRecord.getMainProblem());
		}
		if (StringUtil.isStringAvaliable(searchActivityWorkingRecord.getPoliciesAndMeasures())) {
			map.put("policiesAndMeasures", searchActivityWorkingRecord.getPoliciesAndMeasures());
		}
		if (StringUtil.isStringAvaliable(searchActivityWorkingRecord.getRemark())) {
			map.put("remark", searchActivityWorkingRecord.getRemark());
		}
		map.put("order", order);
		map.put("sortField", sortField);
		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchKeyAreasOfInvestigationInfoWorkingRecord.countSearchKeyAreasOfInvestigationInfoWorkingRecord",
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
						"searchKeyAreasOfInvestigationInfoWorkingRecord.searchKeyAreasOfInvestigationInfoWorkingRecordByDate",
						map, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<KeyAreasOfInvestigationInfo> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<KeyAreasOfInvestigationInfo> pageInfo = new PageInfo<KeyAreasOfInvestigationInfo>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<KeyAreasOfInvestigationInfo> emptyAttentionObjectPage(int pageSize) {
		PageInfo<KeyAreasOfInvestigationInfo> pageInfo = new PageInfo<KeyAreasOfInvestigationInfo>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<KeyAreasOfInvestigationInfo>());
		return pageInfo;
	}
}

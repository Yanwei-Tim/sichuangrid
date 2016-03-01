package com.tianque.analysis.api;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.BusinessDescription;

public interface BusinessDescriptionDubboService {
	public BusinessDescription getBusinessDescriptionById(Long id);

	public BusinessDescription addBusinessDescription(
			BusinessDescription businessDescription);

	public BusinessDescription updateBusinessDescription(
			BusinessDescription businessDescription);

	public int deleteBusinessDescriptionByIds(List<Long> ids);

	public PageInfo<BusinessDescription> findBusinessDescriptionForPage(
			int pageNum, int pageSize, String sortField, String order,
			BusinessDescription businessDescription);

	public String getDescriptionForStatistics(String keyName, Integer year,
			Integer month, Long orgId);

	public BusinessDescription getBusinessDescriptionByKeyName(String keyName);
}

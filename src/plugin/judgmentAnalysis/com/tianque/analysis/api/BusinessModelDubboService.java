package com.tianque.analysis.api;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.BusinessModel;

public interface BusinessModelDubboService {

	public BusinessModel getBusinessModelById(Long id);

	public BusinessModel addBusinessModel(BusinessModel businessModel);

	public BusinessModel updateBusinessModel(BusinessModel businessModel);

	public int deleteBusinessModelByIds(List<Long> ids);

	public PageInfo<BusinessModel> findBusinessModelForPage(int pageNum,
			int pageSize, String sortField, String order,
			BusinessModel businessModel);

}

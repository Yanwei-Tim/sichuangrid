package com.tianque.plugin.dataManage.location.companyPlaceTemp.service;

import org.oproject.framework.orm.PageResult;

import com.tianque.plugin.dataManage.location.companyPlaceTemp.domain.CompanyPlaceTemp;

public interface CompanyPlaceTempService {

	public PageResult<CompanyPlaceTemp> loadCompanyPlaceTempForPageResult(
			CompanyPlaceTemp companyPlaceTemp, int pageNum, int pageSize);
}

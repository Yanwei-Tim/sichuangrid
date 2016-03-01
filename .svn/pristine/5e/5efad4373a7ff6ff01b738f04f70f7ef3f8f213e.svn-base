package com.tianque.plugin.dataManage.location.companyPlaceTemp.service;

import org.oproject.framework.orm.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.dataManage.base.service.LocationDataManageServiceImpl;
import com.tianque.plugin.dataManage.location.companyPlaceTemp.dao.CompanyPlaceTempDAO;
import com.tianque.plugin.dataManage.location.companyPlaceTemp.domain.CompanyPlaceTemp;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("companyPlaceTempService")
public class CompanyPlaceTempServiceImpl extends LocationDataManageServiceImpl
		implements CompanyPlaceTempService {

	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private CompanyPlaceTempDAO companyPlaceTempDAO;

	public final static Logger logger = LoggerFactory
			.getLogger(CompanyPlaceTempServiceImpl.class);

	@Override
	public PageResult<CompanyPlaceTemp> loadCompanyPlaceTempForPageResult(
			CompanyPlaceTemp companyPlaceTemp, int pageNum, int pageSize) {
		PageResult<CompanyPlaceTemp> pageResult = null;
		try {
			pageResult = companyPlaceTempDAO
					.queryCompanyPlaceTempForPageResult(companyPlaceTemp,
							pageNum, pageSize);
		} catch (Exception e) {
			throw new ServiceValidationException("查询出错", e);
		}
		return pageResult;
	}

}

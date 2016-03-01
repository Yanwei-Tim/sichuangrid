package com.tianque.plugin.dataManage.location.companyPlaceTemp.dao;

import org.oproject.framework.orm.PageResult;
import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.plugin.dataManage.location.companyPlaceTemp.domain.CompanyPlaceTemp;

@DynamicIbatisDAO(value = "companyPlaceTempDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("companyPlaceTempDao")
public interface CompanyPlaceTempDAO {

	public PageResult<CompanyPlaceTemp> queryCompanyPlaceTempForPageResult(
			CompanyPlaceTemp companyplace, int page, int rows);

}
